package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlRootElement
@XmlSeeAlso({CrossConstantCondition.class, BooleanDoubleMapCondition.class, CmpDoubleMapCondition.class})
public class Condition<T> extends Operation {
	
	//private enum Conditions {EQUAL, SUP, INF, Going UP, Going DOWN, LOWERHIGH, LOWERLOW, HIGHERHIGH, HIGHERLOW};	
	
	protected Condition() {
		super();
	}
	
	public Condition(String reference) {
		super(reference, reference);
	}

	public Condition(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}

	public Condition(String reference, String description, Operation... operands) {
		this(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public Boolean conditionCheck(Comparable<T> ... ops) {
		return false;
	}

	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return new BooleanMapValue();
	}
	

}
