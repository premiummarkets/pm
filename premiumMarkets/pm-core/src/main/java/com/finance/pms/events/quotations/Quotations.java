/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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
package com.finance.pms.events.quotations;

import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.portfolio.PortfolioMgr;

/**
 * The Class Quotations.
 * 
 * @author Guillaume Thoreton
 */
public class Quotations {

	protected static MyLogger LOGGER = MyLogger.getLogger(Quotations.class);
	
	public static enum ValidityFilter {NONE, ALL, SPLITFREE, OHLC, CLOSE, VOLUME, OHLCV};
	private static ConcurrentHashMap<Stock, SoftReference<Map<String, QuotationData>>> QUOTATIONS_CACHE = new ConcurrentHashMap<Stock, SoftReference<Map<String, QuotationData>>>(1000,0.90f);
	private static ConcurrentHashMap<Stock, LastUpdateStampChecker> UPDATESTAMP_CACHE = new ConcurrentHashMap<Stock, LastUpdateStampChecker>();

	protected Stock stock;
	private Currency targetCurrency;
	
	private Map<String, QuotationData> quotationDataFilters;
	private ValidityFilter cacheFilter;
	private List<ValidityFilter> otherCacheFilters;

	private Date firstDateRequested;
	private Integer firstIndexShiftRequested;
	private Date lastDateRequested;
	
	private Integer firstDatedIndex;
	private Integer lastDateIndex;

	//Called in indicator calculation and event composition calculations
	Quotations(Stock stock, Date firstDate, Date lastDate, Boolean keepCache, Currency targetCurrency, Integer firstIndexShift, ValidityFilter cacheFilter, ValidityFilter... otherCacheFilters) throws NoQuotationsException {
		if (targetCurrency == null) targetCurrency = stock.getMarketValuation().getCurrency(); //TODO use Currency.NAN instead of null
		this.targetCurrency = targetCurrency;
		this.cacheFilter = cacheFilter;
		this.otherCacheFilters = Arrays.asList(otherCacheFilters);
		
		this.firstIndexShiftRequested = firstIndexShift;
		this.firstDateRequested = firstDate;
		this.lastDateRequested = lastDate;
				
		init(stock, firstDate, lastDate, keepCache, firstIndexShift);
	}

	//Called in CalculationQuotations (inheritance)
	Quotations(Stock stock, QuotationData quotationData, Currency targetCurrency, ValidityFilter cacheFilter, ValidityFilter... otherCacheFilters) {
		if (targetCurrency == null) targetCurrency = stock.getMarketValuation().getCurrency(); //TODO use Currency.NAN instead of null
		this.stock = stock;
		this.targetCurrency = targetCurrency;
		this.cacheFilter = cacheFilter;
		this.otherCacheFilters = Arrays.asList(otherCacheFilters);
		
		firstIndexShiftRequested = 0;
		firstDateRequested = quotationData.getDate(0);
		lastDateRequested = quotationData.getDate(quotationData.size() -1);
				
		this.setUnfilteredQuotationData(quotationData);
	}

	protected void init(Stock stock, Date firstDate, Date lastDate, Boolean keepCache, Integer firstIndexShift) throws NoQuotationsException {
		this.stock = stock;
		
		if (otherCacheFilters.contains(ValidityFilter.NONE)) {
			setUnfilteredQuotationData(new QuotationData(new ArrayList<QuotationUnit>()));
			return;
		}
		
		QuotationData requestedQuotationsData;
		if (!keepCache) {
			requestedQuotationsData = this.retreiveQuotationsData(firstDate, firstIndexShift);
		} else {
			synchronized(stock) {
				requestedQuotationsData = this.isAllCached(stock, firstDate, lastDate, firstIndexShift);
				if (requestedQuotationsData == null) {
					
					QuotationData existingQuotationData = Quotations.getCashedStock(stock);
					
					//Retrieve first date point 
					Date lastCached;
					Date cacheFillRetreiveStart = firstDate;
					if ( existingQuotationData != null && !existingQuotationData.isEmpty() && (lastCached = existingQuotationData.get(existingQuotationData.size()-1).getDate()) .before(firstDate) ) {
						cacheFillRetreiveStart = lastCached;
					}
					
					//Load form db
					QuotationData retreivedQuotationsData = this.retreiveQuotationsData(cacheFillRetreiveStart, firstIndexShift);//Retrieves from cacheFillRetreiveStart (<=firstDate) - firstIndexShift to the lastQuote 
					
					//Update cache
					if (existingQuotationData == null) {
						requestedQuotationsData = retreivedQuotationsData;
						Quotations.putCashedStock(stock, retreivedQuotationsData);
					} else {
						SortedSet<QuotationUnit> quotationUnits = new TreeSet<QuotationUnit>();
						quotationUnits.addAll(existingQuotationData);
						quotationUnits.addAll(retreivedQuotationsData);
						requestedQuotationsData = new QuotationData(quotationUnits);
						Quotations.putCashedStock(stock, requestedQuotationsData);
					}
					
				}
			}
		}
		this.setUnfilteredQuotationData(requestedQuotationsData);
		
		if (!hasQuotations()) {
			throw new NoQuotationsException("No quotation available for "+this.stock+" within "+firstDate+" and "+lastDate);
		}
	}


	protected QuotationData isAllCached(Stock stock, Date firstDate, Date lastDate, Integer indexShift) {
		
		QuotationData cachedQuotationData = Quotations.getCashedStock(stock);
		
		QuotationUnit lastQU;
		Integer fdIdx = 0;
		boolean isCached = //Quotations.QUOTATIONS_CACHE.containsKey(stock) &&
									cachedQuotationData != null
									&& !cachedQuotationData.isEmpty()
									&& cachedQuotationData.get(0).getDate().compareTo(firstDate) <= 0 
									&& (//TODO first date in SHARES table
										(cachedQuotationData.get((fdIdx = cachedQuotationData.getClosestIndexBeforeOrAtDate(0, firstDate))).getDate().equals(firstDate) &&  fdIdx >= indexShift) ||
										(cachedQuotationData.get(fdIdx).getDate().before(firstDate) && fdIdx >= indexShift-1)
										)
									&& ( (lastQU = cachedQuotationData.get(cachedQuotationData.size()-1)).getDate().compareTo(lastDate) >= 0 || lastQU.getDate().compareTo(stock.getLastQuote()) == 0 );
		
		if (isCached) {
			return cachedQuotationData;
		} else {
			return null;
		}
		
	}

	protected QuotationData retreiveQuotationsData(Date firstDate, Integer indexShiftBefore) {
		
		ArrayList<QuotationUnit> nStripedQuotationsBefore = DataSource.getInstance().loadNStripedQuotationsBefore(stock, firstDate, indexShiftBefore, false);
		ArrayList<QuotationUnit> stripedQuotationsAfter = DataSource.getInstance().loadStripedQuotationsAfter(stock, firstDate);
		
		//Collections.reverse(nStripedQuotationsBefore); This is already reverse in loadNStripedQuotationsBefore
		nStripedQuotationsBefore.addAll(stripedQuotationsAfter);
		
		return new QuotationData(nStripedQuotationsBefore);
	}

	private void solveSplitBetween(QuotationUnit qjm1, QuotationUnit qj, ArrayList<QuotationUnit> quotationsUnitOut) {
		
		double span = Math.max(4, QuotationsFactories.getFactory().nbOpenIncrementBetween(qjm1.getDate(), qj.getDate()));
		double dj = qj.getClose().doubleValue();
		double djm1 =  qjm1.getClose().doubleValue();
		double change = (dj - djm1)/djm1;
		
		//if ( !Double.isInfinite(change) && !Double.isNaN(change) && Math.abs(change) >= (Math.pow(1.1, span)-1) ) {
		if ( !Double.isInfinite(change) && !Double.isNaN(change) && Math.abs(change) >= (span*Math.log(1.1)) ) {
			for (int i = 0; i < quotationsUnitOut.size()-1; i++) {
				QuotationUnit oldValue = quotationsUnitOut.get(i);
				Double factorDouble = Double.valueOf(dj/djm1);
				BigDecimal factor = new BigDecimal(factorDouble.toString());
				int scale = oldValue.getOpen().scale();
				QuotationUnit newValue = 
						new QuotationUnit(
								oldValue.getStock(), oldValue.getCurrency(),
								oldValue.getDate(), 
								oldValue.getOpen().multiply(factor).setScale(scale, BigDecimal.ROUND_HALF_EVEN), oldValue.getHigh().multiply(factor).setScale(scale, BigDecimal.ROUND_HALF_EVEN), 
								oldValue.getLow().multiply(factor).setScale(scale, BigDecimal.ROUND_HALF_EVEN), oldValue.getClose().multiply(factor).setScale(scale, BigDecimal.ROUND_HALF_EVEN), 
								oldValue.getVolume(), oldValue.getOrigin());
				quotationsUnitOut.set(i, newValue);
			}
		}
		
	}

	public Stock getStock() {
		return stock;
	}

	public Boolean hasQuotations() {
		QuotationData quotationData = getQuotationData();
		if (quotationData == null || quotationData.size() == 0) {
			LOGGER.debug("No Quotations for :" + this.stock + " !! ");
			return false;
		}
		return true;
	}
	
	public Boolean hasQuotationsDownToStartShift() {
		QuotationData quotationData = getQuotationData();
		return hasQuotations() && quotationData.getClosestIndexBeforeOrAtDate(0, firstDateRequested) >= (firstIndexShiftRequested-1); 
	}

	public Integer getFirstDateShiftedIdx() {
		return 0;
	}
	
	public Integer getFirstDateIdx() {
		return firstDatedIndex;
	}

	public Integer getLastDateIdx() {
		return lastDateIndex;
	}


	public Date getDate(int x) {
		return getQuotationData().getDate(x);
	}


	public int size() {
		return getQuotationData().size();
	}
	

	public Integer getClosestIndexBeforeOrAtDateOrIndexZero(Integer startIndex, Date date)  {
		Integer index = getQuotationData().getClosestIndexBeforeOrAtDate(startIndex, date);
		if (index == -1) return 0; //XXX This should return the index for a date before or equal to 'date'. In the case 'date' is before the first date available we return 0 so a date after 'date' ...//throw new InvalidAlgorithmParameterException();
		return index;
	}


	public QuotationUnit get(int index) {
		try {
			QuotationData quotationData = getQuotationData();
			return convertUnit(quotationData.get(index));
		} catch (RuntimeException e) {
			LOGGER.error("No quotation for "+this.stock+" at index : "+index, e);
			throw e;
		}
	}

	private QuotationUnit convertUnit(QuotationUnit quotationUnit) {
		if (notToBeConverted()) {
			return quotationUnit;
		}

		return new QuotationUnit(
				quotationUnit.getStock(), quotationUnit.getCurrency(),
				quotationUnit.getDate(), 
				convert(quotationUnit.getOpen(),quotationUnit.getDate()), 
				convert(quotationUnit.getHigh(), quotationUnit.getDate()), 
				convert(quotationUnit.getLow(), quotationUnit.getDate()), 
				convert(quotationUnit.getClose(), quotationUnit.getDate()), 
				quotationUnit.getVolume(), quotationUnit.getOrigin());
	}


	//TODO Cache the conversion instead of converting on the fly
	public BigDecimal convert(BigDecimal amount, Date date) {
		
		if (notToBeConverted()) {
			return amount;
		}
		
		return PortfolioMgr.getInstance().getCurrencyConverter().convert(this.stock.getMarketValuation(), targetCurrency, amount, date);
		
	}

	private boolean notToBeConverted() {
		MarketValuation marketCurrency = this.stock.getMarketValuation();
		return (this.targetCurrency.equals(marketCurrency.getCurrency()) && marketCurrency.getCurrencyFactor().compareTo(BigDecimal.ONE) == 0) || 
				Currency.NAN.equals(this.targetCurrency) || Currency.NAN.equals(marketCurrency.getCurrency());
	}
	
	public List<QuotationUnit> getQuotationUnits(Integer startIndex, Integer endIndex) {
		List<QuotationUnit> ret = new ArrayList<QuotationUnit>();
		
		QuotationData quotationData = getQuotationData();
		for (QuotationUnit quotationUnit : quotationData) {
			ret.add(convertUnit(quotationUnit));
		}
		
		return ret;
	}

	public BigDecimal getClosestCloseForDate(Date date) throws InvalidAlgorithmParameterException {
		return convert(getQuotationData().getClosestCloseBeforeOrAtDate(date), date);
	}
	
	public Number getClosestFieldForDate(Date date, QuotationDataType field) throws InvalidAlgorithmParameterException {
		QuotationData quotationData = getQuotationData();
		if (field.equals(QuotationDataType.VOLUME)) {
			return quotationData.getClosestFieldBeforeOrAtDate(date, field);
		} else {
			return convert((BigDecimal) quotationData.getClosestFieldBeforeOrAtDate(date, field), date);
		}
	}

	public double[] getCloseValues() {
		return convertArray(getQuotationData().getCloseValues());
	}
	
	public double[] getVolumes() {
		return getQuotationData().getVolumeValues();
	}


	private double[] convertArray(double[] amountsArray) {
		
		if (notToBeConverted()) {
			return amountsArray;
		}
		
		double[] ret = new double[amountsArray.length];
		QuotationData quotationData = getQuotationData();
		for (int i = 0; i < amountsArray.length; i++) {
			ret[i] = convert(BigDecimal.valueOf(amountsArray[i]), quotationData.getDate(i)).doubleValue();
		}
		
		return ret;
	}
	
	public double[] blackSholeDownUpVolatility(Date startDate, Date endDate, Integer period) throws NoQuotationsException {
		
		double[] meanStdVariance = new double[2];
		double[] closes = this.getCloseValues();
		int startIdx = this.getClosestIndexBeforeOrAtDateOrIndexZero(0, startDate);
		int endIdx = this.getClosestIndexBeforeOrAtDateOrIndexZero(0, endDate);
		for (int i = startIdx; i + period < endIdx; i = i + period) {
			
			double mean = 0;
			for (int j = 0; j < period; j++) {
				mean = mean + closes[i+j];
			}
			mean = mean/period;
			
			double[][] logReturn = new double[2][period];
			for (int j = 0; j < period; j++) {
				double dailyDiffToMean = closes[i+j] - mean;
				double dailyRat = closes[i+j+1]/closes[i+j];
				if (dailyDiffToMean <= 0) {
					logReturn[0][j] = Math.log(dailyRat);
				} else {
					logReturn[1][j] = Math.log(dailyRat);
				}
			}
			
			double logReturnMean = 0;
			for (int k = 0; k < 2; k++) {
				for (int j = 0; j < logReturn[k].length; j++) {
					logReturnMean = logReturnMean + logReturn[k][j];
				}
			}
			logReturnMean = logReturnMean / period;
			
			double[] logReturnVariance = new double[2];
			Arrays.fill(logReturnVariance, 0);
			for (int k = 0; k < 2; k++) {
				for (int j = 0; j < logReturn[k].length; j++) {
					double dailyDiff = logReturn[k][j] - logReturnMean;
					double dailyVar = dailyDiff * dailyDiff;
					logReturnVariance[k] = logReturnVariance[k] + dailyVar;
				}
				logReturnVariance[k] = logReturnVariance[k] / period;
			}
			
			double[] logReturnStdDev = new double[2];
			logReturnStdDev[0] = Math.sqrt(logReturnVariance[0]);
			logReturnStdDev[1] = Math.sqrt(logReturnVariance[1]);
			
			meanStdVariance[0] = meanStdVariance[0] + (logReturnStdDev[0]*Math.sqrt(logReturn[0].length));
			meanStdVariance[1] = meanStdVariance[1] + (logReturnStdDev[1]*Math.sqrt(logReturn[1].length));
		}
		
		int nbIter = (endIdx - startIdx)/period;
		meanStdVariance[0] = meanStdVariance[0] / nbIter;
		meanStdVariance[1] = meanStdVariance[1] / nbIter;
		return meanStdVariance;
		
	}
	
	public double[] stdevDownUpVolatility(Date startDate, Date endDate, Integer period) throws NoQuotationsException {
		
		double[] meanStdVariances = new double[2];
		double[] closes = this.getCloseValues();
		int startIdx = this.getClosestIndexBeforeOrAtDateOrIndexZero(0, startDate);
		int endIdx = this.getClosestIndexBeforeOrAtDateOrIndexZero(0, endDate);
		
		for (int i = startIdx; i + period < endIdx; i = i + period) {
			
			double mean = 0;
			for (int j = 0; j < period; j++) {
				mean = mean + closes[i+j];
			}
			mean = mean/period;
			
			double[] variances = new double[2];
			int negEle = 0;
			int posEle = 0;
			for (int j = 0; j < period; j++) {
				double dailyDiffToMean = closes[i+j] - mean;
				double dailyVar = dailyDiffToMean * dailyDiffToMean;
				if (dailyDiffToMean <= 0) {
					variances[0] = variances[0] + dailyVar;
					negEle++;
				} else {
					variances[1] = variances[1] + dailyVar;
					posEle++;
				}
				
			}
			variances[0] = variances[0]/negEle;
			variances[1] = variances[1]/posEle;
			
			meanStdVariances[0] = meanStdVariances[0] + (Math.sqrt(variances[0])/mean);
			meanStdVariances[1] = meanStdVariances[1] + (Math.sqrt(variances[1])/mean);
		}
		
		int nbIter = (endIdx - startIdx)/period;
		meanStdVariances[0] = meanStdVariances[0] / nbIter;
		meanStdVariances[1] = meanStdVariances[1] / nbIter;
		
		return meanStdVariances;
		
	}
	
	public double stdevVolatility(Date startDate, Date endDate, Integer period) throws NoQuotationsException {
		
		double meanStdDev = 0;
		double[] closes = this.getCloseValues();
		int startIdx = this.getClosestIndexBeforeOrAtDateOrIndexZero(0, startDate);
		int endIdx = this.getClosestIndexBeforeOrAtDateOrIndexZero(0, endDate);
		for (int i = startIdx; i + period < endIdx; i = i + period) {
			
			double mean = 0;
			for (int j = 0; j < period; j++) {
				mean = mean + closes[i+j];
			}
			mean = mean/period;
			
			double variance = 0;
			for (int j = 0; j < period; j++) {
				double dailyDiff = (closes[i+j] - mean)/mean;
				double dailyVar = dailyDiff * dailyDiff;
				variance = variance + dailyVar;
			}
			variance = variance/(period-1); //sample standard deviation is used for the corrected estimator (using N - 1)
			
			double stdDev= Math.sqrt(variance);
			meanStdDev = meanStdDev + (stdDev);
		}
		
		int nbIter = (endIdx - startIdx)/period;
		return meanStdDev / nbIter;
		
	}
	
	public double blackScholesVolatility(Date startDate, Date endDate, Integer period) throws NoQuotationsException {
		
		double meanStdVariance = 0;
		double[] closes = this.getCloseValues();
		int startIdx = this.getClosestIndexBeforeOrAtDateOrIndexZero(0, startDate);
		int endIdx = this.getClosestIndexBeforeOrAtDateOrIndexZero(0, endDate);
		for (int i = startIdx; i + period < endIdx; i = i + period) {
			
			double logReturn[] = new double[period];
			for (int j = 0; j < period; j++) {
				logReturn[j] = Math.log(closes[i+j+1]/closes[i+j]);
			}
			
			double logReturnMean = 0;
			for (int j = 0; j < period; j++) {
				logReturnMean = logReturnMean + logReturn[j];
			}
			logReturnMean = logReturnMean/period;
			
			double logReturnVariance = 0;
			for (int j = 0; j < period; j++) {
				double dailyDiff = logReturn[j] - logReturnMean;
				double dailyVar = dailyDiff * dailyDiff;
				logReturnVariance = logReturnVariance + dailyVar;
			}
			logReturnVariance = logReturnVariance/period;
			
			double logReturnStdDev= Math.sqrt(logReturnVariance);
			
			meanStdVariance = meanStdVariance + (logReturnStdDev*Math.sqrt(period));
		}
		
		int nbIter = (endIdx - startIdx)/period;
		return meanStdVariance / nbIter;
		
	}


	public double[] getHighValues() {
		return convertArray(getQuotationData().getHighValues());
	}


	public double[] getLowValues() {
		return convertArray(getQuotationData().getLowValues());
	}


	protected void setUnfilteredQuotationData(QuotationData unfilteredQuotationData) {
		this.quotationDataFilters = new HashMap<String, QuotationData>();
		this.quotationDataFilters.put(ValidityFilter.ALL.name(), unfilteredQuotationData);
		
		String filterName = buildFilterNameKey(cacheFilter, otherCacheFilters);
		quotationDataFilters.put(filterName, buildQuotationDataFilter(firstDateRequested, lastDateRequested, cacheFilter, otherCacheFilters));
	}


	protected QuotationData getQuotationData() {

		String filterName = buildFilterNameKey(cacheFilter, otherCacheFilters);
		QuotationData quotationData = quotationDataFilters.get(filterName);
		if (quotationData == null) {
			quotationData = buildQuotationDataFilter(firstDateRequested, lastDateRequested, cacheFilter, otherCacheFilters);
			quotationDataFilters.put(filterName, quotationData);
		}
		
		return quotationData;
		
	}

	private QuotationData buildQuotationDataFilter(Date firstDate, Date lastDate, ValidityFilter cacheFilter, List<ValidityFilter> otherCacheFilters) {
		
		ArrayList<ValidityFilter> filters = new ArrayList<Quotations.ValidityFilter>();
		filters.add(cacheFilter);
		filters.addAll(otherCacheFilters);
		
		boolean validClose = filters.contains(ValidityFilter.CLOSE) || filters.contains(ValidityFilter.OHLC) ||  filters.contains(ValidityFilter.OHLCV) || filters.contains(ValidityFilter.VOLUME);
		boolean validOhlc = filters.contains(ValidityFilter.OHLC) ||  filters.contains(ValidityFilter.OHLCV);
		boolean validVolume = filters.contains(ValidityFilter.VOLUME) || filters.contains(ValidityFilter.OHLCV);
		boolean splitFree = filters.contains(ValidityFilter.SPLITFREE);
		
		QuotationData allQs = quotationDataFilters.get(ValidityFilter.ALL.name());
		ArrayList<QuotationUnit> quotationsUnitOut = new ArrayList<QuotationUnit>();
		ArrayList<QuotationUnit> quotationsUnitOutHead = new ArrayList<QuotationUnit>();
		QuotationUnit qjm1 = null; //allQs.get(0);
		for (int j = 0; j < allQs.size(); j++) {
			
			QuotationUnit qj = allQs.get(j);
	
			if ( validClose && qj.getClose().compareTo(BigDecimal.ZERO) == 0 ) {
				continue;
			}
			if ( validOhlc && qj.getHigh().compareTo(qj.getLow()) == 0 && qj.getHigh().compareTo(qj.getClose()) == 0 ) {
				continue;
			}
			if ( validVolume && qj.getVolume() == 0 ) {
				continue;
			}
			
			if ( qj.getDate().compareTo(firstDate) > 0) {
				if (quotationsUnitOut.isEmpty()) {
					quotationsUnitOut.addAll(quotationsUnitOutHead.subList(Math.max(0, quotationsUnitOutHead.size() - firstIndexShiftRequested), quotationsUnitOutHead.size()));
					firstDatedIndex = Math.max(0, quotationsUnitOut.size()-1);
				}
				if (qj.getDate().compareTo(lastDate) <= 0) {
					quotationsUnitOut.add(qj);
					if (qjm1 != null && splitFree) {
						solveSplitBetween(qjm1, qj, quotationsUnitOut);
					}
				}
				
			} else {
				quotationsUnitOutHead.add(qj);
			}
			
			//qjm1 = quotationsUnitOut.get(quotationsUnitOut.size()-1);
			qjm1 = qj;
		}
		
		if (quotationsUnitOut.isEmpty() && !quotationsUnitOutHead.isEmpty()) {
			quotationsUnitOut.addAll(quotationsUnitOutHead.subList(Math.max(0, quotationsUnitOutHead.size() - firstIndexShiftRequested), quotationsUnitOutHead.size()));
			firstDatedIndex = Math.max(0, quotationsUnitOut.size()-1);
		}
		
		lastDateIndex = Math.max(0, quotationsUnitOut.size()-1);
		
		return new QuotationData(quotationsUnitOut);
		
	}

	private String buildFilterNameKey(ValidityFilter cacheFilter, List<ValidityFilter> otherCacheFilters) {
		SortedSet<ValidityFilter> filterNameSet = new TreeSet<Quotations.ValidityFilter>();
		if (cacheFilter == null) throw new NotImplementedException("Filters can't be null : "+cacheFilter+", "+otherCacheFilters);
		filterNameSet.add(cacheFilter);
		for (ValidityFilter filter : otherCacheFilters) {
			if (filter == null) throw new NotImplementedException("Filters can't be null : "+cacheFilter+", "+otherCacheFilters);
			filterNameSet.add(filter);
		}
		String filterName = "";
		for (ValidityFilter filter : filterNameSet) {
			if (filter.equals(ValidityFilter.ALL)) throw new NotImplementedException("You must specify a filter.");
			filterName = filterName+filter.name();
		}
		return filterName;
	}

	public static void updateCachedStockKey(Stock stock) {

		SoftReference<Map<String, QuotationData>> softReference = Quotations.QUOTATIONS_CACHE.get(stock);
		if (softReference != null) {
			Quotations.QUOTATIONS_CACHE.remove(stock);
			Quotations.QUOTATIONS_CACHE.put(stock, softReference);
		}

	}
	
	public static Date refreshCaches(Stock stock) {
		
		Date lastQuote = DataSource.getInstance().getLastQuotationDateFromQuotations(stock, false);
		stock.setLastQuote(lastQuote);
		DataSource.getInstance().getShareDAO().saveOrUpdateStock(stock);
		
		Quotations.removeCachedStockKey(stock);
		
		return lastQuote;
	}
	
	public static void removeCachedStockKey(Stock stock) {

		SoftReference<Map<String, QuotationData>> softReference = Quotations.QUOTATIONS_CACHE.get(stock);
		if (softReference != null) {
			softReference.clear();
			Quotations.QUOTATIONS_CACHE.remove(stock);
		}
	}
	
	private static void putCashedStock(Stock stock, QuotationData quotationData) {
		
		Map<String, QuotationData> cachedMap = new HashMap<String, QuotationData>();
		cachedMap.put(ValidityFilter.ALL.name(), quotationData);
		SoftReference<Map<String, QuotationData>> oldValue = Quotations.QUOTATIONS_CACHE.put(stock, new SoftReference<Map<String, QuotationData>>(cachedMap));
		if (oldValue != null) {
			oldValue.clear();
		}
	}
	
	protected static QuotationData getCashedStock(Stock stock) {//XXX Should be private
		
		SoftReference<Map<String, QuotationData>> softRef = Quotations.QUOTATIONS_CACHE.get(stock);
		if (softRef==null || softRef.get() == null) {
			return null;
		} else {
			return softRef.get().get(ValidityFilter.ALL.name());
		}
	}

	public static LastUpdateStampChecker checkLastQuotationUpdateFor(Stock stock) {
		LastUpdateStampChecker lastUpdateStamp = UPDATESTAMP_CACHE.get(stock);
		if (lastUpdateStamp == null) {
			lastUpdateStamp = new LastUpdateStampChecker();
			UPDATESTAMP_CACHE.put(stock, lastUpdateStamp);
		}
		return lastUpdateStamp;
	}

	public Currency getTargetCurrency() {
		return targetCurrency;
	}

}
