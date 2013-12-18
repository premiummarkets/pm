package com.finance.pms.datasources;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ComparableArray<T extends Comparable<T>> extends ArrayList<T> implements Comparable<ArrayList<T>> {

	private static final long serialVersionUID = -363699288012620328L;

	public ComparableArray(Collection<T> values) {
		super(values);
	}

	public ComparableArray() {
		super();
	}

	@Override
	//XXX
	public int compareTo(ArrayList<T> o) {
		//return this.hashCode() - o.hashCode();
		int size = new Integer(this.size()).compareTo(new Integer(o.size()));
		if (size != 0) {
			return size;
		} else {
			boolean equals = this.equals(o);
			if (equals) {
				return 0;
			} else {
				ArrayList<T> left = new ArrayList<T>(this);
				ArrayList<T> right = new ArrayList<T>(o);
				Collections.sort(left);
				Collections.sort(right);
				for (int i = 0; i < left.size(); i++) {
					int compareTo = left.get(i).compareTo(right.get(i));
					if (compareTo != 0) return compareTo;
				}
				throw new InvalidParameterException();
			}
		}
	}
}