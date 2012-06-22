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
package com.finance.pms.portfolio.gui;

import java.math.BigDecimal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.MainGui;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.alerts.Alert;
import com.finance.pms.alerts.AlertType;
import com.finance.pms.alerts.ThresholdType;
import com.finance.pms.portfolio.PortfolioShare;




// TODO: Auto-generated Javadoc
/**
 * The Class NewPortfolioDialog.
 * 
 * @author Guillaume Thoreton
 */
public class AddAlertDialog extends org.eclipse.swt.widgets.Dialog {
	

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
	
	protected enum Modtype {QUANTITY,AIN,AOUT};
	
	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @author Guillaume Thoreton
	 */
	//Test
	public static void main(String[] args) {
		try {
			String dbfile = args[0];
			SpringContext ctx = new SpringContext();
			ctx.setDataSource(dbfile);
			ctx.setMasSource(dbfile,"false");
			ctx.loadBeans(new String[] {"/connexions.xml", "/swtclients.xml","talibanalysisservices.xml","masanalysisservices.xml"});
			ctx.refresh();
			
		} catch (Exception e) {
			LOGGER.debug("",e);
		}
	}
	
	

	public AddAlertDialog(Shell parent,PortfolioShare portfolioShare) {
		super(parent);
		this.portfolioShare = portfolioShare;
		
	}


	/**
	 * Instantiates a new new portfolio dialog.
	 * 
	 * @param parent the parent
	 * @param style the style
	 * 
	 * @author Guillaume Thoreton
	 */
	public AddAlertDialog(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Open.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void open(int xpos, int ypos) {
		try {
			Shell parent = getParent();
			dialogShell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE);
			dialogShell.setLocation(xpos,ypos);

			GridLayout dialogShellLayout = new GridLayout();
			dialogShellLayout.verticalSpacing = 8;
			dialogShellLayout.numColumns = 2;
			dialogShell.layout();			
			dialogShell.setLayout(dialogShellLayout);
			//dialogShell.setMinimumSize(900,300);
			dialogShell.pack();
			dialogShell.setBackground(new Color(dialogShell.getDisplay(),239, 183,103));
			{
				alertSettingLabel = new Label(dialogShell, SWT.BORDER);
				alertSettingLabel.setText("Add an alert on threshold crossing ");
				alertSettingLabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfoliolabelLData.horizontalSpan = 2;
				alertSettingLabel.setLayoutData(newPortfoliolabelLData);
				alertSettingLabel.setBackground(new Color(dialogShell.getDisplay(),239, 183,103));
			}
			{
				alertSettingLabel = new Label(dialogShell, SWT.NONE);
				alertSettingLabel.setText("Threshold price ");
				alertSettingLabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				alertSettingLabel.setLayoutData(newPortfoliolabelLData);
				alertSettingLabel.setBackground(new Color(dialogShell.getDisplay(),239, 183,103));
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
				alertSettingLabel.setBackground(new Color(dialogShell.getDisplay(),239, 183,103));
			}
			{
				combo = new Combo(dialogShell, SWT.READ_ONLY);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				combo.setLayoutData(newPortfoliolabelLData);
				combo.setFont(MainGui.DEFAULTFONT);
				for (int j = 0, n = ThresholdType.values().length; j < n; j++) {
					combo.add(ThresholdType.values()[j].name());
				}
			
				combo.add(AlertType.AVG_BUY_PRICE.name());
				combo.add(AlertType.ABOVE_TAKE_PROFIT_LIMIT.name());
				combo.add(AlertType.BELOW_ZERO_WEIGHTED_PROFIT_LIMIT.name());
				
				combo.select(0);
			}
			{
				alertValidationButon = new Button(dialogShell, SWT.BORDER);
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
								portfolioShare.addSimpleAlert(threshold, value, commentText.getText());
								break;
							case 2 :
								portfolioShare.addBuyAlerts(value, EventSignalConfig.getNewDate());
								break;
							case 3 :
								portfolioShare.addAboveTakeProfitAlert(value,commentText.getText());
								break;
							case 4 :
								portfolioShare.addWeigthedZeroProfitAlertGuardSetter(value,commentText.getText());
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
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
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
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
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
			Display display = dialogShell.getDisplay();
			while (!dialogShell.isDisposed()) {
				try {
					if (!display.readAndDispatch())
						display.sleep();
				} catch (RuntimeException e) {
					LOGGER.error("Error in New Portfolio Dialog Gui : "+e.getMessage(),e);
					LOGGER.debug("Error in New Portfolio Dialog Gui : ",e);
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
	 * 
	 */
	private void updateDownTable() {
		alertTableDown.removeAll();
		for (Alert alert : portfolioShare.getAlertsDown()) {
			TableItem item = new TableItem(alertTableDown, SWT.NONE);
			item.setData(alert);
			item.setText(0, alert.toString());
			item.setFont(MainGui.CONTENTFONT);
		}
		alertTableDown.addListener(SWT.MouseDoubleClick, new Listener() {
			
			public void handleEvent(Event arg0) {
				TableItem tableItem = alertTableDown.getSelection()[0];
				portfolioShare.removeAlert((Alert)tableItem.getData());
				tableItem.dispose();
			}
		});

		alertTableDown.getColumn(0).pack();
	}



	/**
	 * 
	 */
	private void updateUpTable() {
		alertTableUp.removeAll();
		for (Alert alert : portfolioShare.getAlertsUp()) {
			TableItem item = new TableItem(alertTableUp, SWT.NONE);
			item.setData(alert);
			item.setText(0, alert.toString());
			item.setFont(MainGui.CONTENTFONT);
		}
		alertTableUp.addListener(SWT.MouseDoubleClick, new Listener() {
			
			public void handleEvent(Event arg0) {
				TableItem[] selection = alertTableUp.getSelection();
				if (selection.length > 0) {
					TableItem tableItem = selection[0];
					portfolioShare.removeAlert((Alert)tableItem.getData());
					tableItem.dispose();
				}
			}
		});
		alertTableUp.getColumn(0).pack();
	}

}
