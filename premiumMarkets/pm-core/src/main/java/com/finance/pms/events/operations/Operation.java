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
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.conditional.Condition;
import com.finance.pms.events.operations.nativeops.MATypeOperation;
import com.finance.pms.events.operations.nativeops.MapOperation;
import com.finance.pms.events.operations.nativeops.MultiMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.StockOperation;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.parameterized.ParameterizedOperationBuilder;

/**
 * !!Operations must be state less across calculations as reused!! 
 * Operands : resolved at runtime.
 * Parameters : preset operands already resolved.
 **/
@XmlType(propOrder = { "reference", "referenceAsOperand", "description", "formulae", "parameter", "defaultValue", "operands", "availableOutputSelectors", "outputSelector", "isVarArgs"} )
@XmlSeeAlso({Condition.class, MapOperation.class, MATypeOperation.class, NumberOperation.class, StringOperation.class})
public abstract class Operation implements Cloneable, Comparable<Operation> {

	private static MyLogger LOGGER = MyLogger.getLogger(Operation.class);

	private String formulae;

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

	public Value<?> run(TargetStockInfo targetStock, String parentCallStack, int parentStartShift) {

		int thisStartShift = parentStartShift + operationStartDateShift();
		LOGGER.debug("Start shift granted for " + this.getReference() + " = " + this.getFormulae() + ": parent " + parentStartShift + " and parent+this " + thisStartShift);
		String thisCallStack = parentCallStack + "=>" + this.shortOutputReference();
		LOGGER.debug("Call stack : " + thisCallStack);

		Value<?> alreadyCalculated = null;
		try {

			if (parameter != null) {
				return parameter;
			}
			else if ((alreadyCalculated = targetStock.checkAlreadyCalculated(this, this.getOutputSelector(), thisStartShift)) != null) {
				return alreadyCalculated;
			}
			else {

				List<Value<?>> operandsOutputs = new ArrayList<Value<?>>();
				for (int i = 0; i < operands.size(); i++) {

					Operation operand = operands.get(i);
					Value<?> output = operand.run(targetStock, thisCallStack, thisStartShift);
					operandsOutputs.add(output);

					gatherCalculatedOutput(targetStock, operand, output, thisStartShift);

				}

				targetStock.populateChartedOutputGroups(this, thisCallStack, operandsOutputs);

				LOGGER.debug("Calculating " + this.getReference() + " = " + this.getFormulae() + ": parent " + parentStartShift + " and parent+this " + thisStartShift);
				Value<?> operationOutput = calculate(targetStock, thisStartShift, operandsOutputs);

				if (LOGGER.isDebugEnabled())
					LOGGER.debug("Operation " + this.getReference() + ((this.getOutputSelector() != null)?":" + this.getOutputSelector():"") + " returns " + operationOutput.toString());

				return operationOutput;
			}

		} catch (Exception e) {
			LOGGER.warn("Operation calculation error " + this, e);
			throw new RuntimeException(e);
		}

	}

	private void gatherCalculatedOutput(TargetStockInfo targetStock, Operation operand, Value<?> output, int startShift) {

		//We gather only outputs for StockOperation and User formulas.
		if ( (output instanceof NumericableMapValue && (operand.getFormulae() != null)) || operand instanceof StockOperation) {
			targetStock.gatherOneOutput(operand, output, Optional.empty(), startShift);
		}
		//We also gather extraneous chartable outputs from conditions.
		if (output instanceof MultiMapValue) {
			gatherAdditionalOutputs(targetStock, operand, (MultiMapValue) output, startShift);
		}

	}

	private void gatherAdditionalOutputs(TargetStockInfo targetStock, Operation operand, MultiMapValue operandsOutput, int startShit) {

		//add to gathered
		Map<String, NumericableMapValue> extraneousOutputs = operandsOutput.getAdditionalOutputs();
		for (String extOutKey : extraneousOutputs.keySet()) {
			targetStock.gatherOneOutput(operand, extraneousOutputs.get(extOutKey), Optional.of(extOutKey), startShit);
		}

	}

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
		this.parameter = parameter;
	}

	//Must have one param per operand in the same order. Null parameter does nothing
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
					this.getReference() + " rejected " + overridingOperands.stream().map(o -> o.getReference()).reduce((r, ee) -> r + ", " + ee) +
					" are no enough operands. Expected : "+operands+", Parameterised : "+parameter);
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
				throw new IllegalArgumentException(this + " rejected " + overridingOperands+" are too many operands. Expected : "+operands+", Parameterised : "+parameter);
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
				throw new IllegalArgumentException(this + " rejected " + outputSelector + " is not a valid output Selector. Expected : " + this.availableOutputSelectors);
			}
			//No Output needed but provided
		} else {
			if (outputSelector != null) {
				throw new IllegalArgumentException(this + " rejected " + outputSelector + ". No output selector is expected for this operation.");
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
	 * Name given to this operation as a parameter of higher order operation.
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
			operandsSynoptic = operandsSynoptic + sep+ operand.synoptic();
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
		String outSelectorSyno = (outputSelectorSynoptic.isEmpty())?"":"\nOutput selectors : "+outputSelectorSynoptic;
		String paramsSyno = (operandsSynoptic.isEmpty())?"":"\nOperands : "+operandsSynoptic;
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
	 * Upstream calculated operation name
	 */
	public String getOperationReference() {
		return operationReference;
	}

	@Override
	public String toString() {
		return "Operation [ reference=" + reference + ", formula=" + ((formulae != null)?formulae.replaceAll("\n", " "):"null") + ", description=" + description + ", referenceAsOperand=" + referenceAsOperand+ 
				", operationReference=" + operationReference + ", availableOutputSelectors=" + availableOutputSelectors + ", outputSelector="+ outputSelector + ", disabled=" + disabled + "]";
	}

	public void replaceOperand(int i, Operation replacementOp) {
		this.operands.set(i, replacementOp);
	}

	public abstract int operationStartDateShift();

	//Children of this operation not idempotent would make this operation not idempotent. It will return true by default.
	//This can be overridden by making this operation itself not idempotent
	//Non idempotent operation will invalidate any previous calculation.
	public Boolean isIdemPotent() {
		if (operands.isEmpty()) return true;
		return operands.stream().reduce(true, (r, e) -> r && e.isIdemPotent(), (a, b) -> a && b);
	}

	public abstract void invalidateOperation(String analysisName, Optional<Stock> stock);

	public void invalidateAllNonIdempotentOperands(String analysisName, Optional<Stock> stock) {
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

}
