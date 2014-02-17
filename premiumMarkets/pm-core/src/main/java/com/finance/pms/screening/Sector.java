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
package com.finance.pms.screening;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Sector {
	
	private NavigableSet<ScreeningSupplementedStock>  listOfShares;
	private String sectorName;

	public Sector(String sectorName) {
		super();
		this.sectorName = sectorName;
		this.listOfShares = new TreeSet<ScreeningSupplementedStock>(new Comparator<ScreeningSupplementedStock>() {

			
			public int compare(ScreeningSupplementedStock o1, ScreeningSupplementedStock o2) {
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
		return new BigDecimal(factor).subtract(value).divide(new BigDecimal(factor), 10, BigDecimal.ROUND_HALF_EVEN);
	}
	
	private BigDecimal perCentOf(BigDecimal value, Integer factor) {
		value = (value == null)?BigDecimal.ZERO:value;
		return value.divide(new BigDecimal(factor), 10, BigDecimal.ROUND_HALF_EVEN);
	}
	
	private BigDecimal readablePerCent(BigDecimal bigDecimal) {
		BigDecimal rChange = bigDecimal.multiply(new BigDecimal(100)).setScale(10, BigDecimal.ROUND_HALF_EVEN);
		return rChange;
	}
	
	public BigDecimal calculatePotentiel() {
		BigDecimal ret = BigDecimal.ZERO.setScale(4);
		int  nb = 0;
		for (ScreeningSupplementedStock stockPerf : listOfShares) {
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
		return ret.divide(new BigDecimal(nb), 10, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public BigDecimal getPotentiel() {
		return calculatePotentiel();
	}
	
	public BigDecimal calculateConsensus() {
		BigDecimal ret = BigDecimal.ZERO.setScale(4);
		int  nb = 0;
		for (ScreeningSupplementedStock stockPerf : listOfShares) {
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
		return ret.divide(new BigDecimal(nb), 10, BigDecimal.ROUND_HALF_EVEN);
	}

	public BigDecimal getConsensus() {
		return calculateConsensus();
	}
	
	public BigDecimal getPerfs() {
		BigDecimal ret = BigDecimal.ZERO.setScale(4);
		int nbValids = listOfShares.size();
		for (ScreeningSupplementedStock stockPerf : listOfShares) {
			if (stockPerf.priceChangeTTM().isValid()) {
				ret = ret.add(stockPerf.priceChangeTTM().getValue());
			} else {
				nbValids--;
			}
		}
		return (nbValids > 0)?ret.divide(new BigDecimal(nbValids), 10, BigDecimal.ROUND_HALF_EVEN):BigDecimal.ZERO;
	}
	
	public BigDecimal getYield() {
		BigDecimal ret = BigDecimal.ZERO.setScale(4);
		int  nb = 0;
		for (ScreeningSupplementedStock stockPerf : listOfShares) {
			if (stockPerf.yield() != null) {
				ret = ret.add(stockPerf.yield());
				nb++;
			}
		}
		if (nb == 0) {
			return null;
		}
		return ret.divide(new BigDecimal(nb), 10, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public void addStock(ScreeningSupplementedStock stockPerf) {
		this.listOfShares.add(stockPerf);
	}
	
	public ScreeningSupplementedStock getBest() {
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
	
	public NavigableSet<ScreeningSupplementedStock> getListOfShares() {
		return listOfShares;
	}

	
}
