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
package com.finance.pms;


import java.awt.Desktop;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.springframework.beans.factory.BeanCreationException;

import com.finance.pms.admin.config.DbSettings;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.config.IndicatorsConfig;
import com.finance.pms.admin.config.MarketsSettings;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.EventModel;
import com.finance.pms.datasources.EventRefreshController;
import com.finance.pms.datasources.RefreshAllEventStrategyEngine;
import com.finance.pms.datasources.RefreshFourToutStrategyEngine;
import com.finance.pms.datasources.RefreshMonitoredStrategyEngine;
import com.finance.pms.datasources.RefreshPortfolioStrategyEngine;
import com.finance.pms.datasources.ShareListInfo;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.datasources.web.ProvidersInflation;
import com.finance.pms.events.gui.EventsComposite;
import com.finance.pms.portfolio.gui.PortfolioComposite;
import com.finance.pms.portfolio.gui.charts.ChartsComposite;
import com.finance.pms.sharelists.gui.ShareListUpdateDialog;
import com.finance.pms.threads.ConfigThreadLocal;

/**
 * The Class MainGui.
 * 
 * @author Guillaume Thoreton
 */
public class MainGui extends SashForm implements RefreshableView { 

	protected static MyLogger LOGGER = MyLogger.getLogger(MainGui.class);

	private Menu mainMenu;

	private MenuItem settingsMenuItem;
	private Menu settingsSubMenu;
	private MenuItem settingsSubMenuItem;
	private DbSettings dbSettings;

	private MenuItem stockMenuItem;
	private MenuItem refreshStockListMenuItem;
	private MenuItem refreshRecommendationsMenuItem;
	private MarketsSettings marketsSettings;

	private MenuItem quotationMenuItem;
	private MenuItem refreshAllPortfoliosQuotationsMenuItem;
	private MenuItem refreshMonitoredQuotationsMenuItem;

	private MenuItem eventsMenuItem;
	private Menu eventsSubMenu;
	private MenuItem viewEventsMenuItem;
	
	private  MenuItem portfolioMenuItem;
	public static  Menu portfolioSubMenu;
	private MenuItem viewPortfolioMenuItem;
	
	private MenuItem fileMenuItem;
	private Menu fileSubMenu;
	private MenuItem exitMenuItem;
	
	
	private static String dbfile;
	
	public static Font DEFAULTFONT;
	public static Font CONTENTFONT;


	private SashForm sashes;
	private  Composite[] winTable = new Composite[3];
	private LogComposite logComposite;
	

	private EventModel<RefreshAllEventStrategyEngine> allStocksEventModel;
	private EventModel<RefreshMonitoredStrategyEngine> monitoredStocksEventModel;
	private EventModel<RefreshPortfolioStrategyEngine> portfolioStocksEventModel;
	private EventModel<RefreshFourToutStrategyEngine> fourToutStrategyEventModel;
	

	public MainGui(Composite parent, String dbFilePath) {
		
		super(parent, SWT.VERTICAL);

		this.setLayout(new FillLayout()); 
		MainGui.dbfile = dbFilePath;
		
		sashes = new SashForm(this, SWT.HORIZONTAL);
		logComposite = new LogComposite(this, SWT.NONE);
				
		allStocksEventModel = EventModel.getInstance(new RefreshAllEventStrategyEngine(), logComposite);
		monitoredStocksEventModel = EventModel.getInstance(new RefreshMonitoredStrategyEngine(), logComposite);
		portfolioStocksEventModel = EventModel.getInstance(new RefreshPortfolioStrategyEngine(), logComposite);
		fourToutStrategyEventModel = EventModel.getInstance(new RefreshFourToutStrategyEngine(), logComposite);
		
		initMenus();
		
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
								ActionDialog dialog = new ActionDialog(getShell(), SWT.NONE, "A new version is available!", "-------------  A new version is available -------------", null, "Clik to update. Close this popup otherwise.", new ActionDialogAction() {
									
									@Override
									public void action(Button targetButton) {
										Program.launch("http://premiummarkets.elasticbeanstalk.com/html/PremiumMarkets.jnlp");
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
	
	/**
	 * Inits the gui.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void initMenus() {
		try {
			FileInputStream iconImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/icon.img"));
			getShell().setImage(new Image(getDisplay(),iconImg));
			getShell().setText("Premium Markets");
			getShell().setFont(MainGui.DEFAULTFONT);
			this.setBackground(new Color(getDisplay(), 239, 203, 152));
			final MainGui mainwin = this;

			getShell().addShellListener(new ShellAdapter() {
				@Override
				public void shellClosed(ShellEvent evt) {
					mainwin.rootShellClosedRequested(evt);
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
						forecast.setText("The Forescast Engine (NEW)");
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
									ErrorDialog dialog = new ErrorDialog(getShell(),SWT.NULL,
"Premium Markets is an automated stock market analysis system.\n"+
"It implements a graphical environment for monitoring stock market technical analysis major indicators, portfolio management and historical data charting.\n\n"+
"See the new Premium Markets FORECAST web portal at http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo of the Forecast engine.\n\n\n\n"+
"\nPremium Markets\nCopyright (C) 2008-2012 Guillaume Thoreton, see <http://www.gnu.org/licenses/>\nBuild : "+pbuild.getProperty("application.buildtime")+"\n", null);
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
							loadPortofolioFromGnuCash.setText("Load Portfolio from gnucash advanced export ...");
							loadPortofolioFromGnuCash.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									
									getDisplay().asyncExec(new Runnable() {
										
										public void run() {
											try {
												setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
												((PortfolioComposite)winTable[2]).loadGnucashAdvPortfolio();

											} finally {
												setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
											}
										}
									});
								}
							});	
						}
						{
							MenuItem loadPortofolioFromGnuCash = new MenuItem(portfolioSubMenu,SWT.CASCADE);
							loadPortofolioFromGnuCash.setText("Create AutoPortfolio from gnucash advanced export ...");
							loadPortofolioFromGnuCash.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									((PortfolioComposite)winTable[2]).loadGnucashAdvPortfolioForAutoPortfolio();
								}
							});
							loadPortofolioFromGnuCash.setEnabled(false);
						}
						new MenuItem(portfolioSubMenu, SWT.SEPARATOR);
					}
				}
				{
					eventsMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
					eventsMenuItem.setText("Events");
					FileInputStream bellImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/bell.png"));
					eventsMenuItem.setImage(new Image(getDisplay(),bellImg));
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
						{
							MenuItem eventForecast = new MenuItem(eventsSubMenu, SWT.CASCADE);
							eventForecast.setText("Run a forecast based on technical analysis");
							eventForecast.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									ActionDialog actionDialog = new ActionDialog(getShell(), SWT.NULL,  "Info",
											"Run a neural network forecast on technical analysis is not available in this version\n"+
											"This feature is part of the advance Permium Markets Forecast engine not included under this license.\n", 
											null,
											"Click here for more information and a workable demo.",
											new ActionDialogAction() {

										@Override
										public void action(Button targetButton) {
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
					quotationMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
					quotationMenuItem.setText("Quotations");
					FileInputStream portImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/port.png"));
					quotationMenuItem.setImage(new Image(getDisplay(),portImg));
					{
						Menu quotationMenu = new Menu(quotationMenuItem);
						{
							refreshAllPortfoliosQuotationsMenuItem = new MenuItem(quotationMenu, SWT.CASCADE);
							refreshAllPortfoliosQuotationsMenuItem.setText("Refresh quotations for all portfolios"); 
							refreshAllPortfoliosQuotationsMenuItem.addSelectionListener(new EventRefreshController(portfolioStocksEventModel, this) {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									LOGGER.guiInfo("I am refreshing. Thanks for waiting...");
									eventModel.setLastQuotationFetch(EventModel.DEFAULT_DATE);
									this.updateEventRefreshModelState(false,true,false,false, 0l);
									initRefreshAction();
									super.widgetSelected(evt);
								}
							});
						}
						{
							refreshMonitoredQuotationsMenuItem = new MenuItem(quotationMenu, SWT.CASCADE);
							refreshMonitoredQuotationsMenuItem.setText("Refresh quotations for monitored shares");
							refreshMonitoredQuotationsMenuItem.addSelectionListener(new EventRefreshController(monitoredStocksEventModel, this) {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									LOGGER.guiInfo("I am refreshing. Thanks for waiting...");
									eventModel.setLastQuotationFetch(EventModel.DEFAULT_DATE);
									this.updateEventRefreshModelState(false,true,false,false,0l);
									initRefreshAction();
									super.widgetSelected(evt);
								}
							});
						}
						{
							final MenuItem refreshInflation = new MenuItem(quotationMenu, SWT.CASCADE);
							refreshInflation.setText("Refresh Inflation data");
							refreshInflation.addSelectionListener(new EventRefreshController(fourToutStrategyEventModel, this) {
								@Override
								public void widgetSelected(SelectionEvent evt) {										
									LOGGER.guiInfo("I am refreshing. Thanks for waiting...");
									
									eventModel.setLastQuotationFetch(EventModel.DEFAULT_DATE);
									Stock inflationStock  = DataSource.getInstance().loadStockBySymbol(ProvidersInflation.SYMBOL);
									eventModel.setViewStateParams(inflationStock);
									
									this.updateEventRefreshModelState(false,true,false,false, 0l);
									initRefreshAction();
									super.widgetSelected(evt);
								
								}
							});
						}
						quotationMenuItem.setMenu(quotationMenu);
					}
				}
				{
					stockMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
					stockMenuItem.setText("Stock lists and Markets");
					FileInputStream portImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/port.png"));
					stockMenuItem.setImage(new Image(getDisplay(),portImg));
					{
						Menu stockSubMenu = new Menu(stockMenuItem);
						{
							refreshStockListMenuItem = new MenuItem(stockSubMenu, SWT.CASCADE);
							refreshStockListMenuItem.setText("Add or Update a Stock list ...");
							refreshStockListMenuItem.addSelectionListener(new EventRefreshController(allStocksEventModel, this) {
								@Override
								public void widgetSelected(SelectionEvent evt) {

									ShareListUpdateDialog shareListUpdate = new ShareListUpdateDialog(getShell(), SWT.NONE);
									shareListUpdate.open();
									Providers provider = shareListUpdate.getProvider();
									if (provider != null && shareListUpdate.getIsOk()) {
										
										allStocksEventModel.setViewStateParams(new ShareListInfo(Providers.providerShareListName(provider)));
										LOGGER.guiInfo("I am refreshing. Thanks for waiting...");
										eventModel.setLastListFetch(EventModel.DEFAULT_DATE);
										this.updateEventRefreshModelState(true, false, false, false, 0l);
										initRefreshAction();
										super.widgetSelected(evt);

									}
								}
							});
						}
						{
							refreshRecommendationsMenuItem = new MenuItem(stockSubMenu, SWT.CASCADE);
							refreshRecommendationsMenuItem.setText("Refresh web recommandations and advice for your stock lists.");
							refreshRecommendationsMenuItem.addSelectionListener(new EventRefreshController(allStocksEventModel, this) {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									
									refreshRecommendationsMenuItem.addSelectionListener(new SelectionAdapter() {
										@Override
										public void widgetSelected(SelectionEvent evt) {
											ActionDialog actionDialog = new ActionDialog(getShell(), SWT.NULL, "Info",
													"Web recommandations and advice feature is not available in this open version\n"+ 
															"This feature is part of the advance Permium Markets Forecast engine not included under this license.\n",
															null,
															"Click here for more information and a workable demo.",
													new ActionDialogAction() {
	
														@Override
														public void action(Button targetButton) {
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

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void main(String[] args) {
		
		try {
			
			MainPMScmd.getPrefs().put("mail.infoalert.activated", MainPMScmd.getPrefs().get("quotes.sendeventfromui", "false"));
			
			switch (args.length) {
				case 1 :
					dbfile = args[0];
					break;
			   default :
			   		LOGGER.info("Usage : MainGui <path>/db.properties");
			   		System.exit(0);
			}
			
			//Spring Context init
			SpringContext ctx = new SpringContext();
			ctx.setDataSource(dbfile);
			ctx.setMasSource(dbfile,"false");
			ctx.loadBeans(new String[] {"/connexions.xml", "/swtclients.xml","talibanalysisservices.xml","masanalysisservices.xml"});
			try {
				ctx.refresh();
			} catch (BeanCreationException e1) {
				Frame frame = new JFrame();
				String report = 
						"Premium Markets is already running on this computer. You can run only one instance at a time.\n" +
						"Make sure to close all other instances before starting a new one.\n" +
						"If the process is running in the back ground and is not showing in a window, you will have to kill it. \n" +
						"Use the tools and commands provided by your OS to do so.\n" +
						"In case you don't know these, I am sorry to say that you will have to either ask someone that does or reboot.";
				CustomDialog customDialog = new CustomDialog(frame, report, "", "Error Report", false);
				customDialog.pack();
				customDialog.setVisible(true);
				customDialog.dispose();
				frame.dispose();
				Runtime.getRuntime().exit(1);
			}
			
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			shell.setLayout(new FillLayout());
			
			setupAppDefaultFont(display, shell);
			
			final MainGui inst = new MainGui(shell,dbfile);
			
			shell.layout();
            shell.pack();
			
            //Shell
            {
				Point prefSize = inst.computeSize(SWT.DEFAULT, SWT.DEFAULT); 
//				int width = Math.max((prefSize.x)/2,500);
//				int height = Math.max((prefSize.y*8)/10,600);
				int width = Math.min(prefSize.x,1022);
				int height = Math.max((prefSize.y*8)/10,700);
				Rectangle shellBounds = shell.computeTrim(0,0,width,height); 
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
					xLog=2;
				}
				inst.setWeights(new int[]{100-xLog,xLog});
            }
			
			//Portfolio Composite
            {
				Rectangle portfolioShashBounds = ((PortfolioComposite)inst.winTable[2]).getClientArea();
				
				Point port1PrefSize = ((PortfolioComposite)inst.winTable[2]).portfolioInfosGroup.computeSize(SWT.DEFAULT, SWT.DEFAULT); 
				Point port1CompositeSize = ((PortfolioComposite)inst.winTable[2]).portfolioInfosGroup.computeSize(port1PrefSize.x, Math.max(50,port1PrefSize.y));
				((PortfolioComposite)inst.winTable[2]).portfolioInfosGroup.setSize(port1CompositeSize);
				Rectangle infoBounds = ((PortfolioComposite)inst.winTable[2]).portfolioInfosGroup.getBounds();
				
				int x1Port = 100*infoBounds.height/portfolioShashBounds.height + 2;
				if ((100 -x1Port) < 20) {
					x1Port=30;
				}
				((PortfolioComposite)inst.winTable[2]).setWeights(new int[]{100-x1Port,x1Port});
            }
			
			//Chart
            {
				Rectangle chartShashBounds = ((ChartsComposite)inst.winTable[1]).getClientArea();
				
				Point chartPrefSize = ((ChartsComposite)inst.winTable[1]).chartBoutonsGroup.computeSize(SWT.DEFAULT, SWT.DEFAULT); 
				Point chartCompositeSize = ((ChartsComposite)inst.winTable[1]).chartBoutonsGroup.computeSize(chartPrefSize.x, Math.max(50,chartPrefSize.y));
				((ChartsComposite)inst.winTable[1]).chartBoutonsGroup.setSize(chartCompositeSize);
				Rectangle chartButtonsBounds = ((ChartsComposite)inst.winTable[1]).chartBoutonsGroup.getBounds();
				int xLog = 100*chartButtonsBounds.height/chartShashBounds.height;
				if ((100 -xLog) < 20) {
					xLog=30;
				}
				((ChartsComposite)inst.winTable[1]).setWeights(new int[]{100-xLog, xLog});
            }
            	
 
			inst.layout();
			shell.open();
			
			LOGGER.debug("Starting MainGUI display thread");
			System.out.println("IHM RUNNING");
			while (!shell.isDisposed()) {
				try {
					LOGGER.trace("Disptaching MainGUI events");
					if (!display.readAndDispatch())
						LOGGER.trace("Sleeping MainGUI display thread");
						display.sleep();
				} catch (Throwable e) {
					LOGGER.error("Error in Main Gui : "+e.getMessage(),e);
					LOGGER.debug("Error in Gui : ",e);
					inst.setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
					ErrorDialog dialog = new ErrorDialog(inst.getShell(),SWT.NULL, "An unhandeled error as occurred.", e.getMessage());
					dialog.open();
				}
			}
			LOGGER.debug("Stoping MainGUI");
	
		} catch (Throwable e) {
			System.out.println("Unhandeled error runing the ui :"+e);
			e.printStackTrace();
		} finally {
			Runtime.getRuntime().exit(1);
		}
		
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
	/**
	 * Inits the data.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void initContent() {
		
		sashes.setToolTipText("Sash : Click on this border and drag to resize");
		sashes.setCursor(CursorFactory.getCursor(SWT.CURSOR_CROSS));
		
		//Thread local init
		ConfigThreadLocal.set("eventSignal", new EventSignalConfig());
		ConfigThreadLocal.set("indicatorParams", new IndicatorsConfig());
		
		EventsComposite eventC = new EventsComposite(sashes, SWT.NONE|SWT.BORDER, logComposite, getAnalysisStartDate());
		this.winTable[0] = eventC;
		eventC.initData();
		this.viewEventsMenuItem.setSelection(false);
		
		ChartsComposite charts = new ChartsComposite(sashes,SWT.NONE|SWT.BORDER, logComposite);
		this.winTable[1] = charts;
		
		PortfolioComposite portfolio = new PortfolioComposite(sashes,charts,SWT.NONE|SWT.BORDER, logComposite);
		this.winTable[2] = portfolio;
		this.viewPortfolioMenuItem.setSelection(true);
		
		this.setMainDisplay();
		
		LOGGER.debug("Finished initializing MainGui Data.");
	}

	
	/**
	 * Open settings.
	 * 
	 * @author Guillaume Thoreton
	 * @throws FileNotFoundException 
	 */
	private void openSettings() throws FileNotFoundException {
		
		if (dbSettings == null || dbSettings.getParent().isDisposed()) {
			dbSettings = new DbSettings(this.getShell(), dbfile);
		} else {
			dbSettings.getParent().forceFocus();
		}
	}
	
	/**
	 * Open settings.
	 * 
	 * @param shell the sh
	 * 
	 * @return the markets settings
	 * 
	 * @author Guillaume Thoreton
	 */
	@Deprecated
	public MarketsSettings openMarkets(Shell shell) {
		
		if (marketsSettings == null || marketsSettings.isDisposed()) {
			
			Display display = getDisplay();
			Shell mSShell = new Shell(display, SWT.RESIZE | SWT.DIALOG_TRIM);
			try {
				FileInputStream iconImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/icon.img"));
				mSShell.setImage(new Image(getDisplay(),iconImg));
			} catch (FileNotFoundException e) {
				LOGGER.error(e,e);
			}
			mSShell.setText("Premium Markets - Select your market place.");
			mSShell.setFont(MainGui.DEFAULTFONT);
			mSShell.setLayout(new FillLayout());
			
			final ScrolledComposite scrollComposite = new ScrolledComposite(mSShell, SWT.V_SCROLL | SWT.BORDER | SWT.RESIZE);
			marketsSettings = new MarketsSettings(scrollComposite);
			scrollComposite.setContent(marketsSettings);
		    scrollComposite.setExpandVertical(true);
		    scrollComposite.setExpandHorizontal(true);
		    scrollComposite.setMinSize(marketsSettings.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		    scrollComposite.addControlListener(new ControlAdapter() {
		      public void controlResized(ControlEvent e) {
		        Rectangle r = scrollComposite.getClientArea();
		        scrollComposite.setMinSize(marketsSettings.computeSize(r.width, SWT.DEFAULT));
		      }
		    });
		    scrollComposite.pack();
		    
		    mSShell.setSize(scrollComposite.computeSize(SWT.DEFAULT, 700));
		    Rectangle mainBounds = shell.getBounds();
		    Rectangle marketShellBounds = mSShell.getBounds();
		    mSShell.setLocation(mainBounds.x+mainBounds.width/4-marketShellBounds.x/2,mainBounds.y+mainBounds.y/4);
			mSShell.open();
			
			while (!mSShell.isDisposed() && !shell.isDisposed()) {
				  if (!display.readAndDispatch()) {
					  display.sleep();
				  }
			}
				  
		} else {
			marketsSettings.forceFocus();
		}
		
		return marketsSettings;
	}
	
	
	/**
	 * Sets the main display.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void setMainDisplay() {
		
		//0 Event, 1 Chart, 2 Portfolio
		
		if (viewEventsMenuItem.getSelection() && !viewPortfolioMenuItem.getSelection()) {
			winTable[0].setVisible(true);
			winTable[1].setVisible(false);
			winTable[2].setVisible(false);
			sashes.setWeights(new int[]{100,0,0});
		}
		
		if (!viewEventsMenuItem.getSelection() && !viewPortfolioMenuItem.getSelection()) {
			for (int j = 0; j < winTable.length; j++) {
				winTable[j].setVisible(false);
			}
		}
		
		if (viewEventsMenuItem.getSelection() && viewPortfolioMenuItem.getSelection()) { // && !chartsPortfolioMenuItem.getSelection()) {
			winTable[0].setVisible(true);
			winTable[1].setVisible(false);
			winTable[2].setVisible(true);
			sashes.setWeights(new int[]{50,0,50});
		}
		if (!viewEventsMenuItem.getSelection() && viewPortfolioMenuItem.getSelection()) { //&& chartsPortfolioMenuItem.getSelection()) {
			winTable[0].setVisible(false);
			winTable[1].setVisible(true);
			winTable[2].setVisible(true);
			sashes.setWeights(new int[]{0,50,50});
		}

	}
	
	/**
	 * Close button mouse down.
	 * 
	 * @param evt the evt
	 * 
	 * @author Guillaume Thoreton
	 */
	private void  closeButtonMouseDown(SelectionEvent evt) {
		closeMain();
	}

	/**
	 * Close main.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void closeMain() {
		
		Integer hintNumber = new Integer(MainPMScmd.getPrefs().get("email.hint","0"));
		this.emailHint(hintNumber);
		
		winTable[2].setVisible(false);
		this.getShell().setVisible(false);
		this.winTable[0].dispose();
		this.winTable[1].dispose();
		this.winTable[2].dispose();
		this.getShell().dispose();
		SpringContext.getSingleton().close();
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
	
	
	/**
	 * Root shell closed.
	 * 
	 * @param evt the evt
	 * 
	 * @author Guillaume Thoreton
	 */
	private void rootShellClosedRequested(ShellEvent evt) {
		setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
		closeMain();
	}

	@Override
	public void setCursor(Cursor cursor) {
		for (Control control : winTable) {
			control.setCursor(cursor);
		}
	}

	/**
	 * 
	 */
	public static Date analyseStartDateCalculation() {
		Date ret;
		try {
			ret = new SimpleDateFormat("yyyy/MM/dd").parse(
						MainPMScmd.getPrefs().get("gui.crit.date", new SimpleDateFormat("yyyy/MM/dd").format(IndicatorCalculationServiceMain.getDateMoinsNJours(EventSignalConfig.getNewDate(),45))));
		} catch (ParseException e) {
			EventsComposite.LOGGER.error("Wrong date format for last event period, please check your settings. "+ MainPMScmd.getPrefs().get("gui.crit.date", "1970/01/01"),e);
			EventsComposite.LOGGER.debug(e);
			ret = EventModel.DEFAULT_DATE;
		}
		return ret;
	}

	@Override
	public void endRefreshAction(List<Exception> exceptions) {
		try {
			logComposite.endJob(exceptions);
		} finally {
			mainMenu.setEnabled(true);
			this.setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}
	}

	public void initRefreshAction() {
		
		setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
		mainMenu.setEnabled(false);
		logComposite.initRefresh(this);	
	}

	@Override
	public void refreshView(List<Exception> exceptions) {
		for (Composite composite : winTable) {
			if (composite instanceof RefreshableView) {
				((RefreshableView) composite).refreshView(exceptions);
			}
		}
		if (isVisible() && !exceptions.isEmpty()) {
				ErrorDialog dialog = new ErrorDialog(getShell(), SWT.NONE, 
						"Couldn't refresh views. "+
						((allStocksEventModel.getViewStateParams() != null && allStocksEventModel.getViewStateParams().length >0)?allStocksEventModel.getViewStateParams()[0]:"")+"\n",
						exceptions.toString());
				dialog.open();
		}
	}

	public Date getAnalysisStartDate() {
		return analyseStartDateCalculation();
	}
	
}
