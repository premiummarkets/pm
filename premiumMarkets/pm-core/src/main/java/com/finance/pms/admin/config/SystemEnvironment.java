package com.finance.pms.admin.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

public class SystemEnvironment {
	
	private static MyLogger LOGGER = MyLogger.getLogger(SystemEnvironment.class);
	
	private static String JSON_ENV_PATH = System.getProperty("installdir") + File.separator + "env.json";

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
	
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

	private Env env;
	
	private Env getEnvReadMode() {
		readLock.lock();
		return env;
	}
	
	private Env getEnvWriteMode() {
		writeLock.lock();
		initCalcEnv();
		return env;
	}
	
	public Collection<String> getAllStocks() {
		try {
			return getEnvReadMode().getEnvForStocks().keySet();
		} finally {
			readLock.unlock();
		}
	}
	
	public Collection<String> getAnalysisFor(String stockSymbol) {
		try {
			return getEnvReadMode().getEnvForStocks().get(stockSymbol).getNvps().keySet();
		} finally {
			readLock.unlock();
		}
	}
	
	public Collection<String> getOldAnalysisFor(String stockSymbol) {
		try {
			return getEnvReadMode().getEnvForStocks().get(stockSymbol).getOldNvps().keySet();
		} finally {
			readLock.unlock();
		}
	}

	public SystemEnvironment() {
			try {
				getEnvWriteMode();
			} finally {
				writeLock.unlock();
			}
	}
	
	public SystemEnvironment(String envJsonPath) {
		JSON_ENV_PATH = envJsonPath;
		try {
			getEnvWriteMode();
		} finally {
			writeLock.unlock();
		}
	}
	
	public Optional<Object> write(String stockSymbol, String compositeName, Object value) {
		List<Stock> foundStockForSymbol = DataSource.getInstance().getShareDAO().loadStockByIsinOrSymbol(stockSymbol);
		if (foundStockForSymbol.size() != 1) throw new RuntimeException("Could not load symbol: " + stockSymbol);
		Stock stock = foundStockForSymbol.get(0);
		return write(stock, compositeName, value);
		
	}
	
	public Optional<Object> write(Stock stock, String compositeName, Object value) {
		try {
			Env envWriteMode = getEnvWriteMode();
			Optional<Object> oldValue = envWriteMode.putNvp(stock, compositeName, value);
			oldValue.ifPresent(ov -> {
				envWriteMode.backupNvp(stock, compositeName, ov);
			});
			storeCalcEnv(envWriteMode);
		} finally {
			writeLock.unlock();
		}
		return read(stock, compositeName);
	}
	
	public Optional<Object> remove(Stock stock, String compositeName) {
		try {
			Env envWriteMode = getEnvWriteMode();
			Optional<Object> oldValue = envWriteMode.rmNvp(stock, compositeName);
			oldValue.ifPresent(ov -> {
				envWriteMode.backupNvp(stock, compositeName, ov);
			});
			storeCalcEnv(envWriteMode);
			return oldValue;
		} finally {
			writeLock.unlock();
		}
	}
	
	public Optional<Object> read(String stockSymbol, String compositeName) {
		List<Stock> foundStockForSymbol = DataSource.getInstance().getShareDAO().loadStockByIsinOrSymbol(stockSymbol);
		if (foundStockForSymbol.size() != 1) throw new RuntimeException("Could not load symbol: " + stockSymbol);
		Stock stock = foundStockForSymbol.get(0);
		return read(stock, compositeName);
	}
	
	public Optional<Object> read(Stock stock, String compositeName) {
		try {
			return getEnvReadMode().getNvp(stock, compositeName);
		} finally {
			readLock.unlock();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Optional<String> findPath(Stock stock, String rootCompositeName, String keyName, String valueTofind) {
		try {
			Optional<Object> nvp = getEnvReadMode().getNvp(stock, rootCompositeName);
			Object envObject = nvp.orElse("");
			
			Map<String, Object> envMap;
			if (envObject instanceof JsonObject) {
				envMap = new Gson().fromJson(envObject.toString(), HashMap.class);
			} else
			if (envObject instanceof Map) {
				envMap = ((Map<String, Object>) envObject);
			} else {
				return Optional.empty();
			}
			
			for (Object keyObject: envMap.keySet()) {
				String subroot = (String) keyObject;
				if (keyName.equals(subroot) && envMap.get(keyName).equals(valueTofind)) {
					return Optional.of(rootCompositeName + "." + subroot);
				} else {
					Optional<String> foundComposite = findPath(stock, rootCompositeName + "." + subroot, keyName, valueTofind);
					if (foundComposite.isPresent()) {
						return foundComposite;
					}
				}
			};

			return Optional.empty();
		} finally {
			readLock.unlock();
		}
	}
	
	/**
	 * The returned value should be read only
	 * @param stock
	 * @param rootCompositeName
	 * @param keyName
	 * @param valueTofind
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Optional<String> findPathOldNvpReadOnly(Stock stock, String rootCompositeName, String keyName, String valueTofind) {
		try {
			Optional<Object> nvp = getEnvReadMode().getOldNvp(stock, rootCompositeName);
			Object envObject = nvp.orElse("");
			
			Map<String, Object> envMap;
			if (envObject instanceof JsonObject) {
				envMap = new Gson().fromJson(envObject.toString(), HashMap.class);
			} else
			if (envObject instanceof Map) {
				envMap = ((Map<String, Object>) envObject);
			} else {
				return Optional.empty();
			}
			
			for (Object keyObject: envMap.keySet()) {
				String subroot = (String) keyObject;
				if (keyName.equals(subroot) && envMap.get(keyName).equals(valueTofind)) {
					return Optional.of(rootCompositeName + "." + subroot);
				} else {
					Optional<String> foundComposite = findPathOldNvpReadOnly(stock, rootCompositeName + "." + subroot, keyName, valueTofind);
					if (foundComposite.isPresent()) {
						return foundComposite;
					}
				}
			};

			return Optional.empty();
		} finally {
			readLock.unlock();
		}
	}
	
	public Map<String, Object> findEnvObjectAnyWhereThatContainingKeyValue(final String keyName, String keyValue) {
		Map<String, Object> identity = new LinkedHashMap<String, Object>();
		Map<String, Object> refMidEnvDetails = this.getAllStocks().stream()
			.reduce(
				identity, 
				(ac, s) -> {
					Stock stock = DataSource.getInstance().loadStockBySymbol(s);
					Optional<String> trainedModelPathOpt = findPathAnyAnalysis(stock, keyName, keyValue);
					
					if (trainedModelPathOpt.isPresent()) {
						String trainedModelPath = trainedModelPathOpt.get();
						trainedModelPath = trainedModelPath.substring(0, trainedModelPath.lastIndexOf("." + keyName));
						
						Optional<Object> modelInfoJsonOpt = this.read(stock, trainedModelPath);
						if (modelInfoJsonOpt.isEmpty()) {
							modelInfoJsonOpt = this.readOldNvps(stock, trainedModelPath);
						}
						Object orElse = modelInfoJsonOpt.orElse(new LinkedHashMap<>());
						if (orElse instanceof JsonObject) {
							orElse = new Gson().fromJson((JsonObject)orElse, Map.class);
						}
						@SuppressWarnings("unchecked")
						Map<String, Object> modelInfo = (Map<String, Object>) orElse;
						ac.putAll(modelInfo);
					}
					
					return ac;
					
				},
				(a, b) -> {
					a.putAll(b);
					return a;
				}
			);
		return refMidEnvDetails;
	}

	public Optional<String> findPathAnyAnalysis(Stock stock, final String keyName, String keyValue) {
		Collection<String> analysis = this.getAnalysisFor(stock.getSymbol());
		Optional<String> trainedModelPathOpt = analysis.stream()
				.reduce(Optional.empty(), (ac1, an) -> { //search latest nvps
					Optional<String> model = this.findPath(stock, an, keyName, keyValue);
					return ac1.isPresent()?ac1:model;
				}, (a, b) -> (a.isPresent())?a:b);
		if (trainedModelPathOpt.isEmpty()) { 
			trainedModelPathOpt = analysis.stream() 
					.reduce(Optional.empty(), (ac1, an) -> { //search old nvps for existing analysis
						Optional<String> model = this.findPathOldNvpReadOnly(stock, an, keyName, keyValue);
						return ac1.isPresent()?ac1:model;
					}, (a, b) -> (a.isPresent())?a:b);
		}
		if (trainedModelPathOpt.isEmpty()) {  //search old nvps for obsolete analysis
			trainedModelPathOpt = this.getOldAnalysisFor(stock.getSymbol()).stream()
					.reduce(Optional.empty(), (ac1, an) -> {
						Optional<String> model = this.findPathOldNvpReadOnly(stock, an, keyName, keyValue);
						return ac1.isPresent()?ac1:model;
					}, (a, b) -> (a.isPresent())?a:b);
		}
		return trainedModelPathOpt;
	}
	
	public Optional<Object> readOldNvps(Stock stock, String compositeName) {
		try {
			return getEnvReadMode().getOldNvp(stock, compositeName);
		} finally {
			readLock.unlock();
		}
	}
	
	private void initCalcEnv() {
		
		File envFile = new File(JSON_ENV_PATH);
		if (!envFile.exists()) {
			storeCalcEnv(new Env());
		}
		
		Env env = null;
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
		
		this.env = env;
	}
	

	public void cleanOldNvps() {
		try {
			Env envWriteMode = this.getEnvWriteMode();
			envWriteMode.envForStocks.forEach((k,v) -> {
				v.oldNvps.clear();
			});
			storeCalcEnv(envWriteMode);
		} finally {
			writeLock.unlock();
		}
	}
	
	public void cleanOldNvpsInPath(Stock stock, String analysisOperationPeriodDottedPath, List<String> excludingIds) {
		try {
			Env envWriteMode = getEnvWriteMode();
			Optional<Object> nvp = envWriteMode.getOldNvp(stock, analysisOperationPeriodDottedPath);
			Object envObject = nvp.orElse(new JsonArray());
			@SuppressWarnings("unchecked")
			LinkedTreeMap<String, LinkedTreeMap<String, Object>> timeStampArray = ((LinkedTreeMap<String, LinkedTreeMap<String, Object>>) envObject);
			List<String> toBeRemoved = new ArrayList<>();
			for (String timeStamp : timeStampArray.keySet()) {
				Object modelId = timeStampArray.get(timeStamp).get("trainedModel");
				if (modelId != null && !excludingIds.contains(modelId)) {
					LOGGER.info("Remove: " + timeStamp + " and " + modelId + " from " + analysisOperationPeriodDottedPath);
					toBeRemoved.add(timeStamp);
				}
			}
			toBeRemoved.forEach((k) -> {
				timeStampArray.remove(k);
			});
			LOGGER.info("Removing.");
			storeCalcEnv(envWriteMode);
		} finally {
			writeLock.unlock();
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
				try {
					if (subMap instanceof JsonObject) {
						subMap = new Gson().fromJson(subMap.toString(), HashMap.class);
					}
					if (subMap instanceof Map) {
						subMap = ((Map) subMap).get(namePart);
					}
				} catch (JsonSyntaxException e) {
					LOGGER.warn("Json Object is not a map: " + subMap + ": " + e);
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
