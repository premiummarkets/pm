package com.finance.pms.events.operations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.reflections.Reflections;

import com.finance.pms.events.operations.conditional.Condition;
import com.finance.pms.events.operations.nativeops.LeafOperation;
import com.finance.pms.events.operations.nativeops.MapOperation;
import com.finance.pms.events.operations.nativeops.MapValue;
import com.finance.pms.events.operations.nativeops.Value;

public class OperationTest {

	@Test
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

}
