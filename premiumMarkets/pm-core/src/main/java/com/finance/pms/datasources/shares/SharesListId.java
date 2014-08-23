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
package com.finance.pms.datasources.shares;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.web.ProvidersTypes;

/**
 * The Enum MarketListProviders.
 * Defines a market list providers
 * Links each market place with one to many web stock provider 
 * 
 * @author Guillaume Thoreton
 */
public enum SharesListId {
	
	ALLMARKETS ("allMarkets", ProvidersTypes.ALLMARKETS, 
			"The format is as follow  ALL:<MARKET ID>.\n Where MARKET ID is like : "+ Arrays.asList(Market.values())+"\n", "Full market", true, SharesListId.sharesListIdOptionsForAllMarkets()),
	YAHOOINDICES ("yahooIndices",ProvidersTypes.YAHOOINDICES, "Your custom indice format is <INDICE ID>:<MARKET ID>.\n " +
			"Where INDICE ID like NDX, FTLC, SBF250 ...\n" +
			"(these are yahoo indices like the one you can find at http://finance.yahoo.com/indices for instance.)\n " +
			"and MARKET ID is like : "+ Arrays.asList(Market.values()) + ".\n" +
			"(ex : NDX:NASDAQ,NY:NYSE,FTLC:LSE,SBF250:EURONEXT... standing for nasdaq-100, nyse comp index, ftse 350 ...)\n" +
			"You can specify several indices comma separeted and build you own composite list.\n" +
			"If left blank, it will aggregate all the prexisting YAHOO indices available in your database.\n", "YAHOO INDICES components", true,  SharesListId.sharesListIdOptionsForYahooIndices()),
	NSEINDICES ("nseIndices",ProvidersTypes.NSEINDICES, "Your custom indice format is <INDICE ID>:<MARKET ID>.\n " +
					"Where INDICE ID known so far are NIFTY, JRNIFTYLISR, CNX100, CNX200, CNX500, NIFTYMIDCAP50, CNXMIDCAP, CNXSMALLCAP\n" +
					"(these are nse indices you can find at nseindia.com)\n " +
					"and MARKET ID  has to be "+Market.NSE+".\n" +
					"(ex : NIFTY:NSE, CNX100:NSE, ... standing for cnx nifty, cnx 100 ...)\n" +
					"You can specify several indices comma separeted and build you own composite list.\n" +
					"If left blank, it will aggregate all the prexisting NSE indices available in your database.\n", "NSE INDICES components", true, 
					new String[]{"NIFTY:NSE", "NIFTY:NSE", "JRNIFTYLISR:NSE", "CNX100:NSE", "CNX200:NSE", "CNX500:NSE", "NIFTYMIDCAP50:NSE", "CNXMIDCAP:NSE", "CNXSMALLCAP:NSE"}),
	BSE ("bse",ProvidersTypes.BSE,"No comment","All BSE from bseindia.com", false, new String[0]),
	
	//Broken
	GOOGLENYSE ("nyse",ProvidersTypes.GOOGLE,"No comment","All NYSE from google.com", false, new String[0]),
	GOOGLEAMEX ("amex",ProvidersTypes.GOOGLE, "No comment","All AMEX from google.com", false, new String[0]),
	EURONEXT ("euronext",ProvidersTypes.EURONEXT,"No comment", "All EURONEXT shares from euronext.com", false, new String[0]),
	BOURSORAMA ("boursorama",ProvidersTypes.BOURSORAMA,"No comment","All EURONEXT shares from boursorama.com", false, new String[0]),
	NASDAQ ("nasdaq",ProvidersTypes.NASDAQ,"No comment","All NASDAQ from nasdaq.com", false, new String[0]),
	ASX ("asx",ProvidersTypes.ASX,"No comment","All ASX from asx.com.au", false, new String[0]),
	
	//Other
	UNKNOWN ("unknown",ProvidersTypes.NONE,"No comment","No market", false, new String[0]);


	private static MyLogger LOGGER = MyLogger.getLogger(SharesListId.class);

	private String sharesListCmdParam;
	private ProvidersTypes providersType;
	
	private String comment;
	private String description;
	private Boolean isIndicesComposite;
	
	private String[] options;

	private SharesListId(String cmdParam, ProvidersTypes providersType, String comment, String description, Boolean isIndicesComposite, String[] options) {
		this.providersType = providersType;
		this.sharesListCmdParam = cmdParam;
		this.comment = comment;
		this.description=description;
		this.isIndicesComposite = isIndicesComposite;
		this.options = options;
	}

	public static SharesListId valueOfCmd(String cmdString) {
		SharesListId eValues[] = SharesListId.values();
		for (int i=0; i < eValues.length; i++) {
			if (eValues[i].sharesListCmdParam.equals(cmdString)) return eValues[i];
		}
		throw new IllegalArgumentException("No enum const MarketListProviders." + cmdString);
	}

	public static boolean checkProvider(String prov) {
		boolean contains = false;
		try {
			SharesListId.valueOfCmd(prov);
			contains = true;
		} catch (IllegalArgumentException e) {
			LOGGER.debug("Wrong provider :" + prov, e);
			contains = false;
		}
		return contains;
	}

	public static String[] cmdsValues() {
		String[] retour = new String[SharesListId.values().length];
		for (int i=0; i < SharesListId.values().length; i++) {
			retour[i] = SharesListId.values()[i].sharesListCmdParam;
		}
		return retour;	
	}

	public String getSharesListCmdParam() {
		return sharesListCmdParam;
	}

	public ProvidersTypes getProvidersType() {
		return providersType;
	}
	
	public String getDescription() {
		return description;
	}

	public Boolean getIsIndicesComposite() {
		return isIndicesComposite;
	}
	
	public static List<Integer> getCompositeListsOrdinals() {
		List<Integer> sharesListIds = new ArrayList<Integer>();
		for (SharesListId shareListId : SharesListId.values()) {
			if (shareListId.isIndicesComposite) sharesListIds.add(shareListId.ordinal());
		}
		return sharesListIds;
	}

	public static int getNbCompositeLists() {
		int ret = 0;
		for (SharesListId shareListId : SharesListId.values()) {
			if (shareListId.isIndicesComposite) ret++;
		}
		return ret;
	}

	public String getComment() {
		return comment;
	}
	
	//TODO :several share lists ie use the share lists in db instead of props.
	public static void updatePrefs(String shareListName, String indices, String quotationProvider) {
		MainPMScmd.getMyPrefs().put("quotes.listprovider", shareListName);
		MainPMScmd.getMyPrefs().put("quotes.listproviderindices", indices);
		MainPMScmd.getMyPrefs().put("quotes.provider", quotationProvider);
		MainPMScmd.getMyPrefs().flushy();
	}
	
	public static String[] sharesListIdOptionsForAllMarkets() {
		SortedSet<String> ret = new TreeSet<String>();
		for (Market market : Market.values()) {
			if (market.getHasStaticAllMarket()) {
				ret.add("ALL:" + market.name());
			}
		}
		return ret.toArray(new String[0]);
	}
	
	public static String[] sharesListIdOptionsForYahooIndices() {
		SortedSet<String> ret = new TreeSet<String>();
		for (Market market : Market.values()) {
			for (String indice : market.getYahooIndices()) {
				ret.add(indice+ ":" + market.name());
			}
		}
		return ret.toArray(new String[0]);
	}

	public String[] getOptions() {
		return options;
	}


}
