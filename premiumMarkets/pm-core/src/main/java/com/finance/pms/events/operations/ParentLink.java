package com.finance.pms.events.operations;

public class ParentLink {
	
	int paramPosision;
	Operation parent;
	
	public ParentLink(Operation parent, int paramPosision) {
		super();
		this.paramPosision = paramPosision;
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "ParentLink [paramPosision=" + paramPosision + ", parent=" + parent + "]";
	}

	public Operation getParent() {
		return parent;
	}

	public int getParamPosision() {
		return paramPosision;
	}
}