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
package com.finance.pms.events.scoring;

import java.security.InvalidAlgorithmParameterException;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.events.OnTheFlyRevesreCalcPeriod;
import com.finance.pms.datasources.events.TunedConfDAO;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.quotations.QuotationsFactories;

//TODO 
// Add analysis name in tunedConf in order to tell apart the first pass events
// Or make first pass event independent of analysis name?
@Service("tunedConfMgr")
public class TunedConfMgr {
	
	private static MyLogger LOGGER = MyLogger.getLogger(TunedConfMgr.class);
	
	public enum CalcStatus {NONE,INC,RESET,IGNORE};
	
	public class CalculationBounds {
		
		CalcStatus calcStatus;
		Date pmStart;
		Date pmEnd;
		
		public CalculationBounds(CalcStatus calcStatus, Date pmStart, Date pmEnd) {
			super();
			this.calcStatus = calcStatus;
			this.pmStart = pmStart;
			this.pmEnd = pmEnd;
		}

		public CalcStatus getCalcStatus() {
			return calcStatus;
		}

		public Date getPmStart() {
			return pmStart;
		}

		public Date getPmEnd() {
			return pmEnd;
		}

		@Override
		public String toString() {
			return "CalculationBounds [calcStatus=" + calcStatus + ", pmStart=" + pmStart + ", pmEnd=" + pmEnd + "]";
		}

	}
	
	@Autowired
	TunedConfDAO tunedConfDAO;
	
	public static TunedConfMgr getInstance() {
		//TODO a different bean may have to be used for intraday fixing => factory as per the quotation one
		return SpringContext.getSingleton().getBean(TunedConfMgr.class);
	}
	
	public TunedConfMgr() {
	}
	
	public synchronized TunedConf loadUniqueNoRetuneConfig(Stock stock, String configListFileName) {
		
		TunedConfId tunedConfId = new TunedConfId(stock, configListFileName);
		TunedConf stockPrevTunedConf = getTunedConfDAO().loadTunedConf(tunedConfId);
		if (stockPrevTunedConf == null) { //No prev conf : first calculation for this config file
			stockPrevTunedConf = new TunedConf(stock, configListFileName);
			getTunedConfDAO().saveOrUpdateTunedConfs(stockPrevTunedConf);
		}
		
		return stockPrevTunedConf;
	}
	
	public Date endDateConsistencyCheck(TunedConf tunedConf, Stock stock, Date endDate) throws InvalidAlgorithmParameterException {
		
		//Inconsistency check
		if (tunedConf.getLastCalculatedEvent() != null && tunedConf.getLastCalculatedEvent().after(stock.getLastQuote())) {
			throw new InvalidAlgorithmParameterException("Data inconsistency detected for stock : "+stock+". last quote is : "+stock.getLastQuote()+ " and last event calculated is : "+tunedConf.getLastCalculatedEvent());
		}
		//Check end date within quotations
		boolean newEndWithinQuotations = endDate.before(stock.getLastQuote()) || endDate.compareTo(stock.getLastQuote()) == 0;
		if (!newEndWithinQuotations) {
			endDate = stock.getLastQuote();
		}
		return endDate;
		
	}
	
	public CalculationBounds autoCalcAndSetDatesBounds(TunedConf tunedConf, Stock stock, Date startDate, Date endDate) {
		
		LOGGER.info(
				stock.getSymbol() + " TunedConf before calculating bounds from "+tunedConf.getLastCalculationStart()+" to "+tunedConf.getLastCalculationEnd()+". Last event "+ tunedConf.getLastCalculatedEvent() +
				"Requested calculation is from "+startDate+" to "+endDate+". ");

		//Setting PM events calculation date range
		//We want the calculation from the start date onward but we don't want to recalculate already calculated PM events
		//We don't want to update calculation after the last PM event.
		CalcStatus calcStatus = null;

		//What is the start date for this calculation
		Date pmEvtsCalcStart = null;
		String infoMsg = "";
		if (!startDate.before(tunedConf.getLastCalculationStart()) && !startDate.after(tunedConf.getLastCalculationEnd())) {//start is included into the last calculation
			//inc calc, tuned config start stays the same
			pmEvtsCalcStart = (tunedConf.getLastCalculatedEvent() != null)?tunedConf.getLastCalculatedEvent():startDate;
			calcStatus = CalcStatus.INC;
			infoMsg = "New dates for "+stock+" starts within the previous calc : we do INCREMENTAL calc. ";

		} else {//start outside the last calculation
			//recalc from new date, reset tuned start
			pmEvtsCalcStart = startDate;
			tunedConf.setLastCalculationStart(pmEvtsCalcStart);
			calcStatus = CalcStatus.RESET;
			infoMsg = "New dates for "+stock+" DO NOT starts within the previous calc : we RESET the calculation dates. " ;

		}

		//What is the end date for this calculation
		Date pmEvtsCalcEnd = null;
		boolean newEndSpansAfterPreviousCalc = endDate.after(tunedConf.getLastCalculationEnd());
		if (calcStatus.equals(CalcStatus.RESET)) {//Start date outside previous calc (ie Reset calc)
			pmEvtsCalcEnd = endDate;
			tunedConf.setLastCalculationEnd(pmEvtsCalcEnd);
		} else 
			if (calcStatus.equals(CalcStatus.INC)) {//Start date within previous calc
				if (newEndSpansAfterPreviousCalc) { //End date after previous calc => inc calc
					pmEvtsCalcEnd = endDate;
					tunedConf.setLastCalculationEnd(pmEvtsCalcEnd);
				} else { // End date within the previous calc => no re calc needed
					calcStatus = CalcStatus.NONE;
				}
			}

		LOGGER.info(
				infoMsg +
				"TunedConf after calculating bounds from "+tunedConf.getLastCalculationStart()+" to "+tunedConf.getLastCalculationEnd()+". Last event "+ tunedConf.getLastCalculatedEvent() +
				"Requested calculation is from "+startDate+" to "+endDate+". "+
				"New calc ("+calcStatus+") will be from "+pmEvtsCalcStart+" to "+pmEvtsCalcEnd);


		return new CalculationBounds(calcStatus, pmEvtsCalcStart, pmEvtsCalcEnd);

	}
	
	public void updateConf(TunedConf tunedConf, Stock stock, Date lastEventDate) {
		
		if (lastEventDate != null) tunedConf.setLastCalculatedEvent(lastEventDate);
		getTunedConfDAO().saveOrUpdateTunedConfs(tunedConf);
		
	}
	
	//TODO adjust to start date (adjustStartDate) and end date (adjustEndDate)
	public Stack<OnTheFlyRevesreCalcPeriod> onTheFlyReverseCalcDatesStack(Date dateDeb, Date dateFin, Integer tuneFreq) {
		
		PeriodSpliter periodSpliter = new PeriodSpliter();
		//return periodSpliter.splitForward(dateDeb, dateFin, tuneFreq, Calendar.MONTH);
		return periodSpliter.splitBackward(dateDeb, dateFin, tuneFreq, Calendar.MONTH);
	}

	public TunedConfDAO getTunedConfDAO() {
		return tunedConfDAO;
	}
	
	public Date adjustStartDate(Stock stock) {
		Date firstQuotationDateFromQuotations = DataSource.getInstance().getFirstQuotationDateFromQuotations((Stock) stock);
		Calendar adjustedStartCal = Calendar.getInstance();
		adjustedStartCal.setTime(firstQuotationDateFromQuotations);
		QuotationsFactories.getFactory().incrementDate(adjustedStartCal, 200);
		Date adjustedStartDate = adjustedStartCal.getTime();
		return adjustedStartDate;
	}
	
	public Date adjustEndDate(Stock stock) {
		Date lastQuote = stock.getLastQuote();
		return lastQuote;
	}

}


