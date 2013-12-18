package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.NotImplementedException;

public class SmaTransformator<T> {
	
	private Class<T> genType;

	private int destPeriod;
	private int originPeriod;	

	public SmaTransformator(Class<T> genType, int originPeriod, int destPeriod) {
		super();
		this.genType = genType;
		
		this.destPeriod = destPeriod;
		this.originPeriod = originPeriod;
		
		if (destPeriod < originPeriod || destPeriod%originPeriod != 0) {
			throw new NotImplementedException("We need : destPeriod >= originPeriod && destPeriod%originPeriod == 0 ");
		}
			
	}

	public SortedMap<Date, T> transform(SortedMap<Date, T>  smaOrigin) {

		List<T> originValues = new ArrayList<T>();
		originValues.addAll(smaOrigin.values());

		List<T> destValues = new ArrayList<T>();
		for (int i = destPeriod-1; i < originValues.size(); i++) {

			Double destValueAti = 0d;
			for (int j = i-destPeriod + originPeriod -1; j < i; j = j + originPeriod) {
				destValueAti = destValueAti + valueOf(originValues.get(j));
			}

			destValues.add(tOf(destValueAti/(destPeriod/originPeriod)));
		}

		SortedMap<Date, T> destSma = new TreeMap<Date, T>();
		int cpt = 0;
		for (Date date : smaOrigin.keySet()) {
			if (cpt >= destPeriod) {
				destSma.put(date, destValues.get(cpt-destPeriod));
			}
			cpt++;
		}
		
		return destSma;

	}



	@SuppressWarnings("unchecked")
	private T tOf(Double destValueAti) {
		
		if (genType.isAssignableFrom(Double.class)) {
			return (T) destValueAti;
		} else if (genType.isArray() && genType.getComponentType().equals(Double.TYPE)) {
			return (T) new double[]{destValueAti};
		} else throw new NotImplementedException();
		
 	}



	private Double valueOf(T t) {
		
		if (t instanceof Double) {
			return (Double) t;
		} else if (t.getClass().isArray() &&t.getClass().getComponentType().equals(Double.TYPE)){
			return ((double[]) t)[0];
		} else throw new NotImplementedException();
		
	}


}
