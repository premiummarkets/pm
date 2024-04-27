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
import com.finance.pms.events.operations.nativeops.ListOperation;
import com.finance.pms.events.operations.nativeops.NativeOperations;
import com.finance.pms.events.operations.nativeops.NativeOperationsBasic;
import com.finance.pms.events.operations.nativeops.NullOperation;
import com.finance.pms.events.operations.nativeops.OperationReferenceOperation;
import com.finance.pms.events.operations.nativeops.StockOperation;
import com.finance.pms.events.operations.nativeops.TargetStockInfoOperation;
import com.finance.pms.events.operations.nativeops.calc.BuyAndHoldOperation;
import com.finance.pms.events.operations.nativeops.calc.ConcatStringOperation;
import com.finance.pms.events.operations.nativeops.calc.Division;
import com.finance.pms.events.operations.nativeops.calc.EqualsOperation;
import com.finance.pms.events.operations.nativeops.calc.IndicatorStatsOperation;
import com.finance.pms.events.operations.nativeops.calc.LnPeriodicOperation;
import com.finance.pms.events.operations.nativeops.calc.MathOperation;
import com.finance.pms.events.operations.nativeops.calc.NegateOperation;
import com.finance.pms.events.operations.nativeops.calc.NumberMathOperation;
import com.finance.pms.events.operations.nativeops.calc.OProfitOperation;
import com.finance.pms.events.operations.nativeops.calc.PortfolioOperation;
import com.finance.pms.events.operations.nativeops.calc.Product;
import com.finance.pms.events.operations.nativeops.calc.ProfitDrivenOperation;
import com.finance.pms.events.operations.nativeops.calc.ProfitOperation;
import com.finance.pms.events.operations.nativeops.calc.ProfitWalkerOperation;
import com.finance.pms.events.operations.nativeops.calc.RandomOperation;
import com.finance.pms.events.operations.nativeops.calc.RandomizeStringOperation;
import com.finance.pms.events.operations.nativeops.calc.RecursiveOperation;
import com.finance.pms.events.operations.nativeops.calc.StatsOperation;
import com.finance.pms.events.operations.nativeops.calc.StringEqualsOperation;
import com.finance.pms.events.operations.nativeops.calc.Subtraction;
import com.finance.pms.events.operations.nativeops.calc.Sum;
import com.finance.pms.events.operations.nativeops.calc.UnaryDivision;
import com.finance.pms.events.operations.nativeops.calc.UnaryProduct;
import com.finance.pms.events.operations.nativeops.calc.UnarySum;
import com.finance.pms.events.operations.nativeops.calc.VolatilityOperation;
import com.finance.pms.events.operations.nativeops.calc.VolatilityOtherOperation;
import com.finance.pms.events.operations.nativeops.data.CsvFileFilterOperation;
import com.finance.pms.events.operations.nativeops.data.IOsAssemblerOperation;
import com.finance.pms.events.operations.nativeops.data.IOsDeltaExporterOperation;
import com.finance.pms.events.operations.nativeops.data.IOsExporterOperation;
import com.finance.pms.events.operations.nativeops.data.IOsLooseAssemblerOperation;
import com.finance.pms.events.operations.nativeops.data.IOsWebImporterOperation;
import com.finance.pms.events.operations.nativeops.flow.AndOperation;
import com.finance.pms.events.operations.nativeops.flow.EnvOperation;
import com.finance.pms.events.operations.nativeops.flow.GetOperation;
import com.finance.pms.events.operations.nativeops.flow.IfOperation;
import com.finance.pms.events.operations.nativeops.flow.LetOperation;
import com.finance.pms.events.operations.nativeops.flow.LogOperation;
import com.finance.pms.events.operations.nativeops.flow.MetaOperation;
import com.finance.pms.events.operations.nativeops.flow.OrOperation;
import com.finance.pms.events.operations.nativeops.flow.TargetStockDelegateOperation;
import com.finance.pms.events.operations.nativeops.flow.UnEnvOperation;
import com.finance.pms.events.operations.nativeops.ta.PMAroonOperation;
import com.finance.pms.events.operations.nativeops.ta.PMBollingerOperation;
import com.finance.pms.events.operations.nativeops.ta.PMLogRocOperation;
import com.finance.pms.events.operations.nativeops.ta.PMMACDOperation;
import com.finance.pms.events.operations.nativeops.ta.PMMightyChaikinOperation;
import com.finance.pms.events.operations.nativeops.ta.Ta4jOperation;
import com.finance.pms.events.operations.nativeops.ta.TalibAssemblerOperation;
import com.finance.pms.events.operations.nativeops.ta.ZeroLagEMAOperation;
import com.finance.pms.events.operations.nativeops.trans.BandNormalizerOperation;
import com.finance.pms.events.operations.nativeops.trans.BandRatioNormalizerOperation;
import com.finance.pms.events.operations.nativeops.trans.DataTypeCheckOperation;
import com.finance.pms.events.operations.nativeops.trans.FilterOperation;
import com.finance.pms.events.operations.nativeops.trans.FlipOperation;
import com.finance.pms.events.operations.nativeops.trans.InverseOperation;
import com.finance.pms.events.operations.nativeops.trans.LeftShifterOperation;
import com.finance.pms.events.operations.nativeops.trans.RefiterOperation;
import com.finance.pms.events.operations.nativeops.trans.RequiredShiftWrapperOperation;
import com.finance.pms.events.operations.nativeops.trans.TriggerPointJoiner;


public class NativesXmlManager {

	private static MyLogger LOGGER = MyLogger.getLogger(NativesXmlManager.class);

	protected String xmlfile; //= System.getProperty("installdir")+File.separator+"nativeOps.xml";

	public NativesXmlManager(String xmlfile) {
		super();
		this.xmlfile = System.getProperty("installdir") + File.separator + xmlfile + ".xml";
	}

	public NativeOperations initNativeOperations () {

		NativeOperations nativeOperations = initNativeOperationInstance();
		
		//Flows
		MetaOperation metaOperation = new MetaOperation();
		nativeOperations.addOperation(metaOperation);
		NullOperation nullOperation = new NullOperation();
		nativeOperations.addOperation(nullOperation);
		IfOperation ifOperation = new IfOperation();
		nativeOperations.addOperation(ifOperation);
		LetOperation letOperation = new LetOperation();
		nativeOperations.addOperation(letOperation);
		GetOperation getOperation = new GetOperation();
		nativeOperations.addOperation(getOperation);
		EnvOperation envOperation = new EnvOperation();
		nativeOperations.addOperation(envOperation);
		UnEnvOperation unenvOperation = new UnEnvOperation();
		nativeOperations.addOperation(unenvOperation);
		LogOperation logOperation = new LogOperation();
		nativeOperations.addOperation(logOperation);
		AndOperation andOperation = new AndOperation();
		nativeOperations.addOperation(andOperation);
		OrOperation orOperation = new OrOperation();
		nativeOperations.addOperation(orOperation);

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
		PortfolioOperation portfolioOperation = new PortfolioOperation();
		nativeOperations.addOperation(portfolioOperation);

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
		BandNormalizerOperation bandNormalizerOperation = new BandNormalizerOperation();
		nativeOperations.addOperation(bandNormalizerOperation);
		BandRatioNormalizerOperation bandNRatioNormalizerOperation = new BandRatioNormalizerOperation();
		nativeOperations.addOperation(bandNRatioNormalizerOperation);
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
		EqualsOperation equalsOperation = new EqualsOperation();
		nativeOperations.addOperation(equalsOperation);
		NegateOperation negateOperation = new NegateOperation();
		nativeOperations.addOperation(negateOperation);


		//Other
		ZeroLagEMAOperation zeroLagEMAOperation = new ZeroLagEMAOperation();
		nativeOperations.addOperation(zeroLagEMAOperation);
		LnPeriodicOperation lnPeriodicOperation = new LnPeriodicOperation();
		nativeOperations.addOperation(lnPeriodicOperation);
		VolatilityOperation volatilityOperation = new VolatilityOperation();
		nativeOperations.addOperation(volatilityOperation);
		VolatilityOtherOperation volatilityOtherOperation = new VolatilityOtherOperation();
		nativeOperations.addOperation(volatilityOtherOperation);
		Ta4jOperation ta4jOperation = new Ta4jOperation();
		nativeOperations.addOperation(ta4jOperation);
		
		//Strategy
		ProfitDrivenOperation profitDrivenOperation = new ProfitDrivenOperation();
		nativeOperations.addOperation(profitDrivenOperation);
		ProfitWalkerOperation profitWalkerOperation = new ProfitWalkerOperation();
		nativeOperations.addOperation(profitWalkerOperation);
		RandomOperation randomOperation = new RandomOperation();
		nativeOperations.addOperation(randomOperation);

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
