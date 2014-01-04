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

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class, value="hibernateTx")
public class WeatherDAOImpl extends HibernateDaoSupport implements WeatherDAO {
	
	private static WeatherDAO singleton; 

	private WeatherDAOImpl() {
		super();
		WeatherDAOImpl.singleton = this;
	}

	public static WeatherDAO getInstance() {
		return singleton;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public SortedSet<WeatherElement> getMonthlyWeatherUntil(Date endDate, Comparator<WeatherElement> weatherComparator) {
		@SuppressWarnings("rawtypes")
		List weatherElements = this.getHibernateTemplate().find("from WeatherElement as weatherElement where weatherElement.date <= ? ",endDate);
		TreeSet<WeatherElement> treeSet = new TreeSet<WeatherElement>(weatherComparator);
		treeSet.addAll(weatherElements);
		return treeSet;
	}

	
	public void saveAndUpDate(List<WeatherElement> weatherElements) {
		this.getHibernateTemplate().saveOrUpdateAll(weatherElements);
		
	}
}
