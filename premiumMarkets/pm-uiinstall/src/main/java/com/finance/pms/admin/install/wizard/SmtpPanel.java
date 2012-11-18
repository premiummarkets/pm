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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


// TODO: Auto-generated Javadoc
/**
 * The Class SmtpPanel.
 * 
 * @author Guillaume Thoreton
 */
public class SmtpPanel extends JPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2997823935558776268L;
	
	/** The Constant keys. */
	protected static final String[] keys = {"mail.to", "mail.host", "mail.username", "mail.password"};
	
	/** The Constant exValue. */
	protected static final String[] exValue = {"ex : your.name@yourdomain.com", "ex : smtp.yourdomain.com", "", ""};
	
	/** The key comments. */
	private final String[] keyComments = { 
					"Your email address",
					"Your email provider server dns name or IP ", 
					"Your email provider server user name - If authentication is required -",
					"Your email provider server password - If authentication is required -"};
	
	/** The jtxt. */
	static protected JTextField[] jtxt = new JTextField[4];
 
    /** The content panel. */
    private JPanel contentPanel;
    
    /** The icon label. */
    private JLabel iconLabel;
    
    /** The title panel. */
    private JPanel titlePanel;
    
    /** The text label. */
    private JLabel textLabel;
    
    /** The separator. */
    private JSeparator separator;
        
    /**
     * Instantiates a new smtp panel.
     * 
     * @author Guillaume Thoreton
     */
    public SmtpPanel() {
    	
        super();
        
        contentPanel = getContentPanel();

        ImageIcon icon = getImageIcon();
        
        textLabel = new javax.swing.JLabel();
        titlePanel = new javax.swing.JPanel();
        iconLabel = new javax.swing.JLabel();
        separator = new javax.swing.JSeparator();

        setLayout(new java.awt.BorderLayout());
        
        if (icon != null)  iconLabel.setIcon(icon);
        
        titlePanel.setLayout(new java.awt.BorderLayout());
        titlePanel.setBackground(Color.gray);
        
        textLabel.setBackground(Color.gray);
        textLabel.setFont(new Font("MS Sans Serif", Font.BOLD, 20));
        textLabel.setText("PLease setup your email address and ISP smtp access parameters");
        textLabel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        textLabel.setOpaque(true);
        
        
        titlePanel.add(textLabel, BorderLayout.CENTER);
        titlePanel.add(iconLabel, BorderLayout.EAST);
        titlePanel.add(separator, BorderLayout.SOUTH);
        
        add(titlePanel, BorderLayout.NORTH);
        JPanel secondaryPanel = new JPanel();
        secondaryPanel.add(contentPanel, BorderLayout.NORTH);
        add(secondaryPanel, BorderLayout.WEST);
        
	}  
    
    /**
     * Checks if is form filled.
     * 
     * @return true, if is form filled
     */
    public boolean isFormFilled() {
    	return true;
    }
    
    /**
     * Gets the content panel.
     * 
     * @return the content panel
     */
    private JPanel getContentPanel() {
    	
		JPanel jpc = new JPanel();
		jpc.setLayout(new BorderLayout());
		JPanel jp = new JPanel();
		//jp.setBackground(new Color(239, 183, 103));
		jpc.add(jp,BorderLayout.CENTER);
		jp.setLayout(new GridLayout(7,2,30,20));
		for (int i = 0;i < keys.length;i++) {
			
			//comment
			jp.add(new JLabel(keyComments[i]));
			
			//text
			JPanel txtjp = new JPanel(new BorderLayout());
			//StringContent sc = new StringContent(10);
			//jtxt[i]=new JTextField(new PlainDocument(sc));
			if (keys[i].equals("mail.password")) 
					jtxt[i] = new JPasswordField(); 
				else 
					jtxt[i] = new JTextField();
			txtjp.add(jtxt[i],BorderLayout.NORTH);
			txtjp.add(new JLabel(exValue[i]),BorderLayout.SOUTH);
			jp.add(txtjp);
		}
		
		return jpc;
		
	}
    
    /**
     * Gets the image icon.
     * 
     * @return the image icon
     */
    private ImageIcon getImageIcon() {
    	return null;
    	

    }    
    
    /**
     * Populate keys.
     * 
     * @author Guillaume Thoreton
     */
    public void populateKeys() {
		for (int i = 0;i < keys.length;i++) {
			jtxt[i].setText(SmtpPanelDescriptor.p.getProperty(keys[i]));
		}
    }

}
