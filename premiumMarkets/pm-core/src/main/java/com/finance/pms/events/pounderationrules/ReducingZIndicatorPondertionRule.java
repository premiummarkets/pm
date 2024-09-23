package com.finance.pms.events.pounderationrules;

import com.finance.pms.events.SymbolEvents;

public class ReducingZIndicatorPondertionRule extends ReducingIndicatorPondertionRule {
	
	private static final long serialVersionUID = 1L;
	
	public ReducingZIndicatorPondertionRule() {
		super(3,1,2); //Test
	}
	
	@Override
	public int compare(SymbolEvents o1, SymbolEvents o2) {
		
		PonderationRule p1 = new ReducingZIndicatorPondertionRule();
		PonderationRule p2 = new ReducingZIndicatorPondertionRule();
		//!!The compareCal is in inverse order
		return super.compareCal(o1, o2, p1, p2);
	}

}
