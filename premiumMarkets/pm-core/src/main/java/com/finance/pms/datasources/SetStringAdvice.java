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
package com.finance.pms.datasources;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SetStringAdvice implements MethodInterceptor {
	/**
	 * Invoke interceptor
	 */
	public Object invoke(MethodInvocation i) throws Throwable {
		// loop by all arguments
		for (int n = 0; n < i.getArguments().length; n++) {
			// get argument object
			Object argument = i.getArguments()[n];
			try {
//				// check if either we have a String as input or null
//				if (argument instanceof String || argument == null) {
//					// replace string null arguments
//					if (argument == null) {
//						// check if required argument is a string
//						if (i.getMethod().getParameterTypes()[n].isInstance(new String())) {
//							// replace null strings by empty String
//							i.getArguments()[n] = "";
//						}
//					} else {
//						// trim string arguments
//						i.getArguments()[n] = ((String) (argument)).trim();
//					}
//				}
				if (argument instanceof String && argument != null) {
					i.getArguments()[n] = ((String) (argument)).trim();
				}
			} finally {
				// finally clean up unused argument for gc
				argument = null;
			}
		}
		// pass arguments to methods
		return i.proceed();
	}
}
