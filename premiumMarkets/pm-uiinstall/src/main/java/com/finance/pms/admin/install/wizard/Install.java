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

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.finance.pms.admin.install.SystemTypes;
import com.nexes.wizard.Wizard;
import com.nexes.wizard.WizardPanelDescriptor;

// TODO: Auto-generated Javadoc
/**
 * The Class Install.
 * 
 * @author Guillaume Thoreton
 */
public class Install {
	
	/** The Constant archName. */
	public static final String archName = "PremiumMarkets.zip";
	
	/** The Constant iconFile. */
	public static final String iconFile = "icons/icon";
	
	/** The Constant backGround. */
	public static final String backGround = "icons/squeakyPig.jpg";
	
	/** The Constant license. */
	public static final String license = "COPYING";
	
	/** The Constant dbName. */
	public static final String dbName = "derby";
	
	/** The Constant piggyMarketSqueak. */
	public static final String piggyMarketSqueak = "PremiumMarkets";
	
	/** The system type. */
	public static SystemTypes systemType = SystemTypes.WINDOWS;
	
	/** The debug. */
	public static Boolean debug;
	
	private static Wizard wizard;
	

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void main(String[] args) {
		//Win management
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//debug
		if (args.length > 0 && args[0].equals("-d")) {
			Install.debug = new Boolean(args[1]);
		} else {
			Install.debug = false;
		}
		//arch
		String sysa = (String) System.getProperties().get("os.arch");
		System.out.println(sysa);
		String sysn = (String) System.getProperties().get("os.name");
		System.out.println(sysn);
		String sysv = (String) System.getProperties().get("os.version");
		System.out.println(sysv);
		Install.systemType = SystemTypes.getType(sysn);
		
		//Start wizard
		//final Wizard wizard = new Wizard();
		Install.wizard = new Wizard();
		
        wizard.getDialog().addMouseMotionListener(new MouseMotionListener() {
			
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			public void mouseMoved(MouseEvent e) {
//				System.out.println(e.getPoint());
//				System.out.println("size : "+wizard.getDialog().getSize());
//				System.out.println(wizard.getDialog().findComponentAt(e.getPoint()));
			}
		});
        
		wizard.getDialog().setTitle("Premium Markets");
		WizardPanelDescriptor descriptor1 = new IntroPanelDescriptor();
		wizard.registerWizardPanel(IntroPanelDescriptor.IDENTIFIER, descriptor1);
		WizardPanelDescriptor descriptor11 = new UpdateUrlPanelDescriptor();
		wizard.registerWizardPanel(UpdateUrlPanelDescriptor.IDENTIFIER, descriptor11);
		WizardPanelDescriptor descriptor2 = new LicencePanelDescriptor();
		wizard.registerWizardPanel(LicencePanelDescriptor.IDENTIFIER, descriptor2);
		WizardPanelDescriptor descriptor3 = new InstallFolderPanelDescriptor();
		wizard.registerWizardPanel(InstallFolderPanelDescriptor.IDENTIFIER, descriptor3);
		WizardPanelDescriptor descriptor31 = new BaseCheckPanelDescriptor();
		wizard.registerWizardPanel(BaseCheckPanelDescriptor.IDENTIFIER, descriptor31);
		WizardPanelDescriptor descriptor4 = new ProgressPanelDescriptor();
		wizard.registerWizardPanel(ProgressPanelDescriptor.IDENTIFIER, descriptor4);
		WizardPanelDescriptor descriptor5 = new SmtpPanelDescriptor();
		wizard.registerWizardPanel(SmtpPanelDescriptor.IDENTIFIER, descriptor5);
		WizardPanelDescriptor descriptor6 = new DonePanelDescriptor();
		wizard.registerWizardPanel(DonePanelDescriptor.IDENTIFIER, descriptor6);
		wizard.setCurrentPanel(IntroPanelDescriptor.IDENTIFIER);
		int ret = wizard.showModalDialog();
		System.out.println("Dialog return code is (0=Finish,1=Cancel,2=Error): " + ret);

		if (ret == 0) {
			final String guiShell = InstallFolderPanel.piggyMarketSqueakFolder.getAbsolutePath() + 
					File.separator + "shell" +
					File.separator + "gui" + Install.systemType.getShext();
			
			final String params[];
			
			System.out.println("OS like : "+Install.systemType);
			if (Install.systemType.equals(SystemTypes.WINDOWS)) {		
				Install.windowsPostInstall(InstallFolderPanel.piggyMarketSqueakFolder.getAbsolutePath());
				params = new String[] { guiShell, InstallFolderPanel.piggyMarketSqueakFolder.getAbsolutePath()};
			} else {
				params = new String[] {"/bin/bash", guiShell, InstallFolderPanel.piggyMarketSqueakFolder.getAbsolutePath() };
			}
			
			for (String string : params) {
				System.out.println("launch gui params : "+string);
			}
			
			Runtime runTime = Runtime.getRuntime();
			try {

				while (ProgressPanelDescriptor.connection != null && !ProgressPanelDescriptor.connection.isClosed()) {
					System.out.println("Waiting for sql connection to close : is close ? "+ProgressPanelDescriptor.connection.isClosed());
					Thread.sleep(500);
				}
				System.out.println("Install sql connection are closed : ");

				//Thread.sleep(2000);
				Process process = runTime.exec(params, null, InstallFolderPanel.piggyMarketSqueakFolder);
				BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

				String line = null;
				while ((line = input.readLine()) != null) {
					System.out.println(line);
				}

				//System.out.println("Exit Value = " + p.waitFor());
			} catch (Exception e) {
				System.out.println("error===" + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Windows post install.
	 * 
	 * @param installPath the install path
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void windowsPostInstall(String installPath) {
		
		System.out.println("Properties : "+System.getProperties());
		
		//Modify db path for an absolute path
	    File pfile;
	    Properties props = new Properties();
		//Load props
		try {
			pfile = new File(InstallFolderPanel.piggyMarketSqueakFolder.getAbsoluteFile() + File.separator + "db.properties");
			FileInputStream propFileIS = new FileInputStream(pfile);
			props.load(propFileIS);
			props.put("dbpath", installPath+File.separator+"derby"+File.separator);
			props.store(new FileOutputStream(pfile), "Added settings properties for windows");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//desktop icon
		Runtime runtime = Runtime.getRuntime();
		String[] runtimeParams = new String[]{installPath+File.separator+"shell"+File.separator+"desktop_icon.bat", installPath};
		for (String string : runtimeParams) {
			System.out.println("launch desktop_icon params : "+string);
		}
		
		try {
			Process guiBatProcess = runtime.exec(runtimeParams, null, InstallFolderPanel.piggyMarketSqueakFolder);
			
			BufferedReader input = new BufferedReader(new InputStreamReader(guiBatProcess.getInputStream()));
			String line = null;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
	}
	
	public static void selectNextButton() {
		Box next = 
			((Box) ((JPanel)((JPanel)((JLayeredPane)((JRootPane)
					Install.wizard.getDialog().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0)).getComponent(1));
        JButton button = (JButton) next.getComponent(2);
        button.requestFocusInWindow();
	} 
}
