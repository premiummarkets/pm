/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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
package com.finance.pms.events.scoring.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TuningResDTO implements Serializable {
	
	private static final long serialVersionUID = 6537421829385394184L;
	
	
	List<PeriodRatingDTO> periods;
	String csvLink;
	String chartLink;
	String lastTrend;
	Double profit;
	Double stockPriceChange;
	
	String configRatingFile;
	String footNote;
	
	Date calculatedStart;
	Date calculatedEnd;
	
	public TuningResDTO() {
		
		periods = new ArrayList<PeriodRatingDTO>();
		csvLink = "NA";
		chartLink = "NA";
		lastTrend = "NONE";
		profit = 0.0;
		stockPriceChange = 0.0;
		
		configRatingFile = "";
		footNote = "";
		
		calculatedStart = new Date(0);
		calculatedEnd = new Date(0);
	
	}

	public TuningResDTO(List<PeriodRatingDTO> periods, String csvLink, String chartLink, String lastTrend, BigDecimal profit, BigDecimal stockPriceChange, Date calculatedStart, Date calculatedEnd) {
		super();
		this.calculatedStart = calculatedStart;
		this.calculatedEnd = calculatedEnd;
		this.periods = periods;
		this.csvLink = csvLink;
		this.chartLink = chartLink;
		this.lastTrend = lastTrend;
		this.profit = profit.doubleValue();
		this.stockPriceChange = stockPriceChange.doubleValue();
	}
	
	public Double getStockPriceChange() {
		return stockPriceChange;
	}

	public Double getProfit() {
		return profit;
	}

	public List<PeriodRatingDTO> getPeriods() {
		return periods;
	}

	public String getCsvLink() {
		return csvLink;
	}	
	
	public String getLastTrend() {
		return lastTrend;
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
		return "TuningResDTO [periods=" + periods + ", " +
				"csvLink=" + csvLink + ", lastTrend=" + lastTrend + ", profit=" + profit + ", " +
				"stockPriceChange="+stockPriceChange + ", configRatingFile=" + configRatingFile + ", footNote=" + footNote + "]";
	}

	public Date getCalculatedStart() {
		return calculatedStart;
	}

	public void setCalculatedStart(Date calculatedStart) {
		this.calculatedStart = calculatedStart;
	}

	public Date getCalculatedEnd() {
		return calculatedEnd;
	}

	public void setCalculatedEnd(Date calculatedEnd) {
		this.calculatedEnd = calculatedEnd;
	}

	public String getChartLink() {
		return chartLink;
	}

	public void setChartLink(String chartLink) {
		this.chartLink = chartLink;
	}
	
	

}
