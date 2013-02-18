package com.finance.pms.portfolio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.ShareDAO;
import com.finance.pms.datasources.shares.SharesListId;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.Indice;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.portfolio.Transaction.TransactionType;


public abstract class ShareListMgr {
	
	private static final double PERCENT_THRESHO = 20d;

	protected static MyLogger LOGGER = MyLogger.getLogger(ShareListMgr.class);
	
	
	@Autowired
	PortfolioDAO portfolioDAO;
	
	@Autowired
	ShareDAO shareDAO;
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public synchronized void updateShareLists() throws Exception {
		
		initConfig();

		try {
			List<String> shareListNames = portfolioDAO.loadShareListNames(null, new String[]{SharesListId.UNKNOWN.getSharesListCmdParam()});

			for (String shareListName : shareListNames) {
				Set<Indice> indices = Indice.parseString(shareListName);
				if (indices.size() == 1 || indices.size() == 0) { //No composite yahoo share lists or other share lists
					SharesList sharesList = PortfolioMgr.getInstance().getPortfolioDAO().loadShareList(shareListName);
					updateShareList(sharesList);
				} 
			}
		} catch (Exception e) {
			LOGGER.error(e,e);
			throw e;
		}
	}

	private StockList updateShareList(SharesList sharesList) {
		
		Providers provider = null;
		String[] shareListNameSplit = sharesList.getName().split(",");
		
		try {
			
			if (shareListNameSplit.length == 1) {// Yahoo Indices list
				provider = Providers.getInstance(SharesListId.valueOf(sharesList.getName()).getSharesListCmdParam());
			} else {// No indices => Not yahooIndices
				Set<Indice> indices = Indice.parseString(sharesList.getName());
				provider = Providers.getInstance(SharesListId.valueOf(shareListNameSplit[0]).getSharesListCmdParam());
				provider.addIndices(indices, true);
			}
			
			return provider.retrieveStockListFromWeb(MarketQuotationProviders.DEFAULT, new StockList(shareDAO.loadAllStocks()));
			
		} catch (java.lang.IllegalArgumentException e) {
			LOGGER.warn(sharesList.getName()+ " is not associated with any MarketListProvider and won't be updated.", true);
			return null;
		} catch (Exception e) {
			LOGGER.error("Can't update "+sharesList.getName(),e);
			return null;
		}
		 
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void removeSharesFromList(SharesList existingSharesList,  String listProviderDescr,  Set<PortfolioShare> tobeRemovedFromList, int listFromWebSize) {
		
		if (new Double(tobeRemovedFromList.size()) <= (PERCENT_THRESHO/100d)*(new Double(existingSharesList.listShares.size()))) {
			
			LOGGER.guiInfo("Number of old tickers to be removed from "+listProviderDescr+" : " + tobeRemovedFromList.size());
			LOGGER.warn("Old tickers to be removed from "+listProviderDescr+" : " + tobeRemovedFromList, true);
			for (PortfolioShare removedShare : tobeRemovedFromList) {
				try {
					foreignKeysUpdate(removedShare);
					existingSharesList.removeOrUpdateShare(removedShare, removedShare.getQuantity(), EventSignalConfig.getNewDate(), BigDecimal.ZERO, TransactionType.AOUT);
				} catch (InvalidQuantityException e) {
					LOGGER.warn(e,e);
				}
			}
			
		} else {
			LOGGER.error(
					"The number of tickes retrieved for "+listProviderDescr+" is "+listFromWebSize +
					"And the number of old tickers to be removed from "+listProviderDescr+" : " + tobeRemovedFromList.size()+ " is more than "+PERCENT_THRESHO+"% of the existing list size "+existingSharesList.listShares.size()+".\n" +
					"Maybe the list is faulty and no remove will be done. Please check the log for urls used for that list.\n" +
					"Tickers to be removed are : "+tobeRemovedFromList);
		}
	}
	

	protected abstract void foreignKeysUpdate(PortfolioShare removedShare) throws InvalidQuantityException;
	
	//XXX this should be merge with the config loader in a separate bean  + creation of a factory factory for EventConfg and TuningConfg
	public abstract void initConfig();


}
