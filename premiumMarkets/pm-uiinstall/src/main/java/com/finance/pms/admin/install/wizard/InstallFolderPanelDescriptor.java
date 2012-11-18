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
package com.finance.pms.admin.install.wizard;

import java.io.File;

import com.nexes.wizard.WizardPanelDescriptor;


// TODO: Auto-generated Javadoc
/**
 * The Class InstallFolderPanelDescriptor.
 * 
 * @author Guillaume Thoreton
 */
public class InstallFolderPanelDescriptor extends WizardPanelDescriptor {
    
    /** The Constant IDENTIFIER. */
    public static final String IDENTIFIER = "INSTALL_FOLDER_PANEL";
    
    /** The panel3. */
    InstallFolderPanel panel3;
    
    /**
     * Instantiates a new install folder panel descriptor.
     * 
     * @author Guillaume Thoreton
     */
    public InstallFolderPanelDescriptor() {
        
        panel3 = new InstallFolderPanel();
        setPanelDescriptorIdentifier(IDENTIFIER);
        setPanelComponent(panel3);
        
    }

    @Override
	public Object getNextPanelDescriptor() {

			return ProgressPanelDescriptor.IDENTIFIER;

	}
    
    @Override
	public Object getBackPanelDescriptor() {
        return LicencePanelDescriptor.IDENTIFIER;
    }

	@Override
	public void aboutToHidePanel() {
		super.aboutToHidePanel();
		//TODO check release and do as per
		InstallFolderPanel.piggyMarketSqueakFolder = checkInstallFolder(new File(InstallFolderPanel.piggyMarketSqueakFolderName));
		
		getWizard().setNextFinishButtonEnabled(true);
		Install.selectNextButton();
	}
	   
	/**
	 * Check install folder.
	 * 
	 * @param installationFolder the installation folder
	 * 
	 * @return the file
	 * 
	 * @author Guillaume Thoreton
	 */
	private File checkInstallFolder(File installationFolder) {
		//New install??
		File pmFolder = new File(installationFolder.getAbsoluteFile() + File.separator + Install.piggyMarketSqueak);
		if (pmFolder != null && pmFolder.exists()) {
			//TODO all ready installed
			System.out.println("Install folder already exists.");
		} else {
			pmFolder.mkdirs();
		}
		return pmFolder;
	}	
}
