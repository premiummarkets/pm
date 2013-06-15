package com.finance.pms.portfolio.gui.charts;

public class BarSettings {
	public Double alphaDividend;
	public int maxFill;
	public Boolean isZerobased;
	public Boolean isGradiant;

	
	public BarSettings(Double alphaDividend, int maxFill, Boolean isZerobased, Boolean isGradiant) {
		super();
		this.alphaDividend = alphaDividend;
		this.maxFill = maxFill;
		this.isZerobased = isZerobased;
		this.isGradiant = isGradiant;
	}
	
	public Double getAlphaDividend() {
		return alphaDividend;
	}
	public void setAlphaDividend(Double alphaDividend) {
		this.alphaDividend = alphaDividend;
	}
	public int getMaxFill() {
		return maxFill;
	}
	public void setMaxFill(int maxFill) {
		this.maxFill = maxFill;
	}
	public Boolean getIsZerobased() {
		return isZerobased;
	}
	public void setIsZerobased(Boolean isZerobased) {
		this.isZerobased = isZerobased;
	}
	public Boolean getIsGradiant() {
		return isGradiant;
	}
	public void setIsGradiant(Boolean isGradiant) {
		this.isGradiant = isGradiant;
	}

	
}