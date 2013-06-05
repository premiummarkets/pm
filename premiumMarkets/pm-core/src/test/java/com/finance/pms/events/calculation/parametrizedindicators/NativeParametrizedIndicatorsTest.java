package com.finance.pms.events.calculation.parametrizedindicators;

import org.junit.Test;

import com.finance.pms.events.operations.Operation;

public class NativeParametrizedIndicatorsTest {

	@Test
	public void test() {
		NativeParametrizedIndicators.initNativeIndicators();

		NativeParametrizedIndicators nativeInds = NativeParametrizedIndicators.loadNativeIndicators();

		for (Operation operation : nativeInds.getCalculators().values()) {
			System.out.println(operation.toString());
		}
	}
}
