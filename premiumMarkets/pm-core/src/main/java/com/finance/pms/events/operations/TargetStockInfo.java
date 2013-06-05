package com.finance.pms.events.operations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.OutputDescr;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.calculation.parametrizedindicators.OutputReference;
import com.finance.pms.events.operations.conditional.BooleanMultiMapValue;
import com.finance.pms.events.operations.conditional.ChartableCondition;
import com.finance.pms.events.operations.nativeops.StockOperation;

public class TargetStockInfo {
	
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
			result = prime * result + getOuterType().hashCode();
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
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (outputReference == null) {
				if (other.outputReference != null)
					return false;
			} else if (!outputReference.equals(other.outputReference))
				return false;
			return true;
		}

		private TargetStockInfo getOuterType() {
			return TargetStockInfo.this;
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
	
	Stock stock;
	Date startDate;
	Date endDate;
	private String analysisName;
	
	List<Output> gatheredOutputs;
	List<ChartedOutputGroup> chartedOutputGroups;
	
	public TargetStockInfo(String analysisName, Stock stock, Date startDate, Date endDate) {
		super();
		this.analysisName = analysisName;
		this.stock = stock;
		this.startDate = startDate;
		this.endDate = endDate;
		
		this.gatheredOutputs = new ArrayList<TargetStockInfo.Output>();
		this.chartedOutputGroups = new ArrayList<ChartedOutputGroup>();
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
	
	public Value<?> checkAlreadyCalculated(Operation operation) {
		if (operation.getFormula() == null && !(operation instanceof StockOperation)) return null;
		int indexOf = gatheredOutputs.indexOf(new Output(new OutputReference(operation)));
		if (indexOf == -1) {
			return null;
		}
		return gatheredOutputs.get(indexOf).outputData ;
	}
	
	public void addOutput(Operation operation, Value<?> output) {
		
		Value<?> alreadyCalculated = checkAlreadyCalculated(operation);
		if (alreadyCalculated != null) return;
		if (output instanceof BooleanMultiMapValue) {
			for (String outputKey: ((BooleanMultiMapValue) output).getAdditionalOutputs().keySet()) {
				this.gatheredOutputs.add(new Output(new OutputReference(operation, outputKey), ((BooleanMultiMapValue) output).getAdditionalOutputs().get(outputKey)));
			}
		} else {
			this.gatheredOutputs.add(new Output(new OutputReference(operation), output));
		}
		
	}
	
	public ChartedOutputGroup setMain(Operation operation) {

		Integer indexOfOutput = getIndexOfOutput(operation);
		if (indexOfOutput != -1) {
			
			Output output = gatheredOutputs.get(indexOfOutput);
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
			throw new RuntimeException("No historical output not found for "+operation);
		}
	}
	
	public Integer getIndexOfOutput(Operation operation) {
		return gatheredOutputs.indexOf(new Output(new OutputReference(operation)));
	}
	
	public Integer getIndexOfOutput(OutputReference outputRef) {
		return gatheredOutputs.indexOf(new Output(outputRef));
	}

	public List<Output> getGatheredOutputs() {
		return gatheredOutputs;
	}

	public List<ChartedOutputGroup> getChartedOutputGroups() {
		return chartedOutputGroups;
	}

	public void addSignal(ChartedOutputGroup mainChartedGrp, Operation operation) {
		
		Integer indexOfOutput = getIndexOfOutput(operation);
		if (indexOfOutput != -1) {
			Output output = gatheredOutputs.get(indexOfOutput);
			OutputDescr chartedDesrc = output.getChartedDescription();
			if (chartedDesrc != null) {
				//Merge mainChartedGrp and existingChartedGrp if <> + maskType
				ChartedOutputGroup existingChartedGrp = chartedDesrc.getContainer();
				if (existingChartedGrp.equals(mainChartedGrp)) {
					chartedDesrc.maskType(Type.SIGNAL);
				} else {
					OutputDescr existingChartedDesrc = existingChartedGrp.getThisDescription();
					existingChartedDesrc.maskType(Type.BOTH);
					mainChartedGrp.mvComponentIn(existingChartedGrp.getThisReference(), existingChartedDesrc);
					for (OutputReference oldContentRef : existingChartedGrp.getComponents().keySet()) {
						OutputDescr oldOutputDescr = existingChartedGrp.getComponents().get(oldContentRef);
						mainChartedGrp.mvComponentIn(oldContentRef, oldOutputDescr);
					}
					this.chartedOutputGroups.remove(this.chartedOutputGroups.indexOf(existingChartedGrp));
				}
			} else {
				mainChartedGrp.addSignal(operation, indexOfOutput);
			}
		
		} else {
			throw new RuntimeException("Output not found for "+operation);
		}
	}
	
	public void addAdditonalOutputs(Operation operand, Map<String, Type> outputTypes) {
		
		Integer indexOfMainOutput = getIndexOfOutput(operand.getOperands().get(((ChartableCondition)operand).mainPosition()));
		Output output = gatheredOutputs.get(indexOfMainOutput);
		OutputDescr chartedDesrc = output.getChartedDescription();
		if (chartedDesrc != null) {
			ChartedOutputGroup mainChartedGroup = chartedDesrc.getContainer();
			//Type type = null;
			for (String outputKey : outputTypes.keySet()) {
				Integer indexOfOutput = getIndexOfOutput(new OutputReference(operand, outputKey));
				//type = (type == null)?Type.MULTI:Type.MULTISIGNAL;
				mainChartedGroup.addAdditonalOutput(outputKey, operand, indexOfOutput, outputTypes.get(outputKey));
			}
		} else {
			throw new RuntimeException("Multi Output Main group not found not found for "+operand);
		}
		
	}

	@Override
	public String toString() {
		return "TargetStockInfo [stock=" + stock + ", startDate=" + startDate + ", endDate=" + endDate + ", analysisName=" + analysisName + "]";
	}



}
