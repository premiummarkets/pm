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
import java.util.Date;
import java.util.List;
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
import org.eclipse.swt.layout.RowLayout;
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
import com.finance.pms.RefreshableView;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.EventModel;
import com.finance.pms.datasources.EventRefreshController;
import com.finance.pms.datasources.RefreshAllEventStrategyEngine;
import com.finance.pms.datasources.RefreshMonitoredStrategyEngine;
import com.finance.pms.datasources.RefreshPortfolioStrategyEngine;
import com.finance.pms.datasources.web.Indice;
import com.finance.pms.datasources.web.Providers;
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
import com.finance.pms.threads.ConfigThreadLocal;

// TODO: Auto-generated Javadoc
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
	private Button portfolioOtherbutton;
	/** The category filterbutton. */
	private Button indicesFilterbutton;
	/** The clear filterbutton. */
	private Button clearFilterbutton;
	/** The refresh monitored. */
	private Button refreshMonitored;
	private Button refreshPortfolios;
	/** The refresh all. */
	private Button refreshAll;
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
	private EventsActionSort action = new EventsActionSort(EventsActionSortEnum.LATESTNOALERTSEVENTSTXT,new LatestEventsIndicatorOnlyPonderationRule());
	/** The filter. */
	private EventsActionFilter filter = EventsActionFilter.NONE;
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
	
	/**
	 * Instantiates a new events composite.
	 * 
	 * @param parent
	 *            the parent
	 * @param style
	 *            the style
	 * 
	 * @author Guillaume Thoreton
	 */
	public EventsComposite(Composite parent, int style, LogComposite logComposite, Date analysisStartDate) {
		super(parent, style);
		
		this.analyseStartDate = analysisStartDate;
		this.logComposite = logComposite;
		allStocksEventModel = EventModel.getInstance(new RefreshAllEventStrategyEngine());
		monitoredStocksEventModel = EventModel.getInstance(new RefreshMonitoredStrategyEngine());
		portfolioStocksEventModel = EventModel.getInstance(new RefreshPortfolioStrategyEngine(), logComposite);

		initGUI();
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void main(String[] args) {
		SpringContext ctx = new SpringContext();
		ctx.setDataSource(args[0]);
		ctx.setMasSource(args[0], "false");
		ctx.loadBeans(new String[] { "/connexions.xml", "/swtclients.xml", "talibanalysisservices.xml","masanalysisservices.xml" });
		ctx.refresh();
		showGUI();
	}

	/**
	 * Show gui.
	 * 
	 * @author Guillaume Thoreton
	 */
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

		sortOnWeigthCriteriasActions();
		reloadTabs();
		//no filter by default
		setFilter(EventsActionFilter.NONE);
		filterActions();
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
				GridLayout group1Layout2 = new GridLayout();
				group1Layout2.numColumns = 2;
				criteriasgroup.setLayout(group1Layout2);
				GridData criteriasgroupLData = new GridData();
				criteriasgroupLData.horizontalAlignment = GridData.FILL_VERTICAL;
				criteriasgroupLData.widthHint = 250;
				criteriasgroup.setLayoutData(criteriasgroupLData);
				criteriasgroup.setText("Trend sorting criteria :");
				criteriasgroup.setFont(MainGui.DEFAULTFONT);
				criteriasgroup.setBackground(new Color(getDisplay(), 239, 203, 152));
				{
					buyWeigthCritLabel = new Label(criteriasgroup, SWT.PUSH | SWT.CENTER);
					buyWeigthCritLabel.setText("Buy weigth is >= ");
					buyWeigthCritLabel.setFont(MainGui.DEFAULTFONT);
				}
				{
					buyCriteriaWeigthtext = new Text(criteriasgroup, SWT.NONE);
					GridData buyCriteriaWeigthtextLData = new GridData();
					buyCriteriaWeigthtextLData.heightHint = 17;
					buyCriteriaWeigthtextLData.horizontalAlignment = GridData.FILL;
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
					sellCriteriaWeigthLabel.setText("Sell weigth is <= ");
					sellCriteriaWeigthLabel.setFont(MainGui.DEFAULTFONT);
				}
				{
					sellCriteriaWeigthtext = new Text(criteriasgroup, SWT.NONE);
					GridData sellCriteriaWeigthtextLData = new GridData();
					sellCriteriaWeigthtextLData.horizontalAlignment = GridData.FILL;
					sellCriteriaWeigthtextLData.heightHint = 17;
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
					dateCriteriaLabel.setText("Start on - dd MMM yyyy");
					dateCriteriaLabel.setFont(MainGui.DEFAULTFONT);
				}
				{
					dateCriteriatext = new Text(criteriasgroup, SWT.NONE);
					dateCriteriatext.setText(new SimpleDateFormat("dd MMM yyyy").format(analyseStartDate));
					dateCriteriatext.setFont(MainGui.CONTENTFONT);
					GridData sellCriteriaDatetextLData = new GridData();
					sellCriteriaDatetextLData.horizontalAlignment = GridData.FILL;
					sellCriteriaDatetextLData.heightHint = 17;
					dateCriteriatext.setLayoutData(sellCriteriaDatetextLData);
					dateCriteriatext.addListener(SWT.DefaultSelection, new Listener() {
						public void handleEvent(Event e) {
							setCrit(sellCriteriaWeigthtext.getText(), buyCriteriaWeigthtext.getText(), dateCriteriatext.getText());
						}
					});
				}
				{
					applyCritButton = new Button(criteriasgroup, SWT.PUSH | SWT.CENTER);
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
				GridLayout group1Layout1 = new GridLayout();
				group1Layout1.numColumns = 1;
				sortButtonGroup.setLayout(group1Layout1);
				GridData group1LData1 = new GridData();
				group1LData1.horizontalAlignment = GridData.FILL;
				sortButtonGroup.setLayoutData(group1LData1);
				sortButtonGroup.setText("Event weight filters :");
				sortButtonGroup.setFont(MainGui.DEFAULTFONT);
				sortButtonGroup.setBackground(new Color(getDisplay(), 239, 203, 152));
				{
					final Combo combo = new Combo(sortButtonGroup, SWT.NONE);
					GridData group1LData = new GridData(GridData.FILL_HORIZONTAL);
					combo.setLayoutData(group1LData);
					combo.add(EventsActionSortEnum.DEFAULTWEIGTHTXT.getText());
					combo.add(EventsActionSortEnum.LATESTNOALERTSEVENTSTXT.getText());
					for (int j = 0, n = EventDefinition.values().length; j < n; j++) {
						if (EventDefinition.values()[j].getEventDefId() >= 100)
							combo.add(EventDefinition.values()[j].getEventDef());
					}
					combo.setText(action.getActionSortEnum().getText());
					combo.setFont(MainGui.DEFAULTFONT);
					combo.addSelectionListener(new SelectionListener() {
						
						public void widgetDefaultSelected(SelectionEvent arg0) {
							// TODO Auto-generated method stub
						}

						public void widgetSelected(SelectionEvent arg0) {
							if (combo.getText().equals(EventsActionSortEnum.DEFAULTWEIGTHTXT.getText())) {
								Integer sellEventTriggerThreshold = ((EventSignalConfig)ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getSellEventTriggerThreshold(); 
								Integer buyEventTriggerThreshold = ((EventSignalConfig)ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getBuyEventTriggerThreshold();
								setAction(new EventsActionSort(EventsActionSortEnum.DEFAULTWEIGTHTXT, new DefaultPonderationRule(sellEventTriggerThreshold, buyEventTriggerThreshold)));
							} else if (combo.getText().equals(EventsActionSortEnum.LATESTEVENTSTXT.getText())) {
								//FIXME
								throw new NotImplementedException();
								//setAction(new EventsActionSort(EventsActionSortEnum.LATESTEVENTSTXT, new LatestEventsPonderationRule()));
							} else if (combo.getText().equals(EventsActionSortEnum.LATESTNOALERTSEVENTSTXT.getText())) {
								setAction(new EventsActionSort(EventsActionSortEnum.LATESTNOALERTSEVENTSTXT, new LatestEventsIndicatorOnlyPonderationRule()));
							} else {//if (combo.getText().equals(EventsActionSortEnum.TALIBWEIGHTTXT.getText())) {
								EventDefinition eventDefinition = EventDefinition.valueOfEventDef(combo.getText());
								setAction(new EventsActionSort(EventsActionSortEnum.TALIBWEIGHTTXT, new IndicatorPonderationRule(eventDefinition.getEventDefId())));
							}
							sortOnWeigthCriteriasActions();
							reloadTabs();
						}
					});
				}
			}
			{
				filterButtonGroup = new Group(this, SWT.NONE);
				GridLayout group1Layout1 = new GridLayout();
				group1Layout1.numColumns = 1;
				filterButtonGroup.setLayout(group1Layout1);
				GridData group1LData1 = new GridData();
				group1LData1.horizontalAlignment = GridData.FILL;
				filterButtonGroup.setLayoutData(group1LData1);
				filterButtonGroup.setText("Results filtering out :");
				filterButtonGroup.setFont(MainGui.DEFAULTFONT);
				filterButtonGroup.setBackground(new Color(getDisplay(), 239, 203, 152));
				{
					moniteredFilterbutton = new Button(filterButtonGroup, SWT.PUSH | SWT.CENTER);
					moniteredFilterbutton.setText(EventsActionFilter.FILTERMONITOREDTXT.getText());
					moniteredFilterbutton.setFont(MainGui.DEFAULTFONT);
					GridData group1LData = new GridData(GridData.FILL_HORIZONTAL);
					moniteredFilterbutton.setLayoutData(group1LData);
					moniteredFilterbutton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent evt) {
							setFilter(EventsActionFilter.FILTERMONITOREDTXT);
							filterActions();
							reloadTabs();
							moniteredFilterbutton.setEnabled(false);
						}
					});
				}
				{
					indicesFilterbutton = new Button(filterButtonGroup, SWT.PUSH | SWT.CENTER);
					GridData group1LData = new GridData(GridData.FILL_HORIZONTAL);
					indicesFilterbutton.setLayoutData(group1LData);
					indicesFilterbutton.setText(EventsActionFilter.FILTERINDICESTXT.getText());
					indicesFilterbutton.setFont(MainGui.DEFAULTFONT);
					indicesFilterbutton.addListener(SWT.MouseHover, new Listener() {
						
						public void handleEvent(Event evt) {
							String listProvCmd = MainPMScmd.getPrefs().get("quotes.listprovider", "euronext");
							Providers provider =  Providers.getInstance(listProvCmd);
							String current = "All stocks from \n"+listProvCmd+" "+Indice.formatName(provider.getIndices());
							if (indicesFilterbutton.getToolTipText() == null || !indicesFilterbutton.getToolTipText().equals(current)) {
								indicesFilterbutton.setToolTipText(current);
							}
						}
					});
					indicesFilterbutton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent evt) {
							setFilter(EventsActionFilter.FILTERINDICESTXT);
							filterActions();
							reloadTabs();
							indicesFilterbutton.setEnabled(false);
						}
					});
				}
				{
					portfolioFilterbutton = new Button(filterButtonGroup, SWT.PUSH | SWT.CENTER);
					portfolioFilterbutton.setText(EventsActionFilter.FILTERPORTFOLIOTXT.getText());
					portfolioFilterbutton.setFont(MainGui.DEFAULTFONT);
					GridData group1LData = new GridData(GridData.FILL_HORIZONTAL);
					portfolioFilterbutton.setLayoutData(group1LData);
					portfolioFilterbutton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent evt) {
							setFilter(EventsActionFilter.FILTERPORTFOLIOTXT);
							filterActions();
							reloadTabs();
							portfolioFilterbutton.setEnabled(false);
						}
					});
				}
				{
					portfolioOtherbutton = new Button(filterButtonGroup, SWT.PUSH | SWT.CENTER);
					portfolioOtherbutton.setText(EventsActionFilter.FILTEROTHERTXT.getText());
					portfolioOtherbutton.setFont(MainGui.DEFAULTFONT);
					GridData group1LData = new GridData(GridData.FILL_HORIZONTAL);
					portfolioOtherbutton.setLayoutData(group1LData);
					portfolioOtherbutton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent evt) {
							setFilter(EventsActionFilter.FILTEROTHERTXT);
							filterActions();
							reloadTabs();
							portfolioOtherbutton.setEnabled(false);
						}
					});
				}
				{
					clearFilterbutton = new Button(filterButtonGroup, SWT.PUSH | SWT.CENTER);
					clearFilterbutton.setText(EventsActionFilter.NONE.getText());
					clearFilterbutton.setFont(MainGui.DEFAULTFONT);
					GridData group1LData = new GridData(GridData.FILL_HORIZONTAL);
					clearFilterbutton.setLayoutData(group1LData);
					clearFilterbutton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent evt) {
							setFilter(EventsActionFilter.NONE);
							sortOnWeigthCriteriasActions();
							reloadTabs();
							indicesFilterbutton.setEnabled(true);
							moniteredFilterbutton.setEnabled(true);
							portfolioFilterbutton.setEnabled(true);
							portfolioOtherbutton.setEnabled(true);
						}
					});
				}
			}
			{
				refreshButtonGroup = new Group(this, SWT.NONE);
				GridLayout group1Layout1 = new GridLayout();
				group1Layout1.numColumns = 2;
				refreshButtonGroup.setLayout(group1Layout1);
				GridData group1LData1 = new GridData();
				group1LData1.horizontalAlignment = GridData.FILL;
				refreshButtonGroup.setLayoutData(group1LData1);
				refreshButtonGroup.setText("Compute Events :");
				refreshButtonGroup.setFont(MainGui.DEFAULTFONT);
				refreshButtonGroup.setBackground(new Color(getDisplay(), 239, 203, 152));
				{
					refreshMonitored = new Button(refreshButtonGroup, SWT.PUSH | SWT.CENTER);
					refreshMonitored.setText("Monitored stocks only");
					refreshMonitored.setFont(MainGui.DEFAULTFONT);
					GridData group1LData = new GridData(GridData.FILL_HORIZONTAL);
					group1LData.horizontalSpan = 2;
					refreshMonitored.setLayoutData(group1LData);
					refreshMonitored.addMouseListener(new EventRefreshController(monitoredStocksEventModel, this) {
						@Override
						public void mouseDown(MouseEvent evt) {
							this.updateEventRefreshModelState(quotationListBox.getSelection(), quotationBox.getSelection(), true,false);
							initRefreshAction();
							super.mouseDown(evt);
						}
					});
				}
				{
					refreshAll = new Button(refreshButtonGroup, SWT.PUSH | SWT.CENTER);
					GridData group1LData = new GridData(GridData.FILL_HORIZONTAL);
					group1LData.horizontalSpan = 2;
					refreshAll.setLayoutData(group1LData);
					refreshAll.setText("All current market");
					refreshAll.setFont(MainGui.DEFAULTFONT);
					refreshAll.addListener(SWT.MouseHover, new Listener() {
						
						public void handleEvent(Event evt) {
							String listProvCmd = MainPMScmd.getPrefs().get("quotes.listprovider", "euronext");
							Providers provider =  Providers.getInstance(listProvCmd);
							String current = "All stocks from \n"+listProvCmd+" "+Indice.formatName(provider.getIndices());
							if (refreshAll.getToolTipText() == null || !refreshAll.getToolTipText().equals(current)) {
								refreshAll.setToolTipText(current);
							}
						}
					});
					refreshAll.addMouseListener(new EventRefreshController(allStocksEventModel, this) {
						@Override
						public void mouseDown(MouseEvent evt) {
							this.updateEventRefreshModelState(quotationListBox.getSelection(), quotationBox.getSelection(), true,false);
							initRefreshAction();
							super.mouseDown(evt);
						}
					});
				}
				{
					refreshPortfolios = new Button(refreshButtonGroup, SWT.PUSH | SWT.CENTER);
					refreshPortfolios.setText("All portfolios");
					refreshPortfolios.setFont(MainGui.DEFAULTFONT);
					GridData group1LData = new GridData(GridData.FILL_HORIZONTAL);
					group1LData.horizontalSpan = 2;
					refreshPortfolios.setLayoutData(group1LData);
					refreshPortfolios.addMouseListener(new EventRefreshController(portfolioStocksEventModel, this) {
						@Override
						public void mouseDown(MouseEvent evt) {
							this.updateEventRefreshModelState(quotationListBox.getSelection(), quotationBox.getSelection(), true,false);
							initRefreshAction();
							super.mouseDown(evt);
						}
					});
				}
				{
					Button clear = new Button(refreshButtonGroup, SWT.PUSH | SWT.CENTER);
					GridData group1LData = new GridData(GridData.FILL_HORIZONTAL);
					group1LData.horizontalSpan = 2;
					clear.setLayoutData(group1LData);
					clear.setText("Clear All");
					clear.setFont(MainGui.DEFAULTFONT);
					clear.addSelectionListener(new SelectionListener() {
						
						public void widgetSelected(SelectionEvent e) {
							selection();
						}
				
						public void widgetDefaultSelected(SelectionEvent e) {
							selection();
						}

						private void selection() {
							EventsResources.getInstance().cleanEventsForAnalysisName(IndicatorCalculationServiceMain.UI_ANALYSIS, analyseStartDate, EventSignalConfig.getNewDate(), true);
							sortOnWeigthCriteriasActions();
							reloadTabs();
							enableRefreshButtons();
						}
					});
				}
				{
					Group refreshQuotesButtonGroup = new Group(refreshButtonGroup, SWT.NONE);
					RowLayout rl = new RowLayout(SWT.VERTICAL);
					refreshQuotesButtonGroup.setLayout(rl);
					{
						quotationListBox = new Button(refreshQuotesButtonGroup, SWT.CHECK);
						quotationListBox.setText("Update stocks list");
						quotationListBox.setFont(MainGui.DEFAULTFONT);
						quotationListBox.setGrayed(true);
						quotationListBox.setSelection(false);
						quotationBox = new Button(refreshQuotesButtonGroup, SWT.CHECK);
						quotationBox.setText("Get latest quotations");
						quotationBox.setFont(MainGui.DEFAULTFONT);
						quotationBox.setGrayed(true);
						quotationBox.setSelection(true);
					}
				}
			}
			this.layout();
		} catch (Exception e) {
			LOGGER.error("", e);
		}
	}

	protected Object buildViewMonitoredStockList() {
		// TODO Auto-generated method stub
		return null;
	}

	protected Object buildViewStockList() {
		// TODO Auto-generated method stub
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
							// TODO Auto-generated method stub
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
			getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
			switch (filter) {
			case FILTERMONITOREDTXT:
				EventsResources.getInstance().filterNonMonitoredEvents();
				break;
			case FILTERINDICESTXT:
				EventsResources.getInstance().filterCurrentMarketEvents();
				break;
			case FILTERPORTFOLIOTXT:
				EventsResources.getInstance().filterNonPortofolioEvents();
				break;
			case FILTEROTHERTXT:
				EventsResources.getInstance().filterOtherEvents();
				break;
			case NONE:
				LOGGER.debug("Filters clearded. Nothing to do.");
				break;
			}
		} finally {
			getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}
	}

	/**
	 * Sort on weigth criterias actions.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void sortOnWeigthCriteriasActions() {
		try {
			getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
			EventsResources.getInstance().loadEventsByCriteriaAndDate(analyseStartDate, infCrit, supCrit, action.getPonderationRule(), IndicatorCalculationServiceMain.UI_ANALYSIS);
		} catch (InvalidAlgorithmParameterException e) {
			ErrorDialog inst = new ErrorDialog(this.getShell(), SWT.NULL, e.toString(), null);
			inst.open();
		} finally {
			getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}
		filterActions();
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

	/**
	 * Refresh tabs.
	 * 
	 * @author Guillaume Thoreton
	 */
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
		this.sortOnWeigthCriteriasActions();
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
//		if (!this.filter.equals(EventsActionFilter.NONE) && !filter.equals(EventsActionFilter.NONE))
//			this.filter = EventsActionFilter.BOTH;
//		else
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
			getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
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
			getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}
	}

	/**
	 * Refresh action.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void initRefreshAction() {
		getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
		refreshMonitored.setEnabled(false);
		refreshAll.setEnabled(false);
		refreshPortfolios.setEnabled(false);
		logComposite.initRefresh(this);
	}

	/**
	 * End refresh action.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void endRefreshAction() {
		try {
			logComposite.endJob();
			enableRefreshButtons();
		} finally {
			getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}
	}

	private void enableRefreshButtons() {
		refreshMonitored.setEnabled(true);
		refreshAll.setEnabled(true);
		refreshPortfolios.setEnabled(true);
	}

	/**
	 * Refresh view.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void refreshView() {
		//Refresh view
		sortOnWeigthCriteriasActions();
		reloadTabs();
	}

	public Date getAnalysisStartDate() {
		return analyseStartDate;
	}
}
