/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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
package com.finance.pms;


import java.awt.Desktop;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.springframework.beans.factory.BeanCreationException;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.DbSettings;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.config.IndicatorsConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.EventModel;
import com.finance.pms.datasources.EventRefreshController;
import com.finance.pms.datasources.EventTaskQueue;
import com.finance.pms.datasources.InvalidEventRefreshTask;
import com.finance.pms.datasources.RefreshAllEventStrategyEngine;
import com.finance.pms.datasources.RefreshFourToutStrategyEngine;
import com.finance.pms.datasources.RefreshMonitoredStrategyEngine;
import com.finance.pms.datasources.RefreshPortfolioStrategyEngine;
import com.finance.pms.datasources.ShareListInfo;
import com.finance.pms.datasources.TaskId;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.datasources.web.ProvidersInflation;
import com.finance.pms.events.AnalysisClient;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.gui.EventsComposite;
import com.finance.pms.events.gui.OperationBuilderDialog;
import com.finance.pms.portfolio.ShareListMgr;
import com.finance.pms.portfolio.gui.PortfolioComposite;
import com.finance.pms.portfolio.gui.charts.ChartsComposite;
import com.finance.pms.sharelists.gui.ShareListUpdateDialog;
import com.finance.pms.threads.ConfigThreadLocal;

/**
 * @author Guillaume Thoreton
 */
public class MainGui extends SashForm implements RefreshableView { 

	private static MyLogger LOGGER = MyLogger.getLogger(MainGui.class);

	private Integer cursorCpt = 0;

	private Menu mainMenu;

	private MenuItem settingsMenuItem;
	private Menu settingsSubMenu;
	private MenuItem settingsSubMenuItem;
	private DbSettings dbSettings;

	private MenuItem stockMenuItem;
	private MenuItem refreshStockListMenuItem;
	private MenuItem refreshRecommendationsMenuItem;

	private MenuItem quotationMenuItem;
	private MenuItem refreshAllPortfoliosQuotationsMenuItem;
	private MenuItem refreshMonitoredQuotationsMenuItem;

	private MenuItem eventsMenuItem;
	private Menu eventsSubMenu;
	public static MenuItem viewEventsMenuItem;
	private OperationBuilderDialog builderDialog;
	
	private  MenuItem portfolioMenuItem;
	public static Menu portfolioSubMenu;
	private MenuItem viewPortfolioMenuItem;
	
	public static Menu chartSubMenu;
	
	private MenuItem fileMenuItem;
	private Menu fileSubMenu;
	private MenuItem exitMenuItem;
	
	
	private static String dbfile;
	
	public static Font DEFAULTFONT;
	public static Font CONTENTFONT;
	public static String ICONNAME = "icon.image";


	private SashForm sashes;
	private  Composite[] winTable = new Composite[3];
	private LogComposite logComposite;
	

	private EventModel<RefreshAllEventStrategyEngine, Collection<ShareListInfo>> allStocksEventModel;
	private EventModel<RefreshMonitoredStrategyEngine, Collection<Stock>> monitoredStocksEventModel;
	private EventModel<RefreshPortfolioStrategyEngine, Collection<Stock>> portfolioStocksEventModel;
	private EventModel<RefreshFourToutStrategyEngine, Object> fourToutStrategyEventModel;

	public static Color eVENTS_LIGHT;
	public static Color eVENTS_DARKER;
	public static Color cHART_LIGHT;
	public static Color cHART_DARKER;
	public static Color pORTFOLIO_DARKER;
	public static Color pORTFOLIO_LIGHT;
	public static Color pOPUP_BG;
	public static Color pOPUP_GRP;
	public static Color tAB_SELECTION;

	public MainGui(Composite parent, String dbFilePath) {
		
		super(parent, SWT.VERTICAL);
		this.setLayout(new FillLayout()); 
		
		MainGui.dbfile = dbFilePath;
		
		ShareListMgr shareListMgr = (ShareListMgr) SpringContext.getSingleton().getBean("shareListMgr");
		ConfigThreadLocal.set(EventSignalConfig.EVENT_SIGNAL_NAME, shareListMgr.initPkgDependentConfig());
		ConfigThreadLocal.set("indicatorParams", new IndicatorsConfig());
		
		AnalysisClient.setEmailMsgQeueingFilter(EmailFilterEventSource.uiSet());
		
		sashes = new SashForm(this, SWT.HORIZONTAL);
		logComposite = new LogComposite(this);
				
		allStocksEventModel = EventModel.getInstance(new RefreshAllEventStrategyEngine(), logComposite);
		monitoredStocksEventModel = EventModel.getInstance(new RefreshMonitoredStrategyEngine(), logComposite);
		portfolioStocksEventModel = EventModel.getInstance(new RefreshPortfolioStrategyEngine(), logComposite);
		fourToutStrategyEventModel = EventModel.getInstance(new RefreshFourToutStrategyEngine(), logComposite);
		
		initShellAndMenus();
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				
				try {
					
					DateFormat buildDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");
					Properties pbuild = new Properties();
					pbuild.load(this.getClass().getResourceAsStream("/pmsbuild.properties"));
					String currentBuild = pbuild.getProperty("application.buildtime");
					Date currentBuildDate = buildDateFormat.parse(currentBuild); //2009/08/31 12:54:39 BST

					URL sourceforge = new URL("http://sourceforge.net/projects/pmsqueak/files/PremiumMarkets.jnlp");
				    URLConnection yc = sourceforge.openConnection();
				    yc.setReadTimeout(15000);
				    BufferedReader br = new BufferedReader(new InputStreamReader(yc.getInputStream()));

					String line;
					Pattern pattern = Pattern.compile("This version : ([0-9]{4}/[0-9]{2}/[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} [A-Z]*)");
					Matcher fit;
					while (null != (line = br.readLine())) {
						fit = pattern.matcher(line);
						if (fit.find()) {
							boolean isObsolete = isObsolete(line, fit, currentBuildDate);
							if (isObsolete) {
								ActionDialog dialog = new ActionDialog(getShell(), "A new version is available!", "-------------  A new version is available -------------", null, "Click to update. Close this popup otherwise.", new ActionDialogAction() {
									
									@Override
									public void action(Control targetControl) {
										//Program.launch("http://premiummarkets.elasticbeanstalk.com/html/PremiumMarkets.jnlp");
										Program.launch("http://premiummarkets.elasticbeanstalk.com/html/swtui.html#Download");
										rootShellClosedRequested(null);
									}
								});
								dialog.open();
							}
							break;
						} 
					}
					
				} catch (Exception e) {
					LOGGER.warn(e,e);
				}
				
			}
		};
		
		getDisplay().asyncExec(runnable);

		initContent();
		this.layout();
		
	}
	
	private boolean isObsolete(String line, Matcher fit, Date currentBuildDate) throws ParseException {
		
		 DateFormat jnlpDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");
		 
		LOGGER.info("Found pattern in : "+line);
		String versionNumber = fit.group(1).replaceAll("[ /:]", ".");
		Date lastReleaseDate = jnlpDateFormat.parse(fit.group(1));
		Calendar lastReleaseCal = Calendar.getInstance();
		lastReleaseCal.setTime(lastReleaseDate);
		lastReleaseCal.set(Calendar.MILLISECOND, 0);
		lastReleaseCal.set(Calendar.SECOND, 0);
		lastReleaseCal.add(Calendar.MINUTE, -10); //Fix pom timestamp recreated issue
		
		Calendar currentBuildCal= Calendar.getInstance();
		currentBuildCal.setTime(currentBuildDate);
		currentBuildCal.set(Calendar.MILLISECOND, 0);
		currentBuildCal.set(Calendar.SECOND, 0);

		LOGGER.info("Latest version is : "+versionNumber+" released on "+jnlpDateFormat.format(lastReleaseDate)+" +- 10 minutes.");
		LOGGER.info("Your version was released on the "+jnlpDateFormat.format(currentBuildDate));
		return  (lastReleaseCal.getTime().after(currentBuildCal.getTime()));
		
	}
	

	private void initShellAndMenus() {
		try {
			
			try {
				FileInputStream iconImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons"+File.separator+MainGui.ICONNAME));
				getShell().setImage(new Image(getDisplay(),iconImg));
			} catch (Exception e1) {
				LOGGER.warn(e1);
			}
			getShell().setText("Premium Markets");
			getShell().setFont(MainGui.DEFAULTFONT);
			this.setBackground(MainGui.eVENTS_DARKER);

			getShell().addShellListener(new ShellAdapter() {
				@Override
				public void shellClosed(ShellEvent evt) {
					MainGui.this.rootShellClosedRequested(evt);
				}
			});

			FormLayout thisLayout = new FormLayout();
			this.setLayout(thisLayout);

			{
				mainMenu = new Menu(getShell(), SWT.BAR);
				getShell().setMenuBar(mainMenu);
				{
					fileMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
					fileMenuItem.setText("Premium Markets");
					FileInputStream exitImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/exit.png"));
					Image exitImage = new Image(getDisplay(),exitImg);
					fileMenuItem.setImage(new Image(getDisplay(), exitImage.getImageData()));
					fileSubMenu = new Menu(fileMenuItem);
					fileMenuItem.setMenu(fileSubMenu);
					{
						MenuItem forecast = new MenuItem(fileSubMenu, SWT.CASCADE);
						forecast.setText("The Forecast Engine (NEW)");
						forecast.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent evt) {
								Program.launch("http://premiummarkets.elasticbeanstalk.com/");
							}
						});
					}
					{
						MenuItem doc = new MenuItem(fileSubMenu, SWT.CASCADE);
						doc.setText("Project documentation");
						doc.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent evt) {
								Program.launch("http://premiummarkets.elasticbeanstalk.com/html/swtui.html");
							}
						});
					}
					new MenuItem(fileSubMenu, SWT.SEPARATOR);
					{
						MenuItem about = new MenuItem(fileSubMenu, SWT.CASCADE);
						about.setText("About");
						about.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent evt) {
								try {
									Properties pbuild = new Properties();
									pbuild.load(ClassLoader.getSystemClassLoader().getResourceAsStream("pmsbuild.properties"));
									UserDialog dialog = new UserDialog(getShell(),"Premium Markets is an automated stock market analysis system.\n"+
									"It implements a graphical environment for monitoring stock market technical analysis major indicators, portfolio management and historical data charting.\n\n"+
									"See the new Premium Markets FORECAST web portal at http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo of the Forecast engine.\n\n\n\n"+
									"\nPremium Markets\nCopyright (C) 2008-2014 Guillaume Thoreton, see <http://www.gnu.org/licenses/>\nBuild : "+pbuild.getProperty("application.buildtime")+"\n", null);
									dialog.open();				
									
								} catch (IOException e) {
									LOGGER.debug(e);
								}
							}
						});
					}
					new MenuItem(fileSubMenu, SWT.SEPARATOR);
					{
						exitMenuItem = new MenuItem(fileSubMenu, SWT.CASCADE);
						exitMenuItem.setText("Exit");
						exitMenuItem.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent evt) {
								closeButtonMouseDown(evt);
							}
						});
					}
				}
				{
					stockMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
					stockMenuItem.setText("Stocks and Markets");
					FileInputStream portImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/port.png"));
					stockMenuItem.setImage(new Image(getDisplay(),portImg));
					{
						Menu stockSubMenu = new Menu(stockMenuItem);
						{
							refreshStockListMenuItem = new MenuItem(stockSubMenu, SWT.CASCADE);
							refreshStockListMenuItem.setText("Add or Update a set of stocks ...");
							refreshStockListMenuItem.addSelectionListener(new EventRefreshController(allStocksEventModel, this, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
								
								private void superwidgetSelected() {
									super.widgetSelected(null);
								}
								
								
								@Override
								public void widgetSelected(SelectionEvent evt) {
									
									final ShareListUpdateDialog instance = new ShareListUpdateDialog(getShell());
									ActionDialogAction actionDialogAction = new ActionDialogAction() {
										
										@Override
										public void action(Control targetControl) {
											
											Providers provider = instance.getProvider();
											if (provider != null && instance.getIsOk()) {
												
												allStocksEventModel.setViewParamRoot(Arrays.asList(new ShareListInfo[]{new ShareListInfo(Providers.providerShareListName(provider))}));
												LOGGER.guiInfo("I am refreshing. Thanks for waiting ...");
												allStocksEventModel.setLastListFetch(EventModel.DEFAULT_DATE);
												updateEventRefreshModelState(0l, TaskId.FetchLists);
												initRefreshAction();
												superwidgetSelected();

											}
										}
									};
									instance.setAction(actionDialogAction);
									instance.open();
							
								}
							});
						}
						{
							refreshRecommendationsMenuItem = new MenuItem(stockSubMenu, SWT.CASCADE);
							refreshRecommendationsMenuItem.setText("Update web recommendations and advice download for your existing stock lists.");
							refreshRecommendationsMenuItem.addSelectionListener(new EventRefreshController(allStocksEventModel, this, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									
									refreshRecommendationsMenuItem.addSelectionListener(new SelectionAdapter() {
										@Override
										public void widgetSelected(SelectionEvent evt) {
											ActionDialog actionDialog = new ActionDialog(getShell(), "Info",
													"Web recommendation and advice feature is not available in this open source version.\n"+ 
															"This feature is part of the advanced version including Premium Markets Forecast engine.\n",
															null,
															"Click here for more information and a workable demo",
													new ActionDialogAction() {
	
														@Override
														public void action(Control targetControl) {
															Program.launch("http://premiummarkets.elasticbeanstalk.com");
													}
											});
										  actionDialog.open();
										}
									});
								}
							});
							refreshRecommendationsMenuItem.setEnabled(true);
						}
						stockMenuItem.setMenu(stockSubMenu);
					}
				}
				{
					portfolioMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
					portfolioMenuItem.setText("Portfolios");
					FileInputStream portImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/port.png"));
					portfolioMenuItem.setImage(new Image(getDisplay(),portImg));
					{
						portfolioSubMenu = new Menu(portfolioMenuItem);
						portfolioMenuItem.setMenu(portfolioSubMenu);
						{
							viewPortfolioMenuItem = new MenuItem(portfolioSubMenu, SWT.CHECK);
							viewPortfolioMenuItem.setText("View");
							viewPortfolioMenuItem.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									setMainDisplay();
									MenuItem[] items = portfolioSubMenu.getItems();
									for (MenuItem menuItem : items) {
										if (!menuItem.equals(viewPortfolioMenuItem)) menuItem.setEnabled(viewPortfolioMenuItem.getSelection());
									}
								}
							});
							viewPortfolioMenuItem.setSelection(true);
						}
						new MenuItem(portfolioSubMenu, SWT.SEPARATOR);
						{
							MenuItem loadPortofolioFromGnuCash = new MenuItem(portfolioSubMenu,SWT.CASCADE);
							loadPortofolioFromGnuCash.setText("Load Portfolio from GNUCASH 'Advanced Portfolio' HTML export ...");
							loadPortofolioFromGnuCash.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									
									final Config evtConfig = ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME);
									Runnable runnable = new Runnable() {
										
										public void run() {
											try {
												ConfigThreadLocal.set(EventSignalConfig.EVENT_SIGNAL_NAME, evtConfig);
												
												Runnable runnable2 = new Runnable() {
													public void run() {
														setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
													}
												};
												getDisplay().syncExec(runnable2);
												((PortfolioComposite) portfolioSash()).loadGnucashAdvPortfolio();
											} finally {
												Runnable runnable2 = new Runnable() {
													public void run() {
														setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
													}
												};
												getDisplay().syncExec(runnable2);
											}
										}
									};
									Thread thread = new Thread(runnable);
									thread.start();
								}
							});	
						}
						{
							MenuItem loadPortofolioFromGnuCash = new MenuItem(portfolioSubMenu,SWT.CASCADE);
							loadPortofolioFromGnuCash.setText("Create AutoPortfolio from gnucash advanced export ...");
							loadPortofolioFromGnuCash.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									((PortfolioComposite) portfolioSash()).loadGnucashAdvPortfolioForAutoPortfolio();
								}
							});
							loadPortofolioFromGnuCash.setEnabled(false);
						}
						{
							MenuItem loadPortofolioFromGnuCash = new MenuItem(portfolioSubMenu,SWT.CASCADE);
							loadPortofolioFromGnuCash.setText("View Portfolio transactions ...");
							loadPortofolioFromGnuCash.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									((PortfolioComposite) portfolioSash()).viewPortfolioTransactions();
								}
							});
						}
						new MenuItem(portfolioSubMenu, SWT.SEPARATOR);
					}
				}
				{
					quotationMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
					quotationMenuItem.setText("Quotations");
					FileInputStream portImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/port.png"));
					quotationMenuItem.setImage(new Image(getDisplay(),portImg));
					{
						Menu quotationMenu = new Menu(quotationMenuItem);
						{
							refreshAllPortfoliosQuotationsMenuItem = new MenuItem(quotationMenu, SWT.CASCADE);
							refreshAllPortfoliosQuotationsMenuItem.setText("Update quotations download for all your portfolios"); 
							refreshAllPortfoliosQuotationsMenuItem.addSelectionListener(new EventRefreshController(portfolioStocksEventModel, this, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									LOGGER.guiInfo("I am refreshing. Thanks for waiting ...");
									portfolioStocksEventModel.setViewParamRoot(null); //reset (should mean all portfolio stocks)
									portfolioStocksEventModel.setLastQuotationFetch(EventModel.DEFAULT_DATE);
									this.updateEventRefreshModelState(0l, TaskId.FetchQuotations);
									initRefreshAction();
									super.widgetSelected(evt);
								}
							});
						}
						{
							refreshMonitoredQuotationsMenuItem = new MenuItem(quotationMenu, SWT.CASCADE);
							refreshMonitoredQuotationsMenuItem.setText("Update quotations download for your monitored shares");
							refreshMonitoredQuotationsMenuItem.addSelectionListener(new EventRefreshController(monitoredStocksEventModel, this, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									LOGGER.guiInfo("I am refreshing. Thanks for waiting ...");
									monitoredStocksEventModel.setLastQuotationFetch(EventModel.DEFAULT_DATE);
									this.updateEventRefreshModelState(0l, TaskId.FetchQuotations);
									initRefreshAction();
									super.widgetSelected(evt);
								}
							});
						}
						{
							final MenuItem refreshInflation = new MenuItem(quotationMenu, SWT.CASCADE);
							refreshInflation.setText("Update Inflation data download (Based on The Consumer Price Index (CPI-U) compiled by the Bureau of Labor Statistics)");
							refreshInflation.addSelectionListener(new EventRefreshController(fourToutStrategyEventModel, this, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
								
								@Override
								public void widgetSelected(SelectionEvent evt) {										
									LOGGER.guiInfo("I am refreshing. Thanks for waiting ...");
									
									fourToutStrategyEventModel.setLastQuotationFetch(EventModel.DEFAULT_DATE);
									//Stock inflationStock  = DataSource.getInstance().loadStockBySymbol(ProvidersInflation.SYMBOL);
									Stock inflationStock = ProvidersInflation.inflationStock();
									fourToutStrategyEventModel.setViewParamRoot(Arrays.asList(new Stock[]{inflationStock}));
									
									this.updateEventRefreshModelState(0l, TaskId.FetchQuotations);
									initRefreshAction();
									super.widgetSelected(evt);
								
								}
							});
						}
						quotationMenuItem.setMenu(quotationMenu);
					}
				}
				{
					MenuItem chartMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
					chartMenuItem.setText("Charts");
					FileInputStream bellImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/chart.png"));
					chartMenuItem.setImage(new Image(getDisplay(), bellImg));	
					{
						chartSubMenu = new Menu(chartMenuItem);
						chartMenuItem.setMenu(chartSubMenu);
					}
				}
				{
					eventsMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
					eventsMenuItem.setText("Events");
					FileInputStream bellImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/bell.png"));
					eventsMenuItem.setImage(new Image(getDisplay(), bellImg));
					{
						eventsSubMenu = new Menu(eventsMenuItem);
						eventsMenuItem.setMenu(eventsSubMenu);
						{
							viewEventsMenuItem = new MenuItem(eventsSubMenu, SWT.CHECK);
							viewEventsMenuItem.setText("View");
							viewEventsMenuItem.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									setMainDisplay();
								}
							});
						}
						new MenuItem(eventsSubMenu, SWT.SEPARATOR);
						{
							MenuItem eventClean = new MenuItem(eventsSubMenu, SWT.CASCADE);
							eventClean.setText("Customise and Create calculators ...");
							eventClean.addSelectionListener(new SelectionAdapter() {

								@Override
								public void widgetSelected(SelectionEvent e) {
									if (builderDialog == null || builderDialog.getShell() == null || builderDialog.getShell().isDisposed()) {
										builderDialog = new OperationBuilderDialog(getShell(), MainGui.this);
										builderDialog.open(null, 0);
									} else {
										builderDialog.getShell().setVisible(true);
										builderDialog.getShell().setActive();
										builderDialog.getShell().setFocus();
									}
								}
								
							});
						}
						new MenuItem(eventsSubMenu, SWT.SEPARATOR);
						{
							MenuItem eventClean = new MenuItem(eventsSubMenu, SWT.CASCADE);
							eventClean.setText("Clear All previous calculations");
							eventClean.addSelectionListener(clearPreviousCalculationsControler());
						}
						{
							MenuItem eventForecast = new MenuItem(eventsSubMenu, SWT.CASCADE);
							eventForecast.setText("Run a forecast based on technical analysis");
							eventForecast.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									ActionDialog actionDialog = new ActionDialog(getShell(), "Info",
											"Running a neural network forecast on technical analysis is not available in this version.\n"+
											"This feature is part of the advanced version including Premium Markets Forecast engine.\n", 
											null,
											"Click here for more information and a workable demo",
											new ActionDialogAction() {

										@Override
										public void action(Control targetControl) {
											Program.launch("http://premiummarkets.elasticbeanstalk.com");

										}
									});
									actionDialog.open();
								}
							});
						}
					}
				}
				{
					settingsSubMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
					settingsSubMenuItem.setText("Settings");
					FileInputStream toolImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/tool.png"));
					settingsSubMenuItem.setImage(new Image(getDisplay(),toolImg));
					{
						settingsSubMenu = new Menu(settingsSubMenuItem);
						new MenuItem(settingsSubMenu, SWT.SEPARATOR);
						{
							settingsMenuItem = new MenuItem(settingsSubMenu, SWT.CASCADE);
							settingsMenuItem.setText("Application Settings ...");
							settingsMenuItem.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									try {
										openSettings();
									} catch (FileNotFoundException e) {
										LOGGER.error(e,e);
									}
								}
							});
						}

						settingsSubMenuItem.setMenu(settingsSubMenu);
					}
				}
			}

			this.layout();
		} catch (Exception e) {
			LOGGER.error("",e);
		}
	}

	public EventRefreshController clearPreviousCalculationsControler() {
		
		return new EventRefreshController(allStocksEventModel, this, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
			
			@Override
			public void widgetSelected(SelectionEvent evt) {
				
				LOGGER.guiInfo("I am clearing the analysis history. Thanks for waiting ...");
				allStocksEventModel.setViewParamRoot(null); //null means reset the param. All will be processed
				allStocksEventModel.setViewParam(0, null);
				this.updateEventRefreshModelState( 0l, TaskId.Clean);
				initRefreshAction();
				super.widgetSelected(evt);
				
			}
		};
		
	}

	public static void main(String[] args) {
		
		Display.setAppName("Premium Markets");
		
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("____________________Uncaught_________________________ "+e.toString());
				e.printStackTrace();
			}
		});
		
		try {
			// Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel(
					UIManager.getCrossPlatformLookAndFeelClassName());
		} 
		catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (InstantiationException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		try {
			
			MainPMScmd.getPrefs().put("mail.infoalert.activated", MainPMScmd.getPrefs().get("quotes.sendeventfromui", "false"));
			
			switch (args.length) {
				case 1 :
					dbfile = args[0];
					break;
			   default :
			   		LOGGER.info("Usage : MainGui <path>/db.properties");
			   		return;
			}
			
			//Spring Context init		
			SpringContext ctx = new SpringContext(dbfile);
			ctx.setMasSource(dbfile,"false");
			ctx.loadBeans(new String[] {"/connexions.xml", "/swtclients.xml","talibanalysisservices.xml"});
			try {
				ctx.refresh();
			} catch (BeanCreationException e1) {
				e1.printStackTrace();
				Frame frame = new JFrame();
				String report = 
						"It may be that premium Markets is already running on this computer. You can run only one instance at a time.\n" +
						"Make sure to close all other instances before starting a new one.\n" +
						"If the process is running in the back ground and is not showing in a window, you will have to kill it. \n" +
						"Use the tools and commands provided by your OS to do so.\n" +
						"In case you don't know these, I am sorry to say that you will have to either ask someone that does or reboot.";
				CustomDialog customDialog = new CustomDialog(frame, report, e1.getMostSpecificCause().toString(), "Error Report", false);
				customDialog.pack();
				customDialog.setVisible(true);
				customDialog.dispose();
				frame.dispose();
				return;
			}
			
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			shell.setLayout(new FillLayout());
			
			setupAppDefaultFont(display, shell);
			setupAppDefaultColors(display);
			
			final MainGui inst = new MainGui(shell,dbfile);
			inst.setCursor(CursorFactory.getCursor(SWT.CURSOR_APPSTARTING));
			
			shell.layout();
            shell.pack();            	
			shell.open();
			
            //Shell
            {
				Point swtPrefSize = inst.computeSize(SWT.DEFAULT, SWT.DEFAULT); 
				Point myPrefSize = new Point(1024,768);
				Point maxSize = new Point(display.getBounds().width*9/10, display.getBounds().height*9/10);
				int width = Math.min(Math.max(swtPrefSize.x,myPrefSize.x), maxSize.x);
				int height =  Math.min(Math.max(swtPrefSize.y,myPrefSize.y), maxSize.y);
				Rectangle shellBounds = shell.computeTrim(0, 0, width, height); 
				shell.setSize(shellBounds.width, shellBounds.height); 
            }
            
        	//Log
            {
				Rectangle mainBounds = shell.getClientArea();
				
				Point logPrefSize = inst.logComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT); 
				Point logCompositeSize = inst.logComposite.computeSize(mainBounds.width, Math.max(10,logPrefSize.y));
				inst.logComposite.setSize(logCompositeSize);
				Rectangle logBounds = inst.logComposite.getBounds();
				int xLog = 100*logBounds.height/mainBounds.height +1;
				if ((100 -xLog) < 97) {
					xLog=3;
				}
				inst.setWeights(new int[]{100-xLog, xLog});
            }
			
			//Portfolio Composite
            {
				Rectangle portfolioShashBounds = ((PortfolioComposite)inst.winTable[2]).getClientArea();
				
				Point port1PrefSize = ((PortfolioComposite)inst.winTable[2]).portfolioInfosGroup.computeSize(SWT.DEFAULT, SWT.DEFAULT); 
				Point port1CompositeSize = ((PortfolioComposite)inst.winTable[2]).portfolioInfosGroup.computeSize(port1PrefSize.x, Math.max(50, port1PrefSize.y));
				((PortfolioComposite) inst.winTable[2]).portfolioInfosGroup.setSize(port1CompositeSize);
				Rectangle infoBounds = ((PortfolioComposite)inst.winTable[2]).portfolioInfosGroup.getBounds();
				
				int x1Port = 100*infoBounds.height/portfolioShashBounds.height + 2;
				if ((100 -x1Port) < 20) {
					x1Port=30;
				}
				((PortfolioComposite)inst.winTable[2]).setWeights(new int[]{100-x1Port, x1Port});
            }
			
			//Chart
            {
				Rectangle chartShashBounds = ((ChartsComposite)inst.winTable[1]).getClientArea();
				
				Point chartPrefSize = ((ChartsComposite)inst.winTable[1]).chartBoutonsGroup.computeSize(SWT.DEFAULT, SWT.DEFAULT); 
				Point chartCompositeSize = ((ChartsComposite)inst.winTable[1]).chartBoutonsGroup.computeSize(chartPrefSize.x, Math.max(50,chartPrefSize.y));
				((ChartsComposite)inst.winTable[1]).chartBoutonsGroup.setSize(chartCompositeSize);
				Rectangle chartButtonsBounds = ((ChartsComposite)inst.winTable[1]).chartBoutonsGroup.getBounds();
				int xChart = 100*chartButtonsBounds.height/chartShashBounds.height;
				if ((100 -xChart) < 20) {
					xChart=30;
				}
				((ChartsComposite)inst.winTable[1]).setWeights(new int[]{100-xChart, xChart});
            }
            
            shell.layout();
            shell.pack();   
			
			inst.setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
			
			//Post inits
			//first time update
			Integer hintNumber = new Integer(MainPMScmd.getPrefs().get("email.hint","0"));
			if (hintNumber == 0) {
				LOGGER.info("First run : updating quotes");
				QuotationUpdate quotationUpdate = new QuotationUpdate();
				quotationUpdate.getQuotesForAllUserPortfoliosAndMonitored();
			} else {
				LOGGER.info("This is not the First run : no quotes update");
			}
			//load portfolios
			try {
				
				((PortfolioComposite)inst.portfolioSash()).updateMoniAndPSCachedModels();
				((PortfolioComposite)inst.portfolioSash()).tabBuildAllTabs(0, new Observer() {
					@Override
					public void update(Observable o, Object arg) {
						((PortfolioComposite)inst.portfolioSash()).backGroundLoadQuotationCache();
					}
				});
				
				ctx.optionalPostInit();
				
			} catch (Exception e1) {
				LOGGER.error(e1,e1);
			}
			
			//Event loop
			System.out.println("IHM RUNNING");
			while (!shell.isDisposed()) {
				try {
					if (!display.readAndDispatch()) display.sleep();
				} catch (Throwable e) {
					try {
						LOGGER.error("Error in Main Gui : "+e.getMessage(),e);
						inst.setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
						UserDialog dialog = new UserDialog(inst.getShell(),"An Error occurred.", e.getMessage());
						dialog.open();
					} catch (Throwable e1) {
						e1.printStackTrace();
					}
				}
			}
	
		} catch (Throwable e) {
			System.out.println("Unhandled error running the ui : "+e);
			e.printStackTrace();
			genericSuddenDeathMsg(e);
		} finally {
			Runtime.getRuntime().exit(1);
		}
		
	}

	private static void genericSuddenDeathMsg(Throwable e) {
		Frame frame = new JFrame();
		String report = 
				"An unexpected error occurred while running the application.\n" +
				"Make sure to close all other instances before starting this one.\n" +
				"Double check that the installation has run successfully.\n" +
				"Check your OS file system attributes and anti viruses as these may prevent the application to fully install.\n" +
				"If you need to reinstall, it is recommended to install in a new folder fresh and free of any older version.\n" +
				"Have a look at http://premiummarkets.elasticbeanstalk.com/html/swtui.html or http://sourceforge.net/projects/pmsqueak/ for support.";
		CustomDialog customDialog = new CustomDialog(frame, report, e.getMessage(), "Error Report", false);
		customDialog.pack();
		customDialog.setVisible(true);
		customDialog.dispose();
		frame.dispose();
	}

	private static void setupAppDefaultColors(Display display) {
		
		eVENTS_LIGHT = new Color(display, 229,205,177);
		eVENTS_DARKER =  new Color(display, 0, 0, 0);
		
		cHART_LIGHT = new Color(display, 229,205,177);
		cHART_DARKER = new Color(display, 0, 0, 0);
		
		pORTFOLIO_LIGHT = new Color(display, 229,205,177);
		pORTFOLIO_DARKER = new Color(display, 0, 0, 0);
		
		pOPUP_BG = new Color(display, 239, 183,103);
		pOPUP_GRP = new Color(display, 239, 203, 152);
		
		tAB_SELECTION = new Color(display, 239, 183,103);
		
	}

	private static void setupAppDefaultFont(Display display, Shell shell) {
		
		if (LOGGER.isTraceEnabled()) {
			System.out.println("Available fonts :");
			FontData[] fd = shell.getDisplay().getFontList(null, true);
			for (int i = 0; i < fd.length; i++) {
				System.out.println(fd[i].getName() + "," + fd[i].getHeight());
			}
			System.out.println("System default font : " + shell.getDisplay().getSystemFont().getFontData()[0].getName());
		}
		
		String myDefFontName = shell.getDisplay().getSystemFont().getFontData()[0].getName();
		String myContentFontName = shell.getDisplay().getSystemFont().getFontData()[0].getName();
		
		if (LOGGER.isTraceEnabled()) {
			FontData[] myDefFont = shell.getDisplay().getFontList(myDefFontName, true);
			if (myDefFont != null && myDefFont.length > 0) {
				System.out.println(myDefFontName+" is available.");
			} else {
				System.out.println(myDefFontName+" not available.");
			}
			
			FontData[] myContentDefFont = shell.getDisplay().getFontList(myContentFontName, true);
			if (myContentFontName != null && myContentDefFont.length > 0) {
				System.out.println(myContentFontName+" is available.");
			} else {
				System.out.println(myContentFontName+" not available.");
			}
		}
		
		MainGui.DEFAULTFONT = new Font(display, myDefFontName, 8, SWT.NORMAL);
		MainGui.CONTENTFONT = new Font(display, myContentFontName, 8, SWT.NORMAL);
	}
	
	private void initContent() {

		EventsComposite eventC = new EventsComposite(sashes, SWT.NONE|SWT.BORDER, logComposite);
		this.winTable[0] = eventC;
		
		ChartsComposite charts = new ChartsComposite(sashes, SWT.NONE|SWT.BORDER, logComposite);
		this.winTable[1] = charts;
		
		PortfolioComposite portfolio = new PortfolioComposite(sashes, charts, SWT.NONE|SWT.BORDER, logComposite);
		this.winTable[2] = portfolio;
		
		MainGui.viewEventsMenuItem.setSelection(false);
		this.viewPortfolioMenuItem.setSelection(true);
		this.setMainDisplay();
		
		LOGGER.debug("Finished initialising MainGui Data.");
	}

	private void openSettings() throws FileNotFoundException {
		
		if (dbSettings == null || dbSettings.getParent().isDisposed()) {
			dbSettings = new DbSettings(this.getShell(), dbfile);
		} else {
			dbSettings.getParent().forceFocus();
		}
	}
	
	protected void setMainDisplay() {
		
		//0 Event, 1 Chart, 2 Portfolio
		
		if (viewEventsMenuItem.getSelection() && !viewPortfolioMenuItem.getSelection()) {
			eventsSash().setVisible(true);
			chartsSash().setVisible(false);
			portfolioSash().setVisible(false);
			sashes.setWeights(new int[]{100,0,0});
			
			if ( this.isVisible() ) ((RefreshableView) eventsSash()).refreshView(new ArrayList<Exception>());
		}
		
		if (!viewEventsMenuItem.getSelection() && !viewPortfolioMenuItem.getSelection()) {
			for (int j = 0; j < winTable.length; j++) {
				winTable[j].setVisible(false);
			}
			
		}
		
		if (viewEventsMenuItem.getSelection() && viewPortfolioMenuItem.getSelection()) {
			eventsSash().setVisible(true);
			chartsSash().setVisible(false);
			portfolioSash().setVisible(true);
			sashes.setWeights(new int[]{50,0,50});
			
			if ( this.isVisible() ) ((RefreshableView)eventsSash()).refreshView(new ArrayList<Exception>());
			if ( this.isVisible() ) ((RefreshableView)portfolioSash()).refreshView(new ArrayList<Exception>());
		}
		if (!viewEventsMenuItem.getSelection() && viewPortfolioMenuItem.getSelection()) {
			eventsSash().setVisible(false);
			chartsSash().setVisible(true);
			portfolioSash().setVisible(true);
			sashes.setWeights(new int[]{0,50,50});
			
			if ( this.isVisible() ) ((RefreshableView)chartsSash()).refreshView(new ArrayList<Exception>());
			if ( this.isVisible() ) ((RefreshableView)portfolioSash()).refreshView(new ArrayList<Exception>());
		}

	}

	protected Composite portfolioSash() {
		return winTable[2];
	}

	protected Composite chartsSash() {
		return winTable[1];
	}

	protected Composite eventsSash() {
		return winTable[0];
	}

	private void  closeButtonMouseDown(SelectionEvent evt) {
		closeMain();
	}

	private void closeMain() {
		
		try {
			
			portfolioSash().setVisible(false);
			this.getShell().setVisible(false);
			this.winTable[0].dispose();
			this.winTable[1].dispose();
			this.winTable[2].dispose();
			this.getShell().dispose();
			
			Integer hintNumber = new Integer(MainPMScmd.getPrefs().get("email.hint","0"));
			this.emailHint(hintNumber);
			
			SpringContext.getSingleton().close();
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	protected void emailHint(Integer hintNumber) {
		Desktop desktop;
		if (Desktop.isDesktopSupported() && hintNumber == 0) {
			
			desktop = Desktop.getDesktop();
			String messageHead = "piggymarketsqueak@googlemail.com?SUBJECT=You have just been running Premium Markets UI from http://premiummarkets.elasticbeanstalk.com";
			String messageBody = "";
			String release = "";

			try {
				Properties pbuild = new Properties();
				pbuild.load(ClassLoader.getSystemClassLoader().getResourceAsStream("pmsbuild.properties"));
				release = pbuild.getProperty("application.buildtime");
			} catch (IOException e1) {
				LOGGER.debug("No release",e1);
			}


			messageBody +=
					"&BODY=" +
							"Premium Markets is an open source and a non profitable development work.\n" +
							"It is also a work in progress that you and others can benefit from for free.\n"+
							"Hence any suggestions or questions are welcome.\n" +
							"Thank you for your time using Premium Markets.\n" +
							"See the new Premium Markets FORECAST web portal at http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo of the Forecast engine.\n\n\n"+
							"Regards,\nGuillaume.\n\n\n\n" +
							"Release reference : "+release+" ... "+hintNumber ; 

			try {
				URI uriMailTo = new URI("mailto",messageHead+messageBody, null);
				desktop.mail(uriMailTo);
			} catch (URISyntaxException e) {
				LOGGER.error("Can't send email : "+e.getMessage(),e);
			} catch (IOException e) {
				LOGGER.error("Can't send email : "+e.getMessage(),e);
			}


			MainPMScmd.getPrefs().put("email.hint",(++hintNumber).toString());

		}   else {
			LOGGER.info("Desktop mail support is "+Desktop.isDesktopSupported()+" for the "+hintNumber+" time.");
		}    
	}
	
	private void rootShellClosedRequested(ShellEvent evt) {
		setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
		closeMain();
	}

	@Override
	public void setCursor(Cursor cursor) {
		
		synchronized (mainMenu) {
			
			if (cursor != null && (cursor.equals(CursorFactory.getCursor(SWT.CURSOR_WAIT)) || cursor.equals(CursorFactory.getCursor(SWT.CURSOR_APPSTARTING)))) {
				cursorCpt++;
			} else {
				cursorCpt --;
				if (cursorCpt > 0) {
					return;
				} 
			}

			for (Control control : winTable) {
				if (control != null && !control.isDisposed()) {
					control.setCursor(cursor);
				}
			}
		}

	}


	@Override
	public void endRefreshAction(List<Exception> exceptions) {
		try {
			logComposite.endJob(exceptions);
		} finally {
			this.setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}
	}

	public void initRefreshAction() {
		
		setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
		logComposite.initRefresh(this);	
		
	}

	@Override
	public void refreshView(List<Exception> exceptions) {
		
		for (Composite composite : winTable) {
			if (composite instanceof RefreshableView) {
				if (composite.isVisible()) {
					((RefreshableView) composite).refreshView(exceptions);
				}
			}
		}
		if (isVisible() && !exceptions.isEmpty()) {
			
			for (final Exception exception : exceptions) {
				
				if (exception instanceof InvalidEventRefreshTask) {
					
					ActionDialogAction action = new ActionDialogAction() {
						@Override
						public void action(Control targetControl) {
							EventTaskQueue.getSingleton().invalidateTasksCreationDates(((InvalidEventRefreshTask) exception).getTaskId());
						}
					};
					ActionDialog dialog = new ActionDialog(getShell(), 
							"Force request", exception +" has already been fulfilled sometime today.", 
							"It should not need updating but you still can force and run it again by first pressing the button bellow then running your request again.",
							"Reset previous request", action);
					exceptions.clear();
					dialog.open();
					break;
				}
			}
			
			if (!exceptions.isEmpty()) {
				UserDialog dialog = new UserDialog(getShell(), 
						"A slight problem occurred while running your last request. "+
								((allStocksEventModel.getViewParamRoot() != null)? allStocksEventModel.getViewParamRoot().iterator().next():"")+"\n", exceptions.toString());
				dialog.open();
			}
		}
		
		if (isVisible()) {
			Shell[] childrenShells = this.getShell().getShells();
			for (Shell child : childrenShells) {
				if (child.getText().contains("Warning")) child.forceActive();
			}
		}
		
	}

	public Date getAnalysisStartDate() {
		return ((RefreshableView)chartsSash()).getAnalysisStartDate();
	}

	@Override
	public Date getAnalysisEndDate() {
		return ((RefreshableView)chartsSash()).getAnalysisEndDate();
	}
	
}
