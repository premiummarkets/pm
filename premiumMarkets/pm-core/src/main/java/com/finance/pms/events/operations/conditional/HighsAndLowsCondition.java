package com.finance.pms.events.operations.conditional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.DoubleOperation;
import com.finance.pms.events.operations.nativeops.DoubleValue;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.functions.TalibSmaSmoother;

public abstract class HighsAndLowsCondition extends Condition<ArrayList<Double>> implements StandAloneCondition {
	
	private static MyLogger LOGGER = MyLogger.getLogger(HighsAndLowsCondition.class);

	private HighsAndLowsCondition() {
	}
	
	public HighsAndLowsCondition(String reference, String description) {
		super(reference, description, new DoubleOperation("days span look back"), new DoubleOperation("look back smoothed threshold period"), new DoubleMapOperation("historical data input"));
	}

	public HighsAndLowsCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public int mainPosition() {
		return 2;
	}

	@Override
	public BooleanMultiMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Integer lookBackNbdays = ((DoubleValue) inputs.get(0)).getValue(targetStock).intValue();
		Integer lookBackSmoothedThreshPeriod = ((DoubleValue) inputs.get(1)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(2)).getValue(targetStock);
		
		if (lookBackNbdays < 4) return new BooleanMultiMapValue(data.keySet(),false); //We need a least 3 days to draw one of these
		
		if (lookBackSmoothedThreshPeriod == -1) {//default smooth = lookBackNbdays/7
			lookBackSmoothedThreshPeriod = lookBackNbdays/7;
		}
		
		
		TalibSmaSmoother smaSmoother = new TalibSmaSmoother(lookBackSmoothedThreshPeriod);
		SortedMap<Date, Double> sSmooth = smaSmoother.sSmooth(data, false);
		
		Date dataFirstKey = data.firstKey();
		Date sSmoothFirstKey = sSmooth.firstKey();
		
		SortedMap<Date, Double> reglines = new TreeMap<Date, Double>();
		
		BooleanMultiMapValue outputs = new  BooleanMultiMapValue();
		for (Date date : data.keySet()) {
			Calendar currentDate = Calendar.getInstance();
			currentDate.setTime(date);
			Date lookBackPeriodStart = QuotationsFactories.getFactory().incrementDate(currentDate, -lookBackNbdays).getTime();
			
			if (lookBackPeriodStart.after(dataFirstKey) && lookBackPeriodStart.after(sSmoothFirstKey)) {
				SortedMap<Date, Double> quotationLookBackP = data.subMap(lookBackPeriodStart, date);
				SortedMap<Date, Double> thresholdLookBackP = sSmooth.subMap(lookBackPeriodStart, date);
			
				ComparableArray regline = new ComparableArray();
				@SuppressWarnings("unchecked")
				Boolean conditionCheck = conditionCheck(new ComparableArray(quotationLookBackP.values()), new ComparableArray(thresholdLookBackP.values()), regline);
				if (conditionCheck != null) {		
					outputs.getValue(targetStock).put(date, conditionCheck);
					
					if ( conditionCheck && (reglines.isEmpty() || lookBackPeriodStart.after(reglines.lastKey())) ) {
						//Add Printable
//						ListIterator<Double> reglineIterator = regline.listIterator();
//						for (Date regDate : data.subMap(lookBackPeriodStart, date).keySet()) {
//							if (reglineIterator.hasNext()) reglines.put(regDate, reglineIterator.next());
//						}
						List<Date> datesList = new ArrayList<Date>(data.subMap(lookBackPeriodStart, date).keySet());
						reglines.put(datesList.get(0), regline.get(0));
						reglines.put(datesList.get(datesList.size()-1), regline.get(regline.size()-1));
						for (int i = 1; i < datesList.size()-1; i++) {
							reglines.put(datesList.get(i), Double.NaN);
						}
						
					}
				}
			}
			
		}
		
		if (LOGGER.isInfoEnabled()) exportReglines(targetStock, lookBackNbdays, lookBackSmoothedThreshPeriod, data, sSmooth, reglines); 
		
		if (!reglines.isEmpty()) {
			String smaKey = "SMA "+lookBackSmoothedThreshPeriod;
			outputs.getAdditionalOutputs().put(smaKey, new DoubleMapValue(sSmooth));
			outputs.getAdditionalOutputsTypes().put(smaKey, Type.MULTISIGNAL);
			String tangentKey = lookBackNbdays+" days tangent";
			outputs.getAdditionalOutputs().put(tangentKey, new DoubleMapValue(reglines));
			outputs.getAdditionalOutputsTypes().put(tangentKey, Type.MULTI);
		}
		
		return outputs;
	}

	private void exportReglines(TargetStockInfo targetStock, Integer lookBackNbdays, Integer lookBackSmoothedThreshPeriod, SortedMap<Date, Double> data,
			SortedMap<Date, Double> sSmooth, SortedMap<Date, Double> reglines) {
		try {
			File reglineFile = new File(
					System.getProperty("installdir") + File.separator + "tmp" + File.separator +
					targetStock.getStock().getName().replaceAll("[/\\*\\.\\?,;><|\\!\\(\\) ]", "_") + "_"+ this.getClass().getSimpleName() + "_" + this.getReference()+ "_" + data.hashCode() +".csv");
			BufferedWriter regLineBuff = new BufferedWriter(new FileWriter(reglineFile));
			String header = "date, input, smoothed "+lookBackSmoothedThreshPeriod+", regline "+lookBackNbdays;
			regLineBuff.write(header);
			regLineBuff.newLine();
			for (Date date : data.keySet()) {
				String line = date + "," + data.get(date) + "," + sSmooth.get(date) + ",";
				Double regV = reglines.get(date);
				if (regV != null) {
					line = line + regV;
				} 
				regLineBuff.write(line);
				regLineBuff.newLine();
			}
			regLineBuff.close();
		} catch (IOException e) {
			LOGGER.error(e,e);
		}
	}
	
	class ComparableArray extends ArrayList<Double> implements Comparable<ArrayList<Double>> {

		private static final long serialVersionUID = -363699288012620328L;

		public ComparableArray(Collection<Double> values) {
			super(values);
		}

		public ComparableArray() {
			super();
		}

		@Override
		public int compareTo(ArrayList<Double> o) {
			return this.hashCode() - o.hashCode();
		}
		
	}


}
