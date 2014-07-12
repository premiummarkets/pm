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
package com.finance.pms.events.calculation;

import java.security.InvalidAlgorithmParameterException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Observer;
import java.util.Set;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.config.IndicatorsConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.pounderationrules.DataResultReversedComparator;
import com.finance.pms.events.scoring.TunedConf;
import com.finance.pms.events.scoring.TunedConfMgr;
import com.finance.pms.events.scoring.TunedConfMgr.CalcStatus;
import com.finance.pms.events.scoring.TunedConfMgr.CalculationBounds;
import com.finance.pms.threads.ConfigThreadLocal;

public class FirstPassIndicatorCalculationThread extends IndicatorsCalculationThread {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(FirstPassIndicatorCalculationThread.class);
	
	private Integer macdFastPeriod;
	private Integer macdSlowPeriod;
	private Integer macdSignal;
	private Integer rsiLowerThreshold;
	private Integer rsiUpperThreshold;
	private Integer rsiTimePeriod;

	private Integer mfiTimePeriod;
	private Integer mfiLowerThres;
	private Integer mfiUpperThres;

	private Integer variationTimePeriod;
	private Integer variationSpanDiff;

	private Integer varianceTimePeriod;
	private Integer varianceSpanDiff;
	private Integer varianceMinValid;
	
	private Integer fastKLookBackPeriod;
	private Integer slowKSmaPeriod;
	private Integer slowDSmaPeriod;

	private List<EventInfo> firstPassWantedCalculations;

	private String passOneCalcMode;

	private Integer smaReversalPeriod;
	

	public FirstPassIndicatorCalculationThread(
				Stock stock, Date startDate, Date endDate, Currency calculationCurrency, String eventListName, Set<Observer> observers, 
				String passOneCalcMode, Boolean keepCache,Queue queue,JmsTemplate jmsTemplate, Boolean persistEvents) throws NotEnoughDataException {
		
		super(stock, startDate, endDate, eventListName, calculationCurrency, observers, keepCache, persistEvents, null, queue, jmsTemplate);
		this.passOneCalcMode = passOneCalcMode;
		
	}

	@Override
	protected void setCalculationParameters() {
		
		firstPassWantedCalculations = ((EventSignalConfig) ConfigThreadLocal.get("eventSignal")).getIndicators();
		
		variationTimePeriod = ((IndicatorsConfig) ConfigThreadLocal.get("indicatorParams")).getVariationPeriod();
		variationSpanDiff = ((IndicatorsConfig) ConfigThreadLocal.get("indicatorParams")).getVariationSpanDiff();
		
		varianceTimePeriod = ((IndicatorsConfig) ConfigThreadLocal.get("indicatorParams")).getVariancePeriod();
		varianceSpanDiff = ((IndicatorsConfig) ConfigThreadLocal.get("indicatorParams")).getVarianceSpanDiff();
		varianceMinValid = ((IndicatorsConfig) ConfigThreadLocal.get("indicatorParams")).getVarianceMinValid();
		
		macdFastPeriod = ((IndicatorsConfig) ConfigThreadLocal.get("indicatorParams")).getMacdFastPeriod();
		macdSlowPeriod = ((IndicatorsConfig) ConfigThreadLocal.get("indicatorParams")).getMacdSlowPeriod();
		macdSignal = ((IndicatorsConfig) ConfigThreadLocal.get("indicatorParams")).getMacdSignal();
		
		rsiLowerThreshold = ((IndicatorsConfig) ConfigThreadLocal.get("indicatorParams")).getRsiLowerThreshold();
		rsiUpperThreshold = ((IndicatorsConfig) ConfigThreadLocal.get("indicatorParams")).getRsiUpperThreshold();
		rsiTimePeriod = ((IndicatorsConfig) ConfigThreadLocal.get("indicatorParams")).getRsiTimePeriod();
		
		mfiTimePeriod = 14;
		mfiLowerThres = 20;
		mfiUpperThres = 80;
		
		fastKLookBackPeriod = 14;
		slowKSmaPeriod = 3;
		slowDSmaPeriod = 3;
		
		smaReversalPeriod = ((IndicatorsConfig) ConfigThreadLocal.get("indicatorParams")).getSmaReversalSmaPeriod();

	}

	@Override
	protected Set<EventCompostionCalculator> initIndicatorsAndCalculators(SymbolEvents symbolEventsForStock, Observer... observers) {
		
		LOGGER.info("First pass wanted events : "+getWantedEventCalculations());
		Set<EventCompostionCalculator> eventCalculations = new HashSet<EventCompostionCalculator>();
		
		//Which EventCompositions have been found a wanted in the EventConfig (indicators field)
		boolean zeroCrossMACDWanted = checkWanted(EventDefinition.PMMACDZEROCROSS);
		boolean signalCrossMACDWanted = checkWanted(EventDefinition.PMMACDSIGNALCROSS);
		boolean smaReversalWanted = checkWanted(EventDefinition.PMSMAREVERSAL);
		boolean rsiThresholdCrossWanted =checkWanted(EventDefinition.PMRSITHRESHOLD);
		boolean rsiDivergenceWanted =checkWanted(EventDefinition.PMRSIDIVERGENCE);
		boolean obvDivergenceWanted = checkWanted(EventDefinition.PMOBVDIVERGENCE);
		boolean mfiDivergenceWanted = checkWanted(EventDefinition.PMMFIDIVERGENCE);
		boolean mfiThresholdWanted = checkWanted(EventDefinition.PMMFITHRESHOLD);
		boolean varianceWanted = checkWanted(EventDefinition.VARIANCE);
		boolean variationWanted = checkWanted(EventDefinition.VARIATION);
		boolean stddevCrossWanted = checkWanted(EventDefinition.STDDEV);
		boolean stochDivergenceWanted = checkWanted(EventDefinition.PMSSTOCHDIVERGENCE);
		boolean accDistDivergenceWanted = checkWanted(EventDefinition.PMACCDISTDIVERGENCE);
		boolean aroonTrendWanted = checkWanted(EventDefinition.PMAROONTREND);
		boolean chaikinOscDivergenceWanted =  checkWanted(EventDefinition.PMCHAIKINOSCDIVERGENCE);
		boolean chaikinOscThresholdWanted =  checkWanted(EventDefinition.PMCHAIKINOSCTHRESHOLD);
		boolean stochThresholdWanted = checkWanted(EventDefinition.PMSSTOCHTHRESHOLD);
		
		boolean mighyChaikinWanted = checkWanted(EventDefinition.PMMIGHTYCHAIKIN);
		boolean mfiDivergenceOldWanted = checkWanted(EventDefinition.PMMFIDIVERGENCEOLD);
		boolean rsiDivergenceOldWanted = checkWanted(EventDefinition.PMRSIDIVERGENCEOLD);
		boolean stochDivergenceOldWanted = checkWanted(EventDefinition.PMSSTOCHDIVERGENCEOLD);
		
		Integer startDateShift = 0;
		if (zeroCrossMACDWanted) {
				ZeroCrossMACDEventCalculator zeroCrossMACDEventCalculator = new ZeroCrossMACDEventCalculator(macdFastPeriod, macdSlowPeriod, macdSignal, observers);
				startDateShift = Math.max(startDateShift, zeroCrossMACDEventCalculator.getStartShift());
				eventCalculations.add(zeroCrossMACDEventCalculator);
		}
		
		if (signalCrossMACDWanted) {
				SignalCrossMACDEventCalculator signalCrossMACDEventCalculator = new SignalCrossMACDEventCalculator(macdFastPeriod, macdSlowPeriod, macdSignal, observers);
				startDateShift =  Math.max(startDateShift, signalCrossMACDEventCalculator.getStartShift());
				eventCalculations.add(signalCrossMACDEventCalculator);
		}
		
		if (smaReversalWanted) {
			SmaReversal smaReversal = new SmaReversal(smaReversalPeriod, observers);
			startDateShift =  Math.max(startDateShift, smaReversal.getStartShift());
			eventCalculations.add(smaReversal);
		}
	
		if (rsiThresholdCrossWanted) {
			RSIThreshold rSIThreshold = new RSIThreshold(rsiTimePeriod, rsiLowerThreshold, rsiUpperThreshold, observers);
			startDateShift =  Math.max(startDateShift, rSIThreshold.getStartShift());
			eventCalculations.add(rSIThreshold);
		}
		
		if (rsiDivergenceWanted) {
			RSIDivergence rSIDivergence = new RSIDivergence(rsiTimePeriod, rsiUpperThreshold, rsiLowerThreshold, observers);
			startDateShift =  Math.max(startDateShift, rSIDivergence.getStartShift());
			eventCalculations.add(rSIDivergence);	
		}
		
		if (obvDivergenceWanted) {
			ObvDivergence obvDivergence = new ObvDivergence(observers);
			startDateShift =  Math.max(startDateShift, obvDivergence.getStartShift());
			eventCalculations.add(obvDivergence);
		}
		
		if (mfiDivergenceWanted) {
			MFIDivergence mfiDivergence = new MFIDivergence(mfiTimePeriod, mfiLowerThres, mfiUpperThres, observers);
			startDateShift =  Math.max(startDateShift, mfiDivergence.getStartShift());
			eventCalculations.add(mfiDivergence);
			
		}
		
		if (mfiThresholdWanted) {
			MFIThreshold mfiThreshold = new MFIThreshold(mfiTimePeriod, mfiLowerThres, mfiUpperThres, observers);
			startDateShift =  Math.max(startDateShift, mfiThreshold.getStartShift());
			eventCalculations.add(mfiThreshold);
			
		}
		
		if (stochThresholdWanted) {
			StochasticThreshold stochThreshold = new StochasticThreshold(fastKLookBackPeriod, slowKSmaPeriod, slowDSmaPeriod, observers);
			startDateShift =  Math.max(startDateShift, stochThreshold.getStartShift());
			eventCalculations.add(stochThreshold);
		}
		

		if (stochDivergenceWanted) {
			StochasticDivergence stochDiv = new StochasticDivergence(fastKLookBackPeriod, slowKSmaPeriod, slowDSmaPeriod, observers);
			startDateShift =  Math.max(startDateShift, stochDiv.getStartShift());
			eventCalculations.add(stochDiv);
		}
		
		if (accDistDivergenceWanted) {
			AccumulationDistributionDivergence accDistDiv = new AccumulationDistributionDivergence(observers);
			startDateShift =  Math.max(startDateShift, accDistDiv.getStartShift());
			eventCalculations.add(accDistDiv);
			
		}
		
		if (chaikinOscDivergenceWanted) {
			ChaikinOscillatorDivergence chaikinOscDiv = new ChaikinOscillatorDivergence(3, 10, observers);
			startDateShift =  Math.max(startDateShift, chaikinOscDiv.getStartShift());
			eventCalculations.add(chaikinOscDiv);
		}
		
		if (chaikinOscThresholdWanted) {
			ChaikinOscillatorThreshold chaikinOscThreshold = new ChaikinOscillatorThreshold(3, 10, observers);
			startDateShift =  Math.max(startDateShift, chaikinOscThreshold.getStartShift());
			eventCalculations.add(chaikinOscThreshold);
		}
		
		if (aroonTrendWanted) {
			AroonTrend aroonTrend = new AroonTrend(observers);
			startDateShift =  Math.max(startDateShift, aroonTrend.getStartShift());
			eventCalculations.add(aroonTrend);
		}
		
		//Variation
		if (variationWanted) {
				VariationCalculator variationCalc = new VariationCalculator(stock, variationTimePeriod, variationSpanDiff, eventListName, observers);
				startDateShift =  Math.max(startDateShift, variationCalc.getStartShift());
				eventCalculations.add(variationCalc);
		}
		
		//Variance
		if (varianceWanted) {
			VarianceCalculator varianceCalc = new VarianceCalculator(stock, varianceTimePeriod, varianceSpanDiff, varianceMinValid, varianceTimePeriod,eventListName, observers);
			startDateShift =  Math.max(startDateShift, varianceCalc.getStartShift());
			eventCalculations.add(varianceCalc);
		}
		
		//stdDev
		if (stddevCrossWanted ) {
			StandardDeviationCrossing stdevCross = new StandardDeviationCrossing(observers);
			startDateShift =  Math.max(startDateShift, stdevCross.getStartShift());
			eventCalculations.add(stdevCross);
		}
		
		//Events old divergences (Before high and low change) !ChaikinOscillatorDivergence_old is still in use as MighyChaikin
		if (mighyChaikinWanted) {
			ChaikinOscillatorDivergence_old chaikinOscillDiv_old = new ChaikinOscillatorDivergence_old(3, 10, observers);
			startDateShift =  Math.max(startDateShift, chaikinOscillDiv_old.getStartShift());
			eventCalculations.add(chaikinOscillDiv_old);
		
		}
		if (rsiDivergenceOldWanted) {
			RSIDivergence_old rSIDivergence_old = new RSIDivergence_old(rsiTimePeriod, rsiLowerThreshold, rsiUpperThreshold, observers);
			startDateShift =  Math.max(startDateShift, rSIDivergence_old.getStartShift());
			eventCalculations.add(rSIDivergence_old);
		}
		
		if (mfiDivergenceOldWanted) {
			MFIDivergence_old mfiDiv = new MFIDivergence_old(mfiTimePeriod, mfiLowerThres, mfiUpperThres, observers);
			startDateShift =  Math.max(startDateShift, mfiDiv.getStartShift());
			eventCalculations.add(mfiDiv);
		}
		
		if (stochDivergenceOldWanted) {
			StochasticDivergence_old stochDiv = new StochasticDivergence_old(fastKLookBackPeriod, slowKSmaPeriod, slowDSmaPeriod, observers);
			startDateShift =  Math.max(startDateShift, stochDiv.getStartShift());
			eventCalculations.add(stochDiv);
			
		}

		return eventCalculations;
	}


	@Override
	protected List<EventInfo> getWantedEventCalculations() {
		return firstPassWantedCalculations;
	}
	
	@Override
	public void cleanEventsFor(String eventListName, Date datedeb, Date datefin, Boolean persist) {//We don't clean first pass event also they can be cleaned through the ui using 'Clean all previous calculations'
		//EventsResources.getInstance().crudDeleteEventsForStock(stock, eventListName, datedeb, datefin, persist, EventDefinition.loadFirstPassPrefEventDefinitions().toArray(new EventInfo[0]));
	}

	@Override
	protected void calculate(SymbolEvents symbolEventsForStock, List<IncompleteDataSetException> dataSetExceptions) throws NotEnoughDataException, InvalidAlgorithmParameterException {
		
		TunedConf tunedConf = TunedConfMgr.getInstance().loadUniqueNoRetuneConfig(stock, ((EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getConfigListFileName());

		synchronized (tunedConf) {
			
			CalculationBounds calculationBounds = TunedConfMgr.getInstance().new CalculationBounds(CalcStatus.IGNORE, startDate, endDate);
			if (passOneCalcMode.equals("auto")) {
				endDate = TunedConfMgr.getInstance().endDateConsistencyCheck(tunedConf, stock, endDate);
				calculationBounds = TunedConfMgr.getInstance().autoCalcAndSetDatesBounds(tunedConf, stock, startDate, endDate);
			}
			if (passOneCalcMode.equals("reset")) {
				endDate = TunedConfMgr.getInstance().endDateConsistencyCheck(tunedConf, stock, endDate);
				tunedConf.setLastCalculationStart(startDate);
				tunedConf.setLastCalculationEnd(endDate);
				calculationBounds = TunedConfMgr.getInstance().new CalculationBounds(CalcStatus.RESET, startDate, endDate);
			}
			
			startDate = calculationBounds.getPmStart();
			endDate = calculationBounds.getPmEnd();

			if (!calculationBounds.getCalcStatus().equals(CalcStatus.NONE)) {
				super.calculate(symbolEventsForStock, dataSetExceptions);
			} else {
				LOGGER.info(
						"Pass 1 events recalculation requested for "+stock.getSymbol()+" using analysis "+eventListName+" from "+startDate+" to "+endDate+". "+
						"No recalculation needed calculation bound is "+ calculationBounds.toString());
			}

			if (dataSetExceptions.isEmpty()) {
				//if We inc or reset, tuned conf last event will need update : We add it in the map
				if ( (calculationBounds.getCalcStatus().equals(CalcStatus.INC) || calculationBounds.getCalcStatus().equals(CalcStatus.RESET)) && symbolEventsForStock.getDataResultMap().size() > 0) {
					TunedConfMgr.getInstance().updateConf(tunedConf, symbolEventsForStock.getStock(), symbolEventsForStock.getSortedDataResultList(new DataResultReversedComparator()).get(0).getDate());
				}
			} else {//Error(s) as occurred. This should invalidate tuned conf
				TunedConfMgr.getInstance().resetConf(tunedConf, stock);
			}
			
		}

	}

}
