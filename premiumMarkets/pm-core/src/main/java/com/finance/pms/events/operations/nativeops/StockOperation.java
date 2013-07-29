package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;


@XmlRootElement
public class StockOperation extends DoubleMapOperation {
	

	protected static MyLogger LOGGER = MyLogger.getLogger(StockOperation.class);

	public StockOperation() {
		super("stock", "Time series of real stock historical data (close, low, high and volume))");
		setAvailableOutputSelectors(new ArrayList<String>( Arrays.asList(new String[]{"close","open","high","low","volume"})));
	}
	
	public StockOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleMapValue run(TargetStockInfo targetStock) {
		
		String outputSelector = getOutputSelector();
		QuotationDataType targetStockInputType = QuotationDataType.CLOSE;
		if (outputSelector != null) {
			targetStockInputType = QuotationDataType.valueOf(outputSelector.toUpperCase());
		}
		 
		SortedMap<Date, Double> buildSMapFromQuotations = new TreeMap<Date, Double>();
		try {
			switch(targetStockInputType) {
			case CLOSE :
				{
					Quotations quotationsInstance = QuotationsFactories.getFactory().getQuotationsInstance(targetStock.getStock(), targetStock.getStartDate(), targetStock.getEndDate(), true, null, 0, 0);
					buildSMapFromQuotations = QuotationsFactories.getFactory().buildExactSMapFromQuotations(quotationsInstance, targetStockInputType, quotationsInstance.getFirstDateShiftedIdx(), quotationsInstance.getLastDateIdx());
				}
			case HIGH :
			case LOW :
			case OPEN :
			case VOLUME :
			default :
				{
					Quotations quotationsInstance = QuotationsFactories.getFactory().getQuotationsInstance(targetStock.getStock(), targetStock.getStartDate(), targetStock.getEndDate(), true, null, 0, 0);
					buildSMapFromQuotations = QuotationsFactories.getFactory().buildExactSMapFromQuotations(quotationsInstance, targetStockInputType, quotationsInstance.getFirstDateShiftedIdx(), quotationsInstance.getLastDateIdx());
				}
			}

		} catch (Exception e) {
			LOGGER.error(e,e);
		} 
		
		return new DoubleMapValue(buildSMapFromQuotations);
	}

	@Override
	public int operationStartDateShift() {
		return 0;
	}
	
}
