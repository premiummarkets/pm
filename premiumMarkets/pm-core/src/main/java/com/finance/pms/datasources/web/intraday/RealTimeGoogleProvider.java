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
package com.finance.pms.datasources.web.intraday;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyBeanFactoryAware;
import com.finance.pms.datasources.web.formaters.RealTimeGoogleLineFormater;
import com.finance.pms.datasources.web.formaters.RealTimeGoogleLineFormater.ValidatableQuotationUnit;
import com.finance.pms.events.quotations.QuotationUnit;

public class RealTimeGoogleProvider implements MyBeanFactoryAware {
	
	private static MyLogger LOGGER = MyLogger.getLogger(RealTimeGoogleProvider.class);
	
	protected HttpSourceGoogleIntraDay httpSource;
	protected BeanFactory beanAwareFactory;
	
	public RealTimeGoogleProvider(String pathToProps) {
		super();
		this.httpSource = new HttpSourceGoogleIntraDay(pathToProps, this);
	}

	public  SortedSet<QuotationUnit> getIntraDayQuotations(Stock stock, int nbPeriods) throws HttpException {
		
		SortedSet<QuotationUnit> ret = new TreeSet<QuotationUnit>();

		String realTimeQuotesURL = httpSource.getRealTimeQuotesURL(stock, 60, nbPeriods);
		LOGGER.info("Intra Google url : "+realTimeQuotesURL);
		RealTimeGoogleLineFormater lineFormater = new RealTimeGoogleLineFormater(realTimeQuotesURL, stock);
		List<Validatable> intraDayQuotes = httpSource.readURL(lineFormater);
		
		for (Validatable validatable : intraDayQuotes) {
			ret.add(((ValidatableQuotationUnit)validatable).getQuotationUnit());
		}
		
		return ret;
	}

	
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanAwareFactory = beanFactory;
	}

	
	public BeanFactory getBeanFactory() {
		return beanAwareFactory;
	}

}
