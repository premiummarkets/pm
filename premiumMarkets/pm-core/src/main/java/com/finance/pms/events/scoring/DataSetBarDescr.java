package com.finance.pms.events.scoring;

import java.awt.Color;

public class DataSetBarDescr implements Comparable<DataSetBarDescr> {
	
	Integer id;
	String serieName;
	Color serieColor;
	float serieSize;
	String other;
	private boolean labeled;
	
	public DataSetBarDescr(Integer id, String serieName, Color serieColor, float serieSize, String other) {
		super();
		this.id = id;
		this.serieName = serieName;
		this.serieColor = serieColor;
		this.serieSize = serieSize;
		this.other = other;
	}

	public DataSetBarDescr(int id, String serieName, Color serieColor, float serieSize) {
		super();
		this.id = id;
		this.serieName = serieName;
		this.serieColor = serieColor;
		this.serieSize = serieSize;
		this.other = serieName;
		
	}
	
	public DataSetBarDescr(int id, String serieName, Color serieColor) {
		super();
		this.id = id;
		this.serieName = serieName;
		this.serieColor = serieColor;
		this.serieSize = 1f;
		this.other = serieName;
		
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

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public void setLabeled(boolean b) {
		this.labeled = b;
		
	}

	public boolean isLabeled() {
		return labeled;
	}

}
