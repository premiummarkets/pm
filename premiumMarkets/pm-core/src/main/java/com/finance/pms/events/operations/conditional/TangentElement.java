package com.finance.pms.events.operations.conditional;

import java.util.Date;
import java.util.SortedMap;

public class TangentElement {

	private SortedMap<Date, Double> tangent;
	private Date closingDate;

	TangentElement(SortedMap<Date, Double> tangent, Date closingDate) {
		super();
		this.tangent = tangent;
		this.closingDate = closingDate;
	}

	public SortedMap<Date, Double> getTangent() {
		return tangent;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

}