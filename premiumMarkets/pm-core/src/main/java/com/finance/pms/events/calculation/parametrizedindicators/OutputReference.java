package com.finance.pms.events.calculation.parametrizedindicators;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.LeafOperation;

public class OutputReference implements Comparable<OutputReference> {

	String reference;
	String outputSelector;
	String formula;
	String referenceAsOperand;
	Boolean isLeaf;
	
	public OutputReference(Operation operation) {
		this.reference = operation.getReference();
		this.outputSelector = (operation.getOutputSelector() != null)? operation.getOutputSelector() : operation.getReference();
		this.formula = operation.getFormula();
		this.referenceAsOperand = operation.getReferenceAsOperand();
		isLeaf = (operation instanceof LeafOperation);
	}
	
	public OutputReference(Operation operation, String multiOutputDiscriminator) {
		this.reference = operation.getReference();
		this.outputSelector = multiOutputDiscriminator;
		this.formula = operation.getFormula();
		this.referenceAsOperand = operation.getReferenceAsOperand();
		isLeaf = (operation instanceof LeafOperation);
	}
	
	@Override
	public int hashCode() {
		
		if (isLeaf) return super.hashCode();
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((formula == null) ? 0 : formula.hashCode());
		result = prime * result + ((outputSelector == null) ? 0 : outputSelector.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		
		if (isLeaf) return super.equals(obj);
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OutputReference other = (OutputReference) obj;
		if (formula == null) {
			if (other.formula != null)
				return false;
		} else if (!formula.equals(other.formula))
			return false;
		if (outputSelector == null) {
			if (other.outputSelector != null)
				return false;
		} else if (!outputSelector.equals(other.outputSelector))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(OutputReference o) {

		int compareTo = this.reference.compareTo(o.reference);
		if (compareTo == 0) {
			compareTo = this.outputSelector.compareTo(o.outputSelector);
			if (compareTo == 0) {
				if (this.formula == null && o.formula == null) {
					compareTo = 0;
				} 
				else if (this.formula == null) {
					compareTo = 1;
				}
				else if (o.formula == null) {
					compareTo = -1;
				}
				else {
					compareTo = this.formula.compareTo(o.formula);
				}
				if (compareTo == 0 && isLeaf) {
					compareTo = this.hashCode() - o.hashCode();
				}
			}
		}
		return compareTo;
	}

	public String getReference() {
		return reference;
	}

	@Override
	public String toString() {
		return "OutputReference [hash=" + hashCode() + ", referenceAsOperand=" + referenceAsOperand + ", reference=" + reference + ", outputSelector="+ outputSelector + ", formula=" + formula + "]";
	}
	
}