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
package com.finance.pms.events.calculation;

import java.awt.Color;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.calculation.parametrizedindicators.OutputDescr;
import com.finance.pms.threads.ConfigThreadLocal;


public class EventDefDescriptorStatic implements EventDefDescriptor {

	public static MyLogger LOGGER = MyLogger.getLogger(EventDefDescriptorStatic.class);

	public static Color[] STATIC_COLORS = new Color[] {Color.BLACK, Color.ORANGE, Color.GREEN, Color.RED, Color.YELLOW, Color.BLUE};

	String mainIndicator;
	String secondIndicator;
	String thirdIndicator;
	String signalLine;
	String lowerThreshold;
	String upperThreshold;

	private List<Color> colors;
	private LinkedHashMap<String, Type> descriptionMap;


	String bearishDescription;
	String bullishDescription;
	private String[] bearishParamsMethods;
	private String[] bullishParamsMethods;

	String formattedBearishDescription;
	String formattedBullishDescription;

	private String htmlBearishDescription;
	private String htmlBullishDescription;

	private String[] otherIndicators;


	public EventDefDescriptorStatic(
			String mainIndicator, String secondIndicator, String thirdIndicator, String signalLine, String lowerThreshold, String upperThreshold, 
			String bearishDescription, String[] bearishParamsMethods, String bullishDescription,  String[] bullishParamsMethods, String... otherIndicators) {
		super();

		this.mainIndicator = mainIndicator;
		this.secondIndicator = secondIndicator;
		this.thirdIndicator = thirdIndicator;
		this.signalLine = signalLine;
		this.lowerThreshold = lowerThreshold;
		this.upperThreshold = upperThreshold;

		this.bearishDescription = bearishDescription;
		this.bearishParamsMethods = bearishParamsMethods;

		this.bullishDescription = bullishDescription;
		this.bullishParamsMethods = bullishParamsMethods;

		this.otherIndicators = otherIndicators;

		initLists();
	}

	@Override
	public String getFullNameFor(int outputIdx) throws NoSuchElementException {
		return new ArrayList<>(descriptionMap().keySet()).get(outputIdx);
	}

	public LinkedHashMap<String, Type> descriptionMap() {
		if (descriptionMap == null) {
			initLists();
		}
		return descriptionMap;
	}

	private void initLists() {
		descriptionMap = new LinkedHashMap<>();
		colors = new ArrayList<>();
		int i = 0;
		if (mainIndicator != null) {
			descriptionMap.put(mainIndicator, Type.MAIN);
			colors.add(STATIC_COLORS[i]);
		}
		i++;
		if (secondIndicator != null) {
			descriptionMap.put(secondIndicator, Type.MULTI);
			colors.add(STATIC_COLORS[i]);
		}
		i++;
		if (thirdIndicator != null) {
			descriptionMap.put(thirdIndicator, Type.MULTI);
			colors.add(STATIC_COLORS[i]);
		}
		i++;
		if (signalLine != null) {
			descriptionMap.put(signalLine, Type.SIGNAL);
			colors.add(STATIC_COLORS[i]);
		}
		i++;
		if (lowerThreshold != null) {
			descriptionMap.put(lowerThreshold, Type.CONSTANT);
			colors.add(STATIC_COLORS[i]);
		}
		i++;
		if (upperThreshold != null) {
			descriptionMap.put(upperThreshold, Type.CONSTANT);
			colors.add(STATIC_COLORS[i]);
		}

		if (otherIndicators != null) {
			for (int j = 0; j <  otherIndicators.length; j++) {
				descriptionMap.put(otherIndicators[j], Type.MULTISIGNAL);
				colors.add(STATIC_COLORS[3]);
			}
		}
	}

	@Override
	public String getHtmlBearishDescription() {
		if (htmlBearishDescription == null) htmlBearishDescription = initHtmlDescription(getBearishDescription());
		return htmlBearishDescription;
	}

	@Override
	public String getHtmlBullishDescription() {
		if (htmlBullishDescription == null) htmlBullishDescription = initHtmlDescription(getBullishDescription());
		return htmlBullishDescription;
	}

	private String initHtmlDescription(String description) {
		return StringEscapeUtils.escapeHtml(description).replace("\n", "<br>");
	}

	@Override
	public Color getColor(int i) {
		return colors.get(i);
	}

	@Override
	public Boolean displayValues() {
		return true;
	}


	@Override
	public int getGroupIndexFor(int i) {
		return 0;
	}


	@Override
	public int getGroupsCount() {
		return 1;
	}


	@Override
	public Integer[] getOutputIndexesForGroup(int j) {
		List<Integer> ret = new ArrayList<>();
		for (int i = 0; i < descriptionMap().size(); i++) {
			ret.add(i);
		}
		return ret.toArray(new Integer[0]);
	}


	private Object[] configParams(String[] paramsMethods) {

		if (paramsMethods == null || paramsMethods.length < 2 ) return new Object[0];

		Config config;
		try {
			config = ConfigThreadLocal.get(paramsMethods[0]);

			Object[] paramsValues =  new Object[paramsMethods.length-1];
			for (int i = 1; i < paramsMethods.length; i++) {
				try {
					Method method = config.getClass().getMethod("get"+paramsMethods[i]);
					paramsValues[i-1] = method.invoke(config);
				} catch (Exception e) {
					LOGGER.error(e,e);
				}
			}
			return paramsValues;
		} catch (Exception e1) {
			LOGGER.error(e1);
		}
		return null;

	}

	public String getBearishDescription() {
		if (formattedBearishDescription == null) formattedBearishDescription = initDescription(bearishDescription, bearishParamsMethods);
		return formattedBearishDescription;
	}


	public String getBullishDescription() {
		if (formattedBullishDescription == null) formattedBullishDescription = initDescription(bullishDescription, bullishParamsMethods);
		return formattedBullishDescription;
	}


	private String initDescription(String description, String[] paramsMethods) {
		Object[] paramsValues = (paramsMethods != null)?configParams(paramsMethods):new Object[0];
		if (paramsValues != null) return String.format(description, paramsValues);
		return description;
	}


	@Override
	public Integer[] getThresholdsIdx(int groupIdx) {
		return new Integer[0];
	}


	@Override
	public String getMainLabelForGroup(int groupIdx) {
		return (mainIndicator != null) ? mainIndicator : "";
	}
	
	@Override
	public Set<OutputDescr> all100OutputDescr() {
		return allOutputDescr();
	}

	@Override
	public Set<OutputDescr> allOutputDescr() {
		return new HashSet<>();
	}

	@Override
	public Set<OutputDescr> displayedOutputsDescr() {
		return new HashSet<>();
	}


	@Override
	public boolean isDisplayed(int outputIdx) {
		return true;
	}


	@Override
	public String getExportBaseFileName() {
		return "static";
	}


	@Override
	public String getGroupFullDescriptionFor(int groupIndex) {
		return descriptionMap.toString();
	}

}
