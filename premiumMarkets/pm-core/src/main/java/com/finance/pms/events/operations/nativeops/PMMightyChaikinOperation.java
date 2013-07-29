package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.calculation.ChaikinOscillatorDivergence_old;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.talib.indicators.TalibException;

@XmlRootElement
public class PMMightyChaikinOperation extends PMDataFreeOperation {

	protected static MyLogger LOGGER = MyLogger.getLogger(PMMightyChaikinOperation.class);
	
	public PMMightyChaikinOperation() {
		super("chaikin_", "Chaikin indicator house made", 
				new NumberOperation("number","Fast Period","Not implemented - Chaikin fast period", new NumberValue(3.0)),
				new NumberOperation("number","Slow Period","Not implemented - Chaikin slow period", new NumberValue(10.0)));
	}
	
	public PMMightyChaikinOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		//Param check
		Integer fastPeriod = ((NumberValue)inputs.get(0)).getValue(targetStock).intValue();
		Integer slowPeriod = ((NumberValue)inputs.get(1)).getValue(targetStock).intValue();

		DoubleMapValue ret = new DoubleMapValue();
		try {
			ChaikinOscillatorDivergence_old mChaikin = new ChaikinOscillatorDivergence_old(targetStock.getStock(), targetStock.getStartDate(), targetStock.getEndDate(), fastPeriod, slowPeriod);
			
			SortedMap<EventKey, EventValue> eventsFor = mChaikin.calculateEventsFor("inMem"+this.getClass().getSimpleName()+"Operation ");
			
			DoubleMapValue buySellEvents = new DoubleMapValue();
			for (EventKey eventKey : eventsFor.keySet()) {
				if (eventKey.getEventType().equals(EventType.BULLISH)) {
					buySellEvents.getValue(targetStock).put(eventKey.getDate(),-100.0);
				} 
				else if (eventKey.getEventType().equals(EventType.BEARISH)) {
					buySellEvents.getValue(targetStock).put(eventKey.getDate(),+100.0);
				} 
				else {
					buySellEvents.getValue(targetStock).put(eventKey.getDate(),0.0);
				}
			}
			return buySellEvents;
			
		} catch (NoQuotationsException e) {
			LOGGER.warn(e);
		} catch (TalibException e) {
			LOGGER.warn(e);
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return ret;
	}

}
