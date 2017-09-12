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
package com.finance.pms.datasources.web.formaters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;

// TODO: Auto-generated Javadoc
/**
 * The Class StockComplementFormaterTest.
 * 
 * @author Guillaume Thoreton
 */
public class DayStockFormaterTest extends TestCase { //extends AbstractDependencyInjectionSpringContextTests {
	
	/** The formater. */
	LineFormater formater;
	
	/** The st. */
	Stock st;


//	@Override
//	protected String[] getConfigLocations() {
//    	
//    	//Providers.APPLICATION_CONTEXT = new SpringContext(this.applicationContext);
//    	//Providers.APPLICATION_CONTEXT.setProvidersSource("db.properties");
//        return new String[] { "/connexions.xml"};
//    }

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//super.onSetUp();
		
		//new DataSource("/home/guil/Developpement/Quotes/pms/db.properties");
		
		st = new Stock();
		st.setSymbol("AFO.PA");
		st.setIsin("FR0000044612");
		MarketValuation market = new MarketValuation(Market.PARIS);
		st.setMarketValuation(market);
		
		
		formater = new DayQuoteInvestingFormater(null, st);
		
		
	}

	/**
	 * Test format line.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testFormatLine() {
		
	      FileInputStream f;
	        BufferedReader dis;
	        String line;
	        boolean continu = true;
	        List<Validatable> queries = new ArrayList<Validatable>();
	        Set<Validatable> qL= new HashSet<Validatable>();
	        
	        try {   	
	 
	        	f=  new FileInputStream((new File("/home/guil/tmp/resp_2.html")));
	            dis = new BufferedReader(new InputStreamReader(f));
	            int cpt = 0;
		        for(; ((line = dis.readLine()) != null) && continu;)
		        {
			        if(line.length() > 0)
			        {
			        	List<Validatable> q;
			        	try {
			        		q = formater.formatLine(line);
			        		if (q != null ) {
			        			qL.addAll(q);
			        			//System.out.println(q.toDataBase());
			        		}
			        		System.out.println(++cpt);
			        	} catch (StopParseException e) {
			        	    System.out.println("Fin : "+((StopParseFoundException) e).getLastOne());
			        		System.out.println(((Validatable)formater.params.get(0)).getState());
			        		//continu = false;
			        	}
			        	
			        }
		        }
	        }
	        catch (Exception e) {
	        	e.printStackTrace();
	        }
	        
	        queries.addAll(qL);

			for (Validatable v: queries ) {
				System.out.println(v.toDataBase());
			}
	    
    }
}
