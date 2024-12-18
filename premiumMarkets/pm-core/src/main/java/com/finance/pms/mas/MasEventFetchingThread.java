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
package com.finance.pms.mas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventState;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.threads.SourceClient;



// TODO: Auto-generated Javadoc
/**
 * The Class MasEventFetchingThread.
 * 
 * @author Guillaume Thoreton
 */
public class MasEventFetchingThread implements Runnable { //extends MyRunnable {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(MasEventFetchingThread.class);
	
	/** The period. */
	private String period;
	
	/** The stock. */
	private Stock stock;
	
	/** The datedeb. */
	private String datedeb;
	
	/** The datefin. */
	private String datefin;
	
	private String analyseName;
	
	/** The thread semaphore. */
//	private ThreadSemaphore threadSemaphore;
	
	/** The event queue. */
	private Queue eventQueue;
	
	/** The jms template. */
	private JmsTemplate jmsTemplate;
	
	/** The nbretry. */
	private int nbretry;
	
	/**
	 * Instantiates a new mas event fetching thread.
	 * 
	 * @param periodType the period type
	 * @param stock the stock
	 * @param datedeb the datedeb
	 * @param datefin the datefin
	 * @param threadSemaphore the thread semaphore
	 * @param eventQueue the event queue
	 * @param jmsTemplate the jms template
	 * 
	 * @author Guillaume Thoreton
	 */
	public MasEventFetchingThread(String periodType, Stock stock, String datedeb, String datefin, String analyseName, Queue eventQueue, JmsTemplate jmsTemplate) {

		this.period = periodType;
		this.stock = stock;
		this.datedeb = datedeb;
		this.datefin = datefin;
		
		//this.threadSemaphore=threadSemaphore;
		this.eventQueue=eventQueue;
		this.jmsTemplate=jmsTemplate;
		
		nbretry = 0;

	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
		MasConnection c = MasSource.getConnectionFromPool();
		SymbolEvents symbolResults = null;
		
		try {
			while (symbolResults == null) {
				try {
					symbolResults = MasSource.getInstance().sendEventDataRequest(period,stock,datedeb,datefin,c,analyseName);
				} catch (RestartServerException e) { //recoverable
					if (nbretry < 3) {
						nbretry++;
						//symbolResults = new SymbolEvents(stock,new HashMap<EventKey, EventValue>(),new HashMap<Integer, String>(),EventState.STATE_TORETRY);
						if (LOGGER.isDebugEnabled()) LOGGER.debug("To retry "+stock+". On server port "+ c.get_portNumber());
						c = MasSource.restartConnection(c);
					}
					if (nbretry == 3) {
						symbolResults = 
							new SymbolEvents(stock,
									new ConcurrentSkipListMap<EventKey, EventValue>(),
									new ArrayList<String>(),
									EventState.STATE_ABORTEDRETRIED);
						if (LOGGER.isDebugEnabled()) LOGGER.debug("Aborted after "+nbretry+ " retry "+stock+". On server port "+ c.get_portNumber());
						throw new RuntimeException("Aborted after "+nbretry+ " retry "+stock+". On server port "+ c.get_portNumber());
					}
				} catch (ApplicativeException e) { //MAS : applicative non recoverable
					symbolResults = new SymbolEvents(stock,
							new ConcurrentSkipListMap<EventKey, EventValue>(),
							new ArrayList<String>(),
							EventState.STATE_ABORTED);
					if (LOGGER.isDebugEnabled()) LOGGER.debug("Aborted (MAS applicative) "+stock+". On server port "+ c.get_portNumber());
				}
			}
		} catch (IOException e) { //unrecoverable
			symbolResults = new SymbolEvents(stock,
					new ConcurrentSkipListMap<EventKey, EventValue>(),
					new ArrayList<String>(), 
					EventState.STATE_ABORTED);
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Aborted "+stock+". On server port "+ c.get_portNumber(),e);
			throw new RuntimeException(e);
		} finally {
			MasSource.realesePoolConnection(c);
			//this.threadSemaphore.release();
			if (null != symbolResults && null != symbolResults.getDataResultMap() 
					&& symbolResults.getDataResultMap().size() != 0) {
				this.sendEvent(symbolResults);
			}
		}
		LOGGER.info("Just finished "+stock+". On server port "+ c.get_portNumber());
	}
	
	/**
	 * Run.
	 * 
	 * @param thId the th id
	 * @param hC the h c
	 * 
	 * @author Guillaume Thoreton
	 */
	public void run(long thId, SourceClient... hC) {
		throw new UnsupportedOperationException("New thread policy ... Fix me :-)");
	}

	/**
	 * Abort.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void abort() {
		throw new UnsupportedOperationException("New thread policy ... Fix me :-)");

	}
	
	/**
	 * Send event.
	 * 
	 * @param event the event
	 * 
	 * @author Guillaume Thoreton
	 */
	private void sendEvent(final SymbolEvents event) {

		try {

			//EventDataThread.ts.acquire();
			jmsTemplate.send(eventQueue, new MessageCreator() {

				public Message createMessage(Session session) throws JMSException {

					Message message = session.createObjectMessage(event);

					// Source
					message.setObjectProperty("AnalyseSource", "mas");
					// Bearish or Bullish
					List<EventValue> l = event.getSortedDataResultList();
					EventValue e = l.get(l.size() - 1);
					message.setObjectProperty("Trend", e.getEventType().name());
					//FIXME!!!
					EventsResources.getInstance().crudCreateEvents(event, "");
					return message;
				}
			});

		} catch (JmsException e) {
			LOGGER.error("******** ERROR sending event " + event.toEMail(),e);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.stock.toString();
	}
}