package com.finance.pms.events.calculation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
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
	private final EventInfo eventInfo;

	//FIXME ?? some calculator may not support in parallel calculation and will need synchronisation
	protected SelectedIndicatorsCalculationThread(
			Stock stock, Date startDate, Date endDate,
			EventInfo eventInfo, String eventListName,
			Observer... observers) {

		this.configs = ConfigThreadLocal.getAll();
		this.observers = observers;
		for (Observer observer : observers) {
			this.addObserver(observer);
		}

		this.stock = stock;
		this.startDate = startDate;
		this.endDate = endDate;

		this.eventInfo = eventInfo;
		this.eventListName = eventListName;

	}

	public SymbolEvents call() throws IncompleteDataSetException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yy HH:mm:ss");
		try {
			MyLogger.threadLocal.set(stock.getSymbol());

			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME,this.configs.get(Config.EVENT_SIGNAL_NAME));
			ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME,this.configs.get(Config.INDICATOR_PARAMS_NAME));
			
			LOGGER.guiInfo("Calculating " + eventInfo.getEventReadableDef() + " for stock " + stock.toString() + " between " + dateFormat.format(startDate) + " and " + dateFormat.format(endDate));
			SymbolEvents symbolEvents = calculate(startDate, endDate);
			LOGGER.guiInfo("Finishing " + eventInfo.getEventReadableDef() + " for stock " + stock.toString() + " between " + dateFormat.format(startDate) + " and " + dateFormat.format(endDate));
						
			return symbolEvents;

		} catch (IncompleteDataSetException e) {
			LOGGER.error("IncompleteDataSet: While calculating Events for " + stock + ", analysis " + eventListName + " and " + eventInfo.getEventDefinitionRef(), e);
			throw e;
		} catch (Exception e) {
			LOGGER.error("UnHandled error: While calculating Events for " + stock + ", analysis " + eventListName + " and " + eventInfo.getEventDefinitionRef(), e);
			throw new IncompleteDataSetException(stock, new HashSet<>(), e.toString());
		} finally {
			this.setChanged();
			this.notifyObservers(new ObserverMsg(stock, ObsKey.NONE));
		}

	}

	//TODO Only handle EventInfoOpsCompoOperation and get rid of First and Second pass
	protected SymbolEvents calculate(Date start, Date end) throws IncompleteDataSetException, Exception {

		SymbolEvents symbolEvents = new SymbolEvents(stock);

		//FIXME dummyTargetStock: operations method should not be called outside of the operation run() loop and hence the following should be delegated to the event info. 
		TargetStockInfo dummyTargetStock = new TargetStockInfo(eventListName, (EventInfoOpsCompoOperation) eventInfo, stock, DateFactory.dateAtZero(), DateFactory.dateAtZero());
		boolean isIdempotent = eventInfo instanceof EventInfoOpsCompoOperation && ((EventInfoOpsCompoOperation) eventInfo).isIdemPotent(dummyTargetStock);
		boolean forbidEventsOverride = eventInfo instanceof EventInfoOpsCompoOperation && ((EventInfoOpsCompoOperation) eventInfo).isNoOverrideDeltaOnly(dummyTargetStock);
		
		Optional<TunedConf> tunedConfOpt = TunedConfMgr.getInstance().loadUniqueNoRetuneConfig(stock, eventListName, eventInfo.getEventDefinitionRef());
		boolean hasPreviousCalculations = tunedConfOpt.isPresent();
		boolean isAlterableOverridable = !isIdempotent && !forbidEventsOverride;
		TunedConf tunedConf = hasPreviousCalculations?tunedConfOpt.get():TunedConfMgr.getInstance().saveUniqueNoRetuneConfig(stock, eventListName, eventInfo.getEventDefinitionRef(), isAlterableOverridable);
		
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
					if (eventInfo instanceof EventInfoOpsCompoOperation) {
						calculator = new ParameterizedIndicatorsOperator(eventInfo, stock, adjustedStart, adjustedEnd, currency, eventListName, observers);
					} else {
						calculator = legacyEventDefinitionHandling(stock);
					}

					//If FULL_RANGE there maybe previous calculations to delete (when old range is within new range)
					if (calcBounds.getCalcStatus().equals(CalcStatus.FULL_RANGE)) {
						EventsResources.getInstance().crudDeleteEventsForStock(stock, eventListName, eventInfo);
					}

					//Calculation
					Quotations quotations = QuotationsFactories.getFactory().getSplitFreeQuotationsInstance(stock, adjustedStart, adjustedEnd, true, currency, calculator.getStartShift(), calculator.quotationsValidity());
					SortedMap<EventKey, EventValue> calculatorEvents = calculator.calculateEventsFor(quotations, eventListName);

					if (calculatorEvents != null) {//There are results or empty results
						
						LOGGER.info("Received (calculated) " + calculatorEvents.size() + " events for " + symbolEvents.getSymbol() + " using analysis " + eventListName + " and event def " + eventInfo.getEventDefinitionRef() + " from " + adjustedStart + " to " + adjustedEnd);
						
						//FIXME the storage should be delegated to the eventInfo or calculator
						//FIXME And the control of the calculation state in db, here, should be done through tunedConf only.
						//isNoOverrideDeltaOnly
						if (isFullCalculationForbidOverride) {//Fixing the events boundaries to return only the new ones we want to store with no override of the existing ones
							if (calcBounds.getCalcStatus().equals(CalcStatus.RIGHT_INC)) {
								Date lastEventInDb = tunedConf.getLastStoredEventCalculationEnd();
								LOGGER.info(((EventInfoOpsCompoOperation) eventInfo).getReference() + " for " + stock + " reducing result to tail from " + lastEventInDb);
								calculatorEvents = calculatorEvents.entrySet().stream()
									.filter(e -> e.getKey().getDate().after(lastEventInDb))
									.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (a, b) -> a, TreeMap::new));
								LOGGER.info("Received (to be stored " + calcBounds.getCalcStatus() + ") " + calculatorEvents.size() + " events for " + symbolEvents.getSymbol() + " using analysis " + eventListName + " and event def " + eventInfo.getEventDefinitionRef() + " from " + lastEventInDb + " to " + adjustedEnd);
							}
							if (calcBounds.getCalcStatus().equals(CalcStatus.LEFT_INC)) {
								Date firstEventInDb = tunedConf.getFisrtStoredEventCalculationStart();
								LOGGER.info(((EventInfoOpsCompoOperation) eventInfo).getReference() + " for " + stock + " reducing result to head to " + firstEventInDb);
								calculatorEvents = calculatorEvents.entrySet().stream()
									.filter(e -> e.getKey().getDate().before(firstEventInDb))
									.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (a, b) -> a, TreeMap::new));
								LOGGER.info("Received (to be stored " + calcBounds.getCalcStatus() + ") " + calculatorEvents.size() + " events for " + symbolEvents.getSymbol() + " using analysis " + eventListName + " and event def " + eventInfo.getEventDefinitionRef() + " from " + adjustedStart + " to " + firstEventInDb);
							}
							if (calcBounds.getCalcStatus().equals(CalcStatus.NONE)) {
								LOGGER.info(((EventInfoOpsCompoOperation) eventInfo).getReference() + " for " + stock + " reducing result to none");
								calculatorEvents = new TreeMap<>();
								LOGGER.info("Received (to be stored " + calcBounds.getCalcStatus() + ") " + calculatorEvents.size() + " events for " + symbolEvents.getSymbol() + " using analysis " + eventListName + " and event def " + eventInfo.getEventDefinitionRef() + " from " + adjustedStart + " to " + adjustedEnd);
							}
						}
						symbolEvents.addCalculationOutput(eventInfo, calculator.calculationOutput());
						symbolEvents.addEventResultElement(calculatorEvents, eventInfo);

					} else {//No results
						LOGGER.warn("Failed (null returned) calculation for " + symbolEvents.getSymbol() + " using analysis " + eventListName + " and " + eventInfo.getEventDefinitionRef() + " from " + adjustedStart + " to " + adjustedEnd);
						symbolEvents.addCalculationOutput(eventInfo, new TreeMap<>());
						symbolEvents.addEventResultElement(new TreeMap<>(), eventInfo);
						throw new IncompleteDataSetException(stock, symbolEvents, "Some calculations have failed! Are failing: " + eventInfo);
					}

				} else {//No calculation needed
					LOGGER.info(
							"Recalculation requested for " + stock + " using analysis " + eventListName + " and " + eventInfo.getEventDefinitionRef() +
							" from " + adjustedStart + " to " + adjustedEnd + ". " +
							"No recalculation needed calculation bound is " + calcBounds.toString());
					symbolEvents.addCalculationOutput(eventInfo, new TreeMap<>());
					symbolEvents.addEventResultElement(new TreeMap<>(), eventInfo);
				}

			//Error(s) as occurred. This should invalidate tuned conf and potentially generated events.
			} catch (InvalidAlgorithmParameterException | WarningException | NoQuotationsException e) {
				// Unrecoverable
				LOGGER.error( "Failed (Empty Unrecoverable) calculation for " + stock + " using analysis " + eventListName +  " and " +
								eventInfo.getEventDefinitionRef() + " with calculation bounds: " + calcBounds, e);
				symbolEvents.addCalculationOutput(eventInfo, new TreeMap<>());
				symbolEvents.addEventResultElement(new TreeMap<>(), eventInfo);
				throw new IncompleteDataSetException(stock, symbolEvents, "Some calculations have failed! Are failing: " + eventInfo);
				
			} catch (Throwable e) {
				//ErrorException e && e.getCause() instanceof StackException && isNoOverrideDeltaOnly
				LOGGER.error( "Failed (Empty Recoverable??) calculation (isNoOverride: " + isFullCalculationForbidOverride + ") for " +
						stock + " using analysis " + eventListName +  " and " +
						eventInfo.getEventDefinitionRef() + " with calculation bounds: " + calcBounds, e);
				symbolEvents.addCalculationOutput(eventInfo, new TreeMap<>());
				symbolEvents.addEventResultElement(new TreeMap<>(), eventInfo);
				throw new IncompleteDataSetException(stock, symbolEvents, "Some calculations have failed! Are failing: " + eventInfo);

			} finally {
				LOGGER.info("Updating tunedConf: " + tunedConf + " with calculation bounds: " + calcBounds);
				if (calcBounds != null) {
					TunedConfMgr.getInstance().updateConf(tunedConf, calcBounds.getNewTunedConfStart(), calcBounds.getNewTunedConfEnd());
				}
			}

		}

		return symbolEvents;

	}

	private IndicatorsOperator legacyEventDefinitionHandling(Stock stock) {
		try {
			SpringContext springContext = SpringContext.getSingleton();
			@SuppressWarnings("unchecked")
			Map<String, String> nativeCalcsMap = (Map<String, String>) springContext.getBean("availableSecondPassIndicatorCalculators");
			IndicatorsOperator calculator = instanciateECC(stock, eventInfo, nativeCalcsMap.get(eventInfo.getEventDefinitionRef()), observers);
			return calculator;
		} catch (Throwable e) {
			LOGGER.warn("Event definition not supported : " + eventInfo, e);
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
