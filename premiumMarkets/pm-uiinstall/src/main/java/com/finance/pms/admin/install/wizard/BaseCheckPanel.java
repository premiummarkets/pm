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
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;


// TODO: Auto-generated Javadoc
/**
 * The Class BaseCheckPanel.
 * 
 * @author Guillaume Thoreton
 */
public class BaseCheckPanel extends JPanel implements HyperlinkListener {
 
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7214467104369545258L;
    
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
    
    /** The jep. */
    JEditorPane jep;
        
    /**
     * Instantiates a new base check panel.
     * 
     * @author Guillaume Thoreton
     */
    public BaseCheckPanel() {
    	
        super();
        jep = new JEditorPane();
        
        contentPanel = getContentPanel();
        contentPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        ImageIcon icon = getImageIcon();
        
        titlePanel = new javax.swing.JPanel();
        textLabel = new javax.swing.JLabel();
        iconLabel = new javax.swing.JLabel();
        separator = new javax.swing.JSeparator();

        setLayout(new java.awt.BorderLayout());

        titlePanel.setLayout(new java.awt.BorderLayout());
        if (icon != null)  iconLabel.setIcon(icon);
        
        textLabel.setBackground(Color.gray);
        textLabel.setFont(new Font("MS Sans Serif", Font.BOLD, 16));
        textLabel.setText("Missing sample database");
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
     * Gets the content panel.
     * 
     * @return the content panel
     */
    private JPanel getContentPanel() {
		JPanel ed = new JPanel();
		
		ed.setLayout(new BorderLayout());
		jep.setEditable(false);
		jep.setEditorKit(new HTMLEditorKit());
		StringBuffer sb = new StringBuffer();
		sb.append("<p>");
		sb.append("The archive you downloaded is provided only for source forge archiving.<br>");
		sb.append("A more straight forward way to install Premium Markets is to follow this link : ");
		sb.append("<a href='http://sourceforge.net/projects/pmsqueak/files/PremiumMarkets.jnlp'>http://sourceforge.net/projects/pmsqueak/files/PremiumMarkets.jnlp</a><br>");
		sb.append("</p>");
		sb.append("<p>");
		sb.append("However, if you want to carry on with this installation, <br>");
		sb.append("Please download the sample data base from : ");
		sb.append("<a href='http://sourceforge.net/project/showfiles.php?group_id=214811'>http://sourceforge.net/project/showfiles.php?group_id=214811</a><br>");
		sb.append("Copy it in the \"lib\" folder of this unpacked archive ");
		sb.append("and run thins install again :).<br>");
		sb.append("</p>");
		Reader r = new StringReader(sb.toString());
		try {
			jep.read(r, new HTMLDocument());
		} catch (IOException e) {
			e.printStackTrace();
		}
		ed.add(jep,BorderLayout.CENTER);
		return ed;
	}
    
    /**
     * Gets the image icon.
     * 
     * @return the image icon
     */
    private ImageIcon getImageIcon() {
    	return null;

    }


	public void hyperlinkUpdate(HyperlinkEvent event) {
	    if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
	        try {
	          jep.setPage(event.getURL());
	        } catch(IOException ioe) {
	        	ioe.printStackTrace();
	        }
	      }
		
	}
    

}
