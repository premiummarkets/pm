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
package com.finance.pms.admin.install;


import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

// TODO: Auto-generated Javadoc
/**
 * The Class FolderSelect.
 * 
 * @author Guillaume Thoreton
 */
public class FolderSelect extends JFileChooser {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2804684787581299588L;
	
	/** The canceled. */
	private Boolean canceled = false;

	
	

	/**
	 * Instantiates a new folder select.
	 * 
	 * @author Guillaume Thoreton
	 */
	public FolderSelect() {
		super();
		this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		this.setFileFilter(new FileFilter() {

			@Override
			public boolean accept(File f) {
				return f.isDirectory();
			}

			@Override
			public String getDescription() {
				return "Folder";
			}
			
		});
		
		this.setDialogTitle("Please select an installation folder.");
	}


	/* (non-Javadoc)
	 * @see javax.swing.JFileChooser#cancelSelection()
	 */
	@Override
	public void cancelSelection() {
		canceled = true;
		super.cancelSelection();
	}


	/**
	 * Gets the canceled.
	 * 
	 * @return the canceled
	 */
	public Boolean getCanceled() {
		return canceled;
	}
	
	
	
	
}
