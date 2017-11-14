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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
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

    private Button evtDefsTrendChartingBut;
    private Button evtDefsChartingBut;

    private PopupMenu<EventInfo> evtDefTrendPopupMenu;
    private PopupMenu<EventInfo> evtDefChartingPopupMenu;

    private BarSettings barChartSettings;
    private BarSettingsDialog barSettingsDialog;

    private PopupMenu<OutputDescr> chartSettingsPopup;

    private Button indicSettings;

    private Map<EventInfo, TuningResDTO> tuningRessCache = new HashMap<>();

    public ChartIndicatorDisplay(ChartsComposite chartTarget) {
        super();

        this.barChartSettings = new BarSettings();

        this.chartTarget = chartTarget;
        populatePopups(chartTarget.getPopusGroup());
        this.chartTarget.getMainChartWraper().initMainPlot(
                ChartMain.NUMBER_FORMAT, 
                "Nothing to display?\n" +
                        "Select a stock in your portfolio and " +
                        "Use '"+TRENDBUTTXT+"' and/or '"+INDICATORSBUTTXT+"' buttons to select your calculator(s).\n" +
                "Also check the portfolio stocks and sliding date ranges. There may be no quotations available.");

        this.chartTarget.setStripedCloseFunction(this, new StripedCloseRealPrice());

        resetChart(true);

    }


    @Override
    public void highLight(Integer idx, Stock selectedShare, Boolean recalculationGranted) {

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

            if (!chartTarget.getChartedEvtDefsTrends().isEmpty()) {
                chartTarget.getMainChartWraper().resetBarChart();
            } 
            if (!chartTarget.getChartedEvtDef().equals(EventDefinition.ZERO)) {
                chartTarget.getMainChartWraper().resetIndicChart();
            }

            hideAllButSelected(selectedShare);

            chartTarget.getMainChartWraper().setMainYAxisLabel(selectedShare.getFriendlyName());
            chartTarget.getMainChartWraper().highLightSerie(idx, 1);

            if (!chartTarget.getChartedEvtDefsTrends().isEmpty() || !chartTarget.getChartedEvtDef().equals(EventDefinition.ZERO)) {//Some thing has to be displayed

                //We try and run
                try {
                    Set<EventInfo> allEvtInfos = chartTarget.initChartedEvtDefsTrendsSet();
                    allEvtInfos.addAll(chartTarget.getChartedEvtDefsTrends());
                    if (!chartTarget.getChartedEvtDef().equals(EventDefinition.ZERO)) allEvtInfos.add(chartTarget.getChartedEvtDef());

                    HashSet<EventInfo> notUpToDateEventInfos = new HashSet<EventInfo>();
                    Calendar minDate = Calendar.getInstance();
                    minDate.setTime(new Date(0));
                    Boolean needsUpdate = 
                            chartTarget.getHightlitedEventModel().cacheNeedsUpdateCheck(
                                    selectedShare, chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate(), 
                                    notUpToDateEventInfos, minDate, allEvtInfos.toArray(new EventInfo[0]));

                    if (needsUpdate) {
                        eventsRecalculationAck(selectedShare, chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate(), notUpToDateEventInfos, minDate);	
                    } 

                    if (!chartTarget.getChartedEvtDef().equals(EventDefinition.ZERO)) {
                        updateChartIndicator(selectedShare, recalculationGranted, needsUpdate);
                    } else {
                        if (chartSettingsPopup != null && !chartSettingsPopup.getSelectionShell().isDisposed()) {
                            chartSettingsPopup.getSelectionShell().dispose();
                        }
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
                String errorMessage ="No Calculator or Trend is selected.\n Use the buttons below the chart to select a calculator and trends.";
                showPopupDialog(errorMessage, "Ok", null, null);
            }

        } finally {

            this.chartTarget.getShell().setEnabled(true);
            this.chartTarget.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));

        }

    }


    private void eventsRecalculationAck(final Stock selectedShare, Date slidingStartDate, Date slidingEndDate, final HashSet<EventInfo> notUpToDateEI, Calendar minDate) {

        final RefreshableView parentView = (RefreshableView) chartTarget.getParent().getParent();
        final EventRefreshController ctrller = new EventRefreshController(chartTarget.getHightlitedEventModel(), parentView, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {

            @Override
            public void widgetSelected(SelectionEvent evt) {

                LOGGER.guiInfo("Cleaning and Recalculating. Thanks for waiting ...");
                EventTaskQueue.getSingleton().invalidateTasksCreationDates(TaskId.Analysis);
                this.updateEventRefreshModelState(0l, TaskId.FetchQuotations, TaskId.Clean, TaskId.Analysis);
                super.widgetSelected(evt);
            }

        };

        @SuppressWarnings("unchecked")
        boolean isValidTask = ctrller.isValidTask(TaskId.Analysis, selectedShare, new HashSet[]{notUpToDateEI}, slidingStartDate, slidingEndDate);

        if (isValidTask) {

            chartTarget.getHightlitedEventModel().setViewParamRoot(selectedShare);
            chartTarget.getHightlitedEventModel().setViewParam(0, notUpToDateEI);

            String msg = "Analysis are not up to date for "+selectedShare.getName()+", the selected time frame and the requested trends.";
            String click = "Click to update calculations";
            if (minDate.after(new Date(0))) msg = msg +"\nMinimun calculation date reached for this stock : "+new SimpleDateFormat("MMM dd yyyy").format(minDate);
            for (EventInfo eventInfo : notUpToDateEI) {
                if (chartTarget.getChartedEvtDefsTrends().contains(eventInfo) || chartTarget.getChartedEvtDef().equals(eventInfo)) {
                    msg = msg + "\n'" + eventInfo.getEventReadableDef() + "' may be a candidate for update"; 
                }
            }
            ActionDialogAction action  = new ActionDialogAction() {

                @Override
                public void action() {		
                    ctrller.widgetSelected(null);
                }

            };

            showPopupDialog(msg, click, null, action);

        } else {
            //			hidePopupDialog();
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
        chartTarget.getMainChartWraper().updateLineDataSet(chartTarget.getCurrentTabShareList(), chartTarget.getStripedCloseFunction(), getIsApplyColor(), chartTarget.getPlotChartDimensions());

    }

    private void updateBarChart(final Stock selectedShare, Date exentedStartDate, final Boolean recalculationGranted, final Boolean needsUpdate) {

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
                            if (needsUpdate || tuningRessCache == null || tuningRessCache.get(eventDefinition) == null) {
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
                                        ChartBarUtils.buildBarsData(selectedShare, chartTarget.getChartedEvtDefsTrends(), chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate(), ses, tuningRessCache, barChartSettings);
                                chartTarget.getMainChartWraper().updateBarDataSet(barsData, chartTarget.getHighligtedId(), barChartSettings, chartTarget.getPlotChartDimensions());
                            } catch (Exception e) {
                                LOGGER.error(
                                        "arg : "+ arg +
                                        ", chartTarget.getMainChartWraper() : "+chartTarget.getMainChartWraper()+
                                        ", chartTarget.getHighligtedId() : "+chartTarget.getHighligtedId()+
                                        ", barChartSettings : "+barChartSettings+
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
                if (!needsUpdate && !noDataTrends.isEmpty()) {
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

    private void updateChartIndicator(final Stock selectedShare, boolean recalculationGranted, boolean needsUpdate) {

        SortedMap<Date, double[]> outputCache = chartTarget.getHightlitedEventModel().getOutputCache(selectedShare, chartTarget.getChartedEvtDef());

        if (outputCache == null || outputCache.isEmpty()) {

            //No indic found despite recalc
            if (!recalculationGranted && !needsUpdate) {
                String errMsg = 
                        "No output data are available for display within the period you have selected share "+selectedShare.getFriendlyName()+" and "+chartTarget.getChartedEvtDef().getEventReadableDef()+".\n" +
                                "If you just cleared the calculations results, you may want to Force and Update calculations.";
                String addMsg = 
                        "This may also happen if calculations failed, or if there is not enough quotations for the period.\n" +
                                //"Check the calculators in "+TRENDBUTTXT+" as well as the date boundaries against the available quotations.\n" +
                                "You may want to check the date boundaries against the available quotations.\n" +
                                "Also note that some calculators need full OLHC and Volume in order to be calculated.\n"+
                                "If '"+chartTarget.getChartedEvtDef().getEventReadableDef()+"' is a calculator created by you, you may also want to check the formula.";
                showPopupDialog(errMsg, "Ok", addMsg, null);
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

                if (!subMap.isEmpty()) chartTarget.getMainChartWraper().updateIndicDataSet(chartTarget.getChartedEvtDef(), subMap, chartTarget.getPlotChartDimensions());

            }

            if (chartSettingsPopup!= null && !chartSettingsPopup.getSelectionShell().isDisposed()) {
                initChartSettingsPopup(false);
            }

        }

    }

    @Override
    public void initRefreshAction() {
        //Nothing
    }

    @Override
    public void endRefreshAction(List<Exception> exceptions) {
        //Nothing
    }

    @Override
    public void populatePopups(Composite popusGroup) {

        cleanPopupButtonsGroup(popusGroup);

        {
            evtDefsChartingBut = new Button(popusGroup, SWT.PUSH);
            evtDefsChartingBut.setFont(MainGui.DEFAULTFONT);
            evtDefsChartingBut.setText(INDICATORSBUTTXT);
            evtDefsChartingBut.setToolTipText("This is to setup the display of calculators historical data at the bottom of the chart.\nYou must select a share in the portfolio to display its analysis.");

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
                    initEvtChartingPopup(true);
                }

            });

        }
        {
            indicSettings = new Button(popusGroup, SWT.NONE);
            indicSettings.setFont(MainGui.DEFAULTFONT);
            indicSettings.setText("Calculator settings ...");
            indicSettings.setToolTipText(
                    "Only user defined calculators can be customised.\n" +
                            "You must select one of your user defined calculators in '"+INDICATORSBUTTXT + "'\n" +
                            "And wait for its calculation to finish before changing the display settings.\n" +
                    "New calculators can be defined using the menu Events -> Customise and create calculators ...");
            indicSettings.addSelectionListener(new SelectionListener() {

                @Override
                public void widgetSelected(SelectionEvent e) {	
                    handle();
                }

                @Override
                public void widgetDefaultSelected(SelectionEvent e) {	
                    handle();
                }

                private void handle() {
                    initChartSettingsPopup(true);
                }

            });

        }
        {
            evtDefsTrendChartingBut = new Button(popusGroup, SWT.PUSH);
            evtDefsTrendChartingBut.setFont(MainGui.DEFAULTFONT);
            evtDefsTrendChartingBut.setText(TRENDBUTTXT);
            evtDefsTrendChartingBut.setToolTipText("This is to setup the display of gathered calculators trends.\nYou must select a share in the portfolio to display its analysis.");

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
                    initEvtDefTrendPopup(true);
                }

            });

        }
        {
            Button barSettings = new Button(popusGroup, SWT.NONE);
            barSettings.setFont(MainGui.DEFAULTFONT);
            barSettings.setText("Trends settings ...");
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

                        @Override
                        public void action() {

                            Stock viewStateParams = chartTarget.getHightlitedEventModel().getViewParamRoot();
                            if (viewStateParams != null) {
                                highLight(chartTarget.getHighligtedId(), viewStateParams, true);
                            } else {
                                if  (chartTarget.getChartedEvtDefsTrends() != null && !chartTarget.getChartedEvtDefsTrends().isEmpty()) {
                                    String errorMessage = "You must select a share in the portfolio to display its analysis.";
                                    showPopupDialog(errorMessage, "Ok", null, null);
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
            recalc.setText("Force&&Update Calculations");
            recalc.addSelectionListener(new EventRefreshController(chartTarget.getHightlitedEventModel(), (RefreshableView)chartTarget.getParent().getParent(), ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {

                @Override
                public void widgetSelected(SelectionEvent evt) {
                    LOGGER.guiInfo("Cleaning and Recalculating. Thanks for waiting ...");

                    EventTaskQueue.getSingleton().invalidateTasksCreationDates(TaskId.Analysis);

                    Set<EventInfo> allSelectedEventInfos = chartTarget.getChartedEvtDefsTrends();
                    if (!chartTarget.getChartedEvtDef().equals(EventDefinition.ZERO)) allSelectedEventInfos.add(chartTarget.getChartedEvtDef());

                    if (allSelectedEventInfos != null && !allSelectedEventInfos.isEmpty() && chartTarget.getHightlitedEventModel().getViewParamRoot() != null) {
                        chartTarget.getHightlitedEventModel().setViewParam(0, allSelectedEventInfos);
                        chartTarget.getHightlitedEventModel().setViewParam(1, Arrays.asList("setDirty"));
                    }

                    this.updateEventRefreshModelState(0l, TaskId.FetchQuotations, TaskId.Clean, TaskId.Analysis);
                    super.widgetSelected(evt);
                }

            });
        }

        popusGroup.layout();

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

        if (evtDefChartingPopupMenu != null && !evtDefChartingPopupMenu.getSelectionShell().isDisposed()) {
            initEvtChartingPopup(false);
        }

        if (evtDefTrendPopupMenu!= null && !evtDefTrendPopupMenu.getSelectionShell().isDisposed()) {
            initEvtDefTrendPopup(false);
        }		
    }

    private void initEvtDefTrendPopup(Boolean activate) {

        Set<EventInfo> availEventDefs = EventDefinition.loadMaxPassPrefsEventInfo();
        ActionDialogAction disactivateAction = new ActionDialogAction() {

            @Override
            public void action() {

                Stock viewStateParams = chartTarget.getHightlitedEventModel().getViewParamRoot();
                if (viewStateParams != null ) {
                    highLight(chartTarget.getHighligtedId(), viewStateParams, true);
                } else {
                    if (chartTarget.getChartedEvtDefsTrends() != null && !chartTarget.getChartedEvtDefsTrends().isEmpty()) {
                        String errorMessage = "You must select a share in the portfolio to display its analysis.";
                        showPopupDialog(errorMessage, "Ok", null, null);
                    }
                }

            }
        };

        if (evtDefTrendPopupMenu == null || evtDefTrendPopupMenu.getSelectionShell().isDisposed()) {

            evtDefTrendPopupMenu = new PopupMenu<EventInfo>(chartTarget, evtDefsTrendChartingBut, availEventDefs, chartTarget.getChartedEvtDefsTrends(), false, true, SWT.CHECK, null, disactivateAction, true);
            Rectangle parentBounds = chartTarget.getDisplay().map(chartTarget, null, chartTarget.getBounds());
            evtDefTrendPopupMenu.open(new Point(parentBounds.x + parentBounds.width, parentBounds.y + parentBounds.height/2), true);

        } else {

            evtDefTrendPopupMenu.updateAction(availEventDefs, chartTarget.getChartedEvtDefsTrends(), null, disactivateAction, true);
            evtDefTrendPopupMenu.getSelectionShell().setVisible(true);
            if (activate) {
                evtDefTrendPopupMenu.getSelectionShell().setActive();
                evtDefTrendPopupMenu.getSelectionShell().setFocus();
            }

        }

    }


    private void initEvtChartingPopup(Boolean activatePopup) {

        Set<EventInfo> availEventDefs = new HashSet<EventInfo>(EventDefinition.loadMaxPassPrefsEventInfo());
        availEventDefs.add(EventDefinition.ZERO);
        final Set<EventInfo> chartedEvtDefTmpSet = new HashSet<EventInfo>();
        chartedEvtDefTmpSet.add(chartTarget.getChartedEvtDef());

        ActionDialogAction action = new ActionDialogAction() {

            @Override
            public void action() {

                Stock viewStateParams = chartTarget.getHightlitedEventModel().getViewParamRoot();
                if (!chartedEvtDefTmpSet.isEmpty()) {
                    //changing evtdef selection
                    chartTarget.setChartedEvtDef(chartedEvtDefTmpSet.iterator().next());
                    //if stock selected, we update
                    boolean stockSelected = viewStateParams != null;
                    if (stockSelected) { 
                        highLight(chartTarget.getHighligtedId(), viewStateParams, true);
                    } else {
                        //warning only if evtdef selected and no stock
                        if (chartTarget.getChartedEvtDef() != null && !chartTarget.getChartedEvtDef().equals(EventDefinition.ZERO)) {
                            String errorMessage = "You must select a share in the portfolio to display its analysis.";
                            showPopupDialog(errorMessage, "Ok", null, null);
                        }
                    }
                }
            }
        };

        if (evtDefChartingPopupMenu == null || evtDefChartingPopupMenu.getSelectionShell().isDisposed()) {

            evtDefChartingPopupMenu = new PopupMenu<EventInfo>(chartTarget, evtDefsChartingBut, availEventDefs, chartedEvtDefTmpSet, false, false, SWT.RADIO, action);
            Rectangle parentBounds = chartTarget.getDisplay().map(chartTarget, null, chartTarget.getBounds());
            evtDefChartingPopupMenu.open(new Point(parentBounds.x + parentBounds.width, parentBounds.y + parentBounds.height/2), false);

        } else {

            evtDefChartingPopupMenu.updateAction(availEventDefs, chartedEvtDefTmpSet, action, null, null);
            evtDefChartingPopupMenu.getSelectionShell().setVisible(true);
            if (activatePopup) {
                evtDefChartingPopupMenu.getSelectionShell().setActive();
                evtDefChartingPopupMenu.getSelectionShell().setFocus();
            }

        }

    }

    private void initChartSettingsPopup(Boolean activatePopup) {

        try {

            boolean isIndicatorSelected = chartTarget.getChartedEvtDef() != null && !chartTarget.getChartedEvtDef().equals(EventDefinition.ZERO);
            boolean isEmptyOutputIndicator = false;
            boolean isClearInProgress = false;
            String errorMessage = 
                    "Only user defined calculators can be customised.\n" +
                            "You must select one of your user defined calculators in '"+INDICATORSBUTTXT + "'\n" +
                            "And wait for its calculation to finish before changing the display settings.\n" +
                            "New calculators can be defined using the menu Events -> Customise and create calculators ...";


            if ( isIndicatorSelected ) {

                final Set<OutputDescr> availableOutputs = new TreeSet<OutputDescr>();
                final Set<OutputDescr> displayedOutputs = new TreeSet<OutputDescr>();

                try {
                    availableOutputs.addAll(chartTarget.getChartedEvtDef().getEventDefDescriptor().allOutputs());
                    displayedOutputs.addAll(chartTarget.getChartedEvtDef().getEventDefDescriptor().displayedOutputs());
                } catch (NoSuchElementException e) {
                    //errorMessage = e.toString() + "\n\n" +errorMessage;
                    isClearInProgress = true;
                    LOGGER.warn(e);
                } 

                if (!availableOutputs.isEmpty()) {

                    ActionDialogAction  disactivateAction =  new ActionDialogAction() {

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
                            highLight(chartTarget.getHighligtedId(), viewStateParams, true);
                        }
                    };

                    if (chartSettingsPopup == null || chartSettingsPopup.getSelectionShell().isDisposed()) {
                        chartSettingsPopup = new PopupMenu<OutputDescr>(chartTarget, indicSettings, availableOutputs, displayedOutputs, false, true, SWT.CHECK, null, disactivateAction, true);
                        Rectangle parentBounds = chartTarget.getDisplay().map(chartTarget, null, chartTarget.getBounds());
                        chartSettingsPopup.open(new Point(parentBounds.x + parentBounds.width, parentBounds.y), false);
                    } else {
                        chartSettingsPopup.updateAction(availableOutputs, displayedOutputs, null, disactivateAction, true);
                        chartSettingsPopup.getSelectionShell().setVisible(true);
                        if (activatePopup) {
                            chartSettingsPopup.getSelectionShell().setActive();
                            chartSettingsPopup.getSelectionShell().setFocus();
                        }
                    }

                } else {

                    if (chartSettingsPopup!= null && !chartSettingsPopup.getSelectionShell().isDisposed()) {
                        chartSettingsPopup.getSelectionShell().dispose();
                    }
                    isEmptyOutputIndicator = true;

                }

            }

            if (!isIndicatorSelected || (!isClearInProgress && isEmptyOutputIndicator)) {
                showPopupDialog("No calculator selected or No settings available for the selected calculator.", "Ok", errorMessage, null);	
            }

        } catch (Exception e) {
            LOGGER.warn(e,e);
            showPopupDialog(e.getMessage(), "Ok", null, null);
        }
    } 


    @Override
    public void shutDownDisplay() {

        //		for (SlidingPortfolioShare slidingPortfolioShare : chartTarget.getCurrentTabShareList()) {
        //			slidingPortfolioShare.setDisplayOnChart(true);
        //		}

        if (evtDefChartingPopupMenu != null) evtDefChartingPopupMenu.getSelectionShell().dispose();
        if (evtDefTrendPopupMenu != null) evtDefTrendPopupMenu.getSelectionShell().dispose();

        chartTarget.getMainChartWraper().resetLineChart();
        chartTarget.getMainChartWraper().resetBarChart();
        chartTarget.getMainChartWraper().resetIndicChart();

    }


    @Override
    public void updateButtonsToolTips() {
        //Nothing
    }


}
