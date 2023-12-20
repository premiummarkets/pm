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
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.StringableValue;

public class OutputReference implements Comparable<OutputReference> {
	
//	private static MyLogger LOGGER = MyLogger.getLogger(OutputReference.class);

	private String reference;
	
	private String operationReference;
	private String outputSelector;
	private String formula;
	private StringableValue constant;
	
	private String referenceAsOperand;
	private Boolean hasFailed = false;
	
	//FIXME multiOutputDiscriminator and outputSelector should two distinctive attributes
	public OutputReference(Operation operation, String multiOutputDiscriminator) {
		this.reference = operation.getReference();
		this.referenceAsOperand = operation.getReferenceAsOperand();

		this.operationReference = operation.getOperationReference();
		this.outputSelector = multiOutputDiscriminator;
		this.formula = (operation.getFormulae() != null)? operation.getFormulae() : operation.toFormulaeDevelopped();
		this.formula = this.formula.replaceAll("\\s+","");
	}

	public OutputReference(Operation operation, String referenceAsOperandOverride, NumberValue doubleValue) {
		this(operation, operation.getOutputSelector());
		this.referenceAsOperand = referenceAsOperandOverride;
		this.constant = doubleValue;
	}

	public OutputReference(Operation operation, String selector, String tamperedFormula) {
		this(operation, selector);
		this.formula = tamperedFormula;
		this.formula = this.formula.replaceAll("\\s+","");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((formula == null) ? 0 : formula.hashCode());
		result = prime * result + ((outputSelector == null) ? 0 : outputSelector.hashCode());
		result = prime * result + ((operationReference == null) ? 0 : operationReference.hashCode());
		result = prime * result + ((constant == null) ? 0 : constant.getValueAsString().hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {

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

		if (constant == null) {
			if (other.constant != null)
				return false;
		} else if (!constant.getValueAsString().equals(other.constant.getValueAsString()))
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
				compareTo = this.formula.replaceAll("\\s+","").compareTo(o.formula.replaceAll("\\s+",""));
			}
		}
		if (compareTo == 0) {
			if (this.constant == null && o.constant == null) {
				compareTo = 0;
			}
			else if (this.constant == null) {
				compareTo = 1;
			}
			else if (o.constant == null) {
				compareTo = -1;
			}
			else {
				compareTo = this.constant.getValueAsString().compareTo(o.constant.getValueAsString());
			}
		}

		return compareTo;
	}

	public String getReference() {
		return reference;
	}

	@Override
	public String toString() {
		return "OutputReference ["
				+ " reference=" + reference 
				+ " referenceAsOperand=" + referenceAsOperand + ","
				
				+ " formula=" + formula + ","
				+ " outputSelector=" + outputSelector + ","
				+ " operationReference=" + operationReference + ","
				+ " constant=" + constant + ","
				+ "]";
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
		return constant != null;
	}
	
	public Boolean getHasFailed() {
		return hasFailed;
	}

	public void setHasFailed(Boolean hasFailed) {
		this.hasFailed = hasFailed;
	}


}