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
package com.finance.pms.admin.install.wizard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdesktop.swingworker.SwingWorker;

import com.nexes.wizard.Wizard;
import com.nexes.wizard.WizardPanelDescriptor;

/**
 * The Class IntroPanelDescriptor.
 * 
 * @author Guillaume Thoreton
 */
public class UpdateUrlPanelDescriptor extends WizardPanelDescriptor {

    public static final String IDENTIFIER = "UPDATE_PANEL";
    
	Integer newestExist = 0;
	String versionNumber;

	private String jnlpUrl;

    public UpdateUrlPanelDescriptor(Wizard wizard, String jnlpUrl) {
        super(IDENTIFIER, new UpdateUrl());
        this.jnlpUrl = jnlpUrl;
    }
    
    @Override
	public Object getNextPanelDescriptor() {
        return LicencePanelDescriptor.IDENTIFIER;
    }
    
    @Override
	public Object getBackPanelDescriptor() {
        return IntroPanelDescriptor.IDENTIFIER;
    }

	@Override
	public void aboutToDisplayPanel() {
		super.aboutToDisplayPanel();
		getWizard().setNextFinishButtonEnabled(false);	
	} 

	@Override
	public void displayingPanel() {
		Task task = new Task(this);
		task.execute();
		
	}
	
	@Override
	public void aboutToHidePanel() {
		super.aboutToHidePanel();
	}



	public class Task extends SwingWorker<Void, Void> {
		
		private WizardPanelDescriptor w;
		Date lastReleaseDate;
		Date currentBuildDate;
		private DateFormat jnlpDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");
	
		public Task(WizardPanelDescriptor w) {
			super();
			this.w = w;
		}

		@Override
		protected void done() {
				super.done();
				
				if (newestExist >= 1) {
					
					System.out.print("Need download");
					UpdateUrl updateUrl = (UpdateUrl) getPanelComponent();
					updateUrl.jLabel1.setText("Your version dates from "+currentBuildDate);
					updateUrl.label3.setText("Latest version dates from "+lastReleaseDate);
					
					Observer observer = new Observer() {

						public void update(Observable o, Object arg) {
							w.getWizard().setCurrentPanel(LicencePanelDescriptor.IDENTIFIER);
						}
						
					};
					
					updateUrl.downLoadLatest(versionNumber, observer);
					
				} else {
					if (currentBuildDate != null) {
						System.out.print("You have latest : " + jnlpDateFormat.format(currentBuildDate)+"\n");
					} else {
						System.out.print("No build date found in your install, currentBuildDate == " + currentBuildDate+"\n");
					}
					w.getWizard().setCurrentPanel(LicencePanelDescriptor.IDENTIFIER);
				}
				
		}


		@Override
		public Void doInBackground() {
			
			try {
				
				DateFormat buildDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");
				Properties pbuild = new Properties();
				pbuild.load(this.getClass().getResourceAsStream("/pmsbuild.properties"));
				String currentBuild = pbuild.getProperty("application.buildtime");
				currentBuildDate = buildDateFormat.parse(currentBuild); //2009/08/31 12:54:39 BST

				URL sourceforge = new URL(jnlpUrl);
			    URLConnection yc = sourceforge.openConnection();
			    yc.setReadTimeout(15000);
			    BufferedReader br = new BufferedReader(new InputStreamReader(yc.getInputStream()));

				String line;
//				Pattern pattern = Pattern.compile("PremiumMarkets(.*)\\.jnlp:.*released on ([0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} [A-Z]{3})\">");
//				Pattern pattern2 = Pattern.compile("-(.*)\\.jnlp:.*released on ([0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} [A-Z]{3})\">");
				//This version : 2012/12/19 22:23:43 GMT
				Pattern pattern = Pattern.compile("This version : ([0-9]{4}/[0-9]{2}/[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} [A-Z]*)");
				Matcher fit;
				while (null != (line = br.readLine())) {
					fit = pattern.matcher(line);
					if (fit.find()) {
						checkFit(line, fit);
						break;
					} 
				}
				
			} catch (Exception e) {
				System.out.println("Can't read update check URL "+jnlpUrl);
				e.printStackTrace();
			}
						
			return null;
		}

		private void checkFit(String line, Matcher fit) throws ParseException {
			System.out.println("Found pattern in : "+line);
			versionNumber = fit.group(1).replaceAll("[ /:]", ".");
			lastReleaseDate = jnlpDateFormat.parse(fit.group(1));
			Calendar lastReleaseCal = Calendar.getInstance();
			lastReleaseCal.setTime(lastReleaseDate);
			lastReleaseCal.set(Calendar.MILLISECOND, 0);
			lastReleaseCal.set(Calendar.SECOND, 0);
			Calendar currentBuildCal= Calendar.getInstance();
			currentBuildCal.setTime(currentBuildDate);
			currentBuildCal.set(Calendar.MILLISECOND, 0);
			currentBuildCal.set(Calendar.SECOND, 0);

			if (lastReleaseCal.getTime().after(currentBuildCal.getTime())) newestExist++;
			System.out.println("Latest version is : "+versionNumber+" released on "+jnlpDateFormat.format(lastReleaseDate));
			System.out.println("Your version was released on the "+jnlpDateFormat.format(currentBuildDate));
		}
		
	}

}

	