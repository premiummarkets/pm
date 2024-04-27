package com.finance.pms.events.scoring.functions;

public class CannotCompareException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * In order of importance: the least to the most
	 */
	public enum Cause {
		FLOG, MINMAX, PROFIT, NEG_PROFIT, OTHER;
		
		public double getNoCompareCauseWeight() {
			return (this.ordinal() + 1) * 1000.0;
		}
	};
	
	Cause noCompareCause;

	public CannotCompareException(Exception e) {
		super(e);
		this.noCompareCause = Cause.OTHER;
	}

	public CannotCompareException(String string, Cause noCompareCause) {
		super(string);
		this.noCompareCause = noCompareCause;
	}
	
	public Cause getNoCompareCause() {
		return noCompareCause;
	}
	
	public double getNoCompareCauseWeight() {
		return noCompareCause.getNoCompareCauseWeight();
	}

}
