package com.finance.pms.datasources.web.formaters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.DateUtil;

import com.finance.pms.datasources.currency.CurrencyRate;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.web.MyUrl;
import com.finance.pms.events.calculation.DateFactory;

public class CurrencyEuropeanCentralBankFormater extends LineFormater {

	Currency fromCurrency;
	Currency toCurrency;
	int toCurrencyPosition;
	int fromCurrencyPosition;
	private boolean toEuros;
	private boolean fromEuros;
	
	public CurrencyEuropeanCentralBankFormater(Currency fromCurrency, Currency toCurrency, MyUrl myUrl) {
		super(myUrl);
		this.toCurrencyPosition = -1;
		this.fromCurrencyPosition = -1;
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		toEuros = toCurrency.equals(Currency.EUR);
		fromEuros = fromCurrency.equals(Currency.EUR);
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		
		List<Validatable> ret = new ArrayList<Validatable>();
		
		String[] strt = line.split(",");
		String column1 = strt[0];
		if (column1.equals("Date")) {
			//title line
			for(int i = 1;i < strt.length && (toCurrencyPosition == -1 || fromCurrencyPosition == -1); i++) {
				String currency = strt[i];
				if (currency.equals(toCurrency.name())) {
					toCurrencyPosition = i;
				}
				if (currency.equals(fromCurrency.name())) {
					fromCurrencyPosition = i;
				}
			}

			if ( (toCurrencyPosition == -1 && !toEuros) || (fromCurrencyPosition == -1 && !fromEuros) )
					throw new StopParseErrorException(
							"Currency not found : "+fromCurrency+" => "+fromCurrencyPosition+
							" and "+toCurrency+" => "+toCurrencyPosition, 
							"Currency unavailable");
		} else {
			//daily quote
			LocalDate date = LocalDate.parse(column1, DateTimeFormatter.ISO_DATE);
			String fromValueStr = fromEuros?"1":strt[fromCurrencyPosition];
			BigDecimal fromValue = (!fromValueStr.equals("N/A"))? new BigDecimal(fromValueStr) : null;
			String toValueStr = toEuros?"1":strt[toCurrencyPosition];
			BigDecimal toValue = (!toValueStr.equals("N/A"))? new BigDecimal(toValueStr) : null;
			if (fromValue != null && toValue != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(date.atTime(0, 0).toEpochSecond(ZoneOffset.UTC)*1000);
				ret.add(new CurrencyRate(fromCurrency, toCurrency, DateFactory.midnithDate(calendar.getTime()), toValue.divide(fromValue, 10, BigDecimal.ROUND_HALF_EVEN)));
			}
		}
		
		return ret;
	}

	@Override
	public Boolean canHaveNoResultsFound() {
		return false;
	}

}
