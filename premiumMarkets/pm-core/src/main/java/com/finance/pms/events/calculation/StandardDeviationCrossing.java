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

import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import com.finance.pms.admin.config.IndicatorsConfig;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.StandardDeviation;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;
import com.finance.pms.threads.ConfigThreadLocal;

public class StandardDeviationCrossing extends TalibIndicatorsCompositionCalculator {
	
	private StandardDeviation standardDeviation;
	private int stddevQuotationStartDateIdx;
	
	private SMA sma;
	private Integer smaQuotationStartDateIdx;

	public StandardDeviationCrossing(Stock stock, StandardDeviation standardDeviation, Date startDate, Date endDate, Currency calculationCurrency, String analysisName) throws NotEnoughDataException {
		super(stock, startDate, endDate, calculationCurrency);
		this.standardDeviation = standardDeviation;
		
		try {
			Integer smaPeriod = ((IndicatorsConfig) ConfigThreadLocal.get("indicatorParams")).getStdDevSmaPeriod();
			this.sma = new SMA(stock, smaPeriod, startDate, endDate, calculationCurrency);
		} catch (TalibException e) {
			throw new NotEnoughDataException(stock, e.getMessage(),e);
		} catch (NoQuotationsException e) {
			throw new NotEnoughDataException(stock, e.getMessage(),e);
		}
		
		smaQuotationStartDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer smaQuotationEndDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(smaQuotationStartDateIdx, endDate);
		isValidData(stock, sma, startDate, smaQuotationStartDateIdx, smaQuotationEndDateIdx);
		
		stddevQuotationStartDateIdx = standardDeviation.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer stddevQuotationEndDateIdx = standardDeviation.getIndicatorQuotationData().getClosestIndexForDate(stddevQuotationStartDateIdx, endDate);
		isValidData(stock, standardDeviation, startDate, stddevQuotationStartDateIdx, stddevQuotationEndDateIdx);
		
	}

	@Override
	protected FormulatRes eventFormulaCalculation(Integer calcQuotationIdx) throws InvalidAlgorithmParameterException {
		FormulatRes res = new FormulatRes(EventDefinition.STDDEV);
		Date date = this.getCalculatorQuotationData().getDate(calcQuotationIdx);
		res.setCurrentDate(date);
		
		Integer smaIndicatorIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calcQuotationIdx, smaQuotationStartDateIdx);
		Integer stddevIndQuoteIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.standardDeviation, calcQuotationIdx, stddevQuotationStartDateIdx);
		
		double periodMean = 0;
		for (int i = calcQuotationIdx - standardDeviation.getPeriod(); i < calcQuotationIdx ; i++) {
			periodMean = periodMean + this.getCalculatorQuotationData().get(i).getClose().doubleValue();
		}
		periodMean = periodMean / standardDeviation.getPeriod();
		
		double currentQuote = this.getCalculatorQuotationData().get(calcQuotationIdx).getClose().doubleValue();
		double currentDev = currentQuote - periodMean;
		
		//EventType resType = EventType.NONE;
		if (Math.abs(currentDev) > this.standardDeviation.getStdDev()[stddevIndQuoteIndex]) {
			boolean isPriceAboveSMA = this.getCalculatorQuotationData().get(calcQuotationIdx).getClose().doubleValue() > sma.getSma()[smaIndicatorIndex];
			if (currentDev < 0 && !isPriceAboveSMA) {
				res.setBearishCrossBellow(true);
				//resType = EventType.BEARISH;
			}
			if (currentDev > 0 && isPriceAboveSMA) {
				res.setBullishCrossOver(true);
				//resType = EventType.BULLISH;
			}
		}

		return res;
	}

	@Override
	public Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex) {
		if (indicator instanceof SMA) return this.isInDataRange((SMA)indicator, indicatorIndex);
		if (indicator instanceof StandardDeviation) return this.isInDataRange((StandardDeviation)indicator, indicatorIndex);
		throw new RuntimeException("Booo",new Throwable());
	}
	
	private boolean isInDataRange(SMA sma, Integer index) {
		return (0 < index && index < sma.getSma().length);
	}

	private Boolean isInDataRange(StandardDeviation stddev, Integer index) {
		return (getDaysSpan() < index && index < stddev.getStdDev().length);
	}

	@Override
	protected int getDaysSpan() {
		//return this.standardDeviation.getPeriod() -1;
		return 10;
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		return "CALCULATOR DATE; CALCULATOR QUOTE; STDDEV DATE; STDDEV QUOTE; STDEV; SMA DATE; SMA QUOTE; SMA; bearish; bullish\n";
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, List<SortedMap<Date, double[]>> linearsExpects) {
		Date calculatorDate = this.getCalculatorQuotationData().get(calculatorIndex).getDate();
		EventValue bearsihEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.STDDEV,EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.STDDEV,EventType.BULLISH));
		BigDecimal calculatorClose = this.getCalculatorQuotationData().get(calculatorIndex).getClose();
		int smaQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,smaQuotationStartDateIdx);
		int stddevQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,stddevQuotationStartDateIdx);
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + ";" +calculatorClose + ";" 
			+ this.standardDeviation.getIndicatorQuotationData().get(stddevQuotationIndex).getDate() + ";" +this.standardDeviation.getIndicatorQuotationData().get(stddevQuotationIndex).getClose() + ";" 
			+ this.standardDeviation.getStdDev()[getIndicatorIndexFromCalculatorQuotationIndex(this.standardDeviation, calculatorIndex, stddevQuotationStartDateIdx)]  + ";" 
			+ this.sma.getIndicatorQuotationData().get(smaQuotationIndex).getDate() + ";" +this.sma.getIndicatorQuotationData().get(smaQuotationIndex).getClose() + ";" 
			+ this.sma.getSma()[getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calculatorIndex, smaQuotationStartDateIdx)];
		
		if (bearsihEventValue != null) {
			line = line + ";"+calculatorClose+";0;\n";
		} else if (bullishEventValue != null) {
			line = line + ";0;"+calculatorClose+";\n";
		} else {
			line = line + ";0;0;\n";
		}
		return line;
	}
	
	@Override
	protected double[] buildOneOutput(int calculatorIndex) {
			
		return new double[]
				{
					this.sma.getSma()[getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calculatorIndex, smaQuotationStartDateIdx)],
					this.standardDeviation.getStdDev()[getIndicatorIndexFromCalculatorQuotationIndex(this.standardDeviation, calculatorIndex, stddevQuotationStartDateIdx)],
					
				};
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.STDDEV;
	}
}
