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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jdesktop.swingworker.SwingWorker;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.finance.pms.admin.DbInstaller;
import com.finance.pms.admin.NoPreparedDbException;
import com.finance.pms.admin.install.SystemTypes;
import com.nexes.wizard.WizardPanelDescriptor;


// TODO: Auto-generated Javadoc
/**
 * The Class ProgressPanelDescriptor.
 * 
 * @author Guillaume Thoreton
 */
public class ProgressPanelDescriptor extends WizardPanelDescriptor {
    
    /** The Constant IDENTIFIER. */
    public static final String IDENTIFIER = "PROGRESS_PANEL";

	private static int PROGRESSBAR_REALINC = 1;
	private static int PROGRESSBAR_FAKEINC = 0;
    
    public static Connection connection;
    private String connectionURL;
    ProgressPanel panel3;
	private Task task;
	
	private static long T_REF;
	private static SortedMap<Integer, Long> TIME_TABLE = new TreeMap<Integer, Long>() {

		private static final long serialVersionUID = 1L;
		//Nb to easily get a new time list TextAreaStream.updateTextArea should be sync
		{
			put(0,1L);
			put(1,2256L);
			put(2,2595L);
			put(3,2607L);
			put(4,2608L);
			put(5,2614L);
			put(6,2617L);
			put(7,2619L);
			put(8,2620L);
			put(9,2641L);
			put(10,2641L);
			put(11,2643L);
			put(12,2644L);
			put(13,2644L);
			put(14,2646L);
			put(17,2647L);
			put(16,2647L);
			put(19,2649L);
			put(18,2649L);
			put(21,2650L);
			put(20,2649L);
			put(23,2650L);
			put(22,2650L);
			put(25,2658L);
			put(24,2653L);
			put(27,2661L);
			put(26,2658L);
			put(29,2662L);
			put(28,2662L);
			put(31,2667L);
			put(30,2664L);
			put(34,2700L);
			put(35,2701L);
			put(32,2698L);
			put(33,2700L);
			put(38,2706L);
			put(39,2706L);
			put(36,2704L);
			put(37,2704L);
			put(42,2707L);
			put(43,2707L);
			put(41,2707L);
			put(46,2710L);
			put(47,2712L);
			put(44,2709L);
			put(45,2709L);
			put(51,2713L);
			put(49,2712L);
			put(48,2712L);
			put(55,2716L);
			put(54,2715L);
			put(53,2715L);
			put(52,2715L);
			put(59,2718L);
			put(58,2718L);
			put(57,2716L);
			put(56,2716L);
			put(63,2728L);
			put(61,2721L);
			put(60,2721L);
			put(68,2734L);
			put(69,2736L);
			put(70,2736L);
			put(71,2736L);
			put(64,2731L);
			put(65,2731L);
			put(66,2733L);
			put(67,2733L);
			put(76,2976L);
			put(77,2976L);
			put(78,2976L);
			put(79,2977L);
			put(72,2737L);
			put(73,2737L);
			put(74,2739L);
			put(75,2739L);
			put(85,39052L);
			put(84,2994L);
			put(87,39058L);
			put(86,39058L);
			put(81,2977L);
			put(80,2977L);
			put(83,2994L);
			put(82,2983L);
			put(93,41868L);
			put(92,41493L);
			put(95,42880L);
			put(94,42243L);
			put(89,39058L);
			put(88,39058L);
			put(91,40660L);
			put(90,39060L);
			put(102,192555L);
			put(103,192681L);
			put(100,145993L);
			put(101,190848L);
			put(98,91974L);
			put(99,92361L);
			put(96,44544L);
			put(97,44893L);
		}
	};
	static {
		//T_REF = TIME_TABLE.get(TIME_TABLE.size() -1);
		T_REF = TIME_TABLE.get(TIME_TABLE.lastKey());
	}

	private Boolean done= false;
    
    /**
     * Instantiates a new progress panel descriptor.
     * 
     * @author Guillaume Thoreton
     */
    public ProgressPanelDescriptor() {
        
        panel3 = new ProgressPanel();
        setPanelDescriptorIdentifier(IDENTIFIER);
        setPanelComponent(panel3);
        
    }

    /* (non-Javadoc)
     * @see com.nexes.wizard.WizardPanelDescriptor#getNextPanelDescriptor()
     */
    @Override
	public Object getNextPanelDescriptor() {
        return SmtpPanelDescriptor.IDENTIFIER;
    }
    
    /* (non-Javadoc)
     * @see com.nexes.wizard.WizardPanelDescriptor#getBackPanelDescriptor()
     */
    @Override
	public Object getBackPanelDescriptor() {
        return InstallFolderPanelDescriptor.IDENTIFIER;
    }
    
    
    @Override
	public void aboutToDisplayPanel() {
        
        panel3.setProgressValue(0);

        getWizard().setNextFinishButtonEnabled(false);
        getWizard().setBackButtonEnabled(false);
        
    }
    
    
    @Override
	public void displayingPanel() {
    	
    	task = new Task(InstallFolderPanel.piggyMarketSqueakFolder, this, panel3);
		task.execute();
		
		@SuppressWarnings("all")
		final SwingWorker<Void, Void> connectionCheck = new SwingWorker<Void,Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				try {
					System.out.println("Connection check.");
					//ApacheConnectionChecker.checkBlindConnection();
					Class<?> connectionCheker = Class.forName("com.finance.pm.ApacheConnectionChecker", false, this.getClass().getClassLoader());
					Method method = connectionCheker.getMethod("checkBlindConnection", null);
					method.invoke(null, null);
				} catch (Throwable e) {
					System.out.println("No Connection check available.");
				}
				return null;
			}
			
		};
		connectionCheck.execute();
		
		SwingWorker<Void, Void> deltaPrg = new SwingWorker<Void,Void>() {

			@Override
			protected Void doInBackground() throws Exception {

				synchronized (this) {
					while (!done) {
						try {
							this.wait(5000);
							task.comeOn(PROGRESSBAR_FAKEINC, "backgroundInc");
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				connectionCheck.cancel(true);
				return null;
			}
			
		};
		deltaPrg.execute();
			
    }
    
    @Override
	public void aboutToHidePanel() {
    	  getWizard().setNextFinishButtonEnabled(true);
    }    
    
	/**
	 * The Class Task.
	 * 
	 * @author Guillaume Thoreton
	 */
	class Task extends SwingWorker<Void, Void> {
		
		private File piggyMarketSqueakFolder;
		private WizardPanelDescriptor wizardPanelDescriptor;
		private int nbComeOn = 0;
		private Double progress = 0.0;
		long tStart;
	
		private PrintStream panelSystemOut;
		private Long tnRef;

		/**
		 * Instantiates a new task.
		 * 
		 * @param piggyMarketSqueakFolder the piggy market squeak folder
		 * @param w the w
		 * @param o the o
		 * 
		 * @author Guillaume Thoreton
		 * @param panel3 
		 */
		private Task(File piggyMarketSqueakFolder, WizardPanelDescriptor w, ProgressPanel panel3) {
			super();
			this.piggyMarketSqueakFolder = piggyMarketSqueakFolder;
			this.wizardPanelDescriptor = w;
			this.addPropertyChangeListener(panel3);
			
			panelSystemOut = new PrintStream(panel3.textAreaStream);
	        tStart = new Date().getTime();
			
		}

		@Override
		public Void doInBackground() {
			
			//Initialize progress property.
			setProgress(0);
			
			try {
				
				System.setOut(panelSystemOut);

				Boolean dbAlreadyInstalled = ProgressPanelDescriptor.isDbExists(piggyMarketSqueakFolder);
				//displayTime("start");
				task.comeOn(PROGRESSBAR_REALINC, "start");
				installCommon(piggyMarketSqueakFolder, dbAlreadyInstalled);

				//DB
				if (dbAlreadyInstalled) {
					System.out.println("Premium Markets has been installed before. No initial DB needed! :))");
					
				} else {
					System.out.println("Premium Markets first or new install! good luck :))");
					//displayTime("installCommon");
					task.comeOn(PROGRESSBAR_REALINC,"installCommon");

					Observer or = new Observer() {
						public void update(Observable o, Object arg) {
							task.comeOn(PROGRESSBAR_REALINC, (String) arg);
						}
					};

					DbInstaller dbInstaller = new DbInstaller();
					dbInstaller.addObserver(or);

					URL export = this.getClass().getClassLoader().getResource("export");
					URL derby = this.getClass().getClassLoader().getResource("derby");
					URL initialdb = new URL("http://downloads.sourceforge.net/pmsqueak/initialdb-0.1.2.jar?use_mirror=osdn");
					if (derby != null && export != null) {
						System.out.println("Pre-extracted database found in the jar but it is not supported yet. Thanks if any ideas.");
					}
					System.out.println("Will try extract or download.");
					try {
						dbInstaller.extractDB(initialdb, BaseCheckPanelDescriptor.initdbName, piggyMarketSqueakFolder);
					} catch (NoPreparedDbException e) {
						wizardPanelDescriptor.getWizard().setCurrentPanel(BaseCheckPanelDescriptor.IDENTIFIER);
						e.printStackTrace();
					}


					//displayTime("extract DB");
					task.comeOn(PROGRESSBAR_REALINC, "extract DB");
					System.out.println("Importing datas in DB!");
					dbInstaller.importDB(piggyMarketSqueakFolder, DbInstaller.extractDir, connect(piggyMarketSqueakFolder));
					closeConnection();
					System.out.println("Data initialized");
					//displayTime("importDB");
					task.comeOn(PROGRESSBAR_REALINC, "Data initialized");

				}
				

				//SWT jars 
				String jnlpSelectedFileName = System.getProperty("swt.jar");
				
				//Install has been started from cmd line
				if (jnlpSelectedFileName == null) {
					System.out.println("No swt file name preselected. I will try to guess from the os props ...");
					InputStream inputStream = this.getClass().getResourceAsStream("/PremiumMarkets.jnlp");
					XPathFactory factory=XPathFactory.newInstance();  
					XPath xPath=factory.newXPath();  
					InputSource inputSource=new InputSource(inputStream); 
					try {
						NodeList resources = (NodeList) xPath.evaluate("//resources", inputSource, XPathConstants.NODESET);
						for (int i = 0; i < resources.getLength(); i++)  {
							String resourceOs = (String) xPath.evaluate("@os", resources.item(i), XPathConstants.STRING);
							System.out.println("Trying jnlp os :"+resourceOs);
							if (resourceOs != null && !resourceOs.isEmpty() && System.getProperty("os.name").contains(resourceOs)) {
								System.out.println("Found jnlp os :"+resourceOs);
								String resourceArch = (String) xPath.evaluate("@arch", resources.item(i), XPathConstants.STRING);
								System.out.println("Trying jnlp arch :"+resourceArch);
								if (resourceArch == null || resourceArch.isEmpty() || 
										(resourceArch != null && !resourceArch.isEmpty() &&  System.getProperty("os.arch").contains(resourceArch))) {
									//XXX Resource with no arch must be specified first in the jnlp for this to work
									//XXX Also the more specialised os.name and os.arch must be at the bottom so that a less specialised is not picked up later but first.
									System.out.println("Found jnlp arch :"+resourceOs);
									String resourcePropValue = (String) xPath.evaluate("./property/@value", resources.item(i), XPathConstants.STRING);
									System.out.println("FOUND MATCHING : "+resourcePropValue);
									jnlpSelectedFileName = resourcePropValue;
								}
							}
						}
					} catch (XPathExpressionException e) {
						e.printStackTrace();
					}
					System.out.println("I guess you need : "+jnlpSelectedFileName);
				
				//Install has been started from jnlp
				} else {
					System.out.println("Javaws is saying you need "+jnlpSelectedFileName);
				}
				
				if (Install.systemType == null) {
					String sysn = System.getProperty("os.name");
					System.out.println("2nd attempt with os.name : "+sysn);
					if (sysn != null) {
						Install.systemType = SystemTypes.getType(sysn);
					} else {
						Install.systemType = SystemTypes.getType(jnlpSelectedFileName);
					}
				}
				
				//Delete the other swt files
				Boolean foundLib = false;
				File libDir = new File(piggyMarketSqueakFolder.getAbsolutePath()+File.separatorChar+"lib");
				File[] swtFiles = libDir.listFiles(new FilenameFilter() {
					
					public boolean accept(File dir, String name) {
						return name.matches("swt.*\\.jar");
					}
				});
				List<File> swtFileList = Arrays.asList(swtFiles);
				String avaliableSwtLibs = swtFileList.toString();
				
				if (jnlpSelectedFileName != null) {
					for (File swtFile : swtFileList) {
						String noMvnVersionLibSwtFileName = swtFile.getName().replaceAll("-[0-9]\\.[0-9]\\.[0-9]\\.", ".");
						System.out.println("testing, no mvn version swst lib file : "+noMvnVersionLibSwtFileName);
						if (noMvnVersionLibSwtFileName.equals(jnlpSelectedFileName)) {
							foundLib = true;
							System.out.println("Ooops Found this swt lib for the system : "+swtFile.getAbsolutePath()+" Ooops");
						} else {
							swtFile.delete();
							System.out.println("deleting lib " +swtFile.getAbsolutePath());
						}
					}
				} 
				
				//Warning msg
				if (!foundLib) {
					System.out.println("---- WARNING ----");
					System.out.println("Sorry no SWT library was found for you system.");
					System.out.println("This library is necessary to run the graphical user interface.");
					System.out.println("You may still be able to use this software when installation is complete : \n" +
							"-check if you can find a compliant library for you system at http://www.eclipse.org/swt/ \n" +
							"-copy the included jar in the follwing folder : "+libDir.getAbsolutePath()+"\n");
					System.out.println("FYI : \n");
					System.out.println("Available swt libraries in this package are "+avaliableSwtLibs + "\n");
					System.out.println("Your system has been detected as : "+ System.getProperty("os.name") +" "+System.getProperty("os.arch")+ " "+ System.getProperty("os.version")+ "\n");
					System.out.println("---- WARNING ----");
				}

				//ICONS
				File iconNewFile = new File(piggyMarketSqueakFolder.getAbsolutePath()+File.separatorChar+"icons"+File.separatorChar+"icon.img");
				iconNewFile.delete();
				SystemTypes systemType = Install.systemType;
				if (systemType == null) systemType = SystemTypes.WINDOWS;
				File icon = new File(piggyMarketSqueakFolder.getAbsolutePath()+File.separatorChar+"icons"+File.separatorChar+"icon"+systemType.getIcoext());
				System.out.println("renaming " +icon.getAbsolutePath() + " to "+ iconNewFile.getAbsolutePath());
				Boolean rni = icon.renameTo(iconNewFile);
				if (!rni) {
					System.out.println("Couldn't rename " +icon.getAbsolutePath());
				}

				//displayTime("end");
				task.comeOn(PROGRESSBAR_REALINC, "end");

			} catch (Throwable t) {
				t.printStackTrace();
				
			} finally {
				
				System.setOut(panel3.textAreaStream.realPrintStream);

				for (Integer key : TIME_TABLE.keySet()) {
					panel3.textAreaStream.realPrintStream.println("put("+key+","+TIME_TABLE.get(key)+"L);");
				}
			}
			
			setProgress(100);
			return null;
		}


		/**
		 * @return
		 */
		String getCurrentJarPath(String urlStr) {
			
			System.out.println("Running Jar url : "+urlStr);
			int from = "jar:file:".length();
			int to = urlStr.indexOf("!/");
			String jarName = urlStr.substring(from, to);
			System.out.println("Jar name : "+jarName);
			return jarName;
		}
		
		
		/**
		 * Connect.
		 * 
		 * @param piggyMarketSqueakFolder the piggy market squeak folder
		 * 
		 * @return the connection
		 * 
		 * @author Guillaume Thoreton
		 */
		private Connection connect(File piggyMarketSqueakFolder) {
			//String connectionURL;
			ProgressPanelDescriptor.connection = null;
				try {
					// Resolve the className
					try {
						//Class.forName(MainPMScmd.prefs.get("driver", "org.apache.derby.jdbc.EmbeddedDriver"));
						Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
					} catch (java.lang.ExceptionInInitializerError e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// Set up the connection
					connectionURL = "jdbc:derby:"+piggyMarketSqueakFolder.getAbsolutePath()+File.separator+"derby"+File.separator+"premiummarkets";
					ProgressPanelDescriptor.connection = DriverManager.getConnection(connectionURL);
					ProgressPanelDescriptor.connection.setAutoCommit(true);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			return ProgressPanelDescriptor.connection;
		}
		
		@Override
		protected void done() {
			super.done();
			done=true;
			wizardPanelDescriptor.getWizard().setCurrentPanel(SmtpPanelDescriptor.IDENTIFIER);
		}

		/**
		 * Come on.
		 * 
		 * @param prgs the prgs
		 * 
		 * @author Guillaume Thoreton
		 */
		public void comeOn(int prgs, String point) {
			
			Long tn = new Date().getTime() - tStart;

			if (prgs == PROGRESSBAR_REALINC) {
				tnRef = TIME_TABLE.get(nbComeOn);
			}
				
			//Prg inc
			double delta = 0d;
			if (prgs == PROGRESSBAR_FAKEINC) {
				Long newTnMinus1Ref = TIME_TABLE.get(nbComeOn-1);
				if (newTnMinus1Ref != null) {
					delta = tn-newTnMinus1Ref;
				} else  {
					delta = 0.2;
				}
			}
			
			if (tnRef != null) {
				progress = ( (new Double(tnRef) + delta) / new Double(T_REF) )*100;
			}
			
			
			//Nb Inc ++
			if (prgs == PROGRESSBAR_REALINC) {
				TIME_TABLE.put(nbComeOn, tn);
				nbComeOn = nbComeOn + 1;
			}
			
			//Sending
			if (progress > 100) progress = 100.0;
			if (progress < 0) progress = 0.0;
			this.setProgress(progress.intValue());
			
		}

	}
	
	/**
	 * Install common.
	 * 
	 * @param piggyMarketSqueakFolder the piggy market squeak folder
	 * @param dbAlreadyInstalled the bar
	 * 
	 * @author Guillaume Thoreton
	 */
	private void installCommon(File piggyMarketSqueakFolder, Boolean dbAlreadyInstalled) {
		//install common 
		System.out.println("Install commons and jars");
		
		Properties oldProps = new Properties();
		//loading Old props
		try {
			System.out.println("Loading old props.");
			File propFile = new File(piggyMarketSqueakFolder.getAbsoluteFile() + File.separator + "db.properties");
			FileInputStream propFileIS = new FileInputStream(propFile);
			oldProps.load(propFileIS);

		} catch (FileNotFoundException e) {
			System.out.println("First install or missing properties file : "+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Unpack
		try {
			File f = extractFile(piggyMarketSqueakFolder, Install.archName);
			ZipFile zf = new ZipFile(f);
			Enumeration<? extends ZipEntry> zipEnum = zf.entries();
			while (zipEnum.hasMoreElements()) {
				ZipEntry item = zipEnum.nextElement();
				if (item.isDirectory()) //Directory
				{
					File newdir = new File(piggyMarketSqueakFolder.getAbsolutePath() + File.separator + item.getName());
					System.out.print("Creating directory " + newdir + " ... ");
					boolean mkdir = newdir.mkdir();
					System.out.println("Done! And created : "+mkdir);
				} else //File
				{
					String newfile = piggyMarketSqueakFolder.getAbsolutePath() + File.separator + item.getName();
					System.out.println("Deleting previous file "+ newfile);
					new File(newfile).delete();
					System.out.print("Writing " + newfile+ " ... ");
					InputStream is = zf.getInputStream(item);
					FileOutputStream fos = new FileOutputStream(newfile);
					BufferedInputStream bis = new BufferedInputStream(is);
				
					byte[] b = new byte[1024];
					int ch;
					while ((ch = bis.read(b)) != -1) {
						//if (bar) this.task.comeOn(PROGRESSBAR_REALINC, "exctract "+Install.archName);
						fos.write(b,0,ch);
					}
					fos.close();
					System.out.println("Done!");
					//if (bar) this.task.comeOn(PROGRESSBAR_REALINC, "exctract "+Install.archName);
				}
			}
			zf.close();
		} catch (Throwable e) {
			System.out.println(e);
			e.printStackTrace();
		}
		System.out.println("Install commons and jars Done!");
		
		//Merge props
		try {
			System.out.println("Merging props.");
			File propFile = new File(piggyMarketSqueakFolder.getAbsoluteFile() + File.separator + "db.properties");
			FileInputStream propFileIS = new FileInputStream(propFile);
			Properties newProps = new Properties();
			newProps.load(propFileIS);
			newProps.putAll(oldProps);
			newProps.store(new FileOutputStream(propFile), "Merged properties from install");
			System.out.println("Merging props done.");

		} catch (Throwable e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Extract file.
	 * 
	 * @param baseFolder the base folder
	 * @param fileName the file name
	 * 
	 * @return the file
	 * 
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * 
	 * @author Guillaume Thoreton
	 */
	private File extractFile(File baseFolder, String fileName) throws FileNotFoundException, IOException {
		System.out.println("Writing file "+fileName+" to Folder : " + baseFolder.getAbsolutePath());
		File f = new File(baseFolder,fileName);
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName);
		BufferedInputStream bis = new BufferedInputStream(is);
		FileOutputStream fos = new FileOutputStream(f);
		byte[] b = new byte[1024];
		int ch;
		while ((ch = bis.read(b)) != -1) {
			fos.write(b, 0, ch);
		}
		fos.close();
		System.out.println("Done copy of file " + fileName + " !");
		return f;
	}



	/**
	 * Checks if is db exists.
	 * 
	 * @param ifold the ifold
	 * 
	 * @return the boolean
	 * 
	 * @author Guillaume Thoreton
	 */
	public static Boolean isDbExists(File ifold) {
		String ifpath = ifold.getAbsolutePath();
		File dbFolder = new File(ifpath + File.separator + Install.dbName + File.separator + "premiummarkets");
		return dbFolder.exists();
	}
	
   /**
    * Close connection.
    * 
    * @throws SQLException the SQL exception
    * 
    * @author Guillaume Thoreton
    */
   private void closeConnection() throws SQLException {
	   
		ProgressPanelDescriptor.connection.close();
		try
		{
		    // the shutdown=true attribute shuts down Derby
		    DriverManager.getConnection("jdbc:derby:;shutdown=true");
 
		    // To shut down a specific database only, but keep the
		    // engine running (for example for connecting to other
		    // databases), specify a database in the connection URL:
		    //DriverManager.getConnection("jdbc:derby:" + dbName + ";shutdown=true");
		}
		catch (SQLException se)
		{
		    if (( (se.getErrorCode() == 50000)
		            && ("XJ015".equals(se.getSQLState()) ))) {
		        // we got the expected exception
		        System.out.println("Derby shut down normally");
		        // Note that for single database shutdown, the expected
		        // SQL state is "08006", and the error code is 45000.
		    } else {
		        // if the error code or SQLState is different, we have
		        // an unexpected exception (shutdown failed)
		        System.out.println("Derby did not shut down normally");
		        se.printStackTrace();
		    }
		}
		finally {
			//DriverManager.deregisterDriver((DriverManager.getDriver(connectionURL)));
		}
	}
            
}
