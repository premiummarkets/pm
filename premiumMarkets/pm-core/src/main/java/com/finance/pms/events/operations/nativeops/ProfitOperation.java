package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.stream.Collectors;

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
import com.finance.pms.events.operations.conditional.MultiValuesOutput;
import com.finance.pms.events.operations.util.ValueManipulator;
import com.finance.pms.events.operations.util.ValueManipulator.InputToArrayReturn;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.OTFTuningFinalizer;
import com.finance.pms.events.scoring.dto.TuningResDTO;

public class ProfitOperation extends ArrayMapOperation implements MultiValuesOutput {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ProfitOperation.class);
	
	public ProfitOperation(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}
	
	public ProfitOperation(String reference, String description, Operation... operands) {
		this(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public ProfitOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	public ProfitOperation() {
		this("profit", "Calculate the profits (the my way) of series of Events from Indicators",
		new StringOperation("boolean", "unReal", "Add the un realised profit", new StringValue("TRUE")),
		new EventMapOperation("data", "indicatorsCompositioners", "Event Series to analyse profit from (an Indicator)", null)); 
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	@Override
	public DoubleArrayMapValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		Boolean addUnReal = Boolean.valueOf(((StringValue) inputs.get(0)).getValue(targetStock));
		List<SortedMap<EventKey, EventValue>> buySellEventSeries = inputs.subList(1, inputs.size()).stream().map(v -> ((EventMapValue) v).getEventMap()).collect(Collectors.toList());

		try {

			Stock stock = targetStock.getStock();
			Quotations quotations  = QuotationsFactories.getFactory()
					.getSpliFreeQuotationsInstance(stock, targetStock.getStartDate(thisStartShift), targetStock.getEndDate(), true, stock.getMarketValuation().getCurrency(), 0, ValidityFilter.CLOSE);
			SortedMap<Date, Double> qMap = QuotationsFactories.getFactory().buildExactSMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size()-1);

			List<NumericableMapValue> resultMaps = new ArrayList<>();
			List<String> headers = new ArrayList<>();
			
			OTFTuningFinalizer tuningFinalizer = (OTFTuningFinalizer) SpringContext.getSingleton().getBean("tuningFinalizer");
			
			for (SortedMap<EventKey, EventValue> buySellEventSerie : buySellEventSeries) {
				
				if (buySellEventSerie.isEmpty()) continue;
				
				//outs
				DoubleMapValue currentResultMap = new DoubleMapValue();
				resultMaps.add(currentResultMap);
				headers.add(buySellEventSerie.firstKey().getEventInfo().getEventDefinitionRef());
				
				//ins
				//buySellEventSerie
				
				//go
				Date startDate = targetStock.getStartDate(0);
				Date endDate = targetStock.getEndDate();
				TuningResDTO tuningRes = tuningFinalizer.buildTuningRes(targetStock.getStock(), startDate, endDate, qMap, buySellEventSerie.values());
				
				qMap.keySet().stream().forEach(qDate -> {
					if (qDate.compareTo(startDate) >= 0) {
						Double profitAtDate = (addUnReal)?tuningRes.getForecastProfitAtUnReal(qDate):tuningRes.getForecastProfitAt(qDate);
						currentResultMap.getValue(targetStock).put(qDate, profitAtDate);
					}
				});
			
			}

			SortedMap<Date, double[]> inputListToArray = ValueManipulator.inputListToArray(targetStock, resultMaps, true, true).get(InputToArrayReturn.RESULTS);
			DoubleArrayMapValue result = new DoubleArrayMapValue(inputListToArray, headers, 0);
			return result;
			
		} catch (NotEnoughDataException e) {
			LOGGER.warn(this.getReference() + ": " + e, true);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return new DoubleArrayMapValue();
	}
	
	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock, Object... addtionalParams) {
	}

	@Override
	public int mainInputPosition() {
		return 0;
	}

}
