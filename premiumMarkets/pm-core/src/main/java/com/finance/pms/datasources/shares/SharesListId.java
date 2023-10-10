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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.web.ProvidersTypes;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Wrong provider :" + prov, e);
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
	
	//TODO : several share lists ie use the share lists with customized quotation providers and indices in DB instead of props.
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
				ret.add(indice + ":" + market.name());
			}
		}
		return ret.toArray(new String[0]);
	}
	
	public static String[] sharesListIdOptionsForStockSymbolApi() {
		
		SortedSet<String> ret = new TreeSet<String>();
		
		String rootPath = MainPMScmd.getMyPrefs().get("quotes.stockSymbolApiDumpRootPath", "");
		Path marketListFilePath = Path.of(rootPath, "markets_lists.json");
		
		if (!Files.exists(marketListFilePath)) {
			try {
				Path python_py = Files.createTempFile("stock-symbol_market_list", "py");
				try (InputStream stream = SharesListId.class.getResourceAsStream("/stockSymbolApi/stock-symbol_market_list.py")) {
					Files.copy(stream, python_py, StandardCopyOption.REPLACE_EXISTING);
					PosixFileAttributeView view = Files.getFileAttributeView(python_py, PosixFileAttributeView.class);
					if (view != null) {
						Set<PosixFilePermission> perms = view.readAttributes().permissions();
						if (perms.add(PosixFilePermission.OWNER_EXECUTE)) {
							view.setPermissions(perms);
						}
					}
				}
				String pythonExec = MainPMScmd.getMyPrefs().get("quotes.pythonPath", "python3");
				ProcessBuilder pb = new ProcessBuilder(pythonExec, python_py.toString());
				Process p = pb.start();
				
				try (BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream())); BufferedWriter out = new BufferedWriter(new FileWriter(marketListFilePath.toFile()))) {
					String line = null;
					while ((line = in.readLine()) != null) {
						out.write(line);
					}
				}
				
				try {
					Files.delete(python_py);
				} catch (IOException e) {
					LOGGER.error("Error deleting StockSymbolApi market list init py: " + e, e);
				}
				
			} catch (IOException e) {
				LOGGER.error(e);
			}
		}
			
		if (Files.exists(marketListFilePath)) {
			try (Reader in = new FileReader(marketListFilePath.toFile());) {
				JsonArray marketJsons = new JsonParser().parse(in).getAsJsonArray();
				for (JsonElement marketElement : marketJsons) {
					JsonObject market = marketElement.getAsJsonObject();
					JsonArray indexJsonArray = market.get("index").getAsJsonArray();
					if (indexJsonArray.size() > 0) {
						for (JsonElement indiceElement : indexJsonArray) {
							JsonObject indice = indiceElement.getAsJsonObject();
							String[] indiceSplit = indice.get("id").getAsString().split(":"); // <= NASDAQ:NDX
							ret.add(indiceSplit[1] + ":" + indiceSplit[0]); // => NDX:NASDAQ
						}
					} else {
						ret.add("ALL:" + market.get("market").getAsString().toUpperCase()); // => ALL:AMERICA
					}
				}
			} catch (Exception e) {
				LOGGER.error(e, e);
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
