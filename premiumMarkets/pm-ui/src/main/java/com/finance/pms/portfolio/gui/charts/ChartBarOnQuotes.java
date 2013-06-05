package com.finance.pms.portfolio.gui.charts;

import java.util.Date;
import java.util.SortedMap;

import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesDataItem;


public class ChartBarOnQuotes extends BarChartDisplayStrategy {
	
	private ChartMain chartMain;

	public ChartBarOnQuotes(ChartMain chartMain) {
		super();
		this.chartMain = chartMain;
	}


	@Override
	public void setBarYAxis() {
		chartMain.getMainPlot().setRangeAxis(1, chartMain.getMainYAxis());
		chartMain.getMainPlot().mapDatasetToRangeAxis(1, 0);
	}
	
	
	@Override
	public double maxBarValue(TimeSeries lineSerie) {
		return (Math.abs(lineSerie.getMinY()) < Math.abs(lineSerie.getMaxY()))?lineSerie.getMaxY():lineSerie.getMinY();
	}


	@Override
	public TimeSeries buildBarTimeSeries(String serieName, SortedMap<Date, Double> barSerie, TimeSeries lineSerie) {

		TimeSeries timeSerie = new TimeSeries(serieName);
		Number prevLineValue = null;
		int cpt = 0;
		int gap = Math.max(1, barSerie.size()/208);
		for (Date date : barSerie.keySet()) {
			if (cpt % gap  == 0) {
				RegularTimePeriod period = new Day(date);

				Number lineValue = lineSerie.getValue(period);
				if (lineValue != null) {
					prevLineValue = lineValue;
				}
				if (prevLineValue !=null) {
					Number value = barSerie.get(date);
					value = value.doubleValue() * prevLineValue.doubleValue();
					TimeSeriesDataItem item = new TimeSeriesDataItem(period, value);
					timeSerie.add(item, false);
				}
			}
			cpt++;
		}

		return timeSerie;
	}


}
