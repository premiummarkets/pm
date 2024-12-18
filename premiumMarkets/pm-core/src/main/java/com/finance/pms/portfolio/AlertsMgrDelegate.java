package com.finance.pms.portfolio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
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
	private PortfolioShare portfolioShare;
	private Portfolio portfolio;
	private Currency psCurrency;

	public AlertsMgrDelegate(PortfolioShare portfolioShare) {
		this.portfolioShare = portfolioShare;
		this.portfolio = (Portfolio) portfolioShare.getPortfolio();
		this.psCurrency = portfolioShare.getTransactionCurrency();
	}

	public void addBuyAlerts(BigDecimal transcationPrice, Date currentDate) {

		if (portfolio.getQuantityFor(portfolioShare, null, currentDate, true).compareTo(BigDecimal.ZERO) > 0) {
			addBuyPriceAlerts(transcationPrice, currentDate);
			BigDecimal avgCostPerUnit = portfolio.getPriceUnitCostFor(portfolioShare, null, currentDate, psCurrency, true);
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
		for (AlertOnThreshold alert : portfolioShare.getAlertsOnThreshold()) {
			if (alert.getThresholdType().equals(ThresholdType.UP)) alertsUp.add(alert);
		}
		return alertsUp;
	}

	public Set<AlertOnThreshold> getAlertsOnThresholdDown() {
		Set<AlertOnThreshold> alertsDown = new HashSet<AlertOnThreshold>();
		for (AlertOnThreshold alert : portfolioShare.getAlertsOnThreshold()) {
			if (alert.getThresholdType().equals(ThresholdType.DOWN)) alertsDown.add(alert);
		}
		return alertsDown;
	}

	public void addAlertOnThreshold(ThresholdType threshold, BigDecimal value, AlertOnThresholdType alertType, String message) {
		if (value != null) {
			portfolioShare.getAlertsOnThreshold().add(new AlertOnThreshold(portfolioShare, threshold, alertType, value, message));
		}
	}

	public void removeAlertOnThreshold(AlertOnThreshold alert) {
		portfolioShare.getAlertsOnThreshold().remove(alert);
	}

	private void removeAlertOnThresholdFor(AlertOnThresholdType alertType) {
		HashSet<AlertOnThreshold> ret = getAlertsOnThresholdFor(alertType);
		portfolioShare.getAlertsOnThreshold().removeAll(ret);
	}

	public HashSet<AlertOnThreshold> getAlertsOnThresholdFor(AlertOnThresholdType alertType) {
		HashSet<AlertOnThreshold> ret = new HashSet<AlertOnThreshold>();
		for (AlertOnThreshold alert : portfolioShare.getAlertsOnThreshold()) {
			if (alertType.equals(alert.getAlertType())) {
				ret.add(alert);
			}
		}
		return ret;
	}

	private void removeAllAlertsOnThreshold() {
		Set<AlertOnThreshold> alertsOnThresholdCp = new HashSet<AlertOnThreshold>(portfolioShare.getAlertsOnThreshold());
		for (AlertOnThreshold alertOnThreshold : alertsOnThresholdCp) {
			removeAlertOnThreshold(alertOnThreshold);
		}
	}

	private void addBuyPriceAlerts(BigDecimal currentPrice, Date currentDate) {
		this.removeAlertOnThresholdFor(AlertOnThresholdType.AVG_BUY_PRICE);
		if (currentPrice.compareTo(portfolio.getPriceUnitCostFor(portfolioShare, null, currentDate, psCurrency, true)) <= 0) addBuyPriceAlertAbove(currentDate);
		if (currentPrice.compareTo(portfolio.getPriceUnitCostFor(portfolioShare, null, currentDate, psCurrency, true)) >= 0) addBuyPriceAlertBelow(currentDate);	
	}

	private void addBuyPriceAlertBelow(Date currentDate) {
		BigDecimal avgBuyPrice = portfolio.getPriceUnitCostFor(portfolioShare, null, currentDate, psCurrency, true);
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.addAlertOnThreshold(ThresholdType.DOWN, avgBuyPrice, AlertOnThresholdType.AVG_BUY_PRICE, "Calculation price is avg buy price " + readableNumber(avgBuyPrice) + " on the " + df.format(currentDate));
	}

	private void addBuyPriceAlertAbove(Date currentDate) {
		BigDecimal avgBuyPrice = portfolio.getPriceUnitCostFor(portfolioShare, null, currentDate, psCurrency, true);
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.addAlertOnThreshold(ThresholdType.UP, avgBuyPrice, AlertOnThresholdType.AVG_BUY_PRICE, "Calculation price is avg buy price " + readableNumber(avgBuyPrice) + " on the " + df.format(currentDate));
	}

	private String readableNumber(BigDecimal aNumber) {
		NumberFormat format = new DecimalFormat("#0.00");
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
		InOutWeighted weightedInOut = portfolio.getInflatWeightedInvestedFor(portfolioShare, currentDate, psCurrency, true);
		BigDecimal sellLimitGuardPrice;
		if (!weightedInOut.isEmpty()) {
			BigDecimal quantity = portfolio.getQuantityFor(portfolioShare, null, currentDate, true);
			sellLimitGuardPrice = weightedInOut.getWeightedInvestedStillIn().divide(quantity, 10, RoundingMode.HALF_EVEN);
			if (sellLimitGuardPrice.compareTo(BigDecimal.ZERO) <= 0) sellLimitGuardPrice = BigDecimal.ZERO;
			sellLimitGuardPriceRate = (avgBuyPrice.compareTo(BigDecimal.ZERO) > 0)?
					sellLimitGuardPrice.divide(avgBuyPrice, 10, RoundingMode.HALF_EVEN).subtract(BigDecimal.ONE.setScale(4)):
					BigDecimal.ZERO;
		} else {
			sellLimitGuardPriceRate = getEventsConfig().getSellLimitGuardPrice();
			sellLimitGuardPrice = addPercentage(avgBuyPrice, sellLimitGuardPriceRate);
		}

		if (sellLimitGuardPrice.compareTo(portfolioShare.getPriceClose(currentDate, psCurrency)) <= 0) {
			addWeightedZeroProfitAlertGuard(sellLimitGuardPrice);
		} else {
			String aboveMessage = "Will set a guard when "+ readablePercentOf(sellLimitGuardPriceRate) + " above calculation price " + readableNumber(avgBuyPrice);
			addWeightedZeroProfitAlertGuardSetter(sellLimitGuardPrice, aboveMessage);
		}

	}

	private String readablePercentOf(BigDecimal aPercentage) {
		NumberFormat format = new DecimalFormat("#0.00 %");
		String formated = format.format(aPercentage);
		return formated;
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

		String belowMessage = readablePercentOf(limitPriceBelowRate) + " below calculation price " + readableNumber(crossingPrice);

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
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Params : " + alert);

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
			LOGGER.error("Nothing to do for: " + alert);
		}

	}

	public void resetCrossUp(AlertOnThreshold alert, BigDecimal crossingPrice) {
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Params : " + alert);

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
			LOGGER.error("Nothing to do for: " + alert);
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
		message = readablePercentOf(limitPriceAboveRate) + " above calculation price " + readableNumber(crossingPrice);

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

			BigDecimal priceUnitCost = portfolio.getPriceUnitCostFor(portfolioShare, null, currentDate, psCurrency, true);
			
			BigDecimal aboveThresholdSellPrice = BigDecimal.ZERO;
			if (priceUnitCost.compareTo(BigDecimal.ZERO) > 0) {
				aboveThresholdSellPrice = BigDecimal.ONE.add(sellLimitToPriceRate).multiply(priceUnitCost);
			}
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String takeProfitMessage = 
					readablePercentOf(sellLimitToPriceRate) + " threshold reached. " +
					"\nWith " + 
					"limit: " + readableNumber(aboveThresholdSellPrice) + ", unit cost: " + readableNumber(priceUnitCost) +  " (latest line)" +
					" on the " + df.format(currentDate);

			addAboveTakeProfitAlert(aboveThresholdSellPrice, takeProfitMessage);

		} catch (RuntimeException e) {
			LOGGER.error("Failed to update Portfolio share: " + this, e);
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

			BigDecimal cashin = portfolio.getCashInFor(portfolioShare, null, currentDate, psCurrency, true, false);
			BigDecimal reducedCashin = BigDecimal.ONE.subtract(sellLimitBelowPriceRate).multiply(cashin).setScale(10, RoundingMode.HALF_EVEN);
			BigDecimal cashout = portfolio.getCashOutFor(portfolioShare, null, currentDate, psCurrency, true, false);
			BigDecimal quantity = portfolio.getQuantityFor(portfolioShare, null, currentDate, true);
			BigDecimal belowSellLimit = reducedCashin.subtract(cashout).divide(quantity, 10, RoundingMode.HALF_EVEN);
			BigDecimal resultingPercentBelowAvgPrice = calculationPrice.divide(belowSellLimit, 10, RoundingMode.HALF_EVEN).subtract(BigDecimal.ONE.setScale(4));

			String belowMessage = readablePercentOf(sellLimitBelowPriceRate) + " loss. Price is " + readablePercentOf(resultingPercentBelowAvgPrice) + " below threshold";

			addAboveTakeProfitAlert(belowSellLimit, belowMessage);

		} catch (RuntimeException e) {
			LOGGER.error("Failed to update Portfolio share :" + this, e);
			throw e;
		}
	}

	private BigDecimal addPercentage(BigDecimal calculationPrice, BigDecimal rateToCalculationPrice) {
		BigDecimal percentToBeAdded = calculationPrice.multiply(rateToCalculationPrice).setScale(10, RoundingMode.HALF_EVEN);
		BigDecimal aboveSellLimit = calculationPrice.add(percentToBeAdded);
		return aboveSellLimit;
	}


	public void addAlertOnEvent(String eventInfoReference, MonitorLevel monitorLevel, String optionalMessage) {
		portfolioShare.getAlertsOnEvent().add(new AlertOnEvent(portfolioShare, eventInfoReference, monitorLevel, optionalMessage));
	}

	public void clearAlertOnEvent() {
		portfolioShare.getAlertsOnEvent().clear();
	}
	
}
