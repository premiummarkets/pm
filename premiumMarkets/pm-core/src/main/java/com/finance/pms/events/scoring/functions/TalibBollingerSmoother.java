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

		LOGGER.debug("Smoothing res : retcode "+rc.name()+" out begin idx "+outBegIdx.value+", out nb ele "+outNBElement.value);

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
