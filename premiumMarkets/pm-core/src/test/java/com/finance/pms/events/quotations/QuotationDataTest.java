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
package com.finance.pms.events.quotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class QuotationDataTest.
 * 
 * @author Guillaume Thoreton
 */
public class QuotationDataTest extends TestCase {
	
	/** The bfte. */
	QuotationData bfte;
	
	/** The bmanoshort. */
	QuotationData bmanoshort;
	
	/** The sf. */
	SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//FIXME
//		Stock stock = new  Stock("FR0000133308","FTE");
//		new DataSource("/home/guil/Developpement/Quotes/com.finance.pms/db.properties");
//		//FIXME fix test new ProvidersBoursorama("boursorama");
//		bfte= new QuotationData(DataSource.getInstance().loadQuotations(stock));
//	
//		List<QuotationUnit> lb =new ArrayList<QuotationUnit>();
//		lb.add(new QuotationUnit(sf.parse("01/01/2000")));
//		lb.add(new QuotationUnit(sf.parse("02/01/2000")));
//		lb.add(new QuotationUnit(sf.parse("03/01/2000")));
//		lb.add(new QuotationUnit(sf.parse("05/01/2000")));
//		lb.add(new QuotationUnit(sf.parse("10/01/2000")));
//		bmanoshort = new QuotationData(lb);
	}

	/**
	 * Test get closest index for date.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testGetClosestIndexForDate() {
		
		try {
			Date test;
			Integer itest;
			test = sf.parse("03/01/2000");
			itest =bmanoshort.getClosestIndexBeforeOrAtDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(Integer.valueOf(2), itest);
			test = sf.parse("02/01/2000");
			itest =bmanoshort.getClosestIndexBeforeOrAtDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(Integer.valueOf(1), itest);
			test = sf.parse("01/01/2000");
			itest =bmanoshort.getClosestIndexBeforeOrAtDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(Integer.valueOf(0), itest);
			test = sf.parse("04/01/2000");
			itest =bmanoshort.getClosestIndexBeforeOrAtDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(Integer.valueOf(3), itest);
			test = sf.parse("05/01/2000");
			itest =bmanoshort.getClosestIndexBeforeOrAtDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(Integer.valueOf(3), itest);
			test = sf.parse("06/01/2000");
			itest =bmanoshort.getClosestIndexBeforeOrAtDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(Integer.valueOf(4), itest);
			test = sf.parse("07/01/2000");
			itest =bmanoshort.getClosestIndexBeforeOrAtDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(Integer.valueOf(4), itest);
			test = sf.parse("10/01/2000");
			itest =bmanoshort.getClosestIndexBeforeOrAtDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(Integer.valueOf(4), itest);
			test = sf.parse("01/01/2007");
			itest =bmanoshort.getClosestIndexBeforeOrAtDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(Integer.valueOf(4), itest);
			test = sf.parse("04/01/1999");
			itest =bmanoshort.getClosestIndexBeforeOrAtDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(Integer.valueOf(0), itest);
			
			test = sf.parse("01/11/1997");
			itest =bfte.getClosestIndexBeforeOrAtDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(Integer.valueOf(10), itest);
			test = sf.parse("12/07/1998");
			itest =bfte.getClosestIndexBeforeOrAtDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(Integer.valueOf(180), itest);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
