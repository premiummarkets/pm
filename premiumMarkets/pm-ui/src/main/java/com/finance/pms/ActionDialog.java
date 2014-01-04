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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Shell;

public class ActionDialog extends UserDialog {
	
	protected ActionDialogAction action;
	String actionTxt;

	public ActionDialog(Shell parent, int style, String title, String erreur, String addMessage, String actionTxt, ActionDialogAction action) {
		super(parent, (style == SWT.NONE)?SWT.DIALOG_TRIM|SWT.RESIZE:style, title, erreur, addMessage);
		this.actionTxt = actionTxt;
		this.action = action;
		
	}
	
	@Override
	public void open() {
		open(false);
	}

	@Override
	protected void validationButtonTxtAndAction() {

		valideButton1.setText(actionTxt);
		valideButton1.setFont(MainGui.DEFAULTFONT);
		valideButton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent evt) {
				doAction();
			}
		});
		valideButton1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent evt) {
				if (evt.keyCode == SWT.CR || evt.keyCode == SWT.SPACE) {
					doAction();
				}
			}
		});
	}
	

	protected void doAction() {
		
		valideButton1.setCapture(true);
		valideButton1.forceFocus();
		
		getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
		new Thread(new Runnable() {
			public void run() {
				ActionDialog.this.getParent().getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						try {
							action.action(valideButton1);
						} finally {
							if (!getParent().isDisposed()) getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
							validerbutton1MouseDown();
						}
					}
				});
			}
		}).start();
	}


	public void updateDialog(String title, String erreur, String addMessage, String actionTxt, ActionDialogAction action) {
		
		super.updateDialog(title, erreur, addMessage);
		
		this.actionTxt = actionTxt;
		this.action = action;
		valideButton1.setText(actionTxt);
		
		layout();
		
		getParent().setActive();
		valideButton1.setFocus();
	}


	public boolean sameDialog(String erreur, String addMessage, String actionTxt) {
		
		if (!super.sameDialog(erreur, addMessage))
			return false;
		if (this.actionTxt == null) {
			if (actionTxt != null)
				return false;
		} else if (!this.actionTxt.equals(actionTxt))
			return false;
		return true;
	}

	
	
}
