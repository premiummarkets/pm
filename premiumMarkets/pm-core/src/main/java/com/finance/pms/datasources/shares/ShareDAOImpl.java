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
package com.finance.pms.datasources.shares;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.portfolio.MonitorLevel;
import com.finance.pms.portfolio.Portfolio;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.UserPortfolio;
import com.finance.pms.screening.TrendSupplementedStock;

public class ShareDAOImpl extends HibernateDaoSupport implements ShareDAO {
	
	
	@Override
	public void saveOrUpdateStocks(List<Validatable> shares) {
		this.getHibernateTemplate().saveOrUpdateAll(shares);
	}

	@SuppressWarnings("unchecked")
	
	public List<Stock> loadShares(ShareFilter shareFilter) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Stock.class);
		if (shareFilter.getCategories().length > 0) {
			criteria.add(Restrictions.in("category",shareFilter.getCategories()));
		}
		if (shareFilter != null && shareFilter.getRequestConstraint() != null) {
			criteria.add(Restrictions.sqlRestriction(shareFilter.getRequestConstraint()));
		}
		criteria.add(Restrictions.conjunction());
	
		List<Stock> list =  this.getHibernateTemplate().findByCriteria(criteria);
		
		return list;
	}
	
	
	
	public Stock loadStockByIsin(final String isin) {

		return this.getHibernateTemplate().execute(new HibernateCallback<Stock>() {
			
			public Stock doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(Stock.class);
				criteria.add(Restrictions.eq("isin", isin));
				return (Stock)criteria.uniqueResult();
			}

		});	
	}
	
	
	public List<Stock> loadAllStocks() {
		return this.getHibernateTemplate().loadAll(Stock.class);
	}

	@SuppressWarnings("unchecked")
	
	public List<Stock> loadMonitoredStocks() {
		
		return (List<Stock>) this.getHibernateTemplate().execute(new HibernateCallback<List<Stock>>() {

			
			public List<Stock> doInHibernate(Session session) throws HibernateException, SQLException {
				
				Query query = session.createQuery("select distinct stock from PortfolioShare as portfolioShare where portfolioShare.monitorLevel <> :monitorLevel");
				query.setParameter("monitorLevel", MonitorLevel.NONE);
				return query.list();
			}

		});
	}
	
	@SuppressWarnings("unchecked")
	public List<PortfolioShare> loadMonitoredPortfolioShares() {
			return this.getHibernateTemplate().find("from PortfolioShare as portfolioShare where portfolioShare.monitorLevel <> ?", MonitorLevel.NONE);
	}
	
	
	public Stock loadStockBy(final String symbol,final String isin) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Stock>() {
			
			public Stock doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(Stock.class);
				criteria.add(Restrictions.and(Restrictions.eq("isin", isin),Restrictions.eq("symbol", symbol)));
				return (Stock) criteria.uniqueResult();
			}
		});
	}

	
	public Stock loadStockByIsinOrSymbol(final String ref) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Stock>() {
			
			public Stock doInHibernate(Session session) throws HibernateException, SQLException {
				String[] refSplit = ref.split("\\.");
				String symbolRoot = (refSplit.length == 0)?ref:refSplit[0]+".%";
				Criteria criteria = session.createCriteria(Stock.class);
				criteria.add(Restrictions.or(Restrictions.eq("isin", ref),Restrictions.or(Restrictions.eq("symbol", ref), Restrictions.like("symbol", symbolRoot))));
				return (Stock) criteria.uniqueResult();
			}

		});
	}

	
	public void saveOrUpdateStockTrendInfo(Set<TrendSupplementedStock> listTrend) {
		this.getHibernateTemplate().saveOrUpdateAll(listTrend);
	}
	
	
	public void saveOrUpdateStock(Stock stock) {
		this.getHibernateTemplate().saveOrUpdate(stock);
	}
	
	

	
	public TrendSupplementedStock loadTrendForStock(Stock stock) {
		
		org.hibernate.classic.Session currentSession = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = currentSession.createCriteria(TrendSupplementedStock.class);
		criteria.add(Restrictions.eq("stock", stock));
		criteria.addOrder(Order.desc("trendDate"));
		criteria.setMaxResults(1);
		
		@SuppressWarnings("unchecked")
		List<TrendSupplementedStock> results = criteria.list();
		
		return (results == null || results.isEmpty() || results.get(0) == null)? null : results.get(0);
				
	}

	
	public void saveOrUpdateStocks(Set<Stock> listStocks) {
		this.getHibernateTemplate().saveOrUpdateAll(listStocks);
		
	}
	
	@Override
	public Collection<Stock> loadAllUserPortoflioStocks() {
		Collection<Stock> stocks = new HashSet<Stock>();
		List<UserPortfolio> userPortfolios = this.getHibernateTemplate().loadAll(UserPortfolio.class);
		for (UserPortfolio userPortfolio : userPortfolios) {
			for (PortfolioShare portfolioShare : userPortfolio.getListShares().values()) {
				stocks.add(portfolioShare.getStock());
			}
		}
		return stocks;
	}

	
	public Collection<Stock> loadAllPortoflioStocks() {
		Collection<Stock> stocks = new HashSet<Stock>();
		List<Portfolio> userPortfolios = this.getHibernateTemplate().loadAll(Portfolio.class);
		for (Portfolio userPortfolio : userPortfolios) {
			for (PortfolioShare portfolioShare : userPortfolio.getListShares().values()) {
				stocks.add(portfolioShare.getStock());
			}
		}
		return stocks;
	}

	@Override
	public List<String> sectorHintList() {
		Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria( Stock.class );
		criteria.setProjection(Projections.distinct(Projections.property( "sectorHint" )));
		@SuppressWarnings("unchecked")
		List<String> results = criteria.list();
		
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Stock> loadSharesLike(String like, int maxResults) {
		
//		HibernateTemplate hibernateTemplate = new HibernateTemplate(getSessionFactory());
//		hibernateTemplate.setMaxResults(maxResults);
//		
//		return hibernateTemplate.find(
//				"from Stock as stock where " +
//				" stock.symbol like ? or stock.isin like ? or stock.name like ? " +
//				" order by symbol, isin, name", like+"%", like+"%", like+"%");
		
		Criteria crit = getSession().createCriteria(Stock.class);
		crit.setMaxResults(maxResults);
		crit.add(Restrictions.or(Restrictions.or(Restrictions.ilike("symbol", like+"%"), Restrictions.ilike("isin", like+"%")),Restrictions.ilike("name", like+"%")));
		crit.addOrder(Property.forName("symbol").asc()).addOrder(Property.forName("name").asc()).addOrder(Property.forName("isin").asc());
		
		return crit.list();
		
	}

}
