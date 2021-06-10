package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.EventMapOperation;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.conditional.EventMapValue;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.OTFTuningFinalizer;
import com.finance.pms.events.scoring.dto.PeriodRatingDTO;
import com.finance.pms.events.scoring.dto.TuningResDTO;

public class IndicatorStatsOperation extends ArrayMapOperation {
	
	private static MyLogger LOGGER = MyLogger.getLogger(IndicatorStatsOperation.class);
	
	public IndicatorStatsOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public IndicatorStatsOperation() {
		this(
				"indicatorStats", 
				"Give the performance stats for buy and sell events input. "
				+ "ln(failed buy weight/succesful buy weight). "
				+ "Calculated from date range start to date and from date to date range end.",
				new NumberOperation("number","periodRangeSpan", "Look back period ratio for accumulation, 'NaN' is the full range from start to end date.", new NumberValue(221.0)),
				new DoubleMapOperation("data", "quotations", "Quotations map used for the events calculations", null),
				new EventMapOperation("data", "indicatorsCompositioners", "Indicators compositions Event Map list", null)
		);
	}

	public IndicatorStatsOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleArrayMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		Double periodRangeSpan = ((NumberValue) inputs.get(0)).getValue(targetStock).doubleValue();
		SortedMap<Date, Double> quotations = ((NumericableMapValue) inputs.get(1)).getValue(targetStock);
		SortedMap<EventKey, EventValue> indicatorEvents = ((EventMapValue) inputs.get(2)).getEventMap();
		
		OTFTuningFinalizer tuningFinalizer = (OTFTuningFinalizer) SpringContext.getSingleton().getBean("tuningFinalizer");
		try {
			Date startDate = targetStock.getStartDate(0);
			Date endDate = targetStock.getEndDate();
			TuningResDTO tuningRes = tuningFinalizer.buildTuningRes(targetStock.getStock(), startDate, endDate, quotations, indicatorEvents.values());
			
			List<PeriodRatingDTO> periods = tuningRes.getPeriods();
			Long periodAvgLength = periods.stream().map(e -> e.getPeriodLenght()).reduce((a,pl) -> a + pl).get()/periods.size();
			LOGGER.info(this.getReference() + " average period size: " + periodAvgLength);
			
			SortedMap<Date, double[]> results = new TreeMap<>();
			quotations.keySet().stream().forEach(k -> {
				
				if (k.compareTo(startDate) >= 0) {
					Calendar iterSliderStart = Calendar.getInstance();
					if (Double.isNaN(periodRangeSpan)) {
						iterSliderStart.setTime(startDate);
					} else {
						iterSliderStart.setTime(k);
						QuotationsFactories.getFactory().incrementDate(iterSliderStart,-periodRangeSpan.intValue()*periodAvgLength.intValue());
					}
					
					Calendar iterSliderEnd = Calendar.getInstance();
					if (Double.isNaN(periodRangeSpan)) {
						iterSliderEnd.setTime(endDate);
					} else {
						iterSliderEnd.setTime(k);
						//QuotationsFactories.getFactory().incrementDate(iterSliderEnd,+periodRangeSpan.intValue()*periodAvgLength.intValue());
					}
					
					Double[] shiftStats = tuningRes.getStatsBetween(iterSliderStart.getTime(), iterSliderEnd.getTime());
					Double shiftFailureWeight = shiftStats[2];
					Double shiftSuccessWeight = shiftStats[3];
					double shiftFailureLogweight = 0;
					if (shiftFailureWeight == 0 && shiftSuccessWeight == 0) {//No event in the date range
						shiftFailureLogweight = Double.NEGATIVE_INFINITY; //This is for non line breaking charting
					} else if (shiftFailureWeight == 0) { //100% success //XXX 6 and -6 are arbitrarily set.
						shiftFailureLogweight = -6;
					} else if (shiftSuccessWeight == 0) { //100% failure
						shiftFailureLogweight = +6;
					} else {
						shiftFailureLogweight = Math.log(Math.abs(shiftFailureWeight)/shiftSuccessWeight);
					}
					
					results.put(k, new double[] {shiftFailureLogweight});
				}
			});
			
			List<String> columns = new ArrayList<>();
			columns.add("backwardShift");
			
			return new DoubleArrayMapValue(results, columns, 0);

		} catch (NotEnoughDataException e) {
			LOGGER.warn(this.getReference() + ": " + e, true);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}

		return new DoubleArrayMapValue();
	}

	@Override
	public Boolean isIdemPotent() {
		return false;
	}

	@Override
	public int operandsRequiredStartShift() {
		int shift = getOperands().get(0).operandsRequiredStartShift();
		return Double.isNaN(shift)?0:shift;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock) {
		//Nothing
	}

	@Override
	public boolean isDateSensitive() {
		//return true; //XXX will suggest recalculating the root indicator and all its operands (not only this operand) => heavy
		return false;
	}

}
