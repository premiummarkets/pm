package com.finance.pms.events.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.ParameterizedEventKey;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.conditional.EventMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.talib.dataresults.StandardEventValue;

public class ProfitWalkerOperation extends EventMapOperation {
	private static MyLogger LOGGER = MyLogger.getLogger(ProfitWalkerOperation.class);
	
	public ProfitWalkerOperation(String reference, String description, Operation ... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public ProfitWalkerOperation() {
		this("gx_pwalker", "Operation walking through close generating event on realised profit.",
						new NumberOperation("number","minDecreaseForBuy","Minimum decrease (-%) realised for the buy event to be valid", new NumberValue(Double.NaN)),
						new NumberOperation("number","realiseGain","Maximum profit % above which the sell is forced", new NumberValue(Double.NaN)),
						new NumberOperation("number","stopLoss","Maximum loss (ie Min neg profit) % below which the sell is forced", new NumberValue(Double.NaN)));
	}
	
	@Override
	public EventMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Double minDecreaseForBuy = ((NumberValue) inputs.get(0)).getValue(targetStock).doubleValue();
		Double realiseGain = ((NumberValue) inputs.get(1)).getValue(targetStock).doubleValue();
		Double stopLoss = ((NumberValue) inputs.get(2)).getValue(targetStock).doubleValue();
		
		try {
			
			Date startDate = targetStock.getStartDate(0);
			Stock stock = targetStock.getStock();
			Quotations quotations  = QuotationsFactories.getFactory()
					.getQuotationsInstance(stock, targetStock.getStartDate(thisStartShift), targetStock.getEndDate(), true, stock.getMarketValuation().getCurrency(), 0, ValidityFilter.CLOSE);
			SortedMap<Date, Double> qMap = QuotationsFactories.getFactory().buildExactSMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size()-1);			
		
			SortedMap<EventKey, EventValue> validEvents = new TreeMap<>();
			
			Double bullStartPrice = qMap.get(startDate);
			Double bearStartPrice = qMap.get(startDate);
			String evtLstName =  targetStock.getAnalysisName();
			EventInfo evtInfo = EventDefinition.valueOfEventInfo(targetStock.getEventInfoOpsCompoOperation().getReference());
			for (Date currentDate:qMap.keySet()) {
				if (bullStartPrice == null || bearStartPrice == null) continue;
				Double currentPrice = qMap.get(currentDate);
				if (validEvents.isEmpty() || !validEvents.lastKey().getEventType().equals(EventType.BULLISH)) { //BEARISH period
					Double priceRateOfChange = (currentPrice - bearStartPrice)/bearStartPrice;
					if (!minDecreaseForBuy.isNaN() && priceRateOfChange <= minDecreaseForBuy) {
						bullStartPrice = currentPrice;
						ParameterizedEventKey nextDipBuyKey = new ParameterizedEventKey(currentDate, evtInfo, EventType.BULLISH);
						validEvents.put(nextDipBuyKey, new StandardEventValue(nextDipBuyKey, "next dip buy", evtLstName));
					}
				}
				if (validEvents.isEmpty() || !validEvents.lastKey().getEventType().equals(EventType.BEARISH)) { //BULLISH period
					Double priceRateOfChange = (currentPrice - bullStartPrice)/bullStartPrice;
					if ((!realiseGain.isNaN() && priceRateOfChange >= realiseGain)) {
						bearStartPrice = currentPrice;
						ParameterizedEventKey realisedGainBearKey = new ParameterizedEventKey(currentDate, evtInfo, EventType.BEARISH);
						validEvents.put(realisedGainBearKey, new StandardEventValue(realisedGainBearKey, "realise gain", evtLstName));
					}
					if ((!stopLoss.isNaN() && priceRateOfChange <= stopLoss)) {
						bearStartPrice = currentPrice;
						ParameterizedEventKey stopLossBearKey = new ParameterizedEventKey(currentDate, evtInfo, EventType.BEARISH);
						validEvents.put(stopLossBearKey, new StandardEventValue(stopLossBearKey, "stop loss", evtLstName));
					}
				}
			}
			
			return new EventMapValue(validEvents, false);
			
		} catch (NotEnoughDataException e) {
			LOGGER.warn(this.getReference() + ": " + e, true);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}

		return new EventMapValue();
	}

	
}
