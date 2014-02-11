package com.finance.pms.portfolio;

import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.alerts.AlertOnThreshold;
import com.finance.pms.alerts.AlertOnThresholdType;

public class InflationUpdateObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		
		List<Portfolio> visiblePortfolios = PortfolioMgr.getInstance().getVisiblePortfolios();
		for (Portfolio portfolio : visiblePortfolios) {
			for (PortfolioShare ps : portfolio.getListShares().values()) {
				Set<AlertOnThreshold> alertsOnThreshold = new HashSet<AlertOnThreshold>(ps.getAlertsOnThreshold());
				for (AlertOnThreshold alertOnThr : alertsOnThreshold) {
					if (alertOnThr.getAlertType().equals(AlertOnThresholdType.BELOW_ZERO_WEIGHTED_PROFIT_LIMIT)) {
						ps.addWeightedZeroProfitAlertGuardSetter(ps.getPriceUnitCost(EventSignalConfig.getNewDate(), ps.getTransactionCurrency()), EventSignalConfig.getNewDate());
					}
				}
			}
		}
		
	}

}
