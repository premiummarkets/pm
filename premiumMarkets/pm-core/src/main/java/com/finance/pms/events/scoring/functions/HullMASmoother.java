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
		
		MInteger smaOutBegIdx = new MInteger();
		MInteger smaOutNBElement = new MInteger();
		double[] sma = new double[data.size() - period +1];
		RetCode smaRc = TalibCoreService.getCore().sma(startIdx, endIdx, inReal, period, smaOutBegIdx, smaOutNBElement, sma);
		
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
