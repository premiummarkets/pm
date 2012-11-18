package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.talib.indicators.TalibCoreService;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;

public class TalibLinearSmoother extends Smoother {
	
	private static MyLogger LOGGER = MyLogger.getLogger(TalibLinearSmoother.class);

	private int period;
	

	public TalibLinearSmoother(int period) {
		super();
		this.period = period;
	}


	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data , Boolean fixLag) {
		
		if (fixLag != null && fixLag) {
			throw new NotImplementedException();
		} 
		
		MInteger outBegIdx = new MInteger();
		MInteger outNBElement = new MInteger();
		int endIdx = data.size()-1;
		int startIdx = 0;
		
		double[] linearRegression = new double[data.size()];
		
		double[] inReal = new double[data.size()];
		int i=0;
		for (double[] dv : data.values()) {
			inReal[i] = dv[0];
			i++;
		}
		
		RetCode rc = TalibCoreService.getCore().linearReg(startIdx, endIdx, inReal, period, outBegIdx, outNBElement, linearRegression);
		
		LOGGER.debug("linear smothing res : retcode "+rc.name()+" out begin idx "+outBegIdx.value+", out nb ele "+outNBElement.value);
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
		List<Date> dates = new ArrayList<Date>(data.keySet());
		for (int k = outBegIdx.value; k < outBegIdx.value + outNBElement.value; k++) {
			ret.put(dates.get(k), new double[] {linearRegression[k-outBegIdx.value]});
		}
		
		return ret;
	}

}
