package com.finance.pms.datasources;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.calculation.IncompleteDataSetException;
import com.finance.pms.events.calculation.IndicatorAnalysisCalculationRunnableMessage;


public class RefreshChartHightlited extends UserContentStrategyEngine {

	protected Map<Stock, Map<EventDefinition, SortedMap<Date, double[]>>> runPassTwo(IndicatorAnalysisCalculationRunnableMessage actionThread) throws InterruptedException, IncompleteDataSetException {
		return actionThread.runIndicatorsCalculationPassTwo(true);
	}

	protected Map<Stock, Map<EventDefinition, SortedMap<Date, double[]>>> runPassOne(IndicatorAnalysisCalculationRunnableMessage actionThread) throws InterruptedException, IncompleteDataSetException {
		return actionThread.runIndicatorsCalculationPassOne(true , "force");
	}

}
