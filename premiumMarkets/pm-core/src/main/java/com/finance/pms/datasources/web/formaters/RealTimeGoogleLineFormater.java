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
import com.finance.pms.events.quotations.QuotationUnit.ORIGIN;

@SuppressWarnings("unused")
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
				openingMinute = Integer.valueOf(headerLine[1]);
			} else 
				if ("MARKET_CLOSE_MINUTE".equals(headerLine[0])) {
					closingMinutes = Integer.valueOf(headerLine[1]);
				} else 
					if ("TIMEZONE_OFFSET".equals(headerLine[0])) {
						gmtTimeOffset = Integer.valueOf(headerLine[1]);

					}
		} else 
			if (gmtTimeOffset != null) {
				String[] quotationLine = line.split(",");
				try {
					int minuteOTOpenD = Integer.valueOf(quotationLine[0]);
					QuotationUnit quotationUnit = addQuotationUnit(quotationLine, minuteOTOpenD);
					ret.add(new ValidatableQuotationUnit(quotationUnit));

				} catch (NumberFormatException e) {
					long day = Long.valueOf(quotationLine[0].substring(1));
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

		long volume = Long.valueOf(quotationLine[5]);

		DateToMinutesOTDConverter firstDateTimeMinutesConverter = new DateToMinutesOTDConverter(quotationDate, this.openingMinute + minuteOTOpenD);
		firstDateTimeMinutesConverter.minuteOfTheDayToTime();

		return new QuotationUnit((Stock) params.get(0), ((Stock) params.get(0)).getMarketValuation().getCurrency(),quotationDate.getTime(), open, high, low, close, volume, ORIGIN.WEB, BigDecimal.ONE);
	}


	@Override
	public Boolean canHaveNoResultsFound() {
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((quotationUnit == null) ? 0 : quotationUnit.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ValidatableQuotationUnit other = (ValidatableQuotationUnit) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (quotationUnit == null) {
				if (other.quotationUnit != null)
					return false;
			} else if (!quotationUnit.equals(other.quotationUnit))
				return false;
			return true;
		}

		private RealTimeGoogleLineFormater getOuterType() {
			return RealTimeGoogleLineFormater.this;
		}
	}



}
