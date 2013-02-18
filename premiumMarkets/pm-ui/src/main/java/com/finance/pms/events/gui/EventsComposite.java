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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.InvalidAlgorithmParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.prefs.BackingStoreException;

import org.apache.commons.lang.NotImplementedException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.custom.TableTreeItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.CursorFactory;
import com.finance.pms.ErrorDialog;
import com.finance.pms.IndicatorCalculationServiceMain;
import com.finance.pms.LogComposite;
import com.finance.pms.MainGui;
import com.finance.pms.MainPMScmd;
import com.finance.pms.PopupMenu;
import com.finance.pms.RefreshableView;
import com.finance.pms.SpringContext;
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
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.gui.EventsActionSort.EventsActionSortEnum;
import com.finance.pms.events.pounderationrules.DataResultReversedComparator;
import com.finance.pms.events.pounderationrules.DefaultPonderationRule;
import com.finance.pms.events.pounderationrules.IndicatorPonderationRule;
import com.finance.pms.events.pounderationrules.LatestEventsIndicatorOnlyPonderationRule;
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
	/** The LOGGER. */
	public static MyLogger LOGGER = MyLogger.getLogger(EventsComposite.class);
	/** The events c tab folder. */
	private CTabFolder eventsCTabFolder;
	/** The sell criteria weigthtext. */
	private Text sellCriteriaWeigthtext;
	/** The sell criteria weigth label. */
	private Label sellCriteriaWeigthLabel;
	/** The buy criteria weigthtext. */
	private Text buyCriteriaWeigthtext;
	/** The buy weigth crit label. */
	private Label buyWeigthCritLabel;
	/** The date criteriatext. */
	private Text dateCriteriatext;
	/** The date criteria label. */
	private Label dateCriteriaLabel;
	/** The apply crit button. */
	private Button applyCritButton;
	/** The monitered filterbutton. */
	private Button moniteredFilterbutton;
	private Button portfolioFilterbutton;
	private Button allStocksFilteButton;
	/** The category filterbutton. */
	private Button selectedMarketsFilterbutton;
	/** The refresh monitored. */
	private Button refreshMonitored;
	private Button refreshPortfolios;
	/** The refresh all. */
	private Button refreshSelectedMarkets;
	/** The criteriasgroup. */
	private Group criteriasgroup;
	/** The symbol column. */
	private TableColumn symbolColumn;
	/** The sort button group. */
	private Group sortButtonGroup;
	/** The filter button group. */
	private Group filterButtonGroup;
	/** The refresh button group. */
	private Group refreshButtonGroup;
	/** The quotation box. */
	private Button quotationBox;
	/** The quotation list box. */
	private Button quotationListBox;
	//Perso
	/** The c tab item. */
	private ArrayList<CTabItem> cTabItem = new ArrayList<CTabItem>();
	/** The table. */
	private ArrayList<TableTree> table = new ArrayList<TableTree>();
	
	/** The analyse start date. */
	private Date analyseStartDate;
	/** The inf crit. */
	private Integer infCrit = new Integer(MainPMScmd.getPrefs().get("gui.crit.inf", "-1")); //-2;
	/** The sup crit. */
	private Integer supCrit = new Integer(MainPMScmd.getPrefs().get("gui.crit.sup", "1")); // 4;
	/** The action. */
	private EventsActionSort action = new EventsActionSort(EventsActionSortEnum.LATESTNOALERTSEVENTSTXT,new LatestEventsIndicatorOnlyPonderationRule(), EventDefinition.loadPassPrefsEventDefinitions().toArray(new EventDefinition[0]));
	/** The filter. */
	private EventsActionFilter filter = EventsActionFilter.ALL;
	private LogComposite logComposite;
	//Appli
	/** The tabs. */
	final String[] tabs = { "Buy", "Sell", "Neutral", "All" }; //,"Non Accurate"};
	/** The events lists. */
	private List<SymbolEvents>[] eventsLists = new List[4];
	/** The colonnes. */
	final String[] columns = { "Symbol", "Name", "Weigth", "Date", "Event definition", "Event type" };
	/** The sortable collumns att accesors. */
	final String[] sortableCollumnsAttAccesors = { "getSymbol", "getSymbolName", "getWeight", "getLastDate", null, null };
	
	private EventModel<RefreshAllEventStrategyEngine> allStocksEventModel;
	private EventModel<RefreshMonitoredStrategyEngine> monitoredStocksEventModel;
	private EventModel<RefreshPortfolioStrategyEngine> portfolioStocksEventModel;
	
	
	public EventsComposite(Composite parent, int style, LogComposite logComposite, Date analysisStartDate) {
		super(parent, style);
		
		this.analyseStartDate = analysisStartDate;
		this.logComposite = logComposite;
		allStocksEventModel = EventModel.getInstance(new RefreshAllEventStrategyEngine());
		monitoredStocksEventModel = EventModel.getInstance(new RefreshMonitoredStrategyEngine());
		portfolioStocksEventModel = EventModel.getInstance(new RefreshPortfolioStrategyEngine(), logComposite);

		initGUI();
	}


	public static void main(String[] args) {
		SpringContext ctx = new SpringContext();
		ctx.setDataSource(args[0]);
		ctx.setMasSource(args[0], "false");
		ctx.loadBeans(new String[] { "/connexions.xml", "/swtclients.xml", "talibanalysisservices.xml","masanalysisservices.xml" });
		ctx.refresh();
		showGUI();
	}


	public static void showGUI() {
		
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		EventsComposite inst = new EventsComposite(shell, SWT.NULL, new LogComposite(shell, SWT.NULL), MainGui.analyseStartDateCalculation());
		try {
			FileInputStream iconImg = new FileInputStream(new File (System.getProperty("installdir")+File.separator+"icons/icon.img"));
			shell.setImage(new Image(display, iconImg));
		} catch (FileNotFoundException e) {
			LOGGER.error(e,e);
		}
		shell.setText("Premium Markets - Events");
		shell.setFont(MainGui.DEFAULTFONT);
		Point size = inst.getSize();
		shell.setLayout(new FillLayout());
		shell.layout();
		if (size.x == 0 && size.y == 0) {
			inst.pack();
			shell.pack();
		} else {
			Rectangle shellBounds = shell.computeTrim(0, 0, size.x, size.y);
			shell.setSize(shellBounds.width, shellBounds.height);
		}
		shell.open();
		while (!shell.isDisposed()) {
			try {
				if (!display.readAndDispatch())
					display.sleep();
			} catch (RuntimeException e) {
				LOGGER.error("Error in Events Gui : " + e.getMessage(), e);
				LOGGER.debug("Error in Events Gui : ", e);
				inst.enableRefreshButtons();
			} catch (Error e) {
				LOGGER.error("Error in  Gui : " + e.getMessage(), e);
				LOGGER.debug("Error in  Gui : ", e);
				inst.enableRefreshButtons();
			}
		}
	}

	/**
	 * Inits the data.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void initData() {

		portfolioFilterbutton.setSelection(true);
		setFilter(EventsActionFilter.FILTERPORTFOLIOTXT);
		sortOnWeigthCriteriasActions();
		reloadTabs();
	}

	/**
	 * Inits the gui.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void initGUI() {
		try {
			
			GridLayout thisLayout = new GridLayout();
			thisLayout.numColumns = 2;
			this.setLayout(thisLayout);
			
			this.setBackground(new Color(getDisplay(), 239, 183, 103));
			{
				eventsCTabFolder = new CTabFolder(this, SWT.NONE);
				GridData cTabFolder1LData = new GridData();
				cTabFolder1LData.verticalAlignment = GridData.FILL;
				cTabFolder1LData.horizontalAlignment = GridData.FILL;
				cTabFolder1LData.verticalSpan = 4;
				cTabFolder1LData.grabExcessHorizontalSpace = true;
				cTabFolder1LData.grabExcessVerticalSpace = true;
				cTabFolder1LData.heightHint = 500;
				eventsCTabFolder.setLayoutData(cTabFolder1LData);
				eventsCTabFolder.setBackground(new Color(getDisplay(), 239, 203, 152));
				eventsCTabFolder.setSelectionBackground(new Color(getDisplay(), 239, 183, 103));
				buildEventsTab();
				eventsCTabFolder.setSelection(0);
			}
			{
				criteriasgroup = new Group(this, SWT.NONE);
				GridData criteriasgroupLData = new GridData(SWT.FILL,SWT.TOP,true,false);
				criteriasgroup.setLayoutData(criteriasgroupLData);
				GridLayout group1Layout2 = new GridLayout();
				group1Layout2.numColumns = 2;
				criteriasgroup.setLayout(group1Layout2);
				criteriasgroup.setText("Trend sorting criteria :");
				criteriasgroup.setFont(MainGui.DEFAULTFONT);
				criteriasgroup.setBackground(new Color(getDisplay(), 239, 203, 152));
				{
					buyWeigthCritLabel = new Label(criteriasgroup, SWT.PUSH | SWT.CENTER);
					buyWeigthCritLabel.setBackground(new Color(getDisplay(), 239, 203, 152));
					buyWeigthCritLabel.setText("Buy weigth is >= ");
					buyWeigthCritLabel.setFont(MainGui.DEFAULTFONT);
				}
				{
					buyCriteriaWeigthtext = new Text(criteriasgroup, SWT.NONE);
					GridData buyCriteriaWeigthtextLData = new GridData(SWT.FILL,SWT.TOP,true,false);
					buyCriteriaWeigthtext.setLayoutData(buyCriteriaWeigthtextLData);
					buyCriteriaWeigthtext.setText(supCrit.toString());
					buyCriteriaWeigthtext.setFont(MainGui.CONTENTFONT);
					buyCriteriaWeigthtext.addListener(SWT.DefaultSelection, new Listener() {
						public void handleEvent(Event e) {
							setCrit(sellCriteriaWeigthtext.getText(), buyCriteriaWeigthtext.getText(), dateCriteriatext.getText());
						}
					});
				}
				{
					sellCriteriaWeigthLabel = new Label(criteriasgroup, SWT.CENTER);
					sellCriteriaWeigthLabel.setBackground(new Color(getDisplay(), 239, 203, 152));
					sellCriteriaWeigthLabel.setText("Sell weigth is <= ");
					sellCriteriaWeigthLabel.setFont(MainGui.DEFAULTFONT);
				}
				{
					sellCriteriaWeigthtext = new Text(criteriasgroup, SWT.NONE);
					GridData sellCriteriaWeigthtextLData = new GridData(SWT.FILL,SWT.TOP,true,false);
					sellCriteriaWeigthtext.setLayoutData(sellCriteriaWeigthtextLData);
					sellCriteriaWeigthtext.setText(infCrit.toString());
					sellCriteriaWeigthtext.setFont(MainGui.CONTENTFONT);
					sellCriteriaWeigthtext.addListener(SWT.DefaultSelection, new Listener() {
						public void handleEvent(Event e) {
							setCrit(sellCriteriaWeigthtext.getText(), buyCriteriaWeigthtext.getText(), dateCriteriatext.getText());
						}
					});
				}
				{
					dateCriteriaLabel = new Label(criteriasgroup, SWT.PUSH | SWT.CENTER);
					dateCriteriaLabel.setBackground(new Color(getDisplay(), 239, 203, 152));
					dateCriteriaLabel.setText("Start on the");
					dateCriteriaLabel.setToolTipText("This is the start date you wish to see the events from. Date format is dd MMM yyyy");
					dateCriteriaLabel.setFont(MainGui.DEFAULTFONT);
				}
				{
					dateCriteriatext = new Text(criteriasgroup, SWT.NONE);
					dateCriteriatext.setText(new SimpleDateFormat("dd MMM yyyy").format(analyseStartDate));
					dateCriteriatext.setFont(MainGui.CONTENTFONT);
					GridData sellCriteriaDatetextLData = new GridData(SWT.FILL,SWT.TOP,true,false);
					dateCriteriatext.setLayoutData(sellCriteriaDatetextLData);
					dateCriteriatext.addListener(SWT.DefaultSelection, new Listener() {
						public void handleEvent(Event e) {
							setCrit(sellCriteriaWeigthtext.getText(), buyCriteriaWeigthtext.getText(), dateCriteriatext.getText());
						}
					});
				}
				{
					applyCritButton = new Button(criteriasgroup, SWT.PUSH | SWT.CENTER);
					GridData sellCriteriaDatetextLData = new GridData(SWT.END,SWT.TOP,true,false);
					sellCriteriaDatetextLData.horizontalSpan=2;
					applyCritButton.setLayoutData(sellCriteriaDatetextLData);
					applyCritButton.setText("Apply");
					applyCritButton.setFont(MainGui.DEFAULTFONT);
					applyCritButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent evt) {
							setCrit(sellCriteriaWeigthtext.getText(), buyCriteriaWeigthtext.getText(), dateCriteriatext.getText());
						}
					});
				}
			}
			{
				sortButtonGroup = new Group(this, SWT.NONE);
				GridData group1LData1 = new GridData(SWT.FILL,SWT.TOP,true,false);
				sortButtonGroup.setLayoutData(group1LData1);
				GridLayout group1Layout1 = new GridLayout();
				group1Layout1.numColumns = 1;
				sortButtonGroup.setLayout(group1Layout1);
				sortButtonGroup.setText("Event weight filters :");
				sortButtonGroup.setFont(MainGui.DEFAULTFONT);
				sortButtonGroup.setBackground(new Color(getDisplay(), 239, 203, 152));
				{
					final Combo combo = new Combo(sortButtonGroup, SWT.NONE);
					GridData group1LData = new GridData(SWT.BEGINNING,SWT.TOP,true,false);
					combo.setLayoutData(group1LData);
					combo.add(EventsActionSortEnum.DEFAULTWEIGTHTXT.getText());
					combo.add(EventsActionSortEnum.LATESTNOALERTSEVENTSTXT.getText());
					List<EventDefinition> pmEventDefinitionsList = new ArrayList<EventDefinition>(EventDefinition.loadPassPrefsEventDefinitions());
					for (int j = 0, n = pmEventDefinitionsList.size(); j < n; j++) {
						 combo.add(pmEventDefinitionsList.get(j).getEventDef());
					}
					combo.setText(action.getActionSortEnum().getText());
					combo.setFont(MainGui.DEFAULTFONT);
					combo.addSelectionListener(new SelectionListener() {
						
						public void widgetDefaultSelected(SelectionEvent arg0) {
						}

						public void widgetSelected(SelectionEvent arg0) {
							if (combo.getText().equals(EventsActionSortEnum.DEFAULTWEIGTHTXT.getText())) {
								Integer sellEventTriggerThreshold = ((EventSignalConfig)ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getSellEventTriggerThreshold(); 
								Integer buyEventTriggerThreshold = ((EventSignalConfig)ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getBuyEventTriggerThreshold();
								setAction(new EventsActionSort(EventsActionSortEnum.DEFAULTWEIGTHTXT, new DefaultPonderationRule(sellEventTriggerThreshold, buyEventTriggerThreshold), EventDefinition.loadPassPrefsEventDefinitions().toArray(new EventDefinition[0])));
							} else if (combo.getText().equals(EventsActionSortEnum.LATESTEVENTSTXT.getText())) {
								//FIXME//setAction(new EventsActionSort(EventsActionSortEnum.LATESTEVENTSTXT, new LatestEventsPonderationRule()));
								throw new NotImplementedException();
							} else if (combo.getText().equals(EventsActionSortEnum.LATESTNOALERTSEVENTSTXT.getText())) {
								setAction(new EventsActionSort(EventsActionSortEnum.LATESTNOALERTSEVENTSTXT, new LatestEventsIndicatorOnlyPonderationRule(), EventDefinition.loadPassPrefsEventDefinitions().toArray(new EventDefinition[0])));
							} else {
								EventDefinition eventDefinition = EventDefinition.valueOfEventDef(combo.getText());
								setAction(new EventsActionSort(EventsActionSortEnum.TALIBWEIGHTTXT, new IndicatorPonderationRule(eventDefinition.getEventDefId()), eventDefinition));
							}
							sortOnWeigthCriteriasActions();
							reloadTabs();
						}
					});
				}
			}
			{
				filterButtonGroup = new Group(this, SWT.NONE);
				GridData group1LData1 = new GridData(SWT.FILL,SWT.TOP,true,false);
				filterButtonGroup.setLayoutData(group1LData1);
				GridLayout group1Layout1 = new GridLayout();
				filterButtonGroup.setLayout(group1Layout1);
				filterButtonGroup.setText("Show Only :");
				filterButtonGroup.setFont(MainGui.DEFAULTFONT);
				filterButtonGroup.setBackground(new Color(getDisplay(), 239, 203, 152));
				{
					moniteredFilterbutton = new Button(filterButtonGroup, SWT.RADIO | SWT.LEAD);
					moniteredFilterbutton.setText(EventsActionFilter.FILTERMONITOREDTXT.getText());
					moniteredFilterbutton.setFont(MainGui.DEFAULTFONT);
					GridData group1LData = new GridData(GridData.FILL_HORIZONTAL);
					moniteredFilterbutton.setLayoutData(group1LData);
					moniteredFilterbutton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent evt) {
							
							setFilter(EventsActionFilter.FILTERMONITOREDTXT);
							sortOnWeigthCriteriasActions();
							reloadTabs();
						}
					});
				}
				{
					portfolioFilterbutton = new Button(filterButtonGroup, SWT.RADIO | SWT.LEAD);
					portfolioFilterbutton.setText(EventsActionFilter.FILTERPORTFOLIOTXT.getText());
					portfolioFilterbutton.setFont(MainGui.DEFAULTFONT);
					GridData group1LData = new GridData(GridData.FILL_HORIZONTAL);
					portfolioFilterbutton.setLayoutData(group1LData);
					portfolioFilterbutton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent evt) {
							setFilter(EventsActionFilter.FILTERPORTFOLIOTXT);
							sortOnWeigthCriteriasActions();
							reloadTabs();
						}
					});
				}
				{
					selectedMarketsFilterbutton = new Button(filterButtonGroup, SWT.RADIO | SWT.LEAD);
					GridData group1LData = new GridData(GridData.FILL_HORIZONTAL);
					selectedMarketsFilterbutton.setLayoutData(group1LData);
					selectedMarketsFilterbutton.setText(EventsActionFilter.FILTERINDICESTXT.getText());
					selectedMarketsFilterbutton.setFont(MainGui.DEFAULTFONT);
					selectedMarketsFilterbutton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent evt) {
							setFilter(EventsActionFilter.FILTERINDICESTXT);
							
							//Windos fix
							selectedMarketsFilterbutton.setSelection(true);
							portfolioFilterbutton.setSelection(false);
							moniteredFilterbutton.setSelection(false);
							allStocksFilteButton.setSelection(false);
							//End Windos fix
							
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
							PopupMenu<ShareListInfo> popupMenu = new PopupMenu<ShareListInfo>(EventsComposite.this, refreshSelectedMarkets, shareLists, selectedShareLists, SWT.CHECK);
							popupMenu.open();
							allStocksEventModel.setViewStateParams(selectedShareLists.toArray());
							sortOnWeigthCriteriasActions();
							reloadTabs();

						}
					});
				}
				{
					allStocksFilteButton = new Button(filterButtonGroup, SWT.RADIO | SWT.LEAD);
					GridData group1LData = new GridData(GridData.FILL_HORIZONTAL);
					allStocksFilteButton.setLayoutData(group1LData);
					allStocksFilteButton.setText(EventsActionFilter.ALL.getText());
					allStocksFilteButton.setFont(MainGui.DEFAULTFONT);
					allStocksFilteButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent evt) {
							setFilter(EventsActionFilter.ALL);
							sortOnWeigthCriteriasActions();
							reloadTabs();
						}
					});
				}
			}
			{
				refreshButtonGroup = new Group(this, SWT.NONE);
				GridData group1LData1 = new GridData(SWT.FILL, SWT.BOTTOM,true,false);
				refreshButtonGroup.setLayoutData(group1LData1);
				GridLayout group1Layout1 = new GridLayout(2, true);
				refreshButtonGroup.setLayout(group1Layout1);
				refreshButtonGroup.setText("Compute Events for :");
				refreshButtonGroup.setFont(MainGui.DEFAULTFONT);
				refreshButtonGroup.setBackground(new Color(getDisplay(), 239, 203, 152));
				{
					quotationBox = new Button(refreshButtonGroup, SWT.CHECK);
					GridData group1LData2 =  new GridData(SWT.FILL, SWT.TOP,true,false);
					quotationBox.setLayoutData(group1LData2);
					quotationBox.setText("Update quotes");
					quotationBox.setToolTipText("You can request for an update of the quotations before the calculation is done.\n This is recommended the first time in the day.");
					quotationBox.setFont(MainGui.DEFAULTFONT);
					quotationBox.setGrayed(true);
					quotationBox.setSelection(true);
					
					quotationListBox = new Button(refreshButtonGroup, SWT.CHECK);
					GridData group1LData =  new GridData(SWT.FILL, SWT.TOP,true,false);
					quotationListBox.setLayoutData(group1LData);
					quotationListBox.setText("Update lists");
					quotationListBox.setToolTipText("In case you select one or several market lists, you can request for an update of the list before the calculation is done.\n However, this is time consuming.");
					quotationListBox.setFont(MainGui.DEFAULTFONT);
					quotationListBox.setGrayed(true);
					quotationListBox.setSelection(false);
				}
				{
					refreshMonitored = new Button(refreshButtonGroup, SWT.PUSH | SWT.CENTER);
					GridData group1LData = new GridData(SWT.FILL, SWT.TOP,true,false);
					group1LData.horizontalSpan = 2;
					refreshMonitored.setLayoutData(group1LData);
					refreshMonitored.setText(EventsActionFilter.FILTERMONITOREDTXT.getText());
					refreshMonitored.setFont(MainGui.DEFAULTFONT);
					refreshMonitored.addMouseListener(new EventRefreshController(monitoredStocksEventModel, this) {
						@Override
						public void mouseDown(MouseEvent evt) {
							this.updateEventRefreshModelState(quotationListBox.getSelection(), quotationBox.getSelection(), true,false, 0l);
							initRefreshAction();
							super.mouseDown(evt);
						}
					});
				}
				{
					refreshPortfolios = new Button(refreshButtonGroup, SWT.PUSH | SWT.CENTER);
					GridData group1LData = new GridData(SWT.FILL, SWT.TOP,true,false);
					group1LData.horizontalSpan = 2;
					refreshPortfolios.setLayoutData(group1LData);
					refreshPortfolios.setText(EventsActionFilter.FILTERPORTFOLIOTXT.getText());
					refreshPortfolios.setFont(MainGui.DEFAULTFONT);
					refreshPortfolios.addMouseListener(new EventRefreshController(portfolioStocksEventModel, this) {
						@Override
						public void mouseDown(MouseEvent evt) {
							this.updateEventRefreshModelState(quotationListBox.getSelection(), quotationBox.getSelection(), true, false, 0l);
							initRefreshAction();
							super.mouseDown(evt);
						}
					});
				}
				{
					refreshSelectedMarkets = new Button(refreshButtonGroup, SWT.PUSH | SWT.CENTER);
					GridData group1LData = new GridData(SWT.FILL, SWT.TOP,true,false);
					group1LData.horizontalSpan = 2;
					refreshSelectedMarkets.setLayoutData(group1LData);
					refreshSelectedMarkets.setText(EventsActionFilter.FILTERINDICESTXT.getText());
					refreshSelectedMarkets.setFont(MainGui.DEFAULTFONT);
					refreshSelectedMarkets.addMouseListener(new EventRefreshController(allStocksEventModel, this) {
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
							PopupMenu<ShareListInfo> popupMenu = new PopupMenu<ShareListInfo>(EventsComposite.this, refreshSelectedMarkets, shareLists, selectedShareLists, SWT.CHECK);
							popupMenu.open();
					
							allStocksEventModel.setViewStateParams(selectedShareLists.toArray());
							this.updateEventRefreshModelState(quotationListBox.getSelection(), quotationBox.getSelection(), true, false, 0l);
							
							initRefreshAction();
							super.mouseDown(evt);
						}
					});
				}
				{
					Button clear = new Button(refreshButtonGroup, SWT.PUSH | SWT.CENTER);
					GridData group1LData = new GridData(SWT.FILL, SWT.TOP,true,false);
					group1LData.horizontalSpan = 2;
					clear.setLayoutData(group1LData);
					clear.setText("Clear previous calculations");
					clear.setFont(MainGui.DEFAULTFONT);
					clear.addSelectionListener(new SelectionListener() {
						
						public void widgetSelected(SelectionEvent e) {
							selection();
						}
				
						public void widgetDefaultSelected(SelectionEvent e) {
							selection();
						}

						private void selection() {
							EventsComposite.this.getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
							try {
								EventsResources.getInstance().crudDeleteEventsForIndicators(IndicatorCalculationServiceMain.UI_ANALYSIS, analyseStartDate, EventSignalConfig.getNewDate(), true, EventDefinition.loadPassPrefsEventDefinitions().toArray(new EventDefinition[0]));
								sortOnWeigthCriteriasActions();
								reloadTabs();
								enableRefreshButtons();
							} finally {
								EventsComposite.this.getParent().setCursor(CursorFactory.getCursor(SWT.ARROW));
							}
						}
					});
				}
				refreshButtonGroup.layout();
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

	/**
	 * Builds the events tab.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void buildEventsTab() {
		for (int i = 0; i < tabs.length; i++) {
			cTabItem.add(i, new CTabItem(eventsCTabFolder, SWT.NONE));
			cTabItem.get(i).setText(tabs[i]);
			cTabItem.get(i).setFont(MainGui.DEFAULTFONT);
			{
				{
					TableTree tbTree = new TableTree(eventsCTabFolder, SWT.NONE);
					tbTree.setFont(MainGui.CONTENTFONT);
					final int k = i;
					tbTree.addTreeListener(new TreeListener() {
						public void treeCollapsed(TreeEvent arg0) {
						}

						public void treeExpanded(TreeEvent arg0) {
							for (int j = 0; j < columns.length; j++) {
								table.get(k).getTable().getColumn(j).pack();
							}
						}
					});
					table.add(i, tbTree);
					table.get(i).setSize(41, 11);
					table.get(i).getTable().setLinesVisible(false);
					table.get(i).getTable().setHeaderVisible(true);

					cTabItem.get(i).setControl(table.get(i));
					for (int j = 0; j < columns.length; j++) {
						symbolColumn = new TableColumn(table.get(i).getTable(), SWT.NONE);
						symbolColumn.setText(columns[j]);
					}
					for (int j = 0; j < columns.length; j++) {
						final int l = j;
						table.get(i).getTable().getColumn(j).pack();
						//table.get(i).getTable().getColumn(j).setWidth(5);
						table.get(i).getTable().getColumn(j).addSelectionListener(new SelectionListener() {
							public void widgetDefaultSelected(SelectionEvent arg0) {
								LOGGER.debug("Event on default on tab " + k + " and column " + l);
								sortColumn(k, l);
							}

							public void widgetSelected(SelectionEvent arg0) {
								// TODO Auto-generated method stub
							}
						});
					}
				}
			}
		}
	}

	/**
	 * Items.
	 * 
	 * @param i
	 *            the i
	 * 
	 * @author Guillaume Thoreton
	 */
	private void items(int i) {
		for (int k = 0; k < eventsLists[i].size(); k++) {
			//items symbols
			TableTreeItem symbolEventsTableTreeItem = new TableTreeItem(table.get(i), SWT.NONE);
			SymbolEvents se = eventsLists[i].get(k);
			symbolEventsTableTreeItem.setText(0, se.getSymbol());
			symbolEventsTableTreeItem.setText(1, se.getSymbolName());
			symbolEventsTableTreeItem.setText(2, new Float(se.getWeight(this.action.getPonderationRule())).toString());
			//sous items events
			{
				subItems(symbolEventsTableTreeItem, se);
			}
		}
		for (int j = 0; j < columns.length; j++) {
			table.get(i).getTable().getColumn(j).pack();
		}
	}

	/**
	 * Sub items.
	 * 
	 * @param symbolEventsTableTreeItem
	 *            the symbol events table tree item
	 * @param se
	 *            the se
	 * 
	 * @author Guillaume Thoreton
	 */
	private void subItems(TableTreeItem symbolEventsTableTreeItem, SymbolEvents se) {
		ArrayList<EventValue> dataResultsTableTreeItem = se.getSortedDataResultList(new DataResultReversedComparator()); //metier
		for (int l = 0; l < dataResultsTableTreeItem.size(); l++) {
			{
				TableTreeItem sousTableTreeItem = new TableTreeItem(symbolEventsTableTreeItem, SWT.NONE);
				EventValue drv = dataResultsTableTreeItem.get(l);
				//sousTableTreeItem.setFont(font);
				sousTableTreeItem.setText(3, new SimpleDateFormat("dd MMM yyyy").format(drv.getDate()));
				sousTableTreeItem.setText(4, (se.getEventDefList().get(drv.getEventDefId())) + " " + drv.getMessage());
				sousTableTreeItem.setText(5, String.valueOf(drv.getEventType()));
			}
		}
	}

	/**
	 * Filter actions.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void filterActions() {
		try {
			EventsComposite.this.setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
			switch (filter) {
			case FILTERMONITOREDTXT:
				EventsResources.getInstance().filterOutNonMonitoredEvents();
				break;
			case FILTERPORTFOLIOTXT:
				EventsResources.getInstance().filterOutNonPortofolioEvents();
				break;
			case FILTERINDICESTXT:
				EventsResources.getInstance().filterOutCurrentMarketEvents(allStocksEventModel.getViewStateParams());
				break;
			case ALL:
				EventsResources.getInstance().filterOutNone();
				break;
			}
		} finally {
			EventsComposite.this.setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}
	}

	/**
	 * Sort on weigth criterias actions.
	 * 
	 * @author Guillaume Thoreton
	 * @param params 
	 */
	private void sortOnWeigthCriteriasActions() {
		try {
			EventsComposite.this.setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
			
			EventsResources.getInstance().loadEventsByCriteriaAndDate(analyseStartDate, infCrit, supCrit, action.getPonderationRule(), action.getIndicators(), IndicatorCalculationServiceMain.UI_ANALYSIS);
			filterActions();
		
		} catch (InvalidAlgorithmParameterException e) {
			ErrorDialog inst = new ErrorDialog(this.getShell(), SWT.NULL, e.toString(), null);
			inst.open();
		} finally {
			EventsComposite.this.setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}
	}

	/**
	 * Reload tabs.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void reloadTabs() {
		eventsLists[0] = EventsResources.getInstance().getDebList();
		eventsLists[1] = EventsResources.getInstance().getFinList();
		eventsLists[2] = EventsResources.getInstance().getMidleList();
		eventsLists[3] = EventsResources.getInstance().getSortedList();
		LOGGER.debug("Nb buy : " + eventsLists[0].size() + "; Nb sell : " + eventsLists[1].size() + "; Nb neutral : "+ eventsLists[2].size());
		LOGGER.debug("Nb All : " + eventsLists[3].size());
		refreshTabs();
	}


	@SuppressWarnings("deprecation")
	private void refreshTabs() {
		for (int i = 0; i < tabs.length; i++) {
			table.get(i).removeAll();
			items(i);
		}
	}

	/**
	 * Sets the crit.
	 * 
	 * @param infCritp
	 *            the inf critp
	 * @param supCritp
	 *            the sup critp
	 * @param dateCrit
	 *            the date crit
	 * 
	 * @author Guillaume Thoreton
	 */
	private void setCrit(String infCritp, String supCritp, String dateCrit) {
		try {
			infCrit = Integer.parseInt(infCritp);
			MainPMScmd.getPrefs().put("gui.crit.inf", infCritp);
		} catch (NumberFormatException e) {
			ErrorDialog inst = new ErrorDialog(this.getShell(), SWT.NULL, e.toString(), null);
			inst.open();
			sellCriteriaWeigthtext.setText(this.infCrit.toString());
		}
		try {
			supCrit = Integer.parseInt(supCritp);
			MainPMScmd.getPrefs().put("gui.crit.sup", supCritp);
		} catch (NumberFormatException e) {
			ErrorDialog inst = new ErrorDialog(this.getShell(), SWT.NULL, e.toString(), null);
			inst.open();
			buyCriteriaWeigthtext.setText(this.supCrit.toString());
		}
		try {
			//FIXME set backwardDaySpan from UI
			analyseStartDate.setTime(new SimpleDateFormat("dd MMM yyyy").parse(dateCrit).getTime());
			MainPMScmd.getPrefs().put("gui.crit.date", new SimpleDateFormat("yyyy/MM/dd").format(analyseStartDate));
		} catch (ParseException e) {
			ErrorDialog inst = new ErrorDialog(this.getShell(), SWT.NULL, e.getMessage(), null);
			inst.open();
			dateCriteriatext.setText(new SimpleDateFormat("dd MMM yyyy").format(this.analyseStartDate));
		} finally {
			//eventModelImage.setStartAnalyseDate(analyseStartDate);
		}
		try {
			this.sortOnWeigthCriteriasActions();
		} catch (Exception e1) {
			ErrorDialog dialog = new ErrorDialog(this.getShell(), SWT.NULL, "Invalide date : "+dateCriteriatext, null);
			dialog.open();
		}
		reloadTabs();
		try {
			MainPMScmd.getPrefs().flush();
		} catch (BackingStoreException e) {
			LOGGER.warn("Can't save preferences", e);
		}
	}

	/**
	 * Sets the action.
	 * 
	 * @param action
	 *            the new action
	 */
	private void setAction(EventsActionSort action) {
		this.action = action;
	}

	/**
	 * Sets the filter.
	 * 
	 * @param filter
	 *            the new filter
	 */
	private void setFilter(EventsActionFilter filter) {
			this.filter = filter;
	}

	/**
	 * Sort column.
	 * 
	 * @param tabNum
	 *            the tab num
	 * @param colNum
	 *            the col num
	 * 
	 * @author Guillaume Thoreton
	 */
	private void sortColumn(int tabNum, int colNum) {
		try {
			EventsComposite.this.setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
			String sortAttAcc = sortableCollumnsAttAccesors[colNum];
			if (sortAttAcc != null) {
				List<SymbolEvents> sortedCol = new ArrayList<SymbolEvents>();
				sortedCol.addAll(eventsLists[tabNum]);
				if (colNum == 2) {
					SymbolEvents.sortList(sortedCol, new SymbolEventComparator(sortAttAcc, this.action.getPonderationRule()));
				} else {
					SymbolEvents.sortList(sortedCol, new SymbolEventComparator(sortAttAcc));
				}
				eventsLists[tabNum] = sortedCol;
				//refresh
				table.get(tabNum).removeAll();
				items(tabNum);
			}
		} finally {
			EventsComposite.this.setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}
	}

	/**
	 * Refresh action.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void initRefreshAction() {
		
		EventsComposite.this.setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
		refreshMonitored.setEnabled(false);
		refreshSelectedMarkets.setEnabled(false);
		refreshPortfolios.setEnabled(false);
		logComposite.initRefresh(this);
	}

	/**
	 * End refresh action.
	 * 
	 * @author Guillaume Thoreton
	 */
	@Override
	public void endRefreshAction(List<Exception> exceptions) {
		try {
			logComposite.endJob(exceptions);
			enableRefreshButtons();
		} finally {
			EventsComposite.this.setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
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
	public void refreshView(List<Exception> exceptions) {
		//Refresh view
		sortOnWeigthCriteriasActions();
		reloadTabs();
		
		if (isVisible()) {
			for (Exception exception : exceptions) {
				if (exception instanceof EventRefreshException) {
					ErrorDialog dialog = new ErrorDialog(getShell(), SWT.NONE, 
							"Couldn't refresh events\n" +
							"Check that date bounds are not out of range.", 
							exceptions.toString());
					exceptions.clear();
					dialog.open();
					break;
				}
			}
		}
	}

	public Date getAnalysisStartDate() {
		return analyseStartDate;
	}
}
