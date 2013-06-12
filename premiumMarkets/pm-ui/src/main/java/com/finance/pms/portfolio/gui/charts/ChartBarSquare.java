package com.finance.pms.portfolio.gui.charts;

import java.util.Date;
import java.util.SortedMap;

import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesDataItem;

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
	public TimeSeries buildBarTimeSeries(String serieName, SortedMap<Date, Double> barSerie, TimeSeries lineSerie) {

		TimeSeries timeSerie = new TimeSeries(serieName);

		int cpt = 0;
		//int gap = Math.max(1, barSerie.size()/208);
		int gap = 1;
		for (Date date : barSerie.keySet()) {
			if (cpt % gap  == 0) {
				RegularTimePeriod period = new Day(date);

				Number value = barSerie.get(date);
				TimeSeriesDataItem item = new TimeSeriesDataItem(period, value);
				timeSerie.add(item, false);
			}
			cpt++;
		}

		return timeSerie;
	}

}
