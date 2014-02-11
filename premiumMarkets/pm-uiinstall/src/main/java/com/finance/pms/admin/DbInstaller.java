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
package com.finance.pms.admin;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Observable;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.tools.bzip2.CBZip2InputStream;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;

/**
 * The Class DbInstaller.
 * 
 * @author Guillaume Thoreton
 */
public class DbInstaller extends Observable {
	
	public static String EXTRACTDIR = "export";

	private InputStream fis;

	public DbInstaller() {
		super();
	}

	public void extractDB(URL urlfrom, String dbZipFileName, File folderTo) throws NoPreparedDbException {
		//extract db
		System.out.println("Extract db : "+ dbZipFileName +" to "+ folderTo + " and alternatively from "+ urlfrom);
		try {
			fis = this.getClass().getClassLoader().getResourceAsStream(dbZipFileName);
			if (null == fis) {
				System.out.println("No Data base found in package. Will download.");
				fis = downloadDb(urlfrom, dbZipFileName);
			} else {
				System.out.println("Data base found in package.");
			}
			
			fis.read(new byte[2]); //Reads the BZ odd bytes
			CBZip2InputStream bzipIS = new CBZip2InputStream(fis);
			TarInputStream tis = new TarInputStream(bzipIS);
			TarEntry tarEntry = tis.getNextEntry();

			while (tarEntry != null) {
				File destPath = new File(folderTo.getAbsolutePath() + File.separator + tarEntry.getName());
				System.out.println("Extracting " + destPath.getAbsoluteFile());
				if (destPath.exists()) {
					//TODO DB already installed => update??
				}
				if (!tarEntry.isDirectory()) {
					if (!destPath.getParentFile().exists()) {
						System.out.println("Creating directory " + destPath.getParent());
						destPath.getParentFile().mkdirs();
					}
					FileOutputStream fout = new FileOutputStream(destPath);
					tis.copyEntryContents(fout);
					fout.close();
					
					this.setChanged();
					this.notifyObservers("extracting "+destPath.getAbsolutePath());
					
				} else {
					System.out.println("Creating directory " + destPath.getAbsoluteFile());
					destPath.mkdirs();
				}
				tarEntry = tis.getNextEntry();
			}
			tis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		System.out.println("Extract db. Done!");
	}
	
	
	private InputStream downloadDb(URL urlFrom, String dbZipFileName) throws NoPreparedDbException {
		ZipInputStream zI = null;
		try {
			//URL initialdb = new URL("http://surfnet.dl.sourceforge.net/sourceforge/pmsqueak/initialdb.jar");
			//URL initialdb = new URL("http://downloads.sourceforge.net/pmsqueak/initialdb-0.1.2.jar?use_mirror=osdn");	
			InputStream is = urlFrom.openStream();
			zI = new ZipInputStream(is);
			ZipEntry ze = zI.getNextEntry();
			while (!ze.getName().equals(dbZipFileName)) {
				ze = zI.getNextEntry();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw new NoPreparedDbException();
		}
		return zI;
	}

	public void importDB(File piggyMarketSqueakFolder, String extractDirName, Connection connection) throws OtherException {
		
		System.out.println("Doing : Import db");
		
		//drop constraints
		dropConstraints(piggyMarketSqueakFolder, extractDirName, connection);
		 
		//insert data and create indexes
		importDerby(piggyMarketSqueakFolder, extractDirName, connection);
		
		//create keys and indexes 
		createIndexesAndKeys(piggyMarketSqueakFolder, extractDirName, connection);
		
		System.out.println("Import db. Done!");
		
	}
	
	//Expected name format is : xxxx_yyyyMMddhhss_num.sql
	//TODO check last upgrade file run using a time stamp in the db or checking what is already there in the existing install folder?
	public void upgradeExisting(File piggyMarketSqueakFolder, String extractDirName, Connection connection) throws IOException, OtherException {
		
		System.out.println("Doing : Upgrade existing data base.");
		String installPath = piggyMarketSqueakFolder.getAbsolutePath() + File.separator;
		
		//Checking already ran //TODO what shall we do if the script was NOK?
		String lastStampRan = "0000";
		String lastNumRan= "0";
		File scriptsStatusFile = new File(installPath + File.separator + "upgrade" + File.separator + "upgradeStatus.txt");
		if (scriptsStatusFile.exists()) {
			BufferedReader statusReader = new BufferedReader(new FileReader(scriptsStatusFile));
			String  alreadyRanStatus = null;
			while ( (alreadyRanStatus = statusReader.readLine()) != null) {
				String alreadyRanScript = alreadyRanStatus.substring(0, alreadyRanStatus.indexOf(","));
				String[] arSqlScriptTimeStamp = extractSqlScriptTimeStamp(alreadyRanScript);
				lastStampRan = arSqlScriptTimeStamp[0];
				lastNumRan = arSqlScriptTimeStamp[1];
			}
			statusReader.close();
		}

		
		//Loading scripts
		String sqlScriptFolderPath = installPath + extractDirName + File.separator + "upgrade" + File.separator;
		File sqlUpgradeFolder = new File(sqlScriptFolderPath);
		File[] listFiles = sqlUpgradeFolder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".sql");
			}
		});
		
		SortedSet<File> sortedUpgrades = new TreeSet<File>(new Comparator<File>() {

			@Override
			public int compare(File o1, File o2) {
				String[] o1ts = extractSqlScriptTimeStamp(o1.getName());
				String[] o2ts = extractSqlScriptTimeStamp(o2.getName());
				int ts = o1ts[0].compareTo(o2ts[0]);
				if (ts != 0) {
					return ts;
				} else {
					int num = o1ts[1].compareTo(o2ts[1]);
					return num;
				}
			}
		});
		
		//Order and filter
		for (File file : listFiles) {
			String[] sqlScriptTimeStamp = extractSqlScriptTimeStamp(file.getName());
			if (isValidSqlScriptFile(sqlScriptTimeStamp) && sqlScriptTimeStamp[0].compareTo(lastStampRan) > 0 && sqlScriptTimeStamp[1].compareTo(lastNumRan) > 0) {
				sortedUpgrades.add(file);
			} else {
				System.out.println("Ignoring invalid or already ran script file : "+file.getAbsolutePath());
			}
		}
		
		//Running valid scripts
		BufferedWriter statusWriter = new BufferedWriter(new FileWriter(scriptsStatusFile));
		for (File sqlScript : sortedUpgrades) {
			try {
				String scriptStatus = runSqlScript(connection, sqlScript);
				statusWriter.append(sqlScript.getName()+", "+((scriptStatus.isEmpty())?"Ok":"NOk "+scriptStatus));
				statusWriter.newLine();
			} catch (FileNotFoundException e) {
				System.out.println("Can't find upgrade script file : "+sqlScript.getAbsolutePath());
				statusWriter.close();
				throw new OtherException(e);
			} catch (IOException e) {
				System.out.println("Error upgrading script file : "+sqlScript.getAbsolutePath());
				statusWriter.close();
				e.printStackTrace();
				throw new OtherException(e);
			}
			System.out.println(sqlScript.getAbsolutePath() + " Done!");
		}
		statusWriter.close();
		
		this.setChanged();
		this.notifyObservers("Running upgrade scripts");
		
	}
	
	private String[] extractSqlScriptTimeStamp(String fileName) {
		String stampString = fileName.substring(fileName.indexOf("_"), fileName.indexOf("."));
		String[] split = stampString.split("_");
		return split;
	}
	
	private boolean isValidSqlScriptFile(String[] split) {
		if (split.length != 2) return false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhss");
		try {
			dateFormat.parse(split[0]);
		} catch (ParseException e) {
			System.out.println("Invalid sql script file name split : "+split);
			e.printStackTrace();
			return false;
		}
		try {
			Integer.valueOf(split[1]);
		} catch (NumberFormatException e) {
			System.out.println("Invalid sql script file name split : "+split);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private void importDerby(File piggyMarketSqueakFolder, String extractDirName, Connection connection) throws OtherException {
		
		String installPath = piggyMarketSqueakFolder.getAbsolutePath()+File.separator;
		
		File datDir = new File(installPath+extractDirName);
		File [] lsdat = datDir.listFiles(new FileFilter() {

			public boolean accept(File pathname) {
				String fname = pathname.getName();
				return fname.endsWith(".dat");
			}
			
		});
		
		for (int i = 0; i < lsdat.length; i++) {
			
			try {
				String name = lsdat[i].getName();
				String fnNoExt = name.substring(0,name.length()-4);
				
				System.out.println(new Date() + " : Processing "+lsdat[i].getAbsolutePath());
				
				PreparedStatement psQuotation = connection.prepareStatement("CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE (?,?,?,?,?,?,?)");
				psQuotation.setString(1, null);
				psQuotation.setString(2, fnNoExt.toUpperCase());
				psQuotation.setString(3, lsdat[i].getAbsolutePath());
				//psQuotation.setString(4, ";");
				psQuotation.setString(4, null);
				psQuotation.setString(5, null);
				psQuotation.setString(6, null);
				psQuotation.setString(7, "1");
				
				psQuotation.execute();
				System.out.println(new Date() + " : "+lsdat[i].getAbsolutePath() +". Done!");
				
				this.setChanged();
				this.notifyObservers("setting Quotations data");
			} catch (Exception e) {
				System.out.println("Error importing "+lsdat[i].getAbsolutePath());
				e.printStackTrace();
			}
		}
		
		
	}
	
	private void dropConstraints(File piggyMarketSqueakFolder, String extractDirName, Connection connection) throws OtherException {
		String installPath = piggyMarketSqueakFolder.getAbsolutePath() + File.separator;
		try {
			File dropFile = new File(installPath + extractDirName + File.separator + "DROP.sql");
			runSqlScript(connection, dropFile);
			System.out.println("Drop constraints. Done!");
		} catch (FileNotFoundException e) {
			System.out.println("No constraints to drop.");
			throw new OtherException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new OtherException(e);
		}
		this.setChanged();
		this.notifyObservers("Dropping constraints");
		
	}

	private void createIndexesAndKeys(File piggyMarketSqueakFolder, String extractDirName, Connection connection) throws OtherException {

		String installPath = piggyMarketSqueakFolder.getAbsolutePath() + File.separator;

		try {
			File keysFile = new File(installPath + extractDirName + File.separator + "KEYS.sql");
			runSqlScript(connection, keysFile);
			System.out.println("Keys. Done!");
		} catch (FileNotFoundException e) {
			System.out.println("No Keys import available.");
			throw new OtherException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new OtherException(e);
		}

		this.setChanged();
		this.notifyObservers("Setting keys");
		
		try {

			File indexesFile = new File(installPath + extractDirName + File.separator + "INDEXES.sql");
			runSqlScript(connection, indexesFile);
			System.out.println("Indexes. Done!");

		} catch (FileNotFoundException e) {
			System.out.println("No Indexes import available.");
			throw new OtherException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new OtherException(e);
		}

		this.setChanged();
		this.notifyObservers("Setting indexes");

	}

	//TODO roll back on error?
	private String runSqlScript(Connection connection, File sqlScript) throws FileNotFoundException, IOException {
		
		BufferedReader fr = new BufferedReader(new FileReader(sqlScript));
		String statement;
		String status = "";
		while ((statement = fr.readLine()) != null) {
			if (!statement.isEmpty() && !statement.substring(0, 2).equals("--")) {
				System.out.println("Doing : "+statement);
				try {
					PreparedStatement ps = connection.prepareStatement(statement.substring(0, statement.length() - 1));
					ps.execute();
				} catch (java.sql.SQLException e) {
					System.out.println("Error in : "+statement);
					status = status + " ; " + statement +":"+e.toString();
				}
				System.out.println(statement + " Done!");
			}
		}
		fr.close();
		
		return status;
	}

	public InputStream getFis() {
		return fis;
	}

}
