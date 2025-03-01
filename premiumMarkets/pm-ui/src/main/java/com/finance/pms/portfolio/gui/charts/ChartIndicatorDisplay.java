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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.ActionDialogAction;
import com.finance.pms.CursorFactory;
import com.finance.pms.LogComposite;
import com.finance.pms.MainGui;
import com.finance.pms.MainPMScmd;
import com.finance.pms.PopupMenu;
import com.finance.pms.RefreshableView;
import com.finance.pms.SpringContext;
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
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.calculation.InvalidParameterException;
import com.finance.pms.events.calculation.SelectedIndicatorsCalculationService;
import com.finance.pms.events.calculation.parametrizedindicators.OutputDescr;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.operations.CalculateThreadExecutor;
import com.finance.pms.events.scoring.chartUtils.BarChart;
import com.finance.pms.events.scoring.chartUtils.BarSettings;
import com.finance.pms.events.scoring.chartUtils.ChartBarUtils;
import com.finance.pms.events.scoring.chartUtils.DataSetBarDescr;
import com.finance.pms.events.scoring.dto.TuningResDTO;
import com.finance.pms.portfolio.gui.ActionDialogForm;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;
import com.finance.pms.threads.ConfigThreadLocal;

public class ChartIndicatorDisplay extends ChartDisplayStrategy {

	private final class EventsDataLoader extends Observable  implements Runnable {

		private final Stock selectedShare;
		private final Date exentedStartDate;
		private Map<String, Config> configs;

		private EventsDataLoader(Map<String, Config> configs, Stock selectedShare, Date exentedStartDate) {
			this.selectedShare = selectedShare;
			this.exentedStartDate = exentedStartDate;
			this.configs = configs;
		}

		@Override
		public void run() {
			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, configs.get(Config.EVENT_SIGNAL_NAME));
			ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME, configs.get(Config.INDICATOR_PARAMS_NAME));

			SymbolEvents eventsForStock = EventsResources.getInstance().crudReadEventsForStock(
						selectedShare, exentedStartDate, chartTarget.getSlidingEndDate(), 
						chartTarget.getChartedEvtDefsTrends(), SelectedIndicatorsCalculationService.getAnalysisName());
			setChanged();
			notifyObservers(eventsForStock);
		}

	}

	private static MyLogger LOGGER = MyLogger.getLogger(ChartIndicatorDisplay.class);

	protected static final String TRENDBUTTXT = "Charted Trends ...";
	private static final String INDICATORSBUTTXT = "Charted Calculator ...";
	private static final String CALCULATOR_SETTINGS_TITLE = "Calculator settings ...";
	
	private LogComposite logComposite;

	private Button calculatorSettingsButton;
	private PopupMenu<OutputDescr> calculatorSettingsPopupMenu;

	private Button chartedTrendsButton;
	private PopupMenu<EventInfo> chartedTrendsPopupMenu;

	private BarSettings trendSettings;
	private BarSettingsDialog trendSettingsDialog;

	private Button recalculationButton;
	private PopupMenu<EventInfo> recalculationPopupMenu;

	private CCombo popupIndsFilter;


	public ChartIndicatorDisplay(ChartsComposite chartTarget, LogComposite logComposite) {
		super();

		this.trendSettings = new BarSettings();
		this.logComposite = logComposite;
		this.chartTarget = chartTarget;
		this.setStripedCloseFunction(new StripedCloseRealPrice(chartTarget.getSlidingStartDate(), chartTarget.getAnalysisEndDate()));
		
		init(chartTarget);

	}

	@Override
	public void init(ChartsComposite chartTarget) {
		
		populatePopups(chartTarget.getPopusGroup());
		
		this.chartTarget.getMainChartWraper()
		.initMainPlot(
			ChartMain.NUMBER_FORMAT, 
			"Nothing to display?\n" +
					"First select a stock in your portfolio.\n" +
					"Then use '" + TRENDBUTTXT + "' and/or '" + INDICATORSBUTTXT + "' buttons to select calculators.\n" +
			"Also check the portfolio stocks and sliding date ranges. Quotations have to be available."
		);
		
		this.chartTarget.updateDisplay(this);
		resetChart(true);
	}

	@Override
	public void highLight(Integer idx, Stock selectedShare, Boolean recalculationGranted, PopupType... popupTypes) {
		if (popupTypes.length == 0) popupTypes = PopupType.values();
		highLightInds(idx, selectedShare, recalculationGranted, popupTypes);
		refreshCalculatorSettingsPopup(false);
	}

	private void highLightInds(Integer idx, Stock selectedShare, Boolean recalculationGranted, PopupType... popupTypes) {

		LOGGER.info("highLight(Integer " + idx + ", Stock " + selectedShare + ", Boolean " + recalculationGranted + ", PopupType... " + Arrays.toString(popupTypes));
		
		Display.getDefault().asyncExec(new Runnable() {
            public void run() {
            	logComposite.getLogDisplay().setText("");
            }
        });

		try {

        	ChartIndicatorDisplay.this.chartTarget.getShell().setEnabled(false);
        	ChartIndicatorDisplay.this.chartTarget.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));

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
					} else {
						disableRecalculationButton();
					}

				} catch (Exception e) {
					LOGGER.error(e,e);
				}

			}

			//Clear chart areas if nothing is to be displayed
			if (!areEvtDefsTrendsSelected) {
				chartTarget.getMainChartWraper().resetIndicChart();
				chartTarget.getMainChartWraper().resetBarChart();
			}

		} finally {
        	ChartIndicatorDisplay.this.chartTarget.getShell().setEnabled(true);
        	ChartIndicatorDisplay.this.chartTarget.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}

	}

	private void eventsRecalculationAck(final Stock selectedShare, Date slidingStartDate, Date slidingEndDate, final HashSet<EventInfo> notUpToDateEI, Calendar minDate) {

		RefreshableView parentView = chartTarget;
		final EventRefreshController ctrller = new EventRefreshController(chartTarget.getHightlitedEventModel(), parentView, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
			
			@Override
			public void widgetSelected(SelectionEvent evt) {
				
				if (chartTarget.getChartedEvtDefsTrends().size() > 1) {
					Set<EventInfo> selectedForRefresh = new HashSet<>();
					
					ActionDialogAction deactivateAction = new ActionDialogAction() {

						@Override
						public void action() {
							chartTarget.getHightlitedEventModel().setViewParam(0, selectedForRefresh);
							doAction(selectedShare, evt);
							recalculationPopupMenu.getSelectionShell().setVisible(false);
						}

					};
					
					if (recalculationPopupMenu == null || recalculationPopupMenu.getSelectionShell().isDisposed()) {
						recalculationPopupMenu = new PopupMenu<EventInfo>(chartTarget, recalculationButton, notUpToDateEI, selectedForRefresh, false, true, SWT.CHECK, null, deactivateAction, false);
						Rectangle parentBounds = chartTarget.getDisplay().map(recalculationButton, null, recalculationButton.getBounds());
						recalculationPopupMenu.open(new Point(parentBounds.x + parentBounds.width, parentBounds.y), false);
					} else {
						recalculationPopupMenu.updateAction(notUpToDateEI, selectedForRefresh, null, deactivateAction, false);
						//					if (activatePopup) {
						recalculationPopupMenu.getSelectionShell().setVisible(true);
						recalculationPopupMenu.getSelectionShell().setActive();
						recalculationPopupMenu.getSelectionShell().setFocus();
						//					}
					} 
				} else {
					doAction(selectedShare, evt);
				}

			}
			

			private void doAction(final Stock selectedShare, SelectionEvent evt) {
				LOGGER.guiInfo("Cleaning and Recalculating. Thanks for waiting ...");
				EventTaskQueue.getSingleton().invalidateTasksCreationDates(TaskId.Analysis);
				//Will clean notUpToDateEI (selected event infos which have no output cached) event infos for this stock
				updateEventRefreshModelState(0l, TaskId.FetchQuotations, TaskId.Clean, TaskId.Analysis);
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

			String msg = "Analysis are not up to date for " + selectedShare.getName() + ", the selected time frame and the requested trends.";
			if (minDate.after(new Date(0))) msg = msg + "\nMinimun calculation date reached for this stock: " + new SimpleDateFormat("MMM dd yyyy").format(minDate);
			for (EventInfo eventInfo : notUpToDateEI) {
				if (chartTarget.getChartedEvtDefsTrends().contains(eventInfo)) {
					msg = msg + "\n'" + eventInfo.getEventReadableDef() + "' may be a candidate for update"; 
				}
			}

			recalculationButton.addSelectionListener(ctrller);
			recalculationButton.setToolTipText(msg);
			recalculationButton.setForeground(new Color(chartTarget.getDisplay(), 255, 0, 0));
			recalculationButton.setEnabled(true);
			recalculationButton.redraw();
			//recalculationButton.setFocus();
			
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
		this.getStripedCloseFunction().updateStartDate(chartTarget.getSlidingStartDate());
		this.getStripedCloseFunction().updateEndDate(chartTarget.getSlidingEndDate());
		chartTarget.getMainChartWraper().updateLineDataSet(chartTarget.getCurrentTabShareList(), this.getStripedCloseFunction(), getIsApplyColor());

	}

	private void updateBarChart(final Stock selectedShare, Date exentedStartDate, final Boolean recalculationGranted, final Boolean outputDataNeedsUpdate) {

		final Map<String, Config> configs = ConfigThreadLocal.getAll();

		Observer barDataObs = new Observer() {

			@Override
			public void update(Observable o, final Object arg) {

				final Set<EventInfo> noDataTrends = new HashSet<EventInfo>();
				SymbolEvents ses = (SymbolEvents) arg;
	
				if (arg == null || ((SymbolEvents) arg).getDataResultMap().isEmpty()) {//No events found

					if (!recalculationGranted) {//No events found despite recalculation
						chartTarget.getMainChartWraper().resetBarChart();
					} else {
						noDataTrends.addAll(chartTarget.getChartedEvtDefsTrends());
					}

				} else {//That's all or partially good, we display if needsUpdate

					Map<EventInfo, TuningResDTO> tuningRessCache = new HashMap<>();
					for (final EventInfo eventDefinition : chartTarget.getChartedEvtDefsTrends()) {

						TuningResDTO tuningResDTO = null;
						try {
							SortedMap<EventKey, EventValue> evtDefEvents = ses.getDataResultMap().entrySet().stream()
									.filter(e -> e.getKey().getEventInfo().equals(eventDefinition))
									.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (a, b) -> b, TreeMap::new));
							tuningResDTO = chartTarget.getHightlitedEventModel()
									.updateTuningRes(selectedShare, eventDefinition, evtDefEvents, chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate());
						} catch (Exception e) {
							LOGGER.warn("No event results were found for " + eventDefinition + ". Calculation needed. " + e);
						}
						if (tuningResDTO == null) {
							noDataTrends.add(eventDefinition);
						} else {
							tuningRessCache.put(eventDefinition, tuningResDTO);
						}

					}

//					//Generate png test
//					try {
//						Map<EventInfo, SortedMap<Date, double[]>> eventsOutputs = 
//								chartTarget.getChartedEvtDefsTrends().stream().collect(Collectors.toMap(e -> e, e -> chartTarget.getHightlitedEventModel().getOutputCache(selectedShare, e)));
//						EventInfo mainEventInfo = chartTarget.getChartedEvtDefsTrends().stream().findAny().get();
//						ChartImageBuilder chartImageBuilder = new ChartImageBuilder(selectedShare, IndicatorCalculationServiceMain.UI_ANALYSIS, mainEventInfo, tuningRessCache, eventsOutputs);
//						chartImageBuilder.build();
//					} catch (Exception e) {
//						LOGGER.warn(e);
//					}

					Runnable runnable = new Runnable() {
						public void run() {
							try {
								
								Display.getDefault().asyncExec(new Runnable() {
						            public void run() {
						            	ChartIndicatorDisplay.this.chartTarget.getShell().setEnabled(false);
										ChartIndicatorDisplay.this.chartTarget.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
						            }
						        });
								
								SortedMap<DataSetBarDescr, SortedMap<Date, BarChart>> barsData = ChartBarUtils
										.buildBarsData(
												selectedShare, chartTarget.getChartedEvtDefsTrends(), chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate(), 
												ses, tuningRessCache, trendSettings);
								chartTarget.getMainChartWraper().updateBarDataSet(
										selectedShare, chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate(), trendSettings.getAutoSetTimeLine(),
										barsData, chartTarget.getHighligtedId(), trendSettings, chartTarget.getPlotChartDimensions());
							} catch (Exception e) {
								LOGGER.error("arg: " + arg + ", chartTarget.getMainChartWraper(): " + chartTarget.getMainChartWraper() + ", chartTarget.getHighligtedId(): "
										+ chartTarget.getHighligtedId() + ", barChartSettings: " + trendSettings + ", chartTarget.getPlotChartDimensions(): "
										+ chartTarget.getPlotChartDimensions(), e);
								
							} finally {
								Display.getDefault().asyncExec(new Runnable() {
						            public void run() {
						            	ChartIndicatorDisplay.this.chartTarget.getShell().setEnabled(true);
										ChartIndicatorDisplay.this.chartTarget.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
						            }
						        });
							}
						}
					};
					try {
						//EventQueue.invokeAndWait(runnable);
						Thread t = new Thread(runnable);
						t.start();
					} catch (Exception e) {
						LOGGER.error(e, e);
					}

				}
				//Missing bars
				if (!outputDataNeedsUpdate && !noDataTrends.isEmpty()) {
					String chartedEvtStr = EventDefinition.getReadableEventDefSetAsString(", ", noDataTrends);
					String errMsg = 
							"No events are available for " + chartedEvtStr + " and " + selectedShare.getFriendlyName() + " within the period you have selected.\n" +
							"You may want to check the date boundaries, the formulae, the OHLCV available and Force Update the calculations.";
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							logComposite.getLogDisplay().setText(errMsg);
						}
					});
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

			Map<EventInfo, SortedMap<Date, double[]>> outputCache = chartTarget.getHightlitedEventModel().getOutputCache(selectedShare, eventInfo);
			if (outputCache != null && !outputCache.isEmpty()) {
				
				SortedMap<Date, double[]> outputCacheValue = outputCache.get(eventInfo);
				SortedMap<Date, double[]> outputCacheValueRange = new TreeMap<Date, double[]>();
				
				Date endSlide = chartTarget.getSlidingEndDate();
				Date startSlide = chartTarget.getSlidingStartDate();
				//outputCacheValue could be null when the cached eventInfo formulae is different from the current one.
				//This is also is signalled by the outpuTimeStamp being dirty but dirty could also mean other things, like the date range has changed. 
				boolean hasCache = outputCacheValue != null && !outputCacheValue.isEmpty() ;
				if (hasCache && endSlide.compareTo(outputCacheValue.lastKey()) >= 0) {
					outputCacheValueRange = outputCacheValue.tailMap(startSlide);
				} else 
				if (hasCache && endSlide.compareTo(outputCacheValue.firstKey()) >= 0){
					outputCacheValueRange = MapUtils.subMapInclusive(outputCacheValue, startSlide, endSlide);
				} else {
					outputCacheValueRange = new TreeMap<>();
				}
				EventInfo outpuCacheKey = outputCache.keySet().stream().filter(e -> e.equals(eventInfo)).findFirst().orElseThrow();
				eventsSeries.put(outpuCacheKey, outputCacheValueRange);

			}
		}

		if (eventsSeries.isEmpty()) {

			if (!recalculationGranted && !outputDataNeedsUpdate) {//No indic found despite recalc
				String chartedEvtStr = EventDefinition.getReadableEventDefSetAsString(", ", chartTarget.getChartedEvtDefsTrends());
				String errMsg = "No events are available for " + chartedEvtStr + " and " + selectedShare.getFriendlyName() + " within the period you have selected.\n" +
				"You may want to check the date boundaries, the formulae, the OHLCV available and Force Update the calculations.";
				Display.getDefault().asyncExec(new Runnable() {
                    public void run() {
                    	logComposite.getLogDisplay().setText(errMsg);
                    }
                });
			} else if (recalculationGranted && outputDataNeedsUpdate) {
				Display.getDefault().asyncExec(new Runnable() {
                    public void run() {
                    	logComposite.getLogDisplay().setText("No output data is available and may have been cleared up from the memory cache.");
                    }
                });
			}

		} else { //Thats all, some date is good to display
			
			chartTarget.getMainChartWraper().updateIndicDataSet(
					selectedShare, chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate(), trendSettings.getAutoSetTimeLine(),
					eventsSeries, chartTarget.getPlotChartDimensions());
			
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
			chartedCalculatorGroupL.fill = true;
			chartedCalculatorGroupL.wrap = false;
			chartedCalculatorGroupL.marginHeight = 0;
			chartedCalculatorGroup.setLayout(chartedCalculatorGroupL);
			{
				final Button rangeBut =  new Button(chartedCalculatorGroup, SWT.RADIO | SWT.LEAD);
				rangeBut.setFont(MainGui.DEFAULTFONT);
				rangeBut.setText("Group Range");
				rangeBut.setToolTipText("Use one Range Axis for each output group.");
				rangeBut.setSelection(true);
				rangeBut.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						groupRanges();
					}

					private void groupRanges() {
						chartTarget.getMainChartWraper().setUseOneRange(false);
						chartTarget.getMainChartWraper().setUseNoGroupRanges(false);
						chartTarget.updateCharts(false, PopupType.EVTCHARTING);
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						groupRanges();
					}
				});
			}
			{
				final Button rangeBut =  new Button(chartedCalculatorGroup, SWT.RADIO | SWT.LEAD);
				rangeBut.setFont(MainGui.DEFAULTFONT);
				rangeBut.setText("Range for all");
				rangeBut.setToolTipText("Use one Range Axis for all indicators and groups.");
				rangeBut.setSelection(false);
				rangeBut.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						oneRangeForAll();
					}

					private void oneRangeForAll() {
						chartTarget.getMainChartWraper().setUseOneRange(rangeBut.getSelection());
						chartTarget.getMainChartWraper().setUseNoGroupRanges(!rangeBut.getSelection());
						chartTarget.updateCharts(false, PopupType.EVTCHARTING);
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						oneRangeForAll();
					}
				});
			}
			{
				final Button rangeBut =  new Button(chartedCalculatorGroup, SWT.RADIO | SWT.LEAD);
				rangeBut.setFont(MainGui.DEFAULTFONT);
				rangeBut.setText("Range for each");
				rangeBut.setToolTipText("Use one Range Axis for each output.");
				rangeBut.setSelection(false);
				rangeBut.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						oneRangeForEach();
					}

					private void oneRangeForEach() {
						chartTarget.getMainChartWraper().setUseNoGroupRanges(rangeBut.getSelection());
						chartTarget.getMainChartWraper().setUseOneRange(!rangeBut.getSelection());
						chartTarget.updateCharts(false, PopupType.EVTCHARTING);
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						oneRangeForEach();
					}
				});
			}
			{
				calculatorSettingsButton = new Button(chartedCalculatorGroup, SWT.NONE);
				calculatorSettingsButton.setFont(MainGui.DEFAULTFONT);
				calculatorSettingsButton.setText(CALCULATOR_SETTINGS_TITLE);
				calculatorSettingsButton.setToolTipText(
						"Only user defined indicators can be customised.\n" +
								"You must select one of your user defined calculators in '" + INDICATORSBUTTXT + "'\n" +
								"And wait for its calculation to finish before changing the display settings.\n" +
						"New indicators can be defined using the menu Events -> Customise and Create indicators ...");
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
			chartedTrendsGroupL.fill = true;
			chartedTrendsGroupL.wrap = false;
			chartedTrendsGroupL.marginHeight = 0;
			chartedTrendsGroup.setLayout(chartedTrendsGroupL);
			{
				Group chartedTrendsButGroup = new Group(chartedTrendsGroup, SWT.NONE);
				RowLayout chartedTrendsButGroupL = new RowLayout(SWT.HORIZONTAL);
				//chartedTrendsButGroupL.justify = true;
				//chartedTrendsButGroupL.fill = true;
				chartedTrendsButGroupL.wrap = false;
				chartedTrendsButGroupL.marginHeight = 0;
				chartedTrendsButGroupL.marginWidth = 0;
				//chartedTrendsButGroupL.pack = true;
				chartedTrendsButGroup.setLayout(chartedTrendsButGroupL);
				
				{	
					popupIndsFilter = new CCombo(chartedTrendsButGroup, SWT.SEARCH);
					//popupIndsFilter.setMessage("Type a Trends filter");
					popupIndsFilter.setFont(MainGui.DEFAULTFONT);
					try {
						String filters = MainPMScmd.getMyPrefs().get("ui.eventinfo.filter", null);
						//popupIndsFilter.setText(filter);
						List<String> filtersList = Arrays.asList(filters.split("___"));
						filtersList.stream().limit(10).forEach(f -> popupIndsFilter.add(f));
						popupIndsFilter.select(0);
					} catch (Exception e) {
						LOGGER.warn("Could not restore Trends filter: " + e);
					}
					popupIndsFilter.addSelectionListener(new SelectionListener() {
	
						@Override
						public void widgetSelected(SelectionEvent e) {
							handle();
						}
	
						private void handle() {
							//popupIndsFilter.remove(popupIndsFilter.getText());
							List<String> filters = Arrays.asList(popupIndsFilter.getItems());
							if (filters.contains(popupIndsFilter.getText())) {
								popupIndsFilter.remove(filters.indexOf(popupIndsFilter.getText()));
							}
							popupIndsFilter.add(popupIndsFilter.getText(), 0);
							refreshChartedTrendsPopup(true);
						}
	
						@Override
						public void widgetDefaultSelected(SelectionEvent e) {
							handle();
						}
					});
				}
				{
					chartedTrendsButton = new Button(chartedTrendsButGroup, SWT.PUSH);
					chartedTrendsButton.setText(TRENDBUTTXT);
					chartedTrendsButton.setFont(MainGui.DEFAULTFONT);
					chartedTrendsButton.setToolTipText("This is to set up the display of gathered calculators trends.\nYou must select a share in the portfolio to display its analysis.");
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
									highLightInds(chartTarget.getHighligtedId(), viewStateParams, true, PopupType.EVTTREND);
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
			recalcGroupL.fill = true;
			recalcGroupL.wrap = false;
			recalcGroupL.marginHeight = 0;
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
			{
				Button stopCalculationsButton =  new Button(recalcGroup, SWT.PUSH);
				stopCalculationsButton.setFont(MainGui.DEFAULTFONT);
				stopCalculationsButton.setText("Stop Calculations");
				RefreshableView parentView = chartTarget;
				stopCalculationsButton.addSelectionListener(
				new EventRefreshController(chartTarget.getHightlitedEventModel(), parentView, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {

					@Override
					public void widgetSelected(SelectionEvent evt) {
						LOGGER.guiInfo("Stoping Calculations.");
						
						CalculateThreadExecutor.getInstance().close();
						
						SelectedIndicatorsCalculationService analyzer = (SelectedIndicatorsCalculationService) SpringContext.getSingleton().getBean("selectedIndsCalculator");
						analyzer.getFutureTracker().stream().forEach(o -> {
							try {
								o.interruptEventInfo();
							} catch (Exception e) {
								LOGGER.error(e, e);
							}
						});
						
						Set<Thread> runningThreads = Thread.getAllStackTraces().keySet();
						List<Thread> filter = runningThreads.stream()
								.filter(t -> t.getName().contains("my-calculation-thread"))
								.collect(Collectors.toList());
						filter.stream().forEach(t -> t.interrupt());
						boolean allInterrupted = filter.stream().allMatch(t -> t.isInterrupted());
						
						if (allInterrupted) analyzer.getFutureTracker().clear();
						
						super.widgetSelected(evt);
					}

				});

			}
		}
		popusGroup.layout();
		chartTarget.myPack();

	}

	@Override
	public void resetChart(Boolean resetDisplayedList, PopupType ...popupTypes) {
		
		if (popupTypes.length == 0) popupTypes = PopupType.values();

		for (SlidingPortfolioShare sShare : chartTarget.getCurrentTabShareList()) {
			Stock viewStateParams = chartTarget.getHightlitedEventModel().getViewParamRoot();
			if (viewStateParams != null && viewStateParams.equals(sShare.getStock()))  {
				sShare.setDisplayOnChart(true);
			} else {
				sShare.setDisplayOnChart(false);
			}
		}

		if (Arrays.asList(popupTypes).contains(PopupType.EVTTREND)) {
			chartTarget.getMainChartWraper().resetLineChart();
			chartTarget.getMainChartWraper().resetBarChart();
		}
		if (Arrays.asList(popupTypes).contains(PopupType.EVTCHARTING)) {
			chartTarget.getMainChartWraper().resetIndicChart();
		}

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
						String filePath = System.getProperty("installdir") + File.separator + "tmp";
						String fileName = filePath + File.separator + actionDialog.values[0] + ".png";
						if (new File(fileName).exists()) throw new InvalidParameterException("File " + fileName + " already exists!");
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
							"Trends and calculators could not all be updated for " + chartTarget.getHightlitedEventModel().getViewParamRoot().getFriendlyName() + ".\n"
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
		if (calculatorSettingsPopupMenu != null && !calculatorSettingsPopupMenu.getSelectionShell().isDisposed()) {
			refreshCalculatorSettingsPopup(false);
		}
	}

	private void refreshChartedTrendsPopup(Boolean activate) {

		Set<EventInfo> availEventDefs = EventDefinition.loadMaxPassPrefsEventInfo();
		//(?:.*aroon.*|.*stoch.*|.*rsi.*|.*mfi.*|.*macd.*|.*ouse.*|.*input.*corrs.*|.*std.*|.*corFile.*)
		String indsFilter = popupIndsFilter.getText();
		List<String> filters = Arrays.asList(popupIndsFilter.getItems());
		MainPMScmd.getMyPrefs().put("ui.eventinfo.filter", filters.stream().reduce((a, i) -> a + "___" + i).orElse(""));
		MainPMScmd.getMyPrefs().flushy();
		
		final Set<EventInfo> filteredEventDefs;
		if (popupIndsFilter.getText() != null && !popupIndsFilter.getText().isEmpty()) {
			filteredEventDefs = availEventDefs.stream().filter(e -> e.getEventDefinitionRef().matches(indsFilter)).collect(Collectors.toSet());
			filteredEventDefs.addAll(chartTarget.getChartedEvtDefsTrends());
		} else {
			filteredEventDefs = availEventDefs;
		}
		
		ActionDialogAction disactivateAction = new ActionDialogAction() {

			@Override
			public void action() {

				Stock viewStateParams = chartTarget.getHightlitedEventModel().getViewParamRoot();
				if (viewStateParams != null ) {
					LOGGER.info("Calling highLight from initEvtDefTrendPopup (Dialog Action).");
					highLightInds(chartTarget.getHighligtedId(), viewStateParams, true, PopupType.EVTTREND, PopupType.EVTCHARTING);
				} else {
					if (chartTarget.getChartedEvtDefsTrends() != null && !chartTarget.getChartedEvtDefsTrends().isEmpty()) {
						String errorMessage = "You must select a share in the portfolio to display its analysis.";
						showPopupDialog(errorMessage, "Ok", null, null);
					}
				}
				
				//Store Ui Selection
				List<EventInfo> storedEventDefsList = new ArrayList<>(availEventDefs);
				String eventSelection = chartTarget.getChartedEvtDefsTrends().stream().map(se -> storedEventDefsList.indexOf(se) + "").reduce((a, i) -> a + "_" + i).orElse(null);
				MainPMScmd.getMyPrefs().put("ui.eventinfo.selection", eventSelection);
				MainPMScmd.getMyPrefs().flushy();

			}
		};

		if (chartedTrendsPopupMenu == null || chartedTrendsPopupMenu.getSelectionShell().isDisposed()) {
			chartedTrendsPopupMenu = new PopupMenu<EventInfo>(chartTarget, chartedTrendsButton, filteredEventDefs, chartTarget.getChartedEvtDefsTrends(), false, true, SWT.CHECK, null, disactivateAction, true);
			Rectangle parentBounds = chartTarget.getDisplay().map(chartTarget, null, chartTarget.getBounds());
			chartedTrendsPopupMenu.open(new Point(parentBounds.x + parentBounds.width + (int)(parentBounds.width * 0.05), parentBounds.y + (int)(parentBounds.y * 0.05)), false);
		} else {
			chartedTrendsPopupMenu.updateAction(filteredEventDefs, chartTarget.getChartedEvtDefsTrends(), null, disactivateAction, true);
			if (activate) {
				chartedTrendsPopupMenu.getSelectionShell().setVisible(true);
				chartedTrendsPopupMenu.getSelectionShell().setActive();
				chartedTrendsPopupMenu.getSelectionShell().setFocus();
			}
		}

	}

	private void refreshCalculatorSettingsPopup(Boolean activatePopup) {

		try {
			final Set<OutputDescr> availableOutputs = new TreeSet<>();
			final Set<OutputDescr> displayableOutputs = new TreeSet<>(); //subset of availableOutputs

			try {
				
				//Cached event infos (descriptors)
				Set<EventInfo> chartedEvtDefsTrends = chartTarget.getChartedEvtDefsTrends();
				Stock selectedShare = chartTarget.getHightlitedEventModel().getViewParamRoot();
				chartedEvtDefsTrends = chartedEvtDefsTrends.stream()
					.map(ei -> {
						Map<EventInfo, SortedMap<Date, double[]>> outputCache = chartTarget.getHightlitedEventModel().getOutputCache(selectedShare, ei);
						return (outputCache == null || outputCache.isEmpty()) ? new HashSet<EventInfo>() : outputCache.keySet();
					})
					.reduce(new HashSet<>(), (a, e) -> {a.addAll(e); return a;});
				
				//Non Multi
				chartedEvtDefsTrends.stream().forEach(t -> {
					try {
						Set<OutputDescr> nonMultiOutputDescr = t.getEventDefDescriptor().nonMULTIOutputDescr();
						availableOutputs.addAll(nonMultiOutputDescr);
					} catch (Exception e) {
						LOGGER.warn("nonMULTIOutputDescr: " + e);
					}
				});

				//Multi
				Set<OutputDescr> multiOutputDescrLimited = chartedEvtDefsTrends.stream()
						.flatMap(t -> {
							try {
								return t.getEventDefDescriptor().mULTIOutputDescr().stream();
							} catch (Exception e) {
								LOGGER.warn("mULTIOutputDescr: " + e);
								return Stream.empty();
							}
						})
						.limit(100)
						.collect(Collectors.toSet());
				availableOutputs.addAll(multiOutputDescrLimited);

				//Remove outputs above displayable threshold
				availableOutputs.stream().forEach(aOut -> {if (aOut.getDisplayOnChart()) displayableOutputs.add(aOut);});
				chartedEvtDefsTrends.stream()
					.flatMap(t -> t.getEventDefDescriptor().allOutputDescr().stream())
					.forEach(t -> {
						if (!displayableOutputs.contains(t)) t.setDisplayOnChart(false);
					});

				//Truncation Indicator
				long allOutputsSize = chartedEvtDefsTrends.stream().flatMap(t -> t.getEventDefDescriptor().allOutputDescr().stream()).count();
				if (allOutputsSize > availableOutputs.size()) {
					calculatorSettingsButton.setText(CALCULATOR_SETTINGS_TITLE + " TOP only.");
				} else {
					calculatorSettingsButton.setText(CALCULATOR_SETTINGS_TITLE);
				}

			} catch (NoSuchElementException e) {
				LOGGER.warn(e);
			}

			ActionDialogAction deactivateAction = new ActionDialogAction() {

				@Override
				public void action() {
					for (OutputDescr outputDescr : availableOutputs) {
						if (displayableOutputs.contains(outputDescr)) {
							outputDescr.setDisplayOnChart(true);
						} else {
							outputDescr.setDisplayOnChart(false);
						}
					}
					Stock viewStateParams = chartTarget.getHightlitedEventModel().getViewParamRoot();
					LOGGER.info("Calling highLight from initChartSettingsPopup (Dialog Action).");
					highLightInds(chartTarget.getHighligtedId(), viewStateParams, true, PopupType.EVTCHARTING);
				}

			};

			if (calculatorSettingsPopupMenu == null || calculatorSettingsPopupMenu.getSelectionShell().isDisposed()) {
				if (activatePopup) {
					calculatorSettingsPopupMenu = new PopupMenu<OutputDescr>(chartTarget, calculatorSettingsButton, availableOutputs, displayableOutputs, false, true, SWT.CHECK, null, deactivateAction, true);
					Rectangle parentBounds = chartTarget.getDisplay().map(chartTarget, null, chartTarget.getBounds());
					calculatorSettingsPopupMenu.open(new Point(parentBounds.x + parentBounds.width, parentBounds.y), false);
				}
			}
			else {
				calculatorSettingsPopupMenu.updateAction(availableOutputs, displayableOutputs, null, deactivateAction, true);
				if (activatePopup) {
					calculatorSettingsPopupMenu.getSelectionShell().setVisible(true);
					calculatorSettingsPopupMenu.getSelectionShell().setActive();
					calculatorSettingsPopupMenu.getSelectionShell().setFocus();
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
