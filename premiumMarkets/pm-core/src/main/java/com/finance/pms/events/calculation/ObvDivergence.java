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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.ChaikinLine;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.OBV;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

public class ObvDivergence extends TalibIndicatorsCompositionCalculator {
	
	private static final int SMOOTHING = 2;
	
	private OBV obv;
	private Integer obvQuotationStartDateIdx;
	private SMA obvSma;
	private Integer obvSmaQuotationStartDateIdx;
	private ChaikinLine chaikinLine;
	private Integer chaikinQuotationStartDateIdx;
	private SMA sma;
	private Integer smaQuotationStartDateIdx;

	public ObvDivergence(Stock stock, OBV obv, ChaikinLine chaikinLine, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate,  calculationCurrency);
		
		this.obv = obv;
		obvQuotationStartDateIdx = obv.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer obvQuotationEndDateIdx = obv.getIndicatorQuotationData().getClosestIndexForDate(obvQuotationStartDateIdx, endDate);
		isValidData(stock, obv, startDate, obvQuotationStartDateIdx, obvQuotationEndDateIdx);
		
		this.chaikinLine = chaikinLine;
		chaikinQuotationStartDateIdx = chaikinLine.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer chaikinQuotationEndDateIdx = chaikinLine.getIndicatorQuotationData().getClosestIndexForDate(chaikinQuotationStartDateIdx, endDate);
		isValidData(stock, chaikinLine, startDate, chaikinQuotationStartDateIdx, chaikinQuotationEndDateIdx);
		
		try {
			this.obvSma = new SMA(obv, SMOOTHING);
			this.sma = new SMA(stock, SMOOTHING, startDate, endDate, calculationCurrency, Math.max(SMOOTHING, getDaysSpan()), 0);
		} catch (TalibException e) {
			throw new NotEnoughDataException(e.getMessage(),e);
		} catch (NoQuotationsException e) {
			throw new NotEnoughDataException(e.getMessage(),e);
		}
		
		smaQuotationStartDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer smaQuotationEndDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(smaQuotationStartDateIdx, endDate);
		isValidData(stock, sma, startDate, smaQuotationStartDateIdx, smaQuotationEndDateIdx);
		
		obvSmaQuotationStartDateIdx = obvSma.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer obvSmaQuotationEndDateIdx = obvSma.getIndicatorQuotationData().getClosestIndexForDate(obvSmaQuotationStartDateIdx, endDate);
		isValidData(stock, obvSma, startDate, obvSmaQuotationStartDateIdx, obvSmaQuotationEndDateIdx);
	}

	@Override
	protected FormulatRes eventFormulaCalculation(Integer calculatorIndex) throws InvalidAlgorithmParameterException {
		
		FormulatRes res = new FormulatRes(EventDefinition.PMOBVDIVERGENCE);
		res.setCurrentDate(this.getCalculatorQuotationData().getDate(calculatorIndex));
		
		int obvIdx = getIndicatorIndexFromCalculatorQuotationIndex(this.obv, calculatorIndex, obvQuotationStartDateIdx);
		double[] obvLookBackP = Arrays.copyOfRange(this.obv.getObv(), obvIdx - getDaysSpan(), obvIdx);
		double[] quotationLookBackP = Arrays.copyOfRange(this.getCalculatorQuotationData().getCloseValues(), calculatorIndex - getDaysSpan(), calculatorIndex);
		
		int smaIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calculatorIndex, smaQuotationStartDateIdx);
		double[] quotationLookBackPThresh = Arrays.copyOfRange(this.sma.getSma(), smaIndex - getDaysSpan(), smaIndex);
		
		int obvQuotationIdx = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex, obvQuotationStartDateIdx);
		int obvSmaIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.obvSma, obvQuotationIdx, obvSmaQuotationStartDateIdx);
		double[] obvLookBackPThresh = Arrays.copyOfRange(this.obvSma.getSma(), obvSmaIndex - getDaysSpan(), obvSmaIndex);
		
		{
			Boolean isPriceDown = lowerLow(quotationLookBackP, quotationLookBackPThresh);
			Boolean isObvUp = higherLow(obvLookBackP, obvLookBackPThresh);
			res.setBullishCrossOver(isPriceDown && isObvUp); 
			
			if (res.getBullishCrossOver()) return res;

		}
		{
			Boolean isPriceUp = higherHigh(quotationLookBackP, quotationLookBackPThresh);
			Boolean isObvDown = lowerHigh(obvLookBackP, obvLookBackPThresh);
			res.setBearishCrossBellow(isPriceUp && isObvDown);
		
			return res;
		}
	}
	
	protected Boolean isInDataRange(TalibIndicator indicator, Integer index) {
		if (indicator instanceof SMA) return this.isInDataRange((SMA)indicator, index);
		if (indicator instanceof OBV) return this.isInDataRange((OBV)indicator, index);
		if (indicator instanceof ChaikinLine) return this.isInDataRange((ChaikinLine)indicator, index);
		throw new RuntimeException("Booo",new Throwable());
	}
	
	private boolean isInDataRange(SMA sma, Integer index) {
		return getDaysSpan() <= index && index < sma.getSma().length;
	}
	
	public Boolean isInDataRange(OBV obv, Integer index) {
		return getDaysSpan() <= index && index < obv.getObv().length;
	}
	
	private boolean isInDataRange(ChaikinLine chaikinLine, Integer index) {
		return getDaysSpan() <= index && index < chaikinLine.getChaikinLine().length;
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, List<SortedMap<Date, double[]>> linearsExpects) {
		Date calculatorDate = this.getCalculatorQuotationData().get(calculatorIndex).getDate();
		EventValue bearishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMOBVDIVERGENCE, EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMOBVDIVERGENCE, EventType.BULLISH));
		BigDecimal calculatorClose = this.getCalculatorQuotationData().get(calculatorIndex).getClose();

		int chaikinIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.chaikinLine, calculatorIndex, chaikinQuotationStartDateIdx);
		int chaikinQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex, chaikinQuotationStartDateIdx);
		
		int obvIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.obv, calculatorIndex, obvQuotationStartDateIdx);
		int obvQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex, obvQuotationStartDateIdx);
		int obvSmaIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.obvSma, obvQuotationIndex, obvSmaQuotationStartDateIdx);
		int obvSmaQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(obvQuotationIndex, obvSmaQuotationStartDateIdx);
		
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + "," // + this.sma.getSma()[smaIndex] + "," 
			+ this.chaikinLine.getIndicatorQuotationData().get(chaikinQuotationIndex).getDate() + "," + this.chaikinLine.getChaikinLine()[chaikinIndex] + ","
			+ this.obvSma.getIndicatorQuotationData().get(obvSmaQuotationIndex).getDate()+ "," + this.obvSma.getSma()[obvSmaIndex] + "," 
			+ this.obv.getIndicatorQuotationData().get(obvQuotationIndex).getDate() + "," + this.obv.getObv()[obvIndex];
		
		if (bearishEventValue != null) {
			line = line + ","+calculatorClose+",0,";
		} else if (bullishEventValue != null) {
			line = line + ",0,"+calculatorClose+",";
		} else {
			line = line + ",0,0,";
		}
		
		line = addScoringLinesElement(line, calculatorDate, linearsExpects)+"\n";
		
		return line;
	}
	
	@Override
	protected double[] buildOneOutput(int calculatorIndex) {
		
		int obvIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.obv, calculatorIndex, obvQuotationStartDateIdx);
		
		return new double[]
				{
					this.obv.getObv()[obvIndex]
				};
	}


	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, Chaikin Date, Chaikin, OBV SMA DATE, OBV SMA, OBV DATE, OBV, bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}

	@Override
	protected int getDaysSpan() {
		return 60;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMOBVDIVERGENCE;
	}
}
