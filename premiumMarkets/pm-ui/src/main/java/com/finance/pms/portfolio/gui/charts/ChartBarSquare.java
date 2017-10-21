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

import java.util.Date;
import java.util.SortedMap;

import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesDataItem;

import com.finance.pms.events.scoring.chartUtils.BarChart;

public class ChartBarSquare extends BarChartDisplayStrategy {
	
	private NumberAxis mainBarYAxis;
	private ChartMain chartMain;

	public ChartBarSquare(ChartMain chartMain) {
		super();
		this.chartMain = chartMain;
	}

	
	@Override
	public void setBarYAxis() {
		
		//Bar Y axis
		mainBarYAxis = new NumberAxis();
		mainBarYAxis.setAutoRange(true);
		mainBarYAxis.setLowerBound(-.20);
		mainBarYAxis.setUpperBound(1.20);
		mainBarYAxis.setVisible(false);
		
		chartMain.getMainPlot().setRangeAxis(1, mainBarYAxis);
		chartMain.getMainPlot().mapDatasetToRangeAxis(1, 1);
		chartMain.getMainPlot().setRangeAxisLocation(1, AxisLocation.TOP_OR_RIGHT);
		
	}


	@Override
	public double maxBarValue(TimeSeries lineSerie) {
		return 1d;
	}


	@Override
	public TimeSeries buildBarTimeSeries(String serieName, SortedMap<Date, BarChart> barSerie, TimeSeries lineSerie) {

		TimeSeries timeSerie = new TimeSeries(serieName);

		int cpt = 0;
		//int gap = Math.max(1, barSerie.size()/208);
		int gap = 1;
		for (Date date : barSerie.keySet()) {
			if (cpt % gap  == 0) {
				RegularTimePeriod period = new Day(date);

				Number value = barSerie.get(date).getValue();
				TimeSeriesDataItem item = new TimeSeriesDataItem(period, value);
				timeSerie.add(item, false);
			}
			cpt++;
		}

		return timeSerie;
	}

}
