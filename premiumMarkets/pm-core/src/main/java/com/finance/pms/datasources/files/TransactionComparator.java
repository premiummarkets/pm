package com.finance.pms.datasources.files;

import java.util.Comparator;

public class TransactionComparator implements Comparator<TransactionElement> {

	@Override
	public int compare(TransactionElement o1, TransactionElement o2) {

		//The sort MUST be done by date first in order to find the head of the set
		int equalDate = o1.getDate().compareTo(o2.getDate());
		if (equalDate == 0) {
			int id = o1.getId().compareTo(o2.getId());
			return id;
		}
		return equalDate;
	}


}
