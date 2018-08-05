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
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.calculation.WarningException;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.OutputDescr;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.calculation.parametrizedindicators.OutputReference;
import com.finance.pms.events.operations.conditional.ChartableWithMain;
import com.finance.pms.events.operations.conditional.EventInfoOpsCompoOperation;
import com.finance.pms.events.operations.conditional.MultiSelectorsValue;
import com.finance.pms.events.operations.nativeops.ChartableMapValue;
import com.finance.pms.events.operations.nativeops.LeafOperation;
import com.finance.pms.events.operations.nativeops.StockOperation;

public class TargetStockInfo {

//	private static MyLogger LOGGER = MyLogger.getLogger(TargetStockInfo.class);

	public class Output {

		OutputReference outputReference;
		Value<?> outputData;
		OutputDescr chartedDescription;

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
		outputAnalysers.put(new OutputReference(operation), eventAnalyser);
	}

	public Value<?> checkAlreadyCalculated(Operation operation) {
		if (operation.getFormulae() == null && !(operation instanceof StockOperation)) return null;
		int indexOf = calculatedOutputsCache.indexOf(new Output(new OutputReference(operation)));
		if (indexOf == -1) {
			return null;
		}
		return calculatedOutputsCache.get(indexOf).outputData ;
	}

	/**
	 * This method also calls setMain for MultiSelectorsValue
	 */
	public void addOutput(Operation operation, Value<?> output) {

		Value<?> alreadyCalculated = checkAlreadyCalculated(operation);
		if (alreadyCalculated != null) {
			if (getIndexOfChartableOutput(operation) == -1) {
				this.gatheredChartableOutputs.add(new Output(new OutputReference(operation), alreadyCalculated));
			}
			return;
		}

		if (output instanceof MultiSelectorsValue) {
			for (String selector : ((MultiSelectorsValue) output).getSelectors()) {
				//encogPlus:ideal("RealSMATopsAndButts","continuous","continuous",0.0,0.0,84.0,gxEncogPredSmaRealDiscreteContCont84UnNormNoWeight63(),gxEncogPredSmaRealDiscreteContCont84UnNormPgr63(),gxEncogPredSmaRealDiscreteContCont84UnNormSmpl63(), close)
				String tamperedFormula = operation.getFormulae().replaceAll(":[^\\(]*\\(", ":"+selector+"(");
				OutputReference outputReference = new OutputReference(operation.getReference(), selector, tamperedFormula, operation.getReferenceAsOperand(), (operation instanceof LeafOperation), operation.getOperationReference());
				this.calculatedOutputsCache.add(new Output(outputReference, ((MultiSelectorsValue) output).getValue(selector)));
			}
			this.gatheredChartableOutputs.add(new Output(new OutputReference(operation), ((MultiSelectorsValue) output).getValue(((MultiSelectorsValue) output).getCalculationSelector())));
		} else {
			Output outputDescr = new Output(new OutputReference(operation), output);
			this.calculatedOutputsCache.add(outputDescr);
			this.gatheredChartableOutputs.add(outputDescr);
		}

	}

	public void addExtraneousChartableOutput(Operation operation, ChartableMapValue output, String multiOutputDiscriminator) {
		this.gatheredChartableOutputs.add(new Output(new OutputReference(operation, multiOutputDiscriminator), output));
	}

	public ChartedOutputGroup setMain(Operation operation, String mainOperationQualifier) {
		int indexOfOutput = gatheredChartableOutputs.indexOf(new Output(new OutputReference(operation, mainOperationQualifier)));
		return setMain(operation, indexOfOutput);
	}

	public ChartedOutputGroup setMain(Operation operation) {
		Integer indexOfOutput = getIndexOfChartableOutput(operation);
		return setMain(operation, indexOfOutput);
	}

	private ChartedOutputGroup setMain(Operation operation, Integer indexOfOutput) {
		if (indexOfOutput != -1) {

			Output output = gatheredChartableOutputs.get(indexOfOutput);
			OutputDescr chartedDesrc = output.getChartedDescription();
			if (chartedDesrc != null) {
				chartedDesrc.maskType(Type.MAIN);
			} else {
				ChartedOutputGroup chartedOutputGroup = new ChartedOutputGroup(operation, indexOfOutput);
				chartedOutputGroups.add(chartedOutputGroup);
				chartedDesrc = chartedOutputGroup.getThisDescription();
				output.setChartedDescription(chartedDesrc);
			}
			return chartedDesrc.getContainer();

		} else {
			throw new RuntimeException("No historical output found available to display charted output. The main output must be a DoubleMapOperation: "+operation.getClass()+" for "+operation);
		}
	}

	public Integer getIndexOfChartableOutput(Operation operation) {
		return gatheredChartableOutputs.indexOf(new Output(new OutputReference(operation)));
	}

	public Integer getIndexOfChartableOutput(OutputReference outputRef) {
		return gatheredChartableOutputs.indexOf(new Output(outputRef));
	}

	public List<ChartedOutputGroup> getChartedOutputGroups() {
		return chartedOutputGroups;
	}

	public void addChartInfoForSignal(ChartedOutputGroup mainChartedGrp, Operation operation) {

		Integer indexOfOutput = getIndexOfChartableOutput(operation);
		if (indexOfOutput != -1) {
			Output output = gatheredChartableOutputs.get(indexOfOutput);
			OutputDescr chartedDescr = output.getChartedDescription();
			if (chartedDescr != null) {
				//Merge mainChartedGrp and existingChartedGrp if <> + maskType
				ChartedOutputGroup existingChartedGrp = chartedDescr.getContainer();
				if (existingChartedGrp.equals(mainChartedGrp)) {
					chartedDescr.maskType(Type.SIGNAL);
				} else {
					OutputDescr existingChartedDesrc = existingChartedGrp.getThisDescription();
					existingChartedDesrc.maskType(Type.BOTH);
					mainChartedGrp.mvComponentInThisGrp(existingChartedGrp.getThisReference(), existingChartedDesrc);
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


	public void addChartInfoForAdditonalOutputs(Operation operand, Map<String, Type> outputTypes, String outputQualifier) {
		Integer indexOfMain = getIndexOfChartableOutput(new OutputReference(operand, outputQualifier));
		addChartInfoForAdditonalOutputs(operand, outputTypes, indexOfMain);
	}

	public void addChartInfoForAdditonalOutputs(Operation operand, Map<String, Type> outputTypes) {
		Integer indexOfMain = 0;
		if (operand instanceof ChartableWithMain) {
			indexOfMain = getIndexOfChartableOutput(operand.getOperands().get(((ChartableWithMain)operand).mainInputPosition()));
		}
		addChartInfoForAdditonalOutputs(operand, outputTypes, indexOfMain);
	}

	private void addChartInfoForAdditonalOutputs(Operation operand, Map<String, Type> outputTypes, Integer indexOfMain) {
		Output output = gatheredChartableOutputs.get(indexOfMain);
		OutputDescr chartedDesrc = output.getChartedDescription();
		if (chartedDesrc != null) {
			ChartedOutputGroup mainChartedGroup = chartedDesrc.getContainer();
			for (String outputKey : outputTypes.keySet()) {
				Integer indexOfOutput = getIndexOfChartableOutput(new OutputReference(operand, outputKey));
				mainChartedGroup.addAdditonalOutput(outputKey, operand, indexOfOutput, outputTypes.get(outputKey));
			}
		} else {
			throw new RuntimeException("Multi Output Main group (at index "+indexOfMain+") not found not found for " + operand);
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
			if (outputData instanceof ChartableMapValue) {
				header = header + output.getOutputReference().getReference()+",";
				Set<Date> keySet = ((ChartableMapValue)outputData).getValue(this).keySet();
				allKeys.addAll(keySet);
			}
		}
		System.out.println(header);

		for (Date date : allKeys) {
			String line = date + ",";
			for (Output output : gatheredChartableOutputs) {
				Value<?> outputData = output.getOutputData();
				if (outputData instanceof ChartableMapValue) {
					line = line + ((ChartableMapValue)outputData).getValue(this).get(date) + ",";
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

}
