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
package com.finance.pms.portfolio.gui;

import java.math.BigDecimal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.MainGui;
import com.finance.pms.admin.install.logging.MyLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class ErrorDialog.
 * 
 * @author Guillaume Thoreton
 */
public class AddMonitorDialog extends org.eclipse.swt.widgets.Dialog {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(AddMonitorDialog.class);

	/** The dialog shell. */
	private Shell dialogShell;

	/** The Validerbutton1. */
	private Button Validerbutton1;

	/** The Errorlabel1. */
	private Label Errorlabel1;
	
	private Label monitoringPortfolio;
	private Text monitoringPortfolioTxt;
	private Label alertPercentage;
	private Text alertPercentageTxt;

	private Boolean canceled = false;
	
	private String portfolioName;
	private BigDecimal percentageFall;

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void main(String[] args) {
		try {
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			AddMonitorDialog inst = new AddMonitorDialog(shell);
			inst.open();
		} catch (Exception e) {
			LOGGER.debug("",e);
		}
	}

	/**
	 * Instantiates a new error dialog.
	 * 
	 * @param parent the parent
	 * @param style the style
	 * @param erreur the erreur
	 * 
	 * @author Guillaume Thoreton
	 */
	public AddMonitorDialog(Shell parent) {
		super(parent, SWT.NULL);
	}

	/**
	 * Open.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void open() {
		try {
			Shell parent = getParent();
			dialogShell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE);

			{
				// Register as a resource user - SWTResourceManager will
				// handle the obtaining and disposing of resources
				//SWTResourceManager.registerResourceUser(dialogShell);
			}

			GridLayout dialogShellLayout = new GridLayout();
			dialogShellLayout.verticalSpacing = 20;
			dialogShellLayout.numColumns = 2;
			dialogShell.setLayout(dialogShellLayout);
			dialogShell.setBackground(new Color(dialogShell.getDisplay(),239, 183,103));
			{
				Errorlabel1 = new Label(dialogShell, SWT.NONE);
				Errorlabel1.setText("Warning!");
				GridData Errorlabel1LData = new GridData();
				Errorlabel1LData.horizontalSpan = 2;
				Errorlabel1LData.verticalAlignment = GridData.FILL;
				Errorlabel1LData.horizontalAlignment = GridData.FILL;
				Errorlabel1LData.grabExcessVerticalSpace = true;
				Errorlabel1LData.grabExcessHorizontalSpace = true;
				Errorlabel1.setLayoutData(Errorlabel1LData);
				Errorlabel1.setAlignment(SWT.CENTER);
				Errorlabel1.setBackground(new Color(dialogShell.getDisplay(),239, 183,103));
			}
			{
				StyledText text = new StyledText(dialogShell, SWT.NONE);
				GridData Errorlabel1LData = new GridData();
				Errorlabel1LData.horizontalSpan = 2;
				text.setLayoutData(Errorlabel1LData);
				text.setText("You can add this share for monitoring by filling up the following.");
				text.setFont(MainGui.DEFAULTFONT);
				text.setBackground(new Color(dialogShell.getDisplay(),239, 183,103));
			}
			{
				monitoringPortfolio = new Label(dialogShell, SWT.CHECK);
				monitoringPortfolio.setText("Monitoring portfolio");
				monitoringPortfolio.setFont(MainGui.DEFAULTFONT);
				
				monitoringPortfolioTxt = new Text(dialogShell, SWT.CHECK);
				monitoringPortfolioTxt.setText("monitorBuy");
				monitoringPortfolioTxt.setFont(MainGui.CONTENTFONT);
			}
			{
				alertPercentage = new Label(dialogShell, SWT.CHECK);
				alertPercentage.setText("% fall before alert :");
				alertPercentage.setFont(MainGui.DEFAULTFONT);
				
				alertPercentageTxt = new Text(dialogShell, SWT.CHECK);
				alertPercentageTxt.setText("5");
				alertPercentageTxt.setFont(MainGui.CONTENTFONT);
			}
			{
				Validerbutton1 = new Button(dialogShell, SWT.PUSH);
				GridData Validerbutton1LData = new GridData(GridData.BEGINNING);
				Validerbutton1LData.horizontalAlignment = GridData.BEGINNING;
				Validerbutton1.setLayoutData(Validerbutton1LData);
				Validerbutton1.setText("Validate");
				Validerbutton1.setFont(MainGui.DEFAULTFONT);
				Validerbutton1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
						canceled = false;
						portfolioName = monitoringPortfolioTxt.getText();
						percentageFall = BigDecimal.valueOf(Double.valueOf(alertPercentageTxt.getText()));
						closeMouseDown(evt);
					}
				});
			}
			{
				Button cancel = new Button(dialogShell, SWT.PUSH);
				GridData Validerbutton1LData = new GridData(GridData.END);
				Validerbutton1LData.horizontalAlignment = GridData.END;
				cancel.setLayoutData(Validerbutton1LData);
				cancel.setText("Cancel");
				cancel.setFont(MainGui.DEFAULTFONT);
				cancel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
						canceled = true;
						closeMouseDown(evt);
					}
				});
			}
			// dialogShell.layout();
			dialogShell.pack();
			dialogShell.open();
			Display display = dialogShell.getDisplay();
			while (!dialogShell.isDisposed()) {
				try {
					if (!display.readAndDispatch())
						display.sleep();
				} catch (RuntimeException e) {
					LOGGER.error("Error in Error dialog Gui : "+e.getMessage(),e);
					LOGGER.debug("Error in Error Dialog Gui : ",e);
				} catch (Error e) {
					LOGGER.error("Error in  Gui : "+e.getMessage(),e);
					LOGGER.debug("Error in  Gui : ",e);
				}
			}
		} catch (Exception e) {
			LOGGER.error("",e);
		}
	}

	/**
	 * Validerbutton1 mouse down.
	 * 
	 * @param evt the evt
	 * 
	 * @author Guillaume Thoreton
	 */
	private void closeMouseDown(MouseEvent evt) {
		dialogShell.dispose();
	}

	public Boolean getCanceled() {
		return canceled;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public BigDecimal getPercentageFall() {
		return percentageFall;
	}

}
