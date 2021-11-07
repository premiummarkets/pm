package com.finance.pms.events.quotations;

public class SplitData {
	
	private double span;
	private double splitSpanAdjustment;
	private long splitRate;
	private double mergeSpanAdjustment;
	private long mergeRate;
	
	public SplitData(double span, double spanAdjustmentDeltaUp, long splitRate, double adjustementDeltaDown, long mergeRate) {
		super();
		this.span = span;
		this.splitSpanAdjustment = spanAdjustmentDeltaUp;
		this.splitRate = splitRate;
		this.mergeSpanAdjustment = adjustementDeltaDown;
		this.mergeRate = mergeRate;
	}
	public double getSpan() {
		return span;
	}
	public double getSplitSpanAdjustment() {
		return splitSpanAdjustment;
	}
	public long getSplitRate() {
		return splitRate;
	}
	public double getMergeSpanAdjustment() {
		return mergeSpanAdjustment;
	}
	/**
	 * Needs inversion before use.
	 * @return
	 */
	public long getMergeRate() {
		return mergeRate;
	}
	@Override
	public String toString() {
		return "SplitData [span=" + span +
				", splitSpanAdjustment=" + splitSpanAdjustment + ", splitRate=" + splitRate +
				", mergeSpanAdjustment=" + mergeSpanAdjustment + ", mergeRate=" + mergeRate + "]";
	}
	
	
}