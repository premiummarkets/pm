package com.finance.pms.datasources.web.formaters;

import java.math.BigDecimal;
import java.util.Date;

import com.finance.pms.datasources.db.Query;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.db.ValidatableDated;
import com.finance.pms.datasources.shares.Stock;

/**
 * @author gheeyom
 * OHLC are the values gathered over the interval
 */
public class IntraDayQuotation implements ValidatableDated {
	
	private static final long serialVersionUID = 3656593736649751130L;
	
	private Date time;
	private BigDecimal open;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal close;
	private Long volume;
	private Stock stock;

	public IntraDayQuotation(Stock stock, Date time, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, Long volume) {
		this.stock = stock;
		this.time = time;
		this.open = open;
		this.low = low;
		this.high = high;
		this.close = close;
		this.volume = volume;
	}

	public Stock getStock() {
		return stock;
	}
	
	public Date getTime() {
		return time;
	}
	
	public BigDecimal getOpen() {
		return open;
	}
	
	public BigDecimal getHigh() {
		return high;
	}
	
	public BigDecimal getLow() {
		return low;
	}
	
	public BigDecimal getClose() {
		return close;
	}
	
	public Long getVolume() {
		return volume;
	}

	@Override
	public String toString() {
		return "IntraDayQuotation [time=" + time + ", quotation=" + open + "-" + high + "-" + low + "-" + close + ", volume=" + volume + ", stock=" + stock + "]";
	}

	@Override
	public Query toDataBase() {
		throw new UnsupportedOperationException("Data baee does not support that intra day quotation.");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntraDayQuotation other = (IntraDayQuotation) obj;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (time.compareTo(other.time) != 0)
			return false;
		return true;
	}

	@Override
	public int compareTo(Validatable o) {
		return time.compareTo(((IntraDayQuotation)o).time);
	}

	@Override
	public Date getDate() { //XXX: for compatibility with DailyQuotation
		return time;
	}

}
