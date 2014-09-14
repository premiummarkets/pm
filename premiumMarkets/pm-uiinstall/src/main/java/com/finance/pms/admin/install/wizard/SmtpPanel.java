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
		"mail.host", "mail.username", "mail.password"};
	
	protected static final String[] exValue = {"", "", "", "", ""};

	private final String[] keyComments = { 
					"Recipient email address",
					"SMTP service provider DNS or IP", 
					"SMTP authentication User Name",
					"SMTP authentication Password"};
	
	private String[] toolTip = {
			"The email address you will use for buy and sell events and errors notifications.",
			"You can get these parameters from your email client account or webmail set up. Also called OutGoing Server SMTP dns name or IP. For Example : smtp.gmail.com, relay.plus.net, smtp.mail.yahoo.com ...",
			"You will find these in the authentication parameters of your email account set up. Leave blank if no authentication is required",
			"Leave blank if no authentication is required"
	};

	protected JTextField[] jtxt = new JTextField[keyComments.length];
    private JPanel contentPanel;
    private JLabel iconLabel;
    private JPanel titlePanel;
    private JLabel textLabel;
    private JSeparator separator;
    
    private JLabel jTextAreaDescr;
        
    
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
        textLabel.setFont(new Font("MS Sans Serif", Font.BOLD, 16));
        textLabel.setText("Please setup your email address and ISP smtp access parameters");
        textLabel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        textLabel.setOpaque(true);
        
        
        titlePanel.add(textLabel, BorderLayout.CENTER);
        titlePanel.add(iconLabel, BorderLayout.EAST);
        titlePanel.add(separator, BorderLayout.SOUTH);
        
        add(titlePanel, BorderLayout.NORTH);
        
        jTextAreaDescr = new JLabel();
        jTextAreaDescr.setBackground(new Color(192, 192, 192));
        jTextAreaDescr.setFocusable(true);
        jTextAreaDescr.setText(
        	"<html>"+
        	"<p>" +
        	"The settings below are necessary if you want to use the alert on event feature.<br />" +
    		"To activate the feature, you also require an SMTP service provider DNS or IP to relay messages.<br />" +
    		"Note that you can also use a local SMTP server running on your machine.<br />" +
    		"<br />" +
    		"YOU CAN SAFELY IGNORE THIS SETTING FOR NOW.<br />" +
    		"These can be set later on using the Settings -> Email Dialog box." +
			"<br /><br /><br />" +
			"</p>" +
			"</html>"
        	);
        contentPanel.add(jTextAreaDescr, java.awt.BorderLayout.NORTH);
        
        
        
        JPanel secondaryPanel = new JPanel();
        secondaryPanel.add(contentPanel, BorderLayout.NORTH);
        add(secondaryPanel, BorderLayout.WEST);
        
	}  

    public boolean isFormFilled() {
    	return true;
    }

    private JPanel getContentPanel() {
    	
		JPanel jpc = new JPanel();
		jpc.setLayout(new BorderLayout());
		JPanel jp = new JPanel();
		jpc.add(jp,BorderLayout.CENTER);
		jp.setLayout(new GridLayout(7,2,30,20));
		for (int i = 0;i < keys.length;i++) {
			
			//comment
			JLabel label = new JLabel(keyComments[i]);
			jp.add(label);
			
			//text
			JPanel txtjp = new JPanel(new BorderLayout());
			if (keys[i].equals("mail.password")) {
					jtxt[i] = new JPasswordField(); 
			} else {
					jtxt[i] = new JTextField();
			}
			jtxt[i].setToolTipText(toolTip[i]);
			txtjp.add(jtxt[i], BorderLayout.NORTH);
			txtjp.add(new JLabel(exValue[i]), BorderLayout.SOUTH);
			jp.add(txtjp);
		}
		
		return jpc;
		
	}

    private ImageIcon getImageIcon() {
    	return null;
    }
    
    public void populateKeys() {
		for (int i = 0;i < keys.length;i++) {
			jtxt[i].setText(SmtpPanelDescriptor.p.getProperty(keys[i]));
		}
    }

}
