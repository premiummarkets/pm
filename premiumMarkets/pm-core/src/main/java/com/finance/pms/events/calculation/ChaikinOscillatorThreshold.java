package com.finance.pms.events.calculation;

import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
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
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.ChaikinOscillator;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.TalibIndicator;

public class ChaikinOscillatorThreshold extends TalibIndicatorsCompositionCalculator {

	private ChaikinOscillator chaikinOscillator;
	private Integer chaikinQuotationStartDateIdx;

	public ChaikinOscillatorThreshold(Stock stock, ChaikinOscillator chaikinOscillator, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate, calculationCurrency);
		
		this.chaikinOscillator = chaikinOscillator;
		chaikinQuotationStartDateIdx = chaikinOscillator.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer chaikinQuotationEndDateIdx = chaikinOscillator.getIndicatorQuotationData().getClosestIndexForDate(chaikinQuotationStartDateIdx, endDate);
		isValidData(stock, chaikinOscillator, startDate, chaikinQuotationStartDateIdx, chaikinQuotationEndDateIdx);
		
	}

	@Override
	protected FormulatRes eventFormulaCalculation(Integer calculatorIndex) throws InvalidAlgorithmParameterException {

		FormulatRes res = new FormulatRes(EventDefinition.PMCHAIKINOSCTHRESHOLD);
		res.setCurrentDate(this.getCalculatorQuotationData().getDate(calculatorIndex));
		
		Integer stochIndicatorIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.chaikinOscillator, calculatorIndex, chaikinQuotationStartDateIdx);

		
		{
			//BULL : Chaikin cross above zero
			boolean isCrossingBelow = 
					this.chaikinOscillator.getChaikinOsc()[stochIndicatorIndex-1] < 0 && 0 < this.chaikinOscillator.getChaikinOsc()[stochIndicatorIndex];
			res.setBullishCrossOver(isCrossingBelow);
			if (res.getBullishCrossOver()) return res;
		} 
		{
			//BEAR : Chaikin cross below zero
			boolean isCrossingAbove = 
					this.chaikinOscillator.getChaikinOsc()[stochIndicatorIndex-1]  > 0 && 0 > this.chaikinOscillator.getChaikinOsc()[stochIndicatorIndex];
			res.setBearishCrossBellow(isCrossingAbove);
			
			return res;
		}
		
	}

	@Override
	protected Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex) {
		return (getDaysSpan() < indicatorIndex) && (indicatorIndex < chaikinOscillator.getChaikinOsc().length);
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, Chainkin Osc DATE, Chainkin Osc, bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, List<SortedMap<Date, double[]>> linearsExpects) {
		
		Date calculatorDate = this.getCalculatorQuotationData().get(calculatorIndex).getDate();
		EventValue bearishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMCHAIKINOSCTHRESHOLD, EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMCHAIKINOSCTHRESHOLD, EventType.BULLISH));
		BigDecimal calculatorClose = this.getCalculatorQuotationData().get(calculatorIndex).getClose();
		
		int chaikinIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.chaikinOscillator, calculatorIndex, chaikinQuotationStartDateIdx);
		int chaikinQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex, chaikinQuotationStartDateIdx);
		
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + ","  
			+ this.chaikinOscillator.getIndicatorQuotationData().get(chaikinQuotationIndex).getDate() + ","
			+ this.chaikinOscillator.getChaikinOsc()[chaikinIndex];
		
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
	protected int getDaysSpan() {
		return 60;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMCHAIKINOSCTHRESHOLD;
	}

}
