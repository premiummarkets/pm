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
package com.finance.pms.admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class DosPathTest.
 * 
 * @author Guillaume Thoreton
 */
public class DosPathTest {
	
	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void main(String...args) {
		File f = new File(System.getProperty("installdir"));
		System.out.println(f.getAbsolutePath());
		
		String dossedPath1 = f.getAbsolutePath().replaceAll("\\\\","HELLO");
		System.out.println("\""+dossedPath1+"\"");
		
		String dossedPath = f.getAbsolutePath().replaceAll("\\\\","\\\\\\\\\\\\\\\\");
		System.out.println("\""+dossedPath+"\"");
		
		String dossedJavaexe = (System.getProperty("java.home")+File.separator+"bin"+File.separator+"java.exe").replaceAll("\\\\","\\\\\\\\");
		System.out.println("\""+dossedJavaexe+"\"");
		
		System.out.println(dossedPath1);
		
		
		try {
			FileReader inputRegFileReader = new FileReader(new File("try.reg.var"));
			BufferedReader br = new BufferedReader(inputRegFileReader);
			FileWriter ouputRegFileWriter = new FileWriter("try.reg");
			BufferedWriter bw = new BufferedWriter(ouputRegFileWriter);
			String inLine;
			String outLine;
			while( (inLine = br.readLine()) != null) {
				outLine = inLine.replace("%JAVAEXE%", dossedJavaexe);
				outLine = outLine.replaceAll("%CD%", dossedPath);
				System.out.println(outLine);
				bw.write(outLine+"\n");
			}
//			bw.write(dossedPath+"\n");
//			bw.write(dossedPath1+"\n");
	
			bw.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
