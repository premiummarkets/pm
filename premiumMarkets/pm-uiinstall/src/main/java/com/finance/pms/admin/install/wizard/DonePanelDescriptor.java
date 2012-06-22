/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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

import com.finance.pms.admin.install.SystemTypes;
import com.nexes.wizard.WizardPanelDescriptor;


// TODO: Auto-generated Javadoc
/**
 * The Class DonePanelDescriptor.
 * 
 * @author Guillaume Thoreton
 */
public class DonePanelDescriptor extends WizardPanelDescriptor {
    
    /** The Constant IDENTIFIER. */
    public static final String IDENTIFIER = "DONE_PANEL";
    
    /** The panel3. */
    DonePanel panel3;
    
    /**
     * Instantiates a new done panel descriptor.
     * 
     * @author Guillaume Thoreton
     */
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
    	
    	String guiShell = InstallFolderPanel.piggyMarketSqueakFolder.getAbsolutePath() + File.separator + "shell" + File.separator + "gui" + Install.systemType.getShext();
	
		panel3.endInstall.append("Installation completed.\n" +
								 "I will now try to run the program for you. It may not work depending on your sytem.\n");
		panel3.endInstall.append("To run it by your self, you can either :\n\n");
		String unixAdvice = (Install.systemType.equals(SystemTypes.WINDOWS))?"":"Don't forget to do a chmod u+x on it before hand.\n";
		panel3.endInstall.append("\t- Use this file : \n"+ guiShell + "\n"+unixAdvice+"\n");
		panel3.endInstall.append("\t- Or for windows users, the newly Premium Markets icon on your Desktop.\n\n\n\n\n\t\t\tThank you for using Premium Markets.");
		
		//panel3.endInstall.validate();
		panel3.validate();
	   
          
    }

}
