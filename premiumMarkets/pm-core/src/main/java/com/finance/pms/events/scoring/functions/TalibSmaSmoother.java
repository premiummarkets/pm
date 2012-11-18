package com.finance.pms.events.scoring.functions;

import java.util.Arrays;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.talib.indicators.TalibCoreService;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;

public class TalibSmaSmoother extends Smoother {
	
	private static MyLogger LOGGER = MyLogger.getLogger(TalibSmaSmoother.class);

	private int period;
	

	public TalibSmaSmoother(int period) {
		super();
		this.period = period;
	}


	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data , Boolean fixLag) {
		
		int lag = 0;
		if (fixLag) {
			//lag = (period - 1) / 2;
			lag = period / 2 + 1;
		} 
		
		MInteger outBegIdx = new MInteger();
		MInteger outNBElement = new MInteger();
		int endIdx = data.size()-1;
		int startIdx = 0;
		
		double[] sma = new double[data.size() - period +1];
		
		double[] inReal = new double[data.size()];
		int i=0;
		for (double[] dv : data.values()) {
			inReal[i] = dv[0];
			i++;
		}
		
		RetCode rc;
		if (period == 1) {
			sma = Arrays.copyOfRange(inReal, startIdx, endIdx);
			outBegIdx = new MInteger();
			outBegIdx.value = startIdx;
			outNBElement = new MInteger();
			outNBElement.value = endIdx - outBegIdx.value;
			rc = RetCode.Success;
		} else {
			rc = TalibCoreService.getCore().sma(startIdx, endIdx, inReal, period, outBegIdx, outNBElement, sma);
		}
		LOGGER.debug("smothing res : retcode "+rc.name()+" out begin idx "+outBegIdx.value+", out nb ele "+outNBElement.value);
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
		int j = 0;
		for (Date date : data.keySet()){
			if (j >= period - lag && (j- (period - lag)) < sma.length) {
				ret.put(date, new double[] {sma[j - (period - lag)]});
			}
			j++;
		}
		
		
		return ret;
	}

}
