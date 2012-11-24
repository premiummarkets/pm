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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.finance.pms.admin.install.FolderSelect;


// TODO: Auto-generated Javadoc
/**
 * The Class InstallFolderPanel.
 * 
 * @author Guillaume Thoreton
 */
public class InstallFolderPanel extends JPanel {
 

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3412318352164432070L;

    
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
    
    /** The piggy market squeak folder. */
    static protected File piggyMarketSqueakFolder;
    
    /** The piggy market squeak folder name. */
    static protected String piggyMarketSqueakFolderName;
        
    /**
     * Instantiates a new install folder panel.
     * 
     * @author Guillaume Thoreton
     */
    public InstallFolderPanel() {
        
        super();
                
        contentPanel = getContentPanel();
        ImageIcon icon = getImageIcon();
        
        titlePanel = new javax.swing.JPanel();
        textLabel = new javax.swing.JLabel();
        iconLabel = new javax.swing.JLabel();
        separator = new javax.swing.JSeparator();

        setLayout(new java.awt.BorderLayout());

        titlePanel.setLayout(new java.awt.BorderLayout());
        titlePanel.setBackground(Color.gray);
        
        textLabel.setBackground(Color.gray);
        textLabel.setFont(new Font("MS Sans Serif", Font.BOLD, 20));
        textLabel.setText("Choose an installation folder ...");
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
     * Gets the content panel.
     * 
     * @return the content panel
     */
    private JPanel getContentPanel() {            
    	
    	JPanel contentPanel = new JPanel();
    	contentPanel.setLayout(new BorderLayout());
    	
        JPanel contentPanel1 = new JPanel();
   
        contentPanel1.setLayout(new FlowLayout());
        
        Preferences prefs = Preferences.userRoot().node("com.finance.pms.admin.install");
        InstallFolderPanel.piggyMarketSqueakFolderName = prefs.get("pm.default.install.folder", System.getProperty("user.dir"));
        final JTextField jt = new JTextField(InstallFolderPanel.piggyMarketSqueakFolderName, 40);
        
        jt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateInstallPath(jt);
			}
        });
        jt.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				updateInstallPath(jt);
			}
        });

        contentPanel1.add(jt);
        
		JButton jb = new JButton("Browse ...");
		jb.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				//Installation folder
				File installationFolder = null;
				
				FolderSelect fs = new FolderSelect();
				fs.showOpenDialog((JButton)e.getSource());
				installationFolder = fs.getSelectedFile();
				
				if (installationFolder != null && !fs.getCanceled()) {
					jt.setText(installationFolder.getAbsolutePath());
				}
				
				updateInstallPath(jt);
			}
			
		});		
		contentPanel1.add(jb);   
		
		
		JPanel contentPanel2 = new JPanel();
		contentPanel2.add(new JLabel("As it stores historical data, the software will need arround 1 GO bytes of disk."));
        
		
		contentPanel.add(contentPanel1,BorderLayout.WEST);
		contentPanel.add(contentPanel2,BorderLayout.SOUTH);
		
        return contentPanel;
    }
    
	private void updateInstallPath(final JTextField jt) {
		InstallFolderPanel.piggyMarketSqueakFolderName = jt.getText();
		Preferences prefs = Preferences.userRoot().node("com.finance.pms.admin.install");
		prefs.put("pm.default.install.folder",InstallFolderPanel.piggyMarketSqueakFolderName);
		try {
			prefs.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
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
