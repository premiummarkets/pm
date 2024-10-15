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

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.Layer;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.EventDefDescriptor;

public class ChartIndicLineSeriesDataSetBuilder {

	private static MyLogger LOGGER = MyLogger.getLogger(ChartIndicLineSeriesDataSetBuilder.class);
	private XYPlot indicPlot;
	private Boolean oneRangeForAll;
	private Boolean oneRangeForEach;

	private SortedSet<Date> fullDateSet;
	private Map<EventInfo, SortedMap<Date, double[]>> eventsSeries;



	public ChartIndicLineSeriesDataSetBuilder(XYPlot indicPlot, Boolean oneRangeForAll, Boolean oneRangeForEach, SortedSet<Date> fullDateSet, Map<EventInfo, SortedMap<Date, double[]>> eventsSeries) {
		super();
		this.indicPlot = indicPlot;
		this.oneRangeForAll = oneRangeForAll;
		this.oneRangeForEach = oneRangeForEach;
		
		this.fullDateSet = fullDateSet;
		this.eventsSeries = eventsSeries;
	}

	//XYPlot (indicPlot) contains 0..n renderer (one per group). Each renderer contains 0..n series (dataSet)
	//One XYPlot will display several eventDef, each containing several groups, each containing several series.
	//eventDefStartIdx is the the index of the eventDef (sequence in eventDef loop)
	//groupIdx is the index of the group within the eventDef (sequence in group loop)
	//seriesIdx is the index of the series within the group (sequence in output series loop)
	// => the need to flatten the eventDef dimension: EventDef (eventDefIdx, groupIdx, seriesIdx) => XYPLOT shape (rendererIdx, seriesIdx)
	//Also some groups are no displayed. Hence the variable eventDefNbOfRenderesCreatedIdx
	public void build() {

		try {

			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy");
			final NumberFormat numberFormat = new DecimalFormat("0.############");

			indicPlot.clearRangeAxes();

			int eventDefFirstRendererIdx = 0;
			
			for (EventInfo chartedEvtDef: eventsSeries.keySet()) {//Iterate EventDef

				final EventDefDescriptor eventDefDescriptor = chartedEvtDef.getEventDefDescriptor();
				int rendererIdx = eventDefFirstRendererIdx;
				int evtDefGrpRenderersCreated = 0;

				for (int groupIdx = 0; groupIdx < eventDefDescriptor.getGroupsCount(); groupIdx++) {//Iterate groups

					try {
						LOGGER.info("Group description: " + eventDefDescriptor.getMainLabelForGroup(groupIdx));
						Boolean groupIsDisplayed = false;

						//Renderer
						XYItemRenderer renderer = indicPlot.getRenderer(rendererIdx);
						if (renderer == null) {
							renderer = new XYLineAndShapeRenderer(true, false);
							//renderer.setBaseItemLabelsVisible(true, false);
							indicPlot.setRenderer(rendererIdx, renderer);
						}
						
						double groupMaxY = -Double.MAX_VALUE;
						double groupMinY = Double.MAX_VALUE;

						//Build data set adding data series and tool tips for each output of the group
						MyTimeSeriesCollection dataSet = new MyTimeSeriesCollection();
						Integer[] outputIndexes = eventDefDescriptor.getOutputIndexesForGroup(groupIdx);
						int seriesInGrpIdx = 0;
						for (int k = 0; k < outputIndexes.length; k++) {//Iterate outputs series
							
							int outputIdx = outputIndexes[k];
						
							if (eventDefDescriptor.isDisplayed(groupIdx, outputIdx)) {
								
								LOGGER.info("Idx. " + 
										chartedEvtDef.getEventDefinitionRef() + " groupIdx " + groupIdx + " outputIdx(k) " + outputIdx + "(" + k + ")" +
										" => eventDefFirstRendererIdx " + eventDefFirstRendererIdx  + ", rendererIdx " + rendererIdx + 
										", seriesInGrpIdx " + seriesInGrpIdx + ", evtDefGrpRenderersCreated " + evtDefGrpRenderersCreated);

								//Build the timeSeries for the output
								final String domain = eventDefDescriptor.getFullNameFor(groupIdx, outputIdx);
								TimeSeries timeSeries = new TimeSeries(domain);
								Boolean allNaN = true;
								for (Date date : fullDateSet) {
									double[] ds = eventsSeries.get(chartedEvtDef).get(date);
									Number value;
									if (ds != null) {
										try {
											value = ds[outputIdx];
											//Negative Infinity (Double.NEGATIVE_INFINITY) means we should ignore the entry (hence this won't break the drawn line continuity on chart).
											//NaN (Double.NaN) means not wanted for display and should break the line continuity and it needs to be kept as such for the chart rendering... 
											//Except when all is NaN, the line is not rendered.
											if (value != null && !Double.isNaN(value.doubleValue())) allNaN = false;
											if (value != null && !Double.isInfinite(value.doubleValue())) {
												RegularTimePeriod period = new Day(date);
												TimeSeriesDataItem item = new TimeSeriesDataItem(period, value);
												timeSeries.add(item, false);
											}
										} catch (Exception e) {
											LOGGER.warn(
													"This output index " + outputIdx + ", for '" + domain + "' does not have output data in " + chartedEvtDef.getEventDefinitionRef() + 
													" at " + date + ": " + e.toString());
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
								
								renderer.setSeriesPaint(seriesInGrpIdx, eventDefDescriptor.getColor(rendererIdx, groupIdx, outputIdx));
								renderer.setSeriesShape(seriesInGrpIdx, new Rectangle(new Dimension(100, 100)));

								XYToolTipGenerator xyToolTpGen = new XYToolTipGenerator() {

									public String generateToolTip(XYDataset dataset, int series, int item) {

										String y = "NaN";
										String x = "NaN";
										try {
											y = numberFormat.format(dataset.getYValue(series, item));
											Date date = new Date((long) dataset.getXValue(series, item));
											x = simpleDateFormat.format(date);
											String slashedDomain = (domain.length() <= 50)? domain : domain.replaceAll("\\), *([0-9a-zA-z])", "),<br>&nbsp;&nbsp;$1");
											String[] slashedDomainSplit = slashedDomain.split("<br>");
//											slashedDomain = slashedDomain.substring(0, Math.min(slashedDomain.length(), 200));
											String splitDomain = "";
											for (int j=0; j < Math.min(10, slashedDomainSplit.length); j++) {
												splitDomain = splitDomain + slashedDomainSplit[j].substring(0, Math.min(slashedDomainSplit[j].length(), 200)) + "<br>";
											}
											String tooltip =
											"<html>"
											+ "<font size='2'>"
											+ "<b>" + splitDomain + "</b> on the " + x + "<br>"
											+ "Value: " + y //+ ((eventDefDescriptor.displayValues()) ? "Value: " + y : "")
											+ "</font>"
											+ "</html>";
											return tooltip;
										} catch (Exception e) {
											if (LOGGER.isDebugEnabled()) LOGGER.debug(e, e);
										}
										return "NaN";

									}
								};
								dataSet.addToolTipGenerator(seriesInGrpIdx, xyToolTpGen);
								
								if (oneRangeForEach) {  //One renderer per output
									evtDefGrpRenderersCreated = finaliseRangeAxis(
											chartedEvtDef, eventDefDescriptor, rendererIdx, evtDefGrpRenderersCreated, 
											groupIdx, groupIsDisplayed, groupMaxY, groupMinY,
											dataSet);
									rendererIdx = eventDefFirstRendererIdx + evtDefGrpRenderersCreated;
									
									if (k + 1 < outputIndexes.length) { //There is an other group
										dataSet = new MyTimeSeriesCollection();
										renderer = indicPlot.getRenderer(rendererIdx);
										if (renderer == null) {
											renderer = new XYLineAndShapeRenderer(true, false);
											indicPlot.setRenderer(rendererIdx, renderer);
										}
										groupMaxY = -Double.MAX_VALUE;
										groupMinY = Double.MAX_VALUE;
									}
									
								} else {
									seriesInGrpIdx++;
								}

							}

						} //Outputs series loop
						
						if (!oneRangeForEach) {
							//Y Axe for group. Finalising the Group.
							evtDefGrpRenderersCreated = finaliseRangeAxis(
									chartedEvtDef, eventDefDescriptor, rendererIdx, evtDefGrpRenderersCreated, 
									groupIdx, groupIsDisplayed, groupMaxY, groupMinY,
									dataSet);
							rendererIdx = rendererIdx + evtDefGrpRenderersCreated;
						}
						
					} catch (Exception e) {
						LOGGER.error("Can't display group: " + eventDefDescriptor.getGroupFullDescriptionFor(groupIdx), e);
					}

				} //Groups loop

				eventDefFirstRendererIdx = eventDefFirstRendererIdx + evtDefGrpRenderersCreated;

			} //EventDefs loop
			
			
			if (oneRangeForAll) { //Reseting YAxis Range for all groups if use one range
				double maxUpper = -Double.MAX_VALUE;
				double minLower = Double.MAX_VALUE;
				for (int i = 0; i < indicPlot.getRangeAxisCount(); i++) {
					double upperBound = indicPlot.getRangeAxis(i).getRange().getUpperBound();
					double lowerBound = indicPlot.getRangeAxis(i).getRange().getLowerBound();
					maxUpper = Math.max(maxUpper, upperBound);
					minLower = Math.min(minLower, lowerBound);
				}
				for (int i = 0; i < indicPlot.getRangeAxisCount(); i++) {
					indicPlot.getRangeAxis(i).setRange(minLower, maxUpper);
				}
			}

		} catch (NoSuchElementException e) {
			LOGGER.warn("Can't refresh indicator chart: "+ e);
		} catch (Exception e) {
			LOGGER.warn("Can't refresh indicator chart: "+ e, e);
		}

	}

	private int finaliseRangeAxis(
			EventInfo chartedEvtDef, final EventDefDescriptor eventDefDescriptor, int rendererIdx, int eventDefNbOfRenderesCreatedIdx, 
			int groupIdx, Boolean groupIsDisplayed, double groupMaxY, double groupMinY, 
			MyTimeSeriesCollection dataSet) {
		boolean hasData = (groupMinY != Double.MAX_VALUE && groupMaxY != -Double.MAX_VALUE);
		if (groupIsDisplayed && hasData) {

			ValueAxis rangeAxis = indicPlot.getRangeAxis(rendererIdx);
			if (rangeAxis == null) {
				double[] thresholdCenterNMinNMax = groupCenter(groupMinY, groupMaxY, eventsSeries.get(chartedEvtDef), eventDefDescriptor, groupIdx);
				rangeAxis = initYAxis(thresholdCenterNMinNMax[0], thresholdCenterNMinNMax[1], thresholdCenterNMinNMax[2]);
				if (eventDefNbOfRenderesCreatedIdx == 0) {
					indicPlot.addRangeMarker(rendererIdx, new ValueMarker(0), Layer.FOREGROUND, false);
					indicPlot.setRangeGridlinesVisible(true);
					AxisLocation location = AxisLocation.TOP_OR_LEFT;
					indicPlot.setRangeAxisLocation(rendererIdx, location);
					rangeAxis.setLabel(chartedEvtDef.getEventReadableDef() + ": " + eventDefDescriptor.getMainLabelForGroup(groupIdx));
				} else if (eventDefNbOfRenderesCreatedIdx <= 2) {
					AxisLocation location = AxisLocation.TOP_OR_RIGHT;
					indicPlot.setRangeAxisLocation(rendererIdx, location);
					rangeAxis.setLabel(chartedEvtDef.getEventReadableDef() + ": " + eventDefDescriptor.getMainLabelForGroup(groupIdx));
				} else {
					rangeAxis.setVisible(false);
				}
				eventDefNbOfRenderesCreatedIdx++;
				indicPlot.setRangeAxis(rendererIdx, rangeAxis, true);
			}

			//Set group dateSet
			indicPlot.setDataset(rendererIdx, dataSet);
			//if ( rendererIdx != 0 ) 
			indicPlot.mapDatasetToRangeAxis(rendererIdx, rendererIdx);

			if (LOGGER.isDebugEnabled()) LOGGER.debug("Group displayed: \n" + eventDefDescriptor.getGroupFullDescriptionFor(groupIdx));

		} else {
			if (groupIsDisplayed && !hasData) {
				LOGGER.warn("Group has no data (not displayed): \n" + eventDefDescriptor.getGroupFullDescriptionFor(groupIdx));
			}
		}
		return eventDefNbOfRenderesCreatedIdx;
	}

	private double[] groupCenter(double groupYMin, double groupYMax, SortedMap<Date, double[]> serie, EventDefDescriptor eventDefDescriptor, int groupIdx) {
		double thresholdCenter = Double.NaN;
		Integer[] thresholdsIdxs = eventDefDescriptor.getThresholdsIdx(groupIdx);
		if (thresholdsIdxs.length != 0) {
			double[] thresholdValues = new double[thresholdsIdxs.length];
			for (int j = 0; j < thresholdsIdxs.length; j++) {
				double thresholdValue = serie.get(serie.firstKey())[thresholdsIdxs[j]];
				thresholdValues[j] = thresholdValue;
			}
			Mean mean = new Mean();
			thresholdCenter = mean.evaluate(thresholdValues);
			double maxLimit = Math.max(thresholdCenter - groupYMin, groupYMax - thresholdCenter);
			return new double[] { thresholdCenter, thresholdCenter - maxLimit, thresholdCenter + maxLimit };
		} else {
			thresholdCenter = groupYMin + (groupYMax - groupYMin)/2;
			return new double[] { thresholdCenter, groupYMin, groupYMax };
		}
	}

	protected NumberAxis initYAxis(Double centerValue, double lower, double upper) {

		NumberAxis indicYAxis = new NumberAxis();
		
//		upper = upper + Math.abs(upper * .10);
//		lower = lower - Math.abs(lower * .10);
//		double upperToCenter = Math.abs(upper - centerValue);
//		double lowerTocenter = Math.abs(lower - centerValue);
//		double rangeFix = Math.abs(upperToCenter - lowerTocenter);
//		if (upperToCenter < lowerTocenter) upper = upper + rangeFix; else lower = lower - rangeFix;
		indicYAxis.setRange(new Range(lower, upper), true, true);
		indicYAxis.setAutoRange(false);
		indicYAxis.setAutoRangeIncludesZero(false);
//		indicYAxis.setRangeAboutValue(centerValue, Math.abs(lower)*2);
//		indicYAxis.setFixedDimension(Math.abs(lower)*2);
		indicYAxis.setLowerBound(lower);
		indicYAxis.setUpperBound(upper);
		indicYAxis.centerRange(centerValue);

		indicYAxis.setTickLabelFont(indicYAxis.getTickLabelFont().deriveFont(7f));
		indicYAxis.setLabelFont(indicYAxis.getLabelFont().deriveFont(10f));

		return indicYAxis;

	}

}
