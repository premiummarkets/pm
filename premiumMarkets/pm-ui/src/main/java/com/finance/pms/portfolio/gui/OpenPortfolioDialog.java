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

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.admin.install.logging.MyLogger;



// TODO: Auto-generated Javadoc
/**
 * The Class OpenPortfolioDialog.
 * 
 * @author Guillaume Thoreton
 */
public class OpenPortfolioDialog extends org.eclipse.swt.widgets.Dialog {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(OpenPortfolioDialog.class);

	/** The dialog shell. */
	private Shell dialogShell;
	
	/** The open portfoliolabel. */
	private Label openPortfoliolabel;
	
	/** The open portfollio validate button. */
	private Button openPortfollioValidateButton;
	
	/** The open portfolio list. */
	private List openPortfolioList;
	
	/** The table1. */
	private Table table1;
	
	/** The text2. */
	private Text text2;
	
	/** The text3. */
	private Text text3;
	
	/** The table column4. */
	private TableColumn tableColumn4;
	
	/** The table column3. */
	private TableColumn tableColumn3;
	
	/** The text1. */
	private Text text1;
	
	/** The composite1. */
	private Composite composite1;
	
	/** The table column2. */
	private TableColumn tableColumn2;
	
	/** The table column1. */
	private TableColumn tableColumn1;
	
	/** The text4. */
	private Text text4;
	
	/** The c tab item3. */
	private CTabItem cTabItem3;
	
	/** The c tab folder2. */
	private CTabFolder cTabFolder2;
	
	/** The group3. */
	private Group group3;
	
	/** The group2. */
	private Group group2;
	
	/** The composite3. */
	private Composite composite3;
	
	/** The button2. */
	private Button button2;
	
	/** The button1. */
	private Button button1;
	
	/** The group1. */
	private Group group1;
	
	/** The table3. */
	private Table table3;
	
	/** The composite2. */
	private Composite composite2;
	
	/** The c tab item2. */
	private CTabItem cTabItem2;
	
	/** The table2. */
	private Table table2;
	
	/** The scrolled composite1. */
	private ScrolledComposite scrolledComposite1;
	
	/** The tab item1. */
	private TabItem tabItem1;
	
	/** The tab folder1. */
	private TabFolder tabFolder1;
	
	/** The c tab item1. */
	private CTabItem cTabItem1;
	
	/** The c tab folder1. */
	private CTabFolder cTabFolder1;
	
	/** The table item3. */
	private TableItem tableItem3;
	
	/** The table item2. */
	private TableItem tableItem2;
	
	/** The table item1. */
	private TableItem tableItem1;

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
			OpenPortfolioDialog inst = new OpenPortfolioDialog(shell, SWT.NULL);
			inst.open();
		} catch (Exception e) {
			LOGGER.debug("",e);
		}
	}

	/**
	 * Instantiates a new open portfolio dialog.
	 * 
	 * @param parent the parent
	 * @param style the style
	 * 
	 * @author Guillaume Thoreton
	 */
	public OpenPortfolioDialog(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Open.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void open() {
		try {
			Shell parent = getParent();
			dialogShell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);

				{
					//Register as a resource user - SWTResourceManager will
					//handle the obtaining and disposing of resources
					//SWTResourceManager.registerResourceUser(dialogShell);
				}


			GridLayout dialogShellLayout = new GridLayout();
			dialogShell.setLayout(dialogShellLayout);
			dialogShell.pack();
			dialogShell.setSize(672, 825);
			dialogShell.setBackground(new Color(dialogShell.getDisplay(),239, 183,103));
//			this.addShellListener(new ShellAdapter() {
//				public void shellClosed(ShellEvent evt) {
//					rootShellClosed(evt);
//				}
//			});
			{
				openPortfoliolabel = new Label(dialogShell, SWT.CENTER);
				GridData newPortfoliolabelLData = new GridData();
				newPortfoliolabelLData.horizontalAlignment = GridData.CENTER;
				openPortfoliolabel.setLayoutData(newPortfoliolabelLData);
				openPortfoliolabel.setText("New portfolio");
				openPortfoliolabel.setBackground(new Color(dialogShell.getDisplay(),239, 183,103));
			}
			{
				GridData openPortfolioListLData = new GridData();
				openPortfolioListLData.widthHint = 1;
				openPortfolioListLData.heightHint = 1;
				openPortfolioListLData.grabExcessHorizontalSpace = true;
				openPortfolioListLData.horizontalAlignment = GridData.CENTER;
				openPortfolioList = new List(dialogShell, SWT.NONE);
				openPortfolioList.setLayoutData(openPortfolioListLData);
			}
			{
				openPortfollioValidateButton = new Button(dialogShell, SWT.CENTER);
				GridData newPortfollioValidateButtonLData = new GridData();
				newPortfollioValidateButtonLData.horizontalAlignment = GridData.CENTER;
				openPortfollioValidateButton.setLayoutData(newPortfollioValidateButtonLData);
				openPortfollioValidateButton.setText("Valider");
			}
			{
				GridData table1LData = new GridData();
				table1LData.heightHint = 116;
				table1LData.horizontalAlignment = GridData.HORIZONTAL_ALIGN_FILL;
				table1 = new Table(dialogShell,  SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
				table1.setLayoutData(table1LData);
				table1.setHeaderVisible(true);
				table1.setEnabled(false);
				table1.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent evt) {
						table1WidgetSelected(evt);
					}
				});
				{
					tableColumn1 = new TableColumn(table1, SWT.NONE);
					tableColumn1.setText("tableColumn1");
					tableColumn1.setWidth(130);
				}
				{
					tableColumn2 = new TableColumn(table1, SWT.NONE);
					tableColumn2.setText("tableColumn2");
					tableColumn2.setWidth(130);
				}
				{
					tableColumn3 = new TableColumn(table1, SWT.NONE);
					tableColumn3.setText("tableColumn3");
					tableColumn3.setWidth(130);
				}
				{
					tableColumn4 = new TableColumn(table1, SWT.NONE);
					tableColumn4.setText("tableColumn4");
					tableColumn4.setWidth(130);
				}
				{
					tableItem1 = new TableItem(table1, SWT.NONE);
					tableItem1.setText(0,"tableItem0");
					tableItem1.setText(1,"tableItem1");
//					tableItem4 = new TableItem(table1, SWT.HORIZONTAL);
//					tableItem4.setText("tableItem4");
				}
				{
					tableItem2 = new TableItem(table1, SWT.NONE);
					tableItem2.setText(0,"tableItem2");
					tableItem2.setText(1,"toto");
				}
				{
					tableItem3 = new TableItem(table1, SWT.NONE);
					tableItem3.setText("tableItem3");
				}
//				table1.getColumn(0).pack();
//				table1.getColumn(1).pack();
			}
			{
				composite1 = new Composite(dialogShell, SWT.NONE);
				FillLayout composite1Layout = new FillLayout(org.eclipse.swt.SWT.VERTICAL);
				composite1Layout.type = SWT.VERTICAL;
				GridData composite1LData = new GridData();
				composite1LData.verticalAlignment = GridData.FILL;
				composite1LData.horizontalAlignment = GridData.CENTER;
				composite1.setLayoutData(composite1LData);
				composite1.setLayout(composite1Layout);
				{
					text1 = new Text(composite1, SWT.NONE);
					GridData text1LData = new GridData();
					text1LData.widthHint = 130;
					text1LData.heightHint = 20;
					text1.setLayoutData(text1LData);
					text1.setText("text1");
				}
				{
					text2 = new Text(composite1, SWT.NONE);
					GridData text2LData = new GridData();
					text2LData.widthHint = 130;
					text2LData.heightHint = 20;
					text2.setLayoutData(text2LData);
					text2.setText("text2");
				}
				{
					text3 = new Text(composite1, SWT.NONE);
					GridData text3LData = new GridData();
					text3LData.widthHint = 130;
					text3LData.heightHint = 20;
					text3.setLayoutData(text3LData);
					text3.setText("text3");

				}
				{
					text4 = new Text(composite1, SWT.NONE);
					GridData text4LData = new GridData();
					text4LData.widthHint = 130;
					text4LData.heightHint = 20;
					text4.setLayoutData(text4LData);
					text4.setText("text4");
				}
			}
			{
				cTabFolder1 = new CTabFolder(dialogShell, SWT.NONE);
				{
					cTabItem1 = new CTabItem(cTabFolder1, SWT.NONE);
					cTabItem1.setText("cTabItem1");
					{
						scrolledComposite1 = new ScrolledComposite(cTabFolder1, SWT.EMBEDDED | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
						cTabItem1.setControl(scrolledComposite1);
						scrolledComposite1.setExpandHorizontal(true);
						scrolledComposite1.setExpandVertical(true);
						scrolledComposite1.setAlwaysShowScrollBars(true);
						//scrolledComposite1.setLayoutDeferred(false);
						{
							table2 = new Table(scrolledComposite1, SWT.NONE|SWT.FULL_SELECTION);
							scrolledComposite1.setContent(table2);
							table2.setSize(41, 11);
						}
					}
					GridData cTabFolder1LData = new GridData();
					cTabFolder1LData.widthHint = 629;
					cTabFolder1LData.heightHint = 50;
					cTabFolder1.setLayoutData(cTabFolder1LData);
				}
				{
					cTabItem2 = new CTabItem(cTabFolder1, SWT.NONE);
					cTabItem2.setText("cTabItem2");
					{
						composite2 = new Composite(cTabFolder1, SWT.NONE);
						GridLayout composite2Layout = new GridLayout();
						composite2Layout.makeColumnsEqualWidth = true;
						composite2.setLayout(composite2Layout);
						cTabItem2.setControl(composite2);
						{
							GridData table3LData = new GridData();
							table3LData.widthHint = 497;
							table3LData.heightHint = 86;
							table3 = new Table(composite2, SWT.NONE);
							table3.setLayoutData(table3LData);
						}
					}
					cTabFolder1.setSelection(0);
				}
			}
			{
				tabFolder1 = new TabFolder(dialogShell, SWT.NONE);
				{
					tabItem1 = new TabItem(tabFolder1, SWT.NONE);
					tabItem1.setText("tabItem1");
					GridData tabFolder1LData = new GridData();
					tabFolder1LData.widthHint = 580;
					tabFolder1LData.heightHint = 68;
					tabFolder1LData.grabExcessHorizontalSpace = true;
					tabFolder1.setLayoutData(tabFolder1LData);
					tabFolder1.setSelection(0);
				}
			}
			{
				group1 = new Group(dialogShell, SWT.NONE);
				RowLayout group1Layout = new RowLayout(org.eclipse.swt.SWT.HORIZONTAL);
				group1Layout.fill = true;
				group1Layout.justify = true;
				group1Layout.marginWidth = 10;
				group1Layout.marginBottom = 5;
				group1.setLayout(group1Layout);
				GridData group1LData = new GridData();
				group1LData.grabExcessHorizontalSpace = true;
				group1LData.horizontalAlignment = GridData.CENTER;
				group1LData.verticalAlignment = GridData.FILL;
				group1.setLayoutData(group1LData);
				group1.setText("group1");
				{
					button1 = new Button(group1, SWT.PUSH | SWT.CENTER);
					button1.setText("button1");
				}
				{
					button2 = new Button(group1, SWT.PUSH | SWT.CENTER);
					button2.setText("button2");
				}
			}
			{
				composite3 = new Composite(dialogShell, SWT.NONE);
				GridLayout composite3Layout = new GridLayout();
				composite3Layout.makeColumnsEqualWidth = true;
				composite3Layout.numColumns = 2;
				GridData composite3LData = new GridData();
				composite3LData.widthHint = 182;
				composite3LData.heightHint = 178;
				composite3LData.horizontalAlignment = GridData.CENTER;
				composite3LData.grabExcessHorizontalSpace = true;
				composite3.setLayoutData(composite3LData);
				composite3.setLayout(composite3Layout);
				{
					cTabFolder2 = new CTabFolder(composite3, SWT.NONE);
					{
						cTabItem3 = new CTabItem(cTabFolder2, SWT.NONE);
						cTabItem3.setText("cTabItem3");
						GridData cTabFolder2LData = new GridData();
						cTabFolder2LData.verticalAlignment = GridData.BEGINNING;
						cTabFolder2LData.grabExcessVerticalSpace = true;
						cTabFolder2.setLayoutData(cTabFolder2LData);
						cTabFolder2.setSelection(0);
					}
				}
				{
					group3 = new Group(composite3, SWT.NONE);
					GridLayout group3Layout = new GridLayout();
					group3Layout.makeColumnsEqualWidth = true;
					group3Layout.numColumns = 2;
					group3.setLayout(group3Layout);
					GridData group3LData = new GridData();
					group3LData.horizontalAlignment = GridData.END;
					group3LData.verticalAlignment = GridData.BEGINNING;
					group3.setLayoutData(group3LData);
					group3.setText("group3");
				}
				{
					group2 = new Group(composite3, SWT.NONE);
					GridLayout group2Layout = new GridLayout();
					group2Layout.makeColumnsEqualWidth = true;
					group2Layout.numColumns = 2;
					group2.setLayout(group2Layout);
					GridData group2LData = new GridData();
					group2LData.horizontalAlignment = GridData.CENTER;
					group2LData.horizontalSpan = 2;
					group2LData.verticalAlignment = GridData.BEGINNING;
					group2.setLayoutData(group2LData);
					group2.setText("group2");
				}
			}
			dialogShell.layout();
			dialogShell.open();
			Display display = dialogShell.getDisplay();
			while (!dialogShell.isDisposed()) {
				try {
					if (!display.readAndDispatch())
						display.sleep();
				} catch (RuntimeException e) {
					LOGGER.error("Error in Portfolio Gui : "+e.getMessage(),e);
					LOGGER.debug("Error in Gui : ",e);
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
	 * Table1 widget selected.
	 * 
	 * @param evt the evt
	 * 
	 * @author Guillaume Thoreton
	 */
	private void table1WidgetSelected(SelectionEvent evt) {
		LOGGER.debug("table1.widgetSelected, event=" + evt);
		// TODO add your code for table1.widgetSelected
	}

}
