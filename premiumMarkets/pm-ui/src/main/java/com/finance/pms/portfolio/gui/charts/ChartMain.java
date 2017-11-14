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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.swing.ToolTipManager;
import javax.swing.UIManager;

import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.Layer;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventType;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.EventDefDescriptor;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.QuotationUnit.ORIGIN;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.chartUtils.BarChart;
import com.finance.pms.events.scoring.chartUtils.BarSettings;
import com.finance.pms.events.scoring.chartUtils.DataSetBarDescr;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;
import com.tictactec.ta.lib.MInteger;

/**
 * The Class Charts.
 * 
 * @author Guillaume Thoreton
 */
public class ChartMain extends Chart {

    private static MyLogger LOGGER = MyLogger.getLogger(ChartMain.class);

    public static final DateFormat DATE_FORMAT = DateFormat.getTimeInstance();
    public static final DecimalFormat PERCENTAGE_FORMAT = new DecimalFormat("#0.00 %");
    public static final NumberFormat NUMBER_FORMAT = new DecimalFormat("#0.00");
    private static final Integer CHARTS_TOTAL_WEIGHT = 100;

    private BarChartDisplayStrategy barChartDisplayStrategy; 

    private JFreeChartTimePeriod jFreeTimePeriod;
    private JFreeChart jFreeChart;

    private DateAxis xAxis;
    private NumberAxis mainYAxis;
    private XYPlot mainPlot;

    private XYPlot indicPlot;
    private Integer indicPlotWeight = CHARTS_TOTAL_WEIGHT/2;

    private Map<Long, XYTextAnnotation> lineAnnotations;
    private ValueMarker axisMarker;

    public ChartMain(Date startDate, JFreeChartTimePeriod jFreeTimePeriod) {
        super();

        this.jFreeTimePeriod = jFreeTimePeriod;
        UIManager.put("ToolTip.background", new java.awt.Color(239, 203, 152, 255));
        UIManager.put("ToolTip.foreground", java.awt.Color.BLACK);

        //ToolTipManager.sharedInstance().setInitialDelay(100);
        ToolTipManager.sharedInstance().setInitialDelay(20);
        //ToolTipManager.sharedInstance().setReshowDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(120000);

        XYBarRenderer.setDefaultShadowsVisible(false);
        XYBarRenderer.setDefaultBarPainter(new StandardXYBarPainter());

        barChartDisplayStrategy = new ChartBarSquare(this);

        lineAnnotations = new HashMap<Long, XYTextAnnotation>();

    }

    private XYDataset buildLineDataSet(final StripedCloseFunction stripedCloseFunction, final List<SlidingPortfolioShare> portfolioShares, final Boolean applyColors) {

        final TimeSeriesCollection combinedDataset = new TimeSeriesCollection();

        //Check if empty
        Boolean isAnyShowing = false;
        for (SlidingPortfolioShare slidingPortfolioShare : portfolioShares) {
            isAnyShowing = isAnyShowing || slidingPortfolioShare.getDisplayOnChart();
        }
        if (!isAnyShowing) return combinedDataset;

        //Build lines
        for (int k = 0; k < portfolioShares.size(); k++) {

            SlidingPortfolioShare kthPs = portfolioShares.get(k);
            TimeSeries lineSerie;
            XYItemRenderer renderer = mainPlot.getRenderer();

            if (kthPs.getDisplayOnChart()) {

                try {

                    final Quotations quotations = getQuotations(stripedCloseFunction, kthPs);
                    lineSerie = buildLineSeries(stripedCloseFunction, quotations, kthPs);

                    Paint paint = java.awt.Color.BLACK;
                    if (applyColors) {
                        org.eclipse.swt.graphics.Color color = kthPs.getColor();
                        paint = new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue());
                    }

                    renderer.setSeriesPaint(k, paint);
                    renderer.setSeriesStroke(k, new BasicStroke(1));

                    //final int kf = k;
                    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy");
                    XYToolTipGenerator xyToolTpGen = new XYToolTipGenerator() {

                        public String generateToolTip(XYDataset dataset, int series, int item) {

                            String y = "NaN";
                            String x = "NaN";
                            QuotationUnit closeForDate;
                            try {
                                Date date = new Date((long) dataset.getXValue(series, item));
                                x = simpleDateFormat.format(date);
                                SlidingPortfolioShare slPShare = portfolioShares.get(series);

                                try {
                                    closeForDate = quotations.get(quotations.getClosestIndexBeforeOrAtDateOrIndexZero(0, date));
                                } catch (Exception e) {
                                    LOGGER.warn(e);
                                    closeForDate = new QuotationUnit(slPShare.getStock(), slPShare.getTransactionCurrency(), date, BigDecimal.ZERO,
                                            BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0l, ORIGIN.USER);
                                }

                                String variationAddInfo = "";
                                if (!stripedCloseFunction.lineToolTip().isEmpty()) {
                                    y = stripedCloseFunction.formatYValue(dataset.getYValue(series, item));
                                    variationAddInfo = "<br>Value : " + y + " (" + stripedCloseFunction.lineToolTip() + ")";
                                }

                                String origin = (closeForDate.getOrigin().equals(ORIGIN.USER)) ? ORIGIN.USER.name().toLowerCase()
                                        : slPShare.getStock().getSymbolMarketQuotationProvider().getMarketQuotationProvider().getCmdParam();
                                String trCurrency = slPShare.getTransactionCurrency().name();
                                String stockCurrency = slPShare.getStock().getMarketValuation().getCurrency().name();

                                String string = "<html>" + "<font size='2'>" + "<b>" + slPShare.getFriendlyName() + "</b> On the " + x + "<br>" + "</font>"
                                        + "<table CELLSPACING=0 CELLPADDING=1>" + "<tr><td><font size='2'>Open</font></td><td><font size='2'>"
                                        + NUMBER_FORMAT.format(closeForDate.getOpen()) + "</font></td></tr>"
                                        + "<tr><td><font size='2'>High</font></td><td><font size='2'>" + NUMBER_FORMAT.format(closeForDate.getHigh())
                                        + "</font></td></tr>" + "<tr><td><font size='2'>Low</font></td><td><font size='2'>"
                                        + NUMBER_FORMAT.format(closeForDate.getLow()) + "</font></td></tr>"
                                        + "<tr><td><font size='2'>Close</font></td><td><font size='2'>" + NUMBER_FORMAT.format(closeForDate.getClose())
                                        + "</font></td></tr>" + "<tr><td><font size='2'>Volume&nbsp;</font></td><td><font size='2'>" + closeForDate.getVolume()
                                        + "</td></tr>" + "</table>" + "<font size='2'>" + "(Source : " + origin + ", Currency " + stockCurrency + " here in "
                                        + trCurrency + ")" + variationAddInfo + "</font>" + "</html>";
                                return string;

                            } catch (Exception e) {
                                LOGGER.error(e, e);
                            }
                            return "NaN";

                        }
                    };

                    renderer.setSeriesToolTipGenerator(k, xyToolTpGen);
                    renderer.setSeriesShape(k, new Rectangle(new Dimension(100, 100)));

                } catch (NoQuotationsException e) {

                    LOGGER.warn(kthPs + " has no quotation available and won't be displayed");
                    lineSerie = new TimeSeries(kthPs.getName());

                } catch (Exception e) {

                    LOGGER.warn(kthPs + " error building line series ", e);
                    lineSerie = new TimeSeries(kthPs.getName());

                } 
            } else {
                lineSerie = new TimeSeries(kthPs.getName());
            }

            Boolean displayOnChart = (lineSerie.getItemCount() == 0) ? false : kthPs.getDisplayOnChart();
            renderer.setSeriesVisible(k, displayOnChart);
            combinedDataset.addSeries(lineSerie);

        }

        return combinedDataset;
    }


    public JFreeChart initChart(final StripedCloseFunction stripedCloseFunction) {

        Runnable runnable = new Runnable() {

            @Override
            public void run() {

                Date arbitraryStartDate = stripedCloseFunction.getArbitraryStartDateForChart();
                Date arbitraryEndDate = stripedCloseFunction.getArbitraryEndDate();

                xAxis = new DateAxis();
                xAxis.setAutoRange(true);
                xAxis.setTimeline(tradingTimeLine());
                xAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, domainTicksMultiple(arbitraryStartDate, arbitraryEndDate)));
                xAxis.setMinorTickMarksVisible(true);
                if (jFreeTimePeriod.equals(JFreeChartTimePeriod.DAY)) xAxis.setDateFormatOverride(new SimpleDateFormat("dd MMM yy"));

                ///	Y axis
                //Line Y axis
                mainYAxis = new NumberAxis();	 
                mainYAxis.setAutoRange(true);
                mainYAxis.setAutoRangeIncludesZero(false);
                mainYAxis.setTickLabelFont(mainYAxis.getTickLabelFont().deriveFont(7f));
                mainYAxis.setLabelFont(mainYAxis.getLabelFont().deriveFont(10f));

                //Indicator Y axis (indicator is not showing at init)
                //indicYAxis = initYAxis();

                /// Plots
                //Lines
                XYLineAndShapeRenderer lineRenderer = new XYLineAndShapeRenderer(true, false);
                lineRenderer.setBaseItemLabelsVisible(true);	
                TimeSeriesCollection lineDataset = new TimeSeriesCollection();
                mainPlot = new XYPlot(lineDataset, null, null, lineRenderer);
                mainPlot.addRangeMarker(new ValueMarker(0));
                mainPlot.setRangeAxis(0, mainYAxis);
                mainPlot.setDomainMinorGridlinesVisible(true);

                //Bars
                TimeSeriesCollection barDataset = new TimeSeriesCollection();
                XYBarRenderer barRenderer = new XYBarRenderer();
                mainPlot.setRenderer(1, barRenderer);
                barChartDisplayStrategy.setBarYAxis();
                mainPlot.setDataset(1, barDataset);

                //Indicator plot
                //This is not created from start

                //Combine
                CombinedDomainXYPlot combinedDomainXYPlot = new CombinedDomainXYPlot(xAxis);
                combinedDomainXYPlot.add(mainPlot, CHARTS_TOTAL_WEIGHT);

                //Create
                ChartMain.this.jFreeChart = new JFreeChart(combinedDomainXYPlot);
                ChartMain.this.jFreeChart.removeLegend();

            }
        };

        try {
            EventQueue.invokeAndWait(runnable);
        } catch (Exception e) {
            LOGGER.error(e,e);
        }

        return this.jFreeChart;
    }

    protected NumberAxis initYAxis(Double centerValue, double lower, double upper) {

        NumberAxis indicYAxis = new NumberAxis();	

        if (centerValue.isNaN()) {
            indicYAxis.setAutoRange(true);
            indicYAxis.setAutoRangeStickyZero(false);
            indicYAxis.setAutoRangeIncludesZero(false);
        } else {
            double maxMedian = Math.max(centerValue-lower, upper-centerValue);
            indicYAxis.setRangeAboutValue(centerValue, maxMedian*2.1);
        }

        indicYAxis.setTickLabelFont(indicYAxis.getTickLabelFont().deriveFont(7f));
        indicYAxis.setLabelFont(indicYAxis.getLabelFont().deriveFont(10f));

        return indicYAxis;

    }


    public void updateBarDataSet(final SortedMap<DataSetBarDescr, SortedMap<Date, BarChart>> barSeries, final int lineSerieIdx, final BarSettings barSettings, final Rectangle2D plotArea) {

        Runnable runnable = new Runnable() {

            public void run() {

                try {

                    TimeSeriesCollection barDataSets = new TimeSeriesCollection();

                    XYBarRenderer renderer = (XYBarRenderer) mainPlot.getRenderer(1);
                    renderer.removeAnnotations();

                    renderer.setMargin(0);
                    renderer.setBarPainter(new XYBarPainter() {

                        @Override
                        public void paintBarShadow(Graphics2D g2, XYBarRenderer renderer, int row, int column, RectangularShape bar, RectangleEdge base, boolean pegShadow) {
                        }

                        @Override
                        public void paintBar(Graphics2D g2, XYBarRenderer renderer, int row, int column, RectangularShape bar, RectangleEdge base) {

                            //System.out.printf("%f %f %f %f %d %d %d\n", bar.getX(), bar.getY(), bar.getWidth(), bar.getHeight(), row/3, ((int) bar.getX()) % (barSeries.size()/3), row);
                            if ( barSettings.getSideBySide() && ((int) bar.getX()) % (barSeries.size()/3) != row/3 ) return;//TODO??

                            if (barSettings.getIsZeroBased()) {
                                bar.setFrame(bar.getX(), bar.getY(), bar.getWidth(), bar.getHeight());
                            } else {
                                int seriesIdx = (row/3) +1;
                                double barHeight = bar.getHeight()/seriesIdx ;
                                bar.setFrame(bar.getX(), bar.getY(), bar.getWidth(), barHeight);
                            }

                            g2.setColor((Color) renderer.getSeriesPaint(row));
                            g2.fill(bar);
                            g2.draw(bar);
                        }
                    });

                    TimeSeries lineSerie = ((TimeSeriesCollection) mainPlot.getDataset(0)).getSeries(lineSerieIdx);
                    double maxBarValue = barChartDisplayStrategy.maxBarValue(lineSerie);
                    int eventDefSerieIdx = 0;
                    final SimpleDateFormat df = new SimpleDateFormat("dd MMM yy");
                    final NumberFormat pf = new DecimalFormat("#0.00 %");
                    for (final DataSetBarDescr serieDef : barSeries.keySet()) {
                        SortedMap<Date, BarChart> barSerie = barSeries.get(serieDef);

                        TimeSeries barDataSet = barChartDisplayStrategy.buildBarTimeSeries(serieDef.getSerieName(), barSerie, lineSerie);
                        barDataSets.addSeries(barDataSet);

                        //Annotation
                        if (serieDef.isLabeled()) {
                            RegularTimePeriod annTP = lineSerie.getTimePeriod(Math.min(lineSerie.getItemCount()-1, 5));
                            Double annV = maxBarValue * eventDefSerieIdx / barSeries.size();
                            String stopLossString = (!MainPMScmd.getMyPrefs().get("indicator.stoplossratio","0").equals("0"))?" / "+ pf.format(serieDef.getStopLossProfit()):"";
                            XYTextAnnotation annotation = new XYTextAnnotation(serieDef.getEventDisplayeDef() + " (" + pf.format(serieDef.getFollowProfit()) + stopLossString + " / " + pf.format(serieDef.getStockPriceChange()) + ")", annTP.getFirstMillisecond(), annV);
                            annotation.setTextAnchor(TextAnchor.BASELINE_LEFT);
                            annotation.setToolTipText("<html>" + serieDef.getEventDisplayeDef() + "<br>" + serieDef.getTuningResStr() + "</html>");
                            annotation.setPaint(Color.BLUE);
                            Color transpWhite = new Color(1f, 1f, 1f, 0.5f);
                            annotation.setBackgroundPaint(transpWhite);
                            renderer.addAnnotation(annotation);
                        }

                        //ToolTip
                        XYToolTipGenerator xyToolTpGen = new XYToolTipGenerator() {

                            public String generateToolTip(XYDataset dataset, int series, int item) {

                                String x = "NaN";
                                EventType type = EventType.NONE;
                                String desrc = "Indeterministic simultaneous BULLISH and BEARISH";
                                try {

                                    Date date = new Date((long) dataset.getXValue(series, item));
                                    x = df.format(date);

                                    switch (serieDef.getId() % 3) {
                                    case 0:
                                        type = EventType.BULLISH;
                                        desrc = serieDef.getEventDefDescriptor().getHtmlBullishDescription();
                                        break;
                                    case 2:
                                        type = EventType.BEARISH;
                                        desrc = serieDef.getEventDefDescriptor().getHtmlBearishDescription();
                                        break;
                                    default:
                                        break;
                                    }

                                    return "<html>" + "<font size='2'>" + 
                                    "<b>" + serieDef.getStockDescr() + "</b><br>" +
                                    "<b>" + serieDef.getEventDisplayeDef()+ "</b> on the " + x + "<br>" +
                                    "Trend&nbsp;&nbsp;&nbsp;: " + type + "<br>" +
                                    "Descr&nbsp;&nbsp;&nbsp;: " + desrc + "<br>" +
                                    barSerie.get(date).getToolTip() + "<br>" +
                                    "</font>" + "</html>";

                                } catch (Exception e) {
                                    LOGGER.debug(e, e);
                                }
                                return "NaN";

                            }
                        };

                        renderer.setSeriesToolTipGenerator(eventDefSerieIdx, xyToolTpGen);
                        renderer.setSeriesItemLabelsVisible(eventDefSerieIdx, true);
                        renderer.setSeriesStroke(eventDefSerieIdx, new BasicStroke(serieDef.getSerieStrokeSize()));
                        renderer.setSeriesOutlinePaint(eventDefSerieIdx, serieDef.getSerieColor());
                        renderer.setSeriesPaint(eventDefSerieIdx, serieDef.getSerieColor());
                        renderer.setSeriesFillPaint(eventDefSerieIdx, serieDef.getSerieColor());

                        eventDefSerieIdx++;

                    }

                    mainPlot.setDataset(1, barDataSets);

                    resetVerticalLines(plotArea);

                } catch (Exception e) {
                    LOGGER.error(e,e);
                }
            }

        };

        try {
            EventQueue.invokeLater(runnable);
        } catch (Exception e) {
            LOGGER.error(e,e);
        }
    }

    public void updateLineDataSet(final List<SlidingPortfolioShare> listShares, final StripedCloseFunction stripedCloseFunction, final Boolean applyColors, final Rectangle2D plotArea) {

        Runnable runnable = new Runnable() {

            public void run() {

                XYDataset dataSet = buildLineDataSet(stripedCloseFunction, listShares, applyColors);
                Date arbitraryStartDate = stripedCloseFunction.getArbitraryStartDateForChart();
                Date arbitraryEndDate = stripedCloseFunction.getArbitraryEndDate();
                xAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, domainTicksMultiple(arbitraryStartDate, arbitraryEndDate)));
                xAxis.setRange(arbitraryStartDate, arbitraryEndDate);
                try {
                    mainPlot.setDataset(dataSet);

                    if (axisMarker != null ) {
                        mainPlot.removeRangeMarker(axisMarker);
                        axisMarker = null;
                    }
                    if (stripedCloseFunction.isRelative()) {
                        axisMarker = new ValueMarker(0, Color.BLACK, new BasicStroke(1f),Color.BLACK, new BasicStroke(1f), .5f);
                        mainPlot.addRangeMarker(axisMarker);
                    }

                } catch (IllegalArgumentException e) {
                    LOGGER.warn(e, e);
                    resetBarChart();
                    resetIndicChart();
                    updateLineDataSet(listShares, stripedCloseFunction, applyColors, plotArea);
                }

                resetVerticalLines(plotArea);
            }

        };

        EventQueue.invokeLater(runnable);
    }

    public void updateIndicDataSet(final EventInfo chartedEvtDef, final SortedMap<Date, double[]> serie, final Rectangle2D plotArea) {

        Runnable runnable = new Runnable() {

            public void run() {

                try {

                    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy");
                    final NumberFormat numberFormat = new DecimalFormat("0.############");
                    CombinedDomainXYPlot combinedDomainXYPlot = (CombinedDomainXYPlot) jFreeChart.getXYPlot();

                    //Check if indic plot present
                    @SuppressWarnings("unchecked") List<XYPlot> subplots = combinedDomainXYPlot.getSubplots();

                    if (subplots.size() == 1) {
                        XYLineAndShapeRenderer indicRenderer = new XYLineAndShapeRenderer(true, false);
                        indicRenderer.setBaseItemLabelsVisible(true, false); //??

                        indicPlot = new XYPlot(null, null, null, indicRenderer);
                        //indicPlot.addRangeMarker(0, new ValueMarker(0), Layer.FOREGROUND, false);
                        indicPlot.setNoDataMessage("No indicator output is available. Check that the stocks and date ranges are valid.");
                        indicPlot.setDomainMinorGridlinesVisible(true);
                    }

                    indicPlot.clearRangeAxes();

                    final EventDefDescriptor eventDefDescriptor = chartedEvtDef.getEventDefDescriptor();
                    String[] eventDefDescriptorArray = new String[0];
                    if (eventDefDescriptor != null) {
                        eventDefDescriptorArray = eventDefDescriptor.descriptionArray();
                    }

                    int nbOfGroupsAlreadyDisplayed = 0;
                    for (int groupIdx = 0; groupIdx < eventDefDescriptor.getGroupsCount(); groupIdx++) {

                        Boolean groupIsDisplayed = false;

                        //Renderer
                        XYItemRenderer renderer = indicPlot.getRenderer(groupIdx);
                        if (renderer == null) {
                            renderer = new XYLineAndShapeRenderer(true, false);
                            renderer.setBaseItemLabelsVisible(true, false);
                            indicPlot.setRenderer(groupIdx, renderer);
                        }

                        //Build data set adding data series and tool tips for group
                        TimeSeriesCollection dataset = new TimeSeriesCollection();
                        Integer[] outputIndexes = eventDefDescriptor.getOutputIndexesForGroup(groupIdx);
                        int serieIdx = 0;
                        double groupMaxY = Double.MIN_VALUE;
                        double groupMinY = Double.MAX_VALUE;
                        for (int k = 0; k < outputIndexes.length; k++) {

                            int outputIdx = outputIndexes[k];
                            if (eventDefDescriptor.isDisplayed(outputIdx)) {

                                groupIsDisplayed = true;

                                final String domain = eventDefDescriptorArray[outputIdx];
                                TimeSeries timeSerie = new TimeSeries(domain);
                                Boolean isNotNan = false;
                                for (Date date : serie.keySet()) {
                                    double[] ds = serie.get(date);
                                    RegularTimePeriod period = new Day(date);
                                    Number value = ds[outputIdx];
                                    isNotNan = isNotNan || (!((Double) value).isNaN() && !((Double) value).isInfinite());
                                    //Double.NEGATIVE_INFINITY act as a marker for data not available but line still have to be drawn.
                                    if (!((Double) value).isInfinite()) {
                                        TimeSeriesDataItem item = new TimeSeriesDataItem(period, value);
                                        timeSerie.add(item, false);
                                    }
                                }

                                if (isNotNan) {//Series has values to display

                                    groupMaxY = (timeSerie.getMaxY() > groupMaxY)?timeSerie.getMaxY():groupMaxY;
                                    groupMinY = (timeSerie.getMinY() < groupMinY)?timeSerie.getMinY():groupMinY;

                                    dataset.addSeries(timeSerie);

                                    renderer.setSeriesPaint(serieIdx, eventDefDescriptor.getColor(outputIdx));
                                    renderer.setSeriesShape(serieIdx, new Rectangle(new Dimension(100, 100)));

                                    XYToolTipGenerator xyToolTpGen = new XYToolTipGenerator() {

                                        public String generateToolTip(XYDataset dataset, int series, int item) {

                                            String y = "NaN";
                                            String x = "NaN";
                                            try {
                                                y = numberFormat.format(dataset.getYValue(series, item));
                                                Date date = new Date((long) dataset.getXValue(series, item));
                                                x = simpleDateFormat.format(date);
                                                return "<html>" + 
                                                "<font size='2'>" + 
                                                "<b>" + domain + "</b> on the " + x + "<br>"
                                                + ((eventDefDescriptor.displayValues()) ? "Value : " + y : "") +
                                                "</font>" + 
                                                "</html>";
                                            } catch (Exception e) {
                                                LOGGER.debug(e, e);
                                            }
                                            return "NaN";

                                        }
                                    };

                                    renderer.setSeriesToolTipGenerator(serieIdx, xyToolTpGen);
                                    serieIdx++;
                                }
                            }
                        }

                        //Y Axe for group
                        if (groupIsDisplayed) {

                            ValueAxis rangeAxis = indicPlot.getRangeAxis(groupIdx);
                            if (rangeAxis == null) {

                                double thresholdCenter = groupCenter(serie, eventDefDescriptor, groupIdx);
                                rangeAxis = initYAxis(thresholdCenter, groupMinY, groupMaxY);

                                if (nbOfGroupsAlreadyDisplayed == 0) {
                                    indicPlot.addRangeMarker(groupIdx, new ValueMarker(0), Layer.FOREGROUND, false);
                                    indicPlot.setRangeGridlinesVisible(true);
                                    AxisLocation location = AxisLocation.TOP_OR_LEFT;
                                    indicPlot.setRangeAxisLocation(groupIdx, location);
                                    rangeAxis.setLabel(chartedEvtDef.getEventReadableDef() + " : " + eventDefDescriptor.getMainLabelForGroup(groupIdx));
                                }
                                else if (nbOfGroupsAlreadyDisplayed <= 2) {
                                    AxisLocation location = AxisLocation.TOP_OR_RIGHT;
                                    indicPlot.setRangeAxisLocation(groupIdx, location);
                                    rangeAxis.setLabel(chartedEvtDef.getEventReadableDef() + " : " + eventDefDescriptor.getMainLabelForGroup(groupIdx));
                                } 
                                else {
                                    rangeAxis.setVisible(false);
                                }

                                indicPlot.setRangeAxis(groupIdx, rangeAxis, true);

                                nbOfGroupsAlreadyDisplayed++;
                            }

                            //Set group dateSet
                            indicPlot.setDataset(groupIdx, dataset);
                            if ( groupIdx != 0 ) indicPlot.mapDatasetToRangeAxis(groupIdx, groupIdx);

                        }

                    }

                    //Combine group
                    if (subplots.size() == 1) {
                        mainPlot.setWeight(CHARTS_TOTAL_WEIGHT - indicPlotWeight);
                        combinedDomainXYPlot.add(indicPlot, indicPlotWeight);
                    }

                    resetVerticalLines(plotArea);

                } catch (Exception e) {
                    LOGGER.warn("Can't refresh indicator chart : "+ e );
                }
            }

            private double groupCenter(SortedMap<Date, double[]> serie, EventDefDescriptor eventDefDescriptor, int groupIdx) {
                double thresholdCenter = Double.NaN;
                Integer[] thresholdsIdxs = eventDefDescriptor.getThresholdsIdx(groupIdx);
                if (thresholdsIdxs.length != 0) {
                    double[] thesholdValues = new double[thresholdsIdxs.length];
                    for (int j = 0; j < thresholdsIdxs.length; j++) {
                        double thresholdValue = serie.get(serie.firstKey())[thresholdsIdxs[j]];
                        thesholdValues[j] = thresholdValue;
                    }
                    Median median = new Median();
                    thresholdCenter = median.evaluate(thesholdValues);
                }
                return thresholdCenter;
            }

        };

        EventQueue.invokeLater(runnable);
    }

    private Quotations getQuotations(StripedCloseFunction stripedCloseFunction, PortfolioShare portfolioShare) throws NoQuotationsException {

        Date startDate = stripedCloseFunction.getArbitraryStartDateForCalculation();
        Date endDate = stripedCloseFunction.getArbitraryEndDate();
        Quotations bdQuotes;
        try {
            bdQuotes = QuotationsFactories.getFactory().getQuotationsInstance(portfolioShare.getStock(), startDate, endDate, true, portfolioShare.getTransactionCurrency(), 1, ValidityFilter.CLOSE);
        } catch (NoQuotationsException e) {
            throw e;
        }
        return bdQuotes;

    }

    private TimeSeries buildLineSeries(StripedCloseFunction stripedCloseFunction, Quotations bdQuotes, SlidingPortfolioShare portfolioShare) {

        TimeSeries timeSeries = new TimeSeries(portfolioShare.getName());

        if (bdQuotes.size() > 0 && portfolioShare.getStock().getLastQuote().after(stripedCloseFunction.getArbitraryStartDateForChart())) {

            MInteger startIdx = new MInteger();
            MInteger endIdx = new MInteger();
            Number[] relativeCloses = stripedCloseFunction.targetShareData(portfolioShare, bdQuotes, startIdx, endIdx);
            List<QuotationUnit> quotationUnits = bdQuotes.getQuotationUnits(startIdx.value, endIdx.value);

            for (int i = 0; i <= Math.min(relativeCloses.length-1, endIdx.value); i++) {
                QuotationUnit trade = quotationUnits.get( i + startIdx.value);
                RegularTimePeriod period = new Day(trade.getDate());
                Number value = relativeCloses[i].doubleValue();
                timeSeries.add(new TimeSeriesDataItem(period, value));
            }

        }  else {
            portfolioShare.setDisplayOnChart(false);
        }

        return timeSeries;

    }

    public void highLightSerie(final Integer serie, final float highlightStroke) {

        Runnable runnable = new Runnable() {

            public void run() {
                for (int i = 0; i < mainPlot.getSeriesCount(); i++) {
                    mainPlot.getRenderer().setSeriesStroke(i, new BasicStroke(1));
                }
                Stroke stroke = new BasicStroke(highlightStroke);
                mainPlot.getRenderer().setSeriesStroke(serie, stroke);
            }

        };

        EventQueue.invokeLater(runnable);
    }


    @SuppressWarnings("unused") //Test
    private void exportQuotations(String filename, ArrayList<OHLCDataItem> data) {

        File export = new File(filename);
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

        try {
            FileWriter fos = new FileWriter(export);
            fos.write("DATE,QUOTE\n");
            for (int i = 0; i < data.size(); i++) {
                fos.write(df.format(data.get(i).getDate()) + "," + data.get(i).getClose() + "\n");
            }
            fos.close();
        } catch (IOException e) {
            LOGGER.error("", e);
        }

    }

    public void resetLineChart() {
        Runnable runnable = new Runnable() {

            public void run() {
                mainPlot.setDataset(0, new TimeSeriesCollection());
            }
        };

        EventQueue.invokeLater(runnable);
    }

    public void resetBarChart() {

        Runnable runnable = new Runnable() {

            public void run() {
                mainPlot.setDataset(1, new TimeSeriesCollection());
                mainPlot.getRenderer(1).removeAnnotations();
            }
        };

        EventQueue.invokeLater(runnable);
    }

    public void resetIndicChart() {

        Runnable runnable = new Runnable() {

            public void run() {
                @SuppressWarnings("unchecked") List<XYPlot> subplots = ((CombinedDomainXYPlot) jFreeChart.getXYPlot()).getSubplots();
                if (indicPlot != null && subplots.size() == 2) {

                    indicPlotWeight = subplots.get(1).getWeight();

                    indicPlot.clearRangeAxes();
                    indicPlot.clearDomainAxes();
                    ((CombinedDomainXYPlot) jFreeChart.getXYPlot()).remove(indicPlot);
                    indicPlot = null;

                } 
            }

        };

        EventQueue.invokeLater(runnable);
    }

    protected XYPlot getMainPlot() {
        return mainPlot;
    }

    protected NumberAxis getMainYAxis() {
        return mainYAxis;
    }

    public void initMainPlot(final NumberFormat yAxisFormat, final String noDataMsg) {

        Runnable runnable = new Runnable() {

            public void run() {
                mainYAxis.setNumberFormatOverride(yAxisFormat);
                mainPlot.setNoDataMessage(noDataMsg);
            }

        };
        try {
            EventQueue.invokeAndWait(runnable);
        } catch (Exception e) {
            LOGGER.error(e,e);
        }
    }

    public void setMainYAxisLabel(final String label) {

        Runnable runnable = new Runnable() {

            public void run() {
                mainYAxis.setLabel(label);
            }

        };
        EventQueue.invokeLater(runnable);
    }

    public void exportChart(String path) throws FileNotFoundException, IOException {

        ChartUtilities.writeChartAsPNG(new FileOutputStream(new File(path)), this.jFreeChart, 700, 600);

    }

    public void slideChart(int increment, Rectangle2D plotarea) {

        CombinedDomainXYPlot combinedDomainXYPlot = (CombinedDomainXYPlot) jFreeChart.getXYPlot();
        @SuppressWarnings("unchecked") List<XYPlot> subplots = combinedDomainXYPlot.getSubplots();

        if (subplots.size() != 2) {
            return;
        } else {
            int upChartweight = subplots.get(0).getWeight();
            int newUpChartWeight = upChartweight-increment;
            if (newUpChartWeight <= 0 || newUpChartWeight >= CHARTS_TOTAL_WEIGHT) return;
            subplots.get(0).setWeight(newUpChartWeight);
            int lowChartweight = subplots.get(1).getWeight();
            int newLowChartWeight = lowChartweight+increment;
            if (newLowChartWeight <= 0 || newLowChartWeight >= CHARTS_TOTAL_WEIGHT) return;
            subplots.get(1).setWeight(newLowChartWeight);
            indicPlotWeight = newLowChartWeight;

            resetVerticalLines(plotarea);
        }

    }

    public Boolean isSlidingArea(double chartY, double mouseY) {

        CombinedDomainXYPlot combinedDomainXYPlot = (CombinedDomainXYPlot) jFreeChart.getXYPlot();
        @SuppressWarnings("unchecked") List<XYPlot> subplots = combinedDomainXYPlot.getSubplots();
        double xAxisDim = 20;

        if (subplots.size() != 2) {
            return false;
        } else {
            double mousePos = mouseY/(chartY-xAxisDim);
            double slidingArea = (double)subplots.get(0).getWeight()/(double)CHARTS_TOTAL_WEIGHT;
            if (slidingArea + .01 >= mousePos && mousePos >= slidingArea - .01) {
                return true;
            }
        }

        return false;
    }	

    public void addVLineAt(Point2D clickPoint, Rectangle2D plotArea) {

        ValueMarker vLineAt = checkVLineAt(clickPoint, plotArea);
        List<ValueMarker> hLineAt = checkHLineAt(clickPoint, plotArea);

        if (vLineAt == null) {

            if (hLineAt.size() > 0) {
                for (ValueMarker valueMarker : hLineAt) {
                    removeHLine(valueMarker);
                }
            }

            long chartX = point2DToTime(clickPoint, plotArea);
            ValueMarker vDomainMarker = new ValueMarker(chartX);
            vDomainMarker.setPaint(Color.ORANGE);
            vDomainMarker.setStroke(new BasicStroke(1.5f, BasicStroke.JOIN_MITER, BasicStroke.JOIN_MITER, 10.0f, new float[]{1.0f}, 0.0f)); //set the value new float[]{1.0f}as 1.0f to avoiding dashing of marker

            addVerticalLine(mainPlot, vDomainMarker);
            if (indicPlot != null) {
                addVerticalLine(indicPlot, vDomainMarker);
            }

            XYTextAnnotation vLineLabel = createVLineAnnotation(chartX, plotArea.getHeight());
            lineAnnotations.put(chartX, vLineLabel);
            mainPlot.addAnnotation(vLineLabel);

        } else {

            removeVline(vLineAt);

            List<Double> chartYs = point2DToRange(clickPoint, plotArea);

            if (indicPlot == null || clickPoint.getY() < plotArea.getHeight()*((double)(CHARTS_TOTAL_WEIGHT - indicPlotWeight)/(double)CHARTS_TOTAL_WEIGHT)) {

                ValueMarker vRangeMarker = new ValueMarker(chartYs.get(0));
                vRangeMarker.setPaint(Color.ORANGE);
                vRangeMarker.setLabelAnchor(RectangleAnchor.LEFT);
                vRangeMarker.setLabelTextAnchor(TextAnchor.BOTTOM_LEFT);
                vRangeMarker.setLabelFont(mainYAxis.getTickLabelFont().deriveFont(8f));
                vRangeMarker.setLabel(mainYAxis.getNumberFormatOverride().format(chartYs.get(0)));
                vRangeMarker.setStroke(new BasicStroke(1.5f, BasicStroke.JOIN_MITER, BasicStroke.JOIN_MITER, 10.0f, new float[]{1.0f},0.0f)); //set the value new float[]{1.0f}as 1.0f to avoiding dashing of marker
                mainPlot.addRangeMarker(vRangeMarker);

            } else {
                for (int i= 0; i <indicPlot.getRangeAxisCount(); i++) {

                    Double value = chartYs.get(i);

                    if (value != null) {
                        ValueMarker vRangeMarker = new ValueMarker(value);
                        vRangeMarker.setPaint(Color.ORANGE);
                        vRangeMarker.setLabelAnchor(RectangleAnchor.LEFT);
                        vRangeMarker.setLabelOffsetType(LengthAdjustmentType.CONTRACT);
                        vRangeMarker.setLabelOffset(new RectangleInsets(0, 50.0*i, 0, 0));
                        vRangeMarker.setLabelTextAnchor(TextAnchor.BOTTOM_LEFT);
                        vRangeMarker.setLabelFont(indicPlot.getRangeAxis(i).getTickLabelFont().deriveFont(8f));
                        NumberFormat numberFormatOverride = ((NumberAxis)indicPlot.getRangeAxis(i)).getNumberFormatOverride();
                        if (numberFormatOverride == null) numberFormatOverride = new DecimalFormat("#0.0000");
                        vRangeMarker.setLabel((i+1)+"/ "+numberFormatOverride.format(value));
                        vRangeMarker.setStroke(new BasicStroke(1.5f, BasicStroke.JOIN_MITER, BasicStroke.JOIN_MITER, 10.0f, new float[]{1.0f},0.0f)); //set the value new float[]{1.0f}as 1.0f to avoiding dashing of marker
                        indicPlot.addRangeMarker(i, vRangeMarker, Layer.FOREGROUND);
                    }

                }
            }

        }

    }

    protected XYTextAnnotation createVLineAnnotation(long chartX, double chartHeight) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        double chartY = mainYAxis.getLowerBound()+ 35*mainYAxis.getRange().getLength() / (chartHeight*(CHARTS_TOTAL_WEIGHT-indicPlotWeight)/CHARTS_TOTAL_WEIGHT);

        XYTextAnnotation vLineLabel = new XYTextAnnotation(simpleDateFormat.format(chartX), chartX, chartY);
        vLineLabel.setFont(mainYAxis.getTickLabelFont().deriveFont(8f));
        vLineLabel.setRotationAnchor(TextAnchor.BASELINE_CENTER);
        vLineLabel.setTextAnchor(TextAnchor.BASELINE_CENTER);
        vLineLabel.setRotationAngle(-3.14 / 2);
        vLineLabel.setPaint(Color.black);

        return vLineLabel;
    }


    public long point2DToTime(Point2D clickPoint, Rectangle2D plotArea) {
        long chartX = (long) xAxis.java2DToValue(clickPoint.getX(), plotArea, mainPlot.getDomainAxisEdge());
        long chartXRounded = DateFactory.midnithDate(new Date(chartX)).getTime();
        chartXRounded = (chartX-chartXRounded > 24 * 3600 * 1000 /2)?chartXRounded+24 * 3600 * 1000:chartXRounded;
        return chartX;
    }

    private List<Double> point2DToRange(Point2D clickPoint, Rectangle2D plotArea) {

        List<Double> chartY = new ArrayList<Double>();
        double mainH = (indicPlot == null)? plotArea.getHeight() : plotArea.getHeight()*(double)(CHARTS_TOTAL_WEIGHT - indicPlotWeight)/(double)CHARTS_TOTAL_WEIGHT;
        if (clickPoint.getY() < mainH) {
            Rectangle2D.Double doubleRectanble = new Rectangle2D.Double(plotArea.getX(), plotArea.getY(), plotArea.getWidth(), mainH);
            chartY.add(mainYAxis.java2DToValue(clickPoint.getY(), doubleRectanble, mainPlot.getRangeAxisEdge()));
        } else {
            Rectangle2D.Double doubleRectanble = new Rectangle2D.Double(plotArea.getX(), plotArea.getY() + mainH + 4, plotArea.getWidth(), plotArea.getHeight()*((double)(indicPlotWeight)/(double)CHARTS_TOTAL_WEIGHT));
            int rangeAxisCount = indicPlot.getRangeAxisCount();
            for (int i = 0; i < rangeAxisCount; i++) {
                if (indicPlot.getRangeAxis(i) != null) {
                    chartY.add(indicPlot.getRangeAxis(i).java2DToValue(clickPoint.getY(), doubleRectanble, indicPlot.getRangeAxisEdge(i)));
                } else {
                    chartY.add(null);
                }
            }
        }

        return chartY;

    }


    public ValueMarker checkVLineAt(Point2D point2d, Rectangle2D rectangle2d) {

        Point2D lowerClickPoint = new Point( (int) point2d.getX()-1, (int)point2d.getY());
        Point2D higherClickPoint = new Point( (int) point2d.getX()+1, (int)point2d.getY());
        long lowerTime = point2DToTime(lowerClickPoint, rectangle2d);
        long higherTime = point2DToTime(higherClickPoint, rectangle2d);

        @SuppressWarnings("unchecked")
        Collection<ValueMarker> mainDomainMarkers = mainPlot.getDomainMarkers(Layer.FOREGROUND);
        if (mainDomainMarkers != null) {
            for (ValueMarker valueMarker : mainDomainMarkers) {
                if (lowerTime <= valueMarker.getValue() && valueMarker.getValue() <= higherTime) {
                    return valueMarker;
                }
            }
        }

        return null;

    }

    public List<ValueMarker> checkHLineAt(Point2D clickPoint, Rectangle2D plotArea) {

        double mainH = (indicPlot == null)? plotArea.getHeight() : plotArea.getHeight()*(double)(CHARTS_TOTAL_WEIGHT - indicPlotWeight)/(double)CHARTS_TOTAL_WEIGHT;

        List<ValueMarker> valueMarkers = new ArrayList<ValueMarker>();
        if (clickPoint.getY() < mainH) {

            Point2D lowerClickPoint = new Point( (int) clickPoint.getX(), (int) clickPoint.getY()+1);
            Point2D higherClickPoint = new Point( (int) clickPoint.getX(), (int) clickPoint.getY()-1);
            List<Double> lowerTime = point2DToRange(lowerClickPoint, plotArea);
            List<Double> higherTime = point2DToRange(higherClickPoint, plotArea);

            @SuppressWarnings("unchecked")
            Collection<ValueMarker> mainRangeMarkers = mainPlot.getRangeMarkers(Layer.FOREGROUND);
            if (mainRangeMarkers != null) {
                for (ValueMarker valueMarker : mainRangeMarkers) {
                    if (lowerTime.get(0) <= valueMarker.getValue() && valueMarker.getValue() <= higherTime.get(0)) {
                        valueMarkers.add(valueMarker);
                    }
                }
            }

        } else {

            Point2D lowerClickPoint = new Point( (int) clickPoint.getX(), (int) clickPoint.getY()+1);
            Point2D higherClickPoint = new Point( (int) clickPoint.getX(), (int) clickPoint.getY()-1);
            List<Double> lowerTime = point2DToRange(lowerClickPoint, plotArea);
            List<Double> higherTime = point2DToRange(higherClickPoint, plotArea);

            int rangeAxisCount = indicPlot.getRangeAxisCount();
            for (int i = 0; i < rangeAxisCount; i++) {
                @SuppressWarnings("unchecked")
                Collection<ValueMarker> indicRangeMarkers = indicPlot.getRangeMarkers(i, Layer.FOREGROUND);
                if (indicRangeMarkers != null) {
                    for (ValueMarker valueMarker : indicRangeMarkers) {
                        if (lowerTime.get(i) != null && higherTime.get(i) != null) {
                            if (lowerTime.get(i) <= valueMarker.getValue() && valueMarker.getValue() <= higherTime.get(i)) {
                                valueMarkers.add(valueMarker);
                            }
                        }
                    }
                }
            }

        }

        return valueMarkers;

    }

    public Boolean removeVLineAt(Point2D point2d, Rectangle2D rectangle2d) {
        ValueMarker vLineAt = checkVLineAt(point2d, rectangle2d);
        if (vLineAt != null) {
            removeVline(vLineAt);
            return true;
        } else {
            return false;
        }
    }

    public Boolean removeHLineAt(Point2D point2d, Rectangle2D rectangle2d) {
        List<ValueMarker> hLineAt = checkHLineAt(point2d, rectangle2d);
        if (hLineAt.size() > 0) {
            for (ValueMarker valueMarker : hLineAt) {
                removeHLine(valueMarker);
            }
            return true;
        } else {
            return false;
        }
    }

    protected void removeHLine(ValueMarker hLineAt) {
        mainPlot.removeRangeMarker(hLineAt);
        if (indicPlot != null) {
            int rangeAxisCount = indicPlot.getRangeAxisCount();
            for (int i = 0; i < rangeAxisCount; i++) {
                indicPlot.removeRangeMarker(i, hLineAt, Layer.FOREGROUND);
            }
        }
    }

    private void removeVline(ValueMarker valueMarker) {
        mainPlot.removeDomainMarker(valueMarker);
        mainPlot.removeAnnotation(lineAnnotations.remove(new Double(valueMarker.getValue()).longValue()));
        if (indicPlot != null) {
            indicPlot.removeDomainMarker(valueMarker);
        }
    }

    private void addVerticalLine(XYPlot plot, Marker vLineDomainMarker) {
        plot.addDomainMarker(vLineDomainMarker);
    }

    public void removeVLines() {

        mainPlot.clearDomainMarkers();
        for (XYTextAnnotation lineAnnotation : lineAnnotations.values()) {
            mainPlot.removeAnnotation(lineAnnotation);
        }
        lineAnnotations.clear();

        if (indicPlot != null) {
            indicPlot.clearDomainMarkers();
        }
    }

    public void removeHLines() {

        mainPlot.clearRangeMarkers();
        if (indicPlot != null) {
            indicPlot.clearRangeMarkers();
        }

    }

    @SuppressWarnings("unchecked")
    public void resetVerticalLines(Rectangle2D plotArea) {

        Collection<ValueMarker> mainDomainMarkers = mainPlot.getDomainMarkers(Layer.FOREGROUND);

        Collection<ValueMarker> indicDomainMarkers = null;
        if (indicPlot != null) {
            indicDomainMarkers = indicPlot.getDomainMarkers(Layer.FOREGROUND);
        }

        if (mainDomainMarkers != null) {
            for (ValueMarker valueMarker : mainDomainMarkers) {

                XYTextAnnotation xyTextAnnotation = lineAnnotations.remove(new Double(valueMarker.getValue()).longValue());
                mainPlot.removeAnnotation(xyTextAnnotation);
                XYTextAnnotation newAnnotation = createVLineAnnotation(new Double(valueMarker.getValue()).longValue(), plotArea.getHeight());
                mainPlot.addAnnotation(newAnnotation);
                lineAnnotations.put(new Double(xyTextAnnotation.getX()).longValue(), newAnnotation);

                if (indicPlot != null && (indicDomainMarkers == null || !indicDomainMarkers.contains(valueMarker))) {					
                    indicPlot.addDomainMarker(valueMarker);
                }

            }
        }

    }

}
