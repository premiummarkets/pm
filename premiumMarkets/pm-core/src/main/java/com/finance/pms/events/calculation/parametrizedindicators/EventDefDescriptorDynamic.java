package com.finance.pms.events.calculation.parametrizedindicators;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.EventDefDescriptor;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.OutputDescr;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;

public class EventDefDescriptorDynamic implements EventDefDescriptor {
	
	private static MyLogger LOGGER = MyLogger.getLogger(EventDefDescriptorDynamic.class);

	public static Color[][] COLORS = new  Color[][] {{Color.BLACK, Color.RED, Color.ORANGE},  {Color.BLUE, Color.MAGENTA, Color.CYAN}, {Color.GRAY, Color.PINK, Color.YELLOW}};
	
	private String descriptorReference;
	private String bullishDescription;
	private String bearishDescription;
	
	List<ChartedOutputGroup> chartedOutputGroups;
	private String[] descripitonArrays;
	private List<OutputDescr> descripitonList;
	private int groupsCount = 0;
	
	public EventDefDescriptorDynamic() {
		super();
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
		if (chartedOutputGroups == null) throw new java.util.NoSuchElementException("Can't refresh indicator chart for (clear in progress??) : " + descriptorReference);
			
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
		return bullishDescription.replace("\n", "<br>").replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
	}

	@Override
	public String getHtmlBearishDescription() {
		return bearishDescription.replace("\n", "<br>").replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
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
		
		int groupIdx = getGroupFor(outputIdx);
		int alpha = 255 - 255*(groupIdx / COLORS.length)/getGroupsCount();
		Color[] grpColors = COLORS[groupIdx % COLORS.length];
		
		if (descripitonList == null) {
			initLists();
		}
		switch (descripitonList.get(outputIdx).getType()) {
		case CONSTANT :
			return new Color(grpColors[1].getRed(), grpColors[1].getGreen(), grpColors[1].getBlue(), alpha/2);
		case SIGNAL :
			return new Color(grpColors[1].getRed(), grpColors[1].getGreen(), grpColors[1].getBlue(), alpha);
		case BOTH :
			return new Color(grpColors[2].getRed(), grpColors[2].getGreen(), grpColors[2].getBlue(), alpha);
		case MULTI :
			return new Color(grpColors[2].getRed(), grpColors[2].getGreen(), grpColors[2].getBlue(), alpha);
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

	public void setBearishDescription(String beraishDescription) {
		this.bearishDescription = beraishDescription;
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

}
