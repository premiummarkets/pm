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
package com.finance.pms.datasources.web.formaters;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.web.MyUrl;
import com.finance.pms.weather.WeatherElement;

public class WeatherFormater extends LineFormater {
	
	/** The PATTERNS. */
	private static PatternProperties PATTERNS;
	
	private Pattern weatherYearTitle;
	private Pattern weatherYearValue;
	
	Boolean titleFound;
	Integer valuesCptr;

	private WeatherElement weather;

	
	public WeatherFormater(String url, Date date) {
		super(new MyUrl(url));

		try {
			if (null == WeatherFormater.PATTERNS)
				WeatherFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			LOGGER.debug("", e);
		}
		
		weatherYearTitle = Pattern.compile(WeatherFormater.PATTERNS.getProperty("weatherYearTitle"));
		weatherYearValue = Pattern.compile(WeatherFormater.PATTERNS.getProperty("weatherYearValue"));
		titleFound = false;
		valuesCptr = 0;
		weather = new WeatherElement(date);
	
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		
		LOGGER.trace(line);
		
		if (!titleFound) {
			Matcher fitWeatherTitle = weatherYearTitle.matcher(line);
			if (fitWeatherTitle.find()) {
				titleFound = true;
				
			} 
		} else {
			Matcher fitWeatherValue = weatherYearValue.matcher(line);
			if (fitWeatherValue.find()) {
				switch (valuesCptr) {
				case 0 :
					weather.setMaxTemp(Integer.valueOf(fitWeatherValue.group(1)));
					break;
				case 1 :
					weather.setAvgTemp(Integer.valueOf(fitWeatherValue.group(1)));
					break;
				case 2 :
					weather.setMinTemp(Integer.valueOf(fitWeatherValue.group(1)));
					break;
				case 3 :
					throw new StopParseFoundException(weather);
				}
				valuesCptr++;
			}
		}

		return null;
	}
	
	@Override
	public Boolean canHaveNoResultsFound() {
		return false;
	}

}
