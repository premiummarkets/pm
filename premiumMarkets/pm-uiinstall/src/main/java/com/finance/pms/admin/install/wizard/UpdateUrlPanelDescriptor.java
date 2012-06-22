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
package com.finance.pms.admin.install.wizard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdesktop.swingworker.SwingWorker;

import com.nexes.wizard.WizardPanelDescriptor;


// TODO: Auto-generated Javadoc
/**
 * The Class IntroPanelDescriptor.
 * 
 * @author Guillaume Thoreton
 */
public class UpdateUrlPanelDescriptor extends WizardPanelDescriptor {
    
    /** The Constant IDENTIFIER. */
    public static final String IDENTIFIER = "UPDATE_PANEL";
    
	Integer newestExist = 0;
	String versionNumber;
   
    /**
     * Instantiates a new intro panel descriptor.
     * 
     * @author Guillaume Thoreton
     */
    public UpdateUrlPanelDescriptor() {
        super(IDENTIFIER, new UpdateUrl());
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



	class Task extends SwingWorker<Void, Void> {
		
		
		private WizardPanelDescriptor w;
		Date lastReleaseDate;
		Date currentBuildDate;
		private DateFormat sourceForgeDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");

		Task(WizardPanelDescriptor w) {
			super();
			this.w = w;
		}

		@Override
		protected void done() {
				super.done();
				
				if (newestExist > 2) {
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
					System.out.print("You have latest : " + sourceForgeDateFormat.format(currentBuildDate));
					w.getWizard().setCurrentPanel(LicencePanelDescriptor.IDENTIFIER);
				}
				
		}


		@Override
		protected Void doInBackground() {
			
			try {
				DateFormat buildDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");
				Properties pbuild = new Properties();
				pbuild.load(this.getClass().getResourceAsStream("/pmsbuild.properties"));
				String currentBuild = pbuild.getProperty("application.buildtime");
				currentBuildDate = buildDateFormat.parse(currentBuild); //2009/08/31 12:54:39 BST
				
				URL sourceforge = new URL("http://sourceforge.net/projects/pmsqueak/files/");
			    URLConnection yc = sourceforge.openConnection();
			    BufferedReader br = new BufferedReader(new InputStreamReader(yc.getInputStream()));

				String line;
				//Pattern pattern = Pattern.compile(".*PiggyMarketSqueak(.*)\\.jnlp:.*released on (.*)\""); 	//released on 2009-02-04
				// <a href="/projects/pmsqueak/files/latest/download?source=files" title="/executable pmsqueak/0.9.0/PiggyMarketSqueak-0.9.0.jnlp:  released on 2010-06-13 22:51:27 UTC">
				Pattern pattern = Pattern.compile("PremiumMarkets(.*)\\.jnlp:.*released on ([0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} [A-Z]{3})\">");
				Pattern pattern2 = Pattern.compile("-(.*)\\.jnlp:.*released on ([0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} [A-Z]{3})\">");
				Matcher fit, fit2;
				while (null != (line = br.readLine())) {
					//System.out.println(line);
					fit = pattern.matcher(line);
					fit2 = pattern2.matcher(line);
					if (fit.find()) {
						checkFit(line, fit);
						break;
					} else if (fit2.find()) {
						checkFit(line, fit2);
						break;
					}
				}
				
				
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
						
			return null;
		}

		private void checkFit(String line, Matcher fit) throws ParseException {
			System.out.println("Found pattern in : "+line);
			versionNumber = fit.group(1).replace("-", "");
			lastReleaseDate = sourceForgeDateFormat.parse(fit.group(2));
			if (lastReleaseDate.after(currentBuildDate)) {
				newestExist++;
			}
			System.out.println("Latest version is : "+versionNumber+" released on "+sourceForgeDateFormat.format(lastReleaseDate));
			System.out.println("Your version was released on the "+sourceForgeDateFormat.format(currentBuildDate));
		}
		
	}

}

	