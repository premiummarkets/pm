/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
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
package com.finance.pms.portfolio;


import org.junit.Before;
import org.junit.Test;

public class PortfolioTest {
	
	
	AbstractSharesList portfolio;
	AbstractSharesList portfolio2;
	PortfolioShare portfolioShare1;
	PortfolioShare portfolioShare2;
	
	@Before
	public void setUp() throws Exception {
		
		portfolio = new UserPortfolio("pTest",null);
		//portfolio2 = new AutoPortfolio("test");
	}
	
	@Test
	public void testRemoveShare() throws Exception {
		
//		portfolioShare1 = EasyMock.createMock(PortfolioShare.class,
//				new Method[]{
//			PortfolioShare.class.getMethod("getCashin"),
//			PortfolioShare.class.getMethod("getCashout"),
//			PortfolioShare.class.getMethod("getLastDayCloseValue"),
//			PortfolioShare.class.getMethod("getQuantity"),
//			PortfolioShare.class.getMethod("getSymbol")
//		});
//		portfolioShare2 = EasyMock.createMock(PortfolioShare.class,
//				new Method[]{
//			PortfolioShare.class.getMethod("getCashin"),
//			PortfolioShare.class.getMethod("getCashout"),
//			PortfolioShare.class.getMethod("getLastDayCloseValue"),
//			PortfolioShare.class.getMethod("getQuantity"),
//			PortfolioShare.class.getMethod("getSymbol")
//		});		
//		
//		EasyMock.expect(portfolioShare1.getCashin()).andReturn(100f).anyTimes();
//		EasyMock.expect(portfolioShare1.getCashout()).andReturn(99f).anyTimes();
//		EasyMock.expect(portfolioShare1.getLastDayCloseValue()).andReturn(22f).anyTimes();
//		EasyMock.expect(portfolioShare1.getQuantity()).andReturn(2f).anyTimes();
//		EasyMock.expect(portfolioShare1.getSymbol()).andReturn("stock1").anyTimes();
//		
//		
//		EasyMock.expect(portfolioShare2.getCashin()).andReturn(10f).anyTimes();
//		EasyMock.expect(portfolioShare2.getCashout()).andReturn(9f).anyTimes();
//		EasyMock.expect(portfolioShare2.getLastDayCloseValue()).andReturn(11f).anyTimes();
//		EasyMock.expect(portfolioShare2.getQuantity()).andReturn(3f).anyTimes();
//		EasyMock.expect(portfolioShare2.getSymbol()).andReturn("stock2").anyTimes();
//	
//		EasyMock.replay(portfolioShare1);
//		EasyMock.replay(portfolioShare2);
//		
//		portfolio.addOrUpdateShare(portfolioShare1);
//		portfolio.addAmountToTotalAmountIn(new BigDecimal(100f),Currency.EUR);
//		portfolio.addAmountToTotalAmountOut(new BigDecimal(99),Currency.EUR);
//		portfolio.addOrUpdateShare(portfolioShare2);
//		portfolio.addAmountToTotalAmountIn(new BigDecimal(10f),Currency.EUR);
//		portfolio.addAmountToTotalAmountOut(new BigDecimal(9),Currency.EUR);
//		
//		
//		Assert.assertEquals(new BigDecimal(110).setScale(2),portfolio.getTotalInAmountEver());
//		Assert.assertEquals(new BigDecimal(108).setScale(2),portfolio.getTotalOutAmountEver());
//	
//		portfolio.removeShare(portfolioShare2, false);
//		Assert.assertEquals(100.0,portfolio.getTotalInAmountEver().doubleValue());
//		Assert.assertEquals(99.0,portfolio.getTotalOutAmountEver().doubleValue());
//		
//		portfolio.removeShare(portfolioShare1, true);
//		Assert.assertEquals(100.0,portfolio.getTotalInAmountEver().doubleValue());
//		Assert.assertEquals(143.0,portfolio.getTotalOutAmountEver().doubleValue());
//		
//		EasyMock.verify(portfolioShare1);
//		EasyMock.verify(portfolioShare2);
			
	}
	
	public void testRealizedProfit() throws SecurityException, NoSuchMethodException {
	}
	
}
