package com.finance.pms.admin.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.finance.pms.PostInitMonitor;
import com.finance.pms.SpringContext;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.AnalysisClient;
import com.finance.pms.events.operations.nativeops.DoubleArrayMapValue;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.StringListValue;
import com.finance.pms.events.operations.nativeops.data.CsvFileFilterOperation;
import com.finance.pms.threads.ConfigThreadLocal;
import com.google.gson.JsonObject;

public class SystemEnvironmentTest {
	
	private static SpringContext springContext;
	
	@BeforeClass
	public static void oneTimeSetup() {
		springContext = new SpringContext("/home/guil/Developpement/newEclipse/premiumMarkets/pm-forecast/db.properties");
		springContext.loadBeans("/connexions.xml", "/swtclients.xml");
		springContext.refresh();
		
		ConfigThreadLocal.set("eventSignal", new EventSignalConfig());
		springContext.optionalPostInit();
		PostInitMonitor.waitForOptPostInitEnd();
		
	}
	
	@AfterClass
	public static void ontTimeTearDown() {
		AnalysisClient indicatorAnalysis = (AnalysisClient) springContext.getBean("indicatorAnalysis");
		if (indicatorAnalysis != null) indicatorAnalysis.close();
		springContext.close();
	}
	

	//@Test
	public void test20180718() {
		
		String inferModelPath = System.getProperty("installdir") + File.separator + "autoPortfolioLogs/keras_runs.csv";	
		String compositeName = "AutoCalcBckTst18.kerasTrainVolatileBeta20180718";
		String filePath = System.getProperty("installdir") + File.separator + "autoPortfolioLogs/keras_metrics_best_model_20180718.csv";
		
		readWriteEnvJson(compositeName, filePath, inferModelPath);
		
	}
	
	//@Test
	public void testPrgs() {
		
		String inferModelPath = System.getProperty("installdir") + File.separator + "autoPortfolioLogs/keras_runs.csv";	
		String compositeName = "AutoCalc.kerasTrainVolatileBeta";
		String filePath = System.getProperty("installdir") + File.separator + "autoPortfolioLogs/keras_metrics_best_model.csv";
		
		readWriteEnvJson(compositeName, filePath, inferModelPath);
		
	}
	
	//@Test
	public void test3() {
		
		{
		CsvFileFilterOperation anyJavaObject = new CsvFileFilterOperation();
		SystemEnvironment.getInstance().write("EXTR", "anyAnalysis.anyIndicator.CsvFileFilterOperation", anyJavaObject);
		Optional<Object> read = SystemEnvironment.getInstance().read("EXTR", "anyAnalysis.anyIndicator.CsvFileFilterOperation");
		System.out.println("Read anyJavaObject type: " + read.orElseThrow().getClass().getName());
		System.out.println("Read anyJavaObject: " + read.orElseThrow().toString());
		}
		
		{
		NumberValue valueObject = new NumberValue("42");
		SystemEnvironment.getInstance().write("EXTR", "anyAnalysis.anyIndicator.NumberValue", valueObject);
		Optional<Object> read = SystemEnvironment.getInstance().read("EXTR", "anyAnalysis.anyIndicator.NumberValue");
		System.out.println("Read anyJavaObject type: " + read.orElseThrow().getClass().getName());
		System.out.println("Read valueObject: " + read.orElseThrow().toString());
		}
		
		{
		StringListValue valueObject = new StringListValue(Arrays.asList("lala","lolo"));
		SystemEnvironment.getInstance().write("EXTR", "anyAnalysis.anyIndicator.StringListValue", valueObject);
		Optional<Object> read = SystemEnvironment.getInstance().read("EXTR", "anyAnalysis.anyIndicator.StringListValue");
		System.out.println("Read anyJavaObject type: " + read.orElseThrow().getClass().getName());
		System.out.println("Read valueObject: " + read.orElseThrow().toString());
		}
		
		{
		SortedMap<Date, double[]> sortedMap = new TreeMap<Date, double[]>();
		sortedMap.put(DateUtils.addDays(new Date(), -1), new double[] {1,3,2});
		sortedMap.put(new Date(), new double[] {4,6,5});
		DoubleArrayMapValue valueObject = new DoubleArrayMapValue(sortedMap, Arrays.asList("c1","c2","c3"), 0);
		SystemEnvironment.getInstance().write("EXTR", "anyAnalysis.anyIndicator.SortedMap", valueObject);
		Optional<Object> read = SystemEnvironment.getInstance().read("EXTR", "anyAnalysis.anyIndicator.SortedMap");
		System.out.println("Read anyJavaObject type: " + read.orElseThrow().getClass().getName());
		System.out.println("Read valueObject: " + read.orElseThrow().toString());
		}
		
		{
		CsvFileFilterOperation anyJavaObject = new CsvFileFilterOperation();
		SystemEnvironment.getInstance().write("EXTR", "anyAnalysis.anyIndicator.toto", anyJavaObject);
		Optional<Object> read = SystemEnvironment.getInstance().read("EXTR", "anyAnalysis.anyIndicator.toto");
		System.out.println("Read anyJavaObject type: " + read.orElseThrow().getClass().getName());
		System.out.println("Read anyJavaObject: " + read.orElseThrow().toString());
		}
		
		{
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("trainedModel", "xxxxx");
		jsonObject.addProperty("period", 999);
		JsonObject latest = new JsonObject();
		latest.addProperty("trainedModel", "yyyy");
		latest.addProperty("period", 000);
		jsonObject.add("latest", latest);
		SystemEnvironment.getInstance().write("EXTR", "anyAnalysis.anyIndicator.toto", jsonObject);
		Optional<Object> read = SystemEnvironment.getInstance().read("EXTR", "anyAnalysis.anyIndicator.toto");
		System.out.println("Read anyJavaObject type: " + read.orElseThrow().getClass().getName());
		System.out.println("Read anyJavaObject: " + read.orElseThrow().toString());
		}
	}
	
	//@Test
	public void cleanOldNvps() {
		SystemEnvironment sysEnv = SystemEnvironment.getInstance();
		sysEnv.cleanOldNvps();
	}
	
	@Test
	public void cleanSelectedNvps() {
		
		String envJsonPath = System.getProperty("installdir") + File.separator + "env.json";
		SystemEnvironment sysEnv = new SystemEnvironment(envJsonPath);
		
		Stock stock = DataSource.getInstance().getShareDAO().loadStockBy("BTC-USD", "BTC-USD");
		List<String> excluded = List.of(
				"e4caca77-3af9-427e-a3d7-0e651af2d2f2",
				"1fbea643-447b-4db0-88b8-32991a0fe799",
				"d48519cc-46ac-4515-ae71-6c31f3bf0f97",
				"21783d89-84e5-4054-a9a3-8551bb320065",
				"f196b946-a9e1-4371-aef0-f40604ef066d",
				"caa65457-c4b7-4cd0-b69f-c65c6ca1150f",
				"c86e3f4c-f0ed-4dd6-9b9e-a4cef5fd087e",
				"4c45b53a-9017-4f74-9998-c6e158c975f6",
				"5b65276b-cf6e-4039-9316-d237ce6db040",
				"6c7f411b-3ea0-4610-b65f-f373e57e896e",
				"a378446a-f8d2-4d22-8ebf-8e78bdb7bb8b",
				"8d2e58ea-0831-4e68-a0a9-065f718f3d8e",
				"bf994918-8717-4471-8a4e-bcd512f8690d",
				"07af38a9-7d26-4a1a-9f10-b252d30d7f78",
				"14a2db41-582c-4e1e-b5e4-e294855b74e5",
				"91bb5024-72c3-4461-9a7d-93aa7ef0a058",
				"21e284aa-0d61-4ac6-8145-3a184e5d3554",
				"dad7c695-46f3-4dd2-a0a1-8f3edabcf7f6",
				"3bc02bcf-c884-4149-baa4-973761738df5",
				"7915b049-3f04-4387-a03a-f1ede072e4c7",
				"fa530ada-8a21-46fb-acf1-3fded658fc09",
				"99ca33aa-ba0a-4f6b-aa07-1363c7a0322a");
		String analysisOperationPeriodDottedPath = "trainLoop.finderTrain_2dCorWClHTCnnTaLin2DenseAsym.15";
		sysEnv.cleanOldNvpsInPath(stock, analysisOperationPeriodDottedPath, excluded);
		
	}

	private void readWriteEnvJson(String compositeName, String filePath, String inferModelPath) {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

			String line;
			int prevLength = 0;

			while ((line = bufferedReader.readLine()) != null) {
				if (line.isEmpty() || line.startsWith("#")) continue;
				String[] rowSplit = line.split(",");
				
				// file check
				if (prevLength > 0 && prevLength != rowSplit.length)
					throw new RuntimeException("Invalid file");
				
				//Compute
				try {
					prevLength = rowSplit.length;
					String rowStock = rowSplit[0];
					String rowModelId = rowSplit[1];
	
					String kerasRunsLine = readKerasRunsLine(inferModelPath, rowModelId).get(0);
					String[] split = kerasRunsLine.split(",");
					String metricsOpsPeriods = split[split.length -3];
					String period = metricsOpsPeriods.trim().substring(2, metricsOpsPeriods.length());
					String name = compositeName + "." + period + ".trainedModel";
					
					System.out.println(kerasRunsLine);
					SystemEnvironment.getInstance().write(rowStock, name, rowModelId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}

		} catch (Exception e) {
			throw new RuntimeException("File: " + filePath, e);
		}
	}

	private List<String> readKerasRunsLine(String inferModelPath, String rowModelId) {
		List<String> kerasRunsLine = new ArrayList<>();
		try (BufferedReader runsReader = new BufferedReader(new FileReader(inferModelPath))) {

			String runsline;
			while ((runsline = runsReader.readLine()) != null) {
				try {
					boolean anyMatchContains = runsline.startsWith(rowModelId);
					if (anyMatchContains) {
						kerasRunsLine.add(runsline);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		if (kerasRunsLine.size() != 1) throw new RuntimeException("Could not find " + rowModelId  + " in " + kerasRunsLine + " from " + inferModelPath);
		return kerasRunsLine;
	}

}
