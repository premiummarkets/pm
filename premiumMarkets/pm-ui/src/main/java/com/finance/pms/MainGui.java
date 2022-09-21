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
package com.finance.pms;


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
import com.finance.pms.datasources.web.MarketListProvider;
import com.finance.pms.datasources.web.ProvidersInflation;
import com.finance.pms.datasources.web.ProvidersList;
import com.finance.pms.events.AnalysisClient;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.calculation.DateFactory;
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

	private String siteUrl;

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

	protected MenuItem quotationMenuItem;
	private MenuItem refreshAllPortfoliosQuotationsMenuItem;
	private MenuItem refreshMonitoredQuotationsMenuItem;

	private MenuItem eventsMenuItem;
	protected Menu eventsSubMenu;
	public static MenuItem viewEventsMenuItem;
	private OperationBuilderDialog builderDialog;

	private  MenuItem portfolioMenuItem;
	public   Menu portfolioSubMenu;
	private MenuItem viewPortfolioMenuItem;

	public Menu chartSubMenu;

	private MenuItem fileMenuItem;
	protected Menu fileSubMenu;
	private MenuItem exitMenuItem;

	private String dbfile;

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

	public static String APP_NAME;

	public MainGui(Composite parent, String dbFilePath) {

		super(parent, SWT.VERTICAL);
		this.setLayout(new FillLayout()); 

		dbfile = dbFilePath;
		springContextInit(dbfile);

		setupAppName();
		setupAppDefaultFont(getDisplay(), getShell());
		setupAppDefaultColors(getDisplay());

		ShareListMgr shareListMgr = (ShareListMgr) SpringContext.getSingleton().getBean("shareListMgr");
		ConfigThreadLocal.set(EventSignalConfig.EVENT_SIGNAL_NAME, shareListMgr.initPkgDependentConfig());
		ConfigThreadLocal.set("indicatorParams", new IndicatorsConfig());

		siteUrl = MainPMScmd.getMyPrefs().get("site.url", "none.com");

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

					String jnlpUrl = setupJnlpUrl();
					URL sourceforge = new URL(jnlpUrl);
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
								Runnable obsDialog = new Runnable() {
									public void run() {
										ActionDialog dialog = new ActionDialog(getShell(), "A new version is available!",
												"-------------  A new version is available -------------", null, "Click to update. Close this popup otherwise.",
												new ActionDialogAction() {

											@Override
											public void action() {
												//Program.launch("http://"+siteUrl+"/html/PremiumMarkets.jnlp");
												Program.launch("http://" + siteUrl + "/html/swtui.html#Download");
												rootShellClosedRequested(null);
											}
										});
										dialog.open();
									}
								};
								getDisplay().asyncExec(obsDialog);
							}
							break;
						} 
					}

				} catch (Exception e) {
					LOGGER.warn(e,e);
				}

			}
		};

		Thread thread = new Thread(runnable);
		thread.start();

		initContent();
		this.layout();

	}

	protected String setupJnlpUrl() {
		return "http://sourceforge.net/projects/pmsqueak/files/PremiumMarkets.jnlp";
	}

	protected void setupAppName() {
		APP_NAME="Premium Markets";
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
			getShell().setText(MainGui.APP_NAME);
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
				menuFile();
				menuStocksAndMarkets();
				menuPortfolio();
				menuQuotations();
				menuCharts();
				menuEvents();

				{
					settingsSubMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
					settingsSubMenuItem.setText("Settings");
					try {
						FileInputStream toolImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/tool.png"));
						settingsSubMenuItem.setImage(new Image(getDisplay(),toolImg));
					} catch (Exception e) {
						LOGGER.warn(e);
					}
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

	protected void menuEvents() throws FileNotFoundException {
		menuEventsInit();
		new MenuItem(eventsSubMenu, SWT.SEPARATOR);
		menuEventsCreateCalculators();
		menuEventsClearEvents();
		menuEventsForecast();
	}

	protected void menuEventsInit() {
		eventsMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
		eventsMenuItem.setText("Events");
		try {
			FileInputStream bellImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/bell.png"));
			eventsMenuItem.setImage(new Image(getDisplay(), bellImg));
		} catch (Exception e) {
			LOGGER.warn(e);
		}
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
	}

	protected void menuEventsForecast() {
		{
			MenuItem eventForecast = new MenuItem(eventsSubMenu, SWT.CASCADE);
			eventForecast.setText("Run a forecast based on technical analysis");
			eventForecast.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent evt) {
					ActionDialog actionDialog = new ActionDialog(getShell(), "Info",
							"Running a neural network forecast on technical analysis is not available in this version.\n"+
									"This feature is part of the advanced version including "+MainGui.APP_NAME+" Forecast engine.\n", 
									null,
									"Click here for more information and a workable demo",
									new ActionDialogAction() {

						@Override
						public void action() {
							Program.launch("http://"+siteUrl);

						}
					});
					actionDialog.open();
				}
			});
		}
	}

	protected void menuEventsClearEvents() {
		new MenuItem(eventsSubMenu, SWT.SEPARATOR);
		{
			MenuItem eventClean = new MenuItem(eventsSubMenu, SWT.CASCADE);
			eventClean.setText("Clear All previous calculations");
			eventClean.addSelectionListener(clearPreviousCalculationsControler());
		}
	}

	protected void menuEventsCreateCalculators() {
		{
			MenuItem eventClean = new MenuItem(eventsSubMenu, SWT.CASCADE);
			eventClean.setText("Customise and Create indicators ...");
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
	}

	protected void menuCharts() throws FileNotFoundException {
		{
			MenuItem chartMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
			chartMenuItem.setText("Charts");
			try {
				FileInputStream bellImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/chart.png"));
				chartMenuItem.setImage(new Image(getDisplay(), bellImg));
			} catch (Exception e) {
				LOGGER.warn(e);
			}	
			{
				chartSubMenu = new Menu(chartMenuItem);
				chartMenuItem.setMenu(chartSubMenu);
			}
		}
	}

	protected void menuQuotations() throws FileNotFoundException {
		Menu quotationMenu = menuQuotationsInit();
		menuQuotationsUpdate(quotationMenu);
		menuQuotationsUpdateMonitored(quotationMenu);
		menuQuotationsUpdateInflation(quotationMenu);
		quotationMenuItem.setMenu(quotationMenu);
	}

	protected Menu menuQuotationsInit() {
		quotationMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
		quotationMenuItem.setText("Quotations");
		try {
			FileInputStream portImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/port.png"));
			quotationMenuItem.setImage(new Image(getDisplay(), portImg));
		} catch (Exception e) {
			LOGGER.warn(e);
		}
		Menu quotationMenu = new Menu(quotationMenuItem);
		quotationMenuItem.setMenu(quotationMenu);
		return quotationMenu;
	}

	protected void menuQuotationsUpdateInflation(Menu quotationMenu) {
		{
			final MenuItem refreshInflation = new MenuItem(quotationMenu, SWT.CASCADE);
			refreshInflation.setText("Update Inflation data download (Based on The Consumer Price Index (CPI-U) compiled by the Bureau of Labor Statistics)");
			refreshInflation.addSelectionListener(new EventRefreshController(fourToutStrategyEventModel, this, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {

				@Override
				public void widgetSelected(SelectionEvent evt) {										
					LOGGER.guiInfo("I am refreshing. Thanks for waiting ...");

					fourToutStrategyEventModel.setLastQuotationFetch(DateFactory.DEFAULT_DATE);
					Stock inflationStock = ProvidersInflation.inflationStock();
					fourToutStrategyEventModel.setViewParamRoot(Arrays.asList(new Stock[]{inflationStock}));

					this.updateEventRefreshModelState(0l, TaskId.FetchQuotations);
					super.widgetSelected(evt);

				}
			});
		}
	}

	protected void menuQuotationsUpdateMonitored(Menu quotationMenu) {
		{
			refreshMonitoredQuotationsMenuItem = new MenuItem(quotationMenu, SWT.CASCADE);
			refreshMonitoredQuotationsMenuItem.setText("Update quotations download for your monitored shares");
			refreshMonitoredQuotationsMenuItem.addSelectionListener(new EventRefreshController(monitoredStocksEventModel, this, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
				@Override
				public void widgetSelected(SelectionEvent evt) {
					LOGGER.guiInfo("I am refreshing. Thanks for waiting ...");
					monitoredStocksEventModel.setLastQuotationFetch(DateFactory.DEFAULT_DATE);
					this.updateEventRefreshModelState(0l, TaskId.FetchQuotations);
					super.widgetSelected(evt);
				}
			});
		}
	}

	protected void menuQuotationsUpdate(Menu quotationMenu) {
		{
			refreshAllPortfoliosQuotationsMenuItem = new MenuItem(quotationMenu, SWT.CASCADE);
			refreshAllPortfoliosQuotationsMenuItem.setText("Update quotations download for all your portfolios"); 
			refreshAllPortfoliosQuotationsMenuItem.addSelectionListener(new EventRefreshController(portfolioStocksEventModel, this, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
				@Override
				public void widgetSelected(SelectionEvent evt) {
					LOGGER.guiInfo("I am refreshing. Thanks for waiting ...");
					portfolioStocksEventModel.setViewParamRoot(null); //reset (should mean all portfolio stocks)
					portfolioStocksEventModel.setLastQuotationFetch(DateFactory.DEFAULT_DATE);
					this.updateEventRefreshModelState(0l, TaskId.FetchQuotations);
					super.widgetSelected(evt);
				}
			});
		}
	}

	protected void menuPortfolio() throws FileNotFoundException {
		menuPortfolioInit();
		new MenuItem(portfolioSubMenu, SWT.SEPARATOR);
		menuPortfolioGnucashImport();
		menuPortfolioAdvancedPortfolio();
		menuPortfolioExtractTransactions();
		menuPortfolioTransposeTransactions();
		new MenuItem(portfolioSubMenu, SWT.SEPARATOR);
	}

	protected void menuPortfolioInit() {

		portfolioMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
		portfolioMenuItem.setText("Portfolios");
		try {
			FileInputStream portImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/port.png"));
			portfolioMenuItem.setImage(new Image(getDisplay(),portImg));
		} catch (Exception e) {
			LOGGER.warn(e);
		}
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

	}

	protected void menuPortfolioTransposeTransactions() {
		{
			MenuItem loadPortofolioFromGnuCash = new MenuItem(portfolioSubMenu,SWT.CASCADE);
			loadPortofolioFromGnuCash.setText("Extract and transpose Portfolio transactions ...");
			loadPortofolioFromGnuCash.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent evt) {
					try {
						Runnable runnable2 = new Runnable() {
							public void run() {
								setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
							}
						};
						getDisplay().syncExec(runnable2);
						((PortfolioComposite) portfolioSash()).transposePortfolioTransactions();
					} finally {
						Runnable runnable2 = new Runnable() {
							public void run() {
								setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
							}
						};
						getDisplay().syncExec(runnable2);
					}
				}
			});
		}
	}

	protected void menuPortfolioExtractTransactions() {
		{
			MenuItem loadPortofolioFromGnuCash = new MenuItem(portfolioSubMenu,SWT.CASCADE);
			loadPortofolioFromGnuCash.setText("Extract Portfolio transactions ...");
			loadPortofolioFromGnuCash.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent evt) {
					try {
						Runnable runnable2 = new Runnable() {
							public void run() {
								setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
							}
						};
						getDisplay().syncExec(runnable2);
						((PortfolioComposite) portfolioSash()).viewPortfolioTransactions();
					} finally {
						Runnable runnable2 = new Runnable() {
							public void run() {
								setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
							}
						};
						getDisplay().syncExec(runnable2);
					}
				}
			});
		}
	}

	protected void menuPortfolioAdvancedPortfolio() {
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
	}

	protected void menuPortfolioGnucashImport() {
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
	}

	protected void menuStocksAndMarkets() throws FileNotFoundException {
		Menu stockSubMenu = menuStockAndMarkets();
		menuStocksAndMarketsItemRefreshLists(stockSubMenu);
		menuStocksAndMarketsRecommendations(stockSubMenu);
	}

	protected Menu menuStockAndMarkets() {
		stockMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
		stockMenuItem.setText("Stocks and Markets");
		try {
			FileInputStream portImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/port.png"));
			stockMenuItem.setImage(new Image(getDisplay(), portImg));
		} catch (Exception e) {
			LOGGER.warn(e);
		}
		Menu stockSubMenu = new Menu(stockMenuItem);
		stockMenuItem.setMenu(stockSubMenu);
		return stockSubMenu;
	}

	protected void menuStocksAndMarketsRecommendations(Menu stockSubMenu) {
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
											"This feature is part of the advanced version including "+MainGui.APP_NAME+" Forecast engine.\n",
											null,
											"Click here for more information and a workable demo",
											new ActionDialogAction() {

								@Override
								public void action() {
									Program.launch("http://"+siteUrl);
								}
							});
							actionDialog.open();
						}
					});
				}
			});
			refreshRecommendationsMenuItem.setEnabled(true);
		}
	}

	protected void menuStocksAndMarketsItemRefreshLists(Menu stockSubMenu) {
		{

			refreshStockListMenuItem = new MenuItem(stockSubMenu, SWT.CASCADE);
			refreshStockListMenuItem.setText("Add or Update stocks ...");
			refreshStockListMenuItem.addSelectionListener(new EventRefreshController(allStocksEventModel, this, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {

				private void superwidgetSelected() {
					super.widgetSelected(null);
				}

				@Override
				public void widgetSelected(SelectionEvent evt) {

					final ShareListUpdateDialog slDialog = new ShareListUpdateDialog(getShell(), logComposite);
					this.view = slDialog;

					ActionDialogAction actionDialogAction = new ActionDialogAction() {

						@Override
						public void action() {

							MarketListProvider provider = slDialog.getProvider();
							if (provider != null) {

								allStocksEventModel.setViewParamRoot(Arrays.asList(new ShareListInfo[]{new ShareListInfo(ProvidersList.providerIndicedShareListName(provider))}));
								LOGGER.guiInfo("I am refreshing. Thanks for waiting ...");
								allStocksEventModel.setLastListFetch(DateFactory.DEFAULT_DATE);
								updateEventRefreshModelState(0l, TaskId.FetchLists);
								superwidgetSelected();

							}
						}
					};
					ActionDialogAction refreshAction = new ActionDialogAction() {

						@Override
						public void action() {
							Runnable runnable = new Runnable() {
								public void run() {
									((PortfolioComposite) MainGui.this.portfolioSash()).refreshView(new ArrayList<Exception>());
								}
							};
							getDisplay().asyncExec(runnable);
						}

					};

					slDialog.setAction(actionDialogAction);
					slDialog.setRefreshAction(refreshAction);
					slDialog.open();

				}
			});
		}
	}

	protected void menuFile() throws FileNotFoundException {
		menuFileInit();
		menuFileItemMainSite();
		menuFileItemDoc();
		new MenuItem(fileSubMenu, SWT.SEPARATOR);
		menuFileItemAbout();
		new MenuItem(fileSubMenu, SWT.SEPARATOR);
		menuFileItemExit();
	}

	protected void menuFileInit() {
		fileMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
		fileMenuItem.setText(MainGui.APP_NAME);
		try {
			FileInputStream exitImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/exit.png"));
			Image exitImage = new Image(getDisplay(), exitImg);
			fileMenuItem.setImage(new Image(getDisplay(), exitImage.getImageData()));
		} catch (Exception e) {
			LOGGER.warn(e);
		}
		fileSubMenu = new Menu(fileMenuItem);
		fileMenuItem.setMenu(fileSubMenu);
	}

	protected void menuFileItemExit() {
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

	protected void menuFileItemAbout() {
		{
			MenuItem about = new MenuItem(fileSubMenu, SWT.CASCADE);
			about.setText("About");
			about.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent evt) {
					try {
						Properties pbuild = new Properties();
						pbuild.load(ClassLoader.getSystemClassLoader().getResourceAsStream("pmsbuild.properties"));
						UserDialog dialog = new UserDialog(getShell(),MainGui.APP_NAME+" is an automated stock market analysis system.\n"+
								"It implements a graphical environment for monitoring stock market technical analysis major indicators, portfolio management and historical data charting.\n\n"+
								"See the new "+MainGui.APP_NAME+" FORECAST web portal at http://"+siteUrl+" for documentation and a free workable demo of the Forecast engine.\n\n\n\n"+
								"\n"+MainGui.APP_NAME+"\nCopyright (C) 2008-2014 Guillaume Thoreton, see <http://www.gnu.org/licenses/>\nBuild : "+pbuild.getProperty("application.buildtime")+"\n", null);
						dialog.open();				

					} catch (IOException e) {
						LOGGER.debug(e);
					}
				}
			});
		}
	}

	protected void menuFileItemDoc() {
		{
			MenuItem doc = new MenuItem(fileSubMenu, SWT.CASCADE);
			doc.setText("Project documentation");
			doc.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent evt) {
					Program.launch("http://"+siteUrl+"/html/swtui.html");
				}
			});
		}
	}

	protected void menuFileItemMainSite() {
		{
			MenuItem forecast = new MenuItem(fileSubMenu, SWT.CASCADE);
			forecast.setText("The Forecast Engine");
			forecast.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent evt) {
					Program.launch("http://"+siteUrl+"");
				}
			});
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
				super.widgetSelected(evt);

			}
		};

	}

	public static void main(String[] args) {

		Display.setAppName("Premium Markets");

		unCaughErrorHandling();

		crossPlateformLookAndFeel();

		try {

			prefsSetting();

			String dbfile = parseArgs(args);

			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			shell.setLayout(new FillLayout());

			MainGui inst = new MainGui(shell, dbfile);

			inst.setCursor(CursorFactory.getCursor(SWT.CURSOR_APPSTARTING));

			shell.layout();
			shell.pack();
			shell.open();

			sashesSizes(shell, inst);

			shell.layout();
			shell.pack();   

			inst.setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));

			postInstanceInit(inst);

			//Event loop
			System.out.println("IHM RUNNING"); //Keep this as this is used by the installer
			while (!shell.isDisposed()) {
				try {
					if (!display.readAndDispatch()) display.sleep();
				} catch (java.lang.IllegalArgumentException | IllegalStateException e1) {
					//LOGGER.warn("Error in Main Gui : "+ e1.getMessage(), e1);
				} catch (Throwable e) {
					try {
						LOGGER.error("Error in Main Gui : "+ e.getMessage(), e);
						inst.setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
						UserDialog dialog = new UserDialog(inst.getShell(),"An Error occurred.", e.getMessage());
						dialog.open();
					} catch (Throwable e1) {
						e1.printStackTrace();
					}
				}
			}

		} catch (IllegalArgumentException | IllegalStateException e1) {
			//LOGGER.warn("Error in Main Gui : "+ e1.getMessage(), e1);
		} catch (Throwable e) {

			System.out.println("Unhandled error running the ui : " + e);
			e.printStackTrace();
			genericSuddenDeathMsg(e);

		} finally {

			Runtime.getRuntime().exit(1);

		}

	}

	protected static void prefsSetting() {
		MainPMScmd.getMyPrefs().put("mail.infoalert.activated", MainPMScmd.getMyPrefs().get("quotes.sendeventfromui", "false"));
	}

	protected static void postInstanceInit(final MainGui inst) {

		//Post inits
		//First time update
		Integer hintNumber = Integer.valueOf(MainPMScmd.getMyPrefs().get("email.hint","0"));
		if (hintNumber == 0) {
			LOGGER.info("First run : updating quotes");
			QuotationUpdate quotationUpdate = new QuotationUpdate();
			quotationUpdate.getQuotesForAllMonitoredUserPortfolios();
		} else {
			LOGGER.info("This is not the First run : no quotes update");
		}

		Runnable runnable = new Runnable() {
			public void run() {
				//load portfolios
				try {

					((PortfolioComposite) inst.portfolioSash()).updateMoniAndPSCachedModels();
					((PortfolioComposite) inst.portfolioSash()).tabBuildAllTabs(0, new Observer() {
						@Override
						public void update(Observable o, Object arg) {
							((PortfolioComposite) inst.portfolioSash()).backGroundLoadQuotationCache();
						}
					});

					SpringContext.getSingleton().optionalPostInit();

				} catch (Exception e1) {
					LOGGER.error(e1, e1);
				}
			}
		};
		inst.getDisplay().asyncExec(runnable);

	}

	protected static void sashesSizes(Shell shell, final MainGui inst) {
		//Shell
		{
			Point swtPrefSize = inst.computeSize(SWT.DEFAULT, SWT.DEFAULT); 
			Point myPrefSize = new Point(1024,768);
			Point maxSize = new Point(shell.getDisplay().getBounds().width*9/10, shell.getDisplay().getBounds().height*9/10);
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

		//Chart and Chart Buttons
		{
			((ChartsComposite)inst.winTable[1]).myPack();
		}
	}

	protected static String parseArgs(String[] args) {
		switch (args.length) {
		case 1 :
			return args[0];
		default :
			LOGGER.info("Usage : MainGui <path>/db.properties");
			throw new IllegalArgumentException();
		}
	}

	protected SpringContext springContextInit(String dbfile) {
		//Spring Context init		
		SpringContext ctx = new SpringContext(dbfile);
		ctx.setMasSource(dbfile,"false");
		ctx.loadBeans(new String[] {"/connexions.xml", "/swtclients.xml","/talibanalysisservices.xml"});
		try {
			ctx.refresh();
		} catch (BeanCreationException e1) {
			e1.printStackTrace();
			Frame frame = new JFrame();
			String report = 
					"It may be that "+MainGui.APP_NAME+" is already running on this computer. You can run only one instance at a time.\n" +
							"Make sure to close all other instances before starting a new one.\n" +
							"If the process is running in the back ground and is not showing in a window, you will have to kill it. \n" +
							"Use the tools and commands provided by your OS to do so.\n" +
							"In case you don't know these, I am sorry to say that you will have to either ask someone that does or reboot.";
			CustomDialog customDialog = new CustomDialog(frame, report, e1.getMostSpecificCause().toString(), "Error Report", false);
			customDialog.pack();
			customDialog.setVisible(true);
			customDialog.dispose();
			frame.dispose();
			throw new IllegalStateException();
		}
		return ctx;
	}

	protected static void crossPlateformLookAndFeel() {
		try {
			// Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
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
	}

	protected static void unCaughErrorHandling() {
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(" _______________________Uncaught_______________________ "+e.toString());
				e.printStackTrace();
			}
		});
	}

	private static void genericSuddenDeathMsg(Throwable e) {
		Frame frame = new JFrame();
		String report = 
				"An unexpected error occurred while running the application.\n" +
						"Make sure to close all other instances before starting this one.\n" +
						"Double check that the installation has run successfully.\n" +
						"Check your OS file system attributes and anti viruses as these may prevent the application to fully install.\n" +
						"If you need to reinstall, it is recommended to install in a new folder fresh and free of any older version.\n" +
						"Type "+MainGui.APP_NAME+" FORECAST in your favourite search engine or use http://sourceforge.net/projects/pmsqueak/ for support.";
		CustomDialog customDialog = new CustomDialog(frame, report, e.getMessage(), "Error Report", false);
		customDialog.pack();
		customDialog.setVisible(true);
		customDialog.dispose();
		frame.dispose();
	}

	protected void setupAppDefaultColors(Display display) {

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

	protected void setupAppDefaultFont(Display display, Shell shell) {

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

			Integer hintNumber = Integer.valueOf(MainPMScmd.getMyPrefs().get("email.hint","0"));
			this.emailHint(hintNumber);

			EventTaskQueue.getSingleton().close();
			SpringContext.getSingleton().close();

		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	protected void emailHint(Integer hintNumber) {

		if (hintNumber == 0) {

			LOGGER.info("Mail hint triggered. App termination number " + hintNumber + ".");

			String email = setupContactEmail();
			String messageHead = email + "?SUBJECT=You have just been running " + MainGui.APP_NAME + " UI from http://" + siteUrl;
			String messageBody = "";
			String release = "";

			try {
				Properties pbuild = new Properties();
				pbuild.load(ClassLoader.getSystemClassLoader().getResourceAsStream("pmsbuild.properties"));
				release = pbuild.getProperty("application.buildtime");
			} catch (IOException e1) {
				LOGGER.warn("No release",e1);
			}

			messageBody +=
					"&BODY=" +
							"Dear sir or Madam,\n\n" +
							MainGui.APP_NAME + " is an open source and non profitable development work.\n" +
							"It is a Work In Progress that you and others can benefit from for free.\n" +
							"Hence any suggestions or questions are welcome.\n\n" +
							"Thank you for your time using " + MainGui.APP_NAME + ".\n" +
							"More Info and Automated Markets Trends Forecast engine at http://" + siteUrl + ".\n\n\n" +
							"Kind Regards,\nGheeyom.\n" +
							"The " + release + "." + hintNumber ; 

			try {
				URI uriMailTo = new URI("mailto", messageHead + messageBody, null);
				Program.launch(uriMailTo.toString());
			} catch (URISyntaxException e) {
				LOGGER.error("Can't send email : "+e.getMessage(),e);
			} 
			catch (Exception e) {
				LOGGER.error("Can't send email : "+e.getMessage(),e);
			}

			MainPMScmd.getMyPrefs().put("email.hint",(++hintNumber).toString());
			MainPMScmd.getMyPrefs().flushy();

		} else {
			LOGGER.info("Mail hint already triggered. App termination number " + hintNumber + ".");
		}
	}

	protected String setupContactEmail() {
		return MainPMScmd.getMyPrefs().get("mail.to", "admin@premiummarkets.uk");
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

	@Override
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
						public void action() {
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
