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
