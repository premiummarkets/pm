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


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Set;
import java.util.SortedMap;

import org.springframework.stereotype.Service;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.Validity;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.operations.conditional.EventInfoOpsCompoOperation;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.dto.PeriodRatingDTO;
import com.finance.pms.events.scoring.dto.TuningResDTO;

@Service("tuningFinalizer")
public class OTFTuningFinalizer {

	private static MyLogger LOGGER = MyLogger.getLogger(OTFTuningFinalizer.class);

	public TuningResDTO buildTuningRes(Date startDate, Date endDate, Stock stock, String analyseName, EventInfo evtDef, SortedMap<EventKey, EventValue> evtDefEvents, Observer observer) 
			throws NotEnoughDataException {
		
		LOGGER.info("Building Tuning res for " + stock.getFriendlyName() + " and " + evtDef.info() + " and analysis " + analyseName + " between " + startDate + " and " + endDate);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
		String noResMsg = "No estimate is available for " + stock.getName() + " between " + dateFormat.format(startDate) + " and " + dateFormat.format(endDate) + " with " + evtDef + ".\n";
		try {

			//Grab calculated events and make sure they are ordered by date
			Collection<EventValue> eventsValues;
			if (evtDefEvents == null) {
				HashSet<EventInfo> eventDefinitions = new HashSet<EventInfo>();
				eventDefinitions.add(evtDef);
				SymbolEvents eventsCalculated = EventsResources.getInstance().crudReadEventsForStock(stock, startDate, endDate, eventDefinitions, analyseName);
				eventsValues = eventsCalculated.getDataResultMap().values();
			} else {
				eventsValues = evtDefEvents.values();
			}
			if (eventsValues.isEmpty()) throw new NotEnoughDataException(stock, startDate, endDate, "For " + evtDef, new RuntimeException());

			//Build res
			Set<QuotationDataType> requieredStockDataTypes = new HashSet<>(Arrays.asList(QuotationDataType.CLOSE));
			if (evtDef instanceof EventInfoOpsCompoOperation) {
				requieredStockDataTypes = ((EventInfoOpsCompoOperation) evtDef).getRequiredStockData();
			}
			Quotations quotations = QuotationsFactories.getFactory().getSplitFreeQuotationsInstance(stock, startDate, endDate, true, stock.getMarketValuation().getCurrency(), 1, ValidityFilter.getFilterFor(requieredStockDataTypes));
			SortedMap<Date, Number> mapFromQuotationsClose = QuotationsFactories.getFactory().buildExactBMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size()-1);
			LOGGER.info("Quotations map for " + stock.getFriendlyName() + " ranges from " + mapFromQuotationsClose.firstKey() + " to " + mapFromQuotationsClose.lastKey() + " while requested from " + startDate + " to " + endDate);
			
			TuningResDTO buildTuningRes = buildTuningRes(stock, startDate, endDate, mapFromQuotationsClose, eventsValues);
			
			if (buildTuningRes.getPeriods().isEmpty()) {
				LOGGER.warn(String.format("No buy/sell movement was triggered for %s, %s, %s : %s", stock, startDate, endDate, noResMsg));
			}
			return buildTuningRes;


		} catch (NoQuotationsException e) {
			LOGGER.warn(noResMsg, e);
			throw new NotEnoughDataException(stock, noResMsg , e);
		} catch (NotEnoughDataException e) {
			LOGGER.warn(noResMsg, e);
			throw e;
		} catch (Exception e) {
			LOGGER.error(noResMsg, e);
			throw new NotEnoughDataException(stock, noResMsg, e);
		}

	}

	/**
	 * Period are closed only by a contradicting event.
	 * Only the last period should be left unrealised.
	 * Periods are from inclusive, to exclusive.
	 */
	protected List<PeriodRatingDTO> validPeriods(Stock stock, Date startDate, Date endDate, SortedMap<Date, ? extends Number> qMap, Collection<EventValue> generatedEvents) 
					throws NotEnoughDataException {

		qMap = MapUtils.subMapInclusive(qMap, startDate, endDate);
		List<PeriodRatingDTO> periods = new ArrayList<PeriodRatingDTO>();

		PeriodRatingDTO period = null;
		Date nextEvtDate = null;
		for (EventValue eventValue: generatedEvents) {
			
			nextEvtDate = eventValue.getDate();

			//Ignore events after endDate if it starts a new period
			if (nextEvtDate.compareTo(endDate) > 0) {
				throw new NotEnoughDataException(stock, startDate, endDate, "Event: " + eventValue + " is after " + endDate, new Exception());
			}
			
			//Ignore event before start date
			if (nextEvtDate.compareTo(startDate) < 0) {
				continue;
			}

			//Construct period
			if (period != null && !nextEvtDate.after(period.getFrom())) 
				throw new RuntimeException("Algorithm exception. Invalid event sorting or duplication: " + generatedEvents); //Check erroneous input with events on the same date

			//Double closeSpliterBeforeOrAtDate = quotations.getClosestCloseSpForDate(eventDate).doubleValue();
			SortedMap<Date, ? extends Number> tailFromEvent = qMap.tailMap(nextEvtDate);
			if (tailFromEvent.isEmpty()) {//No quotation available at or after the event
				LOGGER.warn("The las event: " + eventValue + " happens after the last quotation: " + qMap.lastKey() + ", with calculation bounds: " + startDate + "-" + endDate);
				break;
			}
			Double closeSpltAfterOrAtEventDate = new BigDecimal(tailFromEvent.get(tailFromEvent.firstKey()).toString()).doubleValue();
			if (eventValue.getEventType().equals(EventType.BULLISH)) {
				if (period == null) {//First period will be bull
					//TODO distinguish continuous form discrete events input??
					//period = new PeriodRatingDTO(nextEvtDate, closeSpltAfterOrAtEventDate, EventType.BULLISH.name()); //First event is bullish and in case of continuous events may not get the full bullish span: WE IGNORE.
				}
				else if (period.getTrend().equals(EventType.BEARISH.name())) { //A Bear period is open and the event is bull. We close the period.
					period.setTo(nextEvtDate);
					period.setPriceAtTo(closeSpltAfterOrAtEventDate);
					period.setRealised(true);
					periods.add(period);
					period = new PeriodRatingDTO(nextEvtDate, closeSpltAfterOrAtEventDate, EventType.BULLISH.name()); //Start new period.
				} 
				else if (period.getTrend().equals(EventType.BULLISH.name())) { //A bull period is open and the event is bull. We ignore the event.
					
				}
			}
			else if (eventValue.getEventType().equals(EventType.BEARISH)) {
				if (period == null) {//First period  will be bear
					period = new PeriodRatingDTO(nextEvtDate, closeSpltAfterOrAtEventDate, EventType.BEARISH.name());
				}
				else if (period.getTrend().equals(EventType.BULLISH.name())) { //A Bull period is open and the event is bear. We close the period.
					period.setTo(nextEvtDate);
					periods.add(period);
					period.setPriceAtTo(closeSpltAfterOrAtEventDate);
					period.setRealised(true);
					period = new PeriodRatingDTO(nextEvtDate, closeSpltAfterOrAtEventDate, EventType.BEARISH.name());//Start new period.
				} else if (period.getTrend().equals(EventType.BEARISH.name())) { //A bear period is open and the event is bear. We ignore the event.
					
				}
			}
		}

		//Finalising unclosed last period
		if (period != null && nextEvtDate != null) {
			
			//QuotationUnit quotationUnit = quotations.get(quotations.getClosestIndexBeforeOrAtDateOrIndexZero(0, endDate));
			Date qUnitDateAroundOrAtEndDate =  null;
			if (nextEvtDate.compareTo(startDate) < 0) { //End is before quotation map start? => bug? 
//				LOGGER.warn("Event at " + nextEvtDate + " is out side calculation/quotations boundaries. Last quotation: " + qMap.lastKey() + ", with calculation bounds: " + startDate + "-" + endDate);
//				qUnitDateAroundOrAtEndDate = qMap.firstKey();
				throw new NotEnoughDataException(stock, startDate, endDate, "Last event is at " + nextEvtDate + " is before start date " + startDate, new Exception());
			} else {//End date is within the available quotations map start //But the event could still be after the map end (interpolation)
				qUnitDateAroundOrAtEndDate = qMap.lastKey();
			}
			
			period.setTo(qUnitDateAroundOrAtEndDate);
			period.setPriceAtTo(qMap.get(qUnitDateAroundOrAtEndDate).doubleValue());
			period.setRealised(false);
			periods.add(period);
		}

		if (periods.isEmpty()) {
			return periods;
		} else {
			//Removing heading periods before start (out of range)
			int i = 0;
			while(i < periods.size()) {
				if (periods.get(i).getFrom().compareTo(startDate) >= 0) {
					break;
				}
				i++;
			}
			return new ArrayList<>(periods.subList(i, periods.size()));
		}

	}

	TuningResDTO buildResOnValidPeriods(List<PeriodRatingDTO> periods, SortedMap<Date, ? extends Number> qMap, Stock stock, Date startDate, Date endDate) {

		String csvFile = "noOutputAvailable";
		String chartFile = "noChartAvailable";

		//Other init
		SortedMap<Date, ? extends Number> subMapInclusive = MapUtils.subMapInclusive(qMap, startDate, endDate);
		BigDecimal firstClose = new BigDecimal(qMap.get(subMapInclusive.firstKey()).toString());
		BigDecimal lastClose = new BigDecimal(qMap.get(subMapInclusive.lastKey()).toString());

		return new TuningResDTO(periods, csvFile, chartFile, firstClose.doubleValue(), lastClose.doubleValue(), startDate, endDate);
	}

	public TuningResDTO buildTuningRes(
			Stock stock, Date startDate, Date endDate,
			SortedMap<Date, ? extends Number> mapFromQuotationsClose,
			Collection<EventValue> eventListForEvtDef) throws NoQuotationsException, NotEnoughDataException {
		
		List<PeriodRatingDTO> periods = validPeriods(stock, startDate, endDate, mapFromQuotationsClose, eventListForEvtDef);
		TuningResDTO buildResOnValidPeriods = buildResOnValidPeriods(periods, mapFromQuotationsClose, stock, startDate, endDate);
		if (LOGGER.isInfoEnabled()) LOGGER.info(export(buildResOnValidPeriods));

		return buildResOnValidPeriods;
	}

	private String export(TuningResDTO buildResOnValidPeriods) {
		String print = "No coumpound available. Empty results";
		List<PeriodRatingDTO> periods = buildResOnValidPeriods.getPeriods();
		if (!periods.isEmpty()) {
			print = "\n\nFollow:\n";
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			for (PeriodRatingDTO period : periods) {
				print = print + period.toCSV() + "," + period + "\n";
			}
			PeriodRatingDTO firstP = periods.get(0);
			PeriodRatingDTO lastP = periods.get(periods.size() - 1);
			print = print + "\nb&h:\n" +
					dateFormat.format(firstP.getFrom()) + "," + firstP.getPriceAtFrom() + "," + 
					dateFormat.format(lastP.getTo()) + "," + lastP.getPriceAtTo() + "," + buildResOnValidPeriods.getBuyNHoldProfit() +
					"\n";
		}
		return print;
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

		fileWriter.write("total, percentage gain (r + ur): " + tuningRes.getForecastProfitUnReal() + ", price change (ur): " + tuningRes.getBuyNHoldProfit() + "\n");
		tuningRes.setConfigRatingFile(fileName);

		fileWriter.write("rating , " + calculatedRating);
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

	public class FinalRating {
		
		//Legacy
		private Double rating;
		private int nbSuccess;
		private int nbFailure;
		
		//New (Flog)
		private double flog;
		private double failureWeightAbs;
		
		//Info
		private Double totForecastProfit;
		private Double totPrcChgUsed;

		//Resulting rating
		private Validity ratingValidityScore;
		private String cause;
		
		public FinalRating() {
			super();
			this.rating = 0d;
			this.nbSuccess = 0;
			this.nbFailure = 0;
			
			this.flog = 0;
			this.failureWeightAbs = 0d;
			
			this.totForecastProfit = 0d;
			this.totPrcChgUsed = 0d;
			
			this.ratingValidityScore = Validity.NORATING;
			this.cause = "No Rating could be calculated";
			
		}

		public FinalRating(Double totForecastProfit, Double totPrcChgUsed, int nbSuccess, int nbFailure) {
			super();
			this.totForecastProfit = totForecastProfit;
			this.totPrcChgUsed = totPrcChgUsed;
			this.nbSuccess = nbSuccess;
			this.nbFailure = nbFailure;
			this.cause = "";
		}

		public Double getRating() {
			return rating;
		}

		public Validity getRatingValidityScore() {
			return ratingValidityScore;
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
		
		public double getFlog() {
			return flog;
		}
		
		public double getFailureWeightAbs() {
			return failureWeightAbs;
		}

		@Override
		public String toString() {
			return "Failure: " + nbFailure + ", Success: " + nbSuccess + ", TotForecastProfit (unReal): " + totForecastProfit + ", TotPrcChgUsed: " + totPrcChgUsed + 
					", Legacy Rating: " + rating + ", Legacy Cause: " + cause +
					", ratingValidityScore: " + ratingValidityScore + ", failureWeightAbs: " + failureWeightAbs + ", flog: " + flog;

		}
		
		public String toCsv() {
			return nbFailure + "," + nbSuccess + "," + totForecastProfit + "," + totPrcChgUsed + "," + rating + "," + cause + "," + ratingValidityScore + "," + failureWeightAbs + "," + flog;
		}


//		+ " / avg" + pf.format(stats[0]) + " / fail" + pf.format(stats[1])
//		+ " / fwght" + pf.format(Math.abs(stats[2])) + " / flog" + pf.format(Math.log(Math.abs(stats[2])/stats[3]))
//		+ " / min" + pf.format(stats[4]) + " / max" + pf.format(stats[5]) + " / std" + pf.format(Math.sqrt(stats[6])) 
//		+ ")"
		public void applyFlog(Map<String, Double> statsBetween) {
			
//			avgROC = statsBetween[0];
//			failureRatio = statsBetween[1];
			Double failureWeigh = statsBetween.get("failureWeigh");
			Double successWeigh = statsBetween.get("successWeigh");
//			minROC = statsBetween[4];
//			maxROC = statsBetween[5];
//			variance = statsBetween[6];
			
			failureWeightAbs = Math.abs(failureWeigh);
			flog = Math.log(failureWeightAbs/successWeigh);
			
			if (failureWeightAbs >= .5 || flog >= -.5) {
				this.ratingValidityScore = Validity.FAILURE;
			} else {
				this.ratingValidityScore = Validity.SUCCESS;
			}
			
		}
		
		public void applyLegacyRating(Double totFollowProfit, Double totPriceChange) {

			rating = 0.0;
			if (nbFailure == 0 && nbSuccess == 0) {
				rating = 0.0;
			} else if (nbFailure == 0) {
				rating = Double.POSITIVE_INFINITY;
			} else {
				Double ln = Math.log(Double.valueOf(nbSuccess) / Double.valueOf(nbFailure));
				rating = ln;
			}

			double threshold = Math.log(100.00 / 75.00);

			if (totFollowProfit < 0 && totFollowProfit < totPriceChange) {
				cause += "Negative profit under performs share price change. ";
				ratingValidityScore = Validity.FAILURE;
				return;
			}

			if (totFollowProfit < 0) {
				cause += "Negative profit.";
				ratingValidityScore = Validity.FAILURE;
				return;
			}

			if (totFollowProfit > 0 && totFollowProfit >= totPriceChange) {
				cause += "Positive profit.";
				ratingValidityScore = Validity.SUCCESS;
				return;
			}

			if (rating < threshold) { // More failure than success it is very likely that profit will be sub zero
				cause = "Below Success/Failure threshold. ";
				ratingValidityScore = Validity.FAILURE;
				return;
			}

			if (rating >= threshold) {
				cause = "Above Success/Failure threshold. ";
				ratingValidityScore = Validity.SUCCESS;
				return;
			}

		}
		
	}

	public FinalRating calculateRating(TuningResDTO tuningRes, Date start, Date end) {
		
		if (tuningRes == null) return new FinalRating();

		int nbSuccess = 0;
		int nbFailure = 0;
		for (PeriodRatingDTO periodRatingDTO : tuningRes.getPeriods()) {
			Validity periodValidity = smoothedPeriodValidity(periodRatingDTO, tuningRes.getBuyNHoldProfit());

			//nbSuccess = nbSuccess + ((periodValidity.equals(Validity.SUCCESS))?1:0);
			if (periodRatingDTO.getTrend().equals(EventType.BULLISH.toString())) {//we tally only the bullish failures and success
				nbSuccess = nbSuccess + ((periodValidity.equals(Validity.SUCCESS))?1:0);
				nbFailure = nbFailure + ((periodValidity.equals(Validity.FAILURE))?1:0);
			}
		}
		
		FinalRating finalRating = new FinalRating(tuningRes.getForecastProfitUnReal(), tuningRes.getBuyNHoldProfit(), nbSuccess, nbFailure);
		
		//Legacy
		finalRating.applyLegacyRating(tuningRes.getForecastProfitUnReal(), tuningRes.getBuyNHoldProfit());
		
		//New
		//FinalRating finalRating = new FinalRating(nbSuccess, nbFailure, tuningRes.getStockPriceChange());
		Map<String, Double> statsBetween = tuningRes.getStatsBetween(start, end, "BULLISH", (x) -> x < 0);
		finalRating.applyFlog(statsBetween);

		return finalRating;
	}


}
