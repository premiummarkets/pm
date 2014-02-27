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
package com.finance.pms.portfolio.gui.charts;

import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.finance.pms.CursorFactory;
import com.finance.pms.MainGui;
import com.finance.pms.MainPMScmd;
import com.finance.pms.UserDialog;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.portfolio.gui.NewPortfolioItemDialog;

/**
 * The Class NewRefereeDialog.
 * 
 * @author Guillaume Thoreton
 */
public class NewRefereeDialog extends NewPortfolioItemDialog {
	
	private static NewPortfolioItemDialog runningInst = null;
	private ChartPerfDisplay perfDisplay;
	
	/**
	 * Instantiates a new new referee dialog.
	 * 
	 * @param parent the parent
	 * @param style the style
	 * 
	 * @author Guillaume Thoreton
	 * @param caller 
	 * @param tabIx 
	 */
	public NewRefereeDialog(Composite parent,int style, Composite caller) {
		super(parent,style, caller);
	}
	
	
	/**
	 * Show ui.
	 * 
	 * @return the new referee dialog
	 * 
	 * @author Guillaume Thoreton
	 * @param shell 
	 * @param caller 
	 */
	public static NewRefereeDialog showUI(Shell shell, Composite caller, ChartPerfDisplay perfDisplay) {
		
		if (NewRefereeDialog.runningInst != null && !NewRefereeDialog.runningInst.isDisposed()) {
			NewRefereeDialog.runningInst.forceFocus();
			return (NewRefereeDialog) runningInst;
		}

		NewRefereeDialog inst = null;
		try {
			Shell piShell = new Shell(shell, SWT.DIALOG_TRIM|SWT.RESIZE);
			piShell.setText("Premium Markets - Referee selection.");
			piShell.setFont(MainGui.DEFAULTFONT);
			piShell.setLayout(new FillLayout(SWT.VERTICAL));

			inst = new NewRefereeDialog(piShell, SWT.RESIZE, caller);
			inst.initGui(SWT.SINGLE);
			inst.perfDisplay = perfDisplay;

			piShell.layout();
			piShell.pack();
			piShell.open();

			runningInst = inst;

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
		super.initGui(selectionMode, false);
		
		this.layout();
		this.pack();
		
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
	protected void addSelection(Set<Stock> listStock) {

		Stock newReferree = null;
		if (listStock != null && listStock.size() == 1) {//Referre selected

			try {
				newReferree = listStock.iterator().next();

				getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));

				QuotationUpdate quotationUpdate = new QuotationUpdate();
				quotationUpdate.getQuotes(new StockList(listStock));
				perfDisplay.loadRefereeQuotations(newReferree);
				try {
					MainPMScmd.getPrefs().put("charts.referee", newReferree.getSymbol()+"||-||"+newReferree.getIsin());
					MainPMScmd.getPrefs().flush();
				} catch (Exception e) {
					LOGGER.warn(e,e);
				}

			} catch (Exception e) {

				UserDialog inst = new UserDialog(getParent().getShell(), "Sorry. Invalid referee : "+newReferree.getFriendlyName()+"\n"+e, null);
				inst.open();
				
			} finally {
				getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
			}
			
		} else {
			
			UserDialog inst = new UserDialog(((ChartsComposite)caller).getShell(), "No referee selected please select a stock \n", null);
			inst.open();
			
		}
		
		if (newReferree != null) {
				
				Stock relativeToRefereeSetting = perfDisplay.relativeToRefereeSetting(newReferree.getSymbol(), newReferree.getIsin());
				
				if (relativeToRefereeSetting != null) {
					((ChartsComposite)caller).updateCharts(false);
					UserDialog inst = new UserDialog(getParent().getShell(), "Added referee : "+((newReferree != null)?newReferree.getFriendlyName():"None"), null);
					inst.open();
				}
				else {
					UserDialog inst = new UserDialog(((ChartsComposite)caller).getShell(), "Referee unknown or no quotations available : "+newReferree.getFriendlyName(), null);
					inst.open();
				}
				
		}
	
	}	
	
}
