package com.finance.pms.admin.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class SystemEnvironment {
	
	private static MyLogger LOGGER = MyLogger.getLogger(SystemEnvironment.class);
	
	private static final String JSON_ENV_PATH = System.getProperty("installdir") + File.separator + "env.json";

	private static SystemEnvironment environmentMgt;
	
	public static SystemEnvironment getInstance() {
		if (environmentMgt == null) {
			synchronized (SystemEnvironment.class) {
				if (environmentMgt == null) {
					environmentMgt = new SystemEnvironment();
				}
			}
		}
		return environmentMgt;
	}

	private Env env;

	public SystemEnvironment() {
			initCalcEnv();
	}
	
	public Optional<Object> write(String stockSymbol, String compositeName, Object value) {
		List<Stock> foundStockForSymbol = DataSource.getInstance().getShareDAO().loadStockByIsinOrSymbol(stockSymbol);
		if (foundStockForSymbol.size() != 1) throw new RuntimeException("Could not load symbol: " + stockSymbol);
		Stock stock = foundStockForSymbol.get(0);
		return write(stock, compositeName, value);
		
	}
	
	public Optional<Object> write(Stock stock, String compositeName, Object value) {
		synchronized (this) {
			initCalcEnv();
			Optional<Object> oldValue = env.putNvp(stock, compositeName, value);
			oldValue.ifPresent(ov -> {
				env.backupNvp(stock, compositeName, ov);
			});
			storeCalcEnv(env);
		}
		return read(stock, compositeName);
	}
	
	public Optional<Object> remove(Stock stock, String compositeName) {
		synchronized (this) {
			initCalcEnv();
			Optional<Object> oldValue = env.rmNvp(stock, compositeName);
			oldValue.ifPresent(ov -> {
				env.backupNvp(stock, compositeName, ov);
			});
			storeCalcEnv(env);
			return oldValue;
		}
	}
	
	public Optional<Object> read(String stockSymbol, String compositeName) {
		List<Stock> foundStockForSymbol = DataSource.getInstance().getShareDAO().loadStockByIsinOrSymbol(stockSymbol);
		if (foundStockForSymbol.size() != 1) throw new RuntimeException("Could not load symbol: " + stockSymbol);
		Stock stock = foundStockForSymbol.get(0);
		return read(stock, compositeName);
	}
	
	public Optional<Object> read(Stock stock, String compositeName) {
		return env.getNvp(stock, compositeName);
	}
	
	public Optional<Object> readOldNvps(Stock stock, String compositeName) {
		return env.getOldNvp(stock, compositeName);
	}
	
	private void initCalcEnv() {
		
		File envFile = new File(JSON_ENV_PATH);
		if (!envFile.exists()) {
			storeCalcEnv(new Env());
		}
		
		this.env = null;
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(envFile))) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			env = gson.fromJson(bufferedReader, new TypeToken<Env>() {}.getType());
		} catch (Exception e) {
			LOGGER.error("Inconsistencies: " + env + " in " + JSON_ENV_PATH, e);
			throw new RuntimeException(e);
		} finally {
			if (env == null) {
				LOGGER.warn("File is empty or inconsistent: " + JSON_ENV_PATH);
				String copyPath = JSON_ENV_PATH + "_" + new Date().getTime();
				try {
					Files.copy(envFile, new File(copyPath));
				} catch (IOException e) {
					LOGGER.warn("Could not backup file " + JSON_ENV_PATH + " to " + copyPath + ": " + e);
				}
				env = new Env();
			}
		}
	}
	
	private void storeCalcEnv(Env env) {
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(JSON_ENV_PATH))) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(env, bufferedWriter);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private class StockEnv {
		private Map<String, Object> stockEnvNvps;
		private Map<String, Object> oldNvps;
		private StockEnv() {
			this.stockEnvNvps = new HashMap<>();
			this.oldNvps = new HashMap<>();
		}
		public Map<String, Object> getOldNvps() {
			return oldNvps;
		}
		private Map<String, Object> getNvps() {
			return stockEnvNvps;
		}
	}
	
	private class Env {
		
		private Map<String, StockEnv> envForStocks;
		
		protected Env() {
			envForStocks = new HashMap<>();
		}

		private Map<String, StockEnv> getEnvForStocks() {
			return envForStocks;
		}
		
		private Map<String, Object> getNameValuePairsFor(Stock stock) {
			StockEnv stockEnv = envForStocks.get(stock.getSymbol());
			return Optional.ofNullable(stockEnv).orElseGet(() -> {
				this.getEnvForStocks().put(stock.getSymbol(), new StockEnv()); 
				return this.getEnvForStocks().get(stock.getSymbol());
			}).getNvps();
		}
		
		@SuppressWarnings("rawtypes")
		private Optional<Object> getNvp(Stock stock, String compositeName) {
			Map<String, Object> nameValuePairsForStock = getNameValuePairsFor(stock);
			String[] nameSplit = compositeName.split("\\.");
			Object subMap = nameValuePairsForStock;
			for (String namePart : nameSplit) {
				if (subMap instanceof Map) {
					subMap = ((Map) subMap).get(namePart);
				}
			}
			Optional<Object> ofNullable = Optional.ofNullable(subMap);
			return ofNullable;
		}
		
		private Map<String, Object> getOldNameValuePairsFor(Stock stock) {
			StockEnv stockEnv = envForStocks.get(stock.getSymbol());
			return Optional.ofNullable(stockEnv).orElseGet(() -> {
				this.getEnvForStocks().put(stock.getSymbol(), new StockEnv()); 
				return this.getEnvForStocks().get(stock.getSymbol());
			}).getOldNvps();
		}
		
		@SuppressWarnings("rawtypes")
		private Optional<Object> getOldNvp(Stock stock, String compositeName) {
			Map<String, Object> nameValuePairsForStock = getOldNameValuePairsFor(stock);
			String[] nameSplit = compositeName.split("\\.");
			Object subMap = nameValuePairsForStock;
			for (String namePart : nameSplit) {
				if (subMap instanceof Map) {
					subMap = ((Map) subMap).get(namePart);
				}
			}
			Optional<Object> ofNullable = Optional.ofNullable(subMap);
			return ofNullable;
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private Optional<Object> putNvp(Stock stock, String compositeName, Object value) {
			Map<String, Object> nameValuePairsForStock = getNameValuePairsFor(stock);
			String[] nameSplit = compositeName.split("\\.");
			List<String> namePath = Arrays.asList(nameSplit).subList(0, nameSplit.length -1);
			Object subMap = nameValuePairsForStock;
			for (String namePart : namePath) {
				if (subMap instanceof Map) {
					if (!((Map) subMap).containsKey(namePart)) {
						((Map) subMap).put(namePart, new HashMap<String, Object>());
					}
					subMap = ((Map) subMap).get(namePart);
				}
			}
			return Optional.ofNullable(((Map) subMap).put(nameSplit[nameSplit.length-1], value));
		}
		
		@SuppressWarnings({"rawtypes" })
		private Optional<Object> rmNvp(Stock stock, String compositeName) {
			Map<String, Object> nameValuePairsForStock = getNameValuePairsFor(stock);
			String[] nameSplit = compositeName.split("\\.");
			List<String> namePath = Arrays.asList(nameSplit).subList(0, nameSplit.length -1);
			Object subMap = nameValuePairsForStock;
			for (String namePart : namePath) {
				if (subMap instanceof Map) {
					if (!((Map) subMap).containsKey(namePart)) {
						return Optional.empty();
					}
					subMap = ((Map) subMap).get(namePart);
				}
			}
			return Optional.ofNullable(((Map) subMap).remove(nameSplit[nameSplit.length-1]));
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public void backupNvp(Stock stock, String compositeName, Object ov) {
			Map<String, Object> oldNvpsForStock = getOldNameValuePairsFor(stock);
			String[] nameSplit = compositeName.split("\\.");
			List<String> namePath = Arrays.asList(nameSplit).subList(0, nameSplit.length -1);
			Object subMap = oldNvpsForStock;
			for (String namePart : namePath) {
				if (subMap instanceof Map) {
					if (!((Map) subMap).containsKey(namePart)) {
						((Map) subMap).put(namePart, new HashMap<String, Object>());
					}
					subMap = ((Map) subMap).get(namePart);
				}
			}
			Object fSubMap = subMap;
			String stemName = nameSplit[nameSplit.length-1];
			((Map)Optional.ofNullable(((Map) subMap).get(stemName)).orElseGet(() -> {
				((Map) fSubMap).put(stemName, new HashMap<String, Object>()); 
				return ((Map) fSubMap).get(stemName);
			})).put(Long.valueOf(new Date().getTime()).toString(), ov);
		}
	}

}
