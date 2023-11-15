package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.util.ValueManipulator;
import com.finance.pms.events.operations.util.ValueManipulator.InputToArrayReturn;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.SplitOption;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;

public class DataTypeCheckOperation extends ArrayMapOperation {
	
	private static MyLogger LOGGER = MyLogger.getLogger(DataTypeCheckOperation.class);
	
	public DataTypeCheckOperation() {
		this("dataTypeCheck", "dataTypeCheck", 
				new DoubleMapOperation("dataTypedInput", "dataTypedInput", "An input that is typed with (ohlcv), the validity of which will reflect the output close.", null));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	public DataTypeCheckOperation(String reference, String description, Operation ...operands) {
		super(reference, description, new ArrayList<>(Arrays.asList(operands)));
	}

	@Override
	public DoubleArrayMapValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		try {
			
			Quotations quotationsFilteredClose = 
					QuotationsFactories.getFactory().getRawQuotationsInstance(
							targetStock.getStock(), targetStock.getStartDate(thisStartShift), targetStock.getEndDate(), true,
							targetStock.getStock().getMarketValuation().getCurrency(), 0, SplitOption.SPLITFREE, ValidityFilter.getFilterFor(getRequiredStockData()));
			SortedMap<Date, Double> filteredClose = QuotationsFactories.getFactory().buildExactSMapFromQuotationsClose(quotationsFilteredClose, 0, quotationsFilteredClose.size()-1);
			
			Quotations quotations = 
					QuotationsFactories.getFactory().getRawQuotationsInstance(
							targetStock.getStock(), targetStock.getStartDate(thisStartShift), targetStock.getEndDate(), true,
							targetStock.getStock().getMarketValuation().getCurrency(), 0, SplitOption.SPLITFREE, ValidityFilter.CLOSE);
			SortedMap<Date, Double> close = QuotationsFactories.getFactory().buildExactSMapFromQuotationsClose(quotations, 0, quotations.size()-1);
			
			List<? extends NumericableMapValue> mapsList = Arrays.asList(new DoubleMapValue(filteredClose), new DoubleMapValue(close));
			SortedMap<Date, double[]> arrayMap = ValueManipulator.inputListToArray(targetStock, mapsList, true, true).get(InputToArrayReturn.RESULTS);
			
			List<String> columns = Arrays.asList("filtered", "close");
			return new DoubleArrayMapValue(arrayMap, columns, 0);
			
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
	public void invalidateOperation(String analysisName, Optional<TargetStockInfo> targetStock) {
		
	}
	
	

}
