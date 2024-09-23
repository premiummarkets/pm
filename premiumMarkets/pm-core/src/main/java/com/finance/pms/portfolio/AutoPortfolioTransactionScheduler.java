package com.finance.pms.portfolio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.time.DateUtils;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.files.TransactionType;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.portfolio.AutoPortfolioDelegate.InvestmentStrategy;
import com.finance.pms.portfolio.CalcSignalRecord.Status;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

public class AutoPortfolioTransactionScheduler {
	
	private static MyLogger LOGGER = MyLogger.getLogger(AutoPortfolioTransactionScheduler.class);

	private static String JSON_TRANS_SCHEDULE_PATH = System.getProperty("installdir") + File.separator + "autoPortfolioLogs" + File.separator + "transactions_schedule.json";

	private static TransactionSchedule transactionSchedule;
	static Gson gson;
	
	@FunctionalInterface
	interface PriceFunction {
	    BigDecimal execute(Date buyDate, Stock stock, Currency transactionCurrency) throws NoQuotationsException, IgnoredEventRangeException;
	}
	
	//TODO Auto: BnSStrategy which should be about how to calculate the transaction price with quotations of the day
	public enum BnSStrategy {
		//AutoCalcBackTestkerasPredictionFit1
		TRIGGERDAY_CLOSE((d, s, tc) -> {
			Quotations quotations = QuotationsFactories.getFactory().getBoundSafeEndDateQuotationsInstance(s, d, true, tc, ValidityFilter.CLOSE);
			BigDecimal buyPrice = quotations.get(quotations.size()-1).getCloseSplit();
			return buyPrice;
		}, (d, s, tc) -> {
			Quotations quotations = QuotationsFactories.getFactory().getBoundSafeEndDateQuotationsInstance(s, d, true, tc, ValidityFilter.CLOSE);
			BigDecimal sellPrice = quotations.get(quotations.size()-1).getCloseSplit();
			return sellPrice;
		}),
		//AutoCalcBackTestkerasPredictionFit
		EVENTDAY_CLOSE((d, s, tc) -> {
			Quotations quotations = QuotationsFactories.getFactory().getBoundSafeEndDateQuotationsInstance(s, d, true, tc, ValidityFilter.CLOSE);
			BigDecimal buyPrice = quotations.get(quotations.size()-1).getCloseSplit();
			return buyPrice;
		}, (d, s, tc) -> {
			Quotations quotations = QuotationsFactories.getFactory().getBoundSafeEndDateQuotationsInstance(s, d, true, tc, ValidityFilter.CLOSE);
			BigDecimal sellPrice = quotations.get(quotations.size()-1).getCloseSplit();
			return sellPrice;
		}),
		//AutoCalcBackTestkerasPredictionFit2
		EVENTDAY_LIM((d, s, tc) -> {			
			Quotations quotations = QuotationsFactories.getFactory().getBoundSafeEndDateQuotationsInstance(s, d, true, tc, ValidityFilter.CLOSE);
			QuotationUnit triggerDateQUnit = quotations.get(quotations.size()-2);
			QuotationUnit transDateQUnit = quotations.get(quotations.size()-1);
			BigDecimal triggerClose = triggerDateQUnit.getCloseSplit();
			BigDecimal lowPrice = transDateQUnit.getLowRaw();
			if (lowPrice.compareTo(triggerClose) <= 0) {
				return triggerClose;
			}
			throw new IgnoredEventRangeException("Event date: " + d + ". Buy limit is not met: low in " + transDateQUnit + " > triggering close in " + triggerDateQUnit, new Throwable());
		}, (d, s, tc) -> {
			Quotations quotations = QuotationsFactories.getFactory().getBoundSafeEndDateQuotationsInstance(s, d, true, tc, ValidityFilter.CLOSE);
			QuotationUnit triggerDateQUnit = quotations.get(quotations.size()-2);
			QuotationUnit transDateQUnit = quotations.get(quotations.size()-1);
			BigDecimal triggerClose = triggerDateQUnit.getCloseSplit();
			BigDecimal highPrice = transDateQUnit.getHighRaw();
			if (highPrice.compareTo(triggerClose) >= 0) {
				return triggerClose;
			}
			throw new IgnoredEventRangeException("Event date: " + d + ". Sell limit is not met: high in " + transDateQUnit + " < triggering close in " + triggerDateQUnit, new Throwable());
		}),
		//AutoCalcBackTestkerasPredictionFit3
		EVENTDAY_AVG((d, s, tc) -> {
			Quotations quotations = QuotationsFactories.getFactory().getBoundSafeEndDateQuotationsInstance(s, d, true, tc, ValidityFilter.CLOSE);
			QuotationUnit transDateQUnit = quotations.get(quotations.size()-1);
			BigDecimal highPrice =  transDateQUnit.getHighSplit();
			BigDecimal lowPrice = transDateQUnit.getLowSplit();
			BigDecimal buyPrice = highPrice.add(lowPrice).divide(new BigDecimal(2), 10, RoundingMode.HALF_EVEN);
			return buyPrice;
		}, (d, s, tc) -> {			
			Quotations quotations = QuotationsFactories.getFactory().getBoundSafeEndDateQuotationsInstance(s, d, true, tc, ValidityFilter.CLOSE);
			QuotationUnit transDateQUnit = quotations.get(quotations.size()-1);
			BigDecimal highPrice =  transDateQUnit.getHighSplit();
			BigDecimal lowPrice = transDateQUnit.getLowSplit();
			BigDecimal sellPrice = highPrice.add(lowPrice).divide(new BigDecimal(2), 10, RoundingMode.HALF_EVEN);
			return sellPrice;
		});
		
		PriceFunction buyFunc;
		PriceFunction sellFunc;

		BnSStrategy(PriceFunction buyFunction, PriceFunction sellFunction) {
			this.buyFunc = buyFunction;
			this.sellFunc = sellFunction;
		}

	}
	
	public static class TransactionSchedule {
		
		Map<String, SortedMap<Date, List<CalcSignalRecord>>> schedule;
		
		public TransactionSchedule() {
			 schedule = new HashMap<>();
		}
		
		private List<CalcSignalRecord> getRecordSignalsAt(String portfolio, Date date) {
			SortedMap<Date, List<CalcSignalRecord>> portfolioSignals = schedule.get(portfolio);
			if (portfolioSignals == null) {
				portfolioSignals = new TreeMap<>();
				schedule.put(portfolio, portfolioSignals);
			}
			List<CalcSignalRecord> dateSignals = portfolioSignals.get(date);
			if (dateSignals == null) {
				dateSignals = new ArrayList<>();
				portfolioSignals.put(date, dateSignals);
			}
			return dateSignals;
		}
	}
	
	private InvestmentStrategy investmentStrategy;
	private BnSStrategy bnSStrategy;

	
	public AutoPortfolioTransactionScheduler(InvestmentStrategy investmentStrategy, BnSStrategy bnSStrategy) {
		super();
		this.investmentStrategy = investmentStrategy;
		this.bnSStrategy = bnSStrategy;
		if (transactionSchedule == null) {
			synchronized (AutoPortfolioTransactionScheduler.class) {
				gson = new GsonBuilder()
						.setPrettyPrinting()
						.registerTypeAdapter(SymbolEvents.class, new JsonSerializer<SymbolEvents>() {

							@Override
							public JsonElement serialize(SymbolEvents src, Type typeOfSrc, JsonSerializationContext context) {
								JsonObject jsonObject = new JsonObject();
								jsonObject.addProperty("symbol", src.getSymbol());
								jsonObject.addProperty("isin", src.getIsin());
								jsonObject.addProperty("events", src.toAutoPortfolioLog());
								return jsonObject;
							}
							
						})
						.registerTypeAdapter(SymbolEvents.class, new JsonDeserializer<SymbolEvents>() {
							@Override
							public SymbolEvents deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
								JsonObject asJsonObject = json.getAsJsonObject();
								Stock stock = DataSource.getInstance().getShareDAO().loadStockBy(asJsonObject.getAsJsonPrimitive("symbol").getAsString(), asJsonObject.getAsJsonPrimitive("isin").getAsString());
								return new SymbolEvents(stock);
							}
						})
						.setDateFormat("yyyyMMdd")
						.enableComplexMapKeySerialization()
						.create();
				initTrSch();
			}
		}
		
	}

	private static void initTrSch() {
		File trSchFile = new File(JSON_TRANS_SCHEDULE_PATH);
		if (!trSchFile.exists()) {
			storeTransSched(new AutoPortfolioTransactionScheduler.TransactionSchedule());
		}
		
		transactionSchedule = null;
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(trSchFile))) {
			transactionSchedule = gson.fromJson(bufferedReader, new TypeToken<AutoPortfolioTransactionScheduler.TransactionSchedule>() {}.getType());
		} catch (Exception e) {
			LOGGER.error("Inconsistencies: " + transactionSchedule + " in " + JSON_TRANS_SCHEDULE_PATH, e);
			throw new RuntimeException(e);
		} finally {
			if (transactionSchedule == null) {
				LOGGER.warn("File is empty or inconsistent: " + JSON_TRANS_SCHEDULE_PATH);
				String copyPath = JSON_TRANS_SCHEDULE_PATH + "_" + new Date().getTime();
				try {
					Files.copy(trSchFile, new File(copyPath));
				} catch (IOException e) {
					LOGGER.warn("Could not backup file " + JSON_TRANS_SCHEDULE_PATH + " to " + copyPath + ": " + e);
				}
				transactionSchedule = new TransactionSchedule();
			}
		}
	}
	
	private static void storeTransSched(TransactionSchedule trSch) {
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(JSON_TRANS_SCHEDULE_PATH))) {
			gson.toJson(trSch, bufferedWriter);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//TODO Auto: Params: current date, for this portfolio, 
	//for this analysis, for this indicator, an investment strategy, + ?a bnsStratgey?
	public void runScheduled(AutoPortfolio portfolio, Date currentDate) {
		
		try {
			
			// D-n..D-2, D-1 (event triggering day), D0 (event rendered), D1 (next day)
			// In this calculation, we always need transDate < currentDate in order to get the quotations price for the transaction. Hence the transaction is applied backward. In reality is should be transDate == eventDate == currentDate
			synchronized (AutoPortfolioTransactionScheduler.class) {
				
				final Date eventDate; //Is the morning after the closure generating signal (Say D0  if we had a Bear on D-2 and a Bull on D-1)
				final Date transDate; //Realistically >= eventDate. Testing == eventDate -1
				switch (bnSStrategy) {
				case TRIGGERDAY_CLOSE:  //Transaction on triggering day at close price (~ bar chart r/ur). This is not realistic because we need to wait for closure to get a signal rendered.
					eventDate = currentDate;
					transDate = DateUtils.addDays(eventDate, -1);
					break;
				case EVENTDAY_CLOSE:   //Transaction on event day at close price
					eventDate = DateUtils.addDays(currentDate, -1); //In order to calculate the previous day price we need to wait for the market closure. Although in reality it happens on the day (event day)
					transDate = eventDate; //Transaction happens during the day after the signal == eventDate
					break;
				case EVENTDAY_LIM: 	//Transaction on event day with a limit set from triggering day price close (TODO check if there could be an OHLC from D-1 to D0 pattern?)
					eventDate = DateUtils.addDays(currentDate, -1);
					transDate = eventDate;
					//TODO this needs an additional algorithm for the calculation of the price.
					break;
				case EVENTDAY_AVG: 	//Transaction on event day with average OHLC price at event day
					eventDate = DateUtils.addDays(currentDate, -1);
					transDate = eventDate;
					break;
				default:
					throw new Exception("Unsupported bNs startegy " + bnSStrategy);
				}
				
				List<CalcSignalRecord> signalRecords = transactionSchedule.getRecordSignalsAt(portfolio.getName(), eventDate);
				
				signalRecords.stream()
				.filter(sr -> sr.getStatus().equals(Status.OPEN))
				.forEach(sr -> {//Can deal with multiple stocks
					switch(sr.getMovement()) {
					case AOUT:
						sr.setStatus(doSell(transDate, portfolio, sr.getStock()));
						break;
					case AIN:
						sr.setStatus(doBuy(transDate, portfolio, sr.getStock()));
						break;
					default:
						throw new RuntimeException("Unsupported TransactionType strategy " + sr.getMovement());
					}
				});
			
				storeTransSched(transactionSchedule);
			}
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
	}
	
	private void isValidDateForLine(Date latestEventDateAndNewBuyDate, PortfolioShare existingPs) throws IgnoredEventDateException {
		if (existingPs != null) {
			LOGGER.info("Checking existing transactions: " + ((AutoPortfolio) existingPs.getPortfolio()).getTransactions());
			Date lastTransactionDate = existingPs.getLastTransactionDate();
			LOGGER.info("Event validity for line: is event date: " + latestEventDateAndNewBuyDate + " (after or equal?) last transaction: " + lastTransactionDate);
			if (latestEventDateAndNewBuyDate.compareTo(lastTransactionDate) < 0) {
				throw new IgnoredEventDateException(
						"Event (" + latestEventDateAndNewBuyDate + ") already processed: " + existingPs + " last transaction on the " + lastTransactionDate, 
						new Throwable());
			}
		}
	}
	
	private Status doSell(Date sellDate, AutoPortfolio portfolio, Stock stock) {
		
		
		PortfolioShare alreadyBoughtShare = portfolio.getListShares().get(stock);
		if (alreadyBoughtShare == null || !alreadyBoughtShare.isOwned(DateFactory.getNowEndDate(), true) ) {
			LOGGER.info("Won't sell at " + sellDate + " with " + stock.getSymbol() + ": is not in owned at present.");
			return Status.CANCELED;
		}
		
		LOGGER.info("Selling. Portfolio share is owned: " + alreadyBoughtShare);
		try {
			
			isValidDateForLine(sellDate, alreadyBoughtShare);
			
			BigDecimal sellPrice = bnSStrategy.sellFunc.execute(sellDate, alreadyBoughtShare.getStock(), alreadyBoughtShare.getTransactionCurrency());
			double fee = 0.0; //0.01;
			BigDecimal sellPriceNet = sellPrice.multiply(new BigDecimal(1 - (fee/(1+fee)))).setScale(10, RoundingMode.HALF_EVEN); //In reality the price is unknown at that point of iteration as this is the next day market price ..
			
			BigDecimal quantityProrata;
			BigDecimal quantity = alreadyBoughtShare.getQuantity(DateFactory.getNowEndDate(), false);
			
			BigDecimal sellAmount = (InvestmentStrategy.INFINITQUANTITY.equals(investmentStrategy))?
					BigDecimal.valueOf((Math.max(alreadyBoughtShare.getQuantity(DateFactory.getNowEndDate(), false).doubleValue(), portfolio.getNominalTransactionAmount().doubleValue()))):
					null;
			
			if (sellAmount != null) {
				quantityProrata = sellAmount.divide(sellPriceNet, 10, RoundingMode.HALF_EVEN);
				quantityProrata = quantityProrata.min(quantity);
			} else {
				quantityProrata = quantity;
			}

			portfolio.updateShare(alreadyBoughtShare, quantityProrata, sellDate, sellPriceNet, TransactionType.AOUT);
			LOGGER.info("Sold: " + quantityProrata + " of " + alreadyBoughtShare + ", quantity left: " + alreadyBoughtShare.getQuantity(DateFactory.getNowEndDate(), false));
			
			return Status.DONE;
			
		} catch(IgnoredEventRangeException e) {
			LOGGER.warn("Can't sell " + stock + " in AutoPortfolio - trigger close outside event day range: " + portfolio.getName() + ". " + e);
			return Status.CANCELED;
		} catch (NoQuotationsException e) 	{
			LOGGER.warn("Can't sell " + alreadyBoughtShare.getStock() + " from " + portfolio.getName( ) + 
					" - no quotations on the " + sellDate + ": " + portfolio.getListShares().get(alreadyBoughtShare.getStock()));
			return Status.ERROR;
		} catch (InvalidQuantityException e) {
			LOGGER.error("Can't sell " + alreadyBoughtShare.getStock() + " from " + portfolio.getName() + 
					" - invalid quantity on the " + sellDate + ": " + portfolio.getListShares().get(alreadyBoughtShare.getStock()), e);
			return Status.ERROR;
		} catch (IgnoredEventDateException e) {
			LOGGER.warn("Can't sell: event invalid or already processed. " + e);
			return Status.ERROR;
		} catch (Exception e) {
			LOGGER.error("Can't sell " + stock + " from " + portfolio.getName() + " on the " + sellDate + ": " + portfolio.getListShares().get(stock), e);
			return Status.ERROR;
		}

	}
	
	private Status doBuy(Date buyDate, AutoPortfolio portfolio, Stock stock) {
		
		//Check if already bought
		PortfolioShare alreadyBoughtShare = portfolio.getListShares().get(stock);
		if (
				!InvestmentStrategy.INFINITQUANTITY.equals(portfolio.getTransactionScheduler().getInvestmentStrategy()) &&
				(alreadyBoughtShare != null && alreadyBoughtShare.isOwned(DateFactory.getNowEndDate(), true))
		) {
			LOGGER.info("Won't buy at " + buyDate + " with " + stock.getSymbol() + ". Already bought (buy once policy).");
			return Status.CANCELED;
		}
		
		LOGGER.info("Buying. Portfolio share is NOT owned or INFINITQUANTITY is granted: " + stock);
		try {
			isValidDateForLine(buyDate, alreadyBoughtShare);
			
			// XXX what if we buy twice the same share but with a different currency?
			Currency transactionCurrency = (portfolio.inferPortfolioCurrency() == null) ? stock.getMarketValuation().getCurrency() : portfolio.inferPortfolioCurrency();
			BigDecimal buyPrice = bnSStrategy.buyFunc.execute(buyDate, stock, transactionCurrency);
			double fee = 0.0; //0.01;
			BigDecimal buyPriceNet = buyPrice.multiply(new BigDecimal(1 + fee)).setScale(10, RoundingMode.HALF_EVEN);


			BigDecimal availableCash = canBuy(portfolio);
			if (availableCash.compareTo(portfolio.getMinimumTransactionAmount()) >= 0) {
				BigDecimal effectiveAmountWithDrawn = portfolio.withdrawCash(buyDate, availableCash, transactionCurrency);
				BigDecimal quantity = effectiveAmountWithDrawn.divide(buyPriceNet, 10, RoundingMode.HALF_EVEN);
				LOGGER.info("Buying: " + quantity + " of " + stock + " on the " + buyDate + ". " +
							"Requested " + transactionCurrency + " amount: " + availableCash + ". " +
							"Effective " + portfolio.inferPortfolioCurrency() + " amount: " + effectiveAmountWithDrawn + ". ");

				if (buyPriceNet.compareTo(BigDecimal.ZERO) == 0) {
					throw new NoQuotationsException("Invalid stock " + stock + " with price " + buyPriceNet + " on " + buyDate + ". Can't be bought");
				}
				PortfolioShare portfolioShare = portfolio.addOrUpdateShare(stock, quantity, buyDate, buyPriceNet, MonitorLevel.ANY, transactionCurrency, TransactionType.AIN);
				
				LOGGER.info("Bought: " + quantity + " of " + portfolioShare + ", quantity left: " + portfolioShare.getQuantity(DateFactory.getNowEndDate(), false));
				return Status.DONE;
			} else {
				throw new NoCashAvailableException("Cash left: " + availableCash + ", buyStrategy: " + investmentStrategy + ", minimum required: " + portfolio.getMinimumTransactionAmount());
			}
			
		} catch (NoCashAvailableException e) {
			LOGGER.info("Can't buy " + stock + " in AutoPortfolio - no cash left: " + portfolio.getName() + ". " + e);
			return Status.CANCELED;
		} catch(IgnoredEventRangeException e) {
			LOGGER.warn("Can't buy " + stock + " in AutoPortfolio - trigger close outside event day range: " + portfolio.getName() + ". " + e);
			return Status.CANCELED;
		} catch (NoQuotationsException e) {
			LOGGER.warn("Can't buy " + stock + " in AutoPortfolio - no quotations: " + portfolio.getName());
			return Status.ERROR;
		} catch (InvalidQuantityException e) {
			LOGGER.error("Can't buy " + stock + " in AutoPortfolio - invalid quantity: " + portfolio.getName(), e);
			return Status.ERROR;
		} catch (IgnoredEventDateException e) {
			LOGGER.warn("Can't buy: event invalid or already processed. " + e);
			return Status.ERROR;
		} catch (Exception e) {
			LOGGER.error("Can't buy " + stock + " from " + portfolio.getName() + " on the " + buyDate + ": " + portfolio.getListShares().get(stock), e);
			return Status.ERROR;
		}
			
	}

	protected BigDecimal canBuy(AutoPortfolio portfolio) {
		BigDecimal availableCash = BigDecimal.ZERO;
		switch (investmentStrategy) {
			case LIMITROWS:
				int ownedSharesSize = portfolio.getOwnedPorfolioShares().size();
				if (ownedSharesSize == portfolio.getMaximumNumberOfLines()) {
					LOGGER.info("Strategy " + investmentStrategy + ", Maximum number of lines reached.");
					availableCash = BigDecimal.ZERO;
				} else if (ownedSharesSize > portfolio.getMaximumNumberOfLines()) {
					throw new RuntimeException("Strategy " + investmentStrategy + ", Maximum number of lines over taken");
				} else {
					availableCash = portfolio.getNominalTransactionAmount();
				}
				break;
			case INFINITCASH:
			case INFINITQUANTITY:
				availableCash = portfolio.getNominalTransactionAmount();
				break;
			case REINVEST:
				availableCash = portfolio.getAvailableCash();
				//availableCash = (availableCash.compareTo(defaultTransactionAmount) >= 0)? availableCash : BigDecimal.ZERO;
				break;
			default:
				throw new UnsupportedOperationException();
		}
		
		LOGGER.info("Strategy " + investmentStrategy + ", available cash: " + availableCash);
		return availableCash;
	}


	public void add(CalcSignalRecord recordSignal) {
		List<CalcSignalRecord> recordSignalsAt = transactionSchedule.getRecordSignalsAt(recordSignal.getPortfolioName(), recordSignal.getSignalDate());
		recordSignalsAt.add(recordSignal);
		synchronized (AutoPortfolioTransactionScheduler.class) {
			storeTransSched(transactionSchedule);
		}
	}
	
	
	public InvestmentStrategy getInvestmentStrategy() {
		return investmentStrategy;
	}

	public BnSStrategy getBnSStrategy() {
		return bnSStrategy;
	}



}
