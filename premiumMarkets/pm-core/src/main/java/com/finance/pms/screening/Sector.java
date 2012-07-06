/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
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
package com.finance.pms.screening;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Sector {
	
	private NavigableSet<TrendSupplementedStock>  listOfShares;
	private String sectorName;

	public Sector(String sectorName) {
		super();
		this.sectorName = sectorName;
		this.listOfShares = new TreeSet<TrendSupplementedStock>(new Comparator<TrendSupplementedStock>() {

			
			public int compare(TrendSupplementedStock o1, TrendSupplementedStock o2) {
				if (o1.equals(02)) return 0;
				return o1.fullRating().getValue().compareTo(o2.fullRating().getValue());
			}
			
		});
	}

	public BigDecimal pastRating() {
		BigDecimal o2y = this.getYield();
		BigDecimal o2c = this.getPerfs();
		int nb  = 2;
		return readablePerCent(perCentOf(o2y.add(o2c),nb));
	}
	
	public BigDecimal futurRating() {
		BigDecimal ret = BigDecimal.ZERO;
		
		ret = ret.add(complementarPerCentOf(getConsensus(),5)).add(getPotentiel());
		Integer nbV = 2;
		
		return readablePerCent(perCentOf(ret, nbV));
	}
	
	public BigDecimal calculateFullRating() {
		return perCentOf(pastRating().add(futurRating()),2);
	}
	
	private BigDecimal complementarPerCentOf(BigDecimal value, Integer factor) {
		value = (value == null)?new BigDecimal(factor):value;
		return new BigDecimal(factor).subtract(value).divide(new BigDecimal(factor),4,BigDecimal.ROUND_DOWN);
	}
	
	private BigDecimal perCentOf(BigDecimal value, Integer factor) {
		value = (value == null)?BigDecimal.ZERO:value;
		return value.divide(new BigDecimal(factor),4,BigDecimal.ROUND_DOWN);
	}
	
	private BigDecimal readablePerCent(BigDecimal bigDecimal) {
		BigDecimal rChange = bigDecimal.multiply(new BigDecimal(100)).setScale(4,BigDecimal.ROUND_DOWN);
		return rChange;
	}
	
	public BigDecimal calculatePotentiel() {
		BigDecimal ret = BigDecimal.ZERO.setScale(4);
		int  nb = 0;
		for (TrendSupplementedStock stockPerf : listOfShares) {
			if (stockPerf.yahooPotentielPrice() != null) {
				ret = ret.add(stockPerf.yahooPotentielPrice().getValue());
				nb++;
			}
			if (stockPerf.boursoPricePotentiel() != null) {
				ret = ret.add(stockPerf.boursoPricePotentiel().getValue());
				nb++;
			}
		}
		if (nb == 0) {
			return new BigDecimal(-1);
		}
		return ret.divide(new BigDecimal(nb),4,BigDecimal.ROUND_DOWN);
	}
	
	public BigDecimal getPotentiel() {
		return calculatePotentiel();
	}
	
	public BigDecimal calculateConsensus() {
		BigDecimal ret = BigDecimal.ZERO.setScale(4);
		int  nb = 0;
		for (TrendSupplementedStock stockPerf : listOfShares) {
			if (stockPerf.getYahooMeanRecommendations() != null) {
				ret = ret.add(stockPerf.getYahooMeanRecommendations());
				nb++;
			}
			if (stockPerf.getBoursoMeanRecommendations()  != null) {
				ret = ret.add(stockPerf.getBoursoMeanRecommendations());
				nb++;
			}
		}
		if (nb == 0) {
			return null;
		}
		return ret.divide(new BigDecimal(nb),4,BigDecimal.ROUND_DOWN);
	}

	public BigDecimal getConsensus() {
		return calculateConsensus();
	}
	
	public BigDecimal getPerfs() {
		BigDecimal ret = BigDecimal.ZERO.setScale(4);
		int nbValids = listOfShares.size();
		for (TrendSupplementedStock stockPerf : listOfShares) {
			if (stockPerf.priceChangeTTM().isValid()) {
				ret = ret.add(stockPerf.priceChangeTTM().getValue());
			} else {
				nbValids--;
			}
		}
		return ret.divide(new BigDecimal(nbValids),4,BigDecimal.ROUND_DOWN);
	}
	
	public BigDecimal getYield() {
		BigDecimal ret = BigDecimal.ZERO.setScale(4);
		int  nb = 0;
		for (TrendSupplementedStock stockPerf : listOfShares) {
			if (stockPerf.yield() != null) {
				ret = ret.add(stockPerf.yield());
				nb++;
			}
		}
		if (nb == 0) {
			return null;
		}
		return ret.divide(new BigDecimal(nb),4,BigDecimal.ROUND_DOWN);
	}
	
	public void addStock(TrendSupplementedStock stockPerf) {
		this.listOfShares.add(stockPerf);
	}
	
	public TrendSupplementedStock getBest() {
		return listOfShares.last();
	}

	public String getSectorName() {
		return sectorName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sectorName == null) ? 0 : sectorName.hashCode());
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
		Sector other = (Sector) obj;
		if (sectorName == null) {
			if (other.sectorName != null)
				return false;
		} else if (!sectorName.equals(other.sectorName))
			return false;
		return true;
	}

	public String getYieldtoString() {
		return (this.getYield() == null)?"null":this.getYield().toString();
	}

	public String getPotentieltoString() {
		return (this.getPotentiel() == null)?"null":getPotentiel().toString();
	}

	public String getConsensustoString() {
		return (this.getConsensus() == null)?"null":getConsensus().toString();
	}
	
	public NavigableSet<TrendSupplementedStock> getListOfShares() {
		return listOfShares;
	}

	
}
