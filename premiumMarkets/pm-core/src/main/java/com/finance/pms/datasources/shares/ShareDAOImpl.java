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
package com.finance.pms.datasources.shares;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.portfolio.MonitorLevel;
import com.finance.pms.portfolio.Portfolio;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.UserPortfolio;
import com.finance.pms.screening.ScreeningSupplementedStock;

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class, value="hibernateTx")
public class ShareDAOImpl extends HibernateDaoSupport implements ShareDAO {


	@Override
	public void saveOrUpdateStocks(List<Validatable> shares) {
		//this.getHibernateTemplate().saveOrUpdateAll(shares);
		for (Validatable share : shares) {
			this.getHibernateTemplate().saveOrUpdate(share);
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Stock> loadShares(ShareFilter shareFilter) {

		DetachedCriteria criteria = DetachedCriteria.forClass(Stock.class);
		if (shareFilter.getCategories().length > 0) {
			criteria.add(Restrictions.in("category",shareFilter.getCategories()));
		}
		if (shareFilter != null && shareFilter.getRequestConstraint() != null) {
			criteria.add(Restrictions.sqlRestriction(shareFilter.getRequestConstraint()));
		}
		criteria.add(Restrictions.conjunction());

		List<Stock> list =  new ArrayList<Stock>((Collection<? extends Stock>) this.getHibernateTemplate().findByCriteria(criteria));

		return list;
	}

	@Transactional(readOnly=true)
	public List<Stock> loadAllStocks() {
		return this.getHibernateTemplate().loadAll(Stock.class);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Stock> loadMonitoredStocks() {

		return this.getHibernateTemplate().execute(new HibernateCallback<List<Stock>>() {

			public List<Stock> doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery("select distinct stock from PortfolioShare as portfolioShare where portfolioShare.monitorLevel <> :monitorLevel");
				query.setParameter("monitorLevel", MonitorLevel.NONE);
				return query.list();
			}

		});
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Stock> loadMonitoredUserPortoflioStocks() {
		Collection<Stock> stocks = new HashSet<Stock>();
		List<UserPortfolio> userPortfolios = this.getHibernateTemplate().loadAll(UserPortfolio.class);
		for (UserPortfolio userPortfolio : userPortfolios) {
			for (PortfolioShare portfolioShare : userPortfolio.getListShares().values()) {
				if (!portfolioShare.getMonitorLevel().equals(MonitorLevel.NONE)) stocks.add(portfolioShare.getStock());
			}
		}
		return stocks;
	}

	@Transactional(readOnly=true)
	public Stock loadStockBy(final String symbol,final String isin) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Stock>() {

			public Stock doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(Stock.class);
				criteria.add(Restrictions.and(Restrictions.eq("isin", isin),Restrictions.eq("symbol", symbol)));
				return (Stock) criteria.uniqueResult();
			}
		});
	}

	@Transactional(readOnly=true)
	public List<Stock> loadStockByIsinOrSymbol(final String ref) {

		return this.getHibernateTemplate().execute(new HibernateCallback<List<Stock>>() {

			@SuppressWarnings("unchecked")
			public List<Stock> doInHibernate(Session session) throws HibernateException, SQLException {

				Criteria exactCriteria = session.createCriteria(Stock.class);
				exactCriteria.add(Restrictions.eq("symbol", ref));
				List<Stock> stocks = exactCriteria.list();

				if (stocks.isEmpty()) {
					
					String[] refSplit = ref.split("\\.");
					String symbolRoot = (refSplit.length == 0)?ref:refSplit[0]+".%";
					
					Criteria sndChanceCriteria = session.createCriteria(Stock.class);
					sndChanceCriteria.add(Restrictions.or(Restrictions.like("symbol", symbolRoot), Restrictions.eq("isin", ref)));
					stocks = sndChanceCriteria.list();
				}

				return stocks;

			}

		});
	}


	public void saveOrUpdateStockTrendInfo(Set<ScreeningSupplementedStock> trends) {
		//this.getHibernateTemplate().saveOrUpdateAll(listTrend);
		for (ScreeningSupplementedStock trend : trends) {
			this.getHibernateTemplate().saveOrUpdate(trend);
		}
	}


	public void saveOrUpdateStock(Stock stock) {
		this.getHibernateTemplate().saveOrUpdate(stock);
	}


	@Transactional(readOnly=true)
	public ScreeningSupplementedStock loadTrendForStock(Stock stock) {

		org.hibernate.classic.Session currentSession = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = currentSession.createCriteria(ScreeningSupplementedStock.class);
		criteria.add(Restrictions.eq("stock", stock));
		criteria.addOrder(Order.desc("trendDate"));
		criteria.setMaxResults(1);

		@SuppressWarnings("unchecked")
		List<ScreeningSupplementedStock> results = criteria.list();

		return (results == null || results.isEmpty() || results.get(0) == null)? null : results.get(0);

	}


	public void saveOrUpdateStocks(Set<Stock> listStocks) {
		//getHibernateTemplate().saveOrUpdateAll(listStocks);
		for (Stock stock : listStocks) {
			getHibernateTemplate().saveOrUpdate(stock);
		}
	}

	@Override
	@Transactional(readOnly=true)
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

	@Transactional(readOnly=true)
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
	@Transactional(readOnly=true)
	public List<String> sectorHintList() {
		Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria( Stock.class );
		criteria.setProjection(Projections.distinct(Projections.property( "sectorHint" )));
		@SuppressWarnings("unchecked")
		List<String> results = criteria.list();

		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Stock> loadSharesLike(String like, int maxResults) {

		Criteria crit = getSession().createCriteria(Stock.class);
		crit.setMaxResults(maxResults);
		crit.add(Restrictions.or(Restrictions.or(Restrictions.ilike("symbol", like+"%"), Restrictions.ilike("isin", like+"%")), Restrictions.ilike("name", like+"%")));
		crit.addOrder(Property.forName("symbol").asc()).addOrder(Property.forName("name").asc()).addOrder(Property.forName("isin").asc());

		return crit.list();

	}

	@Override
	public void saveOrUpdateQuotationUnit(QuotationUnit quotationUnit) {
		if (quotationUnit.getSplit().compareTo(BigDecimal.ONE) != 0) throw new NotImplementedException("Can't save quotation unit with non neutral split: " + quotationUnit);
		getHibernateTemplate().saveOrUpdate(quotationUnit);
	}

	@Override
	public void saveOrUpdateQuotationUnits(List<QuotationUnit> quotationUnits) {
		//getHibernateTemplate().saveOrUpdateAll(quotationUnits);
		for (QuotationUnit quotationUnit : quotationUnits) {
			//FIXME ..
			if (quotationUnit.getSplit().compareTo(BigDecimal.ONE) != 0) throw new NotImplementedException("Can't save quotation unit with non neutral split: " + quotationUnit);
			getHibernateTemplate().saveOrUpdate(quotationUnit);
		}
	}

	@Override
	public void deleteQuotationUnits(List<QuotationUnit> quotationUnits) {
		getHibernateTemplate().deleteAll(quotationUnits);
	}

}
