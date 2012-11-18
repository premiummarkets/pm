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
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;


// TODO: Auto-generated Javadoc
/**
 * The Class LicencePanel.
 * 
 * @author Guillaume Thoreton
 */
public class LicencePanel extends JPanel {
 
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7214467104369545258L;
	
    /** The j check box1. */
    private javax.swing.JCheckBox jCheckBox1;
    
    /** The content panel. */
    private JPanel contentPanel;
    
    /** The icon label. */
    private JLabel iconLabel;
    
    /** The title panel. */
    private JPanel titlePanel;
        
    /**
     * Instantiates a new licence panel.
     * 
     * @author Guillaume Thoreton
     */
    public LicencePanel() {
    	
        super();
        
        contentPanel = getContentPanel();
        contentPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        ImageIcon icon = getImageIcon();
        
        titlePanel = new javax.swing.JPanel();
        iconLabel = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        titlePanel.setLayout(new java.awt.BorderLayout());
        if (icon != null)  iconLabel.setIcon(icon);
        
        titlePanel.add(iconLabel, BorderLayout.CENTER);
        titlePanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        titlePanel.setSize(10,10);
   
        add(contentPanel, BorderLayout.CENTER);

	}  
    
    /**
     * Adds the check box action listener.
     * 
     * @param l the l
     * 
     * @author Guillaume Thoreton
     */
    public void addCheckBoxActionListener(ActionListener l) {
        jCheckBox1.addActionListener(l);
    }
    
    /**
     * Checks if is check box selected.
     * 
     * @return true, if is check box selected
     */
    public boolean isCheckBoxSelected() {
        return jCheckBox1.isSelected();
    }
    
    /**
     * Gets the radio button selected.
     * 
     * @return the radio button selected
     */
    public String getRadioButtonSelected() {
    	return null;
    }
    
    /**
     * Gets the content panel.
     * 
     * @return the content panel
     */
    private JPanel getContentPanel() {
    	
		JPanel jpc = new JPanel();
		
		jpc.setLayout(new BorderLayout());
		JTextArea jep = new JTextArea(30,40);
		jep.setBackground(new Color(192, 192, 192));
		File f = extractFile(new File(System.getProperty("java.io.tmpdir")), Install.license);
		try {
			FileReader reader = new FileReader(f);
			jep.read(reader, null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jep.setEditable(false);
		JScrollPane jsb = new JScrollPane(jep, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jpc.add(jsb, BorderLayout.NORTH);
	
		jCheckBox1 = new JCheckBox("Please agree the license condition first.");
		jCheckBox1.setFont(new java.awt.Font("MS Sans Serif", Font.BOLD, 20));
		jpc.add(jCheckBox1, BorderLayout.SOUTH);
		
		
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
	 * Extract file.
	 * 
	 * @param baseFolder the base folder
	 * @param fileName the file name
	 * 
	 * @return the file
	 * 
	 * @author Guillaume Thoreton
	 */
	private File extractFile(File baseFolder, String fileName) {
		System.out.println("Writing file "+fileName+" to Folder : " + baseFolder.getAbsolutePath());
		File file = new File(baseFolder,fileName);
		file.delete();
		try {
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName);
			BufferedInputStream bis = new BufferedInputStream(is);
			FileOutputStream fos = new FileOutputStream(file);
			byte[] b = new byte[1024];
			int ch;
			while ((ch = bis.read(b)) != -1) {
				fos.write(b, 0, ch);
			}
			fos.close();
			System.out.println("Done copy of file " + fileName + " !");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}
    
    

}
