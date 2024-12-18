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
package com.finance.pms.portfolio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.Date;

import com.finance.pms.events.calculation.DateFactory;

public class AutoPortfolioAnalyser {
	
	AutoPortfolioWays portfolioWays;

	public AutoPortfolioAnalyser(AutoPortfolioWays portfolioWays) throws FileNotFoundException {
		super();
		this.portfolioWays = portfolioWays;
	}

	public String message()  throws Exception {
	
		try {
			
			File transactions = new File(System.getProperty("installdir") + File.separator + "autoPortfolioLogs" + File.separator + portfolioWays.getName()+"_TransactionsExport.csv");
			Date nowEndDate = DateFactory.getNowEndDate();
			
			String extractTransactionLog = portfolioWays.extractPortfolioTransactionLog(DateFactory.dateAtZero(), nowEndDate, false);
			FileWriter fileWriter = new FileWriter(transactions);
			fileWriter.write(extractTransactionLog);
		
			fileWriter.close();
			
			String msg = "file:///"+transactions.getPath()+"\n";
			
			msg = msg + "Totals : \n";
			try {
				
				BigDecimal value = ((Portfolio) portfolioWays).getValue(null, nowEndDate, false);
				BigDecimal gainTotal = ((Portfolio) portfolioWays).getGainTotal(null, nowEndDate, false);
				BigDecimal gainTotalPercent = ((Portfolio) portfolioWays).getGainTotalPercent(null, nowEndDate, false);
				BigDecimal gainUnRealPercent = ((Portfolio) portfolioWays).getGainRemaingPotential(null, nowEndDate);
				BigDecimal totalInAmountEver = ((Portfolio) portfolioWays).getCashIn(null, nowEndDate, portfolioWays.inferPortfolioCurrency(), false, false);
				BigDecimal totalOutAmountEver = ((Portfolio) portfolioWays).getCashOut(null, nowEndDate, portfolioWays.inferPortfolioCurrency(), false, false);
				
				msg = msg + "Value : "+ value + "\n";
				msg = msg +	"GainTotal : " + gainTotal + "\n";
				msg = msg + "GainTotalPercent : " + gainTotalPercent + ", GainUnRealPercent : " + gainUnRealPercent + "\n";
				msg = msg + "TotalInAmountEver : " + totalInAmountEver + ", TotalOutAmountEver : " + totalOutAmountEver + "\n" ;
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return  msg;
			
		} catch (Throwable e) {
			return e.toString();
		}
	
	}

}
