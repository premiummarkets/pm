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

import java.io.Serializable;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.StringableValue;

public class OutputReference implements Comparable<OutputReference>, Serializable {
	
	private static final long serialVersionUID = 7698521853387288950L;

//	private static MyLogger LOGGER = MyLogger.getLogger(OutputReference.class);

	private String operationReference;
	private String reference;
	
	private String userReference;
	
	private String outputSelector;
	private String formula;
	private StringableValue constant;
	private String referenceAsOperand;
	
	private Boolean hasFailed = false;


	
	//FIXME multiOutputDiscriminator and outputSelector should two distinctive attributes
	public OutputReference(Operation operation, String userOperationReference, String multiOutputDiscriminator) {
		this.operationReference = operation.getOperationReference();
		this.reference = operation.getReference();
		
		this.userReference = (userOperationReference != null)? userOperationReference : operationReference;
		
		this.outputSelector = multiOutputDiscriminator;
		this.formula = (operation.getFormulae() != null)? operation.getFormulae() : operation.toFormulaeDevelopped();
		this.formula = this.formula.replaceAll("\\s+","");
		this.referenceAsOperand = operation.getReferenceAsOperand();
	}

	public OutputReference(Operation operation, String userOperationReference, String referenceAsOperandOverride, NumberValue doubleValue) {
		this(operation, userOperationReference, operation.getOutputSelector());
		this.constant = doubleValue;
		this.referenceAsOperand = referenceAsOperandOverride;
	}

	public OutputReference(Operation operation, String userOperationReference, String selector, String tamperedFormula) {
		this(operation, userOperationReference, selector);
		this.formula = tamperedFormula;
		this.formula = this.formula.replaceAll("\\s+","");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((operationReference == null) ? 0 : operationReference.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		result = prime * result + ((userReference == null) ? 0 : userReference.hashCode());
		result = prime * result + ((outputSelector == null) ? 0 : outputSelector.hashCode());
		result = prime * result + ((formula == null) ? 0 : formula.hashCode());
		result = prime * result + ((constant == null) ? 0 : constant.getAsStringable().hashCode());
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
		
		if (operationReference == null) {
			if (other.operationReference != null)
				return false;
		} else if (!operationReference.equals(other.operationReference))
			return false;
		
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		
		if (userReference == null) {
			if (other.userReference != null)
				return false;
		} else if (!userReference.equals(other.userReference))
			return false;
		
		if (outputSelector == null) {
			if (other.outputSelector != null)
				return false;
		} else if (!outputSelector.equals(other.outputSelector))
			return false;

		if (formula == null) {
			if (other.formula != null)
				return false;
		} else if (!formula.equals(other.formula))
			return false;

		if (constant == null) {
			if (other.constant != null)
				return false;
		} else if (!constant.getAsStringable().equals(other.constant.getAsStringable()))
			return false;

		return true;
	}

	@Override
	public int compareTo(OutputReference o) {

		int compareTo = this.operationReference.compareTo(o.operationReference);
		if (compareTo == 0) {
			compareTo = this.reference.compareTo(o.reference);
		}
		if (compareTo == 0) {
			compareTo = this.userReference.compareTo(o.userReference);
		}
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
				compareTo = this.constant.getAsStringable().compareTo(o.constant.getAsStringable());
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
				+ " operationReference=" + operationReference + ","
				+ " reference=" + reference 
				+ " userReference=" + userReference + ","
				+ " outputSelector=" + outputSelector + ","
				+ " formula=" + formula + ","
				+ " constant=" + constant + ","
				+ " referenceAsOperand=" + referenceAsOperand + ","
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