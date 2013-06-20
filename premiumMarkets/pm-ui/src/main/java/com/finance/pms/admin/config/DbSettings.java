/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
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
import org.eclipse.swt.graphics.Image;
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
import com.finance.pms.SpringContext;
import com.finance.pms.UserDialog;
import com.finance.pms.admin.install.logging.MyLogger;

/**
 * The Class DbSettings.
 * 
 * @author Guillaume Thoreton
 */
public class DbSettings extends Dialog {

	protected static MyLogger LOGGER = MyLogger.getLogger(DbSettings.class);

	final private Properties propList;
	
	/** The path to props. */
	final private String pathToProps;
	
	/** The config tab. */
	private final String[] configTab = { "EMail setting", "Feeds performance and defaults", "Data Connection", "Ta lib"};
			//, "Http Connection" };
	
	/** The configtab comment. */
	private final String[] configtabComment = {
			"Email setting - Please fill in",
			"Feeds performance and defaults",
			"Data Connection parameters - Change default data base connection",
		    "Ta lib - Indicator calculation tunning"};
			//"Http Connection - Fill in only to change web market access defaults" };
	
	/** The keys. */
	private final String[][] keys = {
			{ "mail.from", "mail.to", "mail.host", "mail.username", "mail.password", "mail.log.activated" },
			{ "quotes.listfile", "quotes.listprovider", "quotes.provider", "analyse.mas.enable", "semaphore.nbthread" },
			{ "software", "username", "password", "driver", "database", "dbpath", "db.poolsize" },
			//{ "mas.poolsize", "mas.semaphore.nbthread", "mas.ctimeout", "mas.daysbackwardday", "mas.logserver" },
			{ "talib.daysbackwardday" }};
			//, { "http.login", "http.password", "http.poolsize" } };
	
	/** The key comments. */
	private final String[][] keyComments = {
			{ 
					"From email address", "To email address",
					"Smtp server dns name or IP", "Smtp server login name",
					"Smtp server login password", "Activate email error logging" 
			},
			{ 		
					"File name for a potential supplement of stocks",
					"Default web provider where you will retrieve the stocks list from ",
					"Default web provider where you will get the quotations from ",
					"Toogle MAS analysis availability in the Events Window",
					"Number of concurrent threads running during calculations"
			},
			{ "DataBase software name", "Server login name", "Server login password", 
					"DataBase software driver full Class name",
					"Database name", "Database path", "Number of simultaneous connections" },
			//{ "mas.poolsize", "mas.semaphore.nbthread", "mas.ctimeout", "mas.daysbackwardday", "mas.logserver" },
			{ "Number of days the analysis should start before the choosen start date" }};
			//{ "Server login name", "Server login password", "Number of simultaneous connections" } };
	
	
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
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			DbSettings dbSettings = new DbSettings(shell,"db.properties");
			try {
				dbSettings.initGui();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
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
	
	/**
	 * Instantiates a new db settings.
	 * 
	 * @param pathToProps the path to props
	 * @param shell the shell
	 * 
	 * @author Guillaume Thoreton
	 * @throws FileNotFoundException 
	 */
	public DbSettings(Shell shell, String pathToProps) throws FileNotFoundException {
		
		super(new Shell(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE));
		
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

	/**
	 * Inits the gui.
	 * 
	 * @author Guillaume Thoreton
	 * @throws FileNotFoundException 
	 */
	private void initGui() throws FileNotFoundException {
		
		FileInputStream iconImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/icon.img"));
		getParent().setImage(new Image(getParent().getDisplay(),iconImg));
		getParent().setText("Premium Markets - Settings");
		getParent().setBackground(MainGui.pOPUP_BG);
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
				CTabItem[] tabI = new CTabItem[configTab.length];
				for (int j = 0; j < configTab.length; j++) {
					tabI[j] = new CTabItem(tabF, SWT.NONE);
					tabI[j].setText(configTab[j]);
					{
						Group tabGroup = new Group(tabF, SWT.SHADOW_NONE);
						tabGroup.setBackground(MainGui.pOPUP_GRP);
						GridLayout tabLayout = new GridLayout(2,false);
						tabGroup.setLayout(tabLayout);
					
						{
							Label tabDescr = new Label(tabGroup,SWT.WRAP);
							GridData gdDescr = new GridData(SWT.FILL, SWT.FILL,true, false);
							gdDescr.horizontalSpan=2;
							tabDescr.setLayoutData(gdDescr);
							tabDescr.setBackground(MainGui.pOPUP_GRP);
							tabDescr.setFont(MainGui.DEFAULTFONT);
							tabDescr.setText("Description : \n\t" + configtabComment[j]);
							
							Label keyTxt[] = new Label[keys[j].length];
							Text keyVal[] = new Text[keys[j].length];
							GridData gdTxt = new GridData(SWT.FILL, SWT.FILL,true, false);
							GridData gdVal = new GridData(SWT.FILL, SWT.FILL,true, false);
							for (int i = 0; i < keys[j].length; i++) {
								{
									keyTxt[i] = new Label(tabGroup,SWT.NONE);
									keyTxt[i].setFont(MainGui.DEFAULTFONT);
									keyTxt[i].setText(keyComments[j][i] + " : ");
									keyTxt[i].setLayoutData(gdTxt);
									keyTxt[i].setBackground(MainGui.pOPUP_GRP);
									keyVal[i] = (hiddenKeysList.contains(keys[j][i]))?new Text(tabGroup, SWT.NONE|SWT.PASSWORD):new Text(tabGroup, SWT.NONE);
									keyVal[i].setFont(MainGui.CONTENTFONT);
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
							//ctx.setDataSource(pathToProps);
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
