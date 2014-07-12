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
package com.finance.pms.portfolio.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.ActionDialog;
import com.finance.pms.ActionDialogAction;

public class ActionDialogForm extends ActionDialog {

	public Control[] controls; 
	public Object[] values;
	
	public ActionDialogForm(Shell shell, String buttonText,  String formText, String title) {
		super(shell, title, formText, null, buttonText, null);
	}
	
	@Override
	public void open() {
		super.open();
	}

	public Object[] getValues() {
		return values;
	}
	

	public void setControl(Control... controls) {
		this.controls = controls;
		this.values = new Object[controls.length];
		addListeners();
	}

	protected void addListeners() {
		
		if (controls.length == 1) {//XXX Well this is not very accurate and would make sense only with one control ...
			for (Control control : controls) {
				if (control instanceof Text) {
					control.addKeyListener(new KeyAdapter() {
						@Override
						public void keyReleased(KeyEvent evt) {
							if (evt.keyCode == SWT.CR) {
								doAction();
							}
						}
					});
				}
			}
		}
		
	}
	
	public void setAction(ActionDialogAction actionDialogAction) {
		this.action = actionDialogAction;
	}

	
}