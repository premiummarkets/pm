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
package com.finance.pms.events.calculation;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.annotation.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import com.finance.pms.events.quotations.Quotations;

@RunWith(PowerMockRunner.class)
public class StdDevStrategyTest {
	
	StdDevStrategy stdDevStrategy;
	@Mock
	Quotations calculationQuotations;
//	private double[] values;
	
	@Before
	public void setUp() throws Exception {
		stdDevStrategy = new StdDevStrategy(null);
	}

//	@Test
//	public final void testPositiveBullish() throws NotEnoughDataException {
//		
//		values = new double[]{10,12,15,17,18,20,25};
//		EasyMock.expect(calculationQuotations.getCloseValues()).andReturn(values);
//		EasyMock.replay(calculationQuotations);
//		
////		double mean = stdDevStrategy.mean(1, 6, values);
////		Assert.assertEquals(15.0,Math.rint(mean*100));
////		double stdDev = stdDevStrategy.stdDev(values, 1, 6, 15.0);
////		Assert.assertEquals(67.0, Math.rint(stdDev*1000));
//		EventType periodType = stdDevStrategy.periodType(calculationQuotations, 0, 6);
//		Assert.assertEquals(EventType.BULLISH, periodType);
//		
//	}
//	
//	@Test
//	public final void testNegativeBearish() throws NotEnoughDataException {
//		
//		values = new double[]{20,18,17,15,12,10,8};
//		EasyMock.expect(calculationQuotations.getCloseValues()).andReturn(values);
//		EasyMock.replay(calculationQuotations);
//		
////		double mean = stdDevStrategy.mean(1,6, values);
////		Assert.assertEquals(-13.0, Math.rint(mean*100));
////		double stdDev = stdDevStrategy.stdDev(values, 1,6, mean);
////		Assert.assertEquals(51.0, Math.rint(stdDev*1000));
//		EventType periodType = stdDevStrategy.periodType(calculationQuotations, 0, 6);
//		Assert.assertEquals(EventType.BEARISH, periodType);
//		
//	}
//	
//	@Test
//	public final void testPositiveNeutral() throws NotEnoughDataException {
//		
//		values = new double[]{10,12,15,17,18,20,21};
//		EasyMock.expect(calculationQuotations.getCloseValues()).andReturn(values);
//		EasyMock.replay(calculationQuotations);
//		
////		double mean = stdDevStrategy.mean(1, 6, values);
////		Assert.assertEquals(15.0, Math.rint(mean*100));
////		double stdDev = stdDevStrategy.stdDev(values, 1, 6, mean);
////		Assert.assertEquals(67.0, Math.rint(stdDev*1000));
//		EventType periodType = stdDevStrategy.periodType(calculationQuotations, 0, 6);
//		Assert.assertEquals(EventType.NONE, periodType);
//		
//	}
//	
//	@Test
//	public final void testNegativeNeutral() throws NotEnoughDataException {
//		
//		values = new double[]{20,18,17,15,12,10,9};
//		EasyMock.expect(calculationQuotations.getCloseValues()).andReturn(values);
//		EasyMock.replay(calculationQuotations);
//		
////		double mean = stdDevStrategy.mean(1,6, values);
////		Assert.assertEquals(-13.0, Math.rint(mean*100));
////		double stdDev = stdDevStrategy.stdDev(values, 1,6, mean);
////		Assert.assertEquals(51.0, Math.rint(stdDev*1000));
//		EventType periodType = stdDevStrategy.periodType(calculationQuotations, 0, 6);
//		Assert.assertEquals(EventType.NONE, periodType);
//		
//	}
//	
//	@Test
//	public final void testMixedNeutral() throws NotEnoughDataException {
//		
//		values = new double[]{19,20,18,23,17,24,25,15,16,17,19,12,10,8};
//		EasyMock.expect(calculationQuotations.getCloseValues()).andReturn(values);
//		EasyMock.replay(calculationQuotations);
//		
////		double mean = stdDevStrategy.mean(1,values.length-1, values);
////		Assert.assertEquals(13.0, Math.rint(mean*100));
////		double stdDev = stdDevStrategy.stdDev(values, 1,values.length-1, mean);
////		Assert.assertEquals(51.0, Math.rint(stdDev*1000));
//		EventType periodType = stdDevStrategy.periodType(calculationQuotations, 0, values.length-1);
//		Assert.assertEquals(EventType.NONE, periodType);
//		
//	}
//	
//	@Test
//	public final void testMixedBearish() throws NotEnoughDataException {
//		
//		values = new double[]{19,20,18,23,17,24,25,15,16,17,19,12,10,6};
//		EasyMock.expect(calculationQuotations.getCloseValues()).andReturn(values);
//		EasyMock.replay(calculationQuotations);
//		
////		double mean = stdDevStrategy.mean(1,values.length-1, values);
////		Assert.assertEquals(13.0, Math.rint(mean*100));
////		double stdDev = stdDevStrategy.stdDev(values, 1,values.length-1, mean);
////		Assert.assertEquals(51.0, Math.rint(stdDev*1000));
//		EventType periodType = stdDevStrategy.periodType(calculationQuotations, 0, values.length-1);
//		Assert.assertEquals(EventType.BEARISH, periodType);
//		
//	}
//	
//	@Test
//	public final void testMixedBullish() throws NotEnoughDataException {
//		
//		values = new double[]{19,20,18,23,17,24,25,15,16,17,19,12,10,15};
//		EasyMock.expect(calculationQuotations.getCloseValues()).andReturn(values);
//		EasyMock.replay(calculationQuotations);
//		
////		double mean = stdDevStrategy.mean(1,values.length-1, values);
////		Assert.assertEquals(13.0, Math.rint(mean*100));
////		double stdDev = stdDevStrategy.stdDev(values, 1,values.length-1, mean);
////		Assert.assertEquals(51.0, Math.rint(stdDev*1000));
//		EventType periodType = stdDevStrategy.periodType(calculationQuotations, 0, values.length-1);
//		Assert.assertEquals(EventType.BULLISH, periodType);
//		
//	}
}
