package com.finance.pms.datasources.web;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.quotation.GetInflation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.QuotationUnit.ORIGIN;
import com.finance.pms.events.quotations.QuotationsFactories;

public interface ProvidersRates {
	
	static MyLogger LOGGER = MyLogger.getLogger(ProvidersRates.class);
	
	
	public default void lastQuoteInterpolation(Stock stock, BigDecimal lastClose, Date lastDate, Date end, List<QuotationUnit> usersQ) {
		try {
			
			//Clean up interpolations between start and end
			DataSource.getInstance().getShareDAO().deleteQuotationUnits(usersQ); 
			
			//Calculate new interpolation
			GetInflation inflationInterpoler = GetInflation.geInstance();

			BigDecimal inflationRateWithinDateRange = inflationInterpoler.inflationRateWithinDateRange(lastDate, end);
			int a = QuotationsFactories.getFactory().nbOpenIncrementBetween(stock.getTradingMode().getDataPointFactor(), lastDate, end);
			Double endClose = lastClose.doubleValue() + ((double) a) * inflationRateWithinDateRange.doubleValue();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(end);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			QuotationUnit quotationUnit = 
				new QuotationUnit(
					stock, stock.getMarketValuation().getCurrency(),
					calendar.getTime(),
					BigDecimal.ZERO,
					BigDecimal.ZERO,
					BigDecimal.ZERO,
					new BigDecimal(endClose),
					Long.valueOf(0),
					ORIGIN.USER, 
					BigDecimal.ONE, BigDecimal.ONE);
			
			//Insert new interpolation
			DataSource.getInstance().getShareDAO().saveOrUpdateQuotationUnit(quotationUnit);
			
		} catch (NotEnoughDataException e) {
			LOGGER.warn("Can't interpolate remaining data. " + e);
		}
	}
	
	public default void lastQuoteCopy(Stock stock, BigDecimal lastClose, Date lastDate, Date end, List<QuotationUnit> usersQ) {
		
		//Clean up interpolations between start and end
		DataSource.getInstance().getShareDAO().deleteQuotationUnits(usersQ); 
		
		//Calculate new interpolation
		Double endClose = lastClose.doubleValue();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(end);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		QuotationUnit quotationUnit = 
			new QuotationUnit(
				stock, stock.getMarketValuation().getCurrency(),
				calendar.getTime(),
				BigDecimal.ZERO,
				BigDecimal.ZERO,
				BigDecimal.ZERO,
				new BigDecimal(endClose),
				Long.valueOf(0),
				ORIGIN.USER, 
				BigDecimal.ONE, BigDecimal.ONE);
		
		//Insert new interpolation
		DataSource.getInstance().getShareDAO().saveOrUpdateQuotationUnit(quotationUnit);
		
	}
	
	

}
