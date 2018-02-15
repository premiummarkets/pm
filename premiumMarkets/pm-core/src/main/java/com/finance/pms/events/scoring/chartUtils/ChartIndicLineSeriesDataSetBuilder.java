package com.finance.pms.events.scoring.chartUtils;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedMap;

import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.Layer;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.EventDefDescriptor;

public class ChartIndicLineSeriesDataSetBuilder {
    
    private static MyLogger LOGGER = MyLogger.getLogger(ChartIndicLineSeriesDataSetBuilder.class);
    private XYPlot indicPlot;
    
    private EventInfo chartedEvtDef;
    private SortedMap<Date, double[]> series;

    public ChartIndicLineSeriesDataSetBuilder(XYPlot indicPlot, EventInfo chartedEvtDef, SortedMap<Date, double[]> series) {
        super();
        this.indicPlot = indicPlot;
        this.chartedEvtDef = chartedEvtDef;
        this.series = series;
    }

    public void build() {
        
        try {

            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy");
            final NumberFormat numberFormat = new DecimalFormat("0.############");

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
                    //renderer.setBaseItemLabelsVisible(true, false);
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
                        for (Date date : series.keySet()) {
                            double[] ds = series.get(date);
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

                        double thresholdCenter = groupCenter(series, eventDefDescriptor, groupIdx);
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

        } catch (Exception e) {
            LOGGER.warn("Can't refresh indicator chart : "+ e, e);
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

}
