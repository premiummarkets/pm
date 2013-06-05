package com.finance.pms.events.calculation.antlr;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleOperation;

public class EditorOpDescr implements Comparable<EditorOpDescr> , Cloneable {
	
	public enum ParamType {
		
		CONSTANT(DoubleOperation.class, "Number"), DOUBLEMAP (DoubleMapOperation.class, "Data");
		
		Class<? extends Operation> operandClass;
		String typeDescr;
		
		private ParamType(Class<? extends Operation> operandClass, String typeDescr) {
			this.operandClass = operandClass;
			this.typeDescr = typeDescr;
		}
		
		public static ParamType valueOf(Class<? extends Operation> operandClass2) {
			for (ParamType paramType : ParamType.values()) {
				if (paramType.operandClass.isAssignableFrom(operandClass2)) return paramType;
			}
			throw new RuntimeException();
		}
		
		public static ParamType valueOfTokenName(String tokenName, Set<EditorOpDescr> allOps) {
			
			tokenName = tokenName.split(":")[0];
			
			if (tokenName.equals("Double")) {
				return CONSTANT;
			} else if (tokenName.equals("StockOperation")) {
				return DOUBLEMAP;
			} else {
				for (EditorOpDescr editorOpDescr : allOps) {
					if (editorOpDescr.getName().equals(tokenName)) return DOUBLEMAP;
				}
				for (String stockoutput : EditorLexerDelegate.HISTORICALDATA_TOKENS) {
					if (stockoutput.equals(tokenName)) return DOUBLEMAP;
				}
			}

			throw new RuntimeException();

		}

		public String getTypeDescr() {
			return typeDescr;
		}
	};
	
	public class Param {
		
		private ParamType paramType;
		private String paramName;
		private String paramDescription;
		private String paramSynoptic;
		private String paramDefault;
	
		
		public Param(String refAsOperand, Class<? extends Operation> operandClass, String paramSynoptic, String paramDescription, String defaultAsString) {
			super();
			this.paramType = ParamType.valueOf(operandClass);
			this.paramName = refAsOperand;
			this.paramDescription = paramDescription;
			this.paramSynoptic = paramSynoptic;
			this.paramDefault = defaultAsString;
		}

		public ParamType getParamType() {
			return paramType;
		}

		public String getParamName() {
			return paramName;
		}

		public String getParamDefault() {
			return paramDefault;
		}

		public String getParamDescription() {
			return paramDescription;
		}

		@Override
		public String toString() {
			return "Param [paramType=" + paramType + ", paramName=" + paramName + ", paramSynoptic=" + paramSynoptic + ", paramDescription=" + paramDescription+ ", paramDefault=" + paramDefault + "]";
		}

		public String getParamSynoptic() {
			return paramSynoptic;
		}
	}
	
	private String name;
	private List<Param> params;
	private List<String> outputSelectors;
	private String description;
	private String synoptic;
	private String shortSynoptic;
	
	private int nbCommasParsed;
	private List<String> parsedParam;
	
	public EditorOpDescr(String name, String description, String synoptic, String shortSynoptic) {
		super();
		this.name = name;
		this.description = description;
		this.params = new ArrayList<EditorOpDescr.Param>();
		this.parsedParam = new ArrayList<String>();
		this.synoptic = synoptic;
		this.shortSynoptic = shortSynoptic;
		
	}
	
	public void addParam(Param param) {
		this.params.add(param);
		this.parsedParam.add(null);
	}

	public String getName() {
		return name;
	}

	public List<Param> getParams() {
		return params;
	}
	
	public Boolean undeterministicParamCount() {
		if (params != null && params.size() == 1 && params.get(0).paramName != null && params.get(0).paramName.equals("undeterministic")) {
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(EditorOpDescr o) {
//		int compare = o.getName().length() - this.getName().length();
//		if (compare == 0) 
			int compare = this.getName().compareTo(o.getName());
		return compare;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		EditorOpDescr other = (EditorOpDescr) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EditorOpDescr [name=" + name + ", params=" + params + "]";
	}

	public String getDescription() {
		return description;
	}

	public String getSynoptic() {
		return synoptic;
	}

	public int getNbCommasParsed() {
		return nbCommasParsed;
	}

	public void setNbCommasParsed(int nbCommasParsed) {
		this.nbCommasParsed = nbCommasParsed;
	}

	public String getParsedParma(int i) {
		return parsedParam.get(i);
	}

	public void setLastParamParsed(int i, String parsedParam) {
		if (this.undeterministicParamCount() && this.parsedParam.size() <= i) {
			for (int j = this.parsedParam.size(); j <=i; j++ ) {
				this.parsedParam.add(null);
			}
		}
		this.parsedParam.set(i, parsedParam);

	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		EditorOpDescr clone = null;
		try {
			clone = (EditorOpDescr) super.clone();
			clone.params = new ArrayList<Param>(this.params);
			clone.parsedParam = new ArrayList<String>();
			for (int j = 0; j < clone.params.size(); j++ ) {
				clone.parsedParam.add(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clone;
	}

	public List<String> getOutputSelectors() {
		return outputSelectors;
	}

	public void setOutputSelectors(List<String> outputSelectors) {
		this.outputSelectors = outputSelectors;
	}

	public String getShortSynoptic() {
		return shortSynoptic;
	}
	
	
}
