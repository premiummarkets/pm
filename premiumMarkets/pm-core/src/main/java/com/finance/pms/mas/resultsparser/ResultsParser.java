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
package com.finance.pms.mas.resultsparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import com.finance.pms.admin.install.logging.MyLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class ResultsParser.
 * 
 * @author Guillaume Thoreton
 */
public class ResultsParser {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(ResultsParser.class);
	
	/**
	 * Result parser.
	 * 
	 * @param r the r
	 * @param rf the rf
	 * 
	 * @return the map<? extends object,? extends object>
	 * 
	 * @author Guillaume Thoreton
	 */
	public static Map<? extends Object,? extends Object> resultParser(String r, ResultFormat rf){
		Map<Object,Object> retour = new HashMap<Object,Object>();
		BufferedReader reader = new BufferedReader(new StringReader(r));
		String line;
		Result res;
		
		try {
			for(;(line = reader.readLine()) != null;){
				res = rf.parse(line);
				retour.put(res.getKey(),res.getValue());
			}
		} catch (IOException e) {
			LOGGER.error("Error parsing Result :"+r,e);
		}
		return retour;
	}

}
