
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


package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.talib.indicators.TalibCoreService;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;

public class HullMASmoother extends Smoother {
	
	private int period;

	public HullMASmoother(int period) {
		super();
		this.period = period;
	}

	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data, Boolean fixLag) {
		
		double[] inReal = new double[data.size()];
		int i=0;
		for (double[] dv : data.values()) {
			inReal[i] = dv[0];
			i++;
		}
		
		int startIdx = 0;
		int endIdx = data.size()-1;
		MInteger halfSmaOutBegIdx = new MInteger();
		MInteger halfSmaOutNBElement = new MInteger();
		double[] halfSma = new double[data.size() - period/2 +1];
		RetCode halfSmaRc = TalibCoreService.getCore().sma(startIdx, endIdx, inReal, period/2, halfSmaOutBegIdx, halfSmaOutNBElement, halfSma);
		if (!halfSmaRc.equals(RetCode.Success)) throw new RuntimeException("Bad retCode "+halfSmaRc);
		
		MInteger smaOutBegIdx = new MInteger();
		MInteger smaOutNBElement = new MInteger();
		double[] sma = new double[data.size() - period +1];
		RetCode smaRc = TalibCoreService.getCore().sma(startIdx, endIdx, inReal, period, smaOutBegIdx, smaOutNBElement, sma);
		if (!smaRc.equals(RetCode.Success)) throw new RuntimeException("Bad retCode "+smaRc);
		
		double[] diffSma = new double[data.size() - period +1];
		int halfSmaStartIdx = smaOutBegIdx.value - halfSmaOutBegIdx.value;
		for (int j = 0; j < smaOutNBElement.value; j++) {
			double diffSmaData = 2*halfSma[j+halfSmaStartIdx] - sma[j];
			diffSma[j] = diffSmaData;
		}
		
		MInteger sqrSmaOutBegIdx = new MInteger();
		MInteger sqrSmaOutNBElement = new MInteger();
		int sqrSmaPeriod = (int) Math.rint(Math.sqrt(period));
		double[] sqrSma = new double[diffSma.length - sqrSmaPeriod +1];
		RetCode sqrSmaRc = TalibCoreService.getCore().sma(0, diffSma.length -1, diffSma, sqrSmaPeriod, sqrSmaOutBegIdx, sqrSmaOutNBElement, sqrSma);
		if (!sqrSmaRc.equals(RetCode.Success)) throw new RuntimeException("Bad retCode "+sqrSmaRc);
		
		int sqrStartIdx = period + sqrSmaPeriod;
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
		int j = 0;
		for (Date date : data.keySet()){
			if (j >= sqrStartIdx && j - sqrStartIdx < sqrSma.length) {
				ret.put(date, new double[] {sqrSma[j - sqrStartIdx]});
			}
			j++;
		}
		
		return ret;
	}

}
