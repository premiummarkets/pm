package com.finance.pms.events.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.ParameterizedEventKey;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.operations.conditional.EventMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.talib.dataresults.StandardEventValue;

public class ProfitDrivenOperation extends EventMapOperation {
	private static MyLogger LOGGER = MyLogger.getLogger(ProfitDrivenOperation.class);
	
	private static final int FIRST_INPUT_IDX = 4;
	
	public ProfitDrivenOperation(String reference, String description, Operation ... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public ProfitDrivenOperation() {
		this("gx_profit", "Operation filtering false positive based on the realised profit.",
						new NumberOperation("number","minIncreaseForSell","Min increase (or max decrease if negative) for the sell event to be valid", new NumberValue(Double.NaN)),
						new NumberOperation("number","minDecreaseForBuy","Min decrease (or max increase if positive) for the buy event to be valid", new NumberValue(Double.NaN)),
						new NumberOperation("number","realiseGain","Max profit % above which the sell is forced", new NumberValue(Double.NaN)),
						new NumberOperation("number","stopLoss","Min negative profit (Maximum loss) % below which the sell is forced", new NumberValue(Double.NaN)),
						new EventMapOperation("Event Series to analyse (an Indicator)"));
	}
	
	@Override
	public EventMapValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Double minIncreaseForSell = ((NumberValue) inputs.get(0)).getValue(targetStock).doubleValue();
		Double minDecreaseForBuy = ((NumberValue) inputs.get(1)).getValue(targetStock).doubleValue();
		Double realiseGain = ((NumberValue) inputs.get(2)).getValue(targetStock).doubleValue();
		Double stopLoss = ((NumberValue) inputs.get(3)).getValue(targetStock).doubleValue();
		SortedMap<EventKey, EventValue> bsOriginalEvents = ((EventMapValue) inputs.get(FIRST_INPUT_IDX)).getEventMap();
		
		try {
			
			Date startDate = targetStock.getStartDate(0);
			Stock stock = targetStock.getStock();
			Quotations quotations  = QuotationsFactories.getFactory()
					.getSplitFreeQuotationsInstance(stock, targetStock.getStartDate(thisStartShift), targetStock.getEndDate(), true, stock.getMarketValuation().getCurrency(), 0, ValidityFilter.CLOSE);
			SortedMap<Date, Double> qMap = QuotationsFactories.getFactory().buildExactSMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size()-1);
			
//			OTFTuningFinalizer tuningFinalizer = (OTFTuningFinalizer) SpringContext.getSingleton().getBean("tuningFinalizer");
//			Map<Date, EventKey> bsOrgEvtsKeysDates = bsOriginalEvents.keySet().stream().collect(Collectors.toMap(ek -> ek.getDate(), ek -> ek));
//			//Iterate on this until no change ...
//			TuningResDTO tuningRes = tuningFinalizer.buildTuningRes(stock, startDate, endDate, exactMapFromQuotations, bsOriginalEvents.values());
//			SortedMap<EventKey, EventValue> validEvents = tuningRes.getPeriods().stream()
//				 //filter: bullish (ending bearish period) event occurs || bearish (ending bullish period) event occurs and roc > minSellprofit )
//				.filter(p -> p.getTrend().equals(EventType.BEARISH.name()) || (p.getTrend().equals(EventType.BULLISH.name()) && p.getPriceRateOfChange() >= minSellProfit))
//				.flatMap(vp -> Stream.of(bsOrgEvtsKeysDates.get(vp.getTo())))
//				.collect(Collectors.toMap(ek -> ek, ek -> bsOriginalEvents.get(ek), (a,b) -> a, TreeMap::new));
			
		
			SortedMap<EventKey, EventValue> validEvents = new TreeMap<>();
			
			//minIncreaseForSell & minDecreaseForBuy
			{
				Map<Date, EventKey> bsOriginalEvtsKeysDates = bsOriginalEvents.keySet().stream().collect(Collectors.toMap(ek -> ek.getDate(), ek -> ek));
				EventType periodTrend = EventType.NONE;
				Double bullStartPrice = qMap.get(startDate);
				Double bearStartPrice = qMap.get(startDate);
				String evtLstName = null;
				EventInfo evtInfo = null;
				Boolean isBullishNextDipOpen = false;
				for(Date currentDate:qMap.keySet()) {
					if (bsOriginalEvtsKeysDates.containsKey(currentDate)) {
						EventKey eventKey = bsOriginalEvtsKeysDates.get(currentDate);
						EventValue eventValue = bsOriginalEvents.get(eventKey);
						Double closeSplitedBeforeOrAtEventDate = qMap.get(currentDate);
						evtLstName = eventValue.getEventListName();
						evtInfo = eventValue.getEventDef();
						if (eventValue.getEventType().equals(EventType.BULLISH)) {
							if (periodTrend.equals(EventType.NONE)) {//First period will be bull
								periodTrend = EventType.BULLISH;
								isBullishNextDipOpen = false;
								bullStartPrice = closeSplitedBeforeOrAtEventDate;
								validEvents.put(eventKey, eventValue);
							}
							else if (periodTrend.equals(EventType.BEARISH)) { //A Bear period is open and the event is bull. We close the period.
								if (minDecreaseForBuy.isNaN()) {
									periodTrend = EventType.BULLISH; //Start new period.
									isBullishNextDipOpen = false;
									bullStartPrice = closeSplitedBeforeOrAtEventDate;
									validEvents.put(eventKey, eventValue);
								} else {//on minDecreaseForBuy condition
									isBullishNextDipOpen = true;
									Double currentPrice = closeSplitedBeforeOrAtEventDate;
									Double priceRateOfChange = (currentPrice - bearStartPrice)/bearStartPrice;
									if (priceRateOfChange <= minDecreaseForBuy) {
										periodTrend = EventType.BULLISH; //Start new period.
										isBullishNextDipOpen = false;
										bullStartPrice = closeSplitedBeforeOrAtEventDate;
										validEvents.put(eventKey, eventValue);
									}
								}
	
							} 
							else if (periodTrend.equals(EventType.BULLISH)) {//A bull period is open and the event is bull.
								validEvents.put(eventKey, eventValue);
							}
						}
						else if (eventValue.getEventType().equals(EventType.BEARISH)) {
							if (periodTrend.equals(EventType.NONE)) {//First period  will be bear
								periodTrend = EventType.BEARISH;
								bearStartPrice = closeSplitedBeforeOrAtEventDate;
								validEvents.put(eventKey, eventValue);
							}
							else if (periodTrend.equals(EventType.BULLISH)) { //A Bull period is open and the event is bear. We close the period.
								if (minIncreaseForSell.isNaN()) {
									periodTrend = EventType.BEARISH; //Start new period.
									bearStartPrice = closeSplitedBeforeOrAtEventDate;
									validEvents.put(eventKey, eventValue);
								} else {//on minSellProfit condition
									Double currentPrice = closeSplitedBeforeOrAtEventDate;
									Double priceRateOfChange = (currentPrice - bullStartPrice)/bullStartPrice;
									if (priceRateOfChange >= minIncreaseForSell) {
										periodTrend = EventType.BEARISH; //Start new period.
										bearStartPrice = closeSplitedBeforeOrAtEventDate;
										validEvents.put(eventKey, eventValue);
									}
								}
							} else if (periodTrend.equals(EventType.BEARISH)) {//A bear period is open and the event is bear.
								validEvents.put(eventKey, eventValue);
							}
						}
					}
					if (!minDecreaseForBuy.isNaN() && isBullishNextDipOpen &&
							!validEvents.lastKey().getEventType().equals(EventType.BULLISH)) {//Looking for the closest dip
						Double currentPrice = qMap.get(currentDate);
						Double priceRateOfChange = (currentPrice - bearStartPrice)/bearStartPrice;
						if (priceRateOfChange <= minDecreaseForBuy) {
							periodTrend = EventType.BULLISH;
							isBullishNextDipOpen = false;
							bullStartPrice = currentPrice;
							ParameterizedEventKey nextDipBuyKey = new ParameterizedEventKey(currentDate, evtInfo, EventType.BULLISH);
							validEvents.put(nextDipBuyKey, new StandardEventValue(nextDipBuyKey, "next dip buy", evtLstName));
						}
					}
				}
			}
			
			//Stop Loss & Cash Gain
			if (!stopLoss.isNaN() || !realiseGain.isNaN())
			{
				Map<Date, EventKey> bsValidEvtsKeysDates = validEvents.keySet().stream().collect(Collectors.toMap(ek -> ek.getDate(), ek -> ek));
				Double bullStartPrice = Double.NaN;
				String evtLstName = null;
				EventInfo evtInfo = null;
				for(Date currentDate:qMap.keySet()) {
					if (bsValidEvtsKeysDates.containsKey(currentDate)) {
						EventValue eventValue = validEvents.get(bsValidEvtsKeysDates.get(currentDate));
						evtLstName = eventValue.getEventListName();
						evtInfo = eventValue.getEventDef();
						if (eventValue.getEventType().equals(EventType.BULLISH)) {
							bullStartPrice = qMap.get(MapUtils.subMapInclusive(qMap, qMap.firstKey(), eventValue.getDate()).lastKey());
						} else if (eventValue.getEventType().equals(EventType.BEARISH)) {
							bullStartPrice = Double.NaN;
						}
					}
					if (!bullStartPrice.isNaN()) {
						Double bearStartPice = qMap.get(currentDate);
						Double priceRateOfChange = (bearStartPice - bullStartPrice)/bullStartPrice;
						if ((!stopLoss.isNaN() && priceRateOfChange <= stopLoss)) {
							ParameterizedEventKey stopLossBearKey = new ParameterizedEventKey(currentDate, evtInfo, EventType.BEARISH);
							validEvents.put(stopLossBearKey, new StandardEventValue(stopLossBearKey, "stop loss", evtLstName));
							bullStartPrice = Double.NaN;
						}
						if ((!realiseGain.isNaN() && priceRateOfChange >= realiseGain)) {
							ParameterizedEventKey stopLossBearKey = new ParameterizedEventKey(currentDate, evtInfo, EventType.BEARISH);
							validEvents.put(stopLossBearKey, new StandardEventValue(stopLossBearKey, "realise gain", evtLstName));
							bullStartPrice = Double.NaN;
						}
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
