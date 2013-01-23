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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import com.finance.pms.admin.config.DbSettings;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.config.IndicatorsConfig;
import com.finance.pms.admin.config.MarketsSettings;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.EventModel;
import com.finance.pms.datasources.EventRefreshController;
import com.finance.pms.datasources.RefreshAllEventStrategyEngine;
import com.finance.pms.datasources.RefreshMonitoredStrategyEngine;
import com.finance.pms.datasources.RefreshPortfolioStrategyEngine;
import com.finance.pms.datasources.web.Indice;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.events.gui.EventsComposite;
import com.finance.pms.portfolio.gui.PortfolioComposite;
import com.finance.pms.portfolio.gui.charts.ChartsComposite;
import com.finance.pms.threads.ConfigThreadLocal;


// TODO: Auto-generated Javadoc
/**
 * The Class MainGui.
 * 
 * @author Guillaume Thoreton
 */
public class MainGui extends SashForm implements RefreshableView { 
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(MainGui.class);

	/** The menu1. */
	private Menu mainMenu;
	
	/** The settings menu item. */
	private MenuItem settingsMenuItem;
	
	/** The settings menu item. */
	private MenuItem marketsMenuItem;
	
	/** The option menu. */
	private Menu optionMenu;
	
	private MenuItem quotationMenuItem;
	
	/** The option menu item. */
	private MenuItem optionMenuItem;
	
	/** The exit menu item. */
	private MenuItem exitMenuItem;
	
	/** The open portfolio menu item. */
	private MenuItem openPortfolioMenuItem;
	
	/** The open events menu item. */
	private MenuItem openEventsMenuItem;
	
	/** The portfolio menu. */
	private Menu portfolioMenu;
	
	/** The events menu. */
	private Menu eventsMenu;
	
	/** The portfolio. */
	private MenuItem portfolio;
	
	/** The events. */
	private MenuItem events;
	
	/** The file menu. */
	private Menu fileMenu;
	
	/** The file menu item. */
	private MenuItem fileMenuItem;
	
	/** The cancel portfolio menu item. */
	MenuItem cancelPortfolioMenuItem;

	/** The save portfolio menu item. */
	MenuItem savePortfolioMenuItem;
	
	/** The db settings. */
	DbSettings dbSettings;
	
	/** The db settings. */
	MarketsSettings marketsSettings;
	
	/** The win table. */
	private  Composite[] winTable = new Composite[3];
	
	private LogComposite logComposite;
	
	/** The dbfile. */
	private static String dbfile;
	
	public static Font DEFAULTFONT;
	public static Font CONTENTFONT;

	/** The refresh model. */
	private EventModel<RefreshAllEventStrategyEngine> allStocksEventModel;
	private EventModel<RefreshMonitoredStrategyEngine> monitoredStocksEventModel;
	private EventModel<RefreshPortfolioStrategyEngine> portfolioStocksEventModel;

	private MenuItem refreshStockList;

	private MenuItem refreshAllPortfoliosQuotations;
	
	private MenuItem refreshRecommendations;

	private MenuItem refreshMonitoredQuotations;

	private SashForm sashes;
	

	/**
	 * Instantiates a new main gui.
	 * 
	 * @param parent the parent
	 * @param style the style
	 * @param dbFilePath the db file path
	 * 
	 * @author Guillaume Thoreton
	 */
	public MainGui(Composite parent, String dbFilePath) {
		super(parent, SWT.VERTICAL);

		this.setLayout(new FillLayout()); 
		MainGui.dbfile = dbFilePath;
		
		sashes = new SashForm(this, SWT.HORIZONTAL);
		logComposite = new LogComposite(this, SWT.NONE);
				
		allStocksEventModel = EventModel.getInstance(new RefreshAllEventStrategyEngine(), logComposite);
		monitoredStocksEventModel = EventModel.getInstance(new RefreshMonitoredStrategyEngine(), logComposite);
		portfolioStocksEventModel = EventModel.getInstance(new RefreshPortfolioStrategyEngine(), logComposite);
		
		initMenus();
		initContent();
		
		Integer hintNumber = new Integer(MainPMScmd.getPrefs().get("email.hint","0"));
		if (hintNumber == 0) openMarkets(getShell());
		
		this.layout();
		
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
					mainwin.rootShellClosed(evt);
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
					fileMenu = new Menu(fileMenuItem);
					fileMenuItem.setMenu(fileMenu);
					{
						MenuItem forecast = new MenuItem(fileMenu, SWT.CASCADE);
						forecast.setText("The Forescast Engine (NEW)");
						forecast.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent evt) {
								Program.launch("http://premiummarkets.elasticbeanstalk.com/");
							}
						});
					}
					{
						MenuItem doc = new MenuItem(fileMenu, SWT.CASCADE);
						doc.setText("Project documentation");
						doc.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent evt) {
								Program.launch("http://premiummarkets.elasticbeanstalk.com/html/swtui.html");
							}
						});
					}
					new MenuItem(fileMenu, SWT.SEPARATOR);
					{
						MenuItem about = new MenuItem(fileMenu, SWT.CASCADE);
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
"\nPremium Markets\nCopyright (C) 2008-2012 Guillaume Thoreton, see <http://www.gnu.org/licenses/>\nBuild : "+pbuild.getProperty("application.buildtime"), null);
									dialog.open();				
									
								} catch (IOException e) {
									LOGGER.debug(e);
								}
							}
						});
					}
					new MenuItem(fileMenu, SWT.SEPARATOR);
					{
						exitMenuItem = new MenuItem(fileMenu, SWT.CASCADE);
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
					portfolio = new MenuItem(mainMenu,SWT.CASCADE);
					portfolio.setText("Portfolios");
					FileInputStream portImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/port.png"));
					portfolio.setImage(new Image(getDisplay(),portImg));
					{
						portfolioMenu = new Menu(portfolio);
						portfolio.setMenu(portfolioMenu);
						{
							openPortfolioMenuItem = new MenuItem(portfolioMenu, SWT.CHECK);
							openPortfolioMenuItem.setText("View");
							openPortfolioMenuItem.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent evt) {

									setMainDisplay();
									if (openPortfolioMenuItem.getSelection()) {
										cancelPortfolioMenuItem.setEnabled(true);
										savePortfolioMenuItem.setEnabled(true);
									} else {
										cancelPortfolioMenuItem.setEnabled(false);
										savePortfolioMenuItem.setEnabled(false);
									}
								}
							});
							openPortfolioMenuItem.setSelection(true);
						}
						new MenuItem(portfolioMenu, SWT.SEPARATOR);
						{
							MenuItem loadPortofolioFromGnuCash = new MenuItem(portfolioMenu,SWT.CASCADE);
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
						new MenuItem(portfolioMenu, SWT.SEPARATOR);
						{
							MenuItem loadPortofolioFromGnuCash = new MenuItem(portfolioMenu,SWT.CASCADE);
							loadPortofolioFromGnuCash.setText("Create AutoPortfolio from gnucash advanced export ...");
							loadPortofolioFromGnuCash.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									((PortfolioComposite)winTable[2]).loadGnucashAdvPortfolioForAutoPortfolio();
								}
							});
							loadPortofolioFromGnuCash.setEnabled(false);
						}
						new MenuItem(portfolioMenu, SWT.SEPARATOR);
						{
							cancelPortfolioMenuItem = new MenuItem(portfolioMenu, SWT.CASCADE);
							cancelPortfolioMenuItem.setText("Save Modifications");
							cancelPortfolioMenuItem.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									((PortfolioComposite)winTable[2]).portfolioValidateButtonMouseDown(evt);
								}
							});
							cancelPortfolioMenuItem.setEnabled(true);
						}
						{
							savePortfolioMenuItem = new MenuItem(portfolioMenu, SWT.CASCADE);
							savePortfolioMenuItem.setText("Cancel Modifications");
							savePortfolioMenuItem.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									((PortfolioComposite) winTable[2]).portfollioCancelAllButtonMouseDown(evt);
								}
							});
							savePortfolioMenuItem.setEnabled(true);
						}
					}
				}
				{
					events = new MenuItem(mainMenu, SWT.CASCADE);
					events.setText("Events");
					FileInputStream bellImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/bell.png"));
					events.setImage(new Image(getDisplay(),bellImg));
					{
						eventsMenu = new Menu(events);
						events.setMenu(eventsMenu);
						{
							openEventsMenuItem = new MenuItem(eventsMenu, SWT.CHECK);
							openEventsMenuItem.setText("View");
							openEventsMenuItem.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									setMainDisplay();
								}
							});
						}
						{
							MenuItem eventForecast = new MenuItem(eventsMenu, SWT.CASCADE);
							eventForecast.setText("Run a neural network forecast on technical analysis");
							eventForecast.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									ActionDialog actionDialog = new ActionDialog(getShell(), SWT.NULL, 
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
							refreshAllPortfoliosQuotations = new MenuItem(quotationMenu, SWT.CASCADE);
							refreshAllPortfoliosQuotations.addSelectionListener(new EventRefreshController(portfolioStocksEventModel,this) {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									LOGGER.guiInfo("I am refreshing. Thanks for waiting...");
									eventModel.setLastQuotationFetch(EventModel.DEFAULT_DATE);
									this.updateEventRefreshModelState(false,true,false,false);
									initRefreshAction();
									super.widgetSelected(evt);
								}
							});
						}
						{
							refreshMonitoredQuotations = new MenuItem(quotationMenu, SWT.CASCADE);
							refreshMonitoredQuotations.addSelectionListener(new EventRefreshController(monitoredStocksEventModel, this) {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									LOGGER.guiInfo("I am refreshing. Thanks for waiting...");
									eventModel.setLastQuotationFetch(EventModel.DEFAULT_DATE);
									this.updateEventRefreshModelState(false,true,false,false);
									initRefreshAction();
									super.widgetSelected(evt);
								}
							});
						}
						quotationMenu.addListener(SWT.Show, new Listener() {
							
							public void handleEvent(Event evt) {
								refreshAllPortfoliosQuotations.setText("Refresh quotations for all portfolios"); 
								refreshMonitoredQuotations.setText("Refresh quotations for monitored shares");
							}
						});
						quotationMenuItem.setMenu(quotationMenu);
					}
				}
				{
					marketsMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
					marketsMenuItem.setText("Stocks");
					FileInputStream portImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/port.png"));
					marketsMenuItem.setImage(new Image(getDisplay(),portImg));
					{
						Menu marketMenu = new Menu(marketsMenuItem);
						{
							refreshStockList = new MenuItem(marketMenu, SWT.CASCADE);
							refreshStockList.addSelectionListener(new EventRefreshController(allStocksEventModel, this) {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									LOGGER.guiInfo("I am refreshing. Thanks for waiting...");
									eventModel.setLastListFetch(EventModel.DEFAULT_DATE);
									this.updateEventRefreshModelState(true,false,false,false);
									initRefreshAction();
									super.widgetSelected(evt);
								}
							});
						}
						{
							refreshRecommendations = new MenuItem(marketMenu, SWT.CASCADE);
							refreshRecommendations.addSelectionListener(new EventRefreshController(allStocksEventModel, this) {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									
									refreshRecommendations.addSelectionListener(new SelectionAdapter() {
										@Override
										public void widgetSelected(SelectionEvent evt) {
											ActionDialog actionDialog = new ActionDialog(getShell(), SWT.NULL, 
													"Recommandations refresh is not available in this version\n"+ 
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
							refreshRecommendations.setEnabled(true);
						}
						{
							MenuItem marketOptMenuItem = new MenuItem(marketMenu, SWT.CASCADE);
							marketOptMenuItem.setText("Customize your list ...");
							marketOptMenuItem.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									allStocksEventModel.setLastListFetch(EventModel.DEFAULT_DATE);
									openMarkets(getShell());
								}
							});
						}
						marketMenu.addListener(SWT.Show, new Listener() {
							
							public void handleEvent(Event evt) {
								String listProvCmd = MainPMScmd.getPrefs().get("quotes.listprovider", "euronext");
								Providers provider =  Providers.getInstance(listProvCmd);
								refreshStockList.setText("Refresh stock list "+listProvCmd+" "+Indice.formatName(provider.getIndices()));
								refreshRecommendations.setText("Refresh recommandations for "+listProvCmd+" "+Indice.formatName(provider.getIndices()));
							}
						});
						marketsMenuItem.setMenu(marketMenu);
					}
				}
				{
					optionMenuItem = new MenuItem(mainMenu, SWT.CASCADE);
					optionMenuItem.setText("Settings");
					FileInputStream toolImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/tool.png"));
					optionMenuItem.setImage(new Image(getDisplay(),toolImg));
					{
						optionMenu = new Menu(optionMenuItem);
						new MenuItem(optionMenu, SWT.SEPARATOR);
						{
							settingsMenuItem = new MenuItem(optionMenu, SWT.CASCADE);
							settingsMenuItem.setText("Application Settings ...");
							settingsMenuItem.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent evt) {
									openSettings();
								}
							});
						}

						optionMenuItem.setMenu(optionMenu);
					}
				}
			}

			this.layout();
		} catch (Exception e) {
			LOGGER.error("",e);
		}
	}


	protected Object buildViewMonitoredStockList() {
		// TODO Auto-generated method stub
		return null;
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
			ctx.refresh();
			
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			shell.setLayout(new FillLayout());
			
			setupAppDefaultFont(display, shell);
			
			final MainGui inst = new MainGui(shell,dbfile);
			
			inst.addControlListener(new ControlListener() {
				
				@Override
				public void controlResized(ControlEvent e) {
					//inst.sashes.getParent().pack();
				}
				
				@Override
				public void controlMoved(ControlEvent e) {
					//nothing
				}
			});
			
			shell.layout();
            shell.pack();
			
            //Shell
            {
				Point prefSize = inst.computeSize(SWT.DEFAULT, SWT.DEFAULT); 
				int width = Math.max(prefSize.x/2,700);
				int height = Math.max((prefSize.y*8)/10,500);
				Rectangle shellBounds = shell.computeTrim(0,0,width,height); 
				shell.setSize(shellBounds.width, shellBounds.height); 
            }
            
            //shell.pack();
			
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
				
				Point portPrefSize = ((PortfolioComposite)inst.winTable[2]).portfolioBoutonsGroup.computeSize(SWT.DEFAULT, SWT.DEFAULT); 
				Point portCompositeSize = ((PortfolioComposite)inst.winTable[2]).portfolioBoutonsGroup.computeSize(portPrefSize.x, Math.max(50,portPrefSize.y));
				((PortfolioComposite)inst.winTable[2]).portfolioBoutonsGroup.setSize(portCompositeSize);
				Rectangle buttonsBounds = ((PortfolioComposite)inst.winTable[2]).portfolioBoutonsGroup.getBounds();
				
				Point port1PrefSize = ((PortfolioComposite)inst.winTable[2]).portfolioInfosGroup.computeSize(SWT.DEFAULT, SWT.DEFAULT); 
				Point port1CompositeSize = ((PortfolioComposite)inst.winTable[2]).portfolioInfosGroup.computeSize(port1PrefSize.x, Math.max(50,port1PrefSize.y));
				((PortfolioComposite)inst.winTable[2]).portfolioInfosGroup.setSize(port1CompositeSize);
				Rectangle infoBounds = ((PortfolioComposite)inst.winTable[2]).portfolioInfosGroup.getBounds();
				
				int xPort = 100*buttonsBounds.height/portfolioShashBounds.height + 2;
				int x1Port = 100*infoBounds.height/portfolioShashBounds.height + 2;
				if ((100 -xPort -x1Port) < 20) {
					xPort=25;
					x1Port=30;
				}
				((PortfolioComposite)inst.winTable[2]).setWeights(new int[]{100-xPort-x1Port,xPort,x1Port});
            }
			
			//Chart
            {
				Rectangle chartShashBounds = ((ChartsComposite)inst.winTable[1]).getClientArea();
				
				Point chartPrefSize = ((ChartsComposite)inst.winTable[1]).chartBoutonsGroup.computeSize(SWT.DEFAULT, SWT.DEFAULT); 
				Point chartCompositeSize = ((ChartsComposite)inst.winTable[1]).chartBoutonsGroup.computeSize(chartPrefSize.x, Math.max(50,chartPrefSize.y));
				((ChartsComposite)inst.winTable[1]).chartBoutonsGroup.setSize(chartCompositeSize);
				Rectangle chartButtonsBounds = ((ChartsComposite)inst.winTable[1]).chartBoutonsGroup.getBounds();
				int xLog = 100*chartButtonsBounds.height/chartShashBounds.height +2;
				if ((100 -xLog) < 20) {
					xLog=30;
				}
				((ChartsComposite)inst.winTable[1]).setWeights(new int[]{100-xLog,xLog});
            }
            	
			shell.open();
			//shell.setMaximized(true);
			
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
		
		MainGui.DEFAULTFONT = new Font(display, myDefFontName, 10, SWT.NORMAL);
		MainGui.CONTENTFONT = new Font(display, myContentFontName, 9, SWT.NORMAL);
	}
	/**
	 * Inits the data.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void initContent() {
		
		//Thread local init
		ConfigThreadLocal.set("eventSignal", new EventSignalConfig());
		ConfigThreadLocal.set("indicatorParams", new IndicatorsConfig());
		
		EventsComposite eventC = new EventsComposite(sashes, SWT.NONE|SWT.BORDER, logComposite, getAnalysisStartDate());
		this.winTable[0] = eventC;
		eventC.initData();
		this.openEventsMenuItem.setSelection(false);
		
		ChartsComposite charts = new ChartsComposite(sashes,SWT.NONE|SWT.BORDER);
		this.winTable[1] = charts;
		
		PortfolioComposite portfolio = new PortfolioComposite(sashes,charts,SWT.NONE|SWT.BORDER);
		this.winTable[2] = portfolio;
		this.openPortfolioMenuItem.setSelection(true);
		
		this.setMainDisplay();
		
		LOGGER.debug("Finished initializing MainGui Data.");
	}

	
	/**
	 * Open settings.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void openSettings() {
		
		if (dbSettings == null || dbSettings.getShell().isDisposed()) {
			dbSettings = new DbSettings(this.getShell(),dbfile);
		} else {
			dbSettings.getShell().forceFocus();
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
		
		if (openEventsMenuItem.getSelection() && !openPortfolioMenuItem.getSelection()) {
			winTable[0].setVisible(true);
			winTable[1].setVisible(false);
			winTable[2].setVisible(false);
			sashes.setWeights(new int[]{100,0,0});
		}
		
		if (!openEventsMenuItem.getSelection() && !openPortfolioMenuItem.getSelection()) {
			for (int j = 0; j < winTable.length; j++) {
				winTable[j].setVisible(false);
			}
		}
		
		if (openEventsMenuItem.getSelection() && openPortfolioMenuItem.getSelection()) { // && !chartsPortfolioMenuItem.getSelection()) {
			winTable[0].setVisible(true);
			winTable[1].setVisible(false);
			winTable[2].setVisible(true);
			sashes.setWeights(new int[]{50,0,50});
		}
		if (!openEventsMenuItem.getSelection() && openPortfolioMenuItem.getSelection()) { //&& chartsPortfolioMenuItem.getSelection()) {
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
	private void rootShellClosed(ShellEvent evt) {
		LOGGER.debug("dialogShell.shellClosed, event="+evt);
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

	public void endRefreshAction() {
		try {
			logComposite.endJob();
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

	public void refreshView() {
		for (Composite composite : winTable) {
			if (composite instanceof RefreshableView) {
				((RefreshableView) composite).refreshView();
			}
		}
	}

	public Date getAnalysisStartDate() {
		return analyseStartDateCalculation();
	}
	
}
