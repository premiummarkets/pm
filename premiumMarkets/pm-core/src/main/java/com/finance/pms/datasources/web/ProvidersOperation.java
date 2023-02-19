package com.finance.pms.datasources.web;

import java.util.Date;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.apache.http.HttpException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.events.AnalysisClient;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.conditional.EventInfoOpsCompoOperation;
import com.finance.pms.events.operations.nativeops.DoubleArrayMapValue;
import com.finance.pms.events.operations.parameterized.ParameterizedOperationBuilder;

@Deprecated

public class ProvidersOperation extends Providers implements QuotationProvider {
	
	private static final String QUOTE_UPDATE = "QuoteUpdate";

	protected static MyLogger LOGGER = MyLogger.getLogger(ProvidersOperation.class);
	
	private ParameterizedOperationBuilder parameterizedOperationBuilder;
	
	/**
	 * @deprecated ?? What is this supposed to used for?
	 */
	protected ProvidersOperation(String pathToProps, ParameterizedOperationBuilder parameterizedOperationBuilder) {
		super();
		this.parameterizedOperationBuilder =  parameterizedOperationBuilder;
	}
	
	/**
	 * The operation must be independent of the target stock: using AnalysisClient.ANY_STOCK
	 */
	@Override
	public void getQuotes(Stock stock, Date start, Date end) throws Exception {
		
		String operationIdHint = stock.getSymbol();
		
		EventInfoOpsCompoOperation eventInfo = new EventInfoOpsCompoOperation(QUOTE_UPDATE, QUOTE_UPDATE);
		Operation operation = parameterizedOperationBuilder.getCurrentOperations().get(operationIdHint);
		if (operation == null) {
			throw new OperationNotSupportedException("There is no operation defined with name: " + operationIdHint);
		}
		TargetStockInfo targetStock = new TargetStockInfo(QUOTE_UPDATE, eventInfo, AnalysisClient.ANY_STOCK, start, end); 
		Value<?> output = operation.run(targetStock, eventInfo.getReference(), 0);
		
		if (output == null || !(output instanceof DoubleArrayMapValue)) {
			throw new OperationNotSupportedException("Ouput value type not supported." + operationIdHint + ". " + output);
		}
		
	}

	@Override
	public MyUrl resolveUrlFor(Stock stock, Date start, Date end) throws Exception {
		return null;
	}

	@Override
	public List<Validatable> readPage(Stock stock, MyUrl url, Date startDate) throws HttpException {
		return null;
	}

	@Override
	public String getStockRefName(Stock stock) {
		return stock.getSymbol();
	}

	@Override
	public void retrieveAndCompleteStockInfo(Stock stock, StockList stockList) {
		//Nothing
	}

}
