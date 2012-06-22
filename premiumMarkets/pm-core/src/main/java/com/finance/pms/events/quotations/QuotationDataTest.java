/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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
			itest =bmanoshort.getClosestIndexForDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(new Integer(2), itest);
			test = sf.parse("02/01/2000");
			itest =bmanoshort.getClosestIndexForDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(new Integer(1), itest);
			test = sf.parse("01/01/2000");
			itest =bmanoshort.getClosestIndexForDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(new Integer(0), itest);
			test = sf.parse("04/01/2000");
			itest =bmanoshort.getClosestIndexForDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(new Integer(3), itest);
			test = sf.parse("05/01/2000");
			itest =bmanoshort.getClosestIndexForDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(new Integer(3), itest);
			test = sf.parse("06/01/2000");
			itest =bmanoshort.getClosestIndexForDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(new Integer(4), itest);
			test = sf.parse("07/01/2000");
			itest =bmanoshort.getClosestIndexForDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(new Integer(4), itest);
			test = sf.parse("10/01/2000");
			itest =bmanoshort.getClosestIndexForDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(new Integer(4), itest);
			test = sf.parse("01/01/2007");
			itest =bmanoshort.getClosestIndexForDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(new Integer(4), itest);
			test = sf.parse("04/01/1999");
			itest =bmanoshort.getClosestIndexForDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(new Integer(0), itest);
			
			test = sf.parse("01/11/1997");
			itest =bfte.getClosestIndexForDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(new Integer(10), itest);
			test = sf.parse("12/07/1998");
			itest =bfte.getClosestIndexForDate(0,test);
			System.out.println("Res : "+itest);
			assertEquals(new Integer(180), itest);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
