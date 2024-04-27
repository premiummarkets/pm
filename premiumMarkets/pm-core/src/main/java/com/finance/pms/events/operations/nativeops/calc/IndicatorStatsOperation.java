package com.finance.pms.events.operations.nativeops.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.conditional.EventMapValue;
import com.finance.pms.events.operations.nativeops.ArrayMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleArrayMapValue;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.EventMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.Value;
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
				new NumberOperation("number","periodRangeSpan", "Look back period ratio for accumulation, 'NaN' is the full range from start to end date.", new NumberValue(254.0)),
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
	public DoubleArrayMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		Double periodRangeSpan = ((NumberValue) inputs.get(0)).getValue(targetStock).doubleValue();
		SortedMap<Date, Double> quotations = ((NumericableMapValue) inputs.get(1)).getValue(targetStock);
		SortedMap<EventKey, EventValue> indicatorEvents = ((EventMapValue) inputs.get(2)).getEventMap();
		
		OTFTuningFinalizer tuningFinalizer = (OTFTuningFinalizer) SpringContext.getSingleton().getBean("tuningFinalizer");
		try {
			Date startDate = targetStock.getStartDate(0);
			Date endDate = targetStock.getEndDate();
			TuningResDTO tuningRes = tuningFinalizer.buildTuningRes(targetStock.getStock(), startDate, endDate, quotations, indicatorEvents.values());
			
			SortedMap<Date, double[]> results = new TreeMap<>();
			quotations.keySet().stream().forEach(k -> {
				
				if (k.compareTo(startDate) >= 0) {
					Calendar iterSliderStart = Calendar.getInstance();
					if (Double.isNaN(periodRangeSpan)) {
						iterSliderStart.setTime(startDate);
					} else {
						iterSliderStart.setTime(k);
						try {
							List<PeriodRatingDTO> periods = tuningRes.getPeriods();
//							Long periodAvgLength = periods.stream().map(e -> e.getPeriodLenght()).reduce((a,pl) -> a + pl).get()/periods.size();
//							LOGGER.info(this.getReference() + " average period size: " + periodAvgLength);
							QuotationsFactories.getFactory().incrementDate(targetStock.getStock(),targetStock.getQuotationsDataTypes(),iterSliderStart,-periodRangeSpan.intValue());
							Optional<PeriodRatingDTO> lastPeriodSartingBeforeShift = periods.stream().filter(p -> p.getTrend().equals("BULLISH") && p.isRealised() && p.getTo().before(iterSliderStart.getTime())).reduce((first, second) -> second);
							if (lastPeriodSartingBeforeShift.isPresent()) {
								iterSliderStart.setTime(lastPeriodSartingBeforeShift.get().getFrom());
							} else {
								iterSliderStart.setTime(startDate);
							}
						} catch (NotEnoughDataException e1) {
							throw new RuntimeException(e1);
						}
					}
					
					Calendar iterSliderEnd = Calendar.getInstance();
					iterSliderEnd.setTime(k);
					
//					pf.format(entry.getValue().getForecastProfit()) + "," + pf.format(entry.getValue().getForecastProfitUnReal()) + "," + pf.format(entry.getValue().getBuyNHoldProfit()) + "," +
//					pf.format(stats[0]) + "," + pf.format(stats[1])
//					+ "," + pf.format(Math.abs(stats[2])) + "," + ((stats[3] == 0)?10000:(stats[2] == 0)?-10000:pf.format(Math.log(Math.abs(stats[2])/stats[3])))
//					+ "," + pf.format(stats[4]) + "," + pf.format(stats[5]) + "," + pf.format(Math.sqrt(stats[6])) + "\n"
					
					Date start = iterSliderStart.getTime();
					Date end = iterSliderEnd.getTime();
					Map<String, Double> shiftStats = tuningRes.getStatsBetween(start, end, "BULLISH", (x) -> x < 0);
					Double sFW = shiftStats.get("failureWeigh");
					Double sSW = shiftStats.get("successWeigh");
					double shiftFailureLogweight = 0;
					if ( (sFW == null || sFW.isNaN() || sFW == 0) && (sSW == null || sSW.isNaN() || sSW == 0)) {//No event in the date range
						shiftFailureLogweight = Double.NEGATIVE_INFINITY; //This is for non line breaking charting
					} else if (sFW == 0) { //100% success //XXX 6 and -6 are arbitrarily set.
						shiftFailureLogweight = -6;
					} else if (sSW == 0) { //100% failure
						shiftFailureLogweight = +6;
					} else {
						shiftFailureLogweight = Math.log(Math.abs(sFW)/sSW);
					}
					
					double buyNHold = Double.NEGATIVE_INFINITY;
					Double endQ = quotations.get(end);
					Double startQ = quotations.get(start);
					if (endQ != null && startQ != null) {
						buyNHold = endQ/startQ-1;
					}
					
					results.put(k, new double[] {
								tuningRes.getForecastProfitBetween(start, end), tuningRes.getForecastProfitUnRealBetween(start, end), buyNHold,
								shiftStats.get("avgROC"), shiftStats.get("failureRatio"), Math.abs(shiftStats.get("failureWeigh")), 
								shiftFailureLogweight, shiftStats.get("minROC"), shiftStats.get("maxROC"), Math.sqrt(shiftStats.get("varianceROC"))
							});
					
				}
			});
			
			List<String> columns = new ArrayList<>();
			columns.addAll(Arrays.asList("r","ur","b&h","avg","fail","fwght","flog","min","max","std"));
			
			return new DoubleArrayMapValue(results, columns, mainInputPosition());

		} catch (NotEnoughDataException e) {
			LOGGER.warn(this.getReference() + ": " + e, true);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}

		return new DoubleArrayMapValue();
	}

	@Override
	public Boolean isIdemPotent(TargetStockInfo targetStock) {
		return false;
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		Double shift = ((NumberValue) getOperands().get(0).getOrRunParameter(targetStock).orElse(new NumberValue(0.0))).getValue(targetStock).doubleValue();
		return Double.isNaN(shift) ? 0 : shift.intValue();
	}

	@Override
	public int mainInputPosition() {
		return 6;
	}

	@Override
	public Boolean isNoOverrideDeltaOnly(TargetStockInfo targetStock) {
		return false;
	}
	
	

}
