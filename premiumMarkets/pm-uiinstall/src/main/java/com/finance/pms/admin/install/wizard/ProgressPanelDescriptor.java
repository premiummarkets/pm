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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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

//import org.apache.tools.ant.types.Path;
import org.jdesktop.swingworker.SwingWorker;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.finance.pms.admin.DbInstaller;
import com.finance.pms.admin.install.SystemTypes;
import com.nexes.wizard.WizardPanelDescriptor;

/**
 * The Class ProgressPanelDescriptor.
 * 
 * @author Guillaume Thoreton
 */
public class ProgressPanelDescriptor extends WizardPanelDescriptor {

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
            put(0,0L);
            put(1,2008L);
            put(2,2489L);
            put(3,2491L);
            put(4,2493L);
            put(5,2493L);
            put(6,2494L);
            put(7,2497L);
            put(8,2502L);
            put(9,2502L);
            put(10,2504L);
            put(11,2505L);
            put(12,2511L);
            put(13,2512L);
            put(14,2512L);
            put(15,2513L);
            put(16,2514L);
            put(17,2514L);
            put(18,2515L);
            put(19,2515L);
            put(20,2515L);
            put(21,2516L);
            put(22,2516L);
            put(23,2517L);
            put(24,2517L);
            put(25,2518L);
            put(26,2518L);
            put(27,2518L);
            put(28,2519L);
            put(29,2519L);
            put(30,2520L);
            put(31,2520L);
            put(32,2520L);
            put(33,2522L);
            put(34,2523L);
            put(35,2523L);
            put(36,2523L);
            put(37,2524L);
            put(38,2524L);
            put(39,2525L);
            put(40,2527L);
            put(41,2528L);
            put(42,2528L);
            put(43,2528L);
            put(44,2531L);
            put(45,2533L);
            put(46,2535L);
            put(47,2536L);
            put(48,2536L);
            put(49,2536L);
            put(50,2537L);
            put(51,2538L);
            put(52,2538L);
            put(53,2538L);
            put(54,2540L);
            put(55,2541L);
            put(56,2541L);
            put(57,2541L);
            put(58,2542L);
            put(59,2542L);
            put(60,2542L);
            put(61,2543L);
            put(62,2543L);
            put(63,2543L);
            put(64,2544L);
            put(65,2544L);
            put(66,2545L);
            put(67,2545L);
            put(68,2546L);
            put(69,2546L);
            put(71,2548L);
            put(72,2549L);
            put(73,2550L);
            put(74,2550L);

            put(75,2550L);
            put(76,2551L);
            put(77,2553L);
            put(79,2553L);
            put(80,2554L);

            put(81,2555L);
            put(82,2556L);
            put(83,2556L);
            put(85,2557L);
            put(86,2558L);

            put(87,2558L);
            put(88,2558L);
            put(89,2559L);
            put(91,2560L);
            put(92,2560L);

            put(93,2560L);
            put(94,2562L);
            put(95,2563L);
            put(96,2563L);
            put(97,2563L);
            put(98,2564L);
            put(99,2564L);
            put(100,2564L);
            put(101,2565L);
            put(102,2565L);
            put(103,2566L);
            put(104,2566L);
            put(105,2566L);
            put(106,2567L);
            put(107,2567L);
            put(108,2567L);
            put(109,2568L);
            put(110,2568L);
            put(111,2568L);
            put(112,2569L);
            put(113,2570L);
            put(114,2599L);
            put(115,2600L);
            put(116,2600L);
            put(117,8363L);
            put(119,8375L);
            put(120,8375L);

            put(121,8376L);
            put(122,8377L);
            put(123,8379L);
            put(124,8379L);
            put(125,8379L);
            put(127,8381L);
            put(128,8381L);

            put(129,8381L);
            put(130,8381L);
            put(131,8381L);
            put(133,20863L);
            put(134,21592L);
            put(135,22442L);

            put(136,22658L);
            put(137,22900L);
            put(138,63697L);
            put(140,65906L);
            put(141,65944L);
            put(142,67166L);
            put(143,67269L);

        }
    };
    static {
        T_REF = TIME_TABLE.get(TIME_TABLE.lastKey());
    }

    private Boolean done= false;
    public String dbInstallUrl;
    public String dbName;
    public String packagedJnlp;

    public ProgressPanelDescriptor(MyWizard wizard, String dbInstallUrl, String dbName, String packagedJnlp, String waitForProgressTxt, String siteUrl, String whileWeWaitUrl) {

        this.dbInstallUrl = dbInstallUrl;
        this.dbName = dbName;
        this.packagedJnlp = packagedJnlp;

        panel3 = new ProgressPanel(waitForProgressTxt, siteUrl, whileWeWaitUrl);
        setPanelDescriptorIdentifier(IDENTIFIER);
        setPanelComponent(panel3);

    }

    @Override
    public Object getNextPanelDescriptor() {
        return SmtpPanelDescriptor.IDENTIFIER;
    }

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

        task = new Task(InstallFolderPanel.getInstallFolder(), this, panel3);
        task.execute();

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
                return null;
            }

        };
        deltaPrg.execute();

    }

    @Override
    public void aboutToHidePanel() {
        getWizard().setNextFinishButtonEnabled(true);
    }

    class Task extends SwingWorker<Void, Void> {

        private File installFolder;
        private WizardPanelDescriptor wizardPanelDescriptor;
        private int nbComeOn = 0;
        private Double progress = 0.0;
        long tStart;

        private PrintStream panelSystemOut;
        private Long tnRef;


        private Task(File installFolder, WizardPanelDescriptor w, ProgressPanel panel3) {
            super();
            this.installFolder = installFolder;
            this.wizardPanelDescriptor = w;
            this.addPropertyChangeListener(panel3);

            panelSystemOut = new PrintStream(panel3.textAreaStream);
            tStart = new Date().getTime();

        }

        @Override
        public Void doInBackground() {

            //Initialise progress property.
            setProgress(0);

            try {

                System.setOut(panelSystemOut);

                Boolean dbAlreadyInstalled = isDbExists(installFolder);
                //displayTime("start");
                task.comeOn(PROGRESSBAR_REALINC, "start");
                installCommon(installFolder, dbAlreadyInstalled);

                //displayTime("installCommon");
                task.comeOn(PROGRESSBAR_REALINC,"installCommon");

                Observer or = new Observer() {
                    public void update(Observable o, Object arg) {
                        task.comeOn(PROGRESSBAR_REALINC, (String) arg);
                    }
                };
                DbInstaller dbInstaller = new DbInstaller();
                dbInstaller.addObserver(or);

                //DB
                dbInstall(dbInstaller);

                if (dbAlreadyInstalled) {//upgrade
                    System.out.println(Install.APP_NAME+" has been installed before. Will try and upgrade");
                    try {
                        task.comeOn(PROGRESSBAR_REALINC, "Upgrading Database!");
                        System.out.println("Upgrading Database!");
                        dbInstaller.upgradeExisting(installFolder, DbInstaller.EXTRACTDIR, connect(installFolder));
                        System.out.println("Database upgraded");
                    } catch (Throwable e) {
                        System.out.println("Database upgrade didn't complete properly.");
                        e.printStackTrace();
                    } finally {
                        closeConnection();
                        task.comeOn(PROGRESSBAR_REALINC, "Database upgraded");
                    }

                } else {//first install : populate

                    System.out.println(Install.APP_NAME+" first or new install! good luck :))");
                    //displayTime("extract DB");
                    task.comeOn(PROGRESSBAR_REALINC, "Importing data in Database!");
                    System.out.println("Importing data in Database!");
                    dbInstaller.importDB(installFolder, DbInstaller.EXTRACTDIR, connect(installFolder));
                    closeConnection();
                    System.out.println("Database initialised");
                    //displayTime("importDB");
                    task.comeOn(PROGRESSBAR_REALINC, "Database initialised");

                }

                //SWT jars
                String jnlpSelectedFileName = System.getProperty("swt.jar");
                if (jnlpSelectedFileName == null) {//Install has been started from cmd line

                    System.out.println("No swt file name preselected. I will try to guess from the os props and provided jnlp ...");
                    try {
                        jnlpSelectedFileName = swtFileName(System.getProperty("os.name"), System.getProperty("os.arch"));
                    } catch (XPathExpressionException e) {
                        e.printStackTrace();
                    }

                    if (jnlpSelectedFileName == null) {
                        System.out.println("No OS and arch matching for "+System.getProperty("os.name") +" and "+ System.getProperty("os.arch")+". Will default to Windows and x86.");
                        jnlpSelectedFileName = swtFileName("Windows", "x86");
                        System.out.println("I have not found the swt lib matching your system, and will fall back to : "+jnlpSelectedFileName);
                    } else {
                        System.out.println("I guess you need : "+jnlpSelectedFileName);
                    }

                } else {//Install has been started from jnlp
                    System.out.println("Javaws is saying you need "+jnlpSelectedFileName);
                }

                //2nd attempt to fix not found OS using the jnlp findings as fall back
                if (Install.systemType == null) {
                    String sysn = System.getProperty("os.name");
                    String sysa = System.getProperty("os.arch");
                    System.out.println("2nd attempt with os.name : "+sysn+" and "+sysa);
                    if (sysn != null && sysa != null) {
                        Install.systemType = SystemTypes.getType(sysn, sysa);
                    } else {
                        Install.systemType = SystemTypes.getType(jnlpSelectedFileName, jnlpSelectedFileName);
                    }
                }

                //Delete the other swt files
                Boolean foundLib = false;
                File libDir = new File(installFolder.getAbsolutePath()+File.separatorChar+"lib");
                File[] swtFiles = libDir.listFiles(new FilenameFilter() {

                    public boolean accept(File dir, String name) {
                        return name.matches("swt.+\\.jar");
                    }
                });
                List<File> swtFileList = Arrays.asList(swtFiles);
                String avaliableSwtLibs = swtFileList.toString();

                if (jnlpSelectedFileName != null) {
                    for (File swtFile : swtFileList) {
                        //String noMvnVersionLibSwtFileName = swtFile.getName().replaceAll("-[0-9]\\.[0-9]\\.[0-9]\\.", ".");
                        String noMvnVersionLibSwtFileName = swtFile.getName().replaceAll("-([0-9]\\.)+jar", ".jar");
                        System.out.println("testing, no mvn version swst lib file : "+noMvnVersionLibSwtFileName);
                        if (noMvnVersionLibSwtFileName.equals(jnlpSelectedFileName)) {
                            foundLib = true;
                            System.out.println("Ooops Found this swt lib for the system : "+swtFile.getAbsolutePath()+" Ooops");
                            swtFile.renameTo(new File(swtFile.getParentFile().getAbsolutePath()+File.separatorChar+"swt.jar"));
                        } else {
                            System.out.println("deleting lib " +swtFile.getAbsolutePath());
                            swtFile.delete();
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
                            "-copy the included jar in the following folder : "+libDir.getAbsolutePath()+"\n");
                    System.out.println("FYI : \n");
                    System.out.println("Available swt libraries in this package are "+avaliableSwtLibs + "\n");
                    System.out.println("Your system has been detected as : "+ System.getProperty("os.name") +" "+System.getProperty("os.arch")+ " "+ System.getProperty("os.version")+ "\n");
                    System.out.println("---- WARNING ----");
                }

                //ICONS
                SystemTypes systemType = Install.systemType;
                if (systemType == null) systemType = SystemTypes.WINDOWS;
                File icon = new File(installFolder.getAbsolutePath()+File.separatorChar+"icons"+File.separatorChar+"icon"+systemType.getIcoext());
                File iconNewFile = new File(installFolder.getAbsolutePath()+File.separatorChar+"icons"+File.separatorChar+"icon.image");
                try {
                    iconNewFile.delete();
                    System.out.println("copying from " +icon.getAbsolutePath() + " to "+ iconNewFile.getAbsolutePath());
                    Files.copy(icon.toPath(), iconNewFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (Throwable e) {
                    System.out.println("Couldn't copy " +icon.getAbsolutePath());
                    System.out.println("Using house made copy " +icon.getAbsolutePath() + " to "+ iconNewFile.getAbsolutePath());
                    try {
                        copyFile(icon, iconNewFile);
                    } catch (Throwable e1) {
                        System.out.println("Couldn't copy using house made copy : " +icon.getAbsolutePath());
                    }
                }

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

        protected void dbInstall(DbInstaller dbInstaller) throws MalformedURLException {

            URL export = this.getClass().getClassLoader().getResource("export");
            URL derby = this.getClass().getClassLoader().getResource("derby");
            if (derby != null && export != null) {
                System.out.println("Pre-extracted database found in the jar but it is not supported yet. Thanks if any ideas.");
            }

            System.out.println("Will try extract or download.");
            try {
                dbInstaller.extractDB(dbName, dbInstallUrl, BaseCheckPanelDescriptor.initDbName, installFolder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void copyFile(File sourceFile, File destFile) throws IOException {

            if(!destFile.exists()) {
                destFile.createNewFile();
            }

            FileInputStream fileInputStream = null;
            FileChannel source = null;
            FileOutputStream fileOutputStream = null;
            FileChannel destination = null;
            try {

                fileInputStream = new FileInputStream(sourceFile);
                source = fileInputStream.getChannel();
                fileOutputStream = new FileOutputStream(destFile);
                destination = fileOutputStream.getChannel();

                long count = 0;
                long size = source.size();
                while((count += destination.transferFrom(source, count, size-count))<size);

            } finally {
                if(source != null) {
                    source.close();
                }
                if(fileInputStream != null) {
                    fileInputStream.close();
                }
                if(destination != null) {
                    destination.close();
                }
                if(fileOutputStream != null) {
                    fileOutputStream.close();
                }
            }

        }

        protected String swtFileName(String osName, String osArch) throws XPathExpressionException {

            String swtFileName = null;

            System.out.println("Analysing jnlp : "+packagedJnlp);
            InputStream inputStream = this.getClass().getResourceAsStream(packagedJnlp);
            XPathFactory factory = XPathFactory.newInstance();  
            XPath xPath = factory.newXPath();  
            InputSource inputSource= new InputSource(inputStream); 
            NodeList resources = (NodeList) xPath.evaluate("//resources", inputSource, XPathConstants.NODESET);

            int matchingLengthArch = 0;
            int matchingLengthOS = 0;
            for (int i = 0; i < resources.getLength(); i++) {
                
                //OS
                String resourceOs = (String) xPath.evaluate("@os", resources.item(i), XPathConstants.STRING);
                System.out.println("Trying jnlp os :"+resourceOs+" against OS Name "+osName);
                if (resourceOs != null && !resourceOs.isEmpty() && osName.contains(resourceOs)) {
                    System.out.println("Valid jnlp os :"+resourceOs);
                    if (matchingLengthOS <= resourceOs.length()) {
                        matchingLengthOS = resourceOs.length();
                        System.out.println(resourceOs + " is the longest OS match and will be elected.");

                        //Arch
                        String resourceArch = (String) xPath.evaluate("@arch", resources.item(i), XPathConstants.STRING);
                        System.out.println("Trying jnlp arch :\""+resourceArch+"\" against Arch "+osArch);
                        if (resourceArch == null || resourceArch.isEmpty() || 
                                (resourceArch != null && !resourceArch.isEmpty() &&  osArch.contains(resourceArch))) {
                            //XXX Resource with no arch must be specified first in the jnlp for this to work
                            //XXX Also the more specialised os.name and os.arch must be at the bottom so that a less specialised is not picked up later but first.
                            //Trying to mitigate using keeping the longest os and arch name matching
                            System.out.println("Valid jnlp arch :\""+resourceArch+"\"");
                            if (matchingLengthArch <= resourceArch.length()) {
                                matchingLengthArch = resourceArch.length();
                                System.out.println("\""+resourceArch+"\" is the longest match and will be elected.");
                                String resourcePropValue = (String) xPath.evaluate("./property/@value", resources.item(i), XPathConstants.STRING);
                                System.out.println("ELECTED MATCHING FILE : "+resourcePropValue);
                                swtFileName = resourcePropValue;
                            } else {
                                System.out.println("\""+resourceArch+"\" is NOT the longest Arch match and will be ignored. Keeping "+swtFileName);
                            }
                        }

                    } else {
                        System.out.println(resourceOs + " is NOT the longest OS match and will be ignored. Keeping "+swtFileName);
                    }
                }
            }

            return swtFileName;
        }


        String getCurrentJarPath(String urlStr) {

            System.out.println("Running Jar url : "+urlStr);
            int from = "jar:file:".length();
            int to = urlStr.indexOf("!/");
            String jarName = urlStr.substring(from, to);
            System.out.println("Jar name : "+jarName);
            return jarName;
        }


        private Connection connect(File installFolder) {

            //String connectionURL;
            ProgressPanelDescriptor.connection = null;
            try {
                // Resolve the className
                try {
                    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                } catch (java.lang.ExceptionInInitializerError e) {
                    e.printStackTrace();
                }
                // Set up the connection
                connectionURL = "jdbc:derby:"+installFolder.getAbsolutePath()+File.separator+"derby"+File.separator+dbName;
                ProgressPanelDescriptor.connection = DriverManager.getConnection(connectionURL);
                ProgressPanelDescriptor.connection.setAutoCommit(true);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
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

    private void installCommon(File installFolder, Boolean dbAlreadyInstalled) {
        //install common 
        System.out.println("Install commons and jars");

        Properties oldProps = new Properties();
        //loading Old props
        try {
            System.out.println("Loading old props.");
            File propFile = new File(installFolder.getAbsoluteFile() + File.separator + "db.properties");
            FileInputStream propFileIS = new FileInputStream(propFile);
            oldProps.load(propFileIS);

        } catch (FileNotFoundException e) {
            System.out.println("First install or missing properties file : "+e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Unpack
        try {
            File f = extractFile(installFolder, Install.ARCH_NAME);
            ZipFile zf = new ZipFile(f);
            Enumeration<? extends ZipEntry> zipEnum = zf.entries();
            while (zipEnum.hasMoreElements()) {
                ZipEntry item = zipEnum.nextElement();

                if (item.isDirectory()) {//Directory

                    File newdir = new File(installFolder.getAbsolutePath() + File.separator + item.getName());
                    System.out.print("Creating directory " + newdir + " ... ");
                    boolean mkdir = newdir.mkdir();
                    System.out.println("Done! And created : "+mkdir);

                } else  {//File

                    String newfile = installFolder.getAbsolutePath() + File.separator + item.getName();
                    System.out.println("Deleting previous file "+ newfile);
                    new File(newfile).delete();
                    System.out.print("Writing " + newfile+ " ... ");
                    InputStream is = zf.getInputStream(item);
                    FileOutputStream fos = new FileOutputStream(newfile);
                    BufferedInputStream bis = new BufferedInputStream(is);

                    byte[] b = new byte[1024];
                    int ch;
                    while ((ch = bis.read(b)) != -1) {
                        fos.write(b,0,ch);
                    }
                    fos.close();
                    System.out.println("Done!");
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
            File propFile = new File(installFolder.getAbsoluteFile() + File.separator + "db.properties");
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


    public Boolean isDbExists(File ifold) {
        String ifpath = ifold.getAbsolutePath();
        File dbFolder = new File(ifpath + File.separator + Install.dbName + File.separator + dbName);
        return dbFolder.exists();
    }

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
