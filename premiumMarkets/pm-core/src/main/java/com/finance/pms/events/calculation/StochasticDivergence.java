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
import com.finance.pms.events.calculation.houseIndicators.HouseAroon;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.StochasticOscillator;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

public class StochasticDivergence extends TalibIndicatorsCompositionCalculator {
	
//	HouseAroon aroon;
//	private Integer aroonQuotationStartDateIdx;
	private StochasticOscillator stochOsc;
	private Integer stochQuotationStartDateIdx;
	
	private SMA sma;
	private Integer smaQuotationStartDateIdx;

	public StochasticDivergence(Stock stock, StochasticOscillator stochasticOscillator, HouseAroon aroon, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate, calculationCurrency);
		
//		this.aroon = sma;
//		aroonQuotationStartDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
//		Integer aroonQuotationEndDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(aroonQuotationStartDateIdx, endDate);
//		isValidData(stock, sma, startDate, aroonQuotationStartDateIdx, aroonQuotationEndDateIdx);
		
		this.stochOsc = stochasticOscillator;
		stochQuotationStartDateIdx = stochasticOscillator.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer stochQuotationEndDateIdx = stochasticOscillator.getIndicatorQuotationData().getClosestIndexForDate(stochQuotationStartDateIdx, endDate);
		isValidData(stock, stochasticOscillator, startDate, stochQuotationStartDateIdx, stochQuotationEndDateIdx);
		
		try {
			this.sma = new SMA(stock, 2, startDate, endDate, calculationCurrency, Math.max(20, getDaysSpan()), 0);
		} catch (TalibException e) {
			throw new NotEnoughDataException(e.getMessage(),e);
		} catch (NoQuotationsException e) {
			throw new NotEnoughDataException(e.getMessage(),e);
		}
		
		smaQuotationStartDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer smaQuotationEndDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(smaQuotationStartDateIdx, endDate);
		isValidData(stock, sma, startDate, smaQuotationStartDateIdx, smaQuotationEndDateIdx);
	
	}

	@Override
	protected FormulatRes eventFormulaCalculation(Integer calculatorIndex) throws InvalidAlgorithmParameterException {

//		Boolean isAroonBullish = false;
//		Boolean isAroonBearish = false;
//		
		FormulatRes res = new FormulatRes(EventDefinition.PMSSTOCHDIVERGENCE);
		res.setCurrentDate(this.getCalculatorQuotationData().getDate(calculatorIndex));
		
		int stochIdx = getIndicatorIndexFromCalculatorQuotationIndex(this.stochOsc, calculatorIndex, stochQuotationStartDateIdx);
		double[] stochLookBackP = Arrays.copyOfRange(this.stochOsc.getSlowK(), stochIdx - getDaysSpan(), stochIdx);
		double[] quotationLookBackP = Arrays.copyOfRange(this.getCalculatorQuotationData().getCloseValues(), calculatorIndex - getDaysSpan(), calculatorIndex);
		
		int smaIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calculatorIndex, smaQuotationStartDateIdx);
		double[] quotationLookBackPThresh = Arrays.copyOfRange(this.sma.getSma(), smaIndex - getDaysSpan(), smaIndex);
		
		double[] lowThreshLookBackP = new double[getDaysSpan()];
		for (int i = 0; i < lowThreshLookBackP.length; i++) {
			lowThreshLookBackP[i] = this.stochOsc.getLowerThreshold();
			
		}
		
		double[] upperThreshLookBackP = new double[getDaysSpan()];
		for (int i = 0; i < upperThreshLookBackP.length; i++) {
			upperThreshLookBackP[i] = this.stochOsc.getUpperThreshold();
			
		}
		
		{
//			Boolean isPriceDown = dataSlope(quotationLookBackP) < 0;
//			boolean isStochStartingBelowThreshold = this.stochOsc.getSlowK()[stochIdx - getDaysSpan()] < this.stochOsc.getLowerThreshold();
//			Boolean isStochUp = dataSlope(chaikinLookBackP) > 0 && isStochStartingBelowThreshold;
			Boolean isPriceDown = lowerLow(quotationLookBackP, quotationLookBackPThresh);
			Boolean isStochUp = higherLow(stochLookBackP, lowThreshLookBackP);
			res.setBullishCrossOver(isPriceDown && isStochUp); 
			
			if (res.getBullishCrossOver()) return res;

		}
		{
//			Boolean isPriceUp = dataSlope(quotationLookBackP) > 0;
//			Boolean isStochDown = dataSlope(stochLookBackP) < 0 && this.stochOsc.getSlowK()[stochIdx - getDaysSpan()] > this.stochOsc.getUpperThreshold();
			Boolean isPriceUp = higherHigh(quotationLookBackP, quotationLookBackPThresh);
			Boolean isStochDown = lowerHigh(stochLookBackP, upperThreshLookBackP);
			res.setBearishCrossBellow(isPriceUp && isStochDown);
		
			return res;
		}
		
	}
	
	@Override
	protected Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex) {
		if (indicator instanceof HouseAroon) return this.isInDataRange((HouseAroon)indicator, indicatorIndex);
		if (indicator instanceof StochasticOscillator) return this.isInDataRange((StochasticOscillator)indicator, indicatorIndex);
		if (indicator instanceof SMA) return this.isInDataRange((SMA)indicator, indicatorIndex);
		throw new RuntimeException("Booo",new Throwable());
	}

	private Boolean isInDataRange(StochasticOscillator indicator, Integer indicatorIndex) {
		return (getDaysSpan() < indicatorIndex) && (indicatorIndex < this.stochOsc.getSlowK().length);
		
	}
	
	private boolean isInDataRange(SMA sma, Integer index) {
		return (getDaysSpan() < index && index < sma.getSma().length);
	}
	
	private boolean isInDataRange(HouseAroon aroon, Integer index) {
		return (0 < index && index < aroon.getOutAroonUp().length);
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		//return "CALCULATOR DATE; CALCULATOR QUOTE; STOCH DATE; STOCH SLOW K; STOCH SLOW D ; bearish; bullish\n";	
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, STOCH DATE, LOW TH, UP TH, STOCH SLOW K, STOCH SLOW D , bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, List<SortedMap<Date, double[]>> linearsExpects) {
		
		Date calculatorDate = this.getCalculatorQuotationData().get(calculatorIndex).getDate();
		EventValue bearishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMSSTOCHDIVERGENCE, EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMSSTOCHDIVERGENCE, EventType.BULLISH));
		BigDecimal calculatorClose = this.getCalculatorQuotationData().get(calculatorIndex).getClose();
		
		int stochIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.stochOsc, calculatorIndex, stochQuotationStartDateIdx);
		int stochQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex, stochQuotationStartDateIdx);
		
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + ","  
			+ this.stochOsc.getIndicatorQuotationData().get(stochQuotationIndex).getDate() + ","
			+ this.stochOsc.getLowerThreshold() + ","
			+ this.stochOsc.getUpperThreshold() + ","
			+ this.stochOsc.getSlowK()[stochIndex] + ","
			+ this.stochOsc.getSlowD()[stochIndex];
		
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
		return EventDefinition.PMSSTOCHDIVERGENCE;
	}

}
