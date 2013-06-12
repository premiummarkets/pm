package com.finance.pms.portfolio.gui.charts;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.ActionDialog;
import com.finance.pms.ActionDialogAction;
import com.finance.pms.UserDialog;
import com.finance.pms.IndicatorCalculationServiceMain;
import com.finance.pms.MainGui;
import com.finance.pms.MainPMScmd;
import com.finance.pms.PopupMenu;
import com.finance.pms.RefreshableView;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.EventRefreshController;
import com.finance.pms.datasources.db.StripedCloseRealQuote;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.chartUtils.ChartBarUtils;
import com.finance.pms.events.scoring.chartUtils.DataSetBarDescr;
import com.finance.pms.events.scoring.dto.TuningResDTO;
import com.finance.pms.portfolio.gui.ActionDialogForm;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;
import com.finance.pms.threads.ConfigThreadLocal;

public class ChartIndicatorDisplay extends ChartDisplayStrategy {
	

	private static final String TRENDBUTTXT = "Chart Trends ...";
	private static final String INDICATORSBUTTXT = "Chart Indicators ...";

	private static MyLogger LOGGER = MyLogger.getLogger(ChartIndicatorDisplay.class);
	
	private Stock calculating;
	private Double alphaDividend;
	private Boolean spinnerSelection;
	

	private PopupMenu<EventInfo> evtDefTrendPopupMenu;
	private PopupMenu<EventInfo> evtDefChartingPopupMenu;
	private Button evtDefsTrendChartingBut;
	private Button evtDefsChartingBut;

	

	public ChartIndicatorDisplay(ChartsComposite chartTarget) {
		super();
		
		//TODO recalculate only selected indicators?
		this.alphaDividend = MainPMScmd.getPrefs().getDouble("chart.alphaDividend", 2);
		this.spinnerSelection = false;
		
		this.chartTarget = chartTarget;
		this.calculating = null;
		populatePopups(chartTarget.getPopusGroup());
		this.chartTarget.getMainChartWraper().initMainPlot(
				ChartMain.NUMBER_FORMAT, 
				"Nothing to display?\n" +
				"Select a stock in your portfolio and " +
				"Use '"+TRENDBUTTXT+"' and/or '"+INDICATORSBUTTXT+"' buttons to select indicators.\n" +
				"Also check that the portfolio stocks and sliding date ranges are valid. There may not be quotations available for this operation.");
		
	}


	@Override
	public void highLight(Integer idx, Stock selectedShare, boolean recalculationGranted) {

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

		if (!chartTarget.getChartedEvtDefsTrends().isEmpty() || !chartTarget.getChartedEvtDef().equals(EventDefinition.ZERO)) {//Some thing has to be displayed

			if (calculating != null && calculating.equals(selectedShare)) {//This stock is already running a calculation

				UserDialog dialog = new UserDialog(chartTarget.getShell(), SWT.NONE, "Still calculating indicators for "+selectedShare.getFriendlyName()+".\nThanks for waiting.", null);
				dialog.open();

			} else {//We try and run

				try {

					Calendar slidingStartCal = Calendar.getInstance();
					slidingStartCal.setTime(chartTarget.getSlidingStartDate());
					QuotationsFactories.getFactory().incrementDateExtraLarge(slidingStartCal, -1);

					Boolean needsUpdate = chartTarget.getHightlitedEventModel().cacheNeedsUpdateCheck(selectedShare, slidingStartCal.getTime(), chartTarget.getSlidingEndDate());

					if (needsUpdate && recalculationGranted) {
						eventsRecalculationAck(selectedShare);	
					} 
					//else {//No recalc needed

					if (!chartTarget.getChartedEvtDef().equals(EventDefinition.ZERO)) {
						updateChartIndicator(selectedShare, recalculationGranted);
					}

					if (!chartTarget.getChartedEvtDefsTrends().isEmpty()) {		
						updateBarChart(selectedShare, slidingStartCal.getTime(), recalculationGranted, needsUpdate);
					}

					//}


				} catch (Exception e) {
					LOGGER.error(e,e);
				}
			}

		} 

		if (chartTarget.getChartedEvtDef() != null && chartTarget.getChartedEvtDef().equals(EventDefinition.ZERO)) chartTarget.getMainChartWraper().resetIndicChart();
		if (chartTarget.getChartedEvtDefsTrends().isEmpty()) chartTarget.getMainChartWraper().resetBarChart();
		
		if (chartTarget.getChartedEvtDefsTrends().isEmpty() && (chartTarget.getChartedEvtDef() == null || chartTarget.getChartedEvtDef().equals(EventDefinition.ZERO))){
			UserDialog dialog = new UserDialog(chartTarget.getShell(), SWT.NONE, "No Indicator or Trend is selected.\n Use the buttons below the chart to select your indicators and trends.", null);
			dialog.open();
		}
		
		//chartTarget.getMainChartWraper().getMainYAxis().setLabel(selectedShare.getFriendlyName());
		chartTarget.getMainChartWraper().setMainYAxisLabel(selectedShare.getFriendlyName());
		chartTarget.getMainChartWraper().highLightSerie(chartTarget.getHighligtedId(), 1);

	}
	
	private void eventsRecalculationAck(final Stock selectedShare) {
		
		String msg = "All analysis are not up to date for "+selectedShare.getName()+" and the selected time frame.";
		String click = "Click to update calculations";
		ActionDialogAction action  = new ActionDialogAction() {

			@Override
			public void action(Button targetButton) {

				chartTarget.getHightlitedEventModel().setViewStateParams(selectedShare);
				EventRefreshController refreshHighlitedAnalysisController = refreshHighlitedAnalysisController();
				refreshHighlitedAnalysisController.widgetSelected(null);
			}
			
		};
		ActionDialog dialog = new ActionDialog(chartTarget.getShell(), SWT.NULL, "Warning", msg, null, click, action);
		dialog.open();
		
	}
	
	private void hideAllButSelected(final Stock selectedShare) {
		
		for (SlidingPortfolioShare sShare : chartTarget.getListShares()) {
			if (!sShare.getStock().equals(selectedShare)) {
				sShare.setDisplayOnChart(false);
			} else {
				sShare.setDisplayOnChart(true);
			}
		}
		chartTarget.getStripedCloseFunction().updateStartDate(chartTarget.getSlidingStartDate());
		chartTarget.getStripedCloseFunction().updateEndDate(chartTarget.getSlidingEndDate());
		chartTarget.getMainChartWraper().updateLineDataSet(chartTarget.getListShares(), chartTarget.getStripedCloseFunction(), getIsApplyColor());
		
	}
	
	private void updateBarChart(Stock selectedShare, Date exentedStartDate, boolean recalculationGranted, Boolean needsUpdate) {

		SymbolEvents eventsForStock = EventsResources.getInstance().crudReadEventsForStock(selectedShare, exentedStartDate, chartTarget.getSlidingEndDate(), true, chartTarget.getChartedEvtDefsTrends(), IndicatorCalculationServiceMain.UI_ANALYSIS);

		Set<EventInfo> failingTrendAnalysis = new HashSet<EventInfo>();
		
		//No indic found
		if (eventsForStock == null || eventsForStock.getDataResultMap().isEmpty()) {
		
			//No indic found despite recalc
			if (!recalculationGranted) {
				chartTarget.getMainChartWraper().resetBarChart();	
			} else {
				failingTrendAnalysis.addAll(chartTarget.getChartedEvtDefsTrends());
			}
			
		//That's all or partially good, we display
		} else {
	
			Map<EventInfo, TuningResDTO>  tuningRess = new HashMap<EventInfo, TuningResDTO>();
			for (EventInfo eventDefinition :  chartTarget.getChartedEvtDefsTrends()) {
				TuningResDTO tuningResCache = chartTarget.getHightlitedEventModel().updateTuningRes(selectedShare, eventDefinition, chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate());
				if (tuningResCache != null) {
					tuningRess.put(eventDefinition, tuningResCache);
				} else {
					failingTrendAnalysis.add(eventDefinition);
				}
			}
			
			//TODO UI setting
			int maxFill = ((EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getPerceptronTrainingPMEventOccLowerSpan();
			//int maxFill = 0;
			Boolean isZerobased = true;
			
			SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barsData = ChartBarUtils.buildBarsData(
					alphaDividend, selectedShare, 
					chartTarget.getChartedEvtDefsTrends(),
					chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate(), eventsForStock, tuningRess, 
					maxFill, isZerobased);
			
			chartTarget.getMainChartWraper().updateBarDataSet(barsData, chartTarget.getHighligtedId());
		
		}
		
		//Missing bars
		if (!needsUpdate && !failingTrendAnalysis.isEmpty()) {
			String chartedEvtStr = EventDefinition.getEventDefSetAsString(", ",failingTrendAnalysis);
			UserDialog dialog = new UserDialog(chartTarget.getShell(),  SWT.NULL, 
					"Not all analysis are available to refresh the Trends Bar Chart for this period and "+selectedShare.getFriendlyName()+".\n" +
					"If you just cleared the calculation, try Refresh calculations.", 
					"Missing Trends : "+chartedEvtStr+".\n" +
					"This may happen if the calculation has been cleared, has failed, is not granted or has not enough quotations for the period.\n" +
					"Also check the indicators in Charted Trends as well as the date boundaries.");
			dialog.open();
		}
	}

	private void updateChartIndicator(final Stock selectedShare, boolean recalculationGranted) {
		
		SortedMap<Date, double[]> outputCache = chartTarget.getHightlitedEventModel().getOutputCache(selectedShare, chartTarget.getChartedEvtDef());
	
		if (outputCache == null) {
			
			//No indic found despite recalc
			if (!recalculationGranted) {
			
				chartTarget.getMainChartWraper().resetIndicChart();
				ActionDialog actionDialog = new ActionDialog(chartTarget.getShell(),  SWT.NULL, 
						"Warning", "No indicator data for this period and "+selectedShare.getFriendlyName()+" and "+chartTarget.getChartedEvtDef().getEventReadableDef()+".\n" +
						"If you cleared the calculation, try Refresh calculations.", 
						"This may happen if the calculation has been cleared, has failed, is not granted or has not enough quotations for the period.\n" +
						"Also check the date boundaries as well as the indicators displayed.",
						"Update calculations Now?", new ActionDialogAction() {
							@Override
							public void action(Button targetButton) {
								chartTarget.getHightlitedEventModel().setViewStateParams(selectedShare);
								EventRefreshController refreshHighlitedAnalysisController = refreshHighlitedAnalysisController();
								refreshHighlitedAnalysisController.widgetSelected(null);
							}
						});
				actionDialog.open();
				
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
				
			}
			
			try {
				chartTarget.getMainChartWraper().updateIndicDataSet(chartTarget.getChartedEvtDef(), subMap);
			} catch (NoSuchElementException e) {
				UserDialog dialog = new UserDialog(chartTarget.getShell(), SWT.NONE, 
						"I can't refresh Indicator Chart for "+chartTarget.getChartedEvtDef().getEventDefinitionRef()+".\n If you cleared the calculation, try Refresh calculations.",
						"Because I could not find calculation results for : "+chartTarget.getChartedEvtDef().getEventDefinitionRef()+". Please Refresh calculations or choose an other indicator.");
				dialog.open();
			}
		}
		
	}



	@Override
	public void initRefreshAction() {
		synchronized (chartTarget.getLogComposite()) {
			calculating = (Stock) chartTarget.getHightlitedEventModel().getViewStateParams()[0];
		}
	}



	@Override
	public void endRefreshAction(List<Exception> exceptions) {
	
		synchronized (chartTarget.getLogComposite()) {
			calculating = null;
		}
	}

	@Override
	public void populatePopups(Composite popusGroup) {
		
		cleanPopupsGroup(popusGroup);
		
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
					
					if (evtDefChartingPopupMenu == null || evtDefChartingPopupMenu.getSelectionShell().isDisposed()) {
						
						initEvtChartingPopup(null);
						
					} else {
						
						evtDefChartingPopupMenu.getSelectionShell().setVisible(true);
						evtDefChartingPopupMenu.getSelectionShell().setFocus();
						
					}
					
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
					

					if (evtDefTrendPopupMenu == null || evtDefTrendPopupMenu.getSelectionShell().isDisposed()) {

						initEvtDefTrendPopup(null);

					} else {
						
						evtDefTrendPopupMenu.getSelectionShell().setVisible(true);
						evtDefTrendPopupMenu.getSelectionShell().setFocus();
						
					}
					
				}

			});

		}
		{
			Button recalc =  new Button(popusGroup, SWT.PUSH);
			recalc.setFont(MainGui.DEFAULTFONT);
			recalc.setText("Update calculations");
			recalc.addSelectionListener(refreshHighlitedAnalysisController());
			
		}
		{
			final Spinner alphaSpinner = new Spinner(popusGroup, SWT.WRAP|SWT.READ_ONLY);
			alphaSpinner.setFont(MainGui.DEFAULTFONT);
			int digits = 1;
			alphaSpinner.setDigits(digits);
			alphaSpinner.setMinimum(5);
			alphaSpinner.setMaximum(EventDefinition.loadMaxPassPrefsEventInfo().size()*10);
			alphaSpinner.setIncrement(5);
			alphaSpinner.setSelection((int)(alphaDividend*Math.pow(10, digits)));
			alphaSpinner.setToolTipText(
					"For a better visibility of the result, You can change the colours alpha of the charted trend.\n" +
					"You must select a share in the portfolio to display its analysis\n" +
					"You also must choose the trends you want to display using the '"+TRENDBUTTXT+"' button.");
			alphaSpinner.addSelectionListener(new SelectionAdapter() {
			
				public void widgetSelected(SelectionEvent e) {
					int selection = alphaSpinner.getSelection();
					int digits = alphaSpinner.getDigits();
					alphaDividend = selection / Math.pow(10, digits);
					synchronized (spinnerSelection) {
						spinnerSelection = true;
					}
				}
			});
			alphaSpinner.addListener(SWT.MouseExit, new Listener() {

				public void handleEvent(Event arg0) {
					synchronized (spinnerSelection) {
						if (spinnerSelection) {
							spinnerSelection = false;
							
							try {
								MainPMScmd.getPrefs().putDouble("chart.alphaDividend", alphaDividend);
								MainPMScmd.getPrefs().flush();
							} catch (Exception e) {
								LOGGER.error(e, e);
							}

							Object[] viewStateParams = chartTarget.getHightlitedEventModel().getViewStateParams();
							if (viewStateParams != null && viewStateParams.length == 1) {
								highLight(chartTarget.getHighligtedId(), (Stock) viewStateParams[0], true);
							} else {
								if ((chartTarget.getChartedEvtDefsTrends() != null && !chartTarget.getChartedEvtDefsTrends().isEmpty())) {
									UserDialog dialog = new UserDialog(chartTarget.getShell(), SWT.NONE, "You must select a share in the portfolio to display its analysis.", null);
									dialog.open();
								}
							}
							
						}
					}
				}

			});
		}
		
		popusGroup.layout();
		
	}



	protected EventRefreshController refreshHighlitedAnalysisController() {
		
		return new EventRefreshController(chartTarget.getHightlitedEventModel(), (RefreshableView)chartTarget.getParent().getParent(), ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
			
			@Override
			public void widgetSelected(SelectionEvent evt) {
				LOGGER.guiInfo("Cleaning and Recalculating. Thanks for waiting ...");
				this.updateEventRefreshModelState(false,true,true,false, true, false, 0l);
				((RefreshableView)chartTarget.getParent().getParent()).initRefreshAction();
				super.widgetSelected(evt);
			}
		
		};
	
	}


	@Override
	public void resetChart() {
		
		for (SlidingPortfolioShare sShare : chartTarget.getListShares()) {
			Object[] viewStateParams = chartTarget.getHightlitedEventModel().getViewStateParams();
			if (viewStateParams != null && viewStateParams.length != 0 && ((Stock)viewStateParams[0]).equals(sShare.getStock()))  {
				sShare.setDisplayOnChart(true);
			} else {
				sShare.setDisplayOnChart(false);
			}
		}
		chartTarget.setStripedCloseFunction(new StripedCloseRealQuote());
		chartTarget.updateCharts(chartTarget.getListShares(), false, false);
		
	}



	@Override
	public Boolean getIsApplyColor() {
		return false;
	}



	@Override
	public void highLighPrevious() {
		int cpt = 0;
		for (SlidingPortfolioShare sShare : chartTarget.getListShares()) {
			if (sShare.getDisplayOnChart()){
				chartTarget.getHightlitedEventModel().setViewStateParams(sShare.getStock());
				chartTarget.setHighligtedId(cpt);
				highLight(chartTarget.getHighligtedId(), (Stock) chartTarget.getHightlitedEventModel().getViewStateParams()[0], true);
				break;
			}
			cpt ++;
		}
		
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
				public void action(Button targetButton) {
					actionDialog.name = ((Text) actionDialog.control).getText();
				}
			};
			actionDialog.setControl(exportPngFileName);
			actionDialog.setAction(actionDialogAction);
			actionDialog.open();
			
			//Export
			chartTarget.getMainChartWraper().exportChart(filePath+File.separator+actionDialog.name+".png");
		
			
		} catch (Exception e) {
			LOGGER.warn(e,e);
		}
	}


	@Override
	public void refreshView(List<Exception> exceptions) {
		
		if (evtDefChartingPopupMenu != null && !evtDefChartingPopupMenu.getSelectionShell().isDisposed()) {
			Point position = evtDefChartingPopupMenu.getSelectionShell().getLocation();
			evtDefChartingPopupMenu.getSelectionShell().dispose();
			initEvtChartingPopup(position);
		}
		
		if (evtDefTrendPopupMenu!= null && !evtDefTrendPopupMenu.getSelectionShell().isDisposed()) {
			Point position = evtDefTrendPopupMenu.getSelectionShell().getLocation();
			evtDefTrendPopupMenu.getSelectionShell().dispose();
			initEvtDefTrendPopup(position);
		}
		
	}
	
	private void initEvtDefTrendPopup(final Point location) {
		
		Runnable runnable = new Runnable() {
			
			public void run() {
				
				Set<EventInfo> availEventDefs = EventDefinition.loadMaxPassPrefsEventInfo();
				ActionDialogAction action = new ActionDialogAction() {

					@Override
					public void action(Button targetButton) {

						Object[] viewStateParams = chartTarget.getHightlitedEventModel().getViewStateParams();
						if (viewStateParams != null && viewStateParams.length == 1) {
							highLight(chartTarget.getHighligtedId(), (Stock) viewStateParams[0], true);
						} else {
							if (chartTarget.getChartedEvtDefsTrends() != null && !chartTarget.getChartedEvtDefsTrends().isEmpty()) {
								UserDialog dialog = new UserDialog(chartTarget.getShell(), SWT.NONE,
										"You must select a share in the portfolio to display its analysis.", null);
								dialog.open();
							}
						}

					}
				};
				
				evtDefTrendPopupMenu = new PopupMenu<EventInfo>(chartTarget, evtDefsTrendChartingBut, availEventDefs, chartTarget.getChartedEvtDefsTrends(), true, SWT.CHECK, action);
				evtDefTrendPopupMenu.open((location != null)?location:new Point(chartTarget.getLocation().x + chartTarget.getSize().x, chartTarget.getLocation().y));
			}
			
		};
		
		chartTarget.getDisplay().asyncExec(runnable);
	
	}


	private void initEvtChartingPopup(final Point location) {
		
		Runnable runnable = new Runnable() {
			
			public void run() {
				
				Set<EventInfo> availEventDefs = new HashSet<EventInfo>(EventDefinition.loadMaxPassPrefsEventInfo());
				availEventDefs.add(EventDefinition.ZERO);
				final Set<EventInfo> chartedEvtDefTmpSet = new HashSet<EventInfo>();
				chartedEvtDefTmpSet.add(chartTarget.getChartedEvtDef());
				
				ActionDialogAction action = new ActionDialogAction() {

					@Override
					public void action(Button targetButton) {

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
									UserDialog dialog = new UserDialog(chartTarget.getShell(), SWT.NONE, "You must select a share in the portfolio to display its analysis.", null);
									dialog.open();
								}
							}
						}

					}
				};
				evtDefChartingPopupMenu = new PopupMenu<EventInfo>(chartTarget, evtDefsChartingBut, availEventDefs, chartedEvtDefTmpSet, false, SWT.RADIO, action);
				evtDefChartingPopupMenu.open((location != null)?location:new Point(chartTarget.getLocation().x + chartTarget.getSize().x, chartTarget.getLocation().y));
			}
		};
		
		chartTarget.getDisplay().asyncExec(runnable);
	}

}
