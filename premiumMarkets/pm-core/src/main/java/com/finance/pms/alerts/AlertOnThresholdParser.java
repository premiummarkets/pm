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
package com.finance.pms.alerts;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Observer;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.AlertEventKey;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.calculation.IndicatorsCompositioner;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.talib.dataresults.AlertEventValue;


public class AlertOnThresholdParser extends IndicatorsCompositioner {
	
	private static MyLogger LOGGER = MyLogger.getLogger(AlertOnThresholdParser.class);

	private PortfolioShare portfolioShare;
	
//	public AlertOnThresholdParser(PortfolioShare portfolioShare, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
//		super(portfolioShare.getStock(),startDate, endDate, calculationCurrency, ValidityFilter.CLOSE);	
//		this.portfolioShare = portfolioShare;
//		
//	}
	public AlertOnThresholdParser(PortfolioShare portfolioShare, Observer...observers) {
		super(observers);
		this.portfolioShare = portfolioShare;
	}

	@Override
	public SortedMap<EventKey, EventValue> calculateEventsFor(Quotations quotations, String eventListName) {
		
		SortedMap<EventKey, EventValue> edata = new TreeMap<EventKey, EventValue>();
		if (quotations.size() == 0) return edata;
		
		for (int quotationIndex = quotations.getFirstDateShiftedIdx(); quotationIndex <= quotations.getLastDateIdx() && quotationIndex < quotations.size(); quotationIndex++) {

			LOGGER.debug("Calculate alerts for : " + portfolioShare);

			BigDecimal quantity = portfolioShare.getQuantity(EventSignalConfig.getNewDate());
			QuotationUnit quotation = quotations.get(quotationIndex);
			
			if (quantity.compareTo(BigDecimal.ZERO) > 0 && !portfolioShare.getLastTransactionDate().after(quotation.getDate())) {

				checkAlertCrossingUp(quotation, edata);
				checkAlertCrossingDown(quotation, edata);

				if (!edata.isEmpty()) {
					LOGGER.info(
							"Alerts on Threshold crossing for share "+portfolioShare.getName() + " : " + portfolioShare.getAlertsOnThreshold() +
							" quotation " + quotation.getClose() +" and resulting events : "+edata);
				}
			} else {
				LOGGER.debug("Can't parse alert on the " + quotation.getDate() +
						" cause either : the share was bought after on " + portfolioShare.getLastTransactionDate() +
						" or the share as been sold by another thread and there is none left : quantity left is " + quantity);

			}
		}

		return edata;
	}

	private void checkAlertCrossingDown(QuotationUnit quotation, Map<EventKey, EventValue> edata) {
		
		if (portfolioShare.getAlertsOnThresholdDown() != null) {
			Set<AlertOnThreshold> alertsSetDown =  new HashSet<AlertOnThreshold>(portfolioShare.getAlertsOnThresholdDown());
			BigDecimal todaysQuotation = quotation.getClose();
			for (AlertOnThreshold alert : alertsSetDown) {
				
				if (alert.getValue().compareTo(todaysQuotation) > 0) {
					portfolioShare.resetCrossDown(alert, todaysQuotation);
					
					EventDefinition eventDefinition = EventDefinition.ALERTTHRESHOLD;
					String message = "Below "+ alert + " at " + todaysQuotation;
					message = message + additionnalMessage(todaysQuotation);
					
					//TODO improved rules
					EventType eventType = EventType.INFO; //default alert
					if (
						(AlertOnThresholdType.BELOW_PRICE_CHANNEL.equals(alert.getAlertType()) && portfolioShare.getPriceUnitCost(EventSignalConfig.getNewDate(), portfolioShare.getTransactionCurrency()).compareTo(todaysQuotation) > 0) || 
						AlertOnThresholdType.BELOW_ZERO_WEIGHTED_PROFIT_LIMIT.equals(alert.getAlertType())
						) 
					{ //price above buy price and going down or price below weighted profit guard => SELL
						eventType = EventType.BEARISH;
					}
				
					alertDetected(edata, quotation.getDate(), eventDefinition, eventType, message, portfolioShare.getPortfolio().getName(), alert.getAlertType());
					
				}
				
			}
		}
	}

	private void checkAlertCrossingUp(QuotationUnit quotation, Map<EventKey, EventValue> edata) {
		
		if (portfolioShare.getAlertsOnThresholdUp() != null) {
			Set<AlertOnThreshold> alertsSetUp = new HashSet<AlertOnThreshold>(portfolioShare.getAlertsOnThresholdUp());
			BigDecimal todaysQuotation = quotation.getClose();
			for (AlertOnThreshold alert : alertsSetUp) {

				if (alert.getValue().compareTo(todaysQuotation) < 0) {
					
					portfolioShare.resetCrossUp(alert, todaysQuotation);
					
					//TODO improved rules	
					if (AlertOnThresholdType.BELOW_ZERO_WEIGHTED_PROFIT_LIMIT.equals(alert.getAlertType())) {
						//Here we just need to set the alert guard down (done in resetUpCross) so no message needs to be sent
						return;
					}
					
					EventDefinition eventDefinition = EventDefinition.ALERTTHRESHOLD;
					String message = "Above "+alert+ " at " + todaysQuotation;
					message = message + additionnalMessage(todaysQuotation);
					
					EventType eventType = EventType.INFO; //default alert
					if (AlertOnThresholdType.ABOVE_TAKE_PROFIT_LIMIT.equals(alert.getAlertType())) { //above profit limit reach
						//An info event is raised.
					}
					
					alertDetected(edata, quotation.getDate(), eventDefinition, eventType, message, portfolioShare.getPortfolio().getName(), alert.getAlertType());
				}
			}
		}
	}

	private String additionnalMessage(BigDecimal todaysQuotation) {
		
		BigDecimal avgPriceDist = BigDecimal.ZERO;
		BigDecimal avgBuyPrice = portfolioShare.getPriceUnitCost(EventSignalConfig.getNewDate(), portfolioShare.getTransactionCurrency());
		if (avgBuyPrice.compareTo(BigDecimal.ZERO) != 0) {
			avgPriceDist = todaysQuotation.subtract(avgBuyPrice).divide(avgBuyPrice, 10, BigDecimal.ROUND_HALF_EVEN);
		}
		return ".\nFYI, price ("+todaysQuotation+") is "+new DecimalFormat("#0.00 %").format(avgPriceDist.doubleValue())+" away from average cost per unit ("+avgBuyPrice+").";
	}

	private void alertDetected(Map<EventKey, EventValue> eventData, Date current, EventDefinition eventDefinition, EventType eventType, String message, String eventListName, AlertOnThresholdType alertType) {
		AlertEventKey iek = new AlertEventKey(current, eventDefinition, eventType, alertType);
		EventValue iev = new AlertEventValue(current, eventDefinition, eventType, message, eventListName);
		eventData.put(iek, iev);
	}

	protected int getDaysSpan() {
		return 0;
	}

	@Override
	public  SortedMap<Date, double[]> calculationOutput() {
		return new TreeMap<Date, double[]>();
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.ALERTTHRESHOLD;
	}

	@Override
	public EmailFilterEventSource getSource() {
		return EmailFilterEventSource.PMTAEvents;
	}

	@Override
	public Integer getStartShift() {
		return 0;
	}

	@Override
	public ValidityFilter quotationsValidity() {
		return ValidityFilter.CLOSE;
	}
	
}
