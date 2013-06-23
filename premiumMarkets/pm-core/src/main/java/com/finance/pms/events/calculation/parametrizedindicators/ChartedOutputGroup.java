package com.finance.pms.events.calculation.parametrizedindicators;

import java.util.HashMap;
import java.util.Map;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.NumberValue;

public class ChartedOutputGroup {

	public enum Type {MAIN, SIGNAL, BOTH, CONSTANT, MULTI, MULTISIGNAL, INVISIBLE};
	
	public class OutputDescr {
		
		ChartedOutputGroup container;
		String outputName;
		Type type;
		Integer outputIndex;
		Value<?> value;
		
		public OutputDescr(ChartedOutputGroup container, Type type, Integer outputIndex, Value<?> value, String outputName) {
			super();
			this.container = container;
			this.type = type;
			this.outputIndex = outputIndex;
			this.value = value;
			this.outputName = outputName;
		}

		public Type getType() {
			return type;
		}

		public Integer getOutputIndex() {
			return outputIndex;
		}

		public Value<?> getValue() {
			return value;
		}

		public void setOutputIndex(Integer outputIndex) {
			this.outputIndex = outputIndex;
		}

		public String fullQualifiedName() {
			//return ((container!=null && container.getThisDescription() != this)?container.getThisDescription().fullQualifiedName()+" : ":"")+friendlyName;
			return ((container!=null)?container.getThisReference().getOperationReference()+" : ":"")+outputName;
		}

		@Override
		public String toString() {
			return "OutputDescr [name=" + outputName + ", type=" + type + ", outputIndex=" + outputIndex + ", value=" + value + "]";
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
	}
	
	OutputReference thisReference;
	OutputDescr thisDescription;
	Map<OutputReference, OutputDescr> components;
	
	//Non displayed group
	public ChartedOutputGroup(OutputReference outputReference, int outputIndex) {
		thisDescription = new OutputDescr(this, Type.INVISIBLE, outputIndex, null, null);
		thisReference = outputReference;
		components = new HashMap<OutputReference, ChartedOutputGroup.OutputDescr>();
	}
	
	
	//Adding a main
	public ChartedOutputGroup(Operation operation, int outputIndex) {
		//String friendlyName = operation.aMoreFriendlyName();
		String outputName = operation.getReference();
		if (operation.getOutputSelector() != null) outputName = operation.getOutputSelector() + " ("+outputName+")";
		
		thisDescription = new OutputDescr(this, Type.MAIN, outputIndex, null, outputName);
		thisReference = new OutputReference(operation);
		components = new HashMap<OutputReference, ChartedOutputGroup.OutputDescr>();
	}
	
	public void addSignal(Operation operation, int outputIndex) {
		//String friendlyName = operation.aMoreFriendlyName();
		String outputName = operation.getReference();
		if (operation.getOutputSelector() != null) outputName = operation.getOutputSelector() + " ("+outputName+")";
		this.components.put(new OutputReference(operation), new OutputDescr(this, Type.SIGNAL, outputIndex, null, outputName));
	}

	public void addConstant(String parentReference, Operation operation, NumberValue doubleValue) {
		//String friendlyName = doubleValue.getNumberValue() + " " + namePrefix +" "+operation.getReferenceAsOperand();
		String outputName = doubleValue.getNumberValue() +" ("+parentReference+" "+operation.getReferenceAsOperand()+")";
		this.components.put(new OutputReference(operation), new OutputDescr(this, Type.CONSTANT, null, doubleValue, outputName));
	}
	
	public void addAdditonalOutput(String outputKey, Operation operation, int outputIndex, Type type) {
		String outputName = outputKey + " ("+operation.getReference()+")";
		this.components.put(new OutputReference(operation, outputKey), new OutputDescr(this, type, outputIndex, null, outputName));
	}
	
	public OutputDescr mvComponentIn(OutputReference outputRef, OutputDescr outputDescr) {
		outputDescr.setContainer(this);
		this.components.put(outputRef,outputDescr);
		return outputDescr;
	}

	public Map<OutputReference, OutputDescr> getComponents() {
		return components;
	}

	public OutputDescr getThisDescription() {
		return thisDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((thisReference == null) ? 0 : thisReference.hashCode());
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
		if (thisReference == null) {
			if (other.thisReference != null)
				return false;
		} else if (!thisReference.equals(other.thisReference))
			return false;
		return true;
	}

	public OutputReference getThisReference() {
		return thisReference;
	}


	@Override
	public String toString() {
		return "ChartedOutputGroup [\n\t thisReference=" + thisReference + ", \n\t thisDescription=" + thisDescription + ", \n\t components=" + components + "]";
	}
	
	


}
