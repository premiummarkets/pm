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
package com.finance.pms.admin.install.wizard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.nexes.wizard.WizardPanelDescriptor;


// TODO: Auto-generated Javadoc
/**
 * The Class SmtpPanelDescriptor.
 * 
 * @author Guillaume Thoreton
 */
public class SmtpPanelDescriptor extends WizardPanelDescriptor implements ActionListener {
    
    /** The Constant IDENTIFIER. */
    public static final String IDENTIFIER = "SMTP_PANEL";
    
    /** The panel3. */
    SmtpPanel panel3;
    
    /** The p. */
    static Properties p; // = new Properties();
    
    /** The pfile. */
    File pfile;
    
    /**
     * Instantiates a new smtp panel descriptor.
     * 
     * @author Guillaume Thoreton
     */
    public SmtpPanelDescriptor() {
		
        panel3 = new SmtpPanel();
        setPanelDescriptorIdentifier(IDENTIFIER);
        setPanelComponent(panel3);
        
    }

    @Override
	public Object getNextPanelDescriptor() {
        return DonePanelDescriptor.IDENTIFIER;
      
    }
    

    @Override
	public Object getBackPanelDescriptor() {
        return InstallFolderPanelDescriptor.IDENTIFIER;
    }
    
    @Override
	public void aboutToDisplayPanel() {
        setNextButtonAccordingToForm();
		p = new Properties();
		
		try {
			pfile = new File(InstallFolderPanel.piggyMarketSqueakFolder.getAbsoluteFile() + File.separator + "db.properties");
			FileInputStream propFileIS = new FileInputStream(pfile);
			p.load(propFileIS);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		panel3.populateKeys();
		
    }    
    
    public void actionPerformed(ActionEvent e) {
        setNextButtonAccordingToForm();
    }
            
    
    /**
     * Sets the next button according to form.
     * 
     * @author Guillaume Thoreton
     */
    private void setNextButtonAccordingToForm() {
    	Install.selectNextButton();
    }
    
   
	@Override
	public void aboutToHidePanel() {
		super.aboutToHidePanel();
		for (int i =0; i < panel3.jtxt.length; i++) {
			p.put(SmtpPanel.keys[i], panel3.jtxt[i].getText());
		}
		Object value = p.get("mail.from");
		if (value != null) p.put("mail.to", value);
		
		try {
			p.store(new FileOutputStream(pfile), "Added settings properties from install");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
}
