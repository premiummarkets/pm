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
package com.finance.pms.weather;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.finance.pms.datasources.db.Query;
import com.finance.pms.datasources.db.Validatable;

@Entity
@Table(name="Weather")
public class WeatherElement extends Validatable {
	
	private static final long serialVersionUID = 1073659764360998307L;
	
	@Id
	Date date;
	
	Integer maxTemp;
	Integer avgTemp;
	Integer minTemp;
	BigDecimal precipitation;
	Integer wind;

	@SuppressWarnings("unused")
	private WeatherElement() {
		super();
		//hib
	}

	public WeatherElement(Date date) {
		super();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		this.date = calendar.getTime();
		
	}

	@Override
	public Query toDataBase() {
		// TODO Auto-generated method stub
		return null;
	}

	public int compareTo(Validatable o) {
		return this.date.compareTo(((WeatherElement)o).getDate());
	}

	public Integer getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(Integer maxTemp) {
		this.maxTemp = maxTemp;
	}

	public Integer getAvgTemp() {
		return avgTemp;
	}

	public void setAvgTemp(Integer avgTemp) {
		this.avgTemp = avgTemp;
	}

	public Integer getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(Integer minTemp) {
		this.minTemp = minTemp;
	}

	public BigDecimal getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(BigDecimal precipitation) {
		this.precipitation = precipitation;
	}

	public Integer getWind() {
		return wind;
	}

	public void setWind(Integer wind) {
		this.wind = wind;
	}
	
	public Date getDate() {
		return date;
	}

	@SuppressWarnings("unused")
	private void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "WeatherElement [avgTemp=" + avgTemp + ", date=" + date + ", maxTemp=" + maxTemp + ", minTemp=" + minTemp
				+ ", precipitation=" + precipitation + ", wind=" + wind + "]";
	}

	public String toCsv() {
		return date+","+maxTemp+","+avgTemp+","+minTemp+","+precipitation+","+wind;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeatherElement other = (WeatherElement) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}
	
	
	
}
