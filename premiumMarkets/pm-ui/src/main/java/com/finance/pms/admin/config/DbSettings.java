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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class DbSettings.
 * 
 * @author Guillaume Thoreton
 */
public class DbSettings {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(DbSettings.class);
	
	/** The shell. */
	private Shell shell;
	
	/** The prop list. */
	final private Properties propList;
	
	/** The path to props. */
	final private String pathToProps;
	
	/** The config tab. */
	private final String[] configTab = { "eMail Parameters", "Feeds performance and defaults", "Data Connection", 
			"Mas", "Ta lib", "Http Connection" };
	
	/** The configtab comment. */
	private final String[] configtabComment = {
			"Email Parameters - Please fill in",
			"Feeds performance and defaults",
			"Data Connection parameters - Change default data base connection",
			"Mas - Touch only if know about it", "Ta lib - Indicator calculation tunning",
			"Http Connection - Fill in only to change web market access defaults" };
	
	/** The keys. */
	private final String[][] keys = {
			{ "mail.from", "mail.to", "mail.host", "mail.username", "mail.password", "mail.log.activated" },
			{ //"quotes.lastfetch", //"quotes.lastlistfetch",
				"quotes.listfile", "quotes.listprovider", "quotes.provider", "analyse.mas.enable", "semaphore.nbthread" },
			{ "software", "username", "password", "driver", "database", "dbpath", "db.poolsize" },
			{ "mas.poolsize", "mas.semaphore.nbthread", "mas.ctimeout", "mas.daysbackwardday", "mas.logserver" },
			{ "talib.daysbackwardday" }, { "http.login", "http.password", "http.poolsize" } };
	
	/** The key comments. */
	private final String[][] keyComments = {
			{ 
					"From email address", "To email address",
					"Smtp server dns name or IP", "Smtp server login name",
					"Smtp server login password", "Activate email error logging" 
			},
			{ 		
					//"Default date since when the quotation haven't been fetch for this session",
					//"Default date since when the list of stock haven't been updated for this session",
					"File name for a potential suplement of stocks",
					"Default web provider where you will retrieve the stocks list from ",
					"Default web provider where you will get the quotations from ",
					"Toogle MAS analysis availability in the Events Window",
					"Number of concurrent threads running during calculations"
			},
			{ "DataBase software name", "Server login name", "Server login password", 
					"DataBase software driver full Class name",
					"Database name", "Database path", "Number of simultaneous connections" },
			{ "mas.poolsize", "mas.semaphore.nbthread", "mas.ctimeout", "mas.daysbackwardday", "mas.logserver" },
			{ "Number of days the analysis should start before the choosen start date" },
			{ "Server login name", "Server login password", "Number of simultaneous connections" } };
	
	
	/** The hidden keys. */
	private final String hiddenKeys[] = {"mail.password", "password","http.password"};
	
	/** The hidden keys list. */
	private  List<String> hiddenKeysList = Arrays.asList(hiddenKeys);
	
	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void main(String[] args) {
		
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			DbSettings dbSettings = new DbSettings(shell,"db.properties");
			dbSettings.open();
			
			while (!shell.isDisposed()) {
				try {
					if (!shell.getDisplay().readAndDispatch())
						shell.getDisplay().sleep();
				} catch (RuntimeException e) {
					LOGGER.error("Error in MarketSettings Gui : "+e.getMessage(),e);
					LOGGER.debug("Error in MarketSettings Gui : ",e);
				} catch (Error e) {
					LOGGER.error("Error in  Gui : "+e.getMessage(),e);
					LOGGER.debug("Error in  Gui : ",e);
				}
			}
			
	}
	
	/**
	 * Instantiates a new db settings.
	 * 
	 * @param pathToProps the path to props
	 * @param shell the shell
	 * 
	 * @author Guillaume Thoreton
	 */
	public DbSettings(Shell shell, String pathToProps) {
		
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
		
		this.shell = new Shell(shell,SWT.RESIZE);
		this.open();
		this.shell.open();;
	}



	/**
	 * Open.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void open() {
		try {
			GridLayout compositeLayout = new GridLayout();
			compositeLayout.numColumns = 1;
			compositeLayout.makeColumnsEqualWidth = true;
			FileInputStream iconImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/icon.img"));
			getShell().setImage(new Image(getShell().getDisplay(),iconImg));
			getShell().setText("Premium Markets - Settings");
			getShell().setBackground(new Color(getShell().getDisplay(),239,183,103));
			getShell().setLayout(compositeLayout);
			this.initData();
			this.initGui();
		
		} catch (Exception e) {
			LOGGER.error("",e);
		}
	}
	
	/**
	 * Inits the gui.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void initGui() {
		
		{
			Group tabs = new Group(getShell(), SWT.NONE);
			tabs.setBackground(new Color(getShell().getDisplay(),239, 203, 152));
			GridData gridData = new GridData();
	 		gridData.horizontalAlignment = GridData.FILL;
	 		gridData.verticalAlignment = GridData.FILL;
	 		gridData.grabExcessHorizontalSpace = true;
	 		gridData.grabExcessVerticalSpace = true;
	 		tabs.setLayoutData(gridData);
			GridLayout grpLayout = new GridLayout();
			tabs.setLayout(grpLayout);
			{
				CTabFolder tabF = new CTabFolder(tabs, SWT.BORDER);
				GridData tabFLayout = new GridData();
				tabFLayout.verticalAlignment = GridData.FILL;
				tabFLayout.horizontalAlignment = GridData.FILL;
				tabFLayout.grabExcessHorizontalSpace = true;
				tabFLayout.grabExcessVerticalSpace = true;
				tabF.setLayoutData(tabFLayout);
				tabF.setBackground(new Color(getShell().getDisplay(),239, 203, 152));
				tabF.setSelectionBackground(new Color(getShell().getDisplay(),239,183,103));
				tabF.setLayout(new GridLayout());
				CTabItem[] tabI = new CTabItem[configTab.length];
				for (int j = 0; j < configTab.length; j++) {
					tabI[j] = new CTabItem(tabF, SWT.NONE);
					tabI[j].setText(configTab[j]);
					{
						Group tabGroup = new Group(tabF, SWT.SHADOW_NONE);
						tabGroup.setBackground(new Color(getShell().getDisplay(),239, 203, 152));
						GridLayout tabLayout = new GridLayout();
						tabLayout.numColumns = 2;
						tabLayout.makeColumnsEqualWidth = true;
						tabGroup.setLayout(tabLayout);
					
						{
							Label tabDescr = new Label(tabGroup,SWT.WRAP);
							GridData gdDescr = new GridData();
							gdDescr.horizontalAlignment = GridData.FILL;
							gdDescr.grabExcessHorizontalSpace = true;
							gdDescr.horizontalSpan = 2;
							gdDescr.heightHint = 100;
							tabDescr.setLayoutData(gdDescr);
							tabDescr.setBackground(new Color(getShell().getDisplay(),239, 203, 152));
							tabDescr.setText("Description : \n\t" + configtabComment[j]);
							
							Label keyTxt[] = new Label[keys[j].length];
							Text keyVal[] = new Text[keys[j].length];
							GridData gdTxt = new GridData(GridData.FILL_BOTH);
							gdTxt.grabExcessHorizontalSpace = true;
							gdTxt.grabExcessVerticalSpace = true;
							gdTxt.heightHint = 100;
							GridData gdVal = new GridData(GridData.VERTICAL_ALIGN_BEGINNING|GridData.FILL_HORIZONTAL);
							//gdVal.verticalAlignment = GridData.VERTICAL_ALIGN_BEGINNING;
							for (int i = 0; i < keys[j].length; i++) {
								{
									keyTxt[i] = new Label(tabGroup,SWT.WRAP);
									keyTxt[i].setText(keyComments[j][i] + " : ");
									keyTxt[i].setLayoutData(gdTxt);
									keyTxt[i].setBackground(new Color(getShell().getDisplay(),239, 203, 152));
									keyVal[i] = (hiddenKeysList.contains(keys[j][i]))?new Text(tabGroup, SWT.NONE|SWT.PASSWORD):new Text(tabGroup, SWT.NONE);
									keyVal[i].setLayoutData(gdVal);
									keyVal[i].setText((propList.containsKey(keys[j][i]))?propList.getProperty(keys[j][i]):"");
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
			Group buttons = new Group(getShell(), SWT.BOTTOM);
			buttons.setBackground(new Color(getShell().getDisplay(),239, 203, 152));
			buttons.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			RowLayout buttonsLayout = new RowLayout(SWT.HORIZONTAL);
			buttons.setLayout(buttonsLayout);
			{
				Button cancel = new Button(buttons, SWT.NONE);
				cancel.setText("Cancel");
				cancel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent arg0) {
							getShell().close();
							getShell().dispose();
					}
				});
				
				Button save = new Button(buttons, SWT.NONE);
				save.setText("Save");
				save.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent arg0) {
						LOGGER.debug("Saving settings ...");
						try {
							FileOutputStream fos = new FileOutputStream(pathToProps);
							propList.store(fos,"File over written by the settings feature");
						} catch (FileNotFoundException e) {
							LOGGER.error("Can't find file "+pathToProps,e);
						} catch (IOException e) {
							LOGGER.error("Can't access file "+pathToProps,e);
						} finally {
							SpringContext ctx = new SpringContext();
							ctx.setDataSource(pathToProps);
							//ctx.setProvidersSource(pathToProps);
							ctx.setMasSource(pathToProps,"false");
							ctx.loadBeans(new String[] {"/connexions.xml", "/swtclients.xml","talibanalysisservices.xml","masanalysisservices.xml"});
							ctx.refresh();
						}
						
						getShell().close();
						getShell().dispose();
					}
				});
			}
		}
	}
	
	/**
	 * Inits the data.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void initData() {
		
	}

	/**
	 * Gets the shell.
	 * 
	 * @return the shell
	 */
	public Shell getShell() {
		return shell;
	}
	
	
	
}
