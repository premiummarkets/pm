package com.finance.pms.events.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.quotation.QuotationUpdate.QuotationUpdateException;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.calculation.WarningException;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.MapOperation;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.OperationReferenceOperation;
import com.finance.pms.events.operations.nativeops.OperationReferenceValue;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.threads.ConfigThreadLocal;

public class TargetStockDelegateOperation extends MapOperation {
	
	private static MyLogger LOGGER = MyLogger.getLogger(TargetStockDelegateOperation.class);
	
	public TargetStockDelegateOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public TargetStockDelegateOperation() {
		this("targetStockDelegate", "Runs the operation passed as parameter on the choosen stock instead of the current target.",
		new OperationReferenceOperation("operationReference", "operation reference", "Operation to run using the stock data.", null),
		new StringOperation("string", "TargetStock", "Stock to apply the operation upon.", new StringValue("symbol")));
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		Operation operationClone = (Operation) ((OperationReferenceValue<?>) inputs.get(0)).getValue(targetStock).clone();
		String stockSymbolDelegate = ((StringValue) inputs.get(1)).getValue(targetStock);

		Stock stockDelegate = DataSource.getInstance().getShareDAO().loadStockByIsinOrSymbol(stockSymbolDelegate).get(0);
		
		//Update quotations for the delegate ..
		try {
			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, new EventSignalConfig());
			QuotationUpdate quotationUpdate = new QuotationUpdate();
			quotationUpdate.getQuotesFor(stockDelegate);
		} catch (QuotationUpdateException e) {
			LOGGER.warn(e);
		}
		
		try {
			//Run against the delegate
			TargetStockInfo tStockDelegate = new TargetStockInfo(
					targetStock.getAnalysisName(), targetStock.getEventInfoOpsCompoOperation(), 
					stockDelegate, targetStock.getStartDate(0), targetStock.getEndDate());
			NumericableMapValue output = (NumericableMapValue) operationClone.run(tStockDelegate, addThisToStack(thisCallStack, thisStartShift, 0, targetStock), thisStartShift + 0);
			
			//Potential missing keys:
			try {
				ValidityFilter filterFor = ValidityFilter.getFilterFor(this.getRequiredStockData());
				Stock stock = targetStock.getStock();
				Quotations quotations = QuotationsFactories.getFactory()
						.getSplitFreeQuotationsInstance(stock, targetStock.getStartDate(thisStartShift), targetStock.getEndDate(), true, stock.getMarketValuation().getCurrency(), 0, filterFor);
				Quotations quotationsDelegate  = QuotationsFactories.getFactory()
						.getSplitFreeQuotationsInstance(stockDelegate, tStockDelegate.getStartDate(thisStartShift), tStockDelegate.getEndDate(), true, stockDelegate.getMarketValuation().getCurrency(), 0, filterFor);
				List<Date> qDelegateDates = Arrays.asList(quotationsDelegate.getDates());
				List<Date> missingKeys = Arrays.stream(quotations.getDates()).filter(qd -> !qDelegateDates.contains(qd)).collect(Collectors.toList());
				targetStock.addMissingData(missingKeys);
			} catch (NoQuotationsException e) {
				LOGGER.warn(e);
			}
			
			return output;
		} catch (WarningException e) {
			LOGGER.error(this.getReference() + ": " + e, e);
		} catch (NotEnoughDataException e) {
			LOGGER.error(this.getReference() + ": " + e, e);
		}
		
		return new DoubleMapValue();
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock, Object... addtionalParams) {
	}

}