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
package com.finance.pms.portfolio.gui;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.eclipse.swt.graphics.Color;

import com.finance.pms.alerts.AlertOnEvent;
import com.finance.pms.alerts.AlertOnThreshold;
import com.finance.pms.datasources.files.Transaction;
import com.finance.pms.datasources.files.TransactionElement;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.portfolio.InOutWeighted;
import com.finance.pms.portfolio.InfoObject;
import com.finance.pms.portfolio.InvalidQuantityException;
import com.finance.pms.portfolio.MonitorLevel;
import com.finance.pms.portfolio.PortfolioShare;

public class SlidingPortfolioShare extends PortfolioShare implements InfoObject { 

	private static final long serialVersionUID = 7701345524631769605L;

	private PortfolioShare underLyingPortfolioShare;

	private Boolean displayOnChart;
	private Boolean chartTransactions;
	private Color color;

	private Boolean slidingEnd;
	private Boolean slidingStart;

	private Date start;
	private Date end;

	private Currency displayedCurrency;


	public SlidingPortfolioShare(PortfolioShare portfolioShare, Date start, Date end, Boolean slidingStart, Boolean slidingEnd, Color color) {
		super(portfolioShare);
		this.underLyingPortfolioShare = portfolioShare;
		this.start = start;
		this.end = end;
		this.slidingEnd = slidingEnd;
		this.slidingStart = slidingStart;
		this.color = color;
		this.displayOnChart = portfolioShare.getPriceAvgBuy(end, portfolioShare.getTransactionCurrency()).compareTo(BigDecimal.ZERO) > 0;
		this.chartTransactions = false;

		this.displayedCurrency = portfolioShare.getTransactionCurrency();
	}

	public BigDecimal getTodaysCashin() {
		return super.getCashin(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency);
	}

	public BigDecimal getTodaysCashout() {
		return super.getCashout(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency);
	}

	public InOutWeighted getTodaysWeightedInvested() {
		return super.getInflatWeightedInvested(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency);
	}

	public BigDecimal getTodaysPriceClose() {
		return super.getPriceClose(calcSlidingEndDate(), displayedCurrency);
	}

	public Optional<BigDecimal> getTodaysPotentialYield() {
		return super.getPotentialYield(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency);
	}

	public BigDecimal getGainTotalWeightedPercent() {
		return super.getInflatWeightedGainTotalPercent(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency);
	}

	public BigDecimal getTodaysValue() {
		return super.getValue(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency);
	}

	public BigDecimal getTodaysPriceZeroGainWeighted() {
		return super.getInflatWeightedZeroGainPrice(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency);
	}

	public Date calcSlidingEndDate() {
		Date currentDate;
		if (slidingEnd) {
			currentDate = end;
		} else {
			currentDate = DateFactory.getNowEndDate();
		}
		return currentDate;
	}

	private Date calcSlidingStartDate() {
		Date currentDate;
		if (slidingStart) {
			currentDate = start;
		} else {
			currentDate = DateFactory.dateAtZero();
		}
		return currentDate;
	}

	public void setSlidingEnd(Boolean sliding) {
		this.slidingEnd = sliding;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public void setSlidingStart(Boolean slidingStart) {
		this.slidingStart = slidingStart;
	}

	public Color getColor() {
		return color;
	}

	public BigDecimal getTodaysGainTotalPercent() {
		return super.getGainTotalPercent(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency);
	}

	@Override
	public TransactionElement createTransactionElement(Transaction transaction) throws InvalidQuantityException {
		TransactionElement appliedTransaction = underLyingPortfolioShare.createTransactionElement(transaction);
		super.createTransactionElement(transaction);
		return appliedTransaction;
	}

	@Override
	public void setMonitorLevel(MonitorLevel monitorLevel) {
		underLyingPortfolioShare.setMonitorLevel(monitorLevel);
		super.setMonitorLevel(monitorLevel);
	}

	@Override
	public String info() {
		return this.getStock().getFriendlyName();
	}

	public Boolean getDisplayOnChart() {
		return displayOnChart;
	}

	public void setDisplayOnChart(Boolean displayOnChart) {
		this.displayOnChart = displayOnChart;
	}

	public String getFriendlyName() {
		return underLyingPortfolioShare.getStock().getFriendlyName();
	}

	public PortfolioShare getUnderLyingPortfolioShare() {
		return underLyingPortfolioShare;
	}

	@Override
	public String toString() {
		return "SlidingPortfolioShare [underLyingPortfolioShare=" + underLyingPortfolioShare + ", displayOnChart=" + displayOnChart + "]";
	}

	@Override
	public String toolTip() {
		return "";
	}

	public Set<AlertOnThreshold> getAlertsOnThreshold() {
		return underLyingPortfolioShare.getAlertsOnThreshold();
	}

	public Set<AlertOnEvent> getAlertsOnEvent() {
		return underLyingPortfolioShare.getAlertsOnEvent();
	}

	public BigDecimal getTodaysPriceUnitCost() {
		return super.getPriceUnitCost(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency);
	}

	public BigDecimal getTodaysPriceAvgBuy() {
		return super.getPriceAvgBuy(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency);
	}

	public BigDecimal getTodaysQuantity() {
		return super.getQuantity(calcSlidingStartDate(), calcSlidingEndDate());
	}

	public BigDecimal getTodaysGainTotal() {
		return super.getGainTotal(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency);
	}

	public Currency getDisplayedCurrency() {
		return displayedCurrency;
	}

	public void setDisplayedCurrency(Currency displayedCurrency) {
		this.displayedCurrency = displayedCurrency;
	}

	public Boolean isChartTransactions() {
		return chartTransactions;
	}

	public void setChartTransactions(Boolean chartTransactions) {
		this.chartTransactions = chartTransactions;
	}

	
}
