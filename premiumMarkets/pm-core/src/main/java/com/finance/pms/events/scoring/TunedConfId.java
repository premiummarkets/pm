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
package com.finance.pms.events.scoring;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.finance.pms.datasources.shares.Stock;

@Embeddable
public class TunedConfId implements Serializable {

    private static final long serialVersionUID = 4744300587549030997L;


    Stock stock;
    String configFile;
    String eventDefinition;

    @SuppressWarnings("unused")
    private TunedConfId() {
        super();
        // Hib
    }

    public TunedConfId(Stock stock, String configFile, String eventDefinition) {
        super();
        this.stock = stock;
        this.configFile = configFile;
        this.eventDefinition = eventDefinition;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((configFile == null) ? 0 : configFile.hashCode());
        result = prime * result + ((eventDefinition == null) ? 0 : eventDefinition.hashCode());
        result = prime * result + ((stock == null) ? 0 : stock.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TunedConfId other = (TunedConfId) obj;
        if (configFile == null) {
            if (other.configFile != null)
                return false;
        } else if (!configFile.equals(other.configFile))
            return false;
        if (eventDefinition == null) {
            if (other.eventDefinition != null)
                return false;
        } else if (!eventDefinition.equals(other.eventDefinition))
            return false;
        if (stock == null) {
            if (other.stock != null)
                return false;
        } else if (!stock.equals(other.stock))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TunedConfId [stock=" + stock + ", configFile=" + configFile + ", eventDefinition=" + eventDefinition + "]";
    }

    @ManyToOne
    @JoinColumns( { 
        @JoinColumn(name = "isin", referencedColumnName = "isin", insertable = false, updatable = false, nullable = false),
        @JoinColumn(name = "symbol", referencedColumnName = "symbol", insertable = false, updatable = false, nullable = false) 
    })
    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public String getEventDefinition() {
        return eventDefinition;
    }

    public void setEventDefinition(String eventDefinition) {
        this.eventDefinition = eventDefinition;
    }

}
