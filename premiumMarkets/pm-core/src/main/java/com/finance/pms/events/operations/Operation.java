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
package com.finance.pms.events.operations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.conditional.Condition;
import com.finance.pms.events.operations.nativeops.CachableOperation;
import com.finance.pms.events.operations.nativeops.LeafOperation;
import com.finance.pms.events.operations.nativeops.ListOperation;
import com.finance.pms.events.operations.nativeops.MATypeOperation;
import com.finance.pms.events.operations.nativeops.MapOperation;
import com.finance.pms.events.operations.nativeops.MultiMapValue;
import com.finance.pms.events.operations.nativeops.NumberMathOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.OperationReferenceOperation;
import com.finance.pms.events.operations.nativeops.RequiredShiftWrapperOperation;
import com.finance.pms.events.operations.nativeops.StockOperation;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringerOperation;
import com.finance.pms.events.operations.nativeops.TargetStockInfoOperation;
import com.finance.pms.events.operations.parameterized.ParameterizedOperationBuilder;
import com.finance.pms.events.quotations.QuotationDataType;

/**
 * !!Operations must be state less across calculations as reused!! 
 * Operands: resolved at runtime.
 * Parameters: preset operands already resolved.
 **/
@XmlType(propOrder = { "reference", "referenceAsOperand", "description", "formulae", "parameter", "defaultValue", "operands", "availableOutputSelectors", "outputSelector", "isVarArgs"} )
@XmlSeeAlso({
	Condition.class, MapOperation.class, StringerOperation.class, NumberMathOperation.class, MetaOperation.class, NullOperation.class,
	MATypeOperation.class, NumberOperation.class, StringOperation.class,
	TargetStockInfoOperation.class, ListOperation.class, OperationReferenceOperation.class, TargetStockDelegateOperation.class,
	RequiredShiftWrapperOperation.class})
public abstract class Operation implements Cloneable, Comparable<Operation> {

	private static MyLogger LOGGER = MyLogger.getLogger(Operation.class);

	private String formulae;
	
	//User defined reference
	private String reference;
	
	private String description;

	@XmlElementWrapper(name = "operands")
	@XmlElement(name = "operand")
	private ArrayList<Operation> operands;

	//Pre set value for this
	private Value<?> parameter;
	//Default value hint for this : it is a StringableValue
	private Value<?> defaultValue;

	//This name as operand of the parent
	private String referenceAsOperand;

	//This reference as native operation (should not be changed) //TODO use this reference to sort out reentrant and invalid ops instead of the overridden reference.
	private String operationReference;

	private Boolean isVarArgs = false;

	@XmlElementWrapper(name = "availableOutputSelectors")
	@XmlElement(name = "availableOutputSelector")
	private ArrayList<String> availableOutputSelectors;
	private String outputSelector;

	private Boolean disabled;

	protected Operation() {
		super();
		this.operands = new ArrayList<Operation>();
		this.availableOutputSelectors = new ArrayList<String>();

		this.disabled = false;
	}

	@SuppressWarnings("unchecked")
	public Operation(String reference, String description, ArrayList<? extends Operation> operands) {
		super();
		this.reference = reference;
		this.description = description;
		this.operands = (ArrayList<Operation>) operands;
		this.availableOutputSelectors = new ArrayList<String>();

		this.operationReference = reference;

		this.disabled = false;
	}

	public Operation(String reference, String description) {
		super();
		this.reference = reference;
		this.description = description;
		this.operands = new ArrayList<Operation>();
		this.availableOutputSelectors = new ArrayList<String>();

		this.operationReference = reference;
	}


	public Operation(String reference, String referenceAsOperand, String description, StringableValue defaultValue) {
		super();
		this.reference = reference;
		this.referenceAsOperand = referenceAsOperand;
		this.description = description;
		this.defaultValue = (Value<?>) defaultValue;
		this.operands = new ArrayList<Operation>();
		this.availableOutputSelectors = new ArrayList<String>();

		this.operationReference = reference;

		this.disabled = false;
	}

	public Value<?> run(TargetStockInfo targetStock, String parentCallStack, int parentRequiredStartShift) {

		int thisOperandsRequiredStartShift = parentRequiredStartShift + operandsRequiredStartShift();
		
		int stackDepth = parentCallStack.split("=>").length  + 1;
		String tabs = IntStream.range(0, stackDepth).mapToObj(i -> "\t").reduce("", (r,e) -> r + e);
		String thisCallStack = 
					parentCallStack + ": \n" + 
					"Call Stack: " + tabs + "=> s" + parentRequiredStartShift + ": " + this.shortOutputReference() + " with s" +  operandsRequiredStartShift() + " and d" + stackDepth;
		
		LOGGER.debug(thisCallStack);
		
		int maxDepth = (targetStock.isMainConditionStack(thisCallStack))?7:8;
		boolean isInChart = thisCallStack.split("=>").length <= maxDepth;
		

		Value<?> alreadyCalculated = null;
		try {

			if (parameter != null) {
				return parameter;
			}
			else if ((alreadyCalculated = targetStock.checkAlreadyCalculated(this, this.getOutputSelector(), thisOperandsRequiredStartShift)) != null) {
				return alreadyCalculated;
			}
			else {

				List<Value<?>> operandsOutputs = new ArrayList<Value<?>>();
				for (int i = 0; i < operands.size(); i++) {

					Operation operand = operands.get(i);
					Value<?> output = operand.run(targetStock, thisCallStack, thisOperandsRequiredStartShift);
					gatherCalculatedOutput(targetStock, operand, output, thisOperandsRequiredStartShift, isInChart);
					output = output.filterToParentRequierements(targetStock, thisOperandsRequiredStartShift, this);
					operandsOutputs.add(output);

				}

				if (isInChart) targetStock.populateChartedOutputGroups(this, thisCallStack, operandsOutputs);

				LOGGER.debug("Calculating " + this.getReference() + " = " + this.getFormulae() + ": parent required shift " + parentRequiredStartShift + " and parent+this " + thisOperandsRequiredStartShift);
				Value<?> operationOutput = calculate(targetStock, thisOperandsRequiredStartShift, operandsOutputs);

				if (LOGGER.isDebugEnabled())
					LOGGER.debug("Operation " + this.getReference() + ((this.getOutputSelector() != null)?":" + this.getOutputSelector():"") + " returns " + operationOutput.toString());

				return operationOutput;
			}

		} catch (Exception e) {
			LOGGER.warn("Operation calculation error " + this, e);
			throw new RuntimeException(e);
		}

	}

	private void gatherCalculatedOutput(TargetStockInfo targetStock, Operation operand, Value<?> output, int operandRequiredstartShift, boolean isInChart) {
		
		Integer storedStartShift = operandRequiredstartShift;
		if (operand instanceof CachableOperation) {
			storedStartShift = Math.max(operandRequiredstartShift, ((CachableOperation) operand).operationNaturalShift());
		}

		//We gather only outputs for Numericable outputs or if explicitly Cachable
		if ( output instanceof NumericableMapValue || operand instanceof CachableOperation) {
			targetStock.gatherOneOutput(operand, output, Optional.empty(), storedStartShift, isInChart);
		}
		//We also gather extraneous chartable outputs from conditions.
		if (output instanceof MultiMapValue) {
			gatherAdditionalOutputs(targetStock, operand, (MultiMapValue) output, storedStartShift, isInChart);
		}

	}

	private void gatherAdditionalOutputs(TargetStockInfo targetStock, Operation operand, MultiMapValue operandsOutput, int startShift, boolean isInChart) {

		//add to gathered
		Map<String, NumericableMapValue> extraneousOutputs = operandsOutput.getAdditionalOutputs();
		for (String extOutKey : extraneousOutputs.keySet()) {
			targetStock.gatherOneOutput(operand, extraneousOutputs.get(extOutKey), Optional.of(extOutKey), startShift, isInChart);
		}

	}

	/**
	 * thisStartShift : The start left shift required from the operands calculation of this operation + the added parent requirement accumulated.
	 * Operands are already calculated at this stage. So this is assumed they already have had their calculation start shifted to comply with this operation requirement.
	 * Hence, this is used for no data operations (where operands are not pre-calculated) as it accumulates all the hierarchy of parents start shifts.
	 * Not to be confused with the operandsRequiredStartShift() method which only has this operation required start shift (without the parent).
	 */
	public abstract Value<?> calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs);

	/**
	 * Operation reference as in User Operations list.
	 */
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsVarArgs() {
		return isVarArgs;
	}

	public void setIsVarArgs(Boolean isVarArgs) {
		this.isVarArgs = isVarArgs;
	}

	public List<Operation> getOperands() {
		return operands;
	}

	//Parameter for this operation (this is actually a pre evaluated value for the operand)
	public void setParameter(Value<?> parameter) {
		String operationReference = this.getOperationReference();
		if (!(this instanceof LeafOperation) && operationReference != null) {
			ParameterizedOperationBuilder parameterizedOperationBuilder = SpringContext.getSingleton().getBean(ParameterizedOperationBuilder.class);
			Operation upStreamOperation = parameterizedOperationBuilder.getCurrentOperations().get(operationReference);
			if (upStreamOperation == this) throw new RuntimeException("Please clone before using the setParameter");
		}
		this.parameter = parameter;
	}

	//Must have one param per operand in the same order.
	//Null parameter does nothing and leave the operand for calculation.
	public void setOperandsParams(Value<?>... parameters) {
		for (int i = 0; i < operands.size(); i++) {
			Value<?> parameter = parameters[i];
			if (parameter != null) operands.get(i).setParameter(parameter);
		}
	}

	public Value<?> getParameter() {
		return parameter;
	}

	//This operation operands are either set in the default constructor or unknown before usage.
	//In this setter, either all the operands (with no already set parameters) are overridden or none at all.
	//Hence an empty ArrayList as overriding operands means that no change will be made to this.operands
	//A subset of operand will be rejected if it is not complementary of the parameters pre set to this.
	//The case when this.operands is empty means that the operands and their amount are unknown a priory before usage. In that case, we blindly add all the operands.
	public void setOperands(ArrayList<Operation> overridingOperands) throws IllegalArgumentException {

		//No overridingOprands means we keep this as it is
		if (overridingOperands == null || overridingOperands.isEmpty()) return;
		//No operands for this means that the operands are unknown beforehand. We blindly set
		if (this.operands.isEmpty()) {
			this.operands.addAll(overridingOperands);
			return;
		}
		//Otherwise we fill the gaps.
		ListIterator<Operation> overridingOperandsIt = overridingOperands.listIterator();
		try {
			for (int i = 0; i < this.operands.size(); i++) {
				if (this.operands.get(i).getParameter() == null) {//no param is pre set => we pop an operand in place
					Operation nextOverridingOperand = overridingOperandsIt.next();
					nextOverridingOperand.setIsVarArgs(this.operands.get(i).getIsVarArgs());
					nextOverridingOperand.setReferenceAsOperand(this.operands.get(i).getReference());//we set the overriding operand reference name within this operation
					this.operands.set(i, nextOverridingOperand);
				} 
				//else we skip the operand
			}
		} catch (NoSuchElementException e) {//Not enough operands
			throw new IllegalArgumentException(
					this.getReference() + " rejected " + overridingOperands.stream()
					.map(o -> o.getReference()).reduce((r, ee) -> r + ", " + ee) + " are no enough operands. Expected : " + operands + ", Parameterised : " + parameter);
		}
		//Too many operands
		if (overridingOperandsIt.hasNext()) {
			if (this.operands.get(this.operands.size()-1).getIsVarArgs()) {//var args
				while(overridingOperandsIt.hasNext()) {
					Operation nextOverridingOperand = overridingOperandsIt.next();
					nextOverridingOperand.setIsVarArgs(true);
					this.operands.add(nextOverridingOperand);
				}
			} else {//error
				String msg = this + " rejected " + overridingOperands + " are too many operands. Expected : " + operands + ", Parameterised : " + parameter;
				LOGGER.error(msg);
				throw new IllegalArgumentException(msg);
			}
		}

	}

	public void setOutputSelector(String outputSelector) throws IllegalArgumentException {

		//No overriding of the existing outputSelector means we keep it this as it is
		if (this.outputSelector != null && outputSelector == null) {
			return;
		}

		//Output needed
		if (this.availableOutputSelectors != null && !this.availableOutputSelectors.isEmpty()) {
			//But not provided or wrong
			if (outputSelector == null || !this.availableOutputSelectors.contains(outputSelector)) {
				String msg = this + " rejected " + outputSelector + " is not a valid output Selector. Expected : " + this.availableOutputSelectors;
				LOGGER.error(msg);
				throw new IllegalArgumentException(msg);
			}
			//No Output needed but provided
		} else {
			if (outputSelector != null) {
				String msg = this + " rejected " + outputSelector + ". No output selector is expected for this operation.";
				LOGGER.error(msg);
				throw new IllegalArgumentException(msg);
			}
		}
		this.outputSelector = outputSelector;
	}

	public void setReferenceAsOperand(String referenceAsOperand) {
		this.referenceAsOperand = referenceAsOperand;

	}

	public String getFormulae() {
		return formulae;
	}

	public String getFormulaeTrimmed() {
		return getFormulae().replaceAll("\\s+","");
	}
	
	public String toFormulaeShort() {
		return toFormulaeShort(operands);
	}

	protected String toFormulaeShort(List<Operation> ops) {
		if (ops.isEmpty()) return "";
		return ops.stream().reduce("", (r, e) -> {
			String formulaeShort = e.toFormulaeShort();
			return r + ((formulaeShort.isEmpty())?"":((r.isEmpty())?"":"_") + formulaeShort);
		}, (a, b) -> a + b);
	}
	
	public String toFormulae() {
		if (operands.isEmpty()) {
			if (this.getParameter() != null && this.getParameter() instanceof StringableValue) {
				return ((StringableValue) this.getParameter()).getValueAsString();
			} else {
				return this.getOperationReference();
			}
		}
		String selector = (outputSelector != null)? ":" + outputSelector : "";
		return this.getOperationReference() + selector + "(" + operands.stream().reduce("", (r, e) -> r + ((r.isEmpty())?"":",") + e.toFormulae(), (a, b) -> a + b) + ")";
	}

	public void setFormulae(String formula) {
		this.formulae = formula;
	}

	public String toFullString() {
		ArrayList<Operation> parents = new ArrayList<Operation>();
		return toStringRecursive(parents);
	}

	private String noOperandsToString() {
		return "Operation [reference=" + reference + ", outputSelector=" + outputSelector + ", parameter=" + parameter + ", description=" + description + ", formula=" + formulae+",  Operands=";
	}


	private String toStringRecursive(List<Operation> parents) {

		if (this.formulae != null) parents.add(this);
		String opreandStr = "[";

		String sep ="";
		for (Operation operand : operands) {
			if (operand.getFormulae() != null && parents.contains(operand)) {
				opreandStr = opreandStr + sep + "Operation [loop on User Operation " + operand.getReference() + "]";
			} else {
				opreandStr = opreandStr + sep + operand.toStringRecursive(parents);
			}
			sep = ",";
		}
		return noOperandsToString() + opreandStr + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((formulae == null) ? 0 : formulae.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
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
		Operation other = (Operation) obj;
		if (formulae == null) {
			if (other.formulae != null)
				return false;
		} else if (!formulae.equals(other.formulae))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		return true;
	}

	@Override
	public int compareTo(Operation o) {
		int compareTo = this.reference.compareTo(o.reference);
		if (compareTo == 0) {
			if (this.formulae == null && o.formulae == null) return 0;
			if (this.formulae != null && o.formulae == null) return 1;
			compareTo = this.formulae.compareTo(o.formulae);
		}
		return compareTo;
	}

	@Override
	public Object clone() {
		Operation clone;
		try {
			clone = (Operation) super.clone();
			clone.operands = new ArrayList<Operation>();
			if (this.operands != null) {
				for (Operation operand : this.operands) {
					clone.operands.add((Operation) operand.clone());
				}
			}
			if (this.defaultValue != null) clone.defaultValue = (Value<?>) this.defaultValue.clone();
			if (this.parameter != null) clone.parameter = (Value<?>) this.parameter.clone();

			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}

	public String getOutputSelector() {
		//return outputSelector;
		return (outputSelector != null)?
				outputSelector:
					(getAvailableOutputSelectors() != null && !getAvailableOutputSelectors().isEmpty())?
							getAvailableOutputSelectors().get(0):
								null;
	}

	public Boolean isNative() {
		return formulae == null;
	}

	/**
	 * Name given to this operation as an operand of its upstream operation.
	 */
	public String getReferenceAsOperand() {
		return referenceAsOperand;
	}

	public String name() {
		return reference;
	}

	public Value<?> getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Value<?> defaultValue) {
		this.defaultValue = (Value<?>) defaultValue;
	}

	public String shortOutputReference() {
		return "[" + reference + " (is " + operationReference + ":" + outputSelector + ") as "+ referenceAsOperand + "]";
	}

	public String synoptic() {

		String operandsSynoptic = "";
		String sep = "";
		for (Operation operand : operands) {
			operandsSynoptic = operandsSynoptic + sep + operand.synoptic();
			sep = ", ";
		}
		String outputSelectorSynoptic = "";
		sep = "";
		for (String outSelector : availableOutputSelectors) {
			outputSelectorSynoptic = outputSelectorSynoptic + sep + outSelector;
			sep = ", ";
		}

		String referenceSyno = ParameterizedOperationBuilder.readableCamelCase(((this.referenceAsOperand != null)?this.referenceAsOperand:this.reference));
		String defaultSyno = (defaultValue == null)?"":" (defaults to "+((StringableValue) defaultValue).getValueAsString()+")";
		String outSelectorSyno = (outputSelectorSynoptic.isEmpty())?"":"\nOutput selectors : " + outputSelectorSynoptic;
		String paramsSyno = (operandsSynoptic.isEmpty())?"":"\nOperands : " + operandsSynoptic;
		return referenceSyno + defaultSyno + outSelectorSyno + paramsSyno;

	}

	public String shortSynoptic() {

		String operandsSynoptic = "";
		String sep = "";
		for (Operation operand : operands) {
			operandsSynoptic = operandsSynoptic + sep + operand.synoptic();
			sep = ", ";
		}

		String outputSelectorSynoptic = "";
		sep = "";
		for (String outSelector : availableOutputSelectors) {
			outputSelectorSynoptic = outputSelectorSynoptic + sep + outSelector;
			sep = " or ";
		}
		String referenceSyno = (this.referenceAsOperand != null)?this.referenceAsOperand:this.reference;
		String defaultSyno = (defaultValue == null)?"":" (defaults to " + ((StringableValue) defaultValue).getValueAsString() + ")";
		String outSelectorSyno = (outputSelectorSynoptic.isEmpty())?"":":"+outputSelectorSynoptic;
		String paramsSyno = (operandsSynoptic.isEmpty())?"": "("+operandsSynoptic+")";
		return referenceSyno + outSelectorSyno + paramsSyno + defaultSyno;
	}


	@XmlTransient
	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	@XmlTransient
	public List<String> getAvailableOutputSelectors() {
		return availableOutputSelectors;
	}

	protected void setAvailableOutputSelectors(ArrayList<String> availableOutputSelectors) {
		this.availableOutputSelectors = availableOutputSelectors;
	}

	/**
	 * Upstream calculated root operation name
	 */
	public String getOperationReference() {
		return operationReference;
	}

	@Override
	public String toString() {
		return "Operation [ reference=" + reference + ", formula=" + ((formulae != null)?formulae.replaceAll("\n", " "):"null") + 
				", description=" + description + ", referenceAsOperand=" + referenceAsOperand + ", operationReference=" + operationReference + 
				", availableOutputSelectors=" + availableOutputSelectors + ", outputSelector="+ outputSelector + ", disabled=" + disabled + "]";
	}

	public void replaceOperand(int i, Operation replacementOp) {
		this.operands.set(i, replacementOp);
	}

	/**
	 * The start shift left required, from the operands calculations outputs, by this operation, for this operation to provide an output at start date.
	 * Basically the amount of data necessary from the operands for this to yield at least one output at start date.
	 * This is a number of data points (ie open days), not number of calendar days.
	 */
	public abstract int operandsRequiredStartShift();
	
	/**
	 * This gives how far the leafs operands will have to shift
	 */
	public int operandsRequiredStartShiftRecursive() {
		if (operands.isEmpty()) return 0;
		return operands.stream().reduce(0, (max, operand) -> {
			int thisOperandsShift = operand.operandsRequiredStartShift();
			return Math.max(max, thisOperandsShift + operand.operandsRequiredStartShiftRecursive());
		}, (a, b) -> Math.max(a, b));
	}

	//Children of this operation not idempotent would make this operation not idempotent. It will return true by default.
	//This can be overridden by making this operation itself not idempotent
	//Non idempotent operation will invalidate any previous calculation.
	public Boolean isIdemPotent() {
		if (operands.isEmpty()) return true;
		return operands.stream().reduce(true, (r, e) -> r && e.isIdemPotent(), (a, b) -> a && b);
	}
	
	public boolean isDateSensitive() {
		if (operands.isEmpty()) return false;
		return operands.stream().reduce(false, (r, e) -> r || e.isDateSensitive(), (a, b) -> a || b);
	}

	public abstract void invalidateOperation(String analysisName, Optional<Stock> stock, Object... addtionalParams);

	public void invalidateAllNonIdempotentOperands(String analysisName, Optional<Stock> stock) {
		LOGGER.debug("Checking " + getReference() + " for invalidation.");
		if (!this.isIdemPotent()) {
			LOGGER.info("Invalidating " + getReference() + " for " + analysisName + " and " + stock);
			this.invalidateOperation(analysisName, stock);
		}
		operands.stream().forEach(
				o -> {
					if (!o.getOperands().isEmpty()) {
						o.invalidateAllNonIdempotentOperands(analysisName, stock);
					}
				});
	}
	
	public Set<QuotationDataType> getRequieredStockData() {
		Set<QuotationDataType> quotationDataTypes = new HashSet<>();
		if (this instanceof StockOperation) {
			quotationDataTypes.add(QuotationDataType.valueOf(this.getOutputSelector().toUpperCase()));
		}
		if (!operands.isEmpty()) {
			quotationDataTypes.addAll(operands.stream().flatMap(o -> o.getRequieredStockData().stream()).collect(Collectors.toList()));
		}
		return quotationDataTypes;
	}
	
	public void interrupt() throws Exception {
		operands.stream().forEach(
				o -> {
					if (!o.getOperands().isEmpty()) {
						try {
							o.interrupt();
						} catch (Exception e) {
							LOGGER.error(e, e);
						}
					}
				});
	}

}
