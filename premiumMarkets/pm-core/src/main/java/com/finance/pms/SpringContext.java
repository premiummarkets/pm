/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.prefs.BackingStoreException;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.finance.pms.datasources.db.DataSource;


// TODO: Auto-generated Javadoc
/**
 * The Class SpringContext.
 * 
 * @author Guillaume Thoreton
 */
public class SpringContext extends GenericApplicationContext {

	private static SpringContext singleton;
	
	
	public static SpringContext getSingleton() {
		return singleton;
	}

	private boolean hasBeenClosed = false;

	/**
	 * Instantiates a new spring context.
	 * 
	 * @author Guillaume Thoreton
	 */
	public SpringContext(String propsFile) {
		super();
		initPrefs(propsFile);
		setDataSource();
		singleton = this;
	}
	
	public SpringContext(ConfigurableApplicationContext configurableApplicationContext) {
		super(configurableApplicationContext);
		//initPrefs(propsFile);
		setDataSource();
		singleton = this;
	}
	
	//??
	public SpringContext(GenericApplicationContext beanFactory) {
		super(beanFactory);
	}

	@Override
	public void close() {
		super.close();
		this.hasBeenClosed  = true;
	}
	
	public boolean isHasBeenClosed() {
		return hasBeenClosed;
	}
	
	/**
	 * Sets the data source.
	 * 
	 * @param arg the new data source
	 */
	//TODO back to xml configs
	private void setDataSource() {
		
		ConstructorArgumentValues dataSCAV = new  ConstructorArgumentValues();
       // dataSCAV.addGenericArgumentValue(arg,"java.lang.String");
        RootBeanDefinition dataS = new RootBeanDefinition("com.finance.pms.datasources.db.DataSource",dataSCAV,new MutablePropertyValues());
        dataS.setDestroyMethodName("stopThreads");
        this.registerBeanDefinition("dataSource", dataS);
 
	}
	
	/**
	 * Sets the mas source.
	 * 
	 * @param args the new mas source
	 */
	public void setMasSource(String... args) {
		
		//Add db.properties path as variable to be retrieved when loading configuration file
		System.setProperty("dbproperites_file",args[0]);
		
        ConstructorArgumentValues masSCAV = new  ConstructorArgumentValues();
        masSCAV.addGenericArgumentValue(args[0],"java.lang.String");
        masSCAV.addGenericArgumentValue((args.length==2)?new Boolean(args[1]):true,"java.lang.Boolean");
        RootBeanDefinition masS = new RootBeanDefinition("com.finance.pms.mas.MasSource",masSCAV,  new MutablePropertyValues());
        masS.setDestroyMethodName("stopThreads");
        
        this.registerBeanDefinition("masSource", masS);
     
	}
	
	/**
	 * Load beans.
	 * 
	 * @param args the args
	 * 
	 * @author Guillaume Thoreton
	 */
	public void loadBeans(String ...args) {
	
        XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(this);
        for (String str: args) {
        	xmlReader.loadBeanDefinitions(new ClassPathResource(str));
        }
		
	}
	
	/**
	 * @param ctx
	 * @param args
	 */
	public void standardInit(String[] args) {
		//setDataSource(args[0]);
		ArrayList<String> springconf = new ArrayList<String>(Arrays.asList(new String[] { "/connexions.xml", "/swtclients.xml","/talibanalysisservices.xml"}));
		loadBeans(springconf.toArray(new String[0]));
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
			//System.setProperty("dbpathNname", DataSource.dbPathNname());
			initDbPathNname(props);
			
			if (props.containsKey("software"))
				MainPMScmd.getPrefs().put("software", props.getProperty("software"));
			if (props.containsKey("driver"))
				MainPMScmd.getPrefs().put("driver", props.getProperty("driver"));
			if (props.containsKey("host"))
				MainPMScmd.getPrefs().put("host", props.getProperty("host"));
			if (props.containsKey("port"))
				MainPMScmd.getPrefs().put("port", props.getProperty("port"));
			if (props.containsKey("username"))
				MainPMScmd.getPrefs().put("username", props.getProperty("username"));
			if (props.containsKey("password"))
				MainPMScmd.getPrefs().put("password", props.getProperty("password"));
			if (props.containsKey("db.poolsize"))
				MainPMScmd.getPrefs().put("db.poolsize", props.getProperty("db.poolsize"));
			// Tables shares
			if (props.containsKey("shares"))
				MainPMScmd.getPrefs().put("shares", props.getProperty("shares"));
			if (props.containsKey("symbol"))
				MainPMScmd.getPrefs().put("symbol", props.getProperty("symbol"));
			if (props.containsKey("isin"))
				MainPMScmd.getPrefs().put("isin", props.getProperty("isin"));
			if (props.containsKey("sicovam"))
				MainPMScmd.getPrefs().put("sicovam", props.getProperty("sicovam"));
			if (props.containsKey("name"))
				MainPMScmd.getPrefs().put("name", props.getProperty("name"));
			if (props.containsKey("date"))
				MainPMScmd.getPrefs().put("date", props.getProperty("date"));
			if (props.containsKey("open"))
				MainPMScmd.getPrefs().put("open", props.getProperty("open"));
			if (props.containsKey("close"))
				MainPMScmd.getPrefs().put("close", props.getProperty("close"));
			if (props.containsKey("high"))
				MainPMScmd.getPrefs().put("high", props.getProperty("high"));
			if (props.containsKey("low"))
				MainPMScmd.getPrefs().put("low", props.getProperty("low"));
			if (props.containsKey("volume"))
				MainPMScmd.getPrefs().put("volume", props.getProperty("volume"));
			if (props.containsKey("currency"))
				MainPMScmd.getPrefs().put("currency", props.getProperty("currency"));
			if (props.containsKey("lookup"))
				MainPMScmd.getPrefs().put("lookup", props.getProperty("lookup"));
			if (props.containsKey("lookup.symbol"))
				MainPMScmd.getPrefs().put("lookup.symbol", props.getProperty("lookup.symbol"));
			if (props.containsKey("lookup.isin"))
				MainPMScmd.getPrefs().put("lookup.isin", props.getProperty("lookup.isin"));
			if (props.containsKey("lookup.name"))
				MainPMScmd.getPrefs().put("lookup.name", props.getProperty("lookup.name"));
			if (props.containsKey("lookup.removable"))
				MainPMScmd.getPrefs().put("lookup.removable", props.getProperty("lookup.removable"));
			if (props.containsKey("lookup.category"))
				MainPMScmd.getPrefs().put("lookup.category", props.getProperty("lookup.category"));
			if (props.containsKey("lookup.lastquote"))
				MainPMScmd.getPrefs().put("lookup.lastquote", props.getProperty("lookup.lastquote"));
			// Tables lard feuille
			if (props.containsKey("portfolio.table"))
				MainPMScmd.getPrefs().put("portfolio.table", props.getProperty("portfolio.table"));
			if (props.containsKey("portfolio.symbol"))
				MainPMScmd.getPrefs().put("portfolio.symbol", props.getProperty("portfolio.symbol"));
			if (props.containsKey("portfolio.quantity"))
				MainPMScmd.getPrefs().put("portfolio.quantity", props.getProperty("portfolio.quantity"));
			if (props.containsKey("portfolio.cashin"))
				MainPMScmd.getPrefs().put("portfolio.cashin", props.getProperty("portfolio.cashin"));
			if (props.containsKey("portfolio.cashout"))
				MainPMScmd.getPrefs().put("portfolio.cashout", props.getProperty("portfolio.cashout"));
			if (props.containsKey("portfolio.name"))
				MainPMScmd.getPrefs().put("portfolio.name", props.getProperty("portfolio.name"));
			// Tables Events
			if (props.containsKey("events.table"))
				MainPMScmd.getPrefs().put("events.table", props.getProperty("events.table"));
			if (props.containsKey("events.symbol"))
				MainPMScmd.getPrefs().put("events.symbol", props.getProperty("events.symbol"));
			if (props.containsKey("events.accuracy"))
				MainPMScmd.getPrefs().put("events.accuracy", props.getProperty("events.accuracy"));
			if (props.containsKey("events.state"))
				MainPMScmd.getPrefs().put("events.state", props.getProperty("events.state"));
			if (props.containsKey("events.date"))
				MainPMScmd.getPrefs().put("events.date", props.getProperty("events.date"));
			if (props.containsKey("events.eventdefid"))
				MainPMScmd.getPrefs().put("events.eventdefid", props.getProperty("events.eventdefid"));
			if (props.containsKey("events.eventdef"))
				MainPMScmd.getPrefs().put("events.eventdef", props.getProperty("events.eventdef"));
			if (props.containsKey("events.type"))
				MainPMScmd.getPrefs().put("events.type", props.getProperty("events.type"));
			
			// Semaphore
			putInPrefs("alertcalculator.semaphore.nbthread",props);
			putInPrefs("indicatorcalculator.semaphore.nbthread",props);
			putInPrefs("currencyconverter.semaphore.nbthread",props);
			putInPrefs("marketlistretrieval.semaphore.nbthread",props);
			putInPrefs("screeninginforetrieval.semaphore.nbthread",props);
			putInPrefs("quotationretrieval.semaphore.nbthread",props);
			putInPrefs("trendeventscalculation.semaphore.nbthread",props);
			putInPrefs("indicatortunning.semaphore.nbthread",props);

			//Analyse
			if (props.containsKey("mas.daysbackwardday"))
				MainPMScmd.getPrefs().put("mas.daysbackwardday", props.getProperty("mas.daysbackwardday"));
			if (props.containsKey("talib.daysbackwardday"))
				MainPMScmd.getPrefs().put("talib.daysbackwardday", props.getProperty("talib.daysbackwardday"));
			
			//Quotes
			if (props.containsKey("quotes.listfile"))
				MainPMScmd.getPrefs().put("quotes.listfile", props.getProperty("quotes.listfile"));
			if (props.containsKey("quotes.lastlistfetch"))
				MainPMScmd.getPrefs().put("quotes.lastlistfetch", props.getProperty("quotes.lastlistfetch"));
			if (props.containsKey("quotes.lastfetch"))
				MainPMScmd.getPrefs().put("quotes.lastfetch", props.getProperty("quotes.lastfetch"));
			if (props.containsKey("quotes.lastanalyse"))
				MainPMScmd.getPrefs().put("quotes.lastanalyse", props.getProperty("quotes.lastanalyse"));
			if (props.containsKey("quotes.provider"))
				MainPMScmd.getPrefs().put("quotes.provider", props.getProperty("quotes.provider"));
			if (props.containsKey("quotes.listprovider"))
				MainPMScmd.getPrefs().put("quotes.listprovider", props.getProperty("quotes.listprovider"));
			if (props.containsKey("analyse.mas.enable"))
				MainPMScmd.getPrefs().put("analyse.mas.enable", props.getProperty("analyse.mas.enable"));
			if (props.containsKey("quotes.sendeventfromui"))
				MainPMScmd.getPrefs().put("quotes.sendeventfromui", props.getProperty("quotes.sendeventfromui"));
			if (props.containsKey("quotes.listproviderindices"))
				MainPMScmd.getPrefs().put("quotes.listproviderindices", props.getProperty("quotes.listproviderindices"));
			
			//Events
			putInPrefs("event.sendAnalysisEventMsg",props);
			
			if (props.containsKey("event.stoploss"))
				MainPMScmd.getPrefs().put("event.stoploss", props.getProperty("event.stoploss"));
			if (props.containsKey("event.sellalert"))
				MainPMScmd.getPrefs().put("event.sellalert", props.getProperty("event.sellalert"));
			if (props.containsKey("event.buytrigger"))
				MainPMScmd.getPrefs().put("event.buytrigger", props.getProperty("event.buytrigger"));
			if (props.containsKey("event.selltrigger"))
				MainPMScmd.getPrefs().put("event.selltrigger", props.getProperty("event.selltrigger"));
			if (props.containsKey("event.maxloss"))
				MainPMScmd.getPrefs().put("event.maxloss", props.getProperty("event.maxloss"));
			
			if (props.containsKey("event.indicators"))
				MainPMScmd.getPrefs().put("event.indicators", props.getProperty("event.indicators"));
			if (props.containsKey("event.sellindicators"))
				MainPMScmd.getPrefs().put("event.sellindicators", props.getProperty("event.sellindicators"));
			if (props.containsKey("event.buyindicators"))
				MainPMScmd.getPrefs().put("event.buyindicators", props.getProperty("event.buyindicators"));
			putInPrefs("event.indepIndicators",props);
			
			if (props.containsKey("mail.infoalert.activated"))
				MainPMScmd.getPrefs().put("mail.infoalert.activated", props.getProperty("mail.infoalert.activated"));
			if (props.containsKey("event.backwarddayspan"))
				MainPMScmd.getPrefs().put("event.backwarddayspan", props.getProperty("event.backwarddayspan"));
			if (props.containsKey("event.expectedrate"))
				MainPMScmd.getPrefs().put("event.expectedrate", props.getProperty("event.expectedrate"));
			putInPrefs("event.nbPassMax",props);
			
			//Neural
			if (props.containsKey("event.buyponderationrule"))
				MainPMScmd.getPrefs().put("event.buyponderationrule", props.getProperty("event.buyponderationrule"));
			if (props.containsKey("event.sellponderationrule"))
				MainPMScmd.getPrefs().put("event.sellponderationrule", props.getProperty("event.sellponderationrule"));
			if (props.containsKey("perceptron.autoretrain"))
				MainPMScmd.getPrefs().put("perceptron.autoretrain", props.getProperty("perceptron.autoretrain"));
			if (props.containsKey("perceptron.filterTolerance"))
				MainPMScmd.getPrefs().put("perceptron.filterTolerance", props.getProperty("perceptron.filterTolerance"));
			putInPrefs("perceptron.trainingNbSlices",props);
			putInPrefs("perceptron.maxNbOfTrainingYears",props);
			putInPrefs("perceptron.minNbOfTrainingYears",props);
			putInPrefs("perceptron.perceptronMinMonthEvents",props);
			putInPrefs("perceptron.stampoutput",props);
			putInPrefs("perceptron.exportoutput",props);
			if (System.getProperty("neural.nbTrainingIter") != null) {
				MainPMScmd.getPrefs().put("neural.nbTrainingIter", System.getProperty("neural.nbTrainingIter"));
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
				MainPMScmd.getPrefs().put("sector.nbTrainingIter", System.getProperty("sector.nbTrainingIter"));
			} else {
				putInPrefs("sector.nbTrainingIter",props);
			}
			putInPrefs("sectors.nbStepsForward",props);
			putInPrefs("sectors.isTrainingConstrained",props);
			putInPrefs("sectors.refHTSmoothSMA",props);
			putInPrefs("sectors.sectorsHTSmoothSMA",props);
			putInPrefs("sectors.supportStocks",props);
			
			//Tune 
			putInPrefs("perceptron.retuneSpan", props);
			putInPrefs("neural.retuneFreq", props);
			putInPrefs("sector.retuneFreq", props);
			putInPrefs("tuning.configs",props);
			putInPrefs("perceptron.nbfolds",props);
			putInPrefs("perceptron.foldsize",props);
			
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
			
			//Screnner Trend
			if (props.containsKey("trend.sellthreshold"))
				MainPMScmd.getPrefs().put("trend.sellthreshold", props.getProperty("trend.sellthreshold"));
			if (props.containsKey("trend.buythreshold"))
				MainPMScmd.getPrefs().put("trend.buythreshold", props.getProperty("trend.buythreshold"));
			putInPrefs("marketlistretrieval.trendSuppNeeded",props);
			
			//Gnu
			if (props.containsKey("gnurepport.dateformat"))
				MainPMScmd.getPrefs().put("gnurepport.dateformat", props.getProperty("gnurepport.dateformat"));
			
			//End date test
			putInPrefs("test.endDate", props);
			
			//Event cache
			System.out.println("event cache is "+props.getProperty("event.cache"));
			putInPrefs("event.cache", props);
			
			MainPMScmd.getPrefs().flush();
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
			MainPMScmd.getPrefs().put("dbpath", commandLineDb);
		} 
		else if (propertyFileDb != null) {
			MainPMScmd.getPrefs().put("dbpath", propertyFileDb);
		}

		if (props.containsKey("database")) MainPMScmd.getPrefs().put("database", props.getProperty("database"));

		try {
			MainPMScmd.getPrefs().flush();
		} catch (BackingStoreException e) {

		}

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
		

	/**
	 * @param property 
	 * @param props
	 */
	private static void putInPrefs(String property, Properties props) {
		if (props.containsKey(property))
			MainPMScmd.getPrefs().put(property, props.getProperty(property));
	}
	
}
