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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.block.ColumnArrangement;
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
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.VerticalAlignment;

import com.finance.pms.events.EventInfo;



public class ChartGenerator {

	private String title;


	public ChartGenerator(String title) {
		this.title = title;
		XYBarRenderer.setDefaultShadowsVisible(false);
		XYBarRenderer.setDefaultBarPainter(new StandardXYBarPainter());
	}

	public void generateChartPNGFor(
			OutputStream out, EventInfo chartedEvtDef, SortedMap<Date,double[]> lineSeries, 
			SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barPredSeries, SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barRefSeries) throws IOException {

		JFreeChart generatedChart = this.generateChart(chartedEvtDef, lineSeries, barPredSeries, barRefSeries);
		this.exportPNG(out, generatedChart);

	}

	private JFreeChart generateChart(
			EventInfo chartedEvtDef, SortedMap<Date,double[]> lineSeries,
			SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barPredSeries, SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barRefSeries) {

		DateAxis hAxis = new DateAxis("Time line");
		DateFormat dateFormat = new SimpleDateFormat("dd MMM yy");
		hAxis.setTickUnit(new DateTickUnit(DateTickUnitType.MONTH, 6, dateFormat));
		hAxis.setLowerMargin(0.0f);
		hAxis.setUpperMargin(0.0f);

		XYPlot plot = new XYPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setDomainAxis(hAxis);
		plot.setOrientation(PlotOrientation.VERTICAL);
		plot.setRangeGridlinesVisible(false);
		plot.setDomainGridlinesVisible(true);

		//Line rendering
		Map<EventInfo, SortedMap<Date, double[]>> eventsSeries = new HashMap<>();
		eventsSeries.put(chartedEvtDef, lineSeries);
		ChartIndicLineSeriesDataSetBuilder dataSetBuilder = new ChartIndicLineSeriesDataSetBuilder(plot, eventsSeries);
		dataSetBuilder.build();
		int linesGroupsCount = plot.getDatasetCount(); //one DataSet and one Renderer per group

		//Bar rendering
		int predBarsGrp = linesGroupsCount +1;
		XYBarRenderer barPredRenderer = new XYBarRenderer();
		plot.setRenderer(predBarsGrp, barPredRenderer);
		TimeSeriesCollection barPredDataset = buildBarDataSet(barPredSeries, barPredRenderer);
		plot.setDataset(predBarsGrp, barPredDataset);
		plot.mapDatasetToRangeAxis(predBarsGrp, 0);

		int prefBarsGrp = linesGroupsCount;
		XYBarRenderer barRefRenderer = new XYBarRenderer();
		plot.setRenderer(prefBarsGrp, barRefRenderer);
		TimeSeriesCollection barRefDataset = buildBarDataSet(barRefSeries, barRefRenderer);
		plot.setDataset(prefBarsGrp, barRefDataset);
		plot.mapDatasetToRangeAxis(prefBarsGrp, linesGroupsCount-1);

		//Chart
		JFreeChart jFreeChart = new JFreeChart(plot);
		jFreeChart.setTitle(title);

		//Legend mess
		jFreeChart.removeLegend();

		///Bar chart legend
		jFreeChart.addLegend(new LegendTitle(barPredRenderer));
		jFreeChart.addLegend(new LegendTitle(barRefRenderer));

		///Lines legend
		LegendTitle legend = new LegendTitle(new LegendItemSource() {

			Set<Comparable<?>> seriesKeyDuplCount = new HashSet<>();
			@Override
			public LegendItemCollection getLegendItems() {
				LegendItemCollection legendItemCollection = new LegendItemCollection();
				for(int i = 0; i < linesGroupsCount; i++) {
					for (int j = 0 ; j <  plot.getDataset(i).getSeriesCount(); j++) {
						Paint seriesPaint = plot.getRenderer(i).getSeriesPaint(j);
						Comparable<?> seriesKey = plot.getDataset(i).getSeriesKey(j);
						if (seriesKeyDuplCount.contains(seriesKey)) continue;
						seriesKeyDuplCount.add(seriesKey);
						legendItemCollection.add(new LegendItem(seriesKey.toString(), null,
								null, null, new Line2D.Double(-7.0, 0.0, 7.0, 0.0), new BasicStroke(1), seriesPaint));
					}
				}
				return legendItemCollection;
			}
		}, new ColumnArrangement(HorizontalAlignment.LEFT, VerticalAlignment.TOP, 0, 0), null);
		legend.setPosition(RectangleEdge.BOTTOM);
		jFreeChart.addLegend(legend);
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
