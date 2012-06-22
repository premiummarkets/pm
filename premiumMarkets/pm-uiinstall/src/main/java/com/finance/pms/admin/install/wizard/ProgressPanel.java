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
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import com.finance.pms.admin.install.TextAreaStream;

// TODO: Auto-generated Javadoc
/**
 * The Class ProgressPanel.
 * 
 * @author Guillaume Thoreton
 */
public class ProgressPanel extends JPanel  implements PropertyChangeListener {
 
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3084505786992385104L;
	
    /** The blank space. */
    private JLabel blankSpace;
    
    /** The j label1. */
    private JLabel jLabel1;

    /** The j label2. */
    private JLabel jLabel2;
    
    /** The j label3. */
   // private JTextArea jTextAreaDescr;
    private JLabel jTextAreaDescr;
    
    /** The j panel1. */
    private JPanel jPanel1;
    
    /** The progress description. */
    private JLabel progressDescription;
    
    /** The progress sent. */
    private JProgressBar progressBar;
    
    /** The content panel. */
    private JPanel contentPanel;
    
    /** The icon label. */
    private JLabel iconLabel;
    
    /** The separator. */
    private JSeparator separator;
    
    /** The text label. */
    private JLabel textLabel;
    
    /** The title panel. */
    private JPanel titlePanel;
    
    private JScrollPane jScrollPane;
    private JTextArea jTextArea;
    
    public TextAreaStream textAreaStream; 
        
    /**
 * Instantiates a new progress panel.
 * 
 * @author Guillaume Thoreton
 */
public ProgressPanel() {
        
        super();
                
        contentPanel = getContentPanel();
        ImageIcon icon = getImageIcon();
        
        titlePanel = new javax.swing.JPanel();
        textLabel = new javax.swing.JLabel();
        iconLabel = new javax.swing.JLabel();
        separator = new javax.swing.JSeparator();

        setLayout(new java.awt.BorderLayout());
        setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));

        titlePanel.setLayout(new java.awt.BorderLayout());
        titlePanel.setBackground(Color.gray);
        
        textLabel.setBackground(Color.gray);
        textLabel.setFont(new Font("MS Sans Serif", Font.BOLD, 20));
        textLabel.setText("Unpacking and copying files");
        textLabel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        textLabel.setOpaque(true);
        
        jTextArea = new JTextArea(15, 30);
        textAreaStream = new TextAreaStream(jTextArea);
        jTextArea.setEditable(false);
        jScrollPane = new JScrollPane(jTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        iconLabel.setBackground(Color.gray);
        if (icon != null)
            iconLabel.setIcon(icon);
        
        titlePanel.add(textLabel, BorderLayout.CENTER);
        titlePanel.add(iconLabel, BorderLayout.EAST);
        titlePanel.add(separator, BorderLayout.SOUTH);

        add(titlePanel, BorderLayout.NORTH);
        JPanel secondaryPanel = new JPanel();
        secondaryPanel.add(contentPanel, BorderLayout.NORTH);
        add(secondaryPanel, BorderLayout.CENTER);
        add(jScrollPane, BorderLayout.SOUTH);
        
        
    }  
    
    /**
     * Sets the progress text.
     * 
     * @param s the new progress text
     */
    public void setProgressText(String s) {
        progressDescription.setText(s);
    }
    
    /**
     * Sets the progress value.
     * 
     * @param i the new progress value
     */
    public void setProgressValue(int i) {
        progressBar.setValue(i);
    }
    
    /**
     * Gets the content panel.
     * 
     * @return the content panel
     */
    private JPanel getContentPanel() {            
        
        JPanel contentPanel1 = new JPanel();
        contentPanel1.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));

        jPanel1 = new JPanel();
        blankSpace = new JLabel();
        progressBar = new JProgressBar(0,100);
        progressDescription = new JLabel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        
        contentPanel1.setLayout(new java.awt.BorderLayout());
        contentPanel1.add(blankSpace, BorderLayout.NORTH);
        
//        jTextAreaDescr = new JTextArea(10,30);
        jTextAreaDescr = new JLabel();
        jTextAreaDescr.setBackground(new Color(192, 192, 192));
        jTextAreaDescr.setFocusable(true);
        jTextAreaDescr.setText("<html>"+
        		"<br />" +
"Premium Markets is an automated financial technical analysis system. <br />"+
"It implements a graphical environment for monitoring financial technical analysis major indicators and for portfolio management. <br />"+
"In its advanced packaging, not provided under this license, it also includes : <br />"+
"<ul>" +
	"<li>Screening of financial web sites to pickup the best market shares,</li>"+
	"<li>Forecast of share prices trend changes on the basis of financial technical analysis,<br /> "+
	"(with a rate of around 70% of forecasts being successful observed while back testing over DJI, FTSE, DAX and SBF),</li>"+
	"<li>Back testing and Email sending on buy and sell alerts triggered while scanning markets and user defined portfolios. </li>" +
"</ul>" +
"Please refer to Premium Markets PRICE TREND FORECAST web portal at " +
"<a href=\"http://premiummarkets.elasticbeanstalk.com/\">http://premiummarkets.elasticbeanstalk.com/</a><br /> for a preview of more advanced features.<br /><br /><br />" +
"</html>");
        
        contentPanel1.add(jTextAreaDescr, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.GridLayout(0, 1));
        
        jLabel1.setText("Unpacking Premium Markets and a small sample data base.");
        jLabel1.setFont(new java.awt.Font("MS Sans Serif", 1, 11));
        jLabel2.setText("You will be invited to set up your specific market later on while running the tool.");
        jLabel2.setFont(new java.awt.Font("MS Sans Serif", 1, 11));
      
        progressBar.setStringPainted(true);
        jPanel1.add(progressBar);
        
        jPanel1.add(jLabel1);
        jPanel1.add(jLabel2);
        
//        progressDescription.setFont(new java.awt.Font("MS Sans Serif", 1, 11));
//        progressDescription.setText("Downloading and Extracting files ...");
//        jPanel1.add(progressDescription);

        

        contentPanel1.add(jPanel1, java.awt.BorderLayout.SOUTH);
        
        return contentPanel1;
    }
    
    
    /**
     * Gets the image icon.
     * 
     * @return the image icon
     */
    private ImageIcon getImageIcon() {        
        return null;
    }
    
	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
//			if (progress < 0) progress = 0;
//			if (progress > 100) progress = 100;
			progressBar.setValue((int) progress);
		}
	}
    
}
