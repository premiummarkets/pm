/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.finance.pms.admin.install.TextAreaStream;

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
        textLabel.setFont(new Font("MS Sans Serif", Font.BOLD, 16));
        textLabel.setText("Unpacking and copying files");
        textLabel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        textLabel.setOpaque(true);
        
        jTextArea = new JTextArea(10, 30);
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

    public void setProgressText(String s) {
        progressDescription.setText(s);
    }

    public void setProgressValue(int i) {
        progressBar.setValue(i);
    }

    private JPanel getContentPanel() {            
        
        JPanel contentPanel1 = new JPanel();
        contentPanel1.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));

        jPanel1 = new JPanel();
        blankSpace = new JLabel();
        progressBar = new JProgressBar(0,100);
        progressDescription = new JLabel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        
        contentPanel1.setLayout(new BorderLayout());
        
        jTextAreaDescr = new JLabel();
        jTextAreaDescr.setBackground(new Color(192, 192, 192));
        jTextAreaDescr.setFocusable(true);
        jTextAreaDescr.setText(
        	"<html>"+
        	"<p>" +
			"Premium Markets is an automated stock market analysis system.<br />"+
			"It implements a graphical environment for monitoring stock market technical analysis major indicators, <br />portfolio management and historical data charting.<br />" +
			"<br />"+
			"I also invite you for a preview of Premium Markets Forecast advanced features (not provided under this license) which includes : <br />"+
			"<ul>" +
				"<li>Screening of financial web sites to pick up the best market shares,</li>"+
				"<li>Price trend prediction based on stock market technical analysis and indexes rotation,</li> "+
				"<li>Back testing,</li>"+
				"<li>Buy sell email notifications on predictions with automated markets and user defined portfolios scanning.</li>" +
			"</ul>" +
			"<br />" +
			"</p>" +
			"</html>"
        	);
        contentPanel1.add(jTextAreaDescr, java.awt.BorderLayout.NORTH);
        
        try {
			final URI uri = new URI("http://premiummarkets.elasticbeanstalk.com/");
			
			class OpenUrlAction implements ActionListener {
			    @Override public void actionPerformed(ActionEvent e) {   
			    	open(uri);
			    }
			 }
			
			JButton button = new JButton();
			button.setText("<HTML><FONT color=\"red\">Click <FONT color=\"#000099\"><blink><U>http://premiummarkets.elasticbeanstalk.com/</U></blink></FONT> for a preview and a free workable demo of the <b>FORECAST</b> engine.</FONT></HTML>");
			button.setFont(button.getFont().deriveFont(14f));
			button.setHorizontalAlignment(SwingConstants.LEFT);
			button.setBorderPainted(false);
			button.setOpaque(false);
			button.setBackground(Color.WHITE);
			button.setToolTipText(uri.toString());
			button.addActionListener(new OpenUrlAction());
			button.setFocusable(true);
			
			contentPanel1.add(button, java.awt.BorderLayout.CENTER);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

        jPanel1.setLayout(new java.awt.GridLayout(0, 1));
        
        jLabel1.setText("Unpacking Premium Markets and a small sample database.");
        jLabel1.setFont(new java.awt.Font("MS Sans Serif", 1, 11));
        jLabel2.setText("You will be invited to set up your specific markets and stocks lists later on while running the software.");
        jLabel2.setFont(new java.awt.Font("MS Sans Serif", 1, 11));
      
        progressBar.setStringPainted(true);
        
        jPanel1.add(blankSpace);
        jPanel1.add(progressBar);
        
        jPanel1.add(jLabel1);
        jPanel1.add(jLabel2);

        contentPanel1.add(jPanel1, java.awt.BorderLayout.SOUTH);
        
        return contentPanel1;
    }
    
    static void open(URI uri) {
    	if (Desktop.isDesktopSupported()) {
    		try {
    			Desktop.getDesktop().browse(uri);
    		} catch (IOException e) { /* TODO: error handling */ }
    	} else { /* TODO: error handling */ }
    }

    private ImageIcon getImageIcon() {        
        return null;
    }
    

	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			progressBar.setValue((int) progress);
		}
	}
    
}
