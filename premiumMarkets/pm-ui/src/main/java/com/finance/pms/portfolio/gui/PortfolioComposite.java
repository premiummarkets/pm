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
package com.finance.pms.portfolio.gui;


import java.awt.Paint;
import java.io.File;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EventObject;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.jfree.chart.ChartColor;

import com.finance.pms.ActionDialogAction;
import com.finance.pms.CursorFactory;
import com.finance.pms.ErrorDialog;
import com.finance.pms.LogComposite;
import com.finance.pms.MainGui;
import com.finance.pms.RefreshableView;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.alerts.ThresholdType;
import com.finance.pms.datasources.EventModel;
import com.finance.pms.datasources.QuotatationRefreshException;
import com.finance.pms.datasources.RefreshAllEventStrategyEngine;
import com.finance.pms.datasources.RefreshMonitoredStrategyEngine;
import com.finance.pms.datasources.RefreshPortfolioStrategyEngine;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.files.GnuCashAdvPortfolioParser;
import com.finance.pms.datasources.files.GnuCashTransactionReportParser;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.quotation.QuotationUpdate.StockNotFoundException;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.portfolio.AbstractSharesList;
import com.finance.pms.portfolio.InvalidQuantityException;
import com.finance.pms.portfolio.MonitorLevel;
import com.finance.pms.portfolio.Portfolio;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.PortfolioShare.InOutWeighted;
import com.finance.pms.portfolio.Transaction;
import com.finance.pms.portfolio.Transaction.TransactionType;
import com.finance.pms.portfolio.UserPortfolio;
import com.finance.pms.portfolio.gui.charts.ChartsComposite;
import com.finance.pms.threads.ObserverMsg;
import com.finance.pms.threads.ObserverMsg.ObsKey;


/**
 * The Class PortfolioComposite.
 * 
 * @author Guillaume Thoreton
 */
public class PortfolioComposite extends SashForm implements RefreshableView {


	public static MyLogger LOGGER = MyLogger.getLogger(PortfolioComposite.class);
	
	Paint[] paints = ChartColor.createDefaultPaintArray();
	int colorNum = 0;
	int getNextColor() {
		return (++colorNum % paints.length);
	}
	
	private CTabFolder portfolioCTabFolder1;
	private CTabItem[] cTabItem;

	public Group portfolioInfosGroup;
	private Group totalsGroup;

	private final ChartsComposite chartsComposite;

	private Text gain;
	private Text unrealGain;
	private Text unrealProfit;
	private Text value;
	private Text amountIn;
	private Text amountOut;
	private Text currency;

	private Button slidingEndAnchor;
	private Button slidingStartAnchor;

	ModelController modelControler;
	
	private LogComposite logComposite;

	private Font smallFont;
	private Titles sorteted;
	private Shell tableRowToolTip;
	
	private class ModelController {
		
		private final class ObserverImplementation implements Observer,  Comparable<Observer> {
			public void update(Observable o, Object arg) {
				
				ObserverMsg observerMsg = (ObserverMsg) arg;
				
				if (observerMsg != null && observerMsg.getKey().equals(ObserverMsg.ObsKey.DONE)) {
					
					getDisplay().syncExec(
							new Runnable() {
								public void run(){
									for (int tabIdx=0; tabIdx < tabbedPortfolios.size(); tabIdx++) {
										refreshChartData(tabIdx, false);
									}
							}
					});
					
				}
			}

			@Override
			public int compareTo(Observer o) {
				String thisToString = this.getClass().getName() + "@" + Integer.toHexString(this.hashCode());
				String oToString = o.getClass().getName() + "@" + Integer.toHexString(o.hashCode());
				return thisToString.compareTo(oToString);
			}
		}

		private List<List<SlidingPortfolioShare>> tabbedPortfolioShares;
		private List<Portfolio> tabbedPortfolios;

		private EventModel<RefreshPortfolioStrategyEngine> portfolioStocksEventModel;
		private EventModel<RefreshMonitoredStrategyEngine> monitoredStocksEventModel;


		public ModelController(LogComposite logComposite) {
			
			tabbedPortfolioShares = new ArrayList<List<SlidingPortfolioShare>>();
			tabbedPortfolios = new ArrayList<Portfolio>();

			Observer observer =  new ObserverImplementation();
			
			//Event and quotations refresh
			EventModel.getInstance(new RefreshAllEventStrategyEngine(), observer);
			monitoredStocksEventModel = EventModel.getInstance(new RefreshMonitoredStrategyEngine(), observer, logComposite);
			portfolioStocksEventModel = EventModel.getInstance(new RefreshPortfolioStrategyEngine(), observer, logComposite);
		}


		public void addPortfolioShareToTab(int tabId, SlidingPortfolioShare slidingPS) {
			tabbedPortfolioShares.get(tabId).add(slidingPS);
			updateModels();
		}
		
		public void addOrUpdatePortfolioShareToTab(int tabId, SlidingPortfolioShare slidingPS) {
			if (tabbedPortfolioShares.get(tabId).contains(slidingPS)) {
				int indexOf = tabbedPortfolioShares.get(tabId).indexOf(slidingPS);
				tabbedPortfolioShares.get(tabId).set(indexOf, slidingPS);
			} else {
				tabbedPortfolioShares.get(tabId).add(slidingPS);
			}
			updateModels();
		}


		public int tabSize(int tabIndex) {
			return tabbedPortfolioShares.get(tabIndex).size();
		}


		public SlidingPortfolioShare getShareInTab(int tabIdx, int rowIdx) {
			return tabbedPortfolioShares.get(tabIdx).get(rowIdx);
		}


		public List<SlidingPortfolioShare> getShareListInTab(int tabIdx) {
			return tabbedPortfolioShares.get(tabIdx);
		}


		public void resetAllPortfolioModel() {
			tabbedPortfolios = new ArrayList<Portfolio>();
			tabbedPortfolioShares = new ArrayList<List<SlidingPortfolioShare>>();
			updateModels();
		}
		
		public void resetOnePortfolioModel(Integer tabIdx, Portfolio newPortfolio) {
			tabbedPortfolios.set(tabIdx, newPortfolio);	
			tabbedPortfolioShares.set(tabIdx, new ArrayList<SlidingPortfolioShare>());
		}


		public void removePortfolio(int tabindex) {
			
			tabbedPortfolios.remove(tabindex);
			tabbedPortfolioShares.remove(tabindex);
			updateModels();
		}


		public void removeShareFromTab(int tabi, int rowIndex) {
			tabbedPortfolioShares.get(tabi).remove(rowIndex);
			updateModels();
			
		}


		public Portfolio getPortfolio(Integer tabIdx) {
			return tabbedPortfolios.get(tabIdx);
		}
		

		public int nbTabs() {
			return tabbedPortfolios.size();
		}
		
		public void addPortfolioNew(Portfolio portfolio) {
			tabbedPortfolios.add(portfolio);
			tabbedPortfolioShares.add(new ArrayList<SlidingPortfolioShare>());
		}
		
		private void updateModels() {
			
			Set<Stock> monitored = new HashSet<Stock>();
			Set<Stock> portfolioStocks = new HashSet<Stock>();
			
			for (List<SlidingPortfolioShare> slidingPortfolioShares : tabbedPortfolioShares) {
				for (SlidingPortfolioShare slidingPortfolioShare : slidingPortfolioShares) {
					portfolioStocks.add(slidingPortfolioShare.getStock());
					if (!slidingPortfolioShare.getMonitorLevel().equals(MonitorLevel.NONE)) {
						monitored.add(slidingPortfolioShare.getStock());
					}
				}
			}
			
			monitoredStocksEventModel.setViewStateParams(monitored.toArray());
			portfolioStocksEventModel.setViewStateParams(portfolioStocks.toArray());
		}
		
	}

	private final class CursorChangerObserver implements Observer {
		private int cpt;

		private CursorChangerObserver(int cpt) {
			this.cpt = cpt;
		}

		public void update(Observable o, Object arg) {
			cpt--;
			if (cpt > 0) {
				getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_APPSTARTING));
			} else {
				getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
			}
		}
	}

	private final class Tabinit extends Observable implements Runnable {
		private final Integer tabIdx;
		private final AbstractSharesList portfolio;

		private Tabinit(Integer tabIdx, AbstractSharesList portfolio) {
			this.tabIdx = tabIdx;
			this.portfolio = portfolio;
		}

		public void run() {
			
			try {
				
				ArrayList<SlidingPortfolioShare> listOfShares = buildSlidingPortfolioShareList(portfolio.getListShares().values());

				updatePortfolioTabItems(tabIdx, listOfShares, 0);
				refreshChartData(tabIdx, true);
				
			} catch (Exception e) {
				LOGGER.error("Unhandeled error runing the Tab initialisation :"+e,e);
			} finally {
				setChanged();
				notifyObservers();
			}
		}
	}



	/**
	 * The Enum Titles.
	 * 
	 * @author Guillaume Thoreton
	 */
	private enum Titles {
		Name ("Stock", "Stock name."), 
		BuyPrc ("Buy price", "Average buy price."), Close ("Close","Last close price."), ZWProfPrc ("No profit price", "Minimum sell price for no loss including inflation."), 
		UrProf ("Unralised Profit","Unrealized profit percentage."),
		CashIn("Cash in","Amount of cash in."), CashOut("Cash out","Amount of cash out."),
		WUrProf ("Inflated Profit","Unrealized profit percentage including inflation."), 
		WCashIn("Inflated Cash in","Inflation weighted cash in."), WCashOut("Inflated Cash out", "Inflation weighted cash out."),
		Monitor("Monitor","Premium Markets alert monitor level.");
		
		String toolTip;
		String columnName;
		
		Titles(String columnName, String toolTip) {
			this.toolTip = toolTip;
			this.columnName = columnName;
		}
		
		public String getToolTip() {
			return toolTip;
		}

		public String getColumnName() {
			return columnName;
		}
		
		public static Titles valueOfColumnName(String columnName){
			for (Titles e: Titles.values()) {
				if (e.getColumnName().equals(columnName)) return e;
			}
			return Titles.Name;
		}
	};
	
	


	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void main(String[] args) {

		SpringContext ctx = new SpringContext();
		ctx.setDataSource(args[0]);
		ctx.loadBeans(new String[] { "/connexions.xml", "/swtclients.xml" });
		ctx.refresh();		
	}



	/**
	 * Instantiates a new portfolio composite.
	 * 
	 * @param parent the parent
	 * @param style the style
	 * @param chartsComposite the charts composite
	 * 
	 * @author Guillaume Thoreton
	 */
	public PortfolioComposite(Composite parent, ChartsComposite chartsComposite, int style, LogComposite logComposite) {
		super(parent, style);
		
		this.logComposite = logComposite;
		//this.fourToutEventModel = EventModel.getInstance(new RefreshFourToutStrategyEngine(), logComposite);
		//addShareObserver = new AddShareObserver();
		//this.fourToutEventModel.addEngineObserver(addShareObserver);
		
		this.chartsComposite = chartsComposite;
		this.chartsComposite.setComposite(this);
		
		this.addListener(SWT.Hide, new Listener() {

			public void handleEvent(Event arg0) {
				rootShellClosed(arg0);
			}	

		});
		
		this.modelControler = new ModelController(logComposite);
		this.setOrientation(SWT.VERTICAL);
		
		FontData[] fD = MainGui.DEFAULTFONT.getFontData();
		fD[0].setHeight((int)(fD[0].getHeight()*1));
		smallFont = new Font(getDisplay(),fD[0]);
		
		this.setToolTipText("Sash : Click on this border and drag to resize");
		this.setCursor(CursorFactory.getCursor(SWT.CURSOR_CROSS));

		initGUI();
	}

	/**
	 * Inits the gui.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void initGUI() {
		try {

			this.setBackground(new Color(getDisplay(),115,147,250));
			{
				portfolioCTabFolder1 = new CTabFolder(this, SWT.NONE);
				GridData portfolioCTabFolder1LData = new GridData();
				portfolioCTabFolder1LData.verticalAlignment = GridData.FILL;
				portfolioCTabFolder1LData.horizontalAlignment = GridData.FILL;
				portfolioCTabFolder1LData.horizontalSpan = 2;
				portfolioCTabFolder1LData.grabExcessHorizontalSpace = true;
				portfolioCTabFolder1LData.grabExcessVerticalSpace = true;
				portfolioCTabFolder1.setLayoutData(portfolioCTabFolder1LData);
				portfolioCTabFolder1.setBackground(new Color(getDisplay(),204,204,255));
				portfolioCTabFolder1.setSelectionBackground(new Color(getDisplay(),115,147,250));
				portfolioCTabFolder1.setFont(MainGui.DEFAULTFONT);
				buildPortfoliosTabs();
				
				portfolioCTabFolder1.addSelectionListener(new SelectionListener() {

					public void widgetDefaultSelected(SelectionEvent arg0) {
						int selectionIndex = portfolioCTabFolder1.getSelectionIndex();
						if (selectionIndex != -1) {
							refreshChartAndInfos();
						}
					}
					
					public void widgetSelected(SelectionEvent arg0) {
						int selectionIndex = portfolioCTabFolder1.getSelectionIndex();
						if (selectionIndex != -1) {
							refreshChartAndInfos();
						}
					}

					private void refreshChartAndInfos() {
						refreshPortfolioTotalsInfos(portfolioCTabFolder1.getSelectionIndex());
						int si = portfolioCTabFolder1.getSelectionIndex();
						if (!cTabItem[si].isDisposed()) refreshChartData(si, true);
						highLightSlidingCols();
												
					}

				});

			}
			{
				{
					{
						MenuItem portfolioAddPortFoliobutton = new  MenuItem(MainGui.portfolioSubMenu, SWT.CASCADE);
						portfolioAddPortFoliobutton.setText("New portfolio ...");
						portfolioAddPortFoliobutton.addSelectionListener(new SelectionListener() {
							@Override
							public void widgetSelected(SelectionEvent evt) {
								portfolioAddPortfolioButtonMouseDown(evt);
								refreshChartData(portfolioCTabFolder1.getSelectionIndex(), true);
							}
							@Override
							public void widgetDefaultSelected(SelectionEvent evt) {
								portfolioAddPortfolioButtonMouseDown(evt);
								refreshChartData(portfolioCTabFolder1.getSelectionIndex(), true);
								
							}
						});
					}
					{
						MenuItem portfolioDeletePortfoliobutton = new MenuItem(MainGui.portfolioSubMenu, SWT.CASCADE);
						portfolioDeletePortfoliobutton.setText("Remove portfolio");
						portfolioDeletePortfoliobutton.addSelectionListener(new SelectionListener() {
							
							@Override
							public void widgetSelected(SelectionEvent e) {
								portfolioRemovePortfolioButtonMouseDown(e);
								refreshChartData(portfolioCTabFolder1.getSelectionIndex(), true);
								
							}
							
							@Override
							public void widgetDefaultSelected(SelectionEvent e) {
								portfolioRemovePortfolioButtonMouseDown(e);
								refreshChartData(portfolioCTabFolder1.getSelectionIndex(), true);
								
							}
						});
							
					}
					new MenuItem(MainGui.portfolioSubMenu, SWT.SEPARATOR);
					{
						MenuItem addShareFromFilebutton = new  MenuItem(MainGui.portfolioSubMenu, SWT.CASCADE);
						addShareFromFilebutton.setText("New share ...");
						addShareFromFilebutton.addSelectionListener(new SelectionListener() {
							@Override
							public void  widgetSelected(SelectionEvent evt) {
//								portfolioInfosGroup.getParent().getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
//								try {
									portfolioAddShareDialog(evt);
//									refreshChartData(portfolioCTabFolder1.getSelectionIndex(), true);
//								} finally {
//									portfolioInfosGroup.getParent().getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
//								}
							}
							@Override
							public void widgetDefaultSelected(SelectionEvent evt) {
//								portfolioInfosGroup.getParent().getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
//								try {
									portfolioAddShareDialog(evt);
//									refreshChartData(portfolioCTabFolder1.getSelectionIndex(), true);
//								} finally {
//									portfolioInfosGroup.getParent().getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
//								}
							}
						});
					}
					{
						MenuItem addShareFromFilebutton = new  MenuItem(MainGui.portfolioSubMenu, SWT.CASCADE);
						addShareFromFilebutton.setText("Remove share");
						addShareFromFilebutton.addSelectionListener(new SelectionListener() {
							@Override
							public void  widgetSelected(SelectionEvent evt) {
								try {
									portfollioRemoveShareButtonMouseDown(evt);
								} catch (InvalidQuantityException e) {
									LOGGER.warn(e,e);
									ErrorDialog errorDialog = new ErrorDialog(getShell(),SWT.NULL,e.getMessage(), null);
									errorDialog.open();
								}
								refreshChartData(portfolioCTabFolder1.getSelectionIndex(), true);
							}
							@Override
							public void widgetDefaultSelected(SelectionEvent evt) {
								try {
									portfollioRemoveShareButtonMouseDown(evt);
								} catch (InvalidQuantityException e) {
									LOGGER.warn(e,e);
									ErrorDialog errorDialog = new ErrorDialog(getShell(),SWT.NULL,e.getMessage(), null);
									errorDialog.open();
								}
								refreshChartData(portfolioCTabFolder1.getSelectionIndex(), true);
							}
						});
					}
					new MenuItem(MainGui.portfolioSubMenu, SWT.SEPARATOR);
					{
						MenuItem cancelPortfolioMenuItem = new MenuItem(MainGui.portfolioSubMenu, SWT.CASCADE);
						cancelPortfolioMenuItem.setText("Save Modifications");
						cancelPortfolioMenuItem.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent evt) {
								portfolioValidateButtonMouseDown(evt);
							}
						});
						cancelPortfolioMenuItem.setEnabled(true);
					}
					{
						MenuItem savePortfolioMenuItem = new MenuItem(MainGui.portfolioSubMenu, SWT.CASCADE);
						savePortfolioMenuItem.setText("Cancel Modifications");
						savePortfolioMenuItem.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent evt) {
								portfollioCancelAllButtonMouseDown(evt);
							}
						});
						savePortfolioMenuItem.setEnabled(true);
					}
				}
				{
					portfolioInfosGroup = new Group(this, SWT.NONE);
					GridLayout portfolioInfosGroupLayout = new GridLayout();
					portfolioInfosGroupLayout.numColumns = 2;
					portfolioInfosGroup.setLayout(portfolioInfosGroupLayout);
					GridData portfolioInfosGroupData = new GridData(GridData.FILL_BOTH);
					portfolioInfosGroup.setLayoutData(portfolioInfosGroupData);
					portfolioInfosGroup.setText("Portfolios informations : ");
					portfolioInfosGroup.setFont(MainGui.DEFAULTFONT);
					portfolioInfosGroup.setBackground(new Color(getDisplay(),204,204,255));

					{
						GridData portfolioInfoItemsData = new GridData(GridData.FILL_HORIZONTAL);
						Label text0 = new Label(portfolioInfosGroup, SWT.RIGHT);
						text0.setText("Portfolio currency : ");
						text0.setToolTipText("Currency used for the totals");
						text0.setFont(MainGui.DEFAULTFONT);
						text0.setBackground(new Color(getDisplay(),204,204,255));
						currency = new Text(portfolioInfosGroup, SWT.RIGHT);
						currency.setBackground(new Color(getDisplay(),255,255,255));
						currency.setEditable(false);
						currency.setLayoutData(portfolioInfoItemsData);
						currency.setText("");
						currency.setFont(MainGui.CONTENTFONT);
					}
					{
						GridData portfolioInfoItemsData = new GridData(GridData.FILL_HORIZONTAL);
						Label text0 = new Label(portfolioInfosGroup, SWT.RIGHT);
						text0.setText("Current real Gain : ");
						text0.setToolTipText("Sum of sell transactions minus sum of buy transactions for the quantities sold. Sum(out) - Sum(buy price)\nNB : Transactions are stored for gnucash portfolios only. For other portfolios, this is equal to Sum(out).");
						text0.setFont(MainGui.DEFAULTFONT);
						text0.setBackground(new Color(getDisplay(),204,204,255));
						gain = new Text(portfolioInfosGroup, SWT.RIGHT);
						gain.setBackground(new Color(getDisplay(),255,255,255));
						gain.setEditable(false);
						gain.setLayoutData(portfolioInfoItemsData);
						gain.setText("");
						gain.setFont(MainGui.CONTENTFONT);
						Label text2 = new Label(portfolioInfosGroup, SWT.RIGHT);
						text2.setText("Unr. Gain since init : ");
						text2.setToolTipText("Actual value plus monies out minus monies in. (value + Sum(out))- Sum(in)");
						text2.setFont(MainGui.DEFAULTFONT);
						text2.setBackground(new Color(getDisplay(),204,204,255));
						unrealGain = new Text(portfolioInfosGroup, SWT.RIGHT);
						unrealGain.setBackground(new Color(getDisplay(),255,255,255));
						unrealGain.setEditable(false);
						unrealGain.setLayoutData(portfolioInfoItemsData);
						unrealGain.setText("");
						unrealGain.setFont(MainGui.CONTENTFONT);
						Label text2c = new Label(portfolioInfosGroup, SWT.RIGHT);
						text2c.setText("Unr. Profit %  since init : ");
						text2c.setToolTipText("((value + Sum(out))- Sum(in)) /  Sum(in) ");
						text2c.setFont(MainGui.DEFAULTFONT);
						text2c.setBackground(new Color(getDisplay(),204,204,255));
						unrealProfit = new Text(portfolioInfosGroup, SWT.RIGHT);
						unrealProfit.setBackground(new Color(getDisplay(),255,255,255));
						unrealProfit.setEditable(false);
						unrealProfit.setLayoutData(portfolioInfoItemsData);
						unrealProfit.setText("");
						unrealProfit.setFont(MainGui.DEFAULTFONT);
						Label text3 = new Label(portfolioInfosGroup, SWT.RIGHT);
						text3.setText("Actual value : ");
						text3.setFont(MainGui.DEFAULTFONT);
						text3.setBackground(new Color(getDisplay(),204,204,255));
						value = new Text(portfolioInfosGroup, SWT.RIGHT);
						value.setBackground(new Color(getDisplay(),255,255,255));
						value.setEditable(false);
						value.setLayoutData(portfolioInfoItemsData);
						value.setText("");
						value.setFont(MainGui.CONTENTFONT);
					}
					{
						totalsGroup = new Group(portfolioInfosGroup, SWT.NONE);
						GridLayout amountsL = new GridLayout();
						amountsL.numColumns = 4;
						totalsGroup.setLayout(amountsL);
						GridData totalsGroupData = new GridData(GridData.FILL_HORIZONTAL);
						totalsGroupData.horizontalSpan = 2;
						totalsGroup.setLayoutData(totalsGroupData);
						totalsGroup.setBackground(new Color(getDisplay(),204,204,255));

						Label ainT = new Label(totalsGroup, SWT.LEFT);
						ainT.setText("In since init :");
						ainT.setFont(MainGui.DEFAULTFONT);
						ainT.setBackground(new Color(getDisplay(),204,204,255));
						amountIn = new Text(totalsGroup, SWT.LEFT);
						amountIn.setEditable(false);
						amountIn.setText("");
						amountIn.setFont(MainGui.CONTENTFONT);
						
						Label aoutT = new Label(totalsGroup, SWT.LEFT);
						aoutT.setText("Out since init :");
						aoutT.setFont(MainGui.DEFAULTFONT);
						aoutT.setBackground(new Color(getDisplay(),204,204,255));
						amountOut = new Text(totalsGroup, SWT.LEFT);
						amountOut.setEditable(false);
						amountOut.setText("");
						amountOut.setFont(MainGui.CONTENTFONT);
					
					}
					{
						slidingEndAnchor = new Button(portfolioInfosGroup, SWT.CHECK);
						GridData slidingChecksLayout = new GridData(GridData.FILL_HORIZONTAL);
						slidingChecksLayout.horizontalSpan = 2;
						slidingEndAnchor.setLayoutData(slidingChecksLayout);
						slidingEndAnchor.setText("Set current price as chart slider's end date");
						slidingEndAnchor.setFont(MainGui.DEFAULTFONT);
						slidingEndAnchor.addSelectionListener(new SelectionListener() {
							
							public void widgetSelected(SelectionEvent e) {
								slidingDateChange();
								sortColumn(Titles.UrProf.name());
								
							}
							
							public void widgetDefaultSelected(SelectionEvent e) {
								slidingDateChange();
								sortColumn(Titles.UrProf.name());
								
							}
						
						});
						
					}
					{
						slidingStartAnchor = new Button(portfolioInfosGroup, SWT.CHECK);
						GridData slidingChecksLayout = new GridData(GridData.FILL_HORIZONTAL);
						slidingChecksLayout.horizontalSpan = 2;
						slidingStartAnchor.setLayoutData(slidingChecksLayout);
						slidingStartAnchor.setText("Set buy price as chart sliders start date");
						slidingStartAnchor.setFont(MainGui.DEFAULTFONT);
						slidingStartAnchor.addSelectionListener(new SelectionListener() {
							
							public void widgetSelected(SelectionEvent e) {
								slidingDateChange();
								sortColumn(Titles.UrProf.name());
							}

							public void widgetDefaultSelected(SelectionEvent e) {
								slidingDateChange();
								sortColumn(Titles.UrProf.name());
							}
							
						});
						
					}
				}
				refreshPortfolioTotalsInfos(portfolioCTabFolder1.getSelectionIndex());
				
			}
			
		} catch (Exception e) {
			LOGGER.error("",e);
		}
	}
	
	private void highLightSlidingCols() {
		
		int si = portfolioCTabFolder1.getSelectionIndex();
		CTabItem cTabItemElem = cTabItem[si];
		
		if (!cTabItemElem.isDisposed() && cTabItemElem.isShowing()) {
		
			Table t  = (Table)  cTabItemElem.getData();
			
			Color bgColor;
			Color fgColor;
			
			//Table columns
			boolean isSlidingPrices = slidingEndAnchor.getSelection() || slidingStartAnchor.getSelection();
			if (isSlidingPrices) {
				bgColor = new Color(getDisplay(),246,244,242);
				fgColor = new Color(getDisplay(),230,225,220);
			} else {
				bgColor = new Color(getDisplay(),255,255,255);
				fgColor = new Color(getDisplay(),0,0,0);
			}
			
			for (TableItem item : t.getItems()) {
				item.setBackground(Titles.BuyPrc.ordinal(),bgColor);
				item.setForeground(Titles.BuyPrc.ordinal(),fgColor);
				item.setBackground(Titles.CashIn.ordinal(),bgColor);
				item.setForeground(Titles.CashIn.ordinal(),fgColor);
				item.setBackground(Titles.CashOut.ordinal(),bgColor);
				item.setForeground(Titles.CashOut.ordinal(),fgColor);
				item.setBackground(Titles.WCashIn.ordinal(),bgColor);
				item.setForeground(Titles.WCashIn.ordinal(),fgColor);
				item.setBackground(Titles.WCashOut.ordinal(),bgColor);
				item.setForeground(Titles.WCashOut.ordinal(),fgColor);
				item.setBackground(Titles.WUrProf.ordinal(),bgColor);
				item.setForeground(Titles.WUrProf.ordinal(),fgColor);
				item.setBackground(Titles.ZWProfPrc.ordinal(),bgColor);
				item.setForeground(Titles.ZWProfPrc.ordinal(),fgColor);
				
			}
		
			//Infos
			if (slidingStartAnchor.getSelection()) {
				unrealGain.setBackground(bgColor);
				unrealProfit.setBackground(bgColor);
				value.setBackground(bgColor);
				unrealGain.setForeground(fgColor);
				unrealProfit.setForeground(fgColor);
				value.setForeground(fgColor);
				
			}  else {
				unrealGain.setBackground(new Color(getDisplay(),255,255,255));
				unrealProfit.setBackground(new Color(getDisplay(),255,255,255));
				value.setBackground(new Color(getDisplay(),255,255,255));
				unrealGain.setForeground(new Color(getDisplay(),0,0,0));
				unrealProfit.setForeground(new Color(getDisplay(),0,0,0));
				value.setForeground(new Color(getDisplay(),0,0,0));
			}
			
			gain.setBackground(bgColor);
			gain.setForeground(fgColor);
			
			amountIn.setBackground(bgColor);
			amountIn.setForeground(fgColor);
			amountOut.setBackground(bgColor);
			amountOut.setForeground(fgColor);
			
		}
	}


	/**
	 * Inits the data.
	 * 
	 * @author Guillaume Thoreton
	 * @param tabInitObserver 
	 */
	public void updateTabItemsFromPortfolio(final Integer tabIdx, final AbstractSharesList portfolio, Observer tabInitObserver) {

		Tabinit runnable = new Tabinit(tabIdx, portfolio);
		runnable.addObserver(tabInitObserver);
		getDisplay().asyncExec(runnable);	

	}

	/**
	 * Update char data.
	 * 
	 * @author Guillaume Thoreton
	 * @param portfolioHasChanged 
	 */
	private void refreshChartData(int tabIdx, Boolean portfolioHasChanged) {
			try {
				this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
				refreshChartDataNoCursorChange(tabIdx, portfolioHasChanged);
			} finally {
				this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
			}
	}

	private void refreshChartDataNoCursorChange(int tabIdx, Boolean portfolioHasChanged) {
		if (portfolioCTabFolder1.getSelectionIndex() == tabIdx) {

			if (modelControler.nbTabs() > 0 && tabIdx != -1) {

				List<SlidingPortfolioShare> listShares = modelControler.getShareListInTab(tabIdx);
				chartsComposite.resetShareList(listShares, portfolioHasChanged);
				updatePortfolioColors(tabIdx,listShares);

			} else {
				chartsComposite.resetShareList(new ArrayList<SlidingPortfolioShare>(), true);
			}
		}

	}

	/**
	 * @param si
	 * @param listColors 
	 */
	private void updatePortfolioColors(int si,List<SlidingPortfolioShare> portfolioShares) {

		Table t = (Table) cTabItem[si].getData();
		TableItem[] titems = t.getItems();
		for (int i = 0; i < titems.length; i++) {
			Color color = portfolioShares.get(i).getColor();
			titems[i].setBackground(Titles.Name.ordinal(), color);
			titems[i].setForeground(new Color(getDisplay(), new RGB(0, 0, 0)));
		}
		t.setSelection(-1);
		
	}

	/**
	 * Construct portfolios tabs.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void buildPortfoliosTabs() {
		
		List<Portfolio> visiblePortfolios = PortfolioMgr.getInstance().getVisiblePortfolios();
		modelControler.resetAllPortfolioModel();

		cTabItem = new CTabItem[visiblePortfolios.size()];
		int i=0;
		
		try {
			Observer tabInitObs = new CursorChangerObserver(visiblePortfolios.size());
			
			for (Portfolio portfolio : visiblePortfolios) {
				addOneTab(i, portfolio, tabInitObs);
				i++;
			}
			
		} finally {
		}
		
		portfolioCTabFolder1.setSelection(0);
	}


	/**
	 * Adds the one tab.
	 * 
	 * @param i the i
	 * @param portfolio the p
	 * 
	 * @author Guillaume Thoreton
	 * @param tabInitObserver 
	 */
	private void addOneTab(int i, Portfolio portfolio, Observer tabInitObserver) {
		
		modelControler.addPortfolioNew(portfolio);
		
		cTabItem[i] = new CTabItem(portfolioCTabFolder1, SWT.NONE);
		cTabItem[i].setText(portfolio.getName());
		cTabItem[i].setFont(smallFont);
		
		{
			final Table table = new Table(portfolioCTabFolder1, SWT.BORDER | SWT.FULL_SELECTION);
			table.setFont(smallFont);
			table.setLinesVisible(false);
			table.setHeaderVisible(true);
			cTabItem[i].setControl(table);
			for (int j = 0; j < Titles.values().length; j++) {
				
				TableColumn column = new TableColumn(table, SWT.CENTER);
				column.setWidth(2);
				column.setText(Titles.values()[j].getColumnName());
				column.setToolTipText(Titles.values()[j].getToolTip());
				column.addSelectionListener(new SelectionListener() {
					public void widgetDefaultSelected(SelectionEvent arg0) {
						LOGGER.debug("Column selected : " + ((TableColumn) arg0.getSource()).getText());
						sortColumn(((TableColumn) arg0.getSource()).getText());
					}

					public void widgetSelected(SelectionEvent arg0) {
						//Nothing
					}
				});
			}
			cTabItem[i].setData(table);
			
			{
				Menu portfolioShareCtxMenu = new Menu(table);
				table.setMenu(portfolioShareCtxMenu);
				{
					MenuItem menuItemAlert = new MenuItem(portfolioShareCtxMenu, SWT.NONE);
					menuItemAlert.setText("Alert on threshold Manager ...");
					menuItemAlert.addSelectionListener(new SelectionListener() {
						
						@Override
						public void widgetSelected(SelectionEvent event) {
							openAlertDialog(event);
						}
		
						@Override
						public void widgetDefaultSelected(SelectionEvent event) {
							openAlertDialog(event);
						}
						
						private void openAlertDialog(SelectionEvent event) {
							
							int selectionIndex = table.getSelectionIndex();
							if (selectionIndex != -1) {
								SlidingPortfolioShare selectedShare = modelControler.getShareInTab(portfolioCTabFolder1.getSelectionIndex(), selectionIndex);
								AddAlertDialog addAlertDialog = new AddAlertDialog(getShell(), selectedShare);
								Point evtsAbsPos = getDisplay().map(table, null, event.y, event.x);
								addAlertDialog.open(evtsAbsPos.x, evtsAbsPos.y + table.getSelection()[0].getBounds().height + table.getSelection()[0].getBounds().y);
							} 
							
						}
					});
				}
			}
			final TableEditor txtEditor = new TableEditor(table);
			final TableEditor comboEditor = new TableEditor(table);
			txtEditor.horizontalAlignment = SWT.LEFT;
			txtEditor.grabHorizontal = true;
			table.addMouseListener(new MouseListener() {

				public void mouseDoubleClick(MouseEvent event) {
					tableRowClickHandler(table, txtEditor, comboEditor, event);
				}

				public void mouseDown(MouseEvent event) {
					
					int selectionIndex = table.getSelectionIndex();
					if (selectionIndex != -1) {
						SlidingPortfolioShare selectedShare = modelControler.getShareInTab(portfolioCTabFolder1.getSelectionIndex(), selectionIndex);
						if (event.button == 1) {
							chartsComposite.highLight(selectionIndex, selectedShare.getStock(), true);
						} 
					} 
				}

				public void mouseUp(MouseEvent arg0) {
					//Nothing
				}

			});
			
			tableRowToolTip = null;
			table.addListener(SWT.MouseHover, new Listener() {
			
				public void handleEvent (Event event) {
					switch (event.type) {
						case SWT.MouseHover: {
							
							if (tableRowToolTip != null && !tableRowToolTip.isDisposed()) tableRowToolTip.dispose();
							
							TableItem item = table.getItem(new Point (event.x, event.y));
							TableItem[] items = table.getItems();
							
							if (item != null) {
								
								//Find the row index
								int idx = 0;
								for (TableItem tableItem : items) {
									if (tableItem.equals(item)) break;
									idx++;
								}
								
								//Show tip
								SlidingPortfolioShare selectedShare = modelControler.getShareInTab(portfolioCTabFolder1.getSelectionIndex(), idx);
								SimpleDateFormat dateFormat  = new SimpleDateFormat("dd-MMM-yyyy");
								NumberFormat numberFormat = new DecimalFormat("0.00#");
								String[] infoItems = new String[6];
								infoItems[0] = selectedShare.getFreindlyName();
								infoItems[1] = "Buy Date : " + dateFormat.format(selectedShare.getBuyDate());
								infoItems[2] = "Quantity : " + selectedShare.getQuantity();
								infoItems[3] = "Value : " + selectedShare.getTodaysValue() + " "+ selectedShare.getTransactionCurrency();
								infoItems[4] = "Gain : Unrealized " + numberFormat.format(selectedShare.getUnrealizedGain()) + " / Realized " + numberFormat.format(selectedShare.getRealizedGain());
								String shareInfo = infoItems[0] + "\n" + infoItems[1] + "\n" + infoItems[2] + "\n" + infoItems[3] + "\n" + infoItems[4];
								
								Rectangle tableBounds = table.getBounds();
								Rectangle tabsBounds = portfolioCTabFolder1.getBounds();
								Rectangle portfolioBounds = PortfolioComposite.this.getBounds();
								Rectangle shellBounds = PortfolioComposite.this.getShell().getBounds();
								int x = shellBounds.x + portfolioBounds.x + tabsBounds.x +  tableBounds.x + event.x;
								int y = shellBounds.y + portfolioBounds.y + tabsBounds.y +  tableBounds.y + event.y;
								//Point toDisplay = getShell().toDisplay(event.x,event.y);
								tableRowToolTip = showTooltip(getShell(), x, y, shareInfo);
								
							}
						}
					}
				}
			});
			table.addListener(SWT.MouseExit, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					if (tableRowToolTip != null && !tableRowToolTip.isDisposed()) tableRowToolTip.dispose();
				}
			});
		}
		
		updateTabItemsFromPortfolio(i, portfolio, tabInitObserver);
		
	}
	
	public Shell showTooltip(Shell parent, int x, int y, String txt) {
		Shell tooltip = new Shell(parent, SWT.TOOL | SWT.ON_TOP);
		tooltip.setLayout(new GridLayout());

		tooltip.setBackground(tooltip.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
		tooltip.setBackgroundMode(SWT.INHERIT_FORCE);

		Label lbContent = new Label(tooltip, SWT.NONE);
		lbContent.setForeground(tooltip.getDisplay().getSystemColor(SWT.COLOR_INFO_FOREGROUND));
		lbContent.setText(txt);
		
		Point lbContentSize = lbContent.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		int width = lbContentSize.x + 10;
		int height = lbContentSize.y + 10;

		tooltip.setBounds(x + 5, y + height, width, height);
		tooltip.setVisible(true);
		return tooltip;
	}

	/**
	 * @param table
	 * @param txtEditor
	 * @param comboEditor
	 * @param event
	 */
	private void tableRowClickHandler(final Table table, final TableEditor txtEditor, final TableEditor comboEditor, MouseEvent event) {
		//Where im I?
		Point pt = new Point(event.x, event.y);
		final TableItem item = table.getItem(pt); //This is the line
		if (item != null) {
			int column = -1;
			for (int i = 0, n = table.getColumnCount(); i < n; i++) {
				Rectangle rect = item.getBounds(i);
				if (rect.contains(pt)) {
					// This is the selected column
					column = i;
					break;
				}
			}
			//Now I know where I am.
			//I'll save old values before modification
			final ArrayList<String> anciennesVal = new ArrayList<String>();
			for (int cc = 0; cc < table.getColumnCount(); cc++) {
				anciennesVal.add(item.getText(cc));
			}
			//Saved
			//Now If I am in column ...
			if (column == Titles.Monitor.ordinal()) { //Monitor level
				// Create the drop down and add data to it
				final CCombo combo = new CCombo(table, SWT.READ_ONLY);
				for (int j = 0, n = MonitorLevel.values().length; j < n; j++) {
					combo.add(MonitorLevel.values()[j].getMonitorLevel());
				}
				// Select the previously selected item from the cell
				combo.select(combo.indexOf(item.getText(column)));
				comboEditor.minimumWidth = table.getColumn(column).getWidth();
				// Add a listener to set the selected item back into the cell
				final int col = column;
				combo.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent event) {
						item.setText(col, combo.getText());
						item.setFont(MainGui.CONTENTFONT);
						//Update portfolio
						int tabSelectionIndex = portfolioCTabFolder1.getSelectionIndex();
						int itemSelectionIndex = table.getSelectionIndex();
						PortfolioShare pstmp = modelControler.getShareInTab(tabSelectionIndex, itemSelectionIndex);
						pstmp.setMonitorLevel(MonitorLevel.valueOfString(combo.getText()));
						// They selected an item; end the editing session
						combo.dispose();
					}
				});
				combo.addFocusListener(new FocusListener() {
					public void focusGained(FocusEvent arg0) {
						// TODO Auto-generated method stub
					}

					public void focusLost(FocusEvent arg0) {
						combo.dispose();
					}
				});
				// Set the focus on the dropdown and set into the editor
				comboEditor.setEditor(combo, item, column);
				combo.setFocus();
			} else if (column != Titles.Monitor.ordinal()) { //Not Monitored Level Combo column
				final int col = column;
				final Text text = new Text(table, SWT.NONE);
				Listener textListener = new Listener() {
					public void handleEvent(final Event e) {
						switch (e.type) {
						case SWT.FocusOut:
							item.setText(col, text.getText());
							item.setFont(MainGui.CONTENTFONT);
							text.dispose();
							break;
						case SWT.Traverse:
							switch (e.detail) {
							case SWT.TRAVERSE_RETURN:
								item.setText(col, text.getText());
								item.setFont(MainGui.CONTENTFONT);
								columnEditManagment(anciennesVal, item, table.getSelectionIndex(), col);
								// FALL THROUGH
							case SWT.TRAVERSE_ESCAPE:
								text.dispose();
								e.doit = false;
							}
							break;
						}
					}
				};
				text.addListener(SWT.FocusOut, textListener);
				text.addListener(SWT.Traverse, textListener);
				txtEditor.setEditor(text, item, column);
				text.setText(item.getText(column));
				text.setFont(MainGui.CONTENTFONT);
				text.selectAll();
				text.setFocus();
				return;
			}
		}
	}


	/**
	 * Gestion des champs.
	 * 
	 * @param anciennesVal the anciennes val
	 * @param tableItem the ti
	 * @param columnIndex the i
	 * 
	 * @author Guillaume Thoreton
	 * @param rowIdx 
	 */
	private void columnEditManagment(ArrayList<String> anciennesVal, TableItem tableItem, int rowIdx, int columnIndex) {
		ErrorDialog inst;
		TransactionPriceDialog selectPriceDialog;
		
		if (slidingEndAnchor.getSelection() || slidingStartAnchor.getSelection()) {
			inst = new ErrorDialog(this.getShell(), SWT.NULL, "Please untick the sliding anchors before changing the porfolio records.", null);
			inst.open();
			return;
		}

		LOGGER.debug("Tables Items :" + tableItem.getText(0) + ";" + tableItem.getText(1) + ";" + tableItem.getText(2) + ";" + tableItem.getText(3));
		LOGGER.debug("Old Tables Items :" + anciennesVal.get(0) + ";" + anciennesVal.get(1) + ";" + anciennesVal.get(2) + ";"+ anciennesVal.get(3));
		int tabIdx = portfolioCTabFolder1.getSelectionIndex();
		try {
			if (tableItem.getText(Titles.CashIn.ordinal()).equals("") || tableItem.getText(Titles.CashOut.ordinal()).equals("")) {
				throw new Exception("All editable fields must be filled!");
			}
			
			NumberFormat numberFormat = NumberFormat.getNumberInstance();
			numberFormat.setMinimumFractionDigits(2);
			numberFormat.setMaximumFractionDigits(2);
			
			BigDecimal cashIn = new BigDecimal(numberFormat.parse(tableItem.getText(Titles.CashIn.ordinal())).doubleValue());
			BigDecimal cashOut = new BigDecimal(numberFormat.parse(tableItem.getText(Titles.CashOut.ordinal())).doubleValue());
	
			SlidingPortfolioShare pstmp = modelControler.getShareInTab(tabIdx, rowIdx);
			Titles titlesColumnValue = Titles.values()[columnIndex];
			BigDecimal transactionPrice = pstmp.getTodaysCloseQuotation();
			switch (titlesColumnValue) { //TODO ??re factor the modifs behavior in Portfolio Object it self??
			case CashIn: //Cash in
			{
					BigDecimal proposedQuantity = cashIn.subtract(pstmp.getCashin()).divide(transactionPrice,5,BigDecimal.ROUND_DOWN);
					Transaction transaction = 
							new Transaction(
									pstmp.getCashin(),pstmp.getCashout(),
									proposedQuantity,
									transactionPrice,
									TransactionType.AIN, new Date());

					selectPriceDialog = new TransactionPriceDialog(this.getShell(),transaction);
					selectPriceDialog.open();

					applyTransaction(tableItem, tabIdx, rowIdx, pstmp, transaction, selectPriceDialog.getOk(), selectPriceDialog.getReset());

					break;
			}
			case CashOut: //Cash out
			{
					BigDecimal proposedQuantity = cashOut.subtract(pstmp.getCashout()).divide(transactionPrice,5,BigDecimal.ROUND_DOWN).min(pstmp.getQuantity());
					transactionPrice = cashOut.divide(proposedQuantity,10,BigDecimal.ROUND_DOWN);
					Transaction transaction = 
							new Transaction(
									pstmp.getCashin(), pstmp.getCashout(),
									proposedQuantity,
									transactionPrice,
									TransactionType.AOUT, new Date());

					selectPriceDialog = new TransactionPriceDialog(this.getShell(),transaction);
					selectPriceDialog.open();

					applyTransaction(tableItem, tabIdx, rowIdx, pstmp, transaction, selectPriceDialog.getOk(), selectPriceDialog.getReset());

					break;
			}
				
			default: //Not modifiable
				inst = new ErrorDialog(this.getShell(), SWT.NULL, "Column is immutable.", null);
				inst.open();
				tableItem.setText(columnIndex, anciennesVal.get(columnIndex));
				tableItem.setFont(MainGui.CONTENTFONT);
				break;
			}
		} catch (NumberFormatException e) {
			LOGGER.warn(e, e);
			inst = new ErrorDialog(this.getShell(), SWT.NULL, "Both cash in and quotation must be floats", null);
			inst.open();
			tableItem.setText(columnIndex, anciennesVal.get(columnIndex));
			tableItem.setFont(MainGui.CONTENTFONT);
		} catch (ParseException e) {
			LOGGER.warn(e, e);
			inst = new ErrorDialog(this.getShell(), SWT.NULL, "Invalid date please enter something like : dd-MM-yyyy", null);
			inst.open();
			tableItem.setText(columnIndex, anciennesVal.get(columnIndex));
			tableItem.setFont(MainGui.CONTENTFONT);
		} catch (Exception e) {
			LOGGER.warn(e, e);
			inst = new ErrorDialog(this.getShell(), SWT.NULL,(e.getMessage() != null)?e.getMessage():e.toString(), null);
			inst.open();
			tableItem.setText(columnIndex, anciennesVal.get(columnIndex));
			tableItem.setFont(MainGui.CONTENTFONT);
		}
		
		Table ttomod = (Table) cTabItem[tabIdx].getData();
		for (int j = 0; j < Titles.values().length; j++) {
			ttomod.getColumn(j).pack();
		}

		refreshPortfolioTotalsInfos(tabIdx);
	}



	/**
	 * @param tableItem
	 * @param tabIdx 
	 * @param rowIdx 
	 * @param transaction
	 * @param pstmp
	 * @throws InvalidQuantityException 
	 */
	//TODO create an add, remove update method (all in one) in Portfolio hierarchy
	private void applyTransaction(TableItem tableItem, int tabIdx, int rowIdx, SlidingPortfolioShare portfolioShare, Transaction transaction, Boolean proceed, Boolean reset) 
																																						throws InvalidQuantityException {
		if (proceed) {

			Portfolio portfolio =  modelControler.getPortfolio(tabIdx);
		
			if (!reset) {
				//Update or remove existing
				portfolio.removeOrUpdateShare(portfolioShare, transaction.getQuantity(), transaction.getDate(), transaction.getTransactionPrice(), transaction.getModtype());
				
				if (portfolioShare.getQuantity().compareTo(BigDecimal.ZERO) == 0) {
					
					Table ttomod = (Table) cTabItem[tabIdx].getData();
					ttomod.remove(rowIdx);
					modelControler.removeShareFromTab(tabIdx, rowIdx);
					
					addShareForMonitoring(portfolioShare.getStock());
					
				} else {
						updateTableItem(tableItem, portfolioShare);
				}
				
			} else {
				
				Table ttomod = (Table) cTabItem[tabIdx].getData();
				
				//Remove existing
				portfolio.rawRemoveShare(portfolioShare);	
				ttomod.remove(rowIdx);
				modelControler.removeShareFromTab(tabIdx, rowIdx);
				
				//Then Add as new
				if (transaction.getQuantity().compareTo(BigDecimal.ZERO) > 0) {
					
					PortfolioShare replacementPS = portfolio.addOrUpdateShare(
							portfolioShare.getStock(), transaction.getQuantity(), EventSignalConfig.getNewDate(), 
							transaction.getTransactionPrice(), portfolioShare.getMonitorLevel(), portfolioShare.getTransactionCurrency(),
							TransactionType.AIN);
					
					
					SlidingPortfolioShare replacememntSlidingPS = buildSlidingPortfolioShare(replacementPS);
					modelControler.addPortfolioShareToTab(tabIdx, replacememntSlidingPS);
					
					int tabSize = modelControler.tabSize(tabIdx);
					updateTableItem(new TableItem(ttomod, SWT.DIALOG_TRIM, tabSize), replacememntSlidingPS);
					refreshChartData(portfolioCTabFolder1.getSelectionIndex(), true);
				}
				
			}
			
		} else {
			updateTableItem(tableItem, portfolioShare);
		}
					
	}



	/**
	 * @param currentPortfolio
	 */
	private void refreshPortfolioTotalsInfos(Integer currentPortfolioTab) {
		
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setMinimumFractionDigits(2);
		numberFormat.setMaximumFractionDigits(2);
		
		NumberFormat percentFormat = NumberFormat.getPercentInstance();
		percentFormat.setMaximumFractionDigits(2);
		percentFormat.setMinimumFractionDigits(2);
		
		if (currentPortfolioTab != -1) {
		
			Portfolio currentPortfolio = modelControler.getPortfolio(currentPortfolioTab);

			Date currentDate;
			if (slidingEndAnchor.getSelection()) {
				currentDate = chartsComposite.getSlidingEndDate();
			} else {
				currentDate = EventSignalConfig.getNewDate();
			}

			gain.setText(numberFormat.format(currentPortfolio.getGain(EventSignalConfig.getNewDate())));
			
			unrealGain.setText(numberFormat.format(currentPortfolio.getGainAmountUnrealizedForDate(currentDate)));
			unrealProfit.setText(percentFormat.format(currentPortfolio.getProfitUnrealizedForDate(currentDate)));
			value.setText(numberFormat.format(currentPortfolio.getValueForDate(currentDate)));


			String totalInAmountEver = (currentPortfolio.getTotalInAmountEver() != null)? numberFormat.format(currentPortfolio.getTotalInAmountEver()) : "NaN";
			amountIn.setText(totalInAmountEver);
			String totalOutAmountEver = (currentPortfolio.getTotalOutAmountEver() != null)? numberFormat.format(currentPortfolio.getTotalOutAmountEver()) : "NaN";
			amountOut.setText(totalOutAmountEver);
			
			Currency portfolioCurrency = currentPortfolio.inferPortfolioCurrency();
			currency.setText(portfolioCurrency.toString());
					
		}
		
		totalsGroup.pack();
		
	}


	/**
	 * @param ti
	 * @param quantity
	 * @param cashIn
	 * @param cashOut
	 * @param buyDate
	 */
	private void updateTableItem(TableItem ti, SlidingPortfolioShare portfolioShare) {
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setMinimumFractionDigits(2);
		numberFormat.setMaximumFractionDigits(2);
		NumberFormat percentFormat = NumberFormat.getPercentInstance();
		percentFormat.setMaximumFractionDigits(2);
		percentFormat.setMinimumFractionDigits(2);
		ti.setFont(smallFont);
		ti.setText(Titles.Name.ordinal(), portfolioShare.getName());
		ti.setText(Titles.CashIn.ordinal(), numberFormat.format(portfolioShare.getCashin()));
		ti.setText(Titles.CashOut.ordinal(), numberFormat.format(portfolioShare.getCashout()));
		ti.setText(Titles.Close.ordinal(), numberFormat.format(portfolioShare.getTodaysCloseQuotation()));
		ti.setText(Titles.BuyPrc.ordinal(), numberFormat.format(portfolioShare.getAvgBuyPrice()));
		ti.setText(Titles.UrProf.ordinal(), percentFormat.format(portfolioShare.getUnrealizedProfit()));
		InOutWeighted weightedInvested = portfolioShare.getWeightedInvested();
		ti.setText(Titles.WCashIn.ordinal(), numberFormat.format(weightedInvested.getIn()));
		ti.setText(Titles.WCashOut.ordinal(), numberFormat.format(weightedInvested.getOut()));
		ti.setText(Titles.WUrProf.ordinal(), percentFormat.format(portfolioShare.getWeightedUnrealizedProfit()));
		ti.setText(Titles.ZWProfPrc.ordinal(), numberFormat.format(portfolioShare.getPriceForZeroWeightedProfit()));
		ti.setText(Titles.Monitor.ordinal(), portfolioShare.getMonitorLevel().getMonitorLevel());
	
	}

	/**
	 * Sort column.
	 * 
	 * @param colStr the col str
	 * 
	 * @author Guillaume Thoreton
	 */
	private void sortColumn(String colStr) {

		int tabindex = portfolioCTabFolder1.getSelectionIndex();
		if (tabindex == -1) return;
		
		List<SlidingPortfolioShare> list = modelControler.getShareListInTab(tabindex);
		
		Boolean sort = !cTabItem[tabindex].isDisposed() && cTabItem[tabindex].isShowing();
		
		Titles col = Titles.valueOfColumnName(colStr);
		
		if (sorteted != null && sorteted.equals(col)) {
			Collections.reverse(list);
		} else {
			switch (col) {
			case Name:
				Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
					public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
						int ret = o1.getName().trim().toLowerCase().compareTo(o2.getName().trim().toLowerCase());
						ret = (ret == 0)? o1.getStock().compareTo(o2.getStock()):ret;
						return ret;
					}						
				});
				break;
			case CashIn:
				Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
					public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
						int ret = o2.getCashin().compareTo(o1.getCashin());
						ret = (ret == 0)? o1.getStock().compareTo(o2.getStock()):ret;
						return ret;
					}						
				});
				break;
			case UrProf:
				Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
					public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
						int ret = o2.getUnrealizedProfit().compareTo(o1.getUnrealizedProfit());
						ret = (ret == 0)? o1.getStock().compareTo(o2.getStock()):ret;
						return ret;
					}						
				});
				break;
			case WUrProf:
				Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
					public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
						int ret = o2.getWeightedUnrealizedProfit().compareTo(o1.getWeightedUnrealizedProfit());
						ret = (ret == 0)? o1.getStock().compareTo(o2.getStock()):ret;
						return ret;
					}						
				});
				break;
			case ZWProfPrc :
				Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
					public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
						int ret = o2.getPriceForZeroWeightedProfit().compareTo(o1.getPriceForZeroWeightedProfit());
						ret = (ret == 0)? o1.getStock().compareTo(o2.getStock()):ret;
						return ret;
					}						
				});
				break;
			case CashOut:
				Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
					public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
						int ret = o2.getCashout().compareTo(o1.getCashout());
						ret = (ret == 0)? o1.getStock().compareTo(o2.getStock()):ret;
						return ret;
					}						
				});
				break;
			default:
				sort = false;
				break;
			}
		}

		if (sort) {
			sorteted = col;
			Table t  = (Table)  cTabItem[tabindex].getData();
			t.removeAll();
			updatePortfolioTabItems(tabindex, list, 0);
			refreshChartData(portfolioCTabFolder1.getSelectionIndex(), true);
		}

	}



	private ArrayList<SlidingPortfolioShare> buildSlidingPortfolioShareList(Collection<PortfolioShare> portfolioShares) {
		
		ArrayList<SlidingPortfolioShare> list = new ArrayList<SlidingPortfolioShare>();
		
		for (PortfolioShare portfolioShare : portfolioShares) {
			SlidingPortfolioShare slidingPS = buildSlidingPortfolioShare(portfolioShare);
			list.add(slidingPS);
			//startIdx++;
		}
		return list;
	}



	private SlidingPortfolioShare buildSlidingPortfolioShare(PortfolioShare portfolioShare) {
		//int colidx = startIdx % paints.length;
		//java.awt.Color paint = (java.awt.Color) paints[colidx];
		java.awt.Color paint = (java.awt.Color) paints[getNextColor()];
		Color psColor = new Color(getDisplay(),paint.getRed(),paint.getGreen(),paint.getBlue());
		SlidingPortfolioShare slidingPS = new SlidingPortfolioShare(portfolioShare, 
						chartsComposite.getSlidingStartDate(), chartsComposite.getSlidingEndDate(), 
						slidingStartAnchor.getSelection(), slidingEndAnchor.getSelection(),
						psColor);
		return slidingPS;
	}


	/**
	 * Portfollio validate button mouse down.
	 * 
	 * @param evt the evt
	 * 
	 * @author Guillaume Thoreton
	 */
	public void portfolioValidateButtonMouseDown(TypedEvent evt) {
		updatePortfolioDB();
	}

	/**
	 * Root shell closed.
	 * 
	 * @param evt the evt
	 * 
	 * @author Guillaume Thoreton
	 */
	private void rootShellClosed(Event evt) {
		LOGGER.debug("dialogShell.shellClosed, event="+evt);
		updatePortfolioDB();
	}


	/**
	 * Update portfolio list.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void updatePortfolioDB() {

		try {
			PortfolioMgr.getInstance().hibStorePortfolio();
		} catch (NumberFormatException e) {
			LOGGER.error("",e);
			ErrorDialog inst = new ErrorDialog(getShell(), SWT.NULL,"Wrong float value \n"+e, null);
			inst.open();
		} catch (RuntimeException e) {
			LOGGER.error("",e);
			ErrorDialog inst = new ErrorDialog(getShell(), SWT.NULL,"Error While updating data base \n"+e, null);
			inst.open();
		}

	}


	private void portfolioAddPortfolioButtonMouseDown(EventObject evt) {

		
		final ActionDialogForm actionDialog = new ActionDialogForm(getShell(), "Add", null, "Add a new portfolio");
		final Text newPortfolioText = new Text(actionDialog.getParent(), SWT.NONE | SWT.CENTER | SWT.BORDER);
		newPortfolioText.setText("Type in the name");
		newPortfolioText.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (newPortfolioText.getText().equals("Type in the name")) newPortfolioText.setText("");
			}
		});
		ActionDialogAction actionDialogAction = new ActionDialogAction() {
			@Override
			public void action(Button targetButton) {
				actionDialog.name = ((Text) actionDialog.control).getText();
			}
		};
		actionDialog.setControl(newPortfolioText);
		actionDialog.setAction(actionDialogAction);
		actionDialog.open();
		
		//TODO Ui currency
		if (actionDialog.getIsOk()) {
			Portfolio portfolio = new UserPortfolio(actionDialog.name, null);
			try {
	
				PortfolioMgr.getInstance().addPortfolio(portfolio);
				addNewPortfolioTab(portfolio);
	
			} catch (InvalidAlgorithmParameterException e) {
				LOGGER.debug(e);
				ErrorDialog inst = new ErrorDialog(this.getShell(), SWT.NULL,e.getMessage()+"\n"+e.toString(), null);
				inst.open();
			}
			
			refreshPortfolioTotalsInfos(portfolioCTabFolder1.getSelectionIndex());
		}
	}
	
	private int addNewPortfolioTab(Portfolio portfolio) throws InvalidAlgorithmParameterException {

		//Ui
		CTabItem[] cTabItemTmp = new CTabItem[cTabItem.length+1];
		System.arraycopy(cTabItem, 0, cTabItemTmp, 0, cTabItem.length);
				
		cTabItem = cTabItemTmp;

		addOneTab(cTabItem.length-1, portfolio, new CursorChangerObserver(1));
		cTabItem[cTabItem.length-1].getParent().setSelection(cTabItem.length-1);

		return cTabItem.length-1;
	}


	private void portfolioAddShareDialog(EventObject evt) {

		try {
			int tabi = portfolioCTabFolder1.getSelectionIndex();

			//open selection window
			Collection<PortfolioShare> pSharesInTab = modelControler.getPortfolio(tabi).getListShares().values();
			NewPortfolioItemDialog.showUI(pSharesInTab, getShell(), this);
			
		} catch (Exception e) {
			LOGGER.error(e,e);
			ErrorDialog inst = new ErrorDialog(this.getShell(), SWT.NULL,"Error adding share. \n"+e, null);
			inst.open();
		}
		
		refreshPortfolioTotalsInfos(portfolioCTabFolder1.getSelectionIndex());
	}
	
	
	class AddShareRunnable extends Observable implements Runnable {

		private Collection<Stock> selectedStocks;
		private MonitorLevel selectedMonitorLevel;
		private BigDecimal selectedQuantity;
		
		public AddShareRunnable(Collection<Stock> selectedStocks, MonitorLevel selectedMonitorLevel, BigDecimal selectedQuantity, Observer observer) {
			super();
			this.selectedStocks = selectedStocks;
			this.selectedMonitorLevel = selectedMonitorLevel;
			this.selectedQuantity = selectedQuantity;
			this.addObserver(observer);
		}

		@Override
		public void run() {

			synchronized (modelControler.tabbedPortfolioShares) {

				try {

					setChanged();
					notifyObservers(new ObserverMsg(null, ObsKey.INITMSG , new Integer(selectedStocks.size())));

					QuotationUpdate quotationUpdate = new QuotationUpdate();
					Set<Observer> obss = new HashSet<Observer>();
					obss.add(logComposite);
					quotationUpdate.addObservers(obss);
					try {
						quotationUpdate.getQuotesFor(selectedStocks);
					} catch (StockNotFoundException e) {
						selectedStocks.removeAll(e.getInvalidStocks());
						ErrorDialog dialog = new ErrorDialog(getShell(), SWT.NONE, "Stocks not found", e.getMessage());
						dialog.open();
					}

					int tabIdx = portfolioCTabFolder1.getSelectionIndex();
					Collection<PortfolioShare> listOfNewSharesModel = addListOfShareToModel(tabIdx, selectedMonitorLevel, selectedQuantity, selectedStocks);
					for (PortfolioShare portfolioShare : listOfNewSharesModel) {
					
						SlidingPortfolioShare slidingPS = buildSlidingPortfolioShare(portfolioShare);
						modelControler.addOrUpdatePortfolioShareToTab(tabIdx, slidingPS);

					}

				} catch (Exception e) {
					LOGGER.error(e,e);
					ErrorDialog inst = new ErrorDialog(getShell(), SWT.NULL,"Error adding share. \n"+e, null);
					inst.open();
				} finally {
					setChanged();
					notifyObservers(new ObserverMsg(null, ObsKey.DONE));
				}

			}
		}
	}



	public void addShares(Set<Stock> selectedStocks, BigDecimal selectedQuantity, MonitorLevel selectedMonitorLevel) {

		LOGGER.info("adding Shares : "+selectedStocks);
		logComposite.initRefresh(this);
		getDisplay().syncExec(new AddShareRunnable(selectedStocks, selectedMonitorLevel, selectedQuantity, logComposite));
		
		getDisplay().asyncExec(new Runnable() {
			public void run() {
				endRefreshAction(new ArrayList<Exception>());
			}

		});

	}



	/**
	 * @param tabi
	 * @param portfolio 
	 * @param pItemd
	 * @param listStock
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidAlgorithmParameterException 
	 */
	private Collection<PortfolioShare> addListOfShareToModel(int tabi, MonitorLevel monitorLevel, BigDecimal quantity, Collection<Stock> listStock) throws InvalidAlgorithmParameterException {

		Set<PortfolioShare> listOfBoughtShares = new HashSet<PortfolioShare>();
		
		for (Stock stock : listStock) {
 
			PortfolioShare portfolioShare;
			try {
				Portfolio portfolio = modelControler.getPortfolio(tabi);
				portfolioShare = portfolio.addOrUpdateShareForQuantity(stock, quantity, EventSignalConfig.getNewDate(), monitorLevel, stock.getMarketValuation().getCurrency());
				listOfBoughtShares.add(portfolioShare);
				
			} catch (InvalidAlgorithmParameterException e) {
				String message = "No quotations for " + stock;
				LOGGER.warn(message, e);
				ErrorDialog inst = new ErrorDialog(this.getShell(), SWT.NULL, message+"\n"+e.getMessage()+"\n"+e.toString(), null);
				inst.open();
				
			} catch (InvalidQuantityException e) {
				String message = "Wrong quantity for " + stock;
				LOGGER.warn(message, e);
				ErrorDialog inst = new ErrorDialog(this.getShell(), SWT.NULL, message+"\n"+e.getMessage()+"\n"+e.toString(), null);
				inst.open();
				
			}

		}

		return listOfBoughtShares;

	}

	/**
	 * Adds the list of shares.
	 * 
	 * @param tabId the tab id
	 * @param quantity the quantity
	 * @param ml the ml
	 * @param listPortfolioShare the list stock
	 * 
	 * @author Guillaume Thoreton
	 * @param startIdx 
	 */
	private void updatePortfolioTabItems(int tabId, List<SlidingPortfolioShare> listPortfolioShare, int startIdx) {
		
		Table ttomod = (Table) cTabItem[tabId].getData();
		
		//Update the table
		for (SlidingPortfolioShare slidingPS : listPortfolioShare) {
			if (!slidingPS.getSymbol().equals(Stock.MISSINGCODE)) {

				if (modelControler.getShareListInTab(tabId) != listPortfolioShare) {
					modelControler.addPortfolioShareToTab(tabId, slidingPS);
				}

				TableItem item = new TableItem(ttomod, SWT.NONE, startIdx);
				updateTableItem(item, slidingPS);
				
				startIdx ++;
			} else {
				LOGGER.warn("Share without a symbol properly set :( are not supported (yet?) : " + slidingPS.toString() + ". plz fix me :)");
			}
		}

		for (int j = 0; j < Titles.values().length; j++) {
			ttomod.getColumn(j).pack();
		}

		highLightSlidingCols();
	}

	/**
	 * Portfollio remove share button mouse down.
	 * 
	 * @param event the event
	 * 
	 * @author Guillaume Thoreton
	 * @throws InvalidQuantityException 
	 */
	private void portfollioRemoveShareButtonMouseDown(EventObject event) throws InvalidQuantityException {

		int tabi = portfolioCTabFolder1.getSelectionIndex();
		Table ttomod = (Table) cTabItem[tabi].getData();
		int rowIndex = ttomod.getSelectionIndex();

		if (rowIndex != -1) {
			RemoveConfirmationDialog rcD = new RemoveConfirmationDialog(this.getShell());
			rcD.open();

			if (!rcD.getCanceled()) {
				
				Boolean applyTransaction = rcD.getApply();
				PortfolioShare portfolioShare = modelControler.getShareInTab(tabi, rowIndex);
				if (applyTransaction) {
					Date currentDate = EventSignalConfig.getNewDate();
					modelControler.getPortfolio(tabi).removeOrUpdateShare(	portfolioShare, portfolioShare.getQuantity(), currentDate, 
																			portfolioShare.getCloseQuotationFor(currentDate), TransactionType.AOUT);
				} else {
					modelControler.getPortfolio(tabi).rawRemoveShare(portfolioShare);
				}
				
				ttomod.remove(rowIndex);
				modelControler.removeShareFromTab(tabi, rowIndex);
				
				addShareForMonitoring(portfolioShare.getStock());
				
				refreshPortfolioTotalsInfos(tabi);
			
			}
		}

	}

	
	/**
	 * Portfollio cancel all button mouse down.
	 * 
	 * @param evt the evt
	 * 
	 * @author Guillaume Thoreton
	 */
	public void portfollioCancelAllButtonMouseDown(TypedEvent evt) {

		PortfolioMgr.getInstance().cancelModifications();
		resetPortfolioTabs();
		refreshPortfolioTotalsInfos(portfolioCTabFolder1.getSelectionIndex());
	}



	/**
	 * 
	 */
	public void resetPortfolioTabs() {

		int index = portfolioCTabFolder1.getSelectionIndex();
		portfolioCTabFolder1.setSelection(-1);

		CTabItem[] tabItems  = portfolioCTabFolder1.getItems();
		for (int i = 0; i < tabItems.length; i++) {
			tabItems[i].dispose();
		}

		buildPortfoliosTabs();

		portfolioCTabFolder1.setSelection(index);

	}


	/**
	 * Portfolio delete portfolio button mouse down.
	 * 
	 * @param evt the evt
	 * 
	 * @author Guillaume Thoreton
	 */
	private void  portfolioRemovePortfolioButtonMouseDown(EventObject evt) {
		int tabindex = portfolioCTabFolder1.getSelectionIndex();
		if (tabindex != -1) {
			CTabItem tab = portfolioCTabFolder1.getSelection();
			removePortfolio(tabindex, tab);
		}

	}



	private void removePortfolio(int tabindex, CTabItem tab) {
		
		tab.dispose();
		for (int i = tabindex; i < cTabItem.length -1; i++) {
			cTabItem[i] = cTabItem[i+1];
		}
		CTabItem[] cTabItemTmp = new CTabItem[cTabItem.length-1];
		System.arraycopy(cTabItem, 0, cTabItemTmp, 0, cTabItem.length-1);
		cTabItem = cTabItemTmp;

		Portfolio portfolioToRm = modelControler.getPortfolio(tabindex);
		PortfolioMgr.getInstance().removePortfolio(portfolioToRm);

		modelControler.removePortfolio(tabindex);
		
	}

	private Integer getTabFor(String portfolioName) {

		for (int i=0; i < cTabItem.length;i++) {
			CTabItem tab = cTabItem[i];
			if (tab.getText().equals(portfolioName)) return i;
		}
		return null;
	}

	/**
	 * 
	 */
	public void loadGnucashAdvPortfolio() {
			
			String transactionFile[] = selectFilePaths("Premium Markets - Select gnucash transaction file.");
			GnuCashTransactionReportParser gnuCashTransactionReportParser = new GnuCashTransactionReportParser();
			
			try {
				if (transactionFile.length > 0) {
					gnuCashTransactionReportParser.parse(transactionFile[0]);
				} else {
					LOGGER.warn("No transaction file selected. Transactions won't be updated.");
				}
			} catch (Exception e) {
				PortfolioComposite.LOGGER.error("Can't read gnucash transaction file : ", e);
				ErrorDialog errorDialog = new ErrorDialog(getShell(), SWT.NULL, "Can't read gnucash transaction file : " + e, null);
				errorDialog.open();
			}
	
			String[] paths = selectFilePaths("Premium Markets - Select files containing gnucash advanced portfolio exports.");
			GnuCashAdvPortfolioParser gnuCashAdvPortfolioParser = new GnuCashAdvPortfolioParser(DataSource.getInstance().getShareDAO(), PortfolioMgr.getInstance().getCurrencyConverter());
	
			for (String path : paths) {
				String portfolioName = gnuCashAdvPortfolioParser.extractName(path.substring(path.lastIndexOf(File.separator)));
				this.processGnuCashPath(gnuCashAdvPortfolioParser, path, portfolioName);
			}
	
			notFoundGnuSharesHandling(gnuCashAdvPortfolioParser);
	}

	public void loadGnucashAdvPortfolioForAutoPortfolio() {
		throw new NotImplementedException();
	}



	/**
	 * @param gnuCashAdvPortfolioParser
	 */
	private void notFoundGnuSharesHandling(GnuCashAdvPortfolioParser gnuCashAdvPortfolioParser) {
		
		if (!gnuCashAdvPortfolioParser.getNotFoundStocks().isEmpty()) {
			String erreurMessage = "Warning : Couln't find the following in db.\nPlease update your share list.\n";
			String addMess = "";
			for (String emess : gnuCashAdvPortfolioParser.getNotFoundStocks()) {
				addMess = addMess.concat(emess).concat("\n");
			}
			ErrorDialog errorDialog = new ErrorDialog(getShell(),SWT.NULL, erreurMessage, addMess);
			errorDialog.open();
		}
	}

	/**
	 * @param portfolioComposite TODO
	 * @param path
	 * @param portfolioName, Portfolio newPortfolio 
	 */
	public void processGnuCashPath(GnuCashAdvPortfolioParser gnuCashAdvPortfolioParser, String path, String  newPortfolioName) {
		try {
			
			//Getting the oldy out of DB
			UserPortfolio oldPortfolio = null;
			try {
				oldPortfolio = (UserPortfolio) PortfolioMgr.getInstance().getPortfolio(newPortfolioName);
				PortfolioMgr.getInstance().removePortfolio(oldPortfolio);
				
			} catch (IllegalArgumentException e) {
				//Ignoring non existent old portfolio
			}
			
			//Parsing
			Portfolio gnucashPortfolio = gnuCashAdvPortfolioParser.parse(path, newPortfolioName, oldPortfolio);
			
			//Renewing oldy in UI or creating brand new one
			PortfolioMgr.getInstance().addPortfolio(gnucashPortfolio);	
			if (oldPortfolio != null) {
				replacePortfolioTab(gnucashPortfolio);
			} else {
				addNewPortfolioTab(gnucashPortfolio);
			}

		} catch (Exception e) {
			PortfolioComposite.LOGGER.error("Can't read gnucash report file : ", e);
			ErrorDialog errorDialog = new ErrorDialog(getShell(), SWT.NULL, "Can't read gnucash report file : " + e, null);
			errorDialog.open();
		}
	}


	public void replacePortfolioTab(Portfolio newPortfolio) {

		Integer tabIdx = this.getTabFor(newPortfolio.getName());
		
		((Table)  cTabItem[tabIdx].getData()).removeAll();
		modelControler.resetOnePortfolioModel(tabIdx, newPortfolio);
		
		updateTabItemsFromPortfolio(tabIdx, newPortfolio, new CursorChangerObserver(1));
	}



	/**
	 * @return
	 */
	private String[] selectFilePaths(String title) {
		
		String[] filterExtensions = {"*.html"};
		FileDialog fileDialog = new FileDialog(new Shell(getDisplay()), SWT.MULTI);
		fileDialog.setText(title);
		fileDialog.setFilterExtensions(filterExtensions);
		String selectedPath = fileDialog.open();
		String[] selectedFiles = fileDialog.getFileNames();
		String[] paths = new String[0];
		if (selectedFiles.length > 0) {
			paths = new String[selectedFiles.length];
			for (int i =0 ; i < selectedFiles.length; i++)
				paths[i] = new File(selectedPath).getParent() + File.separator + selectedFiles[i];
		}
		return paths;
	}

	public void slidingDateChange() {
		
		int si = portfolioCTabFolder1.getSelectionIndex();
		
		if (si != -1) {
			List<SlidingPortfolioShare> slidingPSs = modelControler.getShareListInTab(si);
			boolean isSlidingPrices = slidingEndAnchor.getSelection() || slidingStartAnchor.getSelection();
				
			if (slidingPSs.size() > 0) {
				
				try {
					this.getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
	
					Table ttomod = (Table) cTabItem[si].getData();
					for (int itemIdx = 0 ; itemIdx  < slidingPSs.size(); itemIdx ++) {
	
						SlidingPortfolioShare portfolioShare = slidingPSs.get(itemIdx);
						portfolioShare.setSlidingEnd(slidingEndAnchor.getSelection());
						portfolioShare.setSlidingStart(slidingStartAnchor.getSelection());
	
						if (isSlidingPrices) {
							portfolioShare.setStart(chartsComposite.getSlidingStartDate());
							portfolioShare.setEnd(chartsComposite.getSlidingEndDate());
							updateTableItem(ttomod.getItem(itemIdx), portfolioShare);
						}
	
					}
					if (isSlidingPrices) {
				
						for (int j = 0; j < Titles.values().length; j++) {
							ttomod.getColumn(j).pack();
						}
						refreshPortfolioTotalsInfos(si);
					}
					
				} finally {
					this.getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
				}
			}
		}
	}
	
	
	private void addShareForMonitoring(Stock removedStock) {
		
		AddMonitorDialog rcD = new AddMonitorDialog(this.getShell());
		rcD.open();

		if (!rcD.getCanceled()) {
			BigDecimal percentageFall = rcD.getPercentageFall();
			String portfolioName = rcD.getPortfolioName();
			
			try {
				
				int tabi = getTabFor(portfolioName);
				Collection<Stock> selectedStocks = Arrays.asList(new Stock[]{removedStock});
				Collection<PortfolioShare> portfolioShares = addListOfShareToModel(tabi, MonitorLevel.BEARISH, BigDecimal.ONE, selectedStocks);
			
				ArrayList<SlidingPortfolioShare> slidingPortfolioShareList = buildSlidingPortfolioShareList(portfolioShares);
				int tabSize = modelControler.tabSize(tabi);
				updatePortfolioTabItems(tabi, slidingPortfolioShareList, tabSize);
				
				for (SlidingPortfolioShare portfolioShare : slidingPortfolioShareList) {
					BigDecimal avgBuyPrice = portfolioShare.getAvgBuyPrice();
					BigDecimal fallingLimit = avgBuyPrice.subtract(avgBuyPrice.multiply(percentageFall.divide(new BigDecimal(100))));
					portfolioShare.addSimpleAlert(ThresholdType.DOWN, fallingLimit, "FALL UNDER POTENTIAL REENTRY POINT : "+percentageFall+"%");
				}

			} catch (Exception e) {
				LOGGER.error(e,e);
				ErrorDialog inst = new ErrorDialog(this.getShell(), SWT.NULL,"Error adding share. \n"+e, null);
				inst.open();
			}
		}
	}



	@Override
	public void endRefreshAction(List<Exception> exceptions) {
		try{
			logComposite.endJob(exceptions);
		} finally {
			getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}	
		
	}

	@Override
	public void initRefreshAction() {
		logComposite.initRefresh(this);
		getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
		
	}

	@Override
	public void refreshView(List<Exception> exceptions) {
		
		int tabindex = portfolioCTabFolder1.getSelectionIndex();
		if (tabindex == -1) return;
		List<SlidingPortfolioShare> list = modelControler.getShareListInTab(tabindex);
		
		Table t  = (Table) cTabItem[tabindex].getData();
		t.removeAll();
		updatePortfolioTabItems(tabindex, list, 0);
		refreshChartData(portfolioCTabFolder1.getSelectionIndex(), false);
		
		if (isVisible()) {
			for (Exception exception : exceptions) {
				if (exception instanceof QuotatationRefreshException) {
					ErrorDialog dialog = new ErrorDialog(getShell(), SWT.NONE, "Couldn't refresh quotations\n", exceptions.toString());
					exceptions.clear();
					dialog.open();
					break;
				}
			}
		}
	
	}

	@Override
	public Date getAnalysisStartDate() {
		return null;
	}

}
