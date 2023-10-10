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

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.calculation.parametrizedindicators.OutputDescr;
import com.finance.pms.events.calculation.parametrizedindicators.OutputReference;
import com.finance.pms.events.operations.conditional.ChartableWithMain;
import com.finance.pms.events.operations.conditional.EventInfoOpsCompoOperation;
import com.finance.pms.events.operations.conditional.MultiSelectorsValue;
import com.finance.pms.events.operations.conditional.MultiValuesOutput;
import com.finance.pms.events.operations.conditional.OnSignalCondition;
import com.finance.pms.events.operations.conditional.OnThresholdCondition;
import com.finance.pms.events.operations.conditional.UnaryCondition;
import com.finance.pms.events.operations.nativeops.DoubleArrayMapValue;
import com.finance.pms.events.operations.nativeops.MultiMapValue;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.StockOperation;
import com.finance.pms.events.quotations.QuotationDataType;

public class TargetStockInfo {

	private static MyLogger LOGGER = MyLogger.getLogger(TargetStockInfo.class);
	
	public class Output {

		private OutputReference outputReference;
		private Value<?> outputData;
		private OutputDescr chartedDescription;
		private int startShift;

		//For checking an entry's presence in the cache
		public Output(OutputReference outputReference) {
			super();
			this.outputReference = outputReference;
		}

		//For creating a new output entry in the cache
		public Output(OutputReference outputReference, Value<?> outputData, int startShift) {
			super();
			this.outputReference = outputReference;
			this.outputData = outputData;
			this.startShift = startShift;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((outputReference == null) ? 0 : outputReference.hashCode());
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
			Output other = (Output) obj;
			if (outputReference == null) {
				if (other.outputReference != null)
					return false;
			} else if (!outputReference.equals(other.outputReference))
				return false;
			return true;
		}

		public Value<?> getOutputData() {
			return outputData;
		}

		public OutputDescr getChartedDescription() {
			return chartedDescription;
		}

		private void setChartedDescription(OutputDescr chartedDescription) {
			this.chartedDescription = chartedDescription;
		}

		@Override
		public String toString() {
			return "Output [outputReference=" + outputReference + ", chartedDescription=" + chartedDescription + ", startShift=" + startShift +"]";
		}

		public OutputReference getOutputReference() {
			return outputReference;
		}

		public int getStartShift() {
			return startShift;
		}

		public void setStartShift(int startShift) {
			this.startShift = startShift;
		}

	}

	private String analysisName;
	private EventInfoOpsCompoOperation eventInfoOpsCompoOperation;
	private Stock stock;
	private final Date startDate;
	private final Date endDate;

	private Map<OutputReference,SoftReference<Output>> calculatedOutputsCache;
	private List<OutputReference> calculatingOutputsFutures;
	
	private List<Output> gatheredChartableOutputs;
	private List<ChartedOutputGroup> chartedOutputGroups;

	private Map<OutputReference, EventsAnalyser> outputAnalysers;
	
	private Set<Date> missingKeys = new HashSet<>();


	public TargetStockInfo(String analysisName, EventInfoOpsCompoOperation eventInfoOpsCompoOperationHolder, Stock stock, Date startDate, Date endDate) {
		super();
		
		//MyLogger.threadLocal.set(stock.getSymbol());
		
		this.analysisName = analysisName;
		this.eventInfoOpsCompoOperation = eventInfoOpsCompoOperationHolder;
		this.stock = stock;
		
//		if (stock.getLastQuote().before(endDate)) { //FIXME this can create problems if a quotation map has been initialised before an operation is ran as the quotations map may then be made out of date
//			try {
//				ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, new EventSignalConfig());
//				QuotationUpdate quotationUpdate = new QuotationUpdate();
//				quotationUpdate.getQuotesFor(stock);
//			} catch (QuotationUpdateException e) {
//				LOGGER.warn(e);
//			}
//		}
		this.endDate = endDate;

		if (stock.getLastQuote().before(startDate)) throw new RuntimeException("No enough quotations to calculate: " + stock.toString());
		this.startDate = startDate;

		this.calculatedOutputsCache = new ConcurrentHashMap<>();
		this.calculatingOutputsFutures = Collections.synchronizedList(new ArrayList<>());
		this.gatheredChartableOutputs = new ArrayList<>();
		this.chartedOutputGroups = new ArrayList<>();
		this.outputAnalysers = new HashMap<>();
		
	}
	
	private Output getCalculatedOutputsCacheFor(OutputReference outputReference) {
		SoftReference<Output> softOutput = this.calculatedOutputsCache.get(outputReference);
		if (softOutput != null) {
			Output output = softOutput.get();
			if (output == null) LOGGER.warn("Cache for " + outputReference.getReference() + " as been cleared");
			return output;
		}
		return null;
 	}
	
	private void putCalculatedOutputsCache(Output output) {
		this.calculatedOutputsCache.put(output.getOutputReference(), new SoftReference<TargetStockInfo.Output>(output));
	}

	public Stock getStock() {
		return stock;
	}

	/**
	 * Take a left shift in data points and returns a left shift in calendar days
	 * @param startShift
	 * @return
	 */
	public Date getStartDate(int startShift) {
		Date incStartDate = DateFactory.incrementDateWraper(stock, getQuotationsDataTypes(), startDate, -startShift);
		return incStartDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getAnalysisName() {
		return analysisName;
	}
	
	public SortedMap<EventKey, EventValue> analyseEvents(SortedMap<EventKey, EventValue> events) {
		SortedMap<EventKey, EventValue> analyzedEvents = events;
		for (OutputReference key : outputAnalysers.keySet()) {
			analyzedEvents = outputAnalysers.get(key).analyse(analyzedEvents);
		}
		return analyzedEvents;
	}

	public void addEventAnalyser(Operation operation, EventsAnalyser eventAnalyser) {
		outputAnalysers.put(new OutputReference(operation, operation.getOutputSelector()), eventAnalyser);
	}
	
	public void putOutputCalculationFuture(OutputReference unfinishedOutputReference) {
		calculatingOutputsFutures.add(unfinishedOutputReference);
	}
	
	public void removeOutputCalculationFuture(OutputReference unfinishedOutputReference) {
		boolean removed = calculatingOutputsFutures.remove(unfinishedOutputReference);
		if (!removed) throw new RuntimeException("Could not remove: " + unfinishedOutputReference);
	}
	
	public OutputReference getOutputCalculationFuture(OutputReference unfinishedOutputReference) {
		int indexOf = calculatingOutputsFutures.indexOf(unfinishedOutputReference);
		OutputReference theirOutputReference = null;
		if (indexOf != -1) theirOutputReference = calculatingOutputsFutures.get(indexOf);
		return theirOutputReference;
	}

	public Value<?> checkAlreadyCalculated(Operation operation, String outputSelector, int operandsRequiredshift) {
		//XXX All operation will be checked although we know only numericable outputs and outputs for cachable are stored?
		OutputReference outputReference = new OutputReference(operation, outputSelector);
		
		Output output = getCalculatedOutputsCacheFor(outputReference);
		if (output == null || (operandsRequiredshift > output.getStartShift())) {
			if (output != null) {
				calculatedOutputsCache.remove(outputReference);
			}
			return null;
		}
		return output.getOutputData();
	}

	public void gatherOneOutput(Operation operation, Value<?> outputValue, Optional<String> outputDiscriminator, int operationRequiredStartShift, boolean isInChart) {
		
		Boolean isCachable = true;
		if (operation instanceof StockOperation) { //We don't cache stock as this is managed in the Quotations classes. 
			//The rational is that we can't distinguish StockOperation validity filters outputs 
			isCachable = false;
		}

		Value<?> alreadyCalculated = checkAlreadyCalculated(operation, outputDiscriminator.orElse(operation.getOutputSelector()), operationRequiredStartShift);
		if (alreadyCalculated != null) {
			if (getIndexOfChartableOutput(operation, outputDiscriminator.orElse(operation.getOutputSelector())) == -1) {
				if (isInChart) this.gatheredChartableOutputsAdd(new Output(new OutputReference(operation, operation.getOutputSelector()), alreadyCalculated, operationRequiredStartShift));
			}
			return;
		}

		if (outputValue instanceof MultiSelectorsValue) {
			//Caches all the selectors outputs as the are all calculated at once.
			for (String selector : ((MultiSelectorsValue) outputValue).getSelectors()) {
				//encogPlus:ideal("RealSMATopsAndButts","continuous","continuous",0.0,0.0,84.0,gxEncogPredSmaRealDiscreteContCont84UnNormNoWeight63(),gxEncogPredSmaRealDiscreteContCont84UnNormPgr63(),gxEncogPredSmaRealDiscreteContCont84UnNormSmpl63(), close)
				String tamperedFormula = (operation.getFormulae() != null)?
						operation.getFormulae().replaceFirst(":[^\\(]*\\(", ":" + selector + "("): //encogPlus:xxxxx(... => encogPlus:selector(...
						operation.toFormulae().replaceFirst(":[^\\(]*\\(", ":" + selector + "("); //Anonymous operation
				//constant = null as the selector output as to be a NumericableMapValue and hence can't be a constant.
				OutputReference outputReference = new OutputReference(operation, selector, tamperedFormula);
				if (isCachable) putCalculatedOutputsCache(new Output(outputReference, ((MultiSelectorsValue) outputValue).getValue(selector), operationRequiredStartShift));
			}
			//Only make available for chart the specific selector
			NumericableMapValue selectorOutputValue = ((MultiSelectorsValue) outputValue).getValue(((MultiSelectorsValue) outputValue).getCalculationSelector());
			if (isInChart)  this.gatheredChartableOutputsAdd(new Output(new OutputReference(operation, operation.getOutputSelector()), selectorOutputValue, operationRequiredStartShift));
		} else if (outputValue instanceof DoubleArrayMapValue) {
			Output outputHolder = new Output(new OutputReference(operation, outputDiscriminator.orElse(operation.getOutputSelector())), outputValue, operationRequiredStartShift);
			if (isCachable) putCalculatedOutputsCache(outputHolder);
			if (isInChart)  this.gatheredChartableOutputsAdd(outputHolder);
		} else {
			Output outputHolder = new Output(new OutputReference(operation, outputDiscriminator.orElse(operation.getOutputSelector())), outputValue, operationRequiredStartShift);
			if (isCachable) putCalculatedOutputsCache(outputHolder);
			if (isInChart) this.gatheredChartableOutputsAdd(outputHolder);
		}

	}

	public ChartedOutputGroup setMain(Operation operation, Optional<String> outputSelector, Boolean displayByDefault) {
		Integer indexOfOutput = getIndexOfChartableOutput(operation, outputSelector.orElse(operation.getOutputSelector()));
		return setMain(operation, outputSelector, indexOfOutput, displayByDefault);
	}

	private ChartedOutputGroup setMain(Operation operation, Optional<String> outputSelector, Integer indexOfOutput, Boolean displayByDefault) {
		
		if (indexOfOutput != -1) {
			Output output = getGatheredChartableOutput(indexOfOutput);
			OutputDescr chartedDesrc = output.getChartedDescription();
			if (chartedDesrc != null) {
				chartedDesrc.maskType(Type.MAIN);
			} else {
				ChartedOutputGroup chartedOutputGroup = new ChartedOutputGroup(operation, outputSelector, indexOfOutput, displayByDefault);
				chartedOutputGroupsAdd(chartedOutputGroup);
				chartedDesrc = chartedOutputGroup.getThisGroupMainOutputDescription();
				output.setChartedDescription(chartedDesrc);
			}
			return chartedDesrc.getContainer();
		} else {
			throw new RuntimeException("No historical output found available to display charted output. The main output must be a DoubleMapOperation: " + operation.getClass() + " for " + operation);
		}
		
	}

	public Integer getIndexOfChartableOutput(Operation operation, String outputSelector) {
		return getGatheredChartableOutputs().indexOf(new Output(new OutputReference(operation, outputSelector)));
	}

	public List<ChartedOutputGroup> getChartedOutputGroups() {
		return Collections.synchronizedList(chartedOutputGroups);
	}
	
	public List<ChartedOutputGroup> getChartedOutputGroupsAsync() {
		return chartedOutputGroups;
	}
	
	private void chartedOutputGroupsRemove(int indexOf) {
		getChartedOutputGroups().remove(indexOf);	
	}
	
	private void chartedOutputGroupsAdd(ChartedOutputGroup chartedOutputGroup) {
		getChartedOutputGroups().add(chartedOutputGroup);
	}

	private void addChartInfoForSignal(ChartedOutputGroup mainChartedGrp, Operation operation, Boolean displayByDefault) {

		Integer indexOfOutput = getIndexOfChartableOutput(operation, operation.getOutputSelector());
		if (indexOfOutput != -1) {
			Output output = getGatheredChartableOutput(indexOfOutput);
			OutputDescr chartedDescr = output.getChartedDescription();
			if (chartedDescr != null) {
				//Merge mainChartedGrp and existingChartedGrp if <> + maskType
				ChartedOutputGroup existingChartedGrp = chartedDescr.getContainer();
				if (existingChartedGrp.equals(mainChartedGrp)) {
					chartedDescr.maskType(Type.SIGNAL);
				} else {
					OutputDescr existingChartedDesrc = existingChartedGrp.getThisGroupMainOutputDescription();
					existingChartedDesrc.maskType(Type.BOTH);
					mainChartedGrp.mvComponentInThisGrp(existingChartedGrp.getThisGroupMainOutputReference(), existingChartedDesrc);
					for (OutputReference oldContentRef : existingChartedGrp.getComponents().keySet()) {
						OutputDescr oldOutputDescr = existingChartedGrp.getComponents().get(oldContentRef);
						mainChartedGrp.mvComponentInThisGrp(oldContentRef, oldOutputDescr);
					}
					this.chartedOutputGroupsRemove(this.getChartedOutputGroups().indexOf(existingChartedGrp));
				}
			} else {
				chartedDescr = mainChartedGrp.addSignal(operation, indexOfOutput,displayByDefault);
				output.setChartedDescription(chartedDescr);
			}

		} else {
			throw new RuntimeException("Output not found for " + operation);
		}
	}

	private void addChartInfoForAdditonalOutputs(Operation operand, Map<String, Type> outputTypes, Integer indexOfMain) {
		if (outputTypes.isEmpty()) return;
		Output mainOutput = getGatheredChartableOutput(indexOfMain);
		OutputDescr chartedDesrc = mainOutput.getChartedDescription();
		if (chartedDesrc != null) {
			ChartedOutputGroup mainChartedGroup = chartedDesrc.getContainer();
			for (String outputKey : outputTypes.keySet()) {
				Integer indexOfOutput = getIndexOfChartableOutput(operand, outputKey);
				mainChartedGroup.addAdditionalOutput(outputKey, operand, indexOfOutput, outputTypes.get(outputKey));
			}
		} else {
			throw new RuntimeException("Multi Output Main group (at index " + indexOfMain + ") not found not found for " + operand);
		}
	}

	private Output getGatheredChartableOutput(Integer indexOfOutput) {
		try {
			return getGatheredChartableOutputs().get(indexOfOutput);
		} catch (Exception e) {
			LOGGER.error("No gathered output for "+indexOfOutput, e);
			throw e;
		}
	}

	@Override
	public String toString() {
		return "TargetStockInfo [stock=" + stock + ", startDate=" + startDate + ", endDate=" + endDate + ", analysisName=" + analysisName + "]";
	}

	public List<Output> getGatheredChartableOutputs() {
		return Collections.synchronizedList(gatheredChartableOutputs);
	}
	
	public List<Output> getGatheredChartableOutputsAsync() {
		return gatheredChartableOutputs;
	}
	
	private void gatheredChartableOutputsAdd(Output output) {
		this.getGatheredChartableOutputs().add(output);	
	}

	public void printOutputs() {

		Set<Date> allKeys = new TreeSet<Date>();
		String header = this.stock.getFriendlyName().replaceAll(",", " ") + ",";

		for (Output output : getGatheredChartableOutputs()) {
			Value<?> outputData = output.getOutputData();
			if (outputData instanceof NumericableMapValue) {
				header = header + output.getOutputReference().getReference() + ",";
				Set<Date> keySet = ((NumericableMapValue)outputData).getValue(this).keySet();
				allKeys.addAll(keySet);
			}
		}
		System.out.println(header);

		for (Date date : allKeys) {
			String line = date + ",";
			for (Output output : getGatheredChartableOutputs()) {
				Value<?> outputData = output.getOutputData();
				if (outputData instanceof NumericableMapValue) {
					line = line + ((NumericableMapValue)outputData).getValue(this).get(date) + ",";
				}
			}
			System.out.println(line);
		}

	}

	public EventInfoOpsCompoOperation getEventInfoOpsCompoOperation() {
		return eventInfoOpsCompoOperation;
	}
	
	public Set<QuotationDataType> getQuotationsDataTypes() {
		return eventInfoOpsCompoOperation.getRequiredStockData();
	}

	public Map<OutputReference, EventsAnalyser> getOutputAnalysers() {
		return outputAnalysers;
	}
	/**
	 * 
	 * @param operation
	 * @param callStack
	 * @param operandsOutputs Only used here to resolve NumberValues(like threshold) and Values type (is NumericableMapValue). The content should not be used otherwise
	 */
	public void populateChartedOutputGroups(Operation operation, String callStack, List<Value<?>> operandsOutputs) {

		Boolean displayByDefault = isMainConditionStack(callStack);

		ChartedOutputGroup chartedOutputGroup = null;
		List<Operation> operands = operation.getOperands();
		if (operands.size() != operandsOutputs.size()) {
			throw new RuntimeException("Operation " + operation + " has no ouput.");
		}
		
		if (operation instanceof OnSignalCondition) {//Operands outputs are grouped
			//pick up or create the group
			int mainOpPosition = ((OnSignalCondition) operation).mainInputPosition();
			chartedOutputGroup = setMain(operands.get(mainOpPosition), Optional.empty(), displayByDefault);
			LOGGER.info("Chart adding main " + operands.get(mainOpPosition).shortOutputReference() + " as OnSignalCondition. GroupId " + chartedOutputGroup.getThisGroupMainOutputDescription().groupId());
			//add the signal
			int signalOpPosition = ((OnSignalCondition) operation).inputSignalPosition();
			addChartInfoForSignal(chartedOutputGroup, operands.get(signalOpPosition), displayByDefault);

		} else if (operation instanceof OnThresholdCondition) {
			//pick up or create the group
			int mainOpPosition = ((OnThresholdCondition) operation).mainInputPosition();
			Operation mainOp = operands.get(mainOpPosition);
			chartedOutputGroup = setMain(mainOp, Optional.empty(), displayByDefault);
			LOGGER.info("Chart adding main " + mainOp.shortOutputReference() + " as OnThresholdCondition. GroupId " + chartedOutputGroup.getThisGroupMainOutputDescription().groupId());
			//add the constant
			int thresholdOpPosition = ((OnThresholdCondition)operation).inputThresholdPosition();
			chartedOutputGroup.addConstant(mainOp.getReference(), operands.get(thresholdOpPosition), (NumberValue) operandsOutputs.get(thresholdOpPosition), displayByDefault);

		} else if (operation instanceof UnaryCondition) {
			//pick up or create the group
			int mainOpPosition = ((UnaryCondition) operation).mainInputPosition();
			chartedOutputGroup = setMain(operands.get(mainOpPosition), Optional.empty(), displayByDefault);
			LOGGER.info("Chart adding main " + operands.get(mainOpPosition).shortOutputReference() + " as OnThresholdCondition. GroupId " + chartedOutputGroup.getThisGroupMainOutputDescription().groupId());

		} else { //The operation is not a ChartableWithMain but an operand up the calculation tree. It won't be visible by default.
			//To create the group, we will rely on the operands only the first operand being pickup as main
			//LOGGER.info("Chart adding " + operation.shortOutputReference() + " as NOT ChartableWithMain.");
			for(int i = 0; i < operands.size(); i++) {
				Value<?> ov = operandsOutputs.get(i);
				Operation operand = operands.get(i);
				if (ov instanceof NumericableMapValue && operand.getFormulae() != null) {
					if (chartedOutputGroup == null) {
						chartedOutputGroup = setMain(operand, Optional.empty(), false);
						LOGGER.info("Chart adding main " + operand.shortOutputReference() + " Operand of " + operation.shortOutputReference() + ". Group Id " + chartedOutputGroup.getThisGroupMainOutputDescription().groupId());
					} else {
						LOGGER.info("Chart adding other operand " + operand.shortOutputReference() + " Operand of " + operation.shortOutputReference() + ". Group Id " + chartedOutputGroup.getThisGroupMainOutputDescription().groupId());
						//FIXME it seems we use outputSelector and operand Reference indiscriminately: this doesn't seem to work if the operand also have an outputSelector. There should be two different attributes.
						Map<String, Type> additionalOutputsTypes = new HashMap<>();
						additionalOutputsTypes.put(operand.getOutputSelector(), Type.MULTI);
						Integer indexOfMain = getIndexOfMainChartableOutput();
						addChartInfoForAdditonalOutputs(operand, additionalOutputsTypes, indexOfMain);
					}
				}
			}
		}

		//MutliMapValues additional processing
		//The operation may have operands with MutliMapValues outputs (Will generate 'Sub' Groups). The problem is that MutliMapValues have to be displayed as if operands outputs.
		//However, the chartInfo of MultiMapValues not being added when calculating operation's operands we need to add it here when we now have the output.
		//Some of this code could also be moved/called in the Operation.gatherAdditionalOutputs?
		for(int i = 0; i < operands.size(); i++) {
			Value<?> ov = operandsOutputs.get(i);
			Operation operand = operands.get(i);
			if (operand instanceof MultiValuesOutput && ov instanceof MultiMapValue) {
				Map<String, Type> multiMapValueOutputTypes = ((MultiMapValue) ov).getAdditionalOutputsTypes();
				if (!multiMapValueOutputTypes.isEmpty()) { //Operand with effective MultiValueMap output.
					if (operand.getFormulae() != null) { //User defined operand. Itself being a NumericableMapValue, as all user defined operations are, the operand also has a main output and hence can be set as main of the group
						ChartedOutputGroup multiVChartedOutputGroup = setMain(operand, Optional.ofNullable(operand.getOutputSelector()), displayByDefault);
						LOGGER.info("Chart adding MutliMapValues with main " + operand.shortOutputReference() + " as operation with formulae. Group Id " + multiVChartedOutputGroup.getThisGroupMainOutputDescription().groupId());
						addChartInfoForAdditonalOutputs(operand, multiMapValueOutputTypes, getIndexOfChartableOutput(operand, operand.getOutputSelector()));
					}
					//We include only the remaining potential ChartableWithMain as other cases may cause scaling issues? This also includes anonymous user defined operations
					else if (chartedOutputGroup == null) { //Not a User defined operand (or it is an anonymously User defined operand) and No main has been set for potential other operands in which group the MutliMapValues of this operand could be reflected (case removed).
						if (operand instanceof ChartableWithMain) { //This operand itself has a main among its own operands. We reuse this main operand and group.
							Operation mainOperandOfOperand = operand.getOperands().get(((ChartableWithMain) operand).mainInputPosition());
							Optional<String> outputSelector = Optional.ofNullable(mainOperandOfOperand.getOutputSelector());
							ChartedOutputGroup multiVChartedOutputGroup = setMain(mainOperandOfOperand, outputSelector, displayByDefault);
							LOGGER.info("Chart adding MutliMapValues with main " + mainOperandOfOperand + " as ChartableWithMain. Group Id " + multiVChartedOutputGroup.getThisGroupMainOutputDescription().groupId());
							addChartInfoForAdditonalOutputs(operand, multiMapValueOutputTypes, getIndexOfChartableOutput(mainOperandOfOperand, outputSelector.orElse(null)));
						}
					}
				}
			}
		}

	}

	public boolean isMainConditionStack(String callStack) {
		return callStack.contains("bullishCondition") || callStack.contains("bearishCondition");
	}
	
	public boolean isAlsoDiplayStack(String callStack) {
		return callStack.contains("alsoDisplay");
	}

	private Integer getIndexOfMainChartableOutput() {
		Integer indexOfMain = getGatheredChartableOutputs().indexOf(getGatheredChartableOutputs().stream()
				.filter(o -> o.getChartedDescription() != null)
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Multi Output Main group not found in " + getGatheredChartableOutputs())));
		return indexOfMain;
	}

	public Set<Date> missingData() {
		return this.missingKeys;
	}
	
	@SuppressWarnings("unchecked")
	public void addMissingData(Collection<?> keys) {
		this.missingKeys.addAll((Collection<? extends Date>) keys);
	}

}
