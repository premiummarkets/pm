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
package com.finance.pms;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.threads.ObserverMsg;

public class LogComposite extends Composite implements Observer, Comparable<Observer> {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(LogComposite.class);
	
	/** The log display. */
	public Label logDisplay;
	
	/** The progress bar. */
	public ProgressBar progressBar;
	
	/** The progress bar label. */
	public Label progressBarLabel;

	private long startTime;
	private Integer nbIterDone;
	private Integer totalIter;
	private RefreshableView view;

	//private Group logGroup;
	

	public LogComposite(Composite arg0, int arg1) {
		super(arg0, arg1);
		
//		GridLayout gridLayout = new GridLayout();
//		this.setLayout(gridLayout);
		
		{
			//logGroup = new Group(this, SWT.SHADOW_NONE);
			GridLayout group1Layout = new GridLayout();
			group1Layout.numColumns = 8;
			group1Layout.marginHeight = 0;
			group1Layout.makeColumnsEqualWidth = true;
			//logGroup.setLayout(group1Layout);
			this.setLayout(group1Layout);
			{
			    logDisplay = new Label(this,SWT.SHADOW_NONE);
			    GridData logDisplayGD = new GridData(GridData.FILL_BOTH);
			    logDisplayGD.horizontalSpan=4;
			    logDisplayGD.grabExcessHorizontalSpace=false;
			    logDisplay.setLayoutData(logDisplayGD);
				logDisplay.setFont(MainGui.DEFAULTFONT);
			}
			{
				progressBar = new ProgressBar(this, SWT.SMOOTH);
				GridData logDisplayGD = new GridData(GridData.FILL_BOTH);
				logDisplayGD.horizontalSpan = 3;
				progressBar.setLayoutData(logDisplayGD);
				progressBar.setFont(MainGui.DEFAULTFONT);
				progressBar.setMaximum(100);
				progressBar.setSelection(0);
				progressBar.setVisible(true);
				
				progressBarLabel = new Label(this, SWT.LEFT|SWT.HORIZONTAL);
				progressBarLabel.setText("-");
				progressBarLabel.setSize(40, SWT.DEFAULT);
				progressBarLabel.setFont(MainGui.DEFAULTFONT);
			}
		}
		this.layout();
		this.pack();
	}
	
	/**
	 * Progress bar update.
	 * 
	 * @param nbIterDone the new per cent
	 * @param oldPerCent the old per cent
	 * @param startTime the start time
	 * 
	 * @author Guillaume Thoreton
	 */
	public void progressBarUpdate() {

		long currentTime = (new Date()).getTime();
		
		long timeElapsed = (nbIterDone == 0)?0:(currentTime - startTime);
		String timeElapsedStr = timeElapsed/(1000*60) + " minutes "+ (timeElapsed - (1000*60)*(timeElapsed/(1000*60)))/1000 + " secondes.";
		
		progressBarLabel.setText("Time elapsed : "+timeElapsedStr);
		double percentDone = (totalIter == 0)?0:new Double(nbIterDone)/new Double(totalIter);
		progressBar.setSelection((int) Math.rint(percentDone*100));
		logDisplay.setText(MyLogger.lastMessage);
		
		progressBarLabel.pack();
		logDisplay.pack();
		
		
	}

	
	public void endJob() {
		progressBarLabel.setText("-");
		progressBar.setSelection(0);
		logDisplay.setText("");
		
		progressBarLabel.pack();
		logDisplay.pack();
		
		view.refreshView();
	}

	/**
	 * @param view TODO
	 * 
	 */
	public void initRefresh(RefreshableView view) {
		
		progressBar.setSelection(0);
		startTime = (new Date()).getTime();
		this.view = view;
	}

	public void update(Observable o, Object arg) {
		
		ObserverMsg observerMsg = (ObserverMsg) arg;
		
		//String[] argSplit;
		//if (arg != null && arg instanceof String && (argSplit = ((String) arg).split(" "))[0].equals("init")) {
		if (observerMsg != null && observerMsg.getKey().equals(ObserverMsg.ObsKey.INITMSG)) { //Logger init
			
			try {
				//totalIter = new Integer(argSplit[1]);
				totalIter = (Integer) observerMsg.getNameValuePairs().get(0).value;
				nbIterDone = 0;
			} catch (Exception e) {
				LOGGER.warn("Unhandeled logger notication : "+arg);
			}
		
		//} else if (arg != null && arg.equals("done")) {
		} else if (observerMsg != null && observerMsg.getKey().equals(ObserverMsg.ObsKey.DONE)) { //Logger end
			
			//nothing yet
			
		} else {//Increment
		
			view.getDisplay().asyncExec(new Runnable() {
	
				public void run() {
					if (totalIter != null && nbIterDone != null) {
						nbIterDone ++;
						progressBarUpdate();
					}
				}
				
			});
			
		}	
	}

	@Override
	public int compareTo(Observer o) {
		String thisToString = this.getClass().getName() + "@" + Integer.toHexString(this.hashCode());
		String oToString = o.getClass().getName() + "@" + Integer.toHexString(o.hashCode());
		return thisToString.compareTo(oToString);
	}
}
