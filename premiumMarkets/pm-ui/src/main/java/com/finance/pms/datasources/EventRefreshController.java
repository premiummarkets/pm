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
package com.finance.pms.datasources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.finance.pms.RefreshableView;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.threads.ConfigThreadLocal;
import com.finance.pms.threads.ObserverMsg;


/**
 * The Class EventRefreshController.
 * 
 * @author Guillaume Thoreton
 */
public class EventRefreshController implements MouseListener, SelectionListener {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(EventRefreshController.class);
	
	protected EventModel<? extends EventModelStrategyEngine> eventModel;
	private RefreshableView view;
	private Config config;


	private Date currentDate;
	
	private Boolean doFetchListStocks;
	private Boolean doFetchQuotes;
	private Boolean doAnalyse;
	private Boolean doReco;
	private Boolean doAnalysisClean;
	private Boolean doAlerts;
	
	private Long taskKey;

	
	
	/**
	 * Instantiates a new event refresh controller.
	 * 
	 * @param refreshModel the refresh model
	 * @param view the view
	 * 
	 * @author Guillaume Thoreton
	 */
	public EventRefreshController(EventModel<? extends EventModelStrategyEngine> refreshModel, RefreshableView view, Config config) {
		
		this.config = config;
		this.view = view;
		this.eventModel = refreshModel;
		try {
			currentDate = new SimpleDateFormat("yyyy/MM/dd").parse(new SimpleDateFormat("yyyy/MM/dd").format((EventSignalConfig.getNewDate())));
		} catch (ParseException e) {
			LOGGER.error("Can't parse "+new SimpleDateFormat("yyyy/MM/dd"),e);
		}
	}
	
	@Override
	public void mouseDoubleClick(MouseEvent arg0) {
	}

	@Override
    public void mouseUp(MouseEvent arg0) {
	}

	@Override
	public void mouseDown(MouseEvent arg0) {
		this.refreshAction(view.getAnalysisStartDate());
	}
	
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		this.refreshAction(view.getAnalysisStartDate());
	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		this.refreshAction(view.getAnalysisStartDate());
	}
	
	
	/**
	 * Update event refresh model state.
	 * 
	 * @param eventModelEngine the event model engine
	 * @param talibSetected the talib setected
	 * @param masSelected the mas selected
	 * @param startAnalyseDate the start analyse date
	 * @param dofetchListOfQuotes the dofetch list of quotes
	 * @param dofetchQuotes the dofetch quotestaskKey
	 * @param doAnalyse the do analyse
	 * 
	 * @author Guillaume Thoreton
	 * @param viewStateParams 
	 */
	public void updateEventRefreshModelState(Boolean dofetchListOfQuotes, Boolean dofetchQuotes, Boolean doAnalyse, Boolean doReco, Boolean doAnalysisClean, Boolean doAlerts, Long taskKey) {
		
		this.doFetchListStocks = dofetchListOfQuotes;
		this.doFetchQuotes = dofetchQuotes;
		this.doAnalyse = doAnalyse;
		this.doReco = doReco;
		this.doAnalysisClean = doAnalysisClean;
		this.doAlerts = doAlerts;
		this.taskKey = taskKey;
		

	}

	/**
	 * Refresh action.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void refreshAction(final Date startAnalyseDate) {
	
		 	final Queue<Runnable> tasks = new LinkedBlockingQueue<Runnable>();
			
			if (doFetchListStocks && isFetchListStocks()) {
				
				eventModel.setLastQuotationFetch(EventModel.DEFAULT_DATE);
				
				//Add Fetch List to the queue
				tasks.offer(new Runnable() {
					public void run() {
						try {
							eventModel.callbackForlastListFetch();
							eventModel.setLastListFetch(currentDate);
						} catch (Exception e) {
							throw new StockListRefreshException(e);
						}
					}
				});
				
			}
			
			if (doReco) {
				//Add Fetch Reco to the queue
				tasks.offer(new Runnable() {
					public void run() {
						try {
							eventModel.callbackForReco();
						} catch (Exception e) {
							throw new RecoRefreshException(e);
						}
					}
				});
				
			}
			
			if (doFetchQuotes && isFetchQuotes()) {
				
				//Add Fetch Quotations
				tasks.offer(new Runnable() {
					public void run() {
						try {
							eventModel.callbackForlastQuotationFetch();
							eventModel.setLastQuotationFetch(currentDate);
							eventModel.resetLastAnalyse();
						} catch (Exception e) {
							throw new QuotatationRefreshException(e);
						}
					}
				});
			}
			
			if (doAnalysisClean) {
				eventModel.resetAnalisysList();
				eventModel.getAnalysisList().add("talib");
				
				//Add clear Analysis
				tasks.offer(new Runnable() {
					public void run() {
						try {
							eventModel.callbackForAnalysisClean();
							eventModel.resetLastAnalyse();
						} catch (Exception e) {
							throw new EventRefreshException(e);
						}
					}
				});
			}
			
			if (doAnalyse) {
				eventModel.resetAnalisysList();
				eventModel.getAnalysisList().add("talib");
				
				//Add Analysis
				tasks.offer(new Runnable() {
					public void run() {
						try {
							eventModel.callbackForlastAnalyse(startAnalyseDate);
							eventModel.setLastAnalyse(currentDate);
						} catch (Exception e) {
							throw new EventRefreshException(e);
						}
					}
				});
			}
			
			if (doAlerts) {
				//Add Alerts calc
				tasks.offer(new Runnable() {
					public void run() {
						try {
							eventModel.callbackForAlerts();
						} catch (Exception e) {
							throw new EventRefreshException(e);
						}
					}
				});
			}
			
//			//XXX ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, new EventSignalConfig()); 
//			//XXX The following should be in an other bean and systematically used instead of the Configs default constructors
//			ShareListMgr shareListMgr = (ShareListMgr) SpringContext.getSingleton().getBean("shareListMgr");
//			final EventSignalConfig config = shareListMgr.propagateConfig();
			
			final List<Exception> exceptions = new ArrayList<Exception>();
			Runnable runnable = new Runnable() {

				@Override
				public void run() {

					Runnable r;
					while ( (r = tasks.poll()) != null) {
						try {
							ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, config);
							r.run();
						} catch (Exception e) {
							LOGGER.warn(e,e);
							exceptions.add(e);
						}
					}
					
					//Notifiy observers
					for (Observer obs : eventModel.getEngineObservers()) {
						obs.update(null, new ObserverMsg(null, ObserverMsg.ObsKey.DONE, taskKey));
					}

					//Update potential registered view when finished
					view.getDisplay().syncExec(new Runnable() {
						public void run() {
							view.endRefreshAction(exceptions);
						}

					});
				}
			};
			Thread thread = new Thread(runnable);
			thread.start();
			
	}
	


	/**
	 * Fetch list of quotes.
	 * 
	 * @return true, if isfetch list of quotes
	 * 
	 * @author Guillaume Thoreton
	 */
	private Boolean isFetchListStocks() {
	
		Date lastListFetch = eventModel.getLastListFetch();
		LOGGER.debug("Last list update asked from event composite window was on the : "+lastListFetch);
		return lastListFetch.before(currentDate);
		
	}
	
	/**
	 * Fetch quotes.
	 * 
	 * @return true, if checks if is fetch quotes
	 * 
	 * @author Guillaume Thoreton
	 */
	private Boolean isFetchQuotes() {
		
		Date lastFetch = eventModel.getLastQuotationFetch();
		LOGGER.debug("Last quotation asked from event composite window was on the : " + lastFetch);
		return lastFetch.before(currentDate);

	}
	
	
}
