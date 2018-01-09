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
import com.finance.pms.events.calculation.parametrizedindicators.ParameterizedIndicatorsCompositioner;
import com.finance.pms.events.operations.conditional.OperationsCompositioner;
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

public class SelectedIndicatorsCalculationThread extends Observable implements Runnable {

    private static MyLogger LOGGER = MyLogger.getLogger(SelectedIndicatorsCalculationThread.class);

    protected Map<String,Config> configs;
    private Observer[] observers;

    private final SymbolEvents symbolEvents;
    private final Date startDate;
    private final Date endDate;

    private final String eventListName;
    private final EventInfo eventInfo;

    //FIXME ?? some calculator may not support in parallel calculation and will need synchronisation
    protected SelectedIndicatorsCalculationThread(
            SymbolEvents symbolEvents, Date startDate, Date endDate,
            EventInfo eventInfo, String eventListName,
            Observer... observers) {

        this.configs = ConfigThreadLocal.getAll();
        this.observers = observers;
        for (Observer observer : observers) {
            this.addObserver(observer);
        }

        this.symbolEvents = symbolEvents;
        this.startDate = startDate;
        this.endDate = endDate;

        this.eventInfo = eventInfo;
        this.eventListName = eventListName;

    }

    public void run() {

        Stock stock = symbolEvents.getStock();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yy HH:mm:ss");
        try {
            ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME,this.configs.get(Config.EVENT_SIGNAL_NAME));
            ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME,this.configs.get(Config.INDICATOR_PARAMS_NAME));

            LOGGER.guiInfo("Calculating "+eventInfo.getEventReadableDef()+" for stock "+stock.toString()+ " between "+dateFormat.format(startDate)+" and "+dateFormat.format(endDate));

            //SymbolEvents eventsMap = 
            calculate(symbolEvents, startDate, endDate);

            LOGGER.guiInfo("Finishing "+eventInfo.getEventReadableDef()+" for stock "+stock.toString()+ " between "+dateFormat.format(startDate)+" and "+dateFormat.format(endDate));

        } catch (Exception e) {
            LOGGER.error("UnHandled error : While calculating Events for "+stock+", analysis "+eventListName+" and "+eventInfo.getEventDefinitionRef()); //+" and dates "+startDate+" to "+endDate, e);
            //throw new Exception("UnHandled error : While calculating Events for "+symbolEvents.getStock()+", analysis "+eventListName+" and "+eventInfo.getEventDefinitionRef(), e);
        } finally {
            this.setChanged();
            this.notifyObservers(new ObserverMsg(stock, ObsKey.NONE));
        }

    }

    //TODO move Tuned conf mgt into EventREsources.
    protected void calculate(SymbolEvents symbolEvents, Date start, Date end) throws Exception {

        Stock stock = symbolEvents.getStock();

        TunedConf tunedConf = TunedConfMgr.getInstance().loadUniqueNoRetuneConfig(stock, eventListName, eventInfo.getEventDefinitionRef());
        synchronized (tunedConf) {

            EventsStatusChecker checker = new EventsStatusChecker(tunedConf);
            CalculationBounds calculationBounds = checker.autoCalcAndSetDatesBounds(start, end);

            Date adjustedStart = calculationBounds.getPmStart();
            Date adjustedEnd = calculationBounds.getPmEnd();

            try {

                //
                if (!calculationBounds.getCalcStatus().equals(CalcStatus.NONE)) {//Calculation needed

                    //Calculator preparation
                    Currency currency = stock.getMarketValuation().getCurrency();
                    IndicatorsCompositioner calculator;
                    if (eventInfo instanceof OperationsCompositioner) {
                        calculator = new ParameterizedIndicatorsCompositioner(eventInfo, stock, adjustedStart, adjustedEnd, currency, eventListName, observers);
                    } else {
                        calculator = legacyEventDefinitionHandling(stock);
                    }

                    //If RESET we clean
                    if (calculationBounds.getCalcStatus().equals(CalcStatus.RESET)) {
                        EventsResources.getInstance().crudDeleteEventsForStock(symbolEvents.getStock(), eventListName, eventInfo);
                    }

                    //Calculation
                    SortedMap<EventKey, EventValue> calculatedEventsForCalculator;

                    Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, adjustedStart, adjustedEnd, true, currency, calculator.getStartShift(), calculator.quotationsValidity());
                    calculatedEventsForCalculator = calculator.calculateEventsFor(quotations, eventListName);

                    if (calculatedEventsForCalculator != null) {//There are results or empty

                        //TunedConf tunedConf = TunedConfMgr.getInstance().loadUniqueNoRetuneConfig(stock, eventListName, eventInfo.getEventDefinitionRef());
                        Date lastEventDate = (calculatedEventsForCalculator.isEmpty())?null:calculatedEventsForCalculator.lastKey().getDate();
                        TunedConfMgr.getInstance().updateConf(tunedConf, lastEventDate, calculationBounds.getNewTunedConfStart(), calculationBounds.getNewTunedConfEnd());
                       
                        symbolEvents.addCalculationOutput(eventInfo, calculator.calculationOutput());
                        symbolEvents.addEventResultElement(calculatedEventsForCalculator, eventInfo);

                    } else {//No results

                        LOGGER.warn("Failed (null returned) calculation for "+symbolEvents.getSymbol()+" using analysis "+eventListName+" and "+eventInfo.getEventDefinitionRef()+" from "+adjustedStart+" to "+adjustedEnd);

                        //Not needed as not modified.
                        //TunedConf tunedConf = checker.getTunedConf();
                        //TunedConfMgr.getInstance().rollBackConf(tunedConf, oldLastCalculatedEvent, oldLastCalculationStart, oldLastCalculationEnd);
                        cleanEventsFor(eventInfo, eventListName, adjustedStart, adjustedEnd);

                        symbolEvents.addCalculationOutput(eventInfo, new TreeMap<>());
                        symbolEvents.addEventResultElement(new TreeMap<>(), eventInfo);
                    }

                } else {//No calculation needed

                    LOGGER.info(
                            "Recalculation requested for "+stock+" using analysis "+eventListName+ "and "+eventInfo.getEventDefinitionRef()+" from "+adjustedStart+" to "+adjustedEnd+". "+
                                    "No recalculation needed calculation bound is "+ calculationBounds.toString());
                    symbolEvents.addCalculationOutput(eventInfo, new TreeMap<>());
                    symbolEvents.addEventResultElement(new TreeMap<>(), eventInfo);
                }

            } catch (Throwable t) { //Error(s) as occurred. This should invalidate tuned conf and potentially generated events.
                LOGGER.error("Failed (empty) calculation for "+stock+" using analysis "+eventListName+ "and "+eventInfo.getEventDefinitionRef()+" from "+adjustedStart+" to "+adjustedEnd, t);
                //Not needed as not modified??
                //TunedConf tunedConf = checker.getTunedConf();
                //TunedConfMgr.getInstance().rollBackConf(tunedConf, oldLastCalculatedEvent, oldLastCalculationStart, oldLastCalculationEnd);
                cleanEventsFor(eventInfo, eventListName, adjustedStart, adjustedEnd);
            }

        }

    }

    public void cleanEventsFor(EventInfo eventInfo, String eventListName, Date start, Date end) {
        LOGGER.info("Cleaning " + eventInfo + " events for " + eventListName + " and "+ symbolEvents.getStock().getFriendlyName() + " from " + start + " to " + end);
        EventsResources.getInstance().crudDeleteEventsForStockNoTunedConfHandling(symbolEvents.getStock(), eventListName, start, end, eventInfo);
    }

    private IndicatorsCompositioner legacyEventDefinitionHandling(Stock stock) {
        try {
            SpringContext springContext = SpringContext.getSingleton();
            @SuppressWarnings("unchecked")
            Map<String, String> nativeCalcsMap = (Map<String, String>) springContext.getBean("availableSecondPassIndicatorCalculators");
            IndicatorsCompositioner calculator = instanciateECC(stock, eventInfo, nativeCalcsMap.get(eventInfo.getEventDefinitionRef()), observers);
            return calculator;
        } catch (Throwable e) {
            LOGGER.warn("Event definition not supported : "+eventInfo, e);
            throw new RuntimeException(e);
        }
    }

    private IndicatorsCompositioner instanciateECC(Stock stock, EventInfo eventInfo, String eventCompositionCalculatorStr, Observer... observers) throws Throwable {

        @SuppressWarnings("unchecked")
        Class<IndicatorsCompositioner> eventCompositionCalculator = (Class<IndicatorsCompositioner>) Class.forName(eventCompositionCalculatorStr);
        try {

            Constructor<IndicatorsCompositioner> constructor = 
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
