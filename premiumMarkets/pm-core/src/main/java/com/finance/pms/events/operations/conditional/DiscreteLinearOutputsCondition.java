package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.scoring.functions.Line;

@XmlSeeAlso({
	LinearTrendsCondition.class,
	HighsAndLowsCondition.class,
	MatchingBooleanMapCondition.class})
@SuppressWarnings("rawtypes")
public abstract class DiscreteLinearOutputsCondition extends Condition<Comparable> {

	protected static double DAY_IN_MILLI = 24*60*60*1000;

	protected DiscreteLinearOutputsCondition() {
		super();
	}

	public DiscreteLinearOutputsCondition(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}

	public DiscreteLinearOutputsCondition(String reference, String description, Operation... operands) {
		this(reference, description, new ArrayList<>(Arrays.asList(operands)));
	}

	protected SortedMap<Date, Double> buildLineFor(Collection<Date> lookBackDates, Line<Integer, Double> line) {
		double firstX = lookBackDates.iterator().next().getTime() / DAY_IN_MILLI;
		SortedMap<Date, Double> result = lookBackDates.stream()
				.collect(Collectors.toMap(
						e -> e,
						e -> line.getIntersect() + line.getSlope() * (e.getTime()/DAY_IN_MILLI - firstX),
						(a, b) -> a, TreeMap::new));
		return result;
	}

}
