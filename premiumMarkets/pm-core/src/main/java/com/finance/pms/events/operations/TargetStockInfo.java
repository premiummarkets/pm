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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.calculation.WarningException;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.calculation.parametrizedindicators.OutputDescr;
import com.finance.pms.events.calculation.parametrizedindicators.OutputReference;
import com.finance.pms.events.operations.conditional.ChartableWithMain;
import com.finance.pms.events.operations.conditional.EventInfoOpsCompoOperation;
import com.finance.pms.events.operations.conditional.MultiSelectorsValue;
import com.finance.pms.events.operations.conditional.OnSignalCondition;
import com.finance.pms.events.operations.conditional.OnThresholdCondition;
import com.finance.pms.events.operations.conditional.UnaryCondition;
import com.finance.pms.events.operations.nativeops.MultiMapValue;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.StockOperation;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;

public class TargetStockInfo {

	private static MyLogger LOGGER = MyLogger.getLogger(TargetStockInfo.class);

	public class Output {

		private OutputReference outputReference;
		private Value<?> outputData;
		private OutputDescr chartedDescription;

		public Output(OutputReference outputReference) {
			super();
			this.outputReference = outputReference;
		}

		public Output(OutputReference outputReference, Value<?> outputData) {
			super();
			this.outputReference = outputReference;
			this.outputData = outputData;
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
			return "Output [outputReference=" + outputReference + ", chartedDescription=" + chartedDescription + "]";
		}

		public OutputReference getOutputReference() {
			return outputReference;
		}

	}

	private Stock stock;
	private final Date startDate;
	private final Date endDate;
	private String analysisName;
	private EventInfoOpsCompoOperation eventInfoOpsCompoOperation;

	private List<Output> calculatedOutputsCache;

	private List<Output> gatheredChartableOutputs;
	private List<ChartedOutputGroup> chartedOutputGroups;

	private Map<OutputReference, EventsAnalyser> outputAnalysers;

	public TargetStockInfo(String analysisName, EventInfoOpsCompoOperation eventInfoOpsCompoOperationHolder, Stock stock, Date startDate, Date endDate) throws WarningException {
		super();
		this.analysisName = analysisName;
		this.eventInfoOpsCompoOperation = eventInfoOpsCompoOperationHolder;
		this.stock = stock;

		Date lastQuote = stock.getLastQuote();
		if (lastQuote.before(startDate)) throw new WarningException("No enough quotations to calculate : "+stock.toString());
		this.startDate = startDate;
		this.endDate = endDate;

		this.calculatedOutputsCache = new ArrayList<TargetStockInfo.Output>();
		this.gatheredChartableOutputs = new ArrayList<TargetStockInfo.Output>();
		this.chartedOutputGroups = new ArrayList<ChartedOutputGroup>();
		this.outputAnalysers = new HashMap<>();
	}

	public Stock getStock() {
		return stock;
	}

	public Date getStartDate() {
		return startDate;
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

	public Value<?> checkAlreadyCalculated(Operation operation, String outputSelector) {
		//!(
		//((output instanceof UnarableMapValue && (operand.getFormulae() != null)) || operand instanceof StockOperation))
		//|| (output instanceof MultiMapValue)
		//)
		if (outputSelector == null && operation.getFormulae() == null && !(operation instanceof StockOperation)) return null;
		int indexOf = calculatedOutputsCache.indexOf(new Output(new OutputReference(operation, outputSelector)));
		if (indexOf == -1) {
			return null;
		}
		return calculatedOutputsCache.get(indexOf).outputData;
	}

	/**
	 * This method also calls setMain for MultiSelectorsValue
	 * @param outputDiscriminator
	 */
	public void gatherOneOutput(Operation operation, Value<?> outputValue, Optional<String> outputDiscriminator) {

		Value<?> alreadyCalculated = checkAlreadyCalculated(operation, outputDiscriminator.orElse(operation.getOutputSelector()));
		if (alreadyCalculated != null) {
			if (getIndexOfChartableOutput(operation, outputDiscriminator.orElse(operation.getOutputSelector())) == -1) {
				this.gatheredChartableOutputs.add(new Output(new OutputReference(operation, operation.getOutputSelector()), alreadyCalculated));
			}
			return;
		}

		if (outputValue instanceof MultiSelectorsValue) {
			for (String selector : ((MultiSelectorsValue) outputValue).getSelectors()) {
				//encogPlus:ideal("RealSMATopsAndButts","continuous","continuous",0.0,0.0,84.0,gxEncogPredSmaRealDiscreteContCont84UnNormNoWeight63(),gxEncogPredSmaRealDiscreteContCont84UnNormPgr63(),gxEncogPredSmaRealDiscreteContCont84UnNormSmpl63(), close)
				String tamperedFormula = operation.getFormulae().replaceAll(":[^\\(]*\\(", ":"+selector+"("); //encogPlus:xxxxx(... => encogPlus:selector(...
				//constant = null as the selector output as to be an UnarableMapValue and hence can't be a constant.
				OutputReference outputReference = new OutputReference(operation.getReference(), selector, tamperedFormula, operation.getReferenceAsOperand(), null, operation.getOperationReference());
				this.calculatedOutputsCache.add(new Output(outputReference, ((MultiSelectorsValue) outputValue).getValue(selector)));
			}
			this.gatheredChartableOutputs.add(new Output(new OutputReference(operation, operation.getOutputSelector()), ((MultiSelectorsValue) outputValue).getValue(((MultiSelectorsValue) outputValue).getCalculationSelector())));
		} else {
			Output outputHolder = new Output(new OutputReference(operation, outputDiscriminator.orElse(operation.getOutputSelector())), outputValue);
			this.calculatedOutputsCache.add(outputHolder);
			this.gatheredChartableOutputs.add(outputHolder);
		}

	}

	public ChartedOutputGroup setMain(Operation operation, Optional<String> outputSelector) {
		Integer indexOfOutput = getIndexOfChartableOutput(operation, outputSelector.orElse(operation.getOutputSelector()));
		return setMain(operation, outputSelector, indexOfOutput);
	}

	private ChartedOutputGroup setMain(Operation operation, Optional<String> outputSelector, Integer indexOfOutput) {
		if (indexOfOutput != -1) {

			Output output = getGatheredChartableOutput(indexOfOutput);
			OutputDescr chartedDesrc = output.getChartedDescription();
			if (chartedDesrc != null) {
				chartedDesrc.maskType(Type.MAIN);
			} else {
				ChartedOutputGroup chartedOutputGroup = new ChartedOutputGroup(operation, outputSelector, indexOfOutput);
				chartedOutputGroups.add(chartedOutputGroup);
				chartedDesrc = chartedOutputGroup.getThisGroupMainOutputDescription();
				output.setChartedDescription(chartedDesrc);
			}
			return chartedDesrc.getContainer();

		} else {
			throw new RuntimeException("No historical output found available to display charted output. The main output must be a DoubleMapOperation: "+operation.getClass()+" for "+operation);
		}
	}

	public Integer getIndexOfChartableOutput(Operation operation, String outputSelector) {
		return gatheredChartableOutputs.indexOf(new Output(new OutputReference(operation, outputSelector)));
	}

	public Integer getIndexOfChartableOutput(OutputReference outputRef) {
		return gatheredChartableOutputs.indexOf(new Output(outputRef));
	}

	public List<ChartedOutputGroup> getChartedOutputGroups() {
		return chartedOutputGroups;
	}

	private void addChartInfoForSignal(ChartedOutputGroup mainChartedGrp, Operation operation) {

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
					this.chartedOutputGroups.remove(this.chartedOutputGroups.indexOf(existingChartedGrp));
				}
			} else {
				chartedDescr = mainChartedGrp.addSignal(operation, indexOfOutput);
				output.setChartedDescription(chartedDescr);
			}

		} else {
			throw new RuntimeException("Output not found for "+operation);
		}
	}

	private void addChartInfoForAdditonalOutputs(Operation operand, Map<String, Type> outputTypes, Integer indexOfMain) {
		if (outputTypes.isEmpty()) return;
		Output mainOutput = getGatheredChartableOutput(indexOfMain);
		OutputDescr chartedDesrc = mainOutput.getChartedDescription();
		if (chartedDesrc != null) {
			ChartedOutputGroup mainChartedGroup = chartedDesrc.getContainer();
			for (String outputKey : outputTypes.keySet()) {
				Integer indexOfOutput = getIndexOfChartableOutput(new OutputReference(operand, outputKey));
				mainChartedGroup.addAdditionalOutput(outputKey, operand, indexOfOutput, outputTypes.get(outputKey));
			}
		} else {
			throw new RuntimeException("Multi Output Main group (at index " + indexOfMain + ") not found not found for " + operand);
		}
	}

	private Output getGatheredChartableOutput(Integer indexOfMain) {
		try {
			return gatheredChartableOutputs.get(indexOfMain);
		} catch (Exception e) {
			LOGGER.error("No gathered output for "+indexOfMain, e);
			throw e;
		}
	}

	@Override
	public String toString() {
		return "TargetStockInfo [stock=" + stock + ", startDate=" + startDate + ", endDate=" + endDate + ", analysisName=" + analysisName + "]";
	}

	public List<Output> getGatheredChartableOutputs() {
		return gatheredChartableOutputs;
	}

	public void printOutputs() {

		Set<Date> allKeys = new TreeSet<Date>();
		String header = this.stock.getFriendlyName().replaceAll(",", " ") + ",";

		for (Output output : gatheredChartableOutputs) {
			Value<?> outputData = output.getOutputData();
			if (outputData instanceof NumericableMapValue) {
				header = header + output.getOutputReference().getReference()+",";
				Set<Date> keySet = ((NumericableMapValue)outputData).getValue(this).keySet();
				allKeys.addAll(keySet);
			}
		}
		System.out.println(header);

		for (Date date : allKeys) {
			String line = date + ",";
			for (Output output : gatheredChartableOutputs) {
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

	public Map<OutputReference, EventsAnalyser> getOutputAnalysers() {
		return outputAnalysers;
	}

	public void populateChartedOutputGroups(Operation operation, List<Value<?>> operandsOutputs) {

		ChartedOutputGroup chartedOutputGroup = null;
		List<Operation> operands = operation.getOperands();
		if (operation instanceof OnSignalCondition) {//Operands outputs are grouped
			//pick up or create the group
			int mainOpPosition = ((OnSignalCondition) operation).mainInputPosition();
			chartedOutputGroup = setMain(operands.get(mainOpPosition), Optional.empty());
			//add the signal
			int signalOpPosition = ((OnSignalCondition) operation).inputSignalPosition();
			addChartInfoForSignal(chartedOutputGroup, operands.get(signalOpPosition));

		} else if (operation instanceof OnThresholdCondition) {
			//pick up or create the group
			int mainOpPosition = ((OnThresholdCondition) operation).mainInputPosition();
			Operation mainOp = operands.get(mainOpPosition);
			chartedOutputGroup = setMain(mainOp, Optional.empty());
			//add the constant
			int thresholdOpPosition = ((OnThresholdCondition)operation).inputThresholdPosition();
			chartedOutputGroup.addConstant(mainOp.getReference(), operands.get(thresholdOpPosition), (NumberValue) operandsOutputs.get(thresholdOpPosition));

		} else if (operation instanceof UnaryCondition) {
			//pick up or create the group
			int mainOpPosition = ((UnaryCondition) operation).mainInputPosition();
			chartedOutputGroup = setMain(operands.get(mainOpPosition), Optional.empty());

		} else { //adHoc will rely on the operands only the first operand being pickup as main
			for(int i = 0; i < operands.size(); i++) {
				Value<?> ov = operandsOutputs.get(i);
				Operation operand = operands.get(i);
				if (ov instanceof NumericableMapValue && operand.getFormulae() != null) {
					if (chartedOutputGroup == null) {
						chartedOutputGroup = setMain(operand, Optional.empty());
					} else {
						Map<String, Type> additionalOutputsTypes = new HashMap<>();
						additionalOutputsTypes.put(operand.getReference(), Type.MULTI);
						addChartInfoForAdditonalOutputs(operand, additionalOutputsTypes, getIndexOfChartableOutput(operand, operand.getOutputSelector()));
					}
				}
			}
		}

		//Operations with MutliMapValues outputs (Generates 'Sub' Groups). The problem is that MutliMapValues have to be displayed as if operands outputs.
		//However, the charted info not being added when calculating operands we need to add it here when we now have the output.
		//Some of this code could also be moved/called in the Operation.gatherAdditionalOutputs?
		for(int i = 0; i < operands.size(); i++) {
			Value<?> ov = operandsOutputs.get(i);
			Operation operand = operands.get(i);
			if (ov instanceof MultiMapValue) {
				Map<String, Type> multiMapValueOutputTypes = ((MultiMapValue) ov).getAdditionalOutputsTypes();
				if (operand.getFormulae() != null) { //User defined operand. Itself being a NumericableMapValue, as all user defined operations are, the operand also has a principal output and can be set as main
					setMain(operand, Optional.ofNullable(operand.getOutputSelector()));
					addChartInfoForAdditonalOutputs(operand, multiMapValueOutputTypes, getIndexOfChartableOutput(operand, operand.getOutputSelector()));
				}
				else if (!multiMapValueOutputTypes.isEmpty()) { //Anonymous operand with effective MultiValueMap output.
					if (chartedOutputGroup == null) { //No main has been set for potential other operands in witch group the multi output of this operand could reflect.
						Operation mainOperandOfOperand;
						Optional<String> outputSelector;
						if (operand instanceof ChartableWithMain) { //This operand itself has main though, among its own operands. We use this main and group.
							mainOperandOfOperand = operand.getOperands().get(((ChartableWithMain) operand).mainInputPosition());
							outputSelector = Optional.ofNullable(mainOperandOfOperand.getOutputSelector());
						} else { //This operand has no main and no main is defined by other operands. We create a new group picking up the first multi output as main.
							mainOperandOfOperand = operand;
							outputSelector = Optional.of(multiMapValueOutputTypes.keySet().iterator().next());
						}
						setMain(mainOperandOfOperand, outputSelector);
						addChartInfoForAdditonalOutputs(operand, multiMapValueOutputTypes, getIndexOfChartableOutput(mainOperandOfOperand, outputSelector.get()));
					//FIXME?? remove this case as it may cause scaling issues linking operands of this operand to other operands in this operation.
					} else { //A main and group is set by other operands. We use theses.
						addChartInfoForAdditonalOutputs(operand, multiMapValueOutputTypes, getIndexOfChartableOutput(operand, operand.getOutputSelector()));
					}
				}
			}
		}

	}

}
