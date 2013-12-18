package com.finance.pms.events.scoring.chartUtils;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;

public class BarSettings {
	
	private static MyLogger LOGGER = MyLogger.getLogger(BarSettings.class);
	
	private Double alphaDividend;
	private int maxFill;
	private Boolean isZeroBased;
	private Boolean isGradiant;
	private Boolean sideBySide;
	private Boolean isReachTop; 

	
	public BarSettings() {
		super();
		this.alphaDividend = MainPMScmd.getPrefs().getDouble("chart.alphaDividend", 5);
		this.maxFill = MainPMScmd.getPrefs().getInt("chart.maxFill", 12);
		this.isZeroBased  = MainPMScmd.getPrefs().getBoolean("chart.isZeroBased", false);
		this.isReachTop =  MainPMScmd.getPrefs().getBoolean("chart.isReachTop", false);
		this.isGradiant = MainPMScmd.getPrefs().getBoolean("chart.isGradient", false);
		this.sideBySide  = MainPMScmd.getPrefs().getBoolean("chart.isSideBySide", false); //TODO?? => calculate some kind of density
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
			MainPMScmd.getPrefs().putInt("chart.maxFill", maxFill);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		this.maxFill = maxFill;
	}
	public Boolean getIsZeroBased() {
		return isZeroBased;
	}
	public void setIsZerobased(Boolean isZerobased) {
		try {
			MainPMScmd.getPrefs().putBoolean("chart.isZeroBased", isZerobased);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		this.isZeroBased = isZerobased;
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

	public Boolean getSideBySide() {
		return sideBySide;
	}

	public void setSideBySide(Boolean isReachTop) {
		try {
			MainPMScmd.getPrefs().putBoolean("chart.isSideBySide", isReachTop);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.sideBySide = isReachTop;
	}

	public Boolean getIsReachTop() {
		return isReachTop;
	}

	public void setIsReachTop(Boolean isReachTop) {
		try {
			MainPMScmd.getPrefs().putBoolean("chart.isReachTop", isReachTop);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.isReachTop = isReachTop;
	}

	
}