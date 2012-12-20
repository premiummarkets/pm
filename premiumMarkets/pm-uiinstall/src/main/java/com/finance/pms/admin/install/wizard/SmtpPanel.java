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
	
	protected static final String[] keys = {
		"mail.to", 
		//"mail.from", 
		"mail.host", "mail.username", "mail.password"};
	
	protected static final String[] exValue = {"", "", "", "", ""};

	private final String[] keyComments = { 
					"Your email address",
				//	"Your reply email address",
					"Your email provider Internet Address", 
					"Your email provider User Name",
					"Your email provider Password"};
	
	private String[] toolTip = {
			"You can get these parameters from your email client account set up or webmail provider. This will be used for buy and sell and errors notifications.",
		//	"Usually the same as your email address above.",
			"Also called OutGoing Server SMTP dns name or IP. For Example : smtp.gmail.com, relay.plus.net, smtp.mail.yahoo.com ...",
			"You will find these in the authentication parameters of your email account set up. Leave blank if no authentication is required",
			"Leave blank if no authentication is required"
	};
	
	/** The jtxt. */
	protected JTextField[] jtxt = new JTextField[keyComments.length];
 
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
        textLabel.setText("Please setup your email address and ISP smtp access parameters");
        //textLabel.setToolTipText("You can get these from your email client account set up or webmail provider. This will be used for buy and sell and errors notifications.");
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
			JLabel label = new JLabel(keyComments[i]);
			//label.setToolTipText(toolTip[i]);
			jp.add(label);
			
			//text
			JPanel txtjp = new JPanel(new BorderLayout());
			if (keys[i].equals("mail.password")) {
					jtxt[i] = new JPasswordField(); 
			} else {
					jtxt[i] = new JTextField();
			}
			jtxt[i].setToolTipText(toolTip[i]);
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
