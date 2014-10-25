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
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.web.ProvidersTypes;

/**
 * Defines a market list providers
 * Links each market place with one to many web stock provider 
 * 
 * @author Guillaume Thoreton
 */
public class SharesListId  {
	
	public static final SharesListId UNKNOWN = new SharesListId("UNKNOWN", "unknown", ProvidersTypes.NONE, "Other stocks not related to a market", "Not in any market", false, false, 	new String[0]);

	private static List<SharesListId> shareListIds = new ArrayList<SharesListId>();

	private static MyLogger LOGGER = MyLogger.getLogger(SharesListId.class);

	private final String name;
	private final String sharesListCmdParam;
	private final ProvidersTypes providersType;
	
	private final String comment;
	private final String description;
	private final Boolean isIndicesComposite;
	private final Boolean isDownloadable;
	
	private final String[] options;

	public SharesListId(String name, String cmdParam, ProvidersTypes providersType, String comment, String description, Boolean isIndicesComposite, Boolean isDownloadable, String[] options) {
		this.name = name;
		this.providersType = providersType;
		this.sharesListCmdParam = cmdParam;
		this.comment = comment;
		this.description=description;
		this.isIndicesComposite = isIndicesComposite;
		this.isDownloadable = isDownloadable;
		this.options = options;
	}
	
	private SharesListId(String name) {
		this.name = name;
		this.sharesListCmdParam = null;
		this.providersType = null;
		this.comment = null;
		this.description = null;
		this.isIndicesComposite = false;
		this.isDownloadable = false;
		this.options = null;
		
	}

	public static SharesListId[] values() {
		return SharesListId.shareListIds.toArray(new SharesListId[]{});
	}
	
	public static SharesListId valueOf(String shareListBaseName) {
		Integer ordinal = new SharesListId(shareListBaseName).ordinal();
		if (ordinal == -1) throw new IllegalArgumentException();
		return SharesListId.shareListIds.get(ordinal);
	}
	
	public static void addSharesListId(SharesListId sharesListId) {
		if (sharesListId != null && !SharesListId.shareListIds.contains(sharesListId)) {
			SharesListId.shareListIds.add(sharesListId);
		}
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
	
	public Integer ordinal() {
		return SharesListId.shareListIds.indexOf(this);
	}

	public String name() {
		return name;
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
	
	//TODO : several share lists ie use the share lists with customised quotation providers and indices in DB instead of props.
//	public static void updatePrefs(String shareListName, String indices) {
////		MainPMScmd.getMyPrefs().put("quotes.listprovider", shareListName);
////		MainPMScmd.getMyPrefs().put("quotes.listproviderindices", indices);
////		MainPMScmd.getMyPrefs().put("quotes.provider", quotationProvider);
////		MainPMScmd.getMyPrefs().flushy();
//	}
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SharesListId other = (SharesListId) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	public Boolean getIsDownloadable() {
		return isDownloadable;
	}
	
}
