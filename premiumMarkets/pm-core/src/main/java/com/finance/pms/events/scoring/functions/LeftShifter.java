package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.events.quotations.QuotationsFactories;

public class LeftShifter {
	
	private int nbDaysAhead;
	private Boolean fixSameDayOTW;
	private Boolean noDataLoss;
	
	public LeftShifter(int nbDaysAhead, Boolean fixSameDayOTW, Boolean noDataLoss) {
		super();
		this.nbDaysAhead = nbDaysAhead;
		this.fixSameDayOTW = fixSameDayOTW;
		this.noDataLoss = noDataLoss;
	}

	public SortedMap<Date, double[]> shift(SortedMap<Date, double[]> data) {

		SortedMap<Date, double[]> shiftedOuptput = new TreeMap<Date, double[]>();

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

	private int fixSameDayOTW(SortedMap<Date, double[]> data) {
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
