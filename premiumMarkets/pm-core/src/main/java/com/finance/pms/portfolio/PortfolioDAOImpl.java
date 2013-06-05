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
package com.finance.pms.portfolio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.files.TransactionElement;
import com.finance.pms.datasources.shares.SharesListId;
import com.finance.pms.datasources.shares.Stock;

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class, value="hibernateTx")
public class PortfolioDAOImpl extends HibernateDaoSupport implements PortfolioDAO {

	protected static MyLogger LOGGER = MyLogger.getLogger(PortfolioDAOImpl.class);  
	
	private SharesList unknownSharelIstCache;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<PortfolioShare> loadPortfolioShareForStock(Stock stock) {
	
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PortfolioShare.class);
		detachedCriteria.add(Restrictions.eq("stock",stock));
		
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
		
	}
	
	@Transactional(readOnly=true)
	public PortfolioShare loadPortfolioShare(final String symbol, final String isin, final String portfolioName) {
		
		return this.getHibernateTemplate().execute(new HibernateCallback<PortfolioShare>() {
					
					public PortfolioShare doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria = session.createCriteria(PortfolioShare.class);
						criteria.add(Restrictions.eq("stock.symbol",symbol));
						criteria.add(Restrictions.eq("stock.isin",isin));
						criteria.add(Restrictions.eq("portfolio.name",portfolioName));
						return (PortfolioShare) criteria.uniqueResult();
					}
		});
		
	}
	
	public void saveOrUpdatePortfolio(AbstractSharesList portfolio) {
		
		try {
			this.getHibernateTemplate().saveOrUpdate(portfolio);
		} catch (Exception e) {
			LOGGER.error("While saving Portfolio : "+portfolio,e);
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public void saveOrUpdatePortfolioShare(PortfolioShare portfolioShare) {
	
		try {
			this.getHibernateTemplate().saveOrUpdate(portfolioShare);
			//this.getHibernateTemplate().flush();
		} catch (Exception e) {
			LOGGER.error("While saving/updating Portfolio Share: "+portfolioShare,e);
			throw new RuntimeException(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
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
	
	@Transactional(readOnly=true)
	public SharesList loadShareList(String shareListName) {
		
		
		String upperShareListName = shareListName.toUpperCase();
		
		if (shareListName.equalsIgnoreCase(SharesListId.UNKNOWN.getSharesListCmdParam())) {
			if (unknownSharelIstCache == null) {
				unknownSharelIstCache = (SharesList)this.getHibernateTemplate().get(SharesList.class, upperShareListName);
			}
			return unknownSharelIstCache;
		}
	
		SharesList shareList= (SharesList)this.getHibernateTemplate().get(SharesList.class, upperShareListName);
		
		if (shareList == null) shareList =  new SharesList(upperShareListName);	
		return shareList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<String> loadShareListNames() {
		return this.getHibernateTemplate().find("select name from SharesList");
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<String> loadShareListNames(String[] include, String[] exclude) {
		DetachedCriteria detachedCriteria= DetachedCriteria.forClass(SharesList.class);
		detachedCriteria.setProjection(Projections.projectionList().add(Projections.property("name")));
		if (include != null && include.length > 0) detachedCriteria.add(Restrictions.in("name", include));	
		if (exclude != null && exclude.length > 0) detachedCriteria.add(Restrictions.not(Restrictions.in("name", exclude)));	
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}
	
	

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
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
	@Transactional(readOnly=true)
	public SortedSet<TransactionElement> loadTransactionReportFor(Stock stock, String account, Date date) {
		List<TransactionElement> trans = this.getHibernateTemplate().find("from TransactionElement where symbol = ? and isin = ? and account = ? and date <= ? order by date", stock.getSymbol(), stock.getIsin(), account, date);
		return new TreeSet<TransactionElement>(trans);
	}

	
	public void deleteTransactionReports() {
		this.getHibernateTemplate().deleteAll(this.getHibernateTemplate().loadAll(TransactionElement.class));
		
	}
	
	public void close() {
		if (unknownSharelIstCache != null) saveOrUpdatePortfolio(unknownSharelIstCache);
	}
	
}
