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
package com.finance.pms.portfolio.gui.charts;

import java.awt.Frame;
import java.awt.event.MouseMotionListener;
import java.security.InvalidAlgorithmParameterException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.jfree.chart.ChartPanel;

import com.finance.pms.ActionDialog;
import com.finance.pms.ActionDialogAction;
import com.finance.pms.CursorFactory;
import com.finance.pms.ErrorDialog;
import com.finance.pms.IndicatorCalculationServiceMain;
import com.finance.pms.LogComposite;
import com.finance.pms.MainGui;
import com.finance.pms.MainPMScmd;
import com.finance.pms.PopupMenu;
import com.finance.pms.RefreshableView;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.EventModel;
import com.finance.pms.datasources.EventRefreshController;
import com.finance.pms.datasources.EventRefreshException;
import com.finance.pms.datasources.RefreshChartHightlited;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.StripedCloseAbsoluteRelative;
import com.finance.pms.datasources.db.StripedCloseDayToDay;
import com.finance.pms.datasources.db.StripedCloseFunction;
import com.finance.pms.datasources.db.StripedCloseIndexRelative;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.DataSetBarDescr;
import com.finance.pms.portfolio.InfoObject;
import com.finance.pms.portfolio.gui.ActionDialogForm;
import com.finance.pms.portfolio.gui.PortfolioComposite;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;

/**
 * The Class ChartsComposite.
 * 
 * @author Guillaume Thoreton
 */
public class ChartsComposite extends SashForm implements RefreshableView {
	

	class TransfoInfo implements InfoObject {
		String info;
		ActionDialogAction action;
		
		public TransfoInfo(String info, ActionDialogAction action) {
			super();
			this.info = info;
			this.action = action;
		}

		@Override
		public String info() {
			return info;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((info == null) ? 0 : info.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TransfoInfo other = (TransfoInfo) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (info == null) {
				if (other.info != null)
					return false;
			} else if (!info.equals(other.info))
				return false;
			return true;
		}

		private ChartsComposite getOuterType() {
			return ChartsComposite.this;
		}
		
		
	
	}


	protected static MyLogger LOGGER = MyLogger.getLogger(ChartsComposite.class);
	
	public static Date DEFAULT_START_DATE;
	static {
		try {
			DEFAULT_START_DATE = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1970");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	
	private PortfolioComposite portfolioComposite;
	private LogComposite logComposite;
	
	private StripedCloseFunction stripedCloseFunction;
	private Quotations refereeQuotations;
	private List<SlidingPortfolioShare> listShares;

	private Integer highligtedId;
	private EventModel<RefreshChartHightlited>  hightlitedEventModel;
	
	private ChartPanel mainChartPanel;
	private Composite mainChartComposite;
	private ChartMain mainChartWraper;
	private Set<EventDefinition> chartedEvtDefsTrends;
	private EventDefinition chartedEvtDef;

	public Group chartBoutonsGroup;
	
	private Slider sliderStartDate;
	private Label startDateLabel;
	private Slider sliderEndDate;
	private Label endDateLabel;
	
	private Date slidingStartDate;
	private Date slidingEndDate;
	private Date firstStartDate;
	private Date lastEndDate;

	private Boolean sliderSelection;
	
	private Boolean closeRequested = false;
	private Stock calculating;



	public ChartsComposite(Composite parent, int style, LogComposite logComposite) {
		super(parent, style);
		
		this.listShares = new ArrayList<SlidingPortfolioShare>();
		
		this.slidingEndDate = DateFactory.midnithDate(EventSignalConfig.getNewDate());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -1);
		this.slidingStartDate = calendar.getTime();
		
		this.stripedCloseFunction = new StripedCloseAbsoluteRelative(slidingStartDate,slidingEndDate);
		this.logComposite = logComposite;
		this.hightlitedEventModel = EventModel.getInstance(new RefreshChartHightlited(), logComposite);
		
		chartedEvtDefsTrends = new TreeSet<EventDefinition>();
		chartedEvtDef = EventDefinition.ZERO;
		
		sliderSelection=false;
		calculating=null;
		
		this.setToolTipText("Sash : Click on this border and drag to resize");
		super.setCursor(new Cursor(getDisplay(), SWT.CURSOR_CROSS));
		
		this.initGUI();
	}


	public void resetShareList(List<SlidingPortfolioShare> listShares, Boolean portfolioHasChanged) {
		
		this.listShares = listShares;
		updateCharts(listShares, portfolioHasChanged, false);
		
	}
	
	public void highLight(Integer idx, final Stock selectedShare, boolean recalculationGranted) {
		
		if (idx == null || selectedShare == null ) {
			return;
		}
		highligtedId = idx;
		hightlitedEventModel.setViewStateParams(selectedShare);
		
		if (chartedEvtDefsTrends.size() > 0 || !chartedEvtDef.equals(EventDefinition.ZERO)) {//Some thing has to be displayed

			if (calculating != null && calculating.equals(selectedShare)) {//This stock is already running a calculation

				ErrorDialog dialog = new ErrorDialog(getShell(), SWT.NONE, "Still calculating indicators for "+selectedShare.getFriendlyName()+".\nThanks for waiting.", null);
				dialog.open();

			} else {//We try and run

				try {
				
					Boolean needsUpdate = hightlitedEventModel.needsUpdate(selectedShare, slidingStartDate, slidingEndDate);
					
					if (needsUpdate && recalculationGranted) {
						
							eventsRecalculationAck(selectedShare);

					//No recalc needed
					} else {
						
						if (!chartedEvtDef.equals(EventDefinition.ZERO)) {
							updateChartIndicator(selectedShare, recalculationGranted);
						}

						if (chartedEvtDefsTrends.size() > 0 ) {
							updateBarChart(selectedShare, recalculationGranted);
						}

					}


				} catch (Exception e) {
					LOGGER.error(e,e);
				}
			}
			
		} else {
			if (chartedEvtDef != null && chartedEvtDef.equals(EventDefinition.ZERO)) mainChartWraper.resetIndicChart();
			if (chartedEvtDefsTrends.size() == 0) mainChartWraper.resetBarChart();
		}
		
		mainChartWraper.highLightSerie(highligtedId);
		
	}

	private SortedMap<DataSetBarDescr, SortedMap<Date, Double>> buildBarsData(Stock selectedShare, SymbolEvents eventsForStock) {
		
		
		SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barData = new TreeMap<DataSetBarDescr, SortedMap<Date,Double>>();
		double factor = 1d; 
		int serieIdx = chartedEvtDefsTrends.size()*2;

		for (EventDefinition eventDefinition : chartedEvtDefsTrends) {
			SortedMap<Date, Double> sellS = new TreeMap<Date,Double>();
			SortedMap<Date, Double> buyS = new TreeMap<Date,Double>();
			EventValue prevEventValue = null;

			for (EventValue eventValue : eventsForStock.getSortedDataResultList()) {

				if (eventValue.getEventDef().equals(eventDefinition) ) {
					if (prevEventValue != null) {
						Date currEvtDate = eventValue.getDate();
						fillBarChart(factor, sellS, buyS, prevEventValue, currEvtDate);
					}

					prevEventValue = eventValue;
				}

			} 

			//Filling up to the end only for non continuous events
			if (prevEventValue != null && !eventDefinition.getIsContinous()) {
				fillBarChart(factor, sellS, buyS, prevEventValue, slidingEndDate);
			}

			//int alpha = (int) (255*(1d-(factor-.10)));
			int alpha = 255/(int)Math.ceil((chartedEvtDefsTrends.size()/4d));
			DataSetBarDescr buyKey = new DataSetBarDescr(serieIdx, eventDefinition.name()+" buy",  new java.awt.Color(189,249,189, alpha), 10f, selectedShare.getFriendlyName());
			DataSetBarDescr sellKey = new DataSetBarDescr(serieIdx-1, eventDefinition.name()+" sell",  new java.awt.Color(246,173,173, alpha), 10f, selectedShare.getFriendlyName());
			if (!buyS.isEmpty()) {
				buyKey.setLabeled(true);
			} else if (!sellS.isEmpty()) {
				sellKey.setLabeled(true);
			}
			barData.put(buyKey, buyS);
			barData.put(sellKey, sellS);

			factor = factor - .9d/chartedEvtDefsTrends.size();
			serieIdx = serieIdx - 2;
		}
		
		return barData;
	}


	private void eventsRecalculationAck(final Stock selectedShare) {
	
		final RefreshableView thiz = this;
		String msg = "Events are not up to date for "+selectedShare.getName()+"\nand the selected time frame.";
		String click = "Click to calculate";
		ActionDialogAction action  = new ActionDialogAction() {

			@Override
			public void action(Button targetButton) {

				hightlitedEventModel.setViewStateParams(selectedShare);
				EventRefreshController eventRefreshController = new EventRefreshController(hightlitedEventModel, thiz) {
					@Override
					public void mouseDown(MouseEvent evt) {
						this.updateEventRefreshModelState(false, true, true,false, 0l);
						initRefreshAction();
						super.mouseDown(evt);
					}
				};
				eventRefreshController.mouseDown(null);
			}
			
		};
		ActionDialog dialog = new ActionDialog(getShell(), SWT.NULL, "Warning", msg, null, click, action);
		dialog.open();
		
	}
	
	private void updateBarChart(Stock selectedShare, boolean recalculationGranted) {
		
		SymbolEvents eventsForStock = EventsResources.getInstance().crudReadEventsForStock(selectedShare, this.slidingStartDate, this.slidingEndDate, true, chartedEvtDefsTrends, IndicatorCalculationServiceMain.UI_ANALYSIS);

		//No indic found
		if (eventsForStock == null || eventsForStock.getDataResultList().isEmpty()) {

			//No indic found despite recalc
			if (!recalculationGranted) {
				
				mainChartWraper.resetBarChart();
				ErrorDialog dialog = new ErrorDialog(getShell(),  SWT.NULL, "Warning", 
						"No events for this period and "+selectedShare.getFriendlyName()+".\n" +
								"This may also happen if the calculation has failed, is not granted or has not enough quotations for the period.\n" +
						"Check the date boundaries as well as the indicators displayed.");
				dialog.open();
			}

		//Thats all good, we display	
		} else {
			mainChartWraper.updateBarDataSet(buildBarsData(selectedShare, eventsForStock));
		}
	}
	

	private void updateChartIndicator(Stock selectedShare, boolean recalculationGranted) {
		
		SortedMap<Date, double[]> outputCache = hightlitedEventModel.getOutputCache(selectedShare, chartedEvtDef);
		
	
		if (outputCache == null) {
			
			//No indic found despite recalc
			if (!recalculationGranted) {
			
				mainChartWraper.resetIndicChart();
				ErrorDialog dialog = new ErrorDialog(getShell(),  SWT.NULL, "Warning", 
						"No indicator data for this period and "+selectedShare.getFriendlyName()+" and "+chartedEvtDef.getEventDef()+".\n" +
						"This may also happen if the calculation has failed, is not granted or has not enough quotations for the period.\n" +
						"Check the date boundaries as well as the indicators displayed.");
				dialog.open();
			}
		
		//Thats all good, we display	
		} else {
			Calendar instance = Calendar.getInstance();
			instance.setTime(this.slidingEndDate);
			QuotationsFactories.getFactory().incrementDate(instance, 1);
			Date endPlus1 = instance.getTime();
			SortedMap<Date, double[]> subMap = null;
			if (endPlus1.after(outputCache.lastKey())) {
				subMap = outputCache.tailMap(this.slidingStartDate);
			} else {
				subMap = outputCache.subMap(this.slidingStartDate, endPlus1);
			}
			mainChartWraper.updateIndicDataSet(chartedEvtDef, subMap);
		}
		
	}


	private void fillBarChart(double factor, SortedMap<Date, Double> sellS, SortedMap<Date, Double> buyS, EventValue prevEventValue, Date currEvtDate) {
		
		Calendar prevDateCal = Calendar.getInstance();
		prevDateCal.setTime(prevEventValue.getDate());
		
		double value = mainChartWraper.getMainYAxisMax()*factor;
		
		if ( prevEventValue.getEventType().equals(EventType.BULLISH)) {
			while (prevDateCal.getTime().before(currEvtDate)) {
				buyS.put(prevDateCal.getTime(), value);
				prevDateCal.add(Calendar.DAY_OF_YEAR, +1);
			}
		}
		else if (prevEventValue.getEventType().equals(EventType.BEARISH)) {
			while (prevDateCal.getTime().before(currEvtDate)) {
				sellS.put(prevDateCal.getTime(), value);
				prevDateCal.add(Calendar.DAY_OF_YEAR, +1);
			}
		}
	}


	private void updateCharts(List<SlidingPortfolioShare> listShares, Boolean portfolioHasChanged, boolean grantEventsUpdate) {
		
		
		//Quotation line
		stripedCloseFunction.updateStartDate(slidingStartDate);
		stripedCloseFunction.updateEndDate(slidingEndDate);
		mainChartWraper.updateLineDataSet(listShares, stripedCloseFunction);
		
		mainChartWraper.resetBarChart();
		mainChartWraper.resetIndicChart();
		
		//indic, bar and highligh
		if (!portfolioHasChanged) {
			
			Object[] viewStateParams = hightlitedEventModel.getViewStateParams();
			if (viewStateParams != null && viewStateParams.length == 1) {
				highLight(this.highligtedId, (Stock) viewStateParams[0], grantEventsUpdate);
			}
			
		} else {
			hightlitedEventModel.setViewStateParams();
			highligtedId = null;
			
		}

	}
	
	
	private void initGUI() {
		try {
			
			this.setOrientation(SWT.VERTICAL);
			
			this.setBackground(new Color(getDisplay(),239,120,103));
			Color innerBgColor = new Color(getDisplay(),241,200,195);
		
			this.addDisposeListener(new DisposeListener() {
				
				public void widgetDisposed(DisposeEvent evt) {
					rootShellClosed(evt);
				}
			});
			
			{
				mainChartComposite = new Composite(this, SWT.EMBEDDED | SWT.NO_BACKGROUND);
				Frame chartFrame = SWT_AWT.new_Frame(mainChartComposite);
				mainChartWraper = new ChartMain(ChartsComposite.DEFAULT_START_DATE, JFreeChartTimePeriod.DAY);
				mainChartPanel = new ChartPanel(mainChartWraper.initChart(stripedCloseFunction));
				chartFrame.add(mainChartPanel);
				chartFrame.setVisible(true);
				mainChartPanel.addMouseMotionListener(new MouseMotionListener() {
					
					@Override
					public void mouseMoved(java.awt.event.MouseEvent e) {
						synchronized (closeRequested) {
							if (!closeRequested) {
								Display.getDefault().asyncExec(new Runnable() {
									public void run() {
										if (!mainChartComposite.isFocusControl()) mainChartComposite.forceFocus();
									}
								});
							}
						}
					}
					
					@Override
					public void mouseDragged(java.awt.event.MouseEvent e) {
						synchronized (closeRequested) {
							if (!closeRequested) {
								Display.getDefault().asyncExec(new Runnable() {
									public void run() {
										if (!mainChartComposite.isFocusControl()) mainChartComposite.forceFocus();
									}
								});
							}
						}
					}
				});
			}
			{
				chartBoutonsGroup = new Group(this, SWT.NONE);
				chartBoutonsGroup.setBackground(innerBgColor);
				GridLayout portfolioBoutonsGroupLayout = new GridLayout();
				portfolioBoutonsGroupLayout.numColumns = 1;
				portfolioBoutonsGroupLayout.verticalSpacing=0;
				portfolioBoutonsGroupLayout.marginHeight=0;
				chartBoutonsGroup.setLayout(portfolioBoutonsGroupLayout);
				GridData portfolioInfosGroupData = new GridData(GridData.FILL_HORIZONTAL);
				chartBoutonsGroup.setLayoutData(portfolioInfosGroupData);
				
				chartBoutonsGroup.setText("Portfolios charting : ");
				chartBoutonsGroup.setFont(MainGui.DEFAULTFONT);
				chartBoutonsGroup.setBackground(innerBgColor);
				
				{
					final Group popusGroup = new Group(chartBoutonsGroup, SWT.NONE);
					GridData popusGroupData = new GridData(SWT.FILL, SWT.FILL,true, false);
					popusGroup.setLayoutData(popusGroupData);
					popusGroup.setBackground(innerBgColor);
					RowLayout popusGroupL = new RowLayout(SWT.HORIZONTAL);
					popusGroupL.justify = true;
					popusGroupL.fill=true;
					popusGroupL.wrap=false;
					popusGroupL.marginHeight=0;
					popusGroup.setLayout(popusGroupL);
					
					{
						final Button evtDefsChartingBut = new Button(popusGroup, SWT.PUSH);
						evtDefsChartingBut.setFont(MainGui.DEFAULTFONT);
						evtDefsChartingBut.setText("Chart Indicators ...");
						evtDefsChartingBut.setToolTipText("You must select a share in the portfolio to display its analysis.");
						final Set<EventDefinition> availEventDefs = new TreeSet<EventDefinition>(new Comparator<EventDefinition>() {

							@Override
							public int compare(EventDefinition o1, EventDefinition o2) {
								return o1.info().compareTo(o2.info());
							}
						});
						availEventDefs.addAll(EventDefinition.loadPassPrefsEventDefinitions());
						availEventDefs.add(EventDefinition.ZERO);
						evtDefsChartingBut.addSelectionListener(new SelectionListener() {

							@Override
							public void widgetSelected(SelectionEvent e) {		
								handleEvent(evtDefsChartingBut, availEventDefs);
							}

							private void handleEvent(final Button evtDefsButs, final Set<EventDefinition> availEventDefs) {
								Set<EventDefinition> chartedEvtDefTmpSet = new HashSet<EventDefinition>();
								chartedEvtDefTmpSet.add(chartedEvtDef);
								PopupMenu<EventDefinition> popupMenu = new PopupMenu<EventDefinition>(ChartsComposite.this, evtDefsButs, availEventDefs, chartedEvtDefTmpSet, SWT.RADIO);
								popupMenu.open();

								Object[] viewStateParams = hightlitedEventModel.getViewStateParams();
								chartedEvtDef = chartedEvtDefTmpSet.iterator().next();
								if (viewStateParams != null && viewStateParams.length == 1 || chartedEvtDefTmpSet.isEmpty()) {
									highLight(highligtedId, (Stock) viewStateParams[0], true);
								} else {
									if (chartedEvtDef != null && !chartedEvtDef.equals(EventDefinition.ZERO)) {
										ErrorDialog dialog = new ErrorDialog(getShell(), SWT.NONE, "You must select a share in the portfolio to display its analysis.", null);
										dialog.open();
									}
								}
								
							}

							@Override
							public void widgetDefaultSelected(SelectionEvent e) {
								handleEvent(evtDefsChartingBut, availEventDefs);
							}
						});

					}
					{
						final Button evtDefsTrendChartingBut = new Button(popusGroup, SWT.PUSH);
						evtDefsTrendChartingBut.setFont(MainGui.DEFAULTFONT);
						evtDefsTrendChartingBut.setText("Chart Indicators Trend ...");
						evtDefsTrendChartingBut.setToolTipText("You must select a share in the portfolio to display its analysis.");
						final Set<EventDefinition> availEventDefs = new TreeSet<EventDefinition>(new Comparator<EventDefinition>() {

							@Override
							public int compare(EventDefinition o1, EventDefinition o2) {
								return o1.info().compareTo(o2.info());
							}
							
						});
						
						final Set<EventDefinition> firstPassEvts = EventDefinition.loadPassPrefsEventDefinitions();
						availEventDefs.addAll(firstPassEvts);
						availEventDefs.add(EventDefinition.INFINITE);
						evtDefsTrendChartingBut.addSelectionListener(new SelectionListener() {

							@Override
							public void widgetSelected(SelectionEvent e) {		
								handleEvent(evtDefsTrendChartingBut, availEventDefs);
							}

							private void handleEvent(final Button evtDefs, final Set<EventDefinition> availEventDefs) {
								PopupMenu<EventDefinition> popupMenu = new PopupMenu<EventDefinition>(ChartsComposite.this, evtDefs, availEventDefs, chartedEvtDefsTrends, SWT.CHECK);
								popupMenu.open();
								if (chartedEvtDefsTrends != null && chartedEvtDefsTrends.contains(EventDefinition.INFINITE)) {
									chartedEvtDefsTrends.remove(EventDefinition.INFINITE);
									chartedEvtDefsTrends.addAll(firstPassEvts);
								}
								Object[] viewStateParams = hightlitedEventModel.getViewStateParams();
								if (viewStateParams != null && viewStateParams.length == 1) {
									highLight(highligtedId, (Stock) viewStateParams[0], true);
								} else {
									if (chartedEvtDefsTrends != null && !chartedEvtDefsTrends.isEmpty()) {
										ErrorDialog dialog = new ErrorDialog(getShell(), SWT.NONE, "You must select a share in the portfolio to display its analysis.", null);
										dialog.open();
									}
								}
							}

							@Override
							public void widgetDefaultSelected(SelectionEvent e) {
								handleEvent(evtDefsTrendChartingBut, availEventDefs);
							}
						});

					}

					{
						final Button closeFunctionBut = new Button(popusGroup, SWT.PUSH);
						closeFunctionBut.setFont(MainGui.DEFAULTFONT);
						closeFunctionBut.setText("Comparison mode ...");
						closeFunctionBut.setToolTipText("Current comparison mode is "+stripedCloseFunction.lineToolTip());
						final Set<TransfoInfo> transfos = new HashSet<TransfoInfo>(Arrays.asList(new TransfoInfo[]{
								new TransfoInfo("Change to buy price", new ActionDialogAction() {

									@Override
									public void action(Button targetButton) {
										stripedCloseFunction =  new StripedCloseInitPriceRelative();
										updateCharts(listShares, false, false);
									}
								}),
								new TransfoInfo("Change to period start", new ActionDialogAction() {

									@Override
									public void action(Button targetButton) {
										stripedCloseFunction =  new StripedCloseAbsoluteRelative(slidingStartDate, slidingEndDate);
										updateCharts(listShares, false, false);
									}
								}),
								new TransfoInfo("Change to previous day (log ROC)", new ActionDialogAction() {

									@Override
									public void action(Button targetButton) {

										final ActionDialogForm actionDialogForm = new ActionDialogForm(getShell(), "Ok", null, "Root at zero");
										ActionDialogAction actionDialogAction = new ActionDialogAction() {
											@Override
											public void action(Button targetButton) {
												actionDialogForm.name = Boolean.valueOf(((Button)actionDialogForm.control).getSelection()).toString();
												stripedCloseFunction =  new StripedCloseDayToDay(actionDialogForm.name.equals(Boolean.TRUE.toString()));
												updateCharts(listShares, false, false);
											}
										};
										Button zeroBut =  new Button(actionDialogForm.getParent(), SWT.CHECK | SWT.LEAD);
										zeroBut.setText("Root at zero");
										zeroBut.setFont(MainGui.DEFAULTFONT);
										zeroBut.setSelection(true);
										actionDialogForm.setControl(zeroBut);
										actionDialogForm.setAction(actionDialogAction);
										actionDialogForm.open();
										
									}
								}),
								new TransfoInfo("Change to Referee", new ActionDialogAction() {

									@Override
									public void action(Button targetButton) {

										String preferedRef = MainPMScmd.getPrefs().get("charts.referee", "Not Defined");
										final ActionDialogForm actionDialogForm = new ActionDialogForm(new Shell(), "Select a new referee ...", "Current referree : "+preferedRef, "Select a new referee ...");
										ActionDialogAction actionDialogAction = new ActionDialogAction() {
											@Override
											public void action(Button targetButton) {
												ChartsComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
												actionDialogForm.getParent().dispose();
												try {
													Stock selectedReferee = selectReferee();
													if (selectedReferee != null) {
														actionDialogForm.name = selectedReferee.getName();
														relativeIndexSetting(actionDialogForm.name);
														updateCharts(listShares, false, false);
													}
												} finally {
													ChartsComposite.this.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
												}
											}
										};
										actionDialogForm.setAction(actionDialogAction);
										actionDialogForm.open();
										if (actionDialogForm.name  == null && !preferedRef.equals("Not Defined")) {
											relativeIndexSetting(preferedRef);
											updateCharts(listShares, false, false);
										}

									}
								})}));

						closeFunctionBut.addSelectionListener(new SelectionListener() {

							@Override
							public void widgetSelected(SelectionEvent e) {		
								handleEvent(closeFunctionBut, transfos);
							}

							private void handleEvent(final Button closeFunctionBut, final Set<TransfoInfo> transfos) {
								
								Set<TransfoInfo> selectTransfo = new HashSet<ChartsComposite.TransfoInfo>();
								for (TransfoInfo transfoInfo : transfos) {
									if (transfoInfo.info().equalsIgnoreCase(stripedCloseFunction.lineToolTip())) {
										selectTransfo.add(transfoInfo);
									}
								}
								PopupMenu<TransfoInfo> popupMenu = new PopupMenu<TransfoInfo>(ChartsComposite.this, closeFunctionBut, transfos, selectTransfo, SWT.RADIO);
								popupMenu.open();
								for (TransfoInfo selctTransUnic : selectTransfo) {
									selctTransUnic.action.action(null);
								}
								closeFunctionBut.setText("Comparison mode ...");
								closeFunctionBut.setToolTipText("Current comparison mode is "+stripedCloseFunction.lineToolTip());
								popusGroup.setSize(popusGroup.getBounds().width, popusGroup.getBounds().height);
								popusGroup.layout();
								
//								Object[] viewStateParams = hightlitedEventModel.getViewStateParams();
//								if (viewStateParams != null && viewStateParams.length == 1) {
//									highLight(highligtedId, (Stock) viewStateParams[0], true);
//								}

							}

							@Override
							public void widgetDefaultSelected(SelectionEvent e) {
								handleEvent(closeFunctionBut, transfos);
							}
						});

					}

					{
						final Button hideStock = new Button(popusGroup, SWT.PUSH);
						hideStock.setFont(MainGui.DEFAULTFONT);
						hideStock.setText("Hide / Show stock ...");
						hideStock.addSelectionListener(new SelectionListener() {

							@Override
							public void widgetSelected(SelectionEvent e) {		
								hideShowShares(hideStock);
							}

							private void hideShowShares(final Button hideStock) {
								
								Set<SlidingPortfolioShare> displayedShares = new HashSet<SlidingPortfolioShare>();

								if (listShares.size() > 0) {
									for (SlidingPortfolioShare slidingPortfolioShare : listShares) {
										if (slidingPortfolioShare.getDisplayOnChart()) {
											displayedShares.add(slidingPortfolioShare);
										} 
									}

									PopupMenu<SlidingPortfolioShare> popupMenu =  new PopupMenu<SlidingPortfolioShare>(ChartsComposite.this, hideStock, new TreeSet<SlidingPortfolioShare>(listShares), displayedShares, SWT.CHECK);
									popupMenu.open();
									for (SlidingPortfolioShare slidingPortfolioShare : listShares) {
										if (displayedShares.contains(slidingPortfolioShare)) {
											slidingPortfolioShare.setDisplayOnChart(true);
										} else {
											slidingPortfolioShare.setDisplayOnChart(false);
										}
									}
									updateCharts(listShares, false, false);
								}

							}

							@Override
							public void widgetDefaultSelected(SelectionEvent e) {
								hideShowShares(hideStock);
							}
						});

					}
				}
				
				//Sliding
				{
					
					final Group slidingGroup = new Group(chartBoutonsGroup, SWT.NONE);
					GridData slidingGroupData = new GridData(SWT.FILL, SWT.FILL,true, false);
					slidingGroup.setLayoutData(slidingGroupData);
					slidingGroup.setBackground(innerBgColor);
					GridLayout slidingGroupL = new GridLayout();
					slidingGroupL.numColumns=5;
					slidingGroupL.marginHeight=0;
					slidingGroupL.verticalSpacing=0;
					slidingGroup.setLayout(slidingGroupL);
					///start
					{
						startDateLabel = new Label(slidingGroup, SWT.NONE);
						GridData startOneYearBackData = new GridData(SWT.BEGINNING, SWT.FILL,false, false);
						startOneYearBackData.horizontalSpan=2;
						startDateLabel.setLayoutData(startOneYearBackData);
						startDateLabel.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingStartDate)+"");
						startDateLabel.setBackground(innerBgColor);
						startDateLabel.setFont(MainGui.DEFAULTFONT);
					}
					///sliding sliders
					Composite slidingSliderGroup = new Composite(slidingGroup, SWT.NONE);
					slidingSliderGroup.setSize(1000, SWT.DEFAULT);
					GridData slidersGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
					slidersGridData.verticalSpan=2;
					slidingSliderGroup.setLayoutData(slidersGridData);
					slidingSliderGroup.setBackground(innerBgColor);
					slidingSliderGroup.setForeground(innerBgColor);
					FillLayout slidingSliderGroupL = new FillLayout(SWT.VERTICAL);
					slidingSliderGroup.setLayout(slidingSliderGroupL);
					{
						sliderStartDate = new Slider(slidingSliderGroup, SWT.HORIZONTAL);
						sliderStartDate.setThumb(1);
						sliderStartDate.setMaximum(100);
						sliderStartDate.addListener(SWT.MouseExit, new Listener() {

							public void handleEvent(Event arg0) {
								sliderChangesApply();
							}

						});
					}	
					{
						sliderEndDate = new Slider(slidingSliderGroup, SWT.HORIZONTAL);
						sliderEndDate.setThumb(1);
						sliderEndDate.setMinimum(0);
						sliderEndDate.setSelection(100);
						sliderEndDate.addListener(SWT.MouseExit, new Listener() {

							public void handleEvent(Event arg0) {
								sliderChangesApply();
							}

						});
					}
					//end
					{
						endDateLabel = new Label(slidingGroup, SWT.NONE);
						GridData endOneYearBackData = new GridData(SWT.END, SWT.FILL,false, false);
						endOneYearBackData.horizontalSpan=2;
						endDateLabel.setLayoutData(endOneYearBackData);
						endDateLabel.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingEndDate));
						endDateLabel.setBackground(innerBgColor);
						endDateLabel.setFont(MainGui.DEFAULTFONT);
					}
					//but start
					
					{
						Button startOneYearBack = new Button(slidingGroup, SWT.ARROW|SWT.LEFT);
						GridData startOneYearBackData = new GridData(SWT.END, SWT.FILL,false, true);
						startOneYearBack.setLayoutData(startOneYearBackData);
						startOneYearBack.setToolTipText("Move start date one year backward.");
						startOneYearBack.addSelectionListener(new SelectionListener() {

							@Override
							public void widgetSelected(SelectionEvent e) {
								handle();
							}

							private void handle() {
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(slidingStartDate);
								calendar.add(Calendar.YEAR, -1);
								firstStartDate = calendar.getTime();
								sliderStartDate.setSelection(0);
								startSliderUpdateConditional(sliderStartDate, startDateLabel, sliderEndDate, endDateLabel);
							}

							@Override
							public void widgetDefaultSelected(SelectionEvent e) {
								handle();

							}
						});
						startOneYearBack.addListener(SWT.MouseExit, new Listener() {
							
							@Override
							public void handleEvent(Event event) {
								sliderChangesApply();
							}
						});
					}
					
					{
						Button startOneYearForward = new Button(slidingGroup, SWT.ARROW|SWT.RIGHT);
						GridData startOneYearBackData = new GridData(SWT.BEGINNING, SWT.FILL,false, true);
						startOneYearForward.setLayoutData(startOneYearBackData);
						startOneYearForward.setToolTipText("Move start date one year forward.");
						startOneYearForward.addSelectionListener(new SelectionListener() {

							@Override
							public void widgetSelected(SelectionEvent e) {
								handle();
							}

							private void handle() {
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(slidingStartDate);
								calendar.add(Calendar.YEAR, +1);
								if (calendar.getTime().before(slidingEndDate)) {
									firstStartDate = calendar.getTime();
									sliderStartDate.setSelection(0);
									startSliderUpdateConditional(sliderStartDate, startDateLabel, sliderEndDate, endDateLabel);
								} else {
									ErrorDialog dialog = new ErrorDialog(getShell(), SWT.NONE, "To move the start date further forward, you will need to move the end date first.", null);
									dialog.open();
								}
							}

							@Override
							public void widgetDefaultSelected(SelectionEvent e) {
								handle();

							}
						});
						startOneYearForward.addListener(SWT.MouseExit, new Listener() {

							@Override
							public void handleEvent(Event event) {
								sliderChangesApply();
							}
						});
					}
					///but end
					
					{
						Button endOneYearBack = new Button(slidingGroup, SWT.ARROW|SWT.LEFT);
						GridData endOneYearBackData = new GridData(SWT.END, SWT.FILL,false, true);
						endOneYearBack.setLayoutData(endOneYearBackData);
						endOneYearBack.setToolTipText("Move end date one year backward.");
						endOneYearBack.addSelectionListener(new SelectionListener() {

							@Override
							public void widgetSelected(SelectionEvent e) {
								handle();
							}

							private void handle() {
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(slidingEndDate);
								calendar.add(Calendar.YEAR, -1);
								if (calendar.getTime().after(slidingStartDate)) {
									lastEndDate = calendar.getTime();
									sliderEndDate.setSelection(100);
									endSliderUpdateConditional(sliderEndDate, endDateLabel, sliderStartDate, startDateLabel);
								} else {
									ErrorDialog dialog = new ErrorDialog(getShell(), SWT.NONE, "To move the end date further backward, you will need to move the start date first.", null);
									dialog.open();
								}
							}

							@Override
							public void widgetDefaultSelected(SelectionEvent e) {
								handle();
							}
						});
						endOneYearBack.addListener(SWT.MouseExit, new Listener() {

							@Override
							public void handleEvent(Event event) {
								sliderChangesApply();
							}
						});
					}
					{
						Button endOneYearForward = new Button(slidingGroup, SWT.ARROW|SWT.RIGHT);
						GridData endOneYearBackData = new GridData(SWT.BEGINNING, SWT.FILL,false, true);
						endOneYearForward.setLayoutData(endOneYearBackData);
						endOneYearForward.setToolTipText("Move end date one year forward.");
						endOneYearForward.addSelectionListener(new SelectionListener() {

							@Override
							public void widgetSelected(SelectionEvent e) {
								handle();
							}

							private void handle() {
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(slidingEndDate);
								calendar.add(Calendar.YEAR, +1);
								Date newDate = EventSignalConfig.getNewDate();
								if (calendar.getTime().after(newDate)) {
									calendar.setTime(newDate);
								}
								lastEndDate = calendar.getTime();
								sliderEndDate.setSelection(100);
								endSliderUpdateConditional(sliderEndDate, endDateLabel, sliderStartDate, startDateLabel);
							}

							@Override
							public void widgetDefaultSelected(SelectionEvent e) {
								handle();

							}
						});
						endOneYearForward.addListener(SWT.MouseExit, new Listener() {

							@Override
							public void handleEvent(Event event) {
								sliderChangesApply();
							}
						});
					}
					
					
					sliderStartDate.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event event) {
							startSliderUpdateConditional(sliderStartDate, startDateLabel, sliderEndDate, endDateLabel);
						}
					});

					sliderEndDate.addListener(SWT.Selection, new Listener() {

						public void handleEvent(Event event) {
							endSliderUpdateConditional(sliderEndDate, endDateLabel, sliderStartDate, startDateLabel);
						}
					});
					
					slidingGroup.layout();
				}
				
			}

			this.layout();	
			this.pack();
			
			
		} catch (Exception e) {
			LOGGER.error("",e);
		}
	}
	
	private void startSliderUpdateConditional(final Slider sliderStartDate, final Label startDateLabel, Slider sliderEndDate,  Label endDateLabel) {
		
		synchronized (sliderSelection) {
			sliderSelection = true;
		}
		
		Integer startSliderValue = sliderStartDate.getSelection();
		
		if (sliderEndDate.getSelection() - sliderStartDate.getSelection() <= 10) {
			if (sliderEndDate.getSelection() <= 90) {
				int endValue = startSliderValue+10;
				sliderEndDate.setSelection(endValue);
				endSliderUpdate(sliderEndDate, endDateLabel, endValue);
			} else {
				sliderEndDate.setSelection(99);
				endSliderUpdate(sliderEndDate, endDateLabel, 99);
				startSliderValue=90;
				sliderStartDate.setSelection(90);
			}

		}
		
    	startSliderUpdate(sliderStartDate, startDateLabel, startSliderValue);
	}


	private void startSliderUpdate(final Slider sliderStartDate, final Label startDateLabel, Integer sliderValue) {
		
		Date maxDate= maxDate();
    	Date minDate= minimumDate();
    	Integer maxSlider= 100;
    	Integer minSlider= 0;
    	Integer perCentValue = sliderValue*100/(maxSlider - minSlider - sliderStartDate.getThumb());
    
    	Long diffDateInDays = (maxDate.getTime() - minDate.getTime())/(1000*3600*24);
    	Long nbDaySinceMin  = perCentValue * diffDateInDays /100;
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(minDate);
    	calendar.add(Calendar.DAY_OF_YEAR, nbDaySinceMin.intValue());
    	slidingStartDate = calendar.getTime();
    	
    	startDateLabel.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingStartDate));
    	startDateLabel.setFont(MainGui.DEFAULTFONT);
	}
	

	private void endSliderUpdateConditional( Slider sliderEndDate,  Label endDateLabel,  Slider sliderStartDate, Label startDateLabel) {
		
		synchronized (sliderSelection) {
			sliderSelection = true;
		}
	
		
		Integer sliderValue = sliderEndDate.getSelection();
    	
		if (sliderEndDate.getSelection() - sliderStartDate.getSelection() <= 10) {
			if (sliderStartDate.getSelection() >= 10) {
				int startValue = sliderValue-10;
				sliderStartDate.setSelection(startValue);
				startSliderUpdate(sliderStartDate, startDateLabel, startValue);
			} else {
				sliderStartDate.setSelection(0);
				startSliderUpdate(sliderStartDate, startDateLabel, 0);
				sliderValue = 10;
				sliderEndDate.setSelection(10);
			}
    	}
    	
    	endSliderUpdate(sliderEndDate, endDateLabel, sliderValue);
	}


	private void endSliderUpdate(Slider sliderEndDate, Label endDateLabel, Integer sliderValue) {
		
		Date maxDate= maxDate();
    	Date minDate= minimumDate();
    	Integer maxSlider= 100;
    	Integer minSlider= 0;
    	Integer perCentValue = sliderValue*100/ (maxSlider - minSlider - sliderEndDate.getThumb());
    
    	Long diffDate = (maxDate.getTime() - minDate.getTime())/(1000*3600*24);
    	Long nbDaySinceMin  = perCentValue * diffDate /100;
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(minDate);
    	calendar.add(Calendar.DAY_OF_YEAR, nbDaySinceMin.intValue());
    	slidingEndDate = calendar.getTime();
    	
    	endDateLabel.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingEndDate));
    	endDateLabel.setFont(MainGui.DEFAULTFONT);
	}
	
	protected void rootShellClosed(DisposeEvent evt) {
		
		synchronized (closeRequested) {
			closeRequested = true;
			SWT_AWT.getFrame(mainChartComposite).dispose();	
		}
		
	}


	private Date minimumDate() {
    	if (firstStartDate == null) {
    		Calendar  calendar  = Calendar.getInstance();
        	calendar.add(Calendar.YEAR, -1);
    		firstStartDate = DateFactory.midnithDate(calendar.getTime());
    	}
    	return firstStartDate;
	}
	
	private Date maxDate() {
    	if (lastEndDate == null) {
    		lastEndDate = DateFactory.midnithDate(EventSignalConfig.getNewDate());
    	}
    	return lastEndDate;
	}
	

	private void loadRefereeQuotations(Stock stock) throws InvalidAlgorithmParameterException {
		try {
			if (null == stock) throw new InvalidAlgorithmParameterException("Referee can't be null");
			refereeQuotations  = QuotationsFactories.getFactory().getQuotationsInstance(stock,ChartsComposite.DEFAULT_START_DATE, EventSignalConfig.getNewDate(),true,stock.getMarketValuation().getCurrency(),0,0);
			stripedCloseFunction =  new StripedCloseIndexRelative(refereeQuotations, slidingStartDate, slidingEndDate);
		} catch (NoQuotationsException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Select referee.
	 * 
	 * @author Guillaume Thoreton
	 * @param listShares 
	 */
	private Stock selectReferee() {

		//Open selection window
		NewRefereeDialog pItemDialog = (NewRefereeDialog) NewRefereeDialog.showUI(getShell(), this);
		Set<Stock> listStock = pItemDialog.getSelectedStocks();
		Stock referree = null;
		if (listStock != null && listStock.size() > 0) {

			referree = listStock.iterator().next();

			try {
				getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
		
				QuotationUpdate quotationUpdate = new QuotationUpdate();
				quotationUpdate.getQuotes(new StockList(listStock));
				loadRefereeQuotations(referree);
				MainPMScmd.getPrefs().put("charts.referee", referree.getSymbol());

			} catch (Exception e) {
				ErrorDialog inst = new ErrorDialog(this.getShell(), SWT.NULL, "Invalid referee : "+referree.getFriendlyName()+"\n"+e, null);
				inst.open();
			} finally {
				getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
			}

		} else if (pItemDialog instanceof NewRefereeDialog) {
			ErrorDialog inst = new ErrorDialog(this.getShell(), SWT.NULL, "No referee selected please select a stock \n", null);
			inst.open();
		}

		return referree;

	}

	/**
	 * @param selectReferreText 
	 * @throws InvalidAlgorithmParameterException 
	 * 
	 */
	private void relativeIndexSetting(String selectReferreText) {
		
		if (selectReferreText != null && !"Your referee".equals(selectReferreText) && refereeQuotations != null && refereeQuotations.size() != 0) {
			try {
				stripedCloseFunction =  new StripedCloseIndexRelative(refereeQuotations, slidingStartDate, slidingEndDate);
			} catch (InvalidAlgorithmParameterException e) {
				LOGGER.error("",e);
			}
		} else {
			String preferedRef = MainPMScmd.getPrefs().get("charts.referee", "Not Defined");
			
			Stock stock;
			if (!preferedRef.equals("Not Defined")) {
				try {
					stock = DataSource.getInstance().loadStockBySymbol(preferedRef);
					loadRefereeQuotations(stock);
				} catch (Exception e) {
					LOGGER.debug(e);
					ErrorDialog inst = new ErrorDialog(getShell(), SWT.NULL, "Referree unknown or no quotations", null);
					inst.open();
					stripedCloseFunction =  new StripedCloseInitPriceRelative();
					//throw new InvalidAlgorithmParameterException(e);
				}
			}
		}
		
	}	
	

	public void setComposite(PortfolioComposite composite) {
		this.portfolioComposite = composite;
	}

	@Override
	public void setCursor(Cursor arg0) {
		super.setCursor(arg0);
		
		java.awt.Cursor awtPredefinedCursor;
		if (arg0.equals(CursorFactory.getCursor(SWT.CURSOR_WAIT)) || arg0.equals(CursorFactory.getCursor(SWT.CURSOR_APPSTARTING))) {
			
			if (arg0.equals(CursorFactory.getCursor(SWT.CURSOR_APPSTARTING))) {
				awtPredefinedCursor = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR);
			} else {
				awtPredefinedCursor = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR);
			}

		} else {
			awtPredefinedCursor = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR);
		}
		
		SWT_AWT.getFrame(mainChartComposite).setCursor(awtPredefinedCursor); 
		if ( mainChartPanel.getComponents().length > 0) {
			mainChartPanel.getComponent(0).setCursor(awtPredefinedCursor);
		}
		
	}
	
	public Date getSlidingStartDate() {
		return slidingStartDate;
	}

	public Date getSlidingEndDate() {
		return slidingEndDate;
	}
	

	@Override
	public void initRefreshAction() {
		logComposite.initRefresh(this);
		getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
		synchronized (logComposite) {
			calculating = (Stock) hightlitedEventModel.getViewStateParams()[0];
		}
	}


	@Override
	public void endRefreshAction(List<Exception> exceptions) {
		synchronized (logComposite) {
			calculating = null;
		}
		try {
			logComposite.endJob(exceptions);
		} finally {
			getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}	
	}


	@Override
	public void refreshView(List<Exception> exceptions) {
		
		Object[] viewStateParams = hightlitedEventModel.getViewStateParams();
		if (viewStateParams != null && viewStateParams.length == 1) highLight(highligtedId, (Stock) viewStateParams[0], false);
		if (viewStateParams != null && viewStateParams.length == 1 && isVisible()) {
			for (Exception exception : exceptions) {
				if (exception instanceof EventRefreshException) {
					ErrorDialog dialog = new ErrorDialog(getShell(), SWT.NONE, 
							"Couldn't refresh events for "+((Stock) viewStateParams[0]).getFriendlyName()+"\n" +
							"Check that date bounds are not out of range.", 
							exceptions.toString());
					exceptions.clear();
					dialog.open();
					break;
				}
			}
		}
		
	}


	@Override
	public Date getAnalysisStartDate() {
		return this.slidingStartDate;
	}


	private void sliderChangesApply() {
		if (sliderSelection) {
			synchronized (sliderSelection) {
				sliderSelection = false;
				updateCharts(listShares, false, true);
				portfolioComposite.slidingDateChange();
			}
		}
	}
	
}