package com.finance.pms.events.operations.conditional;

import com.finance.pms.events.scoring.functions.Line;

import java.util.Date;
import java.util.SortedMap;

public class TangentElement {

	private String label;
	private Line<Integer, Double> line;
	private Date closingDate;

	TangentElement(Line<Integer, Double> line, String label) {
		super();
		this.line = line;
		this.label = label;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public String getLabel() {
		return label;
	}

	public Line<Integer, Double> getLine() {
		return line;
	}

}