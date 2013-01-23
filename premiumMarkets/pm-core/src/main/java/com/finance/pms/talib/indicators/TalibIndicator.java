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
package com.finance.pms.talib.indicators;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.Indicator;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.StripedQuotations;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;

public abstract class TalibIndicator extends Indicator {
	
	private static MyLogger LOGGER = MyLogger.getLogger(TalibIndicator.class);
	
	protected MInteger outBegIdx = new MInteger();
	protected MInteger outNBElement = new MInteger();
	protected Date outBegDate;

	protected TalibIndicator(Stock stock, Date firstDate, Integer firstIdxShift, Date lastDate, Integer lastIdxShift, Currency calculationCurrency, Number...indicatorParams) throws TalibException, NoQuotationsException {
		super(stock, firstDate, lastDate, calculationCurrency, firstIdxShift + 15, lastIdxShift);
		if (hasQuotations()) this.calculateIndicator(indicatorParams);
	}
	
	protected TalibIndicator(Quotations calcQuotations, Number...indicatorParams) throws TalibException {
		super(calcQuotations);
		if (hasQuotations()) this.calculateIndicator(indicatorParams);
	}

	protected void calculateIndicator(Number...indicatorParams) throws TalibException {
		
		if (!this.hasQuotations()) {
			LOGGER.warn("No Quotations for :" + this.getStockName() + " !! ");
			return;
		}

		Integer startIdx = this.startIdx();
		Integer endIdx = this.endIdx();
		initResArray(endIdx-startIdx+1);
		
		double[][] inData = getInputData();
		
		try {
			RetCode rc = talibCall(startIdx, endIdx, inData, indicatorParams);
				
			if (!rc.equals(RetCode.Success)) {
				LOGGER.warn(this.getClass().getName()+" Calculation error : " + rc + " for Quote :" + this.getStockName());
				throw new TalibException(this.getClass().getSimpleName()+" Calculation error : " + rc + " for share :" + this.getStockName(), new Throwable());
			} else {
				outBegDate = this.getIndicatorQuotationData().getDate(outBegIdx.value);
			}
		} catch (Exception e) {
			LOGGER.error(this.getClass().getName()+" Calculation error for Quote :" + this.getStockName(), e);
			throw new TalibException(this.getClass().getSimpleName()+" Calculation error : " + e + " for share : " + this.getStockName(), e);
		}
	
	}

	/**
	 * @return
	 */
	protected abstract double[][] getInputData();
	
	protected abstract void initResArray(int length);

	/**
	 * @param startIdx
	 * @param endIdx
	 * @param inData
	 * @return
	 */
	protected abstract RetCode talibCall(Integer startIdx, Integer endIdx, double[][] inData, Number... indicatorParams);
	
	
	
	/**
	 * Export mac dto csv.
	 * 
	 * @param filename the filename
	 * 
	 * @author Guillaume Thoreton
	 */
	public void exportToCSV() {
		File export = new File(System.getProperty("installdir") + File.separator + "tmp" + File.separator
				+ this.getStockName().replaceAll("[/\\*\\.\\?,;><|\\!\\(\\) \\&]", "_") + "_"+ this.getClass().getSimpleName() +".csv");
		
		Integer fdIx = ((this.startIdx()-this.outBegIdx.value) < 0)?0:this.startIdx()-this.outBegIdx.value;
		Integer ldIx = ((this.endIdx()-this.outBegIdx.value) < 0)?0:this.endIdx()-this.outBegIdx.value;

		try {
			FileWriter fos = new FileWriter(export);
			String header = getHeader();
			fos.write(header);
			for (int i = fdIx; i <= ldIx; i++) {
				String line = getLine(i , i + this.outBegIdx.value);
				fos.write(line);
			}
			fos.close();
		} catch (IOException e) {
			LOGGER.error("", e);
		}  finally {
			
		}
	}
	
	protected abstract String getHeader();
	
	protected abstract String getLine(int indicator, int quotation);

	public MInteger getOutBegIdx() {
		return outBegIdx;
	}

	public Date getOutBegDate() {
		return outBegDate;
	}

	public MInteger getOutNBElement() {
		return outNBElement;
	}
	
	public StripedQuotations getStripedData(Integer lagFix) {

		Integer fdIx = ((this.startIdx()-this.outBegIdx.value) < 0)?0:this.startIdx()-this.outBegIdx.value;
		Integer ldIx = ((this.endIdx()-this.outBegIdx.value) < 0)?0:this.endIdx()-this.outBegIdx.value;

		StripedQuotations ret = new StripedQuotations(ldIx-fdIx+1);

		for (int i = fdIx; i <= ldIx; i++) {
			ret.addBar(i, this.getIndicatorQuotationData().get(i + this.outBegIdx.value - lagFix).getDate(), getOutputData()[i]);
		}
		
		return ret;
	}

	abstract public double[] getOutputData();

	@Override
	public String toString() {
		return "TalibIndicator [outBegDate=" + outBegDate + ", outBegIdx=" + outBegIdx + ", outNBElement=" + outNBElement
				+ ", stock=" + stock + ", getIndicatorQuotationData()=" + getIndicatorQuotationData() + ", hasQuotations()="
				+ hasQuotations() + "]";
	}
	
	
}
