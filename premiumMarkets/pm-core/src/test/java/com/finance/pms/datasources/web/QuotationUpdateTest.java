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
package com.finance.pms.datasources.web;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import junit.framework.TestCase;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.easymock.EasyMock;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.finance.pms.admin.install.logging.Messages;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.Query;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.formaters.StockComplementBoursoFormater;
import com.finance.pms.threads.PoolSemaphore;


// TODO: Auto-generated Javadoc
/**
 * The Class QuotationUpdateTest.
 * 
 * @author Guillaume Thoreton
 */
@SuppressWarnings("all")
@RunWith(PowerMockRunner.class)
@PrepareForTest( { HttpSourceBoursorama.class,MyLogger.class })
public class QuotationUpdateTest extends TestCase {

	/** The database tester. */
	private IDatabaseTester databaseTester;
	
	/** The quotation up date. */
	private QuotationUpdate quotationUpDate;
	
	/** The http source mock. */
	private HttpSourceBoursorama httpSourceMock;
	
	/** The pool semaphore mock. */
	private PoolSemaphore poolSemaphoreMock;

	/** The listOShares stock from web. */
	private List<Validatable> listStockFromWeb = new ArrayList<Validatable>();

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();

//		// Mocks
//		httpSourceMock = EasyMock.createMock(HttpSourceBoursorama.class);
//		poolSemaphoreMock = EasyMock.createMock(PoolSemaphore.class);
////		providersBoursoramaMock = EasyMock.createMock(ProvidersBoursorama.class,new Method[] { ProvidersBoursorama.class.getMethod("getTickers",
////				new Class[] {Stock.class, Date.class, Date.class}) } );
////		dateMock = EasyMock.createMock(Date.class,new Method[] {Date.class.getMethod("getTime",new Class[] {})});
////		providersBoursoramaMock = EasyMock.createMock(ProvidersBoursorama.class,new Method[] { ProvidersBoursorama.class.getMethod("moisParMois",
////				new Class[] {Stock.class, Date.class, Date.class, MyHttpClient.class}) } );
//
//		// DB init
//		//FIXME Test 
////		HttpSource.setSingleton(httpSourceMock);
////		quotationUpDate = new QuotationUpDate(new File("testdb.properties").getAbsolutePath(), "boursorama");
//
//		// Init Hypersonic db => modify hsql schema.script generated to create tables
//		databaseTester = new JdbcDatabaseTester("org.hsqldb.jdbcDriver", 
//				"jdbc:hsqldb:/usr/local/opt/USERDATA/hypersonic/mov_shares", "SA", "");
//		IDataSet dtdSet = new FlatDtdDataSet(new FileInputStream("tests/dunit.dtd"));
//		IDataSet initialDataSet = new FlatXmlDataSet(new FileInputStream("tests/initialDataSet.xml"));
//		databaseTester.setDataSet(dtdSet);
//		databaseTester.setDataSet(initialDataSet);
//		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
//		databaseTester.onSetup();
//
//		// base
//		// Update data in data set
//		Calendar c = GregorianCalendar.getInstance();
//		c.setTime(new Date());
//		c.add(Calendar.DATE, -5);
//		MyDBConnection mdbc = DataSource.getConnectionFromPool();
//		PreparedStatement ps = mdbc.getConn().prepareStatement("UPDATE LOOKUP set lastquote = ?");
//		ps.setDate(1, new java.sql.Date(c.getTimeInMillis()));
//		ps.execute();
//		ps.close();
//		c.add(Calendar.MONTH, -3);
//		ps = mdbc.getConn().prepareStatement("UPDATE LOOKUP set lastquote = ? where symbol = 'Symbol0.Is' or symbol = 'Symbol1.Is'");
//		ps.setDate(1, new java.sql.Date(c.getTimeInMillis()));
//		ps.execute();
//		ps.close();
//		DataSource.realesePoolConnection(mdbc);
		
//		// web
//		// Stock 0 was not retrieved and out of dated should be deleted
//		for (int i = 1; i < 5; i++) { //Stock 1 is obsolete but retrieved so will be kept
//			Stock s = new Stock( "Isin" + i,"Symbol" + i, null, "Name" + i, true, StockCategories.DEFAULT_CATEGORY,ProvidersTypes.BOURSORAMA);
//			listStockFromWeb.add(s);
//		}
//		// Stock 5 was not retrieved
//		// Stock 6 Isin changed
//		listStockFromWeb.add(new Stock( "IsinNew", "Symbol6",null, "Name6", true, StockCategories.DEFAULT_CATEGORY,ProvidersTypes.BOURSORAMA));
//		// Stock 7 symbol changed
//		listStockFromWeb.add(new Stock( "Isin7", "newSymbol",null, "Name7", true, StockCategories.DEFAULT_CATEGORY,ProvidersTypes.BOURSORAMA));
//		// Stock 8 name changed
//		listStockFromWeb.add(new Stock(  "Isin8","Symbol8", null, "newName", true, StockCategories.DEFAULT_CATEGORY,ProvidersTypes.BOURSORAMA));
//		// Stock 9 name and symbol changed
//		listStockFromWeb.add(new Stock( "Isin9","newSymbolAndName",  null, "newSymbolAndName", true, StockCategories.DEFAULT_CATEGORY,ProvidersTypes.BOURSORAMA));
//		// new Stock 10
//		listStockFromWeb.add(new Stock("Isin10",  "Symbol10", null, "Name10", true, StockCategories.DEFAULT_CATEGORY,ProvidersTypes.BOURSORAMA));

	}

	/**
	 * Test get quotes all.
	 * 
	 * @throws SQLException the SQL exception
	 * @throws Exception the exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testGetQuotesAll() throws SQLException, Exception {

//		EasyMock.expect(httpSourceMock.getCategoryStockListURL((StockCategories) EasyMock.anyObject(), (String) EasyMock.anyObject(), (String) EasyMock.anyObject(), (String) EasyMock.anyObject())).andStubReturn(
//				"");
//		EasyMock.expect(httpSourceMock.getThreadPool()).andStubReturn(poolSemaphoreMock);
//		EasyMock.expect(poolSemaphoreMock.getResource()).andStubReturn(new SimpleHttpClient());
//		poolSemaphoreMock.releaseResource((SourceClient) EasyMock.anyObject());
//		EasyMock.expectLastCall().anyTimes();
//		EasyMock.expect(httpSourceMock.getStockInfoPageURL((String) EasyMock.anyObject())).andStubReturn("");
//		EasyMock.expect(
//				httpSourceMock.getStockQuotationURL((String) EasyMock.anyObject(), (String) EasyMock.anyObject(), (String) EasyMock.anyObject(), (String) EasyMock.anyObject(), (String) EasyMock.anyObject(),
//						(String) EasyMock.anyObject(), (String) EasyMock.anyObject())).andStubReturn("");
		
		// return from get listOShares of all stocks form web
		//FIXME
		//StockListBoursormaFormater lsfdef = new StockListBoursormaFormater("",StockCategories.DEFAULT_CATEGORY, new MarketQuotationProvider(MarketQuotationsProviders.BOURSORAMA,"UNKNOWN"));
		//EasyMock.expect(httpSourceMock.readURL(EasyMock.eq(lsfdef))).andReturn(listStockFromWeb).times(1);
		
		//StockListBoursormaFormater lsfind = new StockListBoursormaFormater("",StockCategories.INDICES_EURONEXT, new MarketQuotationProvider(MarketQuotationsProviders.BOURSORAMA,"UNKNOWN"));
		//EasyMock.expect(httpSourceMock.readURL(EasyMock.eq(lsfind))).andReturn(new ArrayList<Validatable>());
		
		List fromBase = new ArrayList<Stock>();
		fromBase.addAll(DataSource.getInstance().loadStocksForCurrentShareList());
		fromBase.addAll(listStockFromWeb);
		// return for every checked stock
		for (final Validatable v : (List<Validatable>) fromBase) {
			//Complements simu
			v.setState(Validatable.VALID);
			List<Validatable> parseRes = new ArrayList<Validatable>();
			parseRes.add(v);
			StockComplementBoursoFormater scf = new StockComplementBoursoFormater("", (Stock) v);
			EasyMock.expect(httpSourceMock.readURL(EasyMock.eq(scf))).andReturn(parseRes);
			
			//Quotations simu
			Stock s = new Stock((Stock)v) {
				@Override
				public Query toDataBase() {
					Query iq = new Query();
					iq.addValue(null);
					iq.addValue(((Stock)v).getName());
					iq.addValue(new Date("10/12/2007"));
					iq.addValue(1);
					iq.addValue(1);
					iq.addValue(1);
					iq.addValue(1);
					iq.addValue(1);
					iq.addValue("EUR");
					iq.addValue(((Stock)v).getSymbol());
					iq.addValue(((Stock)v).getIsin());
					return iq;
				}
			}; 
			List<Validatable> parseRes2 = new ArrayList<Validatable>();
			parseRes2.add(s);
			//FIXME DayQuoteBoursoramaFormater sdf  = new DayQuoteBoursoramaFormater(new MyUrl(),s, "EUR");
			//EasyMock.expect(httpSourceMock.readURL(EasyMock.eq(sdf), (MyHttpClient) EasyMock.anyObject()))
			//	.andStubReturn(parseRes2);
		}

		// getQuotes
		// TODO Validate date issues wish are mocked here
		EasyMock.replay(httpSourceMock);
		EasyMock.replay(poolSemaphoreMock);
	

		// Execute
		// TODO validate stoks from cmd and file
		//FIXME quotationUpDate.getQuotesAll("");

		// Verify
		IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("LOOKUP");

		IDataSet expectedDataSet = new FlatXmlDataSet(new File("tests/expectedDataSet.xml"));
		ITable expectedTable = expectedDataSet.getTable("LOOKUP");

		ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());
		
		System.out.println("Results :");
		for (int i = 0; i < filteredTable.getRowCount(); i++) {
			System.out.println(filteredTable.getValue(i, "symbol")+" ; "+
					filteredTable.getValue(i, "isin")+" ; "+
					filteredTable.getValue(i, "name")+" ; "+
					filteredTable.getValue(i, "lastquote"));
		}
		
		Assertion.assertEquals(expectedTable, filteredTable);

	}
	
	public void testPrintUrl() {
		
		PowerMock.mockStatic(ResourceBundle.class);
		EasyMock.expect(ResourceBundle.getBundle("installloggingmessages")).andReturn(
			new ResourceBundle() {

				@Override
				public Enumeration<String> getKeys() {
					// TODO Auto-generated method stub
					return null;
				}
	
				@Override
				protected Object handleGetObject(String key) {
					// TODO Auto-generated method stub
					return null;
				}
			}
		);
		
		PowerMock.mockStatic(Messages.class);
		EasyMock.expect(Messages.getEncriptedString(EasyMock.anyObject(String.class))).andReturn("toto");
		
		PowerMock.replay(ResourceBundle.class);
		PowerMock.replay(Messages.class);
		
//		HttpSourceBoursorama httpSourceBoursorama = new HttpSourceBoursorama("");
//		httpSourceBoursorama.getStockQuotationURL("ticker", "startYear", "startMonth", "startDay", "endYear", "endMonth", "endDays");
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		databaseTester.onTearDown();
	}

}
