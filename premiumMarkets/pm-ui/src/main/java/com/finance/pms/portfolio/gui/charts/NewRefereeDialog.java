/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
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

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.gui.NewPortfolioItemDialog;

/**
 * The Class NewRefereeDialog.
 * 
 * @author Guillaume Thoreton
 */
public class NewRefereeDialog extends NewPortfolioItemDialog {
	
	/**
	 * Instantiates a new new referee dialog.
	 * 
	 * @param parent the parent
	 * @param style the style
	 * 
	 * @author Guillaume Thoreton
	 * @param composite 
	 */
	public NewRefereeDialog(Shell parent,int style, Composite composite) {
		super(parent,style,new ArrayList<PortfolioShare>(), composite);
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
		
		if (inst == null || inst.isDisposed()) {

			Shell piShell = new Shell(shell, SWT.RESIZE | SWT.DIALOG_TRIM);
			inst = new NewRefereeDialog(piShell, SWT.NULL, composite);
			try {
				inst.open();
				swtLoop();
			} catch (Exception e) {
				LOGGER.error("", e);
			}
		} else {
			inst.forceFocus();
		}
		
		return inst;
	}


	@Override
	public void open() {
		super.open();
		
		this.moniCombo.setVisible(false);
		this.monitorLabel.setVisible(false);
		this.quantityLabel.setVisible(false);
		this.quantityText.setVisible(false);
		this.newPortfollioAddButton.setVisible(false);
		this.addShareManualGroup.dispose();
		this.addFromFile.dispose();
		
		inst.layout();
		inst.pack();
		
	}
	
	
}
