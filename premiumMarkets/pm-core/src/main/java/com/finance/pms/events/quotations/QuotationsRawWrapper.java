package com.finance.pms.events.quotations;

public class QuotationsRawWrapper extends Quotations {

	public QuotationsRawWrapper(Quotations quotations) {
		super(quotations);
	}

	@Override
	public QuotationUnit get(int index) {
		return new QuotationUnitRawWrapper(super.get(index));
	}

}
