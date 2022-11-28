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
package com.finance.pms.portfolio.gui;

import java.math.BigDecimal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.ActionDialogAction;
import com.finance.pms.MainGui;
import com.finance.pms.admin.install.logging.MyLogger;


/**
 * The Class ErrorDialog.
 * 
 * @author Guillaume Thoreton
 */
public class RemoveConfirmationDialog extends org.eclipse.swt.widgets.Dialog {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(RemoveConfirmationDialog.class);

	private Shell dialogShell;
	private Button validerbutton1;
	private Label Errorlabel1;
	
	private Boolean ask4Apply;
	private Button applyToAmountCheck;
	private Boolean apply = false;
	private Boolean canceled = false;

	private Button monitorCheck;
	private Text monitoringPortfolioTxt;
	private Text alertPercentageTxt;
	private String monitroPortfolioName;
	private BigDecimal percentageFall;

	private boolean monitor;

	private ActionDialogAction action;

	public static void main(String[] args) {
		try {
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			RemoveConfirmationDialog inst = new RemoveConfirmationDialog(shell, true);
			inst.open();
		} catch (Exception e) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug("",e);
		}
	}

	public RemoveConfirmationDialog(Shell parent, Boolean ask4Apply) {
		super(parent, SWT.SHELL_TRIM);
		this.ask4Apply = ask4Apply;
	}

	public void open() {
		try {
			Shell parent = getParent();
			//dialogShell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE);
			dialogShell = new Shell(parent, SWT.SHELL_TRIM);
			dialogShell.setText("Apply transaction");

			GridLayout dialogShellLayout = new GridLayout();
			dialogShellLayout.verticalSpacing = 20;
			dialogShellLayout.numColumns = 2;
			dialogShell.setLayout(dialogShellLayout);
			dialogShell.setBackground(MainGui.pOPUP_BG);
			{
				Errorlabel1 = new Label(dialogShell, SWT.NONE);
				Errorlabel1.setText("Your operation requires clearance of the line.");
				GridData Errorlabel1LData = new GridData();
				Errorlabel1LData.horizontalSpan = 2;
				Errorlabel1LData.verticalAlignment = GridData.FILL;
				Errorlabel1LData.horizontalAlignment = GridData.FILL;
				Errorlabel1LData.grabExcessVerticalSpace = true;
				Errorlabel1LData.grabExcessHorizontalSpace = true;
				Errorlabel1.setLayoutData(Errorlabel1LData);
				Errorlabel1.setAlignment(SWT.CENTER);
				Errorlabel1.setBackground(MainGui.pOPUP_BG);
			}
			if (ask4Apply){
				applyToAmountCheck = new Button(dialogShell, SWT.CHECK);
				GridData Errorlabel1LData = new GridData();
				Errorlabel1LData.horizontalSpan = 2;
				applyToAmountCheck.setLayoutData(Errorlabel1LData);
				applyToAmountCheck.setText("Keep line transactions as if sold.");
				applyToAmountCheck.setFont(MainGui.DEFAULTFONT);
				applyToAmountCheck.setToolTipText(
						"Ticking this box, will sell the line at your price current and apply the transaction to the portfolio totals. Aka we sell the line.\n" +
						"If the box is left ticked off, the transaction amounts (money in and out for this line) will be removed from the portfolio totals. Aka as if the line had never been inserted.");
			}
			{
				monitorCheck = new Button(dialogShell, SWT.CHECK);
				GridData Errorlabel1LData = new GridData();
				Errorlabel1LData.horizontalSpan = 2;
				monitorCheck.setLayoutData(Errorlabel1LData);
				monitorCheck.setFont(MainGui.DEFAULTFONT);
				monitorCheck.setText("Add for monitoring");
				monitorCheck.setToolTipText("You can add this share for monitoring the next buy event by filling up the following.");
				monitorCheck.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						handle();
					}
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						handle();
					}
					
					private void handle() {
						monitoringPortfolioTxt.setEnabled(true);
						alertPercentageTxt.setEnabled(true);
					}
				});
			}
			{
				Label monitoringPortfolio = new Label(dialogShell, SWT.NONE);
				monitoringPortfolio.setText("Monitoring portfolio");
				monitoringPortfolio.setFont(MainGui.DEFAULTFONT);
				
				monitoringPortfolioTxt = new Text(dialogShell, SWT.NONE);
				monitoringPortfolioTxt.setText("MonitorBuy");
				monitoringPortfolioTxt.setFont(MainGui.CONTENTFONT);
				monitoringPortfolioTxt.setEnabled(false);
			}
			{
				Label alertPercentage = new Label(dialogShell, SWT.NONE);
				alertPercentage.setText("% fall before alert :");
				alertPercentage.setFont(MainGui.DEFAULTFONT);
				
				alertPercentageTxt = new Text(dialogShell, SWT.NONE);
				alertPercentageTxt.setText("5");
				alertPercentageTxt.setFont(MainGui.CONTENTFONT);
				alertPercentageTxt.setEnabled(false);
			}
			{
				validerbutton1 = new Button(dialogShell, SWT.PUSH);
				GridData Validerbutton1LData = new GridData(GridData.BEGINNING);
				Validerbutton1LData.horizontalAlignment = GridData.BEGINNING;
				validerbutton1.setLayoutData(Validerbutton1LData);
				validerbutton1.setText("Validate");
				validerbutton1.setFont(MainGui.DEFAULTFONT);
				validerbutton1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
						validClose();
						closeMouseDown();
					}
				});
				validerbutton1.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent evt) {
						if (evt.keyCode == SWT.CR) {
							validClose();
							closeMouseDown();
						}
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
						closeMouseDown();
					}
				});
				cancel.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent evt) {
						if (evt.keyCode == SWT.CR) {
							canceled = true;
							closeMouseDown();
						}
					}
				});
			}

			dialogShell.pack();
			dialogShell.open();
			
		} catch (Exception e) {
			LOGGER.error("",e);
		}
	}

	private void closeMouseDown() {
		dialogShell.dispose();
		action.action();
	}

	public Boolean getCanceled() {
		return canceled;
	}
	
	public Boolean getApply() {
		return apply;
	}
	
	public String getMonitorPortfolioName() {
		return monitroPortfolioName;
	}

	public BigDecimal getPercentageFall() {
		return percentageFall;
	}

	public Boolean getMonitorCheck() {
		return monitor;
	}

	protected void validClose() {
		canceled = false;
		apply = ask4Apply && applyToAmountCheck.getSelection();
		monitor = monitorCheck.getSelection();
		if (monitor) {
			monitroPortfolioName = monitoringPortfolioTxt.getText();
			percentageFall = BigDecimal.valueOf(Double.valueOf(alertPercentageTxt.getText()));
		}
	}

	public void setAction(ActionDialogAction action) {
		this.action = action;
		
	}

}
