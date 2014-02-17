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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang.StringEscapeUtils;

import com.finance.pms.admin.install.FolderSelect;


/**
 * The Class InstallFolderPanel.
 * 
 * @author Guillaume Thoreton
 */
public class InstallFolderPanel extends JPanel {
 
	private static final long serialVersionUID = 3412318352164432070L;

    private JPanel contentPanel;
    private JLabel iconLabel;
    private JSeparator separator;
    private JLabel textLabel;
    private JPanel titlePanel;
    
    static private File piggyMarketSqueakFolder;
	private JTextField jt;
        

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
        textLabel.setFont(new Font("MS Sans Serif", Font.BOLD, 16));
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
    
    
    private JPanel getContentPanel() {            
    	
    	JPanel contentPanel = new JPanel();
    	contentPanel.setLayout(new BorderLayout());
    	
        JPanel contentPanel1 = new JPanel();
   
        contentPanel1.setLayout(new FlowLayout());
        
        
        jt = new JTextField(initDefaultInstallFolder().getAbsolutePath(), 40);
        jt.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (checkInstallPath(jt.getText())) {
					}
				}
			}
		});

        contentPanel1.add(jt);
        
		JButton jb = new JButton("Browse ...");
		jb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				FolderSelect fs = new FolderSelect();
				fs.setCurrentDirectory(new File(jt.getText()));
				fs.showOpenDialog((JButton)e.getSource());
				File selectedFile = fs.getSelectedFile();

				if (selectedFile != null && !fs.getCanceled()) {
					if (checkInstallPath(selectedFile.getAbsolutePath())) {
						jt.setText(selectedFile.getAbsolutePath());
						Install.selectNextButton();
					}
				}
			}
			
		});		
		
		contentPanel1.add(jb);   
		
		
		JPanel contentPanel2 = new JPanel();
		contentPanel2.add(new JLabel("As it stores historical data, the software will need around 1 GO bytes of disk."));
        
		
		contentPanel.add(contentPanel1,BorderLayout.WEST);
		contentPanel.add(contentPanel2,BorderLayout.SOUTH);
		
        return contentPanel;
    }

	protected File initDefaultInstallFolder() {
		
		Preferences prefs = Preferences.userRoot().node("com.finance.pms.admin.install");
        File path = new File(StringEscapeUtils.unescapeJava(prefs.get("pm.default.install.folder", System.getProperty("user.dir"))));
        
		//if (path != null &&  path.getParentFile() != null && path.getParentFile().exists()) {
        if (path != null && path.exists()) {
			System.out.println("Prefs install folder name is valid : "+ path.getAbsolutePath());
			//path = path.getParentFile();
		} else {
			System.out.println("Prefs install folder name is invalid : "+ path+ ". Setting back to "+System.getProperty("user.dir"));
			path = new File(System.getProperty("user.dir"));
		}
		
		return path;
		
	}
    
	protected Boolean checkInstallPath(String instFolderName) {
		
//		if (instFolderName.matches("[A-Za-z0-9/\\\\:_\\-+=.\\[\\]{}()~]+")) {
//			return true;
//		} else {
//			errorPathDialog();
//			return false;
//		}
		
		File file = new File(instFolderName);
		try {
			file.getCanonicalPath();
		} catch (IOException e) {
			System.out.println("Invalid path "+instFolderName +" : "+e);
			errorPathDialog(instFolderName);
			return false;
		}
		if (file.exists()) {
			if (file.isDirectory()) {
				return true;
			} else {
				errorPathDialog(instFolderName);
				return false;
			}
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Invalid path "+instFolderName +" : "+e);
				errorPathDialog(instFolderName);
				return false;
			}
			file.delete();
		}
		
		return true;
	}

	protected void errorPathDialog(String instFolderName) {
		
		JDialog errorPopup = new JDialog(Install.getWizard().getDialog(), "Invalid path", true);
		errorPopup.setLocation(MouseInfo.getPointerInfo().getLocation());
		JPanel contentPane = new JPanel();
		contentPane.add(new JLabel("Invalid installation path : "+instFolderName));
		errorPopup.setContentPane(contentPane);
		errorPopup.pack();
		errorPopup.setVisible(true);
		errorPopup.toFront();
		
	}

    private ImageIcon getImageIcon() {        
        return null;
    }

	public void addTextFieldReturn(KeyListener installFolderPanelDescriptor) {
		jt.addKeyListener(installFolderPanelDescriptor);
	}
	
	protected static File setPmFolder(String updatedName) {
		piggyMarketSqueakFolder = new File(updatedName + File.separator + Install.piggyMarketSqueak);
		Preferences prefs = Preferences.userRoot().node("com.finance.pms.admin.install");
		prefs.put("pm.default.install.folder", StringEscapeUtils.escapeJava(updatedName));
		try {
			prefs.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return piggyMarketSqueakFolder;
	}

	public static File getPmFolder() {
		return piggyMarketSqueakFolder;
	}

	public JTextField getJt() {
		return jt;
	}
    
}
