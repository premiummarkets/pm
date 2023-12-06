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
package com.finance.pms.events.operations.nativeops.talib;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.parameterized.ParameterizedOperationBuilder;
import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;

@Service("talibOperationGenerator")
public class TalibOperationGenerator {

	class ConstantNameNType {
		String name;
		Class<?> type;
		public ConstantNameNType(String name, Class<?> type) {
			super();
			this.name = name;
			this.type = type;
		}
	}


	private static MyLogger LOGGER = MyLogger.getLogger(TalibOperationGenerator.class);

	Map<String,String> talibDescription;
	Map<String,List<String>> talibSignatures;

	public TalibOperationGenerator() {
		super();
	}

	public Map<String, TalibGenericOperation> generate() {

		Map<String, TalibGenericOperation> genericOperations = new HashMap<String, TalibGenericOperation>();

		Method[] methods = Core.class.getMethods();

		for (Method method : methods) {

			//Filter out
			if (method.getName().matches("TA_INT_.*")) continue;

			//Generate
			try {
				Class<?> returnType = method.getReturnType();
				if (returnType.equals(RetCode.class)) {

					List<ConstantNameNType> inConstantsNames = new ArrayList<>();
					List<String> inDataNames = new ArrayList<String>();
					ArrayList<String> outDataNames = new ArrayList<String>();

					List<String> params = talibSignatures.get(method.getName());

					int paramShift = 0;
					Class<?>[] parameterTypes = method.getParameterTypes();
					if (parameterTypes[paramShift++].equals(Integer.TYPE) && parameterTypes[paramShift++].equals(Integer.TYPE) ) {

						//inData
						while (paramShift < parameterTypes.length) {
							if (parameterTypes[paramShift].isArray() && parameterTypes[paramShift].getComponentType().equals(Double.TYPE)) {
								inDataNames.add(addParam(params, paramShift, "inData"));
							} 
							else if (parameterTypes[paramShift].equals(Integer.TYPE) || parameterTypes[paramShift].equals(Double.TYPE)) {
								//first constant, end inData
								break;
							}
							else if (parameterTypes[paramShift].equals(MInteger.class)) {
								//first MInteger, No constant, end inData
								break;
							}
							else {
								throw new UnsupportedOperationException(parameterTypes[paramShift] + " is neither Double[] or Integer");
							}
							paramShift++;
						}

						//inConstants
						while (paramShift < parameterTypes.length) {
							if (parameterTypes[paramShift].equals(Integer.TYPE)  || parameterTypes[paramShift].equals(Double.TYPE) || parameterTypes[paramShift].equals(MAType.class)) {
								inConstantsNames.add(new ConstantNameNType(addParam(params, paramShift, "inConstant"), parameterTypes[paramShift]));
							} 
							else if (parameterTypes[paramShift].equals(MInteger.class)) {
								//first MInteger, end inConstants
								break;
							}
							else {
								throw new UnsupportedOperationException(parameterTypes[paramShift] + " is not Integer, MAType or MInteger");
							}
							paramShift++;
						}

						if (parameterTypes[paramShift++].equals(MInteger.class) && parameterTypes[paramShift++].equals(MInteger.class)) {//We skip Two MIntegers (outBegIdx and outNBElement)

							while (paramShift < parameterTypes.length) {
								if (parameterTypes[paramShift].isArray() && parameterTypes[paramShift].getComponentType().equals(Double.TYPE)) { //Double output
									String addParam = addParam(params, paramShift, "outData");
									//if (!addParam.equals("Real")) outDataNames.add(addParam);
									outDataNames.add(addParam);
								} 
								else if (parameterTypes[paramShift].isArray() && parameterTypes[paramShift].getComponentType().equals(Integer.TYPE)) { //Integer output
									String addParam = addParam(params, paramShift, "outData");
									//if (!addParam.equals("Integer")) outDataNames.add(addParam);
									outDataNames.add(addParam);
								}
								else {
									throw new UnsupportedOperationException(parameterTypes[paramShift] + " is not Double[]");
								}
								paramShift++;
							}

						}

						String desrc = method.getName();
						if (talibDescription.containsKey(method.getName().toLowerCase())) {
							desrc = talibDescription.get(method.getName().toLowerCase());
						} else {
							desrc = ParameterizedOperationBuilder.readableCamelCase(desrc);
						}
						TalibGenericOperation genericOperation = new TalibGenericOperation(method.getName(), desrc + " (a TAlib Operation)", method, inConstantsNames, inDataNames, outDataNames);
						genericOperations.put(method.getName(), genericOperation);

					}

				}

			} catch (UnsupportedOperationException e) {
				if (!e.getMessage().contains("class [F is neither")) {
					LOGGER.warn("Ignored talib entry : "+method.getName() + " cause : "+e.getMessage());
				} else {
					if (LOGGER.isDebugEnabled()) LOGGER.debug("Ignored Float talib entry : "+method.getName() + " cause : "+e.getMessage());
				}
			}
		}

		return genericOperations;
	}

	protected String addParam(List<String> params, int paramShift, String fallBack) {
		if (params != null) {
			return params.get(paramShift);
		} else {
			return fallBack + paramShift;
		}
	}


	public void initSynoData() {

		talibDescription = new HashMap<String, String>();
		talibSignatures = new HashMap<String, List<String>>();

		try {

			{
				BufferedReader descriptionBR = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/talibdescriptions.txt")));
				String dline = null;
				while ((dline = descriptionBR.readLine()) != null) {
					dline = dline.replaceAll("[ ]+"," ");
					String[] lineSplit = dline.split(" ");
					if (lineSplit.length >= 2) talibDescription.put(lineSplit[0].toLowerCase(), dline.substring(dline.indexOf(" ")).trim());
				}
			}

			{
				BufferedReader signaturesBR = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/talibsignatures.txt")));
				String sline = null;
				while ((sline = signaturesBR.readLine()) != null) {
					sline = sline.trim();
					if (sline.startsWith("public RetCode")) {
						List<String> signatureArray = new ArrayList<String>();
						sline = sline.replaceAll("[ ]+"," ");
						String[] lineSplit = sline.split(" ");
						if (lineSplit.length >= 3) {
							String methodName = lineSplit[2].replace("(", "").trim();
							int paramShift = 0;
							for (int i = 3; i < lineSplit.length; i++) {
								if (lineSplit[i].contains(",")){
									signatureArray.add(null);
									if (sline.contains("double") || sline.contains("float") || sline.contains("int") || sline.contains("MAType")) {
										String[] paramSplit = sline.substring(sline.indexOf("(")+1).trim().split(" ");
										String param = paramSplit[1].replaceAll("[,)\\[\\]]", "");
										if (param.equals("inReal")) param = "historical data";
										if (param.startsWith("optIn")) param = param.substring(5);
										if (param.startsWith("in")) param = param.substring(2);
										if (param.startsWith("out")) param = param.substring(3);
										signatureArray.set(paramShift,param.trim());
									}
									paramShift++;
								}
							}

							while ((sline = signaturesBR.readLine()) != null && !sline.contains("{")) {
								signatureArray.add(null);
								if (sline.contains("double") || sline.contains("float") || sline.contains("int") || sline.contains("MAType")) {
									String[] paramSplit = sline.trim().split(" ");
									String param = paramSplit[1].replaceAll("[,)\\[\\]]", "");
									if (param.equals("inReal")) param = "historical data";
									if (param.startsWith("optIn")) param = param.substring(5);
									if (param.startsWith("in")) param = param.substring(2);
									if (param.startsWith("out")) param = param.substring(3);
									signatureArray.set(paramShift,param.trim());
								}
								paramShift++;
							}

							if (methodName != null && !methodName.isEmpty() && !signatureArray.isEmpty()) talibSignatures.put(methodName, signatureArray);
						}

					}
				}
			}

		} catch (Exception e) {
			LOGGER.error(e,e);
		}

	}

}
