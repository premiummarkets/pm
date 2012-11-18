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
package com.finance.pms.events.quotations;

import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.portfolio.PortfolioMgr;


// TODO: Auto-generated Javadoc
/**
 * The Class TalibIndicators.
 * 
 * @author Guillaume Thoreton
 */
public class Quotations {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(Quotations.class);
	
	private static ConcurrentHashMap<Stock, SoftReference<QuotationData>> QUOTATIONS_CACHE = new ConcurrentHashMap<Stock, SoftReference<QuotationData>>(1000,0.90f);
	private static Object object = new Object();

	/** The stock. */
	protected Stock stock;
	/** The quotation data. */
	private QuotationData quotationData;
	protected Integer firstDateShiftedIdx;
	protected Integer lastDateIdx;
	private Currency targetCurrency;

	//Called in indicator calculation and event composition calculations
	Quotations(Stock stock, Date firstDate, Date lastDate, Boolean keepCache, Currency targetCurrency, Integer firstIndexShift, Integer lastIndexShift) throws NoQuotationsException {
		if (targetCurrency == null) targetCurrency = stock.getMarket().getCurrency(); //TODO use Currency.NAN instead of null
		this.targetCurrency = targetCurrency;
		init(stock, firstDate, lastDate, keepCache, firstIndexShift, lastIndexShift);
	}

	//Called in CalculationQuotations (inheritance)
	Quotations(Stock stock, QuotationData quotationData, Currency targetCurrency) {
		if (targetCurrency == null) targetCurrency = stock.getMarket().getCurrency(); //TODO use Currency.NAN instead of null
		this.stock = stock;
		this.targetCurrency = targetCurrency;
		firstDateShiftedIdx = 0;
		lastDateIdx = quotationData.size()-1;
		this.setQuotationData(quotationData);
	}

	/**
	 * @param stock
	 * @param firstDate
	 * @param lastDate
	 * @param keepCache
	 * @param firstIndexShift 
	 * @param lastIndexShift 
	 * @param limitedCache
	 * @param targetCurrency 
	 * @throws NoQuotationsException 
	 */
	protected void init(Stock stock, Date firstDate, Date lastDate, Boolean keepCache, Integer firstIndexShift, Integer lastIndexShift) throws NoQuotationsException {
		this.stock = stock;
		
		QuotationData requestedQuotationsData;
		if (!keepCache) {
			requestedQuotationsData = this.retreiveQuotationsData(firstDate, firstIndexShift);
		} else {
			synchronized (Quotations.object) {
				if (!this.isAllCached(stock, firstDate, lastDate, firstIndexShift)) {
					QuotationData retreivedQuotationsData = this.retreiveQuotationsData(firstDate, firstIndexShift);
					//QuotationData existingQuotationData = Quotations.QUOTATIONS_CACHE.get(stock);
					QuotationData existingQuotationData = Quotations.getCashedStock(stock);
					if (existingQuotationData == null) {
						//Quotations.QUOTATIONS_CACHE.put(stock, retreivedQuotationsData);
						Quotations.putCashedStock(stock, retreivedQuotationsData);
					} else {
						SortedSet<QuotationUnit> quotationUnits = new TreeSet<QuotationUnit>();
						quotationUnits.addAll(existingQuotationData);
						quotationUnits.addAll(retreivedQuotationsData);
						//Quotations.QUOTATIONS_CACHE.put(stock, new QuotationData(quotationUnits));
						Quotations.putCashedStock(stock, new QuotationData(quotationUnits));
					}
				}
			}
			//requestedQuotationsData = Quotations.QUOTATIONS_CACHE.get(stock);
			requestedQuotationsData = Quotations.getCashedStock(stock);
		}
		this.setQuotationData(requestedQuotationsData);

		if (hasQuotations()) {
			firstDateShiftedIdx =  Math.max(this.getQuotationData().getClosestIndexForDate(0,firstDate) - firstIndexShift, 0);
			lastDateIdx = Math.min(this.getQuotationData().getClosestIndexForDate(firstDateShiftedIdx, lastDate) + lastIndexShift, this.getQuotationData().size() -1) ;
		} else {
			firstDateShiftedIdx = 0;
			lastDateIdx = 0;
			throw new NoQuotationsException("No quotation available for "+this.stock+" within "+firstDate+" and "+lastDate);
		}
	}


	protected boolean isAllCached(Stock stock, Date firstDate, Date lastDate, Integer indexShift) {
		//QuotationData cachedQuotationData = Quotations.QUOTATIONS_CACHE.get(stock);
		QuotationData cachedQuotationData = Quotations.getCashedStock(stock);
	
		QuotationUnit lastQU;
		return Quotations.QUOTATIONS_CACHE.containsKey(stock)
									&& cachedQuotationData != null
									&& !cachedQuotationData.isEmpty()
									&& cachedQuotationData.get(0).getDate().compareTo(firstDate) <= 0
									&& ( (lastQU = cachedQuotationData.get(cachedQuotationData.size()-1)).getDate().compareTo(lastDate) >= 0 || lastQU.getDate().compareTo(stock.getLastQuote()) == 0 )
									&& cachedQuotationData.getClosestIndexForDate(0, firstDate) >= indexShift;
	}


	/**
	 * @param start
	 * @param end
	 */
	public Boolean hasQuotationsInDateRange(Date start, Date end) {
		if (hasQuotations()) {
			Date firstDate = this.getQuotationData().get(this.getFirstDateShiftedIdx()).getDate();
			if (firstDate.after(start)) { 
				LOGGER.warn("Not enough quotation from " + firstDate + " to " + start + " for "+ this.stock.toString()+ ". Available loaded quotations start from : "+firstDate);
				return false;
			}
			
			return true;
		}
		
		LOGGER.warn("No Quotation at all for "+ this.stock.toString());
		return false;
	}


	/**
	 * Gets the data stock.
	 * @param lastDate 
	 * @param firstDate 
	 * @param indexShiftBefore 
	 * 
	 * @return the data stock
	 */
	protected QuotationData retreiveQuotationsData(Date firstDate, Integer indexShiftBefore) {
		
		SortedSet<QuotationUnit> quotationUnits = new TreeSet<QuotationUnit>();
		
		ArrayList<QuotationUnit> nStripedQuotationsBefore = DataSource.getInstance().loadNStripedQuotationsBefore(stock, firstDate, indexShiftBefore, false);
		ArrayList<QuotationUnit> stripedQuotationsAfter = DataSource.getInstance().loadStripedQuotationsAfter(stock, firstDate);
		
		ArrayList<QuotationUnit> stripedQuotations = nStripedQuotationsBefore;
		stripedQuotations.addAll(stripedQuotationsAfter);
		
		//Remove splits
		ArrayList<QuotationUnit> noSplitQuotations = new ArrayList<QuotationUnit>(stripedQuotations);
		for (int j = 1; j < stripedQuotations.size(); j++) {
			
			double dj = stripedQuotations.get(j).getClose().doubleValue();
			double djm1 =  stripedQuotations.get(j-1).getClose().doubleValue();
			double change = (dj - djm1)/djm1;
			if (!Double.isInfinite(change) && !Double.isNaN(change) && Math.abs(change) >= 0.5) {
				for (int i = 0; i < j; i++) {
					QuotationUnit oldValue = noSplitQuotations.get(i);
					Double factorDouble = Double.valueOf(dj/djm1);
					BigDecimal factor = new BigDecimal(factorDouble.toString());
					int scale = oldValue.getOpen().scale();
					QuotationUnit newValue = 
							new QuotationUnit(oldValue.getDate(), 
									oldValue.getOpen().multiply(factor).setScale(scale, RoundingMode.HALF_UP), oldValue.getHigh().multiply(factor).setScale(scale, RoundingMode.HALF_UP), 
									oldValue.getLow().multiply(factor).setScale(scale, RoundingMode.HALF_UP), oldValue.getClose().multiply(factor).setScale(scale, RoundingMode.HALF_UP), 
									oldValue.getVolume());
					noSplitQuotations.set(i, newValue);
				}
			}
		}
		
		quotationUnits.addAll(noSplitQuotations);
		
		return new QuotationData(quotationUnits);
	}

	/**
	 * Gets the stock.
	 * 
	 * @return the stock
	 */
	public Stock getStock() {
		return stock;
	}

	/**
	 * Gets the last date idx.
	 * 
	 * @return the last date idx
	 */
	public Integer getLastDateIdx() {
		return lastDateIdx;
	}


	/**
	 * 
	 */
	public Boolean hasQuotations() {
		if (this.getQuotationData() == null || this.getQuotationData().size() == 0) {
			LOGGER.debug("No Quotations for :" + this.stock + " !! ");
			return false;
		}
		
		return true;
	}


	public Integer getFirstDateShiftedIdx() {
		return firstDateShiftedIdx;
	}


	public Date getDate(int x) {
		return getQuotationData().getDate(x);
	}


	public int size() {
		return getQuotationData().size();
	}
	

	public Integer getClosestIndexForDate(Integer startIndex, Date date) {
		return getQuotationData().getClosestIndexForDate(startIndex, date);
	}


	public QuotationUnit get(int index) {
		try {
			return convertUnit(getQuotationData().get(index));
		} catch (RuntimeException e) {
			LOGGER.error("No quotation for "+this.stock+" at "+index,e);
			throw e;
		}
	}

	private QuotationUnit convertUnit(QuotationUnit quotationUnit) {
		if (notToBeConverted()) {
			return quotationUnit;
		}

		return new QuotationUnit(
				quotationUnit.getDate(), 
				convert(quotationUnit.getOpen(),quotationUnit.getDate()), 
				convert(quotationUnit.getHigh(), quotationUnit.getDate()), 
				convert(quotationUnit.getLow(), quotationUnit.getDate()), 
				convert(quotationUnit.getClose(), quotationUnit.getDate()), 
				quotationUnit.getVolume());
	}


	//TODO Cache the conversion instead of converting on the fly
	public BigDecimal convert(BigDecimal amount, Date date) {
		
		if (notToBeConverted()) {
			return amount;
		}
		
		return PortfolioMgr.getInstance().getCurrencyConverter().convert(this.stock.getMarket().getCurrency(), targetCurrency, amount, date);
		
	}


	private boolean notToBeConverted() {
		Currency marketCurrency = this.stock.getMarket().getCurrency();
		return this.targetCurrency.equals(marketCurrency) || Currency.NAN.equals(this.targetCurrency) || Currency.NAN.equals(marketCurrency);
	}
	
	public List<QuotationUnit> getQuotationUnits(Integer startIndex, Integer endIndex) {
		List<QuotationUnit> ret = new ArrayList<QuotationUnit>();
		
		for (QuotationUnit quotationUnit : this.getQuotationData()) {
			ret.add(convertUnit(quotationUnit));
		}
		
		return ret;
	}

	public BigDecimal getCloseForDate(Date date) throws InvalidAlgorithmParameterException {
		return convert(getQuotationData().getClosestCloseForDate(date),date);
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
		for (int i = 0; i < amountsArray.length; i++) {
			ret[i] = convert(BigDecimal.valueOf(amountsArray[i]), getQuotationData().getDate(i)).doubleValue();
		}
		
		return ret;
	}
	
	public double[] blackSholeDownUpVolatility(Date startDate, Date endDate, Integer period) throws NoQuotationsException {
		
		double[] meanStdVariance = new double[2];
		double[] closes = this.getCloseValues();
		int startIdx = this.getClosestIndexForDate(0, startDate);
		int endIdx = this.getClosestIndexForDate(0, endDate);
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
		int startIdx = this.getClosestIndexForDate(0, startDate);
		int endIdx = this.getClosestIndexForDate(0, endDate);
		
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
		int startIdx = this.getClosestIndexForDate(0, startDate);
		int endIdx = this.getClosestIndexForDate(0, endDate);
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
			
			//meanStdVariance = meanStdVariance + (stdDev/mean);
			meanStdDev = meanStdDev + (stdDev);
		}
		
		int nbIter = (endIdx - startIdx)/period;
		return meanStdDev / nbIter;
		
	}
	
	public double blackScholesVolatility(Date startDate, Date endDate, Integer period) throws NoQuotationsException {
		
		double meanStdVariance = 0;
		double[] closes = this.getCloseValues();
		int startIdx = this.getClosestIndexForDate(0, startDate);
		int endIdx = this.getClosestIndexForDate(0, endDate);
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


	protected void setQuotationData(QuotationData quotationData) {
		this.quotationData = quotationData;
	}


	protected QuotationData getQuotationData() {
		return quotationData;
	}

	public static void removeCashedStock(Stock stock) {

		//Quotations.QUOTATIONS_CACHE.remove(stock);
		SoftReference<QuotationData> softRef = Quotations.QUOTATIONS_CACHE.remove(stock);
		if (softRef!=null) {
			softRef.clear();
		}
	}
	
	public static void putCashedStock(Stock stock, QuotationData quotationData) {
		
		SoftReference<QuotationData> oldValue = Quotations.QUOTATIONS_CACHE.put(stock, new SoftReference<QuotationData>(quotationData));
		if (oldValue != null) {
			oldValue.clear();
		}
	}
	
	public static QuotationData getCashedStock(Stock stock) {
		
		SoftReference<QuotationData> softRef = Quotations.QUOTATIONS_CACHE.get(stock);
		if (softRef==null) {
			return null;
		} else {
			return softRef.get();
		}
	}

}
