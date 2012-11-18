package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.talib.indicators.TalibCoreService;
import com.tictactec.ta.lib.MInteger;

public class ZeroLagEMASmoother extends Smoother {
	
	int period;
	private MInteger doubleEmaOutBegIdx;
	private MInteger doubleEmaOutNBElement;
	
	
	public ZeroLagEMASmoother(int period) {
		super();
		this.period = period;
	}


	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data, Boolean fixLag) {
		
		double[][] inReal = new double[data.size()][];
		int i=0;
		for (double[] dv : data.values()) {
			
			double[] inRealV = new double[dv.length];
			for (int k = 0; k < dv.length; k++) {
				inRealV[k] = dv[k];
			}
			inReal[i] = inRealV;
			
			i++;
		}
		
		double[][] zeroLagEmas = smooth(inReal);
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
		int j = 0;
		for (Date date : data.keySet()){
			if (j >= 2*period  && (j- (2*period)) < zeroLagEmas.length) {
				
				//ret.put(date, new double[] {zeroLagEmas[j - (2*period)]});
				double[] retV = new double[zeroLagEmas[j - (2*period)].length];
				for (int k = 0; k < zeroLagEmas[j - (2*period)].length; k++) {
					retV[k] = zeroLagEmas[j - (2*period)][k];
				}
				ret.put(date,retV);
				
			}
			j++;
		}
		return ret;
		
	}


	public double[][] smooth(double[][] inReal) {
		
		List<double[]> inRealDecomp = new ArrayList<double[]>();
		double[] ds0 = inReal[0];
		for (double d : ds0) {
			inRealDecomp.add(new double[inReal.length]);
		}
		for (int i = 0; i < inReal.length; i++) {
			double[] ds = inReal[i];
			for (int k = 0; k < ds.length; k++) {
				inRealDecomp.get(k)[i] = ds[k];
			}
		}
		
		double[][] zeroLagEmas = null;
		for (int k = 0; k < inRealDecomp.size(); k++) {
			double[] oneInReal = inRealDecomp.get(k);
			
			MInteger emaOutBegIdx = new MInteger();
			MInteger emaOutNBElement = new MInteger();
			int endIdx = oneInReal.length-1;
			int startIdx = 0;
			double[] ema = new double[oneInReal.length - period +1];
			TalibCoreService.getCore().ema(startIdx, endIdx, oneInReal, period, emaOutBegIdx, emaOutNBElement, ema);
			
			doubleEmaOutBegIdx = new MInteger();
			doubleEmaOutNBElement = new MInteger();
			double[] doubleEma = new double[ema.length - period +1];
			TalibCoreService.getCore().ema(0, ema.length-1, ema, period, doubleEmaOutBegIdx, doubleEmaOutNBElement, doubleEma);
			
			//double[] zeroLagEma = new double[doubleEma.length];
			if (zeroLagEmas == null) zeroLagEmas= new double[doubleEma.length][];
			for (int j = 0; j < doubleEmaOutNBElement.value; j++) {
				double zeroLagEmaData = 2*ema[j+period-1] - doubleEma[j];
				
				//zeroLagEma[j] = zeroLagEmaData;
				if (zeroLagEmas[j] == null) zeroLagEmas[j] = new double[inRealDecomp.size()];
				zeroLagEmas[j][k] = zeroLagEmaData;
				
			}
		}
		
		
		return zeroLagEmas;
		
	}


	public MInteger getDoubleEmaOutBegIdx() {
		return doubleEmaOutBegIdx;
	}


	public MInteger getDoubleEmaOutNBElement() {
		return doubleEmaOutNBElement;
	}

}
