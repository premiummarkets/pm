package com.finance.pms.portfolio;

import java.math.BigDecimal;
import java.util.Date;

public class InOutWeighted {
	

	private BigDecimal in;
	private BigDecimal out;
	private Date date;

	InOutWeighted(BigDecimal in, BigDecimal out, Date currentEndDate) {//TODO
		super();
		this.in = in;
		this.out = out;
		this.date = currentEndDate;
	}
	
	public Boolean isEmpty() {
		return in.compareTo(BigDecimal.ZERO) == 0;
	}

	public InOutWeighted(Date date) {
		this.in = BigDecimal.ZERO;
		this.out = BigDecimal.ZERO;
		this.date = date;
	}
	
	BigDecimal getWeightedInvestedStillIn() {
		BigDecimal weightedInvestedStillIn = in.subtract(out);
		if (weightedInvestedStillIn.compareTo(BigDecimal.ZERO) < 0) weightedInvestedStillIn = BigDecimal.ZERO;
		return weightedInvestedStillIn;
	}
	
	public BigDecimal getIn() {
		return in;
	}

	public BigDecimal getOut() {
		return out;
	}
	
	public Date getDate() {
		return date;
	}
	
}