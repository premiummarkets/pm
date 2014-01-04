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
package com.finance.pms.alerts;

import java.util.Date;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.portfolio.PortfolioDAO;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.PortfolioShare;

public class ThresholdAlertTest {
	
	AlertOnThresholdParser thresholdAlert;
	Stock stock;
	PortfolioMgr portfolioMgr;
	List<PortfolioShare> portfolioShareList;
	PortfolioShare  portfolioShare;
	PortfolioDAO portfolioDAO;
	
	@Before
	public void setUp() throws Exception {
		
//		stock =  EasyMock.createMock(Stock.class,
//				new Method[]{Stock.class.getMethod("getQuotations")});
////		portfolioMgr =  EasyMock.createMock(PortfolioMgr.class,
////				new Method[]{PortfolioMgr.class.getMethod("getAlertsForStock", Stock.class)});
//		portfolioMgr =  EasyMock.createMock(PortfolioMgr.class);
//		portfolioDAO = EasyMock.createMock(PortfolioDAO.class);
//		
//		portfolioShare = EasyMock.createMock(PortfolioShare.class,
//				new Method[]{PortfolioShare.class.getMethod("getStock"),PortfolioShare.class.getMethod("getAverageBuyingPrice")});
//		portfolioShareList = new ArrayList<PortfolioShare>();
//		portfolioShareList.add(portfolioShare);
//	
//		PortfolioMgr.setInit(portfolioMgr);
//		
//		
//		//initial alerts
//		Map<ThresholdType,AlertsSet> alerts = new HashMap<ThresholdType,AlertsSet>();
//		AlertsSet alertsSetUp = new AlertsSet(ThresholdType.UP,portfolioShare);
//		//FIXME
//		//alertsSetUp.add(new Alert(12D,AlertType.ABOVE_PRICE_LIMIT.getText(),AlertType.ABOVE_PRICE_LIMIT));
//		AlertsSet alertsSetDown = new AlertsSet(ThresholdType.DOWN,portfolioShare);
//		//FIXME
//		//alertsSetDown.add(new Alert(10D,AlertType.AVG_BUY_PRICE.getText(),AlertType.AVG_BUY_PRICE));
//		alerts.put(ThresholdType.UP,alertsSetUp);
//		alerts.put(ThresholdType.DOWN,alertsSetDown);
//		
//		portfolioShare.setAlerts(alerts);
//		
//		//EasyMock.expect(PortfolioMgr.getInstance()).andReturn(portfolioMgr);
//		//EasyMock.expect(portfolioMgr.getAlertsForStock(stock)).andReturn(stock.getAlerts());
//		EasyMock.expect(portfolioDAO.loadPortfolioShareForStock(stock)).andReturn(portfolioShareList);
//		EasyMock.expect(portfolioMgr.getRecordedPortfolioShareForStockInAllPortfolios(stock)).andReturn(portfolioShareList);
//		
//		EasyMock.replay(stock);
//		EasyMock.replay(portfolioShare);
//		EasyMock.replay(portfolioDAO);
//		EasyMock.replay(portfolioMgr);
//		
//		//FIXME
//		//thresholdAlert = new ThresholdAlert(stock,"");
//		
//		EasyMock.reset(stock);
//		EasyMock.reset(portfolioShare);
//	//	EasyMock.reset(portfolioMgr);
		
	}

	@Test
	public final void testCalculateEvents() throws Exception {

		
//		//Up trend
//		Quotations quotationData = EasyMock.createMock(Quotations.class);
//		Quotations quotations = EasyMock.createMock(Quotations.class);
//		EasyMock.expect(quotations.getQuotationData()).andReturn(quotationData).anyTimes();	
//		//EasyMock.expect(stock.getQuotations()).andReturn(quotations).anyTimes();
//		
//		
//		//EasyMock.expect(portfolioShare.getStock()).andReturn(stock).anyTimes();
////		List<Portfolio> listPorts = new ArrayList<Portfolio>();
////		Portfolio portfolio = new Portfolio("Toto");
////		portfolio.addShare(portfolioShare);
////		listPorts.add(portfolio);
////		portfolioMgr.setPortfolios(listPorts);
//		EasyMock.expect(portfolioShare.getAvgBuyPrice()).andReturn(BigDecimal.TEN).anyTimes();
//		
//		setTrend(quotationData,9D,12D,13D,14D,14.5D,14.52D);
//		
//		replay(quotationData, quotations, portfolioShare);
//		
//		Map<EventKey, EventValue> map = null;
//		for (int i = 0; i < 5; i++) {
//			map = thresholdAlert.calculateEvents(i);
//			if (i == 0) {//Up 12
//				EventValue eventValue = map.values().iterator().next();
//				Boolean ev = eventValue.getMessage().contains(ThresholdType.UP.getMsgHint()+"12.00 : "+AlertType.ABOVE_PRICE_LIMIT.getText());
//				Assert.assertTrue(ev);
//			}
//			System.out.println(map);
//			System.out.println("Alerts at "+i+" : Up "+portfolioShare.getAlertsUp()+" Down "+portfolioShare.getAlertsDown());
//		}
//		
//		System.out.println("Alerts : Up "+portfolioShare.getAlertsUp()+" Down "+portfolioShare.getAlertsDown());
//		Alert alertup = portfolioShare.getAlerts(ThresholdType.UP).getAlerts().iterator().next();
//		Assert.assertEquals(AlertType.ABOVE_PRICE_LIMIT,alertup.getAlertType());
//		Assert.assertEquals(new BigDecimal(14).add(((EventSignalConfig)ConfigThreadLocal.get("eventSignal")).getStopLossRate().multiply(new BigDecimal(14))),alertup.getValue());
//		
//		//Down Trend
//		//reset(quotationData, quotations, portfolioShare);
//		EasyMock.reset(quotationData);
//		
//		setTrend(quotationData,14D,13D,12D,11D,10D,9D);
//		
//		EasyMock.replay(quotationData);
//
//		map = null;
//		for (int i = 0; i < 5; i++) {
//			map = thresholdAlert.calculateEvents(i);
//			if (i == 3) {//Down 1O
//				EventValue eventValue = map.values().iterator().next();
//				Assert.assertTrue(eventValue.getMessage()
//						.contains(ThresholdType.DOWN.getMsgHint()+"10.00 : "+AlertType.AVG_BUY_PRICE.getText()));
//			}
//			System.out.println(map);
//			System.out.println("Alerts at "+i+" : Up "+portfolioShare.getAlertsUp()+" Down "+portfolioShare.getAlertsDown());
//		}
//		
//		System.out.println("Alerts : Up "+portfolioShare.getAlertsUp()+" Down "+portfolioShare.getAlertsDown());
//		alertup = portfolioShare.getAlerts(ThresholdType.UP).getAlerts().iterator().next();
//		Assert.assertEquals(AlertType.AVG_BUY_PRICE,alertup.getAlertType());
//		Assert.assertEquals(new BigDecimal(10).setScale(2),alertup.getValue());
//		
//		//Up and down
//		//reset(quotationData, quotations, portfolioShare);
//		EasyMock.reset(quotationData);
//		
//		setTrend(quotationData,9D,12D,13D,10D,9D,11D);
//		
//		EasyMock.replay(quotationData);
//
//		map = null;
//		for (int i = 0; i < 5; i++) {
//			map = thresholdAlert.calculateEvents(i);
//			System.out.println(map);
//			System.out.println("Alerts at "+i+" : Up "+portfolioShare.getAlertsUp()+" Down "+portfolioShare.getAlertsDown());
//			if (i == 0) {//Up 10
//				EventValue eventValue = map.values().iterator().next();
//				Assert.assertTrue(eventValue.getMessage()
//						.contains(ThresholdType.UP.getMsgHint()+"10.00 : "+AlertType.AVG_BUY_PRICE.getText()));
//				alertup = portfolioShare.getAlerts(ThresholdType.UP).getAlerts().iterator().next();
//				Assert.assertEquals(AlertType.ABOVE_PRICE_LIMIT,alertup.getAlertType());
//				Assert.assertEquals(new BigDecimal(12).add(((EventSignalConfig)ConfigThreadLocal.get("eventSignal")).getStopLossRate().multiply(new BigDecimal(12))),alertup.getValue());
//			}
//			if (i == 2) {//Down 10
//				EventValue eventValue = map.values().iterator().next();
//				Assert.assertTrue(eventValue.getMessage()
//						.contains(ThresholdType.DOWN.getMsgHint()+"10.00 : "+AlertType.AVG_BUY_PRICE.getText()));
//				alertup = portfolioShare.getAlerts(ThresholdType.UP).getAlerts().iterator().next();
//				Assert.assertEquals(AlertType.AVG_BUY_PRICE,alertup.getAlertType());
//				Assert.assertEquals(new BigDecimal(10).setScale(2),alertup.getValue());
//			}
//			if (i == 4) {//Up 10
//				EventValue eventValue = map.values().iterator().next();
//				Assert.assertTrue(eventValue.getMessage()
//						.contains(ThresholdType.UP.getMsgHint()+"10.00 : "+AlertType.AVG_BUY_PRICE.getText()));
//			}
//		}
//		
//		System.out.println("Alerts : Up "+portfolioShare.getAlertsUp()+" Down "+portfolioShare.getAlertsDown());
//		alertup = portfolioShare.getAlerts(ThresholdType.UP).getAlerts().iterator().next();
//		Assert.assertEquals(AlertType.ABOVE_PRICE_LIMIT,alertup.getAlertType());
//		Assert.assertEquals(new BigDecimal(11).add(((EventSignalConfig)ConfigThreadLocal.get("eventSignal")).getStopLossRate().multiply(new BigDecimal(11))),alertup.getValue());
//		
//		alertup = portfolioShare.getAlerts(ThresholdType.DOWN).getAlerts().iterator().next();
//		Assert.assertEquals(AlertType.AVG_BUY_PRICE,alertup.getAlertType());
//		Assert.assertEquals(new BigDecimal(10).setScale(2),alertup.getValue());
//		
//		//Down Trend And stop loss
//		//reset(quotationData, quotations, portfolioShare);
//		EasyMock.reset(quotationData);
//		
//		setTrend(quotationData,11D,13D,12D,11D,10D,9D);
//		
//		EasyMock.replay(quotationData);
//		
//		portfolioShare.addStopLossAlert(11D);
//		
//		map = null;
//		for (int i = 0; i < 5; i++) {
//			map = thresholdAlert.calculateEvents(i);
//			if (i == 3) {//Down 1O
//				EventValue eventValue = map.values().iterator().next();
//				Assert.assertTrue(eventValue.getMessage()
//						.contains(ThresholdType.DOWN.getMsgHint()+"10.56 : "+AlertType.BELOW_PRICE_LIMIT.getText()));
//			}
//			System.out.println("Events : "+map);
//			System.out.println("Alerts at "+i+" : Up "+portfolioShare.getAlertsUp()+" Down "+portfolioShare.getAlertsDown());
//		}
//		
//		System.out.println("Alerts : Up "+portfolioShare.getAlertsUp()+" Down "+portfolioShare.getAlertsDown());
//		alertup = portfolioShare.getAlerts(ThresholdType.UP).getAlerts().iterator().next();
//		Assert.assertEquals(AlertType.AVG_BUY_PRICE,alertup.getAlertType());
//		Assert.assertEquals(new BigDecimal(10).setScale(2),alertup.getValue());
		
	}

	/**
	 * @param quotationData
	 */
	//FIXME
	 void setTrend(Quotations quotationData,Double...values) {
		
//		for (int i = 0; i < values.length; i++) {
//			EasyMock.expect(quotationData.get(i)).andReturn(new QuotationUnit(new Date(), 0d, 0d, 0d, values[i], 0)).anyTimes();
//		}
		EasyMock.expect(quotationData.getDate(EasyMock.anyInt())).andReturn(new Date()).anyTimes();
		
	}

	/**
	 * @param quotationData
	 * @param quotations
	 * @param portfolioShare
	 */
	 void replay(Quotations quotationData, Quotations quotations, PortfolioShare portfolioShare) {
		//EasyMock.replay(portfolioMgr);
		EasyMock.replay(portfolioShare);
		EasyMock.replay(stock);
		EasyMock.replay(quotations);
		EasyMock.replay(quotationData);
	}
	
	 void reset(Quotations quotationData, Quotations quotations, PortfolioShare portfolioShare) {
		//EasyMock.reset(portfolioMgr);
		EasyMock.reset(portfolioShare);
		EasyMock.reset(stock);
		EasyMock.reset(quotations);
		EasyMock.reset(quotationData);
	}
}
