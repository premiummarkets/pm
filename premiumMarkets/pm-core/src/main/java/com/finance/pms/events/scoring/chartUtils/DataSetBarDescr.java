package com.finance.pms.events.scoring.chartUtils;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.finance.pms.events.calculation.EventDefDescriptor;
import com.finance.pms.events.scoring.dto.TuningResDTO;

public class DataSetBarDescr implements Comparable<DataSetBarDescr> {
	
	Integer id;
	String serieName;
	Color serieColor;
	float serieSize;
	String stockDescr;
	private TuningResDTO tuningRes;
	
	private boolean labeled;
	
	private String eventDisplayeDef;
	private EventDefDescriptor eventDefDescriptor;
	
	public DataSetBarDescr(Integer id, String serieName, String eventDisplayeDef, EventDefDescriptor eventDefDescriptor, Color serieColor, float serieSize, String stockDescr, TuningResDTO tuningRes){
		super();
		this.id = id;
		this.serieName = serieName;
		this.serieColor = serieColor;
		this.serieSize = serieSize;
		this.stockDescr = stockDescr;
		this.tuningRes = tuningRes;
		this.eventDisplayeDef = eventDisplayeDef;
		this.eventDefDescriptor = eventDefDescriptor;
		
	}

	//PmGWT out chart
	public DataSetBarDescr(int id, String serieName, Color serieColor, float serieSize) {
		super();
		this.id = id;
		this.serieName = serieName;
		this.serieColor = serieColor;
		this.serieSize = serieSize;
		this.stockDescr = serieName;
		
	}
	
	public DataSetBarDescr(int id, String serieName, Color serieColor) {
		super();
		this.id = id;
		this.serieName = serieName;
		this.serieColor = serieColor;
		this.serieSize = 1f;
		this.stockDescr = serieName;
		
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

	public float getSerieSize() {
		return serieSize;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "DataSetDescr [id=" + id + ", serieName=" + serieName + ", serieColor=" + serieColor + ","+serieColor.getAlpha()+", serieSize=" + serieSize + "]";
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
			tuningResLabel = "Profit : "+ percentInstance.format(tuningRes.getProfit()) + " V. Price change : "+percentInstance.format(tuningRes.getStockPriceChange());
		}
		return tuningResLabel;
	}
	
	public Double getProfit() {
		if (tuningRes == null) return Double.NaN;
		return tuningRes.getProfit();
	}

	public String getEventDisplayeDef() {
		return eventDisplayeDef;
	}

	public EventDefDescriptor getEventDefDescriptor() {
		return eventDefDescriptor;
	}

}
