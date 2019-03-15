package com.finance.pms.events.scoring.chartUtils;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

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

	private Map<EventInfo, SortedMap<Date, double[]>> eventsSeries;

	public ChartIndicLineSeriesDataSetBuilder(XYPlot indicPlot, Map<EventInfo, SortedMap<Date, double[]>> eventsSeries) {
		super();
		this.indicPlot = indicPlot;
		this.eventsSeries = eventsSeries;
	}

	public void build() {

		try {

			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy");
			final NumberFormat numberFormat = new DecimalFormat("0.############");

			indicPlot.clearRangeAxes();

			SortedSet<Date> fullDateSet = new TreeSet<>();
			for (SortedMap<Date, double[]> output : eventsSeries.values()) {
				fullDateSet.addAll(output.keySet());
			}

			int eventDefStartIdx = 0;
			for (EventInfo chartedEvtDef: eventsSeries.keySet()) {

				final EventDefDescriptor eventDefDescriptor = chartedEvtDef.getEventDefDescriptor();
				int eventDefNbOfGroupsDisplayed = 0;

				for (int groupIdx = 0; groupIdx < eventDefDescriptor.getGroupsCount(); groupIdx++) {//Iterate groups

					try {
						LOGGER.debug("Group description : " + eventDefDescriptor.getGroupFullDescriptionFor(groupIdx));
						Boolean groupIsDisplayed = false;

						//Renderer
						int rendererIdx = eventDefStartIdx + groupIdx;
						XYItemRenderer renderer = indicPlot.getRenderer(rendererIdx);
						if (renderer == null) {
							renderer = new XYLineAndShapeRenderer(true, false);
							//renderer.setBaseItemLabelsVisible(true, false);
							indicPlot.setRenderer(rendererIdx, renderer);
						}

						//Build data set adding data series and tool tips for each output of the group
						TimeSeriesCollection dataSet = new TimeSeriesCollection();
						Integer[] outputIndexes = eventDefDescriptor.getOutputIndexesForGroup(groupIdx);
						int seriesIdx = 0;
						double groupMaxY = -Double.MAX_VALUE;
						double groupMinY = Double.MAX_VALUE;
						for (int k = 0; k < outputIndexes.length; k++) {//Iterate outputs

							int outputIdx = outputIndexes[k];
							if (eventDefDescriptor.isDisplayed(outputIdx)) {

								//Build the timeSeries for the output
								final String domain = eventDefDescriptor.getFullNameFor(outputIdx);
								TimeSeries timeSeries = new TimeSeries(domain);
								Boolean allNaN = true;
								for (Date date : fullDateSet) {
									double[] ds = eventsSeries.get(chartedEvtDef).get(date);
									Number value;
									if (ds != null) {
										value = ds[outputIdx];
										//Negative Infinity means we should ignore the entry (won't break the line).
										//NaN means not wanted for display and should breaks the line so it needs to be kept... except all is NaN
										if (value != null && !Double.isNaN(value.doubleValue())) allNaN = false;
										if (value != null && !Double.isInfinite(value.doubleValue())) {
											RegularTimePeriod period = new Day(date);
											TimeSeriesDataItem item = new TimeSeriesDataItem(period, value);
											timeSeries.add(item, false);
										}
									}
								}

								//Data Set
								groupMaxY = (timeSeries.getMaxY() > groupMaxY)?timeSeries.getMaxY():groupMaxY;
								groupMinY = (timeSeries.getMinY() < groupMinY)?timeSeries.getMinY():groupMinY;

								if (allNaN || (groupMinY == 0 && groupMaxY == 0) || (timeSeries.getMaxY() == -Double.MAX_VALUE && timeSeries.getMinY() == Double.MAX_VALUE)) {
									continue;
								} else {
									groupIsDisplayed = true;
								}

								dataSet.addSeries(timeSeries);

								renderer.setSeriesPaint(seriesIdx, eventDefDescriptor.getColor(outputIdx));
								renderer.setSeriesShape(seriesIdx, new Rectangle(new Dimension(100, 100)));

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

								renderer.setSeriesToolTipGenerator(seriesIdx, xyToolTpGen);
								seriesIdx++;

							}

						} //Outputs loop

						//Y Axe for group. Finalizing the Group.
						boolean hasData = (groupMinY != Double.MAX_VALUE && groupMaxY != -Double.MAX_VALUE);
						if (groupIsDisplayed && hasData) {

							ValueAxis rangeAxis = indicPlot.getRangeAxis(rendererIdx);
							if (rangeAxis == null) {

								double thresholdCenter = groupCenter(eventsSeries.get(chartedEvtDef), eventDefDescriptor, groupIdx);
								rangeAxis = initYAxis(thresholdCenter, groupMinY, groupMaxY);

								if (eventDefNbOfGroupsDisplayed == 0) {
									indicPlot.addRangeMarker(rendererIdx, new ValueMarker(0), Layer.FOREGROUND, false);
									indicPlot.setRangeGridlinesVisible(true);
									AxisLocation location = AxisLocation.TOP_OR_LEFT;
									indicPlot.setRangeAxisLocation(rendererIdx, location);
									rangeAxis.setLabel(chartedEvtDef.getEventReadableDef() + " : " + eventDefDescriptor.getMainLabelForGroup(groupIdx));
								} else if (eventDefNbOfGroupsDisplayed <= 2) {
									AxisLocation location = AxisLocation.TOP_OR_RIGHT;
									indicPlot.setRangeAxisLocation(rendererIdx, location);
									rangeAxis.setLabel(chartedEvtDef.getEventReadableDef() + " : " + eventDefDescriptor.getMainLabelForGroup(groupIdx));
								} else {
									rangeAxis.setVisible(false);
								}
								eventDefNbOfGroupsDisplayed++;
								indicPlot.setRangeAxis(rendererIdx, rangeAxis, true);

							}

							//Set group dateSet
							indicPlot.setDataset(rendererIdx, dataSet);
							if ( rendererIdx != 0 ) indicPlot.mapDatasetToRangeAxis(rendererIdx, rendererIdx);

							LOGGER.debug("Group displayed: \n" + eventDefDescriptor.getGroupFullDescriptionFor(groupIdx));

						} else {
							if (groupIsDisplayed && !hasData) {
								LOGGER.warn("Group has no data (not displayed): \n" + eventDefDescriptor.getGroupFullDescriptionFor(groupIdx));
							}
						}

					} catch (Exception e) {
						LOGGER.error("Can't display group : " + eventDefDescriptor.getGroupFullDescriptionFor(groupIdx), e);
					}

				} //Groups loop

				eventDefStartIdx = eventDefStartIdx + eventDefNbOfGroupsDisplayed;

			} //EventDefs loop

		} catch (NoSuchElementException e) {
			LOGGER.warn("Can't refresh indicator chart : "+ e);
		} catch (Exception e) {
			LOGGER.warn("Can't refresh indicator chart : "+ e, e);
		}

	}

	private double groupCenter(SortedMap<Date, double[]> serie, EventDefDescriptor eventDefDescriptor, int groupIdx) {
		double thresholdCenter = Double.NaN;
		Integer[] thresholdsIdxs = eventDefDescriptor.getThresholdsIdx(groupIdx);
		if (thresholdsIdxs.length != 0) {
			double[] thresholdValues = new double[thresholdsIdxs.length];
			for (int j = 0; j < thresholdsIdxs.length; j++) {
				double thresholdValue = serie.get(serie.firstKey())[thresholdsIdxs[j]];
				thresholdValues[j] = thresholdValue;
			}
			Median median = new Median();
			thresholdCenter = median.evaluate(thresholdValues);
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
