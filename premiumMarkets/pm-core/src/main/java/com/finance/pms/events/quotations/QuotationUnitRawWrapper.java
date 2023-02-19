package com.finance.pms.events.quotations;

import java.math.BigDecimal;

public class QuotationUnitRawWrapper extends QuotationUnit {

	private static final long serialVersionUID = 2088706149099250101L;

	public QuotationUnitRawWrapper(QuotationUnit qUnit) {
		super(qUnit);
	}

	@Override
	public BigDecimal getCloseSplit() {
		return super.getCloseRaw();
	}

	@Override
	public BigDecimal getHighSplit() {
		return super.getHighRaw();
	}

	@Override
	public BigDecimal getLowSplit() {
		return super.getLowRaw();
	}

	@Override
	public BigDecimal getOpenSplit() {
		return super.getOpenRaw();
	}

	@Override
	public long getVolumeSplit() {
		return super.getVolumeRaw();
	}
	
	

}
