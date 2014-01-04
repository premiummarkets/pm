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
package com.finance.pms.admin.config;

import com.finance.pms.MainPMScmd;

public class IndicatorsConfig extends Config {
	
	private Integer smaReversalSmaPeriod = new Integer(MainPMScmd.getPrefs().get("indicators.smareversalsmaperiod", "50"));
	private Integer stdDevSmaPeriod = new Integer(MainPMScmd.getPrefs().get("indicators.stddevsmaperiod", "100"));
	private Integer varianceSmaPeriod = new Integer(MainPMScmd.getPrefs().get("indicators.variancesmaperiod", "200"));
	
	private Integer macdFastPeriod = new Integer(MainPMScmd.getPrefs().get("indicators.macd.fastperiod", "12"));
	private Integer macdSlowPeriod = new Integer(MainPMScmd.getPrefs().get("indicators.macd.slowperiod", "26"));
	private Integer macdSignal = new Integer(MainPMScmd.getPrefs().get("indicators.macd.signal", "9"));
	
	private Integer rsiTimePeriod = 14;
	private Integer rsiUpperThreshold = 70;
	private Integer rsiLowerThreshold = 30;
	
	private Integer variationPeriod = new Integer(MainPMScmd.getPrefs().get("indicators.variation.period", "20"));
	private Integer variationSpanDiff = new Integer(MainPMScmd.getPrefs().get("indicators.variation.spandiff", "10"));
	
	private Integer variancePeriod = new Integer(MainPMScmd.getPrefs().get("indicators.variance.period", "20"));
	private Integer varianceSpanDiff = new Integer(MainPMScmd.getPrefs().get("indicators.variance.spandiff", "1"));
	private Integer varianceMinValid = new Integer(MainPMScmd.getPrefs().get("indicators.variance.minvalid", "10"));

	
	
	
	public IndicatorsConfig(
			Integer macdFastPeriod, Integer macdSignal, Integer macdSlowPeriod, 
			Integer rsiLowerThreshold,	Integer rsiTimePeriod, Integer rsiUpperThreshold, 
			Integer smaPeriod) {
		super();
		this.macdFastPeriod = macdFastPeriod;
		this.macdSignal = macdSignal;
		this.macdSlowPeriod = macdSlowPeriod;
		this.rsiLowerThreshold = rsiLowerThreshold;
		this.rsiTimePeriod = rsiTimePeriod;
		this.rsiUpperThreshold = rsiUpperThreshold;
		this.smaReversalSmaPeriod = smaPeriod;
	}
	
	
	public IndicatorsConfig() {
		super();

	}
	
	

	public void setSmaReversalSmaPeriod(Integer smaPeriod) {
		this.smaReversalSmaPeriod = smaPeriod;
	}

	public void setMacdFastPeriod(Integer macdFastPeriod) {
		this.macdFastPeriod = macdFastPeriod;
	}

	public void setMacdSlowPeriod(Integer macdSlowPeriod) {
		this.macdSlowPeriod = macdSlowPeriod;
	}

	public void setMacdSignal(Integer macdSignal) {
		this.macdSignal = macdSignal;
	}

	public void setRsiTimePeriod(Integer rsiTimePeriod) {
		this.rsiTimePeriod = rsiTimePeriod;
	}

	public void setRsiUpperThreshold(Integer rsiUpperThreshold) {
		this.rsiUpperThreshold = rsiUpperThreshold;
	}

	public void setRsiLowerThreshold(Integer rsiLowerThreshold) {
		this.rsiLowerThreshold = rsiLowerThreshold;
	}

	public Integer getSmaReversalSmaPeriod() {
		return smaReversalSmaPeriod;
	}
	public Integer getMacdFastPeriod() {
		return macdFastPeriod;
	}
	public Integer getMacdSlowPeriod() {
		return macdSlowPeriod;
	}
	public Integer getMacdSignal() {
		return macdSignal;
	}
	public Integer getRsiTimePeriod() {
		return rsiTimePeriod;
	}
	public Integer getRsiUpperThreshold() {
		return rsiUpperThreshold;
	}
	public Integer getRsiLowerThreshold() {
		return rsiLowerThreshold;
	}

	@Override
	public String getName() {
		return INDICATOR_PARAMS_NAME;
	}


	public Integer getStdDevSmaPeriod() {
		return stdDevSmaPeriod;
	}


	public Integer getVarianceSmaPeriod() {
		return varianceSmaPeriod;
	}


	public Integer getVariationPeriod() {
		return variationPeriod;
	}


	public Integer getVariancePeriod() {
		return variancePeriod;
	}

	public Integer getVarianceSpanDiff() {
		return varianceSpanDiff;
	}

	public Integer getVarianceMinValid() {
		return varianceMinValid;
	}


	public Integer getVariationSpanDiff() {
		return variationSpanDiff;
	}


	public void setStdDevSmaPeriod(Integer stdDevSmaPeriod) {
		this.stdDevSmaPeriod = stdDevSmaPeriod;
	}


	public void setVarianceSmaPeriod(Integer varianceSmaPeriod) {
		this.varianceSmaPeriod = varianceSmaPeriod;
	}


	public void setVariationPeriod(Integer variationPeriod) {
		this.variationPeriod = variationPeriod;
	}


	public void setVariationSpanDiff(Integer variationSpanDiff) {
		this.variationSpanDiff = variationSpanDiff;
	}


	public void setVariancePeriod(Integer variancePeriod) {
		this.variancePeriod = variancePeriod;
	}


	public void setVarianceSpanDiff(Integer varianceSpanDiff) {
		this.varianceSpanDiff = varianceSpanDiff;
	}


	public void setVarianceMinValid(Integer varianceMinValid) {
		this.varianceMinValid = varianceMinValid;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((macdFastPeriod == null) ? 0 : macdFastPeriod.hashCode());
		result = prime * result + ((macdSignal == null) ? 0 : macdSignal.hashCode());
		result = prime * result + ((macdSlowPeriod == null) ? 0 : macdSlowPeriod.hashCode());
		result = prime * result + ((rsiLowerThreshold == null) ? 0 : rsiLowerThreshold.hashCode());
		result = prime * result + ((rsiTimePeriod == null) ? 0 : rsiTimePeriod.hashCode());
		result = prime * result + ((rsiUpperThreshold == null) ? 0 : rsiUpperThreshold.hashCode());
		result = prime * result + ((smaReversalSmaPeriod == null) ? 0 : smaReversalSmaPeriod.hashCode());
		result = prime * result + ((stdDevSmaPeriod == null) ? 0 : stdDevSmaPeriod.hashCode());
		result = prime * result + ((varianceMinValid == null) ? 0 : varianceMinValid.hashCode());
		result = prime * result + ((variancePeriod == null) ? 0 : variancePeriod.hashCode());
		result = prime * result + ((varianceSmaPeriod == null) ? 0 : varianceSmaPeriod.hashCode());
		result = prime * result + ((varianceSpanDiff == null) ? 0 : varianceSpanDiff.hashCode());
		result = prime * result + ((variationPeriod == null) ? 0 : variationPeriod.hashCode());
		result = prime * result + ((variationSpanDiff == null) ? 0 : variationSpanDiff.hashCode());
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
		IndicatorsConfig other = (IndicatorsConfig) obj;
		if (macdFastPeriod == null) {
			if (other.macdFastPeriod != null)
				return false;
		} else if (!macdFastPeriod.equals(other.macdFastPeriod))
			return false;
		if (macdSignal == null) {
			if (other.macdSignal != null)
				return false;
		} else if (!macdSignal.equals(other.macdSignal))
			return false;
		if (macdSlowPeriod == null) {
			if (other.macdSlowPeriod != null)
				return false;
		} else if (!macdSlowPeriod.equals(other.macdSlowPeriod))
			return false;
		if (rsiLowerThreshold == null) {
			if (other.rsiLowerThreshold != null)
				return false;
		} else if (!rsiLowerThreshold.equals(other.rsiLowerThreshold))
			return false;
		if (rsiTimePeriod == null) {
			if (other.rsiTimePeriod != null)
				return false;
		} else if (!rsiTimePeriod.equals(other.rsiTimePeriod))
			return false;
		if (rsiUpperThreshold == null) {
			if (other.rsiUpperThreshold != null)
				return false;
		} else if (!rsiUpperThreshold.equals(other.rsiUpperThreshold))
			return false;
		if (smaReversalSmaPeriod == null) {
			if (other.smaReversalSmaPeriod != null)
				return false;
		} else if (!smaReversalSmaPeriod.equals(other.smaReversalSmaPeriod))
			return false;
		if (stdDevSmaPeriod == null) {
			if (other.stdDevSmaPeriod != null)
				return false;
		} else if (!stdDevSmaPeriod.equals(other.stdDevSmaPeriod))
			return false;
		if (varianceMinValid == null) {
			if (other.varianceMinValid != null)
				return false;
		} else if (!varianceMinValid.equals(other.varianceMinValid))
			return false;
		if (variancePeriod == null) {
			if (other.variancePeriod != null)
				return false;
		} else if (!variancePeriod.equals(other.variancePeriod))
			return false;
		if (varianceSmaPeriod == null) {
			if (other.varianceSmaPeriod != null)
				return false;
		} else if (!varianceSmaPeriod.equals(other.varianceSmaPeriod))
			return false;
		if (varianceSpanDiff == null) {
			if (other.varianceSpanDiff != null)
				return false;
		} else if (!varianceSpanDiff.equals(other.varianceSpanDiff))
			return false;
		if (variationPeriod == null) {
			if (other.variationPeriod != null)
				return false;
		} else if (!variationPeriod.equals(other.variationPeriod))
			return false;
		if (variationSpanDiff == null) {
			if (other.variationSpanDiff != null)
				return false;
		} else if (!variationSpanDiff.equals(other.variationSpanDiff))
			return false;
		return true;
	}
	
	
	
}
