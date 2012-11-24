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
package com.finance.pms;

import java.util.HashMap;
import java.util.Map;

import com.finance.pms.admin.config.Config;

public class ConfigThreadLocal {

    private static ThreadLocal<Map<String,Config>> configs = new ThreadLocal<Map<String,Config>>() {

		@Override
		protected Map<String, Config> initialValue() {
			return new HashMap<String, Config>();
		}
    };
    
   
    public static Config get(String configName)  {
    	if (null != configs.get().get(configName)) return configs.get().get(configName);
        throw new IllegalArgumentException("The config name has not been initilized : "+configName);
    }
    
	public static Map<String, Config> get(String configName, String ...configNames) {
		Map<String, Config> configs = new HashMap<String, Config>();
		configs.put(configName, ConfigThreadLocal.get(configName));
		for (String optConfigName : configNames) {
			configs.put(optConfigName, ConfigThreadLocal.get(optConfigName));
		}
		return configs;
	}
	
    
    public static Map<String,Config>  getAll() {
    	return configs.get();
    }
	
    
    public static void set(String configName, Config config) {
    	configs.get().put(configName, config);
    }

}
