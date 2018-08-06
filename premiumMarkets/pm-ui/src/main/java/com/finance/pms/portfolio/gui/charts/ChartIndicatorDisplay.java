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
package com.finance.pms.portfolio.gui.charts;

import java.awt.EventQueue;
import java.io.File;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.ActionDialogAction;
import com.finance.pms.CursorFactory;
import com.finance.pms.IndicatorCalculationServiceMain;
import com.finance.pms.MainGui;
import com.finance.pms.PopupMenu;
import com.finance.pms.RefreshableView;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.EventRefreshController;
import com.finance.pms.datasources.EventRefreshException;
import com.finance.pms.datasources.EventTaskQueue;
import com.finance.pms.datasources.TaskId;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.OutputDescr;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.chartUtils.BarChart;
import com.finance.pms.events.scoring.chartUtils.BarSettings;
import com.finance.pms.events.scoring.chartUtils.ChartBarUtils;
import com.finance.pms.events.scoring.chartUtils.DataSetBarDescr;
import com.finance.pms.events.scoring.dto.TuningResDTO;
import com.finance.pms.portfolio.gui.ActionDialogForm;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;
import com.finance.pms.threads.ConfigThreadLocal;

public class ChartIndicatorDisplay extends ChartDisplayStrategy {

	private enum PopupType {EVTCHARTING, EVTTREND};

	private final class EventsDataLoader extends Observable  implements Runnable {

		private final Stock selectedShare;
		private final Date exentedStartDate;
		private Map<String, Config> configs;

		private EventsDataLoader(Map<String, Config> configs, Stock selectedShare, Date exentedStartDate) {
			this.selectedShare = selectedShare;
			this.exentedStartDate = exentedStartDate;
			this.configs= configs;
		}

		@Override
		public void run() {

			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, configs.get(Config.EVENT_SIGNAL_NAME));
			ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME, configs.get(Config.INDICATOR_PARAMS_NAME));

			SymbolEvents eventsForStock = EventsResources.getInstance().crudReadEventsForStock(selectedShare, exentedStartDate, chartTarget.getSlidingEndDate(), chartTarget.getChartedEvtDefsTrends(), IndicatorCalculationServiceMain.UI_ANALYSIS);
			setChanged();
			notifyObservers(eventsForStock);
		}

	}

	private static MyLogger LOGGER = MyLogger.getLogger(ChartIndicatorDisplay.class);

	protected static final String TRENDBUTTXT = "Charted Trends ...";
	private static final String INDICATORSBUTTXT = "Charted Calculator ...";

	private Button calculatorSettingsButton;
	private PopupMenu<OutputDescr> calculatorSettingsPopupMenu;

	private Button chartedTrendsButton;
	private PopupMenu<EventInfo> chartedTrendsPopupMenu;

	//private Button trendSettingsButton
	private BarSettings trendSettings;
	private BarSettingsDialog trendSettingsDialog;

	private Button recalculationButton;

	private Map<EventInfo, TuningResDTO> tuningRessCache = new HashMap<>();


	public ChartIndicatorDisplay(ChartsComposite chartTarget) {
		super();

		this.trendSettings = new BarSettings();

		this.chartTarget = chartTarget;
		populatePopups(chartTarget.getPopusGroup());
		this.chartTarget.getMainChartWraper().initMainPlot(
				ChartMain.NUMBER_FORMAT, 
				"Nothing to display?\n" +
						"First select a stock in your portfolio.\n" +
						"Then use '"+TRENDBUTTXT+"' and/or '"+INDICATORSBUTTXT+"' buttons to select calculators.\n" +
				"Also check the portfolio stocks and sliding date ranges. Quotations have to be available.");


		this.chartTarget.setStripedCloseFunction(this, new StripedCloseRealPrice());

		resetChart(true);

	}

	@Override
	public void highLight(Integer idx, Stock selectedShare, Boolean recalculationGranted) {
		highLight(idx, selectedShare, recalculationGranted, PopupType.values());
	}

	public void highLight(Integer idx, Stock selectedShare, Boolean recalculationGranted, PopupType... popupTypes) {

		LOGGER.info("highLight(Integer "+idx+", Stock "+selectedShare+", Boolean "+recalculationGranted+", PopupType... "+Arrays.toString(popupTypes));

		try {

			this.chartTarget.getShell().setEnabled(false);
			this.chartTarget.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));

			chartTarget.getMainChartWraper().setMainYAxisLabel("");
			if (idx == null || selectedShare == null ) {
				return;
			}

			chartTarget.setHighligtedId(idx);
			chartTarget.getHightlitedEventModel().setViewParamRoot(selectedShare);

			if (chartTarget.isDisposed() || !chartTarget.isVisible()) {
				return;
			}

			hideAllButSelected(selectedShare);

			chartTarget.getMainChartWraper().setMainYAxisLabel(selectedShare.getFriendlyName());
			chartTarget.getMainChartWraper().highLightSerie(idx, 1);

			boolean areEvtDefsTrendsSelected = !chartTarget.getChartedEvtDefsTrends().isEmpty();
			if (areEvtDefsTrendsSelected) {//Some thing has to be displayed

				//We try and run
				try {
					//Gather event infos selected
					Set<EventInfo> allEvtInfos = chartTarget.initChartedEvtDefsTrendsSet();
					allEvtInfos.addAll(chartTarget.getChartedEvtDefsTrends());

					//Gather obsolete calculations
					HashSet<EventInfo> notUpToDateEventInfos = new HashSet<EventInfo>();
					Calendar minDate = Calendar.getInstance();
					minDate.setTime(new Date(0));
					Boolean outputDataNeedsUpdate =
							chartTarget.getHightlitedEventModel().cacheNeedsUpdateCheck(
									selectedShare, chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate(),
									notUpToDateEventInfos, minDate, allEvtInfos.toArray(new EventInfo[0]));

					//Refresh charts where required
					List<PopupType> popupTypesList = Arrays.asList(popupTypes);

					if (popupTypesList.contains(PopupType.EVTCHARTING)) {
						chartTarget.getMainChartWraper().resetIndicChart();
						updateChartIndicator(selectedShare, recalculationGranted, outputDataNeedsUpdate);
					}

					if (popupTypesList.contains(PopupType.EVTTREND)) {
						chartTarget.getMainChartWraper().resetBarChart();
						updateBarChart(selectedShare, chartTarget.getSlidingStartDate(), recalculationGranted, outputDataNeedsUpdate);
					}

					//Update chart content if needed (this subsequently will trigger charts updates calls)
					if (outputDataNeedsUpdate) {
						eventsRecalculationAck(selectedShare, chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate(), notUpToDateEventInfos, minDate);
					}

				} catch (Exception e) {
					LOGGER.error(e,e);
				}

			}

			//Clear chart areas if nothing is to be displayed
			if (!areEvtDefsTrendsSelected) {
				chartTarget.getMainChartWraper().resetIndicChart();
				chartTarget.getMainChartWraper().resetBarChart();
				refreshChartedTrendsPopup(false);
				refreshCalculatorSettingsPopup(false);
			}

		} finally {

			this.chartTarget.getShell().setEnabled(true);
			this.chartTarget.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));

		}

	}

	private void eventsRecalculationAck(final Stock selectedShare, Date slidingStartDate, Date slidingEndDate, final HashSet<EventInfo> notUpToDateEI, Calendar minDate) {

		RefreshableView parentView = chartTarget;
		final EventRefreshController ctrller = new EventRefreshController(chartTarget.getHightlitedEventModel(), parentView, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {

			@Override
			public void widgetSelected(SelectionEvent evt) {

				LOGGER.guiInfo("Cleaning and Recalculating. Thanks for waiting ...");
				EventTaskQueue.getSingleton().invalidateTasksCreationDates(TaskId.Analysis);
				//Will clean notUpToDateEI (selected event infos which have no output cached) event infos for this stock
				this.updateEventRefreshModelState(0l, TaskId.FetchQuotations, TaskId.Clean, TaskId.Analysis);

				super.widgetSelected(evt);

				if (!chartTarget.getChartedEvtDefsTrends().isEmpty()) {
					updateChartIndicator(selectedShare, false, true);
					updateBarChart(selectedShare, chartTarget.getSlidingStartDate(), false, true);
				}
				disableRecalculationButton();
			}

		};

		@SuppressWarnings("unchecked")
		boolean isValidTask = ctrller.isValidTask(TaskId.Analysis, selectedShare, new HashSet[]{notUpToDateEI}, slidingStartDate, slidingEndDate);

		if (isValidTask) {

			chartTarget.getHightlitedEventModel().setViewParamRoot(selectedShare);
			chartTarget.getHightlitedEventModel().setViewParam(0, notUpToDateEI);

			String msg = "Analysis are not up to date for "+selectedShare.getName()+", the selected time frame and the requested trends.";
			if (minDate.after(new Date(0))) msg = msg +"\nMinimun calculation date reached for this stock : "+new SimpleDateFormat("MMM dd yyyy").format(minDate);
			for (EventInfo eventInfo : notUpToDateEI) {
				if (chartTarget.getChartedEvtDefsTrends().contains(eventInfo)) {
					msg = msg + "\n'" + eventInfo.getEventReadableDef() + "' may be a candidate for update"; 
				}
			}

			recalculationButton.addSelectionListener(ctrller);
			recalculationButton.setToolTipText(msg);
			recalculationButton.setForeground(new Color(chartTarget.getDisplay(), 255, 0, 0));
			recalculationButton.setEnabled(true);
			recalculationButton.forceFocus();

		} else {
			//Disabling should happened in the EventSelectionListener itself.
		}

	}

	public void disableRecalculationButton() {
		recalculationButton.setForeground(null);
		recalculationButton.setEnabled(false);
		recalculationButton.setToolTipText("You can click on this when calculation needs updated");
		Arrays.stream(recalculationButton.getListeners(SWT.Selection)).forEach(l -> recalculationButton.removeListener(SWT.Selection, l));
		Arrays.stream(recalculationButton.getListeners(SWT.DefaultSelection)).forEach(l -> recalculationButton.removeListener(SWT.DefaultSelection, l));
	}

	private void hideAllButSelected(final Stock selectedShare) {

		for (SlidingPortfolioShare sShare : chartTarget.getCurrentTabShareList()) {
			if (!sShare.getStock().equals(selectedShare)) {
				sShare.setDisplayOnChart(false);
			} else {
				sShare.setDisplayOnChart(true);
			}
		}
		chartTarget.getStripedCloseFunction().updateStartDate(chartTarget.getSlidingStartDate());
		chartTarget.getStripedCloseFunction().updateEndDate(chartTarget.getSlidingEndDate());
		chartTarget.getMainChartWraper().updateLineDataSet(chartTarget.getCurrentTabShareList(), chartTarget.getStripedCloseFunction(), getIsApplyColor(), chartTarget.getPlotChartDimensions());

	}

	private void updateBarChart(final Stock selectedShare, Date exentedStartDate, final Boolean recalculationGranted, final Boolean outputDataNeedsUpdate) {

		final Map<String, Config> configs = ConfigThreadLocal.getAll();

		Observer barDataObs = new Observer() {

			@Override
			public void update(Observable o, final Object arg) {

				final Set<EventInfo> noDataTrends = new HashSet<EventInfo>();

				if (arg == null || ((SymbolEvents) arg).getDataResultMap().isEmpty()) {//No events found

					if (!recalculationGranted) {//No events found despite recalc
						chartTarget.getMainChartWraper().resetBarChart();
					} else {
						noDataTrends.addAll(chartTarget.getChartedEvtDefsTrends());
					}

				} else {//That's all or partially good, we display if needsUpdate

					for (final EventInfo eventDefinition : chartTarget.getChartedEvtDefsTrends()) {

						TuningResDTO tuningResDTO = null;
						try {
							if (outputDataNeedsUpdate || tuningRessCache == null || tuningRessCache.get(eventDefinition) == null) {
								tuningResDTO = chartTarget.getHightlitedEventModel().updateTuningRes(selectedShare, eventDefinition, chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate());
							} else {
								tuningResDTO = tuningRessCache.get(eventDefinition);
							}
						} catch (Exception e) {
							LOGGER.warn("No event results were found for "+eventDefinition+". Calculation needed. " + e);
						}
						if (tuningResDTO != null) {
							tuningRessCache.put(eventDefinition, tuningResDTO);
						} else {
							noDataTrends.add(eventDefinition);
						}

					}

					Runnable runnable = new Runnable() {
						public void run() {
							try {
								SymbolEvents ses = (SymbolEvents) arg;
								SortedMap<DataSetBarDescr, SortedMap<Date, BarChart>> barsData = 
										ChartBarUtils.buildBarsData(selectedShare, chartTarget.getChartedEvtDefsTrends(), chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate(), ses, tuningRessCache, trendSettings);
								chartTarget.getMainChartWraper().updateBarDataSet(barsData, chartTarget.getHighligtedId(), trendSettings, chartTarget.getPlotChartDimensions());
							} catch (Exception e) {
								LOGGER.error(
										"arg : "+ arg +
										", chartTarget.getMainChartWraper() : "+chartTarget.getMainChartWraper()+
										", chartTarget.getHighligtedId() : "+chartTarget.getHighligtedId()+
										", barChartSettings : "+trendSettings+
										", chartTarget.getPlotChartDimensions() : "+chartTarget.getPlotChartDimensions(),e);
							}
						}
					};
					try {
						EventQueue.invokeAndWait(runnable);
					} catch (Exception e) {
						LOGGER.error(e,e);
					}

				}

				//Missing bars
				if (!outputDataNeedsUpdate && !noDataTrends.isEmpty()) {
					String chartedEvtStr = EventDefinition.getReadableEventDefSetAsString(", ", noDataTrends);
					String errMsg =
							"No events are available for : " + chartedEvtStr + " and " + selectedShare.getFriendlyName() + " within the period you have selected.\n" +
									"If you just cleared the calculation results, you may want to Force and Update the calculations.";
					String addMsg =
							"This may also happen if calculations failed or if there is not enough quotations for the period.\n" +
									//+ "Check the selected Trends in " + TRENDBUTTXT + " as well as the date boundaries against the available quotations.\n"
									"You may want to check the date boundaries against the available quotations.\n" +
									"Also note that some calculators need full OLHC and Volume in order to be calculated.\n"+
									"If '"+chartedEvtStr+"' is one of your calculators you may also want to check its formula.";
					showPopupDialog(errMsg, "Ok", addMsg, null);
				}

			}

		};

		Observable eventsDataLoader = new EventsDataLoader(configs, selectedShare, exentedStartDate);
		eventsDataLoader.addObserver(barDataObs);

		Thread updateBarChartThread = new Thread((Runnable) eventsDataLoader);
		updateBarChartThread.start();

	}

	private void updateChartIndicator(final Stock selectedShare, boolean recalculationGranted, boolean outputDataNeedsUpdate) {

		Map<EventInfo, SortedMap<Date, double[]>> eventsSeries = new HashMap<>();
		for (EventInfo eventInfo: chartTarget.getChartedEvtDefsTrends()) {

			SortedMap<Date, double[]> outputCache = chartTarget.getHightlitedEventModel().getOutputCache(selectedShare, eventInfo);
			SortedMap<Date, double[]> subMap = new TreeMap<Date, double[]>();
			if (outputCache != null && !outputCache.isEmpty()) {

				Calendar instance = Calendar.getInstance();
				instance.setTime(chartTarget.getSlidingEndDate());
				QuotationsFactories.getFactory().incrementDate(instance, 1);
				Date endPlus1 = instance.getTime();

				if (endPlus1.after(outputCache.lastKey())) {
					subMap = outputCache.tailMap(this.chartTarget.getSlidingStartDate());
				} else {
					subMap = outputCache.subMap(this.chartTarget.getSlidingStartDate(), endPlus1);
				}

				eventsSeries.put(eventInfo, subMap);

			}
		}

		if (eventsSeries.isEmpty()) {

			if (!recalculationGranted && !outputDataNeedsUpdate) {//No indic found despite recalc
				String errMsg = 
						"No output data are available for display within the period you have selected share "+selectedShare.getFriendlyName()+" and selected indicators.\n" +
								"If you just cleared the calculations results, you may want to Force and Update calculations.";
				String addMsg = 
						"This may also happen if calculations failed, or if there is not enough quotations for the period.\n" +
								//"Check the calculators in "+TRENDBUTTXT+" as well as the date boundaries against the available quotations.\n" +
								"You may want to check the date boundaries against the available quotations.\n" +
								"Also note that some calculators need full OLHC and Volume in order to be calculated.\n" +
								"If the selected indicators were created by you, you may also want to check the formula.";
				showPopupDialog(errMsg, "Ok", addMsg, null);
			}

		} else { //Thats all or some are good, we display
			chartTarget.getMainChartWraper().updateIndicDataSet(eventsSeries, chartTarget.getPlotChartDimensions());
		}

	}

	@Override
	public void initRefreshAction() {
		//Nothing
	}

	@Override
	public void endRefreshAction(List<Exception> exceptions) {
		if (exceptions != null && !exceptions.isEmpty()) showPopupDialog("Calculation has failed", "Ok", exceptions.toString(), null);
	}

	@Override
	public void populatePopups(Composite popusGroup) {

		cleanPopupButtonsGroup(popusGroup);

		{
			Group chartedCalculatorGroup = new Group(popusGroup, SWT.NONE);
			RowLayout chartedCalculatorGroupL = new RowLayout(SWT.VERTICAL);
			chartedCalculatorGroupL.justify = true;
			chartedCalculatorGroupL.fill=true;
			chartedCalculatorGroupL.wrap=false;
			chartedCalculatorGroupL.marginHeight=0;
			chartedCalculatorGroup.setLayout(chartedCalculatorGroupL);
			{
				calculatorSettingsButton = new Button(chartedCalculatorGroup, SWT.NONE);
				calculatorSettingsButton.setFont(MainGui.DEFAULTFONT);
				calculatorSettingsButton.setText("Calculator settings ...");
				calculatorSettingsButton.setToolTipText(
						"Only user defined calculators can be customised.\n" +
								"You must select one of your user defined calculators in '"+INDICATORSBUTTXT + "'\n" +
								"And wait for its calculation to finish before changing the display settings.\n" +
						"New calculators can be defined using the menu Events -> Customise and create calculators ...");
				calculatorSettingsButton.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {	
						handleCalculatorSettingsSelection();
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						handleCalculatorSettingsSelection();
					}

					private void handleCalculatorSettingsSelection() {
						refreshCalculatorSettingsPopup(true);
					}

				});

			}
		}
		{
			Group chartedTrendsGroup = new Group(popusGroup, SWT.NONE);
			RowLayout chartedTrendsGroupL = new RowLayout(SWT.VERTICAL);
			chartedTrendsGroupL.justify = true;
			chartedTrendsGroupL.fill=true;
			chartedTrendsGroupL.wrap=false;
			chartedTrendsGroupL.marginHeight=0;
			chartedTrendsGroup.setLayout(chartedTrendsGroupL);
			{
				chartedTrendsButton = new Button(chartedTrendsGroup, SWT.PUSH);
				chartedTrendsButton.setFont(MainGui.DEFAULTFONT);
				chartedTrendsButton.setText(TRENDBUTTXT);
				chartedTrendsButton.setToolTipText("This is to setup the display of gathered calculators trends.\nYou must select a share in the portfolio to display its analysis.");

				chartedTrendsButton.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {		
						handleChartedTrendsSelection();
					}


					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						handleChartedTrendsSelection();
					}

					private void handleChartedTrendsSelection() {
						refreshChartedTrendsPopup(true);
					}

				});

			}
			{
				Button trendSettingsButton = new Button(chartedTrendsGroup, SWT.NONE);
				trendSettingsButton.setFont(MainGui.DEFAULTFONT);
				trendSettingsButton.setText("Trends settings ...");
				trendSettingsButton.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {	
						handleTrendSettingsSelection();
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						handleTrendSettingsSelection();
					}

					private void handleTrendSettingsSelection() {

						ActionDialogAction action = new ActionDialogAction() {

							@Override
							public void action() {

								Stock viewStateParams = chartTarget.getHightlitedEventModel().getViewParamRoot();
								if (viewStateParams != null) {
									highLight(chartTarget.getHighligtedId(), viewStateParams, true, PopupType.EVTTREND);
								} else {
									if  (chartTarget.getChartedEvtDefsTrends() != null && !chartTarget.getChartedEvtDefsTrends().isEmpty()) {
										String errorMessage = "You must select a share in the portfolio to display its analysis.";
										showPopupDialog(errorMessage, "Ok", null, null);
									}
								}
							}
						};

						if (trendSettingsDialog == null || trendSettingsDialog.getShell().isDisposed()) {
							trendSettingsDialog = new BarSettingsDialog(chartTarget, trendSettings, action);
							Rectangle parentBounds = chartTarget.getDisplay().map(chartTarget, null, chartTarget.getBounds());
							trendSettings = trendSettingsDialog.open(new Point(parentBounds.x + parentBounds.width, parentBounds.y));
						} else {
							trendSettingsDialog.getShell().setVisible(true);
							trendSettingsDialog.getShell().setActive();
							trendSettingsDialog.getShell().setFocus();
						}

					}
				});

			}
		}
		{
			Group recalcGroup = new Group(popusGroup, SWT.SHADOW_NONE);
			RowLayout recalcGroupL = new RowLayout(SWT.VERTICAL);
			recalcGroupL.justify = true;
			recalcGroupL.fill=true;
			recalcGroupL.wrap=false;
			recalcGroupL.marginHeight=0;
			recalcGroup.setLayout(recalcGroupL);
			{
				recalculationButton = new Button(recalcGroup, SWT.PUSH);
				recalculationButton.setFont(MainGui.DEFAULTFONT);
				recalculationButton.setText("Calculations Update");
				recalculationButton.setEnabled(false);
			}
			{
				Button forceRecalculationButton =  new Button(recalcGroup, SWT.PUSH);
				forceRecalculationButton.setFont(MainGui.DEFAULTFONT);
				forceRecalculationButton.setText("Force Calculations Update");
				RefreshableView parentView = chartTarget;
				forceRecalculationButton.addSelectionListener(new EventRefreshController(chartTarget.getHightlitedEventModel(), parentView, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {

					@Override
					public void widgetSelected(SelectionEvent evt) {
						LOGGER.guiInfo("Forced recalculation. Cleaning and Recalculating. Thanks for waiting ...");

						EventTaskQueue.getSingleton().invalidateTasksCreationDates(TaskId.Analysis);

						Set<EventInfo> allSelectedEventInfos = chartTarget.getChartedEvtDefsTrends();
						if (allSelectedEventInfos != null && !allSelectedEventInfos.isEmpty() && chartTarget.getHightlitedEventModel().getViewParamRoot() != null) {
							chartTarget.getHightlitedEventModel().setViewParam(0, allSelectedEventInfos);
							chartTarget.getHightlitedEventModel().setViewParam(1, Arrays.asList("setDirty")); //The dirty state has to be checked on the TunedConf status of the EventInfo
						}

						//Will clean selected event infos for this stock
						this.updateEventRefreshModelState(0l, TaskId.FetchQuotations, TaskId.Clean, TaskId.Analysis);
						super.widgetSelected(evt);
					}

				});
			}
		}
		popusGroup.layout();
		chartTarget.myPack();

	}

	@Override
	public void resetChart(Boolean resetDisplayedList) {

		for (SlidingPortfolioShare sShare : chartTarget.getCurrentTabShareList()) {
			Stock viewStateParams = chartTarget.getHightlitedEventModel().getViewParamRoot();
			if (viewStateParams != null && viewStateParams.equals(sShare.getStock()))  {
				sShare.setDisplayOnChart(true);
			} else {
				sShare.setDisplayOnChart(false);
			}
		}

		chartTarget.getMainChartWraper().resetLineChart();
		chartTarget.getMainChartWraper().resetBarChart();
		chartTarget.getMainChartWraper().resetIndicChart();

	}


	@Override
	public Boolean getIsApplyColor() {
		return false;
	}

	@Override
	public void exportBarChartPng() {

		try {

			final ActionDialogForm actionDialog = new ActionDialogForm(chartTarget.getShell(), "Ok", null, "PNG file name" );
			final Text exportPngFileName = new Text(actionDialog.getParent(), SWT.NONE | SWT.CENTER | SWT.BORDER);
			exportPngFileName.setText("Type in the png file name");
			exportPngFileName.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (exportPngFileName.getText().equals("Type in the png file name")) exportPngFileName.setText("");
				}
			});
			ActionDialogAction actionDialogAction = new ActionDialogAction() {
				@Override
				public void action() {

					actionDialog.values[0] = exportPngFileName.getText();

					try {
						//Export
						String filePath = System.getProperty("installdir")+File.separator+"tmp";
						String fileName = filePath+File.separator+actionDialog.values[0]+".png";
						if (new File(fileName).exists()) throw new InvalidParameterException("File "+fileName+" already exists!");
						chartTarget.getMainChartWraper().exportChart(fileName);
					} catch (InvalidParameterException e) {
						throw e;
					} catch (Exception e) {
						LOGGER.error(e,e);
					}
				}
			};
			actionDialog.setControl(exportPngFileName);
			actionDialog.setAction(actionDialogAction);
			actionDialog.open();

		} catch (InvalidParameterException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.warn(e,e);
		}
	}


	@Override
	public void refreshView(List<Exception> exceptions) {

		if (chartTarget.getHightlitedEventModel().getViewParamRoot() != null && chartTarget.isVisible()) {
			for (Exception exception : exceptions) {
				if (exception instanceof EventRefreshException) {
					String errorMessage = 
							"Trends and calculators could not all be updated for "+chartTarget.getHightlitedEventModel().getViewParamRoot().getFriendlyName()+".\n"
									+ "Check that date bounds are not out of range and that the appropriate quotations are available.\n"
									+ "Also keep in mind that some calculators need full OLHC and Volume in order to be calculated.";
					String addMessage = exceptions.toString();
					showPopupDialog(errorMessage, "Ok", addMessage, null);
					exceptions.clear();
					break;
				}
			}
		}

		if (chartedTrendsPopupMenu!= null && !chartedTrendsPopupMenu.getSelectionShell().isDisposed()) {
			refreshChartedTrendsPopup(false);
		}
		if (calculatorSettingsPopupMenu!= null && !calculatorSettingsPopupMenu.getSelectionShell().isDisposed()) {
			refreshCalculatorSettingsPopup(false);
		}
	}

	private void refreshChartedTrendsPopup(Boolean activate) {

		Set<EventInfo> availEventDefs = EventDefinition.loadMaxPassPrefsEventInfo();
		ActionDialogAction disactivateAction = new ActionDialogAction() {

			@Override
			public void action() {

				Stock viewStateParams = chartTarget.getHightlitedEventModel().getViewParamRoot();
				if (viewStateParams != null ) {
					LOGGER.info("Calling highLight from initEvtDefTrendPopup (Dialog Action).");
					highLight(chartTarget.getHighligtedId(), viewStateParams, true, PopupType.EVTTREND, PopupType.EVTCHARTING);
				} else {
					if (chartTarget.getChartedEvtDefsTrends() != null && !chartTarget.getChartedEvtDefsTrends().isEmpty()) {
						String errorMessage = "You must select a share in the portfolio to display its analysis.";
						showPopupDialog(errorMessage, "Ok", null, null);
					}
				}

			}
		};

		if (chartedTrendsPopupMenu == null || chartedTrendsPopupMenu.getSelectionShell().isDisposed()) {

			chartedTrendsPopupMenu = new PopupMenu<EventInfo>(chartTarget, chartedTrendsButton, availEventDefs, chartTarget.getChartedEvtDefsTrends(), false, true, SWT.CHECK, null, disactivateAction, true);
			Rectangle parentBounds = chartTarget.getDisplay().map(chartTarget, null, chartTarget.getBounds());
			chartedTrendsPopupMenu.open(new Point(parentBounds.x + parentBounds.width, parentBounds.y + parentBounds.height/2), true);

		} else {

			chartedTrendsPopupMenu.updateAction(availEventDefs, chartTarget.getChartedEvtDefsTrends(), null, disactivateAction, true);
			chartedTrendsPopupMenu.getSelectionShell().setVisible(true);
			if (activate) {
				chartedTrendsPopupMenu.getSelectionShell().setActive();
				chartedTrendsPopupMenu.getSelectionShell().setFocus();
			}

		}

	}

	private void refreshCalculatorSettingsPopup(Boolean activatePopup) {

		try {

			boolean isIndicatorSelected = !chartTarget.getChartedEvtDefsTrends().isEmpty();

			if ( isIndicatorSelected ) {

				final Set<OutputDescr> availableOutputs = new TreeSet<>();
				final Set<OutputDescr> displayedOutputs = new TreeSet<>();

				try {
					chartTarget.getChartedEvtDefsTrends().stream().forEach(t -> {
						availableOutputs.addAll(t.getEventDefDescriptor().allOutputDesrc());
						displayedOutputs.addAll(t.getEventDefDescriptor().displayedOutputsDescr());
					});
				} catch (NoSuchElementException e) {
					LOGGER.warn(e);
				} 

				if (!availableOutputs.isEmpty()) {

					ActionDialogAction deactivateAction =  new ActionDialogAction() {

						@Override
						public void action() {
							for (OutputDescr outputDescr : availableOutputs) {
								if (displayedOutputs.contains(outputDescr)) {
									outputDescr.setDisplayOnChart(true);
								} else {
									outputDescr.setDisplayOnChart(false);
								}
							}
							Stock viewStateParams = chartTarget.getHightlitedEventModel().getViewParamRoot();
							LOGGER.info("Calling highLight from initChartSettingsPopup (Dialog Action).");
							highLight(chartTarget.getHighligtedId(), viewStateParams, true, PopupType.EVTCHARTING);
						}

					};

					if (calculatorSettingsPopupMenu == null || calculatorSettingsPopupMenu.getSelectionShell().isDisposed()) {
						calculatorSettingsPopupMenu = new PopupMenu<OutputDescr>(chartTarget, calculatorSettingsButton, availableOutputs, displayedOutputs, false, true, SWT.CHECK, null, deactivateAction, true);
						Rectangle parentBounds = chartTarget.getDisplay().map(chartTarget, null, chartTarget.getBounds());
						calculatorSettingsPopupMenu.open(new Point(parentBounds.x + parentBounds.width, parentBounds.y), false);
					} 
					else {
						calculatorSettingsPopupMenu.updateAction(availableOutputs, displayedOutputs, null, deactivateAction, true);
						calculatorSettingsPopupMenu.getSelectionShell().setVisible(true);
						if (activatePopup) {
							calculatorSettingsPopupMenu.getSelectionShell().setActive();
							calculatorSettingsPopupMenu.getSelectionShell().setFocus();
						}
					}

				} else {

					if (calculatorSettingsPopupMenu != null && !calculatorSettingsPopupMenu.getSelectionShell().isDisposed()) {
						calculatorSettingsPopupMenu.getSelectionShell().dispose();
					}

				}

			}

		} catch (Exception e) {
			LOGGER.warn(e,e);
			showPopupDialog(e.getMessage(), "Ok", null, null);
		}
	} 


	@Override
	public void shutDownDisplay() {

		if (chartedTrendsPopupMenu != null) chartedTrendsPopupMenu.getSelectionShell().dispose();

		chartTarget.getMainChartWraper().resetLineChart();
		chartTarget.getMainChartWraper().resetBarChart();
		chartTarget.getMainChartWraper().resetIndicChart();

	}


	@Override
	public void updateButtonsToolTips() {
		//Nothing
	}

	@Override
	public void cleanPreviousStockSelection() {
		disableRecalculationButton();
	}


}
