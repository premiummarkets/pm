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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.finance.pms.MainPMScmd;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.calculation.parametrizedindicators.OutputReference;
import com.finance.pms.events.operations.conditional.Condition;
import com.finance.pms.events.operations.conditional.EventInfoOpsCompoOperation;
import com.finance.pms.events.operations.nativeops.CachableOperation;
import com.finance.pms.events.operations.nativeops.LeafOperation;
import com.finance.pms.events.operations.nativeops.ListOperation;
import com.finance.pms.events.operations.nativeops.MATypeOperation;
import com.finance.pms.events.operations.nativeops.MapOperation;
import com.finance.pms.events.operations.nativeops.MultiMapValue;
import com.finance.pms.events.operations.nativeops.NamedListOperation;
import com.finance.pms.events.operations.nativeops.NullOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumbererOperation;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.OperationReferenceOperation;
import com.finance.pms.events.operations.nativeops.StockOperation;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringableValue;
import com.finance.pms.events.operations.nativeops.StringerOperation;
import com.finance.pms.events.operations.nativeops.TargetStockInfoOperation;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.operations.nativeops.flow.AndOperation;
import com.finance.pms.events.operations.nativeops.flow.EnvOperation;
import com.finance.pms.events.operations.nativeops.flow.GetOperation;
import com.finance.pms.events.operations.nativeops.flow.IfOperation;
import com.finance.pms.events.operations.nativeops.flow.LetOperation;
import com.finance.pms.events.operations.nativeops.flow.LogOperation;
import com.finance.pms.events.operations.nativeops.flow.MetaOperation;
import com.finance.pms.events.operations.nativeops.flow.OrOperation;
import com.finance.pms.events.operations.nativeops.flow.TargetStockDelegateOperation;
import com.finance.pms.events.operations.nativeops.trans.RequiredShiftWrapperOperation;
import com.finance.pms.events.operations.parameterized.ParameterizedOperationBuilder;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.scoring.TunedConf;
import com.finance.pms.events.scoring.TunedConfMgr;

/**
 * !!Operations must be state less across calculations as reused!! 
 * Operands: resolved at runtime.
 * Parameters: preset operands already resolved.
 **/
@XmlType(propOrder = { "reference", "referenceAsOperand", "description", "formulae", "parameter", "defaultValue", "operands", "availableOutputSelectors", "outputSelector", "isVarArgs","runInSequence"} )
@XmlSeeAlso({
	Condition.class, MapOperation.class, StringerOperation.class, NumbererOperation.class, MetaOperation.class, NullOperation.class, IfOperation.class,
	MATypeOperation.class, NumberOperation.class, StringOperation.class,
	TargetStockInfoOperation.class, ListOperation.class, NamedListOperation.class, OperationReferenceOperation.class, TargetStockDelegateOperation.class,
	LogOperation.class,
	RequiredShiftWrapperOperation.class, LetOperation.class, GetOperation.class, EnvOperation.class, AndOperation.class, OrOperation.class})
public abstract class Operation implements Cloneable, Comparable<Operation> {

	private static MyLogger LOGGER = MyLogger.getLogger(Operation.class);
	
	protected final Boolean isDisplay;

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
	//FIXME final or is it need for de-serialisation?
	private String operationReference;

	private Boolean isVarArgs = false;
	private Boolean runInSequence = false;

	@XmlElementWrapper(name = "availableOutputSelectors")
	@XmlElement(name = "availableOutputSelector")
	private ArrayList<String> availableOutputSelectors;
	private String outputSelector;

	private Boolean disabled;

	protected Operation() {
		super();
		this.isDisplay = Boolean.valueOf(MainPMScmd.getMyPrefs().get("chart.display", "true"));
		this.operands = new ArrayList<>();
		this.availableOutputSelectors = new ArrayList<String>();
		this.disabled = false;
		
	}

	@SuppressWarnings("unchecked")
	public Operation(String reference, String description, ArrayList<? extends Operation> operands) {
		super();
		this.isDisplay = Boolean.valueOf(MainPMScmd.getMyPrefs().get("chart.display", "true"));
		
		this.reference = reference;
		this.description = description;
		
		this.operands = (ArrayList<Operation>) operands;
		this.availableOutputSelectors = new ArrayList<String>();
		this.disabled = false;
		
		this.operationReference = reference;
	}

	public Operation(String reference, String description) {
		super();
		this.isDisplay = Boolean.valueOf(MainPMScmd.getMyPrefs().get("chart.display", "true"));
		
		this.reference = reference;
		this.description = description;
		
		this.operands = new ArrayList<>();
		this.availableOutputSelectors = new ArrayList<String>();

		this.operationReference = reference;
		
	}


	public Operation(String reference, String referenceAsOperand, String description, StringableValue defaultValue) {
		super();
		this.isDisplay = Boolean.valueOf(MainPMScmd.getMyPrefs().get("chart.display", "true"));
		
		this.reference = reference;
		this.referenceAsOperand = referenceAsOperand;
		this.description = description;
		this.defaultValue = (Value<?>) defaultValue;
		
		this.operands = new ArrayList<>();
		this.availableOutputSelectors = new ArrayList<String>();
		this.disabled = false;

		this.operationReference = reference;
		
	}

	/**
	 * 
	 * @param targetStock
	 * @param parentCallStack
	 * @param thisOutputRequiredStartShiftByParent: (this operation output shift (== parent input shift) + this operation required operands input shift for 0 lag) (== total operands output shift)
	 * @return
	 */
	public Value<?> run(TargetStockInfo targetStock, List<StackElement> parentCallStack, int thisOutputRequiredStartShiftByParent) {
		
		List<StackElement> thisCallStack = addThisToStack(parentCallStack, thisOutputRequiredStartShiftByParent, targetStock);
		//if (LOGGER.isDebugEnabled()) LOGGER.debug(thisCallStack);
		//LOGGER.info(thisCallStack);
		boolean isUserOpCall = !this.isNative() && !getUserOperationReference(thisCallStack).equals("UnknownUserOpeartion");
		if (isUserOpCall) {
			LOGGER.info(
					"Checking Running: (" + targetStock.getStock().getSymbol() + "): " + this.shortOutputReference(thisCallStack) + 
					". Caller: " + StackElement.toShortString(parentCallStack)
					);
		}
				
		//Literals
		//if (needsReset) this.parameter = null;
		if (parameter != null) {
			return parameter;
		}
		
		//Non Literals
		final AtomicBoolean stopCalcOnErr = new AtomicBoolean(false);
		final AtomicBoolean stopCalcOnCond = new AtomicBoolean(false);
		try {
			
			//if (needsReset) targetStock.removeCalculated(this, this.getOutputSelector());
			Value<?> alreadyCalculated = null;
			if ((alreadyCalculated = targetStock.checkAlreadyCalculated(this, this.getOutputSelector(), thisOutputRequiredStartShiftByParent)) != null) {
				
				return alreadyCalculated;
				
			} else {
				
				if (isUserOpCall) {
					LOGGER.info(
							"Started Running: (" + targetStock.getStock().getSymbol() + "): " + this.shortOutputReference(thisCallStack) + 
							". Caller: " + StackElement.toShortString(parentCallStack)
							);
				}
				
				//Invalidation check //TODO instead of this, keep a state of operations after they ran (in json or db) and invalidate up front using the state.
				if (isUserOpCall) {
					Optional<TunedConf> tunedConfOpt = TunedConfMgr.getInstance().loadUniqueNoRetuneConfig(targetStock.getStock(), targetStock.getAnalysisName(), targetStock.getEventInfoOpsCompoOperation());
					Boolean needsReset = tunedConfOpt.map(t -> t.wasResetOrIsNew()).orElse(false); //XXX unnecessary clean up when new
					if (needsReset) this.invalidateAllForciblyOperands(targetStock.getAnalysisName(), targetStock,  Optional.of(getUserOperationReference(thisCallStack)));
				}
				
				//Data non sensitive operands
				//List<Optional<Value<?>>> nonDataSensitivesOutputs = runNonDataSensitives(targetStock, thisCallStack, operands);

				//Operands chart Cache
				int maxDepth = (targetStock.isMainConditionStack(thisCallStack))?7:8;
				boolean isInChart = isDisplay && targetStock.getEventInfoOpsCompoOperation().isKeepEvents() && thisCallStack.size() <= maxDepth;
				
				//Operands Calculation
				int nbOperands = operands.size();
				List<Value<?>> operandsOutputs = new ArrayList<>(Collections.nCopies(nbOperands, (Value<?>) null));
				//If start > end, non literals operands will be set empty 
				//Effective calculation
				int thisOperationOperandsStartShift = operandsRequiredStartShift(targetStock, thisOutputRequiredStartShiftByParent);
				int thisInputOperandsRequiredShiftFromThis = thisOutputRequiredStartShiftByParent + thisOperationOperandsStartShift;
				final Boolean literalsOnly = targetStock.getStartDate(thisInputOperandsRequiredShiftFromThis).compareTo(targetStock.getEndDate()) > 0;
				
				List<Future<Value<?>>> futures = new ArrayList<>(Collections.nCopies(nbOperands, (Future<Value<?>>) null));
				ExecutorService executor = CalculateThreadExecutor.getJoinForkExecutorInstance(); //Executors.newFixedThreadPool(2); //Test
				for (int i = 0; i < nbOperands; i++) {
					
					final Operation operand = operands.get(i);
					//Optional<Value<?>> nonDataSensistiveOperandOuput = nonDataSensitivesOutputs.get(i);
					if (operand.getParameter() == null) {//if (!nonDataSensistiveOperandOuput.isPresent()) {
						Callable<Value<?>> callable = new Callable<Value<?>>() {
							@Override
							public Value<?> call() throws Exception {
								
								MyLogger.threadLocal.set(targetStock.getStock().getSymbol());
								
								if ((literalsOnly && !operand.isDataShiftSensitive()) || !literalsOnly) {
									OutputReference myOutputReferenceUnfinished = new OutputReference(operand, operand.getOutputSelector());
									try {
										OutputReference theirFutureIsDone = null;
										while (!stopCalcOnErr.get() && !stopCalcOnCond.get() && !(theirFutureIsDone != null && theirFutureIsDone.getHasFailed())) {
											synchronized (targetStock) {
												theirFutureIsDone = targetStock.getOutputCalculationFuture(myOutputReferenceUnfinished);
												if (theirFutureIsDone == null) {
													targetStock.putOutputCalculationFuture(myOutputReferenceUnfinished);
													break;
												}
											}
											Thread.sleep(10);
										};
										if (!stopCalcOnErr.get() && !stopCalcOnCond.get() && !(theirFutureIsDone != null && theirFutureIsDone.getHasFailed())) {
											Value<?> output = operand.run(targetStock, thisCallStack, thisInputOperandsRequiredShiftFromThis);
											output = output.filterToParentRequirements(targetStock, thisInputOperandsRequiredShiftFromThis, Operation.this);
											gatherCalculatedOutput(targetStock, operand, output, thisInputOperandsRequiredShiftFromThis, isInChart);
											if (!isForbidThisParameterValue()) operand.setParameter(output);
											stopCalcOnCond.set(stopOperandsCalculationsOnCondition(targetStock, output));
											return output;
										} else {
											return operand.emptyValue();
										}
									} catch (StackException e) {
										myOutputReferenceUnfinished.setHasFailed(true);
										stopCalcOnErr.set(stopOperandsCalculationsOnError());
										throw e;
									} catch (Exception e) {
										myOutputReferenceUnfinished.setHasFailed(true);
										stopCalcOnErr.set(stopOperandsCalculationsOnError());
										throw new StackException("Failing operand (" + targetStock.getStock().getSymbol() + ")" + ": " + operand.toFormulae(targetStock), thisCallStack, e);
									} finally {
										synchronized (targetStock) {
											if (!myOutputReferenceUnfinished.getHasFailed()) targetStock.removeOutputCalculationFuture(myOutputReferenceUnfinished);
										}
									}
								} else {
									return operand.emptyValue();
								}
								
							}

						};
						if (!operand.getRunInSequence()) {
							Future<Value<?>> iterationFuture = executor.submit(callable);
							futures.set(i, iterationFuture);
						} else {
							try {
								Value<?> callOutput = callable.call();
								//stopCalcOnCond.set(stopOperandsCalculationsOnCondition(targetStock, callOutput));
								if (stopCalcOnCond.get()) {
									throw new RuntimeException("Stop on condition: " + callOutput);
								} else {
									operandsOutputs.set(i, callOutput);
								}
							} catch (Exception e) {
								if (stopCalcOnErr.get() || stopCalcOnCond.get()) {
									throw new RuntimeException(e);
								} else {
									operandsOutputs.set(i, operand.emptyValue());
								}
							}
						}
						//operandsOutputs.set(i, callable.call()); //Test
						
					} else {
						operandsOutputs.set(i, operand.getParameter());
					}
					
				}
				
				IntStream.range(0, nbOperands).forEach(j -> {
					try {
						Future<Value<?>> future = futures.get(j);
						if (future != null) {
							Value<?> output = future.get();
							if (stopCalcOnCond.get()) {
								throw new RuntimeException("Stop on condition: " + output);
							} else {
								operandsOutputs.set(j, output);
							}
						}
					} catch (Exception e) {
						if (stopCalcOnErr.get() || stopCalcOnCond.get()) {
							throw new RuntimeException(e);
						} else {
							operandsOutputs.set(j, getOperands().get(j).emptyValue());
						}
					}
				});
				
				//Operands chart cache
				if (isInChart) {
					synchronized (targetStock.getChartedOutputGroupsAsync()) {
						try {
							targetStock.populateChartedOutputGroups(this, calculationStatus(targetStock, thisCallStack), thisCallStack, operandsOutputs);
						} catch (NoCalculationAvailable e) {
							LOGGER.warn("Can't update chart cache. Some calculations may have failed: " + e);
						}
					}
				}
				
				//This Calculation
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Calculating " + this.getReference() + " = " + this.getFormulae() + ": parent required shift (this output required) " + thisOutputRequiredStartShiftByParent + " and parent+this (this input required)" + thisInputOperandsRequiredShiftFromThis);
				
				Value<?> operationOutput = calculate(targetStock, thisCallStack, thisOutputRequiredStartShiftByParent, thisInputOperandsRequiredShiftFromThis, operandsOutputs);
				
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Calculation results " + this.getReference() + ((this.getOutputSelector() != null)?": " + this.getOutputSelector():"") + " returns " + operationOutput.toString());
				
				
				//Log
				
////				//DEBUG
//				if (operationOutput != null && operationOutput instanceof NumericableMapValue) {
//					Date parse = new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-26");
//					SortedMap<Date, Double> value = ((NumericableMapValue) operationOutput).getValue(targetStock);
//					//if (!value.containsKey(parse) || value.get(parse) == null || Double.isNaN(value.get(parse))) {
//					if (value.containsKey(parse)) {
//						//throw new RuntimeException("Missing key or value at " + parse + " for " + this + ": " + value.get(parse));
//						//LOGGER.error("Missing key or value at " + parse + " for " + this + " and " + targetStock + " with " + this.toFormulae() + ": " + value.get(parse));
//						LOGGER.error("Added NaN key at " + parse + " for " + this + " and " + targetStock + " with " + this.toFormulae() + ": " + value.get(parse));
//					}
//				}
////				//DEBUG
				
				if (isUserOpCall) {
						//&& (operationOutput instanceof NumericableMapValue || operationOutput instanceof MultiMapValue || this instanceof CachableOperation)) {
					LOGGER.info(
							"Done Running: (" + targetStock.getStock().getSymbol() + "): " + this.shortOutputReference(thisCallStack) + 
							". Caller: " + StackElement.toShortString(parentCallStack)
							);
				}
				
				//Log end
				
				return operationOutput;
			}

		} catch (Exception e) {
			if (isUserOpCall) {
				LOGGER.info(
						"Aborted Running: (" + targetStock.getStock().getSymbol() + "): " + this.shortOutputReference(thisCallStack) + 
						". Caller: " + StackElement.toShortString(parentCallStack)
						);
			}
			throw new StackException(this.getReference(), e);
		}

	}
	
	protected boolean stopOperandsCalculationsOnCondition(TargetStockInfo stockInfo, Value<?> call) {
		return false;
	}

	protected Boolean stopOperandsCalculationsOnError() {
		return true;
	}

	//If isDataShiftSensitive == true, isForbidThisParameterValue will be ignored
	private List<Optional<Value<?>>> runNonDataSensitives(TargetStockInfo targetStock, List<StackElement> parentCallStack, List<Operation> someOperands) {
		List<Optional<Value<?>>> outputs = new ArrayList<>();
		for (int i = 0; i < someOperands.size(); i++) {
			Operation operand = someOperands.get(i);
			boolean isDataShiftSensitive = operand.isDataShiftSensitive();
			if (!isDataShiftSensitive && targetStock != null) {
				Value<?> output = operand.run(targetStock, parentCallStack, 0);
				outputs.add(Optional.of(output));
				if (!isForbidThisParameterValue()) operand.setParameter(output);
			} else {
				outputs.add(Optional.empty());
			}
		}
		if (!this.isNative() && !getUserOperationReference(parentCallStack).equals("UnknownUserOpeartion")) {
			LOGGER.info(
					"Done Running non sensitive: (" + targetStock.getStock().getSymbol() + "): " + this.shortOutputReference(parentCallStack) + 
					". Caller: " + StackElement.toShortString(parentCallStack)
					);
		}
		return outputs;
	}

	/**
	 * 
	 * @param parentCallStack
	 * @param targetStock
	 * @param parentRequiredStartShift: this operation output shift (== parent input shift)
	 * @param operationOperandsRequiredStartShift: this operation required operands input shift for 0 lag
	 * @return
	 * @throws NotEnoughDataException 
	 */
	protected List<StackElement> addThisToStack(List<StackElement> parentCallStack, int parentRequiredStartShift, TargetStockInfo targetStock) {
		int stackDepth = (parentCallStack.size() > 0)? parentCallStack.get(parentCallStack.size()-1).getStackDepth() + 1 : 0;
		ArrayList<StackElement> branchCallStack = new ArrayList<>(parentCallStack);
		branchCallStack.add(new StackElement(
				stackDepth, targetStock.getStock().getSymbol(), 
				parentRequiredStartShift, targetStock.getStartDate(parentRequiredStartShift), targetStock.getEndDate(), 
				this.reference, this.operationReference, this.outputSelector, this.referenceAsOperand));
		return branchCallStack;
	}
	
	public  List<StackElement> newCallerStack(TargetStockInfo targetStock) {
		return this.addThisToStack(new ArrayList<>(), 0, targetStock);
	}

	private void gatherCalculatedOutput(TargetStockInfo targetStock, Operation operand, Value<?> output, int operandRequiredstartShift, boolean isInChart) {
		
		Integer storedStartShift = operandRequiredstartShift;
		if (operand instanceof CachableOperation) {
			storedStartShift = Math.max(operandRequiredstartShift, ((CachableOperation) operand).operationNaturalShift());
		}

		//We gather only outputs for Numericable outputs or if explicitly Cachable
		if (output instanceof NumericableMapValue || operand instanceof CachableOperation) {
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
	 * thisInputOperandsRequiredShiftFromThis : The start left shift required from the operands calculation of this operation + the added parents requirement accumulated (thisOutputRequiredStartShiftByParent).
	 * Operands are already calculated at this stage. So this is assumed they already have had their calculation start shifted to comply with this operation requirement.
	 * Hence, this is used for no data operations (where operands are not pre-calculated) as it accumulates all the hierarchy of parents start shifts.
	 * Not to be confused with the operandsRequiredStartShift() method which only has this operation required start shift from its operands (without the parent).
	 * So we can say: thisInputOperandsRequiredShiftFromThis = thisOutputRequiredStartShiftByParent + operandsRequiredStartShift() ??
	 * @param parentCallStack TODO
	 * @param parentRequiredStartShift TODO
	 */
	public abstract Value<?> calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisOutputRequiredStartShiftByParent, int thisInputOperandsRequiredShiftFromThis, @SuppressWarnings("rawtypes") List<? extends Value> inputs);
	
	public abstract Value<?> emptyValue();
	
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

	/**
	 * Parameter for this operation (this is actually a pre evaluated value for this)
	 * TODO?? set the parameter in conjunction (or in place?) of the targetStock output cache?? This would mean the parameter is set not only for the runNonDataSensitives
	 * @param parameter
	 */
	public void setParameter(Value<?> parameter) {
		String operationReference = this.getOperationReference();
		
		//Clone requirement Sanity check
		if (!(this instanceof LeafOperation) && operationReference != null) {
			ParameterizedOperationBuilder parameterizedOperationBuilder = SpringContext.getSingleton().getBean(ParameterizedOperationBuilder.class);
			Operation upStreamOperation = parameterizedOperationBuilder.getCurrentOperations().get(operationReference);
			if (upStreamOperation == this) throw new RuntimeException("Please clone before using the setParameter");
		}
		//
		
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
	
	public Optional<Value<?>> getOrRunParameter(TargetStockInfo targetStock) {
		try {
			if (parameter == null) {
				if (targetStock == null) throw new Exception("No parameter is set and can't be calculated as targetStock is null");
				List<Optional<Value<?>>> outputs = runNonDataSensitives(targetStock,  newCallerStack(targetStock), Arrays.asList(this));
				return outputs.get(0);
			}
		} catch (Exception e) {
			LOGGER.warn("getOrRunParameter is returning an empty parameter for " + this.getReference() + ": " + e);
			return Optional.empty();
		}
		return Optional.of(parameter);
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
					.map(o -> o.getReference()).reduce((r, ee) -> r + ", " + ee) + " are not enough operands. Expected: " + operands + ", Parameterised: " + parameter);
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
	
	public String toFormulaeShort(TargetStockInfo targetStock) {
		return toFormulaeShort(targetStock, operands);
	}

	protected String toFormulaeShort(TargetStockInfo targetStock, List<Operation> ops) {
		if (ops.isEmpty()) return "";
		return ops.stream().reduce("", (r, e) -> {
			String formulaeShort = e.toFormulaeShort(targetStock);
			return r + ((formulaeShort.isEmpty())?"":((r.isEmpty())?"":"_") + formulaeShort);
		}, (a, b) -> a + b);
	}
	
	public String toFormulae(TargetStockInfo targetStock) {

		Optional<Value<?>> orRunParameter = this.getOrRunParameter(targetStock);
		if (orRunParameter.isPresent() && orRunParameter.get() instanceof StringableValue) {
			return ((StringableValue) orRunParameter.get()).getValueAsString();
		}

		String selector = (outputSelector != null)? ":" + outputSelector : "";
		return this.getOperationReference() + selector + "(" + operands.stream().reduce("", (r, e) -> r + ((r.isEmpty())?"":",") + e.toFormulae(targetStock), (a, b) -> a + b) + ")";
	}
	
	public String toFormulaeDevelopped() {

		if (this.getParameter() != null && this.getParameter() instanceof StringableValue) {
			return ((StringableValue) this.getParameter()).getValueAsString();
		}

		String selector = (outputSelector != null)? ":" + outputSelector : "";
		return this.getOperationReference() + selector + "(" + operands.stream().reduce("", (r, e) -> r + ((r.isEmpty())?"":",") + e.toFormulaeDevelopped(), (a, b) -> a + b) + ")";
	}

	public void setFormulae(String formula) {
		this.formulae = formula;
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
			//if (this.parameter != null) clone.parameter = (Value<?>) this.parameter.clone();
			if (this.parameter != null && this instanceof LeafOperation) {
				clone.parameter = (Value<?>) this.parameter.clone();
			} else {
				clone.parameter = null; //We do not clone the parameter as it is a new operation clone and may need recalculation.
			}
			
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

	public String shortOutputReference(List<StackElement> stack) {
		return "[" + getUserOperationReference(stack) + " (is " + operationReference + ":" + outputSelector + ") as " + referenceAsOperand + "]";
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
	 * Effective calculated PM operation name
	 */
	public String getOperationReference() {
		return operationReference;
	}
	
	public Boolean getRunInSequence() {
		return runInSequence;
	}
	
	public void setRunInSequence(Boolean runInSequence) {
		this.runInSequence = runInSequence;
	}
	
	public String getUserOperationReference(List<StackElement> stack) {
		Optional<StackElement> lastUserOp = stack.stream()
			.filter(se -> se.isUserOp())
			.reduce((a,s) -> s);
		return lastUserOp.map(luo -> luo.getOpReference()).orElse("UnknownUserOpeartion");
	}

	@Override
	public String toString() {
		return "Operation [ reference=" + reference + ", formula=" + ((formulae != null)?formulae.replaceAll("\n", " "):"null") + 
				", description=" + description + ", referenceAsOperand=" + referenceAsOperand + ", operationReference=" + operationReference + 
				", parameter=" + parameter +
				", availableOutputSelectors=" + availableOutputSelectors + ", outputSelector=" + outputSelector + ", disabled=" + disabled + "]";
	}

	public void replaceOperand(int i, Operation replacementOp) {
		this.operands.set(i, replacementOp);
	}

	/**
	 * The start shift left required, from the operands calculations outputs, by this operation, for this operation to provide an output at start date.
	 * Basically the amount of data necessary from the operands (input of this) for this to yield at least one output at start date.
	 * This is a number of data points (ie open days), not number of calendar days.
	 * @param targetStock TODO
	 * @param thisParentStartShift TODO
	 */
	public abstract int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift);
	
	/**
	 * This gives how far the leafs operands will have to shift
	 */
	public int operandsRequiredStartShiftRecursive(TargetStockInfo targetStock, int thisOperationStartShift) {
		if (operands.isEmpty()) return 0;
		return operands.stream().reduce(0, (max, operand) -> {
			int operandShift = operand.operandsRequiredStartShift(targetStock, thisOperationStartShift);
			int operandOperandsShift = operand.operandsRequiredStartShiftRecursive(targetStock, operandShift);
			if (operandShift != 0) 
				LOGGER.info(operand.getReference() + " operand shift: " + operandShift + ", operands' operand shift:" + operandOperandsShift + " = " + (operandShift + operandOperandsShift));
			return Math.max(max, operandShift + operandOperandsShift);
		}, (a, b) -> Math.max(a, b));
	}

	/**
	 * For non idempotent operations
	 * Non idempotent operation will ignore previous calculations found in the db and redo the full calculations as required by the start and end date.
	 * If false, events will be deleted as from the CalcStatus.RESET !! unless isNoOverrideDeltaOnly() is also true !!
	 * 	//All children must be idempotent for this to be. True by default.
		//This can be overridden by making this operation itself not idempotent.
	 * @param targetStock TODO
	 * @return
	 */
	public Boolean isIdemPotent(TargetStockInfo targetStock) {
		if (operands.isEmpty()) return true;
		return operands.stream().reduce(true, (r, e) -> r && e.isIdemPotent(targetStock), (a, b) -> a && b);
	}
	
	/** 
	 * For non idempotent operations where past calculations need keeping
	 * If true only the tail end delta of the calculated events will be stored in the db.
	 * No gap or head start checks will be made.
	 * This applies only for non idempotent (when isIdemPotent() == false) operations as events can be overridden for idempotent operations.
	 * //Any Child NoOverrideDeltaOnly will make this operation NoOverrideDeltaOnly. False by default.
	 * @param targetStock TODO
	 * @return
	 */
	public Boolean isNoOverrideDeltaOnly(TargetStockInfo targetStock) {
		//if (this.isIdemPotent()) return false;
		Boolean isNoOverrideDeltaOnly = false;
		if (!operands.isEmpty()) {
			isNoOverrideDeltaOnly = operands.stream().reduce(false, (r, e) -> r || e.isNoOverrideDeltaOnly(targetStock), (a, b) -> a || b);
		}
		if (this instanceof EventInfoOpsCompoOperation) LOGGER.info(this.getReference() + " forbidEventsOverride: " + isNoOverrideDeltaOnly);
		return isNoOverrideDeltaOnly;
	}
	
	//XXX should be protected use with caution or have targetStock as parameter
	/**
	 * If true, this operation won't accept a parameter to be set from its first calculation resulting output. It will be recalculated systematically if its output is not cached.
	 * This ensures that the calculate is always executed. The operands can still have their parameter set.
	 * @return
	 */
	public boolean isForbidThisParameterValue() {
		if (this instanceof StockOperation || this instanceof OperationReferenceOperation) return true;
		if (operands.isEmpty()) return false;
		Boolean reduce = operands.stream().reduce(false, (r, e) -> r || e instanceof StockOperation || e instanceof OperationReferenceOperation || e.isForbidThisParameterValue(), (a, b) -> a || b);
		return reduce;
	}
	
	public boolean isDataShiftSensitive() {
		if (operands.isEmpty()) return false;
		Boolean reduce = operands.stream().reduce(false, (r, e) -> r || e instanceof StockOperation || e instanceof OperationReferenceOperation || e.isDataShiftSensitive(), (a, b) -> a || b);
		return reduce;
	}

	public void invalidateOperation(String analysisName, Optional<TargetStockInfo> targetStock, Optional<String> userOperationName) {
		//Nothing by default
	}

	/**
	 * Top down invalidation of this and its operands calculations.
	 * This is for use outside of the calculation cycle.
	 * EventInfoOpsCompoOperation: delete events from db if eOpsCompo is removable
	 * KerasLine/IOsXXXExporter/ImporterOperation: delete local files if the paths are available
	 * EncogXXXOperation: Obsolete
	 * KerasWebPredictOperation: Nothing
	 * KerasWebTrainOperation: removes env data for eOpsCompo as per targetStock and userOpName
	 * OperationReference/IfOperation: invalidation redirection
	 * @param analysisName
	 * @param targetStock
	 * @param userOperationName TODO
	 */
	public void invalidateAllNonIdempotentOperands(String analysisName, TargetStockInfo targetStock, Optional<String> userOperationName) {
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Checking " + getReference() + " for invalidation.");
		if (!this.isIdemPotent(targetStock) && !this.isNoOverrideDeltaOnly(targetStock)) {
			try {
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Invalidating " + getReference() + " for " + analysisName + " and " + targetStock);
				this.invalidateOperation(analysisName, Optional.of(targetStock), userOperationName);
			} catch (Exception e) {
				LOGGER.warn("Could not invalidate " + this + e);
			}
		}
		operands.stream().forEach(
				o -> {
					//if (!o.getOperands().isEmpty()) {
						o.invalidateAllNonIdempotentOperands(analysisName, targetStock, userOperationName);
					//}
				});
	}
	/**
	 * Top down forced (No check for isIdemPotent or isNoOverrideDeltaOnly) invalidation of this and its operands calculations.
	 * This is for use outside of the calculation cycle.
	 * @param analysisName
	 * @param targetInfo
	 * @param userOperationName TODO
	 * @param params
	 * @see invalidateAllNonIdempotentOperands
	 */
	public void invalidateAllForciblyOperands(String analysisName, TargetStockInfo targetInfo, Optional<String> userOperationName) {
		try {
			this.invalidateOperation(analysisName, Optional.of(targetInfo), userOperationName);
		} catch (Exception e) {
			LOGGER.warn("Could not invalidate " + this + e);
		}
		operands.stream().forEach(
				o -> {
					//if (!o.getOperands().isEmpty()) {
						o.invalidateAllForciblyOperands(analysisName, targetInfo, userOperationName);
					//}
				});
	}
	
	public Set<QuotationDataType> getRequiredStockData() {
		Set<QuotationDataType> quotationDataTypes = new HashSet<>();
		if (this instanceof StockOperation) {
			quotationDataTypes.add(QuotationDataType.valueOf(this.getOutputSelector().toUpperCase()));
		}
		if (!operands.isEmpty()) {
			quotationDataTypes.addAll(operands.stream().flatMap(o -> o.getRequiredStockData().stream()).collect(Collectors.toList()));
		}
		return quotationDataTypes;
	}
	
	//XXX should be protected use with caution or have targetStock as parameter
	public void interrupt() throws Exception {
		operands.stream()
			.forEach(
				o -> {
					//if (!o.getOperands().isEmpty()) {
						try {
							o.interrupt();
						} catch (Exception e) {
							LOGGER.error(e, e);
						}
					//}
				});
		CalculateThreadExecutor.getJoinForkExecutorInstance().shutdownNow();
	}
	
	public String resultHint(TargetStockInfo targetStockInfo, List<StackElement> callStack) {
		if (operands.isEmpty()) return "";
		List<StackElement> thisCallStack = addThisToStack(callStack, 0, targetStockInfo);
		String reduce = operands.stream()
				.map(e -> e.resultHint(targetStockInfo, thisCallStack))
				.filter(e -> !e.isEmpty())
				.reduce((r, e) -> r + ", " + e).orElse("");
		return reduce;
	}
	
	public Optional<String> calculationStatus(TargetStockInfo targetStockInfo, List<StackElement> callStack) {
		try {
			if (operands.isEmpty()) return Optional.empty();
			List<StackElement> thisCallStack = addThisToStack(callStack, 0, targetStockInfo);
			Optional<String> reduce = operands.stream()
					.map(e -> e.calculationStatus(targetStockInfo, thisCallStack))
					.filter(e -> !e.isEmpty())
					.map(e -> e.get())
					.reduce((r, e) -> r + "\n " + e);
			return reduce;
		} catch (Exception e) {
			return Optional.empty();
		}
	}

}
