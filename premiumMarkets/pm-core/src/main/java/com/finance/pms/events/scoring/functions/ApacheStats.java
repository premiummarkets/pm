package com.finance.pms.events.scoring.functions;

import java.util.Collection;

import org.apache.commons.math.stat.descriptive.AbstractUnivariateStatistic;

public class ApacheStats {
	
	AbstractUnivariateStatistic statistic;

	public ApacheStats(AbstractUnivariateStatistic statistic) {
		super();
		this.statistic = statistic;
	}
	
	public double evaluate(Collection<double[]> subMap) {
		double[] values = new double[subMap.size()];
		int i = 0;
		for (double[] element : subMap) {
			values[i] = element[0];
			i++;
		}
		
		return statistic.evaluate(values);
	}
	

}
