/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.operations.parameterized;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Value;

@XmlRootElement
public class ParametersHolder {

	@XmlElement(name="parameter")
	private List<Value<?>> parameters;

	public ParametersHolder() {
		super();
		this.parameters = new ArrayList<Value<?>>();
	}

	public ParametersHolder(Value<?>... parameters) {
		super();
		this.parameters = new ArrayList<Value<?>>();
		for (Value<?> parameter : parameters) {
			this.parameters.add(parameter);
		}
	}
	
//	@SuppressWarnings("unchecked")
//	public List<Value<?>> parameterizedInput(@SuppressWarnings("rawtypes") List<? extends Value> inputs) {
//		List<Value<?>> parametersNInputs = new ArrayList<Value<?>>();
//		parametersNInputs.addAll(parameters);
//		parametersNInputs.addAll((Collection<? extends Value<?>>) inputs);
//		return parametersNInputs;
//	}

	public List<Value<?>> getParameters() {
		return parameters;
	}

	@Override
	public String toString() {
		return "ParametersHolder [parameters=" + parameters + "]";
	}

	public boolean isEmpty() {
		return this.parameters.isEmpty();
	}

	public void fillParameters(List<Value<?>> additionalParams) {
		ListIterator<Value<?>> additionalParamsIt = parameters.listIterator();
		
		//Complete empty spaces
		for (int i = 0; i < this.parameters.size(); i++) {
			if (additionalParamsIt.hasNext()) {
				Value<?> addParam = additionalParamsIt.next();
				if (parameters.get(i) == null)  parameters.set(i, addParam);
			} else {
				break;
			}
		}
		//Add supplement
		while (additionalParamsIt.hasNext()) {
			Value<?> addParam = additionalParamsIt.next();
			parameters.add(addParam);
		}
	}

	public Value<?> getParameter(int i) {
		if (i >= parameters.size()) return null;
		return parameters.get(i);
	}

}
