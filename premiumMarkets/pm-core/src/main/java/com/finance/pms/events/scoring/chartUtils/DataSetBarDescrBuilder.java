package com.finance.pms.events.scoring.chartUtils;

import java.awt.Color;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventType;
import com.finance.pms.events.scoring.dto.TuningResDTO;

//It is assumed 3 possible event types per EventDef: NONE, BEARISH, BULLISH in this order
public class DataSetBarDescrBuilder {
	
	public DataSetBarDescr buildBuyDSBarDescr(Integer serieIdx, int alpha, EventInfo eventInfo, Stock selectedShare, TuningResDTO tuningResDTO) {
		return new DataSetBarDescr(
				serieIdx, EventType.BULLISH,
				eventInfo.info() + " buy", new java.awt.Color(0,255,100, alpha), 10f, 
				selectedShare.getFriendlyName(),
				eventInfo.getEventReadableDef(), eventInfo.getEventDefDescriptor(), tuningResDTO);
	}
	
	public DataSetBarDescr buildSellDSBarDescr(Integer serieIdx, int alpha, EventInfo eventInfo, Stock selectedShare, TuningResDTO tuningResDTO) {
		return new DataSetBarDescr(
				serieIdx, EventType.BEARISH, 
				eventInfo.info() + " sell", new java.awt.Color(240,72,20, alpha), 10f, 
				selectedShare.getFriendlyName(),
				eventInfo.getEventReadableDef(), eventInfo.getEventDefDescriptor(), tuningResDTO);
	}
	
	public DataSetBarDescr buildIndeterDSBarDescr(Integer serieIdx, int alpha, EventInfo eventInfo, Stock selectedShare, TuningResDTO tuningResDTO) {
		return new DataSetBarDescr(
				serieIdx, EventType.NONE, 
				eventInfo.info() + " indeterministic", new java.awt.Color(Color.GRAY.getRed(), Color.GRAY.getGreen(), Color.GRAY.getBlue(), alpha), 10f,
				selectedShare.getFriendlyName(),
				eventInfo.getEventReadableDef(), eventInfo.getEventDefDescriptor(), tuningResDTO);
	}

}
