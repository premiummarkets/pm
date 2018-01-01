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

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
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
        this.getHibernateTemplate().saveOrUpdate(tunedConf);
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

    @Override
    public void deleteTunedConfFor(String analysisName, EventInfo... indicators) {
        if (indicators == null || indicators.length == 0) {
            LOGGER.info("deleteTunedConfFor : "+analysisName);
            this.getHibernateTemplate().execute(new HibernateCallback<Void>() {

                @Override
                public Void doInHibernate(Session session) throws HibernateException, SQLException {
                    int nbDeleted = session.createQuery("delete from TunedConf where configFile=:configFile")
                            .setString("configFile", analysisName)
                            .executeUpdate();
                    LOGGER.info("deleteTunedConfFor : deleted "+nbDeleted+" TunedConfs with "+analysisName);
                    return null;
                }

            });
        } else {
            List<String> eventInfoDefs = Arrays.stream(indicators).map(ei -> ei.getEventDefinitionRef()).collect(Collectors.toList());
            LOGGER.info("deleteTunedConfFor : "+analysisName+" and "+eventInfoDefs);
            this.getHibernateTemplate().execute(new HibernateCallback<Void>() {

                @Override
                public Void doInHibernate(Session session) throws HibernateException, SQLException {
                    int nbDeleted = session.createQuery("delete from TunedConf where configFile=:configFile and eventDefinition in (:eventDefinitions)")
                            .setString("configFile", analysisName)
                            .setParameterList("eventDefinitions", eventInfoDefs)
                            .executeUpdate();
                    LOGGER.info("deleteTunedConfFor : deleted "+nbDeleted+" TunedConfs with "+analysisName+" and "+eventInfoDefs);
                    return null;
                }

            });
        }
    }

    @Override
    public void deleteTunedConfFor(Stock stock, String analysisName, EventInfo... indicators) {
        if (indicators == null || indicators.length == 0) {
            LOGGER.info("deleteTunedConfFor : "+stock+" and "+analysisName);
            this.getHibernateTemplate().execute(new HibernateCallback<Void>() {

                @Override
                public Void doInHibernate(Session session) throws HibernateException, SQLException {
                    int nbDeleted = session.createQuery("delete from TunedConf as tc where tc.tunedConfId.stock=:stock and tc.tunedConfId.configFile=:configFile")
                            .setParameter("stock", stock)
                            .setString("configFile", analysisName)
                            .executeUpdate();
                    LOGGER.info("deleteTunedConfFor : deleted "+nbDeleted+" TunedConfs with "+stock+" and "+analysisName);
                    return null;
                }

            });
        } else {
            List<String> eventInfoDefs = Arrays.stream(indicators).map(ei -> ei.getEventDefinitionRef()).collect(Collectors.toList());
            LOGGER.info("deleteTunedConfFor : "+stock+" and "+analysisName+" and "+eventInfoDefs);
            this.getHibernateTemplate().execute(new HibernateCallback<Void>() {

                @Override
                public Void doInHibernate(Session session) throws HibernateException, SQLException {
                    int nbDeleted = session.createQuery("delete from TunedConf as tc where tc.tunedConfId.stock=:stock and tc.tunedConfId.configFile=:configFile and tc.tunedConfId.eventDefinition in (:eventDefinitions)")
                            .setParameter("stock", stock)
                            .setString("configFile", analysisName)
                            .setParameterList("eventDefinitions", eventInfoDefs)
                            .executeUpdate();
                    LOGGER.info("deleteTunedConfFor : deleted "+nbDeleted+" TunedConfs with "+stock+" and "+analysisName+" and "+eventInfoDefs);
                    return null;
                }

            });
        }
    }
}
