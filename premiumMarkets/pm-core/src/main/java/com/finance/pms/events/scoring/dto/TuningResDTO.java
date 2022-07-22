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
import java.util.Iterator;
import java.util.List;

public class TuningResDTO implements Serializable {

	private static final long serialVersionUID = 6537421829385394184L;

	private List<PeriodRatingDTO> periods;
	private String csvLink;
	private String chartLink;

	private String configRatingFile;
	private String footNote;

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

	public Double getStockPriceChange() {
		return calculationEndPrice/calculationStartPrice -1;
	}

	public Double getFollowProfit() {
		return getFollowProfitAt(calculationEnd);
	}
	
	/**
	 * {@link com.finance.pms.events.scoring.dto.TuningResDTO#getStatsBetween(Date,Date)}
	 */
	public Double[] getStats() {
		return getStatsBetween(calculationStart, calculationEnd);
	}

	public List<PeriodRatingDTO> getPeriods() {
		return periods;
	}

	public String getCsvLink() {
		return csvLink;
	}

	public String getLastTrend() {
		return (periods.isEmpty())?"NONE":periods.get(periods.size()-1).getTrend(); //We can't use Enumerations in a DTO
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
		return !this.csvLink.equals("noOutputAvailable");
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
	 * 
	 * flog = Math.log(Math.abs(failureWeigh)/successWeigh);
	 * 
	 * @param date
	 * @return avgROC, failedBullishRatio, failureWeigh, successWeigh, minROC, maxROC, variance
	 */
	public Double[] getStatsBetween(Date from, Date to) {
		
		Double totalROC = 0d;
		Double failedTotalROC = 0d;
		double nbBullishPeriods = 0;
		double nbFailedBullishPeriod = 0;
		double maxROC = 0;
		double minROC = 0;
		Iterator<PeriodRatingDTO> iterator = periods.iterator();
		PeriodRatingDTO currentPeriod = null; 
		while (iterator.hasNext() && (currentPeriod = iterator.next()).getTo().compareTo(to) <= 0) {
			if (currentPeriod.getFrom().compareTo(from) < 0) continue;
			if ("BULLISH".equals(currentPeriod.getTrend())) {//We can't use Enums in a DTO
				Double bullishPeriodRateOfChange = currentPeriod.getPriceRateOfChange();
				if (!bullishPeriodRateOfChange.isNaN() && !bullishPeriodRateOfChange.isInfinite()) {
					System.out.println(String.format("roc: %s at %s", bullishPeriodRateOfChange, currentPeriod.getTo()));
					System.out.println(String.format("totalROC: %s", totalROC));
					nbBullishPeriods++;
					totalROC = totalROC + bullishPeriodRateOfChange;
					if (bullishPeriodRateOfChange < 0) {
						nbFailedBullishPeriod++;
						failedTotalROC = failedTotalROC + bullishPeriodRateOfChange; //failedTotalROC is negative
					}
					if (bullishPeriodRateOfChange > maxROC) {
						maxROC = bullishPeriodRateOfChange;
					}
					if (bullishPeriodRateOfChange < minROC) {
						minROC = bullishPeriodRateOfChange;
					}
				};
			}
		}
		System.out.println(String.format("totalROC: %s", totalROC));
		Double avgROC = totalROC / nbBullishPeriods;
		Double failedBullishRatio = nbFailedBullishPeriod / nbBullishPeriods;
		
		double successTotalROC = totalROC - failedTotalROC;
		double totalSpan = successTotalROC + Math.abs(failedTotalROC); //failedTotalROC is negative
		System.out.println(String.format("Total span: %s", totalSpan));
		System.out.println(String.format("failedTotalROC: %s", failedTotalROC));
		System.out.println(String.format("successTotalROC: %s", successTotalROC));
		Double failureWeigh = failedTotalROC / totalSpan;
		Double successWeigh = successTotalROC / totalSpan;
		
		Double variance = 0d;
		Iterator<PeriodRatingDTO> iterator2 = periods.iterator();
		while (iterator2.hasNext() && (currentPeriod = iterator2.next()).getTo().compareTo(to) <= 0) {
			if (currentPeriod.getFrom().compareTo(from) < 0) continue;
			if ("BULLISH".equals(currentPeriod.getTrend())) {
				Double bullishPeriodRateOfChange = currentPeriod.getPriceRateOfChange();
				if (!bullishPeriodRateOfChange.isNaN() && !bullishPeriodRateOfChange.isInfinite()) {
					variance = variance + (bullishPeriodRateOfChange - avgROC)*(bullishPeriodRateOfChange - avgROC);
				}
			}
		}
		variance = variance / nbBullishPeriods;
		
		return new Double[]{avgROC, failedBullishRatio, failureWeigh, successWeigh, minROC, maxROC, variance};
	}

	public Double getFollowProfitAt(Date date) {
		Double trendFollowProfit = 1.00;
		Iterator<PeriodRatingDTO> iterator = periods.iterator();
		PeriodRatingDTO currentPeriod = null;
		while (iterator.hasNext() && (currentPeriod = iterator.next()).getTo().compareTo(date) <= 0) {
			if (currentPeriod.isRealised() && "BULLISH".equals(currentPeriod.getTrend())) {	//End of bullish (exclusive).
				Double followPriceRateOfChange = currentPeriod.getPriceRateOfChange();
				if (followPriceRateOfChange.isNaN() || followPriceRateOfChange.isInfinite()) return Double.NaN;
				trendFollowProfit = trendFollowProfit * (1 + followPriceRateOfChange);
			}
		}
		return trendFollowProfit - 1;
	}

	public Double getPriceChangeAt(Date periodToDate) {
		Iterator<PeriodRatingDTO> iterator = periods.iterator();
		PeriodRatingDTO currentPeriod = null;
		while (iterator.hasNext()) {
			currentPeriod = iterator.next();
			if (currentPeriod.getTo().compareTo(periodToDate) == 0) break;
		}
		if (currentPeriod == null || !(currentPeriod.getTo().compareTo(periodToDate) == 0)) return Double.NaN;
		return currentPeriod.getPriceAtTo()/calculationStartPrice -1;
	}

}
