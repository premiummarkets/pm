package com.finance.pms.events;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

public class EventDefDescriptor {
	
	String mainIndicator;
	String bearishIndicator;
	String bullishIndicator;
	String signalLine;
	String lowerThreshold;
	String upperThreshold;
	
	String bearishDescription;
	String bullishDescription;
	
	
	public EventDefDescriptor(String mainIndicator, String bearishIndicator,String bullishIndicator, String signalLine, String lowerThreshold, String upperThreshold, String bearishDescription, String bullishDescription) {
		super();
		this.mainIndicator = mainIndicator;
		this.bearishIndicator = bearishIndicator;
		this.bullishIndicator = bullishIndicator;
		this.signalLine = signalLine;
		this.lowerThreshold = lowerThreshold;
		this.upperThreshold = upperThreshold;
		
		this.bearishDescription = bearishDescription;
		this.bullishDescription = bullishDescription;

	}


	public String[] descriptionArray() {
		
		List<String> retList = new ArrayList<String>();
		retList.add(mainIndicator);
		retList.add(bearishIndicator);
		retList.add(bullishIndicator);
		retList.add(signalLine);
		retList.add(lowerThreshold);
		retList.add(upperThreshold);
	
		return retList.toArray(new String[]{});
	}


	public String getBearishDescription() {
		return bearishDescription;
	}
	
	public String getHtmlBearishDescription() {
		return StringEscapeUtils.escapeHtml(bearishDescription);
	}


	public String getBullishDescription() {
		return bullishDescription;
	}
	
	public String getHtmlBullishDescription() {
		return  StringEscapeUtils.escapeHtml(bullishDescription);
	}
	
	
	

}
