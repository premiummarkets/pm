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
package com.finance.pms.events.gui;

import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.prefs.BackingStoreException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import com.finance.pms.CursorFactory;
import com.finance.pms.IndicatorCalculationServiceMain;
import com.finance.pms.LogComposite;
import com.finance.pms.MainGui;
import com.finance.pms.MainPMScmd;
import com.finance.pms.PopupMenu;
import com.finance.pms.RefreshableView;
import com.finance.pms.TableToolTip;
import com.finance.pms.UserDialog;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.EventModel;
import com.finance.pms.datasources.EventRefreshController;
import com.finance.pms.datasources.EventRefreshException;
import com.finance.pms.datasources.RefreshAllEventStrategyEngine;
import com.finance.pms.datasources.RefreshMonitoredStrategyEngine;
import com.finance.pms.datasources.RefreshPortfolioStrategyEngine;
import com.finance.pms.datasources.ShareListInfo;
import com.finance.pms.events.AnalysisClient;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.EmailSendingFilter;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.pounderationrules.DataResultReversedComparator;
import com.finance.pms.events.pounderationrules.DefaultPonderationRule;
import com.finance.pms.events.pounderationrules.EventValuesComparator;
import com.finance.pms.events.pounderationrules.LatestEventsAllIndDefsPonderationRule;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.events.pounderationrules.SymbolEventComparator;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.threads.ConfigThreadLocal;

/**
 * The Class EventsComposite.
 * 
 * @author Guillaume Thoreton
 */
@SuppressWarnings("unchecked")
public class EventsComposite extends Composite implements RefreshableView {

	public static MyLogger LOGGER = MyLogger.getLogger(EventsComposite.class);
	
	private Boolean needInit = true;

	private CTabFolder eventsCTabFolder;
	private ArrayList<CTabItem> cTabItem = new ArrayList<CTabItem>();
	private ArrayList<Tree> cTabTrees = new ArrayList<Tree>();

	private Composite ctrlComposite;

	private Group trendFilterGrp;
	private Label sellCriteriaWeigthLabel;
	private Text sellCriteriaWeigthtext;
	private Label buyWeigthCritLabel;
	private Text buyCriteriaWeigthtext;
	private Label dateCriteriaLabel;
	private Text dateCriteriatext;
	
	private Group stockFilterButGrp;
	private Button moniteredFilterbutton;
	private Button portfolioFilterbutton;
	private Button allStocksFilteButton;
	private Button selectedMarketsFilterbutton;
	private StocksActionFilter filter = StocksActionFilter.ALLSTOCKS;

	private Group computeButtonGroup;
	private Button quotationBox;
	private Button quotationListBox;
	private Button refreshMonitored;
	private Button refreshPortfolios;
	private Button refreshSelectedMarkets;

	private Group evtFilterButGrp;
	private Button evtFilterLatestTick;
	private Set<EventInfo> selectedEventInfos;
	private EventsActionSort action ;

	private Integer infCrit = new Integer(MainPMScmd.getPrefs().get("gui.crit.inf", "-1")); // -2;
	private Integer supCrit = new Integer(MainPMScmd.getPrefs().get("gui.crit.sup", "1")); // 4;
	private Integer nbDaysFilter;

	private Integer nbMonthsAnalysis;

	private LogComposite logComposite;

	private List<SymbolEvents>[] eventsLists = new List[4];
	private final String[] tabs = { "Buy", "Sell", "Neutral", "All" }; // ,"Non Accurate"};
	private final String[] columnsHeaders = { "Symbol", "Name", "Weight", "Date", "Event definition", "Event type" };
	private final String[] sortableColumnsAttAccesors = { "getSymbol", "getSymbolName", "getWeight", "getDate", "getEventDef", "getEventType" };
	private Integer sortedColumn;

	private EventModel<RefreshAllEventStrategyEngine> allStocksEventModel;
	private EventModel<RefreshMonitoredStrategyEngine> monitoredStocksEventModel;
	private EventModel<RefreshPortfolioStrategyEngine> portfolioStocksEventModel;
	
	private Shell treeRowToolTip;

	private Button sendNotifs;


	public EventsComposite(Composite parent, int style, LogComposite logComposite) {
		super(parent, style);

		this.nbMonthsAnalysis = new Integer(MainPMScmd.getPrefs().get("gui.crit.calcnbmonths", "18"));
		this.logComposite = logComposite;
		allStocksEventModel = EventModel.getInstance(new RefreshAllEventStrategyEngine());
		monitoredStocksEventModel = EventModel.getInstance(new RefreshMonitoredStrategyEngine());
		portfolioStocksEventModel = EventModel.getInstance(new RefreshPortfolioStrategyEngine(), logComposite);
		
		Calendar newDate = Calendar.getInstance();
		newDate.setTime(EventSignalConfig.getNewDate());
		nbDaysFilter = new Integer(MainPMScmd.getPrefs().get("gui.crit.filternbdays", "5"));
		
		selectedEventInfos = new HashSet<EventInfo>();
		selectedEventInfos.add(EventDefinition.PMSMAREVERSAL);
		setAction(new EventsActionSort(new LatestEventsAllIndDefsPonderationRule(), selectedEventInfos.toArray(new EventInfo[0])));
		
		initGUI();
	}

	public void initData() {

		setFilter(StocksActionFilter.FILTERMONITORED);
		sortOnWeigthCriteriasActions();
		reloadTabs();
			
		needInit = false;
	}

	private void initGUI() {
		try {

			GridLayout thisLayout = new GridLayout(2, false);
			thisLayout.verticalSpacing = 0;
			thisLayout.horizontalSpacing = 0;
			thisLayout.marginHeight = 0;
			thisLayout.marginWidth = 0;
			this.setLayout(thisLayout);
			this.setBackground(MainGui.eVENTS_DARKER);
			this.addListener(SWT.Show, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					if (EventsComposite.this.getSendNotifs().getSelection()){
						AnalysisClient.addEmailMsgQeueingFilter(EmailFilterEventSource.PMTAEvents);
						AnalysisClient.setEmailSendingFilter(new EmailSendingFilter(getFilterDate(), selectedEventInfos));
					}
					
				}
			});
			this.addListener(SWT.Hide, new Listener() {

				@Override
				public void handleEvent(Event event) {
					AnalysisClient.removeEmailMsgQeueingFilter(EmailFilterEventSource.PMTAEvents);
					AnalysisClient.clearEmailSendingFilter();
				}
			});
			
			this.addControlListener(new ControlListener() {
				
				@Override
				public void controlResized(ControlEvent e) {
					int treeIdx = eventsCTabFolder.getSelectionIndex();
					TreeColumn[] columns = cTabTrees.get(treeIdx).getColumns();
					for (TreeColumn treeColumn : columns) {
						columnPack(treeColumn);
					}
				}
				
				@Override
				public void controlMoved(ControlEvent e) {
					
				}
			});
			
			{
				eventsCTabFolder = new CTabFolder(this, SWT.NONE);
				GridData cTabFolder1LData = new GridData(SWT.FILL, SWT.FILL, true, true);
				eventsCTabFolder.setLayoutData(cTabFolder1LData);
				eventsCTabFolder.setBackground(MainGui.eVENTS_LIGHT);
				eventsCTabFolder.setSelectionBackground(MainGui.tAB_SELECTION);
				buildEventsTab();
				eventsCTabFolder.setSelection(0);
			}

			ctrlComposite = new Composite(this, SWT.NONE);
			GridData gridData = new GridData(SWT.TRAIL, SWT.FILL, false, true);
			ctrlComposite.setLayoutData(gridData);
			GridLayout cmpLayout = new GridLayout();
			cmpLayout.verticalSpacing = 0;
			cmpLayout.horizontalSpacing = 0;
			cmpLayout.marginHeight = 0;
			cmpLayout.marginWidth = 0;
			ctrlComposite.setLayout(cmpLayout);
			ctrlComposite.setBackground(MainGui.eVENTS_LIGHT);
		
			{
				trendFilterGrp = new Group(ctrlComposite, SWT.NONE);
				GridData criteriasgroupLData = new GridData(SWT.FILL, SWT.TOP, true, false);
				trendFilterGrp.setLayoutData(criteriasgroupLData);
				GridLayout group1Layout2 = new GridLayout();
				group1Layout2.numColumns = 2;
				trendFilterGrp.setLayout(group1Layout2);
				trendFilterGrp.setText("Trend filter :");
				trendFilterGrp.setFont(MainGui.DEFAULTFONT);
				trendFilterGrp.setBackground(MainGui.eVENTS_LIGHT);
				{
					buyWeigthCritLabel = new Label(trendFilterGrp, SWT.PUSH | SWT.CENTER);
					buyWeigthCritLabel.setBackground(MainGui.eVENTS_LIGHT);
					buyWeigthCritLabel.setText("Buy threshold");
					buyWeigthCritLabel.setFont(MainGui.DEFAULTFONT);
				}
				{
					buyCriteriaWeigthtext = new Text(trendFilterGrp, SWT.NONE);
					GridData buyCriteriaWeigthtextLData = new GridData(SWT.FILL, SWT.TOP, true, false);
					buyCriteriaWeigthtext.setLayoutData(buyCriteriaWeigthtextLData);
					buyCriteriaWeigthtext.setText(supCrit.toString());
					buyCriteriaWeigthtext.setFont(MainGui.CONTENTFONT);
					Listener listener = new Listener() {
						public void handleEvent(Event e) {
							setCrit(sellCriteriaWeigthtext.getText(), buyCriteriaWeigthtext.getText(), dateCriteriatext.getText());
						}
					};
					buyCriteriaWeigthtext.addListener (SWT.FocusOut, listener);
					buyCriteriaWeigthtext.addListener (SWT.DefaultSelection, listener);
				}
				{
					sellCriteriaWeigthLabel = new Label(trendFilterGrp, SWT.CENTER);
					sellCriteriaWeigthLabel.setBackground(MainGui.eVENTS_LIGHT);
					sellCriteriaWeigthLabel.setText("Sell threshold");
					sellCriteriaWeigthLabel.setFont(MainGui.DEFAULTFONT);
				}
				{
					sellCriteriaWeigthtext = new Text(trendFilterGrp, SWT.NONE);
					GridData sellCriteriaWeigthtextLData = new GridData(SWT.FILL, SWT.TOP, true, false);
					sellCriteriaWeigthtext.setLayoutData(sellCriteriaWeigthtextLData);
					sellCriteriaWeigthtext.setText(infCrit.toString());
					sellCriteriaWeigthtext.setFont(MainGui.CONTENTFONT);
					Listener listener = new Listener() {
						public void handleEvent(Event e) {
							setCrit(sellCriteriaWeigthtext.getText(), buyCriteriaWeigthtext.getText(), dateCriteriatext.getText());
						}
					};
					sellCriteriaWeigthtext.addListener (SWT.FocusOut, listener);
					sellCriteriaWeigthtext.addListener (SWT.DefaultSelection, listener);
				}
				{
					final Text dateFilterText = new Text(trendFilterGrp, SWT.NONE);
					GridData sellCriteriaDatetextLData = new GridData(SWT.FILL, SWT.TOP, true, false);
					dateFilterText.setLayoutData(sellCriteriaDatetextLData);
					dateFilterText.setFont(MainGui.CONTENTFONT);
					dateFilterText.setText(nbDaysFilter.toString());
					dateFilterText.setToolTipText("From the "+new SimpleDateFormat("dd MMM yyyy").format(getFilterDate()));
					Listener listener = new Listener() {
						public void handleEvent(Event e) {
							try {
								nbDaysFilter = Integer.valueOf(dateFilterText.getText());
								dateFilterText.setToolTipText("From the "+new SimpleDateFormat("dd MMM yyyy").format(getFilterDate()));
								eventFilterChange(selectedEventInfos);
							} catch (NumberFormatException pe) {
								UserDialog inst = new UserDialog(EventsComposite.this.getShell(), pe.getMessage(), null);
								inst.open();
								dateFilterText.setText("5");
							}
						}
					};
					dateFilterText.addListener (SWT.FocusOut, listener);
					dateFilterText.addListener (SWT.DefaultSelection, listener);
				}
				{
					Label dateFilterLabel = new Label(trendFilterGrp, SWT.PUSH | SWT.CENTER);
					dateFilterLabel.setBackground(MainGui.eVENTS_LIGHT);
					dateFilterLabel.setText("Days");
					dateFilterLabel.setToolTipText("This is the number of days you wish to filter the events for.");
					dateFilterLabel.setFont(MainGui.DEFAULTFONT);
				}
				trendFilterGrp.pack();
			}
			{
				evtFilterButGrp = new Group(ctrlComposite, SWT.NONE);
				GridData group1LData1 = new GridData(SWT.FILL, SWT.TOP, true, false);
				evtFilterButGrp.setLayoutData(group1LData1);
				GridLayout group1Layout1 = new GridLayout();
				group1Layout1.numColumns = 1;
				evtFilterButGrp.setLayout(group1Layout1);
				evtFilterButGrp.setText("Event filter :");
				evtFilterButGrp.setFont(MainGui.DEFAULTFONT);
				evtFilterButGrp.setBackground(MainGui.eVENTS_LIGHT);
				
				{
					evtFilterLatestTick = new Button(evtFilterButGrp, SWT.CHECK);
					evtFilterLatestTick.setFont(MainGui.DEFAULTFONT);
					evtFilterLatestTick.setText("Last Trend only");
					evtFilterLatestTick.setToolTipText(
							"When ticked, only events of the most recent Trend (Bullish/Bearish) are gathered over the period. Events from the same indicator are counted only once.\n" +
							"If ticked off all events are gathered indiscriminately over the period, meaning that the result will be : the number of Bullish events minus the number of Bearish events.");
					evtFilterLatestTick.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetSelected(SelectionEvent e) {
							eventFilterChange(selectedEventInfos);
						}

						@Override
						public void widgetDefaultSelected(SelectionEvent e) {
							eventFilterChange(selectedEventInfos);
						}
					});
					evtFilterLatestTick.setSelection(true);
				}
				{
					final Button selectedEventInfosFilterButton = new Button(evtFilterButGrp, SWT.LEAD);
					GridData group1LData = new GridData(SWT.BEGINNING, SWT.TOP, true, false);
					selectedEventInfosFilterButton.setLayoutData(group1LData);
					selectedEventInfosFilterButton.setFont(MainGui.DEFAULTFONT);
					selectedEventInfosFilterButton.setText("Indicators filter ...");
					selectedEventInfosFilterButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent evt) {
					
							PopupMenu<EventInfo> popupMenu = new PopupMenu<EventInfo>(EventsComposite.this, selectedEventInfosFilterButton,  EventDefinition.loadMaxPassPrefsEventInfo(), selectedEventInfos, true, SWT.CHECK, null);
							popupMenu.open();
							eventFilterChange(selectedEventInfos);
						}
					});
				}
				evtFilterButGrp.pack();
			}
			{
				stockFilterButGrp = new Group(ctrlComposite, SWT.NONE);
				GridData group1LData1 = new GridData(SWT.FILL, SWT.TOP, true, false);
				stockFilterButGrp.setLayoutData(group1LData1);
				GridLayout group1Layout1 = new GridLayout();
				stockFilterButGrp.setLayout(group1Layout1);
				stockFilterButGrp.setText("Stock filter :");
				stockFilterButGrp.setFont(MainGui.DEFAULTFONT);
				stockFilterButGrp.setBackground(MainGui.eVENTS_LIGHT);
				{
					moniteredFilterbutton = new Button(stockFilterButGrp, SWT.RADIO | SWT.LEAD);
					GridData group1LData = new GridData(GridData.FILL_HORIZONTAL);
					moniteredFilterbutton.setLayoutData(group1LData);
					moniteredFilterbutton.setFont(MainGui.DEFAULTFONT);
					moniteredFilterbutton.setText(StocksActionFilter.FILTERMONITORED.getText());
					moniteredFilterbutton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent evt) {

							setFilter(StocksActionFilter.FILTERMONITORED);
							try {
								EventsComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
								sortOnWeigthCriteriasActions();
								reloadTabs();
							} finally {
								EventsComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
							}
							
						}
					});
					moniteredFilterbutton.setSelection(true);
				}
				{
					portfolioFilterbutton = new Button(stockFilterButGrp, SWT.RADIO | SWT.LEAD);
					portfolioFilterbutton.setText(StocksActionFilter.FILTERPORTFOLIO.getText());
					portfolioFilterbutton.setFont(MainGui.DEFAULTFONT);
					GridData group1LData = new GridData(GridData.FILL_HORIZONTAL);
					portfolioFilterbutton.setLayoutData(group1LData);
					portfolioFilterbutton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent evt) {
							setFilter(StocksActionFilter.FILTERPORTFOLIO);
							try {
								EventsComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
								sortOnWeigthCriteriasActions();
								reloadTabs();
							} finally {
								EventsComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
							}
						}
					});
				}
				{
					selectedMarketsFilterbutton = new Button(stockFilterButGrp, SWT.RADIO | SWT.LEAD);
					GridData group1LData = new GridData(GridData.FILL_HORIZONTAL);
					selectedMarketsFilterbutton.setLayoutData(group1LData);
					selectedMarketsFilterbutton.setText(StocksActionFilter.FILTERMARKETS.getText());
					selectedMarketsFilterbutton.setFont(MainGui.DEFAULTFONT);
					selectedMarketsFilterbutton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent evt) {
							setFilter(StocksActionFilter.FILTERMARKETS);

							// Windos fix
							selectedMarketsFilterbutton.setSelection(true);
							portfolioFilterbutton.setSelection(false);
							moniteredFilterbutton.setSelection(false);
							allStocksFilteButton.setSelection(false);
							// End Windos fix

							Set<ShareListInfo> shareLists = new HashSet<ShareListInfo>();
							for (String shareListName : PortfolioMgr.getInstance().getPortfolioDAO().loadShareListNames()) {
								shareLists.add(new ShareListInfo(shareListName));
							}
							@SuppressWarnings("rawtypes")
							Set selectedShareLists = new HashSet();
							Object[] viewStateParams = allStocksEventModel.getViewStateParams();
							if (viewStateParams != null) {
								selectedShareLists.addAll(Arrays.asList(viewStateParams));
							}
							PopupMenu<ShareListInfo> popupMenu = new PopupMenu<ShareListInfo>(EventsComposite.this, selectedMarketsFilterbutton, shareLists, selectedShareLists, true, SWT.CHECK, null);
							popupMenu.open();
							allStocksEventModel.setViewStateParams(selectedShareLists.toArray());
							
							try {
								EventsComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
								sortOnWeigthCriteriasActions();
								reloadTabs();
							} finally {
								EventsComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
							}
						}
					});
				}
				{
					allStocksFilteButton = new Button(stockFilterButGrp, SWT.RADIO | SWT.LEAD);
					GridData group1LData = new GridData(GridData.FILL_HORIZONTAL);
					allStocksFilteButton.setLayoutData(group1LData);
					allStocksFilteButton.setText(StocksActionFilter.ALLSTOCKS.getText());
					allStocksFilteButton.setFont(MainGui.DEFAULTFONT);
					allStocksFilteButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent evt) {
							setFilter(StocksActionFilter.ALLSTOCKS);
							try {
								EventsComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
								sortOnWeigthCriteriasActions();
								reloadTabs();
							} finally {
								EventsComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
							}
						}
					});
				}
				stockFilterButGrp.pack();
			}
			{
				computeButtonGroup = new Group(ctrlComposite, SWT.NONE);
				GridData group1LData1 = new GridData(SWT.FILL, SWT.BOTTOM, true, true);
				computeButtonGroup.setLayoutData(group1LData1);
				GridLayout group1Layout1 = new GridLayout(2, false);
				computeButtonGroup.setLayout(group1Layout1);
				computeButtonGroup.setText("Compute :");
				computeButtonGroup.setFont(MainGui.DEFAULTFONT);
				computeButtonGroup.setBackground(MainGui.eVENTS_LIGHT);
				String notificationNote = 
						"By default, Notifications are sent for all stocks marked as monitored (Bearish, Bullish or both) in your portfolios.\n" +
						"Notifications can also be filtered according to your potential 'alert on events' settings for each lines in your portfolio.\n"+
						"Then the 'Trend' and 'Event' filters above will apply.";
				{
					dateCriteriatext = new Text(computeButtonGroup, SWT.NONE);
					GridData sellCriteriaDatetextLData = new GridData(SWT.FILL, SWT.TOP, true, false);
					dateCriteriatext.setLayoutData(sellCriteriaDatetextLData);
					dateCriteriatext.setFont(MainGui.CONTENTFONT);
					dateCriteriatext.setText(nbMonthsAnalysis.toString());
					dateCriteriatext.setToolTipText("From the "+new SimpleDateFormat("dd MMM yyyy").format(getAnalysisStartDate()));
					Listener listener = new Listener() {
						public void handleEvent(Event e) {
							setCrit(sellCriteriaWeigthtext.getText(), buyCriteriaWeigthtext.getText(), dateCriteriatext.getText());
							dateCriteriatext.setToolTipText("From the "+new SimpleDateFormat("dd MMM yyyy").format(getAnalysisStartDate()));
						}
					};
					dateCriteriatext.addListener (SWT.FocusOut, listener);
					dateCriteriatext.addListener (SWT.DefaultSelection, listener);
				}
				{
					dateCriteriaLabel = new Label(computeButtonGroup, SWT.PUSH | SWT.CENTER);
					dateCriteriaLabel.setBackground(MainGui.eVENTS_LIGHT);
					dateCriteriaLabel.setText("Months");
					dateCriteriaLabel.setToolTipText("This is the number of months you wish to calculate the events for.");
					dateCriteriaLabel.setFont(MainGui.DEFAULTFONT);
				}
				{
					sendNotifs = new Button(computeButtonGroup, SWT.CHECK);
					GridData group1LData2 = new GridData(SWT.FILL, SWT.TOP, true, false);
					group1LData2.horizontalSpan =2;
					sendNotifs.setLayoutData(group1LData2);
					sendNotifs.setText("Notify me");
					sendNotifs.setToolTipText("For this to work, make sure your email settings are up to date in 'Settings' menu.\n"+notificationNote);
					sendNotifs.setFont(MainGui.DEFAULTFONT);
					sendNotifs.setSelection(true);
					sendNotifs.addSelectionListener(new SelectionListener() {
						
						@Override
						public void widgetSelected(SelectionEvent e) {
							handle();
						}
						@Override
						public void widgetDefaultSelected(SelectionEvent e) {
							handle();
						}
						
						private void handle() {
							if (sendNotifs.getSelection()) {
								AnalysisClient.addEmailMsgQeueingFilter(EmailFilterEventSource.PMTAEvents);
								AnalysisClient.setEmailSendingFilter(new EmailSendingFilter(getFilterDate(), selectedEventInfos));
							} else {
								AnalysisClient.removeEmailMsgQeueingFilter(EmailFilterEventSource.PMTAEvents);
								AnalysisClient.clearEmailSendingFilter();
							}
						}

					});
				}
				{
					quotationBox = new Button(computeButtonGroup, SWT.CHECK);
					GridData group1LData2 = new GridData(SWT.FILL, SWT.TOP, true, false);
					group1LData2.horizontalSpan =2;
					quotationBox.setLayoutData(group1LData2);
					quotationBox.setText("Update quotes");
					quotationBox.setToolTipText("You can request for an update of the quotations before the calculation is done.\n This is recommended the first time in the day.");
					quotationBox.setFont(MainGui.DEFAULTFONT);
					quotationBox.setSelection(true);

					quotationListBox = new Button(computeButtonGroup, SWT.CHECK);
					GridData group1LData = new GridData(SWT.FILL, SWT.TOP, true, false);
					group1LData.horizontalSpan =2;
					quotationListBox.setLayoutData(group1LData);
					quotationListBox.setText("Update lists");
					quotationListBox.setToolTipText("In case you select one or several market lists, you can request for an update of the list before the calculation is done.\n However, this is time consuming.");
					quotationListBox.setFont(MainGui.DEFAULTFONT);
					quotationListBox.setSelection(false);
				}
				{
					refreshMonitored = new Button(computeButtonGroup, SWT.PUSH | SWT.CENTER);
					GridData group1LData = new GridData(SWT.FILL, SWT.TOP, true, false);
					group1LData.horizontalSpan = 2;
					refreshMonitored.setLayoutData(group1LData);
					refreshMonitored.setFont(MainGui.DEFAULTFONT);
					refreshMonitored.setText(StocksActionFilter.FILTERMONITORED.getText());
					refreshMonitored.setToolTipText(
							"Will update the events calculation on Stocks marked as monitored (Bearish, Bullish or both) in your portfolios.\n" +
							notificationNote+"\n");
					refreshMonitored.addMouseListener(new EventRefreshController(monitoredStocksEventModel, this, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
						@Override
						public void mouseDown(MouseEvent evt) {
							//updateEventRefreshModelState(Boolean dofetchListOfQuotes, Boolean dofetchQuotes, Boolean doAnalyse,) 
							List<TaskId> taskIds = new ArrayList<EventRefreshController.TaskId>();
							if (quotationListBox.getSelection()) taskIds.add(TaskId.FetchLists);
							if (quotationBox.getSelection()) taskIds.add(TaskId.FetchQuotations);
							taskIds.add(TaskId.Analysis);
							this.updateEventRefreshModelState(0l, taskIds.toArray(new TaskId[0]));
							initRefreshAction();
							super.mouseDown(evt);
						}
					});
				}
				{
					refreshPortfolios = new Button(computeButtonGroup, SWT.PUSH | SWT.CENTER);
					GridData group1LData = new GridData(SWT.FILL, SWT.TOP, true, false);
					group1LData.horizontalSpan = 2;
					refreshPortfolios.setLayoutData(group1LData);
					refreshPortfolios.setFont(MainGui.DEFAULTFONT);
					refreshPortfolios.setText(StocksActionFilter.FILTERPORTFOLIO.getText());
					refreshPortfolios.setToolTipText(
							"Will update the events calculation on all your portfolios.\n" + 
							notificationNote);
					refreshPortfolios.addMouseListener(new EventRefreshController(portfolioStocksEventModel, this, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
						@Override
						public void mouseDown(MouseEvent evt) {
							// updateEventRefreshModelState(Boolean dofetchListOfQuotes, Boolean dofetchQuotes, Boolean doAnalyse, Boolean doReco, Boolean doAnalysisClean, Boolean doAlerts, Long taskKey)
							List<TaskId> taskIds = new ArrayList<EventRefreshController.TaskId>();
							if (quotationListBox.getSelection()) taskIds.add(TaskId.FetchLists);
							if (quotationBox.getSelection()) taskIds.add(TaskId.FetchQuotations);
							taskIds.add(TaskId.Analysis);
							this.updateEventRefreshModelState(0l, taskIds.toArray(new TaskId[0]));
							initRefreshAction();
							super.mouseDown(evt);
						}
					});
				}
				{
					refreshSelectedMarkets = new Button(computeButtonGroup, SWT.PUSH | SWT.CENTER);
					GridData group1LData = new GridData(SWT.FILL, SWT.TOP, true, false);
					group1LData.horizontalSpan = 2;
					refreshSelectedMarkets.setLayoutData(group1LData);
					refreshSelectedMarkets.setText(StocksActionFilter.FILTERMARKETS.getText());
					refreshSelectedMarkets.setFont(MainGui.DEFAULTFONT);
					refreshSelectedMarkets.setToolTipText(
							"Will update the events calculation on the market you have selected. Please be aware that this is time consuming.\n" + 
							notificationNote);
					refreshSelectedMarkets.addMouseListener(new EventRefreshController(allStocksEventModel, this, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
						@Override
						public void mouseDown(MouseEvent evt) {

							Set<ShareListInfo> shareLists = new HashSet<ShareListInfo>();
							for (String shareListName : PortfolioMgr.getInstance().getPortfolioDAO().loadShareListNames()) {
								shareLists.add(new ShareListInfo(shareListName));
							}
							@SuppressWarnings("rawtypes")
							Set selectedShareLists = new HashSet();
							Object[] viewStateParams = allStocksEventModel.getViewStateParams();
							if (viewStateParams != null) {
								selectedShareLists.addAll(Arrays.asList(viewStateParams));
							}
							PopupMenu<ShareListInfo> popupMenu = new PopupMenu<ShareListInfo>(EventsComposite.this, refreshSelectedMarkets, shareLists, selectedShareLists, true, SWT.CHECK, null);
							popupMenu.open();

							allStocksEventModel.setViewStateParams(selectedShareLists.toArray());
							
							List<TaskId> taskIds = new ArrayList<EventRefreshController.TaskId>();
							if (quotationListBox.getSelection()) taskIds.add(TaskId.FetchLists);
							if (quotationBox.getSelection()) taskIds.add(TaskId.FetchQuotations);
							taskIds.add(TaskId.Analysis);
							this.updateEventRefreshModelState(0l, taskIds.toArray(new TaskId[0]));

							initRefreshAction();
							super.mouseDown(evt);
						}
					});
				}
				computeButtonGroup.layout();
			}

			this.layout();

		} catch (Exception e) {
			LOGGER.error("", e);
		}
	}

	protected Object buildViewMonitoredStockList() {
		return null;
	}

	protected Object buildViewStockList() {
		return null;
	}

	private void buildEventsTab() {

		for (int i = 0; i < tabs.length; i++) {
			cTabItem.add(i, new CTabItem(eventsCTabFolder, SWT.NONE));
			cTabItem.get(i).setText(tabs[i]);
			cTabItem.get(i).setFont(MainGui.DEFAULTFONT);
			{
				{
					final Tree tbTree = new Tree(eventsCTabFolder, SWT.NONE);
					GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
					tbTree.setLayoutData(layoutData);

					tbTree.setFont(MainGui.CONTENTFONT);		
					treeRowToolTip = null;
					tbTree.addListener(SWT.MouseHover, new TableToolTip() {
						
						@Override
						protected void buildAndShowToolTip(Event event) {
							if (treeRowToolTip != null && !treeRowToolTip.isDisposed()) treeRowToolTip.dispose();

							TreeItem item = tbTree.getItem(new Point (event.x, event.y));
							
							if (item != null) {
								
								TreeItem parentItem = item.getParentItem();
								if (parentItem != null) {
									String mesg = ((EventValue) item.getData()).getMessage();
									if (mesg != null && !mesg.isEmpty()) {
										String newMsg = "";
										if (!mesg.contains("\n")) { //Not already formated
											int nbMsgCut = mesg.length();
											int maxChars = 100;
											for (int j = 0; j <= nbMsgCut/maxChars; j++) {
												newMsg = newMsg + mesg.substring(j*maxChars, Math.min((j+1)*maxChars, mesg.length())) + "\n";
											}
										} else {
											newMsg = mesg;
										}
										String info = parentItem.getText(1) + " ("+parentItem.getText(0)+") : \n"+item.getText(3)+"\n"+newMsg+"\n"+item.getText(5)+"\n";
										
										Point point = new Point (event.x, event.y);
										Point map = getDisplay().map((Control)event.widget, null, point);
										treeRowToolTip = showTooltip(null, map.x, map.y, info);
									}
								}
							}
						}
					});
					tbTree.addListener(SWT.MouseExit, new Listener() {
						@Override
						public void handleEvent(Event event) {
							if (treeRowToolTip != null && !treeRowToolTip.isDisposed()) treeRowToolTip.dispose();
						}
					});
					
					cTabTrees.add(i, tbTree);
					cTabTrees.get(i).setSize(41, 11);
					cTabTrees.get(i).setLinesVisible(false);
					cTabTrees.get(i).setHeaderVisible(true);

					cTabItem.get(i).setControl(cTabTrees.get(i));
					for (int j = 0; j < columnsHeaders.length; j++) {
						TreeColumn symbolColumn = new TreeColumn(cTabTrees.get(i), SWT.NONE);
						symbolColumn.setText(columnsHeaders[j]);
						symbolColumn.setToolTipText("All columns can be reordered.\nClick on a symbol and expand. Then click on the column header.");
					}
					final int fi = i;
					for (int j = 0; j < columnsHeaders.length; j++) {
						final int fj = j;
						cTabTrees.get(i).getColumn(j).pack();
						cTabTrees.get(i).getColumn(j).addSelectionListener(new SelectionListener() {
							public void widgetDefaultSelected(SelectionEvent arg0) {
								sortColumn(fi, fj);
							}

							public void widgetSelected(SelectionEvent arg0) {
								sortColumn(fi, fj);
							}
						});
					}
				}
			}
		}
	}

	private void updateTree(final int treeIdx) {
		
		for (int k = 0; k < eventsLists[treeIdx].size(); k++) {
			//items symbols
			TreeItem symbolEventsTableTreeItem = new TreeItem(cTabTrees.get(treeIdx), SWT.NONE);
			SymbolEvents se = eventsLists[treeIdx].get(k);
			symbolEventsTableTreeItem.setData(se);
			symbolEventsTableTreeItem.setText(0, se.getSymbol());
			symbolEventsTableTreeItem.setText(1, se.getSymbolName());
			symbolEventsTableTreeItem.setText(2, new Float(se.getWeight(this.action.getPonderationRule())).toString());
			// sous items events
			{
				subItems(symbolEventsTableTreeItem, se, new DataResultReversedComparator());
			}
		}

		//column pack listener
		cTabTrees.get(treeIdx).addListener(SWT.Expand, new Listener() {

			@Override
			public void handleEvent(Event event) {
				final TreeItem treeItem = (TreeItem) event.item;
				getDisplay().asyncExec(new Runnable() {

					@Override
					public void run() {
						
						//pack columns
						int tcw = 0;
						if (!treeItem.isDisposed()) {
							
							Tree tree = treeItem.getParent();
							
							//resize sash
							//int evtCmpW = tcw + ctrlComposite.getSize().x + 20;
							Point computeSize = tree.computeSize(SWT.DEFAULT, SWT.DEFAULT);
							int evtCmpW = computeSize.x + ctrlComposite.getSize().x;
							
							int shellW  = ((SashForm)EventsComposite.this.getParent()).getSize().x;
							int evtSashWeight = Math.min(90,100*evtCmpW/shellW);
							SashForm sashParent = (SashForm)EventsComposite.this.getParent();
							int actualTotWeight = 0;
							for (int sashWeight : sashParent.getWeights()) {
								actualTotWeight = actualTotWeight + sashWeight;
							}
							if ((100*sashParent.getWeights()[0]/actualTotWeight) < evtSashWeight) sashParent.setWeights(new int[]{evtSashWeight, 0, 100-evtSashWeight});
							
							//pack columns
							for (TreeColumn tc : tree.getColumns()) {
								columnPack(tc);
								tcw = tcw + tc.getWidth();
							}
						}
						
					}

				});
			}
		});

		//initial pack
		TreeColumn[] columns = cTabTrees.get(treeIdx).getColumns();
		for (TreeColumn treeColumn : columns) {
			columnPack(treeColumn);
		}

	}

	private void columnPack(TreeColumn treeColumn) {
//		treeColumn.pack();
//		int maxSize = (this.getSize().x-ctrlComposite.getSize().x)/columnsHeaders.length;
//		int minSize = 70;
//		int packedActualSize = treeColumn.getWidth();
//		if (minSize < maxSize && maxSize < packedActualSize) treeColumn.setWidth(maxSize);
		
		int minSize = 70;
		int availableSize = (this.getSize().x-ctrlComposite.getSize().x)/columnsHeaders.length;
		if (availableSize <= minSize) {
			treeColumn.setWidth(minSize);
		} 
		else if (availableSize > minSize) {
			treeColumn.pack();
			int actualPackedWidth = treeColumn.getWidth();
			if (actualPackedWidth > availableSize) treeColumn.setWidth(availableSize);
		}
	}

	private void subItems(TreeItem symbolEventsTableTreeItem, SymbolEvents se, Comparator<EventValue> comparator) {
		
		ArrayList<EventValue> dataResultsTableTreeItem = se.getSortedDataResultList(comparator);
		
		for (int l = 0; l < dataResultsTableTreeItem.size(); l++) {
			TreeItem sousTableTreeItem = new TreeItem(symbolEventsTableTreeItem, SWT.NONE);
			EventValue drv = dataResultsTableTreeItem.get(l);
			sousTableTreeItem.setData(drv);
			sousTableTreeItem.setText(3, new SimpleDateFormat("dd MMM yyyy").format(drv.getDate()));
			sousTableTreeItem.setText(4, drv.getEventDef().getEventReadableDef());
			sousTableTreeItem.setText(5, String.valueOf(drv.getEventType()));
		}
		
	}

	private void stockFilter() {
		try {
			getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
			switch (filter) {
			case FILTERMONITORED:
				EventsResources.getInstance().filterOutNonMonitoredEvents();
				break;
			case FILTERPORTFOLIO:
				EventsResources.getInstance().filterOutNonPortofolioEvents();
				break;
			case FILTERMARKETS:
				Object[] viewStateParams = allStocksEventModel.getViewStateParams();
				if (viewStateParams != null) EventsResources.getInstance().filterOutCurrentMarketEvents(viewStateParams);
				break;
			case ALLSTOCKS:
				EventsResources.getInstance().filterOutNone();
				break;
			}
		} finally {
			getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}
	}

	private void sortOnWeigthCriteriasActions() {
		
		try {
			Date filterDateStart = getFilterDate();
			EventsResources.getInstance().updateEventsTabsByCriteriaAndDate(filterDateStart, infCrit, supCrit, action.getPonderationRule(), action.getIndicators(), IndicatorCalculationServiceMain.UI_ANALYSIS);
			stockFilter();

		} catch (InvalidAlgorithmParameterException e) {
			UserDialog inst = new UserDialog(this.getShell(), e.toString(), null);
			inst.open();
		} 
	}

	protected Date getFilterDate() {
		Calendar newDate = Calendar.getInstance();
		newDate.setTime(EventSignalConfig.getNewDate());
		newDate.add(Calendar.DAY_OF_YEAR, -nbDaysFilter);
		return DateFactory.midnithDate(newDate.getTime());
	}

	private void reloadTabs() {
		eventsLists[0] = EventsResources.getInstance().getDebList();
		eventsLists[1] = EventsResources.getInstance().getFinList();
		eventsLists[2] = EventsResources.getInstance().getMidleList();
		eventsLists[3] = EventsResources.getInstance().getSortedList();
		LOGGER.debug("Nb buy : " + eventsLists[0].size() + "; Nb sell : " + eventsLists[1].size() + "; Nb neutral : " + eventsLists[2].size());
		LOGGER.debug("Nb All : " + eventsLists[3].size());
		refreshTabs();
	}

	private void refreshTabs() {
		for (int i = 0; i < tabs.length; i++) {
			cTabTrees.get(i).removeAll();
			updateTree(i);
		}
	}

	private void setCrit(String infCritp, String supCritp, String dateCrit) {
		
		try {
			infCrit = Integer.parseInt(infCritp);
			MainPMScmd.getPrefs().put("gui.crit.inf", infCritp);
		} catch (NumberFormatException e) {
			UserDialog inst = new UserDialog(this.getShell(), e.toString(), null);
			inst.open();
			sellCriteriaWeigthtext.setText(this.infCrit.toString());
		}
		
		try {
			supCrit = Integer.parseInt(supCritp);
			MainPMScmd.getPrefs().put("gui.crit.sup", supCritp);
		} catch (NumberFormatException e) {
			UserDialog inst = new UserDialog(this.getShell(), e.toString(), null);
			inst.open();
			buyCriteriaWeigthtext.setText(this.supCrit.toString());
		}
		
		try {
			nbMonthsAnalysis = Integer.valueOf(dateCrit);
		} catch (NumberFormatException e) {
			UserDialog inst = new UserDialog(this.getShell(), e.getMessage(), null);
			inst.open();
			dateCriteriatext.setText(MainPMScmd.getPrefs().get("gui.crit.calcnbmonths", "18"));
		} finally {
			//
		}
		
		try {
			getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
			sortOnWeigthCriteriasActions();
			reloadTabs();
		} catch (Exception e1) {
			UserDialog dialog = new UserDialog(this.getShell(), "Invalid date : " + dateCriteriatext, null);
			dialog.open();
		} finally {
			getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}
		
		try {
			MainPMScmd.getPrefs().flush();
		} catch (BackingStoreException e) {
			LOGGER.warn("Can't save preferences", e);
		}
	}

	private void setAction(EventsActionSort action) {
		this.action = action;
	}

	private void setFilter(StocksActionFilter filter) {
		this.filter = filter;
	}

	private void sortColumn(int tabNum, int colNum) {
		
		try {
			getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
			String sortAttAcc = sortableColumnsAttAccesors[colNum];
			if (sortAttAcc != null) {

				switch(colNum) {
				case 2 :
				{
					List<SymbolEvents> sortedColumnItems = new ArrayList<SymbolEvents>();
					sortedColumnItems.addAll(eventsLists[tabNum]);
					if (sortedColumn != null && sortedColumn.equals(colNum*(tabNum+1)))  {
						Collections.reverse(sortedColumnItems);
					} else {
						SymbolEvents.sortList(sortedColumnItems, new SymbolEventComparator((tabNum == 1), sortAttAcc, this.action.getPonderationRule()));
					}
					eventsLists[tabNum] = sortedColumnItems;
					//Refresh
					cTabTrees.get(tabNum).removeAll();
					updateTree(tabNum);
					sortedColumn = colNum*(tabNum+1);
					break;
				}
				case 3 :
				case 4 :
				case 5 :
				{
					TreeItem[] selection = cTabTrees.get(tabNum).getSelection();
					if (selection != null && selection.length == 1 && selection[0].getExpanded()) {
						selection[0].removeAll();
						SymbolEvents se = (SymbolEvents) selection[0].getData();
						Boolean isAsc = (sortedColumn == null || !sortedColumn.equals(colNum*(tabNum+1)));
						subItems(selection[0], se, new EventValuesComparator(isAsc, sortAttAcc));
						selection[0].setExpanded(true);
						if (isAsc) {
							sortedColumn =  colNum*(tabNum+1);
						} else {
							sortedColumn = null;
						}
					}
					break;
				}
				default :
				{
					List<SymbolEvents> sortedColumnItems = new ArrayList<SymbolEvents>();
					sortedColumnItems.addAll(eventsLists[tabNum]);
					if (sortedColumn != null && sortedColumn.equals(colNum*(tabNum+1)))  {
						Collections.reverse(sortedColumnItems);
					} else {
						SymbolEvents.sortList(sortedColumnItems, new SymbolEventComparator(true, sortAttAcc));
					}
					eventsLists[tabNum] = sortedColumnItems;
					//Refresh
					cTabTrees.get(tabNum).removeAll();
					updateTree(tabNum);
					sortedColumn = colNum*(tabNum+1);
				}
				}
				
				
			}

		} finally {
			getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}
	}

	public void initRefreshAction() {

		getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
		refreshMonitored.setEnabled(false);
		refreshSelectedMarkets.setEnabled(false);
		refreshPortfolios.setEnabled(false);
		logComposite.initRefresh(this);
	}


	@Override
	public void endRefreshAction(List<Exception> exceptions) {
		try {
			logComposite.endJob(exceptions);
			enableRefreshButtons();
		} finally {
			getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}
	}

	private void enableRefreshButtons() {

		refreshMonitored.setEnabled(true);
		refreshSelectedMarkets.setEnabled(true);
		refreshPortfolios.setEnabled(true);
	}

	/**
	 * Refresh view.
	 * 
	 * @author Guillaume Thoreton
	 */
	@Override
	public void refreshView(List<Exception> exceptions) {
		
		// Refresh view
		//eventFilterComboRefresh();
		sortOnWeigthCriteriasActions();
		reloadTabs();
		enableRefreshButtons();

		if (isVisible()) {
			for (Exception exception : exceptions) {
				if (exception instanceof EventRefreshException) {
					UserDialog dialog = new UserDialog(getShell(), "Couldn't refresh all events. Check that date bounds are not out of range.", exceptions.toString());
					exceptions.clear();
					dialog.open();
					break;
				}
			}
		}
	}

	public Date getAnalysisStartDate() {
		Calendar newDate = Calendar.getInstance();
		newDate.setTime(EventSignalConfig.getNewDate());
		newDate.add(Calendar.MONTH, -nbMonthsAnalysis);
		return newDate.getTime();
	}

	private void eventFilterChange(final Set<EventInfo> eventDefs) {
		
		if (eventDefs == null || eventDefs.isEmpty()) {
			EventsResources.getInstance().resetSortLists();
			reloadTabs();

		} else {

			Display.getDefault().asyncExec(new Runnable() {
				
				public void run() {

					try {

						getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));

						Integer sellEventTriggerThreshold = ((EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getSellEventTriggerThreshold();
						Integer buyEventTriggerThreshold = ((EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getBuyEventTriggerThreshold();
						PonderationRule ponderationRule = (evtFilterLatestTick.getSelection())?new LatestEventsAllIndDefsPonderationRule():new DefaultPonderationRule(sellEventTriggerThreshold, buyEventTriggerThreshold);
						EventInfo[] eventDefArray = eventDefs.toArray(new EventInfo[0]);
						setAction(new EventsActionSort(ponderationRule, eventDefArray));

						sortOnWeigthCriteriasActions();
						reloadTabs();

					} finally {
						getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
					}

				}

			});
		}
		
		AnalysisClient.setEmailSendingFilter(new EmailSendingFilter(getFilterDate(), eventDefs));
	}

	@Override
	public void setVisible(boolean visible) {
		try {
			getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
			if (needInit) initData();
		}
		finally {
			getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}
		super.setVisible(visible);
	}

	public Button getSendNotifs() {
		return sendNotifs;
	}

}
