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
	
	@Test
	public void cleanOldNvps() {
		SystemEnvironment sysEnv = SystemEnvironment.getInstance();
		sysEnv.cleanOldNvps();
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
