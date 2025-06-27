package com.finance.pms.events.operations.nativeops.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.ParameterizedEventKey;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.conditional.EventMapValue;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.EventMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.talib.dataresults.StandardEventValue;

public class ProfitDrivenOperation extends EventMapOperation {
	private static MyLogger LOGGER = MyLogger.getLogger(ProfitDrivenOperation.class);
	
	private static final int FIRST_INPUT_IDX = 5;
	
	public ProfitDrivenOperation(String reference, String description, Operation ... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public ProfitDrivenOperation() {
		this("gx_profit", "Operation filtering false positive based on the realised profit.",
						new NumberOperation("number","minIncreaseForSell","Minimum increase % (or Maximum decrease if negative) for the sell event to be valid", new NumberValue(Double.NaN)),
						new NumberOperation("number","minDecreaseForBuy","Minimum decrease % (or Maximum increase if positive) for the buy event to be valid", new NumberValue(Double.NaN)),
						new NumberOperation("number","realiseGain","Profit % above (Maximum profit) which the sell is forced", new NumberValue(Double.NaN)),
						new NumberOperation("number","stopLoss","Negative profit (Maximum loss) % below which the sell is forced", new NumberValue(Double.NaN)),
						new DoubleMapOperation("data", "reference", "Quotations reference for profit calculation", null),
						new EventMapOperation("Event Series to analyse (an Indicator)"));
	}
	
	@Override
	public EventMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Double minIncreaseForSell = ((NumberValue) inputs.get(0)).getValue(targetStock).doubleValue()/100;
		Double minDecreaseForBuy = ((NumberValue) inputs.get(1)).getValue(targetStock).doubleValue()/100;
		Double realiseGain = ((NumberValue) inputs.get(2)).getValue(targetStock).doubleValue()/100;
		Double stopLoss = ((NumberValue) inputs.get(3)).getValue(targetStock).doubleValue()/100;
		SortedMap<Date, Double> qMap = ((NumericableMapValue) inputs.get(4)).getValue(targetStock);
		SortedMap<EventKey, EventValue> bsOriginalEvents = ((EventMapValue) inputs.get(FIRST_INPUT_IDX)).getEventMap();
		
		try {
			
			Date startDate = targetStock.getStartDate(0);
		
			//minIncreaseForSell & minDecreaseForBuy
			SortedMap<EventKey, EventValue> minVarValidEvents = new TreeMap<>();
			if (!minIncreaseForSell.isNaN() || !minDecreaseForBuy.isNaN())
			{
				Map<Date, EventKey> bsOriginalEvtsKeysDates = bsOriginalEvents.keySet().stream().collect(Collectors.toMap(ek -> ek.getDate(), ek -> ek));
				EventType periodTrend = EventType.NONE;
				Double bullStartPrice = qMap.get(startDate);
				Double bearStartPrice = qMap.get(startDate);
//				Boolean isBullishNextDipOpen = false;
				for(Date currentDate:qMap.keySet()) {
					if (bsOriginalEvtsKeysDates.containsKey(currentDate)) {
						EventKey eventKey = bsOriginalEvtsKeysDates.get(currentDate);
						EventValue eventValue = bsOriginalEvents.get(eventKey);
						Double closeSplitedBeforeOrAtEventDate = qMap.get(currentDate);
						if (eventValue.getEventType().equals(EventType.BULLISH)) {
							if (periodTrend.equals(EventType.NONE)) {//First period will be bull
								periodTrend = EventType.BULLISH;
//								isBullishNextDipOpen = false;
								bullStartPrice = closeSplitedBeforeOrAtEventDate;
								minVarValidEvents.put(eventKey, eventValue);
							}
							else if (periodTrend.equals(EventType.BEARISH)) { //A Bear period is open and the event is bull. We close the Bear period.
								if (minDecreaseForBuy.isNaN()) {
									periodTrend = EventType.BULLISH; //Start new period.
//									isBullishNextDipOpen = false;
									bullStartPrice = closeSplitedBeforeOrAtEventDate;
									minVarValidEvents.put(eventKey, eventValue);
								} else {//on minDecreaseForBuy condition (if: current bull event && bear period is open)
//									isBullishNextDipOpen = true;
									Double currentPrice = closeSplitedBeforeOrAtEventDate;
									Double priceRateOfChange = (currentPrice - bearStartPrice)/bearStartPrice;
									if (priceRateOfChange <= minDecreaseForBuy) {
										periodTrend = EventType.BULLISH; //Start new period.
//										isBullishNextDipOpen = false;
										bullStartPrice = closeSplitedBeforeOrAtEventDate;
										minVarValidEvents.put(eventKey, eventValue);
									}
								}
							} 
							else if (periodTrend.equals(EventType.BULLISH)) {//A bull period is open and the event is bull. (if: current bull event && bull period is open)
								if (minDecreaseForBuy.isNaN()) {
									minVarValidEvents.put(eventKey, eventValue);
								}
							}
						}
						else if (eventValue.getEventType().equals(EventType.BEARISH)) {
							if (periodTrend.equals(EventType.NONE)) {//First period  will be bear
								periodTrend = EventType.BEARISH;
								bearStartPrice = closeSplitedBeforeOrAtEventDate;
								minVarValidEvents.put(eventKey, eventValue);
							}
							else if (periodTrend.equals(EventType.BULLISH)) { //A Bull period is open and the event is bear. We close the Bull period.
								if (minIncreaseForSell.isNaN()) {
									periodTrend = EventType.BEARISH; //Start new period.
									bearStartPrice = closeSplitedBeforeOrAtEventDate;
									minVarValidEvents.put(eventKey, eventValue);
								} else {//on minSellProfit condition
									Double currentPrice = closeSplitedBeforeOrAtEventDate;
									Double priceRateOfChange = (currentPrice - bullStartPrice)/bullStartPrice;
									if (priceRateOfChange >= minIncreaseForSell) {
										periodTrend = EventType.BEARISH; //Start new period.
										bearStartPrice = closeSplitedBeforeOrAtEventDate;
										minVarValidEvents.put(eventKey, eventValue);
									}
								}
							} else if (periodTrend.equals(EventType.BEARISH)) {//A bear period is open and the event is bear.
								minVarValidEvents.put(eventKey, eventValue);
							}
						}
					}
					
//					//Looking for the closest dip (also applies if no event at current date??) in bear period.
//					if (!minDecreaseForBuy.isNaN() && isBullishNextDipOpen && !minVarValidEvents.lastKey().getEventType().equals(EventType.BULLISH)) {
//						Double currentPrice = qMap.get(currentDate);
//						Double priceRateOfChange = (currentPrice - bearStartPrice)/bearStartPrice;
//						if (priceRateOfChange <= minDecreaseForBuy) {
//							periodTrend = EventType.BULLISH;
//							isBullishNextDipOpen = false;
//							bullStartPrice = currentPrice;
//							ParameterizedEventKey nextDipBuyKey = new ParameterizedEventKey(currentDate, evtInfo, EventType.BULLISH);
//							minVarValidEvents.put(nextDipBuyKey, new StandardEventValue(nextDipBuyKey, "next dip buy", evtLstName));
//						}
//					}
					
				}
			} else {
				minVarValidEvents.putAll(bsOriginalEvents);
			}
			
			//Stop Loss & Cash Gain
			SortedMap<EventKey, EventValue> gainLossEvents = new TreeMap<>();
			if (!stopLoss.isNaN() || !realiseGain.isNaN())
			{
				Map<Date, EventKey> bsValidEvtsKeysDates = minVarValidEvents.keySet().stream().collect(Collectors.toMap(ek -> ek.getDate(), ek -> ek));
				Double bullStartPrice = Double.NaN;
				for(Date currentDate:qMap.keySet()) {
					if (bsValidEvtsKeysDates.containsKey(currentDate)) {
						EventKey originalKey = bsValidEvtsKeysDates.get(currentDate);
						EventValue eventOriginalValue = minVarValidEvents.get(originalKey);
						String evtLstName = eventOriginalValue.getEventListName();
						EventInfo evtInfo = eventOriginalValue.getEventDef();
						
						if (eventOriginalValue.getEventType().equals(EventType.BEARISH)) {//Bear period
							gainLossEvents.put(originalKey, eventOriginalValue); //Keep the bearish event as is.
							bullStartPrice = Double.NaN;
						} else if (eventOriginalValue.getEventType().equals(EventType.BULLISH)) {//Bull period
							if (bullStartPrice.isNaN()) {//Bull period started
								bullStartPrice = qMap.get(MapUtils.subMapInclusive(qMap, qMap.firstKey(), eventOriginalValue.getDate()).lastKey());
								gainLossEvents.put(originalKey, eventOriginalValue); //Keep the first bullish event as is.
							}
							//gainLossEvents.put(originalKey, eventOriginalValue); //Running bullish event need testing
						}
						
						if (!bullStartPrice.isNaN()) {//Bull period running
							if (eventOriginalValue.getEventType().equals(EventType.BEARISH)) throw new IllegalStateException("Unexpected bearish event in a bullish period: " + originalKey);
							Double bearStartPrice = qMap.get(currentDate);
							Double priceRateOfChange = (bearStartPrice - bullStartPrice)/bullStartPrice;
							if ((!stopLoss.isNaN() && priceRateOfChange < stopLoss)) {//Force bear
								ParameterizedEventKey stopLossBearKey = new ParameterizedEventKey(currentDate, evtInfo, EventType.BEARISH);
								gainLossEvents.put(stopLossBearKey, new StandardEventValue(stopLossBearKey, "stop loss", evtLstName));
								bullStartPrice = Double.NaN;
							}
							if ((!realiseGain.isNaN() && priceRateOfChange > realiseGain)) {//Force bear
								ParameterizedEventKey stopLossBearKey = new ParameterizedEventKey(currentDate, evtInfo, EventType.BEARISH);
								gainLossEvents.put(stopLossBearKey, new StandardEventValue(stopLossBearKey, "realise gain", evtLstName));
								bullStartPrice = Double.NaN;
							}
							if (!bullStartPrice.isNaN()) {//The bull was not invalidated
								gainLossEvents.put(originalKey, eventOriginalValue); //Keep the bullish event as is.
							}
						}
						
					}
				}
			} else {
				gainLossEvents.putAll(minVarValidEvents);
			}
			
			return new EventMapValue(gainLossEvents, false);
//			
//		} catch (NotEnoughDataException e) {
//			LOGGER.warn(this.getReference() + ": " + e, true);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}

		return new EventMapValue();
	}

	
}
