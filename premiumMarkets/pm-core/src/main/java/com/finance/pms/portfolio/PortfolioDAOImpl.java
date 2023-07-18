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
package com.finance.pms.portfolio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import com.finance.pms.datasources.web.Indice;
import com.finance.pms.datasources.web.ProvidersList;

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class, value="hibernateTx")
public class PortfolioDAOImpl extends HibernateDaoSupport implements PortfolioDAO {

	protected static MyLogger LOGGER = MyLogger.getLogger(PortfolioDAOImpl.class);  

	private SharesList unknownShareListCache;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<PortfolioShare> loadPortfolioShareForStock(Stock stock) {

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PortfolioShare.class);
		detachedCriteria.add(Restrictions.eq("stock", stock));

		return new ArrayList<PortfolioShare>((Collection<? extends PortfolioShare>) this.getHibernateTemplate().findByCriteria(detachedCriteria));

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
			retour = new ArrayList<Portfolio>((Collection<? extends Portfolio>) this.getHibernateTemplate().findByCriteria(detachedCriteriaAutoP));
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

		try {
			DetachedCriteria detachedCriteriaUserP = DetachedCriteria.forClass(UserPortfolio.class);
			detachedCriteriaUserP.addOrder(Order.asc("name"));
			retour.addAll(new ArrayList<Portfolio>((Collection<? extends Portfolio>) this.getHibernateTemplate().findByCriteria(detachedCriteriaUserP)));
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
			if (unknownShareListCache == null) {
				unknownShareListCache = (SharesList)this.getHibernateTemplate().get(SharesList.class, upperShareListName);
			}
			return unknownShareListCache;
		}

		SharesList shareList = (SharesList) this.getHibernateTemplate().get(SharesList.class, upperShareListName);

		if (shareList == null) shareList = new SharesList(upperShareListName);	
		return shareList;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<String> loadValidShareListNames() {
		List<String> dbgShareListes = new ArrayList<String>((Collection<? extends String>) this.getHibernateTemplate().find("select name from SharesList"));
		List<String> validShareLists = new ArrayList<>();
		for (String list : dbgShareListes) {
			try {
				SharesListId.valueOf(ProvidersList.shareListSplit(list)[0]);
				validShareLists.add(list);
			} catch (IllegalArgumentException e) {
				LOGGER.warn("Unrecognised list in the data base : " + list);
			}
		}
		return validShareLists;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<String> loadShareListNames(String[] include, String[] exclude) {
		DetachedCriteria detachedCriteria= DetachedCriteria.forClass(SharesList.class);
		detachedCriteria.setProjection(Projections.projectionList().add(Projections.property("name")));
		if (include != null && include.length > 0) detachedCriteria.add(Restrictions.in("name", include));	
		if (exclude != null && exclude.length > 0) detachedCriteria.add(Restrictions.not(Restrictions.in("name", exclude)));	
		return new ArrayList<String>((Collection<? extends String>) this.getHibernateTemplate().findByCriteria(detachedCriteria));
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<String> loadIndepShareListNames() {
		DetachedCriteria detachedCriteria= DetachedCriteria.forClass(IndepShareList.class);
		detachedCriteria.setProjection(Projections.projectionList().add(Projections.property("name")));	
		return new ArrayList<String>((Collection<? extends String>) this.getHibernateTemplate().findByCriteria(detachedCriteria));
	}



	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<String> loadUserPortfolioNames() {
		return new ArrayList<String>((Collection<? extends String>) this.getHibernateTemplate().find("select name from UserPortfolio"));
	}
	
	@Override
	@Transactional(readOnly=true)
	public AutoPortfolio loadAutoPortfolio(String autoPortfolioName) {
		AutoPortfolio autoPortfolio = (AutoPortfolio) this.getHibernateTemplate().get(AutoPortfolio.class, autoPortfolioName);
		return autoPortfolio;
	}


	public void deletePortfolioShare(PortfolioShare portfolioShare) {
		this.getHibernateTemplate().delete(portfolioShare);
	}


	public void saveOrUpdateTransactionReports(ArrayList<TransactionElement> reportElements) {
		//this.getHibernateTemplate().saveOrUpdateAll(reportElements);
		for (TransactionElement transactionElement : reportElements) {
			this.getHibernateTemplate().saveOrUpdate(transactionElement);
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public SortedSet<TransactionElement> loadOrphanTransactionReportFor(Stock stock, String externalAccount, Date date) {
		List<TransactionElement> trans = 
				new ArrayList<TransactionElement>((Collection<? extends TransactionElement>) this.getHibernateTemplate().find(
						"from TransactionElement where symbol = ? and isin = ? and externalAccount = ? and portfolio is null and date <= ? order by date", 
						stock.getSymbol(), stock.getIsin(), externalAccount, date));
		return new TreeSet<TransactionElement>(trans);
	}


	public void deleteALLTransactionReports() {
		this.getHibernateTemplate().deleteAll(this.getHibernateTemplate().loadAll(TransactionElement.class));

	}

	public void close() {
		if (unknownShareListCache != null) saveOrUpdatePortfolio(unknownShareListCache);
	}

	@Override
	public void deleteOrphanTransactionReportsFor(String externalAccount) {
		DetachedCriteria detachedCriteria= DetachedCriteria.forClass(TransactionElement.class);
		detachedCriteria.add(Restrictions.eq("externalAccount", externalAccount));	
		detachedCriteria.add(Restrictions.isNull("portfolio"));	
		@SuppressWarnings("unchecked")
		List<TransactionElement> accountTransactions = (List<TransactionElement>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		this.getHibernateTemplate().deleteAll(accountTransactions);

	}

	@Override
	public IndepShareList loadIndepShareList(String shareListName) {
		String upperShareListName = shareListName.toUpperCase();
		IndepShareList shareList = (IndepShareList)this.getHibernateTemplate().get(IndepShareList.class, upperShareListName);
		if (shareList == null) shareList = new IndepShareList(upperShareListName);
		return shareList;
	}

	@Override
	public Set<PortfolioShare> loadIndicesSharesListContentExUnknown() {
		List<String> shareListNames = loadShareListNames(null, new String[]{SharesListId.UNKNOWN.getSharesListCmdParam().toUpperCase()});
		Set<PortfolioShare> marketStock = new HashSet<PortfolioShare>();
		for (String listName : shareListNames) {
			Set<Indice> indices = Indice.parseString(listName);
			if (indices.size() == 1 ) {
				LOGGER.info("Adding share list: " + listName);
				SharesList shareList = loadShareList(listName);
				marketStock.addAll(shareList.getListShares().values());
			}
		}
		return marketStock;
	}
	
	@Override
	public Set<PortfolioShare> loadSharesListContent(String[] include, String[] exclude) {
		List<String> shareListNames = loadShareListNames(include, exclude);
		Set<PortfolioShare> marketStock = new HashSet<PortfolioShare>();
		for (String listName : shareListNames) {
			LOGGER.info("Adding share list: " + listName);
			SharesList shareList = loadShareList(listName);
			marketStock.addAll(shareList.getListShares().values());
		}
		return marketStock;
	}


}
