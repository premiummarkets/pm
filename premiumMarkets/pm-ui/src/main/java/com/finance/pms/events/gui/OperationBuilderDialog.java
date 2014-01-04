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
package com.finance.pms.events.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import com.finance.pms.MainGui;

public class OperationBuilderDialog {

	protected Shell shell;
	protected Shell parent;

	private MainGui mainGui;

	public OperationBuilderDialog(Shell parent, MainGui mainGui) {
		this.parent = parent;
		this.mainGui = mainGui;
	}

	public Object open(Point location, int width) {
	
        shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.RESIZE);
        shell.setText("Premium Markets - Customise and Create indicators ...");
        
        initGui();
        
        if (location != null) {
			shell.setLocation(location);
			Point computeSize = shell.computeSize(width, SWT.DEFAULT);
			shell.setSize(computeSize);
		}
		shell.open();
        
        return null;
        
	}

	private void initGui() {
		
		shell.setLayout(new GridLayout());
		shell.setBackground(MainGui.pOPUP_BG);
		
		Group opGrp = new Group(shell, SWT.NONE);
		opGrp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		opGrp.setLayout(new GridLayout());
		opGrp.setText("Customise and Create Operations");
		opGrp.setFont(MainGui.DEFAULTFONT);
		opGrp.setBackground(MainGui.pOPUP_GRP);
		OperationBuilderComposite operationBuilderDialog = new OperationBuilderComposite(opGrp, mainGui);
		operationBuilderDialog.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Group indcGrp = new Group(shell, SWT.NONE);
		indcGrp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		indcGrp.setLayout(new GridLayout());
		indcGrp.setText("Customise and Create Trends Calculators");
		indcGrp.setFont(MainGui.DEFAULTFONT);
		indcGrp.setBackground(MainGui.pOPUP_GRP);
		IndicatorBuilderComposite indicatorBuilderComposite = new IndicatorBuilderComposite(indcGrp, mainGui, operationBuilderDialog.getComboUpdateMonitor());
		operationBuilderDialog.obsComboUpdateMonitor(indicatorBuilderComposite.getComboUpdateMonitor());
		indicatorBuilderComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		shell.layout();
		
		operationBuilderDialog.forceSelection(0);
		indicatorBuilderComposite.forceSelection(0);
		
	}

	public Shell getShell() {
		return shell;
	}


}
