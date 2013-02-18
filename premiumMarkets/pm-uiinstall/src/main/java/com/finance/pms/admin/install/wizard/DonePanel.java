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
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class DonePanel.
 * 
 * @author Guillaume Thoreton
 */
public class DonePanel extends JPanel {
 

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3412318352164432070L;
	
	
	/** The progress description. */
	private JLabel progressDescription;
    
    /** The progress sent. */
    private JProgressBar progressSent;

    
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


	JTextArea endInstall;
        
    /**
     * Instantiates a new done panel.
     * 
     * @author Guillaume Thoreton
     */
    public DonePanel() {
        
        super();
                
      //  contentPanel = getContentPanel();
        contentPanel = new JPanel();
    	contentPanel.setLayout(new BorderLayout());
        
    	endInstall = new JTextArea();
		endInstall.setEditable(false);
		endInstall.setAlignmentY(Component.CENTER_ALIGNMENT);
		contentPanel.add(endInstall, BorderLayout.CENTER);
		//endInstall.setBackground(new Color(239, 183, 103));
		
        ImageIcon icon = getImageIcon();
        
        titlePanel = new javax.swing.JPanel();
        textLabel = new javax.swing.JLabel();
        iconLabel = new javax.swing.JLabel();
        separator = new javax.swing.JSeparator();

        setLayout(new java.awt.BorderLayout());

        titlePanel.setLayout(new java.awt.BorderLayout());
        titlePanel.setBackground(Color.gray);
        
        textLabel.setBackground(Color.gray);
        textLabel.setFont(new Font("MS Sans Serif", Font.BOLD, 16));
        textLabel.setText("Installation completed.");
        textLabel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        textLabel.setOpaque(true);

        iconLabel.setBackground(Color.gray);
        if (icon != null) iconLabel.setIcon(icon);
        
        titlePanel.add(textLabel, BorderLayout.CENTER);
        titlePanel.add(iconLabel, BorderLayout.EAST);
        titlePanel.add(separator, BorderLayout.SOUTH);

        add(titlePanel, BorderLayout.NORTH);
        JPanel secondaryPanel = new JPanel();
        secondaryPanel.add(contentPanel, BorderLayout.NORTH);
        add(secondaryPanel, BorderLayout.WEST);
        
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
        progressSent.setValue(i);
    }
    
    /**
     * Gets the image icon.
     * 
     * @return the image icon
     */
    private ImageIcon getImageIcon() {        
        return null;
    }
   
    
}
