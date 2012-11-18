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
package com.finance.pms.portfolio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.files.TransactionElement;
import com.finance.pms.datasources.shares.Stock;


public class PortfolioDAOImpl extends HibernateDaoSupport implements PortfolioDAO {

	protected static MyLogger LOGGER = MyLogger.getLogger(PortfolioDAOImpl.class);    

	@SuppressWarnings("unchecked")
	
	public List<PortfolioShare> loadPortfolioShareForStock(Stock stock) {
	
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PortfolioShare.class);
		detachedCriteria.add(Restrictions.eq("stock",stock));
		
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
		
	}

	
	public void saveOrUpdatePortfolio(AbstractSharesList portfolio) {
		
		LOGGER.debug("Portfolio before save :"+ portfolio.toString());
		try {
			this.getHibernateTemplate().saveOrUpdate(portfolio);
		} catch (Exception e) {
			LOGGER.error("While saving Portfolio : "+portfolio,e);
			throw new RuntimeException(e);
		}

		LOGGER.debug("Portfolio after save O update :"+ portfolio.toString());
		
	}

	@SuppressWarnings("unchecked")
	
	public List<Portfolio> loadVisiblePortfolios() {
		
		List<Portfolio>  retour = new ArrayList<Portfolio>();
		try {
			DetachedCriteria detachedCriteriaAutoP = DetachedCriteria.forClass(AutoPortfolio.class);
			detachedCriteriaAutoP.addOrder(Order.asc("name"));
			retour = this.getHibernateTemplate().findByCriteria(detachedCriteriaAutoP);
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		
		try {
			DetachedCriteria detachedCriteriaUserP = DetachedCriteria.forClass(UserPortfolio.class);
			detachedCriteriaUserP.addOrder(Order.asc("name"));
			retour.addAll(this.getHibernateTemplate().findByCriteria(detachedCriteriaUserP));
		} catch (DataAccessException e) {
			LOGGER.error(e,e);
		}
		
		return retour;	
	}
	
	public void delete(AbstractSharesList portfolio) {
		this.getHibernateTemplate().delete(portfolio);
	}
	
	public SharesList loadShareList(String shareListName) {
		String upperShareListName = shareListName.toUpperCase();
		SharesList shareList= (SharesList)this.getHibernateTemplate().get(SharesList.class, upperShareListName);
		
		if (shareList == null) shareList =  new SharesList(upperShareListName);	
		return shareList;
	}
	
	@SuppressWarnings("unchecked")
	
	public List<String> loadShareListNames() {
		return this.getHibernateTemplate().find("select name from SharesList");
	}

	@SuppressWarnings("unchecked")
	
	public List<String> loadUserPortfolioNames() {
		return this.getHibernateTemplate().find("select name from UserPortfolio");
	}	
	

	
	public void deletePortfolioShare(PortfolioShare portfolioShare) {		
		this.getHibernateTemplate().delete(portfolioShare);
	}

	
	public void saveOrUpdateTransactionReports(ArrayList<TransactionElement> reportElements) {
			this.getHibernateTemplate().saveOrUpdateAll(reportElements);
		
	}

	@SuppressWarnings("unchecked")
	
	public SortedSet<TransactionElement> loadTransactionReportFor(Stock stock, String account, Date date) {
		List<TransactionElement> trans = this.getHibernateTemplate().find("from TransactionElement where symbol = ? and isin = ? and account = ? and date <= ? order by date", stock.getSymbol(), stock.getIsin(), account, date);
		return new TreeSet<TransactionElement>(trans);
	}

	
	public void deleteTransactionReports() {
		this.getHibernateTemplate().deleteAll(this.getHibernateTemplate().loadAll(TransactionElement.class));
		
	}
	
	
	
}
