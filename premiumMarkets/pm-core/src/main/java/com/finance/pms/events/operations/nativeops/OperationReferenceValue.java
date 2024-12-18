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
package com.finance.pms.events.operations.nativeops;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.antlr.MissingReferenceException;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.parameterized.ParameterizedOperationBuilder;

public class OperationReferenceValue<T extends Operation> extends Value<T> implements StringableValue, Cloneable  {
	
	private static final long serialVersionUID = -6241787631502004630L;

	protected static MyLogger LOGGER = MyLogger.getLogger(OperationReferenceValue.class);
	
	private T operation;
	private Boolean isUsedAsClone = false;

	public OperationReferenceValue(T operation) {
		super();
		this.operation = operation;
	}
	
	@SuppressWarnings("unchecked")
	public OperationReferenceValue(String operationReference) {
		super();
		operationReference = operationReference.replaceAll("\\$", "");
		String[] referenceSplit = operationReference.split(":");
		
		ParameterizedOperationBuilder parameterizedOperationBuilder = SpringContext.getSingleton().getBean(ParameterizedOperationBuilder.class);
		Operation nativeOperation = parameterizedOperationBuilder.getNativeOperations().get(referenceSplit[0]);
		T opClone;
		if (nativeOperation != null) {
			opClone = (T) nativeOperation.clone();
		} else {
			Operation userOperation = parameterizedOperationBuilder.getUserCurrentOperations(false).get(referenceSplit[0]);
			if (userOperation != null) {
				opClone = (T) userOperation.clone();
			} else {
				String message = "Reference not resolved yet in leaf value: " + referenceSplit[0];
				LOGGER.warn(message);
				throw new MissingReferenceException(referenceSplit[0], message);
			}
		}
		
		if (referenceSplit.length > 1) opClone.setOutputSelector(referenceSplit[1]);
		
		this.operation = opClone;
	}
	
	public Boolean getIsUsedAsClone() {
		return isUsedAsClone;
	}

	public void setIsUsedAsClone(Boolean isUsedAsClone) {
		this.isUsedAsClone = isUsedAsClone;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getValue(TargetStockInfo targetStock) {
		if (isUsedAsClone) return (T) operation.clone();
		return operation;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": operation " + ((operation != null)?operation.toString():"unknown yet");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		try {
			OperationReferenceValue<T> clone = (OperationReferenceValue<T>) super.clone();
			clone.operation = (T) this.operation.clone();
			return clone;
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return null;
	}

	@Override
	public String getAsStringable() {
		return "$" + operation.getReference() + "$";
	}

}
