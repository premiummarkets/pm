/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.scoring;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.Observer;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.springframework.stereotype.Service;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.Validity;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.chartUtils.ChartGenerator;
import com.finance.pms.events.scoring.chartUtils.DataSetBarDescr;
import com.finance.pms.events.scoring.dto.PeriodRatingDTO;
import com.finance.pms.events.scoring.dto.TuningResDTO;
import com.finance.pms.events.scoring.functions.ApacheStats;
import com.finance.pms.events.scoring.functions.SDiscretDerivator;
import com.finance.pms.events.scoring.functions.SlopeDerivator;
import com.finance.pms.events.scoring.functions.TalibSmaSmoother;
import com.finance.pms.threads.ObserverMsg;

@Service("tuningFinalizer")
public class OTFTuningFinalizer {

	private static MyLogger LOGGER = MyLogger.getLogger(OTFTuningFinalizer.class);

	public TuningResDTO buildTuningRes(Date startDate, Date endDate, Stock stock, String analyseName, SortedMap<Date, double[]> calcOutput, EventInfo evtDef, Observer observer, Boolean isEventsPersisted) throws NotEnoughDataException {

		if (calcOutput == null) calcOutput = new TreeMap<Date,double[]>();
		if (!calcOutput.isEmpty() && calcOutput.firstKey().before(startDate)) calcOutput = calcOutput.tailMap(startDate);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
		String noResMsg = "No estimate is available for "+stock.getName()+" between "+dateFormat.format(startDate)+ " and "+ dateFormat.format(endDate)+" with "+evtDef+".\n";
		try {
			
			Date endCalcRes = (calcOutput.size() > 0 && calcOutput.lastKey().after(endDate))? calcOutput.lastKey() : endDate;
			
			//Grab calculated events
			HashSet<EventInfo> eventDefinitions = new HashSet<EventInfo>();
			eventDefinitions.add(evtDef);
			SymbolEvents eventsCalculated = EventsResources.getInstance().crudReadEventsForStock(stock, startDate, endCalcRes, isEventsPersisted, eventDefinitions, analyseName);
	
			//Init event def list
			NavigableSet<EventValue> eventListForEvtDef = new TreeSet<EventValue>(new Comparator<EventValue>() {
				@Override
				public int compare(EventValue o1, EventValue o2) {
					return o1.getDate().compareTo(o2.getDate());
				}
			});
			eventListForEvtDef.addAll(eventsCalculated.getDataResultMap().values());
				
			return buildTuningRes(stock, startDate, endDate, endCalcRes, analyseName, calcOutput, eventListForEvtDef, noResMsg, evtDef.info(), observer);
			

		} catch (NoQuotationsException e) {
			LOGGER.warn(noResMsg, e);
			throw new NotEnoughDataException(stock, noResMsg, e);
		} catch (NotEnoughDataException e) {
			LOGGER.warn(noResMsg, e);
			throw e;
		} catch (Exception e) {
			LOGGER.error(noResMsg, e);
			throw new NotEnoughDataException(stock, noResMsg, e);
		}
		
	}
	
	private List<PeriodRatingDTO> validPeriods(
			SortedMap<Date, Number> mapFromQuotationsClose,
			Stock stock, Date startDate, Date endDate, Date endCalcRes, String analyseName, SortedMap<Date, double[]> calcOutput, Collection<EventValue> eventListForEvtDef, String noResMsg, String evtDefInfo) 
			throws NotEnoughDataException {
			
		List<PeriodRatingDTO> periods = new ArrayList<PeriodRatingDTO>();
	
		EventType prevEventType = null;
		PeriodRatingDTO period = null;

		BigDecimal lastClose = (BigDecimal) mapFromQuotationsClose.get(mapFromQuotationsClose.lastKey());

		Calendar currentIterationDateCal = zeroTimeCal(startDate);

		//First event
		Iterator<EventValue> eventsIt = eventListForEvtDef.iterator();
		EventValue currentEvent = null;
		if (eventsIt.hasNext()) {
			currentEvent = eventsIt.next();
		}
		
		for (Date currentIterationDate : mapFromQuotationsClose.keySet()) {
			
			currentIterationDateCal.setTime(currentIterationDate);
			
			BigDecimal closeForDate = (BigDecimal) mapFromQuotationsClose.get(currentIterationDate);
			if (closeForDate.compareTo(BigDecimal.ZERO) == 0) 
				LOGGER.error("Close for date is zero for at "+currentIterationDate+" for "+stock.getFriendlyName()+" and "+evtDefInfo+" between "+startDate+" and "+ endDate+", end calculation res is "+endCalcRes);
			
			//Some events may have been skipped (ie WE events) and must be disregarded
			while (currentEvent != null && currentIterationDateCal.getTime().after(currentEvent.getDate()) && eventsIt.hasNext()) {
				currentEvent = eventsIt.next();
			}
			
			//We process the event when reached
			if (currentEvent != null && currentIterationDateCal.getTime().compareTo(currentEvent.getDate()) == 0) {//Event date reached

				EventType eventType = currentEvent.getEventType();
				if ( prevEventType == null || (!eventType.equals(EventType.NONE) && !prevEventType.equals(eventType)) ) {//First trend or Valid trend change

					//TO
					if (prevEventType != null) {//Not the First trend : We close the current period and add it to the list
						
						period.setTo(currentEvent.getDate());
						period.setPriceAtTo(closeForDate.doubleValue());
						period.setRealised(true);
						addFilteredPeriod(periods, period);
						
					} else {//First trend : Nothing to close
						
					}
					
					//FROM : First trend or new one start
					period = new PeriodRatingDTO(currentEvent.getDate(), closeForDate.doubleValue(), eventType.toString());
					
					//Updating loop vars
					if (eventsIt.hasNext()) {
						currentEvent = eventsIt.next();
					}
					prevEventType = eventType;

				} else {//Same trend or invalid trend (ie NONE)
					
					//Updating loop vars
					if (eventsIt.hasNext()) {
						currentEvent = eventsIt.next();
					}
					
				}
				
			}
			
		} //End for quotations dates iteration
	
		//Not enough data were found : we get out of here
		if (prevEventType == null) {
			
			String message = "No trend forecast events were found after calculation.\n";
			
			LOGGER.warn(noResMsg+message + ".\n" +
					"Stock :"+ stock + "\n" +
					"Available neural events  :"+ ((eventListForEvtDef == null)?"null":eventListForEvtDef.size()) + "\n" +
					"Call Params : start "+startDate+", end "+endDate+", analysis "+analyseName+", event Def "+evtDefInfo + "\n" +
					"Output :  endCalc "+endCalcRes+", Calculated output size "+((calcOutput == null)?"null":calcOutput.size()));
			
			Date firstAvailDate = new Date(0);
			Date lastAvailDate =  new Date(0);
			if (mapFromQuotationsClose.size() > 0) {
				firstAvailDate = mapFromQuotationsClose.firstKey();
				lastAvailDate =  mapFromQuotationsClose.lastKey();
			}
			throw new NotEnoughDataException(stock, firstAvailDate, lastAvailDate, noResMsg+message, new Throwable());
			
		}
		
		//Finalising last trend
		period.setTo(endDate);
		period.setPriceAtTo(lastClose.doubleValue());
		period.setRealised(prevEventType.equals(EventType.BEARISH));
		addFilteredPeriod(periods, period);
		
		return periods;
	}

	private void addFilteredPeriod(List<PeriodRatingDTO> periods, PeriodRatingDTO period) {
		
		if (EventType.valueOf(period.getTrend()).equals(EventType.BULLISH)) {
			if (periods.size() == 0) {
				LOGGER.info("First bullish period discarded : "+period);
			} 
			else if (period.getPeriodLenght() < 15) {
				LOGGER.info("Short bullish period marked as no trend (false positive) : "+period);
				period.setTrend(EventType.NONE.toString());
				periods.add(period);
			} 
			else {
				periods.add(period);
			}
		}
		else {
			periods.add(period);
		}
		
	}
	
	private TuningResDTO buildResOnValidPeriods(
			List<PeriodRatingDTO> periods, SortedMap<Date, Number> mapFromQuotationsClose, Quotations quotations,
			Stock stock, Date startDate, Date endDate, String analyseName, SortedMap<Date, double[]> calcOutput, 
			String evtDefInfo, Observer observer) 
			throws IOException, InvalidAlgorithmParameterException {
		
		String trendFile = "noOutputAvailable";
		String chartFile = "noChartAvailable";
		
		Double trendFollowProfit = 1.00;
		
		Boolean generateBuySellCsv = MainPMScmd.getMyPrefs().getBoolean("autoporfolio.generatecsv", true);
		Boolean generateSmaCmpOutChart =  MainPMScmd.getMyPrefs().getBoolean("autoporfolio.generatepng", true);
		
		//Init output file
		String endDateStamp = "";
		if (MainPMScmd.getMyPrefs().getBoolean("perceptron.stampoutput", false)) {
			endDateStamp = new SimpleDateFormat("yyyyMMdd").format(endDate);
		}
		
		BufferedWriter csvWriter = null;
		String fileName = "noOutputAvailable";
		if (generateBuySellCsv) {
			fileName = "autoPortfolioLogs" + File.separator + analyseName + stock.getSymbol() + "_"+evtDefInfo+"_BuyAndSellRecords"+endDateStamp+".csv";
			File file = new File(System.getProperty("installdir") + File.separator + fileName);
			file.delete();
			csvWriter = new BufferedWriter(new FileWriter(file));
			csvWriter.write("Date, Quotations, Bearish, Bullish, Output \n");
		}

		//Other init
		BigDecimal lastClose = (BigDecimal) mapFromQuotationsClose.get(mapFromQuotationsClose.lastKey());

		Double csvDispFactor = 1.00;

		SortedMap<Date, Double> buySerie = new TreeMap<Date, Double>();
		SortedMap<Date, Double> sellSerie = new TreeMap<Date, Double>();
		
		int lastRealisedBullIdx = -1;
		PeriodRatingDTO previousPeriod = null;
		for (PeriodRatingDTO currentPeriod : periods) {
			
			//Exports
			if (generateBuySellCsv || generateSmaCmpOutChart) {
				
				//csv gaps
				SortedMap<Date, Number> gapQuotationMap;
				if (generateBuySellCsv && previousPeriod != null && (gapQuotationMap = mapFromQuotationsClose.subMap(previousPeriod.getTo(), currentPeriod.getFrom())).size() > 1) {
					for (Date gapDate : gapQuotationMap.keySet()) {
						Double closeForGapDate = gapQuotationMap.get(gapDate).doubleValue();
						double[] output = calcOutput.get(gapDate);
						exportLine(generateBuySellCsv, false, csvDispFactor, csvWriter, buySerie, sellSerie, gapDate, closeForGapDate, EventType.NONE, output);
					}
				}
				previousPeriod = currentPeriod;
				
				//export period
				SortedMap<Date, Number> periodQuotationMap = mapFromQuotationsClose.subMap(currentPeriod.getFrom(), currentPeriod.getTo());
				EventType periodTrend = EventType.valueOf(currentPeriod.getTrend());
				for (Date periodInnerDate : periodQuotationMap.keySet()) {
					Double closeForInnerDate = periodQuotationMap.get(periodInnerDate).doubleValue();
					double[] output = calcOutput.get(periodInnerDate);
					exportLine(generateBuySellCsv, generateSmaCmpOutChart, csvDispFactor, csvWriter, buySerie, sellSerie, periodInnerDate, closeForInnerDate, periodTrend, output);
				}
				
			}
			
			//Calculate profit
			if (EventType.valueOf(currentPeriod.getTrend()).equals(EventType.BULLISH) && currentPeriod.isRealised()) {
				
				lastRealisedBullIdx = periods.indexOf(currentPeriod);

				Double followPriceRateOfChange = currentPeriod.getPriceRateOfChange();
				if (followPriceRateOfChange.isNaN() || followPriceRateOfChange.isInfinite()) {
					String message = "Error calculating followPriceRateOfChange for "+stock.getFriendlyName()+" : "+currentPeriod;
					LOGGER.error(message);
					throw new InvalidAlgorithmParameterException(message);
				}

				//Follow Profit
				if (LOGGER.isDebugEnabled()) LOGGER.debug(
						"Buy : Compound profit is "+trendFollowProfit+" at "+currentPeriod.getFrom()+". "
						+ "First price is "+currentPeriod.getPriceAtFrom()+" at "+currentPeriod.getFrom()+". " 
						+ "Last price is "+currentPeriod.getPriceAtTo()+" at "+currentPeriod.getTo()+". ");
				
				trendFollowProfit = trendFollowProfit * (followPriceRateOfChange + 1);
				
				if (LOGGER.isDebugEnabled()) LOGGER.debug(
						"New Compound at "+currentPeriod.getTo()+" : prevTotProfit*("+followPriceRateOfChange+"+1)="+trendFollowProfit);


			} 
			else if  (EventType.valueOf(currentPeriod.getTrend()).equals(EventType.BEARISH)) {
				
				//Follow Profit
				if (LOGGER.isDebugEnabled()) LOGGER.debug(
						"Sell : Compound profit is "+trendFollowProfit+" at "+currentPeriod.getFrom()+". "
						+ "Period "+currentPeriod+" : followPriceRateOfChange for period "+currentPeriod.getPriceRateOfChange());

			}
			else if (EventType.valueOf(currentPeriod.getTrend()).equals(EventType.BULLISH) && !currentPeriod.isRealised()){
				
				//Nothing
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Unrealised bull period "+currentPeriod);
				
			}
			
		} //End for over periods

		//Finalise Csv file
		if (generateBuySellCsv) {
			csvWriter.close();
			trendFile = fileName;
		}
		
		//Finalise PNG Chart
		if (generateSmaCmpOutChart) {
			
			try {
				
				String chartFileName = "autoPortfolioLogs" + File.separator + analyseName + stock.getSymbol()+ "_"+evtDefInfo+ "_OutChart"+endDateStamp+".png";
				generateOutChart(chartFileName, calcOutput, quotations, buySerie, sellSerie);
				observer.update(null, new ObserverMsg(stock, ObserverMsg.ObsKey.PRGSMSG, "Output images generated ..."));
				chartFile = chartFileName;
				
			} catch (NotEnoughDataException e) {
				LOGGER.warn("Can't generate chart for "+stock,e, true);
				chartFile = "noChartAvailable";
			} catch (Exception e) {
				LOGGER.error("Can't generate chart for "+stock,e);
				chartFile = "noChartAvailable";
			}
			
		} else {
			chartFile = "noChartAvailable";
		}
		
		observer.update(null, new ObserverMsg(stock, ObserverMsg.ObsKey.PRGSMSG, "Output file generated ..."));

		//Finalise profits
		trendFollowProfit = trendFollowProfit - 1;
		
		PeriodRatingDTO firstPeriod = periods.get(0);
		PeriodRatingDTO lastPeriod = periods.get(periods.size()-1);
		
		if (!periods.isEmpty() && lastRealisedBullIdx != -1) {
			Date firstBullFrom = firstPeriod.getFrom();
			BigDecimal firstBullStartPrice = quotations.getClosestCloseForDate(firstBullFrom);
			PeriodRatingDTO lastBullPeriod = periods.get(lastRealisedBullIdx);
			Date lastBullTo = lastBullPeriod.getTo();
			BigDecimal lastBullStartPrice = quotations.getClosestCloseForDate(lastBullTo);
			LOGGER.info("Trend following compounded profit calculation is first Close "+firstBullStartPrice+" at "+firstBullFrom+" and last Close "+lastBullStartPrice+" at "+lastBullTo+" : "+trendFollowProfit);
		} else {
			LOGGER.info("Trend following profit calculation is unknown (No bullish periods were detected or no trend change detected)");
		}
		
		//Buy and hold profit
		BigDecimal firstClose = quotations.getClosestCloseForDate(firstPeriod.getFrom());
		BigDecimal buyAndHoldProfit = (firstClose.compareTo(BigDecimal.ZERO) != 0)?lastClose.subtract(firstClose).divide(firstClose,10, BigDecimal.ROUND_HALF_EVEN):BigDecimal.ZERO;
		LOGGER.info("Buy and hold profit calculation is first Close "+firstClose+" at "+firstPeriod.getFrom()+" and last Close "+lastClose+" at "+endDate+" : ("+lastClose+"-"+firstClose+")/"+firstClose+"="+buyAndHoldProfit);
		
		//Output boundaries
		Date outputFirstKey = startDate;
		Date outputLastKey = endDate;
		if (!calcOutput.isEmpty()) {
			outputFirstKey = calcOutput.firstKey();
			outputLastKey = calcOutput.lastKey();
		}
		
		//return
		return new TuningResDTO(periods, trendFile, chartFile, lastPeriod.getTrend(), trendFollowProfit, Double.NaN, buyAndHoldProfit, outputFirstKey, outputLastKey);
		
	}

	protected void exportLine(Boolean generateBuySellCsv, Boolean generateSmaCmpOutChart, 
			Double csvDispFactor, BufferedWriter csvWriter,
			SortedMap<Date, Double> buySerie, SortedMap<Date, Double> sellSerie, 
			Date periodInnerDate, Double closeForInnerDate, EventType periodTrend, double[] output) {
		
		DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
		String line  = simpleDateFormat.format(periodInnerDate);
		line  = line + ", " + closeForInnerDate;
		
		String outputString =(output == null)?"NaN":output[0]+"";
		
		if (periodTrend.equals(EventType.BULLISH)) {
			if (generateBuySellCsv) {
				line = line + ", , " + (closeForInnerDate * csvDispFactor)+ ", "+outputString+"\n";
				try {
					csvWriter.append(line);
				} catch (IOException e) {
					LOGGER.error("failed to export csv ", e);
				}
			}
			if (generateSmaCmpOutChart) buySerie.put(periodInnerDate, closeForInnerDate);	
		} else if (periodTrend.equals(EventType.BEARISH)) {
			if (generateBuySellCsv) {
				line = line + ", "+ (closeForInnerDate *csvDispFactor) + ", , "+outputString+"\n";
				try {
					csvWriter.append(line);
				} catch (IOException e) {
					LOGGER.error("failed to export csv ", e);
				}
			}
			if (generateSmaCmpOutChart) sellSerie.put(periodInnerDate, closeForInnerDate);
		} else {
			if (generateBuySellCsv) {
				line = line + ", , , "+outputString+"\n";
				try {
					csvWriter.append(line);
				} catch (IOException e) {
					LOGGER.error("failed to export csv ", e);
				}
			}
		}
		
	}


	public TuningResDTO buildTuningRes(
			Stock stock, Date startDate, Date endDate, Date endCalcRes, String analyseName,
			SortedMap<Date, double[]> calcOutput, Collection<EventValue> eventListForEvtDef, String noResMsg, String evtDefInfo, Observer observer) 
			throws IOException, NoQuotationsException, NotEnoughDataException, InvalidAlgorithmParameterException {

		LOGGER.info("Building Tuning res for "+stock.getFriendlyName()+" and "+evtDefInfo+" between "+startDate+" and "+ endDate+", end calculation res is "+endCalcRes);
		
		Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, startDate, endCalcRes, true, stock.getMarketValuation().getCurrency(), 1, ValidityFilter.CLOSE);
		SortedMap<Date, Number> mapFromQuotationsClose = QuotationsFactories.getFactory().buildExactBMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size()-1);
		LOGGER.info("Quotations map for "+stock.getFriendlyName()+" ranges from "+mapFromQuotationsClose.firstKey()+" to "+mapFromQuotationsClose.lastKey()+" while requested from "+startDate+" to "+endCalcRes);
		
		List<PeriodRatingDTO> periods = validPeriods(mapFromQuotationsClose, stock, startDate, endDate, endCalcRes, analyseName, calcOutput, eventListForEvtDef, noResMsg, evtDefInfo);
		
		return buildResOnValidPeriods(periods, mapFromQuotationsClose, quotations, stock, startDate, endDate, analyseName, calcOutput, evtDefInfo, observer);
		
	}

	private void generateOutChart(
			String chartFileName, 
			SortedMap<Date, double[]> calcOutput, Quotations quotations, 
			SortedMap<Date, Double> buySerie, SortedMap<Date, Double> sellSerie) throws NotEnoughDataException, IOException, FileNotFoundException {
		
		ChartGenerator chartGenerator = new ChartGenerator("Premium Markets output V. Lag fixed SMA 100");
		
		//line series
		SortedMap<DataSetBarDescr, SortedMap<Date, Double>> lineSeries = new TreeMap<DataSetBarDescr, SortedMap<Date,Double>>();
		SortedMap<Date, Double> quotes =  QuotationsFactories.getFactory().buildSMapFromQuotationsClose(quotations, quotations.getFirstDateShiftedIdx(), quotations.getLastDateIdx());
		lineSeries.put(new DataSetBarDescr(2, "historical prices",Color.BLUE.darker()), quotes);
		
		SortedMap<Date, Double> sma100;
		try {
			TalibSmaSmoother smaSmoother = new TalibSmaSmoother(100);
			sma100 = smaSmoother.sSmooth(quotes, true);
			lineSeries.put(new DataSetBarDescr(1, "artificially lag fixed sma 100", Color.ORANGE, 3f), sma100);
		} catch (NegativeArraySizeException e) {
			throw new NotEnoughDataException(quotations.getStock(), "Generate chart : No enough data to calculate sma for "+quotations.getStock(), e);
		}
		
		SortedMap<Date, Double> sCalcOutput = sCalcOutput(calcOutput);
		lineSeries.put(new DataSetBarDescr(0, "Premium Markets trend line", new Color(46,236,236), 3f), sCalcOutput);
		
		//bar series
		SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barSeries = new TreeMap<DataSetBarDescr, SortedMap<Date,Double>>();
		
		///sma b&s
		SDiscretDerivator derivator = new SlopeDerivator(1, 0,  true, true);
		SortedMap<Date, Double> sma100Drv = derivator.sDerivateDiscret(sma100);
		
		SortedMap<Date, Double> smaBearish = new TreeMap<Date,Double>();
		SortedMap<Date, Double> smaBullish = new TreeMap<Date,Double>();
		for (Date date : sma100Drv.keySet()) {
			Double value = sma100Drv.get(date);
			if (value == 0) {
				smaBullish.put(date, quotes.get(date)*1.25);
			} else if (value == 1) {
				smaBearish.put(date, quotes.get(date)*1.25);
			}
		}
		barSeries.put(new DataSetBarDescr(4, "bearish sma", new Color(246,173,173)), smaBearish);
		barSeries.put(new DataSetBarDescr(3, "bullish sma", new Color(189,249,189)), smaBullish);
		
		//Prediction period
		Date predStart = maxLastKey(smaBearish, smaBullish);
		Date predEnd = maxLastKey(sellSerie, buySerie);
		SortedMap<Date, Double> pred = new TreeMap<Date,Double>();
		SortedMap<Date, Double> predSubMap = quotes.subMap(predStart, predEnd);
	
		ApacheStats apacheStatsQ = new ApacheStats(new Max());
		double maxQ = apacheStatsQ.sEvaluate(quotes.values());
		ApacheStats apacheStatsO = new ApacheStats(new Max());
		double maxO = apacheStatsO.evaluate(calcOutput.values());
		double fact = Math.max(maxQ,maxO);
		for (Date date : predSubMap.keySet()) {
			pred.put(date, fact*1.25);
		}
		barSeries.put(new DataSetBarDescr(0, "Prediction zone", new Color(128, 128, 128, 100)), pred);

		///Output b&s
		barSeries.put(new DataSetBarDescr(1, "bearish prediction", Color.RED), sellSerie);
		barSeries.put(new DataSetBarDescr(2, "bullish prediction", Color.GREEN), buySerie);
		
		//chart
		chartGenerator.generateChartPNGFor(new FileOutputStream(new File(System.getProperty("installdir") + File.separator + chartFileName)), false, lineSeries, barSeries);
	}

	private Date maxLastKey(SortedMap<Date, Double> firstSerie, SortedMap<Date, Double> secondSerie) throws NotEnoughDataException {
		
		Date maxDate = null;
		if (secondSerie.isEmpty() && firstSerie.isEmpty()) {
			throw new NotEnoughDataException(null, "No prediction data : No prediction period can be infered.", new Exception());
		} else if (secondSerie.isEmpty()) {
			maxDate = firstSerie.lastKey();
		} else if (firstSerie.isEmpty()) {
			maxDate = secondSerie.lastKey();
		} else {
			maxDate = (secondSerie.lastKey().after(firstSerie.lastKey()))? secondSerie.lastKey() : firstSerie.lastKey();
		}
		return maxDate;
		
	}

	//TODO obj
	private SortedMap<Date, Double> sCalcOutput(SortedMap<Date, double[]> calcOutput) {
		
		SortedMap<Date, Double> sCalcOutput = new TreeMap<Date, Double>();
		for (Date date : calcOutput.keySet()) {
			sCalcOutput.put(date, calcOutput.get(date)[0]);
		}
		return sCalcOutput;
	}

	private Calendar zeroTimeCal(Date date) {
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(date);
		currentDate.set(Calendar.HOUR_OF_DAY,0);
		currentDate.set(Calendar.MINUTE,0);
		currentDate.set(Calendar.SECOND,0);
		currentDate.set(Calendar.MILLISECOND,0);
		return currentDate;
	}

	//No longer needed as we filter in the sql request now.
	@SuppressWarnings("unused")
	private NavigableSet<EventValue> filterEventsDefs(SymbolEvents symbolEvents, EventDefinition... evtDefs) {

		NavigableSet<EventValue> neuralEventsList =  new TreeSet<EventValue>(new Comparator<EventValue>() {
			@Override
			public int compare(EventValue o1, EventValue o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});

		for (EventValue eventValue : symbolEvents.getDataResultMap().values()) {
			for (EventDefinition evtDef : evtDefs) {
				if (eventValue.getEventDef().equals(evtDef)) {
					Calendar currentEventDate = zeroTimeCal(eventValue.getDate());
					eventValue.setDate(currentEventDate.getTime());
					neuralEventsList.add(eventValue);
				}
			}
			
		}
		return neuralEventsList;
	}
	
	/**
	 * 
	 * Builds a *_ConfigRating.csv file for the stock containing the trend periods results of the stock tuning.
	 * The from and to dates are the date for the start of a trend (BULLISH/BEARISH) and the end of the trend.
	 * The file contains one line per config elected over the tuning.
	 * For each config its trend period of occurrence and the trend values during this period.
	 * Hence you can have several lines with different configs over the same trend period where they were consecutively elected with the same trend results. 
	 * As in fact the config elected will change at the pace of the tuning periods which are different from the trend periods.
	 * On the other hand, when trend changes, also does the trend period dates.
	 * In the same way the same config can be elected over several consecutive trend period changes.
	 */
	public void exportConfigRating(String analysisName, TuningResDTO tuningRes, Date startDate, Date endDate, FinalRating calculatedRating) throws IOException {
				
			String fileName = "tmp" + File.separator + analysisName + "_ConfigRating.csv";
			File configRatings = new File(System.getProperty("installdir") + File.separator + fileName);
			FileWriter fileWriter = new FileWriter(configRatings);
			fileWriter.write("config, cfg start, cfg end, trend start, trend end, length, price change, trend, success/failure, compound profit \n");

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
			Double compoundProfit = 1d;
			for (PeriodRatingDTO periodRatingDTO : tuningRes.getPeriods()) {
				
				String successOrFailure = 
						(	
							(periodRatingDTO.getTrend().equals(EventType.BULLISH.name()) && periodRatingDTO.getPriceRateOfChange() <= 0) || 
							(periodRatingDTO.getTrend().equals(EventType.BEARISH.name()) && periodRatingDTO.getPriceRateOfChange() > 0) 	
						)?
						"FAILURE":"SUCCESS";

				if (periodRatingDTO.getTrend().equals(EventType.BULLISH.name())) compoundProfit = compoundProfit* (periodRatingDTO.getPriceRateOfChange()+1);
				
				String cfgStr = 
						dateFormat.format(periodRatingDTO.getFrom())+" , "+dateFormat.format(periodRatingDTO.getTo())+" , "+ 
						periodRatingDTO.getPeriodLenght() + " , " +periodRatingDTO.getPriceRateOfChange()+" , "+periodRatingDTO.getTrend() + " , " + successOrFailure + " , " + compoundProfit;
				
				if (periodRatingDTO.getConfigs().size() > 0) {
					
					for (String pConfig : periodRatingDTO.getConfigs()) {
						String cfgStart = dateFormat.format(startDate);
						String cfgEnd = dateFormat.format(endDate);
						fileWriter.write(pConfig + " , " + cfgStart + " , " + cfgEnd + " , " +cfgStr + "\n");
					}
					
				} else {//No config
					fileWriter.write("No config? " + " , , , " + cfgStr + "\n");
				}
				
			}
			
			fileWriter.write("total , percent gain : "+tuningRes.getFollowProfit()+", price change : "+tuningRes.getStockPriceChange()+"\n");
			tuningRes.setConfigRatingFile(fileName);
			
			fileWriter.write("rating , "+calculatedRating);		
			fileWriter.close();

	}

	private Validity smoothedPeriodValidity(PeriodRatingDTO periodRatingDTO, Double totalPriceChange) {
		
		Validity periodValidity = Validity.NORATING;
		
		double errorDelta = 0.0;
		double ratioToTotPriceChange = 0.10;
		boolean isBullAndNeg = periodRatingDTO.getTrend().equals(EventType.BULLISH.name()) && periodRatingDTO.getPriceRateOfChange() < -errorDelta;
		boolean isBearAndPos = periodRatingDTO.getTrend().equals(EventType.BEARISH.name()) && periodRatingDTO.getPriceRateOfChange() > errorDelta;
		boolean isPeriodSignificant = Math.abs(periodRatingDTO.getPriceRateOfChange()) >  Math.abs(totalPriceChange*ratioToTotPriceChange);

		if ( isBullAndNeg || isBearAndPos ) {
			if (isPeriodSignificant) {
				periodValidity = Validity.FAILURE;
			} else {
				periodValidity = Validity.NORATING;
			}
		} else {
			periodValidity = Validity.SUCCESS;
		}
		
		return periodValidity;
	}
	
	public FinalRating tuningFinalizationRating(Double totProfit, Double totPriceChange, int nbSuccess, int nbFailure) {

		FinalRating ret = new FinalRating(nbSuccess, nbFailure, totPriceChange);

		ret.rating = 0.0;
		if (nbFailure == 0 && nbSuccess == 0) {
			ret.rating = 0.0;
		} else if (nbFailure == 0) {
			ret.rating = Double.POSITIVE_INFINITY;
		} else {
			Double log10 = Math.log10(new Double(nbSuccess)/new Double(nbFailure));
			ret.rating = log10;
		}

		double threshold = Math.log10(100.00/75.00);
		
		if (totProfit < 0 && totProfit < totPriceChange) {
			ret.cause += "Neg. profit underperforms share price change. ";
			ret.validity = Validity.FAILURE;
			return ret;
		}
		
		if (totProfit < 0) {
			ret.cause += "Negative profit.";
			ret.validity = Validity.FAILURE;
			return ret;
		}
		
		if (totProfit > 0 && totProfit >= totPriceChange) {
			ret.cause += "Positive profit.";
			ret.validity = Validity.SUCCESS;
			return ret;
		}
		
		if (ret.rating < threshold ) { //More failure than success it is very likely that profit will be sub zero
			ret.cause =  "Below Success/Failure threshold. ";
			ret.validity = Validity.FAILURE;
			return ret;
		} 
		
		if (ret.rating >= threshold) {
			ret.cause = "Above Success/Failure threshold. ";
			ret.validity = Validity.SUCCESS;
			return ret;
		}

		return ret;
	}
	
	public class FinalRating {
		
		private Double rating;
		private Double totPrcChgUsed;
		private int nbSuccess;
		private int nbFailure;
		private Validity validity;
		private String cause;

		public FinalRating(int nbSuccess, int nbFailure, Double totPrcChgUsed) {
			super();
			this.nbSuccess = nbSuccess;
			this.nbFailure = nbFailure;
			this.totPrcChgUsed = totPrcChgUsed;
		}

		public Double getRating() {
			return rating;
		}
		
		public Validity getValidity() {
			return validity;
		}

		public String getCause() {
			return cause;
		}

		public int getNbSuccess() {
			return nbSuccess;
		}

		public int getNbFailure() {
			return nbFailure;
		}
		
		@Override
		public String toString() {
			return "Failure : "+nbFailure+", Success : "+nbSuccess + ", TotPrcChgUsed : "+totPrcChgUsed+ ", Rating : "+rating+ ", Validity : "+validity+", Cause : "+cause;
		}
	}

	public FinalRating calculateRating(TuningResDTO tuningRes) {
		
		int nbSuccess = 0;
		int nbFailure = 0;
		for (PeriodRatingDTO periodRatingDTO : tuningRes.getPeriods()) {
			Validity periodValidity = smoothedPeriodValidity(periodRatingDTO, tuningRes.getStockPriceChange());
			
			//nbSuccess = nbSuccess + ((periodValidity.equals(Validity.SUCCESS))?1:0);
			if (periodRatingDTO.getTrend().equals(EventType.BULLISH.toString())) {//we tally only the bullish failures and success
				nbSuccess = nbSuccess + ((periodValidity.equals(Validity.SUCCESS))?1:0);
				nbFailure = nbFailure + ((periodValidity.equals(Validity.FAILURE))?1:0);
			}
		}
		
		FinalRating finalRating = tuningFinalizationRating(tuningRes.getFollowProfit(), tuningRes.getStockPriceChange(), nbSuccess, nbFailure);
		
		//Test 
		//finalRating.validity = Validity.SUCCESS;
		
		return finalRating;
	}


}
