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
package com.finance.pms.datasources;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.http.HttpException;
import org.easymock.EasyMock;
import org.eclipse.swt.widgets.Display;

import com.finance.pms.MainPMScmd;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.web.MarketListProvider;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.events.calculation.IndicatorsCalculationService;
import com.finance.pms.events.gui.EventsComposite;

/**
 * The Class EventRefreshControllerTest.
 * 
 * @author Guillaume Thoreton
 */
public class EventRefreshControllerTest extends TestCase {
	
	//TODO: junit and multi thread
	
	/** The providers. */
	Providers providers;
	
	/** The quotation update. */
	QuotationUpdate quotationUpdate;
	
	/** The spring context. */
	SpringContext springContext;
	
	/** The indicator calculation service. */
	IndicatorsCalculationService indicatorCalculationService; 
	
	/** The events composite. */
	EventsComposite eventsComposite;
	
	/** The display. */
	Display display;
	
	/** The refresh model. */
	@SuppressWarnings("rawtypes")
	EventModel refreshModel;

	
	//default
	/** The monitored only. */
	@SuppressWarnings("rawtypes")
	EventModelStrategyEngine monitoredOnly = new RefreshAllEventStrategyEngine();
	
	/** The start analyse date. */
	//Date startAnalyseDate = IndicatorCalculationServiceMain.getDateMoinsNJours(EventSignalConfig.getNewDate(),15);
	//FIXME
	Date startAnalyseDate = EventSignalConfig.getNewDate();
	
	/** The dofetch list of quotes. */
	Boolean dofetchListOfQuotes = true;
	
	/** The dofetch quotes. */
	Boolean dofetchQuotes = false;
	
	/** The do analyse. */
	Boolean doAnalyse = true;

	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		MainPMScmd.getMyPrefs().put("gui.crit.date", new SimpleDateFormat("yyyy/MM/dd").format(startAnalyseDate));
		MainPMScmd.getMyPrefs().put("quotes.lastlistfetch","1970/01/01");
		MainPMScmd.getMyPrefs().put("quotes.lastfetch", "1970/01/01");
		MainPMScmd.getMyPrefs().put("quotes.lastanalyse", "1970/01/01");
		
		providers = EasyMock.createMock(Providers.class);
		springContext = EasyMock.createMock(SpringContext.class);
		indicatorCalculationService = EasyMock.createMock(IndicatorsCalculationService.class);
		quotationUpdate = EasyMock.createMock(QuotationUpdate.class);
		eventsComposite = EasyMock.createMock(EventsComposite.class);
		display = EasyMock.createMock(Display.class);
		
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("UnCaugth Exception :"+e+"; sent by "+t.getName());
				e.printStackTrace();
			}
			
		});
		
	}

	/**
	 * Test mouse down nothing.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testMouseDownNothing() {

		
		EventRefreshController eventRefreshController = initNotMonitored();
		
		//Do nothing
		dofetchListOfQuotes = false;
		dofetchQuotes = false;
		dofetchListOfQuotes = false;
		doAnalyse = false;

		checkDisplayRefresh();
		
		testCore(eventRefreshController); 
		
		Assert.assertEquals( "1970/01/01", new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastListFetch()));
		Assert.assertEquals( "1970/01/01",  new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastQuotationFetch()));
		Assert.assertEquals( "1970/01/01",  new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastAnalyse()));
		
	}

	/**
	 * Test core.
	 * 
	 * @param eventRefreshController the event refresh controller
	 * 
	 * @author Guillaume Thoreton
	 */
	private void testCore(EventRefreshController eventRefreshController) {
		
		replayMocks();
		
		mouseDown(eventRefreshController);
		
		endTest();
	}
	
	/**
	 * Test mouse down analyse monitored.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testMouseDownAnalyseMonitored() {
			
			////Monitored test
			EventRefreshController eventRefreshController = initMonitored();
			
			//Do nothing
			dofetchListOfQuotes = false;
			dofetchQuotes = false;
			dofetchListOfQuotes = false;
			doAnalyse = true;
			
			//Check Analyse date change
			
			//Check list update
			
			//Check quotations update
			
			//check Analyse
			//checkCallForMonitoredAnalyse();
			
			checkDisplayRefresh();
			
			testCore(eventRefreshController); 
			
			Assert.assertEquals( "1970/01/01", new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastListFetch()));
			Assert.assertEquals( "1970/01/01",  new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastQuotationFetch()));
			Assert.assertEquals(   "1970/01/01",new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastAnalyse()));
			
		}


	/**
	 * Inits the monitored.
	 * 
	 * @return the event refresh controller
	 * 
	 * @author Guillaume Thoreton
	 */
	@SuppressWarnings("unchecked")
	private EventRefreshController initMonitored() {
		monitoredOnly = new RefreshMonitoredStrategyEngine();
		//FIXME
		//refreshModel = new EventModel<EventModelStrategyEngine>(startAnalyseDate, null);
		EventRefreshController eventRefreshController = new EventRefreshController(refreshModel, eventsComposite, null);
		return eventRefreshController;
	}
	
	/**
	 * Test mouse down analyse all.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testMouseDownAnalyseAll() {
		
		EventRefreshController eventRefreshController = initNotMonitored();
		
		//Do nothing
		dofetchListOfQuotes = false;
		dofetchQuotes = false;
		dofetchListOfQuotes = false;
		doAnalyse = true;
		
		//Check Analyse date change
		//Check list update
		//Check quotations update
		
		//check Analyse
		checkCallForAnalyse();
		
		checkDisplayRefresh();
		
		testCore(eventRefreshController);
		
		Assert.assertEquals( "1970/01/01", new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastListFetch()));
		Assert.assertEquals( "1970/01/01",  new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastQuotationFetch()));
		Assert.assertEquals(  new SimpleDateFormat("yyyy/MM/dd").format(new Date()), new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastAnalyse()));
		
	}
	
	/**
	 * Test mouse down quotation monitored.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void testMouseDownQuotationMonitored() {
		
		EventRefreshController eventRefreshController = initMonitored();
		
		dofetchQuotes = true;
		dofetchListOfQuotes = false;
		doAnalyse = false;
		
		//Check Analyse date change
		//Check list update
		//Check quotations update
		checkCallForMonitoredQuotation();
		
		//checkCallForMonitoredAnalyse();
		
		checkDisplayRefresh();
		
		testCore(eventRefreshController);
		
		Assert.assertEquals( "1970/01/01", new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastListFetch()));
		Assert.assertEquals( "1970/01/01",  new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastQuotationFetch()));
		Assert.assertEquals( "1970/01/01", new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastAnalyse()));
		
	}


	private void checkCallForMonitoredQuotation() {
		//FIXME
		//refreshModel.setQuotationUpdate(quotationUpdate);
		
		quotationUpdate.getQuotesForMonitored();
		org.easymock.EasyMock.expectLastCall().times(1);
	}

	public void testMouseDownListShareAll() throws HttpException {
		
		EventRefreshController eventRefreshController = initNotMonitored();
		
		dofetchListOfQuotes = true;
		dofetchQuotes = false;
		doAnalyse = false;
		
		//Check Analyse date change
		
		//Check list update
		checkCallForListShareUpdate();
		
		//Check quotations update
		checkCallForQuotations();
		
		//check Analyse
		checkCallForAnalyse();
		
		checkDisplayRefresh();
		
		testCore(eventRefreshController);
		
		Assert.assertEquals( new SimpleDateFormat("yyyy/MM/dd").format(new Date()), new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastListFetch()));
		Assert.assertEquals( new SimpleDateFormat("yyyy/MM/dd").format(new Date()),  new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastQuotationFetch()));
		Assert.assertEquals(new SimpleDateFormat("yyyy/MM/dd").format(new Date()), new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastAnalyse()));
		
	}

	private void checkCallForListShareUpdate() throws HttpException {
		org.easymock.EasyMock.expect(springContext.getBean((String)org.easymock.EasyMock.anyObject())).andStubReturn(providers);
		((MarketListProvider) providers).updateStockListFromWeb((MarketQuotationProviders) org.easymock.EasyMock.anyObject());
		org.easymock.EasyMock.expectLastCall().times(1);
	}
	

public void testMouseDownChangeDateAll() {
		
		////Monitored test
		EventRefreshController eventRefreshController = initNotMonitored();
		
		//startAnalyseDate = IndicatorCalculationServiceMain.getDateMoinsNJours(new Date(),16);
		//FIXME
		startAnalyseDate = EventSignalConfig.getNewDate();
		dofetchListOfQuotes = false;
		dofetchQuotes = false;
		doAnalyse = false;
		
		//check Analyse	
		checkCallForAnalyse();
		
		checkDisplayRefresh();
		
		testCore(eventRefreshController); 
		
		Assert.assertEquals( "1970/01/01", new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastListFetch()));
		Assert.assertEquals(  "1970/01/01",  new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastQuotationFetch()));
		Assert.assertEquals(new SimpleDateFormat("yyyy/MM/dd").format(new Date()), new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastAnalyse()));
		
	}

private void checkDisplayRefresh() {
	org.easymock.EasyMock.expect(eventsComposite.getDisplay()).andReturn(display).times(1);
	display.syncExec((Runnable)org.easymock.EasyMock.anyObject());
	org.easymock.EasyMock.expectLastCall().times(1);
}

private void checkCallForAnalyse() {
//FIXME
	org.easymock.EasyMock.expect(springContext.getBean("talib")).andReturn(indicatorCalculationService);
//	try {
//		indicatorCalculationService.fullAnalyze((Date) EasyMock.anyObject(), (Date) EasyMock.anyObject(), Currency.EUR, "test", (String) EasyMock.anyObject(), false, 1, "force");
//	} catch (InvalidAlgorithmParameterException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	
	org.easymock.EasyMock.expectLastCall().times(1);
}

private void checkCallForQuotations() {
	//FIXME
	//refreshModel.setQuotationUpdate();
	//quotationUpdate.getQuotesForCurrentListInDB();
	org.easymock.EasyMock.expectLastCall().times(1);
}

@SuppressWarnings("unchecked")
private EventRefreshController initNotMonitored() {
	monitoredOnly = new RefreshAllEventStrategyEngine();
	//FIXME
	//refreshModel = new EventModel<EventModelStrategyEngine>(startAnalyseDate, null);
	EventRefreshController eventRefreshController = new EventRefreshController(refreshModel,eventsComposite, null);
	return eventRefreshController;
}

private void threadMessWait() {
	synchronized (display) {
		try {
			//System.out.println("Nb of THreads remaining : "+Thread.currentThread().getThreadGroup().activeCount());
			Thread[] list = new Thread[100];
			Thread.currentThread().getThreadGroup().enumerate(list, true);
			for (int i=0;i < list.length;i++) {
				System.out.println("Nb of THreads remaining : "+Thread.currentThread().getThreadGroup().activeCount());
				if (list[i] != null ) System.out.println("Thread info : "+ list[i].getName());
				//System.out.println("Main Thread info : "+ Thread.currentThread().getName());
				while (list[i] != null && 
						!list[i].equals(Thread.currentThread()) && !"ReaderThread".equals(list[i].getName()) && !"Timer-0".equals(list[i].getName()) &&
						list[i].isAlive()) {
					System.out.println("Thread alive : "+ list[i].getName());
					display.wait(100);
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

@SuppressWarnings("unchecked")
public void testCompleteAll() throws HttpException {
	
	EasyMock.checkOrder(quotationUpdate, true);
	
	////Monitored test
	//FIXME
	//refreshModel = new EventModel<EventModelStrategyEngine>(startAnalyseDate, null);
	EventRefreshController eventRefreshController = new EventRefreshController(refreshModel,eventsComposite, null);
	
	//Nothing
	checkDisplayRefresh();
	
	//check update monitored but analyze
	checkCallForMonitoredQuotation();
	//checkCallForMonitoredAnalyse();
	checkDisplayRefresh();
	
	//check list share all
	checkCallForListShareUpdate();
	checkCallForQuotations();
	checkCallForAnalyse();
	checkDisplayRefresh();
	
	//check quotation all
	//checkCallForQuotations();
	//checkCallForAnalyse();
	checkDisplayRefresh();
	
	//check analyze all
	//checkCallForAnalyse();
	checkDisplayRefresh();
	
	replayMocks();
	
	//Nothing
	//startAnalyseDate = IndicatorCalculationServiceMain.getDateMoinsNJours(new Date(),16);
	monitoredOnly = new RefreshAllEventStrategyEngine();
	dofetchListOfQuotes = false;
	dofetchQuotes = false;
	doAnalyse = false;
	
	testCoreBiss(eventRefreshController);
	
	threadMessWait();
		
	Assert.assertEquals( "1970/01/01", new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastListFetch()));
	Assert.assertEquals(  "1970/01/01",  new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastQuotationFetch()));
	Assert.assertEquals("1970/01/01", new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastAnalyse()));
	
	
	//check update monitored but analyze
	//startAnalyseDate = IndicatorCalculationServiceMain.getDateMoinsNJours(new Date(),16);
	monitoredOnly = new RefreshMonitoredStrategyEngine();
	dofetchListOfQuotes = true;
	dofetchQuotes = true;
	doAnalyse = false;
	
	testCoreBiss(eventRefreshController);
	
	threadMessWait();
	
	Assert.assertEquals( "1970/01/01", new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastListFetch()));
	Assert.assertEquals( "1970/01/01",  new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastQuotationFetch()));
	Assert.assertEquals("1970/01/01", new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastAnalyse()));
	
	//check list share all
	//startAnalyseDate = IndicatorCalculationServiceMain.getDateMoinsNJours(new Date(),16);
	monitoredOnly = new RefreshAllEventStrategyEngine();
	dofetchListOfQuotes = true;
	dofetchQuotes = false;
	doAnalyse = false;

	
	testCoreBiss(eventRefreshController);
	
	threadMessWait();
	
	Assert.assertEquals( new SimpleDateFormat("yyyy/MM/dd").format(new Date()), new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastListFetch()));
	Assert.assertEquals( new SimpleDateFormat("yyyy/MM/dd").format(new Date()),  new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastQuotationFetch()));
	Assert.assertEquals(new SimpleDateFormat("yyyy/MM/dd").format(new Date()), new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastAnalyse()));
	
	//check quotation all
	//startAnalyseDate = IndicatorCalculationServiceMain.getDateMoinsNJours(new Date(),16);
	monitoredOnly = new RefreshAllEventStrategyEngine();
	dofetchListOfQuotes = false;
	dofetchQuotes = true;
	doAnalyse = false;

	testCoreBiss(eventRefreshController);
	
	threadMessWait();
	
	Assert.assertEquals( new SimpleDateFormat("yyyy/MM/dd").format(new Date()), new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastListFetch()));
	Assert.assertEquals( new SimpleDateFormat("yyyy/MM/dd").format(new Date()),  new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastQuotationFetch()));
	Assert.assertEquals( new SimpleDateFormat("yyyy/MM/dd").format(new Date()), new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastAnalyse()));
	
	//check analyse all
	//startAnalyseDate = IndicatorCalculationServiceMain.getDateMoinsNJours(new Date(),16);
	monitoredOnly = new RefreshAllEventStrategyEngine();
	dofetchListOfQuotes = false;
	dofetchQuotes = false;
	doAnalyse = true;
	
	testCoreBiss(eventRefreshController);
	
	threadMessWait();
	
	Assert.assertEquals( new SimpleDateFormat("yyyy/MM/dd").format(new Date()), new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastListFetch()));
	Assert.assertEquals( new SimpleDateFormat("yyyy/MM/dd").format(new Date()),  new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastQuotationFetch()));
	Assert.assertEquals(new SimpleDateFormat("yyyy/MM/dd").format(new Date()), new SimpleDateFormat("yyyy/MM/dd").format(eventRefreshController.eventModel.getLastAnalyse()));

	
	endTest();

	}

/**
 * Test core biss.
 * 
 * @param eventRefreshController the event refresh controller
 * 
 * @author Guillaume Thoreton
 */
private void testCoreBiss(EventRefreshController eventRefreshController) {
	
	//checkDisplayRefresh();
	
	//replayMocks();
	
	mouseDown(eventRefreshController);
	
	//threadMessWait();
	
	//endTest();
	
}

/**
 * Replay mocks.
 * 
 * @author Guillaume Thoreton
 */
private void replayMocks() {
	EasyMock.replay(providers);
	EasyMock.replay(quotationUpdate);
	EasyMock.replay(springContext);
	EasyMock.replay(indicatorCalculationService); 
	EasyMock.replay(display);
	EasyMock.replay(eventsComposite);
}

/**
 * End test.
 * 
 * @author Guillaume Thoreton
 */
private void endTest() {
	threadMessWait();
	
	EasyMock.verify(providers);
	EasyMock.verify(quotationUpdate);
	EasyMock.verify(indicatorCalculationService);
	
}

/**
 * Mouse down.
 * 
 * @param eventRefreshController the event refresh controller
 * 
 * @author Guillaume Thoreton
 */
private void mouseDown(EventRefreshController eventRefreshController) {
	//FIXME eventRefreshController.updateEventRefreshModelState(dofetchListOfQuotes, dofetchQuotes, doAnalyse, false, false, false, 0l);
	//eventRefreshController.mouseDown((MouseEvent) org.easymock.EasyMock.anyObject());
}


}
