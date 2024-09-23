package com.finance.pms.datasources.web.formaters;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class YahooPyQuotation extends DailyQuotation {

	private static final long serialVersionUID = 1338187621985651051L;

	public YahooPyQuotation(DailyQuotation dailyQuotation) {
		super(dailyQuotation.mainQuery, dailyQuotation.stock, dailyQuotation.currency);
	}
	
//case 1: //Open
//case 2: //High
//case 3: //Low
//case 4: //Close
//	tokenisedLine.add(new BigDecimal(field));
//	break;
//case 5 : //Volume
//	tokenisedLine.add(Long.valueOf(field));	
//	break;
//case 6://Dividends
//	break;
//case 7://Stock Splits
//	Double lineSplitValue = Double.valueOf(field);
//	if (lineSplitValue == 0d) lineSplitValue++; //0 should actually be 1
//	tokenisedLine.add(lineSplitValue);	
//	break;
//case 8://?Capital Gains?
//	break;
	//dohlcvs: Date, BigDecimal,BigDecimal,BigDecimal,BigDecimal, Long, Double
	public YahooPyQuotation reverseSplit(Double split) {
		if (split != 0d) {
			for (int j = 1; j < 5; j++) { //ohlc
				BigDecimal splitBigD = new BigDecimal(split).setScale(10, RoundingMode.HALF_EVEN);
				mainQuery.set(j, ((BigDecimal) mainQuery.get(j)).multiply(splitBigD).setScale(10, RoundingMode.HALF_EVEN));
			}
			Long volume = (Long) mainQuery.get(5);
			mainQuery.set(5, Long.valueOf((long) ( volume / split))); //volume
		}
		return this;
	}
	
	/**
	 * @return 1 if no split
	 */
	public Double getSplit() {
		return (Double) mainQuery.get(6);
	}
	
	public List<Comparable<?>> getOHLCV() {
		return mainQuery.subList(1, 6);
	}

}
