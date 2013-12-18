package com.finance.pms.events.calculation;

import java.awt.Color;
import java.util.NoSuchElementException;
import java.util.Set;

import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.OutputDescr;

public interface EventDefDescriptor {

	String[] descriptionArray() throws NoSuchElementException;

	String getHtmlBullishDescription();

	String getHtmlBearishDescription();

	Color getColor(int i) throws NoSuchElementException;

	Boolean displayValues();

	int getGroupFor(int i);

	int getGroupsCount();

	Integer[] getOutputIndexesForGroup(int j);

	Integer[] getThresholdsIdx(int grpIdx);

	String getMainLabelForGroup(int groupIdx);

	public abstract Set<OutputDescr> displayedOutputs();

	public abstract Set<OutputDescr> allOutputs();

	boolean isDisplayed(int outputIdx);

}
