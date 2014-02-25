/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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
import java.util.Arrays;
import java.util.Date;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.Indicator;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.StripedQuotations;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;

public abstract class TalibIndicator extends Indicator {
	
	private static MyLogger LOGGER = MyLogger.getLogger(TalibIndicator.class);
	
	protected MInteger outBegIdx = new MInteger();
	protected MInteger outNBElement = new MInteger();
	protected Date outBegDate;
	
	private Number[] indicatorParams;

	
	protected TalibIndicator(Number...indicatorParams) {
		this.indicatorParams = indicatorParams;
	}

	public void calculateIndicator(Quotations quotations) throws TalibException {
		
		if (!quotations.hasQuotations()) {
			LOGGER.warn("No Quotations for :" + quotations.getStock() + " !! ");
			return;
		}
		
		RetCode rc = RetCode.InternalError;
		try {
			Integer startIdx = quotations.getFirstDateShiftedIdx();
			Integer endIdx = quotations.getLastDateIdx();
			initResArray(endIdx-startIdx+1);

			double[][] inData = getInputData(quotations);
			rc = talibCall(startIdx, endIdx, inData, indicatorParams);
			
		} catch (Exception e) {
			LOGGER.error(this.getClass().getName()+" Calculation error for Quote :" + quotations.getStock(), e);
			throw new TalibException(this.getClass().getSimpleName()+" Calculation error : " + e + " for share : " + quotations.getStock(), e);
		}

		if (!rc.equals(RetCode.Success)) {
			throw new TalibException(this.getClass().getSimpleName()+" Calculation error : " + rc + " for share :" + quotations.getStock(), new Throwable());
		} else {
			outBegDate = quotations.getDate(outBegIdx.value);
		}
	
	}

	protected abstract double[][] getInputData(Quotations quotations);
	
	protected abstract void initResArray(int length);
	
	public abstract Integer getStartShift();

	protected abstract RetCode talibCall(Integer startIdx, Integer endIdx, double[][] inData, Number... indicatorParams);

	@Override
	public void exportToCSV(Quotations quotations) {
		File export = new File(System.getProperty("installdir") + File.separator + "tmp" + File.separator + quotations.getStock().getFriendlyName().replaceAll("[/\\*\\.\\?,;><|\\!\\(\\) \\&]", "_") + "_"+ this.getClass().getSimpleName() +".csv");
		
		try {
			FileWriter fos = new FileWriter(export);
			String header = getHeader();
			fos.write(header);
			for (int i = quotations.getFirstDateShiftedIdx(); i <= quotations.getLastDateIdx(); i++) {
				String line = getLine(i , quotations.get(i + this.outBegIdx.value));
				fos.write(line);
			}
			fos.close();
		} catch (IOException e) {
			LOGGER.error("", e);
		}  finally {
			
		}
	}

	protected abstract String getHeader();
	
	protected abstract String getLine(Integer indicator, QuotationUnit qU);

	public MInteger getOutBegIdx() {
		return outBegIdx;
	}

	public Date getOutBegDate() {
		return outBegDate;
	}

	public MInteger getOutNBElement() {
		return outNBElement;
	}

	public abstract double[] getOutputData();

	@Override
	public String toString() {
		return "TalibIndicator [outBegIdx=" + outBegIdx + ", outNBElement=" + outNBElement + ", outBegDate=" + outBegDate + ", indicatorParams="+ Arrays.toString(indicatorParams) + "]";
	}
	
	public StripedQuotations indicatorStrip(Quotations quotations) {
		StripedQuotations striped = new StripedQuotations(quotations.getLastDateIdx() - this.getOutBegIdx().value - quotations.getFirstDateShiftedIdx() + 1);
		for (int i = quotations.getFirstDateShiftedIdx(); i <= quotations.getLastDateIdx() - this.getOutBegIdx().value; i++) {
			striped.addCloseOnlyBar(quotations.getStock(), i, quotations.get(i + this.getOutBegIdx().value).getDate(), this.getOutputData()[i]);
		}
		return striped;
	}
	
	public abstract ValidityFilter quotationValidity();
	
}
