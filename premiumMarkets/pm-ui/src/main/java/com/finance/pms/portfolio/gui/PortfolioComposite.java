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
package com.finance.pms.portfolio.gui;


import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidAlgorithmParameterException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.persistence.NoResultException;

import org.apache.commons.lang.NotImplementedException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
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
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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

import com.finance.pms.ActionDialog;
import com.finance.pms.ActionDialogAction;
import com.finance.pms.CursorFactory;
import com.finance.pms.LogComposite;
import com.finance.pms.MainGui;
import com.finance.pms.PopupMenu;
import com.finance.pms.RefreshableView;
import com.finance.pms.SpringContext;
import com.finance.pms.TableToolTip;
import com.finance.pms.UserDialog;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.alerts.AlertOnEvent;
import com.finance.pms.datasources.EventModel;
import com.finance.pms.datasources.EventRefreshController;
import com.finance.pms.datasources.EventTaskQueue;
import com.finance.pms.datasources.InvalidEventRefreshTask;
import com.finance.pms.datasources.QuotatationRefreshException;
import com.finance.pms.datasources.RefreshAllEventStrategyEngine;
import com.finance.pms.datasources.RefreshChartHighlighted;
import com.finance.pms.datasources.RefreshMonitoredStrategyEngine;
import com.finance.pms.datasources.RefreshPortfolioStrategyEngine;
import com.finance.pms.datasources.TaskId;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.files.GnuCashAdvPortfolioParser;
import com.finance.pms.datasources.files.GnuCashTransactionReportParser;
import com.finance.pms.datasources.files.Transaction;
import com.finance.pms.datasources.files.TransactionElement;
import com.finance.pms.datasources.files.TransactionType;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.quotation.QuotationUpdate.QuotationUpdateException;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.ProvidersInflation;
import com.finance.pms.datasources.web.currency.CurrencyConverter;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.QuotationUnit.ORIGIN;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.portfolio.AlertsMgrDelegate;
import com.finance.pms.portfolio.InOutWeighted;
import com.finance.pms.portfolio.InvalidQuantityException;
import com.finance.pms.portfolio.MonitorLevel;
import com.finance.pms.portfolio.Portfolio;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.UserPortfolio;
import com.finance.pms.portfolio.gui.charts.ChartIndicatorDisplay;
import com.finance.pms.portfolio.gui.charts.ChartPerfDisplay;
import com.finance.pms.portfolio.gui.charts.ChartsComposite;
import com.finance.pms.threads.ConfigThreadLocal;
import com.finance.pms.threads.ObserverMsg;
import com.finance.pms.threads.ObserverMsg.ObsKey;


/**
 * The Class PortfolioComposite.
 * 
 * @author Guillaume Thoreton
 */
public class PortfolioComposite extends SashForm implements RefreshableView {


	public static MyLogger LOGGER = MyLogger.getLogger(PortfolioComposite.class);

	public static Paint[] PAINTS = ChartColor.createDefaultPaintArray();

	private Integer colorNum = 0;
	private int getNextColor() {
		synchronized (PAINTS) {
			return (++colorNum % PortfolioComposite.PAINTS.length);
		}
	}

	private CTabFolder portfolioCTabFolder;
	private CTabItem[] cTabItem;

	private final ChartsComposite chartsComposite;


	private MainGui mainGuiParent;

	public Group portfolioInfosGroup;
	private Table portfolioInfosTable;
	private Button slidingEndAnchor;
	private Button slidingStartAnchor;
	private Button isLatestTransactionsOnly;

	ModelController modelControler;

	private LogComposite logComposite;

	private Font smallFont;
	private Titles sorteted;
	private Shell tableRowToolTip;

	private NumberFormat moneysFormat;
	private NumberFormat percentFormat;
	private NumberFormat quantiesFormat;

	public final class ManualQuotationSelectionListener implements SelectionListener, ActionListener {

		private Text currency;

		@Override
		public void actionPerformed(ActionEvent event) {
			Point2D clickPoint = chartsComposite.getPointCoordinates(chartsComposite.getPanelClickPosition());
			Rectangle2D plotArea = chartsComposite.getPlotChartDimensions();
			long clickPointDate = chartsComposite.getMainChartWraper().point2DToTime(clickPoint, plotArea);
			final Date clickDate = DateFactory.midnithDate(new Date(clickPointDate));
			Runnable runnable = new Runnable() {
				public void run() {
					handleManualQuotation(clickDate);
				}
			};
			getDisplay().syncExec(runnable);
		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			handleManualQuotation(EventSignalConfig.getNewDate());
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			handleManualQuotation(EventSignalConfig.getNewDate());
		}

		private void handleManualQuotation(Date date) {

			final Integer tabIdx = selectedPortfolioIdx();
			final Table table = (Table) cTabItem[tabIdx].getData();
			final int itemIdx = table.getSelectionIndex();

			if (itemIdx != -1) {

				final SlidingPortfolioShare ss = modelControler.getSlidingShareInTab(tabIdx, itemIdx);
				final Stock stock = ss.getStock();

				final CurrencyConverter cC = PortfolioMgr.getInstance().getCurrencyConverter();
				final Currency stockCurrency = stock.getMarketValuation().getCurrency();
				final Currency trCurrency = ss.getTransactionCurrency();

				final ActionDialogForm actForm = new ActionDialogForm(getShell(), "Add", null, "Add a quotation for "+ss.getFriendlyName());
				final SimpleDateFormat displayDF = new SimpleDateFormat("yyyy-MM-dd");

				final Text warning = new Text(actForm.getParent(), SWT.WRAP);
				warning.setBackground(MainGui.pOPUP_BG);
				warning.setFont(MainGui.DEFAULTFONT);
				warning.setText("Fill in your values for " + stock.getFriendlyName()+"\n");

				Group insertManualGroup = new Group(actForm.getParent(), SWT.NONE);
				insertManualGroup.setLayout(new GridLayout(4, true));
				insertManualGroup.setBackground(MainGui.pOPUP_GRP);
				//Date
				Label dateLabel = new Label(insertManualGroup, SWT.NONE);
				dateLabel.setBackground(MainGui.pOPUP_GRP);
				dateLabel.setFont(MainGui.DEFAULTFONT);
				dateLabel.setText("Date");
				final Text dateTxt = new Text(insertManualGroup, SWT.NONE);
				dateTxt.setFont(MainGui.CONTENTFONT);
				dateTxt.setText(displayDF.format(date));
				dateTxt.addKeyListener(new KeyListener() {

					@Override
					public void keyReleased(KeyEvent e) {
						handleDateChange(e);
					}

					@Override
					public void keyPressed(KeyEvent e) {
						handleDateChange(e);
					}

					private void handleDateChange(KeyEvent evt) {
						if (evt.keyCode == SWT.CR || evt.keyCode == SWT.SPACE) {
							try {
								Date parseDate = parseDate(dateTxt.getText());
								updateConvertionRate(parseDate, cC, stockCurrency, trCurrency);
							} catch (ParseException e) {
								//
							}
						}
					}

				});
				//Volume
				Label volumeLabel = new Label(insertManualGroup, SWT.NONE);
				volumeLabel.setBackground(MainGui.pOPUP_GRP);
				volumeLabel.setFont(MainGui.DEFAULTFONT);
				volumeLabel.setText("Volume");
				final Text volumeTxt = new Text(insertManualGroup, SWT.NONE);
				volumeTxt.setFont(MainGui.CONTENTFONT);

				//Quotes
				Label openLabel = new Label(insertManualGroup, SWT.NONE);
				openLabel.setBackground(MainGui.pOPUP_GRP);
				openLabel.setFont(MainGui.DEFAULTFONT);
				openLabel.setText("Open");
				Label highLabel = new Label(insertManualGroup, SWT.NONE);
				highLabel.setBackground(MainGui.pOPUP_GRP);
				highLabel.setFont(MainGui.DEFAULTFONT);
				highLabel.setText("High");
				Label lowLabel = new Label(insertManualGroup, SWT.NONE);
				lowLabel.setBackground(MainGui.pOPUP_GRP);
				lowLabel.setFont(MainGui.DEFAULTFONT);
				lowLabel.setText("Low");
				Label closeLabel = new Label(insertManualGroup, SWT.NONE);
				closeLabel.setBackground(MainGui.pOPUP_GRP);
				closeLabel.setFont(MainGui.DEFAULTFONT);
				closeLabel.setText("Close");
				final Text openTxt = new Text(insertManualGroup, SWT.NONE);
				openTxt.setFont(MainGui.CONTENTFONT);
				final Text highTxt = new Text(insertManualGroup, SWT.NONE);
				highTxt.setFont(MainGui.CONTENTFONT);
				final Text lowTxt = new Text(insertManualGroup, SWT.NONE);
				lowTxt.setFont(MainGui.CONTENTFONT);
				final Text closeTxt = new Text(insertManualGroup, SWT.NONE);
				closeTxt.setFont(MainGui.CONTENTFONT);

				currency = new Text(actForm.getParent(), SWT.NONE);
				currency.setBackground(MainGui.pOPUP_BG);
				currency.setFont(MainGui.DEFAULTFONT);
				updateConvertionRate(date, cC, stockCurrency, trCurrency);

				final StringBuffer error = new StringBuffer();
				ActionDialogAction actionDialogAction = new ActionDialogAction() {

					@Override
					public void action() {

						String text = dateTxt.getText();
						try {
							actForm.values[0] = parseDate(text);
						} catch (ParseException e) {
							error.append("Invalid date "+text+"\n");
						}
						try {
							BigDecimal close = new BigDecimal(closeTxt.getText());
							actForm.values[4] = close;
							try {
								actForm.values[1] = (openTxt.getText().isEmpty())?close:new BigDecimal(openTxt.getText());
							} catch (NumberFormatException e) {
								error.append("Invalid open value "+openTxt.getText()+"\n");
							}
							try {
								actForm.values[3] = (highTxt.getText().isEmpty())?close:new BigDecimal(highTxt.getText());
							} catch (Exception e) {
								error.append("Invalid high value "+highTxt.getText()+"\n");
							}
							try {
								actForm.values[2] = (lowTxt.getText().isEmpty())?close:new BigDecimal(lowTxt.getText());
							} catch (Exception e) {
								error.append("Invalid low value "+lowTxt.getText()+"\n");
							}
						} catch (NumberFormatException e1) {
							error.append("Invalid close value "+closeTxt.getText()+"\n");
						}
						try {
							actForm.values[5] = new Long((volumeTxt.getText().isEmpty())?"0":volumeTxt.getText());
						} catch (Exception e) {
							error.append("Invalid volume value "+volumeTxt.getText()+"\n");
						}

						if (error.length() == 0) {
							try {

								stock.setOverrideUserQuotes(false);

								//Db and caches ...
								Date inDate = (Date)actForm.values[0];
								QuotationUnit quotationUnit = new QuotationUnit(
										stock, stockCurrency, inDate, 
										cC.convert(trCurrency, stockCurrency, (BigDecimal)actForm.values[1], inDate), 
										cC.convert(trCurrency, stockCurrency, (BigDecimal)actForm.values[2], inDate), 
										cC.convert(trCurrency, stockCurrency, (BigDecimal)actForm.values[3], inDate), 
										cC.convert(trCurrency, stockCurrency, (BigDecimal)actForm.values[4], inDate), 
										(Long)actForm.values[5], ORIGIN.USER);
								DataSource.getInstance().getShareDAO().saveOrUpdateQuotationUnit(quotationUnit);
								Quotations.refreshCaches(stock);

								//Ui ...
								tabUpdateTableItem(table.getSelection()[0], ss);
								refreshChartData();
								refreshPortfolioTotalsInfos(tabIdx);

							} catch (Exception e) {
								LOGGER.error(e,e);
							}
						} else {
							throw new RuntimeException(error.toString());
						}
					}

				};

				ActionDialogAction errorHandler = new ActionDialogAction() {
					@Override
					public void action() {
						warning.setForeground(new Color(getDisplay(), 255, 0, 0));
						warning.setText(error.toString());
						actForm.getParent().pack();
						actForm.getParent().layout();
						error.delete(0, error.length());
					}
				};
				actForm.setControl(dateTxt, openTxt, highTxt, lowTxt, closeTxt, volumeTxt);
				actForm.setAction(actionDialogAction);
				actForm.setErrorHandler(errorHandler);
				actForm.open();

			} else {
				UserDialog dialog = new UserDialog(getShell(), "You must select a share in one of your Portfolios before doing this.", null);
				dialog.open();
			}
		}


		private Date parseDate(String text) throws ParseException {
			return new SimpleDateFormat("yyyy-MM-dd").parse(text.replaceAll(" ", ""));
		}

		private void updateConvertionRate(Date date, final CurrencyConverter cC, final Currency stockCurrency, final Currency trCurrency) {
			String exchange = (stockCurrency.equals(trCurrency))?"":" ( for "+moneysFormat.format(cC.convert(trCurrency, stockCurrency, BigDecimal.ONE, date))+" "+stockCurrency.name()+" at date )";
			currency.setText("Currency : " + trCurrency + exchange);
			currency.getParent().layout();
		}
	}

	private class ModelController {

		private final class ObserverImplementation implements Observer,  Comparable<Observer> {
			public void update(Observable o, Object arg) {

				ObserverMsg observerMsg = (ObserverMsg) arg;

				if (observerMsg != null && observerMsg.getKey().equals(ObserverMsg.ObsKey.DONE)) {

					getDisplay().asyncExec(
							new Runnable() {
								public void run(){

									int tabIndex = portfolioCTabFolder.getSelectionIndex();
									if (isVisible() && tabIndex != -1) {
										tabUpdateItemsColors(tabIndex);
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

		private EventModel<RefreshPortfolioStrategyEngine, Collection<Stock>> portfolioStocksEventModel;
		private EventModel<RefreshMonitoredStrategyEngine, Collection<Stock>> monitoredStocksEventModel;

		public ModelController(LogComposite logComposite) {

			Observer observer =  new ObserverImplementation();

			//Event and quotations refresh
			EventModel.getInstance(new RefreshAllEventStrategyEngine(), observer);
			monitoredStocksEventModel = EventModel.getInstance(new RefreshMonitoredStrategyEngine(), observer, logComposite);
			portfolioStocksEventModel = EventModel.getInstance(new RefreshPortfolioStrategyEngine(), observer, logComposite);

		}

		public void addOrUpdateSlidingShareToTab(int tabId, SlidingPortfolioShare slidingPS) {

			Table table = (Table)  cTabItem[tabId].getData();

			//Replacing already existing ps with updated quantities
			Boolean existing = false;
			for (TableItem tableItem : table.getItems()) {
				SlidingPortfolioShare itemData = (SlidingPortfolioShare) tableItem.getData();
				Currency displayedCurrency = itemData.getDisplayedCurrency();
				if (itemData.equals(slidingPS)) {
					slidingPS.setDisplayedCurrency(displayedCurrency);
					tabUpdateTableItem(tableItem, slidingPS);
					existing = true;
				}
			}
			//This is a new ps
			if (!existing) {
				tabUpdateTableItem(new TableItem(table, SWT.DIALOG_TRIM), slidingPS);
			}

		}

		public SlidingPortfolioShare getSlidingShareInTab(int tabIdx, int rowIdx) {
			return (SlidingPortfolioShare) ((Table) cTabItem[tabIdx].getData()).getItem(rowIdx).getData();
		}


		public List<SlidingPortfolioShare> getSlidingSharesInTab(int tabIdx) {

			ArrayList<SlidingPortfolioShare> arrayList = new ArrayList<SlidingPortfolioShare>();

			TableItem[] items = ((Table) cTabItem[tabIdx].getData()).getItems();
			for (TableItem tableItem : items) {
				arrayList.add((SlidingPortfolioShare) tableItem.getData());
			}

			return Collections.unmodifiableList(arrayList);
		}

		public Portfolio getPortfolio(Integer tabIdx) {
			return PortfolioMgr.getInstance().getVisiblePortfolios().get(tabIdx);
		}

		private void updateMoniAndPSCachedModels() {

			Set<Stock> monitored = new HashSet<Stock>();
			Set<Stock> portfolioStocks = new HashSet<Stock>();

			for (Portfolio visiblePortfolio : PortfolioMgr.getInstance().getVisiblePortfolios()) {
				scanForPortfolioStocksAndMonitored(monitored, portfolioStocks, visiblePortfolio.getListShares().values());
			}

			monitoredStocksEventModel.setViewParamRoot(monitored);
			portfolioStocksEventModel.setViewParam(0, PortfolioMgr.getInstance().getUserPortfolios());

		}

	}

	private void scanForPortfolioStocksAndMonitored(Set<Stock> monitored, Set<Stock> portfolioStocks, Collection<PortfolioShare> shareListInTab) {
		for (PortfolioShare portfolioShare : shareListInTab) {
			portfolioStocks.add(portfolioShare.getStock());
			if (!portfolioShare.getMonitorLevel().equals(MonitorLevel.NONE)) {
				monitored.add(portfolioShare.getStock());
			}
		}
	}

	private final class CursorChangerObserver implements Observer {

		private int cpt;

		private CursorChangerObserver(int cpt, Integer cursorWaitType) {
			this.cpt = cpt;
			getParent().getParent().setCursor(CursorFactory.getCursor(cursorWaitType));
		}

		public void update(Observable o, Object arg) {
			cpt--;
			if (cpt <= 0) getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}

	}

	private final class TabInit extends Observable implements Runnable {

		private final Integer tabIdx;
		private final Portfolio portfolio;

		private TabInit(Integer tabIdx, Portfolio portfolio) {
			this.tabIdx = tabIdx;
			this.portfolio = portfolio;
		}

		public void run() {

			synchronized (this.portfolio) {

				this.portfolio.setIsUiDirty(false);

				try {

					ArrayList<SlidingPortfolioShare> listOfShares = tabBuildSlidingPortfolioShareList(portfolio.getListShares().values());
					
					Collections.sort(listOfShares, new Comparator<SlidingPortfolioShare>() {
						public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
							BigDecimal o2TodaysGainTotalPercent = o2.getTodaysGainTotalPercent();
							BigDecimal o1TodaysGainTotalPercent = o1.getTodaysGainTotalPercent();
							if (o2.getTodaysQuantity().compareTo(BigDecimal.ZERO) == 0) o2TodaysGainTotalPercent = new BigDecimal(-9999);
							if (o1.getTodaysQuantity().compareTo(BigDecimal.ZERO) == 0) o1TodaysGainTotalPercent = new BigDecimal(-9999);
							int ret = o2TodaysGainTotalPercent.compareTo(o1TodaysGainTotalPercent);
							ret = (ret == 0)? o1.getStock().compareTo(o2.getStock()):ret;
							return ret;
						}
					});

					for (SlidingPortfolioShare slidingPortfolioShare : listOfShares) {
						modelControler.addOrUpdateSlidingShareToTab(tabIdx, slidingPortfolioShare);
					}

					if (tabIdx == portfolioCTabFolder.getSelectionIndex()) {
						refreshChartData();
						refreshPortfolioTotalsInfos(tabIdx);
					}

				} catch (Exception e) {
					LOGGER.error("UnHandled error running the Tab initialisation :"+e, e);
				} finally {
					setChanged();
					notifyObservers();
				}

			}
		}

	}

	private enum Titles {
		Name ("Stock", "Stock name."), 
		Symbol ("Symbol", "Symbol"), 
		Isin ("Isin", "Isin"), 
		Value ("Value","Value at date."),
		Close ("Close","Last unit close price available."),
		AvgBuyPrc ("Avg price", "Average buy price basis.\nFormula : Sum(in) / Sum(quantity bought)"),  
		CostPerUnit ("Unit cost", "Cost per unit if including moneys out.\nFormula : (Sum(in) - Sum(out)) / actual quantity"),  
		ZeroWGainPrc ("Zero gain price", "Minimum unit sell price for no loss if including inflation (Based on The Consumer Price Index (CPI-U) compiled by the Bureau of Labor Statistics and is based upon a 1982 Base of 100. A Consumer Price Index of 158 indicates 58% inflation since 1982.).\nFormula : (Inflated(in)-  Inflated(out)) / actual quantity"),
		TotalPercentGain ("% gain","Realized and unrealized percent gain.\nFormula : ( ( actual value + Sum(out) ) - Sum(in) ) / Sum(in)"), 
		RPercentGain ("Real % gain","Realized percent gain.\nFormula ( ( basis + Sum(out) ) - Sum(in) ) / Sum(in)  \nwhere basis =  Average(buy price) * actual quantity"),
		UrPercentGain ("Unreal % gain","Unrealized percent gain.\nFormula : ( actual value - basis ) / basis  \nwhere basis =  Average(buy price) * actual quantity"),
		WTotalPercentGain ("% gain inflated","Total gain if including inflation (Based on The Consumer Price Index (CPI-U) compiled by the Bureau of Labor Statistics and is based upon a 1982 Base of 100. A Consumer Price Index of 158 indicates 58% inflation since 1982.).\nFormula : ( (actual value + Inflated(out)) - Inflated(in) ) / Inflated(in)"),
		DisplayedCurrency("Currency", "You can choose to display the amounts using another currency.\nDouble click to edit."),
		Monitor("Monitor","Alert monitor level.\nDouble click to edit.\nAll lines with a monitor level different from NONE will generate notifications.");

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

	private enum PortfolioInfosTitles {

		Value ("Actual value", ""),
		Basis ("Basis","Formula : Average(buy price) * actual quantity"),
		TotalGain ("Total gain","Formula : (value + Sum(out))- Sum(in)"),
		TotalGainPercent ("Percent total gain","Formula : ((value + Sum(out))- Sum(in)) /  Sum(in) "),
		UnrGain ("Unrealised gain", "Formula : value - basis \nwhere basis = Average(buy price) * actual quantity"),
		UnrGainPercent ("Percent unrealised gain", "Formula : ( value - basis ) / basis \nwhere basis = Average(buy price) * actual quantity"),
		MoneyIn ("Money in", ""),
		MoneyOut ("Money out", "");

		String toolTip;
		String columnName;

		PortfolioInfosTitles(String columnName, String toolTip) {
			this.toolTip = toolTip;
			this.columnName = columnName;
		}

		public String getToolTip() {
			return toolTip;
		}

		public String getColumnName() {
			return columnName;
		}

	}

	public PortfolioComposite(Composite parent, ChartsComposite chartsComposite, int style, LogComposite logComposite) {
		super(parent, style);

		mainGuiParent = (MainGui) parent.getParent();

		moneysFormat = NumberFormat.getNumberInstance();
		moneysFormat.setRoundingMode(RoundingMode.HALF_EVEN);
		moneysFormat.setMinimumFractionDigits(2);
		moneysFormat.setMaximumFractionDigits(2);
		percentFormat = NumberFormat.getPercentInstance();
		percentFormat.setRoundingMode(RoundingMode.HALF_EVEN);
		percentFormat.setMaximumFractionDigits(2);
		percentFormat.setMinimumFractionDigits(2);
		quantiesFormat = NumberFormat.getNumberInstance();
		quantiesFormat.setRoundingMode(RoundingMode.HALF_EVEN);
		quantiesFormat.setMinimumFractionDigits(5);
		quantiesFormat.setMaximumFractionDigits(5);

		this.logComposite = logComposite;

		this.chartsComposite = chartsComposite;
		this.chartsComposite.setComposite(this);

		this.addListener(SWT.Hide, new Listener() {

			public void handleEvent(Event arg0) {
				handleRootShellClosed(arg0);
			}	

		});

		this.modelControler = new ModelController(logComposite);
		this.setOrientation(SWT.VERTICAL);

		FontData[] fD = MainGui.DEFAULTFONT.getFontData();
		fD[0].setHeight((int)(fD[0].getHeight()*1));
		smallFont = new Font(getDisplay(),fD[0]);

		initGUI();

		this.addControlListener(new ControlListener() {	
			@Override
			public void controlResized(ControlEvent e) {
				int tabIdx =  portfolioCTabFolder.getSelectionIndex();
				if (tabIdx != -1) {
					Table ttomod = (Table) cTabItem[tabIdx].getData();
					packColumns(ttomod, Titles.values().length, 50);
				}
			}
			@Override
			public void controlMoved(ControlEvent e) {
			}
		});

	}

	private void initGUI() {
		try {

			this.setBackground(MainGui.pORTFOLIO_DARKER);

			{
				portfolioCTabFolder = new CTabFolder(this, SWT.NONE);
				GridData portfolioCTabFolder1LData = new GridData();
				portfolioCTabFolder1LData.verticalAlignment = GridData.FILL;
				portfolioCTabFolder1LData.horizontalAlignment = GridData.FILL;
				portfolioCTabFolder1LData.horizontalSpan = 2;
				portfolioCTabFolder1LData.grabExcessHorizontalSpace = true;
				portfolioCTabFolder1LData.grabExcessVerticalSpace = true;
				portfolioCTabFolder.setLayoutData(portfolioCTabFolder1LData);
				portfolioCTabFolder.setBackground(MainGui.pORTFOLIO_LIGHT);
				portfolioCTabFolder.setSelectionBackground(MainGui.tAB_SELECTION);
				portfolioCTabFolder.setFont(MainGui.DEFAULTFONT);

				portfolioCTabFolder.addSelectionListener(new SelectionListener() {

					public void widgetDefaultSelected(SelectionEvent arg0) {
						handleTabSelection();
					}

					public void widgetSelected(SelectionEvent arg0) {
						handleTabSelection();
					}

					private void handleTabSelection() {

						final int selectionIndex = portfolioCTabFolder.getSelectionIndex();
						if (selectionIndex != -1) {

							sorteted = null;
							Runnable runnable = new Runnable() {
								public void run() {

									try {

										getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));

										Portfolio portfolio = modelControler.getPortfolio(selectionIndex);
										Table ttomod = (Table) cTabItem[selectionIndex].getData();

										if (portfolio.isUiDirty() || ttomod.getItems().length != portfolio.getListShares().size()) {
											tabUpdateItemsFromPortfolio(selectionIndex, portfolio, new CursorChangerObserver(1, SWT.CURSOR_WAIT));
										} else {
											if (!cTabItem[selectionIndex].isDisposed()) {
												refreshChartData();
												refreshPortfolioTotalsInfos(selectionIndex);
											}
										}

										highLightSlidingColsAndInfos();


									} finally {
										getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
									}

								}
							};

							getDisplay().asyncExec(runnable);

						}
					}

				});

			}
			{
				{	
					{
						MenuItem emailAlertsMenu = new  MenuItem(mainGuiParent.portfolioSubMenu, SWT.CASCADE);
						emailAlertsMenu.setText("Email alerts on threshold cross");
						emailAlertsMenu.addSelectionListener(new EventRefreshController(modelControler.portfolioStocksEventModel, this, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
							@Override
							public void widgetSelected(SelectionEvent evt) {
								handleEmailAlertPopup(evt);
							}
							@Override
							public void widgetDefaultSelected(SelectionEvent evt) {
								handleEmailAlertPopup(evt);
							}
							private void handleEmailAlertPopup(SelectionEvent evt) {
								this.updateEventRefreshModelState(0l, TaskId.Alerts);
								super.widgetSelected(evt);
							}
						});
					}
					new MenuItem(mainGuiParent.portfolioSubMenu, SWT.SEPARATOR);
					{
						MenuItem portfolioAddPortFoliobutton = new  MenuItem(mainGuiParent.portfolioSubMenu, SWT.CASCADE);
						portfolioAddPortFoliobutton.setText("Add a portfolio ...");
						portfolioAddPortFoliobutton.addSelectionListener(new SelectionListener() {
							@Override
							public void widgetSelected(SelectionEvent evt) {
								handleAddPortoflio();
							}
							@Override
							public void widgetDefaultSelected(SelectionEvent evt) {
								handleAddPortoflio();
							}
						});
					}
					{
						MenuItem portfolioDeletePortfoliobutton = new MenuItem(mainGuiParent.portfolioSubMenu, SWT.CASCADE);
						portfolioDeletePortfoliobutton.setText("Remove portfolio");
						portfolioDeletePortfoliobutton.addSelectionListener(new SelectionListener() {

							@Override
							public void widgetSelected(SelectionEvent e) {
								handleRemovePortoflio();

							}

							protected void handleRemovePortoflio() {

								Portfolio portfolio = modelControler.getPortfolio(selectedPortfolioIdx());
								ActionDialog errorDialog = new ActionDialog(getShell(),"Warning", null, null, "Please, confirm '"+portfolio.getName()+"' portfolio removal", new ActionDialogAction() {	
									@Override
									public void action() {
										removeSelectedPortfolio();
										refreshChartData();
										refreshPortfolioTotalsInfos(-1);
									}
								});
								errorDialog.open();

							}

							@Override
							public void widgetDefaultSelected(SelectionEvent e) {
								handleRemovePortoflio();

							}
						});

					}
					new MenuItem(mainGuiParent.portfolioSubMenu, SWT.SEPARATOR);
					{
						MenuItem addShare = new  MenuItem(mainGuiParent.portfolioSubMenu, SWT.CASCADE);
						addShare.setText("Add shares ...");
						addShare.addSelectionListener(new SelectionListener() {
							@Override
							public void  widgetSelected(SelectionEvent evt) {
								handleAddShareToTab(evt);
							}
							@Override
							public void widgetDefaultSelected(SelectionEvent evt) {
								handleAddShareToTab(evt);
							}
						});
					}
					{
						MenuItem addCurrency = new  MenuItem(mainGuiParent.portfolioSubMenu, SWT.CASCADE);
						addCurrency.setText("Add a currency ...");
						addCurrency.addSelectionListener(new SelectionListener() {
							@Override
							public void  widgetSelected(SelectionEvent evt) {
								handleRemoveShare();
							}
							@Override
							public void widgetDefaultSelected(SelectionEvent evt) {
								handleRemoveShare();
							}
							private void handleRemoveShare() {

								try {
									NewCurrencyStockDialog currencyStockDialog = new NewCurrencyStockDialog(getShell(), SWT.NONE, PortfolioComposite.this);
									currencyStockDialog.open();
								} catch (Exception e) {
									LOGGER.error(e,e);
									UserDialog inst = new UserDialog(getShell(), "Error adding currency. \n"+e,null);
									inst.open();
								}

							}
						});
					}
					new MenuItem(mainGuiParent.portfolioSubMenu, SWT.SEPARATOR);
					{
						MenuItem cancelPortfolioMenuItem = new MenuItem(mainGuiParent.portfolioSubMenu, SWT.CASCADE);
						cancelPortfolioMenuItem.setText("Save modifications");
						cancelPortfolioMenuItem.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent evt) {
								handleSaveAllPortfolios(evt);
							}
						});
						cancelPortfolioMenuItem.setEnabled(true);
					}
					{
						MenuItem savePortfolioMenuItem = new MenuItem(mainGuiParent.portfolioSubMenu, SWT.CASCADE);
						savePortfolioMenuItem.setText("Roll back to last save or shutdown");
						savePortfolioMenuItem.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent evt) {
								handleCancelPortfolioModifications(evt);
							}
						});
						savePortfolioMenuItem.setEnabled(true);
					}
				}

				{
					final MenuItem indicatorMenuItem = new MenuItem(mainGuiParent.chartSubMenu, SWT.RADIO);
					indicatorMenuItem.setText("Trend Calculators Charting");
					indicatorMenuItem.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent evt) {
							if (indicatorMenuItem.getSelection()) {
								chartsComposite.shutDownDisplay();
								chartsComposite.setChartDisplayStrategy(new ChartIndicatorDisplay(chartsComposite));
							}
						}
					});
				}
				{
					final MenuItem perfsMenuItem = new MenuItem(mainGuiParent.chartSubMenu, SWT.RADIO);
					perfsMenuItem.setText("Historical performance Charting");
					perfsMenuItem.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent evt) {
							if (perfsMenuItem.getSelection()) {
								chartsComposite.shutDownDisplay();
								chartsComposite.setChartDisplayStrategy(new ChartPerfDisplay(chartsComposite));
							}
						}
					});
					perfsMenuItem.setSelection(true);
				}

				{
					portfolioInfosGroup = new Group(this, SWT.NONE);
					GridData portfolioInfosGroupData = new GridData(SWT.FILL, SWT.TOP, true, false);
					portfolioInfosGroup.setLayoutData(portfolioInfosGroupData);
					portfolioInfosGroup.setLayout(new GridLayout());
					portfolioInfosGroup.setText("Portfolio summary : ");
					portfolioInfosGroup.setFont(MainGui.DEFAULTFONT);
					portfolioInfosGroup.setBackground(MainGui.pORTFOLIO_LIGHT);

					{
						portfolioInfosTable = new Table(portfolioInfosGroup, SWT.NONE);
						portfolioInfosTable.setFont(MainGui.DEFAULTFONT);
						portfolioInfosTable.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
						portfolioInfosTable.setLinesVisible(false);
						portfolioInfosTable.setHeaderVisible(true);

						for (int j = 0; j < PortfolioInfosTitles.values().length; j++) {
							TableColumn column = new TableColumn(portfolioInfosTable, SWT.LEFT);
							column.setText(PortfolioInfosTitles.values()[j].getColumnName());
							column.setToolTipText(PortfolioInfosTitles.values()[j].getToolTip());
							//column.pack();
						}
						new TableItem(portfolioInfosTable, SWT.NONE);

						packColumns(portfolioInfosTable, PortfolioInfosTitles.values().length, 100);
						portfolioInfosGroup.layout();

					}

					{
						slidingStartAnchor = new Button(portfolioInfosGroup, SWT.CHECK);
						GridData slidingChecksLayout = new GridData(SWT.FILL, SWT.TOP, true, false);
						slidingChecksLayout.horizontalSpan = 2;
						slidingStartAnchor.setLayoutData(slidingChecksLayout);
						slidingStartAnchor.setText("Set first price at chart's slider start date");
						slidingStartAnchor.setToolTipText("This will set the portfolio entries average buy price to the price at the chart's slider start date and update the portfolio calculations accordingly");
						slidingStartAnchor.setFont(MainGui.DEFAULTFONT);
						slidingStartAnchor.addSelectionListener(new SelectionListener() {

							public void widgetSelected(SelectionEvent e) {
								handleSyncPriceToSlideStart();
							}

							protected void handleSyncPriceToSlideStart() {
								slidingDateChange();
								highLightSlidingColsAndInfos();
								refreshChartData();
								//refreshPortfolioTotalsInfos(portfolioCTabFolder.getSelectionIndex());
							}

							public void widgetDefaultSelected(SelectionEvent e) {
								handleSyncPriceToSlideStart();
							}

						});
					}

					{
						slidingEndAnchor = new Button(portfolioInfosGroup, SWT.CHECK);
						GridData slidingChecksLayout = new GridData(SWT.FILL, SWT.TOP, true, false);
						slidingChecksLayout.horizontalSpan = 2;
						slidingEndAnchor.setLayoutData(slidingChecksLayout);
						slidingEndAnchor.setText("Set last price at chart's slider end date");
						slidingEndAnchor.setToolTipText("This will set set the portfolio entries last close price available to the price at the chart's slider end date and update the portfolio calculations accordingly");
						slidingEndAnchor.setFont(MainGui.DEFAULTFONT);
						slidingEndAnchor.addSelectionListener(new SelectionListener() {

							public void widgetSelected(SelectionEvent e) {
								handleSyncPriceToSlideEnd();

							}

							protected void handleSyncPriceToSlideEnd() {
								slidingDateChange();
								highLightSlidingColsAndInfos();
								refreshChartData();
								//refreshPortfolioTotalsInfos(portfolioCTabFolder.getSelectionIndex());
							}

							public void widgetDefaultSelected(SelectionEvent e) {
								handleSyncPriceToSlideEnd();
							}

						});
					}
					
					{
						isLatestTransactionsOnly = new Button(portfolioInfosGroup, SWT.CHECK);
						GridData isLatestTransactionsOnlyLayout = new GridData(SWT.FILL, SWT.TOP, true, false);
						isLatestTransactionsOnlyLayout.horizontalSpan = 2;
						isLatestTransactionsOnly.setLayoutData(isLatestTransactionsOnlyLayout);
						isLatestTransactionsOnly.setText("Use invested lines transactions only");
						isLatestTransactionsOnly.setToolTipText("Take in acccount only the transactions for which the lines are still invested i.e. not empty.");
						isLatestTransactionsOnly.setFont(MainGui.DEFAULTFONT);
						isLatestTransactionsOnly.addSelectionListener(new SelectionListener() {

							public void widgetSelected(SelectionEvent e) {
								handleSyncPriceToSlideEnd();
							}

							protected void handleSyncPriceToSlideEnd() {
								isLatestTransactionsOnlyChange();
								refreshChartData();
							}

							public void widgetDefaultSelected(SelectionEvent e) {
								handleSyncPriceToSlideEnd();
							}

						});
					}
				}
				refreshPortfolioTotalsInfos(portfolioCTabFolder.getSelectionIndex());

			}

		} catch (Exception e) {
			LOGGER.error("",e);
		}
	}
	
	public void isLatestTransactionsOnlyChange() {

		int si = selectedPortfolioIdx();

		if (si != -1) {
			Portfolio currentPortfolio = modelControler.getPortfolio(si);
			currentPortfolio.setLatestTransactionsOnly(isLatestTransactionsOnly.getSelection());

			List<SlidingPortfolioShare> slidingPSs = modelControler.getSlidingSharesInTab(si);
			if (slidingPSs.size() > 0) {
				try {
					this.getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
					Table ttomod = (Table) cTabItem[si].getData();
					for (int itemIdx = 0 ; itemIdx  < slidingPSs.size(); itemIdx ++) {
						SlidingPortfolioShare portfolioShare = slidingPSs.get(itemIdx);
						tabUpdateTableItem(ttomod.getItem(itemIdx), portfolioShare);
					}
					refreshPortfolioTotalsInfos(si);
				} finally {
					this.getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
				}
			}
		}
	}


	private void highLightSlidingColsAndInfos() {

		int si = selectedPortfolioIdx();
		if (si == -1) return;

		CTabItem cTabItemElem = cTabItem[si];

		if (!cTabItemElem.isDisposed() && cTabItemElem.isShowing()) {

			Table t = (Table) cTabItemElem.getData();

			Color bgColor;
			Color fgColor;

			boolean isSlidingStart = slidingStartAnchor.getSelection();
			if (isSlidingStart) {
				bgColor = new Color(getDisplay(), 246, 244, 242);
				fgColor = new Color(getDisplay(), 230, 225, 220);
			} else {
				bgColor = new Color(getDisplay(), 255, 255, 255);
				fgColor = new Color(getDisplay(), 0, 0, 0);
			}

			for (TableItem item : t.getItems()) {
				item.setBackground(Titles.Value.ordinal(),bgColor);
				item.setForeground(Titles.Value.ordinal(),fgColor);
				item.setBackground(Titles.AvgBuyPrc.ordinal(),bgColor);
				item.setForeground(Titles.AvgBuyPrc.ordinal(),fgColor);
				item.setBackground(Titles.CostPerUnit.ordinal(),bgColor);
				item.setForeground(Titles.CostPerUnit.ordinal(),fgColor);
				item.setBackground(Titles.ZeroWGainPrc.ordinal(),bgColor);
				item.setForeground(Titles.ZeroWGainPrc.ordinal(),fgColor);
				item.setBackground(Titles.TotalPercentGain.ordinal(),bgColor);
				item.setForeground(Titles.TotalPercentGain.ordinal(),fgColor);
				item.setBackground(Titles.RPercentGain.ordinal(),bgColor);
				item.setForeground(Titles.RPercentGain.ordinal(),fgColor);
				item.setBackground(Titles.UrPercentGain.ordinal(),bgColor);
				item.setForeground(Titles.UrPercentGain.ordinal(),fgColor);
				item.setBackground(Titles.WTotalPercentGain.ordinal(),bgColor);
				item.setForeground(Titles.WTotalPercentGain.ordinal(),fgColor);

			}

			//Infos
			portfolioInfosTable.getItem(0).setBackground(bgColor);
			portfolioInfosTable.getItem(0).setForeground(fgColor);

		}
	}

	public void tabUpdateItemsFromPortfolio(Integer tabIdx, Portfolio portfolio, Observer... tabInitObservers) {

		TabInit runnable = new TabInit(tabIdx, portfolio);
		for (Observer observer : tabInitObservers) {
			runnable.addObserver(observer);
		}
		getDisplay().syncExec(runnable);	
	}

	private void refreshChartData() {

		int tabIdx = portfolioCTabFolder.getSelectionIndex();
		if (tabIdx != -1) {

			packColumns((Table) portfolioCTabFolder.getItem(tabIdx).getData(), Titles.values().length, 50);

			try {
				this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
				refreshChartDataNoCursorChange();
			} finally {
				this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
			}

		} else {
			chartsComposite.resetChart();
		}

	}

	private void refreshChartDataNoCursorChange() {

		int tabIdx = selectedPortfolioIdx();

		if (cTabItem.length > 0 && tabIdx != -1) {
			chartsComposite.updateChartsWith(modelControler.getSlidingSharesInTab(tabIdx));
			tabUpdateItemsColors(tabIdx);
		} 

	}

	private void tabUpdateItemsColors(int tabIdx) {

		Table t = (Table) cTabItem[tabIdx].getData();
		TableItem[] titems = t.getItems();
		for (int i = 0; i < titems.length; i++) {
			Color color = ((SlidingPortfolioShare)titems[i].getData()).getColor();
			int red = color.getRed();
			int green = color.getGreen();
			int blue = color.getBlue();
			Color shaded = new Color(getDisplay(), red*3/4, green*3/4, blue*3/4);
			Color tinted = new Color(getDisplay(), red+((255-red)*3/4), green+((255-green)*3/4), blue + ((255-blue)*3/4));
			titems[i].setBackground(Titles.Name.ordinal(), tinted);
			titems[i].setForeground(Titles.Name.ordinal(), shaded);
			titems[i].setBackground(Titles.Symbol.ordinal(), tinted);
			titems[i].setForeground(Titles.Symbol.ordinal(), shaded);
			titems[i].setBackground(Titles.Isin.ordinal(), tinted);
			titems[i].setForeground(Titles.Isin.ordinal(), shaded);
		}

	}

	public void tabBuildAllTabs(int selectedTabIdx, Observer... addObss) {

		if (addObss == null ) addObss = new Observer[0];

		try {

			List<Portfolio> visiblePortfolios = PortfolioMgr.getInstance().getVisiblePortfolios();

			cTabItem = new CTabItem[visiblePortfolios.size()];
			int tabIdxi=0;

			for (Portfolio portfolio : visiblePortfolios) {	
				//Create tabs
				tabAddOneTab(tabIdxi, portfolio);
				tabIdxi++;	
			}

			//Fill up first tab items
			if (portfolioCTabFolder.getItemCount() > 0) {

				portfolioCTabFolder.setSelection(selectedTabIdx);

				Observer[] observers = new Observer[addObss.length+1];
				for (int i = 0; i < addObss.length; i++) {
					observers[i] = addObss[i];
				}
				observers[addObss.length] = new CursorChangerObserver(1, SWT.CURSOR_APPSTARTING);

				tabUpdateItemsFromPortfolio(selectedTabIdx, visiblePortfolios.get(selectedTabIdx), observers);

			}

		} finally {


		}

	}

	public void backGroundLoadQuotationCache() {

		try {
			PortfolioComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_APPSTARTING));

			final ExecutorService executor = Executors.newFixedThreadPool(5);

			List<Portfolio> visiblePortfolios = PortfolioMgr.getInstance().getVisiblePortfolios();
			for (Portfolio portfolio : visiblePortfolios) {
				for (final PortfolioShare pS : portfolio.getListShares().values()) {

					final Date today = EventSignalConfig.getNewDate();
					if (pS.getQuantity(chartsComposite.getSlidingStartDate(), today).compareTo(BigDecimal.ZERO) > 0) {
						try {

							Runnable loadQRunnable = new Runnable() {
								public void run() {
									try {
										if (pS.getPortfolio().equals(modelControler.getPortfolio(portfolioCTabFolder.getSelectionIndex()))) {
											try {
												Thread.sleep(1000);
											} catch (InterruptedException e) {
												LOGGER.warn(e);
											}
										} else {
											LOGGER.info("Loading in background quotations for "+pS.getStock().getFriendlyName());
											QuotationsFactories.getFactory().getQuotationsInstance(pS.getStock(), chartsComposite.getSlidingStartDate(), today, true, pS.getTransactionCurrency(), 1, ValidityFilter.CLOSE);
										}
									} catch (NoQuotationsException e) {
										LOGGER.warn(e);
									}
								}
							};
							if (SpringContext.getSingleton().isActive()) executor.submit(loadQRunnable);

						} catch (Exception e) {
							LOGGER.error(e, e);
						}
					}

				}
			}

			Runnable executorRunnable = new Runnable() {
				public void run() {

					executor.shutdown();

					try {
						boolean awaitTermination = executor.awaitTermination(20, TimeUnit.MINUTES);
						if (!awaitTermination) {
							List<Runnable> shutdownNow = executor.shutdownNow();
							LOGGER.warn(shutdownNow, new Exception());
						}
					} catch (InterruptedException e) {
						List<Runnable> shutdownNow = executor.shutdownNow();
						LOGGER.error(shutdownNow, e);
					} finally {

						getDisplay().asyncExec(
								new Runnable() {
									public void run() {
										PortfolioComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
									}
								});

					}
				}
			}; 
			new Thread(executorRunnable).start();

		} catch  (Throwable throwable){
			LOGGER.error(throwable, throwable);
		} 

	}

	private void tabAddOneTab(int tabIdx, Portfolio portfolio) {

		cTabItem[tabIdx] = new CTabItem(portfolioCTabFolder, SWT.NONE);
		cTabItem[tabIdx].setText(portfolio.getName());
		cTabItem[tabIdx].setFont(smallFont);

		{
			final Table table = new Table(portfolioCTabFolder, SWT.BORDER | SWT.FULL_SELECTION);
			table.setFont(smallFont);
			table.setLinesVisible(false);
			table.setHeaderVisible(true);
			cTabItem[tabIdx].setControl(table);
			for (int j = 0; j < Titles.values().length; j++) {

				TableColumn column = new TableColumn(table, SWT.LEFT);
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
			cTabItem[tabIdx].setData(table);

			{
				Menu portfolioShareCtxMenu = new Menu(table);
				final MenuItem menuTitle = new MenuItem(portfolioShareCtxMenu, SWT.NONE);
				menuTitle.setEnabled(false);
				new MenuItem(portfolioShareCtxMenu, SWT.SEPARATOR);
				final MenuItem menuItemBuy = new MenuItem(portfolioShareCtxMenu, SWT.NONE);
				final MenuItem menuItemSell = new MenuItem(portfolioShareCtxMenu, SWT.NONE);

				portfolioShareCtxMenu.addMenuListener(new MenuListener() {

					@Override
					public void menuShown(MenuEvent e) {
						menuTitle.setText(modelControler.getSlidingShareInTab(selectedPortfolioIdx(), table.getSelectionIndex()).getFriendlyName());
						menuItemBuy.setText("Buy ...");
						menuItemSell.setText("Sell ...");
					}

					@Override
					public void menuHidden(MenuEvent e) {
					}
				});
				table.setMenu(portfolioShareCtxMenu);
				{
					menuItemBuy.setText("Buy ... ");
					menuItemBuy.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetSelected(SelectionEvent event) {
							openTransactionDialog(event);
						}

						@Override
						public void widgetDefaultSelected(SelectionEvent event) {
							openTransactionDialog(event);
						}

						private void openTransactionDialog(SelectionEvent event) {
							TableItem item = table.getItems()[table.getSelectionIndex()];
							columnEditManagment(item, table.getSelectionIndex(), TransactionType.AIN);
						}
					});
				}
				{
					menuItemSell.setText("Sell ... ");
					menuItemSell.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetSelected(SelectionEvent event) {
							openTransactionDialog(event);
						}

						@Override
						public void widgetDefaultSelected(SelectionEvent event) {
							openTransactionDialog(event);
						}

						private void openTransactionDialog(SelectionEvent event) {
							TableItem item = table.getItems()[table.getSelectionIndex()];
							columnEditManagment(item, table.getSelectionIndex(), TransactionType.AOUT);
						}
					});
				}
				{
					MenuItem menuItemRemove = new MenuItem(portfolioShareCtxMenu, SWT.NONE);
					menuItemRemove.setText("Remove line and its transactions history");
					menuItemRemove.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetSelected(SelectionEvent event) {
							handleRemoveShare(event);
						}

						@Override
						public void widgetDefaultSelected(SelectionEvent event) {
							handleRemoveShare(event);
						}

						private void handleRemoveShare(SelectionEvent event) {
							int tabIdx = selectedPortfolioIdx();
							Portfolio portfolio =  modelControler.getPortfolio(tabIdx);
							int rowIdx = table.getSelectionIndex();
							if (rowIdx != -1) {

								SlidingPortfolioShare selectedShare = modelControler.getSlidingShareInTab(tabIdx, rowIdx);
								portfolio.rawRemoveShare(selectedShare);

								Table ttomod = (Table) cTabItem[tabIdx].getData();
								ttomod.remove(rowIdx);
								modelControler.updateMoniAndPSCachedModels();

								refreshChartData();
								refreshPortfolioTotalsInfos(tabIdx);

							}
						}
					});
				}
				new MenuItem(portfolioShareCtxMenu, SWT.SEPARATOR);
				{
					MenuItem menuItemAlert = new MenuItem(portfolioShareCtxMenu, SWT.NONE);
					menuItemAlert.setText("Set alert on threshold cross ...");
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

								final SlidingPortfolioShare selectedShare = modelControler.getSlidingShareInTab(selectedPortfolioIdx(), selectionIndex);
								Point evtsAbsPos = getDisplay().map(table, null, event.y, event.x);

								ActionDialogAction action = new ActionDialogAction() {
									@Override
									public void action() {
										EventTaskQueue.getSingleton().invalidateTasksCreationDates(TaskId.Alerts);
										tabUpdateTableItem(table.getSelection()[0], selectedShare);
									}
								};
								AddAlertDialog addAlertDialog = new AddAlertDialog(getShell(), selectedShare, action);
								addAlertDialog.open(evtsAbsPos.x, evtsAbsPos.y + table.getSelection()[0].getBounds().height + table.getSelection()[0].getBounds().y);

							} 	
						}
					});
				}
				{
					MenuItem menuItemAlert = new MenuItem(portfolioShareCtxMenu, SWT.NONE);
					menuItemAlert.setText("Set alert on events ...");
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
								final SlidingPortfolioShare selectedShare = modelControler.getSlidingShareInTab(selectedPortfolioIdx(), selectionIndex);

								Set<EventInfo> availEventDefs = EventDefinition.loadMaxPassPrefsEventInfo();
								final SortedSet<EventInfo> selectedEventDefs = new TreeSet<EventInfo>(new Comparator<EventInfo>() {

									@Override
									public int compare(EventInfo o1, EventInfo o2) {
										return o1.info().compareTo(o2.info());
									}
								});
								Set<AlertOnEvent> alertsOnEvents = selectedShare.getAlertsOnEvent();
								for (AlertOnEvent alertOnEvent : alertsOnEvents) {
									try {
										selectedEventDefs.add(EventDefinition.valueOfEventInfo(alertOnEvent.getEventInfoReference()));
									} catch (NoSuchFieldException e) {
										LOGGER.warn("Event definition not found in this configuration : "+e);
									}
								}

								ActionDialogAction closeAction = new ActionDialogAction() {

									@Override
									public void action() {

										new AlertsMgrDelegate(selectedShare).clearAlertOnEvent();
										if (!selectedEventDefs.isEmpty()) {
											selectedShare.setMonitorLevel(MonitorLevel.ANY);
											for (EventInfo selectedEvt : selectedEventDefs) {
												new AlertsMgrDelegate(selectedShare).addAlertOnEvent(selectedEvt.getEventDefinitionRef(), MonitorLevel.ANY, "");
											}
											EventTaskQueue.getSingleton().invalidateTasksCreationDates(TaskId.Analysis);
											tabUpdateTableItem(table.getSelection()[0], selectedShare);
										} 
									}

								};
								PopupMenu<EventInfo> popupMenu = new PopupMenu<EventInfo>(PortfolioComposite.this, table, availEventDefs, selectedEventDefs, false, true, SWT.CHECK, null, closeAction, false);
								popupMenu.open();

							} 

						}
					});
				}
				new MenuItem(portfolioShareCtxMenu, SWT.SEPARATOR);
				{
					MenuItem refreshQuotationsMenuItem = new MenuItem(portfolioShareCtxMenu, SWT.NONE);
					refreshQuotationsMenuItem.setText("Update quotations download");
					final EventModel<RefreshChartHighlighted, Stock> evtModelInst = EventModel.getInstance(new RefreshChartHighlighted());
					refreshQuotationsMenuItem.addSelectionListener(new EventRefreshController(evtModelInst, this, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
						@Override
						public void widgetSelected(SelectionEvent evt) {
							int selectionIndex = table.getSelectionIndex();
							if (selectionIndex != -1) {
								LOGGER.guiInfo("I am refreshing. Thanks for waiting ...");
								SlidingPortfolioShare selectedShare = modelControler.getSlidingShareInTab(selectedPortfolioIdx(), selectionIndex);
								evtModelInst.setViewParamRoot(selectedShare.getStock());
								evtModelInst.setLastQuotationFetch(EventModel.DEFAULT_DATE);
								this.updateEventRefreshModelState(0l, TaskId.FetchQuotations);
								super.widgetSelected(evt);
							}
						}
					});
				}
				{
					final MenuItem resetQuotesMenuItem = new MenuItem(portfolioShareCtxMenu, SWT.NONE);
					resetQuotesMenuItem.setText("Reset quotations download");
					portfolioShareCtxMenu.addMenuListener(new MenuListener() {

						@Override
						public void menuShown(MenuEvent e) {
							final int tabIdx = table.getSelectionIndex();
							if (tabIdx != -1) {
								SlidingPortfolioShare ss = modelControler.getSlidingShareInTab(selectedPortfolioIdx(), tabIdx);
								Stock stock = ss.getStock();
								if (stock.equals(ProvidersInflation.inflationStock())) {
									resetQuotesMenuItem.setEnabled(false);
								} else {
									resetQuotesMenuItem.setEnabled(true);
								}
							}
						}

						@Override
						public void menuHidden(MenuEvent e) {
						}

					});
					resetQuotesMenuItem.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetSelected(SelectionEvent e) {
							handleResetQuotation();
						}

						@Override
						public void widgetDefaultSelected(SelectionEvent e) {
							handleResetQuotation();
						}

						private void handleResetQuotation() {
							final int itemIdx = table.getSelectionIndex();
							if (itemIdx != -1) {

								final SlidingPortfolioShare ss = modelControler.getSlidingShareInTab(selectedPortfolioIdx(), itemIdx);

								final ActionDialogForm actForm = new ActionDialogForm(getShell(), "Reset", null, "Reset quotation for "+ss.getFriendlyName());
								final Button clearExisting = new Button(actForm.getParent(), SWT.CHECK);
								clearExisting.setBackground(MainGui.pOPUP_BG);
								clearExisting.setFont(MainGui.DEFAULTFONT);
								clearExisting.setText("Also clear previous downloads.");
								clearExisting.setToolTipText("If ticked, existing quotations will be cleared before being updated.");
								clearExisting.setSelection(false);
								ActionDialogAction actionDialogAction = new ActionDialogAction() {

									@Override
									public void action() {

										Stock stock = ss.getStock();

										boolean clearPrevious = clearExisting.getSelection();
										try {
											if (clearPrevious) DataSource.getInstance().cleanQuotationsFor(stock);
											stock.setLastQuote(DateFactory.dateAtZero());
										} catch (SQLException e1) {
											LOGGER.error(e1, e1);
											stock.setLastQuote(DataSource.getInstance().getLastQuotationDateFromQuotations(stock, false));
										}

										QuotationUpdate quotationUpdate = new QuotationUpdate();
										try {
											quotationUpdate.getQuotes(new StockList(stock), true);
										} catch (QuotationUpdateException e) {
											UserDialog dialog = new UserDialog(getShell(), "No quotation found for "+stock.getFriendlyName(), e.toString());
											dialog.open();
										}

										//Ui ...
										tabUpdateTableItem(table.getSelection()[0], ss);
										refreshChartData();
										refreshPortfolioTotalsInfos(selectedPortfolioIdx());
									}

								};
								actForm.setAction(actionDialogAction);
								actForm.setControl(clearExisting);
								actForm.open();

							}
						}
					});
				}
				new MenuItem(portfolioShareCtxMenu, SWT.SEPARATOR);
				{
					MenuItem addManQuotesMenuItem = new MenuItem(portfolioShareCtxMenu, SWT.NONE);
					addManQuotesMenuItem.setText("Add a quotation");
					addManQuotesMenuItem.addSelectionListener(new ManualQuotationSelectionListener());
				}
				{
					MenuItem addManQuotesMenuItem = new MenuItem(portfolioShareCtxMenu, SWT.NONE);
					addManQuotesMenuItem.setText("Add line transactions prices as new quotations");
					addManQuotesMenuItem.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetSelected(SelectionEvent e) {
							handleAddTransactionPriceQs();
						}

						@Override
						public void widgetDefaultSelected(SelectionEvent e) {
							handleAddTransactionPriceQs();
						}

						private void handleAddTransactionPriceQs() {

							final int itemIdx = table.getSelectionIndex();
							CurrencyConverter cc = PortfolioMgr.getInstance().getCurrencyConverter();

							if (itemIdx != -1) {
								SlidingPortfolioShare ss = modelControler.getSlidingShareInTab(selectedPortfolioIdx(), itemIdx);
								Stock stock = ss.getStock();
								SortedSet<TransactionElement> transactions = ss.getTransactions();

								Map<Date, Object[]> cumQu = new HashMap<Date, Object[]>();
								for (TransactionElement te : transactions) {
									Object[] priceNnbOccs = cumQu.get(te.getDate());
									if (priceNnbOccs == null) {
										priceNnbOccs = new Object[2];
										priceNnbOccs[0] = BigDecimal.ZERO;
										priceNnbOccs[1] = 0;
										cumQu.put(te.getDate(), priceNnbOccs);
									}
									priceNnbOccs[0] = ((BigDecimal) priceNnbOccs[0]).add(cc.convert(te.getCurrency(), stock.getMarketValuation().getCurrency(), te.getPrice(), te.getDate()));
									priceNnbOccs[1] = ((Integer)priceNnbOccs[1]) + 1;

								}
								List<QuotationUnit> quotationUnits = new ArrayList<QuotationUnit>();
								for (Date cumDate : cumQu.keySet()) {
									Object[] values = cumQu.get(cumDate);
									BigDecimal p = ((BigDecimal) values[0]).divide(new BigDecimal(values[1].toString()), 10, BigDecimal.ROUND_HALF_EVEN);
									if (p.compareTo(BigDecimal.ZERO) != 0) {
										QuotationUnit quotationUnit = new QuotationUnit(stock, stock.getMarketValuation().getCurrency(), cumDate, p, p, p, p, 0l, ORIGIN.USER);
										quotationUnits.add(quotationUnit);
									}
								}

								//Db and caches ...
								DataSource.getInstance().getShareDAO().saveOrUpdateQuotationUnits(quotationUnits);
								Quotations.refreshCaches(stock);

								//Ui ...
								tabUpdateTableItem(table.getSelection()[0], ss);
								refreshChartData();
								refreshPortfolioTotalsInfos(selectedPortfolioIdx());
							}
						}

					});
				}
				new MenuItem(portfolioShareCtxMenu, SWT.SEPARATOR);
				{
					//Untick to see your edited entries when there already are entries from downloaded quotations at the same date.\n"+
					//When ticked, the downloaded quotations will be predominant if exist at the same date");
					final MenuItem allowOverride = new MenuItem(portfolioShareCtxMenu, SWT.CHECK);
					allowOverride.setText("Allow quotation download updates to overshadowing quotation manual entries");
					portfolioShareCtxMenu.addMenuListener(new MenuListener() {

						@Override
						public void menuShown(MenuEvent e) {
							final int tabIdx = table.getSelectionIndex();
							if (tabIdx != -1) {
								SlidingPortfolioShare ss = modelControler.getSlidingShareInTab(selectedPortfolioIdx(), tabIdx);
								Stock stock = ss.getStock();
								allowOverride.setSelection(stock.isOverrideUserQuotes());
							} else {
								allowOverride.setSelection(true);
							}
						}

						@Override
						public void menuHidden(MenuEvent e) {
						}

					});
					allowOverride.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetSelected(SelectionEvent e) {
							handleQOverride(table, allowOverride);
						}

						protected void handleQOverride(final Table table, final MenuItem allowOverride) {
							final int itemIdx = table.getSelectionIndex();
							if (itemIdx != -1) {
								SlidingPortfolioShare ss = modelControler.getSlidingShareInTab(selectedPortfolioIdx(), itemIdx);
								Stock stock = ss.getStock();
								stock.setOverrideUserQuotes(allowOverride.getSelection());

								//Db
								DataSource.getInstance().getShareDAO().saveOrUpdateStock(stock);
								Quotations.refreshCaches(stock);
								//Ui
								tabUpdateTableItem(table.getSelection()[0], ss);
								refreshChartData();
								refreshPortfolioTotalsInfos(selectedPortfolioIdx());
							}
						}

						@Override
						public void widgetDefaultSelected(SelectionEvent e) {
							handleQOverride(table, allowOverride);
						}

					});
				}


			}
			table.addMouseListener(new MouseListener() {

				public void mouseDoubleClick(MouseEvent event) {
					//tabItemClickHandler(table, event);
				}

				public void mouseDown(MouseEvent event) {

					if (event.button == 1 && event.count == 1) {

						Point pt = new Point(event.x, event.y);
						TableItem item = table.getItem(pt); 

						if (item != null) {
							int selectionIndex = table.getSelectionIndex();
							if (selectionIndex != -1) {
								SlidingPortfolioShare selectedShare = modelControler.getSlidingShareInTab(selectedPortfolioIdx(), selectionIndex);
								LOGGER.info("Calling highLight from Portfolio Table (MouseListener");
								chartsComposite.highLight(selectionIndex, selectedShare.getStock(), true);
								modelControler.addOrUpdateSlidingShareToTab(selectedPortfolioIdx(), selectedShare);
							} 
						} 
					}

					tabItemClickHandler(table, event);
				}

				public void mouseUp(MouseEvent arg0) {
					//Nothing
				}

			});

			tableRowToolTip = null;
			final SimpleDateFormat dateFormat  = new SimpleDateFormat("dd MMM yy");

			table.addListener(SWT.MouseHover, new TableToolTip() {

				@Override
				protected void buildAndShowToolTip(Event event) {
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
						if (idx == items.length) return;

						//Show tip
						final SlidingPortfolioShare selectedShare = modelControler.getSlidingShareInTab(selectedPortfolioIdx(), idx);
						Currency displayedCurrency = selectedShare.getDisplayedCurrency();

						String[] infoItems = new String[7];

						QuotationUnit lastClose =  null;
						String lastCloseDate = "NA";
						String origin = selectedShare.getStock().getSymbolMarketQuotationProvider().getMarketQuotationProvider().getCmdParam();
						try {
							Quotations quotationsInstance = QuotationsFactories.getFactory().getQuotationsInstance(selectedShare.getStock(), EventSignalConfig.getNewDate(), true, selectedShare.getStock().getMarketValuation().getCurrency(), ValidityFilter.CLOSE);
							if (quotationsInstance.hasQuotations()) {
								lastClose = quotationsInstance.get(quotationsInstance.size()-1);
								lastCloseDate = dateFormat.format(selectedShare.getStock().getLastQuote());
								if (lastClose.getOrigin().equals(ORIGIN.USER)) origin = ORIGIN.USER.name().toLowerCase();
							}
						} catch (NoQuotationsException e) {

						}

						infoItems[0] = selectedShare.getFriendlyName();
						infoItems[1] = "Last quotation date : " + lastCloseDate + " (Source : " + origin + ")";
						infoItems[2] = "Actual quantity : " + quantiesFormat.format(selectedShare.getTodaysQuantity());
						infoItems[3] = 
								"Total Gain " + signumRoundedFormat(moneysFormat, selectedShare.getTodaysGainTotal()) +
								" / Real " +  signumRoundedFormat(moneysFormat, selectedShare.getTodaysGainReal()) +
								" / Unreal " +  signumRoundedFormat(moneysFormat, selectedShare.getTodaysGainUnreal()) + " " + displayedCurrency;
						infoItems[4] = 
								"Cash flow : In " + signumRoundedFormat(moneysFormat, selectedShare.getTodaysCashin()) + 
								" / Out " + signumRoundedFormat(moneysFormat, selectedShare.getTodaysCashout()) + " " + displayedCurrency;
						InOutWeighted weightedInvested = selectedShare.getTodaysWeightedInvested();
						infoItems[5] = "Inflation Weighted " +
								"Cash flow : In " + signumRoundedFormat(moneysFormat, weightedInvested.getIn()) + 
								" / Out "+ signumRoundedFormat(moneysFormat, weightedInvested.getOut()) + " "+ displayedCurrency;
						infoItems[6] = "Listed in : ";

						String shareInfo = infoItems[0] + "\n" + infoItems[1] + "\n" + infoItems[2] + "\n" + infoItems[3] + "\n" + infoItems[4]+ "\n" + infoItems[5]+"\n" + infoItems[6];

						Point point = new Point (event.x, event.y);
						Point map = getDisplay().map((Control)event.widget, null, point);
						tableRowToolTip = showTooltip(selectedShare.hashCode(), null, map.x, map.y, shareInfo);

						Runnable runnable = new Runnable() {
							public void run() {
								List<PortfolioShare> portfolioSharesForStock = PortfolioMgr.getInstance().getPortfolioDAO().loadPortfolioShareForStock(selectedShare.getStock());
								String shareListsForStock = "";
								String sep = "";
								for (PortfolioShare portfolioShare : portfolioSharesForStock) {
									shareListsForStock = shareListsForStock + sep + portfolioShare.getPortfolio().getName();
									sep = " / ";
								}
								final String fshareListsForStock = shareListsForStock;
								Runnable runnable2 = new Runnable() {
									public void run() {
										updateTooltip(selectedShare.hashCode(), fshareListsForStock);
									}
								};
								getDisplay().asyncExec(runnable2);

							}
						};
						Thread thread = new Thread(runnable); 
						thread.start();

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

	}

	private void tabItemClickHandler(final Table table, MouseEvent event) {

		//Where am I?
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

				final CCombo combo = tableCombo(table);
				final int col = column;
				// Add a listener to set the selected item back into the cell
				combo.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent event) {
						handleChangeMoniLevel();
					}

					private void handleChangeMoniLevel() {
						item.setText(col, combo.getText());
						item.setFont(MainGui.CONTENTFONT);
						// Update portfolio
						doChangeShareMoniLevel(table.getSelectionIndex(), combo.getText());
						modelControler.updateMoniAndPSCachedModels();
						//combo.dispose();
					}
				});

				for (MonitorLevel ml : MonitorLevel.values()) {
					combo.add(ml.getMonitorLevel());
				}

				tableComboEditor(combo, table, item, column);

			} 
			else if (column == Titles.DisplayedCurrency.ordinal()) { 

				final CCombo combo = tableCombo(table);
				final int col = column;
				combo.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent event) {
						getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
						try {
							handleChangeCurrency();
						} catch (Exception e) {
							LOGGER.error(e, e);
						} finally {
							getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
						}
					}

					private void handleChangeCurrency() {

						item.setText(col, combo.getText());
						item.setFont(MainGui.CONTENTFONT);
						doChangeDisplayedCurrency(table.getSelectionIndex(), combo.getText());
						final int tabindex = selectedPortfolioIdx();
						Runnable runnable = new Runnable() {
							public void run() {
								getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
								try {
									tabUpdateItemsFromPortfolio(tabindex, modelControler.getPortfolio(tabindex), new CursorChangerObserver(1, SWT.CURSOR_WAIT));
								} catch (Exception e) {
									LOGGER.error(e, e);
								} finally {
									getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
								}
							}
						};
						getDisplay().asyncExec(runnable);	

					}

				});

				for (Currency ml : Currency.values()) {
					combo.add(ml.name());
				}

				tableComboEditor(combo, table, item, column);

			} 
		}

	}

	protected CCombo tableCombo(final Table table) {
		final CCombo combo = new CCombo(table, SWT.READ_ONLY);
		combo.setLayout(new FillLayout(SWT.HORIZONTAL));
		combo.setFont(MainGui.DEFAULTFONT);
		return combo;
	}

	private void tableComboEditor(final CCombo combo, Table table, TableItem item, int column) {

		combo.select(combo.indexOf(item.getText(column)));

		final TableEditor comboEditor = new TableEditor(table);
		comboEditor.horizontalAlignment = SWT.LEFT;
		comboEditor.grabHorizontal= true;
		comboEditor.minimumWidth = table.getColumn(column).getWidth();

		// Set the focus on the dropdown and set into the editor
		comboEditor.setEditor(combo, item, column);

		combo.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				comboEditor.dispose();
				combo.dispose();
			}

			@Override
			public void focusGained(FocusEvent e) {
				combo.setVisible(true);
			}
		});

		combo.pack();
		combo.setFocus();

	}

	private void columnEditManagment(final TableItem tableItem, final int rowIdx, final TransactionType transactionType) {

		UserDialog inst;

		LOGGER.debug("Tables Items :" + tableItem.getText(0) + ";" + tableItem.getText(1) + ";" + tableItem.getText(2) + ";" + tableItem.getText(3));
		final int tabIdx = selectedPortfolioIdx();
		try {

			final SlidingPortfolioShare pstmp = modelControler.getSlidingShareInTab(tabIdx, rowIdx);
			Date newDate = EventSignalConfig.getNewDate();

			BigDecimal transactionPrice = BigDecimal.ZERO;
			try {
				Quotations quotationsInstance = QuotationsFactories.getFactory().getQuotationsInstance(pstmp.getStock(), newDate, true, pstmp.getTransactionCurrency(), ValidityFilter.CLOSE);
				transactionPrice = quotationsInstance.getClosestCloseForDate(newDate);
			} catch (Exception e1) {
				LOGGER.warn(e1);
			}
			BigDecimal proposedQuantity = pstmp.getQuantity(newDate);
			switch (transactionType) { 
			case AIN: //Buy
			{
				final Transaction transaction = new Transaction(proposedQuantity, transactionPrice, TransactionType.AIN, newDate);

				final TransactionPriceDialog selectPriceDialog = new TransactionPriceDialog(PortfolioComposite.this.getShell(), pstmp, transaction);
				ActionDialogAction action = new ActionDialogAction() {

					@Override
					public void action() {
						try {
							applyTransaction(tableItem, tabIdx, rowIdx, pstmp, transaction, selectPriceDialog.getOk(), selectPriceDialog.getResetLine());
						} catch (InvalidQuantityException e) {
							UserDialog inst = new UserDialog(getShell(), (e.getMessage() != null)?e.getMessage():e.toString(),null);
							inst.open();
						} finally {
							refreshChartData();
							refreshPortfolioTotalsInfos(tabIdx);
						}

					}
				};
				selectPriceDialog.setAction(action);
				selectPriceDialog.open();

				break;
			}
			case AOUT: //Sell
			{	
				final Transaction transaction = new Transaction(proposedQuantity, transactionPrice, TransactionType.AOUT, newDate);

				final TransactionPriceDialog selectPriceDialog = new TransactionPriceDialog(PortfolioComposite.this.getShell(), pstmp, transaction);
				ActionDialogAction action = new ActionDialogAction() {

					@Override
					public void action() {
						try {
							applyTransaction(tableItem, tabIdx, rowIdx, pstmp, transaction, selectPriceDialog.getOk(), selectPriceDialog.getResetLine());
						} catch (InvalidQuantityException e) {
							UserDialog inst = new UserDialog(getShell(), (e.getMessage() != null)?e.getMessage():e.toString(),null);
							inst.open();
						} finally {	
							refreshChartData();
							refreshPortfolioTotalsInfos(tabIdx);
						}
					}
				};
				selectPriceDialog.setAction(action);
				selectPriceDialog.open();

				break;
			}

			default: //Not modifiable
			{
				inst = new UserDialog(this.getShell(), "Column is immutable.", null);
				inst.open();
				break;
			}
			}

		} catch (Exception e) {
			LOGGER.warn(e, e);
			inst = new UserDialog(this.getShell(), (e.getMessage() != null)?e.getMessage():e.toString(),null);
			inst.open();
		}

	}

	//TODO create an add, remove update method (all in one) in Portfolio hierarchy
	private void applyTransaction(TableItem tableItem, int tabIdx, int rowIdx, SlidingPortfolioShare portfolioShare, Transaction transaction, Boolean proceed, Boolean resetToNewLine) throws InvalidQuantityException {

		if (proceed) {

			Portfolio portfolio =  modelControler.getPortfolio(tabIdx);
			Table ttomod = (Table) cTabItem[tabIdx].getData();

			if (!resetToNewLine) {//Update

				//Update existing 
				portfolio.updateShare(portfolioShare, transaction.getQuantity(), transaction.getDate(), transaction.getTransactionSharePrice(), transaction.getModtype());
				tabUpdateTableItem(tableItem, portfolioShare);

			} else {//Replace

				//Remove existing
				portfolio.rawRemoveShare(portfolioShare);	
				ttomod.remove(rowIdx);

				//Then Add as new
				if (transaction.getQuantity().compareTo(BigDecimal.ZERO) > 0) {

					try {

						PortfolioShare replacementPS = portfolio.addOrUpdateShare(
								portfolioShare.getStock(), transaction.getQuantity(), transaction.getDate(), //EventSignalConfig.getNewDate(), 
								transaction.getTransactionSharePrice(), portfolioShare.getMonitorLevel(), portfolioShare.getTransactionCurrency(),
								TransactionType.AIN);

						SlidingPortfolioShare replacememntSlidingPS = tabBuildSlidingPortfolioShare(replacementPS);
						modelControler.addOrUpdateSlidingShareToTab(tabIdx, replacememntSlidingPS);

					} catch (InvalidAlgorithmParameterException e) {
						LOGGER.error(e,e);
					}

					refreshChartData();
				}

			}

			modelControler.updateMoniAndPSCachedModels();

		} else {
			tabUpdateTableItem(tableItem, portfolioShare);
		}

	}

	void refreshPortfolioTotalsInfos(final Integer currentPortfolioTab) {

		Runnable runnable = new Runnable() {
			public void run() {
				if (currentPortfolioTab != -1) {

					Portfolio currentPortfolio = modelControler.getPortfolio(currentPortfolioTab);
					final Currency portfolioCurrency = currentPortfolio.inferPortfolioCurrency();

					if (LOGGER.isDebugEnabled()) {
						try {
							LOGGER.debug(currentPortfolio.extractPortfolioTransactionLog(
									(slidingStartAnchor.getSelection()) ? chartsComposite.getSlidingStartDate() : DateFactory.dateAtZero(),
											(slidingEndAnchor.getSelection()) ? chartsComposite.getSlidingEndDate() : EventSignalConfig.getNewDate()));
						} catch (Throwable e) {
							e.printStackTrace();
						}
					}
					
					final Date currentStartDate = DateFactory.dateAtZero();
					final Date currentEndDate = EventSignalConfig.getNewDate();
					;
					Runnable anchorCheckRunnable = new Runnable() {
						public void run() {
							if (slidingStartAnchor.getSelection()) {
								currentStartDate.setTime(chartsComposite.getSlidingStartDate().getTime());
							}
							if (slidingEndAnchor.getSelection()) {
								currentEndDate.setTime(chartsComposite.getSlidingEndDate().getTime());
							}
						}
					};
					getDisplay().syncExec(anchorCheckRunnable);

					BigDecimal value;
					BigDecimal basis;
					BigDecimal gainTotal;
					BigDecimal gainTotalPercent;
					BigDecimal gainUnReal;
					BigDecimal gainUnRealPercent;
					BigDecimal totalInAmountEver;
					BigDecimal totalOutAmountEver;
					try {
						Runnable cursorOn = new Runnable() {
							public void run() {
								getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
								portfolioInfosTable.remove(0);
								TableItem item = new TableItem(portfolioInfosTable, SWT.NONE);
								item.setFont(MainGui.CONTENTFONT);
							}
						};
						getDisplay().syncExec(cursorOn);

						value = currentPortfolio.getValue(currentStartDate, currentEndDate);
						basis = currentPortfolio.getBasis(currentStartDate, currentEndDate);
						gainTotal = currentPortfolio.getGainTotal(currentStartDate, currentEndDate);
						gainTotalPercent = currentPortfolio.getGainTotalPercent(currentStartDate, currentEndDate);
						gainUnReal = currentPortfolio.getGainUnReal(currentStartDate, currentEndDate);
						gainUnRealPercent = currentPortfolio.getGainUnRealPercent(currentStartDate, currentEndDate);
						totalInAmountEver = currentPortfolio.getTotalInAmountEver(currentStartDate, currentEndDate);
						totalOutAmountEver = currentPortfolio.getTotalOutAmountEver(currentStartDate, currentEndDate);

					} catch (Exception e) {
						throw new RuntimeException(e);
					} finally {
						Runnable cursorOff = new Runnable() {
							public void run() {
								getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
							}
						};
						getDisplay().syncExec(cursorOff);
					}

					Runnable runnable = new Runnable() {
						public void run() {

							if (currentPortfolioTab != getCurrentTabSelection())
								return;
							
							isLatestTransactionsOnly.setSelection(currentPortfolio.isLatestTransactionsOnly());

							TableItem item = portfolioInfosTable.getItem(0);

							item.setText(PortfolioInfosTitles.Value.ordinal(), moneysFormat.format(value) + " " + portfolioCurrency.toString());
							item.setText(PortfolioInfosTitles.Basis.ordinal(), moneysFormat.format(basis) + " " + portfolioCurrency.toString());
							item.setText(PortfolioInfosTitles.TotalGain.ordinal(),
									signumRoundedFormat(moneysFormat, gainTotal) + " " + portfolioCurrency.toString());
							item.setText(PortfolioInfosTitles.TotalGainPercent.ordinal(), signumRoundedFormat(percentFormat, gainTotalPercent));
							item.setText(PortfolioInfosTitles.UnrGain.ordinal(),
									signumRoundedFormat(moneysFormat, gainUnReal) + " " + portfolioCurrency.toString());
							item.setText(PortfolioInfosTitles.UnrGainPercent.ordinal(), signumRoundedFormat(percentFormat, gainUnRealPercent));
							item.setText(PortfolioInfosTitles.MoneyIn.ordinal(),
									moneysFormat.format(totalInAmountEver) + " " + portfolioCurrency.toString());
							item.setText(PortfolioInfosTitles.MoneyOut.ordinal(),
									moneysFormat.format(totalOutAmountEver) + " " + portfolioCurrency.toString());

							packColumns(portfolioInfosTable, PortfolioInfosTitles.values().length, 80);
							portfolioInfosGroup.layout();

						};
					};
					getDisplay().asyncExec(runnable);

				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();

	}

	protected String signumRoundedFormat(NumberFormat numberFormat, BigDecimal bigDecimalValue) {
		int signum = bigDecimalValue.signum();
		String signumTxt = (signum == -1)?"-":"";
		String signumRoundedTxt = signumTxt+numberFormat.format(bigDecimalValue.abs());
		return signumRoundedTxt;
	}

	private void tabUpdateTableItem(TableItem ti, SlidingPortfolioShare portfolioShare) {

		ti.setData(portfolioShare);

		ti.setFont(smallFont);

		ti.setText(Titles.Name.ordinal(), portfolioShare.getName());
		ti.setText(Titles.Symbol.ordinal(), portfolioShare.getSymbol());
		ti.setText(Titles.Isin.ordinal(), portfolioShare.getIsin());

		//
		if (portfolioShare.getDisplayOnChart()) {
			BigDecimal todaysPriceClose = portfolioShare.getTodaysPriceClose();
			BigDecimal todaysValue = portfolioShare.getTodaysValue();
			BigDecimal todaysPriceAvgBuy = portfolioShare.getTodaysPriceAvgBuy();
			BigDecimal todaysPriceUnitCost = portfolioShare.getTodaysPriceUnitCost();
			BigDecimal todaysGainTotalPercent = portfolioShare.getTodaysGainTotalPercent();
			BigDecimal todaysGainRealPercent = portfolioShare.getTodaysGainRealPercent();
			BigDecimal todaysGainUnrealPercent = portfolioShare.getTodaysGainUnrealPercent();
			BigDecimal todaysPriceZeroGainWeighted = portfolioShare.getTodaysPriceZeroGainWeighted();
			BigDecimal gainTotalWeightedPercent = portfolioShare.getGainTotalWeightedPercent();


			ti.setText(Titles.Close.ordinal(), moneysFormat.format(todaysPriceClose));
			ti.setText(Titles.Value.ordinal(), moneysFormat.format(todaysValue));
			ti.setText(Titles.AvgBuyPrc.ordinal(), moneysFormat.format(todaysPriceAvgBuy));
			ti.setText(Titles.CostPerUnit.ordinal(), moneysFormat.format(todaysPriceUnitCost));

			ti.setText(Titles.TotalPercentGain.ordinal(), signumRoundedFormat(percentFormat, todaysGainTotalPercent));
			ti.setText(Titles.RPercentGain.ordinal(), signumRoundedFormat(percentFormat, todaysGainRealPercent));
			ti.setText(Titles.UrPercentGain.ordinal(), signumRoundedFormat(percentFormat, todaysGainUnrealPercent));

			ti.setText(Titles.ZeroWGainPrc.ordinal(), moneysFormat.format(todaysPriceZeroGainWeighted));

			ti.setText(Titles.WTotalPercentGain.ordinal(), signumRoundedFormat(percentFormat, gainTotalWeightedPercent));
		}
		//

		ti.setText(Titles.Monitor.ordinal(), portfolioShare.getMonitorLevel().getMonitorLevel());
		ti.setText(Titles.DisplayedCurrency.ordinal(), portfolioShare.getDisplayedCurrency().name());

	}

	private void sortColumn(String colStr) {

		int tabindex = selectedPortfolioIdx();
		if (tabindex == -1) return;

		List<SlidingPortfolioShare> list = new ArrayList<SlidingPortfolioShare>(modelControler.getSlidingSharesInTab(tabindex));

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
			case Symbol:
				Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
					public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
						int ret = o1.getSymbol().trim().toLowerCase().compareTo(o2.getSymbol().trim().toLowerCase());
						ret = (ret == 0)? o1.getStock().compareTo(o2.getStock()):ret;
						return ret;
					}						
				});
				break;			
			case Isin:
				Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
					public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
						int ret = o1.getIsin().trim().toLowerCase().compareTo(o2.getIsin().trim().toLowerCase());
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
			case TotalPercentGain:
				Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
					public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
						BigDecimal o2TodaysGainTotalPercent = o2.getTodaysGainTotalPercent();
						BigDecimal o1TodaysGainTotalPercent = o1.getTodaysGainTotalPercent();
						if (o2.getTodaysQuantity().compareTo(BigDecimal.ZERO) == 0) o2TodaysGainTotalPercent = new BigDecimal(-9999);
						if (o1.getTodaysQuantity().compareTo(BigDecimal.ZERO) == 0) o1TodaysGainTotalPercent = new BigDecimal(-9999);
						int ret = o2TodaysGainTotalPercent.compareTo(o1TodaysGainTotalPercent);
						ret = (ret == 0)? o1.getStock().compareTo(o2.getStock()):ret;
						return ret;
					}						
				});
				break;
			case RPercentGain:
				Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
					public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
						BigDecimal o2TodaysGainRealPercent = o2.getTodaysGainRealPercent();
						BigDecimal o1TodaysGainRealPercent = o1.getTodaysGainRealPercent();
						if (o2.getTodaysQuantity().compareTo(BigDecimal.ZERO) == 0) o2TodaysGainRealPercent = new BigDecimal(-9999);
						if (o1.getTodaysQuantity().compareTo(BigDecimal.ZERO) == 0) o1TodaysGainRealPercent = new BigDecimal(-9999);
						int ret = o2TodaysGainRealPercent.compareTo(o1TodaysGainRealPercent);
						ret = (ret == 0)? o1.getStock().compareTo(o2.getStock()):ret;
						return ret;
					}						
				});
				break;
			case UrPercentGain:
				Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
					public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
						BigDecimal o2TodaysGainUnrealPercent = o2.getTodaysGainUnrealPercent();
						BigDecimal o1TodaysGainUnrealPercent = o1.getTodaysGainUnrealPercent();
						if (o2.getTodaysQuantity().compareTo(BigDecimal.ZERO) == 0) o2TodaysGainUnrealPercent = new BigDecimal(-9999);
						if (o1.getTodaysQuantity().compareTo(BigDecimal.ZERO) == 0) o1TodaysGainUnrealPercent = new BigDecimal(-9999);
						int ret = o2TodaysGainUnrealPercent.compareTo(o1TodaysGainUnrealPercent);
						ret = (ret == 0)? o1.getStock().compareTo(o2.getStock()):ret;
						return ret;
					}						
				});
				break;
			case WTotalPercentGain:
				Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
					public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
						BigDecimal o2GainTotalWeightedPercent = o2.getGainTotalWeightedPercent();
						BigDecimal o1GainTotalWeightedPercent = o1.getGainTotalWeightedPercent();
						if (o2.getTodaysQuantity().compareTo(BigDecimal.ZERO) == 0) o2GainTotalWeightedPercent = new BigDecimal(-9999);
						if (o1.getTodaysQuantity().compareTo(BigDecimal.ZERO) == 0) o1GainTotalWeightedPercent = new BigDecimal(-9999);
						int ret = o2GainTotalWeightedPercent.compareTo(o1GainTotalWeightedPercent);
						ret = (ret == 0)? o1.getStock().compareTo(o2.getStock()):ret;
						return ret;
					}						
				});
				break;
			case DisplayedCurrency :
				Collections.sort(list, new Comparator<SlidingPortfolioShare>() {
					public int compare(SlidingPortfolioShare o1, SlidingPortfolioShare o2) {
						int ret = o2.getDisplayedCurrency().compareTo(o1.getDisplayedCurrency());
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
			for (SlidingPortfolioShare slidingPortfolioShare : list) {
				modelControler.addOrUpdateSlidingShareToTab(tabindex, slidingPortfolioShare);
			}
			refreshChartData();
		}

	}


	private ArrayList<SlidingPortfolioShare> tabBuildSlidingPortfolioShareList(Collection<PortfolioShare> portfolioShares) {

		ArrayList<SlidingPortfolioShare> list = new ArrayList<SlidingPortfolioShare>();

		for (PortfolioShare portfolioShare : portfolioShares) {
			SlidingPortfolioShare slidingPS = tabBuildSlidingPortfolioShare(portfolioShare);
			list.add(slidingPS);
		}

		return list;
	}

	private SlidingPortfolioShare tabBuildSlidingPortfolioShare(PortfolioShare portfolioShare) {

		java.awt.Color paint = (java.awt.Color) PortfolioComposite.PAINTS[getNextColor()];
		Color psColor = new Color(getDisplay(),paint.getRed(),paint.getGreen(),paint.getBlue());
		SlidingPortfolioShare slidingPS = new SlidingPortfolioShare(portfolioShare, 
				chartsComposite.getSlidingStartDate(), chartsComposite.getSlidingEndDate(), 
				slidingStartAnchor.getSelection(), slidingEndAnchor.getSelection(),
				psColor);

		return slidingPS;
	}

	public void handleSaveAllPortfolios(TypedEvent evt) {
		hibernatePortfolios();
	}

	private void handleRootShellClosed(Event evt) {
		LOGGER.debug("dialogShell.shellClosed, event="+evt);
		hibernatePortfolios();
	}

	private void hibernatePortfolios() {

		try {
			PortfolioMgr.getInstance().hibStorePortfolio();
		} catch (RuntimeException e) {
			LOGGER.error("",e);
			UserDialog inst = new UserDialog(getShell(), "Error While updating data base \n"+e,null);
			inst.open();
		}

	}


	private void handleAddPortoflio() {

		final ActionDialogForm actionDialog = new ActionDialogForm(getShell(), "Add", null, MainGui.APP_NAME+" - Add a new portfolio");
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

		final CCombo curCombo = new CCombo(actionDialog.getParent(), SWT.NONE);
		curCombo.setToolTipText("Portfolio main currency.\nIf left to "+Currency.NAN.name()+", the portfolio can host several currencies. This currency is used the calculation of totals.");
		SortedSet<Currency> currencies = new TreeSet<Currency>(new Comparator<Currency>() {
			@Override
			public int compare(Currency o1, Currency o2) {
				if (o1.equals(o2)) return 0;
				if (o1.equals(Currency.NAN)) return -1;
				if (o2.equals(Currency.NAN)) return 1;
				return o1.name().compareTo(o2.name());
			}
		});

		currencies.addAll(Arrays.asList(Currency.values()));

		for (Currency currency : currencies) {
			curCombo.add(currency.name());
		}
		curCombo.select(0);

		ActionDialogAction actionDialogAction = new ActionDialogAction() {
			@Override
			public void action() {
				actionDialog.values[0] = newPortfolioText.getText();
				actionDialog.values[1] = Currency.valueOf(curCombo.getText());
				addPortfolio(new UserPortfolio((String) actionDialog.values[0], (Currency) actionDialog.values[1]));
				refreshChartData();
				refreshPortfolioTotalsInfos(portfolioCTabFolder.getSelectionIndex());
			}
		};
		actionDialog.setControl(newPortfolioText, curCombo);
		actionDialog.setAction(actionDialogAction);
		actionDialog.open();

	}

	private void addPortfolio(Portfolio portfolio) {
		try {
			doAddPortfolio(portfolio);
			modelControler.updateMoniAndPSCachedModels();
			tabAddNew(portfolio);
		} catch (InvalidAlgorithmParameterException e) {
			LOGGER.warn(e);
			UserDialog inst = new UserDialog(this.getShell(), e.getMessage(), null);
			inst.open();
		}
	}


	private void doAddPortfolio(Portfolio portfolio) throws InvalidAlgorithmParameterException {
		PortfolioMgr.getInstance().addPortfolio(portfolio);
	}

	private int tabAddNew(Portfolio portfolio) {

		//Ui
		CTabItem[] cTabItemTmp = new CTabItem[cTabItem.length+1];
		System.arraycopy(cTabItem, 0, cTabItemTmp, 0, cTabItem.length);

		cTabItem = cTabItemTmp;

		tabAddOneTab(cTabItem.length-1, portfolio);
		cTabItem[cTabItem.length-1].getParent().setSelection(cTabItem.length-1);

		tabUpdateItemsFromPortfolio(cTabItem.length-1, portfolio, new CursorChangerObserver(1, SWT.CURSOR_WAIT));

		return cTabItem.length-1;
	}


	private void handleAddShareToTab(EventObject evt) {

		int tabi = selectedPortfolioIdx();
		if (tabi == -1) return;
		try {
			//Open selection window
			Collection<PortfolioShare> pSharesInTab = modelControler.getPortfolio(tabi).getListShares().values();
			NewPortfolioItemDialog.showUI(tabi, pSharesInTab, getShell(), this);

		} catch (Exception e) {
			LOGGER.error(e,e);
			UserDialog inst = new UserDialog(this.getShell(), "Error adding share. \n"+e,null);
			inst.open();
		}

	}


	class AddShareRunnable extends Observable implements Runnable {

		private Collection<Stock> selectedStocks;
		private List<SlidingPortfolioShare> addedSlidings;
		private MonitorLevel selectedMonitorLevel;
		private BigDecimal selectedQuantity;
		private int tabIdx;

		public AddShareRunnable( int tabIdx, Collection<Stock> selectedStocks, MonitorLevel selectedMonitorLevel, BigDecimal selectedQuantity, Observer observer) {
			super();
			this.selectedStocks = selectedStocks;
			this.selectedMonitorLevel = selectedMonitorLevel;
			this.selectedQuantity = selectedQuantity;
			this.tabIdx = tabIdx;
			this.addObserver(observer);

			this.addedSlidings = new ArrayList<SlidingPortfolioShare>();
		}

		@Override
		public void run() {

			synchronized (modelControler) {

				try {

					setChanged();
					notifyObservers(new ObserverMsg(null, ObsKey.INITMSG , new Integer(selectedStocks.size())));

					QuotationUpdate quotationUpdate = new QuotationUpdate();
					Set<Observer> obss = new HashSet<Observer>();
					obss.add(logComposite);
					quotationUpdate.addObservers(obss);
					try {
						quotationUpdate.getQuotesFor(selectedStocks);
					} catch (QuotationUpdateException e) {
						UserDialog dialog = new UserDialog(getShell(), "Some of the shares you have selected don't have any available quotation data", e.getMessage());
						dialog.open();
					}

					Collection<PortfolioShare> listOfNewSharesPortfolioShares = doAddSharesToPortfolio(tabIdx, selectedMonitorLevel, selectedQuantity, selectedStocks);
					for (PortfolioShare portfolioShare : listOfNewSharesPortfolioShares) {

						SlidingPortfolioShare slidingPS = tabBuildSlidingPortfolioShare(portfolioShare);
						modelControler.addOrUpdateSlidingShareToTab(tabIdx, slidingPS);
						this.addedSlidings.add(slidingPS);

					}

				} catch (Exception e) {
					LOGGER.error(e,e);
					UserDialog inst = new UserDialog(getShell(), "Error adding share. \n"+e,null);
					inst.open();
				} finally {
					setChanged();
					notifyObservers(new ObserverMsg(null, ObsKey.DONE));
				}

			}
		}

		public List<SlidingPortfolioShare> getAddedSlidings() {
			return addedSlidings;
		}
	}

	public void addShares(int tabIdx, Set<Stock> selectedStocks, BigDecimal selectedQuantity, MonitorLevel selectedMonitorLevel) {

		LOGGER.info("adding Shares : "+selectedStocks);
		logComposite.initRefresh(this);
		getDisplay().syncExec(new AddShareRunnable(tabIdx, selectedStocks, selectedMonitorLevel, selectedQuantity, logComposite));

		getDisplay().asyncExec(new Runnable() {
			public void run() {
				endRefreshAction(new ArrayList<Exception>());
				refreshChartData();
			}

		});

	}

	private Collection<PortfolioShare> doAddSharesToPortfolio(int tabi, MonitorLevel monitorLevel, BigDecimal quantity, Collection<Stock> listStock) {

		Set<PortfolioShare> listOfBoughtShares = new HashSet<PortfolioShare>();

		for (Stock stock : listStock) {

			PortfolioShare portfolioShare;
			try {
				Portfolio portfolio = modelControler.getPortfolio(tabi);
				portfolioShare = portfolio.addOrUpdateShareForQuantity(stock, quantity, EventSignalConfig.getNewDate(), monitorLevel, stock.getMarketValuation().getCurrency());
				listOfBoughtShares.add(portfolioShare);

			} catch (InvalidAlgorithmParameterException e) {
				String message = "Invalid operation " + stock;
				LOGGER.warn(message, e);
				UserDialog inst = new UserDialog(this.getShell(), message+"\n"+e.getMessage()+"\n"+e.toString(), null);
				inst.open();

			} catch (InvalidQuantityException e) {
				String message = "Wrong quantity for " + stock;
				LOGGER.warn(message, e);
				UserDialog inst = new UserDialog(this.getShell(), message+"\n"+e.getMessage()+"\n"+e.toString(), null);
				inst.open();

			} catch (NoQuotationsException e) {
				String message = "No quotations for " + stock;
				LOGGER.warn(message, e);
				UserDialog inst = new UserDialog(this.getShell(), message+"\n"+e.getMessage()+"\n"+e.toString(), null);
				inst.open();
			}

			modelControler.updateMoniAndPSCachedModels();

		}

		return listOfBoughtShares;

	}

	private void handleCancelPortfolioModifications(TypedEvent evt) {

		PortfolioMgr.getInstance().cancelModifications();
		modelControler.updateMoniAndPSCachedModels();
		tabResetAllTabs();
		refreshPortfolioTotalsInfos(portfolioCTabFolder.getSelectionIndex());
	}

	public void tabResetAllTabs() {

		int index = selectedPortfolioIdx();
		portfolioCTabFolder.setSelection(-1);

		for (int i = 0; i < cTabItem.length; i++) {
			cTabItem[i].dispose();
		}
		cTabItem = new CTabItem[0];

		if (index != -1) {
			tabBuildAllTabs(index);
			portfolioCTabFolder.setSelection(index);
		}

	}

	private void  removeSelectedPortfolio() {
		int tabindex = selectedPortfolioIdx();
		if (tabindex != -1) {
			CTabItem tab = portfolioCTabFolder.getSelection();
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
		doRemovePortfolio(portfolioToRm);

		modelControler.updateMoniAndPSCachedModels();

	}

	private void doRemovePortfolio(Portfolio portfolioToRm) {
		PortfolioMgr.getInstance().removePortfolio(portfolioToRm);
	}

	public void loadGnucashAdvPortfolio() {

		final String transactionFile[] = selectFilePaths(MainGui.APP_NAME+" - Select your GNUCASH 'Transaction report' HTML export.");
		GnuCashTransactionReportParser gnuCashTransactionReportParser = new GnuCashTransactionReportParser();

		try {
			if (transactionFile.length > 0) {
				gnuCashTransactionReportParser.parse(transactionFile[0]);
			} else {
				LOGGER.warn("No transaction file selected. Transactions won't be updated.");
			}
		} catch (final Exception e) {
			PortfolioComposite.LOGGER.error("Can't read GNUCASH transaction file : "+ transactionFile[0], e);
			Runnable runnable = new Runnable() {
				public void run() {
					UserDialog errorDialog = new UserDialog(getShell(), "Can't read GNUCASH transaction file : " + transactionFile[0], e.toString());
					errorDialog.open();
				}
			};
			getDisplay().asyncExec(runnable);
			return;
		}

		String[] paths = selectFilePaths(MainGui.APP_NAME+" - Select any of your GNUCASH 'Advanced Portfolio' HTML exports.");
		final GnuCashAdvPortfolioParser gnuCashAdvPortfolioParser = new GnuCashAdvPortfolioParser(DataSource.getInstance().getShareDAO(), PortfolioMgr.getInstance().getCurrencyConverter());

		for (String path : paths) {
			String portfolioName = gnuCashAdvPortfolioParser.extractName(path.substring(path.lastIndexOf(File.separator)));
			this.processGnuCashPath(gnuCashAdvPortfolioParser, path, portfolioName);
		}
	}

	public void loadGnucashAdvPortfolioForAutoPortfolio() {
		throw new NotImplementedException();
	}

	private void processGnuCashPath(GnuCashAdvPortfolioParser gnuCashAdvPortfolioParser, final String path, final String  newPortfolioName) {

		UserPortfolio oldPortfolio = null;

		//Getting the old out
		try {
			oldPortfolio = (UserPortfolio) PortfolioMgr.getInstance().getPortfolio(newPortfolioName);
			Runnable runnable = new Runnable() {
				public void run() {
					Integer tabindex = tabGetTabIdxFor(newPortfolioName);
					if (tabindex != -1) {
						removePortfolio(tabindex, cTabItem[tabindex]);
					}
				}
			};
			getDisplay().syncExec(runnable);
		} catch (IllegalArgumentException e) {
			//Ignoring non existent old portfolio
			LOGGER.info(newPortfolioName + ", found in import file name is a new portfolio.");
		}

		try {

			//Parsing
			final Portfolio gnucashPortfolio = gnuCashAdvPortfolioParser.parse(path, newPortfolioName, oldPortfolio);

			//Add new
			Runnable runnable = new Runnable() {
				public void run() {
					addPortfolio(gnucashPortfolio);
				}
			};
			getDisplay().syncExec(runnable);

		} catch (NoResultException e) {
			noResultExceptionHandling(gnuCashAdvPortfolioParser, path, e, oldPortfolio);

		} catch (final Exception e) {
			LOGGER.error("GNUCASH report import failed : "+path, e);
			final Portfolio fold = oldPortfolio;
			Runnable runnable = new Runnable() {
				public void run() {
					if (fold != null) addPortfolio(fold);
					UserDialog errorDialog = new UserDialog(getShell(), "GNUCASH report import failed : " + path, e.toString());
					errorDialog.open();
				}
			};
			getDisplay().syncExec(runnable);

		}
	}

	private Integer tabGetTabIdxFor(String portfolioName) {

		for (int i=0; i < cTabItem.length;i++) {
			CTabItem tab = cTabItem[i];
			if (tab.getText().equals(portfolioName)) return i;
		}
		return -1;
	}

	private void noResultExceptionHandling(GnuCashAdvPortfolioParser gnuCashAdvPortfolioParser, String path, NoResultException e, final Portfolio oldPortfolio) {

		final String erreurMessage = "Error importing file : " + path +"\n"+ e.toString();
		String addMess = null;
		if (!gnuCashAdvPortfolioParser.getNotFoundStocks().isEmpty()) {
			addMess = "The following errors occurred : \n\n";
			for (String emess : gnuCashAdvPortfolioParser.getNotFoundStocks()) {
				addMess = addMess.concat(emess).concat("\n");
			}
		}
		final String faddMess = addMess;
		Runnable runnable = new Runnable() {
			public void run() {
				if (oldPortfolio != null) addPortfolio(oldPortfolio);
				UserDialog errorDialog = new UserDialog(PortfolioComposite.this.getShell(), erreurMessage, faddMess);
				errorDialog.open();
			}
		};
		getDisplay().syncExec(runnable);

	}

	private String[] selectFilePaths(final String title) {

		final List<String> paths = new ArrayList<String>();
		Runnable runnable = new Runnable() {
			public void run() {
				String[] filterExtensions = { "*.html" };
				FileDialog fileDialog = new FileDialog(new Shell(getDisplay()), SWT.MULTI);
				fileDialog.setText(title);
				fileDialog.setFilterExtensions(filterExtensions);
				String selectedPath = fileDialog.open();
				String[] selectedFiles = fileDialog.getFileNames();
				if (selectedFiles.length > 0) {
					//paths = new String[selectedFiles.length];
					for (int i = 0; i < selectedFiles.length; i++)
						paths.add(new File(selectedPath).getParent() + File.separator + selectedFiles[i]);
				}
			}
		};
		getDisplay().syncExec(runnable);
		return paths.toArray(new String[0]);
	}

	public void slidingDateChange() {

		int si = selectedPortfolioIdx();

		if (si != -1) {
			List<SlidingPortfolioShare> slidingPSs = modelControler.getSlidingSharesInTab(si);
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
						}

						tabUpdateTableItem(ttomod.getItem(itemIdx), portfolioShare);

					}
					if (isSlidingPrices) {
						packColumns(ttomod, Titles.values().length, 50);
						refreshPortfolioTotalsInfos(si);
					}

				} finally {
					this.getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
				}
			}
		}
	}



	private void packColumns(Table ttomod, int nbColumns, int minSize) {

		for (int j = 0; j < nbColumns; j++) {

			if (j > 0) {
				int availableSize = (this.getClientArea().width -ttomod.getColumn(0).getWidth())/(nbColumns-1);
				if (availableSize <= minSize) {
					ttomod.getColumn(j).setWidth(minSize);
				} 
				else if (availableSize > minSize) {
					ttomod.getColumn(j).pack();
					int actualPackedWidth = ttomod.getColumn(j).getWidth();
					if (actualPackedWidth > availableSize) ttomod.getColumn(j).setWidth(availableSize);
				}
			} else {
				ttomod.getColumn(j).pack();
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

		int tabindex = portfolioCTabFolder.getSelectionIndex();
		Portfolio portfolio = modelControler.getPortfolio(tabindex);

		tabUpdateItemsFromPortfolio(tabindex, portfolio, new CursorChangerObserver(1, SWT.CURSOR_WAIT));

		if (tabindex == -1) return;

		if (isVisible()) {
			for (final Exception exception : exceptions) {
				if (exception instanceof QuotatationRefreshException) {
					UserDialog dialog = new UserDialog(getShell(), "Couldn't refresh all quotations\n", exceptions.toString());
					exceptions.clear();
					dialog.open();
					break;
				}
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
		}

		if (isVisible()) {
			Shell[] childrenShells = this.getShell().getShells();
			for (Shell child : childrenShells) {
				if (child.getText().contains("Warning")) child.forceActive();
			}
		}

	}

	@Override
	public Date getAnalysisStartDate() {
		return chartsComposite.getAnalysisStartDate();
	}

	@Override
	public Date getAnalysisEndDate() {
		return chartsComposite.getAnalysisEndDate();
	}

	public int getCurrentTabSelection() {
		return selectedPortfolioIdx();
	}

	public List<SlidingPortfolioShare> getCurrentTabContent() {
		return modelControler.getSlidingSharesInTab(portfolioCTabFolder.getSelectionIndex());
	}

	public SlidingPortfolioShare getCurrentShareSelection() {
		Integer currentShareSelectionIdx = getCurrentShareSelectionIdx();
		if (currentShareSelectionIdx != -1) {
			TableItem selection =  ((Table) portfolioCTabFolder.getSelection().getData()).getItems()[currentShareSelectionIdx];
			if (selection != null) return (SlidingPortfolioShare) selection.getData();
		}
		return null;
	}

	public Integer getCurrentShareSelectionIdx() {
		Table table = (Table) portfolioCTabFolder.getSelection().getData();
		Integer selection = table.getSelectionIndex();
		return selection;
	}

	public void updateMoniAndPSCachedModels() {
		modelControler.updateMoniAndPSCachedModels();
	}



	private void doChangeShareMoniLevel(int itemIdx, String txt) {
		SlidingPortfolioShare pstmp =  modelControler.getSlidingShareInTab(selectedPortfolioIdx(), itemIdx);
		pstmp.setMonitorLevel(MonitorLevel.valueOfString(txt));
	}

	private void doChangeDisplayedCurrency(int itemIdx, String text) {
		SlidingPortfolioShare pstmp =  modelControler.getSlidingShareInTab(selectedPortfolioIdx(), itemIdx);
		pstmp.setDisplayedCurrency(Currency.valueOf(text));
	}

	private int selectedPortfolioIdx() {
		int tabi = portfolioCTabFolder.getSelectionIndex();
		if (tabi == -1 && portfolioCTabFolder.getItems().length == 0) {
			String erreur = "No portfolio is defined at the moment.\nYou must create at least one portfolio for this to work.(Portfolios -> Add a portfolio...)";
			UserDialog inst = new UserDialog(getShell(), erreur,null);
			inst.open();
			LOGGER.warn(erreur, new Exception());
		}
		return tabi;
	}

	public void viewPortfolioTransactions() {

		final Portfolio portfolio = modelControler.getPortfolio(selectedPortfolioIdx());
		String dateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String transactionExportName = portfolio.getName()+"_"+dateStr;
		final Date startDate = (slidingStartAnchor.getSelection())?chartsComposite.getSlidingStartDate():DateFactory.dateAtZero();
		final Date endDate = (slidingEndAnchor.getSelection())?chartsComposite.getSlidingEndDate():EventSignalConfig.getNewDate();

		TransactionExtractor te = new TransactionExtractor() {	
			@Override
			protected String run() throws Throwable {
				return portfolio.extractPortfolioTransactionLog(startDate, endDate);
			}
		};

		extractTransactions(portfolio, transactionExportName, te);

	}


	public void transposePortfolioTransactions() {

		final ActionDialogForm actionDialog = new ActionDialogForm(getShell(), "Ok", null, "Transposition settings");

		//Currency
		final Label currencyLabel = new Label(actionDialog.getParent(), SWT.NONE);
		currencyLabel.setBackground(MainGui.pOPUP_BG);
		currencyLabel.setText("Target currency");
		final CCombo currencyListCombo = new CCombo(actionDialog.getParent(), SWT.NONE);
		currencyListCombo.setEditable(false);

		SortedSet<Currency> currencies = new TreeSet<Currency>(new Comparator<Currency>() {
			@Override
			public int compare(Currency o1, Currency o2) {
				return o1.name().compareTo(o2.name());
			}
		});

		currencies.addAll(Arrays.asList(Currency.values()));
		int i = 0;
		int gbpIdx = i;
		for (Currency currency : currencies) {
			if (currency.equals(Currency.GBP)) gbpIdx = i;
			currencyListCombo.add(currency.name());
			i++;
		}
		currencyListCombo.select(gbpIdx);

		//Capital gain period
		final Date slidingEnd = (slidingEndAnchor.getSelection())?chartsComposite.getSlidingEndDate():EventSignalConfig.getNewDate();
		final DateFormat dateInstance = new SimpleDateFormat("yyyy-MM-dd");
		final Label startPeriodLabel = new Label(actionDialog.getParent(), SWT.NONE);
		startPeriodLabel.setBackground(MainGui.pOPUP_BG);
		startPeriodLabel.setText("Capital Gain from");
		final Text startPeriodTxt = new Text(actionDialog.getParent(), SWT.NONE);
		Calendar sCalendar = Calendar.getInstance();
		sCalendar.setTime(slidingEnd);
		sCalendar.add(Calendar.YEAR, (sCalendar.get(Calendar.MONTH) < 3)?-1:0);
		sCalendar.set(Calendar.MONTH, 3);
		sCalendar.set(Calendar.DAY_OF_MONTH, 6);
		startPeriodTxt.setText(dateInstance.format(sCalendar.getTime()));
		final Label endPeriodLabel = new Label(actionDialog.getParent(), SWT.NONE);
		endPeriodLabel.setBackground(MainGui.pOPUP_BG);
		endPeriodLabel.setText("Capital Gain to");
		final Text endPeriodTxt = new Text(actionDialog.getParent(), SWT.NONE);
		endPeriodTxt.setText(dateInstance.format(slidingEnd));

		final NumberFormat percentInstance = NumberFormat.getPercentInstance();
		//Transaction Fees
		final Label transactionFeeLabel = new Label(actionDialog.getParent(), SWT.NONE);
		transactionFeeLabel.setBackground(MainGui.pOPUP_BG);
		transactionFeeLabel.setText("Transaction Fee");
		final Text transactionFeeTxt = new Text(actionDialog.getParent(), SWT.NONE);
		transactionFeeTxt.setText(percentInstance.format(0.00));
		//Exchange Fees
		final Label exchangeFeeLabel = new Label(actionDialog.getParent(), SWT.NONE);
		exchangeFeeLabel.setBackground(MainGui.pOPUP_BG);
		exchangeFeeLabel.setText("Exchange Fee");
		final Text exchangeFeeTxt = new Text(actionDialog.getParent(), SWT.NONE);
		exchangeFeeTxt.setText(percentInstance.format(0.01));

		ActionDialogAction actionDialogAction = new ActionDialogAction() {
			@Override
			public void action() {

				try {
					final Currency targetCurrency = Currency.valueOf(currencyListCombo.getItem(currencyListCombo.getSelectionIndex()));
					final BigDecimal transactionFee = new BigDecimal(percentInstance.parse(transactionFeeTxt.getText()).toString());
					final BigDecimal exchangeFee = new BigDecimal(percentInstance.parse(exchangeFeeTxt.getText()).toString());
					final Date cpgPeriodStart = dateInstance.parse(startPeriodTxt.getText());
					final Date cpgPeriodEnd = dateInstance.parse(endPeriodTxt.getText());

					final Portfolio portfolio = modelControler.getPortfolio(selectedPortfolioIdx());
					String dateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
					String transactionExportName = portfolio.getName()+"_in"+targetCurrency.toString()+"_"+dateStr;
					final Date startDate = (slidingStartAnchor.getSelection())?chartsComposite.getSlidingStartDate():DateFactory.dateAtZero();
					final Date endDate = (slidingEnd.after(cpgPeriodEnd))?cpgPeriodEnd:slidingEnd;

					TransactionExtractor te = new TransactionExtractor() {	
						@Override
						protected String run() throws Throwable {
							return portfolio.extractTransposedTransactionLog(startDate, endDate, targetCurrency , cpgPeriodStart, cpgPeriodEnd, transactionFee, exchangeFee);
						}
					};

					extractTransactions(portfolio, transactionExportName, te);

				} catch (ParseException e) {
					UserDialog dialog = new UserDialog(getShell(),"Invalid fee", e.toString());
					dialog.open();
				}
			}
		};

		actionDialog.setControl(currencyListCombo, startPeriodLabel, startPeriodTxt, endPeriodLabel, endPeriodTxt, transactionFeeLabel, transactionFeeTxt, exchangeFeeLabel, exchangeFeeTxt);
		actionDialog.setAction(actionDialogAction);

		actionDialog.open();

	}


	protected void extractTransactions(final Portfolio portfolio, String transactionExportName, TransactionExtractor te) {

		try {

			String dir = System.getProperty("installdir") + File.separator + "transactionsexports" + File.separator;
			new File(dir).mkdirs();
			String reportFileName = dir + transactionExportName + ".csv";
			File reportFile = new File(reportFileName);
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportFile));

			try {
				bufferedWriter.write(te.run());
			} catch (Exception e) {
				throw e;
			} finally {
				bufferedWriter.close();
			}
			LOGGER.info("File generated:"+reportFileName);

			try {
				LOGGER.info("Trying default desktop spreadsheet");
				Program csvProgram = Program.findProgram(".csv");
				if (csvProgram == null ) csvProgram = Program.findProgram("csv");
				csvProgram.execute(reportFileName);
			} catch (Throwable e) {
				String[] commands = new String[]{"/usr/bin/gnumeric","/usr/bin/soffice"};
				try {
					LOGGER.info("Trying "+commands[0]+" fall back");
					runSpread(transactionExportName, dir, commands[0]);
				} catch (Exception e1) {
					try {
						LOGGER.info("Trying "+commands[0]+" fall back");
						runSpread(transactionExportName, dir, commands[1]);
					} catch (Exception e2) {
						UserDialog dialog = new UserDialog(getShell(), "Your Transaction summary  for "+portfolio.getName(),
								"Your transaction summary for "+portfolio.getName()+" is available here :\n"+
										reportFileName+"\n"+
								"Alternatively, to automatically open it in as a spreadsheet, you may want to check the software associated with '.csv' file in your Operating System.");
						dialog.open();
					}
				}
			}

		} catch (Throwable e) {
			LOGGER.error(e,e);
			UserDialog dialog=new UserDialog(getShell(), "An error occurred while opening the transaction file", e.toString());
			dialog.open();
		}

	}

	private abstract class TransactionExtractor {
		protected abstract String run() throws Throwable;
	}

	protected void runSpread(String transactionExportName, String dir, String gnumeric) throws IOException {
		List<String> command = new ArrayList<String>();
		command.add(gnumeric);
		command.add(dir + transactionExportName + ".csv");
		ProcessBuilder builder = new  ProcessBuilder(command);
		builder.start();
	}

}
