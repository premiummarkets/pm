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
package com.finance.pms.events.scoring.chartUtils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.block.ColumnArrangement;
import org.jfree.chart.block.FlowArrangement;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.AbstractXYItemRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.VerticalAlignment;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;



public class ChartGenerator {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ChartGenerator.class);

	private String title;

	public ChartGenerator(String title) {
		this.title = title;
		XYBarRenderer.setDefaultShadowsVisible(false);
		XYBarRenderer.setDefaultBarPainter(new StandardXYBarPainter());
	}

	public void generateChartPNGFor(
			OutputStream out, EventInfo chartedEvtDef, Map<EventInfo, SortedMap<Date, double[]>> lineSeries, 
			SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barPredSeries, 
			SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barRefSeries, boolean includeWeekends) throws IOException {
		
		JFreeChart generatedChart = this.generateChart(chartedEvtDef, lineSeries, barPredSeries, barRefSeries, includeWeekends);
		this.exportPNG(out, generatedChart);

	}

	private JFreeChart generateChart(
			EventInfo chartedEvtDef, Map<EventInfo, SortedMap<Date, double[]>> eventsLinesSeries,
			SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barPredSeries, SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barRefSeries, 
			boolean includeWeekends) {

		DateAxis hAxis = new DateAxis();
		
		if (includeWeekends) {
			LOGGER.info("Including weekends - continous quotations.");
			hAxis.setTimeline(new SegmentedTimeline(SegmentedTimeline.DAY_SEGMENT_SIZE,7,0));
		} else {
			LOGGER.info("Skiping weekends.");
			hAxis.setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());
		}
		
		hAxis.setTickUnit(new DateTickUnit(DateTickUnitType.MONTH, 3, new SimpleDateFormat("MMM yy")), true, true);
		hAxis.setLowerMargin(0.0f);
		hAxis.setUpperMargin(0.0f);
		
		SortedSet<Date> fullDateSet = new TreeSet<>();
		for (SortedMap<Date, double[]> output : eventsLinesSeries.values()) {
			fullDateSet.addAll(output.keySet());
		}

		XYPlot plot = new XYPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setDomainAxis(hAxis);
		plot.setOrientation(PlotOrientation.VERTICAL);
		plot.setRangeGridlinesVisible(false);
		plot.setDomainGridlinesVisible(true);

		//Line rendering for all groups
		//We expect two EventInfos : predictions and targets with Predictions the first and target the second.
		//The first EventInfo (predictions) has two groups of outputs: predictions and close
		//The second EventInfo (targets) has the targets outputs and potentially additional lines.
		//WIP: We display only the predictions EventInfo lines or None
		eventsLinesSeries.keySet().retainAll(Arrays.asList(chartedEvtDef));
		//eventsLinesSeries.keySet().stream().forEach(e -> e.getEventDefDescriptor().allOutputDescr().stream().forEach(d -> d.setDisplayOnChart(true)));
		eventsLinesSeries.keySet().stream()
				.forEach(e -> e.getEventDefDescriptor().allOutputDescr().stream().forEach(d -> d.setDisplayOnChart(!(d.getType().equals(Type.MAIN) || d.getType().equals(Type.MULTI)))));
		
		ChartIndicLineSeriesDataSetBuilder dataSetBuilder = new ChartIndicLineSeriesDataSetBuilder(plot, fullDateSet, eventsLinesSeries);
		dataSetBuilder.build();
		
		int linesGroupsCount = plot.getDatasetCount(); //one DataSet and one Renderer per group

		//Adding Bar rendering for predictions and targets
		int predBarsGrp = linesGroupsCount + 1;
		XYBarRenderer barPredRenderer = new XYBarRenderer();
		plot.setRenderer(predBarsGrp, barPredRenderer);
		TimeSeriesCollection barPredDataset = buildBarDataSet(barPredSeries, barPredRenderer);
		plot.setDataset(predBarsGrp, barPredDataset);
		plot.mapDatasetToRangeAxis(predBarsGrp, linesGroupsCount -1);
		
		int refBarsGrp = linesGroupsCount;
		XYBarRenderer barRefRenderer = new XYBarRenderer();
		plot.setRenderer(refBarsGrp, barRefRenderer);
		TimeSeriesCollection barRefDataset = buildBarDataSet(barRefSeries, barRefRenderer);
		plot.setDataset(refBarsGrp, barRefDataset);
		plot.mapDatasetToRangeAxis(refBarsGrp, linesGroupsCount -1);

		//Chart
		plot.setBackgroundPaint(Color.WHITE);
		JFreeChart jFreeChart = new JFreeChart(plot);
		jFreeChart.setTitle(title);
		jFreeChart.setBackgroundPaint(Color.WHITE);

		//Legend mess
		jFreeChart.removeLegend();

		///Bar chart legend
		LegendTitle predLegend = new LegendTitle(barPredRenderer, new ColumnArrangement(), new FlowArrangement());
		predLegend.setHorizontalAlignment(HorizontalAlignment.LEFT);
		predLegend.setPosition(RectangleEdge.TOP);
		jFreeChart.addLegend(predLegend);
		LegendTitle targetLegend = new LegendTitle(barRefRenderer, new ColumnArrangement(), new FlowArrangement());
		targetLegend.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		targetLegend.setPosition(RectangleEdge.TOP);
		jFreeChart.addLegend(targetLegend);

		///Lines legend
		LegendTitle lineLegend = new LegendTitle(new LegendItemSource() {

			Set<Comparable<?>> seriesKeyDuplCount = new HashSet<>();
			@Override
			public LegendItemCollection getLegendItems() {
				LegendItemCollection legendItemCollection = new LegendItemCollection();
				for(int i = 0; i < linesGroupsCount; i++) {
					XYDataset dataset = plot.getDataset(i);
					if (dataset == null) continue;
					for (int j = 0 ; j <  dataset.getSeriesCount(); j++) {
						Paint seriesPaint = plot.getRenderer(i).getSeriesPaint(j);
						Comparable<?> seriesKey = plot.getDataset(i).getSeriesKey(j);
						String regex = "^[_0-9a-f]+:([_a-zA-Z0-9]+).* as (.*)$";
						Pattern pattern = Pattern.compile(regex);
						Matcher matcher = pattern.matcher(seriesKey.toString().replace('\n', ' '));
						boolean found = matcher.find();
						if (!found) {
							LOGGER.error("No match found for seriesKey short name: " + seriesKey + ", while looking for: " + regex);
							continue;
						}
						String seriesKeyShort = matcher.group(1); //matcher.group(2) + ": " + matcher.group(1);
						if (seriesKeyDuplCount.contains(matcher.group(1))) continue;
						seriesKeyDuplCount.add(matcher.group(1));
						legendItemCollection.add(new LegendItem(seriesKeyShort, null, null, null, new Line2D.Double(-7.0, 0.0, 7.0, 0.0), new BasicStroke(1), seriesPaint));
					}
				}
				return legendItemCollection;
			}
		}, new ColumnArrangement(HorizontalAlignment.LEFT, VerticalAlignment.TOP, 0, 0), null);
		lineLegend.setHorizontalAlignment(HorizontalAlignment.LEFT);
		lineLegend.setPosition(RectangleEdge.BOTTOM);
		jFreeChart.addLegend(lineLegend);
		//

		return jFreeChart;
	}

	private TimeSeriesCollection buildBarDataSet(SortedMap<DataSetBarDescr, SortedMap<Date,Double>> series, AbstractXYItemRenderer renderer) {

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		int seriesIdx = 0;
		for (DataSetBarDescr serieDef : series.keySet()) {

			TimeSeries timeSerie = new TimeSeries(serieDef.getSerieName());

			SortedMap<Date, Double> serie = series.get(serieDef);
			for (Date date : serie.keySet()) {
				RegularTimePeriod period = new Day(date);
				Number value = serie.get(date);
				TimeSeriesDataItem item = new TimeSeriesDataItem(period, value);
				timeSerie.add(item, false);
			}

			dataset.addSeries(timeSerie);
			renderer.setSeriesPaint(seriesIdx, serieDef.getSerieColor());
			renderer.setSeriesFillPaint(seriesIdx, serieDef.getSerieColor());
			renderer.setSeriesStroke(seriesIdx, new BasicStroke(serieDef.getSerieStrokeSize()));

			seriesIdx++;
		}

		return dataset;
	}


	private void exportPNG(OutputStream out, JFreeChart chart) throws IOException {
		ChartUtilities.writeChartAsPNG(out, chart, 700, 800);

	}

}
