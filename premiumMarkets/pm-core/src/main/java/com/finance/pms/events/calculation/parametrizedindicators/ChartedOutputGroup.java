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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StringableValue;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.portfolio.InfoObject;

public class ChartedOutputGroup {

	public enum Type {MAIN, SIGNAL, BOTH, CONSTANT, MULTI, MULTISIGNAL, INVISIBLE}; //BOTH is MAIN & SIGNAL

	public class OutputDescr implements InfoObject, Comparable<OutputDescr>{

		private UUID uuid;

		private OutputReference outputReference;
		private ChartedOutputGroup container;
		private Type type;
		private Integer outputIndex;
		private StringableValue constant;

		private Boolean displayOnChart;

		public OutputDescr(OutputReference outputReference, ChartedOutputGroup container, Type type, Integer outputIndex, StringableValue constant) {
			super();
			this.uuid = UUID.randomUUID();
			this.outputReference = outputReference;
			this.container = container;
			this.type = type;
			this.outputIndex = outputIndex;
			this.constant = constant;

			this.displayOnChart = true;
		}

		public Type getType() {
			return type;
		}

		public Integer getOutputIndex() {
			return outputIndex;
		}

		public StringableValue getConstant() {
			return constant;
		}

		public void setOutputIndex(Integer outputIndex) {
			this.outputIndex = outputIndex;
		}

		public String fullQualifiedName() {
			String discriminantReference = outputReference.getReference();
			if (outputReference.getIsLeaf()) {
				discriminantReference = constant.getValueAsString();
			}

			String familyName = outputReference.getOperationReference() +
					((outputReference.getOutputSelector() != null)?":" + outputReference.getOutputSelector():"");

			//			String displayedAs = (outputReference.getReferenceAsOperand() != null)?outputReference.getReferenceAsOperand():"";
			//			if (type.equals(Type.MAIN)) {
			//				displayedAs = "indicator";
			//			}
			//			if (type.equals(Type.MULTISIGNAL) || type.equals(Type.SIGNAL)) {
			//				displayedAs = "signal";
			//			}

			String displayedAs = (outputReference.getReferenceAsOperand() != null)?
					outputReference.getReferenceAsOperand():
						((outputReference.getOutputSelector() != null)?outputReference.getOutputSelector():outputReference.getOperationReference());

					return discriminantReference + " (" + familyName + ") on graph as " + displayedAs;
		}

		@Override
		public String toString() {
			return "OutputDescr [type=" + type + ", outputIndex=" + outputIndex + ", constant=" + constant + ", displayed=" + displayOnChart + "]";
		}

		public ChartedOutputGroup getContainer() {
			return container;
		}

		public void maskType(Type newType) {

			switch (newType) {
			case MAIN :
				if (this.type.equals(Type.SIGNAL) || this.type.equals(Type.BOTH)) this.type = Type.BOTH;
				break;
			case SIGNAL :
				if (this.type.equals(Type.MAIN)  || this.type.equals(Type.BOTH)) this.type = Type.BOTH;
				break;
			default :
				this.type = newType;
			}

		}

		private void setContainer(ChartedOutputGroup container) {
			this.container = container;
		}

		public Boolean getDisplayOnChart() {
			return displayOnChart;
		}

		public void setDisplayOnChart(Boolean displayOnChart) {
			this.displayOnChart = displayOnChart;
		}

		@Override
		public String info() {
			return fullQualifiedName();
		}

		@Override
		public String tootTip() {
			return (outputReference.getFormula() != null)?outputReference.getFormula():fullQualifiedName();
		}

		@Override
		public int compareTo(OutputDescr o) {
			return (this.uuid).compareTo(o.uuid);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
			OutputDescr other = (OutputDescr) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (uuid == null) {
				if (other.uuid != null)
					return false;
			} else if (!uuid.equals(other.uuid))
				return false;
			return true;
		}

		private ChartedOutputGroup getOuterType() {
			return ChartedOutputGroup.this;
		}

	}

	private OutputReference thisReference;
	private OutputDescr thisDescription;
	private Map<OutputReference, OutputDescr> components;
	private UUID uuid;

	//Non displayed group
	public ChartedOutputGroup(OutputReference outputReference, int outputIndex) {
		uuid = UUID.randomUUID();
		thisDescription = new OutputDescr(outputReference, this, Type.INVISIBLE, outputIndex, null);
		thisReference = outputReference;
		components = new HashMap<>();
	}

	//Adding a main
	public ChartedOutputGroup(Operation operation, int outputIndex) {
		uuid = UUID.randomUUID();
		OutputReference outputReference = new OutputReference(operation);
		thisDescription = new OutputDescr(outputReference, this, Type.MAIN, outputIndex, null);
		thisReference = outputReference;
		components = new HashMap<>();
	}

	public OutputDescr addSignal(Operation operation, int outputIndex) {
		OutputReference outputReference = new OutputReference(operation);
		OutputDescr outputDescr = new OutputDescr(outputReference, this, Type.SIGNAL, outputIndex, null);
		this.components.put(outputReference, outputDescr);
		return outputDescr;
	}

	public void addConstant(String parentReference, Operation operation, NumberValue doubleValue) {
		String referenceAsOperandOverride = parentReference+" "+operation.getReferenceAsOperand();
		OutputReference outputReference = new OutputReference(operation.getReference(), null, null, referenceAsOperandOverride, true, operation.getOperationReference()); 
		this.components.put(outputReference, new OutputDescr(outputReference, this, Type.CONSTANT, null, doubleValue));
	}

	public void addAdditionalOutput(String outputKey, Operation operation, int outputIndex, Type type) {
		OutputReference outputReference = new OutputReference(operation, outputKey);
		this.components.put(outputReference, new OutputDescr(outputReference, this, type, outputIndex, null));
	}

	public OutputDescr mvComponentInThisGrp(OutputReference outputRef, OutputDescr outputDescr) {
		outputDescr.setContainer(this);
		this.components.put(outputRef, outputDescr);
		return outputDescr;
	}

	public Map<OutputReference, OutputDescr> getComponents() {
		return components;
	}

	public OutputDescr getThisDescription() {
		return thisDescription;
	}

	public UUID groupUniqueId() {
		return uuid;
	}

	public OutputReference getThisReference() {
		return thisReference;
	}

	@Override
	public String toString() {
		return "ChartedOutputGroup [\n\t uuid=" + uuid + ", \n\t thisReference=" + thisReference + ", \n\t thisDescription=" + thisDescription + ", \n\t components=" + components + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		ChartedOutputGroup other = (ChartedOutputGroup) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

}
