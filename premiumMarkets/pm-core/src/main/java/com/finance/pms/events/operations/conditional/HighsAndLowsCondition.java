/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.operations.conditional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlSeeAlso;

import org.apache.commons.lang.mutable.MutableInt;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.ComparableArray;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.functions.TalibSmaSmoother;


/**
 * 
 * @author Guillaume Thoreton
 * Additional constraints :
 * not implemented : 'over'
 * does not make sense : 'for'. As the condition is an event in time not a status in time.
 * 'spanning'
 * 
 */
@SuppressWarnings("rawtypes")
@XmlSeeAlso({HigherHighCondition.class, HigherLowCondition.class, LowerHighCondition.class, LowerLowCondition.class})
public abstract class HighsAndLowsCondition extends Condition<Comparable> implements UnaryCondition {
	
	private static MyLogger LOGGER = MyLogger.getLogger(HighsAndLowsCondition.class);

	private HighsAndLowsCondition() {
	}
	
	public HighsAndLowsCondition(String reference, String description) {
		super(reference, description, 
				new NumberOperation("minimum days span between two extremes"),
				new NumberOperation("look back period in days"),
				new NumberOperation("smoothing period for extremes inclusion edge (unused?)"),//UnUsed
				new DoubleMapOperation("historical data input"));
	}

	public HighsAndLowsCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public int mainInputPosition() {
		return 3;
	}


	@Override
	public BooleanMultiMapValue calculate(TargetStockInfo targetStock, List<? extends Value> inputs) {
		
		Double minimunNbDaysBetweenExtrs = ((NumberValue) inputs.get(0)).getValue(targetStock).doubleValue();
		Integer lookBackNbdays = ((NumberValue) inputs.get(1)).getValue(targetStock).intValue();
		Integer lookBackSmoothedThreshPeriod = ((NumberValue) inputs.get(2)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(3)).getValue(targetStock);
		Date dataFirstKey = data.firstKey();
		
		if (minimunNbDaysBetweenExtrs < 4) return new BooleanMultiMapValue(data.keySet(),false); //We need a least 3 days to draw one of these
		
		if (lookBackNbdays == -1) {
			lookBackNbdays = (int) (minimunNbDaysBetweenExtrs*4);
		}
		
		SortedMap<Date, Double> sSmooth = new TreeMap<Date, Double>();
		Date sSmoothFirstKey= new Date(0);
		if (lookBackSmoothedThreshPeriod != -1) {
			TalibSmaSmoother smaSmoother = new TalibSmaSmoother(lookBackSmoothedThreshPeriod);
			sSmooth = smaSmoother.sSmooth(data, false);
			sSmoothFirstKey = sSmooth.firstKey();
		}
		
		SortedMap<Date, Double> reglines = new TreeMap<Date, Double>();
		
		BooleanMultiMapValue outputs = new  BooleanMultiMapValue();
		for (Date date : data.keySet()) {
			Calendar currentDate = Calendar.getInstance();
			currentDate.setTime(date);
			Date lookBackPeriodStart = QuotationsFactories.getFactory().incrementDate(currentDate, -lookBackNbdays).getTime();
			
			if ( lookBackPeriodStart.after(dataFirstKey) && lookBackPeriodStart.after(sSmoothFirstKey) ) {
				
				SortedMap<Date, Double> quotationLookBackP = data.subMap(lookBackPeriodStart, date);
				if (quotationLookBackP.size() < 4) continue; //We need a least 3 days to draw one of these
				
				SortedMap<Date, Double> thresholdLookBackP = new TreeMap<Date, Double>();
				if (!sSmooth.isEmpty()) {
					thresholdLookBackP = sSmooth.subMap(lookBackPeriodStart, date);
				}
			
				ComparableArray<Double> regline = new ComparableArray<Double>();
				MutableInt firstExtremeIdx = new MutableInt(-1);
				MutableInt lastExtremeIdx = new MutableInt(-1);

				Comparable periodDataOps = (Comparable)new ComparableArray<Double>(quotationLookBackP.values());
				Comparable periodSmootherEdgeOps = (Comparable) new ComparableArray<Double>(thresholdLookBackP.values());
				Comparable alphaBalanceOps = minimunNbDaysBetweenExtrs;
				Comparable reglineOps = regline;
				Comparable firstExtremeIdxOps = firstExtremeIdx;
				Comparable lastExtremeIdxOps = lastExtremeIdx;
				@SuppressWarnings("unchecked")
				Boolean conditionCheck = conditionCheck(periodDataOps, periodSmootherEdgeOps, alphaBalanceOps, reglineOps, firstExtremeIdxOps, lastExtremeIdxOps);
				
				if (conditionCheck != null) {
					
					outputs.getValue(targetStock).put(date, conditionCheck);
					
					if ( conditionCheck ) {
						//Add Printable
						try {
							
							SortedMap<Date, Double> datesSubMap = data.subMap(lookBackPeriodStart, date);
							ArrayList<Date> lbDates = new ArrayList<Date>(datesSubMap.keySet());
							List<Date> regLineDates = lbDates.subList(lbDates.size() - regline.size(), lbDates.size());
							
							int gap = 0;
							if (!reglines.isEmpty()) {
								SortedMap<Date,Double> roomAvail = data.subMap(reglines.lastKey(), date);
								int reglineSize = regLineDates.size();
								while (roomAvail.size() <= reglineSize + 2 && gap + 1 < regLineDates.size()) {
									gap++;
									reglineSize = data.subMap(regLineDates.get(gap), date).size();
								}
							}
							
							if (gap < regLineDates.size()) {
								reglines.put(regLineDates.get(gap), regline.get(gap));
								for (int i = gap+1; i < regLineDates.size()-1; i++) {
									reglines.put(regLineDates.get(i), Double.NaN);
								}
								reglines.put(regLineDates.get(regLineDates.size()-1), regline.get(regline.size()-1));
							}
							
						} catch (Exception e) {
							//Out of range wont be printed
							LOGGER.error(e,e);
						}
					}
				}
			}
			
		}
		
		if (LOGGER.isTraceEnabled()) exportReglines(targetStock, lookBackNbdays, lookBackSmoothedThreshPeriod, data, sSmooth, reglines); 
		
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
	
	@Override
	public int operationStartDateShift() {
		int maxDateShift = getOperands().get(mainInputPosition()).operationStartDateShift();
		for (int i = 0; i < mainInputPosition(); i++) {
			maxDateShift = maxDateShift + getOperands().get(i).operationStartDateShift();
		}
		return maxDateShift;
	}
	
}
