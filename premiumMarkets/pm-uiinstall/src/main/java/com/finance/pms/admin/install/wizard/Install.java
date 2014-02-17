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

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.mutable.MutableBoolean;
import org.jdesktop.swingworker.SwingWorker;

import com.finance.pms.admin.install.SystemTypes;
import com.nexes.wizard.Wizard;
import com.nexes.wizard.WizardPanelDescriptor;

/**
 * The Class Install.
 * 
 * @author Guillaume Thoreton
 */
public class Install {
	
	public static final String archName = "PremiumMarkets.zip";
	public static final String iconFile = "icons/icon";
	public static final String backGround = "icons/squeakyPig.png";
	public static final String license = "COPYING";
	public static final String dbName = "derby";
	public static final String piggyMarketSqueak = "PremiumMarkets";
	public static SystemTypes systemType = SystemTypes.WINDOWS;
	
	public static Boolean debug;
	private static Wizard wizard;
	private static Boolean stop = false;
	

	public static void main(String[] args) {
		
		//Connection check
		@SuppressWarnings("all")
		final SwingWorker<Void, Void> connectionCheck = new SwingWorker<Void,Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				try {
					System.out.println("Connection check.");
					Class<?> connectionCheker = Class.forName("com.finance.pm.ApacheConnectionChecker", false, this.getClass().getClassLoader());
					Method method = connectionCheker.getMethod("checkBlindConnection", null);
					method.invoke(null, null);
				} catch (Throwable e) {
					System.out.println("No Connection check available.");
				} finally {
					stop = true;
				}
				
				return null;
			}
			
		};
		connectionCheck.execute();

		try {
			//Win management
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
			//debug
			if (args.length > 0 && args[0].equals("-d")) {
				Install.debug = new Boolean(args[1]);
			} else {
				Install.debug = false;
			}
			//arch
			String sysa = System.getProperty("os.arch");
			System.out.println(sysa);
			String sysn = System.getProperty("os.name");
			System.out.println(sysn);
			String sysv = System.getProperty("os.version");
			System.out.println(sysv);
			Install.systemType = SystemTypes.getType(sysn);

			//Start wizard
			Install.wizard = new Wizard();
			
			wizard.getDialog().setSize(500,200);
			wizard.getDialog().setFont(new java.awt.Font("MS Sans Serif", Font.PLAIN, 14));

			wizard.getDialog().addMouseMotionListener(new MouseMotionListener() {
				public void mouseDragged(MouseEvent e) {
				}
				public void mouseMoved(MouseEvent e) {
				}
			});

			wizard.getDialog().setTitle("Premium Markets");
			WizardPanelDescriptor descriptor1 = new IntroPanelDescriptor();
			wizard.registerWizardPanel(IntroPanelDescriptor.IDENTIFIER, descriptor1);
			WizardPanelDescriptor descriptor11 = new UpdateUrlPanelDescriptor();//
			wizard.registerWizardPanel(UpdateUrlPanelDescriptor.IDENTIFIER, descriptor11);
			WizardPanelDescriptor descriptor2 = new LicencePanelDescriptor();
			wizard.registerWizardPanel(LicencePanelDescriptor.IDENTIFIER, descriptor2);
			WizardPanelDescriptor descriptor3 = new InstallFolderPanelDescriptor();
			wizard.registerWizardPanel(InstallFolderPanelDescriptor.IDENTIFIER, descriptor3);
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

				SystemTypes systemType = Install.systemType;
				if (systemType == null) systemType = SystemTypes.WINDOWS;

				final String guiShell = InstallFolderPanel.getPmFolder().getAbsolutePath() + File.separator + "shell" + File.separator + "gui" + systemType.getShext();

				final String params[];

				System.out.println("OS like : "+systemType);
				if (systemType.equals(SystemTypes.WINDOWS)) {		
					Install.windowsPostInstall(InstallFolderPanel.getPmFolder().getAbsolutePath());
					params = new String[] { guiShell, InstallFolderPanel.getPmFolder().getAbsolutePath()};
				} else {
					Install.unixPostInstall(InstallFolderPanel.getPmFolder().getAbsolutePath());
					params = new String[] {"/bin/bash", guiShell, InstallFolderPanel.getPmFolder().getAbsolutePath() };
				}

				for (String string : params) {
					System.out.println("launch gui params : "+string);
				}

				try {

					while (ProgressPanelDescriptor.connection != null && !ProgressPanelDescriptor.connection.isClosed()) {
						System.out.println("Waiting for sql connection to close : is close ? "+ProgressPanelDescriptor.connection.isClosed());
						Thread.sleep(500);
					}
					System.out.println("Install sql connection are closed : ");

					ProcessBuilder builder = new ProcessBuilder(params);
					builder.redirectErrorStream(true);
					builder.directory(InstallFolderPanel.getPmFolder());
					Process process = builder.start();
					
					final BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

					Timer timer = new Timer(true);
					final MutableBoolean stopReading = new MutableBoolean(false);
					timer.schedule(new TimerTask() {

						@Override
						public void run() {
							System.out.println("Time out launching the UI");
							stopReading.setValue(true);
							try {
								input.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}

					}, 300000);

					String line = null;
					while (!stopReading.booleanValue() && (line = input.readLine()) != null) {
						System.out.println("Running pss output : "+line);
						if (line.contains("IHM RUNNING")) {
							stopReading.setValue(true);
						}
					}

				} catch (Exception e) {
					System.out.println("error===" + e.getMessage());
					e.printStackTrace();
				} 
			}

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				Frame[] frames = Frame.getFrames();
				for (Frame frame : frames) {
					frame.dispose();
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
			try {
				while (!stop) {
					Thread.sleep(3000);
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	private static void unixPostInstall(String installPath) {
		
		//Change exec modes
		String[] runtimeParams = new String[]{"/bin/bash", installPath+File.separator+"shell"+File.separator+"changeMods.sh", installPath};
		for (String string : runtimeParams) {
			System.out.println("launch change exec mods params : "+string);
		}
		
		try {
			Runtime runtime = Runtime.getRuntime();
			Process guiBatProcess = runtime.exec(runtimeParams, null, InstallFolderPanel.getPmFolder());
			
			BufferedReader input = new BufferedReader(new InputStreamReader(guiBatProcess.getInputStream()));
			String line = null;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}   
		
		
	}

	public static void windowsPostInstall(String installPath) {
		
		System.out.println("Properties : "+System.getProperties());
		
		//Modify db path for an absolute path
	    File pfile;
	    Properties props = new Properties();
		//Load props
		try {
			pfile = new File(InstallFolderPanel.getPmFolder().getAbsoluteFile() + File.separator + "db.properties");
			FileInputStream propFileIS = new FileInputStream(pfile);
			props.load(propFileIS);
			props.put("dbpath", StringEscapeUtils.escapeJava(installPath)+File.separator+"derby"+File.separator);
			props.store(new FileOutputStream(pfile), "Added settings properties for windows");

		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		//desktop icon
		Runtime runtime = Runtime.getRuntime();
		String[] runtimeParams = new String[]{installPath+File.separator+"shell"+File.separator+"desktop_icon.bat", installPath};
		for (String string : runtimeParams) {
			System.out.println("launch desktop_icon params : "+string);
		}
		
		try {
			Process guiBatProcess = runtime.exec(runtimeParams, null, InstallFolderPanel.getPmFolder());
			
			BufferedReader input = new BufferedReader(new InputStreamReader(guiBatProcess.getInputStream()));
			String line = null;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}   
		
	}
	
	public static void selectNextButton() {
		try {
			Box next = 
				((Box) ((JPanel)((JPanel)((JLayeredPane)((JRootPane)
						Install.wizard.getDialog().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0)).getComponent(1));
			JButton button = (JButton) next.getComponent(2);
			button.requestFocusInWindow();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pressNextButton() {
		try {
			Box next = 
				((Box) ((JPanel)((JPanel)((JLayeredPane)((JRootPane)
						Install.wizard.getDialog().getComponent(0)).getComponent(1)).getComponent(0)).getComponent(0)).getComponent(1));
			JButton button = (JButton) next.getComponent(2);
			button.doClick();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static Wizard getWizard() {
		return wizard;
	}
}
