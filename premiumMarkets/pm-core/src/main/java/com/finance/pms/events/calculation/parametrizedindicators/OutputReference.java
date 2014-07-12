/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.calculation.parametrizedindicators;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.LeafOperation;

public class OutputReference implements Comparable<OutputReference> {

	String reference;
	String operationReference;
	String outputSelector;
	String formula;
	String referenceAsOperand;
	Boolean isLeaf;
	
	public OutputReference(String reference, String outputSelector, String formula, String referenceAsOperand, Boolean isLeaf, String operationReference) {
		this.reference = reference;
		this.outputSelector = outputSelector;
		this.formula = formula;
		this.referenceAsOperand = referenceAsOperand;
		this.isLeaf = isLeaf;
		
		this.operationReference = operationReference;
	}
	
	public OutputReference(Operation operation) {
		this.reference = operation.getReference();
		this.outputSelector  = operation.getOutputSelector();
		this.formula = operation.getFormula();
		this.referenceAsOperand = operation.getReferenceAsOperand();
		this.isLeaf = (operation instanceof LeafOperation);
		
		this.operationReference = operation.getOperationReference();
	}
	
	public OutputReference(Operation operation, String multiOutputDiscriminator) {
		this.reference = operation.getReference();
		this.outputSelector = multiOutputDiscriminator;
		this.formula = operation.getFormula();
		this.referenceAsOperand = operation.getReferenceAsOperand();
		this.isLeaf = (operation instanceof LeafOperation);
		
		this.operationReference = operation.getOperationReference();
	}
	
	@Override
	public int hashCode() {
		
		if (isLeaf) return super.hashCode();
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((formula == null) ? 0 : formula.hashCode());
		result = prime * result + ((outputSelector == null) ? 0 : outputSelector.hashCode());
		result = prime * result + ((operationReference == null) ? 0 : operationReference.hashCode());
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
		if (operationReference == null) {
			if (other.operationReference != null)
				return false;
		} else if (!operationReference.equals(other.operationReference))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(OutputReference o) {

		int compareTo = this.operationReference.compareTo(o.operationReference);
		if (compareTo == 0) {
			if (this.outputSelector == null && o.outputSelector == null) {
				compareTo = 0;
			} 
			else if (this.outputSelector == null) {
				compareTo = 1;
			}
			else if (o.outputSelector == null) {
				compareTo = -1;
			}
			else {
				compareTo = this.outputSelector.compareTo(o.outputSelector);
			}
		}
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
		}
		if (compareTo == 0 && isLeaf) {
			compareTo = this.hashCode() - o.hashCode();
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

	public String getOperationReference() {
		return operationReference;
	}

	public String getFormula() {
		return formula;
	}

	public String getOutputSelector() {
		return outputSelector;
	}

	public String getReferenceAsOperand() {
		return referenceAsOperand;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}
	
}