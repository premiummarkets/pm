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


import java.awt.Paint;
import java.io.File;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.graphics.Color;
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
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.jfree.chart.ChartColor;

import com.finance.pms.CursorFactory;
import com.finance.pms.ErrorDialog;
import com.finance.pms.MainGui;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.alerts.ThresholdType;
import com.finance.pms.datasources.EventModel;
import com.finance.pms.datasources.RefreshAllEventStrategyEngine;
import com.finance.pms.datasources.RefreshMonitoredStrategyEngine;
import com.finance.pms.datasources.RefreshPortfolioStrategyEngine;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.files.GnuCashAdvPortfolioParser;
import com.finance.pms.datasources.files.GnuCashTransactionReportParser;
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



// TODO: Auto-generated Javadoc
/**
 * The Class PortfolioComposite.
 * 
 * @author Guillaume Thoreton
 */
public class PortfolioComposite extends Composite {

	/** The LOGGER. */
	public static MyLogger LOGGER = MyLogger.getLogger(PortfolioComposite.class);


	Paint[] paints = ChartColor.createDefaultPaintArray();
	
	private CTabFolder portfolioCTabFolder1;
	private CTabItem[] cTabItem;

	private Button addShareFromFilebutton;
	private Button portfolioRemovebutton;
	private Button portfolioDeletePortfoliobutton;
	private Button portfolioAddPortFoliobutton;
	private Text newPortfolioText;

	private Group portfolioBoutonsGroup;
	private Group portfolioInfosGroup;
	private Group totalsGroup;

	private final ChartsComposite chartsComposite;

	private Text gain;
	private Text unrealGain;
	private Text unrealProfit;
	private Text value;
	private Text amountIn;
	private Text amountOut;
	//private Text historicalGain;
	private Text currency;

	private Button slidingEndAnchor;
	private Button slidingStartAnchor;

	ModelController modelControler;

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
				
				ArrayList<SlidingPortfolioShare> listOfShares = buildSlidingPortfolioShareList(portfolio.getListShares().values(), 0);
				Collections.sort(listOfShares, new ShareComparator());

				updatePortfolioTabItems(tabIdx, listOfShares, 0);
				refreshChartData(tabIdx);
				
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
		Name ("Stock name."), BuyPrc ("Avg buy price."), ZWProfPrc ("Minimum sell price for no loss including inflation."), Close("Last close price."),
		UrProf ("Unrealized profit percentage."), WUrProf ("Unrealized profit percentage including inflation."), 
		UrGain ("Unrealized profit amount."), RGain("Realized profit amount."), CashIn("Amount of cash in."), CashOut("Amount of cash out."),
		Value("Line value for last close price."), Quant ("Quantity."), 
		WCashIn("Inflation weighted cash in."), WCashOut("Inflation weighted cash out."),
		Symbol("Stock symbol."), Isin ("Stock isin."), Start ("Buy date"), Monitor("Premium Market monitor level."),
		Currency("Transcation currency");
		
		String toolTip;
		
		Titles(String toolTip) {
			this.toolTip = toolTip;
		}
		
		public String getToolTip() {
			return toolTip;
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
	public PortfolioComposite(Composite parent, ChartsComposite chartsComposite, int style) {
		super(parent, style);
		
		this.chartsComposite = chartsComposite;
		this.chartsComposite.setComposite(this);
		
		this.addListener(SWT.Hide, new Listener() {

			public void handleEvent(Event arg0) {
				rootShellClosed(arg0);
			}	

		});
		
		this.modelControler = new ModelController();

		initGUI();
	}

	/**
	 * Inits the gui.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void initGUI() {
		try {
			
			GridLayout compositeLayout = new GridLayout();
			compositeLayout.numColumns = 2;
			this.setLayout(compositeLayout);

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
						if (!cTabItem[si].isDisposed()) refreshChartData(si);
						highLightSlidingCols();
						
						//portfolioCTabFolder1.getParent().layout();
						//portfolioCTabFolder1.getParent().pack();
						//portfolioBoutonsGroup.pack();
						//portfolioInfosGroup.pack();
						
					}

				});

			}
			{
				{
					portfolioBoutonsGroup = new Group(this, SWT.NONE);
					GridLayout portfolioBoutonsGroupLayout = new GridLayout();
					portfolioBoutonsGroupLayout.numColumns = 2;
					portfolioBoutonsGroup.setLayout(portfolioBoutonsGroupLayout);
					GridData portfolioBoutonsGroupLData = new GridData(GridData.FILL_BOTH);
					portfolioBoutonsGroup.setLayoutData(portfolioBoutonsGroupLData);
					portfolioBoutonsGroup.setText("Portfolios managment : ");
					portfolioBoutonsGroup.setFont(MainGui.DEFAULTFONT);
					portfolioBoutonsGroup.setBackground(new Color(getDisplay(),204,204,255));

					{
						portfolioDeletePortfoliobutton = new Button(portfolioBoutonsGroup, SWT.PUSH | SWT.CENTER);
						GridData portfolioDeletePortfoliobuttonData = new GridData(GridData.FILL_HORIZONTAL);
						portfolioDeletePortfoliobuttonData.horizontalSpan=2;
						portfolioDeletePortfoliobutton.setLayoutData(portfolioDeletePortfoliobuttonData);
						portfolioDeletePortfoliobutton.setText("Remove portfolio");
						portfolioDeletePortfoliobutton.setFont(MainGui.DEFAULTFONT);
						portfolioDeletePortfoliobutton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseDown(MouseEvent evt) {
								portfolioRemovePortfolioButtonMouseDown(evt);
								refreshChartData(portfolioCTabFolder1.getSelectionIndex());
							}
						});
					}
					{
						portfolioAddPortFoliobutton = new Button(portfolioBoutonsGroup, SWT.PUSH | SWT.CENTER);
						portfolioAddPortFoliobutton.setText("Add new portfolio");
						portfolioAddPortFoliobutton.setFont(MainGui.DEFAULTFONT);
						portfolioAddPortFoliobutton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseDown(MouseEvent evt) {
								portfolioAddPortfolioButtonMouseDown(evt);
								refreshChartData(portfolioCTabFolder1.getSelectionIndex());
							}
						});
					}
					{
						newPortfolioText = new Text(portfolioBoutonsGroup, SWT.CENTER);
						newPortfolioText.setText("PortfolioName");
						newPortfolioText.setFont(MainGui.DEFAULTFONT);
					}
					{
						addShareFromFilebutton = new Button(portfolioBoutonsGroup, SWT.PUSH | SWT.CENTER);
						GridData portfolioAddbuttonData = new GridData(GridData.FILL_HORIZONTAL);
						portfolioAddbuttonData.horizontalSpan = 2;
						addShareFromFilebutton.setLayoutData(portfolioAddbuttonData);
						addShareFromFilebutton.setText("New share ...");
						addShareFromFilebutton.setFont(MainGui.DEFAULTFONT);
						addShareFromFilebutton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseDown(MouseEvent evt) {
								portfolioBoutonsGroup.getParent().getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
								try {
								portfolioAddShareButtonMouseDown(evt);
								refreshChartData(portfolioCTabFolder1.getSelectionIndex());
								} finally {
									portfolioBoutonsGroup.getParent().getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
								}
							}
						});
					}
					{
						portfolioRemovebutton = new Button(portfolioBoutonsGroup, SWT.PUSH | SWT.CENTER);
						GridData portfolioRemovebuttonData = new GridData(GridData.FILL_HORIZONTAL);
						portfolioRemovebuttonData.horizontalSpan=2;
						portfolioRemovebutton.setLayoutData(portfolioRemovebuttonData);
						portfolioRemovebutton.setText("Remove share");
						portfolioRemovebutton.setFont(MainGui.DEFAULTFONT);
						portfolioRemovebutton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseDown(MouseEvent evt) {
								try {
									portfollioRemoveShareButtonMouseDown(evt);
								} catch (InvalidQuantityException e) {
									LOGGER.warn(e,e);
									ErrorDialog errorDialog = new ErrorDialog(getShell(),SWT.NULL,e.getMessage(), null);
									errorDialog.open();
								}
								refreshChartData(portfolioCTabFolder1.getSelectionIndex());
							}
						});
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
						Text text0 = new Text(portfolioInfosGroup, SWT.RIGHT);
						text0.setText("Portfolio currency : ");
						text0.setToolTipText("Currency used for the totals");
						text0.setFont(MainGui.DEFAULTFONT);
						text0.setBackground(new Color(getDisplay(),204,204,255));
						currency = new Text(portfolioInfosGroup, SWT.RIGHT);
						currency.setEditable(false);
						currency.setLayoutData(portfolioInfoItemsData);
						currency.setText("");
						currency.setFont(MainGui.CONTENTFONT);
					}
					{
						GridData portfolioInfoItemsData = new GridData(GridData.FILL_HORIZONTAL);
						Text text0 = new Text(portfolioInfosGroup, SWT.RIGHT);
						text0.setText("Current real Gain : ");
						text0.setToolTipText("Sum of sell transactions minus sum of buy transactions for the quantities sold. Sum(out) - Sum(buy price)\nNB : Transactions are stored for gnucash portfolios only. For other portfolios, this is equal to Sum(out).");
						text0.setFont(MainGui.DEFAULTFONT);
						text0.setBackground(new Color(getDisplay(),204,204,255));
						gain = new Text(portfolioInfosGroup, SWT.RIGHT);
						gain.setEditable(false);
						gain.setLayoutData(portfolioInfoItemsData);
						gain.setText("");
						gain.setFont(MainGui.CONTENTFONT);
						Text text2 = new Text(portfolioInfosGroup, SWT.RIGHT);
						text2.setText("Unr. Gain since init : ");
						text2.setToolTipText("Actual value plus monies out minus monies in. (value + Sum(out))- Sum(in)");
						text2.setFont(MainGui.DEFAULTFONT);
						text2.setBackground(new Color(getDisplay(),204,204,255));
						unrealGain = new Text(portfolioInfosGroup, SWT.RIGHT);
						unrealGain.setEditable(false);
						unrealGain.setLayoutData(portfolioInfoItemsData);
						unrealGain.setText("");
						unrealGain.setFont(MainGui.CONTENTFONT);
						Text text2c = new Text(portfolioInfosGroup, SWT.RIGHT);
						text2c.setText("Unr. Profit %  since init : ");
						text2c.setToolTipText("((value + Sum(out))- Sum(in)) /  Sum(in) ");
						text2c.setFont(MainGui.DEFAULTFONT);
						text2c.setBackground(new Color(getDisplay(),204,204,255));
						unrealProfit = new Text(portfolioInfosGroup, SWT.RIGHT);
						unrealProfit.setEditable(false);
						unrealProfit.setLayoutData(portfolioInfoItemsData);
						unrealProfit.setText("");
						unrealProfit.setFont(MainGui.DEFAULTFONT);
						Text text3 = new Text(portfolioInfosGroup, SWT.RIGHT);
						text3.setText("Actual value : ");
						text3.setFont(MainGui.DEFAULTFONT);
						text3.setBackground(new Color(getDisplay(),204,204,255));
						value = new Text(portfolioInfosGroup, SWT.RIGHT);
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

						Text ainT = new Text(totalsGroup, SWT.LEFT);
						ainT.setText("In since init :");
						ainT.setFont(MainGui.DEFAULTFONT);
						ainT.setBackground(new Color(getDisplay(),204,204,255));
						amountIn = new Text(totalsGroup, SWT.LEFT);
						amountIn.setEditable(false);
						amountIn.setText("");
						amountIn.setFont(MainGui.CONTENTFONT);
						
						Text aoutT = new Text(totalsGroup, SWT.LEFT);
						aoutT.setText("Out since init :");
						aoutT.setFont(MainGui.DEFAULTFONT);
						aoutT.setBackground(new Color(getDisplay(),204,204,255));
						amountOut = new Text(totalsGroup, SWT.LEFT);
						amountOut.setEditable(false);
						amountOut.setText("");
						amountOut.setFont(MainGui.CONTENTFONT);
						
//						Text historicalGainLab = new Text(totalsGroup, SWT.LEFT);
//						historicalGainLab.setText("Historical gain :");
//						historicalGainLab.setFont(MainGui.DEFAULTFONT);
//						historicalGainLab.setBackground(new Color(getDisplay(),204,204,255));
//						historicalGain = new Text(totalsGroup, SWT.LEFT);
//						historicalGain.setText("");
//						historicalGain.setFont(MainGui.CONTENTFONT);
					}
					{
						slidingEndAnchor = new Button(portfolioInfosGroup, SWT.CHECK);
						GridData slidingChecksLayout = new GridData(GridData.FILL_HORIZONTAL);
						slidingChecksLayout.horizontalSpan = 2;
						slidingEndAnchor.setLayoutData(slidingChecksLayout);
						slidingEndAnchor.setText("Set current price as chart slider's end date");
						slidingEndAnchor.setFont(MainGui.DEFAULTFONT);
						slidingEndAnchor.setGrayed(true);
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
						slidingStartAnchor.setGrayed(true);
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
			
			this.layout();
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
				item.setBackground(Titles.Quant.ordinal(),bgColor);
				item.setForeground(Titles.Quant.ordinal(),fgColor);
				item.setBackground(Titles.Start.ordinal(),bgColor);
				item.setForeground(Titles.Start.ordinal(),fgColor);
				item.setBackground(Titles.WCashIn.ordinal(),bgColor);
				item.setForeground(Titles.WCashIn.ordinal(),fgColor);
				item.setBackground(Titles.WCashOut.ordinal(),bgColor);
				item.setForeground(Titles.WCashOut.ordinal(),fgColor);
				item.setBackground(Titles.WUrProf.ordinal(),bgColor);
				item.setForeground(Titles.WUrProf.ordinal(),fgColor);
				item.setBackground(Titles.ZWProfPrc.ordinal(),bgColor);
				item.setForeground(Titles.ZWProfPrc.ordinal(),fgColor);
				item.setBackground(Titles.RGain.ordinal(),bgColor);
				item.setForeground(Titles.RGain.ordinal(),fgColor);
				
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
	 */
	private void refreshChartData(int tabIdx) {
			try {
				this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
				refreshChartDataNoCursorChange(tabIdx);
			} finally {
				this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
			}
	}

	private void refreshChartDataNoCursorChange(int tabIdx) {
		if (portfolioCTabFolder1.getSelectionIndex() == tabIdx) {

			if (modelControler.nbTabs() > 0 && tabIdx != -1) {

				List<SlidingPortfolioShare> listShares = modelControler.getShareListInTab(tabIdx);
				chartsComposite.resetShareList(listShares);
				updatePortfolioColors(tabIdx,listShares);

			} else {
				chartsComposite.resetShareList(new ArrayList<SlidingPortfolioShare>());
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
		cTabItem[i].setFont(MainGui.DEFAULTFONT);
		
		{
			final Table table = new Table(portfolioCTabFolder1, SWT.BORDER | SWT.FULL_SELECTION);
			table.setFont(MainGui.DEFAULTFONT);
			table.setLinesVisible(false);
			table.setHeaderVisible(true);
			cTabItem[i].setControl(table);
			for (int j = 0; j < Titles.values().length; j++) {
				
				TableColumn column = new TableColumn(table, SWT.RIGHT);
				column.setText(Titles.values()[j].toString());
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

			final TableEditor txtEditor = new TableEditor(table);
			final TableEditor comboEditor = new TableEditor(table);
			txtEditor.horizontalAlignment = SWT.LEFT;
			txtEditor.grabHorizontal = true;
			table.addMouseListener(new MouseListener() {

				public void mouseDoubleClick(MouseEvent event) {
					tableRowClickHandler(table, txtEditor, comboEditor, event);
				}

				public void mouseDown(MouseEvent event) {

					LOGGER.trace("You clicked on : "+event.toString());	
					int selectionIndex = table.getSelectionIndex();
					if (selectionIndex != -1) {
						if (event.button == 1) {
							chartsComposite.highLight(selectionIndex);
						} else {
							SlidingPortfolioShare selectedShare = modelControler.getShareInTab(portfolioCTabFolder1.getSelectionIndex(), selectionIndex);
							AddAlertDialog addAlertDialog = new AddAlertDialog(getShell(), selectedShare);
							addAlertDialog.open(event.y, event.x);
						}
					} else {
						ErrorDialog errorDialog = new ErrorDialog(getShell(),SWT.NULL, " Please select the row you want to edit. ", null);
						errorDialog.open();
					}
				}

				public void mouseUp(MouseEvent arg0) {
					//Nothing
				}

			});
		}
		
		updateTabItemsFromPortfolio(i, portfolio, tabInitObserver);
		
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
			if (	tableItem.getText(Titles.Quant.ordinal()).equals("") || 
					tableItem.getText(Titles.CashIn.ordinal()).equals("") || 
					tableItem.getText(Titles.CashOut.ordinal()).equals("") || 
					tableItem.getText(Titles.Start.ordinal()).equals("")) {
				throw new Exception("All editable fields must be filled!");
			}
			
			NumberFormat numberFormat = NumberFormat.getNumberInstance();
			numberFormat.setMinimumFractionDigits(2);
			numberFormat.setMaximumFractionDigits(2);
			
			BigDecimal quantity = new BigDecimal(numberFormat.parse(tableItem.getText(Titles.Quant.ordinal())).doubleValue());
			BigDecimal cashIn = new BigDecimal(numberFormat.parse(tableItem.getText(Titles.CashIn.ordinal())).doubleValue());
			BigDecimal cashOut = new BigDecimal(numberFormat.parse(tableItem.getText(Titles.CashOut.ordinal())).doubleValue());
			Date buyDate = new SimpleDateFormat("dd-MMM-yyyy").parse(tableItem.getText(Titles.Start.ordinal()));

			SlidingPortfolioShare pstmp = modelControler.getShareInTab(tabIdx, rowIdx);
			Titles titlesColumnValue = Titles.values()[columnIndex];
			BigDecimal transactionPrice = pstmp.getTodaysCloseQuotation();
			switch (titlesColumnValue) { //TODO re factor the modifs behavior inPortfolio Object it self
			//Modifiable
			case Quant: //Quantity
			{
					BigDecimal quantDif = quantity.subtract(pstmp.getQuantity());
					TransactionType transactionType = TransactionType.NULL;
					if (quantDif.compareTo(BigDecimal.ZERO) < 0) {
						transactionType = TransactionType.AOUT;
					} else {
						transactionType = TransactionType.AIN;
					}
					Transaction transaction = new Transaction(pstmp.getCashin(),pstmp.getCashout(), quantDif.abs(), transactionPrice, transactionType, new Date());

					selectPriceDialog = new TransactionPriceDialog(this.portfolioCTabFolder1, transaction);
					selectPriceDialog.open();

					applyTransaction(tableItem, tabIdx, rowIdx, pstmp, transaction, selectPriceDialog.getOk(), selectPriceDialog.getReset());

					break;
			}
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
			case Start: //buy date
				pstmp.setBuyDate(buyDate);
				updateTableItem(tableItem, pstmp);
				break;
				
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

			//Portfolio portfolio = (Portfolio) portfolioShare.getPortfolio();
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
					
					int tabSize = modelControler.tabSize(tabIdx);
					SlidingPortfolioShare replacememntSlidingPS = buildSlidingPortfolioShare(tabSize, replacementPS);
					modelControler.addPortfolioShareToTab(tabIdx, replacememntSlidingPS);
					
//					tableItem = new TableItem(ttomod, SWT.DIALOG_TRIM, tabSize);
//					portfolioShare = replacememntSlidingPS;
					updateTableItem(new TableItem(ttomod, SWT.DIALOG_TRIM, tabSize), replacememntSlidingPS);
					refreshChartData(portfolioCTabFolder1.getSelectionIndex());
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
			
			Currency portfolioCurrency = currentPortfolio.inferPortfolioCurrency(); //currentPortfolio.getPortfolioCurrency();
			//if (portfolioCurrency == null) portfolioCurrency = Currency.NAN;
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
		ti.setFont(MainGui.CONTENTFONT);
		ti.setText(Titles.Symbol.ordinal(), portfolioShare.getSymbol());
		ti.setText(Titles.Name.ordinal(), portfolioShare.getName());
		ti.setText(Titles.Quant.ordinal(), numberFormat.format(portfolioShare.getQuantity()));
		ti.setText(Titles.CashIn.ordinal(), numberFormat.format(portfolioShare.getCashin()));
		ti.setText(Titles.CashOut.ordinal(), numberFormat.format(portfolioShare.getCashout()));
		ti.setText(Titles.Start.ordinal(), new SimpleDateFormat("dd-MMM-yyyy").format(portfolioShare.getBuyDate()));	
		ti.setText(Titles.Close.ordinal(), numberFormat.format(portfolioShare.getTodaysCloseQuotation()));
		ti.setText(Titles.Value.ordinal(), numberFormat.format(portfolioShare.getTodaysValue()));
		ti.setText(Titles.BuyPrc.ordinal(), numberFormat.format(portfolioShare.getAvgBuyPrice()));
		ti.setText(Titles.UrProf.ordinal(), percentFormat.format(portfolioShare.getUnrealizedProfit()));
		InOutWeighted weightedInvested = portfolioShare.getWeightedInvested();
		ti.setText(Titles.WCashIn.ordinal(), numberFormat.format(weightedInvested.getIn()));
		ti.setText(Titles.WCashOut.ordinal(), numberFormat.format(weightedInvested.getOut()));
		ti.setText(Titles.WUrProf.ordinal(), percentFormat.format(portfolioShare.getWeightedUnrealizedProfit()));
		ti.setText(Titles.ZWProfPrc.ordinal(), numberFormat.format(portfolioShare.getPriceForZeroWeightedProfit()));
		ti.setText(Titles.UrGain.ordinal(), numberFormat.format(portfolioShare.getUnrealizedGain()));
		ti.setText(Titles.RGain.ordinal(), numberFormat.format(portfolioShare.getRealizedGain()));
		ti.setText(Titles.Monitor.ordinal(), portfolioShare.getMonitorLevel().getMonitorLevel());
		ti.setText(Titles.Isin.ordinal(), portfolioShare.getIsin());
		ti.setText(Titles.Currency.ordinal(), portfolioShare.getTransactionCurrency().toString());
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
		Titles col = Titles.valueOf(colStr);
		switch (col) {
//		Symbol("Stock symbol."), Name ("Stock name."),  CashIn("Amount of cash in."), Start ("Buy date"), UrProf ("Unrealized profit percentage."), UrGain ("Unrealized profit amount."), RGain("Realized profit amount."), 
//		WUrProf ("Unrealized profit percentage including inflation."), ZWProfPrc ("Minimum sell price for no loss including inflation."), Isin ("Stock isin."),
		case Symbol:
			Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
				public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
					int ret = o1.getSymbol().trim().toLowerCase().compareTo(o2.getSymbol().trim().toLowerCase());
					ret = (ret == 0)? o1.getName().trim().toLowerCase().compareTo(o2.getName().trim().toLowerCase()):ret;
					return ret;
				}						
			});
			break;
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
		case Start:
			Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
				public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
					int ret = o1.getBuyDate().compareTo(o2.getBuyDate());
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
		case UrGain:
			Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
				public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
					int ret = o2.getUnrealizedGain().compareTo(o1.getUnrealizedGain());
					ret = (ret == 0)? o1.getStock().compareTo(o2.getStock()):ret;
					return ret;
				}						
			});
			break;
		case RGain:
			Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
				public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
					int ret = o2.getRealizedGain().compareTo(o1.getRealizedGain());
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
		case Isin:
			Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
				public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
					int ret = o1.getIsin().compareTo(o2.getIsin());
					ret = (ret == 0)? o1.getName().trim().toLowerCase().compareTo(o2.getName().trim().toLowerCase()):ret;
					return ret;
				}						
			});
			break;
//			CashOut("Amount of cash out."),
//			Value("Line value for last close price."), 
		case CashOut:
			Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
				public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
					int ret = o2.getCashout().compareTo(o1.getCashout());
					ret = (ret == 0)? o1.getStock().compareTo(o2.getStock()):ret;
					return ret;
				}						
			});
			break;
		case Value:
			Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
				public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
					int ret = o2.getTodaysValue().compareTo(o1.getTodaysValue());
					ret = (ret == 0)? o1.getStock().compareTo(o2.getStock()):ret;
					return ret;
				}						
			});
			break;
//			Currency();
		case Currency:
			Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
				public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
					int ret = o2.getTransactionCurrency().compareTo(o1.getTransactionCurrency());
					ret = (ret == 0)? o1.getStock().compareTo(o2.getStock()):ret;
					return ret;
				}						
			});
			break;
//			BuyPrc ("Buy price."), Close("Last close price."), Quant ("Quantity."), 
//			WCashIn("Inflation weighted cash in."), WCashOut("Inflation weighted cash out."),
//			Monitor("Premium Market monitor level.")
		default:
			sort = false;
			break;
		}

		if (sort) {
			Table t  = (Table)  cTabItem[tabindex].getData();
			t.removeAll();
			updatePortfolioTabItems(tabindex, list, 0);
			refreshChartData(portfolioCTabFolder1.getSelectionIndex());
		}

	}



	private ArrayList<SlidingPortfolioShare> buildSlidingPortfolioShareList(Collection<PortfolioShare> portfolioShares, int startIdx) {
		
		ArrayList<SlidingPortfolioShare> list = new ArrayList<SlidingPortfolioShare>();
		//int idx = 0;
		for (PortfolioShare portfolioShare : portfolioShares) {
			SlidingPortfolioShare slidingPS = buildSlidingPortfolioShare(startIdx, portfolioShare);
			list.add(slidingPS);
			startIdx++;
		}
		return list;
	}



	private SlidingPortfolioShare buildSlidingPortfolioShare(int startIdx, PortfolioShare portfolioShare) {
		java.awt.Color paint = (java.awt.Color) paints[startIdx];
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


	/**
	 * Portfollio add portfolio button mouse down.
	 * 
	 * @param evt the evt
	 * 
	 * @author Guillaume Thoreton
	 */
	private void portfolioAddPortfolioButtonMouseDown(MouseEvent evt) {

		String name = newPortfolioText.getText();
		
		//TODO Ui currency
		Portfolio portfolio = new UserPortfolio(name, null);
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



	/**
	 * @param portfolio
	 * @throws InvalidAlgorithmParameterException
	 */
	private int addNewPortfolioTab(Portfolio portfolio) throws InvalidAlgorithmParameterException {

		//Ui
		CTabItem[] cTabItemTmp = new CTabItem[cTabItem.length+1];
		System.arraycopy(cTabItem, 0, cTabItemTmp, 0, cTabItem.length);
				
		cTabItem = cTabItemTmp;

		addOneTab(cTabItem.length-1, portfolio, new CursorChangerObserver(1));
		cTabItem[cTabItem.length-1].getParent().setSelection(cTabItem.length-1);

		return cTabItem.length-1;
	}


	/**
	 * Portfollio add share button mouse down.
	 * 
	 * @param evt the evt
	 * 
	 * @author Guillaume Thoreton
	 */
	private void portfolioAddShareButtonMouseDown(MouseEvent evt) {

		try {
			int tabi = portfolioCTabFolder1.getSelectionIndex();

			//open selection window
			Collection<PortfolioShare> pSharesInTab = modelControler.getPortfolio(tabi).getListShares().values();
			NewPortfolioItemDialog pItemd = NewPortfolioItemDialog.showUI(pSharesInTab, getShell(), this);
			
			addShares(pItemd);

		} catch (Exception e) {
			LOGGER.error(e,e);
			ErrorDialog inst = new ErrorDialog(this.getShell(), SWT.NULL,"Error adding share. \n"+e, null);
			inst.open();
		}
		
		refreshPortfolioTotalsInfos(portfolioCTabFolder1.getSelectionIndex());
	}



	void addShares(NewPortfolioItemDialog pItemd) {

		int tabi = portfolioCTabFolder1.getSelectionIndex();

		try {
			List<Stock> selectedStocks = pItemd.getSelectedStocks();

			Collection<PortfolioShare> portfolioShares = addListOfShareToModel(tabi, pItemd.getSelectedMonitorLevel(), pItemd.getSelectedQuantity(), selectedStocks);
			int tabSize = modelControler.tabSize(tabi);
			updatePortfolioTabItems(tabi, buildSlidingPortfolioShareList(portfolioShares, tabSize), tabSize);
			refreshChartData(portfolioCTabFolder1.getSelectionIndex());

		} catch (InvalidAlgorithmParameterException e) {
			LOGGER.info(e,e);
			ErrorDialog inst = new ErrorDialog(this.getShell(), SWT.NULL,"Error adding share. \n"+e, null);
			inst.open();
		}

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
				portfolioShare = portfolio.addOrUpdateShareForQuantity(stock, quantity, EventSignalConfig.getNewDate(), monitorLevel, stock.getMarket().getCurrency());
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
	private void portfollioRemoveShareButtonMouseDown(MouseEvent event) throws InvalidQuantityException {

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
	private void  portfolioRemovePortfolioButtonMouseDown(MouseEvent evt) {
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



	/**
	 * @param newPortfolio
	 * @return
	 */
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
	
	private class ModelController {
		
		private final class ObserverImplementation implements Observer,  Comparable<Observer> {
			public void update(Observable o, Object arg) {
				
				ObserverMsg observerMsg = (ObserverMsg) arg;
				
				//if (arg != null && arg.equals("done")) {
				if (observerMsg != null && observerMsg.getKey().equals(ObserverMsg.ObsKey.DONE)) {
					
					getDisplay().syncExec(
							new Runnable() {
								public void run(){
									//resetPortfolioTabs();
									for (int tabIdx=0; tabIdx < tabbedPortfolios.size(); tabIdx++) {
										refreshChartData(tabIdx);
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
		
		
		public ModelController() {
			
			tabbedPortfolioShares = new ArrayList<List<SlidingPortfolioShare>>();
			tabbedPortfolios = new ArrayList<Portfolio>();

			Observer observer =  new ObserverImplementation();
			
			//Event and quotations refresh
			EventModel.getInstance(new RefreshAllEventStrategyEngine(), observer);
			monitoredStocksEventModel = EventModel.getInstance(new RefreshMonitoredStrategyEngine(), observer);
			portfolioStocksEventModel = EventModel.getInstance(new RefreshPortfolioStrategyEngine(), observer);
		}


		public void addPortfolioShareToTab(int tabId, SlidingPortfolioShare slidingPS) {
			tabbedPortfolioShares.get(tabId).add(slidingPS);
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
			//updateModels();
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
			
			monitoredStocksEventModel.setStateParams(monitored.toArray());
			portfolioStocksEventModel.setStateParams(portfolioStocks.toArray());
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
				
				int tabSize = modelControler.tabSize(tabi);
				ArrayList<SlidingPortfolioShare> slidingPortfolioShareList = buildSlidingPortfolioShareList(portfolioShares, tabSize);
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

}
