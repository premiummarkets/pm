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
package com.finance.pms;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.events.calculation.parametrizedindicators.ParameterizedIndicatorsBuilder;
import com.finance.pms.events.operations.nativeops.OperationReflectiveGenerator;
import com.finance.pms.events.operations.nativeops.pm.TalibIndicatorsCompositionerOperationReflectiveGenerator;
import com.finance.pms.events.operations.nativeops.talib.TalibOperationGenerator;
import com.finance.pms.events.operations.parameterized.ParameterizedOperationBuilder;
import com.finance.pms.threads.ConfigThreadLocal;

public class SpringContext extends GenericApplicationContext {

	private static SpringContext singleton;

	public static SpringContext getSingleton() {
		return singleton;
	}

	private boolean hasBeenClosed = false;


	public SpringContext(String propsFile) {
		super();

		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");

		initPrefs(propsFile);
		setDataSource();
		singleton = this;

		buildStampPrefs();	
	}

	private void buildStampPrefs() {
		//BuildInPrefs
		try {
			Properties pbuild = new Properties();
			try {
				pbuild.load(this.getClass().getResourceAsStream("/pmsbuild.properties"));
			} catch (Exception e) {
				try {
					pbuild.load(this.getClass().getResourceAsStream("pmsbuild.properties"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			String siteUrlProp = "none.com";
			if (System.getProperty("site.url") != null) {
				siteUrlProp = System.getProperty("site.url");
			}
			else if (pbuild.containsKey("site.url")) {
				siteUrlProp = pbuild.getProperty("site.url");
			}

			MainPMScmd.getMyPrefs().put("site.url", siteUrlProp);
			MainPMScmd.getMyPrefs().flushy();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SpringContext(ConfigurableApplicationContext configurableApplicationContext) {

		super(configurableApplicationContext);
		setDataSource();
		singleton = this;

		buildStampPrefs();

	}

	//??
	public SpringContext(GenericApplicationContext beanFactory) {
		super(beanFactory);
		setDataSource();
		singleton = this;
	}

	@Override
	public void close() {
		super.close();
		this.hasBeenClosed  = true;
	}

	public boolean isHasBeenClosed() {
		return hasBeenClosed;
	}

	//TODO back to xml configs
	private void setDataSource() {

		ConstructorArgumentValues dataSCAV = new  ConstructorArgumentValues();
		RootBeanDefinition dataS = new RootBeanDefinition("com.finance.pms.datasources.db.DataSource",dataSCAV,new MutablePropertyValues());
		dataS.setDestroyMethodName("stopThreads");
		this.registerBeanDefinition("dataSource", dataS);

	}

	public void setMasSource(String... args) {

		//Add db.properties path as variable to be retrieved when loading configuration file
		System.setProperty("dbproperites_file",args[0]);

		ConstructorArgumentValues masSCAV = new  ConstructorArgumentValues();
		masSCAV.addGenericArgumentValue(args[0],"java.lang.String");
		masSCAV.addGenericArgumentValue((args.length==2)?Boolean.parseBoolean(args[1]):true,"java.lang.Boolean");
		RootBeanDefinition masS = new RootBeanDefinition("com.finance.pms.mas.MasSource",masSCAV,  new MutablePropertyValues());
		masS.setDestroyMethodName("stopThreads");

		this.registerBeanDefinition("masSource", masS);

	}

	public void loadBeans(String ...args) {

		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(this);
		for (String str: args) {
			xmlReader.loadBeanDefinitions(new ClassPathResource(str));
		}

	}

	public void standardInit(String... args) {
		System.out.println("args is ignored (legacy?) :"+args);
		loadBeans("/connexions.xml", "/swtclients.xml","/talibanalysisservices.xml");
		refresh();
	}

	public static void initPrefs(String pathToprops) {

		//Add db.properties path as variable to be retrieved when loading configuration file
		System.setProperty("dbproperites_file",pathToprops);
		System.setProperty("dbproperties", new File(pathToprops).getName());

		Properties props = new Properties();
		try {
			props.load(new FileInputStream((new File(pathToprops))));

			// Database
			initDbPathNname(props);

			if (props.containsKey("software"))
				MainPMScmd.getMyPrefs().put("software", props.getProperty("software"));
			if (props.containsKey("driver"))
				MainPMScmd.getMyPrefs().put("driver", props.getProperty("driver"));
			if (props.containsKey("host"))
				MainPMScmd.getMyPrefs().put("host", props.getProperty("host"));
			if (props.containsKey("port"))
				MainPMScmd.getMyPrefs().put("port", props.getProperty("port"));
			if (props.containsKey("username"))
				MainPMScmd.getMyPrefs().put("username", props.getProperty("username"));
			if (props.containsKey("password"))
				MainPMScmd.getMyPrefs().put("password", props.getProperty("password"));
			if (props.containsKey("db.poolsize"))
				MainPMScmd.getMyPrefs().put("db.poolsize", props.getProperty("db.poolsize"));
			// Tables shares
			if (props.containsKey("shares"))
				MainPMScmd.getMyPrefs().put("shares", props.getProperty("shares"));
			if (props.containsKey("symbol"))
				MainPMScmd.getMyPrefs().put("symbol", props.getProperty("symbol"));
			if (props.containsKey("isin"))
				MainPMScmd.getMyPrefs().put("isin", props.getProperty("isin"));
			if (props.containsKey("sicovam"))
				MainPMScmd.getMyPrefs().put("sicovam", props.getProperty("sicovam"));
			if (props.containsKey("name"))
				MainPMScmd.getMyPrefs().put("name", props.getProperty("name"));
			if (props.containsKey("date"))
				MainPMScmd.getMyPrefs().put("date", props.getProperty("date"));
			if (props.containsKey("open"))
				MainPMScmd.getMyPrefs().put("open", props.getProperty("open"));
			if (props.containsKey("close"))
				MainPMScmd.getMyPrefs().put("close", props.getProperty("close"));
			if (props.containsKey("high"))
				MainPMScmd.getMyPrefs().put("high", props.getProperty("high"));
			if (props.containsKey("low"))
				MainPMScmd.getMyPrefs().put("low", props.getProperty("low"));
			if (props.containsKey("volume"))
				MainPMScmd.getMyPrefs().put("volume", props.getProperty("volume"));
			if (props.containsKey("currency"))
				MainPMScmd.getMyPrefs().put("currency", props.getProperty("currency"));
			if (props.containsKey("lookup"))
				MainPMScmd.getMyPrefs().put("lookup", props.getProperty("lookup"));
			if (props.containsKey("lookup.symbol"))
				MainPMScmd.getMyPrefs().put("lookup.symbol", props.getProperty("lookup.symbol"));
			if (props.containsKey("lookup.isin"))
				MainPMScmd.getMyPrefs().put("lookup.isin", props.getProperty("lookup.isin"));
			if (props.containsKey("lookup.name"))
				MainPMScmd.getMyPrefs().put("lookup.name", props.getProperty("lookup.name"));
			if (props.containsKey("lookup.removable"))
				MainPMScmd.getMyPrefs().put("lookup.removable", props.getProperty("lookup.removable"));
			if (props.containsKey("lookup.category"))
				MainPMScmd.getMyPrefs().put("lookup.category", props.getProperty("lookup.category"));
			if (props.containsKey("lookup.lastquote"))
				MainPMScmd.getMyPrefs().put("lookup.lastquote", props.getProperty("lookup.lastquote"));
			// Tables lard feuille
			if (props.containsKey("portfolio.table"))
				MainPMScmd.getMyPrefs().put("portfolio.table", props.getProperty("portfolio.table"));
			if (props.containsKey("portfolio.symbol"))
				MainPMScmd.getMyPrefs().put("portfolio.symbol", props.getProperty("portfolio.symbol"));
			if (props.containsKey("portfolio.quantity"))
				MainPMScmd.getMyPrefs().put("portfolio.quantity", props.getProperty("portfolio.quantity"));
			if (props.containsKey("portfolio.cashin"))
				MainPMScmd.getMyPrefs().put("portfolio.cashin", props.getProperty("portfolio.cashin"));
			if (props.containsKey("portfolio.cashout"))
				MainPMScmd.getMyPrefs().put("portfolio.cashout", props.getProperty("portfolio.cashout"));
			if (props.containsKey("portfolio.name"))
				MainPMScmd.getMyPrefs().put("portfolio.name", props.getProperty("portfolio.name"));
			// Tables Events
			if (props.containsKey("events.table"))
				MainPMScmd.getMyPrefs().put("events.table", props.getProperty("events.table"));
			if (props.containsKey("events.symbol"))
				MainPMScmd.getMyPrefs().put("events.symbol", props.getProperty("events.symbol"));
			if (props.containsKey("events.accuracy"))
				MainPMScmd.getMyPrefs().put("events.accuracy", props.getProperty("events.accuracy"));
			if (props.containsKey("events.state"))
				MainPMScmd.getMyPrefs().put("events.state", props.getProperty("events.state"));
			if (props.containsKey("events.date"))
				MainPMScmd.getMyPrefs().put("events.date", props.getProperty("events.date"));
			if (props.containsKey("events.eventdefid"))
				MainPMScmd.getMyPrefs().put("events.eventdefid", props.getProperty("events.eventdefid"));
			if (props.containsKey("events.eventdef"))
				MainPMScmd.getMyPrefs().put("events.eventdef", props.getProperty("events.eventdef"));
			if (props.containsKey("events.type"))
				MainPMScmd.getMyPrefs().put("events.type", props.getProperty("events.type"));

			//Semaphore
			putInPrefs("alertcalculator.semaphore.nbthread",props);
			putInPrefs("indicatorcalculator.semaphore.nbthread",props);
			putInPrefs("indicEventsCalculator.semaphore.eventthread",props);
			putInPrefs("currencyconverter.semaphore.nbthread",props);
			putInPrefs("marketlistretrieval.semaphore.nbthread",props);
			putInPrefs("screeninginforetrieval.semaphore.nbthread",props);
			putInPrefs("quotationretrieval.semaphore.nbthread",props);
			putInPrefs("trendeventscalculation.semaphore.nbthread",props);
			putInPrefs("indicatortunning.semaphore.nbthread",props);

			//Analyse
			if (props.containsKey("mas.daysbackwardday"))
				MainPMScmd.getMyPrefs().put("mas.daysbackwardday", props.getProperty("mas.daysbackwardday"));
			if (props.containsKey("talib.daysbackwardday"))
				MainPMScmd.getMyPrefs().put("talib.daysbackwardday", props.getProperty("talib.daysbackwardday"));
			if (props.containsKey("autoporfolio.generatecsv"))
				MainPMScmd.getMyPrefs().put("autoporfolio.generatecsv", props.getProperty("autoporfolio.generatecsv"));
			if (props.containsKey("autoporfolio.generatepng"))
				MainPMScmd.getMyPrefs().put("autoporfolio.generatepng", props.getProperty("autoporfolio.generatepng"));
			putInPrefs("print.outputs",props);


			//Quotes
			if (props.containsKey("quotes.listfile"))
				MainPMScmd.getMyPrefs().put("quotes.listfile", props.getProperty("quotes.listfile"));
			if (props.containsKey("quotes.lastlistfetch"))
				MainPMScmd.getMyPrefs().put("quotes.lastlistfetch", props.getProperty("quotes.lastlistfetch"));
			if (props.containsKey("quotes.lastfetch"))
				MainPMScmd.getMyPrefs().put("quotes.lastfetch", props.getProperty("quotes.lastfetch"));
			if (props.containsKey("quotes.lastanalyse"))
				MainPMScmd.getMyPrefs().put("quotes.lastanalyse", props.getProperty("quotes.lastanalyse"));
			//			if (props.containsKey("quotes.provider"))
			//				MainPMScmd.getMyPrefs().put("quotes.provider", props.getProperty("quotes.provider"));
			//			if (props.containsKey("quotes.listprovider"))
			//				MainPMScmd.getMyPrefs().put("quotes.listprovider", props.getProperty("quotes.listprovider"));
			if (props.containsKey("analyse.mas.enable"))
				MainPMScmd.getMyPrefs().put("analyse.mas.enable", props.getProperty("analyse.mas.enable"));
			if (props.containsKey("quotes.sendeventfromui"))
				MainPMScmd.getMyPrefs().put("quotes.sendeventfromui", props.getProperty("quotes.sendeventfromui"));
			if (props.containsKey("quotes.listproviderindices"))
				MainPMScmd.getMyPrefs().put("quotes.listproviderindices", props.getProperty("quotes.listproviderindices"));

			//Events
			putInPrefs("event.sendAnalysisEventMsg",props);

			if (props.containsKey("event.stoploss"))
				MainPMScmd.getMyPrefs().put("event.stoploss", props.getProperty("event.stoploss"));
			if (props.containsKey("event.sellalert"))
				MainPMScmd.getMyPrefs().put("event.sellalert", props.getProperty("event.sellalert"));
			if (props.containsKey("event.buytrigger"))
				MainPMScmd.getMyPrefs().put("event.buytrigger", props.getProperty("event.buytrigger"));
			if (props.containsKey("event.selltrigger"))
				MainPMScmd.getMyPrefs().put("event.selltrigger", props.getProperty("event.selltrigger"));
			if (props.containsKey("event.maxloss"))
				MainPMScmd.getMyPrefs().put("event.maxloss", props.getProperty("event.maxloss"));

			if (props.containsKey("event.indicators"))
				MainPMScmd.getMyPrefs().put("event.indicators", props.getProperty("event.indicators"));
			if (props.containsKey("event.sellindicators"))
				MainPMScmd.getMyPrefs().put("event.sellindicators", props.getProperty("event.sellindicators"));
			if (props.containsKey("event.buyindicators"))
				MainPMScmd.getMyPrefs().put("event.buyindicators", props.getProperty("event.buyindicators"));
			putInPrefs("event.indepIndicators",props);

			if (props.containsKey("mail.infoalert.activated"))
				MainPMScmd.getMyPrefs().put("mail.infoalert.activated", props.getProperty("mail.infoalert.activated"));
			if (props.containsKey("event.backwarddayspan"))
				MainPMScmd.getMyPrefs().put("event.backwarddayspan", props.getProperty("event.backwarddayspan"));
			if (props.containsKey("event.expectedrate"))
				MainPMScmd.getMyPrefs().put("event.expectedrate", props.getProperty("event.expectedrate"));
			putInPrefs("event.nbPassMax",props);

			//Neural
			if (props.containsKey("event.buyponderationrule"))
				MainPMScmd.getMyPrefs().put("event.buyponderationrule", props.getProperty("event.buyponderationrule"));
			if (props.containsKey("event.sellponderationrule"))
				MainPMScmd.getMyPrefs().put("event.sellponderationrule", props.getProperty("event.sellponderationrule"));
			if (props.containsKey("perceptron.autoretrain"))
				MainPMScmd.getMyPrefs().put("perceptron.autoretrain", props.getProperty("perceptron.autoretrain"));
			if (props.containsKey("perceptron.filterTolerance"))
				MainPMScmd.getMyPrefs().put("perceptron.filterTolerance", props.getProperty("perceptron.filterTolerance"));
			putInPrefs("perceptron.trainingNbSlices",props);
			putInPrefs("perceptron.maxNbOfTrainingYears",props);
			putInPrefs("perceptron.minNbOfTrainingYears",props);
			putInPrefs("perceptron.perceptronMinMonthEvents",props);
			putInPrefs("perceptron.stampoutput",props);
			putInPrefs("perceptron.exportoutput",props);
			if (System.getProperty("neural.nbTrainingIter") != null) {
				MainPMScmd.getMyPrefs().put("neural.nbTrainingIter", System.getProperty("neural.nbTrainingIter"));
			}
			else if (System.getProperty("PARAM1") != null) {
				MainPMScmd.getMyPrefs().put("neural.nbTrainingIter", System.getProperty("PARAM1"));
			} else {
				putInPrefs("neural.nbTrainingIter",props);
			}
			putInPrefs("perceptron.trainingPMEventOccLowerSpan",props);
			putInPrefs("perceptron.expectedSmothingSMAPeriod",props);
			putInPrefs("perceptron.trainOutNbDaysAhead",props);
			putInPrefs("perceptron.outputGeneratorInst",props);
			putInPrefs("perceptron.expectedSmothingSMALag",props);

			//Roc
			putInPrefs("rocnneural.houseTrendPeriod",props);
			putInPrefs("rocnneural.quoteSmthPeriod",props);

			//Sector
			if (System.getProperty("sector.nbTrainingIter") != null) {
				MainPMScmd.getMyPrefs().put("sector.nbTrainingIter", System.getProperty("sector.nbTrainingIter"));
			}
			else if (System.getProperty("PARAM2") != null) {
				MainPMScmd.getMyPrefs().put("sector.nbTrainingIter", System.getProperty("PARAM2"));
			} else {
				putInPrefs("sector.nbTrainingIter",props);
			}
			putInPrefs("sectors.nbStepsForward",props);
			putInPrefs("sectors.isTrainingConstrained",props);
			putInPrefs("sectors.refHTSmoothSMA",props);
			putInPrefs("sectors.sectorsHTSmoothSMA",props);
			putInPrefs("sectors.supportStocks",props);
			putInPrefs("sectors.inputGenerator",props);

			//Tune 
			putInPrefs("perceptron.retuneSpan", props);
			putInPrefs("neural.retuneFreq", props);
			putInPrefs("sector.retuneFreq", props);
			putInPrefs("tuning.configs",props);
			putInPrefs("perceptron.nbfolds",props);
			putInPrefs("perceptron.foldsize",props);
			putInPrefs("nbMonthCalculation.SECTOR",props);
			putInPrefs("nbMonthCalculation.NEURAL",props);
			putInPrefs("nbMonthCalculation.gxWebNeural",props);
			
			//Keras
			putInPrefs("kerasweb.hostip",props);
			putInPrefs("kerasweb.hostport",props);

			//Q fact
			putInPrefs("bean.quotationFactory", props);

			//Indicators
			putInPrefs("indicators.smareversalsmaperiod",props);
			putInPrefs("indicators.stddevsmaperiod",props);
			putInPrefs("indicators.variancesmaperiod",props);

			putInPrefs("indicators.macd.fastperiod",props);
			putInPrefs("indicators.macd.slowperiod",props);
			putInPrefs("indicators.macd.signal",props);

			putInPrefs("indicators.variation.period",props);
			putInPrefs("indicators.variation.spandiff",props);

			putInPrefs("indicators.variance.period",props);
			putInPrefs("indicators.variance.spandiff",props);
			putInPrefs("indicators.variance.minvalid",props);

			putInPrefs("indicators.returnoutput", props);

			//Screener Trend
			if (props.containsKey("trend.sellthreshold"))
				MainPMScmd.getMyPrefs().put("trend.sellthreshold", props.getProperty("trend.sellthreshold"));
			if (props.containsKey("trend.buythreshold"))
				MainPMScmd.getMyPrefs().put("trend.buythreshold", props.getProperty("trend.buythreshold"));
			putInPrefs("marketlistretrieval.trendSuppNeeded",props);

			//Gnu
			if (props.containsKey("gnurepport.dateformat"))
				MainPMScmd.getMyPrefs().put("gnurepport.dateformat", props.getProperty("gnurepport.dateformat"));

			//End date test
			putInPrefs("test.endDate", props);

			//Event cache
			System.out.println("event cache is "+props.getProperty("event.cache"));
			putInPrefs("event.cache", props);

			MainPMScmd.getMyPrefs().flushy();

		} catch (Exception e) {
			System.out.println("Couldn't init DataSource. Check the propeties path");
			e.printStackTrace();
		}
	}

	public static void initDbPathNname(Properties props) {

		//Update the prefs
		String commandLineDb = System.getProperty("dbpath");
		String propertyFileDb = props.getProperty("dbpath");
		if (commandLineDb != null) {
			MainPMScmd.getMyPrefs().put("dbpath", commandLineDb);
		} 
		else if (propertyFileDb != null) {
			MainPMScmd.getMyPrefs().put("dbpath", propertyFileDb);
		}

		if (props.containsKey("database")) MainPMScmd.getMyPrefs().put("database", props.getProperty("database"));
		MainPMScmd.getMyPrefs().flushy();

		DataSource.DB_PATH_NNAME = null;
		if (commandLineDb != null) {
			DataSource.DB_PATH_NNAME = commandLineDb;
		} 
		else if (propertyFileDb != null) {
			DataSource.DB_PATH_NNAME = propertyFileDb;
		} 
		else {
			String fallBackDb = "derby/";
			DataSource.DB_PATH_NNAME = fallBackDb;
		}

		if (DataSource.DB_PATH_NNAME.endsWith("/")) DataSource.DB_PATH_NNAME = DataSource.DB_PATH_NNAME.substring(0,DataSource.DB_PATH_NNAME.length()-1);
		DataSource.DB_PATH_NNAME = DataSource.DB_PATH_NNAME + "/" + ((props.containsKey("database"))?props.get("database"):"premiummarkets");

		System.setProperty("dbpathNname", DataSource.dbPathNname());

	}

	private static void putInPrefs(String property, Properties props) {
		if (props.containsKey(property)) MainPMScmd.getMyPrefs().put(property, props.getProperty(property));
	}

	public void optionalPostInit() {

		PostInitMonitor.startOptPostInit();

		//ops reflective generators
		final TalibOperationGenerator talibOperationGenerator = SpringContext.getSingleton().getBean(TalibOperationGenerator.class);
		final TalibIndicatorsCompositionerOperationReflectiveGenerator talibIndicatorsCompositionerOperationReflectiveGenerator = SpringContext.getSingleton().getBean(TalibIndicatorsCompositionerOperationReflectiveGenerator.class);

		//ops instances handlers
		final ParameterizedOperationBuilder parameterizedOperationBuilder = SpringContext.getSingleton().getBean(ParameterizedOperationBuilder.class);
		final ParameterizedIndicatorsBuilder parameterizedIndicatorsBuilder = SpringContext.getSingleton().getBean(ParameterizedIndicatorsBuilder.class);

		final EventSignalConfig config = (EventSignalConfig) ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME);

		new Thread(new Runnable() {
			public void run() {

				ConfigThreadLocal.set(EventSignalConfig.EVENT_SIGNAL_NAME, config);
				try {
					//Other ops reflective generators
					List<OperationReflectiveGenerator> operationReflectiveGenerators = new ArrayList<>();
					try {
						OperationReflectiveGenerator operationReflectiveGenerator = (OperationReflectiveGenerator) SpringContext.getSingleton().getBean("desiredGenericOperationReflectiveGenerator");
						operationReflectiveGenerators.add(operationReflectiveGenerator);
					} catch (BeansException e) {
						System.out.println(e);
					}

					parameterizedOperationBuilder.init(
							talibOperationGenerator, talibIndicatorsCompositionerOperationReflectiveGenerator,
							operationReflectiveGenerators.toArray(new OperationReflectiveGenerator[0])
							);
					parameterizedIndicatorsBuilder.init();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					PostInitMonitor.stopOptPostInit();
				}
				//parameterizedOperationBuilder.getCurrentOperations(false).putAll(parameterizedIndicatorsBuilder.getCurrentOperations(false));

			}
		}).start();

	}

}
