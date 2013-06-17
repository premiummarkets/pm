package com.finance.pms.portfolio.gui.charts;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;

public class BarSettings {
	
	private static MyLogger LOGGER = MyLogger.getLogger(BarSettings.class);
	
	public Double alphaDividend;
	public int maxFill;
	public Boolean isZerobased;
	public Boolean isGradiant;

	
	public BarSettings() {
		super();
		this.alphaDividend = MainPMScmd.getPrefs().getDouble("chart.alphaDividend", 2);
		this.maxFill = MainPMScmd.getPrefs().getInt("chart.maxFill", 12);
		this.isZerobased  = MainPMScmd.getPrefs().getBoolean("chart.isZeroBased", false);
		this.isGradiant = MainPMScmd.getPrefs().getBoolean("chart.isGradient", false);
	}
	
	public Double getAlphaDividend() {
		return alphaDividend;
	}
	public void setAlphaDividend(Double alphaDividend) {
		try {
			MainPMScmd.getPrefs().putDouble("chart.alphaDividend", alphaDividend);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
		this.alphaDividend = alphaDividend;
	}
	public int getMaxFill() {
		return maxFill;
	}
	public void setMaxFill(int maxFill) {
		try {
			MainPMScmd.getPrefs().putDouble("chart.maxFill", maxFill);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		this.maxFill = maxFill;
	}
	public Boolean getIsZerobased() {
		return isZerobased;
	}
	public void setIsZerobased(Boolean isZerobased) {
		try {
			MainPMScmd.getPrefs().putBoolean("chart.isZerobased", isZerobased);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		this.isZerobased = isZerobased;
	}
	public Boolean getIsGradiant() {
		return isGradiant;
	}
	public void setIsGradient(Boolean isGradiant) {
		try {
			MainPMScmd.getPrefs().putBoolean("chart.isGradient", isGradiant);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.isGradiant = isGradiant;
	}

	
}