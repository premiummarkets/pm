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

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.NumberValue;

public class ChartedOutputGroup implements Comparable<ChartedOutputGroup>, Serializable{

	private static final long serialVersionUID = 6947436277777559496L;

	public enum Type {MAIN, SIGNAL, BOTH, CONSTANT, MULTI, MULTISIGNAL, INVISIBLE}; //BOTH is MAIN & SIGNAL

	private OutputReference thisGroupMainOutputReference;
	private OutputDescr thisGroupMainOutputDescription;
	private Map<OutputReference, OutputDescr> components;
	
	private String groupStatus;

	private UUID uuid;

	public ChartedOutputGroup(TargetStockInfo targetStock, List<StackElement> thisCallStack, Operation mainOperation, String userOperationReference, Optional<String> outputSelector, int outputIndex, Optional<String> groupStatus, Boolean displayByDefault) {
		uuid = UUID.randomUUID();
		OutputReference outputReference = new OutputReference(targetStock, thisCallStack, mainOperation, userOperationReference, outputSelector.orElse(mainOperation.getOutputSelector()));
		thisGroupMainOutputDescription = new OutputDescr(outputReference, this, Type.MAIN, outputIndex, null, displayByDefault);
		thisGroupMainOutputReference = outputReference;
		components = new HashMap<>();
		
		this.groupStatus = groupStatus.orElse("");
		
	}

	public OutputDescr addSignal(TargetStockInfo targetStock, List<StackElement> thisCallStack, Operation operation, String userOperationReference, int outputIndex, Boolean displayByDefault) {
		OutputReference outputReference = new OutputReference(targetStock, thisCallStack, operation, userOperationReference, operation.getOutputSelector());
		OutputDescr outputDescr = new OutputDescr(outputReference, this, Type.SIGNAL, outputIndex, null, displayByDefault);
		this.components.put(outputReference, outputDescr);
		return outputDescr;
	}

	public void addConstant(TargetStockInfo targetStock, List<StackElement> thisCallStack, String parentReference, Operation operation, String userOperationReference, NumberValue doubleValue, Boolean displayByDefault) {
		String referenceAsOperandOverride = parentReference + " " + operation.getReferenceAsOperand();
		OutputReference outputReference = new OutputReference(targetStock, thisCallStack, operation, userOperationReference, referenceAsOperandOverride, doubleValue);
		this.components.put(outputReference, new OutputDescr(outputReference, this, Type.CONSTANT, null, doubleValue, displayByDefault));
	}

	public void addAdditionalOutput(TargetStockInfo targetStock, List<StackElement> thisCallStack, String outputKey, Operation operation, String userOperationReference, int outputIndex, Type type) {
		OutputReference outputReference = new OutputReference(targetStock, thisCallStack, operation, userOperationReference, outputKey);
		this.components.put(outputReference, new OutputDescr(outputReference, this, type, outputIndex, null, false));
	}

	public OutputDescr mvComponentInThisGrp(OutputReference outputRef, OutputDescr outputDescr) {
		outputDescr.setContainer(this);
		this.components.put(outputRef, outputDescr);
		return outputDescr;
	}

	public Map<OutputReference, OutputDescr> getComponents() {
		return components;
	}

	public OutputDescr getThisGroupMainOutputDescription() {
		return thisGroupMainOutputDescription;
	}

	public UUID groupUniqueId() {
		return uuid;
	}

	public OutputReference getThisGroupMainOutputReference() {
		return thisGroupMainOutputReference;
	}
	
	public String getGroupStatus() {
		return groupStatus;
	}

	@Override
	public String toString() {
		return "ChartedOutputGroup [\n\t uuid=" + uuid + ", \n\t thisDescription=" + thisGroupMainOutputDescription + ",\n" +
				"\t components=\n\t\t" + components.values().stream().map(od -> od.toString()).reduce((r,e) -> r + "\n\t\t" + e).orElse("None") + "]";
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

	@Override
	public int compareTo(ChartedOutputGroup o) {
		return this.uuid.compareTo(o.uuid);
	}

}
