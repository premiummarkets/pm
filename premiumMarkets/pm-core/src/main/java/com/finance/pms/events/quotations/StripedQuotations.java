/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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
package com.finance.pms.events.quotations;

import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * The Class StripedQuotations.
 * 
 * @author Guillaume Thoreton
 */
public class StripedQuotations {
	/** The bar list. */
	private ArrayList<QuotationUnit> barList;
	/** The close list. */
	private double[] closeList;
	private double[] highList;
	private double[] lowList;
	
	public StripedQuotations() {
		super();
		this.barList = new ArrayList<QuotationUnit>();
		this.closeList = new double[500];
		this.highList = new double[500];
		this.lowList =  new double[500];
	}

	/**
	 * Instantiates a new striped quotations.
	 * 
	 * @param limit
	 *            the limit
	 * 
	 * @author Guillaume Thoreton
	 */
	public StripedQuotations(Integer limit) {
		super();
		this.barList = new ArrayList<QuotationUnit>();
		this.closeList = new double[limit];
		this.highList = new double[limit];
		this.lowList =  new double[limit];
	}


	/**
	 * Increase array size by.
	 * 
	 * @param nbEle
	 *            the nb ele
	 * 
	 * @author Guillaume Thoreton
	 */
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

	/**
	 * Gets the bar list.
	 * 
	 * @return the bar list
	 */
	protected ArrayList<QuotationUnit> getBarList() {
		return barList;
	}

	/**
	 * Gets the n last closed.
	 * 
	 * @param n
	 *            the n
	 * 
	 * @return the n last closed
	 * 
	 * @throws InvalidAlgorithmParameterException
	 *             the invalid algorithm parameter exception
	 */
	public double[] getNLastClosed(int n) throws InvalidAlgorithmParameterException {
		if (n > this.barList.size())
			throw new InvalidAlgorithmParameterException("Close values available for dates is " + getCloseList().length);
		return Arrays.copyOfRange(getCloseList(), this.barList.size() - n, this.barList.size());
	}

	/**
	 * Gets the close trimed list.
	 * 
	 * @return the close trimed list
	 */
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

	public void addBar(Date date, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, long volume) {
		QuotationUnit quotationUnit = new QuotationUnit(date,open,high,low,close,volume);
		this.getBarList().add(quotationUnit);
	}
	
	public void addBar(int i, java.util.Date date, double close) {
		QuotationUnit quotationUnit = new QuotationUnit(date,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,new BigDecimal(close),0l);
		this.getBarList().add(quotationUnit);
		this.closeList[i] = close;
		
	}

	public QuotationUnit getLast() {
		return this.getBarList().get(this.getBarList().size()-1);
	}	
}