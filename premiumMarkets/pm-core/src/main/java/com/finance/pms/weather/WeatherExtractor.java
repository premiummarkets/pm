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
package com.finance.pms.weather;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.BeansException;

import com.finance.pms.SpringContext;

public class WeatherExtractor {
	
	private static SpringContext springContext;

	public static void main(String... args) throws IOException, ParseException {
		
		String dbProps = args[0];
		String fileName = args[1];
		
		try {
			SortedSet<WeatherElement> weatherElements = readFile(fileName);
			
			exportToCsv(fileName, weatherElements);
			
			springContext = new SpringContext(dbProps);
			//springContext.setDataSource(dbProps);
			springContext.loadBeans("/connexions.xml","/swtclients.xml");
			springContext.refresh();
			
			storeInDb(weatherElements);
			
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (springContext != null) springContext.close();
		}
		
		
	}

	private static void storeInDb(SortedSet<WeatherElement> weatherElements) {
		
		WeatherDAO weatherDao = (WeatherDAO) springContext.getBean("weatherDAO");
		weatherDao.saveAndUpDate(new ArrayList<WeatherElement>(weatherElements));
	}

	/**
	 * @param fileName
	 * @param weatherElements
	 * @throws IOException
	 */
	private static void exportToCsv(String fileName, SortedSet<WeatherElement> weatherElements) throws IOException {
		Integer currentMonth = -1;
		File exportFile;
		FileWriter fileWriter = null;
		for (WeatherElement weatherElement : weatherElements) {
			Date date = weatherElement.getDate();
			Integer month = getField(date, Calendar.MONTH);
			if (currentMonth != month) {
				currentMonth = month;
				if (fileWriter != null) {
					fileWriter.flush();
					fileWriter.close();
				}
				exportFile = new File(fileName.replace(".csv", "_"+month+"_.csv"));
				exportFile.delete();
				fileWriter = new FileWriter(exportFile,true);
			} 
			fileWriter.write(weatherElement.toCsv()+"\n");
		}
	}

	/**
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	private static SortedSet<WeatherElement> readFile(String fileName) throws FileNotFoundException, IOException, ParseException {
		File file = new File(fileName);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = "";
		bufferedReader.readLine();
		SortedSet<WeatherElement> weatherElements = new TreeSet<WeatherElement>(new WeatherElementsComparator()); 
		
		while ((line = bufferedReader.readLine()) != null) {
			String[] values = line.split(",");
			WeatherElement weatherElement = new WeatherElement(new SimpleDateFormat("EEE MMM 01 00:00:00 z yyyy").parse(values[0]));
			weatherElement.setMaxTemp(Integer.valueOf(values[1]));
			weatherElement.setAvgTemp(Integer.valueOf(values[2]));
			weatherElement.setMinTemp(Integer.valueOf(values[3]));	
			weatherElements.add(weatherElement);
		}
		bufferedReader.close();
		return weatherElements;
	}
	
	/**
	 * @param date
	 */
	static Integer getField(Date date, Integer field) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(field);
	}
}
