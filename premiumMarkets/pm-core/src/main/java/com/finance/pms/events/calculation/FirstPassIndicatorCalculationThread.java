/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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
import com.finance.pms.talib.indicators.MACD;
import com.finance.pms.talib.indicators.MFI;
import com.finance.pms.talib.indicators.OBV;
import com.finance.pms.talib.indicators.RSI;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.StandardDeviation;
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

	private List<EventDefinition> firstPassWantedCalculations;


	
	public FirstPassIndicatorCalculationThread(Stock stock, Date startDate, Date endDate, Currency calculationCurrency, String eventListName, 
											Set<Observer> observers, Boolean keepCache,Queue queue,JmsTemplate jmsTemplate) throws NotEnoughDataException {
		
		super(stock, startDate, endDate, eventListName, calculationCurrency, observers, false, keepCache, null, queue, jmsTemplate);
		//eventsCalculators = initIndicatorsAndCalculators();
	}

	@Override
	protected void setCalculationParameters() {
		
		firstPassWantedCalculations = ((EventSignalConfig) ConfigThreadLocal.get("eventSignal")).getIndicators();
		
		//smaPeriod = ((IndicatorsConfig) ConfigThreadLocal.get("indicatorParams")).getSmaPeriod();
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
		
		//stdDevPeriod = 10;
	}

	/**
	 * @param quotations
	 * @return
	 */
	@Override
	protected Set<EventCompostionCalculator> initIndicatorsAndCalculators(Observer... observers) {
		
		//List of Event Compositions to be calculated 
		//TODO get formulas stored in DB or from spring config
		Set<EventCompostionCalculator> eventCalculations = new HashSet<EventCompostionCalculator>();
		
		MACD macd = null;
		SMA sma200 = null;
		RSI rsi = null;
		OBV obv = null;
		MFI mfi = null;
		StandardDeviation standardDeviation = null;
	
		boolean zeroCrossMACDWanted = checkWanted(EventDefinition.PMMACDZEROCROSS);
		boolean signalCrossMACDWanted = checkWanted(EventDefinition.PMMACDSIGNALCROSS);
		boolean smaReversalWanted = checkWanted(EventDefinition.PMSMAREVERSAL);
		boolean rsiThresholdCrossWanted =checkWanted(EventDefinition.PMRSITHRESHOLD);
		boolean obvDivergenceWanted = checkWanted(EventDefinition.PMOBVDIVERGENCE);
		boolean mfiDivergenceWanted = checkWanted(EventDefinition.PMMFIDIVERGENCE);
		boolean mfiThresholdWanted = checkWanted(EventDefinition.PMMFITHRESHOLD);
		boolean varianceWanted = checkWanted(EventDefinition.VARIANCE);
		boolean variationWanted = checkWanted(EventDefinition.VARIATION);
		boolean stddevCrossWanted = checkWanted(EventDefinition.STDDEV);

		
		//Which Indicators
		boolean isSMA200Ok = true;;
		boolean isMACDOk = true;
		boolean isOBVOk = true;
		boolean isRSIOk = true;
		boolean isMFIOk = true;
		boolean isStddevOk = true;

		
		if (mfiDivergenceWanted || zeroCrossMACDWanted || signalCrossMACDWanted || rsiThresholdCrossWanted || stddevCrossWanted) {
			try {
				sma200 = new SMA(stock, 200, startDate, endDate, calculationCurrency);
			} catch (Exception e) {
				isSMA200Ok = false;
				LOGGER.error(e);
			}
		}
		
		if (zeroCrossMACDWanted || signalCrossMACDWanted) {
			try {
				macd = new MACD(stock, macdFastPeriod, macdSlowPeriod, macdSignal, startDate, endDate, calculationCurrency);
			} catch (Exception e) {
				isMACDOk = false;
				LOGGER.error(e);
			}
		}
		if (rsiThresholdCrossWanted) {
			try {
				rsi = new RSI(stock,rsiTimePeriod,rsiUpperThreshold,rsiLowerThreshold, startDate, endDate, calculationCurrency);
			} catch (Exception e) {
				isRSIOk = false;
				LOGGER.error(e);
			}
		}
		if (obvDivergenceWanted) {
			try {
				obv = new OBV(stock, startDate, endDate, calculationCurrency);
			} catch (Exception e) {
				isOBVOk = false;
				LOGGER.error(e);
			}
		}
		if (mfiDivergenceWanted || mfiThresholdWanted) {
			try {
				mfi = new MFI(stock, mfiTimePeriod, mfiLowerThres, mfiUpperThres, startDate, endDate, calculationCurrency);
			} catch (Exception e) {
				isMFIOk = false;
				LOGGER.error(e);
			}
		}
		
		if (stddevCrossWanted) {
			try {
				standardDeviation = new StandardDeviation(stock, 20, 1.0, startDate, endDate, calculationCurrency);
			} catch (Exception e) {
				isMFIOk = false;
				LOGGER.error(e);
			}
		}
		
		//Which Calculators
		boolean zeroCrossMACDOk = true;;
		boolean signalCrossMACDOk = true;
		boolean smaReversalOk = true;
		boolean rsiThresholdCrossOk = true;
		boolean obvDivergenceOk = true;
		boolean mfiDivergenceOk = true;
		boolean mfiThresholdOk = true;
		boolean varianceOk = true;
		boolean variationOk = true;
		boolean stddevCrossOk = true;
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (zeroCrossMACDWanted && isMACDOk && isSMA200Ok) {
			try {
				eventCalculations.add(new ZeroCrossMACDEventCalculator(stock, macd, sma200, startDate, endDate, calculationCurrency));
			} catch (NotEnoughDataException e) {
				if (e.getShiftedStartDate() != null) {
					LOGGER.warn(warnMessage("ZeroCrossMACD",startDate, endDate) + butMessage(simpleDateFormat,e));
				} else {
					LOGGER.error("Failed calculation : "+warnMessage("ZeroCrossMACD",new Date(), new Date()));
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
					LOGGER.error("Failed calculation : "+warnMessage("SignalCrossMACD",new Date(), new Date()));
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
					LOGGER.error("Failed calculation : "+warnMessage("SmaReversal",new Date(), new Date()));
				}
				smaReversalOk = false;
			} 
		}
	
		if (rsiThresholdCrossWanted && isSMA200Ok && isRSIOk) {
			try {
				eventCalculations.add(new RSIThresholdCrossCalculator(stock, sma200, rsi, startDate, endDate, calculationCurrency));
			} catch (NotEnoughDataException e) {
				if (e.getShiftedStartDate() != null) {
					LOGGER.warn(warnMessage("RSIThresholdCross",startDate, endDate) + butMessage(simpleDateFormat, e));
				} else {
					LOGGER.error("Failed calculation : "+warnMessage("RSIThresholdCross",new Date(), new Date()));
				}
				rsiThresholdCrossOk = false;
			}
		}
		
		if (obvDivergenceWanted && isOBVOk) {
			try {
				eventCalculations.add(new ObvDivergence(stock, obv, startDate, endDate, calculationCurrency));
			} catch (NotEnoughDataException e) {
				if (e.getShiftedStartDate() != null) {
					LOGGER.warn(warnMessage("ObvDivergence",startDate, endDate) + butMessage(simpleDateFormat, e));
				} else {
					LOGGER.error("Failed calculation : "+warnMessage("ObvDivergence",new Date(), new Date()));
				}
				obvDivergenceOk = false;
			}
		}
		
		if (mfiDivergenceWanted && isMFIOk && isSMA200Ok) {
			try {
				eventCalculations.add(new MFIDivergence(stock, mfi, sma200, startDate, endDate, calculationCurrency));
			} catch (NotEnoughDataException e) {
				if (e.getShiftedStartDate() != null) {
					LOGGER.warn(warnMessage("MFIDivergence",startDate, endDate) + butMessage(simpleDateFormat, e));
				} else {
					LOGGER.error("Failed calculation : "+warnMessage("MFIDivergence",new Date(), new Date()));
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
					LOGGER.error("Failed calculation : "+warnMessage("MFIThreshold",new Date(), new Date()));
				}
				mfiThresholdOk = false;
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
	
		
		if (!zeroCrossMACDOk || !signalCrossMACDOk || !smaReversalOk || !rsiThresholdCrossOk || !obvDivergenceOk || !mfiDivergenceOk || !mfiThresholdOk || !varianceOk || !variationOk || !stddevCrossOk) {
			LOGGER.warn("One of the composite indicator calculation as failed for "+stock+"\n" +
					" actual state is : zeroCrossMACDOk, signalCrossMACDOk, smaReversalOk, rsiThresholdCrossOk, obvDivergenceOk, mfiDivergenceOk, mfiThresholdOk, varianceOk, variationOk, stddevCrossOk"+
					zeroCrossMACDOk +", "+ signalCrossMACDOk+", "+ smaReversalOk+", "+rsiThresholdCrossOk+", "+ obvDivergenceOk+", "+ mfiDivergenceOk+", "+ mfiThresholdOk+", "+ varianceOk+", "+  variationOk+", "+ stddevCrossOk);
		}
		
		return eventCalculations;
	}

	/**
	 * @param simpleDateFormat
	 * @param e
	 * @return
	 */
	private String butMessage(SimpleDateFormat simpleDateFormat, NotEnoughDataException e) {
		String butMessage = " BUT FROM " + simpleDateFormat.format(e.getShiftedStartDate()) + " TO " + simpleDateFormat.format(e.getShiftedEndDate());
		return butMessage;
	}

	@Override
	protected List<EventDefinition> getWantedEventCalculations() {
		return firstPassWantedCalculations;
	}

}
