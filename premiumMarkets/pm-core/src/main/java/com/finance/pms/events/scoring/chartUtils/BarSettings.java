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

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;

public class BarSettings {
	
	private static MyLogger LOGGER = MyLogger.getLogger(BarSettings.class);
	
	private Double alphaDividend;
	private int maxFill;
	private Boolean isZeroBased;
	private Boolean isGradient;
	private Boolean sideBySide;
	private Boolean isReachTop;
	private Boolean isToQuotations;
	private Boolean autoSetTimeLine;

	
	public BarSettings() {
		super();
		this.alphaDividend = Double.valueOf(MainPMScmd.getMyPrefs().get("chart.alphaDividend", "5.0"));
		this.maxFill = Integer.valueOf(MainPMScmd.getMyPrefs().get("chart.maxFill", "1"));
		this.isZeroBased  = Boolean.valueOf(MainPMScmd.getMyPrefs().get("chart.isZeroBased", "false"));
		this.isReachTop =  Boolean.valueOf(MainPMScmd.getMyPrefs().get("chart.isReachTop", "false"));
		this.isGradient = Boolean.valueOf(MainPMScmd.getMyPrefs().get("chart.isGradient", "false"));
		this.sideBySide  = Boolean.valueOf(MainPMScmd.getMyPrefs().get("chart.isSideBySide", "false")); //TODO?? => calculate some kind of density
		this.autoSetTimeLine  = Boolean.valueOf(MainPMScmd.getMyPrefs().get("chart.autoSetTimeLine", "false"));
		this.isToQuotations  = Boolean.valueOf(MainPMScmd.getMyPrefs().get("chart.toQuotations", "false"));
	}
	
	public Double getAlphaDividend() {
		return alphaDividend;
	}
	public void setAlphaDividend(Double alphaDividend) {
		try {
			MainPMScmd.getMyPrefs().putDouble("chart.alphaDividend", alphaDividend);
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
			MainPMScmd.getMyPrefs().putInt("chart.maxFill", maxFill);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		this.maxFill = maxFill;
	}
	public Boolean getIsZeroBased() {
		return isZeroBased;
	}
	public void setIsZeroBased(Boolean isZerobased) {
		try {
			MainPMScmd.getMyPrefs().putBoolean("chart.isZeroBased", isZerobased);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		this.isZeroBased = isZerobased;
	}
	public Boolean getIsGradient() {
		return isGradient;
	}
	public void setIsGradient(Boolean isGradient) {
		try {
			MainPMScmd.getMyPrefs().putBoolean("chart.isGradient", isGradient);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.isGradient = isGradient;
	}

	public Boolean getSideBySide() {
		return sideBySide;
	}

	public void setSideBySide(Boolean isSideBySide) {
		try {
			MainPMScmd.getMyPrefs().putBoolean("chart.isSideBySide", isSideBySide);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.sideBySide = isSideBySide;
	}

	public Boolean getIsReachTop() {
		return isReachTop;
	}

	public void setIsReachTop(Boolean isReachTop) {
		try {
			MainPMScmd.getMyPrefs().putBoolean("chart.isReachTop", isReachTop);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.isReachTop = isReachTop;
	}

	public Boolean getAutoSetTimeLine() {
		return autoSetTimeLine;
	}

	public void setAutoSetTimeLine(Boolean autoSetTimeLine) {
		try {
			MainPMScmd.getMyPrefs().putBoolean("chart.autoSetTimeLine", autoSetTimeLine);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.autoSetTimeLine = autoSetTimeLine;
	}

	public Boolean isToQuotations() {
		return isToQuotations;
	}

	public void setIsToQuotations(Boolean toQuotations) {
		this.isToQuotations = toQuotations;
	}

}