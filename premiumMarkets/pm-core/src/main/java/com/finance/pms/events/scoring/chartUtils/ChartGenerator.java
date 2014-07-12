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
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedMap;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.AbstractXYItemRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;



public class ChartGenerator {
	
	private String title;


	public ChartGenerator(String title) {
		this.title = title;
		XYBarRenderer.setDefaultShadowsVisible(false);
		XYBarRenderer.setDefaultBarPainter(new StandardXYBarPainter());
	}
	
	public void generateChartPNGFor(OutputStream out, Boolean hasDifferentRangeAxis, SortedMap<DataSetBarDescr, SortedMap<Date, Double>> lineSeries, SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barSeries) throws IOException {
		
		JFreeChart generatedChart = this.generateChart(hasDifferentRangeAxis, lineSeries, barSeries);
		this.exportPNG(out, generatedChart);
		
	}

	private JFreeChart generateChart(Boolean hasDifferentRangeAxis, SortedMap<DataSetBarDescr, SortedMap<Date,Double>> lineSeries, SortedMap<DataSetBarDescr, SortedMap<Date,Double>> barSeries) {
		
		DateAxis hAxis = new DateAxis("Time line");
		DateFormat dateFormat = new SimpleDateFormat("dd MMM yy");
		hAxis.setTickUnit(new DateTickUnit(DateTickUnitType.MONTH, 6, dateFormat));
		hAxis.setLowerMargin(0.0f);
		hAxis.setUpperMargin(0.0f);
		
		XYPlot plot = new XYPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setDomainAxis(hAxis);
		if (hasDifferentRangeAxis) {
			NumberAxis premRangeAxis = new NumberAxis();
			premRangeAxis.setAutoRangeIncludesZero(false);
			plot.setRangeAxis(0, premRangeAxis);
			NumberAxis secRangeAxis = new NumberAxis();
			secRangeAxis.setVisible(false);
			plot.setRangeAxis(1, secRangeAxis);
			plot.mapDatasetToRangeAxis(1, 1);
		} else {
			plot.setRangeAxis(new NumberAxis());
		}
	    plot.setOrientation(PlotOrientation.VERTICAL);
	    plot.setRangeGridlinesVisible(false);
	    plot.setDomainGridlinesVisible(true);
	  
		//Line rendering
	    XYLineAndShapeRenderer lineRenderer = new XYLineAndShapeRenderer(true, false);
		plot.setRenderer(0, lineRenderer);
		TimeSeriesCollection dataset = buildDataSet(lineSeries, lineRenderer);
		plot.setDataset(0, dataset);
		
		//Bar rendering
		XYBarRenderer barRenderer = new XYBarRenderer();
		plot.setRenderer(1, barRenderer);
		TimeSeriesCollection barDataset = buildDataSet(barSeries, barRenderer);
		plot.setDataset(1, barDataset);
		
		//Chart
		JFreeChart jFreeChart = new JFreeChart(plot);
		jFreeChart.setTitle(title);
		return jFreeChart;
	}

	private TimeSeriesCollection buildDataSet(SortedMap<DataSetBarDescr, SortedMap<Date,Double>> series, AbstractXYItemRenderer renderer) {
		
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
		ChartUtilities.writeChartAsPNG(out,chart, 700, 600);
		
	}

}
