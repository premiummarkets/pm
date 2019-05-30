package com.finance.pms.events.quotations;

public class SplitData {
	public SplitData(double span, double delta, double split) {
		super();
		this.span = span;
		this.delta = delta;
		this.split = split;
	}
	double span;
	double delta;
	double split;
	public double getSpan() {
		return span;
	}
	public double getDelta() {
		return delta;
	}
	public double getSplit() {
		return split;
	}
}