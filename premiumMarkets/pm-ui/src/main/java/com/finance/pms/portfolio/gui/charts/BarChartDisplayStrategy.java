package com.finance.pms.portfolio.gui.charts;

import java.util.Date;
import java.util.SortedMap;

import org.jfree.data.time.TimeSeries;

public abstract class BarChartDisplayStrategy {

	public abstract void setBarYAxis();

	public abstract double maxBarValue(TimeSeries lineSerie);

	public abstract TimeSeries buildBarTimeSeries(String serieName, SortedMap<Date, Double> barSerie, TimeSeries lineSerie);

}
