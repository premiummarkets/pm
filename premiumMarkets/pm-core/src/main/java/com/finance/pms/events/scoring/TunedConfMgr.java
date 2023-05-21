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

import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.events.OnTheFlyRevesreCalcPeriod;
import com.finance.pms.datasources.events.TunedConfDAO;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.QuotationsFactories;

//TODO 
// Add analysis name in tunedConf in order to tell apart the first pass events
// Or make first pass event independent of analysis name?
@Service("tunedConfMgr")
public class TunedConfMgr {

	private static MyLogger LOGGER = MyLogger.getLogger(TunedConfMgr.class);

	public enum CalcStatus {NONE,INC,RESET,IGNORE};

	@Autowired
	TunedConfDAO tunedConfDAO;

	public static TunedConfMgr getInstance() {
		//TODO a different bean may have to be used for intraday fixing => factory as per the quotation one
		return SpringContext.getSingleton().getBean(TunedConfMgr.class);
	}

	public TunedConfMgr() {
	}

	public synchronized TunedConf loadUniqueNoRetuneConfig(Stock stock, String configListFileName, String eventDefinition) {

		TunedConfId tunedConfId = new TunedConfId(stock, configListFileName, eventDefinition);
		TunedConf stockPrevTunedConf = tunedConfDAO.loadTunedConf(tunedConfId);
		if (stockPrevTunedConf == null) { //No prev conf : first calculation for this config file
			stockPrevTunedConf = new TunedConf(stock, configListFileName, eventDefinition);
			tunedConfDAO.saveOrUpdateTunedConfs(stockPrevTunedConf);
		}

		return stockPrevTunedConf;
	}

	public Date endDateConsistencyCheck(TunedConf tunedConf, Stock stock, Date endDate) throws InvalidAlgorithmParameterException {

		//Check end date within quotations
		Date lastQuote = stock.getLastQuote();
		boolean newEndWithinQuotations = endDate.before(lastQuote) || endDate.compareTo(lastQuote) == 0;
		if (!newEndWithinQuotations) {
			endDate = lastQuote;
		}
		return endDate;

	}
	
	
	@Deprecated
	/**
	 * @deprecated If related to first and second pass IndicatorCalculationThread
	 * 
	 * @param tunedConf
	 * @param stock
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public CalculationBounds autoCalcAndSetDatesBounds(TunedConf tunedConf, Stock stock, Date startDate, Date endDate) {

		LOGGER.info(
				stock.getSymbol() + " TunedConf before calculating bounds from " + tunedConf.getLastCalculationStart() + " to " + tunedConf.getLastCalculationEnd() + ". " +
				"Requested calculation is from " + startDate + " to " + endDate + ". "
				);

		//Setting PM events calculation date range
		//We want the calculation from the start date onward but we don't want to recalculate already calculated PM events
		//We don't want to update calculation after the last PM event.
		CalcStatus calcStatus = null;

		//What is the start date for this calculation
		Date pmEvtsCalcStart = null;
		String infoMsg = "";
		if (!startDate.before(tunedConf.getLastCalculationStart()) && !startDate.after(tunedConf.getLastCalculationEnd())) {//start is included into the last calculation
			//inc calc, tuned config start stays the same
			pmEvtsCalcStart = startDate;
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
		if (calcStatus.equals(CalcStatus.RESET)) {//End date outside previous calc (ie Reset calc)
			pmEvtsCalcEnd = endDate;
			tunedConf.setLastCalculationEnd(pmEvtsCalcEnd);
		}
		else if (calcStatus.equals(CalcStatus.INC)) {//Start date within previous calc
			if (newEndSpansAfterPreviousCalc) { //End date after previous calc => inc calc
				pmEvtsCalcEnd = endDate;
				tunedConf.setLastCalculationEnd(pmEvtsCalcEnd);
			} else { //End date within the previous calc => no re calc needed
				calcStatus = CalcStatus.NONE;
			}
		}

		LOGGER.info(
				infoMsg +
				"TunedConf after calculating bounds from "+tunedConf.getLastCalculationStart()+" to "+tunedConf.getLastCalculationEnd() +
				". Requested calculation is from "+startDate+" to "+endDate+". "+
				"New calculation status is "+calcStatus+" and will either be from "+pmEvtsCalcStart+" to "+pmEvtsCalcEnd +" or is not needed if "+CalcStatus.NONE+".");

		return new CalculationBounds(calcStatus, pmEvtsCalcStart, pmEvtsCalcEnd, null, null);

	}

	private void setDirty(TunedConf tunedConf) {
		updateConf(tunedConf, true, tunedConf.getLastCalculationStart(), tunedConf.getLastCalculationEnd());
	}

	private void resetDates(TunedConf tunedConf) {
		updateConf(tunedConf, tunedConf.getDirty(), DateFactory.dateAtZero(), DateFactory.dateAtZero());
	}

	public void updateConf(TunedConf tunedConf, Boolean dirty) {
		this.updateConf(tunedConf, dirty, tunedConf.getLastCalculationStart(), tunedConf.getLastCalculationEnd());
	}

	public void updateConf(TunedConf tunedConf, Boolean dirty, Date lastCalculationStart, Date lastCalculationEnd) {
		tunedConf.setLastCalculationStart(lastCalculationStart);
		tunedConf.setLastCalculationEnd(lastCalculationEnd);
		tunedConf.setDirty(dirty);
		tunedConfDAO.saveOrUpdateTunedConfs(tunedConf);
	}

	//TODO adjust to start date (adjustStartDate) and end date (adjustEndDate)
	public Stack<OnTheFlyRevesreCalcPeriod> onTheFlyReverseCalcDatesStack(Stock stock, Date dateDeb, Date dateFin, Integer tuneFreq) throws NotEnoughDataException {
		PeriodSpliter periodSpliter = new PeriodSpliter(stock, Arrays.asList(QuotationDataType.CLOSE));
		return periodSpliter.splitBackward(dateDeb, dateFin, tuneFreq, Calendar.MONTH);
	}

	public Date minimumStartDate(Stock stock) throws NotEnoughDataException {//200 days after first quotation available
		Date firstQuotationDateFromQuotations = DataSource.getInstance().getFirstQuotationDateFromQuotations((Stock) stock);
		Calendar adjustedStartCal = Calendar.getInstance();
		adjustedStartCal.setTime(firstQuotationDateFromQuotations);
		int adjustmentAmount = 0;
		QuotationsFactories.getFactory().incrementDate(stock, Arrays.asList(QuotationDataType.CLOSE), adjustedStartCal, adjustmentAmount);
		Date adjustedStartDate = adjustedStartCal.getTime();
		return adjustedStartDate;
	}

	public Date maximumEndDate(Stock stock) {
		Date lastQuote = stock.getLastQuote();
		return lastQuote;
	}

	/**
	 * Reset calculation dates without changing the dirty state.
	 * For use after delete events.
	 * @param analysisName
	 * @param indicators
	 */
	public void resetTunedConfDatesFor(String analysisName, EventInfo... indicators) { 
		List<TunedConf> loadAllTunedConfs = tunedConfDAO.loadAllTunedConfs();
		List<String> eis = Arrays.stream(indicators).map(i -> i.getEventDefinitionRef()).collect(Collectors.toList());
		loadAllTunedConfs.stream()
		.filter(tc -> tc.getTunedConfId().getConfigFile().equals(analysisName) && eis.contains(tc.getTunedConfId().getEventDefinition()))
		.forEach(tc -> resetDates(tc));
	}

	/**
	 * Reset calculation dates without changing the dirty state.
	 * For use after delete events.
	 * @param stock
	 * @param analysisName
	 * @param indicators
	 */
	public void resetTunedConfDatesFor(Stock stock, String analysisName, EventInfo[] indicators) {
		for(EventInfo ei: indicators) {
			TunedConf tc = loadUniqueNoRetuneConfig(stock, analysisName, ei.getEventDefinitionRef());
			resetDates(tc);
		}
	}

	/**
	 * Deletes Events, Reset calculation dates AND set Dirty.
	 * @param analysisName
	 * @param indicators
	 */
	public void deleteEventsDirtyConfs(String analysisName, EventInfo... indicators) {

		if (indicators != null && indicators.length > 0) {
			EventsResources.getInstance().crudDeleteEventsForIndicators(analysisName, indicators);

			List<TunedConf> loadAllTunedConfs = tunedConfDAO.loadAllTunedConfs();
			List<String> eis = Arrays.stream(indicators).map(i -> i.getEventDefinitionRef()).collect(Collectors.toList());
			loadAllTunedConfs.stream()
			.filter(tc -> tc.getTunedConfId().getConfigFile().equals(analysisName) && eis.contains(tc.getTunedConfId().getEventDefinition()))
			.forEach(tc -> setDirty(tc));
		}

	}

	/**
	 * Deletes Events, Reset calculation dates AND set Dirty.
	 * @param stock
	 * @param analysisName
	 * @param indicators
	 */
	public void deleteEventsDirtyConfsFor(Stock stock, String analysisName, EventInfo... indicators) {

		if (indicators != null && indicators.length > 0) {
			EventsResources.getInstance().crudDeleteEventsForStock(stock, analysisName, indicators);

			for(EventInfo ei: indicators) {
				TunedConf tc = loadUniqueNoRetuneConfig(stock, analysisName, ei.getEventDefinitionRef());
				setDirty(tc);
			}
		}

	}

}
