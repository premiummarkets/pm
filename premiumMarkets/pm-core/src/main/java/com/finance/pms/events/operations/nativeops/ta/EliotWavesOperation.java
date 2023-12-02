package com.finance.pms.events.operations.nativeops.ta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.conditional.EventMapValue;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.EventMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.Value;

/**
 * https://www.investopedia.com/terms/e/elliottwavetheory.asp
 * @author guil
 *
 */
@SuppressWarnings("all") //TODO WIP
public class EliotWavesOperation extends EventMapOperation {

	public EliotWavesOperation(String reference, String description, Operation ... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public EliotWavesOperation() {
		this("gx_eliot", "Eliot waves.",
						new NumberOperation("number","period","The period for data points to pick", new NumberValue(1.0)),
						new DoubleMapOperation("Series to analyse"));
	}
	
	
	@Override
	public EventMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Double period = ((NumberValue) inputs.get(0)).getValue(targetStock).doubleValue(); //TODO
		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(1)).getValue(targetStock);
		
		ArrayList<Date> times = new ArrayList<>(data.keySet());
		ArrayList<Double> values = new ArrayList<>(data.values());
		
		for (int i = 0 ; i < values.size(); i++) {
			
			//TODO the reverse buy signal

			//Impulse Waves 5-3-5-3-5: 5 waves
			List<Double> impulseDataPoints = IntStream.range(0, 5) //5 data points + 1 //TODO create the differential series before the loop
				.mapToObj(dp -> values.get(dp + 1) - values.get(dp)) 
				.collect(Collectors.toList());
			double wave1_motive = impulseDataPoints.get(0);
			boolean isImpulse = false;
			if (wave1_motive > 0) { //TODO + low volumes //TODO the number of impulse waves can be smaller?
				double wave2_corrective = impulseDataPoints.get(1);
				if (wave2_corrective < 0 && Math.abs(wave1_motive) > Math.abs(wave2_corrective)) {
					double wave3_motive = impulseDataPoints.get(2);
					if (wave3_motive > 0) {
						double wave4_corrective = impulseDataPoints.get(3);
						if (wave4_corrective < 0 && Math.abs(wave3_motive) > (Math.abs(wave4_corrective) + Math.abs(wave2_corrective))) {
							double wave5_motive = impulseDataPoints.get(4);
							if (wave5_motive > 0 &&  Math.abs(wave3_motive) > Math.min(Math.abs(wave1_motive), Math.min(Math.abs(wave3_motive), Math.abs(wave5_motive)))) {
								isImpulse = true;
							};
						}
					}
				}
			}
			
			// Corrective Waves
			if (isImpulse) {
				List<Double> correcitveDataPoints = IntStream.range(0, 3)
						.mapToObj(dp -> values.get(dp + 1) - values.get(dp)) 
						.collect(Collectors.toList());
				double waveA_corrective = correcitveDataPoints.get(0);
				if (waveA_corrective < 0) {
					double waveB_motive = correcitveDataPoints.get(1);
					if (waveB_motive > 0 && Math.abs(waveB_motive) < Math.abs(waveA_corrective)) { //TODO + low volumes
						double waveC_corrective = correcitveDataPoints.get(2);
						if (waveC_corrective < 0 && Math.abs(waveC_corrective) > Math.abs(waveB_motive)) {
							//TODO sell signal
						}
					}
				}
			}
		}
		
		//TODO Fractals??: The waves, motive or corrective, denoted above can actually be smaller Eliot waves patterns within 
		//ie a motive wave is actually composed of the five impulse waves and respectively a corrective wave composed of the 3 corrective waves.
		//hence the 5-3-5-3-5 denomination for the impulse phase
		//It is more conuse for the corrective phase where the sub waves can be:
		//		Zig-zags (Simple, double and triple zig-zags): 5-3-5 for respectively A-B-C
 //		Flats (Regular flat, Expanding flat, Running flat )
//		Triangles
//		Double and Triple Threes
		
		return null;
	}

}
