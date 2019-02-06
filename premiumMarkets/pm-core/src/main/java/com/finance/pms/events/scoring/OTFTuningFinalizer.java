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
import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Observer;
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
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.dto.PeriodRatingDTO;
import com.finance.pms.events.scoring.dto.TuningResDTO;

@Service("tuningFinalizer")
public class OTFTuningFinalizer {

	private static MyLogger LOGGER = MyLogger.getLogger(OTFTuningFinalizer.class);

	public TuningResDTO buildTuningRes(Date startDate, Date endDate, Stock stock, String analyseName, EventInfo evtDef, SortedMap<EventKey, EventValue> evtDefEvents, Observer observer) throws NotEnoughDataException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
		String noResMsg = "No estimate is available for "+stock.getName()+" between "+dateFormat.format(startDate)+ " and "+ dateFormat.format(endDate)+" with "+evtDef+".\n";
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
			if (eventsValues.isEmpty()) throw new NotEnoughDataException(stock, startDate, endDate, "", new RuntimeException());

			//Build res
			return buildTuningRes(stock, startDate, endDate, analyseName, eventsValues, noResMsg, evtDef.info(), observer);


		} catch (NoQuotationsException e) {
			LOGGER.warn(noResMsg, e);
			throw new NotEnoughDataException(stock, noResMsg , e);
		} catch (NotEnoughDataException e) {
			LOGGER.warn(noResMsg);
			throw e;
		} catch (Exception e) {
			LOGGER.error(noResMsg, e);
			throw new NotEnoughDataException(stock, noResMsg, e);
		}

	}

	protected List<PeriodRatingDTO> validPeriods(
			Quotations quotations,
			Stock stock, Date startDate, Date endDate, 
			Collection<EventValue> generatedEvents, String noResMsg) throws NotEnoughDataException, InvalidAlgorithmParameterException {

		List<PeriodRatingDTO> periods = new ArrayList<PeriodRatingDTO>();

		PeriodRatingDTO period = null;
		for (EventValue eventValue: generatedEvents) {

			Date eventDate = eventValue.getDate();

			//Ignore events after endDate if it starts a new period
			if (eventDate.compareTo(endDate) > 0 && (period == null || period.isRealised())) {
				break;
			}

			//Construct period
			if (period != null && !eventDate.after(period.getFrom())) throw new RuntimeException();//Check erroneous input with events on the same date

			if (eventValue.getEventType().equals(EventType.BULLISH)) {
				if (period != null && period.getTrend().equals(EventType.BEARISH.name())) {
					BigDecimal closestCloseForDate = quotations.getClosestCloseSpForDate(eventDate);
					period.setTo(eventDate);
					period.setPriceAtTo(closestCloseForDate.doubleValue());
					period.setRealised(true);
					//addFilteredPeriod(periods, period, -1);
					periods.add(period);
					period = new PeriodRatingDTO(eventDate, closestCloseForDate.doubleValue(), EventType.BULLISH.name());
				}
				if (period == null) {
					period = new PeriodRatingDTO(eventDate, quotations.getClosestCloseSpForDate(eventDate).doubleValue(), EventType.BULLISH.name()); 
				}

			}
			if (eventValue.getEventType().equals(EventType.BEARISH)) {
				if (period != null && period.getTrend().equals(EventType.BULLISH.name())) {
					BigDecimal closestCloseForDate = quotations.getClosestCloseSpForDate(eventDate);
					period.setTo(eventDate);
					//addFilteredPeriod(periods, period, -1);
					periods.add(period);
					period.setPriceAtTo(closestCloseForDate.doubleValue());
					period.setRealised(true);
					period = new PeriodRatingDTO(eventDate, closestCloseForDate.doubleValue(), EventType.BEARISH.name()); 
				}
				if (period == null) {
					period = new PeriodRatingDTO(eventDate, quotations.getClosestCloseSpForDate(eventDate).doubleValue(), EventType.BEARISH.name()); 
				}
			}
		}

		//Finalising unclosed last period
		if (period != null) {
			//Ends in Bullish we suppose we sell (in order to compete with the compound)
			if (period.getTrend().equals(EventType.BULLISH.name())) {
				QuotationUnit quotationUnit = quotations.get(quotations.getClosestIndexBeforeOrAtDateOrIndexZero(0, endDate));
				//if (!quotationUnit.getDate().after(period.getFrom())) throw new RuntimeException();
				period.setTo(quotationUnit.getDate());
				period.setPriceAtTo(quotationUnit.getCloseSplit().doubleValue());
				period.setRealised(true);
			}
			//Ends with Bearish we don't know but we need to close the trend
			if (period.getTrend().equals(EventType.BEARISH.name())) {
				QuotationUnit quotationUnit = quotations.get(quotations.getClosestIndexBeforeOrAtDateOrIndexZero(0, endDate));
				//if (!quotationUnit.getDate().after(period.getFrom())) throw new RuntimeException();
				period.setTo(quotationUnit.getDate());
				period.setPriceAtTo(quotationUnit.getCloseSplit().doubleValue());
				period.setRealised(false);
			}
			//addFilteredPeriod(periods, period, -1);
			periods.add(period);
		}

		//Removing heading periods up to the first sell before or at start
		int i = 0;
		while(i < periods.size()) {
			if(periods.get(i).getTo().compareTo(startDate) >= 0) {
				break;
			}
			i++;
		}

		if (periods.isEmpty()) {
			LOGGER.warn(String.format(
					"No buy/sell movement was triggered for %s, %s, %s : %s",
					stock, quotations.get(0).getDate(), quotations.get(quotations.getLastDateIdx()).getDate(), noResMsg));
			return periods;
		} else {
			return new ArrayList<>(periods.subList(i, periods.size()));
		}

	}

	/**
	 * @return TuningResDTO    followProfit by adding the valid bullish periods (ie excluding a starting bullish) up to the end of the calculation if last trend is bullish
                               stopLossProfit from the first period (will be a bearish as above) to the end of calculations
	 * @throws IOException
	 * @throws InvalidAlgorithmParameterException
	 */
	TuningResDTO buildResOnValidPeriods(
			List<PeriodRatingDTO> periods, SortedMap<Date, Number> qMap, Quotations quotations,
			Stock stock, Date startDate, Date endDate, String analyseName, 
			String evtDefInfo, Observer observer) 
					throws IOException, InvalidAlgorithmParameterException {

		String csvFile = "noOutputAvailable";
		String chartFile = "noChartAvailable";

		Double trendFollowProfit = 1.00;

		//Other init
		BigDecimal lastClose = (BigDecimal) qMap.get(qMap.lastKey());

		int lastRealisedBullIdx = -1;
		for (PeriodRatingDTO currentPeriod : periods) {

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

		//Finalise profits
		trendFollowProfit = trendFollowProfit - 1;

		if (!periods.isEmpty()) {

			PeriodRatingDTO firstPeriod = periods.get(0);
			PeriodRatingDTO lastPeriod = periods.get(periods.size()-1);

			if (lastRealisedBullIdx != -1) {
				Date firstBullFrom = firstPeriod.getFrom();
				BigDecimal firstBullStartPrice = quotations.getClosestCloseSpForDate(firstBullFrom);
				PeriodRatingDTO lastBullPeriod = periods.get(lastRealisedBullIdx);
				Date lastBullTo = lastBullPeriod.getTo();
				BigDecimal lastBullStartPrice = quotations.getClosestCloseSpForDate(lastBullTo);
				LOGGER.info("Trend following compounded profit calculation is first Close "+firstBullStartPrice+" at "+firstBullFrom+" and last Close "+lastBullStartPrice+" at "+lastBullTo+" : "+trendFollowProfit);
			} else {
				LOGGER.info("Trend following profit calculation is unknown (No bullish periods were detected or no trend change detected)");
			}

			//Buy and hold profit
			BigDecimal firstClose = quotations.getClosestCloseSpForDate(firstPeriod.getFrom());
			Double buyAndHoldProfit = (firstClose.compareTo(BigDecimal.ZERO) != 0)?lastClose.subtract(firstClose).divide(firstClose,10, BigDecimal.ROUND_HALF_EVEN).doubleValue():Double.NaN;
			LOGGER.info("Buy and hold profit calculation is first Close "+firstClose+" at "+firstPeriod.getFrom()+" and last Close "+lastClose+" at "+endDate+" : ("+lastClose+"-"+firstClose+")/"+firstClose+"="+buyAndHoldProfit);

			return new TuningResDTO(periods, csvFile, chartFile, lastPeriod.getTrend(), trendFollowProfit, Double.NaN, buyAndHoldProfit, startDate, endDate);
		}

		LOGGER.info("No events detected or no buy sell was realized over the selected date range.");
		return new TuningResDTO(periods, csvFile, chartFile, EventType.NONE.toString(), Double.NaN, Double.NaN, Double.NaN, startDate, endDate);

	}

	public TuningResDTO buildTuningRes(
			Stock stock, Date startDate, Date endDate, String analyseName,
			Collection<EventValue> eventListForEvtDef, String noResMsg, String evtDefInfo, Observer observer) 
					throws IOException, NoQuotationsException, NotEnoughDataException, InvalidAlgorithmParameterException {

		LOGGER.info("Building Tuning res for "+stock.getFriendlyName()+" and "+evtDefInfo+" between "+startDate+" and "+ endDate);

		Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, startDate, endDate, true, stock.getMarketValuation().getCurrency(), 1, ValidityFilter.CLOSE);
		SortedMap<Date, Number> mapFromQuotationsClose = QuotationsFactories.getFactory().buildExactBMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size()-1);
		LOGGER.info("Quotations map for "+stock.getFriendlyName()+" ranges from "+mapFromQuotationsClose.firstKey()+" to "+mapFromQuotationsClose.lastKey()+" while requested from "+startDate+" to "+endDate);

		List<PeriodRatingDTO> periods = validPeriods(quotations, stock, startDate, endDate, eventListForEvtDef, noResMsg);

		TuningResDTO buildResOnValidPeriods = buildResOnValidPeriods(periods, mapFromQuotationsClose, quotations, stock, startDate, endDate, analyseName, evtDefInfo, observer);

		LOGGER.info(export(buildResOnValidPeriods));

		return buildResOnValidPeriods;

	}

	private String export(TuningResDTO buildResOnValidPeriods) {
		String print = "No coumpound available. Empty results";
		List<PeriodRatingDTO> periods = buildResOnValidPeriods.getPeriods();
		if (!periods.isEmpty()) {
			print = "\nbuy date, buy price, sell date, sell price, price Rate Of Change\n";
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			for (PeriodRatingDTO period : periods) {
				if (period.getTrend().equals("BULLISH")) {
					print = print + dateFormat.format(period.getFrom()) + "," + period.getPriceAtFrom() + ",";
					if (period.isRealised()) {
						print = print + dateFormat.format(period.getTo())+ "," + period.getPriceAtTo() + ",";
					}
					print = print + period.getPriceRateOfChange() + "\n";
				}
			}
			PeriodRatingDTO firstP = periods.get(0);
			PeriodRatingDTO lastP = periods.get(periods.size() - 1);
			print = print + "\nb&h:\n" +
					dateFormat.format(firstP.getFrom()) + "," + firstP.getPriceAtFrom() + "," + 
					dateFormat.format(lastP.getTo()) + "," + lastP.getPriceAtTo() + "," + buildResOnValidPeriods.getStockPriceChange();
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
			ret.cause += "Negative profit under performs share price change. ";
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
