package com.finance.pms.datasources.db;

import java.util.Date;

import org.apache.commons.lang.ArrayUtils;

import com.finance.pms.events.calculation.HouseDerivation;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.talib.indicators.TalibException;

public class StripedCloseDayToDay extends StripedCloseFunction {
	
	private HouseDerivation derivation;

	@Override
	public void targetShareData(PortfolioShare ps, Quotations stockQuotations) {
		
		Date startDate = getStartDate(stockQuotations);
		Date endDate = getEndDate(stockQuotations);
		try {
			
			derivation = new HouseDerivation(ps.getStock(), startDate, endDate, ps.getTransactionCurrency(), 20);

		} catch (NoQuotationsException e) {
			LOGGER.error(e,e);
		} catch (TalibException e) {
			LOGGER.error(e,e);
		}
		
		startDateQuotationIndex = stockQuotations.getClosestIndexForDate(0,startDate);
		endDateQuotationIndex = stockQuotations.getClosestIndexForDate(startDateQuotationIndex, endDate);

	}

	@Override
	public Double[] relativeCloses() {
		
		Integer startIndicatorIndexFromStartQuotationIndex = derivation.getIndicatorIndexFromQuotationIndex(startDateQuotationIndex);
		Integer endIndicatorIndexFromEndQuotationIndex = derivation.getIndicatorIndexFromQuotationIndex(endDateQuotationIndex);
		
		Integer fillFact = (startIndicatorIndexFromStartQuotationIndex < 0)?Math.abs(startIndicatorIndexFromStartQuotationIndex):0;
		Integer nonZeroStartIndx = Math.max(0, startIndicatorIndexFromStartQuotationIndex);
	
		double[] houseDerivation = new double[endIndicatorIndexFromEndQuotationIndex - startIndicatorIndexFromStartQuotationIndex +1];
		System.arraycopy(derivation.getHouseDerivation(), nonZeroStartIndx, houseDerivation, fillFact, endIndicatorIndexFromEndQuotationIndex - startIndicatorIndexFromStartQuotationIndex +1 - fillFact);	
		return ArrayUtils.toObject(houseDerivation);
	}

}
