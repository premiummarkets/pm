package com.finance.pms.portfolio.gui.charts;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.ActionDialog;
import com.finance.pms.ActionDialogAction;
import com.finance.pms.IndicatorCalculationServiceMain;
import com.finance.pms.MainGui;
import com.finance.pms.PopupMenu;
import com.finance.pms.RefreshableView;
import com.finance.pms.UserDialog;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.EventModel;
import com.finance.pms.datasources.EventModel.UpdateStamp;
import com.finance.pms.datasources.EventRefreshController;
import com.finance.pms.datasources.EventRefreshController.TaskId;
import com.finance.pms.datasources.db.StripedCloseRealPrice;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.chartUtils.BarSettings;
import com.finance.pms.events.scoring.chartUtils.ChartBarUtils;
import com.finance.pms.events.scoring.chartUtils.DataSetBarDescr;
import com.finance.pms.events.scoring.dto.TuningResDTO;
import com.finance.pms.portfolio.gui.ActionDialogForm;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;
import com.finance.pms.threads.ConfigThreadLocal;

public class ChartIndicatorDisplay extends ChartDisplayStrategy {

	private static MyLogger LOGGER = MyLogger.getLogger(ChartIndicatorDisplay.class);

	protected static final String TRENDBUTTXT = "Chart Trends ...";
	private static final String INDICATORSBUTTXT = "Chart Indicators ...";

	private Button evtDefsTrendChartingBut;
	private Button evtDefsChartingBut;
	
	private PopupMenu<EventInfo> evtDefTrendPopupMenu;
	private PopupMenu<EventInfo> evtDefChartingPopupMenu;

	private ActionDialog eventRecalculationAck;

	private BarSettings barChartSettings;
	private BarSettingsDialog barSettingsDialog;
	

	public ChartIndicatorDisplay(ChartsComposite chartTarget) {
		super();
		
		this.barChartSettings = new BarSettings();
		
		//TODO recalculate only selected indicators?
		this.chartTarget = chartTarget;
		populatePopups(chartTarget.getPopusGroup());
		this.chartTarget.getMainChartWraper().initMainPlot(
				ChartMain.NUMBER_FORMAT, 
				"Nothing to display?\n" +
				"Select a stock in your portfolio and " +
				"Use '"+TRENDBUTTXT+"' and/or '"+INDICATORSBUTTXT+"' buttons to select indicators.\n" +
				"Also check the portfolio stocks and sliding date ranges. There may be no quotations available.");
		
	}


	@Override
	public void highLight(Integer idx, Stock selectedShare, Boolean recalculationGranted) {

		if (!chartTarget.isVisible() || idx == null || selectedShare == null ) {
			chartTarget.getMainChartWraper().setMainYAxisLabel("");
			return;
		}
		
		chartTarget.setHighligtedId(idx);
		chartTarget.getHightlitedEventModel().setViewStateParams(selectedShare);

		if (!chartTarget.getChartedEvtDefsTrends().isEmpty()) {
			chartTarget.getMainChartWraper().resetBarChart();
		} 
		if (!chartTarget.getChartedEvtDef().equals(EventDefinition.ZERO)) {
			chartTarget.getMainChartWraper().resetIndicChart();
		}
		
		hideAllButSelected(selectedShare);
		
		chartTarget.getMainChartWraper().setMainYAxisLabel(selectedShare.getFriendlyName());
		chartTarget.getMainChartWraper().highLightSerie(chartTarget.getHighligtedId(), 1);

		if (!chartTarget.getChartedEvtDefsTrends().isEmpty() || !chartTarget.getChartedEvtDef().equals(EventDefinition.ZERO)) {//Some thing has to be displayed
				
				//We try and run
				try {
					Set<EventInfo> allEvtInfos = chartTarget.initChartedEvtDefsTrendsSet();
					allEvtInfos.addAll(chartTarget.getChartedEvtDefsTrends());
					if (!chartTarget.getChartedEvtDef().equals(EventDefinition.ZERO)) allEvtInfos.add(chartTarget.getChartedEvtDef());
					
					HashMap<EventInfo, UpdateStamp> notUptoDateStamps = new HashMap<EventInfo, EventModel.UpdateStamp>();
					Calendar minDate = Calendar.getInstance();
					minDate.setTime(new Date(0));
					Boolean needsUpdate = 
							chartTarget.getHightlitedEventModel().cacheNeedsUpdateCheck(
									selectedShare, chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate(), 
									notUptoDateStamps, minDate, allEvtInfos.toArray(new EventInfo[0]));

					//if (needsUpdate && recalculationGranted) {
					if (needsUpdate) {
						eventsRecalculationAck(selectedShare, chartTarget.getSlidingStartDate(), notUptoDateStamps, minDate);	
					} 

					if (!chartTarget.getChartedEvtDef().equals(EventDefinition.ZERO)) {
						updateChartIndicator(selectedShare, recalculationGranted);
					}

					if (!chartTarget.getChartedEvtDefsTrends().isEmpty()) {		
						updateBarChart(selectedShare, chartTarget.getSlidingStartDate(), recalculationGranted, needsUpdate);
					}

				} catch (Exception e) {
					LOGGER.error(e,e);
				}

		} 

		if (chartTarget.getChartedEvtDef() != null && chartTarget.getChartedEvtDef().equals(EventDefinition.ZERO)) chartTarget.getMainChartWraper().resetIndicChart();
		if (chartTarget.getChartedEvtDefsTrends().isEmpty()) chartTarget.getMainChartWraper().resetBarChart();
		
		if (chartTarget.getChartedEvtDefsTrends().isEmpty() && (chartTarget.getChartedEvtDef() == null || chartTarget.getChartedEvtDef().equals(EventDefinition.ZERO))){
			UserDialog dialog = new UserDialog(chartTarget.getShell(), "No Indicator or Trend is selected.\n Use the buttons below the chart to select your indicators and trends.", null);
			dialog.open();
		}
		
	}
	
	private void eventsRecalculationAck(final Stock selectedShare, Date slidingStartDate, HashMap<EventInfo, UpdateStamp> notUptoDateStamps, Calendar minDate) {
			
		chartTarget.getHightlitedEventModel().setViewStateParams(selectedShare);
		final EventRefreshController refreshHighlitedAnalysisController = refreshHighlightedAnalysisController();
	
		if (eventRecalculationAck == null && refreshHighlitedAnalysisController.isValidTask(TaskId.Analysis, slidingStartDate)) {
			
			String msg = "Analysis are not up to date for "+selectedShare.getName()+", the selected time frame and the requested trends.";
			String click = "Click to update calculations";
			if (minDate.after(new Date(0))) msg = msg +"\nMinimun calculation date reached for this stock : "+new SimpleDateFormat("MMM dd yyyy").format(minDate);
			for (EventInfo eventInfo : notUptoDateStamps.keySet()) {
				msg = msg + "\n'" + eventInfo.getEventReadableDef() + "' may be a candidate for update"; 
			}
		
			ActionDialogAction action  = new ActionDialogAction() {
	
				@Override
				public void action(Control targetControl) {
					refreshHighlitedAnalysisController.widgetSelected(null);
				}
				
			};
			
			eventRecalculationAck = new ActionDialog(chartTarget.getDisplay().getActiveShell(), SWT.NULL, "Warning", msg, null, click, action);
			eventRecalculationAck.open();
			eventRecalculationAck = null;
			
		}
		
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
		chartTarget.getMainChartWraper().updateLineDataSet(chartTarget.getCurrentTabShareList(), chartTarget.getStripedCloseFunction(), getIsApplyColor());
		
	}
	
	private void updateBarChart(final Stock selectedShare, Date exentedStartDate, boolean recalculationGranted, Boolean needsUpdate) {

		SymbolEvents eventsForStock = EventsResources.getInstance().crudReadEventsForStock(selectedShare, exentedStartDate, chartTarget.getSlidingEndDate(), true, chartTarget.getChartedEvtDefsTrends(), IndicatorCalculationServiceMain.UI_ANALYSIS);

		final Set<EventInfo> noDataTrends = new HashSet<EventInfo>();
		
		//No events found
		if (eventsForStock == null || eventsForStock.getDataResultMap().isEmpty()) {
		
			//No events found despite recalc
			if (!recalculationGranted) {
				chartTarget.getMainChartWraper().resetBarChart();	
			} else {
				noDataTrends.addAll(chartTarget.getChartedEvtDefsTrends());
			}
			
		//That's all or partially good, we display
		} else {
	
			final Map<EventInfo, TuningResDTO>  tuningRess = new HashMap<EventInfo, TuningResDTO>();
			
			ExecutorService executor = Executors.newFixedThreadPool(50);
			
			final Map<String, Config> configs = ConfigThreadLocal.getAll();
			for (final EventInfo eventDefinition :  chartTarget.getChartedEvtDefsTrends()) {
				
				Runnable runnable = new Runnable() {
					public void run() {
	
						ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME,configs.get(Config.EVENT_SIGNAL_NAME));
						ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME,configs.get(Config.INDICATOR_PARAMS_NAME));
						
						TuningResDTO tuningResCache = null;
						try {
							tuningResCache = chartTarget.getHightlitedEventModel().updateTuningRes(selectedShare, eventDefinition, chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate());
						} catch (Exception e) {
							LOGGER.error(e,e);
						}
						if (tuningResCache != null) {
							tuningRess.put(eventDefinition, tuningResCache);
						} else {
							noDataTrends.add(eventDefinition);
						}
					}
				};
				executor.execute(runnable);
				
			}
			
			executor.shutdown();
			try {
				executor.awaitTermination(5, TimeUnit.MINUTES);
			} catch (InterruptedException e) {
				LOGGER.error(e,e);
			}
			
			SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barsData = ChartBarUtils.buildBarsData(
					selectedShare, chartTarget.getChartedEvtDefsTrends(), 
					chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate(), eventsForStock, tuningRess, 
					barChartSettings);
			
			chartTarget.getMainChartWraper().updateBarDataSet(barsData, chartTarget.getHighligtedId(), barChartSettings);
		
		}
		
		//Missing bars
		if (!needsUpdate && !noDataTrends.isEmpty()) {
			String chartedEvtStr = EventDefinition.getEventDefSetAsString(", ",noDataTrends);
			UserDialog dialog = new UserDialog(chartTarget.getShell(),  //"Not all analysis are available to refresh the Trends Bar Chart for this period and "+selectedShare.getFriendlyName()+".\n" +
			"No events are available for : "+chartedEvtStr+".\nWithin the period you have selected "+selectedShare.getFriendlyName()+".\n" +
			"If you just cleared the calculation results, you may want to Update calculations.", 
					"This may also happen if calculations failed or if there is not enough quotations for the period.\n" +
					"Check the indicators in "+TRENDBUTTXT+" as well as the date boundaries against the available quotations.\n" +
					"If this is an edited indicator you can also check the formula of : "+chartedEvtStr);
			dialog.open();
		}
	}

	private void updateChartIndicator(final Stock selectedShare, boolean recalculationGranted) {
		
		SortedMap<Date, double[]> outputCache = chartTarget.getHightlitedEventModel().getOutputCache(selectedShare, chartTarget.getChartedEvtDef());
	
		if (outputCache == null || outputCache.isEmpty()) {
			
			//No indic found despite recalc
			if (!recalculationGranted) {
			
				chartTarget.getHightlitedEventModel().setViewStateParams(selectedShare);
				final EventRefreshController refreshHighlitedAnalysisController = refreshHighlightedAnalysisController();
				if (refreshHighlitedAnalysisController.isValidTask(TaskId.Analysis, chartTarget.getSlidingStartDate())) {
					
					chartTarget.getMainChartWraper().resetIndicChart();
					ActionDialog actionDialog = new ActionDialog(chartTarget.getShell(),  SWT.NULL, 
							"Warning", 
							"No events are available within the period you have selected share "+selectedShare.getFriendlyName()+" and "+chartTarget.getChartedEvtDef().getEventReadableDef()+".\n" +
							"If you just cleared the calculations results, you may want to Update calculations.", 
							"This may also happen if calculations failed, or if there is not enough quotations for the period.\n" +
							"Check the indicators in "+TRENDBUTTXT+" as well as the date boundaries against the available quotations.\n" +
							"If this is an edited indicator you can also check the formula of : "+chartTarget.getChartedEvtDef().getEventReadableDef(),
							"Update calculations Now?", new ActionDialogAction() {
								@Override
								public void action(Control targetControl) {
									refreshHighlitedAnalysisController.widgetSelected(null);
								}
							});
					actionDialog.open();
					
				}
				
			}
		
		//Thats all good, we display	
		} else {
			
			SortedMap<Date, double[]> subMap = new TreeMap<Date, double[]>();
			if (!outputCache.isEmpty()) {

				Calendar instance = Calendar.getInstance();
				instance.setTime(chartTarget.getSlidingEndDate());
				QuotationsFactories.getFactory().incrementDate(instance, 1);
				Date endPlus1 = instance.getTime();

				if (endPlus1.after(outputCache.lastKey())) {
					subMap = outputCache.tailMap(this.chartTarget.getSlidingStartDate());
				} else {
					subMap = outputCache.subMap(this.chartTarget.getSlidingStartDate(), endPlus1);
				}
				
				if (!subMap.isEmpty()) chartTarget.getMainChartWraper().updateIndicDataSet(chartTarget.getChartedEvtDef(), subMap);
				
			}
			
//			try {
			
//			} catch (NoSuchElementException e) {
//				UserDialog dialog = new UserDialog(chartTarget.getShell(), "I can't refresh Indicator Chart for "+chartTarget.getChartedEvtDef().getEventDefinitionRef()+".\n If you cleared the calculation, try Refresh calculations.", 
//						"Because I could not find calculation results for : "+chartTarget.getChartedEvtDef().getEventDefinitionRef()+". Please Refresh calculations or choose an other indicator.");
//				dialog.open();
//			}
		}
		
	}



	@Override
	public void initRefreshAction() {
	}



	@Override
	public void endRefreshAction(List<Exception> exceptions) {
	}

	@Override
	public void populatePopups(Composite popusGroup) {
		
		cleanPopupButtonsGroup(popusGroup);
		
		{
			evtDefsChartingBut = new Button(popusGroup, SWT.PUSH);
			evtDefsChartingBut.setFont(MainGui.DEFAULTFONT);
			evtDefsChartingBut.setText(INDICATORSBUTTXT);
			evtDefsChartingBut.setToolTipText("This is to setup the display of indicators historical data at the bottom of the chart.\nYou must select a share in the portfolio to display its analysis.");
		
			evtDefsChartingBut.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {		
					handleEvent();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handleEvent();
				}

				private void handleEvent() {
					initEvtChartingPopup();
				}

			});

		}
		{
			evtDefsTrendChartingBut = new Button(popusGroup, SWT.PUSH);
			evtDefsTrendChartingBut.setFont(MainGui.DEFAULTFONT);
			evtDefsTrendChartingBut.setText(TRENDBUTTXT);
			evtDefsTrendChartingBut.setToolTipText("This is to setup the display of gathered indicators trends.\nYou must select a share in the portfolio to display its analysis.");
			
			evtDefsTrendChartingBut.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {		
					handleEvent();
				}
				

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handleEvent();
				}

				private void handleEvent() {
					initEvtDefTrendPopup();
				}

			});

		}
		{
			Button barSettings = new Button(popusGroup, SWT.NONE);
			barSettings.setFont(MainGui.DEFAULTFONT);
			barSettings.setText("Trend settings ...");
			barSettings.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {	
					handle();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {	
					handle();
				}

				private void handle() {
					
					ActionDialogAction  action =  new ActionDialogAction() {
						
						private UserDialog noStockSelectedWarningDialog;

						@Override
						public void action(Control targetControl) {

							Object[] viewStateParams = chartTarget.getHightlitedEventModel().getViewStateParams();
							if (viewStateParams != null && viewStateParams.length == 1) {
								highLight(chartTarget.getHighligtedId(), (Stock) viewStateParams[0], true);
							} else {
								if (noStockSelectedWarningDialog == null && (chartTarget.getChartedEvtDefsTrends() != null && !chartTarget.getChartedEvtDefsTrends().isEmpty())) {
									noStockSelectedWarningDialog = new UserDialog(chartTarget.getShell(), "You must select a share in the portfolio to display its analysis.", null);
									noStockSelectedWarningDialog.open();
								}
							}
						}
					};
					
					if (barSettingsDialog == null || barSettingsDialog.getShell().isDisposed()) {
						barSettingsDialog = new BarSettingsDialog(chartTarget, barChartSettings, action);
						Rectangle parentBounds = chartTarget.getDisplay().map(chartTarget, null, chartTarget.getBounds());
						barChartSettings = barSettingsDialog.open(new Point(parentBounds.x + parentBounds.width, parentBounds.y));
					} else {
						barSettingsDialog.getShell().setVisible(true);
						barSettingsDialog.getShell().setActive();
						barSettingsDialog.getShell().setFocus();
					}

				}
			});
			
		}
		{
			Button recalc =  new Button(popusGroup, SWT.PUSH);
			recalc.setFont(MainGui.DEFAULTFONT);
			recalc.setText("Update calculations");
			recalc.addSelectionListener(refreshHighlightedAnalysisController());
			
		}
		
		popusGroup.layout();
		
	}



	protected EventRefreshController refreshHighlightedAnalysisController() {
		
		return new EventRefreshController(chartTarget.getHightlitedEventModel(), (RefreshableView)chartTarget.getParent().getParent(), ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
			
			@Override
			public void widgetSelected(SelectionEvent evt) {
				LOGGER.guiInfo("Cleaning and Recalculating. Thanks for waiting ...");
				//updateEventRefreshModelState(Boolean dofetchQuotes, Boolean doAnalyse, Boolean doAnalysisClean) 
				this.updateEventRefreshModelState(0l, TaskId.FetchQuotations, TaskId.Clean, TaskId.Analysis);
				((RefreshableView)chartTarget.getParent().getParent()).initRefreshAction();
				super.widgetSelected(evt);
			}
		
		};
	
	}


	@Override
	public void resetChart() {
		
		for (SlidingPortfolioShare sShare : chartTarget.getCurrentTabShareList()) {
			Object[] viewStateParams = chartTarget.getHightlitedEventModel().getViewStateParams();
			if (viewStateParams != null && viewStateParams.length != 0 && ((Stock)viewStateParams[0]).equals(sShare.getStock()))  {
				sShare.setDisplayOnChart(true);
			} else {
				sShare.setDisplayOnChart(false);
			}
		}
		chartTarget.setStripedCloseFunction(new StripedCloseRealPrice());
		
		chartTarget.getMainChartWraper().resetLineChart();
		chartTarget.getMainChartWraper().resetBarChart();
		chartTarget.getMainChartWraper().resetIndicChart();
		
	}
	
	@Override
	public void lightResetChart() {
		resetChart();
		
	}



	@Override
	public Boolean getIsApplyColor() {
		return false;
	}



	@Override
	public int retreivePreviousSelection() {
		int cpt = 0;
		for (SlidingPortfolioShare sShare : chartTarget.getCurrentTabShareList()) {
			if (sShare.getDisplayOnChart()){
				//chartTarget.getHightlitedEventModel().setViewStateParams(sShare.getStock());
				//chartTarget.setHighligtedId(cpt);
				//highLight(chartTarget.getHighligtedId(), (Stock) chartTarget.getHightlitedEventModel().getViewStateParams()[0], true);
				//break;
				return cpt;
			}
			cpt ++;
		}
		return -1;
		
	}


	@Override
	public void exportBarChartPng() {
		
		try {
			
			//File name
			String filePath = System.getProperty("installdir");
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
				public void action(Control targetControl) {
					actionDialog.values[0] = exportPngFileName.getText();
				}
			};
			actionDialog.setControl(exportPngFileName);
			actionDialog.setAction(actionDialogAction);
			actionDialog.open();
			
			//Export
			chartTarget.getMainChartWraper().exportChart(filePath+File.separator+actionDialog.values[0]+".png");
		
			
		} catch (Exception e) {
			LOGGER.warn(e,e);
		}
	}


	@Override
	public void refreshView(List<Exception> exceptions) {
		
		if (evtDefChartingPopupMenu != null && !evtDefChartingPopupMenu.getSelectionShell().isDisposed()) {
			initEvtChartingPopup();
		}
		
		if (evtDefTrendPopupMenu!= null && !evtDefTrendPopupMenu.getSelectionShell().isDisposed()) {
			initEvtDefTrendPopup();
		}
		
	}
	
	private void initEvtDefTrendPopup() {
		
		Set<EventInfo> availEventDefs = EventDefinition.loadMaxPassPrefsEventInfo();
		ActionDialogAction action = new ActionDialogAction() {

			@Override
			public void action(Control targetControl) {

				Object[] viewStateParams = chartTarget.getHightlitedEventModel().getViewStateParams();
				if (viewStateParams != null && viewStateParams.length == 1) {
					highLight(chartTarget.getHighligtedId(), (Stock) viewStateParams[0], true);
				} else {
					if (chartTarget.getChartedEvtDefsTrends() != null && !chartTarget.getChartedEvtDefsTrends().isEmpty()) {
						UserDialog dialog = new UserDialog(chartTarget.getShell(), "You must select a share in the portfolio to display its analysis.", null);
						dialog.open();
					}
				}

			}
		};
		
		if (evtDefTrendPopupMenu == null || evtDefTrendPopupMenu.getSelectionShell().isDisposed()) {
	
			evtDefTrendPopupMenu = new PopupMenu<EventInfo>(chartTarget, evtDefsTrendChartingBut, availEventDefs, chartTarget.getChartedEvtDefsTrends(), true, SWT.CHECK, action);
			Rectangle parentBounds = chartTarget.getDisplay().map(chartTarget, null, chartTarget.getBounds());
			evtDefTrendPopupMenu.open(new Point(parentBounds.x + parentBounds.width, parentBounds.y + parentBounds.height/2), true);

		} else {
			
			evtDefTrendPopupMenu.updateAction(action, availEventDefs, chartTarget.getChartedEvtDefsTrends());
			evtDefTrendPopupMenu.getSelectionShell().setVisible(true);
			evtDefTrendPopupMenu.getSelectionShell().setActive();
			evtDefTrendPopupMenu.getSelectionShell().setFocus();
			
		}

	}


	private void initEvtChartingPopup() {

		Set<EventInfo> availEventDefs = new HashSet<EventInfo>(EventDefinition.loadMaxPassPrefsEventInfo());
		availEventDefs.add(EventDefinition.ZERO);
		final Set<EventInfo> chartedEvtDefTmpSet = new HashSet<EventInfo>();
		chartedEvtDefTmpSet.add(chartTarget.getChartedEvtDef());

		ActionDialogAction action = new ActionDialogAction() {

			@Override
			public void action(Control targetControl) {

				Object[] viewStateParams = chartTarget.getHightlitedEventModel().getViewStateParams();
				if (!chartedEvtDefTmpSet.isEmpty()) {
					//changing evtdef selection
					chartTarget.setChartedEvtDef(chartedEvtDefTmpSet.iterator().next());
					//if stock selected, we update
					boolean stockSelected = viewStateParams != null && viewStateParams.length == 1;
					if (stockSelected) { 
						highLight(chartTarget.getHighligtedId(), (Stock) viewStateParams[0], true);
					} else {
						//warning only if evtdef selected and no stock
						if (chartTarget.getChartedEvtDef() != null && !chartTarget.getChartedEvtDef().equals(EventDefinition.ZERO)) {
							UserDialog dialog = new UserDialog(chartTarget.getShell(), "You must select a share in the portfolio to display its analysis.", null);
							dialog.open();
						}
					}
				}
			}
		};
		
		if (evtDefChartingPopupMenu == null || evtDefChartingPopupMenu.getSelectionShell().isDisposed()) {

			evtDefChartingPopupMenu = new PopupMenu<EventInfo>(chartTarget, evtDefsChartingBut, availEventDefs, chartedEvtDefTmpSet, false, SWT.RADIO, action);
			Rectangle parentBounds = chartTarget.getDisplay().map(chartTarget, null, chartTarget.getBounds());
			evtDefChartingPopupMenu.open(new Point(parentBounds.x + parentBounds.width, parentBounds.y + parentBounds.height/2), false);
			
		} else {

			evtDefChartingPopupMenu.updateAction(action, availEventDefs, chartedEvtDefTmpSet);
			evtDefChartingPopupMenu.getSelectionShell().setVisible(true);
			evtDefChartingPopupMenu.getSelectionShell().setActive();
			evtDefChartingPopupMenu.getSelectionShell().setFocus();

		}

	}


	@Override
	public void shutDownDisplay() {
		if (evtDefChartingPopupMenu != null) evtDefChartingPopupMenu.getSelectionShell().dispose();
		if (evtDefTrendPopupMenu != null) evtDefTrendPopupMenu.getSelectionShell().dispose();
	}


	@Override
	public void updateButtonsToolTips() {
		//Nothing
	}


}
