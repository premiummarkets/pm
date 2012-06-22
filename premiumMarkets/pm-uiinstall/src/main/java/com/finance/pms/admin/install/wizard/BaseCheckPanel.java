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
        textLabel.setFont(new Font("MS Sans Serif", Font.BOLD, 20));
        textLabel.setText("Missing sample data base");
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
		//JEditorPane jep = new JEditorPane();
		jep.setEditable(false);
		jep.setEditorKit(new HTMLEditorKit());
		StringBuffer sb = new StringBuffer();
		sb.append("<p>");
		sb.append("The archive you downloaded is provided only for source forge archiving.<br>");
		sb.append("A more straight forward way to install Premium Markets is to follow this link : ");
		sb.append("<a href='http://sourceforge.net/projects/pmsqueak/files/PremiumMarkets.jnlp'>http://sourceforge.net/projects/pmsqueak/files/PremiumMarkets.jnlp</a><br>");
		sb.append("</p>");
		//sb.append("<br><br>");
		sb.append("<p>");
		sb.append("However, if you want to carry on with this installation, <br>");
		sb.append("Please download the sample data base from : ");
		sb.append("<a href='http://sourceforge.net/project/showfiles.php?group_id=214811'>http://sourceforge.net/project/showfiles.php?group_id=214811</a><br>");
		sb.append("Copy it in the \"lib\" folder of this unpacked archive ");
		//sb.append("<br><br>");
		sb.append("and run thins install again :).<br>");
		sb.append("</p>");
//		sb.append("<p align='right'>");
//		sb.append("<b>Thank you!</b>");
//		sb.append("</p>");
		Reader r = new StringReader(sb.toString());
		try {
			jep.read(r, new HTMLDocument());
		} catch (IOException e) {
			// TODO Auto-generated catch block
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

	/* (non-Javadoc)
	 * @see javax.swing.event.HyperlinkListener#hyperlinkUpdate(javax.swing.event.HyperlinkEvent)
	 */
	public void hyperlinkUpdate(HyperlinkEvent event) {
	    if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
	        try {
	          jep.setPage(event.getURL());
	          //urlField.setText(event.getURL().toExternalForm());
	        } catch(IOException ioe) {
//	          warnUser("Can't follow link to " 
//	                   + event.getURL().toExternalForm() + ": " + ioe);
	        	ioe.printStackTrace();
	        }
	      }
		
	}
    

}
