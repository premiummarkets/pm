package com.finance.pms.portfolio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.finance.pms.datasources.files.TransactionElement;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;

/**
 * Other list to play with without messing up the Market ShareLists ({@link SharesList})
 * 
 * @author guil
 */
@Entity
@DiscriminatorValue("IndepShareList")
public class IndepShareList extends AbstractSharesList {
	
	@SuppressWarnings("unused")
	private IndepShareList() {
		super();
	}

	public IndepShareList(String name) {
		super(name);
	}
	
	public void addShare(Stock stock) {
		PortfolioShare newPortfolioShare = new PortfolioShare(this, stock, MonitorLevel.NONE, stock.getMarketValuation().getCurrency());
		addShareToList(newPortfolioShare);
	}

	@Override
	public Date getLastDateTransactionFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate) {
		return new Date(0);
	}

	@Override
	public BigDecimal getCashInFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency currency) {
		return BigDecimal.ONE;
	}

	@Override
	public BigDecimal getCashOutFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency currency) {
		return BigDecimal.ZERO;
	}

	@Override
	public BigDecimal getQuantityFor(PortfolioShare portfolioShare,Date currentStartDate, Date currentEndDate) {
		return BigDecimal.ONE;
	}

	@Override
	public BigDecimal getBasisFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency currency) {
		return BigDecimal.ONE;
	}

	@Override
	public BigDecimal getPriceAvgBuyFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate, Currency currency) {
		return BigDecimal.ONE;
	}

	@Override
	public InOutWeighted getWeightedInvestedFor(PortfolioShare portfolioShare, Date currentEndDate, Currency currency) {
		return new InOutWeighted(BigDecimal.ONE, BigDecimal.ZERO, currentEndDate);
	}

	@Override
	public SortedSet<TransactionElement> getTransactionsFor(PortfolioShare portfolioShare, Date currentStartDate, Date currentEndDate) {
		return new TreeSet<TransactionElement>();
	}
}
