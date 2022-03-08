package com.finance.pms.events.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.conditional.EventMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.OTFTuningFinalizer;
import com.finance.pms.events.scoring.dto.TuningResDTO;

public class ProfitDrivenOperation extends EventMapOperation {
	private static MyLogger LOGGER = MyLogger.getLogger(ProfitDrivenOperation.class);
	
	public ProfitDrivenOperation(String reference, String description, Operation ... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public ProfitDrivenOperation() {
		this("gx_profit", "Operation filtering false positive based on the realised profit.",
						new NumberOperation("number","minSellProfit","Minimum profit % acquired for the sell event being valid", new NumberValue(0.0)),
						new NumberOperation("number","takeGain","Maximum profit % above which the sell is forced", new NumberValue(0.0)),
						new NumberOperation("number","stopLoss","Minimum loss % below which the sell is forced", new NumberValue(0.0)),
						new EventMapOperation("Event Series to analyse (an Indicator)"));
	}
	
	@Override
	public EventMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Double minSellProfit = ((NumberValue) inputs.get(0)).getValue(targetStock).doubleValue();
//		Double takeGain = ((NumberValue) inputs.get(1)).getValue(targetStock).doubleValue();
//		Double stopLoss = ((NumberValue) inputs.get(2)).getValue(targetStock).doubleValue();
		SortedMap<EventKey, EventValue> bsOriginalEvents = ((EventMapValue) inputs.get(3)).getEventMap();
		Map<Date, EventKey> bsOrgEvtsKeysDates = bsOriginalEvents.keySet().stream().collect(Collectors.toMap(ek -> ek.getDate(), ek -> ek));
		
		try {
			OTFTuningFinalizer tuningFinalizer = (OTFTuningFinalizer) SpringContext.getSingleton().getBean("tuningFinalizer");
			
			Date startDate = targetStock.getStartDate(0);
			Date endDate = targetStock.getEndDate();
			Stock stock = targetStock.getStock();
			Quotations quotations  = QuotationsFactories.getFactory()
					.getQuotationsInstance(stock, targetStock.getStartDate(thisStartShift), targetStock.getEndDate(), true, stock.getMarketValuation().getCurrency(), 0, ValidityFilter.CLOSE);
			SortedMap<Date, Double> exactMapFromQuotations = QuotationsFactories.getFactory().buildExactSMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size()-1);;
			TuningResDTO tuningRes = tuningFinalizer.buildTuningRes(stock, startDate, endDate, exactMapFromQuotations, bsOriginalEvents.values());
			
			SortedMap<EventKey, EventValue> validEvents = tuningRes.getPeriods().stream()
				.filter(p -> p.getTrend().equals(EventType.BEARISH.name()) || (p.getTrend().equals(EventType.BULLISH.name()) && p.getPriceRateOfChange() >= minSellProfit))
				.flatMap(vp -> Stream.of(bsOrgEvtsKeysDates.get(vp.getFrom())))
				.collect(Collectors.toMap(ek -> ek, ek -> bsOriginalEvents.get(ek), (a,b) -> a, TreeMap::new));
			
			return new EventMapValue(validEvents, false);
			
		} catch (NotEnoughDataException e) {
			LOGGER.warn(this.getReference() + ": " + e, true);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}

		return new EventMapValue();
	}

	
}
