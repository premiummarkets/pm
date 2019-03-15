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
package com.finance.pms.datasources.events;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.AnalysisClient;
import com.finance.pms.events.scoring.TunedConf;
import com.finance.pms.events.scoring.TunedConfId;

@Repository("TunedConfDAO")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class, value="hibernateTx")
public class TunedConfDAOImpl extends HibernateDaoSupport implements TunedConfDAO {

	private static MyLogger LOGGER = MyLogger.getLogger(TunedConfDAOImpl.class);

	@Override
	@Transactional(readOnly=true)
	public TunedConf loadTunedConf(TunedConfId tunedConfId) {
		return this.getHibernateTemplate().get(TunedConf.class, tunedConfId);
	}

	@Override
	public void saveOrUpdateTunedConfs(TunedConf tunedConf) {

		//ANY_STOCK has got no tuned conf and is not a stock
		if (tunedConf.getTunedConfId().getStock().equals(AnalysisClient.ANY_STOCK)) return;

		try {
			this.getHibernateTemplate().saveOrUpdate(tunedConf);
		} catch (DataAccessException e) {
			LOGGER.error("Is " + tunedConf.getTunedConfId().getStock() + " a real SHARE? : " + e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<TunedConf> loadAllTunedConfs() {
		return this.getHibernateTemplate().loadAll(TunedConf.class);
	}

	@Autowired
	public void init(SessionFactory factory) {
		setSessionFactory(factory);
	}
}
