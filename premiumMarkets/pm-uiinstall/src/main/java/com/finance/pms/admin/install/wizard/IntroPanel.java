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

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;


// TODO: Auto-generated Javadoc
/**
 * The Class IntroPanel.
 * 
 * @author Guillaume Thoreton
 */
public class IntroPanel extends JPanel {
 
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6101824066434845051L;
	
	/** The blank space. */
	private JLabel blankSpace;
    
    /** The j label1. */
    private JLabel jLabel1;
    
    /** The j label2. */
    private JLabel jLabel2;
    
    /** The j label3. */
    private JLabel jLabel3;
    
    /** The j label4. */
    private JLabel jLabel4;
    
    /** The j label5. */
    private JLabel jLabel5;
    
    /** The j label6. */
    private JLabel jLabel6;
    
    /** The j label7. */
    private JLabel jLabel7;
    
    /** The j label8. */
    private JLabel jLabel8;
    
    /** The j label9. */
    private JLabel jLabel9;

    /** The welcome title. */
    private JLabel welcomeTitle;
    
    /** The content panel. */
    private JPanel contentPanel;
    
    /** The icon label. */
    private JLabel iconLabel;
    
    /** The icon. */
    private ImageIcon icon;
    
    /**
     * Instantiates a new intro panel.
     * 
     * @author Guillaume Thoreton
     */
    public IntroPanel() {
        
        iconLabel = new JLabel();
        contentPanel = getContentPanel();
        contentPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        icon = getImageIcon();

        setLayout(new java.awt.BorderLayout());

        if (icon != null)
            iconLabel.setIcon(icon);
        
        iconLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        
        add(iconLabel, BorderLayout.WEST);
        
        JPanel secondaryPanel = new JPanel();
        secondaryPanel.add(contentPanel, BorderLayout.NORTH);
        add(secondaryPanel, BorderLayout.CENTER);
    }
    
    
    /**
     * Gets the content panel.
     * 
     * @return the content panel
     */
    private JPanel getContentPanel() {
        
        JPanel contentPanel1 = new JPanel();
        JPanel jPanel1 = new JPanel();
        
        welcomeTitle = new JLabel();
        blankSpace = new JLabel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel7 = new JLabel();
        jLabel6 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        

        contentPanel1.setLayout(new java.awt.BorderLayout());

        welcomeTitle.setFont(new java.awt.Font("MS Sans Serif", Font.BOLD, 20));
        welcomeTitle.setText("Welcome to Premium Markets!");
        contentPanel1.add(welcomeTitle, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.GridLayout(0, 1));

        jPanel1.add(blankSpace);
        jLabel1.setText("You are going to install Premium Markets.");
        jPanel1.add(jLabel1);
       
        jLabel5.setText("Along the installation process, You may require the following :");
        jPanel1.add(jLabel5);
        jLabel6.setText(" - Your ISP smtp host name and login paramaters, in order to set up email notification.");
        jPanel1.add(jLabel6);
        jLabel7.setText(" - An installation folder. As this software stores quotations data, it will need around of 1GO of disk resources for a start.");
        jPanel1.add(jLabel7);
        jLabel8.setText("");
        jPanel1.add(jLabel8);
        jLabel3.setText("Depending on your hardware resources, it may take a few minutes to unpack and install.");
        jPanel1.add(jLabel3);
        jLabel9.setText("");
        jPanel1.add(jLabel9);
        jLabel4.setText("Thanks for using Premium Markets.");
        jPanel1.add(jLabel4);
        
        try {
			Properties pbuild = new Properties();
			pbuild.load(this.getClass().getResourceAsStream("/pmsbuild.properties"));
			
			JLabel panel1 = new JLabel();
			JLabel panel2 = new JLabel();
			JLabel panel3 = new JLabel();
			
			jLabel2.setText("");
			panel1.setText("Premium Markets");
			panel2.setText("Copyright (c) Thoreton Guillaume. see <http://www.gnu.org/licenses/>");
			panel3.setText("Build : "+ pbuild.getProperty("application.buildtime"));
			
			jPanel1.add(jLabel2);
			jPanel1.add(panel1);
			jPanel1.add(panel2);
			jPanel1.add(panel3);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        contentPanel1.add(jPanel1, java.awt.BorderLayout.CENTER);

        return contentPanel1;
        
    }

    /**
     * Gets the image icon.
     * 
     * @return the image icon
     */
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
