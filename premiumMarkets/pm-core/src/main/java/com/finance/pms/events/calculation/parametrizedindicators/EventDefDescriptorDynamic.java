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
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.EventDefDescriptor;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;

/**
 * Describe an event info. Its content as to be linked to the event info as defined by the formulae and should not contain calculated information.
 */
public class EventDefDescriptorDynamic implements EventDefDescriptor {

	private static MyLogger LOGGER = MyLogger.getLogger(EventDefDescriptorDynamic.class);

	public static Color[][] COLORS = new  Color[][] {
		{Color.BLACK, Color.RED, new Color(0,128,0), new Color(50,178,50)},
		{new Color(0,139,139), new Color(220,20,60), new Color(107,142,35), new Color(157,192,85)},
		{Color.CYAN, Color.MAGENTA, Color.GREEN, new Color(50, 49, 50)},
		{Color.BLUE, new Color(128,0,0), new Color(128,128,0), new Color(178,178,50)}
	};
	private double[] randoms;

	private String descriptorReference;
	private String bullishDescription;
	private String bearishDescription;
	private String alsoDisplayDescription;

	private List<ChartedOutputGroup> chartedOutputGroups;
	private Map<Integer, OutputDescr> outputDescrFlatList;

	private Optional<String> exportBaseFileName;

	public EventDefDescriptorDynamic() {
		super();
		exportBaseFileName = Optional.empty();
		randoms = new double[100];
		Arrays.setAll(randoms, i -> Math.random());
	}

	private  Map<Integer, OutputDescr> getOutputDescrFlatList() {
		if (outputDescrFlatList == null) {
			initLists();
		}
		return outputDescrFlatList;
	}

	@Override
	public String getFullNameFor(int outputIdx) throws NoSuchElementException {
		return getOutputDescr(outputIdx).fullQualifiedName();
	}

	private OutputDescr getOutputDescr(int outputIdx) throws NoSuchElementException {
		OutputDescr outputDescr = getOutputDescrFlatList().get(outputIdx);
		if (outputDescr == null) throw new NoSuchElementException();
		return outputDescr;
	}

	protected void initLists() throws NoSuchElementException {
		initDescriptionsList();
	}

	protected void initDescriptionsList() {
		List<ChartedOutputGroup> chartedOutputGroups = getChartedOutputGroups();

		outputDescrFlatList = new HashMap<>();
		for (ChartedOutputGroup chartedOutputGroup : chartedOutputGroups) {
			OutputDescr mainOutput = chartedOutputGroup.getThisGroupMainOutputDescription();
			if (mainOutput.getType() != Type.INVISIBLE) outputDescrFlatList.put(mainOutput.getOutputIndex(), mainOutput);
			chartedOutputGroup.getComponents().values().stream().filter(c -> c.getType() != Type.INVISIBLE).forEach(c -> outputDescrFlatList.put(c.getOutputIndex(), c));
		}

	}

	private List<ChartedOutputGroup> getChartedOutputGroups() {
		if (chartedOutputGroups == null) throw new java.util.NoSuchElementException("No group to be found for the dynamic description of " + descriptorReference+ ". Its calculation may be in progress?");
		return chartedOutputGroups;
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

		SortedSet<ChartedOutputGroup> sortedGroups = new TreeSet<>(new Comparator<ChartedOutputGroup>() {
			@Override
			public int compare(ChartedOutputGroup o1, ChartedOutputGroup o2) {
				int comparator = o2.getComponents().size() - o1.getComponents().size();
				if (comparator == 0) comparator = o2.getThisGroupMainOutputReference().compareTo(o1.getThisGroupMainOutputReference());
				return comparator;
			}
		});
		sortedGroups.addAll(chartedOutputGroups);

		this.chartedOutputGroups = new ArrayList<>(sortedGroups);

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

		int groupIdx = getGroupIndexFor(outputIdx);
		//int alpha = (int) (255 - 255*( ((double)groupIdx / COLORS.length))/getGroupsCount());
		//int alpha = Math.max(100, (int) (255 - 255*((double)groupIdx)/getGroupsCount()));
		int alpha = (int) (255 - 128*((double)groupIdx)/getGroupsCount());
		Color[] grpColors = COLORS[groupIdx % COLORS.length];

		switch (getOutputDescr(outputIdx).getType()) {
		case CONSTANT :
			return new Color(grpColors[1].getRed(), grpColors[1].getGreen(), grpColors[1].getBlue(), alpha/2);
		case SIGNAL :
			return new Color(grpColors[1].getRed(), grpColors[1].getGreen(), grpColors[1].getBlue(), alpha);
		case BOTH :
			return new Color(grpColors[2].getRed(), (grpColors[2].getGreen() + outputIdx * 10) % 256, grpColors[2].getBlue(), alpha);
		case MULTI :
			return new Color((grpColors[3].getRed() + (int)(128*randoms[outputIdx%100])) % 256, (grpColors[3].getGreen() + (int)(128*randoms[(outputIdx+1)%100])) % 256, (grpColors[3].getBlue() + (int)(128*randoms[(outputIdx+2)%100])) % 256, alpha);
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
		String chartedOutputGroups;
		try {
			chartedOutputGroups = getChartedOutputGroups().toString();
		} catch (Exception e) {
			chartedOutputGroups = e.getMessage();
		}
		return "EventDefDescriptorDynamic [descriptorReference=" + descriptorReference + ", outputRefSet=" + chartedOutputGroups + "]";
	}

	@Override
	public Boolean displayValues() {
		return true;
	}

	@Override
	public int getGroupIndexFor(int outputIdx) {
		ChartedOutputGroup container = getOutputDescr(outputIdx).getContainer();
		return getChartedOutputGroups().indexOf(container);
	}

	@Override
	public int getGroupsCount() {
		return getChartedOutputGroups().size();
	}

	@Override
	public Integer[] getOutputIndexesForGroup(int groupIndex) {
		return getOutputIndexesForGroupSans(groupIndex);
	}

	private Integer[] getOutputIndexesForGroupSans(int groupIndex, Type... filter) {
		if (chartedOutputGroups == null) throw new java.util.NoSuchElementException("Can't refresh indicator chart for (clear in progress??) : " + descriptorReference);

		ChartedOutputGroup chartedOutputGroup = chartedOutputGroups.get(groupIndex);
		List<Integer> outputIdxs = new ArrayList<>();
		List<Type> sansList = new ArrayList<>((filter == null || filter.length == 0)?Arrays.asList(Type.values()):Arrays.asList(filter));
		sansList.remove(Type.INVISIBLE);
		OutputDescr thisGroupMainOutputDescription = chartedOutputGroup.getThisGroupMainOutputDescription();
		if (sansList.contains(thisGroupMainOutputDescription.getType())) outputIdxs.add(thisGroupMainOutputDescription.getOutputIndex());
		for (OutputDescr outputDescr : chartedOutputGroup.getComponents().values()) {
			if (sansList.contains(outputDescr.getType())) outputIdxs.add(outputDescr.getOutputIndex());
		}
		return outputIdxs.toArray(new Integer[outputIdxs.size()]);
	}

	@Override
	public Integer[] getThresholdsIdx(int groupIndex) {
		return getOutputIndexesForGroupSans(groupIndex, Type.CONSTANT);
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
	public String getMainLabelForGroup(int groupIdx) {
		return getChartedOutputGroups().get(groupIdx).getThisGroupMainOutputReference().getReference();
	}

	@Override
	public Set<OutputDescr> allOutputDescr() {
		return new HashSet<>(getOutputDescrFlatList().values());
	}

	@Override
	public Set<OutputDescr> displayedOutputsDescr() {

		Set<OutputDescr> ret = new TreeSet<>();
		for (final OutputDescr outputDescr: getOutputDescrFlatList().values()) {
			if (outputDescr.getDisplayOnChart()) ret.add(outputDescr);
		}
		return ret;

	}

	@Override
	public boolean isDisplayed(int outputIdx) {
		try {
			return getOutputDescr(outputIdx).getDisplayOnChart();
		} catch (Exception e) {
			return false;
		}
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

	@Override
	public String getGroupFullDescriptionFor(int groupIndex) {
		return getChartedOutputGroups().get(groupIndex).toString();
	}

}
