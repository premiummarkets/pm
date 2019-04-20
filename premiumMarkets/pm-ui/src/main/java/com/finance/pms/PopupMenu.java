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
package com.finance.pms;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import com.finance.pms.portfolio.InfoObject;

public class PopupMenu<T extends InfoObject> {

	private final Composite rootParent;
	private Control controlParent;
	private int style;
	private Shell selectionShell;


	private SortedSet<T> availableOptSet;
	private Set<T> selectionSet;

	private Boolean unableSelectAll;

	private ActionDialogAction selectionAction;
	private ActionDialogAction closeAction;

	private boolean disposeOnDeactivate;
	private Boolean actionateOnDeactivate;

	private String title;
	private Boolean hasChanged;


	public PopupMenu(
			Composite rootParent, Control controlParent,
			Set<T> availableOptSet, Set<T> selectionSet,
			Boolean disposeOnDeactivate, Boolean unableSelectAll, int style,
			ActionDialogAction selectAction) {

		super();
		this.rootParent = rootParent;
		this.controlParent = controlParent;

		this.style = style;

		addAvailableOpts(availableOptSet);

		this.selectionSet = selectionSet;

		this.unableSelectAll = unableSelectAll;

		this.selectionAction = selectAction;
		this.disposeOnDeactivate = disposeOnDeactivate;

		this.title = "";
		if (controlParent instanceof Button) {
			title = ((Button) controlParent).getText();
		} else {
			title = controlParent.getShell().getText();
		}

		hasChanged = false;

	}

	private void addAvailableOpts(Set<T> availableOptSet) {
		this.availableOptSet = new TreeSet<T>(T::compareToInfoObject);
		this.availableOptSet.addAll(availableOptSet);
	}

	public PopupMenu(
			Composite rootParent, Control controlParent,
			Set<T> availableOptSet, Set<T> selectionList,
			Boolean disposeOnDeactivate, Boolean unableSelectAll, int style,
			ActionDialogAction selectAction, ActionDialogAction closeAction, Boolean actionateOnDeactivate) {

		this(rootParent, controlParent, availableOptSet, selectionList, disposeOnDeactivate, unableSelectAll, style, selectAction);

		this.closeAction = closeAction;
		this.actionateOnDeactivate = actionateOnDeactivate;

		if (closeAction == null && actionateOnDeactivate != null && actionateOnDeactivate) throw new RuntimeException();
		if (closeAction == null && selectAction == null) throw new RuntimeException();

	}

	public void open() {
		this.open(null,null);
	}

	public void open(Point location, Boolean addWidth) {

		selectionShell = new Shell(rootParent.getShell(), SWT.SHELL_TRIM);

		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		rowLayout.wrap= true;
		selectionShell.setLayout(rowLayout);
		selectionShell.setFont(MainGui.CONTENTFONT);
		selectionShell.setText(title);

		Listener deactivateListener = new Listener() {

			@Override
			public void handleEvent(Event e) {

				Control control = selectionShell.getDisplay().getCursorControl();
				for (Control child : selectionShell.getChildren()) {
					if (control == child) return;
				}

				deactivate();
			}

			protected void deactivate() {
				if (disposeOnDeactivate) { 
					selectionShell.dispose();
				} else if (actionateOnDeactivate != null && actionateOnDeactivate) {
					runCloseAction();
				}
			}

		};
		selectionShell.addListener(SWT.MouseExit, deactivateListener);
		selectionShell.addListener(SWT.Deactivate, deactivateListener);

		selectionShell.addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent e) {
				runCloseAction();
			}

		});

		initPopup();

		//Location
		if (location == null) {

			Rectangle parentShellBounds = rootParent.getShell().getBounds();
			Rectangle parentBounds = controlParent.getParent().getBounds();
			Rectangle pparentBounds = controlParent.getParent().getParent().getBounds();
			int x =  Math.max(0, Math.min(selectionShell.getDisplay().getBounds().width-selectionShell.getSize().x, parentShellBounds.x + pparentBounds.x +parentBounds.x + controlParent.getBounds().x));
			int y = Math.max(0, Math.min(selectionShell.getDisplay().getBounds().height-selectionShell.getSize().y, parentShellBounds.y + pparentBounds.y +parentBounds.y + controlParent.getBounds().y));
			//selectionShell.setBounds(x, y, selectionShell.getBounds().width, selectionShell.getBounds().height);
			selectionShell.setLocation(x, y);

		} else {

			if (addWidth != null && addWidth) {
				location.x = location.x + selectionShell.getSize().x;
			}
			int x = Math.max(0, Math.min(selectionShell.getDisplay().getBounds().width-selectionShell.getSize().x, location.x));
			int y =  Math.max(0, Math.min(selectionShell.getDisplay().getBounds().height-selectionShell.getSize().y, location.y));
			selectionShell.setLocation(x, y);

		}

		//Size
		Point computeSize = selectionShell.computeSize(SWT.DEFAULT, Math.min(selectionShell.getSize().y, selectionShell.getParent().getSize().y - 20), true);
		selectionShell.setSize(computeSize.x,computeSize.y);

		selectionShell.open();

	}

	public void inhibate() {
		this.hasChanged = false;
		this.closeAction = null;

	}

	protected void initPopup() {

		if (unableSelectAll) {

			final Button selectAllBut = new Button(selectionShell, style);
			selectAllBut.setText("Select/DeSelect all");
			selectAllBut.setFont(MainGui.DEFAULTFONT);
			selectAllBut.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handleSelectAllTicks();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handleSelectAllTicks();
				}

				private void handleSelectAllTicks() {

					hasChanged = true;

					if (selectAllBut.getSelection()) {
						for (Control button : selectionShell.getChildren()) {
							((Button)button).setSelection(true);
							@SuppressWarnings("unchecked") T data = (T) ((Button)button).getData();
							if (data != null) selectionSet.add(data);
						}
					} else {
						for (Control button : selectionShell.getChildren()) {
							((Button)button).setSelection(false);
						}
						selectionSet.clear();
					}

					if (selectionAction != null) selectionAction.action();

				}

			});

		}

		int maxInfoLenth = (availableOptSet.size() > 30)?50:255;
		for (final T buttonInfo : availableOptSet) {

			final Button button = new Button(selectionShell, style);
			button.setText(buttonInfo.info(maxInfoLenth));
			button.setToolTipText(buttonInfo.toolTip());
			button.setData(buttonInfo);
			button.setFont(MainGui.DEFAULTFONT);
			button.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handleButtonSelection(buttonInfo, button, e);
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handleButtonSelection(buttonInfo, button, e);
				}

				private void handleButtonSelection(T buttonInfo, final Button button, SelectionEvent e) {

					hasChanged=true;

					if (button.getSelection()) {
						selectionSet.add(buttonInfo);
						if ((e.stateMask & SWT.CONTROL) != 0 && buttonInfo.isMain()) {
							for (Control otherButton : selectionShell.getChildren()) {
								@SuppressWarnings("unchecked") T data = (T) ((Button)otherButton).getData();
								if (data != null && data.groupId() != null && data.groupId().equals(buttonInfo.groupId())) {
									((Button)otherButton).setSelection(true);
									selectionSet.add(data);
								}
							}
						}
					} else {
						selectionSet.remove(buttonInfo);
						if ((e.stateMask & SWT.CONTROL) != 0 && buttonInfo.isMain()) {
							for (Control otherButton : selectionShell.getChildren()) {
								@SuppressWarnings("unchecked") T data = (T) ((Button)otherButton).getData();
								if (data != null &&  data.groupId() != null && data.groupId().equals(buttonInfo.groupId())) {
									((Button)otherButton).setSelection(false);
									selectionSet.remove(data);
								}
							}
						}
					}

					if (selectionAction != null ) selectionAction.action();

				}

			});

			if (selectionSet.contains(buttonInfo)) button.setSelection(true);
			button.setVisible(true);
		}

		selectionShell.layout();
		selectionShell.pack();
	}

	public Shell getSelectionShell() {
		return selectionShell;
	}

	public void updateAction(Set<T> availEventDefs, Set<T> selectionSet, ActionDialogAction selectionAction, ActionDialogAction closeAction, Boolean actionOnDeactivate) {
		this.selectionAction = selectionAction;
		this.closeAction = closeAction;
		this.actionateOnDeactivate = actionOnDeactivate;
		updatePopup(availEventDefs, selectionSet);
	}

	private void updatePopup(Set<T> availEventDefs, Set<T> selectionSet) {
		this.availableOptSet.clear();
		addAvailableOpts(availEventDefs);
		this.selectionSet = selectionSet;

		Control[] children = selectionShell.getChildren();
		for (Control control : children) {
			control.dispose();
		}

		initPopup();

		//Size
		Point computeSize = selectionShell.computeSize(SWT.DEFAULT, Math.min(selectionShell.getSize().y,selectionShell.getParent().getSize().y - 20), true);
		selectionShell.setSize(computeSize.x,computeSize.y);

		this.hasChanged=true;
	}

	private void runCloseAction() {
		if (closeAction != null && hasChanged) {
			hasChanged = false;
			closeAction.action();
		}
	}

}