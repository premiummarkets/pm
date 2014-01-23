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
package com.finance.pms.portfolio.gui;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.graphics.Color;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.alerts.AlertOnEvent;
import com.finance.pms.alerts.AlertOnThreshold;
import com.finance.pms.alerts.AlertOnThresholdType;
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
				BigDecimal startClosePrice = quotations.getClosestCloseForDate(start);
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

	public BigDecimal getProfit() {
		//return super.getProfit(EventSignalConfig.getNewDate());
		return super.getProfit(calcCurrentDate());
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

	public BigDecimal getUnrealizedGain() {
		return super.getUnrealizedGain(calcCurrentDate());
	}
	

	public BigDecimal getRealizedGain() {
		//return super.calculateGain(EventSignalConfig.getNewDate());
		return this.getCashout();
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
	public void removeAlertOnThreshold(AlertOnThreshold alert) {
		underLyingPortfolioShare.removeAlertOnThreshold(alert);
		super.removeAlertOnThreshold(alert);
	}

	@Override
	public void addSimpleAlertOnThreshold(ThresholdType threshold, BigDecimal value, String message) {
		underLyingPortfolioShare.addSimpleAlertOnThreshold(threshold, value, message);
		super.addSimpleAlertOnThreshold(threshold, value, message);
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
		//return this.getName()+" ("+this.getSymbol()+" / "+this.getIsin()+")";
		return this.getStock().getFriendlyName();
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

	@Override
	public void addAlertOnEvent(String eventInfoReference, MonitorLevel monitorLevel, String optionalMessage) {
		underLyingPortfolioShare.addAlertOnEvent(eventInfoReference, monitorLevel, optionalMessage);
		//super.addAlertOnEvent(eventInfoReference, monitorLevel, optionalMessage);
	}

	@Override
	public void clearAlertOnEvent() {
		underLyingPortfolioShare.clearAlertOnEvent();
		//super.clearAlertOnEvent();
	}

	public PortfolioShare getUnderLyingPortfolioShare() {
		return underLyingPortfolioShare;
	}

	@Override
	public String toString() {
		return "SlidingPortfolioShare [underLyingPortfolioShare=" + underLyingPortfolioShare + ", displayOnChart=" + displayOnChart + "]";
	}

	@Override
	public String tootTip() {
		return "";
	}

	public void addWeigthedZeroProfitAlertGuard(BigDecimal price, String message) {
		underLyingPortfolioShare.addWeigthedZeroProfitAlertGuard(price, message);
	}

	public PortfolioShare resetCrossDown(AlertOnThreshold alert, BigDecimal crossingPrice) {
		return underLyingPortfolioShare.resetCrossDown(alert, crossingPrice);
	}

	public PortfolioShare resetCrossUp(AlertOnThreshold alert, BigDecimal crossingPrice) {
		return underLyingPortfolioShare.resetCrossUp(alert, crossingPrice);
	}

	public void addAlertOnThreshold(ThresholdType threshold, BigDecimal value, AlertOnThresholdType alertType, String message) {
		underLyingPortfolioShare.addAlertOnThreshold(threshold, value, alertType, message);
	}

	public HashSet<AlertOnThreshold> getAlertsOnThresholdFor(AlertOnThresholdType alertType) {
		return underLyingPortfolioShare.getAlertsOnThresholdFor(alertType);
	}

	public Set<AlertOnThreshold> getAlertsOnThreshold() {
		return underLyingPortfolioShare.getAlertsOnThreshold();
	}

	public Set<AlertOnThreshold> getAlertsOnThresholdUp() {
		return underLyingPortfolioShare.getAlertsOnThresholdUp();
	}

	public Set<AlertOnThreshold> getAlertsOnThresholdDown() {
		return underLyingPortfolioShare.getAlertsOnThresholdDown();
	}

	public Set<AlertOnEvent> getAlertsOnEvent() {
		return underLyingPortfolioShare.getAlertsOnEvent();
	}

}
