package com.finance.pms.events.operations;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.reflections.Reflections;

import com.finance.pms.PostInitMonitor;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.parametrizedindicators.ParameterizedIndicatorsBuilder;
import com.finance.pms.events.operations.conditional.Condition;
import com.finance.pms.events.operations.conditional.EventInfoOpsCompoOperation;
import com.finance.pms.events.operations.nativeops.LeafOperation;
import com.finance.pms.events.operations.nativeops.MapOperation;
import com.finance.pms.events.operations.nativeops.MapValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.operations.parameterized.ParameterizedOperationBuilder;
import com.finance.pms.threads.ConfigThreadLocal;

public class OperationTest {
	
	private static MyLogger LOGGER = MyLogger.getLogger(OperationTest.class);
	private static SpringContext springContext;
	
	
	@BeforeClass
	public static void oneTimeSetup() {
		try {
			springContext = new SpringContext("/home/guil/Developpement/newEclipse/premiumMarkets/pm-forecast/db.properties");
			springContext.loadBeans("/connexions.xml", "/swtclients.xml");
			springContext.refresh();

			ConfigThreadLocal.set("eventSignal", new EventSignalConfig());
			springContext.optionalPostInit();
			PostInitMonitor.waitForOptPostInitEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//isDataShiftSensitive & isForbidThisParameterValue checks
//	@Test
	public void test() {
		Reflections reflections = new Reflections("com.finance.pms.events.operations");
		Set<Class<? extends Operation>> subTypes = reflections.getSubTypesOf(Operation.class);
		List<Class<? extends Operation>> isIgnore = new ArrayList<>();
		List<Class<? extends Operation>> isDataShiftSensitive = new ArrayList<>();
		List<Class<? extends Operation>> isDataShiftSensitiveCoveredByparent = new ArrayList<>();
		List<Class<? extends Operation>> isForbidThisParameterValue = new ArrayList<>();
		List<Class<? extends Operation>> isForbidThisParameterValueCoveredByParent = new ArrayList<>();
		subTypes.stream()
		//.filter(cls -> !Modifier.isAbstract(cls.getModifiers()))
//		.filter(cls -> {
//			Class<?> superclass = cls;
//			while (!(superclass.equals(Operation.class))) {
//				if (superclass.equals(Condition.class) || superclass.equals(FlowOperation.class)) {
//					return false;
//				}
//				superclass = superclass.getSuperclass();
//			}
//			return true;
//		})
		.forEach(cls -> {
			try {
				if (Arrays.stream(cls.getInterfaces()).filter(ifs -> ifs.equals(LeafOperation.class)).count() >= 1) {
					isIgnore.add(cls);
				};
				Class<?> superclass = cls;
				boolean stop = false;
				while (!stop) {
					if (superclass.equals(MapOperation.class)) {
						isDataShiftSensitiveCoveredByparent.add(cls);
						isForbidThisParameterValueCoveredByParent.add(cls);
					}
					if (superclass.equals(VarOperation.class)) {
						isForbidThisParameterValueCoveredByParent.add(cls);
					}
					if (superclass.equals(Condition.class) || superclass.equals(FlowOperation.class)) {
						isIgnore.add(cls);
					}
					superclass = superclass.getSuperclass();
					stop = superclass.equals(Operation.class);
				}
				Method method = cls.getMethod("calculate", TargetStockInfo.class, List.class, int.class, int.class, List.class);
				Class<?> returnedCls = method.getReturnType();
				while (!(returnedCls.equals(Value.class))) {
					if (returnedCls.equals(MapValue.class)) {
						isDataShiftSensitive.add(cls);
					}
					returnedCls = returnedCls.getSuperclass();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		
		System.out.println("isIgnore - do NOT impl (default is false): " + isIgnore.stream().map(c -> c.toString()).reduce((a, e) -> a + "\n" + e));
		System.out.println("isDataShiftSensitive - IMPL true: " + isDataShiftSensitive.stream().map(c -> c.toString()).reduce((a, e) -> a + "\n" + e));
		System.out.println("isDataShiftSensitiveCoveredByparent - do NOT impl (inheriting true): " + isDataShiftSensitiveCoveredByparent.stream().map(c -> c.toString()).reduce((a, e) -> a + "\n" + e));
		System.out.println("isForbidThisParameterValue - IMPL true: " + isForbidThisParameterValue.stream().map(c -> c.toString()).reduce((a, e) -> a + "\n" + e));
		System.out.println("isForbidThisParameterValueCoveredByParent - do NOT impl (inheriting true): " + isForbidThisParameterValueCoveredByParent.stream().map(c -> c.toString()).reduce((a, e) -> a + "\n" + e));

		
		ArrayList<Class<? extends Operation>> dataShiftsLeftOver = new ArrayList<>(subTypes);
		dataShiftsLeftOver.removeAll(isIgnore);
		dataShiftsLeftOver.removeAll(isDataShiftSensitive);
		dataShiftsLeftOver.removeAll(isDataShiftSensitiveCoveredByparent);
		System.out.println("dataShiftsLeftOver: " + dataShiftsLeftOver.stream().map(c -> c.toString()).reduce((a, e) -> a + "\n" + e));
		
		ArrayList<Class<? extends Operation>> forbidThissParameterValueLeftOver = new ArrayList<>(subTypes);
		forbidThissParameterValueLeftOver.removeAll(isIgnore);
		forbidThissParameterValueLeftOver.removeAll(isForbidThisParameterValue);
		forbidThissParameterValueLeftOver.removeAll(isForbidThisParameterValueCoveredByParent);
		System.out.println("forbidThissParameterValueLeftOver: " + forbidThissParameterValueLeftOver.stream().map(c -> c.toString()).reduce((a, e) -> a + "\n" + e));
		
	}
	
	
	@AfterClass
	public static void ontTimeTearDown() {
		springContext.close();
	}
	
	@After
	public void deleteOps() throws IOException {
		ParameterizedIndicatorsBuilder parameterizedIndiactorsBuilder = SpringContext.getSingleton().getBean(ParameterizedIndicatorsBuilder.class);
		parameterizedIndiactorsBuilder.removeFormula("myInd", false);
		
		ParameterizedOperationBuilder parameterizedOperationBuilder = SpringContext.getSingleton().getBean(ParameterizedOperationBuilder.class);
		String[] fomulaes = new String[] {"myOp","throwOperation","doubleMapOperation","falseOperation","trueOperation","falseEnvOperation","trueEnvOperation"};
		Arrays.stream(fomulaes).forEach(f -> {
			try {
				parameterizedOperationBuilder.removeFormula(f, false);
			} catch (IOException e) {
				LOGGER.error(e, e);
			}
		});
	}
	
	private ParameterizedOperationBuilder initOps() throws IOException {
		ParameterizedOperationBuilder parameterizedOperationBuilder = SpringContext.getSingleton().getBean(ParameterizedOperationBuilder.class);
		if (!parameterizedOperationBuilder.isAntlrInitialised()) {
			parameterizedOperationBuilder.updateEditableOperationLists();
		}
		
		//Throw
		String throwOpId = "throwOperation";
		parameterizedOperationBuilder.addFormula(throwOpId, "sma(-1,close)");
		
		//Double map
		String doubleMapOpId = "doubleMapOperation";
		parameterizedOperationBuilder.addFormula(doubleMapOpId, "sma(2,close)");
		
		//False
		String falseOpId = "falseOperation";
		parameterizedOperationBuilder.addFormula(falseOpId, "equals(\"A\",\"B\")");
		
		//True
		String trueOpId = "trueOperation";
		parameterizedOperationBuilder.addFormula(trueOpId, "equals(\"A\",\"A\")");
		
		//True non parameterised
		String trueEnvOpId = "trueEnvOperation";
		parameterizedOperationBuilder.addFormula(trueEnvOpId, "get(\"bla\",\"TRUE\")");
		
		//False non parameterised
		String falseEnvOpId = "falseEnvOperation";
		parameterizedOperationBuilder.addFormula(falseEnvOpId, "get(\"bla\",\"FALSE\")");
		
		return parameterizedOperationBuilder;
	}
	
	@Test
	public void fOrNfAndDircetCallsTest() throws Exception {
		
		ParameterizedOperationBuilder parameterizedOperationBuilder = initOps();
		
		//Test
		String  fOrId = "myOp";
		parameterizedOperationBuilder.addFormula(fOrId, "fOr(fAnd(falseOperation(),throwOperation()),doubleMapOperation())");
		//Operation operation = parameterizedOperationBuilder.getUserCurrentOperations().get(fOrId);
		
		Value<?> results = runOp(parameterizedOperationBuilder, fOrId);
		assertTrue(results instanceof NumericableMapValue);
		// verify(operation.getOperands().get(0), times(1)).run(any(), any(), anyInt()); => Not a mock!!
		
	}
	
	@Test
	public void fOrNfAndDircetCallsTest2() throws Exception {
		
		ParameterizedOperationBuilder parameterizedOperationBuilder = initOps();			
		
		//Test
		String  fOrId = "myOp";
		parameterizedOperationBuilder.addFormula(fOrId, "fOr(fAnd(trueOperation(),throwOperation()),doubleMapOperation())");
		
		Value<?> results = runOp(parameterizedOperationBuilder, fOrId);
		assertTrue(results instanceof NumericableMapValue);
		
	}
	
	@Test
	public void fOrNfAndReferencedCallsTest() throws Exception {
		
		ParameterizedOperationBuilder parameterizedOperationBuilder = initOps();			
		
		//Test
		String  fOrId = "myOp";
		parameterizedOperationBuilder.addFormula(fOrId, "fOr(fAnd($falseOperation$,$throwOperation$),$doubleMapOperation$)");
		
		Value<?> results = runOp(parameterizedOperationBuilder, fOrId);
		assertTrue(results instanceof NumericableMapValue);
		
	}
	
	@Test
	public void fOrNfAndReferencedCallsTest2() throws Exception {
		
		ParameterizedOperationBuilder parameterizedOperationBuilder = initOps();			
		
		//Test
		String  fOrId = "myOp";
		parameterizedOperationBuilder.addFormula(fOrId, "fOr(fAnd($trueOperation$,$throwOperation$),$doubleMapOperation$)");
		
		Value<?> results = runOp(parameterizedOperationBuilder, fOrId);
		assertTrue(results instanceof NumericableMapValue);
		
	}
	
	@Test
	public void fOrNfAndMixedCallsTest() throws Exception {
		
		ParameterizedOperationBuilder parameterizedOperationBuilder = initOps();			
		
		//Test
		String  fOrId = "myOp";
		parameterizedOperationBuilder.addFormula(fOrId, "fOr(fAnd(falseOperation(),$throwOperation$),$doubleMapOperation$)");
		
		Value<?> results = runOp(parameterizedOperationBuilder, fOrId);
		assertTrue(results instanceof NumericableMapValue);
		
	}
	
	@Test
	public void fOrNfAndMixedCallsTest2() throws Exception {
		
		ParameterizedOperationBuilder parameterizedOperationBuilder = initOps();			
		
		//Test
		String  fOrId = "myOp";
		parameterizedOperationBuilder.addFormula(fOrId, "fOr(fAnd(trueOperation(),$throwOperation$),$doubleMapOperation$)");
		
		Value<?> results = runOp(parameterizedOperationBuilder, fOrId);
		assertTrue(results instanceof NumericableMapValue);
		
	}
	
	@Test
	public void fOrNfAndMixedCallsTest3() throws Exception {
		
		ParameterizedOperationBuilder parameterizedOperationBuilder = initOps();			
		
		//Test
		String  fOrId = "myOp";
		parameterizedOperationBuilder.addFormula(fOrId, "fOr(fAnd(falseEnvOperation(),$throwOperation$),$doubleMapOperation$)");
		
		Value<?> results = runOp(parameterizedOperationBuilder, fOrId);
		assertTrue(results instanceof NumericableMapValue);
		
	}
	
	@Test
	public void fOrNfAndMixedCallsTest4() throws Exception {
		
		ParameterizedOperationBuilder parameterizedOperationBuilder = initOps();			
		
		//Test
		String  fOrId = "myOp";
		parameterizedOperationBuilder.addFormula(fOrId, "fOr(fAnd(trueEnvOperation(),$throwOperation$),$doubleMapOperation$)");
		
		Value<?> results = runOp(parameterizedOperationBuilder, fOrId);
		assertTrue(results instanceof NumericableMapValue);
		
	}

	private Value<?> runOp(ParameterizedOperationBuilder parameterizedOperationBuilder, String fOrId) throws IOException, ParseException {
		
		//Indicator
		String indicatorDefinitionStr = "myInd";
		ParameterizedIndicatorsBuilder parameterizedIndiactorsBuilder = SpringContext.getSingleton().getBean(ParameterizedIndicatorsBuilder.class);
		parameterizedIndiactorsBuilder.addFormula(indicatorDefinitionStr, "is bullish when " + fOrId + " is above threshold 0; is bearish when " + fOrId + " is below threshold 0;");
		EventInfoOpsCompoOperation eventInfo = (EventInfoOpsCompoOperation) parameterizedIndiactorsBuilder.getCurrentOperations().get(indicatorDefinitionStr);
		
		//Run and expect
		Stock stock = DataSource.getInstance().loadStockBySymbol("MUR");
		Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2023");
		Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/10/2023");
		TargetStockInfo targetStock = new TargetStockInfo("runInd" + eventInfo.getReference(), eventInfo, stock, startDate, endDate);
		
		String evtiFormulaeDevelopped = eventInfo.toFormulaeDevelopped();
		LOGGER.info("eventInfo formulaeDevelopped: " + evtiFormulaeDevelopped);
		String evtiFormulae = eventInfo.toFormulae(targetStock);
		LOGGER.info("eventInfo formulae: " + evtiFormulae);
		String evtiFormulaeShort = eventInfo.toFormulaeShort(targetStock);
		LOGGER.info("eventInfo formulaeShort: " + evtiFormulaeShort);
		
		Operation fOrOperation = parameterizedOperationBuilder.getUserCurrentOperations().get(fOrId);
		String formulaeDevelopped = fOrOperation.toFormulaeDevelopped();
		LOGGER.info("op formulaeDevelopped: " + formulaeDevelopped);
		String formulae = fOrOperation.toFormulae(targetStock);
		LOGGER.info("op formulae: " + formulae);
		String formulaeShort = fOrOperation.toFormulaeShort(targetStock);
		LOGGER.info("op formulaeShort: " + formulaeShort);
	
		Value<?> results = eventInfo.run(targetStock, new ArrayList<>(), 0);
		return results;
		
	}
	

}
