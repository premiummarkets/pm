/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
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
package com.finance.pms.portfolio.gui.charts;

import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.finance.pms.MainGui;
import com.finance.pms.UserDialog;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.portfolio.gui.NewPortfolioItemDialog;

/**
 * The Class NewRefereeDialog.
 * 
 * @author Guillaume Thoreton
 */
public class NewRefereeDialog extends NewPortfolioItemDialog {
	
	private static NewPortfolioItemDialog runningInst = null;

	private Set<Stock> selectedStocks;
	
	/**
	 * Instantiates a new new referee dialog.
	 * 
	 * @param parent the parent
	 * @param style the style
	 * 
	 * @author Guillaume Thoreton
	 * @param composite 
	 * @param tabIx 
	 */
	public NewRefereeDialog(Composite parent,int style, Composite composite) {
		super(parent,style, composite);
	}
	
	
	/**
	 * Show ui.
	 * 
	 * @return the new referee dialog
	 * 
	 * @author Guillaume Thoreton
	 * @param shell 
	 * @param composite 
	 */
	public static NewPortfolioItemDialog showUI(Shell shell, Composite composite) {
		
		if (NewRefereeDialog.runningInst != null && !NewRefereeDialog.runningInst.isDisposed()) {
			NewRefereeDialog.runningInst.forceFocus();
			return runningInst;
		}

		NewRefereeDialog inst = null;
		try {
			Shell piShell = new Shell(shell, SWT.DIALOG_TRIM|SWT.RESIZE);
			piShell.setText("Premium Markets - Referee selection.");
			piShell.setFont(MainGui.DEFAULTFONT);
			piShell.setLayout(new FillLayout(SWT.VERTICAL));

			inst = new NewRefereeDialog(piShell, SWT.RESIZE, composite);
			inst.initGui(SWT.SINGLE);

			piShell.layout();
			piShell.pack();
			piShell.open();

			runningInst = inst;
			
			try {
				inst.swtLoop();
			} catch (Exception e) {
				LOGGER.error(e, e);
				throw e;
			}

		} catch (Exception e) {

			try {
				LOGGER.error(e,e);
				if (inst != null) inst.dispose();
			} finally {
				inst = null;
			}

		}
		
		return inst;
	}


	@Override
	public void initGui(int selectionMode) {
		super.initGui(selectionMode);
		this.ctrlComposite.dispose();
		
		this.layout();
		this.pack();
		
	}

	public Set<Stock> getSelectedStocks() {
		return selectedStocks;
	}


	@Override
	protected String addListLabel() {
		return "Add List selection as Referee";
	}
	@Override
	protected String addSearchSelectionLabel() {
		return "Add Search selection as Referee";
	}


	@Override
	protected void addSelection(Set<Stock> stocks) {
		this.selectedStocks = stocks;
		
		UserDialog inst = new UserDialog(getShell(), "Added referee : "+stocks,null);
		inst.open();
	}	
	
}
