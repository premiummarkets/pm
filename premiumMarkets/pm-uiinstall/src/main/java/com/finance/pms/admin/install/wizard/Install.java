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

import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
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
	
	public static final String iconFile = "icons/icon";
	public static final String license = "COPYING";
	public static final String dbName = "derby";
	public static SystemTypes systemType = SystemTypes.WINDOWS;
	
	public static String APP_SYS_NAME;
	public static String ARCH_NAME;
	public static String APP_NAME;
	
	public static Boolean debug;
	private static Boolean stop = false;
	
	protected MyWizard wizard;
	private String siteUrl;
	
	public Install() {
		
	}


	public static void main(String[] args) {
		
		Install install = new Install();
		
		//Connection check
		install.connectionCheck();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			System.out.println("No look and feel : "+e1.toString());
		}

		try {
			install.setupAppNames();
			//Win management
			install.lookAndFeel();
			//debug
			install.debugLevel(args);
			//arch
			install.arch();

			//Start wizard
			install.initWizard();
			
			int ret = install.showModalDialog();
			System.out.println("Dialog return code is (0=Finish,1=Cancel,2=Error): " + ret);

			if (ret == 0) {
				install.launchUi(); 
			}

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			install.disposeFrames();
			
			try {
				while (!stop) {
					Thread.sleep(3000);
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
		}
	}


	protected void disposeFrames() {
		try {
			Frame[] frames = Frame.getFrames();
			for (Frame frame : frames) {
				frame.setVisible(false);
				//frame.dispose();
			}
			wizard.getDialog().dispose();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}


	protected int showModalDialog() {
		return wizard.showModalDialog();
	}


	protected void launchUi() {
		
		SystemTypes systemType = Install.systemType;
		if (systemType == null) systemType = SystemTypes.WINDOWS;

		final String guiShell = InstallFolderPanel.getInstallFolder().getAbsolutePath() + File.separator + "shell" + File.separator + "gui" + systemType.getShext();

		final String params[];

		System.out.println("OS like : "+systemType);
		if (systemType.equals(SystemTypes.WINDOWS)) {		
			Install.windowsPostInstall(InstallFolderPanel.getInstallFolder().getAbsolutePath());
			params = new String[] { guiShell, InstallFolderPanel.getInstallFolder().getAbsolutePath()};
		} else {
			Install.unixPostInstall(InstallFolderPanel.getInstallFolder().getAbsolutePath());
			params = new String[] {"/bin/bash", guiShell, InstallFolderPanel.getInstallFolder().getAbsolutePath() };
		}

		for (String string : params) {
			System.out.println("Launch gui params : "+string);
		}
		
		try {

			while (ProgressPanelDescriptor.connection != null && !ProgressPanelDescriptor.connection.isClosed()) {
				System.out.println("Waiting for sql connection to close : is close ? "+ProgressPanelDescriptor.connection.isClosed());
				Thread.sleep(500);
			}
			System.out.println("Install sql connection are closed : ");

			ProcessBuilder builder = new ProcessBuilder(params);
			builder.redirectErrorStream(true);
			builder.directory(InstallFolderPanel.getInstallFolder());
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


	protected void initWizard() {
		
		wizard = new MyWizard();
		
		wizard.getDialog().setSize(500,200);
		wizard.getDialog().setFont(new java.awt.Font("MS Sans Serif", Font.PLAIN, 14));

		wizard.getDialog().addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
			}
			public void mouseMoved(MouseEvent e) {
			}
		});

		wizard.getDialog().setTitle(APP_NAME);
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", APP_NAME);
		Image appImageIcon = getAppImageIcon();
		if (appImageIcon != null) wizard.getDialog().setIconImage(appImageIcon);
		
		String siteUrl = setupSiteUrl();
		String introButtonTxt = setupIntroButtonTxt(siteUrl);
		String copyRights = setupCopyRightsHtml();
		WizardPanelDescriptor descriptor1 = new IntroPanelDescriptor(wizard, siteUrl, introButtonTxt, copyRights);
		wizard.registerWizardPanel(IntroPanelDescriptor.IDENTIFIER, descriptor1);
		
		String jnlpUrl = setupUpdateJnlpUrl();
		WizardPanelDescriptor descriptor11 = new UpdateUrlPanelDescriptor(wizard, jnlpUrl);
		wizard.registerWizardPanel(UpdateUrlPanelDescriptor.IDENTIFIER, descriptor11);
		
		WizardPanelDescriptor descriptor2 = new LicencePanelDescriptor(wizard);
		wizard.registerWizardPanel(LicencePanelDescriptor.IDENTIFIER, descriptor2);
		
		WizardPanelDescriptor descriptor3 = new InstallFolderPanelDescriptor(wizard);
		wizard.registerWizardPanel(InstallFolderPanelDescriptor.IDENTIFIER, descriptor3);
		
		String dbInstallUrl = setupInstallAltDbUrl();
		String dbName = setupDbName();
		String packagedJnlp = setupPackagedJnlpName();
        String waitForProgressTxt = setupWhileWeWaitText();
        String whileWeWaitUrl = setupWhileWeWaitUrl(siteUrl);
		WizardPanelDescriptor descriptor4 = new ProgressPanelDescriptor(wizard, dbInstallUrl, dbName, packagedJnlp, waitForProgressTxt, siteUrl, whileWeWaitUrl);
		wizard.registerWizardPanel(ProgressPanelDescriptor.IDENTIFIER, descriptor4);
		
		WizardPanelDescriptor descriptor5 = new SmtpPanelDescriptor(wizard);
		wizard.registerWizardPanel(SmtpPanelDescriptor.IDENTIFIER, descriptor5);
		
		WizardPanelDescriptor descriptor6 = new DonePanelDescriptor(wizard);
		wizard.registerWizardPanel(DonePanelDescriptor.IDENTIFIER, descriptor6);
		
		wizard.setCurrentPanel(IntroPanelDescriptor.IDENTIFIER);
	}
    
    private Image getAppImageIcon() {
    	URL resource = this.getClass().getClassLoader().getResource(Install.iconFile+".png");
    	if (resource != null){
    		try {
				BufferedImage bufferedeImage = ImageIO.read(resource);
				return bufferedeImage;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
    	} else {
    		return null;
    	}
    }


	protected String setupWhileWeWaitUrl(String siteUrl) {
		return "<HTML><FONT color=\"red\">Click <FONT color=\"#000099\"><blink><U>http://"+siteUrl+"/</U></blink></FONT> for a preview and a free workable demo of the <b>FORECAST</b> engine.</FONT></HTML>";
	}


	protected String setupWhileWeWaitText() {
		return "<html>"+
		"<p>" +
		Install.APP_NAME+" is an automated stock market analysis system.<br />"+
		"It implements a graphical environment for monitoring stock market technical analysis major indicators, <br />portfolio management and historical data charting.<br />" +
		"<br />"+
		"I also invite you for a preview of "+Install.APP_NAME+" Forecast advanced features (not provided under this license) which includes : <br />"+
		"<ul>" +
			"<li>Screening of financial web sites to pick up the best market shares,</li>"+
			"<li>Price trend prediction based on stock market technical analysis and indexes rotation,</li> "+
			"<li>Back testing,</li>"+
			"<li>Buy sell email notifications on predictions with automated markets and user defined portfolios scanning.</li>" +
		"</ul>" +
		"<br />" +
		"</p>" +
		"</html>";
	}


	protected String setupCopyRightsHtml() {
		return "Copyright &copy; Thoreton Guillaume. See &lt;http://www.gnu.org/licenses/&gt;";
	}


	protected String setupPackagedJnlpName() {
		return "/PremiumMarkets.jnlp";
	}


	protected String setupDbName() {
		return "premiummarkets";
	}


	protected String setupInstallAltDbUrl() {
		return "http://downloads.sourceforge.net/pmsqueak/initialdb-0.1.2.jar?use_mirror=osdn";
	}


	protected String setupUpdateJnlpUrl() {
		return"http://sourceforge.net/projects/pmsqueak/files/PremiumMarkets.jnlp";
	}


	protected String setupIntroButtonTxt(String siteUrl) {
		return "<HTML>" +
				"<FONT color=\"red\">I would also like to bring to your attention that Premium Markets announces its NEW Forecast engine!<br/>"+
				"Click <FONT color=\"#000099\"><blink><U>http://"+siteUrl+"/</U></blink></FONT> for a preview and a free workable demo.</FONT></HTML>";
	}


	protected void setupAppNames() {
		APP_SYS_NAME = "PremiumMarkets";
		ARCH_NAME = "PremiumMarkets.zip";
		APP_NAME = "Premium Markets";
		
	}


	protected void arch() {
		String sysa = System.getProperty("os.arch");
		System.out.println(sysa);
		String sysn = System.getProperty("os.name");
		System.out.println(sysn);
		String sysv = System.getProperty("os.version");
		System.out.println(sysv);
		Install.systemType = SystemTypes.getType(sysn);
	}


	protected void debugLevel(String[] args) {
		if (args.length > 0 && args[0].equals("-d")) {
			Install.debug = new Boolean(args[1]);
		} else {
			Install.debug = false;
		}
	}


	protected void lookAndFeel() {
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
	}


	protected void connectionCheck() {
		@SuppressWarnings("all")
		final SwingWorker<Void, Void> connectionCheck = new SwingWorker<Void,Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				try {
					
		        	String siteUrl = "none.com";
					try {
						Properties pbuild = new Properties();
						pbuild.load(this.getClass().getResourceAsStream("/pmsbuild.properties"));
						siteUrl = pbuild.getProperty("site.url");
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					System.out.println("Connection check : "+siteUrl);
					
					if (!siteUrl.equals("none.com")) {
						Class<?> connectionCheker = Class.forName("com.finance.pm.ApacheConnectionChecker", false, this.getClass().getClassLoader());
						Method method = connectionCheker.getMethod("checkBlindConnection", String.class);
						method.invoke(null, siteUrl);
					}
					
				} catch (Throwable e) {
					System.out.println("No Connection check available.");
				} finally {
					stop = true;
				}
				
				return null;
			}
			
		};
		connectionCheck.execute();
	}
	
	
	private static void unixPostInstall(String installPath) {
		
		//Change exec modes
		String[] runtimeParams = new String[]{"/bin/bash", installPath+File.separator+"shell"+File.separator+"changeMods.sh", installPath};
		for (String string : runtimeParams) {
			System.out.println("launch change exec mods params : "+string);
		}
		
		try {
			Runtime runtime = Runtime.getRuntime();
			Process guiBatProcess = runtime.exec(runtimeParams, null, InstallFolderPanel.getInstallFolder());
			
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
			pfile = new File(InstallFolderPanel.getInstallFolder().getAbsoluteFile() + File.separator + "db.properties");
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
			Process guiBatProcess = runtime.exec(runtimeParams, null, InstallFolderPanel.getInstallFolder());
			
			BufferedReader input = new BufferedReader(new InputStreamReader(guiBatProcess.getInputStream()));
			String line = null;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}   
		
	}


	public Wizard getWizard() {
		return wizard;
	}
	

	protected String setupSiteUrl() {
		if (siteUrl == null) {
			String siteUrl = "none.com";
			try {
				Properties pbuild = new Properties();
				pbuild.load(this.getClass().getResourceAsStream("/pmsbuild.properties"));
				siteUrl = pbuild.getProperty("site.url");
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.siteUrl = siteUrl;
		}
		return siteUrl;
	}
}
