/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.admin.install.wizard;

import java.io.File;

import com.finance.pms.admin.install.SystemTypes;
import com.nexes.wizard.WizardPanelDescriptor;


/**
 * The Class DonePanelDescriptor.
 * 
 * @author Guillaume Thoreton
 */
public class DonePanelDescriptor extends WizardPanelDescriptor {
    
    public static final String IDENTIFIER = "DONE_PANEL";
    DonePanel panel3;
    
    public DonePanelDescriptor() {
        
        panel3 = new DonePanel();
        setPanelDescriptorIdentifier(IDENTIFIER);
        setPanelComponent(panel3);
        
    }

    @Override
	public Object getNextPanelDescriptor() {
        return FINISH;
    }
    
    @Override
	public Object getBackPanelDescriptor() {
        return SmtpPanelDescriptor.IDENTIFIER;
    }
    
    @Override
  	public void aboutToDisplayPanel() {
    	
    	SystemTypes systemType = Install.systemType;
    	if (systemType == null) systemType = SystemTypes.WINDOWS;
    	
		String installDir = InstallFolderPanel.getPmFolder().getAbsolutePath() + File.separator + "shell";
		String guiShell = installDir + File.separator + "gui" + systemType.getShext();
	

		String osSpecAdvice = "<html>I believe you are running a "+systemType+" OS.<br /><br />";
		osSpecAdvice = osSpecAdvice +"If Premium Markets doesn't start and to launch it again (NB. You may want to be patient at first launch) :<br />";
		
		osSpecAdvice = osSpecAdvice +"<ul>";
		if (systemType.equals(SystemTypes.WINDOWS)) {
			osSpecAdvice = osSpecAdvice + "<li>A new icon should appear on your Desktop. You just have to double click on it.</br></li>";
			osSpecAdvice = osSpecAdvice + "<li>Otherwise, go into the following folder :<br />" + installDir + ".</li>";
			osSpecAdvice = osSpecAdvice + "<li>The "+systemType.getSdescr()+" for starting the application is : <br />\t"+guiShell+"</li>";
		} else {
			osSpecAdvice = osSpecAdvice + "<li>Go into the following folder :<br />" + installDir + "</li>";
			osSpecAdvice = osSpecAdvice + "<li>The "+systemType.getSdescr()+" for starting the application is : <br />\t"+guiShell+"</li>";
			osSpecAdvice = osSpecAdvice + "<li>You may first want to check that the "+systemType.getSdescr()+" 'gui" + systemType.getShext()+"' has execution rights properly set up.</li>";
		}
		osSpecAdvice = osSpecAdvice + "<li>Make sure that Premium Markets is not already running. Quit and kill any instances.</li>";
		osSpecAdvice = osSpecAdvice + "<li>Then double click on : gui" + systemType.getShext()+"</li>";
		osSpecAdvice = osSpecAdvice + "<li>Alternatively, Premium Markets can also be run from a terminal or prompt, using the command line.</li>";
		osSpecAdvice = osSpecAdvice + "<ul>";
		
		osSpecAdvice = osSpecAdvice + "<br />Now you can click the 'Finish' button below.";
		osSpecAdvice = osSpecAdvice +"<br /><br /><br />\t\t\tThank you for using Premium Markets.</html>";
		
		panel3.endInstallTxt.setText(osSpecAdvice);
		
		panel3.endInstallTxt.validate();
		panel3.validate();
	   
          
    }

}
