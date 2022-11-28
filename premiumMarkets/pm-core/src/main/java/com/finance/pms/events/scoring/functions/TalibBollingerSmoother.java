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
package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.talib.indicators.TalibCoreService;
import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;

public class TalibBollingerSmoother extends Smoother {
	
	private static MyLogger LOGGER = MyLogger.getLogger(TalibSmaSmoother.class);
	
	
	private int optInTimePeriod;
	private double optInNbDevUp;
	private double optInNbDevDn;
	
	

	public TalibBollingerSmoother(int optInTimePeriod, double optInNbDevUp, double optInNbDevDn) {
		super();
		this.optInTimePeriod = optInTimePeriod;
		this.optInNbDevUp = optInNbDevUp;
		this.optInNbDevDn = optInNbDevDn;
	}



	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data, Boolean fixLag) {
		
		int lag = lagCalc(fixLag, optInTimePeriod); 
		
		MInteger outBegIdx = new MInteger();
		MInteger outNBElement = new MInteger();
		int endIdx = data.size()-1;
		int startIdx = 0;

		double[] outRealUpperBand = new double[data.size() - optInTimePeriod +1];
		double[] outRealMiddleBand = new double[data.size() - optInTimePeriod +1];
		double[] outRealLowerBand = new double[data.size() - optInTimePeriod +1];

		double[] inReal = new double[data.size()];
		int i=0;
		for (double[] dv : data.values()) {
			inReal[i] = dv[0];
			i++;
		}

		RetCode rc = TalibCoreService.getCore().bbands(startIdx, endIdx, inReal, optInTimePeriod, optInNbDevUp, optInNbDevDn, MAType.Sma, outBegIdx, outNBElement, outRealUpperBand, outRealMiddleBand, outRealLowerBand);

		if (LOGGER.isDebugEnabled()) LOGGER.debug("Smoothing res : retcode "+rc.name()+" out begin idx "+outBegIdx.value+", out nb ele "+outNBElement.value);

		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
		int j = 0;
		for (Date date : data.keySet()){
			int k = j - (optInTimePeriod - lag);
			if (j >= optInTimePeriod - lag && k < outRealMiddleBand.length) {
				ret.put(date, new double[] {outRealLowerBand[k],outRealMiddleBand[k],outRealUpperBand[k]});
			}
			j++;
		}


		return ret;
	}

}
