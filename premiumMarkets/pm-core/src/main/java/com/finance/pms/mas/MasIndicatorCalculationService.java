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
package com.finance.pms.mas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Observer;
import java.util.SortedMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.IndicatorsCalculationService;



// TODO: Auto-generated Javadoc
/**
 * The Class MasIndicatorCalculationService.
 * 
 * @author Guillaume Thoreton
 */
public class MasIndicatorCalculationService extends IndicatorsCalculationService {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(MasIndicatorCalculationService.class);
	
	/** The mas source. */
	private  MasSource masSource;
	
	//public MasIndicatorCalculationService(ConnectionFactory connectionFactory, Queue eventQueue) {
	/**
	 * Instantiates a new mas indicator calculation service.
	 * 
	 * @param jmsTemplate the jms template
	 * @param eventQueue the event queue
	 * 
	 * @author Guillaume Thoreton
	 */
	public MasIndicatorCalculationService(JmsTemplate jmsTemplate, Queue eventQueue) {
		super();
		this.eventQueue = eventQueue;
		//this.connectionFactory = connectionFactory;
		//this.jmsTemplate = new JmsTemplate102(connectionFactory, false);
		this.jmsTemplate = jmsTemplate;
		this.jmsTemplate.setSessionTransacted(true);
		this.jmsTemplate.setExplicitQosEnabled(true);

	}
	

//	/* (non-Javadoc)
//	 * @see com.finance.pms.events.calculation.IndicatorCalculationService#analyze(java.lang.String, java.util.Date, java.util.Date, java.util.Collection)
//	 */
//	@Override
//	public void analyze(String periodType, Date datedeb, Date datefin, Collection<Stock> symbols, String analyseName,Boolean keepCache, Integer pass, Boolean export) throws InvalidAlgorithmParameterException {
//		super.analyze(periodType, datedeb, datefin, symbols,analyseName,keepCache, pass, export);
//		
//	}
	
	/* (non-Javadoc)
	 * @see com.finance.pms.events.calculation.IndicatorCalculationService#analyseSymbolCollection(java.lang.String, java.util.Date, java.util.Date, java.util.Collection)
	 */
	@Override
	public Map<Stock,Map<EventInfo, SortedMap<Date, double[]>>> analyseSymbolCollection(
			Collection<Stock> symbols, Date datedeb, Date datefin, Currency calculationCurrency, 
			String analyseName,String periodType, Boolean keepCache, Integer passNumber, Boolean export, Boolean p, String passOneCalcMode, Observer...observers) {

		//		String dateDeb = "now - 30 day";
		//		String dateFin = "now";
		
		//default dates and start date offset shift
		if (null == datedeb || null == datefin) {
			datefin = new Date();
			datedeb = new Date();
		} 
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(datedeb);
			c.add(Calendar.DAY_OF_YEAR, new Integer("-"+MainPMScmd.getPrefs().get("mas.daysbackwardday","30")));
			datedeb = c.getTime();
		} catch (NumberFormatException e) {
			LOGGER.error("Invalid number of days back ward : "+"-"+MainPMScmd.getPrefs().get("mas.daysbackwardday","30"),e);
		}
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String dateDeb = df.format(datedeb);
		String dateFin = df.format(datefin);
		
		MasSource.getInstance().setInitAnalyzeDates(dateDeb,dateFin);
		LOGGER.debug("Mas calculation date range : from "+datedeb+" to "+datefin);
		
//		ThreadSemaphore ts =
//			ThreadSemaphore.getNewSemaphore(symbols.size(),(new Integer(MainPMScmd.prefs.get("mas.semaphore.nbthread","5"))));
		
		ExecutorService executor = Executors.newFixedThreadPool(new Integer(MainPMScmd.getPrefs().get("mas.semaphore.nbthread","5")));
		for (Stock stock : symbols) {
			
			if (null != stock.getSymbol() && stock.getSymbol() != Stock.MISSINGCODE) {
				LOGGER.guiInfo("Mas analyse : Launching analyze of stock : " + stock.getName() + "(" + stock.getSymbol() + ")");
//				try {
//					ts.acquire();
//					MasEventFetchingThread th = 
//						new MasEventFetchingThread(periodType, stock, dateDeb, dateFin,analyseName, ts, this.eventQueue, this.jmsTemplate);
//
//					Thread masReadThread = new Thread(th);
//					masReadThread.start();
					//FIXME
					executor.execute(new MasEventFetchingThread(periodType, stock, dateDeb, dateFin,analyseName, null, this.eventQueue, this.jmsTemplate));
//				} catch (InterruptedException e) {
//					LOGGER.debug("",e);
//				}
			} else {
				LOGGER.warn("No symbol for stock : "+stock.toString());
			}
			
		}
		executor.shutdown();
		
		try {
			executor.awaitTermination(0, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	

//	@Override
//	public void sendEventsProcessingMessage(String signalAnalyseName, String eventAnalyseName,Date sd, Date ed) {
//		throw new UnsupportedOperationException();
//		
//	}


//	/**
//	 * Sort events by more buy.
//	 * 
//	 * @param events the events
//	 * 
//	 * @return the array list< symbol events>
//	 * 
//	 * @author Guillaume Thoreton
//	 */
//	@Deprecated
//	public ArrayList<SymbolEvents> sortEventsByMoreBuy(Collection<?> events) {
//		ArrayList<SymbolEvents> retour = new ArrayList<SymbolEvents>();
//		Iterator<?> eventsIt = events.iterator();
//		while (eventsIt.hasNext()) {
//			SymbolEvents se = (SymbolEvents) eventsIt.next();
//			retour = se.sortedInsertIn(new DefaultPonderationRule(),retour);
//		}
//		return retour;
//		
//	}
	
	/**
	 * Gets the mas source.
	 * 
	 * @return the mas source
	 */
	public MasSource getMasSource() {
		return masSource;
	}


	/**
	 * Sets the mas source.
	 * 
	 * @param masSource the new mas source
	 */
	public void setMasSource(MasSource masSource) {
		this.masSource = masSource;
	}

}
