package com.finance.pms.screening;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.ShareDAO;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.calculation.WarningException;
import com.finance.pms.events.calculation.antlr.NextToken;
import com.finance.pms.events.calculation.parametrizedindicators.ParameterizedIndicatorsBuilder;
import com.finance.pms.events.operations.CalculateThreadExecutor;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.conditional.EventInfoOpsCompoOperation;
import com.finance.pms.events.operations.conditional.EventMapValue;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.parameterized.ParameterizedOperationBuilder;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.OTFTuningFinalizer;
import com.finance.pms.events.scoring.dto.TuningResDTO;
import com.finance.pms.portfolio.PortfolioDAO;
import com.finance.pms.threads.ConfigThreadLocal;
import com.google.common.collect.Lists;

public class BestHTClassifier {
	
	private static MyLogger LOGGER = MyLogger.getLogger(BestHTClassifier.class);
	
	private final String TMP_PATH = System.getProperty("installdir") + File.separator + "tmp" + File.separator;

	private ParameterizedIndicatorsBuilder parameterizedIndiactorsBuilder = SpringContext.getSingleton().getBean(ParameterizedIndicatorsBuilder.class);
	private ParameterizedOperationBuilder parameterizedOperationBuilder = SpringContext.getSingleton().getBean(ParameterizedOperationBuilder.class);
	private OTFTuningFinalizer tuningFinalizer = (OTFTuningFinalizer) SpringContext.getSingleton().getBean("tuningFinalizer");
	private PortfolioDAO portfolioDAO = SpringContext.getSingleton().getBean(PortfolioDAO.class);
	private ShareDAO shareDAO = SpringContext.getSingleton().getBean(ShareDAO.class);
	
	//shiftWrapper(NaN,bandNormalizer(-1,1,0,NaN,leftShifter(31,sma(21,periodicLn_(1,sma(42,close))))))
	private String templateOperationFormulae = "shiftWrapper(NaN,bandNormalizer(-1,1,0,NaN,leftShifter(NaN,sma(NaN,periodicLn_(1,sma(NaN,close))))))";
//	private String templateOperationFormulae = "shiftWrapper(NaN,bandNormalizer(-1,1,0,NaN,leftShifter(NaN,sma(NaN,periodicLn_(1,sma(NaN,bandNormalizer(0,100,NaN,NaN,subtraction_(close,stat:msimplereg(NaN,close)))))))))";
	private String templateIndicatorformulae = "is bullish when operationXX is above threshold 0; is bearish when operationXX is below threshold 0;";
	
//	private String simpleReg = "stat:msimplereg(NaN,close)";

	private Set<String> createdOperations = Collections.synchronizedSet(new HashSet<>());
	private Set<String> createdIndicators = Collections.synchronizedSet(new HashSet<>());
	
	protected BestHTClassifier() {
		super();
		if (!this.parameterizedOperationBuilder.isAntlrInitialised()) {
			this.parameterizedOperationBuilder.updateEditableOperationLists();
		}
	}

	private Operation templateOperation(String opPrefix, String randomUUID, String operationFormulae) throws IOException {
		
		String templateOperationId = opPrefix + randomUUID;
		
		NextToken checkNextToken = parameterizedOperationBuilder.checkNextToken(operationFormulae);
		if (checkNextToken != null) throw new RuntimeException("Invalid formulae. " + templateOperationId + ". Formula: " + operationFormulae + ". Error: " + checkNextToken);
		
		Operation existingOperation = parameterizedOperationBuilder.getCurrentOperations().get(templateOperationId);
		if (existingOperation != null && !existingOperation.toFormulae().equals(operationFormulae))
				throw new RuntimeException("Invalid formulae. " + templateOperationId + ". Formula: " + operationFormulae + ". Error: differe from existing " + existingOperation.toFormulae());
		
		if (existingOperation == null) {
			LOGGER.info("Adding formulae. " + templateOperationId + ": " + operationFormulae);
			this.createdOperations.add(templateOperationId);
			parameterizedOperationBuilder.addFormula(templateOperationId, operationFormulae);
		}
		
		return parameterizedOperationBuilder.getCurrentOperations().get(templateOperationId);
	}
	
	private Operation iterationOperation(Operation templateOperation, @SuppressWarnings("rawtypes") List<Value> nansReplacements) {
		
		Operation iterationOperation = (Operation) templateOperation.clone();
		iterationOpSetParamsRec(nansReplacements, iterationOperation);
		return iterationOperation;
		
	}

	private void iterationOpSetParamsRec(@SuppressWarnings("rawtypes") List<Value> nansReplacements, Operation iterationOperation) {
		
		List<Operation> operands = iterationOperation.getOperands();
		int nbOperands = operands.size();
		
		int nextParam = 0;
		for (int i = 0; i < nbOperands; i++) {
			Operation operand = operands.get(i);
			if (!operand.isQuotationsDataSensitive()) {
				Value<?> output = operand.run(null, operand.shortOutputReference(), 0);								
				if (output instanceof NumberValue && ((NumberValue) output).getNumberValue().isNaN()) {
					operand.setParameter(nansReplacements.get(nextParam));
					nextParam++;
				}
			}
			iterationOpSetParamsRec(nansReplacements.subList(nextParam, nansReplacements.size()), operand);
		}
	}
	
	
	private class BestShiftReturn {
		
		Integer bestShift;
		Double bestRatio;
		private Map<Integer, Double> shiftRatio;
		
		protected BestShiftReturn(Integer bestShift, Double bestRatio, Map<Integer, Double> shiftRatio) {
			super();
			this.bestShift = bestShift;
			this.bestRatio = bestRatio;
			this.shiftRatio = shiftRatio;
		}
		
		public Map<Integer, Double> getShiftRatio() {
			return shiftRatio;
		}
		
		@Override
		public String toString() {
			return bestShift + "," + bestRatio;
		}
		
	}
	
	/**
	 * 
	 * @param randomUUID
	 * @param stock
	 * @param periodRangeStart
	 * @param periodRangeEnd
	 * @param periodRangeStep
	 * @param minShiftRangeOverPeriod: in ratio of the period
	 * @param maxShiftRangeOverPeriod: in ratio of the period
	 * @param shiftRangeStep
	 * @return
	 * @throws NoQuotationsException
	 * @throws NotEnoughDataException
	 * @throws IOException
	 * @throws WarningException
	 */
	private SortedMap<Integer, BestShiftReturn> bestShiftFor(
				String randomUUID, 
				final Stock stock, int periodRangeStart, int periodRangeEnd,
				int periodRangeStep, double minShiftRangeOverPeriod, double maxShiftRangeOverPeriod, int shiftRangeStep
				) throws NoQuotationsException, NotEnoughDataException, IOException, WarningException {
				
		final Date endDate = DateFactory.getNowEndDate();
		final Date startDate = DateUtils.addYears(endDate, -20);
		
		Quotations quotations  = QuotationsFactories.getFactory().getSpliFreeQuotationsInstance(stock, startDate, endDate, true, stock.getMarketValuation().getCurrency(), 0, ValidityFilter.CLOSE);
		final SortedMap<Date, Double> qMap = QuotationsFactories.getFactory().buildExactSMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size()-1);
		
//		Operation simpleRegressionOperation = templateOperation("c_op_simplereg_", randomUUID, this.simpleReg);
//		EventInfoOpsCompoOperation simpleRegEventInfo = new EventInfoOpsCompoOperation("simpleRegression", "simpleRegression");
//		TargetStockInfo simpleRegTragetStock = new TargetStockInfo("simpleRegression", simpleRegEventInfo, stock, startDate, endDate); 
//		NumericableMapValue simpleRegRun = (NumericableMapValue) simpleRegressionOperation.run(simpleRegTragetStock, randomUUID, periodRangeEnd);
//		SortedMap<Date, Double> simpleRegValue = simpleRegRun.getValue(simpleRegTragetStock);
//		Double simpleRegValueStart = simpleRegValue.get(simpleRegValue.firstKey());
//		Double simpleRegValueEnd = simpleRegValue.get(simpleRegValue.lastKey());

		SortedMap<Integer, BestShiftReturn> periodsBestShift = new TreeMap<>();
		final Operation templateOperation = templateOperation("c_op_template_", randomUUID, this.templateOperationFormulae);
		Map<Integer, Future<BestShiftReturn>> futures = new HashMap<>();
		
		for (int period = periodRangeStart; period <= periodRangeEnd; period = period + periodRangeStep) {
		
			final int periodX = period;
			final int shiftStart = (int)(periodX*minShiftRangeOverPeriod);
			final int shiftEnd = (int)(periodX*maxShiftRangeOverPeriod);
			
			LOGGER.info("Calculating: p " + periodX + " with start s " + shiftStart + " end s " + shiftEnd + " step s " + shiftRangeStep);
			Config config = ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME);
			
			Callable<BestShiftReturn> bestShiftCallable = new Callable<BestShiftReturn>() {
				public BestShiftReturn call() {
					
					ConfigThreadLocal.set(EventSignalConfig.EVENT_SIGNAL_NAME, config);
					
					Map<Integer, Double> shiftRatio = new HashMap<>();
					
					int bestShift = 0;
					Double bestRatio = Double.NEGATIVE_INFINITY;
					for (int leftShift = shiftStart; leftShift < shiftEnd; leftShift = leftShift + shiftRangeStep) {

						try {

							String iterationOperationId = "c_op_p" + periodX + "_s" + leftShift + "_" + randomUUID;
							LOGGER.info("Calculating: " + iterationOperationId + " for " + stock);

							//shiftWrapper(P0==NaN,bandNormalizer(-1,1,0,P1==NaN,leftShifter(P2==leftShift,sma(P3=periodX/2,periodicLn_(1,sma(periodX,close)))))
							//"shiftWrapper(NaN,bandNormalizer(-1,1,0,NaN,leftShifter(NaN,sma(NaN,periodicLn_(1,sma(NaN,bandNormalizer(0,100,NaN,NaN,subtraction_(close,stat:msimplereg(NaN,close)))))))))";
							@SuppressWarnings("rawtypes")
							List<Value> params = Lists.newArrayList(
									new NumberValue(Double.NaN), new NumberValue(Double.NaN), new NumberValue((double) leftShift), 
									new NumberValue((double) (periodX / 2)), new NumberValue((double) periodX)); //,
									//new NumberValue(Double.NaN), new NumberValue(Double.NaN), new NumberValue(Double.NaN), new NumberValue(Double.NaN));
							Operation iterationOperation = iterationOperation(templateOperation, params);

							Operation existingOperation = parameterizedOperationBuilder.getCurrentOperations().get(iterationOperationId);
							if (existingOperation != null && !existingOperation.toFormulae().equals(iterationOperation.toFormulae()))
								throw new RuntimeException("Invalid formulae. " + iterationOperationId + ". Formula: " + iterationOperation.toFormulae() + ". Error: differ from existing " + existingOperation.toFormulae());

							if (existingOperation == null) {
								BestHTClassifier.this.createdOperations.add(iterationOperationId);
								parameterizedOperationBuilder.addFormula(iterationOperationId, iterationOperation.toFormulae());
							}

							//indicator
							//"is bullish when operationXX is above threshold 0; is bearish when operationXX is below threshold 0;"
							String indicatorFormulae = BestHTClassifier.this.templateIndicatorformulae.replaceAll("operationXX", iterationOperationId);
							String indicatorId = iterationOperationId + "Ind";
							Operation existingIndicator = parameterizedIndiactorsBuilder.getCurrentOperations().get(indicatorId);
							if (existingIndicator != null && !existingIndicator.getFormulae().equals(indicatorFormulae))
								throw new RuntimeException("Invalid formulae. " + iterationOperationId + ". Formula: " + indicatorFormulae + ". Error: differ from existing " + existingIndicator.getFormulae());

							if (existingIndicator == null) {
								BestHTClassifier.this.createdIndicators.add(indicatorId);
								parameterizedIndiactorsBuilder.addFormula(indicatorId, indicatorFormulae);
							}

							EventInfoOpsCompoOperation eventInfo = (EventInfoOpsCompoOperation) parameterizedIndiactorsBuilder.getCurrentOperations().get(indicatorId);
							TargetStockInfo targetStock = new TargetStockInfo("runInd" + eventInfo.getReference(), eventInfo, stock, startDate, endDate);
							EventMapValue results = (EventMapValue) eventInfo.run(targetStock, eventInfo.getReference(), 0);

							TuningResDTO tuningRes = tuningFinalizer.buildTuningRes(targetStock.getStock(), startDate, endDate, qMap, results.getEventMap().values());
							Double forecastRateOfChange = 1 + tuningRes.getForecastProfitUnReal();
//							double simpleRegDiff = simpleRegValueEnd - simpleRegValueStart;
//							Double stockPriceRateOfChange = 1 +
//									(tuningRes.getPeriods().get(tuningRes.getPeriods().size()-1).getPriceAtTo() - simpleRegDiff/2)/
//									(tuningRes.getPeriods().get(0).getPriceAtFrom() + simpleRegDiff/2);
//							Double stockPriceRateOfChange = 1 + tuningRes.getBuyNHoldProfit();
							
//							Double ratio = forecastRateOfChange / stockPriceRateOfChange;
							Double ratio = forecastRateOfChange;
							
							shiftRatio.put(leftShift, ratio);
							if (ratio > bestRatio) {
								LOGGER.info("Updating best ratio for " + stock + " and period " + periodX + " and shift " + leftShift +
										" with forecastRateOfChange: " + forecastRateOfChange + //" and stockPriceRateOfChange: " + stockPriceRateOfChange  + 
										" V real stockPriceRateOfChange: " + (1 + tuningRes.getBuyNHoldProfit()));
								bestRatio = ratio;
								bestShift = leftShift;
							}

						} catch (Exception e) {
							LOGGER.error("Could not calculate leftShift " + leftShift + " with " + periodX + " for "  + stock, e);
						}

					}
					return new BestShiftReturn(bestShift, bestRatio, shiftRatio);
				}
			};
			
			Future<BestShiftReturn> future = CalculateThreadExecutor.getExecutorInstance().submit(bestShiftCallable);
			futures.put(period, future);
			
		}
		
		futures.entrySet().forEach(f -> {
			try {
				BestShiftReturn bestShiftRet = f.getValue().get();
				periodsBestShift.put(f.getKey(), bestShiftRet);
			} catch (InterruptedException | ExecutionException e) {
				LOGGER.error(e, e);
			}
		});
		
		return periodsBestShift;
		
	}
	
	
	private void tearDown() {
		
		createdIndicators.stream().forEach(o -> {
			try {
				parameterizedIndiactorsBuilder.destroyFormula(o);
			} catch (IOException e) {
				LOGGER.error("Indicator " + o + " does not exist " + e.toString());
			}
		});
		
		createdOperations.stream().forEach(o -> {
			try {
				parameterizedOperationBuilder.destroyFormula(o);
			} catch (IOException e) {
				LOGGER.error("Operation " + o + " does not exist " + e.toString());
			}
		});
		
	}
	
	public void calculateBestShifts() {
		final UUID randomUUID = UUID.randomUUID();
		Function<Stock, SortedMap<Integer, BestShiftReturn>> function = a -> {
			try {
				return bestShiftFor(randomUUID.toString(), a, 7, 84, 7, 0, 1, 1);
			} catch (NoQuotationsException | NotEnoughDataException | IOException | WarningException e) {
				return new TreeMap<>();
			}
		};
		calculate("bestshift", function);
		
	}
	
	public void calculateBestShifts(Integer periodRangeStart, Integer periodRangeEnd, Integer periodRangeStep, Integer shiftRangeStep) {
		final UUID randomUUID = UUID.randomUUID();
		Function<Stock, SortedMap<Integer, BestShiftReturn>> function = a -> {
			try {
				return bestShiftFor(randomUUID.toString(), a, periodRangeStart, periodRangeEnd, periodRangeStep, 0, 1, shiftRangeStep);
			} catch (NoQuotationsException | NotEnoughDataException | IOException | WarningException e) {
				return new TreeMap<>();
			}
		};
		calculate("bestRatioBestShift", function);
		
	}
	
	public void calculateProfitRatioForDefinedShift() {
		final UUID randomUUID = UUID.randomUUID();
		Function<Stock, SortedMap<Integer, BestShiftReturn>> function = a -> {
			try {
				//return bestShiftFor(randomUUID.toString(), a, 7, 84, 7, 1d/1.8500506792355262, 1d/1.1093514164587905, 1);
				return bestShiftFor(randomUUID.toString(), a, 14, 14, 7, 1d/1.8500506792355262, 1d/1.1093514164587905, 1);
			} catch (NoQuotationsException | NotEnoughDataException | IOException | WarningException e) {
				return new TreeMap<>();
			}
		};
		calculate("bestRatioLimitedShift", function);
		
	}
	
	
	private void calculate(String extention, Function<Stock, SortedMap<Integer, BestShiftReturn>> function) {
		
		try {
			Set<Stock> allStocks = stocks();
					
			Config config = ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME);
			
			Map<Stock, SortedMap<Integer,BestShiftReturn>> allBestShifts = allStocks.parallelStream().collect(Collectors.toMap(s -> s, s -> {
				ConfigThreadLocal.set(EventSignalConfig.EVENT_SIGNAL_NAME, config);
				SortedMap<Integer, BestShiftReturn> apply = function.apply(s);
				return apply;
			}));
			
			
			try (FileWriter fileWriter = new FileWriter(new File(TMP_PATH + UUID.randomUUID().toString() + "_bestShifts_" + extention + ".csv"))) {
				fileWriter.write("stock,period,best shift,profit ratio\n"); //header
				allBestShifts.keySet().stream().forEach(k -> allBestShifts.get(k).entrySet().forEach(e -> {
					try {
						fileWriter.write(k.getSymbol() + "," + e.getKey() + "," + e.getValue() + "\n");
					} catch (IOException e1) {
						throw new RuntimeException(e1);
					}
				}));
			} catch (Exception e) {
				LOGGER.error(e, e);
			}
			
			try (FileWriter fileWriter = new FileWriter(new File(TMP_PATH + UUID.randomUUID().toString() + "_allShifts_" + extention + ".csv"))) {
				fileWriter.write("stock,period,shift,profit ratio\n"); //header
				allBestShifts.keySet().stream().forEach(k -> allBestShifts.get(k).entrySet().forEach(e -> {
					e.getValue().getShiftRatio().entrySet().forEach(sr -> {
						try {
							fileWriter.write(k.getSymbol() + "," + e.getKey() + "," + sr.getKey() + "," + sr.getValue() +"\n");
						} catch (IOException e1) {
							LOGGER.error(e1, e1);
						}
					});
				}));
			} catch (Exception e) {
				LOGGER.error(e, e);
			}
					
		} finally {
			tearDown();
		} 

	}

	private Set<Stock> stocks() {
//				IndepShareList shareList = portfolioDAO.loadIndepShareList("VOLATILITY,ALLVOLATILITY:" + "MISCELLANEOUS");
//				Set<Stock> allStocks = shareList.getListShares().values().stream().map(ps -> ps.getStock()).collect(Collectors.toSet());
				Set<Stock> allStocks = new HashSet<>();
				allStocks.add(shareDAO.loadStockBy("BTC-USD", "BTC-USD"));
				allStocks.add(shareDAO.loadStockBy("FCHI", "FCHI"));
				allStocks.add(shareDAO.loadStockBy("FTSE", "FTSE"));
				allStocks.add(shareDAO.loadStockBy("VOW.DE", "VOW.DE"));
				allStocks.add(shareDAO.loadStockBy("INTC", "INTC"));
				allStocks.add(shareDAO.loadStockBy("AAPL", "AAPL"));
				allStocks.add(shareDAO.loadStockBy("JPM", "JPM"));
				allStocks.add(shareDAO.loadStockBy("IBM", "IBM"));
				
				allStocks.add(shareDAO.loadStockBy("GALT", "GALT"));
				allStocks.add(shareDAO.loadStockBy("CASI", "CASI"));
				
				allStocks.add(shareDAO.loadStockBy("FSBW", "FSBW"));
				allStocks.add(shareDAO.loadStockBy("AGG", "AGG"));
				
				// Calculating: c_op_p84_s74_a80996bb-53f7-4c0e-a501-e102aa78c412 for Halozyme Therapeutics Inc (HALO / HALO / 20230210 / USD)
//				allStocks.add(shareDAO.loadStockBy("ADMP", "ADMP"));
				
		return allStocks;
	}

}
