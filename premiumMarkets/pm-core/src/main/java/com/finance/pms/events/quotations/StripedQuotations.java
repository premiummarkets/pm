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
package com.finance.pms.events.quotations;

import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.finance.pms.datasources.shares.Stock;


/**
 * The Class StripedQuotations.
 * 
 * @author Guillaume Thoreton
 */
public class StripedQuotations {

	private ArrayList<QuotationUnit> barList;

	private double[] closeList;
	private double[] highList;
	private double[] lowList;
	
//	public StripedQuotations() {
//		super();
//		this.barList = new ArrayList<QuotationUnit>();
//		this.closeList = new double[500];
//		this.highList = new double[500];
//		this.lowList =  new double[500];
//	}

	public StripedQuotations(Integer limit) {
		super();
		this.barList = new ArrayList<QuotationUnit>();
		this.closeList = new double[limit];
		this.highList = new double[limit];
		this.lowList =  new double[limit];
	}

	public void increaseArraySizeBy(Integer nbEle) {
		double[] closeListIncr = new double[getCloseList().length + nbEle];
		System.arraycopy(getCloseList(), 0, closeListIncr, 0, getCloseList().length);
		closeList = closeListIncr;
		double[] highListIncr = new double[highList.length + nbEle];
		System.arraycopy(highList, 0, highListIncr, 0, highList.length);
		highList = highListIncr;
		double[] lowListIncr = new double[lowList.length + nbEle];
		System.arraycopy(lowList, 0, lowListIncr, 0, lowList.length);
		lowList = lowListIncr;
	}

	protected ArrayList<QuotationUnit> getBarList() {
		return barList;
	}

	public double[] getNLastClosed(int n) throws InvalidAlgorithmParameterException {
		if (n > this.barList.size())
			throw new InvalidAlgorithmParameterException("Close values available for dates is " + getCloseList().length);
		return Arrays.copyOfRange(getCloseList(), this.barList.size() - n, this.barList.size());
	}

	public double[] getCloseTrimedList() {
		double[] trimedCloseList = new double[this.barList.size()];
		System.arraycopy(this.closeList, 0, trimedCloseList, 0, this.barList.size());
		return trimedCloseList;
	}
	
	public double[] getLowTrimedList() {
		double[] trimedLowList = new double[this.barList.size()];
		System.arraycopy(this.lowList, 0, trimedLowList, 0, this.barList.size());
		return trimedLowList;
	}
	
	public double[] getHighTrimedList() {
		double[] highCloseList = new double[this.barList.size()];
		System.arraycopy(this.highList, 0, highCloseList, 0, this.barList.size());
		return highCloseList;
	}
	
	public void addStripedValues(int i,BigDecimal closeValue, BigDecimal highValue, BigDecimal lowValue) {		
		this.closeList[i] = closeValue.doubleValue();
		this.highList[i] = highValue.doubleValue();
		this.lowList[i] = lowValue.doubleValue();
	}

	public boolean isStriped() {
		return (getCloseList().length != 0);
	}

	public double[] getCloseList() {
		return closeList;
	}
	
	public void addCloseOnlyBar(Stock stock, int i, Date date, double close) {
		QuotationUnit quotationUnit = new QuotationUnit(stock, stock.getMarketValuation().getCurrency(), date, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal(close), 0l, null);
		this.getBarList().add(quotationUnit);
		this.closeList[i] = close;
		
	}

	public QuotationUnit getLast() {
		return this.getBarList().get(this.getBarList().size()-1);
	}	
}