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
package com.finance.pms.datasources.web;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.WindowEvent;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

// TODO: Auto-generated Javadoc
/**
 * The Class ProvidersBoursoramaTestCase.
 * 
 * @author Guillaume Thoreton
 */
public class ProvidersBoursoramaTestCase extends TestCase
{
  
  /** The var providers boursorama. */
  protected Providers varProvidersBoursorama;

  /**
   * Instantiates a new providers boursorama test case.
   * 
   * @param name the name
   * 
   * @author Guillaume Thoreton
   */
  public ProvidersBoursoramaTestCase(String name) {
    super(name);
  } // end of ProvidersBoursoramaTestCase(String name)
  
  /**
   * The main method.
   * 
   * @param args the arguments
   * 
   * @author Guillaume Thoreton
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite());
  } // end of main(Stringp[] args)
  
  /* (non-Javadoc)
   * @see junit.framework.TestCase#setUp()
   */
  @Override
protected void setUp() {
    Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
        public void eventDispatched(AWTEvent event) {
          WindowEvent we = ((WindowEvent) event);
          if (we.getID() == WindowEvent.WINDOW_OPENED)
            we.getWindow().dispose();
        }
      }, AWTEvent.WINDOW_EVENT_MASK);
    //FIXME varProvidersBoursorama = new com.finance.pms.db.ProvidersBoursorama();
  } // end of setUp()
  
  /**
   * Suite.
   * 
   * @return the test
   * 
   * @author Guillaume Thoreton
   */
  public static Test suite() {
    return new TestSuite(ProvidersBoursoramaTestCase.class);
  } // end of suite()
  
  /**
   * Test no methods.
   * 
   * @author Guillaume Thoreton
   */
  public void testNoMethods() {
  }

  /**
   * Test get transactionCurrency.
   * 
   * @author Guillaume Thoreton
   */
  public void testGetCURRENCY() {

    assertTrue("Warning! This is new test method with no real test code inside.", false);

  } // end of testGetCURRENCY()

  /**
   * Test get stock ref name2131046506.
   * 
   * @author Guillaume Thoreton
   */
  public void testGetStockRefName2131046506() {

    assertTrue("Warning! This is new test method with no real test code inside.", false);

  } // end of testGetStockRefName2131046506(com.finance.pms.db.Stock)

  /**
   * Test get tickers21310465066557527865575278.
   * 
   * @author Guillaume Thoreton
   */
  public void testGetTickers21310465066557527865575278() {

    assertTrue("Warning! This is new test method with no real test code inside.", false);

  } // end of testGetTickers21310465066557527865575278( ... )

  /**
   * Test retreive stock list from cmd line65821278.
   * 
   * @author Guillaume Thoreton
   */
  public void testRetreiveStockListFromCmdLine65821278() {

    assertTrue("Warning! This is new test method with no real test code inside.", false);

  } // end of testRetreiveStockListFromCmdLine65821278(java.util.List)

  /**
   * Test retreive stock list from file1195259493.
   * 
   * @author Guillaume Thoreton
   */
  public void testRetreiveStockListFromFile1195259493() {

    assertTrue("Warning! This is new test method with no real test code inside.", false);

  } // end of testRetreiveStockListFromFile1195259493(java.lang.String)

  /**
   * Test retreive stock list from web.
   * 
   * @author Guillaume Thoreton
   */
  public void testRetreiveStockListFromWeb() {

    assertTrue("Warning! This is new test method with no real test code inside.", false);

  } // end of testRetreiveStockListFromWeb()

  /**
   * Test validate stocks513722028.
   * 
   * @author Guillaume Thoreton
   */
  public void testValidateStocks513722028() {

    assertTrue("Warning! This is new test method with no real test code inside.", false);

  } // end of testValidateStocks513722028(com.finance.pms.db.StockList)

} // end of ProvidersBoursoramaTestCase
