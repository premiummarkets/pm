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
package com.finance.pms.datasources.web.formaters;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.finance.pms.datasources.db.Query;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyUrl;
import com.finance.pms.events.quotations.DateToMinutesOTDConverter;
import com.finance.pms.events.quotations.QuotationUnit;

public class RealTimeGoogleLineFormater extends LineFormater {
	

	//TODO
	private int closingMinutes;
	
	private int openingMinute;
	private Integer gmtTimeOffset;
	private Calendar quotationDate;
	

	
	public RealTimeGoogleLineFormater(String url, Stock stock) {
		super(new MyUrl(url));
		this.params.add(stock);
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		
		List<Validatable> ret = new ArrayList<Validatable>();
		
		String[] headerLine = line.split("=");
		if (headerLine.length == 2) {
			if ("MARKET_OPEN_MINUTE".equals(headerLine[0])) {
				openingMinute = new Integer(headerLine[1]);
			} else 
				if ("MARKET_CLOSE_MINUTE".equals(headerLine[0])) {
					closingMinutes = new Integer(headerLine[1]);
				} else 
					if ("TIMEZONE_OFFSET".equals(headerLine[0])) {
						gmtTimeOffset = new Integer(headerLine[1]);
						
					}
		} else 
			if (gmtTimeOffset != null) {
				String[] quotationLine = line.split(",");
				try {
					int minuteOTOpenD = new Integer(quotationLine[0]);
					QuotationUnit quotationUnit = addQuotationUnit(quotationLine, minuteOTOpenD);
					ret.add(new ValidatableQuotationUnit(quotationUnit));
					
				} catch (NumberFormatException e) {
					long day = new Long(quotationLine[0].substring(1));
					quotationDate = Calendar.getInstance();
					quotationDate.setTimeInMillis(day*1000);
					QuotationUnit quotationUnit = addQuotationUnit(quotationLine, 0);
					ret.add(new ValidatableQuotationUnit(quotationUnit));
				}
			}
		
		return ret;
	}

	private QuotationUnit addQuotationUnit(String[] quotationLine, int minuteOTOpenD) {
		BigDecimal open = new BigDecimal(quotationLine[1]);
		BigDecimal high = new BigDecimal(quotationLine[2]);
		BigDecimal low = new BigDecimal(quotationLine[3]);
		BigDecimal close = new BigDecimal(quotationLine[4]);
		
		long volume = new Long(quotationLine[5]);
		
		DateToMinutesOTDConverter firstDateTimeMinutesConverter = new DateToMinutesOTDConverter(quotationDate, this.openingMinute + minuteOTOpenD);
		firstDateTimeMinutesConverter.minuteOfTheDayToTime();
		
		return new QuotationUnit(quotationDate.getTime(), open, high, low, close, volume);
	}


	@Override
	public Boolean canHaveEmptyResults() {
		return false;
	}
	
	public class ValidatableQuotationUnit extends Validatable {

		private static final long serialVersionUID = 9060878063360050971L;
		
		QuotationUnit quotationUnit;
		
		public ValidatableQuotationUnit(QuotationUnit quotationUnit) {
			super();
			this.quotationUnit = quotationUnit;
		}

		public int compareTo(Validatable o) {
			return quotationUnit.compareTo(((ValidatableQuotationUnit) o).quotationUnit);
		}

		@Override
		public Query toDataBase() {
			return null;
		}
		
		public QuotationUnit getQuotationUnit() {
			return quotationUnit;
		}
		
		@Override
		public String toString() {
			return "ValidatableQuotationUnit [quotationUnit=" + quotationUnit + "]";
		}
	}
	
	

}
