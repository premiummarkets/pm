package com.finance.pms.events.scoring.chartUtils;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.conditional.EventInfoOpsCompoOperation;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.dto.PeriodRatingDTO;
import com.finance.pms.events.scoring.dto.TuningResDTO;

public class ChartImageBuilder {
    
    private static MyLogger LOGGER = MyLogger.getLogger(ChartImageBuilder.class);

    private static final String NO_CHART_AVAILABLE = "squeakyPig.png";

    private String analyseName;
    private EventInfo eventInfo;
    private Stock stock;
    private Map<EventInfo, SortedMap<Date, double[]>> calcOutputs;
    private Map<EventInfo, TuningResDTO> eventsPeriods;

    private Date startDate;
    private Date endDate;

    public ChartImageBuilder(Stock stock, String analyseName, EventInfo eventInfo,
    		Date chartedStartDate, Map<EventInfo, TuningResDTO> eventsPeriods, Map<EventInfo, SortedMap<Date, double[]>> calcOutputs) {
        super();
        this.analyseName = analyseName;
        this.eventInfo = eventInfo;
        this.stock = stock;
        
        this.startDate = chartedStartDate;
        this.endDate = DateFactory.midnithDate(new Date());
        
        this.eventsPeriods = eventsPeriods.entrySet().stream()
			    .collect(Collectors.toMap(
			        Map.Entry::getKey,
			        entry -> {
			            TuningResDTO dto = entry.getValue();
						List<PeriodRatingDTO> filteredPeriods = dto.getPeriods().stream()
			                .filter(period -> period.getFrom().after(this.startDate))
			                .collect(Collectors.toList());
			            return new TuningResDTO(
			            		filteredPeriods, 
			            		dto.getCsvLink(), dto.getChartLink(),
			            		dto.getCalculationStartPrice(), dto.getCalculationEndPrice(), dto.getCalculatedStart(), dto.getCalculatedEnd());
			        }
			    ));
//		if (chartedEventRes.isEmpty()) { eventsPeriods.get(eventInfo).getPeriods().get(0).getFrom();
//			throw new NotEnoughDataException(stock, "No data at " + new SimpleDateFormat("dd MMM yyyy").format(this.startDate), null);
//		}
        this.calcOutputs = calcOutputs.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().tailMap(this.startDate)));
    }

	public String build() throws NotEnoughDataException, IOException, NoQuotationsException {

		if (stock == null || stock.getSymbol() == null || stock.getSymbol().isEmpty()) {
			throw new NotEnoughDataException(stock, "No stock symbol defined for " + eventInfo.getEventDefinitionRef(), new Exception());
		}
		if (eventInfo == null) {
			throw new NotEnoughDataException(stock, "No event info defined for " + stock.getSymbol(), new Exception());
		}
		if (startDate == null) {
			startDate = DateFactory.midnithDate(new Date());
		}
		if (endDate == null) {
			endDate = DateFactory.midnithDate(new Date());
		}
    	
    	LOGGER.info("Building chart for: " +
    			"outputs " + calcOutputs.keySet().stream().map(e -> e.getEventDefinitionRef()).reduce((a,e) -> a + ", " + e) + ", " +
    			"periods " + eventsPeriods.keySet().stream().map(e -> e.getEventDefinitionRef()).reduce((a,e) -> a + ", " + e)  + 
    			" with main " + eventInfo.getEventDefinitionRef());

        Boolean generateSmaCmpOutChart = Boolean.valueOf(MainPMScmd.getMyPrefs().get("autoporfolio.generatepng", "true"));
        if (!generateSmaCmpOutChart) return NO_CHART_AVAILABLE;

		Set<QuotationDataType> requieredStockDataTypes = new HashSet<>(Arrays.asList(QuotationDataType.CLOSE));
		if (eventInfo instanceof EventInfoOpsCompoOperation) {
			requieredStockDataTypes = ((EventInfoOpsCompoOperation) eventInfo).getRequiredStockData();
		}
        Currency currency = stock.getMarketValuation().getCurrency();
		ValidityFilter filterFor = ValidityFilter.getFilterFor(requieredStockDataTypes);
		Quotations quotations = QuotationsFactories.getFactory().getSplitFreeQuotationsInstance(stock, startDate, endDate, true, currency, 100, filterFor);
        SortedMap<Date, Double> quotationMap = QuotationsFactories.getFactory().buildExactSMapFromQuotationsClose(quotations, quotations.getFirstDateShiftedIdx(), quotations.getLastDateIdx());

        String chartFile;
        try {
            String chartFileName = "autoPortfolioLogs" + File.separator + 
            						eventInfo.getEventDefDescriptor().getExportBaseFileName() +
            					   "_" + analyseName + stock.getSymbol() + "_" + eventInfo.getEventReadableDef() + "_" + UUID.randomUUID() +
            					   ".png";
            generateOutChart(chartFileName, quotationMap);
            chartFile = chartFileName;
        } catch (NotEnoughDataException | IOException | NoQuotationsException e) {
            LOGGER.warn("Can't generate chart for " + stock.getSymbol(), e, true);
            chartFile = NO_CHART_AVAILABLE;
            throw e;
        }

        return chartFile;
    }

    private void generateOutChart(String chartFileName, SortedMap<Date, Double> quotationMap) throws IOException, NoQuotationsException, NotEnoughDataException {
        
        Color violet = new Color(204, 153, 255);
        Color blue = new Color(153, 204, 255);
        Color red = new Color(255,153,153);
        Color green = new Color(204,255,153);
        Color grey = new Color(128, 128, 128, 192);

        ChartGenerator chartGenerator = new ChartGenerator("Premium Markets predictions V. targets");

        //Bar series
        ///Prediction bars        
        SymbolEvents preds = EventsResources.getInstance().crudReadEventsForStock(stock, startDate, endDate, Collections.singleton(eventInfo), analyseName);
		BarSettings predBarSettings = new BarSettings();
		predBarSettings.setIsZeroBased(true);
		predBarSettings.setIsReachTop(true);
		int predSeriesIdx = 1;
		SortedMap<DataSetBarDescr, SortedMap<Date, BarChart>> barPredSeries = ChartBarUtils
				.buildBarsData(
						stock, Collections.singleton(eventInfo), startDate, endDate, 
						preds, eventsPeriods, 
						predBarSettings, new DataSetBarDescrBuilder() {

							@Override
							public DataSetBarDescr buildBuyDSBarDescr(Integer serieIdx, int alpha, EventInfo eventInfo, Stock selectedShare, TuningResDTO tuningResDTO) {
								return new DataSetBarDescr(predSeriesIdx, EventType.BULLISH, "Prediction Bullish", blue);
							}

							@Override
							public DataSetBarDescr buildSellDSBarDescr(Integer serieIdx, int alpha, EventInfo eventInfo, Stock selectedShare, TuningResDTO tuningResDTO) {
								return new DataSetBarDescr(predSeriesIdx, EventType.BEARISH, "Prediction Bearish", violet);
							}

							@Override
							public DataSetBarDescr buildIndeterDSBarDescr(Integer serieIdx, int alpha, EventInfo eventInfo, Stock selectedShare, TuningResDTO tuningResDTO) {
								return null;
							}
							
						}, quotationMap.values().stream().max(Double::compareTo).orElse(100d)
				);

        final SortedMap<DataSetBarDescr, SortedMap<Date, BarChart>> barRefSeries = new TreeMap<DataSetBarDescr, SortedMap<Date, BarChart>>();
		eventsPeriods.keySet().stream().filter(e -> !e.equals(eventInfo)).findFirst().ifPresentOrElse(e -> {
			
	        ///Ideal reference bars
	        SymbolEvents refs = EventsResources.getInstance().crudReadEventsForStock(stock, startDate, endDate, Collections.singleton(e), analyseName);
			BarSettings refBarSettings = new BarSettings();
			refBarSettings.setIsToQuotations(true);
			int refSeriesIdx = 2;
	  		barRefSeries.putAll(ChartBarUtils
  				.buildBarsData(
  						stock, Collections.singleton(e), startDate, endDate, 
  						refs, eventsPeriods, 
  						refBarSettings, new DataSetBarDescrBuilder() {

  							@Override
  							public DataSetBarDescr buildBuyDSBarDescr(Integer serieIdx, int alpha, EventInfo eventInfo, Stock selectedShare, TuningResDTO tuningResDTO) {
								return new DataSetBarDescr(refSeriesIdx, EventType.BULLISH, "Target Bullish", green);
  							}

  							@Override
  							public DataSetBarDescr buildSellDSBarDescr(Integer serieIdx, int alpha, EventInfo eventInfo, Stock selectedShare, TuningResDTO tuningResDTO) {
  								return new DataSetBarDescr(refSeriesIdx, EventType.BEARISH, "Target Bearish", red);
  							}

  							@Override
  							public DataSetBarDescr buildIndeterDSBarDescr(Integer serieIdx, int alpha, EventInfo eventInfo, Stock selectedShare, TuningResDTO tuningResDTO) {
  								return null;
  							}
  							
  						}, 1d
  				));
  		
		        ///Future grey period
		        try {
					Date futurePeriodStart = maxLastKey(barRefSeries);
					futurePeriodStart = DateUtils.addDays(futurePeriodStart, 1); //Add one day to the future period start
					SortedMap<Date, BarChart> future = new TreeMap<Date,BarChart>();
					SortedMap<Date, Double> futureSubMap = quotationMap.tailMap(futurePeriodStart);
					for (Date date : futureSubMap.keySet()) {
					    future.put(date, new BarChart(quotationMap.get(date), ""));
					}
					int futureSeriesIdx = 3;
					barRefSeries.put(new DataSetBarDescr(futureSeriesIdx, EventType.NONE, "Future Predicted", grey), future);
				} catch (NotEnoughDataException e1) {
					LOGGER.error("Error determining future period for chart", e1);
				}
		        
		}, () -> LOGGER.warn(
				"No reference Event info found in " + eventsPeriods.keySet().stream().map(e -> e.getEventDefinitionRef()).reduce((a,e) -> a + ", " + e) + ", " +
				"as alternative to " + eventInfo.getEventDefinitionRef())
		);

        //Chart
        boolean includeWeekends = quotationMap.keySet().stream().anyMatch(d -> Instant.ofEpochMilli(d.getTime()).atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek().getValue() >= 6);
        FileOutputStream outputFileStream = new FileOutputStream(new File(System.getProperty("installdir") + File.separator + chartFileName));
        chartGenerator.generateChartPNGFor(outputFileStream, eventInfo, calcOutputs, barPredSeries, barRefSeries, includeWeekends);
    }

    private Date maxLastKey(SortedMap<DataSetBarDescr, SortedMap<Date, BarChart>> series) throws NotEnoughDataException {
        Date maxDate = null;
        for (SortedMap<Date, BarChart> innerMap : series.values()) {
            if (!innerMap.isEmpty()) {
                Date lastKey = innerMap.lastKey();
                if (maxDate == null || lastKey.after(maxDate)) {
                    maxDate = lastKey;
                }
            }
        }

        if (maxDate == null) {
            throw new NotEnoughDataException(null, "No data available to determine the maximum date.", new Exception());
        }

        return maxDate;
    }

}
