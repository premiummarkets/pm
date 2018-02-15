/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.scoring.chartUtils;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.finance.pms.MainPMScmd;
import com.finance.pms.events.calculation.EventDefDescriptor;
import com.finance.pms.events.scoring.dto.TuningResDTO;

public class DataSetBarDescr implements Comparable<DataSetBarDescr> {
	
	private Integer id;
	private String serieName;
	private Color serieColor;
	private float serieStrokeSize;
	private String stockDescr;
	private TuningResDTO tuningRes;
	
	private boolean labeled;
	
	private String eventDisplayeDef;
	private EventDefDescriptor eventDefDescriptor;
	private double base;
	
	//Ui bar chart
	public DataSetBarDescr(Integer id, String serieName, String eventDisplayeDef, EventDefDescriptor eventDefDescriptor, TuningResDTO tuningRes, String stockDescr, double base, Color serieColor, float serieStrokeSize){
		super();
		this.id = id;
		this.serieName = serieName;
		this.serieColor = serieColor;
		this.serieStrokeSize = serieStrokeSize;
		this.stockDescr = stockDescr;
		this.tuningRes = tuningRes;
		this.eventDisplayeDef = eventDisplayeDef;
		this.eventDefDescriptor = eventDefDescriptor;
		this.base = base;
		
	}

	//PmGWT out chart
	public DataSetBarDescr(int id, String serieName, Color serieColor, float serieStrokeSize) {
		super();
		this.id = id;
		this.serieName = serieName;
		this.serieColor = serieColor;
		this.serieStrokeSize = serieStrokeSize;
		this.stockDescr = serieName;
		this.base = 0;
		
	}
	
	//PmGWT out chart
	public DataSetBarDescr(int id, String serieName, Color serieColor) {
		super();
		this.id = id;
		this.serieName = serieName;
		this.serieColor = serieColor;
		this.serieStrokeSize = 1f;
		this.stockDescr = serieName;
		this.base = 0;
		
	}

	@Override
	public int compareTo(DataSetBarDescr o) {
		return id.compareTo(o.id);
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataSetBarDescr other = (DataSetBarDescr) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getSerieName() {
		return serieName;
	}

	public Color getSerieColor() {
		return serieColor;
	}

	public float getSerieStrokeSize() {
		return serieStrokeSize;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "DataSetDescr [id=" + id + ", serieName=" + serieName + ", serieColor=" + serieColor + ","+serieColor.getAlpha()+", serieSize=" + serieStrokeSize + "]";
	}

	public String getStockDescr() {
		return stockDescr;
	}

	public void setLabeled(boolean b) {
		this.labeled = b;
		
	}

	public boolean isLabeled() {
		return labeled;
	}

	public String getTuningResStr() {
		String tuningResLabel = "";
		if (tuningRes != null) {
			NumberFormat percentInstance = new DecimalFormat("#0.00 %");
			String stopLossString = (!MainPMScmd.getMyPrefs().get("indicator.stoplossratio","0").equals("0"))?", Profit (using stop losses) : "+percentInstance.format(tuningRes.getStopLossProfit()):"";
			tuningResLabel = "Profit (compound) : " + percentInstance.format(tuningRes.getFollowProfit()) + stopLossString + " V. Price change : " + percentInstance.format(tuningRes.getStockPriceChange());
		}
		return tuningResLabel;
	}
	
	public Double getFollowProfit() {
		if (tuningRes == null) return Double.NaN;
		return tuningRes.getFollowProfit();
	}
	
	public Double getStopLossProfit() {
		if (tuningRes == null) return Double.NaN;
		return tuningRes.getStopLossProfit();
	}
	
	public Double getStockPriceChange() {
		if (tuningRes == null) return Double.NaN;
		return tuningRes.getStockPriceChange();
	}

	public String getEventDisplayeDef() {
		return eventDisplayeDef;
	}

	public EventDefDescriptor getEventDefDescriptor() {
		return eventDefDescriptor;
	}

	public double getBase() {
		return base;
	}

}
