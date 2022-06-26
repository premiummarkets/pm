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
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.Callable;

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
			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME,this.configs.get(Config.EVENT_SIGNAL_NAME));
			ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME,this.configs.get(Config.INDICATOR_PARAMS_NAME));

			LOGGER.guiInfo("Calculating " + eventInfo.getEventReadableDef()+" for stock " + stock.toString() + " between "+dateFormat.format(startDate) + " and " + dateFormat.format(endDate));
			SymbolEvents symbolEvents = calculate(startDate, endDate);
			LOGGER.guiInfo("Finishing " + eventInfo.getEventReadableDef()+" for stock " + stock.toString() + " between "+dateFormat.format(startDate) + " and " + dateFormat.format(endDate));
			return symbolEvents;

		} catch (IncompleteDataSetException e) {
			LOGGER.error("UnHandled error : While calculating Events for " + stock + ", analysis " + eventListName + " and " + eventInfo.getEventDefinitionRef(), e);
			throw e;
		} catch (Exception e) {
			LOGGER.error("UnHandled error : While calculating Events for " + stock + ", analysis " + eventListName + " and " + eventInfo.getEventDefinitionRef(), e);
			throw new IncompleteDataSetException(stock, new HashSet<>(), null);
		} finally {
			this.setChanged();
			this.notifyObservers(new ObserverMsg(stock, ObsKey.NONE));
		}

	}

	//TODO Only handle EventInfoOpsCompoOperation and get rid of First and Second pass
	protected SymbolEvents calculate(Date start, Date end) throws IncompleteDataSetException {

		SymbolEvents symbolEvents = new SymbolEvents(stock);

		TunedConf tunedConf = TunedConfMgr.getInstance().loadUniqueNoRetuneConfig(stock, eventListName, eventInfo.getEventDefinitionRef());
		synchronized (tunedConf) {

			//FIXME delete needs the calculator..
			//			try {
			//				//The check on dirty is just a safe check as making dirty should also have previously deleted the events.
			//				if (!evtCalculator.isIdemPotent() || tunedConf.getDirty()) cleanEventsFor(stock, evtCalculator.getEventDefinition(), eventListName);
			//			} catch (Exception e) {
			//				LOGGER.error(e,e);
			//			}

			CalculationBounds calculationBounds;
			try {
				if (eventInfo instanceof EventInfoOpsCompoOperation && !((EventInfoOpsCompoOperation) eventInfo).isIdemPotent()) {
					calculationBounds = new CalculationBounds(CalcStatus.RESET, start, end, start, end);
				} else {
					EventsStatusChecker checker = new EventsStatusChecker(tunedConf);
					calculationBounds = checker.autoCalcAndSetDatesBounds(start, end);
				}
			} catch (InvalidAlgorithmParameterException e1) {
				//Recoverable we leave the tunedConf as is
				LOGGER.error("Failed invalid calculation dates for " + stock + " using analysis " + eventListName + ": from " + start + " to " + end, e1);
				throw new IncompleteDataSetException(stock, symbolEvents, "Some calculations have failed! Are failing : "+eventInfo);
			}

			Boolean dirty = true;
			try {
				Date adjustedStart = calculationBounds.getPmStart();
				Date adjustedEnd = calculationBounds.getPmEnd();

				//
				if (!calculationBounds.getCalcStatus().equals(CalcStatus.NONE)) {//Calculation needed

					//Calculator preparation
					Currency currency = stock.getMarketValuation().getCurrency();
					IndicatorsOperator calculator;
					if (eventInfo instanceof EventInfoOpsCompoOperation) {
						calculator = new ParameterizedIndicatorsOperator(eventInfo, stock, adjustedStart, adjustedEnd, currency, eventListName, observers);
					} else {
						calculator = legacyEventDefinitionHandling(stock);
					}

					//If RESET or not idemPotent we clean
					if (calculationBounds.getCalcStatus().equals(CalcStatus.RESET)) {
						EventsResources.getInstance().crudDeleteEventsForStock(stock, eventListName, eventInfo);
					}

					//Calculation
					Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, adjustedStart, adjustedEnd, true, currency, calculator.getStartShift(), calculator.quotationsValidity());
					SortedMap<EventKey, EventValue> calculatedEventsForCalculator = calculator.calculateEventsFor(quotations, eventListName);

					if (calculatedEventsForCalculator != null) {//There are results or empty results
						LOGGER.info("Calculated " + calculatedEventsForCalculator.size() + " events for " + symbolEvents.getSymbol() + " using analysis " + eventListName + " and " + eventInfo.getEventDefinitionRef() + " from " + adjustedStart + " to " + adjustedEnd);
						dirty = false;
						symbolEvents.addCalculationOutput(eventInfo, calculator.calculationOutput());
						symbolEvents.addEventResultElement(calculatedEventsForCalculator, eventInfo);

					} else {//No results
						LOGGER.warn("Failed (null returned) calculation for " + symbolEvents.getSymbol() + " using analysis " + eventListName + " and " + eventInfo.getEventDefinitionRef() + " from " + adjustedStart + " to " + adjustedEnd);
						dirty = false; //Not recoverable issue
						symbolEvents.addCalculationOutput(eventInfo, new TreeMap<>());
						symbolEvents.addEventResultElement(new TreeMap<>(), eventInfo);
						throw new IncompleteDataSetException(stock, symbolEvents, "Some calculations have failed! Are failing : "+eventInfo);

					}

				} else {//No calculation needed
					LOGGER.info(
							"Recalculation requested for " + stock + " using analysis " + eventListName + " and " + eventInfo.getEventDefinitionRef() + " from " + adjustedStart + " to " + adjustedEnd+". " +
									"No recalculation needed calculation bound is " + calculationBounds.toString());
					dirty = false;
					symbolEvents.addCalculationOutput(eventInfo, new TreeMap<>());
					symbolEvents.addEventResultElement(new TreeMap<>(), eventInfo);

				}

			//Error(s) as occurred. This should invalidate tuned conf and potentially generated events.
			} catch (InvalidAlgorithmParameterException | WarningException | NoQuotationsException e) {
				// Unrecoverable
				LOGGER.error(
						"Failed (empty) calculation for " + stock + " using analysis " + eventListName +  " and " +
								eventInfo.getEventDefinitionRef() + " from " + calculationBounds.getPmStart() + " to " + calculationBounds.getPmEnd(), e);
				dirty = false; //Not recoverable issue
				symbolEvents.addCalculationOutput(eventInfo, new TreeMap<>());
				symbolEvents.addEventResultElement(new TreeMap<>(), eventInfo);
				throw new IncompleteDataSetException(stock, symbolEvents, "Some calculations have failed! Are failing : " + eventInfo);

			} catch (Throwable e) {
				// Recoverable??
				LOGGER.error(
						"Failed (empty) calculation for " + stock + " using analysis " + eventListName +  " and "+
								eventInfo.getEventDefinitionRef() + " from " + calculationBounds.getPmStart() + " to " + calculationBounds.getPmEnd(), e);
				dirty = true; //Recoverable issue ??
				symbolEvents.addCalculationOutput(eventInfo, new TreeMap<>());
				symbolEvents.addEventResultElement(new TreeMap<>(), eventInfo);
				throw new IncompleteDataSetException(stock, symbolEvents, "Some calculations have failed! Are failing : " + eventInfo);

			} finally {
				TunedConfMgr.getInstance().updateConf(tunedConf, dirty, calculationBounds.getNewTunedConfStart(), calculationBounds.getNewTunedConfEnd());
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

			Constructor<IndicatorsOperator> constructor = 
					eventCompositionCalculator.getConstructor(
							EventInfo.class, Stock.class, Date.class, Date.class, Currency.class, String.class, Observer[].class);
			return constructor.newInstance(eventInfo, stock, startDate, endDate, stock.getMarketValuation().getCurrency(), eventListName, observers);

		} catch (InvocationTargetException e) {
			if (e.getCause() instanceof WarningException || e.getCause() instanceof NotEnoughDataException) {
				if (e.getCause() instanceof NotEnoughDataException || (e.getCause().getCause() != null && e.getCause().getCause() instanceof NotEnoughDataException) ) {
					LOGGER.warn("Failed calculation : Not Enough Data " + warnMessage(stock, eventInfo.toString(), startDate, endDate));
				} else {
					LOGGER.warn("Failed calculation : " + warnMessage(stock, eventInfo.toString(), startDate, endDate)+ " cause : \n" + e.getCause());
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
