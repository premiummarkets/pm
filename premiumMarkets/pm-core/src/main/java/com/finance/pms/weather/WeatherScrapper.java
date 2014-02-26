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
package com.finance.pms.weather;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.web.HttpSourceWeather;
import com.finance.pms.datasources.web.MyBeanFactoryAware;
import com.finance.pms.datasources.web.formaters.WeatherFormater;


public class WeatherScrapper implements MyBeanFactoryAware {
	
	private final MyLogger LOGGER = MyLogger.getLogger(WeatherScrapper.class);

	private HttpSourceWeather httpSource;
	private SpringContext springContext;
	
	public WeatherScrapper(SpringContext springContext, String pathToprops) {
		super();
		this.httpSource = new HttpSourceWeather(pathToprops, this);
		this.springContext = springContext;
	}


	@SuppressWarnings("unchecked")
	public List<WeatherElement> getWeather(final Date date) throws InterruptedException {
		
		@SuppressWarnings("rawtypes")
		final List weathers = new ArrayList<WeatherElement>();

		Thread thread = new Thread(new Runnable() {

			public void run() {
				try {
					String url = httpSource.getMonthHistory(date); 
					LOGGER.debug("Url : "+url);
					weathers.addAll(httpSource.readURL(new WeatherFormater(url,date)));
				} catch (HttpException e) {
					LOGGER.error("",e);
				} finally {

				}
			}
		});
		
		thread.start();
		
		thread.join();
		return weathers;
	}
	

	public void updateWeatherData(Date endDate) throws InterruptedException {
		
		WeatherDAO weatherDao = (WeatherDAO) springContext.getBean("weatherDAO");
		SortedSet<WeatherElement> monthlyWeatherUntilEndDate = weatherDao.getMonthlyWeatherUntil(endDate, new Comparator<WeatherElement>() {
			
			public int compare(WeatherElement o1, WeatherElement o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});
		
		Date lastWeatherDateInDB;
		try {
			lastWeatherDateInDB = new SimpleDateFormat("yyyy/MM/dd").parse("2000/01/01");
			if (monthlyWeatherUntilEndDate.size() > 0) {
				lastWeatherDateInDB = monthlyWeatherUntilEndDate.last().getDate();
			}
		} catch (ParseException e) {
			LOGGER.error(e,e);
			throw new InterruptedException(e.toString());
		}
	
		Calendar lastDBDateMinus2Month = Calendar.getInstance();
		lastDBDateMinus2Month.setTime(lastWeatherDateInDB);
		lastDBDateMinus2Month.add(Calendar.MONTH, -2);
		List<WeatherElement> weatherElements = this.scrape(lastDBDateMinus2Month.getTime(), endDate);
		
		weatherDao.saveAndUpDate(weatherElements);
	
	}
	
	public static void main(String... args) throws InterruptedException, ParseException {
		String dbProps = args[0];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date startDate = simpleDateFormat.parse(args[1]);
		Date endDate = simpleDateFormat.parse(args[2]);
		
		SpringContext springContext = new SpringContext(dbProps);
		springContext.loadBeans("/connexions.xml", "/swtclients.xml");
		springContext.refresh();
		
		WeatherScrapper scrapper = new WeatherScrapper(springContext, dbProps);
		List<WeatherElement> weatherElements = scrapper.scrape(startDate, endDate);
		
		System.out.println(weatherElements);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		File file = new File(System.getProperty("installdir") + File.separator + "tmp" + File.separator +"weatherFor"+df.format(startDate)+"_"+df.format(endDate)+".csv");
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			bufferedWriter.write("Date, Max Temp, Avg Temp, Min Temp");
			bufferedWriter.newLine();
			for (WeatherElement weatherElement : weatherElements) {
				bufferedWriter.write(weatherElement.toCsv());
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (springContext != null) springContext.close();
	}

	public List<WeatherElement> scrape(Date startDate, Date endDate) throws InterruptedException {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		
		List<WeatherElement> weatherElements = new ArrayList<WeatherElement>();
		
		for (; calendar.getTime().compareTo(endDate) <= 0; calendar.add(Calendar.MONTH, 1)) {
			weatherElements.addAll(this.getWeather(calendar.getTime()));
		}
		return weatherElements;
	}


	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		//Nothing
	}


	public BeanFactory getBeanFactory() {
		return springContext.getBeanFactory();
	}
}
