/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms;

import java.util.Comparator;
import java.util.Set;
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

public class PopupMenu<T extends InfoObject>  {

	private final Composite rootParent;
	private Control controlParent;
	private int style;
	private Shell selectionShell;
	
	
	private Set<T> availableOptSet;
	private Set<T> selectionSet;
	
	private Boolean unableSelectAll;
	private ActionDialogAction action;
	private boolean disposeOnDeactivate;
	
	private ActionDialogAction closeAction;
	//private ActionDialogAction deactivateAction;
	Boolean actionOnDeactivate;
	
	private String title;


	public PopupMenu(
			Composite rootParent, Control controlParent, 
			Set<T> availableOptSet, Set<T> selectionList, 
			Boolean disposeOnDeactivate, Boolean unableSelectAll, int style, 
			ActionDialogAction selectAction) {
		
		super();
		this.rootParent = rootParent;
		this.controlParent = controlParent;
	
		this.style = style;
		
		this.availableOptSet = new TreeSet<T>(new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return o1.info().compareTo(o2.info());
			}
		});
		this.availableOptSet.addAll(availableOptSet);
		this.selectionSet = selectionList;
		
		this.unableSelectAll = unableSelectAll;
		
		this.action = selectAction;
		this.disposeOnDeactivate = disposeOnDeactivate;
		
		this.title = "";
		if (controlParent instanceof Button) {
			title = ((Button) controlParent).getText();
		} else {
			title = controlParent.getShell().getText();
		}

	}
	
	public PopupMenu(
			Composite rootParent, Control controlParent, 
			Set<T> availableOptSet, Set<T> selectionList, 
			Boolean disposeOnDeactivate, Boolean unableSelectAll, int style, 
			ActionDialogAction selectAction, ActionDialogAction closeAction, Boolean actionOnDeactivate) {
		
		this(rootParent, controlParent, availableOptSet, selectionList, disposeOnDeactivate, unableSelectAll, style, selectAction);
		
		this.closeAction = closeAction;
		//this.deactivateAction = deactivateAction;
		this.actionOnDeactivate = actionOnDeactivate;
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
		
		selectionShell.addListener(SWT.Deactivate, new Listener() {
			
			public void handleEvent(Event e){	
				//if (deactivateAction != null) deactivateAction.action(null);
				if (disposeOnDeactivate) { 
					selectionShell.dispose();
				} else if (closeAction != null && actionOnDeactivate != null && actionOnDeactivate) {
					closeAction.action(null);
				}
			}
		});
		
		selectionShell.addListener(SWT.Activate, new Listener() {

			@Override
			public void handleEvent(Event event) {
				//Thread.dumpStack();
			}
			
		});
		
		selectionShell.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				if (closeAction != null) closeAction.action(null);
			}
			
		});
		
		initPopup();
		
		//Size
		Point computeSize = selectionShell.computeSize(SWT.DEFAULT, Math.min(selectionShell.getSize().y,selectionShell.getParent().getSize().y), true);
		selectionShell.setSize(computeSize.x,computeSize.y);
		
		//Location
		if (location == null) {
			
			Rectangle parentShellBounds = rootParent.getShell().getBounds();
			Rectangle parentBounds = controlParent.getParent().getBounds();
			Rectangle pparentBounds = controlParent.getParent().getParent().getBounds();
			int x =  Math.max(0, Math.min(selectionShell.getDisplay().getBounds().width-selectionShell.getSize().x, parentShellBounds.x + pparentBounds.x +parentBounds.x + controlParent.getBounds().x));
			int y = Math.max(0, Math.min(selectionShell.getDisplay().getBounds().height-selectionShell.getSize().y, parentShellBounds.y + pparentBounds.y +parentBounds.y + controlParent.getBounds().y));
			selectionShell.setBounds(x, y, selectionShell.getBounds().width, selectionShell.getBounds().height);
		} else {
			
			if (addWidth != null && addWidth) {
				location.x = location.x + selectionShell.getSize().x;
			}
			int x = Math.max(0, Math.min(selectionShell.getDisplay().getBounds().width-selectionShell.getSize().x, location.x));
			int y =  Math.max(0, Math.min(selectionShell.getDisplay().getBounds().height-selectionShell.getSize().y, location.y));
			selectionShell.setLocation(x, y);
			
		}
				
		selectionShell.open();
				
	}

	protected void initPopup() {
		
		if (unableSelectAll) {
			
			final Button selectAllBut = new Button(selectionShell, style);
			selectAllBut.setText("Select/Deselect all");
			selectAllBut.setFont(MainGui.DEFAULTFONT);
			selectAllBut.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handle();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handle();
				}
				
				private void handle() {
					
					if (selectAllBut.getSelection()) {
						for (Control button : selectionShell.getChildren()) {
							((Button)button).setSelection(true);
							@SuppressWarnings("unchecked")
							T data = (T) ((Button)button).getData();
							if (data != null) selectionSet.add(data);
						}
					} else {
						for (Control button : selectionShell.getChildren()) {
							((Button)button).setSelection(false);
						}
						selectionSet.clear();
					}
					
					if (action != null) action.action(selectAllBut);
					
				}
				
			});
			
		}

		for (final T buttonInfo : availableOptSet) {
			
			final Button button = new Button(selectionShell, style);
			button.setText(buttonInfo.info());
			button.setData(buttonInfo);
			button.setFont(MainGui.DEFAULTFONT);
			button.setToolTipText(buttonInfo.tootTip());
			button.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					handleSelection(buttonInfo, button);
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handleSelection(buttonInfo, button);
					
				}
				
				private void handleSelection(T buttonInfo, final Button button) {
					
					if (button.getSelection()) {
						selectionSet.add(buttonInfo);
					} else {
						selectionSet.remove(buttonInfo);
					}
					
					if (action != null ) action.action(button);
			
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

	public void updateAction(Set<T> availEventDefs, Set<T> selectionSet, ActionDialogAction action, ActionDialogAction closeAction, Boolean actionOnDeactivate) {
		this.action = action;
		this.closeAction = action;
		//this.deactivateAction = deactivateAction;
		this.actionOnDeactivate = actionOnDeactivate;
		updatePopup(availEventDefs, selectionSet);
		
	}

	private void updatePopup(Set<T> availEventDefs, Set<T> selectionSet) {
		this.availableOptSet.clear();
		this.availableOptSet.addAll(availEventDefs);
		this.selectionSet = selectionSet;
		
		Control[] children = selectionShell.getChildren();
		for (Control control : children) {
			control.dispose();
		}
		
		initPopup();
		
		//Size
		Point computeSize = selectionShell.computeSize(SWT.DEFAULT, Math.min(selectionShell.getSize().y,selectionShell.getParent().getSize().y), true);
		selectionShell.setSize(computeSize.x,computeSize.y);
	}
	
}