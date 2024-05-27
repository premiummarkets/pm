package com.finance.pms.events.scoring.chartUtils;

import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.data.time.TimeSeriesCollection;

public final class MyTimeSeriesCollection extends TimeSeriesCollection {

	private static final long serialVersionUID = 8952089723108435323L;
	
	Map<Integer, XYToolTipGenerator> toolTipGenerator = new HashMap<>();

	public XYToolTipGenerator getToolTipGenerator(int series) {
		return toolTipGenerator.get(series);
	}

	public void addToolTipGenerator(int series, XYToolTipGenerator toolTipGenerator) {
		this.toolTipGenerator.put(series, toolTipGenerator);
	}
	
}