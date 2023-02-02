package com.finance.pms.datasources.web.formaters;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyUrl;

public class DayQuoteYahooPythonFormater extends DayQuoteFormater {

	public DayQuoteYahooPythonFormater(MyUrl url, Stock stock, String currency) {
		super(url, stock, currency);
	}

	@Override
	//Date,Open,High,Low,Close,Volume,Dividends,Stock Splits,Capital Gains
	protected LinkedList<Comparable<?>> mainQuery(String line) throws StopParseException {
		StringTokenizer strt = new StringTokenizer(line, ",");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		assert strt.countTokens() >= 7;

		LinkedList<Comparable<?>> tokenisedLine = new LinkedList<Comparable<?>>();
		for (int columnNum = 0; strt.hasMoreTokens() && columnNum < 9; columnNum++) {
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
				tokenisedLine.add(Long.valueOf(Double.valueOf(field).longValue()));	
				break;
			case 6://Dividends
				break;
			case 7://Stock Splits
				Double lineSplitValue = Double.valueOf(field);
				if (lineSplitValue == 0d) lineSplitValue++; //0 should actually be 1
				tokenisedLine.add(lineSplitValue);	
				break;
			case 8://?Capital Gains?
				break;
			default:
				throw new StopParseErrorException("Invalid yahoo quote parsing: ", line);
			}
			
		}
		
		return tokenisedLine;
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		List<Validatable> dailyQuotations = super.formatLine(line);
		return dailyQuotations.stream().map(dq -> {
			YahooPyQuotation yahooPyQuotation = new YahooPyQuotation((DailyQuotation) dq);
			return yahooPyQuotation;
		}).collect(Collectors.toList());
	}

	@Override
	public Boolean canHaveNoResultsFound() {
		return true;
	}

}
