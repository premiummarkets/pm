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
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

//TODO this is not a TalibComposition if we use HouseAroon.
//Add an extra layer under EventCompositionCalulator to deal with house indicators
public class AroonTrend extends TalibIndicatorsCompositionCalculator {

	private HouseAroon aroon;
	private Integer aroonQuotationStartDateIdx;
	
	public AroonTrend(Stock stock, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate, calculationCurrency);
		
		try {
			
			int aroonPeriod = getDaysSpan();
			this.aroon =  new HouseAroon(stock, startDate, endDate, calculationCurrency, aroonPeriod);
		} catch (TalibException e) {
			throw new NotEnoughDataException(e.getMessage(),e);
		} catch (NoQuotationsException e) {
			throw new NotEnoughDataException(e.getMessage(),e);
		}
		
		aroonQuotationStartDateIdx = aroon.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer aroonQuotationEndDateIdx = aroon.getIndicatorQuotationData().getClosestIndexForDate(aroonQuotationStartDateIdx, endDate);
		isValidData(stock, aroon, startDate, aroonQuotationStartDateIdx, aroonQuotationEndDateIdx);
		
	}

	@Override
	protected FormulatRes eventFormulaCalculation(Integer calculatorIndex) throws InvalidAlgorithmParameterException {
		FormulatRes res = new FormulatRes(EventDefinition.PMAROONTREND);
		res.setCurrentDate(this.getCalculatorQuotationData().getDate(calculatorIndex));
		
		int aroonIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.aroon, calculatorIndex, aroonQuotationStartDateIdx);
		{
			Boolean wasAroonUpCrossingAboveAroonDown = 
					(aroon.getOutAroonUp()[aroonIndex - getDaysSpan()] < aroon.getOutAroonDown()[aroonIndex - getDaysSpan()]) &&
					(aroon.getOutAroonUp()[aroonIndex] > aroon.getOutAroonDown()[aroonIndex]);
	
			Boolean wasAroonUpCrossingAbove50 = aroon.getOutAroonUp()[aroonIndex - getDaysSpan()] < 50 && 50 < aroon.getOutAroonUp()[aroonIndex] ;
			Boolean isArronUpTo100 = 90 < aroon.getOutAroonUp()[aroonIndex];
			Boolean isAroonDownBelow30 = 30 > aroon.getOutAroonDown()[aroonIndex];
			
			res.setBullishCrossOver(wasAroonUpCrossingAboveAroonDown && wasAroonUpCrossingAbove50 && isArronUpTo100 && isAroonDownBelow30); 
			if (res.getBullishCrossOver()) return res;

		}
		{
			Boolean wasAroonDownCrossingAboveAroonUp = 
					(aroon.getOutAroonDown()[aroonIndex - getDaysSpan()] < aroon.getOutAroonUp()[aroonIndex - getDaysSpan()]) &&
					(aroon.getOutAroonDown()[aroonIndex] > aroon.getOutAroonUp()[aroonIndex]);
	
			Boolean wasAroonDownCrossingAbove50 = aroon.getOutAroonDown()[aroonIndex - getDaysSpan()] < 50 && 50 < aroon.getOutAroonDown()[aroonIndex] ;
			Boolean isArronDownTo100 = 90 < aroon.getOutAroonDown()[aroonIndex];
			Boolean isAroonUpBelow30 = 30 > aroon.getOutAroonUp()[aroonIndex];
			
			res.setBearishCrossBellow(wasAroonDownCrossingAboveAroonUp && wasAroonDownCrossingAbove50 && isArronDownTo100 && isAroonUpBelow30);
			return res;
			
		}
	}

	@Override
	protected Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex) {
		if (indicator instanceof HouseAroon) return this.isInDataRange((HouseAroon)indicator, indicatorIndex);
		if (indicator instanceof SMA) return this.isInDataRange((SMA) indicator, indicatorIndex);
		throw new RuntimeException("Booo",new Throwable());
		
	}
	
	private boolean isInDataRange(SMA sma, Integer index) {
		return getDaysSpan() <= index && index < sma.getSma().length;
	}
	
	private Boolean isInDataRange(HouseAroon aroon, Integer index) {
		return (getDaysSpan() < index) && (index < aroon.getOutAroonUp().length);
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, Aroon DATE, Aroon Up, Aroon Down, bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}
	
	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, List<SortedMap<Date, double[]>> linearsExpects) {
		
		Date calculatorDate = this.getCalculatorQuotationData().get(calculatorIndex).getDate();
		EventValue bearishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMAROONTREND, EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMAROONTREND, EventType.BULLISH));
		BigDecimal calculatorClose = this.getCalculatorQuotationData().get(calculatorIndex).getClose();
		
		int aroonIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.aroon, calculatorIndex, aroonQuotationStartDateIdx);
		int aroonQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex, aroonQuotationStartDateIdx);
		
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + ","
			+ this.aroon.getIndicatorQuotationData().get(aroonQuotationIndex).getDate() + "," 
			+ this.aroon.getOutAroonUp()[aroonIndex] + "," + this.aroon.getOutAroonDown()[aroonIndex];
		
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
		return 25;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMAROONTREND;
	}

}
