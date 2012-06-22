/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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
package com.finance.pms.threads;

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
