package com.finance.pms.events.calculation;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.scoring.TunedConf;
import com.finance.pms.events.scoring.TunedConfMgr;
import com.finance.pms.threads.ObserverMsg;

public class SelectedIndicatorsCalculationService {

	private static MyLogger LOGGER = MyLogger.getLogger(SelectedIndicatorsCalculationService.class);

	public class QuotesBounds {

		private Date adjustedStartDate;
		private Date adjustedEndDate;

		public QuotesBounds(Date adjustedStartDate, Date adjustedEndDate) {
			this.adjustedStartDate = adjustedStartDate;
			this.adjustedEndDate = adjustedEndDate;
		}

		public Date getAdjustedStartDate() {
			return adjustedStartDate;
		}

		public Date getAdjustedEndDate() {
			return adjustedEndDate;
		}

	}

	public SelectedIndicatorsCalculationService() {
	}

	public List<SymbolEvents> calculate(
			Date startDate, Date endDate, String eventListName, Map<Stock, List<EventInfo>> stocksEventInfos, Observer... observers) throws IncompleteDataSetException {

		Boolean isDataSetComplete = true;
		List<SymbolEvents> allEvents = new ArrayList<SymbolEvents>();
		List<Stock> failingStocks = new ArrayList<Stock>();

		ExecutorService executor = Executors.newFixedThreadPool(Integer.valueOf(MainPMScmd.getMyPrefs().get("indicatorcalculator.semaphore.nbthread","20")));
		try {

			Map<Stock, List<Future<SymbolEvents>>> futuresMap = new HashMap<>();

			int obsSize = stocksEventInfos.values().stream().map(v -> v.size()).reduce(0, (a, e) -> a + e);
			Arrays.stream(observers).forEach(o -> o.update(null, new ObserverMsg(null, ObserverMsg.ObsKey.INITMSG, obsSize)));

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yy HH:mm:ss");
			for(Stock stock: stocksEventInfos.keySet()) {

				try {

					LOGGER.guiInfo("Calculation requested : events for stock " + stock.toString() + " between " + dateFormat.format(startDate) + " and " + dateFormat.format(endDate));
					QuotesBounds adjCalcDatesToQs = adjustCalculationDatesToQuotations(stock, startDate, endDate);
					LOGGER.info("Calculation adjusted : events for stock " + stock.toString() + " between " + dateFormat.format(adjCalcDatesToQs.getAdjustedStartDate()) + " and " + dateFormat.format(adjCalcDatesToQs.getAdjustedEndDate()));

					List<Future<SymbolEvents>> eventInfosFutures = new ArrayList<>();
					for(EventInfo eventInfo: stocksEventInfos.get(stock)) {
						Callable<SymbolEvents> calculationRunnable = new SelectedIndicatorsCalculationThread(stock, adjCalcDatesToQs.getAdjustedStartDate(), adjCalcDatesToQs.getAdjustedEndDate(), eventInfo, eventListName, observers);
						Future<SymbolEvents> submittedRunnable = executor.submit(calculationRunnable);
						eventInfosFutures.add(submittedRunnable);
					}
					futuresMap.put(stock, eventInfosFutures);

				} catch (Exception e1) {
					LOGGER.warn("Could not proceed with initialisation of calculation for " + stock + ": " + e1.getMessage());
					//We update the tunedConfs assuming subsequent calculations will fail as well.
					stocksEventInfos.get(stock).stream().forEach( ei -> {
						TunedConf tunedConf = TunedConfMgr.getInstance().loadUniqueNoRetuneConfig(stock, eventListName, ei.getEventDefinitionRef());
						TunedConfMgr.getInstance().updateConf(tunedConf, false);
					});
					isDataSetComplete = false;
					failingStocks.add(stock);
				}

			}

			for (Stock stock : futuresMap.keySet()) {

				SymbolEvents stockAllSymbolEvents = new SymbolEvents(stock);
				allEvents.add(stockAllSymbolEvents);
				for(Future<SymbolEvents> stockEventInfoFuture : futuresMap.get(stock)) {

					try {
						SymbolEvents symbolEvents = stockEventInfoFuture.get();
						stockAllSymbolEvents.addEventResultElement(symbolEvents);
						stockAllSymbolEvents.addAllCalculationOutput(symbolEvents.getCalculationOutputs());
					} catch (ExecutionException executionException) {
						LOGGER.warn("Failed : events for stock " + stock.toString() + " between "+dateFormat.format(startDate) + " and " + dateFormat.format(endDate), executionException);
						Throwable cause = executionException.getCause();
						if (cause instanceof IncompleteDataSetException) {
							(((IncompleteDataSetException) cause).getSymbolEvents()).stream().forEach(se -> {
								stockAllSymbolEvents.addEventResultElement(se);
								stockAllSymbolEvents.addAllCalculationOutput(se.getCalculationOutputs());
							});
							isDataSetComplete = false;
							failingStocks.add(stock);
						}
					} catch (Exception e) {
						LOGGER.error("Failed : events for stock " + stock.toString() + " between " + dateFormat.format(startDate) + " and " + dateFormat.format(endDate), e);
						isDataSetComplete = false;
						failingStocks.add(stock);
					}
				}

				//Output //TODO UI?
				//Map<EventInfo, SortedMap<Date, double[]>> calculationOutput = symbolEventsMap.get(stock).getCalculationOutputs();
				//if (calculationOutput == null) calculationOutput = new HashMap<EventInfo, SortedMap<Date,double[]>>();
				//calculatedOutputReturn.put(stock, calculationOutput);

			}

			try {
				Integer nbEvents = allEvents.stream().map(se -> se.getDataResultMap().size()).reduce( 0, (r, mapSize) -> r + mapSize);
				LOGGER.guiInfo("Storing "+nbEvents+" events for "+allEvents.size()+" stocks.");
				EventsResources.getInstance().crudCreateEvents(allEvents, eventListName);
				LOGGER.guiInfo("Stored "+nbEvents+" events for "+allEvents.size()+" stocks.");
			} catch (Exception e) { 
				isDataSetComplete = false;
				if (e.getCause() != null && e.getCause() instanceof SQLIntegrityConstraintViolationException) {
					LOGGER.warn("Intercepted : " + e + " -> IncompleteDataset");
				} else {
					LOGGER.error(e,e);
				}
			}

		} catch (Throwable e) {
			LOGGER.error(e);
		} finally {
			executor.shutdownNow();
		}

		if (!isDataSetComplete) {
			throw new IncompleteDataSetException(failingStocks, allEvents, null, "All Indicators couldn't be calculated properly. This may invalidates the dataset for further usage. Stock concerned : " + failingStocks);
		}

		return allEvents;
	}

	private QuotesBounds adjustCalculationDatesToQuotations(Stock stock, Date startDate, Date endDate) {

		//Adjust calculation date to available quotes
		Date minimumStartDate = TunedConfMgr.getInstance().minimumStartDate(stock);
		if (minimumStartDate.before(startDate) || minimumStartDate.equals(startDate)) {
			minimumStartDate = startDate;
		} else {
			LOGGER.info("Start date calculation adjusted : for stock " + stock.toString() + " is now starting on " + minimumStartDate);
		}
		if (minimumStartDate.after(endDate) || minimumStartDate.equals(endDate)) {
			throw new RuntimeException("Not enough quotations to calculate (invalid date bounds) : for stock " + stock.toString() + " between " + minimumStartDate + " and " + endDate);
		}
		Date maximumEndDate = TunedConfMgr.getInstance().maximumEndDate(stock);
		if (maximumEndDate.after(endDate) || maximumEndDate.equals(endDate)) {
			maximumEndDate = endDate;
		} else {
			LOGGER.info("End Date calculation adjusted : events for stock " + stock.toString() + " is now ending on  " + maximumEndDate);
		}
		if (maximumEndDate.before(minimumStartDate) || maximumEndDate.equals(minimumStartDate)) {
			throw new RuntimeException("Not enough quotations to calculate (invalid date bounds) : for stock " + stock.toString() + " between " + minimumStartDate + " and " + maximumEndDate);
		}

		return new QuotesBounds(minimumStartDate, maximumEndDate);

	}

}
