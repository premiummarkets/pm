package com.finance.pms.events.calculation.parametrizedindicators;

import java.io.Serializable;

import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.operations.nativeops.StringableValue;
import com.finance.pms.portfolio.InfoObject;

public class OutputDescr implements InfoObject, Comparable<OutputDescr>, Serializable {

	private static final long serialVersionUID = -9172972104526800258L;
	
	private OutputReference outputReference;
	private ChartedOutputGroup container;

	private Type type;
	private Integer outputIndex;
	private StringableValue discriminentConstant;

	private Boolean displayOnChart;

	public OutputDescr(OutputReference outputReference, ChartedOutputGroup container, Type type, Integer outputIndex, StringableValue discriminentConstant, Boolean displayOnChart) {
		super();
		this.outputReference = outputReference;
		this.container = container;

		this.type = type;
		this.outputIndex = outputIndex;
		this.discriminentConstant = discriminentConstant;

		this.displayOnChart = displayOnChart;
		
	}

	public Type getType() {
		return type;
	}

	public Integer getOutputIndex() {
		return outputIndex;
	}

	public StringableValue getConstant() {
		return discriminentConstant;
	}

	public void setOutputIndex(Integer outputIndex) {
		this.outputIndex = outputIndex;
	}

	public String fullQualifiedName() {

		//outputReference || discriminentConstant
		String discriminantReference = (outputReference.getIsLeaf())?discriminentConstant.getAsStringable():outputReference.getReference();

		//group (: outputSelector)?
		String group = getContainer().groupUniqueId().toString().substring(0, 8);
		discriminantReference = ((isMain())? "": "_") + group + ":" + discriminantReference
								+ ((outputReference.getOutputSelector() != null)? ":" + outputReference.getOutputSelector():"");

		//(operationReference)? 
		String familyName =
				(outputReference.getFormula() != null)?
						outputReference.getFormula():
						(!(outputReference.getOperationReference().equals(discriminantReference))?outputReference.getOperationReference():"");
		//familyName = familyName + ((outputReference.getOutputSelector() != null)? ":" + outputReference.getOutputSelector():"");

		//(referenceAsOperand)?
		String displayedAs = (outputReference.getReferenceAsOperand() != null)?outputReference.getReferenceAsOperand():"";

		//discriminantReference ( \(familyName\) )? (as displayedAs)?
		return discriminantReference + ((!familyName.isEmpty())?" = " + familyName + " ":"") + ((!displayedAs.isEmpty())?" as " + displayedAs:"");
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
			if (this.type.equals(Type.MAIN) || this.type.equals(Type.BOTH)) this.type = Type.BOTH;
			break;
		default :
			this.type = newType;
		}

	}

	void setContainer(ChartedOutputGroup container) {
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
	public String groupId() {
		return getContainer().groupUniqueId().toString();
	}

	@Override
	public Boolean isMain() {
		return getContainer().getThisGroupMainOutputDescription().equals(this);
	}

	@Override
	public String toolTip() {
		return //((outputReference.getFormula() != null)?(outputReference.getFormula()).replaceAll("(.{100,200}),", "$1,\n"):fullQualifiedName()) + "\n" +
				outputReference.getReference() + ":" + (isMain()?outputReference.getFormula():outputReference.getOutputSelector()) + "\n" +
				"Group: " + getContainer().groupUniqueId().toString() + "\n" +
				((isMain())?"\nStatus:\n" + getContainer().getGroupStatus() + "\n":"");
	}

	@Override
	public String toString() {
		return "OutputDescr [" + outputReference + ", container=" + container.getThisGroupMainOutputReference().getReference() +
				", type=" + type + ", outputIndex=" + outputIndex + ", constant=" + discriminentConstant + ", displayOnChart=" + displayOnChart + "]";
	}
	
	@Override
	public int compareToInfoObject(InfoObject o) {
		if (o instanceof OutputDescr) {
			int cmp = getContainer().getThisGroupMainOutputReference().compareTo(((OutputDescr) o).getContainer().getThisGroupMainOutputReference());
			if (cmp != 0) return cmp;
			cmp = (groupId() != null && o.groupId() != null)?groupId().compareTo(o.groupId()):((groupId() == null)?((o.groupId() != null)?-1:0):1);
			if (cmp != 0) return cmp;
			cmp = (isMain() != null && o.isMain() != null)?o.isMain().compareTo(isMain()):((isMain() == null)?((o.isMain() != null)?1:this.compareTo((OutputDescr) o)):-1);
			if (cmp != 0) return cmp;
			return InfoObject.super.compareToInfoObject(o);
		} else {
			return InfoObject.super.compareToInfoObject(o);
		}
	}

	@Override
	public int compareTo(OutputDescr o) {
		int compareTo = this.outputReference.compareTo(o.outputReference);
		if (compareTo == 0) {
			compareTo = this.container.compareTo(o.container);
		}
		if (compareTo == 0) {
			if (this.discriminentConstant == null && o.discriminentConstant == null) return 0;
			if (this.discriminentConstant != null && o.discriminentConstant == null) return 1;
			compareTo = this.discriminentConstant.getAsStringable().compareTo(o.discriminentConstant.getAsStringable());
		}
		return compareTo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((outputReference == null) ? 0 : outputReference.hashCode());
		result = prime * result + ((container == null) ? 0 : container.hashCode());
		result = prime * result + ((discriminentConstant == null) ? 0 : discriminentConstant.getAsStringable().hashCode());
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
		if (outputReference == null) {
			if (other.outputReference != null)
				return false;
		} else if (!outputReference.equals(other.outputReference))
			return false;
		if (container == null) {
			if (other.container != null)
				return false;
		} else if (!container.equals(other.container))
			return false;
		if (discriminentConstant == null) {
			if (other.discriminentConstant != null)
				return false;
		} else if (!discriminentConstant.equals(other.discriminentConstant))
			return false;
		return true;
	}

}