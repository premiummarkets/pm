package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.stream.Collectors;

import org.apache.commons.math3.util.Precision;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.EventMapOperation;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.conditional.EventMapValue;
import com.finance.pms.events.operations.util.ValueManipulator;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;

public class OProfitOperation extends ArrayMapOperation {
	
	private static MyLogger LOGGER = MyLogger.getLogger(OProfitOperation.class);
	
	private static final double INITIAL_CASH = 10000.0;
	private static final int NUMBER_OF_STOCK_PER_TRANS = 1; //Why times 100 ??
	private static final double FEE = 0.0; // Not sure what this is??
	
	public OProfitOperation(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}
	
	public OProfitOperation(String reference, String description, Operation... operands) {
		this(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public OProfitOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	public OProfitOperation() {
		this("oProfit", "Calculate the profits (the Omar way) of series of Events from Indicators", new EventMapOperation("Event Series to analyse profit from (an Indicator)"));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	@Override
	public DoubleArrayMapValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		List<SortedMap<EventKey, EventValue>> buySellEventSeries = inputs.subList(0, inputs.size()).stream().map(v -> ((EventMapValue) v).getEventMap()).collect(Collectors.toList());

		try {

			Stock stock = targetStock.getStock();
			Quotations quotations  = QuotationsFactories.getFactory()
					.getQuotationsInstance(stock, targetStock.getStartDate(thisStartShift), targetStock.getEndDate(), true, stock.getMarketValuation().getCurrency(), 0, ValidityFilter.CLOSE);
			SortedMap<Date, Double> qMap = QuotationsFactories.getFactory().buildExactSMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size()-1);
			List<Double> prices = new ArrayList<>(qMap.values());
			List<Date> quotationsDates = new ArrayList<>(qMap.keySet());
			
			List<NumericableMapValue> resultMaps = new ArrayList<>();
			List<String> headers = new ArrayList<>();

			for (SortedMap<EventKey, EventValue> buySellEventSerie : buySellEventSeries) {
				
				if (buySellEventSerie.isEmpty()) continue;
				
				//outs
				DoubleMapValue currentResultMap = new DoubleMapValue();
				resultMaps.add(currentResultMap);
				headers.add(buySellEventSerie.firstKey().getEventInfo().getEventDefinitionRef());
				
				//ins
				Map<Date, EventType> buySellEventTypesByDate =  buySellEventSerie.keySet().stream().collect(Collectors.toMap(ek -> ek.getDate(), ek -> ek.getEventType()));
				
				//go
				Transactions transactionsHolder = new Transactions();
				Portfolio portfolio = new Portfolio();

				double[] dailyProfit = new double[quotationsDates.size()-1];

				int numberOfDay = 0;
				int buyDayIdx = 0;
				//System.out.println("Start Capital: \\$" + money);
				//builder.append("Start Capital: \\$" + money + "\n");

				Double buyPrice, sellPrice, gainAmount = 0.0, totalGain = 0.0;
				Double potentialSellAmount = 0.0;
				Double profit = 0.0, dailyAvgProfit = 0.0;
				while(buyDayIdx < quotationsDates.size()-1){

					dailyProfit[buyDayIdx] = 0.0;

					Date buyDate = quotationsDates.get(buyDayIdx);
					boolean isBuy = buySellEventTypesByDate.containsKey(buyDate) && buySellEventTypesByDate.get(buyDate).equals(EventType.BULLISH);
					if(isBuy){

						buyPrice = prices.get(buyDayIdx);
						buyPrice = buyPrice * NUMBER_OF_STOCK_PER_TRANS;
						portfolio.stockQuantity = (portfolio.availableCash - FEE)/buyPrice; // XXX Do we keep one $ or is it one $ fee?

						for (int sellDayIdx = buyDayIdx; sellDayIdx < quotationsDates.size()-1; sellDayIdx++) {

							sellPrice = prices.get(sellDayIdx);
							sellPrice = sellPrice * 100;
							potentialSellAmount = (portfolio.stockQuantity * sellPrice) - FEE;
							
							//stop loss %10
							/*if(money*0.95>moneyTemp){
							money=moneyTemp;
							forceSell=true;
							}*/

							Date sellDate = quotationsDates.get(sellDayIdx);
							boolean isSell = buySellEventTypesByDate.containsKey(sellDate) && buySellEventTypesByDate.get(sellDate).equals(EventType.BEARISH);
							if (isSell) {
								sellPrice =  prices.get(sellDayIdx);;
								sellPrice = sellPrice * NUMBER_OF_STOCK_PER_TRANS; //Should not this be portfolio.stockQuantity instead of NUMBER_OF_STOCK_PER_TRANS??
								gainAmount = sellPrice - buyPrice; //What about the FEE??
								
								if (gainAmount > 0) {
									transactionsHolder.successTransactionCount++;
								}

								if (gainAmount >= transactionsHolder.highestGainAmount) {
									transactionsHolder.highestGainAmount = gainAmount;
									transactionsHolder.highestGainPercent = Double.valueOf(transactionsHolder.highestGainAmount)/Double.valueOf(buyPrice)*100;		
								}
								if (gainAmount <= transactionsHolder.lowestGainAmount) {
									transactionsHolder.lowestGainAmount = gainAmount;
									transactionsHolder.lowestGainPercent = Double.valueOf(transactionsHolder.lowestGainAmount)/Double.valueOf(buyPrice)*100;		
								}

								potentialSellAmount = (portfolio.stockQuantity * sellPrice) - FEE;
								portfolio.availableCash = potentialSellAmount;

								if (portfolio.availableCash > portfolio.highestCashAmount) {
									portfolio.highestCashAmount = portfolio.availableCash;
								}
								if (portfolio.availableCash < portfolio.lowestCashAmount) {
									portfolio.lowestCashAmount = portfolio.availableCash;
								}

								transactionsHolder.transactionCount++;
								//System.out.println("\\\\"+transactionCount+"."+"("+(k+1)+"-"+(j+1)+") => " + Precision.round(sellPoint,2) + "-" + Precision.round(buyPoint,2)+ "= " + Precision.round(gain,2) + " Capital: \\$" + Precision.round(money,2) );
								//						System.out.println(
								//								transactionsHolder.transactionCount + "." + 
								//								"(" + (buyDayIdx+1) + "-" + (sellDayIdx+1) + ") => " + Precision.round( (gainAmount * portfolio.stockQuantity), 2) + 
								//								" Capital: $" + Precision.round(portfolio.availableCash, 2) );
								//System.out.println(Precision.round((gain*shareNumber),2));
								//builder.append(transactionCount+"."+"("+(k+1)+"-"+(j+1)+") => " + Precision.round((gain*shareNumber),2) + " Capital: $" + Precision.round(money,2)+"\n");

								//////////////////////////////
								profit = Precision.round(Precision.round((gainAmount * portfolio.stockQuantity), 2)/(portfolio.availableCash - (gainAmount * portfolio.stockQuantity)), 4);
								numberOfDay = sellDayIdx - buyDayIdx;
								dailyAvgProfit = Precision.round((profit/numberOfDay),4);
								for(int m = buyDayIdx+1;m <= sellDayIdx; m++){
									dailyProfit[m] = dailyAvgProfit;
									//System.out.println("dailyProfit["+m+"]:"+dailyProfit[m]);
								}
								//////////////////////////////////////////
								transactionsHolder.totalPercentProfit = transactionsHolder.totalPercentProfit + (gainAmount / buyPrice);
								transactionsHolder.totalTransactionLength = transactionsHolder.totalTransactionLength + (sellDayIdx-buyDayIdx);
								totalGain = totalGain + gainAmount;
								currentResultMap.getValue(targetStock).put(sellDate, portfolio.availableCash/INITIAL_CASH-1);
								buyDayIdx = sellDayIdx;
								break;
							} else {
								currentResultMap.getValue(targetStock).put(quotationsDates.get(sellDayIdx), portfolio.availableCash/INITIAL_CASH-1);
							}
						}
					} else {
						currentResultMap.getValue(targetStock).put(quotationsDates.get(buyDayIdx), portfolio.availableCash/INITIAL_CASH-1);
					}
					buyDayIdx++;
				}
			}

			SortedMap<Date, double[]> inputListToArray = ValueManipulator.inputListToArray(targetStock, resultMaps, true, true);
			DoubleArrayMapValue result = new DoubleArrayMapValue(inputListToArray, headers, 0);
			return result;
			
		} catch (NotEnoughDataException e) {
			LOGGER.warn(this.getReference() + ": " + e, true);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return new DoubleArrayMapValue();
	}
	
	class Transactions {
		int totalTransactionLength = 0;
		Double highestGainAmount = 0.0, lowestGainAmount = 100.0, totalPercentProfit = 0.0;
		int transactionCount = 0, successTransactionCount = 0;
		Double highestGainPercent = 0.0, lowestGainPercent = 0.0;
	}
	
	class Portfolio {
		Double availableCash=INITIAL_CASH, stockQuantity=0.0, highestCashAmount=0.0, lowestCashAmount=INITIAL_CASH;
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock, Object... addtionalParams) {
	}

}
