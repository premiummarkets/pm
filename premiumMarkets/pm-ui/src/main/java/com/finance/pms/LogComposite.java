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
package com.finance.pms;

import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.threads.ObserverMsg;

public class LogComposite extends Composite implements Observer, Comparable<Observer> {

	protected static MyLogger LOGGER = MyLogger.getLogger(LogComposite.class);

	public Label logDisplay;
	public ProgressBar progressBar;
	public Label progressBarLabel;

	private long startTime;
	private Integer nbIterDone;
	private Integer totalIter;
	private RefreshableView view;

	public LogComposite(Composite parent) {
		super(parent, SWT.NONE);

		{
			GridLayout group1Layout = new GridLayout();
			group1Layout.numColumns = 10;
			group1Layout.marginHeight = 0;
			group1Layout.verticalSpacing = 0;
			group1Layout.makeColumnsEqualWidth = true;
			this.setLayout(group1Layout);

			this.setBackgroundMode(SWT.INHERIT_DEFAULT);
			{
				logDisplay = new Label(this,SWT.NONE);
				GridData logDisplayGD = new GridData(SWT.FILL,SWT.FILL,true,true);
				logDisplayGD.horizontalSpan=4;
				logDisplay.setLayoutData(logDisplayGD);
				logDisplay.setFont(MainGui.DEFAULTFONT);
				logDisplay.setForeground(new Color(getDisplay(),255,0,0));
			}
			{
				progressBar = new ProgressBar(this, SWT.SMOOTH);
				GridData logDisplayGD = new GridData(SWT.FILL,SWT.FILL,true,true);
				logDisplayGD.horizontalSpan=4;
				progressBar.setLayoutData(logDisplayGD);

				progressBar.setFont(MainGui.DEFAULTFONT);
				progressBar.setMaximum(100);
				progressBar.setSelection(0);
				progressBar.setVisible(true);
			}
			{
				progressBarLabel = new Label(this, SWT.LEFT|SWT.HORIZONTAL);
				GridData prgLabelGD = new GridData(SWT.FILL,SWT.FILL,true,true);
				prgLabelGD.horizontalSpan=2;
				progressBarLabel.setLayoutData(prgLabelGD);
				progressBarLabel.setText("-");
				progressBarLabel.setSize(40, SWT.DEFAULT);
				progressBarLabel.setFont(MainGui.DEFAULTFONT);
			}
		}
		this.layout();
		this.pack();

	}

	public void progressBarUpdate() {

		long currentTime = (new Date()).getTime();

		long timeElapsed = (nbIterDone == 0)? 0 : (currentTime - startTime);

		if (timeElapsed > 0) {
			String timeElapsedStr = timeElapsed/(1000*60) + " minutes "+ (timeElapsed - (1000*60)*(timeElapsed/(1000*60)))/1000 + " seconds.";
			progressBarLabel.setText("Time elapsed : " + timeElapsedStr);
			progressBarLabel.pack();
		}

		String lastMessage = MyLogger.lastMsg.getLastMessage();
		if (lastMessage != null) {
			logDisplay.setText(lastMessage);
			logDisplay.setToolTipText(lastMessage);
			logDisplay.pack();
		}

		double percentDone = (totalIter == 0)? 0 : new Double(nbIterDone)/new Double(totalIter);
		progressBar.setSelection((int) Math.rint(percentDone*100));

		this.layout();

	}

	public void endJob(List<Exception> exceptions) {
		progressBarLabel.setText("_ _ _");
		progressBar.setSelection(0);
		logDisplay.setText("");

		progressBarLabel.pack();
		logDisplay.pack();

		this.layout();

		view.refreshView(exceptions);
	}

	public void initRefresh(RefreshableView view) {

		progressBar.setSelection(0);
		startTime = (new Date()).getTime();
		this.view = view;

		Observer observer = new Observer() {

			@Override
			public void update(Observable o, final Object arg) {

				Display.getDefault().asyncExec(new Runnable() {

					public void run() {
						logDisplay.setText((String) arg);
						logDisplay.setToolTipText((String) arg);
						logDisplay.pack();
						LogComposite.this.layout();
					}

				});
			}
		};

		MyLogger.lastMsg.addObserver(observer);
	}

	public void update(Observable o, Object arg) {

		ObserverMsg observerMsg = (ObserverMsg) arg;

		if (observerMsg != null && observerMsg.getKey().equals(ObserverMsg.ObsKey.INITMSG)) { //Logger init

			try {
				totalIter = (Integer) observerMsg.getNameValuePairs().get(0).value;
				nbIterDone = 0;

				Display.getDefault().asyncExec(new Runnable() {

					public void run() {
						if (totalIter != null && nbIterDone != null) {
							progressBarUpdate();
						}
					}

				});

			} catch (Exception e) {
				LOGGER.warn("Unhandled logger notification : "+arg);
			}

		} else if (observerMsg != null && observerMsg.getKey().equals(ObserverMsg.ObsKey.DONE)) { //Logger end

			//refresh is done in endJob()
			//view.refreshView();

		} else {//Increment

			Display.getDefault().asyncExec(new Runnable() {

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
