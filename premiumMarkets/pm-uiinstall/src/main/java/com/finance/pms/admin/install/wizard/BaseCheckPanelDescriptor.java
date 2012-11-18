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

import java.io.InputStream;

import com.nexes.wizard.WizardPanelDescriptor;


// TODO: Auto-generated Javadoc
/**
 * The Class BaseCheckPanelDescriptor.
 * 
 * @author Guillaume Thoreton
 */
public class BaseCheckPanelDescriptor extends WizardPanelDescriptor {
    
    /** The Constant IDENTIFIER. */
    public static final String IDENTIFIER = "DB_PANEL";
	
	/** The Constant initdbName. */
	public static final String initdbName = "pmdb-initialdb.tar.bz2";
    
    /** The panel2. */
    BaseCheckPanel panel2;
    
    /**
     * Instantiates a new base check panel descriptor.
     * 
     * @author Guillaume Thoreton
     */
    public BaseCheckPanelDescriptor() {
        
        panel2 = new BaseCheckPanel();
        
        setPanelDescriptorIdentifier(IDENTIFIER);
        setPanelComponent(panel2);
        
    }
    
    /* (non-Javadoc)
     * @see com.nexes.wizard.WizardPanelDescriptor#getNextPanelDescriptor()
     */
    @Override
	public Object getNextPanelDescriptor() {
        return ProgressPanelDescriptor.IDENTIFIER;
    }
    
    /* (non-Javadoc)
     * @see com.nexes.wizard.WizardPanelDescriptor#getBackPanelDescriptor()
     */
    @Override
	public Object getBackPanelDescriptor() {
        return InstallFolderPanelDescriptor.IDENTIFIER;
    }

	/* (non-Javadoc)
	 * @see com.nexes.wizard.WizardPanelDescriptor#aboutToHidePanel()
	 */
	@Override
	public void aboutToHidePanel() {
		if (!BaseCheckPanelDescriptor.checkAvailability()) {
			System.out.println("The database is included in the jar or already installed");
			super.aboutToDisplayPanel();
		} else {
			System.out.println("No database is included in the jar nor already installed");
			super.aboutToHidePanel();
		}
	}
    
    /* (non-Javadoc)
     * @see com.nexes.wizard.WizardPanelDescriptor#aboutToDisplayPanel()
     */
    @Override
	public void aboutToDisplayPanel() {
		super.aboutToDisplayPanel();
        getWizard().setNextFinishButtonEnabled(false);
        getWizard().setBackButtonEnabled(false);
        
        
	}

	/**
	 * Check availability.
	 * 
	 * @return the boolean
	 * 
	 * @author Guillaume Thoreton
	 */
	public static Boolean checkAvailability() {
    	InputStream fis = BaseCheckPanelDescriptor.class.getClassLoader().getResourceAsStream(BaseCheckPanelDescriptor.initdbName);
    	return (fis != null || ProgressPanelDescriptor.isDbExists(InstallFolderPanel.piggyMarketSqueakFolder));
    }
       
    
}
