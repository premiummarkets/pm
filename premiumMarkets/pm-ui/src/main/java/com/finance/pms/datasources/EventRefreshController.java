/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
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
import java.util.Date;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.finance.pms.RefreshableView;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.threads.ObserverMsg;


// TODO: Auto-generated Javadoc
/**
 * The Class EventRefreshController.
 * 
 * @author Guillaume Thoreton
 */
public class EventRefreshController implements  MouseListener, SelectionListener {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(EventRefreshController.class);
	
	protected EventModel<? extends EventModelStrategyEngine> eventModel;
	
	private RefreshableView view;

	private Date currentDate;
	
	private Boolean doFetchListStocks;
	private Boolean doFetchQuotes;
	private Boolean doAnalyse;
	private Boolean doReco;
	
	
	/**
	 * Instantiates a new event refresh controller.
	 * 
	 * @param refreshModel the refresh model
	 * @param view the view
	 * 
	 * @author Guillaume Thoreton
	 */
	public EventRefreshController(EventModel<? extends EventModelStrategyEngine> refreshModel, RefreshableView view) {
		
		this.view = view;
		this.eventModel = refreshModel;
		try {
			currentDate = new SimpleDateFormat("yyyy/MM/dd").parse(new SimpleDateFormat("yyyy/MM/dd").format((EventSignalConfig.getNewDate())));
		} catch (ParseException e) {
			LOGGER.error("Can't parse "+new SimpleDateFormat("yyyy/MM/dd"),e);
		}
	}
	
	
	/**eventModel
	 * Update event refresh model state.
	 * 
	 * @param eventModelEngine the event model engine
	 * @param talibSetected the talib setected
	 * @param masSelected the mas selected
	 * @param startAnalyseDate the start analyse date
	 * @param dofetchListOfQuotes the dofetch list of quotes
	 * @param dofetchQuotes the dofetch quotes
	 * @param doAnalyse the do analyse
	 * 
	 * @author Guillaume Thoreton
	 */
	public void updateEventRefreshModelState(Boolean dofetchListOfQuotes, Boolean dofetchQuotes, Boolean doAnalyse, Boolean doReco) {
		
		this.doFetchListStocks = dofetchListOfQuotes;
		this.doFetchQuotes = dofetchQuotes;
		this.doAnalyse = doAnalyse;
		this.doReco = doReco;
		//this.eventModel.setStateParams(viewStateParams);
		
	
	}
	

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.MouseListener#mouseDoubleClick(org.eclipse.swt.events.MouseEvent)
	 */
	public void mouseDoubleClick(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

    /* (non-Javadoc)
     * @see org.eclipse.swt.events.MouseListener#mouseUp(org.eclipse.swt.events.MouseEvent)
     */
    public void mouseUp(MouseEvent arg0) {
    	// TODO Auto-generated method stub
	}


	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.MouseListener#mouseDown(org.eclipse.swt.events.MouseEvent)
	 */
	public void mouseDown(MouseEvent arg0) {
		this.refreshAction(view.getAnalysisStartDate());
    	
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
				doFetchQuotes = true;
				
				//Add Fetch List to the queue
				tasks.offer(new Runnable() {
					public void run() {
						eventModel.callbackForlastListFetch();
						eventModel.setLastListFetch(currentDate);
					}
				});
				
			}
			
			if (doReco) {
				//Add Fetch Reco to the queue
				tasks.offer(new Runnable() {
					public void run() {
						eventModel.callbackForReco();
					}
				});
				
			}
			
			if (doFetchQuotes && isFetchQuotes()) {
				
				//Add Fetch Quotations
				tasks.offer(new Runnable() {
					public void run() {
						eventModel.callbackForlastQuotationFetch();
						eventModel.setLastQuotationFetch(currentDate);
						eventModel.resetLastAnalyse();
					}
				});
			}
			
			if (doAnalyse) {
				eventModel.resetAnalisysList();
				eventModel.getAnalysisList().add("talib");
				
				//add Analysis
				tasks.offer(new Runnable() {
					public void run() {
						eventModel.callbackForlastAnalyse(startAnalyseDate);
						eventModel.setLastAnalyse(currentDate);
					}
				});
			}
			
			//Run tasks in serial mode and synchronise at the end
			Thread t = new Thread() {

				
				public void run() {
					
					Runnable r;
					while ( (r = tasks.poll()) != null) {
						r.run();
					}
					
					//Update potential registered view when finished
					view.getDisplay().syncExec(new Runnable() {
						public void run() {
							view.endRefreshAction();
						}
						
					});
					
					//Notifiy observers
					for (Observer obs : eventModel.getEngineObservers()) {
						obs.update(null, new ObserverMsg(null, ObserverMsg.ObsKey.DONE));
					}
				}
			};
			
			t.start();
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


	
	public void widgetDefaultSelected(SelectionEvent arg0) {
		this.refreshAction(view.getAnalysisStartDate());
		
	}


	
	public void widgetSelected(SelectionEvent arg0) {
		this.refreshAction(view.getAnalysisStartDate());
		
	}
	
	
}
