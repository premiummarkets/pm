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
import java.util.Set;
import java.util.SortedSet;

import org.eclipse.swt.graphics.Color;

import com.finance.pms.alerts.AlertOnEvent;
import com.finance.pms.alerts.AlertOnThreshold;
import com.finance.pms.datasources.files.Transaction;
import com.finance.pms.datasources.files.TransactionElement;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.portfolio.InOutWeighted;
import com.finance.pms.portfolio.InfoObject;
import com.finance.pms.portfolio.InvalidQuantityException;
import com.finance.pms.portfolio.MonitorLevel;
import com.finance.pms.portfolio.PortfolioShare;

public class SlidingPortfolioShare extends PortfolioShare implements InfoObject { 

	private static final long serialVersionUID = 7701345524631769605L;

	private PortfolioShare underLyingPortfolioShare;

	private Boolean displayOnChart;
	private Boolean isChartTransactions;
	private Color color;
	private Date start;
	private Date end;

	private Currency displayedCurrency;


	public SlidingPortfolioShare(PortfolioShare portfolioShare, Date start, Date end, Color color, Boolean isLatestOnly) {
		super(portfolioShare);
		this.underLyingPortfolioShare = portfolioShare;
		this.start = start;
		this.end = end;
		this.color = color;
		this.displayOnChart = isOwned(end, isLatestOnly);
		this.isChartTransactions = false;

		this.displayedCurrency = portfolioShare.getTransactionCurrency();
	}

	public BigDecimal getTodaysCashin(Boolean isLatestOnly) {
		return super.getCashin(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency, isLatestOnly);
	}

	public BigDecimal getTodaysCashout(Boolean isLatestOnly) {
		return super.getCashout(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency, isLatestOnly);
	}

	public InOutWeighted getTodaysWeightedInvested(Boolean isLatestOnly) {
		return super.getInflatWeightedInvested(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency, isLatestOnly);
	}

	public BigDecimal getTodaysPriceClose(Boolean isLatestOnly) {
		return super.getPriceClose(calcSlidingEndDate(), displayedCurrency);
	}

	public BigDecimal getTodaysPotentialReturn() {
		return super.getGainRemaingPotentialReturn(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency);
	}
	
	public BigDecimal getTodaysGainRealisedPercent(Boolean isLatestOnly) {
		return super.getGainRealisedPercent(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency, isLatestOnly);
	}
	
	public BigDecimal getTodaysValue(Boolean isLatestOnly) {
		return super.getValue(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency, isLatestOnly);
	}

	public BigDecimal getTodaysGainTotalWeightedPercent(Boolean isLatestOnly) {
		return super.getGainTotalWeightedPercent(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency, isLatestOnly);
	}

	public BigDecimal getTodaysZeroPriceWeighted(Boolean isLatestOnly) {
		return super.getZeroPriceWeighted(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency, isLatestOnly);
	}

	public BigDecimal getTodaysGainTotalPercent(Boolean isLatestOnly) {
		return super.getGainTotalPercent(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency, isLatestOnly);
	}
	public BigDecimal getTodaysPriceUnitCost(Boolean isLatestOnly) {
		return super.getPriceUnitCost(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency, isLatestOnly);
	}

	public BigDecimal getTodaysPriceAvgBuy(Boolean isLatestOnly) {
		return super.getPriceAvgBuy(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency, isLatestOnly, false);
	}

	public BigDecimal getTodaysQuantity(Boolean isLatestOnly) {
		return super.getQuantity(calcSlidingStartDate(), calcSlidingEndDate(), isLatestOnly);
	}

	public BigDecimal getTodaysGainTotal(Boolean isLatestOnly) {
		return super.getGainTotal(calcSlidingStartDate(), calcSlidingEndDate(), displayedCurrency, isLatestOnly);
	}
	
	@Override
	public SortedSet<TransactionElement> getTransactions(Boolean isLatestOnly) {
		return getPortfolio().getTransactionsFor(this, calcSlidingStartDate(), calcSlidingEndDate(), isLatestOnly);
	}
	
	public BigDecimal getTodaysGainAnnualised(Boolean isLatestOnly) {
		SortedSet<TransactionElement> transactions = this.getTransactions(isLatestOnly);
		if (transactions.size() > 0) {
			Date firstTransactionDate = transactions.first().getDate();
			return super.getGainAnnualisedPercent(firstTransactionDate, calcSlidingEndDate(), displayedCurrency, isLatestOnly);
		} else {
			return BigDecimal.ZERO;
		}	
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

	@Override
	public Set<AlertOnThreshold> getAlertsOnThreshold() {
		return underLyingPortfolioShare.getAlertsOnThreshold();
	}

	@Override
	public Set<AlertOnEvent> getAlertsOnEvent() {
		return underLyingPortfolioShare.getAlertsOnEvent();
	}

	public Currency getDisplayedCurrency() {
		return displayedCurrency;
	}

	public void setDisplayedCurrency(Currency displayedCurrency) {
		this.displayedCurrency = displayedCurrency;
	}

	public Boolean isChartTransactions() {
		return isChartTransactions;
	}

	public void setIsChartTransactions(Boolean chartTransactions) {
		this.isChartTransactions = chartTransactions;
	}
	
	public Date calcSlidingEndDate() {
		return end;
	}

	private Date calcSlidingStartDate() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Color getColor() {
		return color;
	}

	
}
