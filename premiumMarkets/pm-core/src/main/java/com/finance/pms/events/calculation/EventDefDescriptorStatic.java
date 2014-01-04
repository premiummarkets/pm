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
package com.finance.pms.events.calculation;

import java.awt.Color;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.OutputDescr;
import com.finance.pms.threads.ConfigThreadLocal;


public class EventDefDescriptorStatic implements EventDefDescriptor {
	
	public static MyLogger LOGGER = MyLogger.getLogger(EventDefDescriptorStatic.class);
	
	public static Color[] STATIC_COLORS = new Color[] {Color.BLACK,Color.ORANGE,Color.GREEN,Color.RED,Color.YELLOW,Color.BLUE};
	
	String mainIndicator;
	String secondIndicator;
	String thirdIndicator;
	String signalLine;
	String lowerThreshold;
	String upperThreshold;

	private List<Color> colors;
	private String[] descriptionArrays;
	

	String bearishDescription;
	String bullishDescription;
	private String[] bearishParamsMethods;
	private String[] bullishParamsMethods;
	
	String formatedBearishDescription;
	String formatedBullishDescription;

	private String htmlBearishDescription;
	private String htmlBullishDescription;


	
	public EventDefDescriptorStatic(
			String mainIndicator, String secondIndicator, String thirdIndicator, String signalLine, String lowerThreshold, String upperThreshold, 
			String bearishDescription, String[] bearishParamsMethods, String bullishDescription,  String[] bullishParamsMethods) {
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

		initLists();
	}


	public String[] descriptionArray() {
		
		if (descriptionArrays == null) {
			initLists();
		}
		
		return descriptionArrays;
	}


	protected void initLists() {
		List<String> descriptionArrayList = new ArrayList<String>();
		colors = new ArrayList<Color>();
		int i = 0;
		if (mainIndicator != null) {
			descriptionArrayList.add(mainIndicator);
			colors.add(STATIC_COLORS[i]);
		}
		i++;
		if (secondIndicator != null) {
			descriptionArrayList.add(secondIndicator);
			colors.add(STATIC_COLORS[i]);
		}
		i++;
		if (thirdIndicator != null) {
			descriptionArrayList.add(thirdIndicator);
			colors.add(STATIC_COLORS[i]);
		}
		i++;
		if (signalLine != null) {
			descriptionArrayList.add(signalLine);
			colors.add(STATIC_COLORS[i]);
		}
		i++;
		if (lowerThreshold != null) {
			descriptionArrayList.add(lowerThreshold);
			colors.add(STATIC_COLORS[i]);
		}
		i++;
		if (upperThreshold != null) {
			descriptionArrayList.add(upperThreshold);
			colors.add(STATIC_COLORS[i]);
		}
		descriptionArrays = descriptionArrayList.toArray(new String[] {});
	}


	public String getHtmlBearishDescription() {
		if (htmlBearishDescription == null) htmlBearishDescription = initHtmlDescription(getBearishDescription());
		return htmlBearishDescription;
	}
	
	public String getHtmlBullishDescription() {
		if (htmlBullishDescription == null) htmlBullishDescription = initHtmlDescription(getBullishDescription());
		return htmlBullishDescription;
	}

	private String initHtmlDescription(String description) {
		return StringEscapeUtils.escapeHtml(description).replace("\n", "<br>");
	}

	
	public Color getColor(int i) {
		return colors.get(i);
	}

	@Override
	public Boolean displayValues() {
		return true;
	}


	@Override
	public int getGroupFor(int i) {
		return 0;
	}


	@Override
	public int getGroupsCount() {
		return 1;
	}


	@Override
	public Integer[] getOutputIndexesForGroup(int j) {
		String[] descriptionArray = descriptionArray();
		List<Integer> ret = new ArrayList<Integer>();
		for (int i = 0; i < descriptionArray.length; i++) {
				ret.add(i);
		}
		return ret.toArray(new Integer[0]);
	}


	private Object[] configParams(String[] paramsMethods) {

		if (paramsMethods == null || paramsMethods.length < 2 ) return new Object[0];

		Config config;
		try {
			config = (Config) ConfigThreadLocal.get(paramsMethods[0]);

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
		if (formatedBearishDescription == null) formatedBearishDescription = initDesrciption(bearishDescription, bearishParamsMethods);
		return formatedBearishDescription;
	}


	public String getBullishDescription() {
		if (formatedBullishDescription == null) formatedBullishDescription = initDesrciption(bullishDescription, bullishParamsMethods);
		return formatedBullishDescription;
	}
	
	
	private String initDesrciption( String description, String[] paramsMethods) {
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
		return (mainIndicator != null)?mainIndicator:"";
	}


	public Set<OutputDescr> displayedOutputs() {
		return new HashSet<ChartedOutputGroup.OutputDescr>();
	}


	public Set<OutputDescr> allOutputs() {
		return new HashSet<ChartedOutputGroup.OutputDescr>();
	}


	@Override
	public boolean isDisplayed(int outputIdx) {
		return true;
	}	

}
