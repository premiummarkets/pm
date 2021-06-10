package com.finance.pms.portfolio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.alerts.AlertOnEvent;
import com.finance.pms.alerts.AlertOnThreshold;
import com.finance.pms.alerts.AlertOnThresholdType;
import com.finance.pms.alerts.ThresholdType;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.threads.ConfigThreadLocal;

public class AlertsMgrDelegate {

	private static MyLogger LOGGER = MyLogger.getLogger(AlertsMgrDelegate.class);

	private LatestOnlyPSBridge ps;

	public AlertsMgrDelegate(PortfolioShare portfolioShare) {
		this.ps = new LatestOnlyPSBridge(portfolioShare);
	}

	public void addBuyAlerts(BigDecimal transcationPrice, Date currentDate) {

		if (ps.getQuantity(currentDate).compareTo(BigDecimal.ZERO) > 0) {

			addBuyPriceAlerts(transcationPrice, currentDate);
			BigDecimal avgCostPerUnit = ps.getPriceUnitCost(currentDate, ps.getTransactionCurrency());
			addAboveTakeProfitAlert(avgCostPerUnit, currentDate);
			addBelowMaxLossAlert(avgCostPerUnit, currentDate);
			addWeightedZeroProfitAlertGuardSetter(avgCostPerUnit, currentDate);
			addChannelAlerts(transcationPrice);

		} else {

			removeAllAlertsOnThreshold();

		}

	}

	public Set<AlertOnThreshold> getAlertsOnThresholdUp() {
		Set<AlertOnThreshold> alertsUp = new HashSet<AlertOnThreshold>();
		for (AlertOnThreshold alert : ps.getAlertsOnThreshold()) {
			if (alert.getThresholdType().equals(ThresholdType.UP)) alertsUp.add(alert);
		}
		return alertsUp;
	}

	public Set<AlertOnThreshold> getAlertsOnThresholdDown() {
		Set<AlertOnThreshold> alertsDown = new HashSet<AlertOnThreshold>();
		for (AlertOnThreshold alert : ps.getAlertsOnThreshold()) {
			if (alert.getThresholdType().equals(ThresholdType.DOWN)) alertsDown.add(alert);
		}
		return alertsDown;
	}

	public void addAlertOnThreshold(ThresholdType threshold, BigDecimal value, AlertOnThresholdType alertType, String message) {
		if (value != null) {
			ps.getAlertsOnThreshold().add(new AlertOnThreshold(ps.getUpStreamPs(), threshold, alertType, value, message));
		}
	}

	public void removeAlertOnThreshold(AlertOnThreshold alert) {
		ps.getAlertsOnThreshold().remove(alert);
	}

	private void removeAlertOnThresholdFor(AlertOnThresholdType alertType) {
		HashSet<AlertOnThreshold> ret = getAlertsOnThresholdFor(alertType);
		ps.getAlertsOnThreshold().removeAll(ret);
	}

	public HashSet<AlertOnThreshold> getAlertsOnThresholdFor(AlertOnThresholdType alertType) {
		HashSet<AlertOnThreshold> ret = new HashSet<AlertOnThreshold>();
		for (AlertOnThreshold alert : ps.getAlertsOnThreshold()) {
			if (alertType.equals(alert.getAlertType())) {
				ret.add(alert);
			}
		}
		return ret;
	}

	private void removeAllAlertsOnThreshold() {
		Set<AlertOnThreshold> alertsOnThresholdCp = new HashSet<AlertOnThreshold>(ps.getAlertsOnThreshold());
		for (AlertOnThreshold alertOnThreshold : alertsOnThresholdCp) {
			removeAlertOnThreshold(alertOnThreshold);
		}
	}

	private void addBuyPriceAlerts(BigDecimal currentPrice, Date currentDate) {
		this.removeAlertOnThresholdFor(AlertOnThresholdType.AVG_BUY_PRICE);
		if (currentPrice.compareTo(ps.getPriceUnitCost(currentDate, ps.getTransactionCurrency())) <= 0) addBuyPriceAlertAbove(currentDate);
		if (currentPrice.compareTo(ps.getPriceUnitCost(currentDate, ps.getTransactionCurrency())) >= 0) addBuyPriceAlertBelow(currentDate);	
	}

	private void addBuyPriceAlertBelow(Date currentDate) {
		BigDecimal avgBuyPrice = ps.getPriceUnitCost(currentDate, ps.getTransactionCurrency());
		this.addAlertOnThreshold(ThresholdType.DOWN, avgBuyPrice, AlertOnThresholdType.AVG_BUY_PRICE, "(Calculation price is avg buy price " + readableNumber(avgBuyPrice) +")");
	}

	private void addBuyPriceAlertAbove(Date currentDate) {
		BigDecimal avgBuyPrice = ps.getPriceUnitCost(currentDate, ps.getTransactionCurrency());
		this.addAlertOnThreshold(ThresholdType.UP, avgBuyPrice, AlertOnThresholdType.AVG_BUY_PRICE, "(Calculation price is avg buy price " + readableNumber(avgBuyPrice) +")");
	}

	private String readableNumber(BigDecimal aNumber) {
		NumberFormat format = new DecimalFormat("#0.0000000000");
		return format.format(aNumber);
	}

	public void addWeightedZeroProfitAlertGuardSetter(BigDecimal price, String message) {
		this.addAlertOnThreshold(ThresholdType.UP, price, AlertOnThresholdType.BELOW_ZERO_WEIGHTED_PROFIT_LIMIT, message);
	}

	private void addWeightedZeroProfitAlertGuard(BigDecimal profitGuard) {
		this.removeAlertOnThresholdFor(AlertOnThresholdType.BELOW_ZERO_WEIGHTED_PROFIT_LIMIT);	
		String belowGuardMessage = "";
		addWeightedZeroProfitAlertGuard(profitGuard, belowGuardMessage);
	}

	void addWeightedZeroProfitAlertGuardSetter(BigDecimal avgBuyPrice, Date currentDate) {

		this.removeAlertOnThresholdFor(AlertOnThresholdType.BELOW_ZERO_WEIGHTED_PROFIT_LIMIT);	

		BigDecimal sellLimitGuardPriceRate;
		InOutWeighted weightedInOut = ps.getWeightedInvested(null, currentDate, ps.getTransactionCurrency());
		BigDecimal sellLimitGuardPrice;
		if (!weightedInOut.isEmpty()) {
			sellLimitGuardPrice = weightedInOut.getWeightedInvestedStillIn().divide(ps.getQuantity(currentDate), 10, RoundingMode.HALF_EVEN);
			sellLimitGuardPriceRate = (avgBuyPrice.compareTo(BigDecimal.ZERO) > 0)?
					sellLimitGuardPrice.divide(avgBuyPrice, 10, RoundingMode.HALF_EVEN).subtract(BigDecimal.ONE.setScale(4)):
					BigDecimal.ZERO;
		} else {
			sellLimitGuardPriceRate = getEventsConfig().getSellLimitGuardPrice();
			sellLimitGuardPrice = addPercentage(avgBuyPrice, sellLimitGuardPriceRate);
		}

		if (sellLimitGuardPrice.compareTo(ps.getPriceClose(currentDate, ps.getTransactionCurrency())) <= 0) {
			addWeightedZeroProfitAlertGuard(sellLimitGuardPrice);
		} else {
			String aboveMessage = "(Will set a guard when "+ readablePercentOf(sellLimitGuardPriceRate) + " above calculation price " + readableNumber(avgBuyPrice) +")";
			addWeightedZeroProfitAlertGuardSetter(sellLimitGuardPrice, aboveMessage);
		}

	}

	private String readablePercentOf(BigDecimal aPercentage) {
		NumberFormat format = new DecimalFormat("#0.00 %");
		return format.format(aPercentage);
	}

	private EventSignalConfig getEventsConfig() {
		return ((EventSignalConfig) ConfigThreadLocal.get("eventSignal"));
	}

	/**
	 * We don't want to sell more than n% below average buy price 
	 * If price is down n% below last transaction price and > avg buy price => sell ie stop loss. 
	 * If price more than n% below avg buy price  => nothing
	 * @param currentPrice
	 */
	private void addMovingLimitBelow(BigDecimal crossingPrice) {
		this.removeAlertOnThresholdFor(AlertOnThresholdType.BELOW_PRICE_CHANNEL);

		BigDecimal limitPriceBelowRate = getEventsConfig().getLimitPriceBelow();
		BigDecimal belowSellLimit = addPercentage(crossingPrice, limitPriceBelowRate.negate());

		String belowMessage = "(" +readablePercentOf(limitPriceBelowRate) + " below calculation price " + readableNumber(crossingPrice) +")";

		this.addAlertOnThreshold(ThresholdType.DOWN, belowSellLimit, AlertOnThresholdType.BELOW_PRICE_CHANNEL, belowMessage);
	}

	public void addAboveTakeProfitAlert(BigDecimal price, String message) {
		this.addAlertOnThreshold(ThresholdType.UP, price, AlertOnThresholdType.ABOVE_TAKE_PROFIT_LIMIT, message);
	}

	public void addBelowMaxLossAlert(BigDecimal price, String message) {
		this.addAlertOnThreshold(ThresholdType.DOWN, price, AlertOnThresholdType.BELOW_MAX_LOSS_LIMIT, message);
	}

	public void addWeightedZeroProfitAlertGuard(BigDecimal price, String message) {
		this.addAlertOnThreshold(ThresholdType.DOWN, price, AlertOnThresholdType.BELOW_ZERO_WEIGHTED_PROFIT_LIMIT, message);
	}

	public void addSimpleAlertOnThreshold(ThresholdType threshold, BigDecimal value, String message) {
		if (threshold.equals(ThresholdType.UP)) this.addAlertOnThreshold(threshold, value, AlertOnThresholdType.MANUALUP, message);
		if (threshold.equals(ThresholdType.DOWN)) this.addAlertOnThreshold(threshold, value, AlertOnThresholdType.MANUALDOWN, message);
	}

	public void resetCrossDown(AlertOnThreshold alert, BigDecimal crossingPrice) {
		LOGGER.debug("Params : " + alert);

		switch (alert.getAlertType()) {
		case BELOW_ZERO_WEIGHTED_PROFIT_LIMIT:
			//nothing as the alert (guard) will be repeated until further user action
			break;
		case AVG_BUY_PRICE:
			//remove and reset alert buy price up
			//this.removeAlertOnThresholdFor(AlertOnThresholdType.AVG_BUY_PRICE);
			//addBuyPriceAlertAbove();
			this.addBuyPriceAlerts(crossingPrice, DateFactory.getNowEndDate());
			break;
		case BELOW_PRICE_CHANNEL:
			this.resetAlertLimitBelow(alert, crossingPrice);
			break;
		case MANUALUP:
		case MANUALDOWN:
			this.removeAlertOnThreshold(alert);
			break;
		default:
			LOGGER.error("Nothing to do for : " + alert);
		}

	}

	public void resetCrossUp(AlertOnThreshold alert, BigDecimal crossingPrice) {
		LOGGER.debug("Params : " + alert);

		switch (alert.getAlertType()) {
		case BELOW_ZERO_WEIGHTED_PROFIT_LIMIT:
			//remove and set a take profit below alert guard
			addWeightedZeroProfitAlertGuard(alert.getValue());
			break;
		case ABOVE_TAKE_PROFIT_LIMIT:
			//remove and reset a new take profit above alert
			addAboveTakeProfitAlert(crossingPrice, DateFactory.getNowEndDate());
			break;
		case BELOW_MAX_LOSS_LIMIT:
			//remove and reset stop loss alert
			addBelowMaxLossAlert(crossingPrice, DateFactory.getNowEndDate());
			break;
		case AVG_BUY_PRICE:
			//remove and reset alert buy price down
			//this.removeAlertOnThresholdFor(AlertOnThresholdType.AVG_BUY_PRICE);
			//addBuyPriceAlertBelow();
			addBuyPriceAlerts(crossingPrice, DateFactory.getNowEndDate());
			break;
		case ABOVE_PRICE_CHANNEL:
			this.resetAlertLimitAbove(alert, crossingPrice);
			break;
		case MANUALUP:
		case MANUALDOWN:
			this.removeAlertOnThreshold(alert);
			break;
		default:
			LOGGER.error("Nothing to do for : " + alert);
		}

	}

	private void resetAlertLimitAbove(AlertOnThreshold alert, BigDecimal crossPrice) {
		this.removeAlertOnThreshold(alert);		
		addChannelAlerts(crossPrice);

	}

	private void addChannelAlerts(BigDecimal crossingPrice) {
		addMovingLimitAbove(crossingPrice);	
		addMovingLimitBelow(crossingPrice);
	}

	private void addMovingLimitAbove(BigDecimal crossingPrice) {

		this.removeAlertOnThresholdFor(AlertOnThresholdType.ABOVE_PRICE_CHANNEL);

		String message = "";
		BigDecimal limitPriceAboveRate = getEventsConfig().getLimitPriceAbove();
		BigDecimal limitAbovePrice = addPercentage(crossingPrice, limitPriceAboveRate);
		message = "(" +readablePercentOf(limitPriceAboveRate) + " above calculation price " + readableNumber(crossingPrice) +")";

		this.addAlertOnThreshold(ThresholdType.UP, limitAbovePrice, AlertOnThresholdType.ABOVE_PRICE_CHANNEL, message);

	}

	private void resetAlertLimitBelow(AlertOnThreshold alert, BigDecimal crossPrice) {
		this.removeAlertOnThreshold(alert);
		addChannelAlerts(crossPrice);

	}

	/**
	 * If price is up n% above avg buy price = > sell
	 * Shift up to current price if calculated threshold below current price
	 * @param currentDate 
	 * @param transactionPrice
	 */
	private void addAboveTakeProfitAlert(BigDecimal calculationPrice, Date currentDate) {
		try {

			this.removeAlertOnThresholdFor(AlertOnThresholdType.ABOVE_TAKE_PROFIT_LIMIT);

			BigDecimal sellLimitToPriceRate = getEventsConfig().getSellLimitToPrice();

			BigDecimal cashin = ps.getCashin(currentDate, ps.getTransactionCurrency());
			BigDecimal augmentedCashin = BigDecimal.ONE.add(sellLimitToPriceRate).multiply(cashin).setScale(10, RoundingMode.HALF_EVEN);
			BigDecimal cashout = ps.getCashout(currentDate, ps.getTransactionCurrency());
			BigDecimal aboveSellLimit = augmentedCashin.subtract(cashout).divide(ps.getQuantity(currentDate), 10, RoundingMode.HALF_EVEN);
			BigDecimal resultingPercentAboveAvgPrice = calculationPrice.divide(aboveSellLimit, 10, RoundingMode.HALF_EVEN).subtract(BigDecimal.ONE.setScale(4));

			String aboveMessage = "(" + readablePercentOf(sellLimitToPriceRate) + " gain. Price is " + readablePercentOf(resultingPercentAboveAvgPrice) + " away from threshold)";

			addAboveTakeProfitAlert(aboveSellLimit, aboveMessage);

		} catch (RuntimeException e) {
			LOGGER.error("Failed to update Portfolio share :"+this,e);
			throw e;
		}
	}

	/**
	 * If price is down n% below avg buy price => sell
	 * Shift down to current price if calculated threshold above current price
	 * @param currentDate 
	 * @param transactionPrice
	 */
	private void addBelowMaxLossAlert(BigDecimal calculationPrice, Date currentDate) {
		try {

			this.removeAlertOnThresholdFor(AlertOnThresholdType.BELOW_MAX_LOSS_LIMIT);

			BigDecimal sellLimitBelowPriceRate = getEventsConfig().getSellLimitBelowPrice();

			BigDecimal cashin = ps.getCashin(currentDate, ps.getTransactionCurrency());
			BigDecimal reducedCashin = BigDecimal.ONE.subtract(sellLimitBelowPriceRate).multiply(cashin).setScale(10, RoundingMode.HALF_EVEN);
			BigDecimal cashout = ps.getCashout(currentDate, ps.getTransactionCurrency());
			BigDecimal belowSellLimit = reducedCashin.subtract(cashout).divide(ps.getQuantity(currentDate), 10, RoundingMode.HALF_EVEN);
			BigDecimal resultingPercentBelowAvgPrice = calculationPrice.divide(belowSellLimit, 10, RoundingMode.HALF_EVEN).subtract(BigDecimal.ONE.setScale(4));

			String belowMessage = "(" + readablePercentOf(sellLimitBelowPriceRate) + " loss. Price is " + readablePercentOf(resultingPercentBelowAvgPrice) + " below threshold)";

			addAboveTakeProfitAlert(belowSellLimit, belowMessage);

		} catch (RuntimeException e) {
			LOGGER.error("Failed to update Portfolio share :"+this,e);
			throw e;
		}
	}

	private BigDecimal addPercentage(BigDecimal calculationPrice, BigDecimal rateToCalculationPrice) {
		BigDecimal percentToBeAdded = calculationPrice.multiply(rateToCalculationPrice).setScale(10, RoundingMode.HALF_EVEN);
		BigDecimal aboveSellLimit = calculationPrice.add(percentToBeAdded);
		return aboveSellLimit;
	}


	public void addAlertOnEvent(String eventInfoReference, MonitorLevel monitorLevel, String optionalMessage) {
		ps.getAlertsOnEvent().add(new AlertOnEvent(ps.getUpStreamPs(), eventInfoReference, monitorLevel, optionalMessage));
	}

	public void clearAlertOnEvent() {
		ps.getAlertsOnEvent().clear();
	}

	private class LatestOnlyPSBridge extends PortfolioShare {
		private static final long serialVersionUID = -4649581283474796279L;

		private PortfolioShare upStreamPs;

		private LatestOnlyPSBridge(PortfolioShare upStreamPs) {
			super(upStreamPs);
			this.upStreamPs = upStreamPs;
		}

		public PortfolioShare getUpStreamPs() {
			return upStreamPs;
		}

		public Set<AlertOnEvent> getAlertsOnEvent() {
			return upStreamPs.getAlertsOnEvent();
		}

		public Currency getTransactionCurrency() {
			return upStreamPs.getTransactionCurrency();
		}

		public Set<AlertOnThreshold> getAlertsOnThreshold() {
			return upStreamPs.getAlertsOnThreshold();
		}

		@Override
		public AbstractSharesList getPortfolio() {
			AbstractSharesList portfolio = upStreamPs.getPortfolio();
			if (portfolio instanceof Portfolio) {
				return new Portfolio((Portfolio) portfolio) {
					@Override
					public Boolean isLatestTransactionsOnly() {
						return true;
					};
				};
			} else {
				return portfolio;
			}
		}

	}
}
