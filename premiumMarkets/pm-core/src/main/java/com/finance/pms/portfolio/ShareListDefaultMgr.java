package com.finance.pms.portfolio;

import java.math.BigDecimal;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.shares.SharesListId;
import com.finance.pms.portfolio.Transaction.TransactionType;
import com.finance.pms.threads.ConfigThreadLocal;

public class ShareListDefaultMgr extends ShareListMgr {

	@Override
	protected void foreignKeysUpdate(PortfolioShare oldPortfolioShare) throws InvalidQuantityException {
			
		SharesList unknownShareList = new SharesList(SharesListId.UNKNOWN.name());
		unknownShareList.addOrUpdateShare(
				oldPortfolioShare.getStock(), 
				BigDecimal.ONE, EventSignalConfig.getNewDate(), BigDecimal.ONE, MonitorLevel.NONE, 
				oldPortfolioShare.getStock().getMarketValuation().getCurrency(), TransactionType.AIN);
		PortfolioMgr.getInstance().getPortfolioDAO().saveOrUpdatePortfolio(unknownShareList);

	}

	@Override
	public void initConfig() {
		
		try {
			ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME);
		} catch (IllegalArgumentException e) {
			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, new EventSignalConfig());
		}
		
	}

}
