
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


package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.events.quotations.QuotationsFactories;

public class LeftShifter<T> {
	
	private int nbDaysAhead;
	private Boolean fixSameDayOTW;
	private Boolean noDataLoss;
	
	public LeftShifter(int nbDaysAhead, Boolean fixSameDayOTW, Boolean noDataLoss) {
		super();
		this.nbDaysAhead = nbDaysAhead;
		this.fixSameDayOTW = fixSameDayOTW;
		this.noDataLoss = noDataLoss;
	}

	public SortedMap<Date, T> shift(SortedMap<Date, T> data) {

		SortedMap<Date, T> shiftedOuptput = new TreeMap<Date, T>();

		int fixedNbDaysAhead = fixSameDayOTW(data);

		List<Date> keyList = new ArrayList<Date>(data.keySet());
		int j0 = (fixedNbDaysAhead >= 0)? fixedNbDaysAhead:0;
		int jLast =  (fixedNbDaysAhead >= 0)? keyList.size(): keyList.size()+fixedNbDaysAhead;
		for (int j = j0; j < jLast; j++) {
			shiftedOuptput.put(keyList.get(j-fixedNbDaysAhead), data.get(keyList.get(j)));
		}

		if (noDataLoss) {
			int nbMissingDays = data.size() - shiftedOuptput.size();
			Calendar calendar = Calendar.getInstance();

			if (fixedNbDaysAhead > 0) {
				calendar.setTime(shiftedOuptput.firstKey());
				for (int i = 1; i <= nbMissingDays; i++) {
					QuotationsFactories.getFactory().incrementDate(calendar, -1);
					shiftedOuptput.put(calendar.getTime(), data.get(keyList.get(j0-i)));
				}
			} else {
				calendar.setTime(shiftedOuptput.lastKey());
				for (int i = 0; i < nbMissingDays; i++) {
					QuotationsFactories.getFactory().incrementDate(calendar, +1);
					shiftedOuptput.put(calendar.getTime(), data.get(keyList.get(jLast+i)));
				}
			}
		}
		return shiftedOuptput;

	}

	private int fixSameDayOTW(SortedMap<Date, T> data) {
		int fixedNbDaysAhead = nbDaysAhead;
		if (fixSameDayOTW) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(data.firstKey());
			int dataDayOTW = calendar.get(Calendar.DAY_OF_WEEK);
			QuotationsFactories.getFactory().incrementDate(calendar, -nbDaysAhead);
			int shiftDayOTW = calendar.get(Calendar.DAY_OF_WEEK);
			while (shiftDayOTW != dataDayOTW) {
				fixedNbDaysAhead++;
				calendar.setTime(data.firstKey());
				QuotationsFactories.getFactory().incrementDate(calendar, -fixedNbDaysAhead);
				shiftDayOTW = calendar.get(Calendar.DAY_OF_WEEK);
			}
		}
		return fixedNbDaysAhead;
	}

}
