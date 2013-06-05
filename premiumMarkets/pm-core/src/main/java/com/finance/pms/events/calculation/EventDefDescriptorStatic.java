package com.finance.pms.events.calculation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;


public class EventDefDescriptorStatic implements EventDefDescriptor {
	
	public static Color[] STATIC_COLORS = new Color[] {Color.BLACK,Color.ORANGE,Color.GREEN,Color.RED,Color.YELLOW,Color.BLUE};
	
	String mainIndicator;
	String secondIndicator;
	String thirdIndicator;
	String signalLine;
	String lowerThreshold;
	String upperThreshold;
	
	String bearishDescription;
	String bullishDescription;

	private List<Color> colors;
	private String[] descriptionArrays;
	
	
	public EventDefDescriptorStatic(String mainIndicator, String secondIndicator, String thirdIndicator, String signalLine, String lowerThreshold, String upperThreshold, String bearishDescription, String bullishDescription) {
		super();
		this.mainIndicator = mainIndicator;
		this.secondIndicator = secondIndicator;
		this.thirdIndicator = thirdIndicator;
		this.signalLine = signalLine;
		this.lowerThreshold = lowerThreshold;
		this.upperThreshold = upperThreshold;
		
		this.bearishDescription = bearishDescription;
		this.bullishDescription = bullishDescription;

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
		return StringEscapeUtils.escapeHtml(bearishDescription).replace("\n", "<br>");
	}

	
	public String getHtmlBullishDescription() {
		return  StringEscapeUtils.escapeHtml(bullishDescription).replace("\n", "<br>");
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
	public Integer[] getIndexesForGroup(int j) {
		String[] descriptionArray = descriptionArray();
		List<Integer> ret = new ArrayList<Integer>();
		for (int i = 0; i < descriptionArray.length; i++) {
				ret.add(i);
		}
		return ret.toArray(new Integer[0]);
	}


	public String getBearishDescription() {
		return bearishDescription;
	}


	public String getBullishDescription() {
		return bullishDescription;
	}
	

}
