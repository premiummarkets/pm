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
package com.finance.pms.events;

import java.io.File;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.IfOperation;
import com.finance.pms.events.operations.MetaOperation;
import com.finance.pms.events.operations.NullOperation;
import com.finance.pms.events.operations.ProfitDrivenOperation;
import com.finance.pms.events.operations.ProfitWalkerOperation;
import com.finance.pms.events.operations.RandomOperation;
import com.finance.pms.events.operations.TargetStockDelegateOperation;
import com.finance.pms.events.operations.nativeops.BandNormalizerOperation;
import com.finance.pms.events.operations.nativeops.BandRatioNormalizerOperation;
import com.finance.pms.events.operations.nativeops.BuyAndHoldOperation;
import com.finance.pms.events.operations.nativeops.ConcatStringOperation;
import com.finance.pms.events.operations.nativeops.CsvFileFilterOperation;
import com.finance.pms.events.operations.nativeops.DataTypeCheckOperation;
import com.finance.pms.events.operations.nativeops.Division;
import com.finance.pms.events.operations.nativeops.FilterOperation;
import com.finance.pms.events.operations.nativeops.FlipOperation;
import com.finance.pms.events.operations.nativeops.IOsAssemblerOperation;
import com.finance.pms.events.operations.nativeops.IOsDeltaExporterOperation;
import com.finance.pms.events.operations.nativeops.IOsExporterOperation;
import com.finance.pms.events.operations.nativeops.IOsLooseAssemblerOperation;
import com.finance.pms.events.operations.nativeops.IOsWebImporterOperation;
import com.finance.pms.events.operations.nativeops.IndicatorStatsOperation;
import com.finance.pms.events.operations.nativeops.InverseOperation;
import com.finance.pms.events.operations.nativeops.LeftShifterOperation;
import com.finance.pms.events.operations.nativeops.ListOperation;
import com.finance.pms.events.operations.nativeops.LnPeriodicOperation;
import com.finance.pms.events.operations.nativeops.MathOperation;
import com.finance.pms.events.operations.nativeops.NativeOperations;
import com.finance.pms.events.operations.nativeops.NativeOperationsBasic;
import com.finance.pms.events.operations.nativeops.NumberMathOperation;
import com.finance.pms.events.operations.nativeops.OProfitOperation;
import com.finance.pms.events.operations.nativeops.OperationReferenceOperation;
import com.finance.pms.events.operations.nativeops.PMAroonOperation;
import com.finance.pms.events.operations.nativeops.PMBollingerOperation;
import com.finance.pms.events.operations.nativeops.PMLogRocOperation;
import com.finance.pms.events.operations.nativeops.PMMACDOperation;
import com.finance.pms.events.operations.nativeops.PMMightyChaikinOperation;
import com.finance.pms.events.operations.nativeops.Product;
import com.finance.pms.events.operations.nativeops.ProfitOperation;
import com.finance.pms.events.operations.nativeops.RandomizeStringOperation;
import com.finance.pms.events.operations.nativeops.RecursiveOperation;
import com.finance.pms.events.operations.nativeops.RefiterOperation;
import com.finance.pms.events.operations.nativeops.RequiredShiftWrapperOperation;
import com.finance.pms.events.operations.nativeops.StatsOperation;
import com.finance.pms.events.operations.nativeops.StockOperation;
import com.finance.pms.events.operations.nativeops.StringEqualsOperation;
import com.finance.pms.events.operations.nativeops.Subtraction;
import com.finance.pms.events.operations.nativeops.Sum;
import com.finance.pms.events.operations.nativeops.Ta4jOperation;
import com.finance.pms.events.operations.nativeops.TalibAssemblerOperation;
import com.finance.pms.events.operations.nativeops.TargetStockInfoOperation;
import com.finance.pms.events.operations.nativeops.TriggerPointJoiner;
import com.finance.pms.events.operations.nativeops.UnaryDivision;
import com.finance.pms.events.operations.nativeops.UnaryProduct;
import com.finance.pms.events.operations.nativeops.UnarySum;
import com.finance.pms.events.operations.nativeops.VolatilityOperation;
import com.finance.pms.events.operations.nativeops.VolatilityOtherOperation;
import com.finance.pms.events.operations.nativeops.pm.ZeroLagEMAOperation;


public class NativesXmlManager {

	private static MyLogger LOGGER = MyLogger.getLogger(NativesXmlManager.class);

	protected String xmlfile; //= System.getProperty("installdir")+File.separator+"nativeOps.xml";

	public NativesXmlManager(String xmlfile) {
		super();
		this.xmlfile = System.getProperty("installdir") + File.separator + xmlfile + ".xml";
	}

	public NativeOperations initNativeOperations () {

		NativeOperations nativeOperations = initNativeOperationInstance();
		
		MetaOperation metaOperation = new MetaOperation();
		nativeOperations.addOperation(metaOperation);
		NullOperation nullOperation = new NullOperation();
		nativeOperations.addOperation(nullOperation);
		IfOperation ifOperation = new IfOperation();
		nativeOperations.addOperation(ifOperation);

		//Arithmetic //=> Arithmetic are added here so that they show in ui only (indeed they are not parameterized and not in the operation grammar either)
		//This is different with condition which are instantiated on the fly => conditions are indeed hard coded in the indicator grammar.)
		//TODO match the same dynamic as operations in indicators?. This requires more work as the grammar rules are different from one condition to another.
		Sum sum = new Sum();
		nativeOperations.addOperation(sum);
		Product product = new Product();
		nativeOperations.addOperation(product);
		Division division = new Division();
		nativeOperations.addOperation(division);
		Subtraction subtraction = new Subtraction();
		nativeOperations.addOperation(subtraction);

		UnarySum unarySum = new UnarySum();
		nativeOperations.addOperation(unarySum);
		UnaryProduct unaryProduct = new UnaryProduct();
		nativeOperations.addOperation(unaryProduct);
		UnaryDivision unaryDivision = new UnaryDivision();
		nativeOperations.addOperation(unaryDivision);
		
		NumberMathOperation numberMathOperation = new NumberMathOperation();
		nativeOperations.addOperation(numberMathOperation);
		
		//Params
		ListOperation listOperation = new ListOperation();
		nativeOperations.addOperation(listOperation);
		OperationReferenceOperation operationReferenceOperation = new OperationReferenceOperation();
		nativeOperations.addOperation(operationReferenceOperation);
		
		//Stock
		StockOperation stockOperation = new StockOperation();
		nativeOperations.addOperation(stockOperation);
		TargetStockInfoOperation targetStockInfoOperation = new TargetStockInfoOperation();
		nativeOperations.addOperation(targetStockInfoOperation);
		OProfitOperation oProfitOperation = new OProfitOperation();
		nativeOperations.addOperation(oProfitOperation);
		ProfitOperation profitOperation = new ProfitOperation();
		nativeOperations.addOperation(profitOperation);
		TargetStockDelegateOperation targetStockDelegateOperation = new TargetStockDelegateOperation();
		nativeOperations.addOperation(targetStockDelegateOperation);
		DataTypeCheckOperation dataTypeCheckOperation = new DataTypeCheckOperation();
		nativeOperations.addOperation(dataTypeCheckOperation);

		//Pm
		PMMACDOperation pmMacdOperation = new PMMACDOperation();
		nativeOperations.addOperation(pmMacdOperation);
		PMLogRocOperation houseTrendOperation = new PMLogRocOperation();
		nativeOperations.addOperation(houseTrendOperation);
		PMAroonOperation pmAroonOperation = new PMAroonOperation();
		nativeOperations.addOperation(pmAroonOperation);
		PMMightyChaikinOperation pmMChaikinOperation = new PMMightyChaikinOperation();
		nativeOperations.addOperation(pmMChaikinOperation);
		PMBollingerOperation bollingerOperation = new PMBollingerOperation();
		nativeOperations.addOperation(bollingerOperation);
		BuyAndHoldOperation buyAndHoldOperation = new BuyAndHoldOperation();
		nativeOperations.addOperation(buyAndHoldOperation);

		//Data manipulation
		MathOperation mathOperation = new MathOperation();
		nativeOperations.addOperation(mathOperation);
		StatsOperation pmSmaOperation = new StatsOperation();
		nativeOperations.addOperation(pmSmaOperation);
		RecursiveOperation recursiveOperation = new RecursiveOperation();
		nativeOperations.addOperation(recursiveOperation);
		FlipOperation flipOperation = new FlipOperation();
		nativeOperations.addOperation(flipOperation);
		InverseOperation inverseOperation = new InverseOperation();
		nativeOperations.addOperation(inverseOperation);
		LeftShifterOperation leftShiterOperation = new LeftShifterOperation();
		nativeOperations.addOperation(leftShiterOperation);
		FilterOperation filterOperation = new FilterOperation();
		nativeOperations.addOperation(filterOperation);
		IOsAssemblerOperation oneInputAssemblerOperation = new IOsAssemblerOperation();
		nativeOperations.addOperation(oneInputAssemblerOperation);
		IOsLooseAssemblerOperation iosLooseAssemblerOperation = new IOsLooseAssemblerOperation();
		nativeOperations.addOperation(iosLooseAssemblerOperation);
		IOsExporterOperation inputExporterOperation = new IOsExporterOperation();
		nativeOperations.addOperation(inputExporterOperation);
		IOsDeltaExporterOperation inputDeltaExporterOperation = new IOsDeltaExporterOperation();
		nativeOperations.addOperation(inputDeltaExporterOperation);
		IOsWebImporterOperation iOsWebImporterOperation = new IOsWebImporterOperation();
		nativeOperations.addOperation(iOsWebImporterOperation);
		CsvFileFilterOperation csvFileFilterOperation = new CsvFileFilterOperation();
		nativeOperations.addOperation(csvFileFilterOperation);
		IndicatorStatsOperation indicatorStatsOperation = new IndicatorStatsOperation();
		nativeOperations.addOperation(indicatorStatsOperation);
		RefiterOperation refiterOperation = new RefiterOperation();
		nativeOperations.addOperation(refiterOperation);
		TriggerPointJoiner triggerPointJoiner = new TriggerPointJoiner();
		nativeOperations.addOperation(triggerPointJoiner);
		ProfitDrivenOperation profitDrivenOperation = new ProfitDrivenOperation();
		nativeOperations.addOperation(profitDrivenOperation);
		BandNormalizerOperation bandNormalizerOperation = new BandNormalizerOperation();
		nativeOperations.addOperation(bandNormalizerOperation);
		BandRatioNormalizerOperation bandNRatioNormalizerOperation = new BandRatioNormalizerOperation();
		nativeOperations.addOperation(bandNRatioNormalizerOperation);
		ProfitWalkerOperation profitWalkerOperation = new ProfitWalkerOperation();
		nativeOperations.addOperation(profitWalkerOperation);
		TalibAssemblerOperation talibAssemblerOperation = new TalibAssemblerOperation();
		nativeOperations.addOperation(talibAssemblerOperation);
		RequiredShiftWrapperOperation requiredShiftWrapperOperation = new RequiredShiftWrapperOperation();
		nativeOperations.addOperation(requiredShiftWrapperOperation);
		RandomizeStringOperation randomizeStringOperation = new RandomizeStringOperation();
		nativeOperations.addOperation(randomizeStringOperation);
		ConcatStringOperation concatStringOperation = new ConcatStringOperation();
		nativeOperations.addOperation(concatStringOperation);
		StringEqualsOperation stringEqualsOperation = new StringEqualsOperation();
		nativeOperations.addOperation(stringEqualsOperation);

		//Other
		ZeroLagEMAOperation zeroLagEMAOperation = new ZeroLagEMAOperation();
		nativeOperations.addOperation(zeroLagEMAOperation);
		LnPeriodicOperation lnPeriodicOperation = new LnPeriodicOperation();
		nativeOperations.addOperation(lnPeriodicOperation);
		VolatilityOperation volatilityOperation = new VolatilityOperation();
		nativeOperations.addOperation(volatilityOperation);
		VolatilityOtherOperation volatilityOtherOperation = new VolatilityOtherOperation();
		nativeOperations.addOperation(volatilityOtherOperation);
		RandomOperation randomOperation = new RandomOperation();
		nativeOperations.addOperation(randomOperation);
		Ta4jOperation ta4jOperation = new Ta4jOperation();
		nativeOperations.addOperation(ta4jOperation);

		return nativeOperations;
	}

	public void saveNativeOperations(NativeOperations nativeOperations) {
		try {
			JAXBContext context = JAXBContext.newInstance(NativeOperationsBasic.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(nativeOperations, System.out);
			m.marshal(nativeOperations, new File(xmlfile));
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
	}

	protected NativeOperations initNativeOperationInstance() {
		return new NativeOperationsBasic();
	}

	public NativeOperations loadNativeOperations() throws Exception {

		try {
			JAXBContext context = JAXBContext.newInstance(NativeOperationsBasic.class);
			Unmarshaller um = context.createUnmarshaller();
			NativeOperations nativeOps = (NativeOperations) um.unmarshal(new FileReader(xmlfile));
			return nativeOps;
		} catch (Exception e) {
			LOGGER.error(e,e);
			throw e;
		}
	}

}
