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
package com.finance.pms.portfolio;

import java.util.ArrayList;

import org.apache.commons.lang.NotImplementedException;

public class TransactionHistory extends ArrayList<TransactionRecord> {

	private static final long serialVersionUID = -3657003269695335915L;
	private String name;
	private double lastBuy;
	private int lastNbBuy;
	private double lastSell;
	private int lastNbSell;
	private ArrayList<Double> bsDiffs;
	private String previousMove;
	
	private boolean finalized = false;

	public TransactionHistory(String name) {
		super(0);
		this.name = name;
		lastBuy = 0.0;
		lastNbBuy = 0;
		lastSell = 0.0;
		lastNbSell = 0;
		bsDiffs = new ArrayList<Double>(0);
		previousMove = "";
	}

	/**
	 * 
	 */
	@Override
	public boolean add(TransactionRecord transactionRecord) {
		boolean res = super.add(transactionRecord);
		this.add2(transactionRecord);
		
		return res;
	}
	
	public void add2(TransactionRecord record) {
		
		if (finalized) throw new NotImplementedException();

		if (previousMove.equals(record.getMovement())) {
			//Nothing we just add up
		} else if (record.getMovement().equals("buy")){
			bsDiffs.add(lastSell/lastNbSell - lastBuy/lastNbBuy);
			lastBuy =0.0;
			lastNbBuy = 0;
			lastSell = 0.0;
			lastNbSell = 0;
			previousMove = "buy";
		} else if (record.getMovement().equals("sell")) {
			previousMove = "sell";
		}

		if (record.getMovement().equals("buy")) {
			lastBuy += record.getTransactionPrice().doubleValue();
			lastNbBuy++;
		} else if (record.getMovement().equals("sell")) {
			lastSell += record.getTransactionPrice().doubleValue();
			lastNbSell++;
		}

	}	

	public Double getSellBuyAvgDiff() {
		
		finilize();
		
		Double diff = 0.0;
		for (int i=1; i < bsDiffs.size(); i++) {
			diff = diff + bsDiffs.get(i);
		}
		if (bsDiffs.size() == 0) return 0D;
		if (bsDiffs.size() == 1) return diff;
		
		return diff/(bsDiffs.size()-1);
	}

	/**
	 * 
	 */
	private void finilize() {
		if (previousMove.equals("sell") && !finalized) {
			bsDiffs.add(lastSell/lastNbSell - lastBuy/lastNbBuy);
			finalized=true;
		}
	}
	
	public Integer nbDiffs() {
		
		finilize();
		
		return bsDiffs.size();
	}

	public String getName() {
		return this.name;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionHistory other = (TransactionHistory) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public double getLastBuy() {
		return lastBuy;
	}

	public void setLastBuy(double lastBuy) {
		this.lastBuy = lastBuy;
	}

	public int getLastNbBuy() {
		return lastNbBuy;
	}

	public void setLastNbBuy(int lastNbBuy) {
		this.lastNbBuy = lastNbBuy;
	}

	public double getLastSell() {
		return lastSell;
	}

	public void setLastSell(double lastSell) {
		this.lastSell = lastSell;
	}

	public int getLastNbSell() {
		return lastNbSell;
	}

	public void setLastNbSell(int lastNbSell) {
		this.lastNbSell = lastNbSell;
	}

	@Override
	public String toString() {
		return "TransactionHistory [name=" + name + ", bsDiffs=" + bsDiffs + "]";
	}
	
}
