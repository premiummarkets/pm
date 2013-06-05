package com.finance.pms.events.calculation;

import java.awt.Color;
import java.util.NoSuchElementException;

public interface EventDefDescriptor {

	String[] descriptionArray() throws NoSuchElementException;

	String getHtmlBullishDescription();

	String getHtmlBearishDescription();

	Color getColor(int i) throws NoSuchElementException;

	Boolean displayValues();

	int getGroupFor(int i);

	int getGroupsCount();

	Integer[] getIndexesForGroup(int j);

}
