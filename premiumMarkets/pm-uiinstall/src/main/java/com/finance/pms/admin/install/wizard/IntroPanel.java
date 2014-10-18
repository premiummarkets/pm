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
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;

/**
 * The Class IntroPanel.
 * 
 * @author Guillaume Thoreton
 */
public class IntroPanel extends JPanel {
 
	private static final long serialVersionUID = -6101824066434845051L;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel welcomeTitle;
    private JPanel contentPanel;
    private JLabel iconLabel;
    private ImageIcon icon;
    
	private String copyRights;
    
    
    public IntroPanel( String siteUrl, String introButtonTxt, String copyRights) {
        
    	this.copyRights = copyRights;
        iconLabel = new JLabel();
        contentPanel = getContentPanel();
        contentPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        icon = getImageIcon();

        setLayout(new java.awt.BorderLayout());

        if (icon != null) iconLabel.setIcon(icon);
        
        iconLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        
        add(iconLabel, BorderLayout.WEST);
        
        JPanel secondaryPanel = new JPanel();
        secondaryPanel.setLayout(new BorderLayout());
        secondaryPanel.add(contentPanel, BorderLayout.NORTH);
        
	    try {
			final URI uri = new URI("http://"+siteUrl+"/");
			
			class OpenUrlAction implements ActionListener {
			    @Override public void actionPerformed(ActionEvent e) {   
			    	ProgressPanel.open(uri);
			    }
			 }
			
			JButton button = new JButton();
			button.setText(introButtonTxt);
			button.setBorderPainted(false);
			button.setOpaque(false);
			button.setBackground(Color.WHITE);
			button.setToolTipText(uri.toString());
			button.addActionListener(new OpenUrlAction());
			button.setFocusable(true);
			
			secondaryPanel.add(button, BorderLayout.CENTER);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
        add(secondaryPanel, BorderLayout.CENTER);
    }

    private JPanel getContentPanel() {
        
        JPanel contentPanel1 = new JPanel();
        JPanel jPanel1 = new JPanel();
        
        welcomeTitle = new JLabel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();

        contentPanel1.setLayout(new java.awt.BorderLayout());

        welcomeTitle.setFont(new java.awt.Font("MS Sans Serif", Font.BOLD, 16));
        welcomeTitle.setText("Welcome to "+Install.APP_NAME+"!");
        contentPanel1.add(welcomeTitle, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.GridLayout(0, 1));

        jLabel1.setText("You are going to install "+Install.APP_NAME);
        jPanel1.add(jLabel1);
       
        jLabel2.setText(
        		"<html>" +
        		"Along the installation process, You may require the following : <br />"+
        		"<ul>"+
        		"<li>Your ISP SMTP host name and login parameters, in order to set up email notification.</li>"+
        		"<li>An installation folder. As this software stores quotations data, it will need around of 1GO of disk resources for a start.</li>"+
        		"</ul>"+
        		"Depending on your hardware resources, it may take a few minutes to unpack and install.<br />"+
        		"Thanks for using "+Install.APP_NAME+"."+
        		"</html>");
        jPanel1.add(jLabel2);
        
        try {
			Properties pbuild = new Properties();
			pbuild.load(this.getClass().getResourceAsStream("/pmsbuild.properties"));
			
			JLabel panel1 = new JLabel();
			JLabel panel3 = new JLabel();

			panel1.setText("<html>"+Install.APP_NAME+"<br />"+copyRights+"</html>");
			panel3.setText("Build : "+ pbuild.getProperty("application.buildtime"));
			
			jPanel1.add(jLabel2);
			jPanel1.add(panel1);
			jPanel1.add(panel3);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        contentPanel1.add(jPanel1, java.awt.BorderLayout.CENTER);

        return contentPanel1;
        
    }
    
    private ImageIcon getImageIcon() {
    	URL resource = this.getClass().getClassLoader().getResource(Install.iconFile+".png");
    	if (resource != null){
    		return new ImageIcon(resource);
    	} else {
    		return new ImageIcon();
    	}
    }
    
    class ActivatedHyperlinkListener implements HyperlinkListener {

    	JEditorPane editorPane;

    	public ActivatedHyperlinkListener(JEditorPane editorPane) {
    		this.editorPane = editorPane;
    	}

    	public void hyperlinkUpdate(HyperlinkEvent hyperlinkEvent) {
    		HyperlinkEvent.EventType type = hyperlinkEvent.getEventType();
    		final URL url = hyperlinkEvent.getURL();
    		if (type == HyperlinkEvent.EventType.ENTERED) {
    			System.out.println("URL: " + url);
    		} else if (type == HyperlinkEvent.EventType.ACTIVATED) {
    			System.out.println("Activated");
    			Document doc = editorPane.getDocument();
    			try {
    				editorPane.setPage(url);
    			} catch (IOException ioException) {
    				System.out.println("Error following link");
    				editorPane.setDocument(doc);
    			}
    		}
    	}
    }

}
