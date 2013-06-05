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
package com.finance.pms.datasources.events;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.scoring.TunedConf;
import com.finance.pms.events.scoring.TunedConfId;

@Repository("TunedConfDAO")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class, value="hibernateTx")
public class TunedConfDAOImpl extends HibernateDaoSupport implements TunedConfDAO {
	
	@Override
	@Transactional(readOnly=true)
	public TunedConf loadTunedConf(TunedConfId tunedConfId) {
		return this.getHibernateTemplate().get(TunedConf.class, tunedConfId);
	}

	@Override
	public void saveOrUpdateTunedConfs(TunedConf tunedConf) {
		this.getHibernateTemplate().saveOrUpdate(tunedConf);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<TunedConf> loadAllTunedConfs() {
		return this.getHibernateTemplate().loadAll(TunedConf.class);
	}

	@Override
	public void resetTunedConfs() {
		List<TunedConf> loadAll = this.getHibernateTemplate().loadAll(TunedConf.class);
		this.getHibernateTemplate().deleteAll(loadAll);
	}
	
	//TODO cfgfile should be passed as it is in the key
	@Override
	public void resetTunedConfsFor(final Stock stock) {
		this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				Query createQuery = session.createQuery("delete from TunedConf where symbol = :symbol and isin = :isin");
				createQuery.setString("symbol", stock.getSymbol());
				createQuery.setString("isin", stock.getIsin());
				return createQuery.executeUpdate();
				
			}
		});
	}

	
	@Autowired
	public void init(SessionFactory factory) {
	    setSessionFactory(factory);
	}
}
