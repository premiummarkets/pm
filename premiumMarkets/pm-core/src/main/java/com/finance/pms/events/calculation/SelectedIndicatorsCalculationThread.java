package com.finance.pms.events.calculation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.calculation.parametrizedindicators.ParameterizedIndicatorsOperator;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.conditional.EventInfoOpsCompoOperation;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.CalculationBounds;
import com.finance.pms.events.scoring.TunedConf;
import com.finance.pms.events.scoring.TunedConfMgr;
import com.finance.pms.events.scoring.TunedConfMgr.CalcStatus;
import com.finance.pms.events.utils.EventsStatusChecker;
import com.finance.pms.threads.ConfigThreadLocal;
import com.finance.pms.threads.ObserverMsg;
import com.finance.pms.threads.ObserverMsg.ObsKey;

public class SelectedIndicatorsCalculationThread extends Observable implements Callable<SymbolEvents> {

	private static MyLogger LOGGER = MyLogger.getLogger(SelectedIndicatorsCalculationThread.class);

	protected Map<String,Config> configs;
	private Observer[] observers;

	private final Stock stock;
	private final Date startDate;
	private final Date endDate;

	private final String eventListName;
	private final EventInfo eventInfoCln;

	//FIXME ?? some calculator may not support in parallel calculation and will need synchronisation
	protected SelectedIndicatorsCalculationThread(Stock stock, Date startDate, Date endDate, EventInfo eventInfo, String eventListName, Observer... observers) {

		this.configs = ConfigThreadLocal.getAll();
		this.observers = observers;
		for (Observer observer : observers) {
			this.addObserver(observer);
		}

		this.stock = stock;
		this.startDate = startDate;
		this.endDate = endDate;

		//this.eventInfo = (eventInfo instanceof EventInfoOpsCompoOperation)?(EventInfo) ((EventInfoOpsCompoOperation)eventInfo).clone():eventInfo;
		this.eventInfoCln = eventInfo; //if EventInfoOpsCompoOperation, it should have been cloned by the caller..
		this.eventListName = eventListName;

	}

	public SymbolEvents call() throws IncompleteDataSetException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yy HH:mm:ss");
		try {
			MyLogger.threadLocal.set(stock.getSymbol());

			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME,this.configs.get(Config.EVENT_SIGNAL_NAME));
			ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME,this.configs.get(Config.INDICATOR_PARAMS_NAME));
			
			LOGGER.guiInfo("Calculating " + eventInfoCln.getEventReadableDef() + " for stock " + stock.toString() + " between " + dateFormat.format(startDate) + " and " + dateFormat.format(endDate));
			SymbolEvents symbolEvents = calculate(startDate, endDate);
			LOGGER.guiInfo("Finishing " + eventInfoCln.getEventReadableDef() + " for stock " + stock.toString() + " between " + dateFormat.format(startDate) + " and " + dateFormat.format(endDate));
						
			return symbolEvents;

		} catch (IncompleteDataSetException e) {
			LOGGER.error("IncompleteDataSet: While calculating Events for " + stock + ", analysis " + eventListName + " and " + eventInfoCln.getEventDefinitionRef(), e);
			throw e;
		} catch (Exception e) {
			LOGGER.error("UnHandled error: While calculating Events for " + stock + ", analysis " + eventListName + " and " + eventInfoCln.getEventDefinitionRef(), e);
			throw new IncompleteDataSetException(stock, new SymbolEvents(stock), e.toString());
		} finally {
			this.setChanged();
			this.notifyObservers(new ObserverMsg(stock, ObsKey.NONE));
		}

	}

	//TODO Only handle EventInfoOpsCompoOperation and get rid of First and Second pass
	protected SymbolEvents calculate(Date start, Date end) throws IncompleteDataSetException {

		SymbolEvents returnedSymbolEvents = new SymbolEvents(stock);

		//FIXME dummyTargetStock: operations method should not be called outside of the operation run() loop and hence the following should be delegated to the EventInfoOpsCompoOperation calculation.
		TargetStockInfo dummyTargetStock = new TargetStockInfo(eventListName, (EventInfoOpsCompoOperation) eventInfoCln, stock, start, end);
		boolean isIdempotent = eventInfoCln instanceof EventInfoOpsCompoOperation && ((EventInfoOpsCompoOperation) eventInfoCln).isIdemPotent(dummyTargetStock);
		boolean forbidEventsOverride = eventInfoCln instanceof EventInfoOpsCompoOperation && ((EventInfoOpsCompoOperation) eventInfoCln).isNoOverrideDeltaOnly(dummyTargetStock);
		
		Optional<TunedConf> tunedConfOpt = TunedConfMgr.getInstance().loadUniqueNoRetuneConfig(stock, eventListName, eventInfoCln);
		boolean hasPreviousCalculations = tunedConfOpt.isPresent() && !tunedConfOpt.get().isEmpty();
		boolean isAlterableOverridable = !isIdempotent && !forbidEventsOverride;
		TunedConf tunedConf = hasPreviousCalculations?tunedConfOpt.get():TunedConfMgr.getInstance().saveUniqueNoRetuneConfig(stock, eventListName, eventInfoCln, isAlterableOverridable);
		tunedConf.setIsRemovable(isAlterableOverridable);
		
		//When xx, we still want to calculate the full range as the operation is not idempotent but without overriding the existing data in the db
		boolean isFullCalculationForbidOverride = !isIdempotent && forbidEventsOverride;
		boolean isFullCalculationGrantOverride =  isAlterableOverridable;
		
		LOGGER.info(
				"CalculationBounds constraints: isIdempotent " + isIdempotent + ", forbidEventsOverride " + forbidEventsOverride + 
				" isFullCalculationForbidOverride " + isFullCalculationForbidOverride + ", isFullCalculationGrantOverride " + isFullCalculationGrantOverride);
		
		synchronized (tunedConf) {

			CalculationBounds calcBounds = null;
			try {
				
				EventsStatusChecker checker = new EventsStatusChecker(tunedConf);
				if (isFullCalculationGrantOverride) { //Not idempotent (alterable) and can override
					calcBounds = new CalculationBounds(CalcStatus.FULL_RANGE, start, end, start, end);
				} else { //Idempotent or Not idempotent and no override
					calcBounds = checker.setDatesBounds(start, end, isFullCalculationForbidOverride);
				}

				Date adjustedStart = calcBounds.getPmStart();
				Date adjustedEnd = calcBounds.getPmEnd();

				if (!calcBounds.getCalcStatus().equals(CalcStatus.NONE) || isFullCalculationForbidOverride) { //Calculation needed

					//Calculator preparation
					Currency currency = stock.getMarketValuation().getCurrency();
					IndicatorsOperator calculator;
					if (eventInfoCln instanceof EventInfoOpsCompoOperation) {
						calculator = new ParameterizedIndicatorsOperator(dummyTargetStock, eventInfoCln, stock, adjustedStart, adjustedEnd, currency, eventListName, observers);
					} else {
						calculator = legacyEventDefinitionHandling(stock);
					}

					//If FULL_RANGE there maybe previous calculations to delete (when old range is within new range)
					//Should only be here if isFullCalculationForbidOverride is false OR there is no known previous calculation
					//If the state of isFullCalculationForbidOverride has changed over time from true to false, this delete will break. 
					if (calcBounds.getCalcStatus().equals(CalcStatus.FULL_RANGE)) {
						EventsResources.getInstance().crudDeleteEventsForStock(stock, eventListName, eventInfoCln);
					}

					//Calculation
					Quotations quotations = QuotationsFactories.getFactory().getSplitFreeQuotationsInstance(stock, adjustedStart, adjustedEnd, true, currency, calculator.getStartShift(), calculator.quotationsValidity());
					SortedMap<EventKey, EventValue> calculatorEvents = calculator.calculateEventsFor(quotations, eventListName);

					if (calculatorEvents != null) {//There are results or empty results
						
						LOGGER.info("Received (calculated) " + calculatorEvents.size() + " events for " + returnedSymbolEvents.getSymbol() + " using analysis " + eventListName + " and event def " + eventInfoCln.getEventDefinitionRef() + " from " + adjustedStart + " to " + adjustedEnd);
						
						//FIXME the storage should be delegated to the eventInfo or calculator
						//FIXME And the control of the calculation state in db, here, should be done through tunedConf only.
						SortedMap<EventKey, EventValue> storedEvents = calculatorEvents;
						//isNoOverrideDeltaOnly
						if (isFullCalculationForbidOverride) {//Fixing the events boundaries to return only the new ones we want to store with no override of the existing ones
							if (calcBounds.getCalcStatus().equals(CalcStatus.RIGHT_INC)) {
								Date lastEventInDb = tunedConf.getLastStoredEventCalculationEnd();
								LOGGER.info(((EventInfoOpsCompoOperation) eventInfoCln).getReference() + " for " + stock + " reducing new stored events to tail from " + lastEventInDb);
								storedEvents = calculatorEvents.entrySet().stream()
									.filter(e -> e.getKey().getDate().after(lastEventInDb))
									.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (a, b) -> a, TreeMap::new));
							}
							if (calcBounds.getCalcStatus().equals(CalcStatus.LEFT_INC)) {
								Date firstEventInDb = tunedConf.getFisrtStoredEventCalculationStart();
								LOGGER.info(((EventInfoOpsCompoOperation) eventInfoCln).getReference() + " for " + stock + " reducing new stored events to head to " + firstEventInDb);
								storedEvents = calculatorEvents.entrySet().stream()
									.filter(e -> e.getKey().getDate().before(firstEventInDb))
									.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (a, b) -> a, TreeMap::new));
							}
							if (calcBounds.getCalcStatus().equals(CalcStatus.NONE)) {
								LOGGER.info(((EventInfoOpsCompoOperation) eventInfoCln).getReference() + " for " + stock + " reducing new stored events to none");
								storedEvents = new TreeMap<>();
							}
						}
						
						//FIXME the storage should be delegated to the eventInfo or calculator and coupled with TunedConf update
						LOGGER.info("Received (to be stored " + calcBounds.getCalcStatus() + ") " + storedEvents.size() + " events for " + returnedSymbolEvents.getSymbol() + " using analysis " + eventListName + " and event def " + eventInfoCln.getEventDefinitionRef() + " from " + adjustedStart + " to " + adjustedEnd);
						SymbolEvents storedSymbolEvents = new SymbolEvents(stock);
						storedSymbolEvents.addEventResultElement(storedEvents, eventInfoCln);
						EventsResources.getInstance().crudCreateEvents(storedSymbolEvents, eventListName, eventInfoCln);
						
						returnedSymbolEvents.addCalculationOutput(eventInfoCln, calculator.calculationOutput());
						returnedSymbolEvents.addEventResultElement(calculatorEvents, eventInfoCln);

					} else {//No results
						
						LOGGER.warn("Failed (null returned) calculation for " + returnedSymbolEvents.getSymbol() + " using analysis " + eventListName + " and " + eventInfoCln.getEventDefinitionRef() + " from " + adjustedStart + " to " + adjustedEnd);
						emptyReturn(returnedSymbolEvents);
						throw new IncompleteDataSetException(stock, returnedSymbolEvents, "Some calculations have failed! Are failing: " + eventInfoCln + "\nCause: nothing returned.");
						
					}

				} else {//No calculation needed
					LOGGER.info(
							"Recalculation requested for " + stock + " using analysis " + eventListName + " and " + eventInfoCln.getEventDefinitionRef() +
							" from " + adjustedStart + " to " + adjustedEnd + ". " +
							"No recalculation needed calculation bound is " + calcBounds.toString());
					emptyReturn(returnedSymbolEvents);
				}
				
				LOGGER.info("Updating tunedConf (on successful calculation): " + tunedConf + " with calculation bounds: " + calcBounds);
				if (calcBounds != null) {
					TunedConfMgr.getInstance().updateConf(tunedConf, eventInfoCln, calcBounds.getNewTunedConfStart(), calcBounds.getNewTunedConfEnd());
				}
				
			//Error(s) as occurred. This should invalidate tuned conf and potentially generated events.
			} catch (InvalidAlgorithmParameterException | WarningException | NoQuotationsException e) {
				// Unrecoverable
				LOGGER.error( "Failed (Empty Unrecoverable) calculation for " + stock + " using analysis " + eventListName +  " and " +
								eventInfoCln.getEventDefinitionRef() + " with calculation bounds: " + calcBounds, e);
				LOGGER.info("Updating tunedConf (on unrecovarable exception): " + tunedConf + " with calculation bounds: " + calcBounds);
				if (calcBounds != null) {
					TunedConfMgr.getInstance().updateConf(tunedConf, eventInfoCln,calcBounds.getNewTunedConfStart(), calcBounds.getNewTunedConfEnd());
				}
				
				emptyReturn(returnedSymbolEvents);
				throw new IncompleteDataSetException(stock, returnedSymbolEvents, "Some calculations have failed! Are failing: " + eventInfoCln + "\nCause: " + e.getMessage());
				
			} catch (Throwable e) {
				//ErrorException e && e.getCause() instanceof StackException && isNoOverrideDeltaOnly
				LOGGER.error( "Failed (Empty Recoverable??) calculation for " + stock + " using analysis " + eventListName +  " and " +
						eventInfoCln.getEventDefinitionRef() + " with calculation bounds: " + calcBounds, e);
				LOGGER.info("Rolling back tunedConf (on recovarable exception): " + tunedConf + ", attempted calculation bounds: " + calcBounds);
				if (calcBounds != null) {
					TunedConfMgr.getInstance().updateConf(tunedConf, eventInfoCln, tunedConf.getFisrtStoredEventCalculationStart(), tunedConf.getLastStoredEventCalculationEnd());
				}
				
				emptyReturn(returnedSymbolEvents);
				throw new IncompleteDataSetException(stock, returnedSymbolEvents, "Some calculations have failed! Are failing: " + eventInfoCln + "\nCause: " + e.getMessage());

			}
		}

		return returnedSymbolEvents;

	}

	private void emptyReturn(SymbolEvents returnedSymbolEvents) {
		returnedSymbolEvents.addCalculationOutput(eventInfoCln, new TreeMap<>());
		returnedSymbolEvents.addEventResultElement(new TreeMap<>(), eventInfoCln);
	}

	private IndicatorsOperator legacyEventDefinitionHandling(Stock stock) {
		try {
			SpringContext springContext = SpringContext.getSingleton();
			@SuppressWarnings("unchecked")
			Map<String, String> nativeCalcsMap = (Map<String, String>) springContext.getBean("availableSecondPassIndicatorCalculators");
			IndicatorsOperator calculator = instanciateECC(stock, eventInfoCln, nativeCalcsMap.get(eventInfoCln.getEventDefinitionRef()), observers);
			return calculator;
		} catch (Throwable e) {
			LOGGER.warn("Event definition not supported : " + eventInfoCln, e);
			throw new RuntimeException(e);
		}
	}

	private IndicatorsOperator instanciateECC(Stock stock, EventInfo eventInfo, String eventCompositionCalculatorStr, Observer... observers) throws Throwable {

		@SuppressWarnings("unchecked")
		Class<IndicatorsOperator> eventCompositionCalculator = (Class<IndicatorsOperator>) Class.forName(eventCompositionCalculatorStr);
		try {

			Constructor<IndicatorsOperator> constructor = eventCompositionCalculator.getConstructor(EventInfo.class, Stock.class, Date.class, Date.class, Currency.class, String.class, Observer[].class);
			return constructor.newInstance(eventInfo, stock, startDate, endDate, stock.getMarketValuation().getCurrency(), eventListName, observers);

		} catch (InvocationTargetException e) {
			if (e.getCause() instanceof WarningException || e.getCause() instanceof NotEnoughDataException) {
				if (e.getCause() instanceof NotEnoughDataException || (e.getCause().getCause() != null && e.getCause().getCause() instanceof NotEnoughDataException) ) {
					LOGGER.warn("Failed calculation : Not Enough Data " + warnMessage(stock, eventInfo.toString(), startDate, endDate));
				} else {
					LOGGER.warn("Failed calculation : " + warnMessage(stock, eventInfo.toString(), startDate, endDate) + " cause : \n" + e.getCause());
				}
				if (e.getCause() != null) throw e.getCause();
			} else if (e.getCause() instanceof ErrorException) {
				LOGGER.error(stock + " second pass calculation error ", e);
				if (e.getCause() != null) throw e.getCause();
			} else {
				LOGGER.error(
						String.format("%s second pass calculation un handled error.\n"
								+ "Parameters :\n"
								+ "event def = %s, start date = %s, end date = %s, calc currency = %s, event list name = '%s'", stock, eventInfo, startDate, endDate, stock.getMarketValuation().getCurrency(), eventListName), e);
				if (e.getCause() != null) throw e.getCause();
			}
			if (e.getCause() != null) throw e.getCause(); else throw e;
		} catch (Exception e) {
			LOGGER.error(stock+ " second pass calculation error ", e);
			throw e;
		}
	}

	private String warnMessage( Stock stock, String calculatorName, Date startDate, Date endDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return String.format("%s can't be calculated for %s between %s and %s", calculatorName, stock.getName(), simpleDateFormat.format(startDate), simpleDateFormat.format(endDate));
	}

}
