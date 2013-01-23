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
package com.finance.pms.alerts;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.events.AlertEventKey;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.calculation.EventCompostionCalculator;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.talib.dataresults.AlertEventValue;


public class ThresholdAlertParser extends EventCompostionCalculator {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ThresholdAlertParser.class);

	private PortfolioShare portfolioShare;
	
	public ThresholdAlertParser(PortfolioShare portfolioShare, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(portfolioShare.getStock(),startDate, endDate, calculationCurrency);	
		this.portfolioShare = portfolioShare;
		
	}

	@Override
	public Map<EventKey, EventValue> calculateEventsFor(String eventListName) {
		
		Map<EventKey, EventValue> edata = new HashMap<EventKey, EventValue>();
		if (this.getCalculatorQuotationData().size() == 0) return edata;
		
		for (int quotationIndex = calculationStartIdx; quotationIndex <= calculationEndIdx && quotationIndex < this.getCalculatorQuotationData().size(); quotationIndex++) {

			LOGGER.debug("Calculate alerts for : " + portfolioShare);

			if (portfolioShare.getQuantity().compareTo(BigDecimal.ZERO) > 0 && !portfolioShare.getBuyDate().after(this.getCalculatorQuotationData().get(quotationIndex).getDate())) {

				checkAlertCrossingUp(edata, quotationIndex);
				checkAlertCrossingDown(edata, quotationIndex);

				if (!edata.isEmpty()) {
					LOGGER.info(
							"Alerts on Threshold crossing for share "+portfolioShare.getName() + " : " + portfolioShare.getAlerts() +
							" quotation " + this.getCalculatorQuotationData().get(quotationIndex).getClose() +" and resulting events : "+edata);
				}
			} else {
				LOGGER.debug("Can't parse alert on the " + getCalculatorQuotationData().get(quotationIndex).getDate() +
						" cause either : the share was bought after on " + portfolioShare.getBuyDate() +
						" or the share as been sold by another thread and there is none left : quantity left is " + portfolioShare.getQuantity());

			}
		}

		return edata;
	}

	/**
	 * @param edata
	 * @param quotationIndex
	 * @param downMessage
	 */
	private void checkAlertCrossingDown(Map<EventKey, EventValue> edata, Integer quotationIndex) {
		
		if (portfolioShare.getAlertsDown() != null) {
			Set<Alert> alertsSetDown =  new HashSet<Alert>(portfolioShare.getAlertsDown());
			BigDecimal todaysQuotation = this.getCalculatorQuotationData().get(quotationIndex).getClose();
			for (Alert alert : alertsSetDown) {
				
				if (alert.getValue().compareTo(todaysQuotation) > 0) {
					portfolioShare.resetCrossDown(alert, todaysQuotation);
					
					EventDefinition eventDefinition = EventDefinition.ALERTTHRESHOLD;
					String message = "Below "+alert + " at " + todaysQuotation;
					message = message + additionnalMessage(todaysQuotation);
					
					//TODO improved rules
					EventType eventType = EventType.INFO; //default alert
					if (
						(AlertType.BELOW_PRICE_CHANNEL.equals(alert.getAlertType()) && portfolioShare.getAvgBuyPrice().compareTo(todaysQuotation) > 0) || 
						AlertType.BELOW_ZERO_WEIGHTED_PROFIT_LIMIT.equals(alert.getAlertType())
						) 
					{ //price above buy price and going down or price below weighted profit guard => SELL
						eventType = EventType.BEARISH;
					}
				
					alertDetected(edata, quotationIndex, eventDefinition, eventType, message, portfolioShare.getPortfolio().getName(), alert.getAlertType());
					
				}
				
			}
		}
	}

	/**
	 * @param edata
	 * @param quotationIndex
	 * @param upMessage
	 */
	private void checkAlertCrossingUp(Map<EventKey, EventValue> edata, Integer quotationIndex) {
		
		if (portfolioShare.getAlertsUp() != null) {
			Set<Alert> alertsSetUp = new HashSet<Alert>(portfolioShare.getAlertsUp());
			BigDecimal todaysQuotation = this.getCalculatorQuotationData().get(quotationIndex).getClose();
			for (Alert alert : alertsSetUp) {

				if (alert.getValue().compareTo(todaysQuotation) < 0) {
					
					portfolioShare.resetCrossUp(alert, todaysQuotation);
					
					//TODO improved rules	
					if (AlertType.BELOW_ZERO_WEIGHTED_PROFIT_LIMIT.equals(alert.getAlertType())) {
						//Here we just need to set the alert guard down (done in resetUpCross) so no message needs to be sent
						return;
					}
					
					EventDefinition eventDefinition = EventDefinition.ALERTTHRESHOLD;
					String message = "Above "+alert+ " at " + todaysQuotation;
					message = message + additionnalMessage(todaysQuotation);
					
					EventType eventType = EventType.INFO; //default alert
					if (AlertType.ABOVE_TAKE_PROFIT_LIMIT.equals(alert.getAlertType())) { //above profit limit reach => SELL
						eventType = EventType.BEARISH;
					}
					
					alertDetected(edata, quotationIndex, eventDefinition, eventType, message, portfolioShare.getPortfolio().getName(), alert.getAlertType());
				}
			}
		}
	}

	/**
	 * @param todaysQuotation
	 * @param message
	 * @return
	 */
	private String additionnalMessage(BigDecimal todaysQuotation) {
		
		BigDecimal avgPriceDist = BigDecimal.ZERO;
		BigDecimal avgBuyPrice = portfolioShare.getAvgBuyPrice();
		if (avgBuyPrice.compareTo(BigDecimal.ZERO) != 0) {
			avgPriceDist = todaysQuotation.subtract(avgBuyPrice).divide(avgBuyPrice,2,BigDecimal.ROUND_DOWN);
		}
		return ".\nFYI, price ("+todaysQuotation+") is "+new DecimalFormat("#0.00 %").format(avgPriceDist.doubleValue())+" away from average buy price ("+avgBuyPrice+").";
	}

	/**
	 * @param eventData
	 * @param quotationDataIndex
	 * @param eventType 
	 * @param message
	 * @param alertType 
	 * @param portfolioShare
	 */
	private void alertDetected(Map<EventKey, EventValue> eventData, Integer quotationDataIndex, EventDefinition eventDefinition, EventType eventType, String message, String eventListName, AlertType alertType) {
		Date current = this.getCalculatorQuotationData().getDate(quotationDataIndex);
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

	
	
}
