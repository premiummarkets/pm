package com.finance.pms.events.scoring.chartUtils;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventType;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.dto.PeriodRatingDTO;
import com.finance.pms.events.scoring.dto.TuningResDTO;

public class ChartImageBuilder {
    
    private static MyLogger LOGGER = MyLogger.getLogger(ChartImageBuilder.class);

    private static final double PREDICTIONS_SERIES_FACTOR = 1;
    private static final double REF_SERIES_FACTOR = 100;

    private static final String NO_CHART_AVAILABLE = "noChartAvailable";

    private String analyseName;
    private EventInfo eventInfo;
    private Stock stock;
    private Map<EventInfo, SortedMap<Date, double[]>> calcOutputs;
    private Map<EventInfo, TuningResDTO> eventsPeriods;

    private Date startDate;
    private Date endDate;

    public ChartImageBuilder(Stock stock, String analyseName, EventInfo eventInfo, Map<EventInfo, TuningResDTO> eventsPeriods, Map<EventInfo, SortedMap<Date, double[]>> calcOutputs) {
        super();
        this.analyseName = analyseName;
        this.eventInfo = eventInfo;
        this.stock = stock;
        this.eventsPeriods = eventsPeriods;

        this.startDate = eventsPeriods.get(eventInfo).getPeriods().get(0).getFrom();
        this.endDate = DateFactory.midnithDate(new Date());
        this.calcOutputs = calcOutputs.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().tailMap(startDate)));
    }

    public String build() throws NoQuotationsException, NotEnoughDataException {

        Boolean generateSmaCmpOutChart = MainPMScmd.getMyPrefs().getBoolean("autoporfolio.generatepng", true);
        if (!generateSmaCmpOutChart) return NO_CHART_AVAILABLE;

        Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, startDate, endDate, true, stock.getMarketValuation().getCurrency(), 100, ValidityFilter.CLOSE);
        SortedMap<Date, Double> quotationMap = QuotationsFactories.getFactory().buildExactSMapFromQuotationsClose(quotations, quotations.getFirstDateShiftedIdx(), quotations.getLastDateIdx());

        String chartFile;
        try {
            String chartFileName = "autoPortfolioLogs" + File.separator + eventInfo.getEventDefDescriptor().getExportBaseFileName()+ "_" + analyseName + stock.getSymbol() + "_" + eventInfo.getEventReadableDef() + ".png";
            generateOutChart(chartFileName, quotationMap);
            chartFile = chartFileName;
        } catch (NotEnoughDataException e) {
            LOGGER.warn("Can't generate chart for " + stock.getSymbol(), e, true);
            chartFile = NO_CHART_AVAILABLE;
        } catch (Exception e) {
            LOGGER.error("Can't generate chart for " + stock.getSymbol(), e);
            chartFile = NO_CHART_AVAILABLE;
        }

        return chartFile;
    }

    private void generateOutChart(String chartFileName, SortedMap<Date, Double> quotationMap) 
    		throws IOException, NoQuotationsException, NotEnoughDataException {
        
        Color violet = new Color(204, 153, 255);
        Color blue = new Color(153, 204, 255);
        Color red = new Color(255,153,153);
        Color green = new Color(204,255,153);
        Color grey = Color.lightGray;

        ChartGenerator chartGenerator = new ChartGenerator("Premium Markets predictions V. target");

        //Bar series
        ///Prediction bars       
        SortedMap<Date, Double> buySerie = new TreeMap<Date, Double>();
        SortedMap<Date, Double> sellSerie = new TreeMap<Date, Double>();
        for (PeriodRatingDTO currentPeriod : eventsPeriods.get(eventInfo).getPeriods()) {
            Date to = (calcOutputs.get(eventInfo).lastKey().compareTo(currentPeriod.getTo()) <= 0)?calcOutputs.get(eventInfo).lastKey():currentPeriod.getTo();
			SortedMap<Date, Double> periodQuotationMap = MapUtils.subMapInclusive(quotationMap, currentPeriod.getFrom(), to);
            EventType periodTrend = EventType.valueOf(currentPeriod.getTrend());
            for (Date periodInnerDate : periodQuotationMap.keySet()) {
                Double closeForInnerDate = periodQuotationMap.get(periodInnerDate).doubleValue();
                if (periodTrend.equals(EventType.BULLISH)) {
                    buySerie.put(periodInnerDate, closeForInnerDate * PREDICTIONS_SERIES_FACTOR);
                } else if (periodTrend.equals(EventType.BEARISH)) {
                    sellSerie.put(periodInnerDate, closeForInnerDate * PREDICTIONS_SERIES_FACTOR);
                }
            }
        }
        SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barPredSeries = new TreeMap<DataSetBarDescr, SortedMap<Date,Double>>();
        barPredSeries.put(new DataSetBarDescr(1, "Prediction Bearish", violet), sellSerie);
        barPredSeries.put(new DataSetBarDescr(2, "Prediction Bullish", blue), buySerie);

        ///Ideal reference bars
        SortedMap<Date, Double> refBullish = new TreeMap<Date, Double>();
        SortedMap<Date, Double> refBearish = new TreeMap<Date, Double>();
        eventsPeriods.keySet().stream().filter(e -> !e.equals(eventInfo)).findFirst().ifPresent(e -> {
	       for (PeriodRatingDTO currentPeriod : eventsPeriods.get(e).getPeriods()) {
	    	   Date to = (calcOutputs.get(e).lastKey().compareTo(currentPeriod.getTo()) <= 0)?calcOutputs.get(e).lastKey():currentPeriod.getTo();
	            SortedMap<Date, Double> periodQuotationMap = MapUtils.subMapInclusive(quotationMap, currentPeriod.getFrom(), to);
	            EventType periodTrend = EventType.valueOf(currentPeriod.getTrend());
	            for (Date periodInnerDate : periodQuotationMap.keySet()) {
	                Double closeForInnerDate = periodQuotationMap.get(periodInnerDate).doubleValue();
	                if (periodTrend.equals(EventType.BULLISH)) {
	                	refBullish.put(periodInnerDate, closeForInnerDate * REF_SERIES_FACTOR);
	                } else if (periodTrend.equals(EventType.BEARISH)) {
	                	refBearish.put(periodInnerDate, closeForInnerDate * REF_SERIES_FACTOR);
	                }
	            }
	        }
        });
        SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barRefSeries = new TreeMap<DataSetBarDescr, SortedMap<Date,Double>>();
        barRefSeries.put(new DataSetBarDescr(1, "Target Bearish", red), refBearish);
        barRefSeries.put(new DataSetBarDescr(2, "Target Bullish", green), refBullish);

        ///Prediction grey period
        Date predictedPeriodStart = maxLastKey(refBearish, refBullish);
        Date predictedPeriodEnd = maxLastKey(sellSerie, buySerie);
        SortedMap<Date, Double> pred = new TreeMap<Date,Double>();
        SortedMap<Date, Double> predSubMap = MapUtils.subMapInclusive(quotationMap,predictedPeriodStart, predictedPeriodEnd);
        for (Date date : predSubMap.keySet()) {
            pred.put(date, quotationMap.get(date) * REF_SERIES_FACTOR);
        }
        barRefSeries.put(new DataSetBarDescr(3, "Future Predicted", grey), pred);

        //Chart
        FileOutputStream outputFileStream = new FileOutputStream(new File(System.getProperty("installdir") + File.separator + chartFileName));
        chartGenerator.generateChartPNGFor(outputFileStream, eventInfo, calcOutputs, barPredSeries, barRefSeries);
    }

    private Date maxLastKey(SortedMap<Date, Double> firstSerie, SortedMap<Date, Double> secondSerie) throws NotEnoughDataException {

        Date maxDate = null;
        if (secondSerie.isEmpty() && firstSerie.isEmpty()) {
            throw new NotEnoughDataException(null, "No prediction data : No prediction period can be inferred.", new Exception());
        } else if (secondSerie.isEmpty()) {
            maxDate = firstSerie.lastKey();
        } else if (firstSerie.isEmpty()) {
            maxDate = secondSerie.lastKey();
        } else {
            maxDate = (secondSerie.lastKey().after(firstSerie.lastKey()))? secondSerie.lastKey() : firstSerie.lastKey();
        }
        return maxDate;

    }

}
