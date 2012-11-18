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
import com.finance.pms.events.calculation.houseIndicators.HouseAroon;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.StochasticOscillator;
import com.finance.pms.talib.indicators.TalibIndicator;


public class StochasticThreshold extends TalibIndicatorsCompositionCalculator {
	
//	HouseAroon aroon;
//	private Integer aroonQuotationStartDateIdx;
	private StochasticOscillator stochasticOscillator;
	private Integer stochQuotationStartDateIdx;

	public StochasticThreshold(Stock stock, StochasticOscillator stochasticOscillator, HouseAroon aroon, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate, calculationCurrency);
		
//		this.aroon = sma;
//		aroonQuotationStartDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
//		Integer aroonQuotationEndDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(aroonQuotationStartDateIdx, endDate);
//		isValidData(stock, sma, startDate, aroonQuotationStartDateIdx, aroonQuotationEndDateIdx);
		
		this.stochasticOscillator = stochasticOscillator;
		stochQuotationStartDateIdx = stochasticOscillator.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer stochQuotationEndDateIdx = stochasticOscillator.getIndicatorQuotationData().getClosestIndexForDate(stochQuotationStartDateIdx, endDate);
		isValidData(stock, stochasticOscillator, startDate, stochQuotationStartDateIdx, stochQuotationEndDateIdx);
	
	}

	@Override
	protected FormulatRes eventFormulaCalculation(Integer calculatorIndex) throws InvalidAlgorithmParameterException {
		
		FormulatRes res = new FormulatRes(EventDefinition.PMSSTOCHTHRESHOLD);
		res.setCurrentDate(this.getCalculatorQuotationData().getDate(calculatorIndex));
		
		Integer stochIndicatorIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.stochasticOscillator, calculatorIndex, stochQuotationStartDateIdx);

		
		{
			//BULL : Stoch cross below low threshold (over sold) with an up trend (above sma)
			boolean isCrossingBelow = 
					this.stochasticOscillator.getSlowD()[stochIndicatorIndex-1] > stochasticOscillator.getLowerThreshold() && 
					stochasticOscillator.getLowerThreshold() > this.stochasticOscillator.getSlowD()[stochIndicatorIndex]; // crosses below  lower threshold
			res.setBullishCrossOver(isCrossingBelow);
			if (res.getBullishCrossOver()) return res;
		} 
		{
			//BEAR : RSI cross above upper threshold (over bought) with a down trend (under sma)
			boolean isCrossingAbove = 
					this.stochasticOscillator.getSlowD()[stochIndicatorIndex-1]  < stochasticOscillator.getUpperThreshold()  &&
					stochasticOscillator.getUpperThreshold() < this.stochasticOscillator.getSlowD()[stochIndicatorIndex];// crosses above upper threshold
			res.setBearishCrossBellow(isCrossingAbove);
			
			return res;
		}
		
		
	}
	
	@Override
	protected Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex) {
		if (indicator instanceof HouseAroon) return this.isInDataRange((HouseAroon)indicator, indicatorIndex);
		if (indicator instanceof StochasticOscillator) return this.isInDataRange((StochasticOscillator)indicator, indicatorIndex);
		throw new RuntimeException("Booo",new Throwable());
	}

	private Boolean isInDataRange(StochasticOscillator indicator, Integer indicatorIndex) {
		return (getDaysSpan() < indicatorIndex) && (indicatorIndex < this.stochasticOscillator.getSlowK().length);
		
	}
	
	private boolean isInDataRange(HouseAroon sma, Integer index) {
		return (0 < index && index < sma.getOutAroonUp().length);
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		//return "CALCULATOR DATE; CALCULATOR QUOTE; STOCH DATE; STOCH SLOW K; STOCH SLOW D ; bearish; bullish\n";	
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, STOCH DATE, Upper Th, lower Th, STOCH SLOW K, STOCH SLOW D , bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, List<SortedMap<Date, double[]>> linearsExpects) {
		
		Date calculatorDate = this.getCalculatorQuotationData().get(calculatorIndex).getDate();
		EventValue bearishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMSSTOCHTHRESHOLD, EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMSSTOCHTHRESHOLD, EventType.BULLISH));
		BigDecimal calculatorClose = this.getCalculatorQuotationData().get(calculatorIndex).getClose();
		
		int stochIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.stochasticOscillator, calculatorIndex, stochQuotationStartDateIdx);
		int stochQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex, stochQuotationStartDateIdx);
		
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + ","  
			+ this.stochasticOscillator.getIndicatorQuotationData().get(stochQuotationIndex).getDate() + ","
			+ this.stochasticOscillator.getLowerThreshold() + ","
			+ this.stochasticOscillator.getUpperThreshold() + ","
			+ this.stochasticOscillator.getSlowK()[stochIndex] + ","
			+ this.stochasticOscillator.getSlowD()[stochIndex];
		
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
		return 1;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMSSTOCHTHRESHOLD;
	}

}
