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
package com.finance.pms.events.quotations;

import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
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
 * This object should be immutable.
 * This object contains a memory cache of the quotations from a defined start to a defined end and with a defined ValidityFilter and SplitOptions
 * The data is stored in thisCacheQuotationData
 * This object will be initialised from the Quotations cache QUOTATIONS_CACHE. The later being mutable.
 *
 * @author Guillaume Thoreton
 */
public class Quotations {

	private static MyLogger LOGGER = MyLogger.getLogger(Quotations.class);
	
	public static enum SplitOption {
		RAW, SPLITFREE, SPLITFREE_CALCULATEDONLY
	}

	public static enum ValidityFilter {
		ALLVALID, CLOSE, OHLC, VOLUME, OHLCV, NONEVALID; //NONEVALID doesn't return anything. ALLVALID should return all quotes as in the db.
		
		static ValidityFilter[] allActualFilters = EnumSet.<ValidityFilter>range(CLOSE, NONEVALID).toArray(new ValidityFilter[0]);
		
		public QuotationDataType[] toQuotationDataType() {
			switch (this) {
			case VOLUME:
				return new QuotationDataType[] {QuotationDataType.VOLUME};
			case CLOSE:
				return new QuotationDataType[] {QuotationDataType.CLOSE};
			case OHLC:
				return new QuotationDataType[] {QuotationDataType.OPEN, QuotationDataType.HIGH, QuotationDataType.LOW, QuotationDataType.CLOSE};
			case OHLCV:
				return QuotationDataType.values();
			default:
				throw new UnsupportedOperationException();
			}
		};
		
		public static boolean isValidClose(List<ValidityFilter> filters) {
			return filters.contains(ValidityFilter.CLOSE) || filters.contains(ValidityFilter.OHLC) || filters.contains(ValidityFilter.OHLCV) || filters.contains(ValidityFilter.VOLUME);
		}
		
		public static boolean isValidOhlc(List<ValidityFilter> filters) {
			return filters.contains(ValidityFilter.OHLC) ||  filters.contains(ValidityFilter.OHLCV);
		}

		public static boolean isValidVolume(List<ValidityFilter> filters) {
			return filters.contains(ValidityFilter.VOLUME) || filters.contains(ValidityFilter.OHLCV);
		}
		
		public static ValidityFilter getFilterFor(Collection<QuotationDataType> requieredStockData) {
			ValidityFilter[] ohl = (requieredStockData.contains(QuotationDataType.OPEN) || requieredStockData.contains(QuotationDataType.HIGH) || requieredStockData.contains(QuotationDataType.LOW))?
					new ValidityFilter[] {ValidityFilter.OHLC, ValidityFilter.OHLCV}: allActualFilters;
			ValidityFilter[] c = (requieredStockData.contains(QuotationDataType.CLOSE))? 
					new ValidityFilter[] {ValidityFilter.CLOSE, ValidityFilter.OHLC, ValidityFilter.OHLCV}: allActualFilters;
			ValidityFilter[] v = (requieredStockData.contains(QuotationDataType.VOLUME))? 
					new ValidityFilter[] {ValidityFilter.VOLUME, ValidityFilter.OHLCV}: allActualFilters;
			ValidityFilter result = Arrays.asList(ohl).stream()
					  .distinct()
					  .filter(Arrays.asList(c)::contains)
					  .distinct()
					  .filter(Arrays.asList(v)::contains)
					  .reduce((r, vf) -> (vf.ordinal() < r.ordinal())?vf:r)
					  .orElse(ValidityFilter.CLOSE);
			return result;
		}
	}
	
	private static ConcurrentHashMap<Stock, SoftReference<Map<String, QuotationData>>> QUOTATIONS_CACHE = new ConcurrentHashMap<Stock, SoftReference<Map<String, QuotationData>>>(100,0.90f);
	private static final LastUpdateStampChecker LAST_UPDATE_STAMP_CHECKER = new LastUpdateStampChecker();
	
	protected final Stock stock;
	private final Currency targetCurrency;

	private final Boolean keepCache;
	private final QuotationData thisCacheQuotationData;
	private Date firstQuotationDateFromDB;
	
	private final SplitOption splitOption;
	private final ValidityFilter[] validityFilters;

	private final Date firstDateRequested;
	private final Integer firstIndexLeftShiftRequested;
	private final Date lastDateRequested;
	
	public Quotations(Quotations quotations) {
		
		this.targetCurrency = quotations.targetCurrency;
		this.keepCache = quotations.keepCache;
		this.splitOption = quotations.splitOption;
		this.validityFilters = quotations.validityFilters;
		this.firstIndexLeftShiftRequested = quotations.firstIndexLeftShiftRequested;
		this.firstDateRequested = quotations.firstDateRequested;
		this.lastDateRequested = quotations.lastDateRequested;
		this.stock = quotations.stock;
		this.thisCacheQuotationData = quotations.thisCacheQuotationData;
		
		this.firstQuotationDateFromDB = quotations.firstQuotationDateFromDB;
	}

	//Called in indicator calculation and event composition calculations via Quotations Factory getQuotationsInstance
	Quotations(
			Stock stock, Date firstDate, Date lastDate, Boolean keepCache, Currency targetCurrency, Integer firstIndexLeftShift, 
			SplitOption splitOption, ValidityFilter... otherFilters) throws NoQuotationsException {
		
		if (targetCurrency == null) targetCurrency = stock.getMarketValuation().getCurrency(); //TODO use Currency.NAN instead of null
		this.targetCurrency = targetCurrency;
		
		this.keepCache = keepCache;
		this.splitOption = splitOption;
		this.validityFilters = otherFilters;

		this.firstIndexLeftShiftRequested = firstIndexLeftShift;
		this.firstDateRequested = firstDate;
		this.lastDateRequested = lastDate;
	
		try {
			this.stock = stock;
			this.thisCacheQuotationData = init(firstDate, lastDate, firstIndexLeftShift);
			if (!hasQuotations()) {
				throw new NoQuotationsException("No quotation available for " + this.stock + " within " + firstDate + " and " + lastDate +  " and filters " + splitOption + ", " + validityFilters);
			}
		} catch (NoQuotationsException e) {
			if (!getFirstQuotationFromDB(stock).after(lastDate)) {
				LOGGER.warn("Failed init quotations: " + e + " for " + this); //Could be an issue but depends on the filter ..
			};
			throw e;
		} catch (Exception e) {
			LOGGER.error("Failed init quotations: " + e + " for " + this);
			throw e;
		}
		
	}

	protected QuotationData init(Date firstDate, Date lastDate, Integer firstIndexShift) throws NoQuotationsException {

		if (Arrays.asList(validityFilters).contains(ValidityFilter.NONEVALID)) {
			return new QuotationData(new ArrayList<>());
		}

		//ALLVALID cache update
		QuotationData allValidQuotationData;
		if (keepCache) {
			
			synchronized (stock) {
				allValidQuotationData = this.isAllCached(stock, firstDate, lastDate, firstIndexShift);
				if (allValidQuotationData == null) { //Update the cache from DB for ALLVALID
					QuotationData existingProcessed = Quotations.getCachedStock(stock, splitOption, buildFilterNameKey(ValidityFilter.ALLVALID));
					
					//Remove potential other filters as they need update
					Quotations.removeCachedStockKey(stock);
	
					//Retrieve first date point
					Date cacheFillRetrieveStart = firstDate;
					Date lastCachedDate;
					if ( //Potential shift right for cache completion start
							existingProcessed != null && !existingProcessed.isEmpty() && 
							(lastCachedDate = existingProcessed.get(existingProcessed.size()-1).getDate()).before(firstDate) 
						) {
						cacheFillRetrieveStart = lastCachedDate;
					}
	
					//Load from DB
					QuotationData retreived = this.retreiveQuotationsData(cacheFillRetrieveStart, firstIndexShift); //Retrieves from cacheFillRetreiveStart date (<=firstDate) - firstIndexShift to lastQuote date 
	
					//Update cache for all valid
					//TODO the stock split calculation should be here before we cache
					boolean splitFree = SplitOption.SPLITFREE.equals(splitOption) || SplitOption.SPLITFREE_CALCULATEDONLY.equals(splitOption);
					boolean calculatedSplitFreeOnly =  SplitOption.SPLITFREE_CALCULATEDONLY.equals(splitOption);
					
					if (existingProcessed == null || existingProcessed.isEmpty()) { //Update cache from scratch
						
						QuotationUnit filteredQjm1 = null;
						List<QuotationUnit> quotationsUnitOut = new ArrayList<QuotationUnit>();
						if (splitFree) {
							for (int j = 0; j < retreived.size(); j++) {
								QuotationUnit qj = retreived.get(j);
								quotationsUnitOut.add(qj);
								if (filteredQjm1 != null) {
									solveSplitBetween(calculatedSplitFreeOnly, filteredQjm1, qj, quotationsUnitOut);
								}
								filteredQjm1 = qj; 
							}
							Quotations.putCashedStock(stock, splitOption, buildFilterNameKey(ValidityFilter.ALLVALID), new QuotationData(quotationsUnitOut));
						} else {
							Quotations.putCashedStock(stock, splitOption, buildFilterNameKey(ValidityFilter.ALLVALID), retreived);
						}
						
					} else { //Update Cache completion 
						
						if (!retreived.isEmpty()) {
						
							if (splitFree) {
								
								Date lastCached = existingProcessed.get(existingProcessed.size()-1).getDate();
								Date firstCached = existingProcessed.get(0).getDate();
								Date lastRetreived = retreived.get(retreived.size()-1).getDate();
								Date firstRetreived = retreived.get(0).getDate();
								
								List<QuotationUnit> processed = new ArrayList<QuotationUnit>();
								if (lastRetreived.after(lastCached)) {// Addition to the right: go through the right only  ]..] and apply to the whole series
									processed.addAll(existingProcessed);
									QuotationUnit filteredQjm1 = existingProcessed.get(existingProcessed.size()-1);
									for (int j = 0; j < retreived.size(); j++) {
										QuotationUnit qj = retreived.get(j);
										if (qj.getDate().after(lastCached)) { //we fill after to the end
											processed.add(qj);
											solveSplitBetween(calculatedSplitFreeOnly, filteredQjm1, qj, processed);
										}
										filteredQjm1 = qj;
									}
								}
								if (firstRetreived.before(firstCached)) {// addition to the left: go through the left only  [..] and apply only to the left
									QuotationUnit filteredQjm1 = null;
									for (int j = 0; j < retreived.size(); j++) {
										QuotationUnit qj = retreived.get(j);
										if (qj.getDate().before(firstCached)) {//we fill before and stop
											processed.add(qj);
											if (filteredQjm1 != null) {
												solveSplitBetween(calculatedSplitFreeOnly, filteredQjm1, qj, processed);
											}
										} else {
											break;
										}
										filteredQjm1 = qj; //qj is not affected by the split free as it is the pivot in the split free operation
									}
									processed.add(existingProcessed.get(0));
									solveSplitBetween(calculatedSplitFreeOnly, filteredQjm1, existingProcessed.get(0), processed);
									processed.addAll(existingProcessed.subList(1, existingProcessed.size()));
								}
								
								Quotations.putCashedStock(stock, splitOption, buildFilterNameKey(ValidityFilter.ALLVALID), new QuotationData(processed));
									
							} else {
								SortedSet<QuotationUnit> quotationUnits = new TreeSet<QuotationUnit>();
								quotationUnits.addAll(existingProcessed);
								quotationUnits.addAll(retreived);
								Quotations.putCashedStock(stock, splitOption, buildFilterNameKey(ValidityFilter.ALLVALID), new QuotationData(quotationUnits));
							}
							
						}
						
					}
					
					allValidQuotationData = Quotations.getCachedStock(stock, splitOption, buildFilterNameKey(ValidityFilter.ALLVALID));
				}
			}
		
		} else {
			allValidQuotationData = this.retreiveQuotationsData(firstDate, firstIndexShift);
		}
		
		//Filtered cache Update
		QuotationData cachedStock = null;
		if (keepCache) {
			synchronized (stock) {
				String validityFilterKey = buildFilterNameKey(validityFilters);
				QuotationData cachedStockForFilter = Quotations.getCachedStock(stock, splitOption, validityFilterKey);
				if (cachedStockForFilter == null) {
					cacheFilteredDataQuotationData(splitOption, validityFilterKey, allValidQuotationData);
				}
				cachedStock = Quotations.getCachedStock(stock, splitOption, validityFilterKey);
			}
		} else {
			if (Arrays.asList(validityFilters).contains(ValidityFilter.ALLVALID)) {
				cachedStock = allValidQuotationData;
			} else {
				cachedStock = buildQuotationDataFilter(allValidQuotationData, firstDateRequested, lastDateRequested, validityFilters);
			}
		}
		
		Integer firstIdx = Math.max(0, cachedStock.getClosestIndexBeforeOrAtDate(0, this.firstDateRequested) - firstIndexLeftShiftRequested);
		Integer lastIdx = cachedStock.getClosestIndexBeforeOrAtDate(0, lastDateRequested) + 1;

		return new QuotationData(cachedStock.subList(firstIdx, lastIdx));
	}


	protected QuotationData isAllCached(Stock stock, Date firstDate, Date lastDate, Integer indexShift) {

		QuotationData maxRangeCachedQuotationData = Quotations.getCachedStock(stock, splitOption, buildFilterNameKey(ValidityFilter.ALLVALID));

		QuotationUnit lastQU;
		Integer fdIdx = 0;
		boolean isCached =
				maxRangeCachedQuotationData != null
				&& !maxRangeCachedQuotationData.isEmpty()
				&& //start is out of range or start cache - indexShift <= firstDate
				(
						getFirstQuotationFromDB(stock).compareTo(maxRangeCachedQuotationData.get(0).getDate()) == 0 ||
						(
							maxRangeCachedQuotationData.get(0).getDate().compareTo(firstDate) <= 0
							&& (
								(	maxRangeCachedQuotationData.get((fdIdx = maxRangeCachedQuotationData.getClosestIndexBeforeOrAtDate(0, firstDate))).getDate().equals(firstDate) && 
									fdIdx >= indexShift
								) ||
								(	maxRangeCachedQuotationData.get(fdIdx).getDate().before(firstDate) &&
									fdIdx >= indexShift-1
								)
							)
						)
				)
				&& //end is out of range or end cache >= lastDate
				( 		(lastQU = maxRangeCachedQuotationData.get(maxRangeCachedQuotationData.size()-1)).getDate().compareTo(stock.getLastQuote()) == 0 ||
						lastQU.getDate().compareTo(lastDate) >= 0
				);

		if (isCached) {
			return maxRangeCachedQuotationData;
		} else {
			LOGGER.info("Cache is not uptodate for " + firstDate + " - " + indexShift + " to " + lastDate + " and " + stock);
			return null;
		}

	}

	private Date getFirstQuotationFromDB(Stock stock) {
		if (firstQuotationDateFromDB == null) firstQuotationDateFromDB = DataSource.getInstance().getFirstQuotationDateFromQuotations(stock);
		return firstQuotationDateFromDB;
	}

	protected QuotationData retreiveQuotationsData(Date firstDate, Integer indexShiftBefore) {

		ArrayList<QuotationUnit> nStripedQuotationsBefore = DataSource.getInstance().loadNStripedQuotationsBefore(stock, firstDate, indexShiftBefore, false);
		ArrayList<QuotationUnit> stripedQuotationsAfter = DataSource.getInstance().loadStripedQuotationsAfter(stock, firstDate);

		//Collections.reverse(nStripedQuotationsBefore); This is already reverse in loadNStripedQuotationsBefore
		nStripedQuotationsBefore.addAll(stripedQuotationsAfter);

		return new QuotationData(nStripedQuotationsBefore);
	}

	//qj (last of quotationsUnitToJ) is not affected by the split free as it is the pivot in the split free operation
	private void solveSplitBetween(boolean calculatedSplitFreeOnly, QuotationUnit qjm1, QuotationUnit qj, List<QuotationUnit> quotationsUnitToJ) {

		Double roundedFactor = 1d;
		if (calculatedSplitFreeOnly || qj.getDbStoredSplit() == null) {
			
			Date jm1Date = qjm1.getDate();
			double jm1Close = qjm1.getCloseRaw().doubleValue();
			Date jDate = qj.getDate();
			double jClose = qj.getCloseRaw().doubleValue();
			
			SplitData splitData = calculateSplit(qjm1.getStock().getTradingMode().getDataPointFactor(), jm1Date, jm1Close, jDate, jClose);
			if ( splitData.getSplitRate() >= 2 || splitData.getMergeRate() >= 2 ) { //price suddenly more than halved or doubled
				if (splitData.getSplitRate() >= 2) {
					roundedFactor = (double) splitData.getSplitRate(); 
					LOGGER.warn(
							"Split detected for " + this.stock.getFriendlyName()+ ": " + splitData +
									" from " + jm1Close + " (splited " + qjm1.getSplit() + ") at " + jm1Date + " to " + jClose+ " (splited " + qj.getSplit() + ") at " + jDate);
				} else if  (splitData.getMergeRate() >= 2) {
					roundedFactor = 1d/((double) splitData.getMergeRate());
					LOGGER.warn(
							"Merge detected for " + this.stock.getFriendlyName()+ ": " + splitData +
									" from " + jm1Close + " (splited " + qjm1.getSplit() + ") at " + jm1Date + " to " + jClose+ " (splited " + qj.getSplit() + ") at " + jDate);
				}
			}
		} else {
			roundedFactor = qj.getDbStoredSplit().doubleValue() * qj.getSplit().doubleValue();
		}

		if ( roundedFactor != 1d ) {
			BigDecimal factor = new BigDecimal(roundedFactor, new MathContext(10, RoundingMode.HALF_EVEN));
			for (int i = 0; i < quotationsUnitToJ.size() -1; i++) { // The last element has the split and is not added
				QuotationUnit quotationUnit = quotationsUnitToJ.get(i);
				quotationUnit.setSplit(quotationUnit.getSplit().multiply(factor, new MathContext(10, RoundingMode.HALF_EVEN)));
			}
		}
		
	}

	public static SplitData calculateSplit(double dataPointFactor, Date jm1Date, double jm1Close, Date jDate, double jClose) {
	
		double sessionAdjustmentDown = 0.13; //Hypotheses of a Circuit breaker at -13% per session
		double spanAdjustmentDown = 1d;
		double split = 1d;
		
		double sessionAdjustmentUp = 0.20; //Hypotheses of a Circuit breaker at +20% per session although is there such a thing ??
		double spanAdjustmentUp = 1d;
		double merge = 1d;
		
		double span = Math.min(5, Math.abs(QuotationsFactories.getFactory().nbOpenIncrementBetween(dataPointFactor, jm1Date, jDate))); //1 <= span <= 5
		if (span < 3) { //if span is just one day or over a bank?
			split = jm1Close/(jClose*(1d - sessionAdjustmentDown)); //We adjust the close price down to compensate a potential increase at j
			//We can do this as no natural decrease over one session, say D, can be so that jClose*(1.0 - sessionAdjustmentDown - D) < jm1Close/2
			merge = jClose*(1d + sessionAdjustmentUp)/jm1Close; //Same as above
			
		} else {//XXX this virtually impossible to know if the price change is natural or a split/merge
			
			//Was there a split? Here we check only the hypothesis that the price could have gone down naturally
			//This will miss the split if actually the price went significantly up since the split.
			spanAdjustmentDown = Math.pow(1d - sessionAdjustmentDown , span-1d);
			double adjustedJm1Down = jm1Close*spanAdjustmentDown; //This compensates a potential natural decrease of the price down to 13% per session.
			split = adjustedJm1Down/jClose;
	
			//Was there a merge? Same as above
			spanAdjustmentUp = Math.pow(1d + sessionAdjustmentUp , span-1d);
			double adjustedJm1Up = jm1Close*spanAdjustmentUp; //This compensates a potential natural gross of the price up to 20% per session
			merge = jClose/adjustedJm1Up;
			
		}
	
		return new SplitData(span, spanAdjustmentDown, (long) Math.floor(split), spanAdjustmentUp, (long) Math.floor(merge));
	}

	public Stock getStock() {
		return stock;
	}

	public Boolean hasQuotations() {
		QuotationData quotationData = getQuotationData();
		if (quotationData == null || quotationData.size() == 0) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug("No Quotations for:" + this.stock + " !! ");
			return false;
		}
		return true;
	}

	public Integer getFirstDateShiftedIdx() {
		return 0;
	}

	public Integer getLastDateIdx() {
		return Math.max(0, getQuotationData().size()-1);
	}


	public Date getDate(int x) {
		try {
			return getQuotationData().getDate(x);
		} catch (NoQuotationsException e) {
			throw new RuntimeException(e);
		}
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
			LOGGER.error("No quotation for " + this.stock + " at index : " + index, e);
			throw e;
		}
	}

	private QuotationUnit convertUnit(QuotationUnit quotationUnit) {
		if (notToBeConverted()) {
			return quotationUnit;
		}

		BigDecimal convertUnitFactor = convert(BigDecimal.ONE, quotationUnit.getDate());
		return new QuotationUnit(
				quotationUnit.getStock(), quotationUnit.getCurrency(),
				quotationUnit.getDate(),
				quotationUnit.getOpenRaw().multiply(convertUnitFactor),
				quotationUnit.getHighRaw().multiply(convertUnitFactor),
				quotationUnit.getLowRaw().multiply(convertUnitFactor),
				quotationUnit.getCloseRaw().multiply(convertUnitFactor),
				quotationUnit.getVolumeRaw(), quotationUnit.getOrigin(), 
				quotationUnit.getSplit(), quotationUnit.getDbStoredSplit());
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

	/**
	 * Both inclusive
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public List<QuotationUnit> getQuotationUnits(Integer startIndex, Integer endIndex) {
		List<QuotationUnit> ret = new ArrayList<QuotationUnit>();

		QuotationData quotationData = getQuotationData();
		for (int i = startIndex; i <= endIndex; i++) {
			ret.add(convertUnit(quotationData.get(i)));
		}

		return ret;
	}

	public BigDecimal getClosestCloseSpForDate(Date date) throws InvalidAlgorithmParameterException {
		return convert(getQuotationData().getClosestQuotationBeforeOrAtDate(date).getCloseSplit(), date);
	}
	
	public BigDecimal getClosestCloseForDate(Date date) throws InvalidAlgorithmParameterException {
		return convert(getQuotationData().getClosestQuotationBeforeOrAtDate(date).getCloseRaw(), date);
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
		try {
			return convertArray(getQuotationData().getCloseValues());
		} catch (NoQuotationsException e) {
			throw new RuntimeException(e);
		}
	}

	public double[] getVolumes() {
		return getQuotationData().getVolumeValues();
	}
	
	public Date[] getDates() {
		return getQuotationData().getDates();
	}


	private double[] convertArray(double[] amountsArray) throws NoQuotationsException {

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

	public double[] stdevLnDownUpVolatility(Date startDate, Date endDate, Integer period) throws NoQuotationsException {

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

	public double stdevLnVolatility(Date startDate, Date endDate, Integer period) throws NoQuotationsException {

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
		try {
			return convertArray(getQuotationData().getHighValues());
		} catch (NoQuotationsException e) {
			throw new RuntimeException(e);
		}
	}


	public double[] getLowValues() {
		try {
			return convertArray(getQuotationData().getLowValues());
		} catch (NoQuotationsException e) {
			throw new RuntimeException(e);
		}
	}


	protected void cacheFilteredDataQuotationData(SplitOption splitOption, String validityFilterKey, QuotationData allValidQuotationData) throws NoQuotationsException {
		putCashedStock(
				stock, splitOption, validityFilterKey, 
				buildQuotationDataFilter(allValidQuotationData, allValidQuotationData.getDate(0), allValidQuotationData.getLastDate(), validityFilters));
	}


	protected QuotationData getQuotationData() {
		return thisCacheQuotationData;
	}

	private QuotationData buildQuotationDataFilter(QuotationData allValidQuotationData, Date firstDate, Date lastDate, ValidityFilter... validityFilters) {

		List<ValidityFilter> filters = Arrays.asList(validityFilters);

		boolean validClose = ValidityFilter.isValidClose(filters);
		boolean validOhlc = ValidityFilter.isValidOhlc(filters);
		boolean validVolume = ValidityFilter.isValidVolume(filters);

		QuotationData allQs = allValidQuotationData;
		ArrayList<QuotationUnit> quotationsUnitOut = new ArrayList<QuotationUnit>();
		ArrayList<QuotationUnit> quotationsUnitOutHead = new ArrayList<QuotationUnit>();
		for (int j = 0; j < allQs.size(); j++) {

			QuotationUnit qj = allQs.get(j);

			if ( validClose && qj.getCloseSplit().compareTo(BigDecimal.ZERO) == 0 ) {
				continue;
			}
			if ( validOhlc && 
					(
							(qj.getHighSplit().compareTo(qj.getLowSplit()) == 0 && qj.getHighSplit().compareTo(qj.getCloseSplit()) == 0) //H==L==C i.e. no OHL
							||
							!(  //! (open <= high && close <= high && low <= open && low <= close)
									qj.getHighSplit().compareTo(qj.getOpenSplit()) >= 0 && qj.getHighSplit().compareTo(qj.getCloseSplit()) >= 0
									&& qj.getLowSplit().compareTo(qj.getOpenSplit()) <= 0 && qj.getLowSplit().compareTo(qj.getCloseSplit()) <= 0
							) 
							|| 
							(  // Unrealistic variations ( > 20%)
								( 0.8 > qj.getHighSplit().doubleValue()/qj.getOpenSplit().doubleValue() || qj.getHighSplit().doubleValue()/qj.getOpenSplit().doubleValue() > 1.2 ) ||
								( 0.8 > qj.getLowSplit().doubleValue()/qj.getOpenSplit().doubleValue() || qj.getLowSplit().doubleValue()/qj.getOpenSplit().doubleValue() > 1.2 ) ||
								( 0.8 > qj.getCloseSplit().doubleValue()/qj.getOpenSplit().doubleValue() || qj.getCloseSplit().doubleValue()/qj.getOpenSplit().doubleValue() > 1.2 )
							)
					)
				) {
				continue;
			}
			if ( validVolume ) {
					
					// 0 volume
					if (qj.getVolumeSplit() == 0 ) continue;
					
					//Unrealistic variations (*10)
					OptionalDouble lastMonthMeanOptional = allQs.subList(Math.max(0, j-21), j).stream().mapToLong(q -> q.getVolumeSplit()).average();
					if (	lastMonthMeanOptional.isPresent() && 
							(((double)qj.getVolumeSplit())/lastMonthMeanOptional.getAsDouble() > 10d || lastMonthMeanOptional.getAsDouble()/((double)qj.getVolumeSplit()) > 10d)) {
						continue;
					}
					
			}

			if ( qj.getDate().compareTo(firstDate) >= 0 ) {
				if (quotationsUnitOut.isEmpty()) {
					quotationsUnitOut.addAll(quotationsUnitOutHead.subList(Math.max(0, quotationsUnitOutHead.size() - firstIndexLeftShiftRequested), quotationsUnitOutHead.size()));
				}
				if (qj.getDate().compareTo(lastDate) <= 0) {
					quotationsUnitOut.add(qj);
				}

			} else {
				quotationsUnitOutHead.add(qj);
			}

		}

		if (quotationsUnitOut.isEmpty() && !quotationsUnitOutHead.isEmpty()) {
			quotationsUnitOut.addAll(quotationsUnitOutHead.subList(Math.max(0, quotationsUnitOutHead.size() - firstIndexLeftShiftRequested), quotationsUnitOutHead.size()));
		}

		return new QuotationData(quotationsUnitOut);

	}

	private String buildFilterNameKey(ValidityFilter... cacheFilters) {
		SortedSet<String> filterNameSet = new TreeSet<>();
		for (ValidityFilter filter : cacheFilters) {
			if (filter == null) throw new NotImplementedException("Filters can't be null: " + cacheFilters);
			filterNameSet.add(filter.name());
		}
		String filterName = "";
		for (String filter : filterNameSet) {
			filterName = filterName + filter;
		}
		return filterName;
	}
	
	//Update the key in case its isin and/or symbol has changed? 
	public static void updateCachedStockKey(Stock stock) {

		synchronized (stock) {
			SoftReference<Map<String, QuotationData>> softReference = Quotations.QUOTATIONS_CACHE.get(stock);
			if (softReference != null) {
				Quotations.QUOTATIONS_CACHE.remove(stock);
				Quotations.QUOTATIONS_CACHE.put(stock, softReference);
			}
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

		synchronized (stock) {
			SoftReference<Map<String, QuotationData>> softReference = Quotations.QUOTATIONS_CACHE.get(stock);
			if (softReference != null) {
				softReference.clear();
				Quotations.QUOTATIONS_CACHE.remove(stock);
			}
		}
		
	}

	private static void putCashedStock(Stock stock, SplitOption splitOption, String validityFilterKey, QuotationData quotationData) {
		
		validityFilterKey = "_" + splitOption.name() + "_" + validityFilterKey;

		synchronized (stock) {
			Map<String, QuotationData> cachedMap = null;
			SoftReference<Map<String, QuotationData>> softRef = Quotations.QUOTATIONS_CACHE.get(stock);
			if (softRef != null) {
				cachedMap = softRef.get();
			}
			if (cachedMap == null) {
				 cachedMap = new HashMap<String, QuotationData>();
			}
			cachedMap.put(validityFilterKey, quotationData);
			
			SoftReference<Map<String, QuotationData>> oldValue = Quotations.QUOTATIONS_CACHE.put(stock, new SoftReference<Map<String, QuotationData>>(cachedMap));
			if (oldValue != null) {
				oldValue.clear();
			}
		}
		
	}

	protected static QuotationData getCachedStock(Stock stock, SplitOption splitOption, String validityFilterKey) {
		
		validityFilterKey = "_" + splitOption.name() + "_" + validityFilterKey;

		synchronized (stock) {
			SoftReference<Map<String, QuotationData>> softRef = Quotations.QUOTATIONS_CACHE.get(stock);
			if (softRef == null || softRef.get() == null) { //|| softRef.get().get(validityFilterKey) == null || softRef.get().get(validityFilterKey).size() == 0) {
				return null;
			} else {
				return softRef.get().get(validityFilterKey);
			}
		}
		
	}

	public static LastUpdateStampChecker checkLastQuotationUpdateFor() {
		return LAST_UPDATE_STAMP_CHECKER;
	}

	public Currency getTargetCurrency() {
		return targetCurrency;
	}
	
	@Override
	public String toString() {
		return "Quotations [stock=" + stock + ", firstDateRequested=" + firstDateRequested + ", firstIndexLeftShiftRequested=" + firstIndexLeftShiftRequested + ", lastDateRequested="
				+ lastDateRequested + ", targetCurrency=" + targetCurrency + ", keepCache=" + keepCache + ", mainValidityFilter=" + splitOption + ", otherValidityFilters="
				+ validityFilters + "]";
	}

}
