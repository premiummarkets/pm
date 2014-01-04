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
package com.finance.pms.admin.install.wizard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import com.nexes.wizard.WizardPanelDescriptor;


// TODO: Auto-generated Javadoc
/**
 * The Class InstallFolderPanelDescriptor.
 * 
 * @author Guillaume Thoreton
 */
public class InstallFolderPanelDescriptor extends WizardPanelDescriptor implements KeyListener {
    
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
        panel3.addTextFieldReturn(this);
        
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
		String instFolderName = panel3.getJt().getText();
		if (panel3.checkInstallPath(instFolderName)) {
			File pmFolder = InstallFolderPanel.setPmFolder(instFolderName);
			updateInstallFolder(pmFolder);
			
			getWizard().setNextFinishButtonEnabled(true);
			Install.selectNextButton();
		}
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
	private void updateInstallFolder(File pmFolder) {
		//New install??
		if (pmFolder != null && pmFolder.exists()) {
			//TODO already installed
			System.out.println("Install folder already exists : "+pmFolder.getAbsolutePath());
		} else {
			System.out.println("Install folder will be created : "+pmFolder.getAbsolutePath());
			pmFolder.mkdirs();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			
			String instFolderName = panel3.getJt().getText();
			if (panel3.checkInstallPath(instFolderName)) {
				File pmFolder = InstallFolderPanel.setPmFolder(instFolderName);
				updateInstallFolder(pmFolder);
				
				getWizard().setNextFinishButtonEnabled(true);
				Install.selectNextButton();
				Install.pressNextButton();
				
			}
			
		}
	}	
}
