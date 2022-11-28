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
package com.finance.pms.events;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.portfolio.AbstractSharesList;
import com.finance.pms.portfolio.AutoPortfolioWays;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.queue.ExportAutoPortfolioMessage;

public class ExportAutoPortfolioRunnable implements Runnable {
	
	
	protected static MyLogger LOGGER = MyLogger.getLogger(ExportAutoPortfolioMessage.class);
	
	ExportAutoPortfolioMessage message;

	
	public ExportAutoPortfolioRunnable(ExportAutoPortfolioMessage autoPortfolioMessage) {
		super();
		this.message = autoPortfolioMessage;
	}


	public void run() {

			try {
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Exporting "+message);
				
				AbstractSharesList autoPortfolio =  PortfolioMgr.getInstance().getPortfolio(message.getAnalyseName());
				
				((AutoPortfolioWays) autoPortfolio).exportAutoportfolioContent(message.getDatefin());
				
				LOGGER.info("Exporting message completed : "+message.getAnalyseName());
			} catch (Exception e) {
				LOGGER.error("Error in "+this.toString(),e);
			}

		
	}
	
	@Override
	public String toString() {
		return this.getClass().getName()+" doing "+this.message.getAnalyseName();
	}
}
