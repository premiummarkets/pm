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
package com.finance.pms.admin.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.CursorFactory;
import com.finance.pms.MainGui;
import com.finance.pms.MainPMScmd;
import com.finance.pms.SpringContext;
import com.finance.pms.UserDialog;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.MarketQuotationProviders;

/**
 * The Class DbSettings.
 * 
 * @author Guillaume Thoreton
 */
public class DbSettings extends Dialog {

	protected static MyLogger LOGGER = MyLogger.getLogger(DbSettings.class);

	private Properties propList;
	private String pathToProps;
	
	private final String[] configTab = { "EMail setting", "Feeds performance and defaults", "Database", "Technical analysis"};
	private final String[] configTabComment = {
			"Email setting - Please fill in",
			"Default quotations provider - Possible values : "+MarketQuotationProviders.YAHOO.getCmdParam()+","+MarketQuotationProviders.INVESTIR.getCmdParam()+","+MarketQuotationProviders.GOOGLE.getCmdParam(),
			"Internal Data Base parameters - You normally don't have to change this.",
		    "Technical analysis - Indicator calculation tuning"};
	private final String[][] keys = {
			{ "mail.from", "mail.to", "mail.host", "mail.username", "mail.password", "mail.log.activated" },
			{ //"quotes.listfile", "quotes.listprovider", 
				"quotes.provider", 
			  //"analyse.mas.enable",	"semaphore.nbthread"
			},
			{ "software", "username", "password", "driver", "database", "dbpath", "db.poolsize" },
			{ "talib.daysbackwardday" }};
	private final String[][] keyComments = {
			{ 
					"From email address", "To email address",
					"Smtp server dns name or IP", "Smtp server login name",
					"Smtp server login password", "Activate email error logging" 
			},
			{ 		
					//"Default file name for a potential supplement of stocks",
					//"Default web provider where you will retrieve the stocks list from ",
					"Default quotations provider ",
					//"Toogle MAS analysis availability in the Events Window",
					//"Number of concurrent threads running during calculations"
			},
			{ 
					"DataBase software name", "Database login user name", "Database login password", 
					"DataBase software driver full Class name",
					"Database name", "Database path", "Number of simultaneous connections" 
			},
			{ 
					"Number of additional analysis calculation days before every calculations" 
			}};
	private final String hiddenKeys[] = {"mail.password","password","http.password"};
	private  List<String> hiddenKeysList = Arrays.asList(hiddenKeys);
	
	public static void main(String[] args) throws FileNotFoundException {

		Display display = Display.getDefault();
		Shell shell = new Shell(display);

//		MainGui.pOPUP_BG = tintedColor(display, new Color(display, 154, 103, 51), 1/4);
//		MainGui.pOPUP_GRP = tintedColor(display, MainGui.pOPUP_BG, 1/4);
		Color brown = new Color(display, 154, 103, 51);
//		Color green = new Color(display, 58, 101, 49);
		MainGui.pOPUP_BG = shadedColor(display, brown, 1.10);
		MainGui.pOPUP_GRP = shadedColor(display, brown, 1.20);

		new DbSettings(shell,"db.properties");

		while (!shell.isDisposed()) {
			try {
				if (!shell.getDisplay().readAndDispatch()) shell.getDisplay().sleep();
			} catch (RuntimeException e) {
				LOGGER.error("Error in MarketSettings Gui : "+e.getMessage(),e);
				LOGGER.debug("Error in MarketSettings Gui : ",e);
			} catch (Error e) {
				LOGGER.error("Error in  Gui : "+e.getMessage(),e);
				LOGGER.debug("Error in  Gui : ",e);
			}
		}

	}
	
	protected static Color shadedColor(Display display, Color original, double factor) {
		int red = original.getRed();
		int green = original.getGreen();
		int blue = original.getBlue();
		return new Color(display, (int) (red*factor), (int) (green*factor), (int) (blue*factor));
	}
	
	protected static Color tintedColor(Display display, Color original, double factor) {
		int red = original.getRed();
		int green = original.getGreen();
		int blue = original.getBlue();
		return new Color(display, red + (int)((255-red)*factor), green + (int)((255-green)*factor), blue + (int)((255-blue)*factor));
	}

	public DbSettings(Shell parent, String pathToProps) throws FileNotFoundException {
		
		super(new Shell(parent, SWT.SHELL_TRIM));
		
		this.pathToProps = pathToProps;
		this.propList = new Properties();

		try {
			File propFile = new File(this.pathToProps);
			FileInputStream propFileIS = new FileInputStream(propFile);
			this.propList.load(propFileIS);

		} catch (FileNotFoundException e) {
			LOGGER.error("Can't find file s"+pathToProps,e);
		} catch (IOException e) {
			LOGGER.error("Error accessing file s"+pathToProps,e);
		}
		
		this.initGui();
		this.getParent().open();
	}

	private void initGui() throws FileNotFoundException {
		
		getParent().setText(MainGui.APP_NAME+" - Settings");
		getParent().setBackground(MainGui.pOPUP_BG);
		getParent().setToolTipText("For more settings, see db.properties file at the root of the project installation folder.");
		
		GridLayout layout = new GridLayout();
		this.getParent().setLayout(layout);
		
		{
			final Group tabs = new Group(this.getParent(), SWT.NONE);
			tabs.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
			tabs.setBackground(MainGui.pOPUP_GRP);
			GridLayout grpLayout = new GridLayout();
			tabs.setLayout(grpLayout);
			{
				final CTabFolder tabF = new CTabFolder(tabs, SWT.BORDER);
				GridData tabFLayout = new GridData(SWT.FILL,SWT.FILL,true,false);
				tabF.setLayoutData(tabFLayout);
				tabF.setBackground(MainGui.pOPUP_GRP);
				tabF.setSelectionBackground(MainGui.tAB_SELECTION);
				tabF.setLayout(new FillLayout());
				tabF.setFont(MainGui.DEFAULTFONT);
				tabF.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						tabF.layout();
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						tabF.layout();
					}
					
				});
				CTabItem[] tabI = new CTabItem[configTab.length];
				for (int j = 0; j < configTab.length; j++) {
					tabI[j] = new CTabItem(tabF, SWT.NONE);
					tabI[j].setText(configTab[j]);
					{
						Group tabGroup = new Group(tabF, SWT.SHADOW_NONE);
						tabGroup.setBackground(MainGui.pOPUP_GRP);
						GridLayout tabLayout = new GridLayout(2, false);
						tabGroup.setLayout(tabLayout);
					
						{
							Label tabDescr = new Label(tabGroup,SWT.WRAP);
							GridData gdDescr = new GridData(SWT.FILL, SWT.FILL,true, false);
							gdDescr.horizontalSpan=2;
							tabDescr.setLayoutData(gdDescr);
							tabDescr.setBackground(MainGui.pOPUP_GRP);
							tabDescr.setFont(MainGui.DEFAULTFONT);
							tabDescr.setText("Description : \n\t" + configTabComment[j]);
							
							Label keyTxt[] = new Label[keys[j].length];
							Text keyVal[] = new Text[keys[j].length];
							GridData gdTxt = new GridData(SWT.FILL, SWT.FILL,true, false);
							GridData gdVal = new GridData(SWT.FILL, SWT.FILL,true, false);
							for (int i = 0; i < keys[j].length; i++) {
								{
									keyTxt[i] = new Label(tabGroup, SWT.NONE);
									keyTxt[i].setFont(MainGui.DEFAULTFONT);
									keyTxt[i].setText(keyComments[j][i] + " : ");
									keyTxt[i].setLayoutData(gdTxt);
									keyTxt[i].setBackground(MainGui.pOPUP_GRP);
									keyVal[i] = (hiddenKeysList.contains(keys[j][i]))?new Text(tabGroup, SWT.NONE|SWT.PASSWORD):new Text(tabGroup, SWT.NONE);
									keyVal[i].setFont(MainGui.CONTENTFONT);
									keyVal[i].setLayoutData(gdVal);
									
									String defaultValue = "";
									if (propList.containsKey(keys[j][i])) {
										defaultValue = propList.getProperty(keys[j][i]);
									} else {
										defaultValue = MainPMScmd.getMyPrefs().get(keys[j][i],"");
									}
									keyVal[i].setText(defaultValue);
									
									final String key = keys[j][i];
									keyVal[i].addModifyListener(new ModifyListener() {
										public void modifyText(ModifyEvent e) {
											propList.setProperty(key,((Text)e.widget).getText());
										}
									});
								}
							}
						}
						tabI[j].setControl(tabGroup);
					}
				}
			}
		}
		{
			Group buttons = new Group(getParent(), SWT.NONE);
			buttons.setLayoutData(new GridData(SWT.END,SWT.FILL,true,true));
			buttons.setBackground(MainGui.pOPUP_GRP);
			buttons.setFont(MainGui.DEFAULTFONT);
			RowLayout buttonsLayout = new RowLayout(SWT.HORIZONTAL);
			buttons.setLayout(buttonsLayout);
			{
				Button cancel = new Button(buttons, SWT.PUSH);
				cancel.setFont(MainGui.DEFAULTFONT);
				cancel.setText("Cancel");
				cancel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent arg0) {
							getParent().close();
							getParent().dispose();
					}
				});
				
				Button save = new Button(buttons, SWT.PUSH);
				save.setFont(MainGui.DEFAULTFONT);
				save.setText("Save");
				save.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent arg0) {
						
						try {
							DbSettings.this.getParent().getShell().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
							
							FileOutputStream fos = new FileOutputStream(pathToProps);
							propList.store(fos,"File over written by the settings feature");
						} catch (FileNotFoundException e) {
							LOGGER.error("Can't find file "+pathToProps,e);
						} catch (IOException e) {
							LOGGER.error("Can't access file "+pathToProps,e);
						} finally {
							
							SpringContext ctx = new SpringContext(pathToProps);
							ctx.setMasSource(pathToProps,"false");
							ctx.loadBeans(new String[] {"/connexions.xml", "/swtclients.xml","talibanalysisservices.xml"});
							ctx.refresh();
							
							DbSettings.this.getParent().getShell().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
						}
						
						UserDialog dialog = new UserDialog(getParent(), "The new settings will take effect at next restart.", null);
						dialog.open();
						getParent().close();
						getParent().dispose();
					}
				});
			}
		}
		
		getParent().layout();
		getParent().pack();
	}
	
}
