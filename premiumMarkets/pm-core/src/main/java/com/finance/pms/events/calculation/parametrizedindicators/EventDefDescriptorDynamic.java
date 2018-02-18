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
package com.finance.pms.events.calculation.parametrizedindicators;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.EventDefDescriptor;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.OutputDescr;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;

public class EventDefDescriptorDynamic implements EventDefDescriptor {
	
	private static MyLogger LOGGER = MyLogger.getLogger(EventDefDescriptorDynamic.class);

	public static Color[][] COLORS = new  Color[][] {
	    {Color.BLACK, Color.RED, new Color(0,128,0), new Color(50,178,50)},
	    {new Color(0,139,139), new Color(220,20,60), new Color(107,142,35), new Color(157,192,85)},
	    {Color.CYAN, Color.MAGENTA, Color.GREEN, new Color(50, 49, 50)},
	    {Color.BLUE, new Color(128,0,0), new Color(128,128,0), new Color(178,178,50)}
	};
	
	private String descriptorReference;
	private String bullishDescription;
	private String bearishDescription;
	private String alsoDisplayDescription;
	
	List<ChartedOutputGroup> chartedOutputGroups;
	private String[] descripitonArrays;
	private List<OutputDescr> descripitonList;
	private int groupsCount = 0;

    private Optional<String> exportBaseFileName;
	
	public EventDefDescriptorDynamic() {
		super();
		exportBaseFileName = Optional.empty();
	}

	@Override
	public String[] descriptionArray() throws NoSuchElementException {
		if (descripitonArrays == null) {
			initLists();
		}
		return descripitonArrays;
	}

	protected void initLists() throws NoSuchElementException {
		
		initDescriptionsList();
		
		descripitonArrays = new String[descripitonList.size()];
		for (int i = 0; i < descripitonList.size(); i++) {
			descripitonArrays[i] = descripitonList.get(i).fullQualifiedName();
		}
		
	}

	protected void initDescriptionsList() {
		if (chartedOutputGroups == null) throw new java.util.NoSuchElementException("Can't refresh indicator chart for : " + descriptorReference+ ". There may be a clear in progress?");
			
		SortedSet<OutputDescr> descriptionSet = new  TreeSet<OutputDescr>(new Comparator<OutputDescr>() {
			@Override
			public int compare(OutputDescr o1, OutputDescr o2) {
				return o1.getOutputIndex().compareTo(o2.getOutputIndex());
			}
		});
		
		for (ChartedOutputGroup chartedOutputGroup : chartedOutputGroups) {
			descriptionSet.add(chartedOutputGroup.getThisDescription());
			descriptionSet.addAll(chartedOutputGroup.getComponents().values());
		}		
		descripitonList = new ArrayList<ChartedOutputGroup.OutputDescr>();
		descripitonList.addAll(descriptionSet);
	}

	@Override
	public String getHtmlBullishDescription() {
		return bullishDescription.replace("\n", "<br>").replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").replaceAll(" ", "&nbsp;");
	}

	@Override
	public String getHtmlBearishDescription() {
		return bearishDescription.replace("\n", "<br>").replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").replaceAll(" ", "&nbsp;");
	}

	public void setChartedOutputGroups(List<ChartedOutputGroup> chartedOutputGroups, ChartedOutputGroup invisibleGroup) {
		
		SortedSet<ChartedOutputGroup> sortedGroups = new TreeSet<ChartedOutputGroup>(new Comparator<ChartedOutputGroup>() {
			@Override
			public int compare(ChartedOutputGroup o1, ChartedOutputGroup o2) {
				int comparator = o2.getComponents().size() - o1.getComponents().size();
				if (comparator == 0) comparator = o2.getThisReference().compareTo(o1.getThisReference());
				return comparator;
			}
		});
		sortedGroups.addAll(chartedOutputGroups);
		
		this.chartedOutputGroups = new ArrayList<ChartedOutputGroup>(sortedGroups);
		groupsCount = chartedOutputGroups.size();
		
		if (invisibleGroup != null) {
			this.chartedOutputGroups.add(invisibleGroup);
		}
		
		try {
			initLists();
		} catch (NoSuchElementException e) {
			LOGGER.error(e,e);
		}
	}

	@Override
	public Color getColor(int outputIdx) throws NoSuchElementException {
		
		if (descripitonList == null) {
			initLists();
		}
		
		int groupIdx = getGroupFor(outputIdx);
		//int alpha = (int) (255 - 255*( ((double)groupIdx / COLORS.length))/getGroupsCount());
		//int alpha = Math.max(100, (int) (255 - 255*((double)groupIdx)/getGroupsCount()));
		int alpha = (int) (155 +  100 - 100*((double)groupIdx)/getGroupsCount());
		Color[] grpColors = COLORS[groupIdx % COLORS.length];
		
		switch (descripitonList.get(outputIdx).getType()) {
		case CONSTANT :
			return new Color(grpColors[1].getRed(), grpColors[1].getGreen(), grpColors[1].getBlue(), alpha/2);
		case SIGNAL :
			return new Color(grpColors[1].getRed(), grpColors[1].getGreen(), grpColors[1].getBlue(), alpha);
		case BOTH :
			return new Color(grpColors[2].getRed(), (grpColors[2].getGreen() + outputIdx * 10) % 256, grpColors[2].getBlue(), alpha);
		case MULTI :
			return new Color(grpColors[3].getRed(), (grpColors[3].getGreen() + outputIdx * 10) % 256, grpColors[3].getBlue(), alpha);
		case MULTISIGNAL :
			return new Color(grpColors[1].getRed(), grpColors[1].getGreen(), grpColors[1].getBlue(), alpha/4);
		default :
			return new Color(grpColors[0].getRed(), grpColors[0].getGreen(), grpColors[0].getBlue(), alpha);
		}
		
	}

	public void setDescriptorReference(String reference) {
		this.descriptorReference = reference;
	}

	@Override
	public String toString() {
		return "EventDefDescriptorDynamic [descriptorReference=" + descriptorReference + ", outputRefSet=" + chartedOutputGroups + "]";
	}

	@Override
	public Boolean displayValues() {
		return true;
	}

	@Override
	public int getGroupFor(int i) {
		ChartedOutputGroup container = descripitonList.get(i).getContainer();
		return chartedOutputGroups.indexOf(container);
	}

	@Override
	public int getGroupsCount() {
		return groupsCount;
	}

	@Override
	public Integer[] getOutputIndexesForGroup(int j) {
		ChartedOutputGroup chartedOutputGroup = chartedOutputGroups.get(j);
		Integer[] ret = new Integer[chartedOutputGroup.getComponents().size()+1];
		ret[0] = chartedOutputGroup.getThisDescription().getOutputIndex();
		int k = 1;
		for (OutputDescr outputDescr : chartedOutputGroup.getComponents().values()) {
			ret[k] = outputDescr.getOutputIndex();
			k++;
		}
		return ret;
	}

	public void setBullishDescription(String bullishDescription) {
		this.bullishDescription = bullishDescription;
	}

	public void setBearishDescription(String bearishDescription) {
		this.bearishDescription = bearishDescription;
	}

	public String getBullishDescription() {
		return bullishDescription;
	}

	public String getBearishDescription() {
		return bearishDescription;
	}

	@Override
	public Integer[] getThresholdsIdx(int grpIdx) {
		
		if (chartedOutputGroups == null) throw new java.util.NoSuchElementException("Can't refresh indicator chart for (clear in progress??) : " + descriptorReference);
		
		List<Integer> thresholdsIdxs = new ArrayList<Integer>();
		SortedSet<OutputDescr> descriptionSet = new  TreeSet<OutputDescr>(new Comparator<OutputDescr>() {
			@Override
			public int compare(OutputDescr o1, OutputDescr o2) {
				return o1.getOutputIndex().compareTo(o2.getOutputIndex());
			}
		});
		
		ChartedOutputGroup chartedOutputGroup = chartedOutputGroups.get(grpIdx);
		descriptionSet.add(chartedOutputGroup.getThisDescription());
		descriptionSet.addAll(chartedOutputGroup.getComponents().values());
		
		for (OutputDescr outputDescr : descriptionSet) {
			if (outputDescr.getType().equals(Type.CONSTANT)) {
				thresholdsIdxs.add(outputDescr.getOutputIndex());
			}
		}
		
		return thresholdsIdxs.toArray(new Integer[0]);
	}

	@Override
	public String getMainLabelForGroup(int groupIdx) {
		return chartedOutputGroups.get(groupIdx).getThisReference().getReference();
	}
	
	@Override
	public Set<OutputDescr> allOutputs() {
		
		if (descripitonList == null) {
			initLists();
		}
		
		Set<OutputDescr> ret = new TreeSet<OutputDescr>();
		for (final OutputDescr  outputDescr: descripitonList) {
			if (!outputDescr.getType().equals(Type.INVISIBLE)) ret.add(outputDescr);
		}
		return ret;
	}
	
	@Override
	public Set<OutputDescr> displayedOutputs() {
		
		Set<OutputDescr> ret = new TreeSet<OutputDescr>();
		for (final OutputDescr  outputDescr: allOutputs()) {
			if (outputDescr.getDisplayOnChart()) ret.add(outputDescr);
		}
		return ret;
		
	}

	@Override
	public boolean isDisplayed(int outputIdx) {
		
		if (descripitonList == null) {
			initLists();
		}
		
		return descripitonList.get(outputIdx).getDisplayOnChart();
	}

	public String getAlsoDisplayDescription() {
		return alsoDisplayDescription;
	}

	public void setAlsoDisplayDescription(String alsoDisplayDescription) {
		this.alsoDisplayDescription = alsoDisplayDescription;
	}

    @Override
    public String getExportBaseFileName() {
        return exportBaseFileName.orElse("");
    }
    
    public void setExportBaseFileName(String exportBaseFileName) {
        this.exportBaseFileName = Optional.of(exportBaseFileName);
    }

}
