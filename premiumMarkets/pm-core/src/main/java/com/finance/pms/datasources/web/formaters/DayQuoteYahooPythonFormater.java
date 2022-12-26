package com.finance.pms.datasources.web.formaters;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.StringTokenizer;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyUrl;

public class DayQuoteYahooPythonFormater extends DayQuoteFormater {

	public DayQuoteYahooPythonFormater(MyUrl url, Stock stock, String currency) {
		super(url, stock, currency);
	}

	@Override
	protected LinkedList<Comparable<?>> mainQuery(String line) throws StopParseException {
		StringTokenizer strt = new StringTokenizer(line, ",");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		assert strt.countTokens() == 8;

		LinkedList<Comparable<?>> tokenisedLine = new LinkedList<Comparable<?>>();
		for (int columnNum = 0; strt.hasMoreTokens() && columnNum < 6; columnNum++) {
			String field = strt.nextToken();

			switch (columnNum) {
			case 0: //Date
				if (field.equals("Date")) {	throw new RuntimeException(line); }
				try {
					tokenisedLine.add(df1.parse(field));
				} catch (Exception e) {
					throw new StopParseErrorException("Date Format error while parsing yahoo quotations : " + params.get(0) + ", for line: " + line, e.getMessage());
				}
				break;
			case 1: //Open
			case 2: //High
			case 3: //Low
			case 4: //Close
				tokenisedLine.add(new BigDecimal(field));
				break;
			case 5 : //Volume
				tokenisedLine.add(Long.valueOf(field));	
				break;
			default:
				throw new StopParseErrorException("Invalid yahoo quote parsing: ", line);
			}
			
		}
		
		return tokenisedLine;
	}

	@Override
	public Boolean canHaveNoResultsFound() {
		return true;
	}

}
