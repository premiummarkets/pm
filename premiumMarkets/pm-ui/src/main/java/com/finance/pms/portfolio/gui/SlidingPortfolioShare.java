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
package com.finance.pms.portfolio.gui;

import java.math.BigDecimal;
import java.util.Date;

import org.eclipse.swt.graphics.Color;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.alerts.Alert;
import com.finance.pms.alerts.ThresholdType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.portfolio.InfoObject;
import com.finance.pms.portfolio.InvalidQuantityException;
import com.finance.pms.portfolio.MonitorLevel;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.Transaction;

public class SlidingPortfolioShare extends PortfolioShare implements InfoObject { 
	
	private static final long serialVersionUID = 7701345524631769605L;
	private static MyLogger LOGGER = MyLogger.getLogger(SlidingPortfolioShare.class);
	
	private PortfolioShare underLyingPortfolioShare;
	
	Boolean displayOnChart;
	Color color;
	
	Boolean slidingEnd;
	Boolean slidingStart;
	
	Date start;
	Date end;

	
	public SlidingPortfolioShare(PortfolioShare portfolioShare, Date start, Date end, Boolean slidingStart, Boolean slidingEnd, Color color) {
		super(portfolioShare);
		this.underLyingPortfolioShare = portfolioShare;
		this.start = start;
		this.end = end;
		this.slidingEnd = slidingEnd;
		this.slidingStart = slidingStart;
		this.color = color;
		displayOnChart = true;
	}

	public BigDecimal getCashin() {
		
		try {
			if (slidingStart) {
				Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(super.getStock(), start, true, super.getTransactionCurrency());
				BigDecimal startClosePrice = quotations.getCloseForDate(start);
				return super.getQuantity().multiply(startClosePrice).setScale(2, BigDecimal.ROUND_DOWN);
			} else {
				return super.getCashin();
			}
		} catch (Exception e) {
			LOGGER.warn(e,e);
			return BigDecimal.ZERO;
		} 
	}

	public BigDecimal getCashout() {
		
		if (slidingStart) {
			return BigDecimal.ZERO;
		} else {
			return super.getCashout();
		}
	}

	public InOutWeighted getWeightedInvested() {
		return super.getWeightedInvested(EventSignalConfig.getNewDate());
	}

	public BigDecimal getTodaysCloseQuotation() {
		return super.getCloseQuotationFor(calcCurrentDate());
	}
	
	public BigDecimal getUnrealizedProfit() {
		return super.getUnrealizedProfit(calcCurrentDate());
	}

	public BigDecimal getWeightedUnrealizedProfit() {
		return super.getWeightedUnrealizedProfit(EventSignalConfig.getNewDate());
	}

	public BigDecimal getTodaysValue() {
		return super.getValueForDate(calcCurrentDate());
	}

	public BigDecimal getPriceForZeroWeightedProfit() {
		return super.getPriceForZeroWeightedProfit(EventSignalConfig.getNewDate());
	}

	private Date calcCurrentDate() {
		Date currentDate;
		if (slidingEnd) {
			currentDate = end;
		} else {
			currentDate = EventSignalConfig.getNewDate();
		}
		return currentDate;
	}

	public Boolean getSlidingEnd() {
		return slidingEnd;
	}

	public void setSlidingEnd(Boolean sliding) {
		this.slidingEnd = sliding;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Boolean getSlidingStart() {
		return slidingStart;
	}

	public void setSlidingStart(Boolean slidingStart) {
		this.slidingStart = slidingStart;
	}

	public Color getColor() {
		return color;
	}

	public BigDecimal getUnrealizedGain() {
		return super.getUnrealizedGain(calcCurrentDate());
	}

	public BigDecimal getRealizedGain() {
		return super.calculateGain(EventSignalConfig.getNewDate());
	}

	@Override
	public void applyTransaction(Transaction transaction, boolean propagate) throws InvalidQuantityException {
		underLyingPortfolioShare.applyTransaction(transaction, true);
		super.applyTransaction(transaction, false);
	}
	
	@Override
	public void setBuyDate(Date buyDate) {
		underLyingPortfolioShare.setBuyDate(buyDate);
		super.setBuyDate(buyDate);
	}
	
	@Override
	public void setMonitorLevel(MonitorLevel monitorLevel) {
		underLyingPortfolioShare.setMonitorLevel(monitorLevel);
		super.setMonitorLevel(monitorLevel);
	}

	@Override
	public void addBuyAlerts(BigDecimal transcationPrice, Date currentDate) {
		underLyingPortfolioShare.addBuyAlerts(transcationPrice, currentDate);
		super.addBuyAlerts(transcationPrice, currentDate);
	}

	@Override
	public void removeAlert(Alert alert) {
		underLyingPortfolioShare.removeAlert(alert);
		super.removeAlert(alert);
	}

	@Override
	public void addSimpleAlert(ThresholdType threshold, BigDecimal value, String message) {
		underLyingPortfolioShare.addSimpleAlert(threshold, value, message);
		super.addSimpleAlert(threshold, value, message);
	}

	@Override
	public void addAboveTakeProfitAlert(BigDecimal price, String message) {
		underLyingPortfolioShare.addAboveTakeProfitAlert(price, message);
		super.addAboveTakeProfitAlert(price, message);
	}

	@Override
	public void addWeigthedZeroProfitAlertGuardSetter(BigDecimal price, String message) {
		underLyingPortfolioShare.addWeigthedZeroProfitAlertGuardSetter(price, message);
		super.addWeigthedZeroProfitAlertGuard(price, message);
	}

	@Override
	public String info() {
		return this.getName()+" ("+this.getSymbol()+" / "+this.getIsin()+")";
	}

	public Boolean getDisplayOnChart() {
		return displayOnChart;
	}

	public void setDisplayOnChart(Boolean displayOnChart) {
		this.displayOnChart = displayOnChart;
	}

	public String getFreindlyName() {
		return underLyingPortfolioShare.getStock().getFriendlyName();
	}	
	
}
