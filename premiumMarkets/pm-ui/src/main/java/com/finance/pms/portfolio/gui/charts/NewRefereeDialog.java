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

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.finance.pms.MainGui;
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
		
		if (inst == null || inst.isDisposed()) {

			Shell piShell = new Shell(shell, SWT.RESIZE | SWT.DIALOG_TRIM);
			piShell.setText("Premium Markets - Referee selection.");
			piShell.setFont(MainGui.DEFAULTFONT);
			piShell.setLayout(new FillLayout());
			
			final ScrolledComposite scrollComposite = new ScrolledComposite(piShell,SWT.BORDER | SWT.RESIZE);
			inst = new NewRefereeDialog(scrollComposite, SWT.NULL, composite);
			inst.open();
			scrollComposite.setContent(inst);
		    scrollComposite.setExpandVertical(true);
		    scrollComposite.setExpandHorizontal(true);
		    scrollComposite.setMinSize(inst.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		    scrollComposite.addControlListener(new ControlAdapter() {
		      public void controlResized(ControlEvent e) {
		        Rectangle r = scrollComposite.getClientArea();
		        scrollComposite.setMinSize(inst.computeSize(r.width, r.height));
		      }
		    });
		    scrollComposite.pack();
		    
		    piShell.setSize(scrollComposite.computeSize(200, 500));
		    Rectangle mainBounds = shell.getBounds();
		    Rectangle marketShellBounds = piShell.getBounds();
		    piShell.setLocation(mainBounds.x+mainBounds.width/4-marketShellBounds.x/2,mainBounds.y+mainBounds.y/4);
		    piShell.open();

			
			try {
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
