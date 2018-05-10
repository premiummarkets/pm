package com.finance.pms.events;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.events.pounderationrules.Signal;
import com.finance.pms.events.pounderationrules.SilentSignal;

public class SymbolEventsWeightData {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(SymbolEventsWeightData.class);

	private PonderationRule ponderationRule;
	private Optional<Signal> signal;

	public SymbolEventsWeightData(PonderationRule ponderationRule) {
		super();
		this.ponderationRule = ponderationRule;
		this.signal = Optional.empty();
	}

	public void setSignal(Signal initSignal) {
		this.signal = Optional.of(initSignal);
	}

	public Signal getSignal() {
		return signal.orElse(new SilentSignal());
	}

	public Date getLatestRelevantEventDate() {
		return getSignal().getLatestRelevantEventDate();
	}

	public Set<EventInfo> getBuyTriggeringEvents() {
		return getSignal().getBuyTriggeringEvents();
	}

	public Set<EventInfo> getSellTriggeringEvents() {
		return getSignal().getSellTriggeringEvents();
	}

	public Float getFinalWeight() {
		return getSignal().getSignalWeight().floatValue();
	}

	public PonderationRule getPonderationRule() {
		return ponderationRule;
	}

	public boolean isSamePonderation(PonderationRule otherPonderationRule) {
		if (!getPonderationRule().getClass().equals(ponderationRule.getClass())) return false;
		List<Field> thisPondFields = getAllFields(ponderationRule.getClass());
		List<Field> otherPondFields = getAllFields(otherPonderationRule.getClass());
		boolean allMatch = thisPondFields.stream().
		allMatch(field -> {
			Integer idx = otherPondFields.indexOf(field);
			if (idx == -1) return false;
			Field otherField = otherPondFields.get(idx);
			otherField.setAccessible(true);
			if (Modifier.isStatic(field.getModifiers()) && Modifier.isStatic(otherField.getModifiers())) return true;
			field.setAccessible(true);
			try {
				boolean equals = field.get(ponderationRule).equals(otherField.get(otherPonderationRule));
				//if (!equals) LOGGER.info("Not equal :"+field+ " and "+ otherField);
				return equals;
			} catch (Exception e) {
				//LOGGER.error("Error comparing "+field+ " and "+ otherField, e);
				return false;
			}
		});
		return allMatch && thisPondFields.size() == otherPondFields.size();
	}

	public static List<Field> getAllFields(Class<?> type) {
		List<Field> fields = new ArrayList<Field>();
		for (Class<?> c = type; c != null; c = c.getSuperclass()) {
			fields.addAll(Arrays.asList(c.getDeclaredFields()));
		}
		return fields;
	}
}