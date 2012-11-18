package com.finance.pms.events.scoring.functions;

import java.util.Arrays;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.talib.indicators.TalibCoreService;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;

public class TalibEmaSmoother extends Smoother {
	
	private static MyLogger LOGGER = MyLogger.getLogger(TalibEmaSmoother.class);

	private int period;
	

	public TalibEmaSmoother(int period) {
		super();
		this.period = period;
	}


	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data , Boolean fixLag) {
		
		int lag = 0;
		if (fixLag) {
			lag = (period - 1) / 2;
		} 
		
		MInteger outBegIdx = new MInteger();
		MInteger outNBElement = new MInteger();
		int endIdx = data.size()-1;
		int startIdx = 0;
		
		double[] ema = new double[data.size() - period +1];
		
		double[] inReal = new double[data.size()];
		int i=0;
		for (double[] dv : data.values()) {
			inReal[i] = dv[0];
			i++;
		}
		
		RetCode rc;
		if (period == 1) {
			ema = Arrays.copyOfRange(inReal, startIdx, endIdx);
			outBegIdx = new MInteger();
			outBegIdx.value = startIdx;
			outNBElement = new MInteger();
			outNBElement.value = endIdx - outBegIdx.value;
			rc = RetCode.Success;
		} else {
			rc = TalibCoreService.getCore().ema(startIdx, endIdx, inReal, period, outBegIdx, outNBElement, ema);
		}
		LOGGER.debug("l smothing res : retcode "+rc.name()+" out begin idx "+outBegIdx.value+", out nb ele "+outNBElement.value);
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
		int j = 0;
		for (Date date : data.keySet()){
			if (j >= period - lag && (j- (period - lag)) < ema.length) {
				ret.put(date, new double[] {ema[j - (period - lag)]});
			}
			j++;
		}
		
		
		return ret;
	}

}
