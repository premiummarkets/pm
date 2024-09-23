package com.finance.pms.datasources.web.formaters;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import com.finance.pms.datasources.shares.Stock;

public class IntraDayQuoteFormater {

	private Stock stock;

	public IntraDayQuoteFormater(Stock stock) {
		this.stock = stock;
	}

	public IntraDayQuotation formatLine(String line) throws StopParseException {
		StringTokenizer strt = new StringTokenizer(line, ",");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss+00:00");
		assert strt.countTokens() >= 7;
		
		if (line.contains("No data") || line.isEmpty()) { throw new RuntimeException(line); }

		Date time = null;
		BigDecimal open = null;
		BigDecimal high = null;
		BigDecimal low = null;
		BigDecimal close = null;
		Long volume = null;
		for (int columnNum = 0; strt.hasMoreTokens() && columnNum < 9; columnNum++) {
			String field = strt.nextToken();

			switch (columnNum) {
			case 0: //Date
				if (field.equals("Datetime")) {	throw new RuntimeException(line); }
				try {
					time = df1.parse(field);
				} catch (Exception e) {
					throw new StopParseErrorException("Date Format error while parsing yahoo quotations: " + stock + ", for line: " + line, e.getMessage());
				}
				break;
			case 1: //Quote
				open = new BigDecimal(field);
				break;
			case 2:
				high = new BigDecimal(field);
				break;
			case 3:
				low = new BigDecimal(field);
				break;
			case 4:
				close = new BigDecimal(field);
				break;
			case 5 : //Volume
				volume = Long.valueOf(Double.valueOf(field).longValue());
				break;
			case 6:
			case 7:
			case 8:
				//Ignore
				break;
			default:
				throw new StopParseErrorException("Invalid yahoo quote parsing: ", line);
			}
			
		}

		return new IntraDayQuotation(stock, time, open, high, low, close, volume);

	}

}
