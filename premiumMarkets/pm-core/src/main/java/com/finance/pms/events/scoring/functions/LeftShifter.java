
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


package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.QuotationsFactories;
/**
 * 
 * @author guil
 * Although the chart will show discrepancies (because of weekend gaps being shifted through the week) the left shit is correct in terms of data points.
 * see 
 * ee19d8ce-650a-4f8b-b0c4-46a51d0c3655_IBM_k_training_0b6df74a-eeb0-47b7-a8b7-de1a9ec71f66.csv from 
 * fileOperation(iosExporter("autoPortfolioLogs",targetStockInfo("symbol"),iosAssembler("FALSE","FALSE",ogHouseTrendSMA(),ogHouseTrendUnNorm(),ogHouseTrendUnNormNLFix())),1,0)
 * @param <T>
 * noDataLoss: 
 * 	will complement the shit with the data that may have been cut (left is > 0 or right if < 0)
 * 	The issue is that in this algorithm the keySet does not span over the cut period. Hence the made up dates using QuotationsFactories.getFactory().incrementDate calls.
 * 
 */
public class LeftShifter<T> {
	
	private int nbDaysAhead;
	private Boolean noDataLoss;
	private Stock stock;
	private Collection<QuotationDataType> quotationsDataTypes;
	
	public LeftShifter(Stock stock, Collection<QuotationDataType> quotationsDataTypes, int nbDaysAhead, Boolean noDataLoss) {
		super();
		this.stock = stock;
		this.quotationsDataTypes = quotationsDataTypes;
		this.nbDaysAhead = nbDaysAhead;
		this.noDataLoss = noDataLoss;
	}

	public SortedMap<Date, T> shift(SortedMap<Date, T> data) throws NotEnoughDataException {

		SortedMap<Date, T> shiftedOutput = new TreeMap<Date, T>();

		List<Date> keyList = new ArrayList<Date>(data.keySet());
		int j0 = (nbDaysAhead >= 0)? nbDaysAhead:0;
		int jLast =  (nbDaysAhead >= 0)? keyList.size(): keyList.size() + nbDaysAhead;
		for (int j = j0; j < jLast; j++) {
			shiftedOutput.put(keyList.get(j-nbDaysAhead), data.get(keyList.get(j)));
		}

		if (noDataLoss) {
			int nbMissingDays = data.size() - shiftedOutput.size();
			Calendar calendar = Calendar.getInstance();

			if (nbDaysAhead > 0) {
				calendar.setTime(shiftedOutput.firstKey());
				for (int i = 1; i <= nbMissingDays; i++) {
					QuotationsFactories.getFactory().incrementDate(stock, quotationsDataTypes, calendar, -1);
					shiftedOutput.put(calendar.getTime(), data.get(keyList.get(j0-i)));
				}
			} else {
				calendar.setTime(shiftedOutput.lastKey());
				for (int i = 0; i < nbMissingDays; i++) {
					QuotationsFactories.getFactory().incrementDate(stock, quotationsDataTypes, calendar, +1);
					shiftedOutput.put(calendar.getTime(), data.get(keyList.get(jLast+i)));
				}
			}
		}
		return shiftedOutput;

	}

}
