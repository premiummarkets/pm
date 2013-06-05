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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + paramPosision;
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
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
		ParentLink other = (ParentLink) obj;
		if (paramPosision != other.paramPosision)
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		return true;
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