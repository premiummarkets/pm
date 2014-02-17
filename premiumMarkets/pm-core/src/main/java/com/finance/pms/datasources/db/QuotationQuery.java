package com.finance.pms.datasources.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.finance.pms.datasources.db.DataSource.QUOTATIONS;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.QuotationUnit.ORIGIN;

public class QuotationQuery extends Query {
	
	private Date prevEntryDate = DateFactory.dateAtZero();
	
	public QuotationQuery() {
		super();
	}

	public QuotationQuery(List<Object> params) {
		super(params);
	}

	public QuotationQuery(String query) {
		super(query);
	}


	protected java.sql.Date override(Boolean isUserEntryRemovable, ResultSet rs, List<Object> retour) throws SQLException {
		java.sql.Date entryDate = rs.getDate(QUOTATIONS.DATE_FIELD);
		if (entryDate.equals(prevEntryDate)) {
			ORIGIN origin = ORIGIN.values()[rs.getInt(QUOTATIONS.ORIGIN_FIELD)];
			Boolean override = (isUserEntryRemovable && origin.equals(ORIGIN.WEB)) || (!isUserEntryRemovable && origin.equals(ORIGIN.USER));
			if (override) {
				retour.remove(retour.size()-1);
				return entryDate;
			} else {
				return null;
			}
		}
		prevEntryDate = entryDate;
		return entryDate;
	}

}
