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
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.ActionDialogAction;
import com.finance.pms.MainGui;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.alerts.AlertOnThreshold;
import com.finance.pms.alerts.AlertOnThresholdType;
import com.finance.pms.alerts.ThresholdType;
import com.finance.pms.portfolio.PortfolioShare;

/**
 * The Class NewPortfolioDialog.
 * 
 * @author Guillaume Thoreton
 */
public class AddAlertDialog extends Dialog {
	

	protected static MyLogger LOGGER = MyLogger.getLogger(AddAlertDialog.class);

	private Shell dialogShell;
	private Label alertSettingLabel;
	private Button alertValidationButon;
	private Text sharePriceText;
	
	private Text commentText;
	private Combo combo;

	private PortfolioShare portfolioShare;

	private Table alertTableUp;
	private Table alertTableDown;

	private ActionDialogAction action;
	
	protected enum Modtype {QUANTITY,AIN,AOUT};
	
	public AddAlertDialog(Shell parent, PortfolioShare portfolioShare, ActionDialogAction action) {
		super(parent);
		this.portfolioShare = portfolioShare;
		this.action = action;
		
	}


	public AddAlertDialog(Shell parent, int style) {
		super(parent, style);
	}


	public void open(int xpos, int ypos) {
		try {
			Shell parent = getParent();
			dialogShell = new Shell(parent, SWT.SHELL_TRIM);
			dialogShell.setLocation(xpos,ypos);
			GridLayout dialogShellLayout = new GridLayout(2, false);
			dialogShellLayout.verticalSpacing = 8;
			dialogShell.setLayout(dialogShellLayout);
	
			dialogShell.setText("Add an alert on threshold crossing");
			dialogShell.setBackground(MainGui.pOPUP_BG);
			
			
			dialogShell.addDisposeListener(new DisposeListener() {
				
				@Override
				public void widgetDisposed(DisposeEvent e) {
					action.action(null);
					
				}
			});
			
			{
				alertSettingLabel = new Label(dialogShell, SWT.NONE);
				alertSettingLabel.setText("Threshold price ");
				alertSettingLabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				alertSettingLabel.setLayoutData(newPortfoliolabelLData);
				alertSettingLabel.setBackground(MainGui.pOPUP_BG);
			}
			{
				sharePriceText = new Text(dialogShell, SWT.BORDER);
				GridData newPortfolioTextLData = new GridData(GridData.FILL_HORIZONTAL);
				sharePriceText.setLayoutData(newPortfolioTextLData);
				sharePriceText.setText("0.00");
				sharePriceText.setFont(MainGui.CONTENTFONT);
			}	
			{
				commentText = new Text(dialogShell, SWT.BORDER);
				GridData newPortfolioTextLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfolioTextLData.horizontalSpan = 2;
				commentText.setLayoutData(newPortfolioTextLData);
				commentText.setText("No comment");
				commentText.setFont(MainGui.CONTENTFONT);
			}	
			{
				alertSettingLabel = new Label(dialogShell, SWT.NONE);
				alertSettingLabel.setText("Trend ");
				alertSettingLabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				alertSettingLabel.setLayoutData(newPortfoliolabelLData);
				alertSettingLabel.setBackground(MainGui.pOPUP_BG);
			}
			{
				combo = new Combo(dialogShell, SWT.READ_ONLY);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				combo.setLayoutData(newPortfoliolabelLData);
				combo.setFont(MainGui.DEFAULTFONT);
				for (int j = 0, n = ThresholdType.values().length; j < n; j++) {
					combo.add(ThresholdType.values()[j].name());
				}
			
				combo.add(AlertOnThresholdType.AVG_BUY_PRICE.name());
				combo.add(AlertOnThresholdType.ABOVE_TAKE_PROFIT_LIMIT.name());
				combo.add(AlertOnThresholdType.BELOW_ZERO_WEIGHTED_PROFIT_LIMIT.name());
				
				combo.select(0);
			}
			{
				alertValidationButon = new Button(dialogShell, SWT.PUSH);
				GridData newPortfollioValidateButtonLData = new GridData();
				newPortfollioValidateButtonLData.horizontalSpan = 2;
				newPortfollioValidateButtonLData.horizontalAlignment = GridData.END;
				alertValidationButon.setLayoutData(newPortfollioValidateButtonLData);
				alertValidationButon.setText("Apply");
				alertValidationButon.setFont(MainGui.DEFAULTFONT);
				alertValidationButon.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
						BigDecimal value = new BigDecimal(sharePriceText.getText());

						switch(combo.getSelectionIndex()) {
							case 0 :
							case 1 :
								ThresholdType threshold;
								threshold = ThresholdType.values()[combo.getSelectionIndex()];
								portfolioShare.addSimpleAlertOnThreshold(threshold, value, commentText.getText());
								break;
							case 2 :
								portfolioShare.addBuyAlerts(value, EventSignalConfig.getNewDate());
								break;
							case 3 :
								portfolioShare.addAboveTakeProfitAlert(value,commentText.getText());
								break;
							case 4 :
								portfolioShare.addWeightedZeroProfitAlertGuardSetter(value,commentText.getText());
								break;
							default : 
								LOGGER.error("Nothing to do! "+combo.getSelectionIndex());
						}
						updateDownTable();
						updateUpTable();
						
					}
				});
			}
			{
				alertTableUp = new Table(dialogShell, SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);
				alertTableUp.setFont(MainGui.DEFAULTFONT);
				alertTableUp.setHeaderVisible(true);
				GridData newPortfoliolabelLData = new GridData(SWT.FILL, SWT.FILL, true, true);
				newPortfoliolabelLData.horizontalSpan = 2;
				alertTableUp.setLayoutData(newPortfoliolabelLData);
				TableColumn column = new TableColumn(alertTableUp, SWT.NONE);
				column.setText("Existing alerts UP (double click to delete)");
				updateUpTable();
				
			}
			
			{
				alertTableDown = new Table(dialogShell, SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);
				alertTableDown.setFont(MainGui.DEFAULTFONT);
				alertTableDown.setHeaderVisible(true);
				GridData newPortfoliolabelLData = new GridData(SWT.FILL, SWT.FILL, true, true);
				newPortfoliolabelLData.horizontalSpan = 2;
				alertTableDown.setLayoutData(newPortfoliolabelLData);
				TableColumn column = new TableColumn(alertTableDown, SWT.NONE);
				column.setText("Existing alerts DOWN");
				updateDownTable();
				
			}
			
			Point pUp = alertTableUp.computeSize(350, SWT.DEFAULT);
			Point pDown = alertTableDown.computeSize(350, SWT.DEFAULT);
			
			dialogShell.setMinimumSize(350,300+pUp.y+pDown.y);
			dialogShell.pack();
			
			dialogShell.layout();
			dialogShell.open();
		} catch (Exception e) {
			LOGGER.error("",e);
		}
	}

	private void updateDownTable() {
		
		alertTableDown.removeAll();
		for (AlertOnThreshold alert : portfolioShare.getAlertsOnThresholdDown()) {
			TableItem item = new TableItem(alertTableDown, SWT.NONE);
			item.setData(alert);
			item.setText(0, alert.toString());
			item.setFont(MainGui.CONTENTFONT);
		}
		alertTableDown.addListener(SWT.MouseDoubleClick, new Listener() {
			
			public void handleEvent(Event arg0) {
				TableItem[] selection = alertTableDown.getSelection();
				if (selection != null && selection.length > 0) {
					TableItem tableItem = selection[0];
					portfolioShare.removeAlertOnThreshold((AlertOnThreshold)tableItem.getData());
					tableItem.dispose();
				}
			}
		});

		alertTableDown.getColumn(0).pack();
	}

	private void updateUpTable() {
		
		alertTableUp.removeAll();
		for (AlertOnThreshold alert : portfolioShare.getAlertsOnThresholdUp()) {
			TableItem item = new TableItem(alertTableUp, SWT.NONE);
			item.setData(alert);
			item.setText(0, alert.toString());
			item.setFont(MainGui.CONTENTFONT);
		}
		alertTableUp.addListener(SWT.MouseDoubleClick, new Listener() {
			
			public void handleEvent(Event arg0) {
				TableItem[] selection = alertTableUp.getSelection();
				if (selection != null && selection.length > 0) {
					TableItem tableItem = selection[0];
					portfolioShare.removeAlertOnThreshold((AlertOnThreshold)tableItem.getData());
					tableItem.dispose();
				}
			}
		});
		alertTableUp.getColumn(0).pack();
	}

}
