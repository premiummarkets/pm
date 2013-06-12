/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.calculation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Observer;
import java.util.Set;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.config.IndicatorsConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.houseIndicators.HouseAroon;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.talib.indicators.ChaikinLine;
import com.finance.pms.talib.indicators.ChaikinOscillator;
import com.finance.pms.talib.indicators.MACD;
import com.finance.pms.talib.indicators.MFI;
import com.finance.pms.talib.indicators.OBV;
import com.finance.pms.talib.indicators.RSI;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.StandardDeviation;
import com.finance.pms.talib.indicators.StochasticOscillator;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.threads.ConfigThreadLocal;

public class FirstPassIndicatorCalculationThread extends IndicatorsCalculationThread {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(FirstPassIndicatorCalculationThread.class);
	
	//private Integer smaPeriod;
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


	
	public FirstPassIndicatorCalculationThread(
				Stock stock, Date startDate, Date endDate, Currency calculationCurrency, String eventListName, 
				Set<Observer> observers, Boolean keepCache,Queue queue,JmsTemplate jmsTemplate, Boolean persistEvents) throws NotEnoughDataException {
		
		super(stock, startDate, endDate, eventListName, calculationCurrency, observers, false, keepCache, persistEvents, null, queue, jmsTemplate);
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

	}

	/**
	 * @param quotations
	 * @return
	 * @throws IncompleteDataSetException 
	 */
	@Override
	protected Set<EventCompostionCalculator> initIndicatorsAndCalculators(Observer... observers) throws IncompleteDataSetException {
		
		//TODO get formulas stored in DB or from spring config
		Set<EventCompostionCalculator> eventCalculations = new HashSet<EventCompostionCalculator>();
		
		//List of Event Compositions to be calculated
		MACD macd = null;
		SMA sma200 = null;
		RSI rsi = null;
		OBV obv = null;
		MFI mfi = null;
		StandardDeviation standardDeviation = null;
		StochasticOscillator stoch = null;
		ChaikinLine chaikinLine = null;
		ChaikinOscillator chaikinOscillator = null;
		HouseAroon aroon = null;
	
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
		
//		boolean parameterizedWanted = checkWanted(EventDefinition.PARAMETRIZED);
		
		//Which Indicators
		boolean isSMA200Ok = true;
		boolean isMACDOk = true;
		boolean isOBVOk = true;
		boolean isRSIOk = true;
		boolean isMFIOk = true;
		boolean isStddevOk = true;
		boolean isStochOk = true;
		boolean isChaikinOk = true;
		boolean isAroonOk = true;
		boolean isChaikinOscOk = true;

		
		if (mfiDivergenceWanted || zeroCrossMACDWanted || signalCrossMACDWanted || rsiThresholdCrossWanted || stddevCrossWanted) {
			try {
				sma200 = new SMA(stock, 200, startDate, endDate, calculationCurrency);
			} catch (NoQuotationsException e) {
				isSMA200Ok = false;
				LOGGER.warn(e);
			} catch (TalibException e) {
				isSMA200Ok = false;
				LOGGER.warn(e);
			} catch (Exception e) {
				isSMA200Ok = false;
				LOGGER.error(e,e);
			}
		}
		
		if (stochDivergenceWanted) {
			try {
				aroon = new HouseAroon(stock,  startDate, endDate, calculationCurrency, 25);
			} catch (NoQuotationsException e) {
				isAroonOk = false;
				LOGGER.warn(e);
			} catch (TalibException e) {
				isAroonOk = false;
				LOGGER.warn(e);
			} catch (Exception e) {
				isAroonOk = false;
				LOGGER.warn(e,e);
			}
		}
		
		if (zeroCrossMACDWanted || signalCrossMACDWanted) {
			try {
				macd = new MACD(stock, macdFastPeriod, macdSlowPeriod, macdSignal, startDate, endDate, calculationCurrency);
			} catch (NoQuotationsException e) {
				isMACDOk = false;
				LOGGER.warn(e);
			} catch (TalibException e) {
				isMACDOk = false;
				LOGGER.warn(e);
			} catch (Exception e) {
				isMACDOk = false;
				LOGGER.error(e,e);
			}
		}
		if (rsiThresholdCrossWanted || rsiDivergenceWanted) {
			try {
				rsi = new RSI(stock, rsiTimePeriod, rsiUpperThreshold, rsiLowerThreshold, startDate, endDate, calculationCurrency);
			} catch (NoQuotationsException e) {
				isRSIOk = false;
				LOGGER.warn(e);
			} catch (TalibException e) {
				isRSIOk = false;
				LOGGER.warn(e);
			} catch (Exception e) {
				isRSIOk = false;
				LOGGER.error(e,e);
			}
		}
		if (obvDivergenceWanted) {
			try {
				obv = new OBV(stock, startDate, endDate, calculationCurrency);
			} catch (NoQuotationsException e) {
				isOBVOk = false;
				LOGGER.warn(e);
			} catch (TalibException e) {
				isOBVOk = false;
				LOGGER.warn(e);
			} catch (Exception e) {
				isOBVOk = false;
				LOGGER.error(e,e);
			}
		}
		if (mfiDivergenceWanted || mfiThresholdWanted) {
			try {
				mfi = new MFI(stock, mfiTimePeriod, mfiLowerThres, mfiUpperThres, startDate, endDate, calculationCurrency);
			} catch (NoQuotationsException e) {
				isMFIOk = false;
				LOGGER.warn(e);
			} catch (TalibException e) {
				isMFIOk = false;
				LOGGER.warn(e);
			} catch (Exception e) {
				isMFIOk = false;
				LOGGER.error(e,e);
			}
		}
	
		if (stochDivergenceWanted || stochThresholdWanted) {
			try {
				stoch = new StochasticOscillator(stock, startDate, endDate, calculationCurrency, fastKLookBackPeriod, slowKSmaPeriod, slowDSmaPeriod);
			} catch (NoQuotationsException e) {
				isStochOk = false;
				LOGGER.warn(e);
			} catch (TalibException e) {
				isStochOk = false;
				LOGGER.warn(e);
			} catch (Exception e) {
				isStochOk = false;
				LOGGER.error(e,e);
			}
		}
		
		if (accDistDivergenceWanted || obvDivergenceWanted) {
			try {
				chaikinLine = new ChaikinLine(stock, startDate, endDate, calculationCurrency);
			} catch (NoQuotationsException e) {
				isChaikinOk = false;
				LOGGER.warn(e);
			} catch (TalibException e) {
				isChaikinOk = false;
				LOGGER.warn(e);
			} catch (Exception e) {
				isChaikinOk = false;
				LOGGER.error(e,e);
			}
		}
		
		if (chaikinOscDivergenceWanted || chaikinOscThresholdWanted) {
			try {
				chaikinOscillator = new ChaikinOscillator(stock, startDate, endDate, calculationCurrency);
			} catch (NoQuotationsException e) {
				isChaikinOscOk = false;
				LOGGER.warn(e);
			} catch (TalibException e) {
				isChaikinOscOk = false;
				LOGGER.warn(e);
			} catch (Exception e) {
				isChaikinOscOk = false;
				LOGGER.error(e,e);
			}
		}
		
		if (stddevCrossWanted) {
			try {
				standardDeviation = new StandardDeviation(stock, 20, 1.0, startDate, endDate, calculationCurrency);
			} catch (NoQuotationsException e) {
				isStddevOk = false;
				LOGGER.warn(e);
			} catch (TalibException e) {
				isStddevOk = false;
				LOGGER.warn(e);
			} catch (Exception e) {
				isStddevOk = false;
				LOGGER.error(e,e);
			}
		}
		
		//Which Calculators
		boolean zeroCrossMACDOk = true;;
		boolean signalCrossMACDOk = true;
		boolean smaReversalOk = true;
		boolean rsiThresholdCrossOk = true;
		boolean rsiDivergenceOk = true;
		boolean obvDivergenceOk = true;
		boolean mfiDivergenceOk = true;
		boolean mfiThresholdOk = true;
		boolean varianceOk = true;
		boolean variationOk = true;
		boolean stddevCrossOk = true;
		boolean stockDivergenceOk = true;
		boolean chaikinDivergenceOk = true;
		boolean aroonTrendOk = true;
		boolean chaikinOscDivergenceOk = true;
		boolean chaikinOscThresholdOk = true;
		boolean stochThresholdOk = true;
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (zeroCrossMACDWanted && isMACDOk && isSMA200Ok) {
			try {
				eventCalculations.add(new ZeroCrossMACDEventCalculator(stock, macd, sma200, startDate, endDate, calculationCurrency));
			} catch (NotEnoughDataException e) {
				if (e.getShiftedStartDate() != null) {
					LOGGER.warn(warnMessage("ZeroCrossMACD",startDate, endDate) + butMessage(simpleDateFormat,e));
				} else {
					LOGGER.error("Failed calculation : "+warnMessage("ZeroCrossMACD",new Date(), new Date()), e);
				}
				zeroCrossMACDOk = false;
			}
		}
		
		if (signalCrossMACDWanted && isMACDOk && isSMA200Ok) {
			try {
				eventCalculations.add(new SignalCrossMACDEventCalculator(stock, macd, sma200, startDate, endDate, calculationCurrency));
			} catch (NotEnoughDataException e) {
				if (e.getShiftedStartDate() != null) {
					LOGGER.warn(warnMessage("SignalCrossMACD",startDate, endDate) + butMessage(simpleDateFormat, e));
				} else {
					LOGGER.error("Failed calculation : "+warnMessage("SignalCrossMACD",new Date(), new Date()), e);
				}
				signalCrossMACDOk = false;
			}
		}
		
		if (smaReversalWanted) {
			try {
				eventCalculations.add(new SmaReversal(stock, startDate, endDate, calculationCurrency));
			} catch (NotEnoughDataException e) {
				if (e.getShiftedStartDate() != null) {
					LOGGER.warn(warnMessage("SmaReversal",startDate, endDate) + butMessage(simpleDateFormat, e));
				} else {
					LOGGER.warn("Failed calculation : "+warnMessage("SmaReversal",new Date(), new Date()), e);
				}
				smaReversalOk = false;
			} 
		}
	
		if (rsiThresholdCrossWanted && isSMA200Ok && isRSIOk) {
			try {
				eventCalculations.add(new RSIThreshold(stock, sma200, rsi, startDate, endDate, calculationCurrency));
			} catch (NotEnoughDataException e) {
				if (e.getShiftedStartDate() != null) {
					LOGGER.warn(warnMessage("RSIThresholdCross",startDate, endDate) + butMessage(simpleDateFormat, e));
				} else {
					LOGGER.error("Failed calculation : "+warnMessage("RSIThresholdCross",new Date(), new Date()), e);
				}
				rsiThresholdCrossOk = false;
			}
		}
		
		if (rsiDivergenceWanted && isRSIOk) {
			try {
				eventCalculations.add(new RSIDivergence(stock, rsi, startDate, endDate, calculationCurrency));
			} catch (NotEnoughDataException e) {
				if (e.getShiftedStartDate() != null) {
					LOGGER.warn(warnMessage("RSIDivergence",startDate, endDate) + butMessage(simpleDateFormat, e));
				} else {
					LOGGER.error("Failed calculation : "+warnMessage("MFIDivergence",new Date(), new Date()), e);
				}
				rsiDivergenceOk = false;
			}
		}
		
		if (obvDivergenceWanted && isOBVOk && isChaikinOk) {
			try {
				eventCalculations.add(new ObvDivergence(stock, obv, chaikinLine, startDate, endDate, calculationCurrency));
			} catch (NotEnoughDataException e) {
				if (e.getShiftedStartDate() != null) {
					LOGGER.warn(warnMessage("ObvDivergence",startDate, endDate) + butMessage(simpleDateFormat, e));
				} else {
					LOGGER.error("Failed calculation : "+warnMessage("ObvDivergence",new Date(), new Date()), e);
				}
				obvDivergenceOk = false;
			}
		}
		
		if (mfiDivergenceWanted && isMFIOk) {
			try {
				eventCalculations.add(new MFIDivergence(stock, mfi, startDate, endDate, calculationCurrency));
			} catch (NotEnoughDataException e) {
				if (e.getShiftedStartDate() != null) {
					LOGGER.warn(warnMessage("MFIDivergence",startDate, endDate) + butMessage(simpleDateFormat, e));
				} else {
					LOGGER.error("Failed calculation : "+warnMessage("MFIDivergence",new Date(), new Date()), e);
				}
				mfiDivergenceOk = false;
			}
		}
		
		if (mfiThresholdWanted && isMFIOk) {
			try {
				eventCalculations.add(new MFIThreshold(stock, mfi, startDate, endDate, calculationCurrency));
			} catch (NotEnoughDataException e) {
				if (e.getShiftedStartDate() != null) {
					LOGGER.warn(warnMessage("MFIThreshold",startDate, endDate) + butMessage(simpleDateFormat, e));
				} else {
					LOGGER.error("Failed calculation : "+warnMessage("MFIThreshold",new Date(), new Date()), e);
				}
				mfiThresholdOk = false;
			}
		}
		
		if (stochThresholdWanted && isStochOk && isAroonOk) {
			try {
				eventCalculations.add(new StochasticThreshold(stock, stoch, aroon, startDate, endDate, calculationCurrency));
			} catch (NotEnoughDataException e) {
				if (e.getShiftedStartDate() != null) {
					LOGGER.warn(warnMessage("StochasticThreshold", startDate, endDate) + butMessage(simpleDateFormat, e));
				} else {
					LOGGER.error("Failed calculation : "+warnMessage("StochasticThreshold",new Date(), new Date()), e);
				}
				stochThresholdOk = false;
			}
		}
		

		if (stochDivergenceWanted && isStochOk && isAroonOk) {
			try {
				eventCalculations.add(new StochasticDivergence(stock, stoch, aroon, startDate, endDate, calculationCurrency));
			} catch (NotEnoughDataException e) {
				if (e.getShiftedStartDate() != null) {
					LOGGER.warn(warnMessage("StochasticDivergence", startDate, endDate) + butMessage(simpleDateFormat, e));
				} else {
					LOGGER.error("Failed calculation : "+warnMessage("StochasticDivergence",new Date(), new Date()), e);
				}
				stockDivergenceOk = false;
			}
		}
		
		if (accDistDivergenceWanted && isChaikinOk) {
			try {
				eventCalculations.add(new AccumulationDistributionDivergence(stock, chaikinLine, startDate, endDate, calculationCurrency));
			} catch (NotEnoughDataException e) {
				if (e.getShiftedStartDate() != null) {
					LOGGER.warn(warnMessage("ChaikinDivergence", startDate, endDate) + butMessage(simpleDateFormat, e));
				} else {
					LOGGER.error("Failed calculation : "+warnMessage("ChaikinDivergence", new Date(), new Date()), e);
				}
				chaikinDivergenceOk = false;
			}
		}
		
		if (chaikinOscDivergenceWanted && isChaikinOscOk) {
			try {
				eventCalculations.add(new ChaikinOscillatorDivergence(stock, chaikinOscillator, startDate, endDate, calculationCurrency));
			} catch (NotEnoughDataException e) {
				if (e.getShiftedStartDate() != null) {
					LOGGER.warn(warnMessage("ChaikinOscillatorDivergence", startDate, endDate) + butMessage(simpleDateFormat, e));
				} else {
					LOGGER.error("Failed calculation : "+warnMessage("ChaikinOscillatorDivergence", new Date(), new Date()), e);
				}
				chaikinOscDivergenceOk = false;
			}
		}
		
		if (chaikinOscThresholdWanted && isChaikinOscOk) {
			try {
				eventCalculations.add(new ChaikinOscillatorThreshold(stock, chaikinOscillator, startDate, endDate, calculationCurrency));
			} catch (NotEnoughDataException e) {
				if (e.getShiftedStartDate() != null) {
					LOGGER.warn(warnMessage("ChaikinOscillatorThreshold", startDate, endDate) + butMessage(simpleDateFormat, e));
				} else {
					LOGGER.error("Failed calculation : "+warnMessage("ChaikinOscillatorThreshold", new Date(), new Date()), e);
				}
				chaikinOscThresholdOk = false;
			}
		}
		
		if (aroonTrendWanted) {
			try {
				eventCalculations.add(new AroonTrend(stock, startDate, endDate, calculationCurrency));
			} catch (NotEnoughDataException e) {
				if (e.getShiftedStartDate() != null) {
					LOGGER.warn(warnMessage("AroonTrend", startDate, endDate) + butMessage(simpleDateFormat, e));
				} else {
					LOGGER.warn("Failed calculation : "+warnMessage("AroonTrend", new Date(), new Date()), e);
				}
				aroonTrendOk = false;
			}
		}
		
		//Variation
		if (variationWanted) {
			try {
				eventCalculations.add(new VariationCalculator(stock, variationTimePeriod, variationSpanDiff, startDate, endDate, calculationCurrency, this.eventListName));
			} catch (NotEnoughDataException e) {
				LOGGER.error("Failed calculation : " + warnMessage("Variation", e.getShiftedStartDate(), e.getShiftedEndDate()));
				variationOk = false;
			}
		}
		
		//Variance
		if (varianceWanted) {
			try {
				eventCalculations.add(new VarianceCalculator(stock, varianceTimePeriod, varianceSpanDiff, varianceMinValid, startDate, endDate, calculationCurrency, this.eventListName));
			} catch (NotEnoughDataException e) {
				LOGGER.error("Failed calculation : "+warnMessage("Variance",e.getShiftedStartDate(), e.getShiftedEndDate()));
				varianceOk = false;
			}
		}
		
		//stdDev
		if (stddevCrossWanted && isStddevOk) {
			try {
				eventCalculations.add(new StandardDeviationCrossing(stock, standardDeviation, startDate, endDate, calculationCurrency, this.eventListName));
			} catch (NotEnoughDataException e) {
				LOGGER.error("Failed calculation : "+warnMessage("Std Dev",e.getShiftedStartDate(), e.getShiftedEndDate()));
				stddevCrossOk = false;
			}
		}
		
		if (
				!rsiDivergenceOk|| !zeroCrossMACDOk || !signalCrossMACDOk || !smaReversalOk || !rsiThresholdCrossOk ||
				!obvDivergenceOk || !mfiDivergenceOk || !mfiThresholdOk || !varianceOk || 
				!variationOk || !stddevCrossOk || !stockDivergenceOk ||
				!chaikinDivergenceOk || !aroonTrendOk|| !chaikinOscDivergenceOk || !stochThresholdOk || !chaikinOscThresholdOk
				
			) {
			
			String error = "One of the composite indicator calculation as failed for "+stock+"\n" +
							" actual state is : " +
							"rsiDivergenceOk, zeroCrossMACDOk, signalCrossMACDOk, smaReversalOk, rsiThresholdCrossOk, " +
							"obvDivergenceOk, mfiDivergenceOk, mfiThresholdOk, varianceOk, " +
							"variationOk, stddevCrossOk, stockDivergenceOk, " +
							"chaikinDivergenceOk, aroonTrendOk, chaikinOscDivergenceOk, stochThresholdOk, chaikinOscThresholdOk"+
							rsiDivergenceOk+","+zeroCrossMACDOk +", "+ signalCrossMACDOk+", "+ smaReversalOk+", "+rsiThresholdCrossOk+", "+ 
							obvDivergenceOk+", "+ mfiDivergenceOk+", "+ mfiThresholdOk+", "+ varianceOk+", "+  
							variationOk+", "+ stddevCrossOk+", "+stockDivergenceOk+", "+
							chaikinDivergenceOk+","+aroonTrendOk+","+chaikinOscDivergenceOk+","+stochThresholdOk+", "+chaikinOscThresholdOk;
			LOGGER.warn(error);
			
			throw new IncompleteDataSetException(stock, eventCalculations, error);
		}
		
		return eventCalculations;
	}

	/**
	 * @param simpleDateFormat
	 * @param e
	 * @return
	 */
	private String butMessage(SimpleDateFormat simpleDateFormat, NotEnoughDataException e) {
		String butMessage = " but maybe from " + simpleDateFormat.format(e.getShiftedStartDate()) + " to " + simpleDateFormat.format(e.getShiftedEndDate());
		return butMessage;
	}

	@Override
	protected List<EventInfo> getWantedEventCalculations() {
		return firstPassWantedCalculations;
	}
	

	@Override
	public void cleanEventsFor(String eventListName, Date datedeb, Date datefin, Boolean persist) {
		// Nothing as Talib events are not clean but overridden
	}

}
