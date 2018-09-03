/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.calculation;

import java.util.List;
import java.util.Observer;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.talib.indicators.OBV;
import com.finance.pms.talib.indicators.TalibIndicator;
/**
 * is bullish when close makes a lower low over 35 days spanning 5 days smoothed 0 days and OBV makes a higher low over 35 days spanning 5 days smoothed 0 days;
 * is bearish when close makes a higher high over 35 days spanning 5 days smoothed 0 days and OBV makes a lower high over 35 days spanning 5 days smoothed 0 days;
 * whit OBV : obv(close,volume)
 * 
 * TODO : https://www.stockcharts.com/school/doku.php?id=chart_school:technical_indicators:on_balance_volume_obv
 * Trend Confirmation
 * 
 * @author Gheeyom Thor
 *
 */
public class ObvDivergence extends DivergentOperator {

	private OBV obv;

	/**
	 * @param nullString FIXME work around the empty parameters operation which accepts, in the editor, only at least one parameter
	 * @param observers
	 */
	public ObvDivergence(Observer... observers) {
		super(EventDefinition.PMOBVDIVERGENCE, observers);
		init(null);
	}

	public ObvDivergence(EventInfo reference) {
		//Reflective ops generator
		super(reference);
	}

	protected void init(String nullString) {
		this.obv = new OBV();
	}

	@Override
	public void genericInit(Integer... constants) {
		init(null);
	}

	protected Boolean isInDataRange(TalibIndicator indicator, Integer index) {
		if (indicator instanceof OBV) return this.isInDataRange((OBV)indicator, index);
		throw new RuntimeException("Booo",new Throwable());
	}

	public Boolean isInDataRange(OBV obv, Integer index) {
		return getDaysSpan() <= index && index < obv.getObv().length;
	}

	@Override
	protected double[] buildOneOutput(QuotationUnit quotationUnit, Integer idx) {
		int obvIndex = getIndicatorIndexFromQuotationIndex(this.obv, idx);
		return new double[]
				{
						this.obv.getObv()[obvIndex]
				};
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		//		String head = "CALCULATOR DATE, CALCULATOR QUOTE, Chaikin Date, Chaikin, OBV SMA DATE, OBV SMA, OBV DATE, OBV, bearish, bullish";
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, OBV, bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}

	@Override
	protected int getDaysSpan() {
		return 35;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMOBVDIVERGENCE;
	}

	@Override
	public ValidityFilter quotationsValidity() {
		return ValidityFilter.OHLCV;
	}

	@Override
	protected int oscLookBackSmoothingPeriod() {
		return 0;
	}

	@Override
	protected Boolean isOcsWithinBearThresholds(int idxSpan, int oscIdx) {
		return true;
	}

	@Override
	protected Boolean isOscWithinBullThresholds(int idxSpan, int oscIdx) {
		return true;
	}

	@Override
	protected double[] getOscillatorOutput() {
		return obv.getObv();
	}

	@Override
	protected TalibIndicator getOscillator() {
		return obv;
	}

	@Override
	protected Double getAlphaBalance() {
		return (double) (getDaysSpan()/7);
	}

}
