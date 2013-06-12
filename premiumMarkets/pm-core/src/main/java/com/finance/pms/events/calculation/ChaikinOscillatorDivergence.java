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

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.talib.indicators.ChaikinOscillator;
import com.finance.pms.talib.indicators.TalibIndicator;

public class ChaikinOscillatorDivergence extends OscillatorDivergenceCalculator {

	private ChaikinOscillator chaikinOscillator;
	private Integer chaikinQuotationStartDateIdx;
	
	public ChaikinOscillatorDivergence(Stock stock, ChaikinOscillator chaikinOscillator, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate, calculationCurrency);
		
		this.chaikinOscillator = chaikinOscillator;
		chaikinQuotationStartDateIdx = chaikinOscillator.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer chaikinQuotationEndDateIdx = chaikinOscillator.getIndicatorQuotationData().getClosestIndexForDate(chaikinQuotationStartDateIdx, endDate);
		isValidData(stock, chaikinOscillator, startDate, chaikinQuotationStartDateIdx, chaikinQuotationEndDateIdx);
		
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, Chainkin Osc DATE, Chainkin Osc, bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}
	
	@Override
	protected String printThresholdsCSV() {
		return "";
	}
	
	@Override
	protected double[] buildOneOutput(int calculatorIndex) {
		return new double[]
				{
					getOscillatorOutput()[getIndicatorIndexFromCalculatorQuotationIndex(getOscillator(), calculatorIndex, getOscillatorQuotationStartDateIdx())],
					translateOutputForCharting(this.higherLows.get(calculatorIndex)),
					translateOutputForCharting(this.lowerHighs.get(calculatorIndex))
				};
	}
	

	@Override
	protected Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex) {
		return (getDaysSpan() < indicatorIndex) && (indicatorIndex < chaikinOscillator.getChaikinOsc().length);
	}

	@Override
	protected int getDaysSpan() {
		return 60;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMCHAIKINOSCDIVERGENCE;
	}
	
	@Override
	protected Integer getOscillatorQuotationStartDateIdx() {
		return chaikinQuotationStartDateIdx;
	}

	@Override
	protected double[] getOscillatorOutput() {
		return chaikinOscillator.getChaikinOsc();
	}

	@Override
	protected TalibIndicator getOscillator() {
		return chaikinOscillator;
	}
	
	@Override
	protected Boolean isOcsAboveUpperThreshold(int idxSpan, int mfiIdx) {
		return true;
	}

	@Override
	protected Boolean isOscBelowLowerThreshold(int idxSpan, int mfiIdx) {
		return true;
	}

	@Override
	protected double getOscillatorLowerThreshold() {
		throw new NotImplementedException();
	}

	@Override
	protected double getOscillatorUpperThreshold() {
		throw new NotImplementedException();
	}





}
