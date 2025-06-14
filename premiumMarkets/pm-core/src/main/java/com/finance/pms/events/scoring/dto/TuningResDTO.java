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
package com.finance.pms.events.scoring.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TuningResDTO implements Serializable, IsSerializable {

	private static final long serialVersionUID = 6537421829385394184L;

	private String csvLink;
	private String chartLink;

	private String configRatingFile;
	private String footNote;

	private List<PeriodRatingDTO> periods;
	private Date calculationStart;
	private Double calculationStartPrice;
	private Date calculationEnd;
	private Double calculationEndPrice;

	public TuningResDTO() {

		periods = new ArrayList<PeriodRatingDTO>();
		csvLink = "NA";
		chartLink = "NA";

		configRatingFile = "";
		footNote = "";

		calculationStartPrice = 0.0;
		calculationStart = new Date(0);
		calculationEndPrice = 0.0;
		calculationEnd = new Date(0);

	}

	public TuningResDTO(List<PeriodRatingDTO> periods, String csvLink, String chartLink, Double startPrice, Double endPrice, Date calculatedStart, Date calculatedEnd) {
		super();
		this.calculationStart = calculatedStart;
		this.calculationStartPrice = startPrice;
		this.calculationEnd = calculatedEnd;
		this.calculationEndPrice = endPrice;
		this.periods = periods;
		this.csvLink = csvLink;
		this.chartLink = chartLink;
		this.footNote = toString();
	}
	
	public void resetStart(List<PeriodRatingDTO> periods, Date calculationStart, Double calculationStartPrice) {
		this.periods = periods;
		this.calculationStart = calculationStart;
		this.calculationStartPrice = calculationStartPrice;
	}

	public Double getBuyNHoldProfit() {
		return calculationEndPrice/calculationStartPrice -1;
	}
	
	public Double getBNHAnnualisedProfit() {
		double cummulativeReturn = getBuyNHoldProfit();
		return annualisedProfit(calculationEnd, cummulativeReturn);
	}

	public Double annualisedProfit(Date date, double cummulativeReturn) {
		double nbDays = TimeUnit.DAYS.convert(date.getTime() - calculationStart.getTime(), TimeUnit.MILLISECONDS);
		if (nbDays == 0) return 0.0;
		double annualReturn = Math.pow(1 + cummulativeReturn, 365d/nbDays) - 1;
		return annualReturn;
	}

	public Double getForecastProfit() {
		return getForecastProfitAt(calculationEnd);
	}
	
	public Double getForeAnnualProfit() {
		return getForeAnnualProfitAt(calculationEnd);
	}
	
	public Double getForeAnnualProfitAt(Date date) {
		double cummulativeReturn = getForecastProfitAt(date);
		return annualisedProfit(date, cummulativeReturn);
	}
	
	public Double getForecastProfitUnReal() {
		return getForecastProfitAtUnReal(calculationEnd);
	}
	
	public Double getForeAnnualProfitUnReal() {
		return getForeAnnualProfitAtUnReal(calculationEnd);
	}
	
	public Double getForeReinvested() {
		return getForecastReinvestAt(calculationEnd);
	}
	
	public Double getForeAnnualProfitAtUnReal(Date date) {
		double cummulativeReturn = getForecastProfitAtUnReal(date);
		return annualisedProfit(date, cummulativeReturn);
	}
	
	public Map<String, Double> getBullStats() {
		return getStatsBetween(calculationStart, calculationEnd, "BULLISH", (x) -> x < 0);
	}
	
	public Map<String, Double> getBearStats() {
		return getStatsBetween(calculationStart, calculationEnd, "BEARISH", (x) -> x > 0);
	}

	public List<PeriodRatingDTO> getPeriods() {
		return periods;
	}
	
	public List<PeriodRatingDTO> getBullPeriods() {
		return periods.stream().filter(p -> "BULLISH".equals(p.getTrend())).collect(Collectors.toList());
	}
	
	public List<PeriodRatingDTO> getBearPeriods() {
		return periods.stream().filter(p -> "BEARISH".equals(p.getTrend())).collect(Collectors.toList());
	}

	public String getCsvLink() {
		return csvLink;
	}

	public String getLastTrend() {
		return (periods.isEmpty())?"NONE":periods.get(periods.size()-1).getTrend(); //We can't use Enumerations in a DTO
	}
	
	public PeriodRatingDTO getLastPeriod() {
		return (periods.isEmpty())?null:periods.get(periods.size()-1);
	}

	public String getConfigRatingFile() {
		return configRatingFile;
	}

	public void setConfigRatingFile(String configRatingFile) {
		this.configRatingFile = configRatingFile;
	}

	public String getFootNote() {
		return footNote;
	}

	public void setFootNote(String footNote) {
		this.footNote = footNote;
	}

	public Boolean isInitialized() {
		return this.periods != null;
	}

	public Boolean hasCvsLink() {
		return !this.csvLink.equals("noOutputAvailable") && !this.csvLink.equals("NA");
	}

	@Override
	public String toString() {
		return "TuningResDTO [periods=" + periods + ", csvLink=" + csvLink + ", chartLink=" + chartLink
				+ ", configRatingFile=" + configRatingFile + ", footNote="
				+ footNote + ", calculatedStart=" + calculationStart + ", calculatedStartPrice=" + calculationStartPrice
				+ ", calculatedEnd=" + calculationEnd + ", calculatedEndPrice=" + calculationEndPrice + "]";
	}

	public Date getCalculatedStart() {
		return calculationStart;
	}

	public void setCalculatedStart(Date calculatedStart) {
		this.calculationStart = calculatedStart;
	}

	public Date getCalculatedEnd() {
		return calculationEnd;
	}

	public void setCalculatedEnd(Date calculatedEnd) {
		this.calculationEnd = calculatedEnd;
	}

	public String getChartLink() {
		return chartLink;
	}

	public void setChartLink(String chartLink) {
		this.chartLink = chartLink;
	}

	/**
	 * Flog will be = Math.log(Math.abs(failureWeigh)/successWeigh) </br>
	 *  	=> Math.log(Math.abs(failedTotalROC)/successTotalROC) </br>
	 * 		=> log(|failedRoc|/(totalROC-failedRoc)) </br>
	 * ROC is the actual rate of change of a predicted bullish period
	 * The weigh here is also out of the ROCs of the bullish periods
	 * @param trendInScope 
	 * @param trendInScopeInvSignFunc 
	 * 
	 * @return avgROC, failureRatio, failureWeigh, successWeigh, minROC, maxROC, varianceOfROC ...
	 */
	public Map<String, Double> getStatsBetween(Date from, Date to, String trendInScope, Function<Double, Boolean> trendInScopeInvSignFunc) {
		
		Double totalROC = 0d;
		Double failedTotalROC = 0d;
		double nbTrendPeriods = 0;
		double nbFailedTrendPeriod = 0;
		double maxROC = 0;
		double minROC = 0;
		double duration = 0;
		double maxDuration = Double.NEGATIVE_INFINITY;
		double minDuration = Double.POSITIVE_INFINITY;
		Iterator<PeriodRatingDTO> iterator = periods.iterator();
		PeriodRatingDTO currentPeriod = null; 
		while (iterator.hasNext() && (currentPeriod = iterator.next()).getTo().compareTo(to) <= 0) {
			if (currentPeriod.getFrom().compareTo(from) < 0) continue;
			if (trendInScope.equals(currentPeriod.getTrend())) {//We can't use Enums in a DTO //eg. trendInScope == "BULLISH"
				Double trendPeriodRateOfChange = currentPeriod.getPriceRateOfChange();
				if (!trendPeriodRateOfChange.isNaN() && !trendPeriodRateOfChange.isInfinite()) {
//					System.out.println(String.format("roc: %s at %s", bullishPeriodRateOfChange, currentPeriod.getTo()));
//					System.out.println(String.format("totalROC: %s", totalROC));
					nbTrendPeriods++;
					totalROC = totalROC + trendPeriodRateOfChange;
					int periodDuration = (int)( (currentPeriod.getTo().getTime() - currentPeriod.getFrom().getTime()) / (1000 * 60 * 60 * 24) );
					duration = duration + periodDuration;
					if (trendInScopeInvSignFunc.apply(trendPeriodRateOfChange)) { //eg trendPeriodRateOfChange < 0
						nbFailedTrendPeriod++;
						failedTotalROC = failedTotalROC + trendPeriodRateOfChange; //failedTotalROC is negative
					}
					if (trendPeriodRateOfChange > maxROC) {
						maxROC = trendPeriodRateOfChange;
					}
					if (trendPeriodRateOfChange < minROC) {
						minROC = trendPeriodRateOfChange;
					}
					if (currentPeriod.isRealised() && periodDuration > maxDuration) {
						maxDuration = periodDuration;
					}
					if (currentPeriod.isRealised() && periodDuration < minDuration) {
						minDuration = periodDuration;
					}
				};
			}
		}
//		System.out.println(String.format("totalROC: %s", totalROC));
		Double avgROC = totalROC / nbTrendPeriods;
		Double avgDuration = duration / nbTrendPeriods;
		Double failureRatio = nbFailedTrendPeriod / nbTrendPeriods;
		 
		double successTotalROC = totalROC - failedTotalROC; //totalROC does accumulate success and failure (ie total = success + failure) and failure is negative in case of BULLISH
		double totalSpreadOfROC = Math.abs(successTotalROC) + Math.abs(failedTotalROC); //failedTotalROC is negative in case of BULLISH
//		System.out.println(String.format("Total span: %s", totalSpan));
//		System.out.println(String.format("failedTotalROC: %s", failedTotalROC));
//		System.out.println(String.format("successTotalROC: %s", successTotalROC));
		Double failureWeigh = failedTotalROC / totalSpreadOfROC;
		Double successWeigh = successTotalROC / totalSpreadOfROC;
		
		Double varianceOfROC = 0d;
		Double varianceDuration = 0d;
		Iterator<PeriodRatingDTO> iterator2 = periods.iterator();
		while (iterator2.hasNext() && (currentPeriod = iterator2.next()).getTo().compareTo(to) <= 0) {
			if (currentPeriod.getFrom().compareTo(from) < 0) continue;
			if (trendInScope.equals(currentPeriod.getTrend())) {
				Double trendPeriodRateOfChange = currentPeriod.getPriceRateOfChange();
				if (!trendPeriodRateOfChange.isNaN() && !trendPeriodRateOfChange.isInfinite()) {
					varianceOfROC = varianceOfROC + (trendPeriodRateOfChange - avgROC)*(trendPeriodRateOfChange - avgROC);
					int periodDuration2 = (int)( (currentPeriod.getTo().getTime() - currentPeriod.getFrom().getTime()) / (1000 * 60 * 60 * 24) );
					int trendPeriodDuration = periodDuration2;
					varianceDuration = varianceDuration + (trendPeriodDuration - avgDuration)*(trendPeriodDuration - avgDuration);
				}
			}
		}
		varianceOfROC = varianceOfROC / nbTrendPeriods;
		varianceDuration = varianceDuration / nbTrendPeriods;
		
		
		Map<String, Double> results = new HashMap<>();
		//Bullish stats: avgROC, failureRatio, failureWeigh, successWeigh, minROC, maxROC, varianceOfROC
		results.put("avgROC", avgROC);
		results.put("failureRatio", failureRatio);
		results.put("failureWeigh", failureWeigh);
		results.put("successWeigh", successWeigh);
		results.put("minROC", minROC);
		results.put("maxROC", maxROC);
		results.put("varianceOfROC", varianceOfROC);
		results.put("avgDuration", avgDuration);
		results.put("varianceDuration", varianceDuration);
		results.put("minDuration", minDuration);
		results.put("maxDuration", maxDuration);
		//return new Double[]{avgROC, failureRatio, failureWeigh, successWeigh, minROC, maxROC, varianceROC};
		return results;
	}

	private Double getForecastProfitAt(Date to, boolean unReal) {
		return getForecastProfitBetween(calculationStart, to, unReal);
	}
	
	public Double getForecastProfitAt(Date date) {
		return getForecastProfitAt(date, false);
	}
	
	public Double getForecastProfitAtUnReal(Date date) {
		return getForecastProfitAt(date, true);
	}
	
	public Double getForecastReinvestAt(Date date) {
		Double capital = 0.0;
		Iterator<PeriodRatingDTO> iterator = periods.iterator();
		PeriodRatingDTO currentPeriod = null;
		Double initialCapital = 0.0;
		while (iterator.hasNext() && (currentPeriod = iterator.next()).getTo().compareTo(date) <= 0) {
			if (currentPeriod.isRealised() && "BULLISH".equals(currentPeriod.getTrend())) {	//End of bullish (exclusive).
				if (initialCapital == 0.0) {
					initialCapital = currentPeriod.getPriceAtFrom();
					capital = initialCapital;
				}
				Double priceRateOfChange = currentPeriod.getPriceRateOfChange();
				if (priceRateOfChange.isNaN() || priceRateOfChange.isInfinite()) return Double.NaN;
				capital = capital * (1 + priceRateOfChange);
			}
		}
		//!iterator.hasNext() this is the last period or currentPeriod.getTo().compareTo(date) > 0
		if (currentPeriod != null) {
			if ("BULLISH".equals(currentPeriod.getTrend())) {
				Double endPrice = 0.0;
				if (currentPeriod.isRealised() && currentPeriod.getTo().compareTo(date) <= 0) {
					endPrice = currentPeriod.getPriceAtTo();
				} else {
					endPrice = calculationEndPrice;
				}
				Double priceRateOfChange = endPrice/currentPeriod.getPriceAtFrom() -1;
				if (priceRateOfChange.isNaN() || priceRateOfChange.isInfinite()) return Double.NaN;
				capital = capital * (1 + priceRateOfChange);
			}
		}
		return capital/initialCapital - 1;
	}
	
	/**
	 * [from, to]
	 * @param from
	 * @param to
	 * @return
	 */
	private Double getForecastProfitBetween(Date from, Date to, Boolean unReal) {
		Double trendFollowProfit = 1.00;
		Iterator<PeriodRatingDTO> iterator = periods.iterator();
		PeriodRatingDTO currentPeriod = null;
		while (iterator.hasNext() && (currentPeriod = iterator.next()).getTo().compareTo(to) <= 0) {
			if (currentPeriod.getFrom().compareTo(from) < 0) continue;
			if (currentPeriod.isRealised() && "BULLISH".equals(currentPeriod.getTrend())) {	//End of bullish (exclusive).
				Double followPriceRateOfChange = currentPeriod.getPriceRateOfChange();
				if (followPriceRateOfChange.isNaN() || followPriceRateOfChange.isInfinite()) return Double.NaN;
				trendFollowProfit = trendFollowProfit * (1 + followPriceRateOfChange);
			}
		}
		//(!iterator.hasNext() and this is the last period) or (currentPeriod.getTo().compareTo(date) > 0)
		if (unReal  && currentPeriod != null && !(currentPeriod.getFrom().compareTo(from) < 0)) { //Adding last unrealised if bullish
			if ("BULLISH".equals(currentPeriod.getTrend())) {
				Double endPrice = 0.0;
				if (currentPeriod.isRealised() && currentPeriod.getTo().compareTo(to) <= 0) {
					endPrice = currentPeriod.getPriceAtTo();
				} else {
					endPrice = calculationEndPrice;
				}
				Double followPriceRateOfChange = endPrice/currentPeriod.getPriceAtFrom() -1;
				if (followPriceRateOfChange.isNaN() || followPriceRateOfChange.isInfinite()) return Double.NaN;
				trendFollowProfit = trendFollowProfit * (1 + followPriceRateOfChange);
			}
		}
		return trendFollowProfit - 1;
	}
	
	public Double getForecastProfitBetween(Date from, Date to) {
		return getForecastProfitBetween(from, to, false);
	}
	
	public Double getForecastProfitUnRealBetween(Date from, Date to) {
		return getForecastProfitBetween(from, to, true);
	}
	

	public Double getPriceChangeAtPeriodEnding(Date periodToDate) {
		Iterator<PeriodRatingDTO> iterator = periods.iterator();
		PeriodRatingDTO currentPeriod = null;
		while (iterator.hasNext()) {
			currentPeriod = iterator.next();
			if (currentPeriod.getTo().compareTo(periodToDate) == 0) break;
		}
		if (currentPeriod == null || !(currentPeriod.getTo().compareTo(periodToDate) == 0)) return Double.NaN;
		return currentPeriod.getPriceAtTo()/calculationStartPrice -1;
	}

	public Double getCalculationStartPrice() {
		return calculationStartPrice;
	}

	public Double getCalculationEndPrice() {
		return calculationEndPrice;
	}

}
