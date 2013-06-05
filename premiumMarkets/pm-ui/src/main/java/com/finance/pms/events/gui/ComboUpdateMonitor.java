package com.finance.pms.events.gui;

import java.util.Observable;

public class ComboUpdateMonitor extends Observable {
	@Override
	 public void notifyObservers() {
		super.setChanged();
		super.notifyObservers();
	 }
}