/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
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