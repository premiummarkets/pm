package com.finance.pms.events.calculation;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.SortedMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.scoring.TunedConfMgr;
import com.finance.pms.threads.ObserverMsg;

public class SelectedIndicatorsCalculationService {

    private static MyLogger LOGGER = MyLogger.getLogger(SelectedIndicatorsCalculationService.class);

    public class QuotesBounds {

        private Date adjustedStartDate;
        private Date adjustedEndDate;

        public QuotesBounds(Date adjustedStartDate, Date adjustedEndDate) {
            this.adjustedStartDate = adjustedStartDate;
            this.adjustedEndDate = adjustedEndDate;
        }

        public Date getAdjustedStartDate() {
            return adjustedStartDate;
        }

        public Date getAdjustedEndDate() {
            return adjustedEndDate;
        }

    }

    public SelectedIndicatorsCalculationService() {
    }

    public Map<Stock,Map<EventInfo, SortedMap<Date, double[]>>> calculate(
            Date startDate, Date endDate, String eventListName, Map<Stock, List<EventInfo>> stocksEventInfos, Observer... observers) throws IncompleteDataSetException {

        //TODO use a Fork Join Pool or parallel stream for executor
        Map<Stock,Map<EventInfo, SortedMap<Date, double[]>>> calculatedOutputReturn =  new HashMap<Stock, Map<EventInfo, SortedMap<Date, double[]>>>(1);
        ExecutorService executor = Executors.newFixedThreadPool(new Integer(MainPMScmd.getMyPrefs().get("indicatorcalculator.semaphore.nbthread","20")));
        try {

            Map<Stock, List<Future<?>>> futuresMap = new HashMap<>();
            Map<Stock, SymbolEvents> symbolEventsMap = new HashMap<>();

            int obsSize = stocksEventInfos.values().stream().map(v -> v.size()).reduce(0, (a, e) -> a + e);
            Arrays.stream(observers).forEach(o -> o.update(null, new ObserverMsg(null, ObserverMsg.ObsKey.INITMSG, obsSize)));

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yy HH:mm:ss");
            Boolean isDataSetComplete = true;
            for(Stock stock: stocksEventInfos.keySet()) {

                LOGGER.guiInfo("Calculation requested : events for stock "+stock.toString()+ " between "+dateFormat.format(startDate)+" and "+dateFormat.format(endDate));
                QuotesBounds adjCalcDatesToQs = adjustCalculationDatesToQuotations(stock, startDate, endDate);
                LOGGER.info("Calculation adjusted : events for stock "+stock.toString()+ " between "+dateFormat.format(adjCalcDatesToQs.getAdjustedStartDate())+" and "+dateFormat.format(adjCalcDatesToQs.getAdjustedEndDate()));

                List<Future<?>> eventInfosFutures = new ArrayList<>();
                SymbolEvents symbolEvents = new SymbolEvents(stock);
                symbolEventsMap.put(stock, symbolEvents);
                for(EventInfo eventInfo: stocksEventInfos.get(stock)) {
                    Runnable calculationRunnable = 
                            new SelectedIndicatorsCalculationThread(symbolEvents, adjCalcDatesToQs.getAdjustedStartDate(), adjCalcDatesToQs.getAdjustedEndDate(), eventInfo, eventListName, observers);
                    Future<?> submitedRunnable = executor.submit(calculationRunnable);
                    eventInfosFutures.add(submitedRunnable);
                }

                futuresMap.put(stock, eventInfosFutures);

            }

            List<SymbolEvents> allEvents = new ArrayList<SymbolEvents>();
            List<Stock> failingStocks = new ArrayList<Stock>();
            for (Stock stock : futuresMap.keySet()) {

                for(Future<?> stockEventInfoFuture : futuresMap.get(stock)) {
                    try {
                        stockEventInfoFuture.get();
                    } catch (Exception e) {
                        LOGGER.error("Failed : events for stock "+stock.toString()+" between "+dateFormat.format(startDate)+" and "+dateFormat.format(endDate), e);
                        isDataSetComplete = false;
                        failingStocks.add(stock);
                    }
                }

                //Events
                allEvents.add(symbolEventsMap.get(stock));

                //Output (not used for first pass events ..)
                Map<EventInfo, SortedMap<Date, double[]>> calculationOutput = symbolEventsMap.get(stock).getCalculationOutputs();
                if (calculationOutput == null) calculationOutput = new HashMap<EventInfo, SortedMap<Date,double[]>>();
                calculatedOutputReturn.put(stock, calculationOutput);

            }

            try {
                LOGGER.guiInfo("Storing "+allEvents.size()+".");
                EventsResources.getInstance().crudCreateEvents(allEvents, eventListName);
                LOGGER.guiInfo(allEvents.size()+" stored.");
            } catch (Exception e) { 
                isDataSetComplete = false;
                if (e.getCause() != null && e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                    LOGGER.warn("Intercepted : " + e + " -> IncompleteDataset");
                } else {
                    LOGGER.error(e,e);
                }
            }

            if (!isDataSetComplete) {
                throw new IncompleteDataSetException(null, null, null, "All Indicators couldn't be calculated properly. This may invalidates the dataset for further usage. Stock concerned : "+failingStocks);
            }

        } catch (Throwable e) {
            LOGGER.error(e);
        } finally {
            executor.shutdownNow();
        }

        return calculatedOutputReturn;
    }

    private QuotesBounds adjustCalculationDatesToQuotations(Stock stock, Date startDate, Date endDate) {

        //Adjust calculation date to available quotes
        Date minimumStartDate = TunedConfMgr.getInstance().minimumStartDate(stock);
        if (minimumStartDate.before(startDate) || minimumStartDate.equals(startDate)) {
            minimumStartDate = startDate;
        } else {
            LOGGER.info("Start date calculation adjusted : for stock "+stock.toString()+ " is now starting on "+minimumStartDate);
        }
        if (minimumStartDate.after(endDate) || minimumStartDate.equals(endDate)) {
            throw new RuntimeException("Not enough quotations to calculate (invalid date bounds) : for stock "+stock.toString()+ " between "+minimumStartDate+" and "+endDate);
        }
        Date maximumEndDate = TunedConfMgr.getInstance().maximumEndDate(stock);
        if (maximumEndDate.after(endDate) || maximumEndDate.equals(endDate)) {
            maximumEndDate = endDate;
        } else {
            LOGGER.info("End Date calculation adjusted : events for stock "+stock.toString()+ " is now ending on  "+maximumEndDate);
        }
        if (maximumEndDate.before(minimumStartDate) || maximumEndDate.equals(minimumStartDate)) {
            throw new RuntimeException("Not enough quotations to calculate (invalid date bounds) : for stock "+stock.toString()+ " between "+minimumStartDate+" and "+maximumEndDate);
        }

        return new QuotesBounds(minimumStartDate, maximumEndDate);

    }

}
