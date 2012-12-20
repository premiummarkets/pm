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
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;


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
	public SpringContext() {
		super();
		singleton = this;
	}
	
	public SpringContext(ConfigurableApplicationContext configurableApplicationContext) {
		super(configurableApplicationContext);
		singleton = this;
	}
	
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
	public void setDataSource(String arg) {
		
		//Add db.properties path as variable to be retrieved when loading configuration file
		System.setProperty("dbproperites_file",arg);
		System.setProperty("dbproperties", new File(arg).getName());
		
		ConstructorArgumentValues dataSCAV = new  ConstructorArgumentValues();
        dataSCAV.addGenericArgumentValue(arg,"java.lang.String");
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
		setDataSource(args[0]);
		ArrayList<String> springconf = new ArrayList<String>(Arrays.asList(new String[] { "/connexions.xml", "/swtclients.xml","/talibanalysisservices.xml"}));
		loadBeans(springconf.toArray(new String[0]));
		refresh();
	}
}
