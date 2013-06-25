
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
import java.util.Observer;
import java.util.SortedMap;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.functions.HouseTrendSmoother;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;
import com.finance.pms.threads.ConfigThreadLocal;

public class HouseTrendDerivation  extends TalibIndicatorsCompositionCalculator {

	private SortedMap<Date, double[]> houseTrend;
	private int houseTrendPeriod;
	
	public HouseTrendDerivation(EventInfo eventInfo, Stock stock, Date firstDate, Date lastDate, Currency calculationCurrency) throws NotEnoughDataException, TalibException, NoQuotationsException  {
		super(stock, firstDate, lastDate, calculationCurrency);
		
		EventSignalConfig eventSignalConfig = (EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME);
		this.houseTrendPeriod = eventSignalConfig.getRocNNeuralHouseTrendPeriod();

		HouseTrendSmoother  houseTrendSmoother = new HouseTrendSmoother(houseTrendPeriod);
		SortedMap<Date, double[]> mapFromQuotationsClose = QuotationsFactories.getFactory().buildMapFromQuotationsClose(this.getCalculatorQuotationData());
		houseTrend = houseTrendSmoother.smooth(mapFromQuotationsClose, false);
		
		
	}
	
	public HouseTrendDerivation(EventInfo eventInfo, Stock stock, Date firstDate, Date lastDate, Currency calculationCurrency, String analyseName, Boolean export, Boolean persistTrainingEvents, Observer... observers) throws NotEnoughDataException, TalibException, NoQuotationsException {
		this(eventInfo, stock,firstDate,lastDate,calculationCurrency);
	}

	@Override
	protected FormulatRes eventFormulaCalculation(Integer calculatorIndex) throws InvalidAlgorithmParameterException {
		
		FormulatRes res = new FormulatRes(EventDefinition.HOUSETREND);
		Date prevDate = this.getCalculatorQuotationData().getDate(calculatorIndex-1);
		Date date = this.getCalculatorQuotationData().getDate(calculatorIndex);
		res.setCurrentDate(date);
		
		double[] prevHt = houseTrend.get(prevDate);
		double[] currentHt = houseTrend.get(date);
		if (prevHt == null || currentHt == null) return res;
		
		{
			Boolean isHTCrossingUpZero = prevHt[0] <= 0 && 0 < currentHt[0];
			res.setBullishCrossOver(isHTCrossingUpZero); 
			if (res.getBullishCrossOver()) return res;

		}
		{
			Boolean isHTCrossingDownZero = prevHt[0] >= 0 && 0 > currentHt[0];
			res.setBearishCrossBellow(isHTCrossingDownZero);
			return res;
			
		}
	}

	@Override
	protected Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex) {
		return getDaysSpan() <= indicatorIndex;
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, House Trend, bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, List<SortedMap<Date, double[]>> linearsExpects) {
		
		Date calculatorDate = this.getCalculatorQuotationData().get(calculatorIndex).getDate();
		EventValue bearishEventValue = edata.get(new StandardEventKey(calculatorDate, EventDefinition.HOUSETREND, EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate, EventDefinition.HOUSETREND, EventType.BULLISH));
		BigDecimal calculatorClose = this.getCalculatorQuotationData().get(calculatorIndex).getClose();

		double[] htOutput = this.houseTrend.get(calculatorDate);
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + ","
			+ ((htOutput != null)?htOutput[0]:Double.NaN);
		
		if (bearishEventValue != null) {
			line = line + "," + calculatorClose + ",0,";
		} else if (bullishEventValue != null) {
			line = line + ",0," + calculatorClose + ",";
		} else {
			line = line + ",0,0,";
		}
		
		line = addScoringLinesElement(line, calculatorDate, linearsExpects)+"\n";
		
		return line;
	}
	

	@Override
	protected SortedMap<Date, double[]> buildOutput() {
		return houseTrend;
	}
	
	
	@Override
	protected double[] buildOneOutput(int calculatorIndex) {
		throw new NotImplementedException();
	}

	@Override
	protected int getDaysSpan() {
		return houseTrendPeriod;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.HOUSETREND;
	}
	
	@Override
	public SortedMap<Date, double[]> calculationOutput() {
		return houseTrend;
	}


}
