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
package com.finance.pms.events.gui;

import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import com.finance.pms.ActionDialog;
import com.finance.pms.ActionDialogAction;
import com.finance.pms.CursorFactory;
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
import com.finance.pms.datasources.EventTaskQueue;
import com.finance.pms.datasources.InvalidEventRefreshTask;
import com.finance.pms.datasources.RefreshAllEventStrategyEngine;
import com.finance.pms.datasources.RefreshMonitoredStrategyEngine;
import com.finance.pms.datasources.RefreshPortfolioStrategyEngine;
import com.finance.pms.datasources.ShareListInfo;
import com.finance.pms.datasources.TaskId;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.AnalysisClient;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.EmailSendingFilter;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.SelectedIndicatorsCalculationService;
import com.finance.pms.events.pounderationrules.DataResultReversedComparator;
import com.finance.pms.events.pounderationrules.DefaultPonderationRule;
import com.finance.pms.events.pounderationrules.EventValuesComparator;
import com.finance.pms.events.pounderationrules.LatestEventsAllIndDefsPonderationRule;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.events.pounderationrules.SymbolEventComparator;
import com.finance.pms.portfolio.AbstractSharesList;
import com.finance.pms.portfolio.Portfolio;
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
	private Button marketsFilterbutton;
	private StocksActionFilter filter;
	
	private Group computeButtonGroup;
	private Button quotationBox;
	private Button quotationListBox;
	private Button refreshCompute;

	private Group evtFilterButGrp;
	private Button evtFilterLatestTick;
	private Set<EventInfo> selectedEventInfos;
	private Set<ShareListInfo> selectedPortfolios;
	private Set<ShareListInfo> selectedShareLists;
	private EventsActionSort action;

	private Integer infCrit; // -2; 
	private Integer supCrit; // 4;
	private Integer nbDaysFilter;

	private Integer nbMonthsAnalysis;

	private LogComposite logComposite;

	private List<SymbolEvents>[] eventsLists = new List[4];
	private final String[] tabs = { "Buy", "Sell", "Neutral", "All" }; // ,"Non Accurate"};
	private final String[] columnsHeaders = { "Symbol", "Name", "Weight", "Date", "Event definition", "Event type" };
	private final String[] sortableColumnsAttAccesors = { "getSymbol", "getSymbolName", "getWeight", "getDate", "getEventDef", "getEventType" };
	private Integer sortedColumn;

	private EventModel<RefreshAllEventStrategyEngine, Collection<ShareListInfo>> allStocksEventModel;
	private EventModel<RefreshMonitoredStrategyEngine, Collection<Stock>> monitoredStocksEventModel;
	private EventModel<RefreshPortfolioStrategyEngine, Collection<Stock>> portfolioStocksEventModel;

	private Shell treeRowToolTip;

	private Button sendNotifs;

	private SelectionListener refreshComputeCurrentListener;
	private EventRefreshController refreshMonitoredListner;
	private EventRefreshController refreshPortfoliosListener;
	private EventRefreshController refreshMarketsListener;
	private SelectionListener refreshAllStocksListener;

	public EventsComposite(Composite parent, int style, LogComposite logComposite) {
		super(parent, style);
		
		this.logComposite = logComposite;
		allStocksEventModel = EventModel.getInstance(new RefreshAllEventStrategyEngine());
		monitoredStocksEventModel = EventModel.getInstance(new RefreshMonitoredStrategyEngine());
		portfolioStocksEventModel = EventModel.getInstance(new RefreshPortfolioStrategyEngine(), logComposite);

		loadPrefs();

		initGUI();
	}

	private void loadPrefs() {
		
		this.infCrit = Integer.valueOf(MainPMScmd.getMyPrefs().get("gui.crit.inf", "-1"));
		this.supCrit = Integer.valueOf(MainPMScmd.getMyPrefs().get("gui.crit.sup", "1"));
		this.nbDaysFilter = Integer.valueOf(MainPMScmd.getMyPrefs().get("gui.crit.filternbdays", "5"));
		this.nbMonthsAnalysis = Integer.valueOf(MainPMScmd.getMyPrefs().get("gui.crit.calcnbmonths", "18"));

		this.selectedEventInfos = new HashSet<EventInfo>();//These are initialised in the set visible as it requires knowledge of the EventInfos (async back ground loaded)

		this.selectedPortfolios = new HashSet<ShareListInfo>();
		List<String> previouslySelectedPortfolios = Arrays.asList(MainPMScmd.getMyPrefs().get("gui.crit.selectedportfolios", "").split(","));
		if (!previouslySelectedPortfolios.isEmpty()) {
			for (String selectedPortfolio : previouslySelectedPortfolios) {
				if (!selectedPortfolio.isEmpty()) {
					try {
						AbstractSharesList portfolio = PortfolioMgr.getInstance().getPortfolio(selectedPortfolio);
						this.selectedPortfolios.add(new ShareListInfo(portfolio.getName()));
					} catch (IllegalArgumentException e) {
						LOGGER.warn("Can't reload portfolio '"+selectedPortfolio+"'. It may have been deleted. "+e);
					}
				}
			}
		}
		this.filter = StocksActionFilter.valueOf(MainPMScmd.getMyPrefs().get("gui.crit.sotckfilter", StocksActionFilter.ALLSTOCKS.name()));
		
	}
	
	private void savePrefs() {
		
		MainPMScmd.getMyPrefs().put("gui.crit.inf", this.infCrit.toString());
		MainPMScmd.getMyPrefs().put("gui.crit.sup", this.supCrit.toString());
		MainPMScmd.getMyPrefs().put("gui.crit.filternbdays", this.nbDaysFilter.toString());
		MainPMScmd.getMyPrefs().put("gui.crit.calcnbmonths", this.nbMonthsAnalysis.toString());
		MainPMScmd.getMyPrefs().put("gui.crit.selectedeventinfos", EventDefinition.getEventDefSetAsString(",",selectedEventInfos));
		
		String sps = "";
		String sep = "";
		for (ShareListInfo selectedPortfolio : this.selectedPortfolios) {
			sps = sps + sep + selectedPortfolio.info();
			sep = ",";
		}
		MainPMScmd.getMyPrefs().put("gui.crit.selectedportfolios", sps);
		MainPMScmd.getMyPrefs().put("gui.crit.sotckfilter", this.filter.name());
		
		MainPMScmd.getMyPrefs().flushy();
		
	}

	public void initData() {

		//init events prefs
		List<String> prefEventInfos = Arrays.asList(MainPMScmd.getMyPrefs().get("gui.crit.selectedeventinfos", EventDefinition.PMSMAREVERSAL.name()).split(","));
		SortedSet<EventInfo> allKnownEventInfos = EventDefinition.loadMaxPassPrefsEventInfo();
		for (EventInfo knownEventInfo : allKnownEventInfos) {
			if (prefEventInfos.contains(knownEventInfo.getEventDefinitionRef())) selectedEventInfos.add(knownEventInfo);
		}
		setAction(new EventsActionSort(new LatestEventsAllIndDefsPonderationRule(), selectedEventInfos.toArray(new EventInfo[0])));
		
		sortAndReload();

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
					if (EventsComposite.this.getSendNotifs().getSelection()) {
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
					columnPack(cTabTrees.get(treeIdx));
				}

				@Override
				public void controlMoved(ControlEvent e) {

				}
			});
			
			this.addDisposeListener(new DisposeListener() {
				
				@Override
				public void widgetDisposed(DisposeEvent e) {
					savePrefs();
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
					buyCriteriaWeigthtext.addListener(SWT.FocusOut, listener);
					buyCriteriaWeigthtext.addListener(SWT.DefaultSelection, listener);
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
					sellCriteriaWeigthtext.addListener(SWT.FocusOut, listener);
					sellCriteriaWeigthtext.addListener(SWT.DefaultSelection, listener);
				}
				{
					final Text dateFilterText = new Text(trendFilterGrp, SWT.NONE);
					GridData sellCriteriaDatetextLData = new GridData(SWT.FILL, SWT.TOP, true, false);
					dateFilterText.setLayoutData(sellCriteriaDatetextLData);
					dateFilterText.setFont(MainGui.CONTENTFONT);
					dateFilterText.setText(nbDaysFilter.toString());
					dateFilterText.setToolTipText("From the " + new SimpleDateFormat("dd MMM yyyy").format(getFilterDate()));
					Listener listener = new Listener() {
						public void handleEvent(Event e) {
							try {
								nbDaysFilter = Integer.valueOf(dateFilterText.getText().trim());
								dateFilterText.setToolTipText("From the " + new SimpleDateFormat("dd MMM yyyy").format(getFilterDate()));
								eventFilterChange(selectedEventInfos);
							} catch (NumberFormatException pe) {
								UserDialog inst = new UserDialog(EventsComposite.this.getShell(), pe.getMessage(), null);
								inst.open();
								dateFilterText.setText("5");
							}
						}
					};
					dateFilterText.addListener(SWT.FocusOut, listener);
					dateFilterText.addListener(SWT.DefaultSelection, listener);
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
					evtFilterLatestTick
							.setToolTipText("When ticked, only events of the most recent Trend (Bullish/Bearish) are gathered over the period. Events from the same calculator are counted only once.\n"
									+ "If ticked off all events are gathered indiscriminately over the period, meaning that the result will be : the number of Bullish events minus the number of Bearish events.");
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
					selectedEventInfosFilterButton.setText("Calculators filter ...");
					selectedEventInfosFilterButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(final MouseEvent evt) {

							ActionDialogAction closeAction = new ActionDialogAction() {

								@Override
								public void action() {
									try {
										EventsComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
										eventFilterChange(selectedEventInfos);
									} finally {
										EventsComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
									}
								}
							};
							
							SortedSet<EventInfo> allKnownEventInfos = EventDefinition.loadMaxPassPrefsEventInfo();
							//popup
							PopupMenu<EventInfo> popupMenu = new PopupMenu<EventInfo>(EventsComposite.this, selectedEventInfosFilterButton, allKnownEventInfos, selectedEventInfos, true, true, SWT.CHECK, null, closeAction, false);
							popupMenu.open();

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

							refreshCompute.removeSelectionListener(refreshComputeCurrentListener);
							refreshComputeCurrentListener = refreshMonitoredListner;
							refreshCompute.addSelectionListener(refreshComputeCurrentListener);
							sortAndReload();

						}
					});
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
							
							// Windos fix
							moniteredFilterbutton.setSelection(false);
							portfolioFilterbutton.setSelection(true);
							marketsFilterbutton.setSelection(false);
							allStocksFilteButton.setSelection(false);
							// End Windos fix
							
							Set<ShareListInfo> visiblePortfolios = new HashSet<ShareListInfo>();
							for (Portfolio visiblePortfolio : PortfolioMgr.getInstance().getVisiblePortfolios()) {
								visiblePortfolios.add(new ShareListInfo(visiblePortfolio.getName()));
							}

							ActionDialogAction closeAction = new ActionDialogAction() {

								@Override
								public void action() {
									Collection<Stock> selectedStocks = extractStockSetFrom(selectedPortfolios);
									portfolioStocksEventModel.setViewParamRoot(selectedStocks);
									
									refreshCompute.removeSelectionListener(refreshComputeCurrentListener);
									refreshComputeCurrentListener = refreshPortfoliosListener;
									refreshCompute.addSelectionListener(refreshComputeCurrentListener);

									try {
										EventsComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
										sortAndReload();
									} finally {
										EventsComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
									}

								}
							};
							PopupMenu<ShareListInfo> popupMenu = new PopupMenu<ShareListInfo>(EventsComposite.this, portfolioFilterbutton, visiblePortfolios, selectedPortfolios, true, true, SWT.CHECK, null, closeAction, false);
							popupMenu.open();
						}
					});
				}
				{
					marketsFilterbutton = new Button(stockFilterButGrp, SWT.RADIO | SWT.LEAD);
					GridData group1LData = new GridData(GridData.FILL_HORIZONTAL);
					marketsFilterbutton.setLayoutData(group1LData);
					marketsFilterbutton.setText(StocksActionFilter.FILTERMARKETS.getText());
					marketsFilterbutton.setFont(MainGui.DEFAULTFONT);
					marketsFilterbutton.addMouseListener(new MouseAdapter() {

						@Override
						public void mouseDown(MouseEvent evt) {
							setFilter(StocksActionFilter.FILTERMARKETS);

							// Windos fix
							moniteredFilterbutton.setSelection(false);
							portfolioFilterbutton.setSelection(false);
							marketsFilterbutton.setSelection(true);
							allStocksFilteButton.setSelection(false);
							// End Windos fix

							Set<ShareListInfo> shareLists = new HashSet<ShareListInfo>();
							for (String shareListName : PortfolioMgr.getInstance().getPortfolioDAO().loadValidShareListNames()) {
								shareLists.add(new ShareListInfo(shareListName));
							}

							selectedShareLists = new HashSet<ShareListInfo>();
							Collection<ShareListInfo> viewStateParams = allStocksEventModel.getViewParamRoot();
							if (viewStateParams != null) {
								selectedShareLists.addAll(viewStateParams);
							}

							ActionDialogAction closeAction = new ActionDialogAction() {

								@Override
								public void action() {
									
									allStocksEventModel.setViewParamRoot(selectedShareLists);
									
									refreshCompute.removeSelectionListener(refreshComputeCurrentListener);
									refreshComputeCurrentListener = refreshMarketsListener;
									refreshCompute.addSelectionListener(refreshComputeCurrentListener);

									try {
										EventsComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
										sortAndReload();
									} finally {
										EventsComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
									}

								}
							};
							PopupMenu<ShareListInfo> popupMenu = new PopupMenu<ShareListInfo>(EventsComposite.this, marketsFilterbutton, shareLists, selectedShareLists, true, true, SWT.CHECK, null, closeAction, false);
							popupMenu.open();

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
							
							refreshCompute.removeSelectionListener(refreshComputeCurrentListener);
							refreshComputeCurrentListener = refreshAllStocksListener;
							refreshCompute.addSelectionListener(refreshAllStocksListener);
							
							setFilter(StocksActionFilter.ALLSTOCKS);
							sortAndReload();
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
				String notificationNote = "By default, Notifications are sent for all stocks marked as monitored (Bearish, Bullish or both) in your portfolios.\n"
						+ "Notifications can also be filtered according to your potential 'alert on events' settings for each lines in your portfolio.\n"
						+ "Then the 'Trend filter' and 'Event filter' filters set for this view will apply.";
				{
					dateCriteriatext = new Text(computeButtonGroup, SWT.NONE);
					GridData sellCriteriaDatetextLData = new GridData(SWT.FILL, SWT.TOP, true, false);
					dateCriteriatext.setLayoutData(sellCriteriaDatetextLData);
					dateCriteriatext.setFont(MainGui.CONTENTFONT);
					dateCriteriatext.setText(nbMonthsAnalysis.toString());
					dateCriteriatext.setToolTipText("From the " + new SimpleDateFormat("dd MMM yyyy").format(getAnalysisStartDate()));
					Listener listener = new Listener() {
						public void handleEvent(Event e) {
							setCrit(sellCriteriaWeigthtext.getText().trim(), buyCriteriaWeigthtext.getText().trim(), dateCriteriatext.getText().trim());
							dateCriteriatext.setToolTipText("From the " + new SimpleDateFormat("dd MMM yyyy").format(getAnalysisStartDate()));
						}
					};
					dateCriteriatext.addListener(SWT.FocusOut, listener);
					dateCriteriatext.addListener(SWT.DefaultSelection, listener);
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
					group1LData2.horizontalSpan = 2;
					sendNotifs.setLayoutData(group1LData2);
					sendNotifs.setText("Notify me");
					sendNotifs.setToolTipText("For this to work, make sure your email settings are up to date in 'Settings' menu.\n" + notificationNote);
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
					group1LData2.horizontalSpan = 2;
					quotationBox.setLayoutData(group1LData2);
					quotationBox.setText("Update quotes");
					quotationBox
							.setToolTipText("You can request for an update of the quotations before the calculation is done.\n This is recommended the first time in the day.");
					quotationBox.setFont(MainGui.DEFAULTFONT);
					quotationBox.setSelection(true);

					quotationListBox = new Button(computeButtonGroup, SWT.CHECK);
					GridData group1LData = new GridData(SWT.FILL, SWT.TOP, true, false);
					group1LData.horizontalSpan = 2;
					quotationListBox.setLayoutData(group1LData);
					quotationListBox.setText("Update lists");
					quotationListBox
							.setToolTipText("In case you select one or several market lists, you can request for an update of the list before the calculation is done.\n However, this is time consuming.");
					quotationListBox.setFont(MainGui.DEFAULTFONT);
					quotationListBox.setSelection(false);
				}
				{
					refreshCompute = new Button(computeButtonGroup, SWT.PUSH | SWT.CENTER);
					GridData group1LData = new GridData(SWT.FILL, SWT.TOP, true, false);
					group1LData.horizontalSpan = 2;
					refreshCompute.setLayoutData(group1LData);
					refreshCompute.setFont(MainGui.DEFAULTFONT);
					refreshCompute.setText("Run");
					refreshCompute
							.setToolTipText("Will update the events calculation on Stocks according to the 'Event filter' and 'Stock filter' above.\n"
									+ "Be aware that refreshing all calculators and full markets can lead to prohibitive computation time.\n" + notificationNote
									+ "\n");
					refreshMonitoredListner = new EventRefreshController(monitoredStocksEventModel, this, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
						@Override
						public void widgetSelected(SelectionEvent evt) {

							monitoredStocksEventModel.setViewParam(0, selectedEventInfos);
							
							List<TaskId> taskIds = new ArrayList<TaskId>();
							if (quotationListBox.getSelection()) taskIds.add(TaskId.FetchLists);
							if (quotationBox.getSelection()) taskIds.add(TaskId.FetchQuotations);
							taskIds.add(TaskId.Clean); //Clean selected event infos
							taskIds.add(TaskId.Analysis);
							this.updateEventRefreshModelState(0l, taskIds.toArray(new TaskId[0]));
							super.widgetSelected(evt);
						}
					};
					if (filter.equals(StocksActionFilter.FILTERMONITORED)) {
						moniteredFilterbutton.setSelection(true);
						refreshComputeCurrentListener = refreshMonitoredListner;
						refreshCompute.addSelectionListener(refreshComputeCurrentListener);
					}
					refreshPortfoliosListener = new EventRefreshController(portfolioStocksEventModel, this, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
					
						@Override
						public void widgetSelected(SelectionEvent evt) {
							
							Collection<Stock> selectedStocks = extractStockSetFrom(selectedPortfolios); //XXX cf RefreshPortfolioStrategyEngine TODO
							
							portfolioStocksEventModel.setViewParamRoot(selectedStocks);
							portfolioStocksEventModel.setViewParam(1, selectedEventInfos);
							
							List<TaskId> taskIds = new ArrayList<TaskId>();
							if (quotationListBox.getSelection()) taskIds.add(TaskId.FetchLists);
							if (quotationBox.getSelection()) taskIds.add(TaskId.FetchQuotations);
							taskIds.add(TaskId.Clean); //Clean selected event infos
							taskIds.add(TaskId.Analysis);
							this.updateEventRefreshModelState(0l, taskIds.toArray(new TaskId[0]));
							super.widgetSelected(evt);
						}
					};
					if (filter.equals(StocksActionFilter.FILTERPORTFOLIO)) {
						portfolioFilterbutton.setSelection(true);
						refreshComputeCurrentListener = refreshPortfoliosListener;
						refreshCompute.addSelectionListener(refreshComputeCurrentListener);
					}
					refreshMarketsListener = new EventRefreshController(allStocksEventModel, this, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
						@Override
						public void widgetSelected(SelectionEvent evt) {

							allStocksEventModel.setViewParamRoot(selectedShareLists);
							allStocksEventModel.setViewParam(0, selectedEventInfos);
							
							List<TaskId> taskIds = new ArrayList<TaskId>();
							if (quotationListBox.getSelection()) taskIds.add(TaskId.FetchLists);
							if (quotationBox.getSelection()) taskIds.add(TaskId.FetchQuotations);
							taskIds.add(TaskId.Clean); //Clean selected event infos
							taskIds.add(TaskId.Analysis);
							this.updateEventRefreshModelState(0l, taskIds.toArray(new TaskId[0]));
							super.widgetSelected(evt);
						}
					};
					if (filter.equals(StocksActionFilter.FILTERMARKETS)) {
						marketsFilterbutton.setSelection(true);
						refreshComputeCurrentListener = refreshMarketsListener;
						refreshCompute.addSelectionListener(refreshComputeCurrentListener);
					}
					refreshAllStocksListener = new SelectionListener() {
						
						@Override
						public void widgetSelected(SelectionEvent e) {
							handle();
						}
						
						@Override
						public void widgetDefaultSelected(SelectionEvent e) {
							handle();
						}

						private void handle() {
							UserDialog dialog = new UserDialog(getShell(), "Compute All Stock is not implemented for potential performances issues.\nChoose among Monitored, All Portfolios or Markets in the Stock filter.", null);
							dialog.open();
						}
					};
					if (filter.equals(StocksActionFilter.ALLSTOCKS)) {
						allStocksFilteButton.setSelection(true);
						refreshComputeCurrentListener = refreshAllStocksListener;
						refreshCompute.addSelectionListener(refreshAllStocksListener);
					}

//					refreshComputeCurrentListener = refreshMonitoredListner;
//					refreshCompute.addSelectionListener(refreshComputeCurrentListener);

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
							if (treeRowToolTip != null && !treeRowToolTip.isDisposed())
								treeRowToolTip.dispose();

							TreeItem item = tbTree.getItem(new Point(event.x, event.y));

							if (item != null) {

								TreeItem parentItem = item.getParentItem();
								if (parentItem != null) {
									String mesg = ((EventValue) item.getData()).getMessage();
									if (mesg != null && !mesg.isEmpty()) {
										String newMsg = "";
										if (!mesg.contains("\n")) { //Not already formated
											int nbMsgCut = mesg.length();
											int maxChars = 100;
											for (int j = 0; j <= nbMsgCut / maxChars; j++) {
												newMsg = newMsg + mesg.substring(j * maxChars, Math.min((j + 1) * maxChars, mesg.length())) + "\n";
											}
										} else {
											newMsg = mesg;
										}
										String info = parentItem.getText(1) + " (" + parentItem.getText(0) + ") : \n" + item.getText(3) + "\n" + newMsg + "\n"+ item.getText(5) + "\n";

										Point point = new Point(event.x, event.y);
										Point map = getDisplay().map((Control) event.widget, null, point);
										treeRowToolTip = showTooltip(item.hashCode(), null, map.x, map.y, info);
									}
								}
							}
						}
					});
					tbTree.addListener(SWT.MouseExit, new Listener() {
						@Override
						public void handleEvent(Event event) {
							if (treeRowToolTip != null && !treeRowToolTip.isDisposed())
								treeRowToolTip.dispose();
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
			// items symbols
			TreeItem symbolEventsTableTreeItem = new TreeItem(cTabTrees.get(treeIdx), SWT.NONE);
			SymbolEvents se = eventsLists[treeIdx].get(k);
			symbolEventsTableTreeItem.setData(se);
			symbolEventsTableTreeItem.setText(0, se.getSymbol());
			symbolEventsTableTreeItem.setText(1, se.getSymbolName());
			symbolEventsTableTreeItem.setText(2, Float.valueOf(se.getWeight(this.action.getPonderationRule())).toString());
			// sous items events
			{
				subItems(symbolEventsTableTreeItem, se, new DataResultReversedComparator());
			}
		}

		// column pack listener
		cTabTrees.get(treeIdx).addListener(SWT.Expand, new Listener() {

			@Override
			public void handleEvent(Event event) {
				final TreeItem treeItem = (TreeItem) event.item;
				getDisplay().asyncExec(new Runnable() {

					@Override
					public void run() {

						// pack columns
						if (!treeItem.isDisposed()) {

							Tree tree = treeItem.getParent();

							// resize sash
							Point computeSize = tree.computeSize(SWT.DEFAULT, SWT.DEFAULT);
							int evtCmpW = computeSize.x + ctrlComposite.getSize().x;

							int shellW = ((SashForm) EventsComposite.this.getParent()).getSize().x;
							int evtSashWeight = Math.min(90, 100 * evtCmpW / shellW);
							SashForm sashParent = (SashForm) EventsComposite.this.getParent();
							int actualTotWeight = 0;
							for (int sashWeight : sashParent.getWeights()) {
								actualTotWeight = actualTotWeight + sashWeight;
							}
							if ((100 * sashParent.getWeights()[0] / actualTotWeight) < evtSashWeight)
								sashParent.setWeights(new int[] { evtSashWeight, 0, 100 - evtSashWeight });

							// pack columns
							columnPack(tree);
						}

					}

				});
			}
		});

		// initial pack
		columnPack(cTabTrees.get(treeIdx));

	}

	private void columnPack(Tree tree) {
		
		List<Integer> widths = new ArrayList<Integer>();
		TreeColumn[] columns = tree.getColumns();
		for (TreeColumn treeColumn : columns) {
			int minSize = 70;
			int availableSize = (this.getSize().x - ctrlComposite.getSize().x) / columnsHeaders.length;
			if (availableSize <= minSize) { //min columns are bigger than available
				widths.add(minSize);
			} else if (availableSize > minSize) { //min columns are smaller than available
				int actualPackedWidth = treeColumn.getWidth();
				if (actualPackedWidth > availableSize) {
					widths.add(availableSize);
				} else {
					widths.add(availableSize);
				}
			}
		}
		
		tree.setRedraw(false);
		for (int i = 0; i < columns.length; i++) {
			columns[i].setWidth(widths.get(i));
		}
		tree.setRedraw(true);

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

		switch (filter) {
		case FILTERMONITORED:
			EventsResources.getInstance().filterOutNonMonitoredEvents();
			break;
		case FILTERPORTFOLIO:
			EventsResources.getInstance().filterOutNonPortofolioEvents();
			break;
		case FILTERMARKETS:
			Collection<ShareListInfo> viewStateParams = allStocksEventModel.getViewParamRoot();
			if (viewStateParams != null)
				EventsResources.getInstance().filterOutCurrentMarketEvents(viewStateParams.toArray(new ShareListInfo[0]));
			break;
		case ALLSTOCKS:
			EventsResources.getInstance().filterOutNone();
			break;
		}
	}

	private void sortOnWeigthCriteriasActions() {

		try {
			Date filterDateStart = getFilterDate();
			EventsResources.getInstance().updateEventsTabsByCriteriaAndDate(
					filterDateStart, infCrit, supCrit, action.getPonderationRule(), action.getIndicators(), SelectedIndicatorsCalculationService.getAnalysisName());
			stockFilter();

		} catch (InvalidAlgorithmParameterException e) {
			UserDialog inst = new UserDialog(this.getShell(), e.toString(), null);
			inst.open();
		}
	}

	protected Date getFilterDate() {
		Calendar newDate = Calendar.getInstance();
		newDate.setTime(DateFactory.getNowEndDate());
		newDate.add(Calendar.DAY_OF_YEAR, -nbDaysFilter);
		return DateFactory.midnithDate(newDate.getTime());
	}

	private void reloadTabs() {
		eventsLists[0] = EventsResources.getInstance().getDebList();
		eventsLists[1] = EventsResources.getInstance().getFinList();
		eventsLists[2] = EventsResources.getInstance().getMidleList();
		eventsLists[3] = EventsResources.getInstance().getSortedList();
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Nb buy : " + eventsLists[0].size() + "; Nb sell : " + eventsLists[1].size() + "; Nb neutral : " + eventsLists[2].size());
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Nb All : " + eventsLists[3].size());
		refreshTabs();
	}

	private void refreshTabs() {

		int selectedTab = eventsCTabFolder.getSelectionIndex();
		for (int i = 0; i < tabs.length; i++) {
			cTabTrees.get(i).removeAll();
			updateTree(i);
		}
		sortedColumn = 2 * (selectedTab + 1);
	}

	private void setCrit(String infCritp, String supCritp, String dateCrit) {

		try {
			infCrit = Integer.parseInt(infCritp);
			MainPMScmd.getMyPrefs().put("gui.crit.inf", infCritp);
		} catch (NumberFormatException e) {
			UserDialog inst = new UserDialog(this.getShell(), e.toString(), null);
			inst.open();
			sellCriteriaWeigthtext.setText(this.infCrit.toString());
		}

		try {
			supCrit = Integer.parseInt(supCritp);
			MainPMScmd.getMyPrefs().put("gui.crit.sup", supCritp);
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
			dateCriteriatext.setText(MainPMScmd.getMyPrefs().get("gui.crit.calcnbmonths", "18"));
		}

		try {
			sortAndReload();
		} catch (Exception e1) {
			UserDialog dialog = new UserDialog(this.getShell(), "Invalid date : " + dateCriteriatext, null);
			dialog.open();
		}

		MainPMScmd.getMyPrefs().flushy();
		
	}

	protected void sortAndReload() {
		try {
			EventsComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
			sortOnWeigthCriteriasActions();
			reloadTabs();
		} finally {
			EventsComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
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

				switch (colNum) {
				case 2: {
					List<SymbolEvents> sortedColumnItems = new ArrayList<SymbolEvents>();
					sortedColumnItems.addAll(eventsLists[tabNum]);
					if (sortedColumn != null && sortedColumn.equals(colNum * (tabNum + 1))) {
						Collections.reverse(sortedColumnItems);
					} else {
						SymbolEvents.sortList(sortedColumnItems, new SymbolEventComparator((tabNum == 1), sortAttAcc, this.action.getPonderationRule()));
					}
					eventsLists[tabNum] = sortedColumnItems;
					// Refresh
					cTabTrees.get(tabNum).removeAll();
					updateTree(tabNum);
					sortedColumn = colNum * (tabNum + 1);
					break;
				}
				case 3:
				case 4:
				case 5: {
					TreeItem[] selection = cTabTrees.get(tabNum).getSelection();
					if (selection != null && selection.length == 1 && selection[0].getExpanded()) {
						selection[0].removeAll();
						SymbolEvents se = (SymbolEvents) selection[0].getData();
						Boolean isAsc = (sortedColumn == null || !sortedColumn.equals(colNum * (tabNum + 1)));
						subItems(selection[0], se, new EventValuesComparator(isAsc, sortAttAcc));
						selection[0].setExpanded(true);
						if (isAsc) {
							sortedColumn = colNum * (tabNum + 1);
						} else {
							sortedColumn = null;
						}
					}
					break;
				}
				default: {
					List<SymbolEvents> sortedColumnItems = new ArrayList<SymbolEvents>();
					sortedColumnItems.addAll(eventsLists[tabNum]);
					if (sortedColumn != null && sortedColumn.equals(colNum * (tabNum + 1))) {
						Collections.reverse(sortedColumnItems);
					} else {
						SymbolEvents.sortList(sortedColumnItems, new SymbolEventComparator(true, sortAttAcc));
					}
					eventsLists[tabNum] = sortedColumnItems;
					// Refresh
					cTabTrees.get(tabNum).removeAll();
					updateTree(tabNum);
					sortedColumn = colNum * (tabNum + 1);
				}
				}

			}

		} finally {
			getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}
	}

	public void initRefreshAction() {

		getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
		refreshCompute.setEnabled(false);
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
		refreshCompute.setEnabled(true);
	}

	@Override
	public void refreshView(List<Exception> exceptions) {

		sortAndReload();
		enableRefreshButtons();

		if (isVisible()) {
			for (final Exception exception : exceptions) {
				if (exception instanceof EventRefreshException) {
					UserDialog dialog = new UserDialog(getShell(), "Couldn't update all trends and events calculations. Check that date bounds are not out of range.", exceptions.toString());
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
							"Force request", exception + " has already been fulfilled sometime today.", 
							"It should not need updating but you still can force and run it again by first pressing the button below then running your request again.",
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

	public Date getAnalysisStartDate() {
		Calendar newDate = Calendar.getInstance();
		newDate.setTime(DateFactory.getNowEndDate());
		newDate.add(Calendar.MONTH, -nbMonthsAnalysis);
		return DateFactory.midnithDate(newDate.getTime());
	}

	@Override
	public Date getAnalysisEndDate() {
		return DateFactory.midnithDate(DateFactory.getNowEndDate());
	}
	

	private void eventFilterChange(final Set<EventInfo> eventDefs) {

		if (eventDefs == null || eventDefs.isEmpty()) {

			EventsResources.getInstance().resetSortLists();
			reloadTabs();

		} else {

			Integer sellEventTriggerThreshold = ((EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getSellEventTriggerThreshold();
			Integer buyEventTriggerThreshold = ((EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getBuyEventTriggerThreshold();
			PonderationRule ponderationRule = (evtFilterLatestTick.getSelection()) ? new LatestEventsAllIndDefsPonderationRule() : new DefaultPonderationRule(sellEventTriggerThreshold, buyEventTriggerThreshold);
			EventInfo[] eventDefArray = eventDefs.toArray(new EventInfo[0]);
			setAction(new EventsActionSort(ponderationRule, eventDefArray));

			sortAndReload();

		}

		AnalysisClient.setEmailSendingFilter(new EmailSendingFilter(getFilterDate(), eventDefs));
	}

	@Override
	public void setVisible(boolean visible) {
		
		if (visible && needInit) {
			try {
				getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
				initData();
			} finally {
				getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
			}
		}
		super.setVisible(visible);
	}

	public Button getSendNotifs() {
		return sendNotifs;
	}

	protected Collection<Stock> extractStockSetFrom(Set<ShareListInfo> selectedPortfolios) {
		Collection<Stock> selectedStocks = new HashSet<Stock>();
		for (ShareListInfo selectedPortfolio : selectedPortfolios) {
			AbstractSharesList portfolio = PortfolioMgr.getInstance().getPortfolio(selectedPortfolio.info());
			for (Stock stock : portfolio.getListShares().keySet()) {
				selectedStocks.add(stock);
			}
		}
		return selectedStocks;
	}


}
