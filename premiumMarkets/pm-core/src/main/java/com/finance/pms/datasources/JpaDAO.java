/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
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
package com.finance.pms.datasources;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

public abstract class JpaDAO<K, E> extends JpaDaoSupport { 
	
    protected Class<E> entityClass; 

    @SuppressWarnings("unchecked") 
    public JpaDAO() { 
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass(); 
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1]; 
    } 

    public void persist(E entity) { 
        getJpaTemplate().persist(entity); 
    } 

    public void remove(E entity) { 
        getJpaTemplate().remove(entity); 
    } 
    
    public E merge(E entity) { 
        return getJpaTemplate().merge(entity); 
    } 
    
    public void refresh(E entity) { 
        getJpaTemplate().refresh(entity); 
    } 

    public E findById(K id) { 
        return getJpaTemplate().find(entityClass, id); 
    } 
    
    public E flush(E entity) { 
        getJpaTemplate().flush(); 
        return entity; 
    } 
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<E> findAll() { 
        Object res = getJpaTemplate().execute(new JpaCallback() { 

            public Object doInJpa(EntityManager em) throws PersistenceException { 
                Query q = em.createQuery("SELECT h FROM " + entityClass.getName() + " h"); 
                return q.getResultList(); 
            } 
            
        }); 
        
        return (List<E>) res; 
    } 

    @SuppressWarnings({ "unchecked", "rawtypes" }) 
    public Integer removeAll() { 
        return (Integer) getJpaTemplate().execute(new JpaCallback() { 

            public Object doInJpa(EntityManager em) throws PersistenceException { 
                Query q = em.createQuery("DELETE FROM " + entityClass.getName() + " h"); 
                return q.executeUpdate(); 
            } 
            
        }); 
    }
    
}

