package com.finance.pms.events.operations.nativeops.trans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleArrayMapValue;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.operations.nativeops.ta.PMWithDataOperation;
import com.finance.pms.events.operations.util.ValueManipulator;
import com.finance.pms.events.operations.util.ValueManipulator.InputToArrayReturn;

public class TriggerPointJoiner extends PMWithDataOperation {
	
	private static MyLogger LOGGER = MyLogger.getLogger(TriggerPointJoiner.class);
	
	private static final int FIRST_INPUT = 2;

	public TriggerPointJoiner() {
		super("triggerPointJoiner", "Return the time points maps and values of a set of Series where the reference Series crosses a threshold",
				new DoubleMapOperation("data", "reference series", "Reference series triggering data collection on thershold cross.", null),
				new NumberOperation("number","mean","Mean of the Period lengths", new NumberValue(0.0)),
				new DoubleMapOperation("data", "datasets", "Data sets to collect joint points from.", null));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	public TriggerPointJoiner(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}
	
	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		SortedMap<Date,Double> referenceDs = ((NumericableMapValue)inputs.get(0)).getValue(targetStock);
		double threshold = ((NumberValue) inputs.get(1)).getValue(targetStock).doubleValue();
		
		SortedMap<Date, double[]> joinPoints = new TreeMap<>();
		try {
			
			@SuppressWarnings("unchecked")
			List<? extends NumericableMapValue> developpedInputs = (List<? extends NumericableMapValue>) inputs.subList(FIRST_INPUT, inputs.size());
			SortedMap<Date, double[]> inputListToArray = ValueManipulator.inputListToArray(targetStock, developpedInputs, false, true).get(InputToArrayReturn.RESULTS);
		
			
			Date previousDate = referenceDs.firstKey();
			double previousValue = referenceDs.get(previousDate);
			for (Date currentDate: referenceDs.keySet()) {
				double currentValue = referenceDs.get(currentDate).doubleValue();
				boolean isThresholdCross = (previousValue < threshold && currentValue >= threshold) || (previousValue > threshold && currentValue <= threshold);
				
				double[] avgJoinPoints = new double[developpedInputs.size()];
				if (isThresholdCross) {
					double[] previousJoinPoints = inputListToArray.get(previousDate);
					double[] currentJoinPoints = inputListToArray.get(currentDate);
					for (int i=0; i < avgJoinPoints.length; i++) {
						avgJoinPoints[i] = (previousJoinPoints[i] + currentJoinPoints[i])/2;
					}
				} else {
					for (int i=0; i < avgJoinPoints.length; i++) {
						avgJoinPoints[i] = Double.NEGATIVE_INFINITY; //This is for non line breaking charting
					}
				}
				joinPoints.put(currentDate, avgJoinPoints);
				previousValue = currentValue;
				previousDate = currentDate;
			}
			
		} catch (Exception e) {
			LOGGER.error(this.getReference() + " : " + e, e);
		}
		
		List<String> headers = getOperands().subList(FIRST_INPUT, getOperands().size()).stream().map(o -> o.getReference() + " Jpoints").collect(Collectors.toList());
		return new DoubleArrayMapValue(joinPoints, headers, 0);
	}


}
