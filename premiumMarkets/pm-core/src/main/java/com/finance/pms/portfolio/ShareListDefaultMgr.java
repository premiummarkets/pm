package com.finance.pms.portfolio;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.threads.ConfigThreadLocal;

public class ShareListDefaultMgr extends ShareListMgr {

	@Override
	protected void foreignKeysUpdate(PortfolioShare removedShare) {
		//Nothing

	}

	@Override
	protected void initConfig() {
		
		try {
			ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME);
		} catch (IllegalArgumentException e) {
			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, new EventSignalConfig());
		}
		
	}

}
