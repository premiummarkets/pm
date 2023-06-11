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
package com.finance.pms.threads;

import java.util.HashMap;
import java.util.Map;

import com.finance.pms.admin.config.Config;

public class ConfigThreadLocal {
	
    private static ThreadLocal<Map<String, Config>> threadLocalConfigs = new ThreadLocal<Map<String,Config>>() {
		@Override
		protected Map<String, Config> initialValue() {
			return new HashMap<String, Config>();
		}
    };
    
   
    public static Config get(String configName)  {
    	Config config = threadLocalConfigs.get().get(configName);
		if (null != config) {
    		return config;
    	}
        throw new IllegalArgumentException("The config name has not been initialised: " + configName);
    }
    
	public static Map<String, Config> get(String configName, String ...configNames) {
		Map<String, Config> configs = new HashMap<String, Config>();
		Config config = ConfigThreadLocal.get(configName);
		configs.put(configName, config);
		for (String optConfigName : configNames) {
			configs.put(optConfigName, ConfigThreadLocal.get(optConfigName));
		}
		return configs;
	}
	
    //TODO getAll strict?
    public static Map<String,Config> getAll() {
    	return threadLocalConfigs.get();
    }
	
    public static void set(String configName, Config config) {
//    	String myLoggerThreadLocal = config.getMyLoggerThreadLocal();
//    	if (myLoggerThreadLocal != null) MyLogger.threadLocal.set(myLoggerThreadLocal);
    	threadLocalConfigs.get().put(configName, config);
    }
    
    public static void setAll(Map<String, Config> configs) {
    	for (String configKey : configs.keySet()) {
    		Config config = configs.get(configKey);	
			set(configKey,config);
		}
    }

}
