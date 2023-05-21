package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.screening.BestHTClassifier;
import com.finance.pms.screening.BestHTClassifier.BestShiftReturn;
import com.finance.pms.threads.ConfigThreadLocal;

@XmlRootElement
public class BestShiftOperation extends Operation {

	protected static MyLogger LOGGER = MyLogger.getLogger(BestShiftOperation.class);
	
	public BestShiftOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public BestShiftOperation() {
		this("bestShift", "Calculate the more profitable shift (best shift) for given operation formulae. If > 0 => Buy, If < 0 => Sell",
			 //TODO formulae should leverage the meta operation instead of using NaN and hard coded parameters expansion
			 //TODO replace formulae with operationReference parameter?
			 new StringOperation("string", "formulae", "formulae", new StringValue("\"shiftWrapper(NaN,bandNormalizer(-1,1,0,NaN,leftShifter(NaN,sma(NaN,periodicLn_(1,sma(NaN,close))))))\"")),
			 new NumberOperation("number", "period", "period", new NumberValue(42.0)),
			 new NumberOperation("number", "shiftSteps", "shift steps per 1", new NumberValue(1.0)));
	}

	public BestShiftOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}


	@Override
	public NumberValue calculate(TargetStockInfo targetStock, String thisCallStack, 
								int thisOutputRequiredStartShiftFromParent, int thisInputOperandsRequiredShiftFromThis, 
								@SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		String templateOperationFormulea = ((StringValue)inputs.get(0)).getValue(targetStock);
		Integer period = ((NumberValue)inputs.get(1)).getValue(targetStock).intValue();
		Integer shiftRangeStep = ((NumberValue)inputs.get(2)).getValue(targetStock).intValue();
		
		String templateIndicatorFormulea =  "is bullish when ? is above threshold 0; is bearish when ? is below threshold 0;";
		
		//One stock, one period
		BestHTClassifier bestHTClassifier = new BestHTClassifier(templateOperationFormulea, templateIndicatorFormulea) {
			@Override
			protected Set<Stock>  stocks() {
				Set<Stock> stocks = new HashSet<>();
				stocks.add(targetStock.getStock());
				return stocks;
			}
		};

		ConfigThreadLocal.set("eventSignal", new EventSignalConfig());
		Map<Stock, SortedMap<Integer, BestShiftReturn>> bestShifts = bestHTClassifier.calculateBestShifts(false, period, period, 1, shiftRangeStep);
		Integer bestShift = bestShifts.get(targetStock.getStock()).get(period).getBestShift();
		
		LOGGER.info("Best shift for " + this + ": " + bestShift);

		return new NumberValue(bestShift.doubleValue());
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock, Object... addtionalParams) {
	}

	@Override
	public NumberValue emptyValue() {
		return null;
	}
	
	@Override
	public boolean isQuotationsDataSensitive() {
		return false;
	}

}
