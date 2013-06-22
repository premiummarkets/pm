package com.finance.pms.events.scoring.functions.polysolve;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

public class MyMatrixFunction {

	public SortedMap<Date,double[]> polynomialReg(SortedMap<Date,double[]> data) {


		ArrayList<Pair> pairs = new ArrayList<Pair>();
		int j = 0;
		for (double[] value : data.values()) {
			pairs.add(new Pair(j,value[0]));
			j++;

		}

		ArrayList<Double> terms = MatrixFunctions.compute_coefficients(pairs, 2);
		
		SortedMap<Date,double[]> polReg = new TreeMap<Date, double[]>();
		Iterator<Date> dateIterator = data.keySet().iterator();
		for (Pair pair : pairs) {
			double regress = MatrixFunctions.regress(pair.x, terms);
			polReg.put(dateIterator.next(),new double[]{regress});
		}
		
		
		return polReg;
		
	}


}




