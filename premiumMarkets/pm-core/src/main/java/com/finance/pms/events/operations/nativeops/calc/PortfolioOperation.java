package com.finance.pms.events.operations.nativeops.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.files.TransactionElement;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.conditional.MultiValuesOutput;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.portfolio.AbstractSharesList;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.PortfolioShare;

public class PortfolioOperation extends DoubleMapOperation implements MultiValuesOutput {

	private static MyLogger LOGGER = MyLogger.getLogger(PortfolioOperation.class);
	
	public PortfolioOperation(String reference, String description, ArrayList<? extends Operation> operands) {
		super(reference, description, operands);
	}
	
	public PortfolioOperation(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public PortfolioOperation() {
		this("portfolioStats", "Portfolio shares perfs over time.", new DoubleMapOperation("Data as time series reference"));
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisStartShift, int thisAndOperandsStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		NumericableMapValue numericableMapValue = (NumericableMapValue) inputs.get(0);
		
		String currentPortfolio = TargetStockInfo.getGlobalVar("currentPortfolio");
		
		if (currentPortfolio != null) {

			AbstractSharesList portfolio = PortfolioMgr.getInstance().getPortfolio(currentPortfolio);
			Stock stock = targetStock.getStock();
			Currency currency = stock.getMarketValuation().getCurrency();
			
			try {
				
				PortfolioShare shareForStock = portfolio.getShareForStock(stock);
				Date dateAtZero = DateFactory.dateAtZero();
				SortedSet<TransactionElement> transactions = shareForStock.getTransactions(false);
				if (transactions.size() > 0) {
					Date firstTransactionDate = transactions.first().getDate();
					SortedMap<Date, Double> anualised = numericableMapValue.getDateKeys().stream().collect(Collectors.toMap(k -> k, k -> {
						double anu = shareForStock.getGainAnnualisedPercent(firstTransactionDate, k, currency, false).doubleValue();
						return anu;
					}, (a, b) -> b, TreeMap::new));
					DoubleMapValue result = new DoubleMapValue(anualised);
					SortedMap<Date, Double> realised = numericableMapValue.getDateKeys().stream().collect(Collectors.toMap(k -> k, k -> {
						double rgain = shareForStock.getGainRealisedPercent(dateAtZero, k, currency, false).doubleValue();
						return rgain;
					}, (a, b) -> b, TreeMap::new));
					result.getAdditionalOutputs().put("realised", new DoubleMapValue(realised));
					result.getAdditionalOutputsTypes().put("realised", Type.MULTI);
					
					return result;
				}
				
			} catch (Exception e) {
				LOGGER.error("currentPortfolio " + currentPortfolio + ": " + e, e);
				throw e;
			}
		}
		
		throw new RuntimeException("currentPortfolio is not set");
	}

	@Override
	public int mainInputPosition() {
		return 0;
	}
	
}
