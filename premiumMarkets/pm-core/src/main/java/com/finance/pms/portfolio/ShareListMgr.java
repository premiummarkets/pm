package com.finance.pms.portfolio;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.ShareDAO;
import com.finance.pms.datasources.shares.SharesListId;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.Indice;
import com.finance.pms.datasources.web.Providers;


public abstract class ShareListMgr {
	
	public static final double PERCENT_THRESHOLD = 30d;

	protected static MyLogger LOGGER = MyLogger.getLogger(ShareListMgr.class);
	
	
	@Autowired
	PortfolioDAO portfolioDAO;
	
	@Autowired
	ShareDAO shareDAO;
	
	public static ShareListMgr getInstance() {
		return (ShareListMgr) SpringContext.getSingleton().getBean("shareListMgr");
	}
	
	public synchronized void updateShareLists() throws Exception {
		
		try {
			List<String> shareListNames = portfolioDAO.loadShareListNames(null, new String[]{SharesListId.UNKNOWN.getSharesListCmdParam().toUpperCase()});

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
			
		} catch (IllegalArgumentException e) {
			LOGGER.warn(sharesList.getName()+ " is not associated with any MarketListProvider and won't be updated.", true);
			return null;
		} catch (Exception e) {
			LOGGER.error("Can't update "+sharesList.getName(),e);
			return null;
		}
		 
	}

	protected abstract void removeForeignKeysUpdate(PortfolioShare removedShare);

	protected abstract void addForeignKeysUpdate(PortfolioShare newPortfolioShare);
	
	//XXX this should be merge with the config loader in a separate bean  + creation of a factory factory for EventConfg and TuningConfg
	public abstract EventSignalConfig initPkgDependentConfig();


}
