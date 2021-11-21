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
package com.finance.pms.events.calculation.antlr;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.ListOperation;
import com.finance.pms.events.operations.nativeops.MATypeOperation;
import com.finance.pms.events.operations.nativeops.MapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.StringOperation;

public class EditorOpDescr implements Comparable<EditorOpDescr>, Cloneable {

	public enum ParamType {

		//TODO MAType could just be a String as well, no need for a particular case here
		NUMBER(NumberOperation.class, "Number"), DATA (MapOperation.class, "Data"), MATYPE (MATypeOperation.class, "MAType"), STRING (StringOperation.class, "String"), LIST (ListOperation.class, "ListOperation");

		Class<? extends Operation> operandClass;
		String typeDescr;
		
		private ParamType(Class<? extends Operation> operandClass, String typeDescr) {
			this.operandClass = operandClass;
			this.typeDescr = typeDescr;
		}

		public static ParamType valueOf(Class<?  extends Operation> operandClass2) {
			for (ParamType paramType : ParamType.values()) {
				if (paramType.operandClass.isAssignableFrom(operandClass2)) return paramType;
			}
			throw new RuntimeException("No Param type assignable from operand type : " + operandClass2 + ". Available Param Types are " + EnumSet.allOf(ParamType.class));
		}

		public static ParamType valueOfTokenName(String tokenName, Set<EditorOpDescr> allOps) {

			tokenName = tokenName.split(":")[0];

			if (tokenName.equals("Number")) {
				return NUMBER;
			} else if (tokenName.equals("StockOperation")) {
				return DATA;
			} else if (tokenName.equals("MAType")) {
				return MATYPE;
			} else if (tokenName.equals("String")) {
				return STRING;
			} else if (tokenName.equals("ListOperation")) {
				return LIST;
			}
			else {
				for (String stockOutput : EditorLexerDelegate.HISTORICALDATA_TOKENS) {
					if (stockOutput.equals(tokenName)) return DATA;
				}
				for (EditorOpDescr editorOpDescr : allOps) {
					if (editorOpDescr.getName().equals(tokenName)) return DATA;
					if (editorOpDescr.getName().startsWith(tokenName)) return null;
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
		private Boolean isVarArgs;


		public Param(String refAsOperand, Class<? extends Operation> operandClass, String paramSynoptic, String paramDescription, String defaultAsString, Boolean isVarArgs) {
			super();
			this.paramType = ParamType.valueOf(operandClass);
			this.paramName = refAsOperand;
			this.paramDescription = paramDescription;
			this.paramSynoptic = paramSynoptic;
			this.paramDefault = defaultAsString;
			this.isVarArgs = isVarArgs;
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
		//Operation with an empty list operands
		if (params != null && params.size() == 1 && params.get(0).paramName != null && params.get(0).paramName.equals("undeterministic")) {
			return true;
		}
		//Operation with trailing "varargs" operand
		else if (params != null) {
			for(int i = 0; i < params.size(); i++) {
				if (params.get(i).paramName != null && params.get(i).isVarArgs) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Only last param can be a varArgs?
	 * @param currentParamPos
	 * @return
	 */
	public Param getCurrentParamOrVarArg(Integer currentParamPos) {
		//Empty list operands
		if (params != null && params.size() == 1 && params.get(0).paramName != null && params.get(0).paramName.equals("undeterministic")) {
			return params.get(0);
		}
		//Trailing "varargs" operand
		else if (params != null) {
			int i = 0;
			for(; i < params.size(); i++) {
				if (params.get(i).paramName != null && params.get(i).isVarArgs) {
					break; //varArg position
				}
			}
			if (currentParamPos < i) {//current position is not the varArg yet
				return params.get(currentParamPos);
			} else {//We passed the first varArg
				return params.get(i);
			}
		}
		return null; //No varArg (should not be here ...) throw Exception?
	}

	@Override
	public int compareTo(EditorOpDescr o) {
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
			for (int j = this.parsedParam.size(); j <= i; j++ ) {
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
