package com.finance.pms.events.calculation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.calculation.parametrizedindicators.ParameterizedIndicatorsOperator;
import com.finance.pms.events.operations.StackException;
import com.finance.pms.events.operations.conditional.EventInfoOpsCompoOperation;
import com.finance.pms.events.quotations.Quotations;
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

		try {

			//Calculator preparation
			Currency currency = stock.getMarketValuation().getCurrency();
			Quotations quotations = null;
			IndicatorsOperator calculator;
			if (eventInfoCln instanceof EventInfoOpsCompoOperation) {
				calculator = new ParameterizedIndicatorsOperator(eventInfoCln, stock, start, end, currency, eventListName, observers);
			} else {
//				calculator = legacyEventDefinitionHandling(stock);
//				try {
//					quotations = QuotationsFactories.getFactory().getSplitFreeQuotationsInstance(stock, adjustedStart, adjustedEnd, true, currency, calculator.getStartShift(), calculator.quotationsValidity());
//				} catch (NoQuotationsException e) {
//					throw new RuntimeException(e);
//				}
				throw new RuntimeException("Not supported anymore");
			}

			//Calculation
			SortedMap<EventKey, EventValue> calculatorEvents = calculator.calculateEventsFor(quotations, eventListName);

			if (calculatorEvents != null) {//There are results or empty results

				LOGGER.info("Received (calculated) " + calculatorEvents.size() + " events "
						+ "from " + calculatorEvents.firstKey() + " to " + calculatorEvents.lastKey() + " for " + returnedSymbolEvents.getSymbol() + 
						" using analysis " + eventListName + " and event def " + eventInfoCln.getEventDefinitionRef());

				returnedSymbolEvents.addCalculationOutput(eventInfoCln, calculator.calculationOutput());
				returnedSymbolEvents.addEventResultElement(calculatorEvents, eventInfoCln);

			} else {//No results

				LOGGER.warn("Failed (null returned) calculation for " + returnedSymbolEvents.getSymbol() + " using analysis " + eventListName + " and " + eventInfoCln.getEventDefinitionRef());
				emptyReturn(returnedSymbolEvents);
				throw new IncompleteDataSetException(stock, returnedSymbolEvents, "Some calculations have failed! Are failing: " + eventInfoCln + "\nCause: nothing returned.");

			}

			//Error(s) as occurred. This should invalidate tuned conf and its potentially generated events.

		} catch (RuntimeException e) { //This code exceptions
			// Unrecoverable
			String message = "Failed (Empty Unrecoverable) calculation for " + stock + " using analysis " + eventListName +  " and " + eventInfoCln.getEventDefinitionRef();
			LOGGER.error(message, e);

			emptyReturn(returnedSymbolEvents);
			throw new IncompleteDataSetException(stock, returnedSymbolEvents, "Some calculations have failed! Are failing: " + eventInfoCln + "\nCause: " + e.getMessage());

		} catch (Throwable e) {//Deeper call stack exceptions

			String message = "Failed (Empty Recoverable??) calculation for " + stock + " using analysis " + eventListName +  " and " + eventInfoCln.getEventDefinitionRef();

			//ErrorException e && e.getCause() instanceof StackException && isNoOverrideDeltaOnly
			Throwable rootCause = e;
			while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
				rootCause = rootCause.getCause();
			}
			if (rootCause instanceof StackException) {
				LOGGER.warn(message + ": " + rootCause + ": " + rootCause.getCause());
			} else {
				LOGGER.error(message + ": " + rootCause + ": " + rootCause.getCause(), e);
			}

			emptyReturn(returnedSymbolEvents);
			throw new IncompleteDataSetException(stock, returnedSymbolEvents, "Some calculations have failed! Are failing: " + eventInfoCln + "\nCause: " + message + ":\n" + rootCause);

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
