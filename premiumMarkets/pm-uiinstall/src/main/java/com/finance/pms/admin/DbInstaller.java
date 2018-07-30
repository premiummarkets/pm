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
package com.finance.pms.admin;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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

	private static final String oldtestUpgradeNum_0 = "-1";
	private static final String oldestUpgradeDate_0000 = "0000";

	public static String EXTRACTDIR = "export";

	private InputStream packagedDbStream;

	public DbInstaller() {
		super();
	}

	public void extractDataAndSql(String targetDbName, String remoteDb, String dbZipFileName, File folderTo, Boolean unCompressData) throws NoPreparedDbException {

		//extract db
		System.out.println("Extract db : "+ dbZipFileName +" to "+ folderTo + " and alternatively from "+ remoteDb);

		packagedDbStream = this.getClass().getClassLoader().getResourceAsStream(dbZipFileName);

		if (null == packagedDbStream) {
			try {
				URL remoteDbUrl = new URL(remoteDb);
				System.out.println("No Data base found in package. Will download.");
				packagedDbStream = downloadDb(remoteDbUrl, dbZipFileName);
			} catch (Exception e) {
				System.out.println("Error reading for remote db copy.");
				e.printStackTrace();
			}
		} else {
			System.out.println("Data base found in package.");
		}

		uncompressBzip2(packagedDbStream, folderTo, unCompressData);
		System.out.println("Extract db. Done!");

		String defaultDbName = "premiummarkets";
		if (!targetDbName.equals(defaultDbName)) {
			new File(folderTo.getAbsolutePath() + File.separator + "derby" + File.separator + defaultDbName)
			.renameTo(new File(folderTo.getAbsolutePath() + File.separator + "derby" + File.separator + targetDbName));
			System.out.println("Renaming from " + defaultDbName + " to " + targetDbName + ". Done!");
		}

	}

	protected void uncompressBzip2(InputStream bzip2FileIS, File folderTo, Boolean unCompressData) {

		try {

			bzip2FileIS.read(new byte[2]); //Reads the BZ odd bytes
			CBZip2InputStream bzipIS = new CBZip2InputStream(bzip2FileIS);
			TarInputStream tis = new TarInputStream(bzipIS);
			TarEntry tarEntry = tis.getNextEntry();

			while (tarEntry != null) {

				if (!unCompressData) { //Upgrade
					if (tarEntry.getName().startsWith("derby") || tarEntry.getName().endsWith(".dat") || tarEntry.getName().endsWith(".dat.bz2")) {
						System.out.println("Upgrade, skipping extraction of " + tarEntry.getName());
						tarEntry = tis.getNextEntry();
						continue;
					}
				}

				File destPath = new File(folderTo.getAbsolutePath() + File.separator + tarEntry.getName());
				System.out.println("Extracting " + destPath.getAbsoluteFile());

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

	public void importDBData(File installFolder, String extractDirName, Connection connection) throws OtherException {

		System.out.println("Doing : Import db");

		//drop constraints
		dropConstraints(installFolder, extractDirName, connection);

		//insert data and create indexes
		importData(installFolder, extractDirName, connection);

		//create keys and indexes 
		createIndexesAndKeys(installFolder, extractDirName, connection);

		//init status file
		try {

			String installPath = installFolder.getAbsolutePath() + File.separator;
			File scriptsStatusFile = new File(installPath + extractDirName + File.separator + "upgrade" + File.separator + "upgradeStatus.txt");
			System.out.println("Creating initial status script file : "+scriptsStatusFile.getAbsolutePath());
			scriptsStatusFile.createNewFile();
			SortedSet<File> sortedUpgrades = sortedUpgradeScriptFilesSet(oldestUpgradeDate_0000, oldtestUpgradeNum_0, extractDirName, installPath);
			runUpgradeScripts(scriptsStatusFile, sortedUpgrades, connection, true);

		} catch (IOException e) {
			System.out.println("Could not init status file for future upgrades!");
			e.printStackTrace();
		}

		System.out.println("Import db. Done!");

	}

	//Expected name format is : yyyyMMdd_num_ ..... .sql
	public void upgradeExisting(File installFolder, String extractDirName, Connection connection) throws IOException, OtherException {

		try {

			System.out.println("Doing : Upgrade existing data base.");
			String installPath = installFolder.getAbsolutePath() + File.separator;

			//Checking already ran //TODO what shall we do if the script was NOK?
			String lastStampRan = oldestUpgradeDate_0000;
			String lastNumRan= oldtestUpgradeNum_0;
			File scriptsStatusFile = new File(installPath + extractDirName + File.separator + "upgrade" + File.separator + "upgradeStatus.txt");
			if (scriptsStatusFile.exists()) {
				BufferedReader statusReader = new BufferedReader(new FileReader(scriptsStatusFile));
				String alreadyRanStatus = null;
				while ( (alreadyRanStatus = statusReader.readLine()) != null ) {
					String alreadyRanScript = alreadyRanStatus.substring(0, alreadyRanStatus.indexOf(","));
					String[] arSqlScriptTimeStamp = extractSqlScriptTimeStamp(alreadyRanScript);
					System.out.println("Script found in status : "+ alreadyRanStatus + ". Time stamp : "+Arrays.toString(arSqlScriptTimeStamp));
					lastStampRan = arSqlScriptTimeStamp[0];
					lastNumRan = arSqlScriptTimeStamp[1];
				}
				System.out.println("Last script found time stamp : "+lastStampRan+"_"+lastNumRan);
				statusReader.close();
			} else {
				System.out.println("No status script file found : "+scriptsStatusFile.getAbsolutePath());
				scriptsStatusFile.createNewFile();
			}

			//Loading scripts
			SortedSet<File> sortedUpgrades = sortedUpgradeScriptFilesSet(lastStampRan, lastNumRan, extractDirName, installPath);

			SortedSet<File> sortedAndReplacedUpgrades = replaceInstallPath(installPath, sortedUpgrades);

			//Running valid scripts
			runUpgradeScripts(scriptsStatusFile, sortedAndReplacedUpgrades, connection, false);

		} finally {

			this.setChanged();
			this.notifyObservers("Running upgrade scripts");

		}

	}

	protected SortedSet<File> replaceInstallPath(String installPath, SortedSet<File> sortedUpgrades) throws FileNotFoundException, IOException {
		SortedSet<File> sortedAndReplacedUpgrades = new TreeSet<File>();
		for (File file : sortedUpgrades) {
			BufferedReader br = new BufferedReader(new FileReader(file));
			File newFile = new File(file.getAbsolutePath()+"_replaced");
			BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
			String line = null;
			while( (line = br.readLine()) != null) {
				String newLine = line.replace("#INSTALLDIR#", installPath);
				bw.write(newLine+"\n");
			}
			br.close();
			bw.close();
			sortedAndReplacedUpgrades.add(newFile);
		}
		return sortedAndReplacedUpgrades;
	}

	protected void runUpgradeScripts(File scriptsStatusFile, SortedSet<File> sortedUpgrades, Connection connection, Boolean smoke) throws IOException, OtherException {

		BufferedWriter statusWriter = new BufferedWriter(new FileWriter(scriptsStatusFile, true));
		for (File sqlScript : sortedUpgrades) {
			try {

				if (!smoke) {
					System.out.println("Running : "+scriptsStatusFile.getAbsolutePath());
					try {
						runSqlScript(connection, sqlScript, true);
						statusWriter.append(sqlScript.getName()+", OK");
						statusWriter.newLine();
						statusWriter.flush();
					} catch (Exception e) {
						statusWriter.append(sqlScript.getName() + ", NOK " + e.getMessage());
						statusWriter.newLine();
						statusWriter.flush();
						System.out.println("Error running upgrade script : "+sqlScript.getAbsolutePath());
						e.printStackTrace();
					}
				} else {
					System.out.println("Updating in status only (init smoke) : "+scriptsStatusFile.getAbsolutePath());
					statusWriter.append(sqlScript.getName()+", OK");
					statusWriter.newLine();
					statusWriter.flush();
				}

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

	}

	protected SortedSet<File> sortedUpgradeScriptFilesSet(String lastStampRan, String lastNumRan, String extractDirName, String installPath) {

		File[] listFiles = upgradeScriptFilesList(extractDirName, installPath);

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
			System.out.println("Checking file validity:"+file.getName());
			String[] sqlScriptStampsArray = new String[0];
			boolean validSqlScriptFile = false;
			boolean isNewerScript = false;
			try {
				sqlScriptStampsArray = extractSqlScriptTimeStamp(file.getName());
				validSqlScriptFile = isValidSqlScriptFile(sqlScriptStampsArray);
				isNewerScript = isNewerScript(lastStampRan, lastNumRan, sqlScriptStampsArray);
			} catch (Exception e) {
				System.out.println(file.getName() + ": " + e.getMessage());
			}
			if (validSqlScriptFile && isNewerScript) {
				System.out.println("Will potentially run upgrade script : "+file.getAbsolutePath() + ". Time stamp : "+Arrays.toString(sqlScriptStampsArray) + ". Validity : "+validSqlScriptFile +". Against last time stamp : "+lastStampRan+"_"+lastNumRan);
				sortedUpgrades.add(file);
			} else {
				System.out.println("Ignoring invalid or already ran script file : "+file.getAbsolutePath() + ". Time stamp : "+Arrays.toString(sqlScriptStampsArray) + ". Validity : "+validSqlScriptFile+". Against last time stamp : "+lastStampRan+"_"+lastNumRan);
			}
		}
		return sortedUpgrades;
	}

	protected boolean isNewerScript(String lastStampRan, String lastNumRan, String[] sqlScriptStampsArray) {
		return sqlScriptStampsArray[0].compareTo(lastStampRan) > 0 || ( sqlScriptStampsArray[0].compareTo(lastStampRan) == 0  && sqlScriptStampsArray[1].compareTo(lastNumRan) > 0 );
	}

	protected File[] upgradeScriptFilesList(String extractDirName, String installPath) {
		String sqlScriptFolderPath = installPath + extractDirName + File.separator + "upgrade" + File.separator;
		File sqlUpgradeFolder = new File(sqlScriptFolderPath);
		File[] listFiles = sqlUpgradeFolder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".sql");
			}
		});
		return listFiles;
	}

	private String[] extractSqlScriptTimeStamp(String fileName) {
		try {
			int first_ = fileName.indexOf("_");
			int second_ = fileName.indexOf("_", first_+1);
			String stampString = fileName.substring(0, second_);
			String[] split = stampString.split("_");
			return split;
		} catch (Exception e) {
			System.out.println("Invalid file name: "+fileName);
			return new String[]{oldestUpgradeDate_0000, oldtestUpgradeNum_0};
		}
	}

	private boolean isValidSqlScriptFile(String[] split) {
		if (split.length != 2) return false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			dateFormat.parse(split[0]);
		} catch (ParseException e) {
			System.out.println("Invalid sql script file name split : "+Arrays.toString(split)+": "+e.getMessage());
			//e.printStackTrace();
			return false;
		}
		try {
			Integer.valueOf(split[1]);
		} catch (NumberFormatException e) {
			System.out.println("Invalid sql script file name split : "+split+": "+e.getMessage());
			//e.printStackTrace();
			return false;
		}
		return true;
	}

	private void importData(File installFolter, String extractDirName, Connection connection) throws OtherException {

		String installPath = installFolter.getAbsolutePath() + File.separator;

		File datDir = new File(installPath+extractDirName);

		//Potential compressed
		File [] bzip2Files = datDir.listFiles(new FileFilter() {

			public boolean accept(File pathname) {
				String fname = pathname.getName();
				return fname.endsWith(".bz2");
			}

		});
		for (File bz2File : bzip2Files) {
			try {
				InputStream bzip2FileIS = new FileInputStream(bz2File);
				uncompressBzip2(bzip2FileIS, datDir.getAbsoluteFile(), true);
			} catch (FileNotFoundException e) {
				System.out.println("Well I couldn't find the file : " + bz2File.getAbsolutePath() + ". Also I could list it in " + datDir.getAbsoluteFile() + " as a .bz2 compressed file");
				e.printStackTrace();
			}
		}

		//Import .dat s
		File [] datFiles = datDir.listFiles(new FileFilter() {

			public boolean accept(File pathname) {
				String fname = pathname.getName();
				return fname.endsWith(".dat");
			}

		});

		for (int i = 0; i < datFiles.length; i++) {

			try {

				String name = datFiles[i].getName();
				String fnNoExt = name.substring(0,name.length()-4);

				System.out.println(new Date() + " : Processing "+datFiles[i].getAbsolutePath());

				PreparedStatement psQuotation = connection.prepareStatement("CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE (?,?,?,?,?,?,?)");
				psQuotation.setString(1, null);
				psQuotation.setString(2, fnNoExt.toUpperCase());
				psQuotation.setString(3, datFiles[i].getAbsolutePath());
				psQuotation.setString(4, null);
				psQuotation.setString(5, null);
				psQuotation.setString(6, null);
				psQuotation.setString(7, "1");

				psQuotation.execute();
				System.out.println(new Date() + " : "+datFiles[i].getAbsolutePath() +". Done!");

				this.setChanged();
				this.notifyObservers("setting Quotations data");

			} catch (Exception e) {
				System.out.println("Error importing "+datFiles[i].getAbsolutePath());
				e.printStackTrace();
			}
		}


	}

	private void dropConstraints(File installFolder, String extractDirName, Connection connection) throws OtherException {
		String installPath = installFolder.getAbsolutePath() + File.separator;
		try {
			File dropFile = new File(installPath + extractDirName + File.separator + "DROP.sql");
			runSqlScript(connection, dropFile, false);
			System.out.println("Drop constraints. Done!");
		} catch (FileNotFoundException e) {
			System.out.println("No constraints to drop.");
			throw new OtherException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new OtherException(e);
		} catch (SQLException e) {
			System.out.println("Scripts DROP.sql is NOT atomic so I should not be here");
			e.printStackTrace();
		}
		this.setChanged();
		this.notifyObservers("Dropping constraints");

	}

	private void createIndexesAndKeys(File installFolder, String extractDirName, Connection connection) throws OtherException {

		String installPath = installFolder.getAbsolutePath() + File.separator;

		try {
			File keysFile = new File(installPath + extractDirName + File.separator + "KEYS.sql");
			runSqlScript(connection, keysFile, false);
			System.out.println("Keys. Done!");
		} catch (FileNotFoundException e) {
			System.out.println("No Keys import available.");
			throw new OtherException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new OtherException(e);
		} catch (SQLException e) {
			System.out.println("Scripts KEYS.sql is NOT atomic so I should not be here");
			e.printStackTrace();
		}

		this.setChanged();
		this.notifyObservers("Setting keys");

		try {

			File indexesFile = new File(installPath + extractDirName + File.separator + "INDEXES.sql");
			runSqlScript(connection, indexesFile, false);
			System.out.println("Indexes. Done!");

		} catch (FileNotFoundException e) {
			System.out.println("No Indexes import available.");
			throw new OtherException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new OtherException(e);
		} catch (SQLException e) {
			System.out.println("Scripts INDEXES.sql is NOT atomic so I should not be here");
			e.printStackTrace();
		}

		this.setChanged();
		this.notifyObservers("Setting indexes");

	}

	//TODO roll back on error?
	private void runSqlScript(Connection connection, File sqlScript, Boolean atomic) throws FileNotFoundException, IOException, SQLException {

		BufferedReader fr = new BufferedReader(new FileReader(sqlScript));
		String statement;
		while ((statement = fr.readLine()) != null) {
			statement = statement.trim();
			if (!statement.isEmpty() && !statement.substring(0, 2).equals("--")) {
				System.out.println("Doing : "+statement);
				try {
					PreparedStatement ps = connection.prepareStatement(statement.substring(0, statement.length() - 1));
					ps.execute();
				} catch (java.sql.SQLException e) {
					System.out.println("Error in : "+statement);
					String status =  statement + " : "+ e.toString();
					if (atomic) {
						fr.close();
						throw new SQLException(status, e);
					}
				}
				System.out.println(statement + " Done!");
			}
		}
		fr.close();

	}

	public InputStream getPackagedDbStream() {
		return packagedDbStream;
	}

}
