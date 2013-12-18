/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
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
import java.math.RoundingMode;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.finance.pms.events.quotations.Quotations;
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

	public TuningResDTO buildTuningRes(
			Stock stock, Date startDate, Date endDate, Date endCalcRes, String analyseName,
			SortedMap<Date, double[]> calcOutput, Collection<EventValue> eventListForEvtDef, String noResMsg, String evtDefInfo, Observer observer) 
			throws IOException, NoQuotationsException, InvalidAlgorithmParameterException, NotEnoughDataException {
		
		List<PeriodRatingDTO> periods = new ArrayList<PeriodRatingDTO>();
		String trendFile = "noOutputAvailable";
		String chartFile = "noChartAvailable";
		BigDecimal totProfit = BigDecimal.ONE;

		EventType prevEventType = null;
		PeriodRatingDTO period = new PeriodRatingDTO();
		
		Boolean generateBuySellCsv = MainPMScmd.getPrefs().getBoolean("autoporfolio.generatecsv", true);
		Boolean generateSmaCmpOutChart =  MainPMScmd.getPrefs().getBoolean("autoporfolio.generatepng", true);
		
		//Init output file
		String endDateStamp = "";
		if (MainPMScmd.getPrefs().getBoolean("perceptron.stampoutput", false)) {
			endDateStamp = new SimpleDateFormat("yyyyMMdd").format(endDate);
		}
		
		BufferedWriter bufferedWriter = null;
		String fileName = "noOutputAvailable";
		if (generateBuySellCsv) {
			fileName = "autoPortfolioLogs" + File.separator + analyseName + stock.getSymbol() + "_"+evtDefInfo+"_BuyAndSellRecords"+endDateStamp+".csv";
			File file = new File(System.getProperty("installdir") + File.separator + fileName);
			file.delete();
			bufferedWriter = new BufferedWriter(new FileWriter(file));
			bufferedWriter.write("Date, Quotations, Bearish, Bullish, Output \n");
		}

		//Other init
		Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, startDate, endCalcRes, true, stock.getMarketValuation().getCurrency(), 0, 0);
		BigDecimal lastClose = quotations.getClosestCloseForDate(endDate);

		Pattern pattern = Pattern.compile("config : (.*) es : ");
		DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

		Calendar currentDate = zeroTimeDate(startDate);

		BigDecimal big2 = BigDecimal.ONE;

		Iterator<EventValue> eventsIt = eventListForEvtDef.iterator();
		EventValue currentEvent = null;
		Date firstEventDate = null;
		//We fetch the first event
		if (eventsIt.hasNext()) {
			currentEvent = eventsIt.next();
			firstEventDate = currentEvent.getDate();
		}

		BigDecimal prevCloseForDate = quotations.getClosestCloseForDate(currentDate.getTime());
		SortedMap<Date, Double> buySerie = new TreeMap<Date, Double>();
		SortedMap<Date, Double> sellSerie = new TreeMap<Date, Double>();
		
		while (currentDate.getTime().before(endDate) || currentDate.getTime().compareTo(endDate) == 0) {
			
			BigDecimal closeForDate = quotations.getClosestCloseForDate(currentDate.getTime());
			
			String line = "";
			if (generateBuySellCsv) {
				line  = simpleDateFormat.format(currentDate.getTime());
				line  = line + ", " + closeForDate;
			}
		
			//Some events may have been skipped (ie WE events) and must be disregarded
			while (currentEvent != null && currentDate.getTime().after(currentEvent.getDate()) && eventsIt.hasNext()) {
				currentEvent = eventsIt.next();
			}
			
			//We process the event when reached
			if (currentEvent != null && currentDate.getTime().compareTo(currentEvent.getDate()) == 0) {

				//TODO : remove this config match
				try {
					String message = currentEvent.getMessage();
					Matcher matcher = pattern.matcher(message);
					matcher.find();

					String currentConfig = matcher.group(1);
					if (!period.getConfigs().contains(currentConfig)) {
						period.getConfigs().add(currentConfig);
					}

				} catch (Exception e) {
					if (!period.getConfigs().contains("No_cfg_match_in_event_msg")) {
						period.getConfigs().add("No_cfg_match_in_event_msg");
					}
				}

				EventType eventType = currentEvent.getEventType();
				if (prevEventType == null || !prevEventType.equals(eventType)) {//Trend change or First trend

					//TO
					if (prevEventType != null) {//Not the First trend : we cummulate

						BigDecimal pPriceChange = closeForDate.subtract(prevCloseForDate).divide(prevCloseForDate,10,RoundingMode.HALF_DOWN);

						//Period
						period.setTo(currentEvent.getDate());
						period.setPriceChange(pPriceChange);
						periods.add(period);
						period = new PeriodRatingDTO();

						//Profit for period
						if (eventType.equals(EventType.BEARISH)) {//sell
							
							LOGGER.debug(
									"Sell : Compound profit is "+totProfit+" at "+currentDate.getTime()+". " +
									"New price change is "+closeForDate+" at "+currentDate.getTime()+", previous close "+prevCloseForDate+" : "+closeForDate+"-"+prevCloseForDate+"/"+prevCloseForDate+"="+pPriceChange);
							
							totProfit = totProfit.multiply(pPriceChange.add(BigDecimal.ONE)).setScale(10,RoundingMode.HALF_DOWN);
							
							LOGGER.debug("New Compound at "+currentDate.getTime()+" : prevTotProfit*("+pPriceChange+"+1)="+totProfit);
						}				
					}

					//FROM //First trend or start of a new one
					//Period
					period.setTrend(eventType.toString());
					period.setFrom(currentEvent.getDate());

					//Csv Trend change
					if (currentEvent.getEventType().equals(EventType.BULLISH)) {
						if (generateBuySellCsv) line = line + ", , " + prevCloseForDate.multiply(big2);
						buySerie.put(currentDate.getTime(), quotations.getClosestCloseForDate(currentDate.getTime()).doubleValue());
						
						LOGGER.debug("Buy : Compound profit at "+totProfit+" at "+currentDate.getTime()+". First price is "+closeForDate+" at "+currentDate.getTime());
						
					} else if (currentEvent.getEventType().equals(EventType.BEARISH)) {
						if (generateBuySellCsv) line = line + ", "+ prevCloseForDate.multiply(big2) + ", ";
						sellSerie.put(currentDate.getTime(), quotations.getClosestCloseForDate(currentDate.getTime()).doubleValue());
					}
					if (eventsIt.hasNext()) {
						currentEvent = eventsIt.next();
					}

					if (!eventType.equals(EventType.NONE)){
						prevEventType = eventType;
						prevCloseForDate = closeForDate;
					}
					

				} else {

					//Csv No trend change
					if (currentEvent.getEventType().equals(EventType.BULLISH)) {
						if (generateBuySellCsv) line = line + ", , " + prevCloseForDate.multiply(big2);
						buySerie.put(currentDate.getTime(), quotations.getClosestCloseForDate(currentDate.getTime()).doubleValue());
					} else if (currentEvent.getEventType().equals(EventType.BEARISH)) {
						if (generateBuySellCsv) line = line + ", "+ prevCloseForDate.multiply(big2) + ", ";
						sellSerie.put(currentDate.getTime(), quotations.getClosestCloseForDate(currentDate.getTime()).doubleValue());
					}
					if (eventsIt.hasNext()) {
						currentEvent = eventsIt.next();
					}
				}

			} else { //Filling up until the current event date is reached
				
				if (prevEventType == null) {
					if (generateBuySellCsv) line = line + ", , ";
				}
				else if (prevEventType.equals(EventType.BULLISH)) {
					if (generateBuySellCsv) line = line + ", , " + prevCloseForDate.multiply(big2);
					buySerie.put(currentDate.getTime(), quotations.getClosestCloseForDate(currentDate.getTime()).doubleValue());
				} 
				else if (prevEventType.equals(EventType.BEARISH)) {
					if (generateBuySellCsv) line = line + ", "+ prevCloseForDate.multiply(big2) + ", ";
					sellSerie.put(currentDate.getTime(), quotations.getClosestCloseForDate(currentDate.getTime()).doubleValue());
				}
				
			}

			
			if (generateBuySellCsv) {
				
				double[] output = calcOutput.get(currentDate.getTime());
				String outputString =(output == null)?"NaN":output[0]+"";
				
				line = line + ", "+outputString;
				line = line + "\n";
				bufferedWriter.write(line);
			}

			QuotationsFactories.getFactory().incrementDate(currentDate, 1);
			
		}//End while

		if (generateBuySellCsv) {
			bufferedWriter.close();
		}
		
		//Not enough data we stop
		if (prevEventType == null || (calcOutput.size() == 0 && (generateBuySellCsv || generateSmaCmpOutChart))) {
			
			String message = "No trend forecast events were found after calculation.\n";
			
			LOGGER.warn(noResMsg+message + ".\n" +
					"Stock :"+ stock + "\n" +
					"Available neural events  :"+ ((eventListForEvtDef == null)?"null":eventListForEvtDef.size()) + "\n" +
					"Call Params : start "+startDate+", end "+endDate+", analysis "+analyseName+", event Def "+evtDefInfo + "\n" +
					"Output :  endCalc "+endCalcRes+", Calculated output size "+((calcOutput == null)?"null":calcOutput.size()));
			
			Date firstAvailDate = new Date(0);
			Date lastAvailDate =  new Date(0);
			if (quotations.size() > 0) {
				firstAvailDate = quotations.getDate(quotations.getFirstDateShiftedIdx());
				lastAvailDate = quotations.getDate(quotations.getLastDateIdx());
			}
			throw new NotEnoughDataException(stock, firstAvailDate, lastAvailDate, noResMsg+message, new Throwable());
			
		}
		
		//Enough data we throw up the things
		
		//PNG chart
		if (generateBuySellCsv) trendFile = fileName;
		
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
		
		
		BigDecimal pPriceChange = lastClose.subtract(prevCloseForDate).divide(prevCloseForDate,10,RoundingMode.HALF_DOWN);

		//Finalise profit
		if (prevEventType.equals(EventType.BULLISH)) {
			totProfit = totProfit.multiply(pPriceChange.add(BigDecimal.ONE)).setScale(10,RoundingMode.HALF_DOWN);
		}
		totProfit = totProfit.subtract(BigDecimal.ONE);

		//Finalise trend
		period.setTo(endDate);
		period.setPriceChange(pPriceChange);
		periods.add(period);	
		
		//Buy and hold profit
		BigDecimal firstClose = quotations.getClosestCloseForDate(firstEventDate);
		BigDecimal buyAndHoldProfit = (firstClose.compareTo(BigDecimal.ZERO) != 0)?lastClose.subtract(firstClose).divide(firstClose,10,RoundingMode.HALF_DOWN):BigDecimal.ZERO;
		LOGGER.debug("Buy and hold profit calculation is first Close "+firstClose+" at "+firstEventDate+" and last Close "+lastClose+" at "+endDate+" : "+lastClose+"-"+firstClose+"/"+firstClose+"="+buyAndHoldProfit);
		
		Date outputFirstKey = startDate;
		Date outputLastKey = endDate;
		if (!calcOutput.isEmpty()) {
			outputFirstKey = calcOutput.firstKey();
			outputLastKey = calcOutput.lastKey();
		}
		
		return new TuningResDTO(periods, trendFile, chartFile, prevEventType.toString(), totProfit, buyAndHoldProfit, outputFirstKey, outputLastKey);
		
	}

	private void generateOutChart(
			String chartFileName, 
			SortedMap<Date, double[]> calcOutput, Quotations quotations, 
			SortedMap<Date, Double> buySerie, SortedMap<Date, Double> sellSerie) throws NotEnoughDataException, IOException, FileNotFoundException {
		
		ChartGenerator chartGenerator = new ChartGenerator("Premium Markets output V. Lag fixed SMA 100");
		
		//line series
		SortedMap<DataSetBarDescr, SortedMap<Date, Double>> lineSeries = new TreeMap<DataSetBarDescr, SortedMap<Date,Double>>();
		SortedMap<Date, Double> quotes =  QuotationsFactories.getFactory().buildSMapFromQuotationsClose(quotations, quotations.getFirstDateShiftedIdx(), quotations.getLastDateIdx()); //.subMap(fromKey, toKey);
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

	private Calendar zeroTimeDate(Date date) {
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
					Calendar currentEventDate = zeroTimeDate(eventValue.getDate());
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
	 * The from and to dates are the the date for the start of a trend (BULLISH/BEARISH) and the end of the trend.
	 * The file contains one line per config elected over the tuning.
	 * For each config its trend period of occurrence and the trend values during this period.
	 * Hence you can have several lines with different configs over the same trend period where they were consecutively elected with the same trend results. 
	 * As in fact the config elected will change at the pace of the tuning periods which are different from the trend periods.
	 * On the other hand, when trend changes, also does the trend period dates.
	 * In the same way the same config can be elected over several consecutive trend period changes.
	 * 
	 * @param analysisName
	 * @param tuningRes
	 * @param endDate 
	 * @param startDate 
	 * @param calculatedRating 
	 * @return
	 * @throws IOException
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
							(periodRatingDTO.getTrend().equals(EventType.BULLISH.name()) && periodRatingDTO.getPriceChange() <= 0) || 
							(periodRatingDTO.getTrend().equals(EventType.BEARISH.name()) && periodRatingDTO.getPriceChange() > 0) 	
						)?
						"FAILURE":"SUCCESS";

				if (periodRatingDTO.getTrend().equals(EventType.BULLISH.name())) compoundProfit = compoundProfit* (periodRatingDTO.getPriceChange()+1);
				
				String cfgStr = 
						dateFormat.format(periodRatingDTO.getFrom())+" , "+dateFormat.format(periodRatingDTO.getTo())+" , "+ 
						periodRatingDTO.getPeriodLenght() + " , " +periodRatingDTO.getPriceChange()+" , "+periodRatingDTO.getTrend() + " , " + successOrFailure + " , " + compoundProfit;
				
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
			
			fileWriter.write("total , profit : "+tuningRes.getProfit()+", price change : "+tuningRes.getStockPriceChange()+"\n");
			tuningRes.setConfigRatingFile(fileName);
			
			fileWriter.write("rating , "+calculatedRating);		
			fileWriter.close();

	}

	private Validity smoothedPeriodValidity(PeriodRatingDTO periodRatingDTO, Double totalPriceChange) {
		
		Validity periodValidity = Validity.NORATING;
		
		double errorDelta = 0.0;
		double ratioToTotPriceChange = 0.10;
		boolean isBullAndNeg = periodRatingDTO.getTrend().equals(EventType.BULLISH.name()) && periodRatingDTO.getPriceChange() < -errorDelta;
		boolean isBearAndPos = periodRatingDTO.getTrend().equals(EventType.BEARISH.name()) && periodRatingDTO.getPriceChange() > errorDelta;
		boolean isPeriodSignificant = Math.abs(periodRatingDTO.getPriceChange()) >  Math.abs(totalPriceChange*ratioToTotPriceChange);

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
		
		FinalRating finalRating = tuningFinalizationRating(tuningRes.getProfit(), tuningRes.getStockPriceChange(), nbSuccess, nbFailure);
		
		//Test 
		//finalRating.validity = Validity.SUCCESS;
		
		return finalRating;
	}


}
