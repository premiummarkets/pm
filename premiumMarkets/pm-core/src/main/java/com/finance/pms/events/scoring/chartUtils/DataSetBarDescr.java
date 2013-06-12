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
	float serieStrokeSize;
	String stockDescr;
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

	public double getBase() {
		return base;
	}

}
