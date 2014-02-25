/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.quotations;

import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.SortedSet;

/**
 * The Class QuotationData.
 * 
 * @author Guillaume Thoreton
 */
class QuotationData implements List<QuotationUnit> {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = -674715272697353811L;

	private StripedQuotations stripedQuotations;

	protected QuotationData(StripedQuotations striped) {
		this.stripedQuotations = striped;
	}

	protected QuotationData(SortedSet<QuotationUnit> quotationUnits) {
		init(quotationUnits);
	}
	
	public QuotationData(List<QuotationUnit> sortedQuotationUnits) {
		init(sortedQuotationUnits);
	}

	protected void init(Collection<QuotationUnit> sortedQuotationUnits) {
		this.stripedQuotations = new StripedQuotations(sortedQuotationUnits.size());
		this.addAll(sortedQuotationUnits);
		int i = 0 ;
		for (QuotationUnit quotationUnit : sortedQuotationUnits) {
			this.stripedQuotations.addStripedValues(i, quotationUnit.getClose(), quotationUnit.getHigh(), quotationUnit.getLow());
			i++;
		}
	}

	public boolean remove(QuotationUnit o) {
		return this.stripedQuotations.getBarList().remove(o);
	}

	public QuotationUnit get(int index) {
		return this.stripedQuotations.getBarList().get(index);
	}

	public int size() {
		return this.stripedQuotations.getBarList().size();
	}

	public Date getDate(int x) {
		return get(x).getDate();
	}
	
	public BigDecimal getClosestCloseBeforeOrAtDate(Date date) throws InvalidAlgorithmParameterException {
		Integer index = getClosestIndexBeforeOrAtDate(0, date);
		if (index == -1) throw new InvalidAlgorithmParameterException();
		return get(index).getClose();
	}
	
	public Number getClosestFieldBeforeOrAtDate(Date date, QuotationDataType field) throws InvalidAlgorithmParameterException {
		Integer index = getClosestIndexBeforeOrAtDate(0, date);
		if (index == -1) throw new InvalidAlgorithmParameterException();
		return get(index).getData(field);
	}

	public Integer getClosestIndexBeforeOrAtDate(Integer startSearchIndex, Date date) {
		if (date == null || this.size() == 0) return -1;
		return this.getClosestBeforeOrAtByDicho(date, startSearchIndex, this.size() - 1);
	}

	private Integer getClosestBeforeOrAtByDicho(Date date, Integer start, Integer end) {
		Integer midle = (end - start) / 2 + start;
		Date dMidle = this.get(midle).getDate();
		//Stop conditions
		if (date.compareTo(dMidle) == 0)
			return midle;
		if (midle.equals(start)) {
			if (date.before(this.get(start).getDate())) {
				//return start;
				//return Math.min(0, start-1); //FIXME<= should be Math.max(0, start-1);
				return start-1;
			} else {
				//return end;
				if (date.before(this.get(end).getDate())) {
					return midle;
				} else {
					return end;
				}
			}
		}
		//Continue
		if (date.before(dMidle)) {
			return this.getClosestBeforeOrAtByDicho(date, start, midle);
		}
		if (date.after(dMidle)) {
			return this.getClosestBeforeOrAtByDicho(date, midle, end);
		}
		return -1;
	}
	

	public double[] getCloseValues() {
		if (this.stripedQuotations.isStriped()) 
			return this.stripedQuotations.getCloseTrimedList();
		else 
			return this.getInput(QuotationDataType.CLOSE);
	}
	
	public double[] getLowValues() {
		if (this.stripedQuotations.isStriped()) 
			return this.stripedQuotations.getLowTrimedList();
		else 
			return this.getInput(QuotationDataType.LOW);
	}
	
	public double[] getHighValues() {
		if (this.stripedQuotations.isStriped()) 
			return this.stripedQuotations.getHighTrimedList();
		else 
			return this.getInput(QuotationDataType.HIGH);
	}
	
	public double[] getVolumeValues() {
		return this.getInput(QuotationDataType.VOLUME);
	}

	private double[] getInput(QuotationDataType field) {
		double[] values = new double[this.size()];
		switch (field) {
		case OPEN:
			for (int i = 0; i < this.size(); i++)
				values[i] = this.get(i).getOpen().doubleValue();
			break;
		case HIGH:
			for (int i = 0; i < this.size(); i++)
				values[i] = this.get(i).getHigh().doubleValue();
			break;
		case LOW:
			for (int i = 0; i < this.size(); i++)
				values[i] = this.get(i).getLow().doubleValue();
			break;
		case CLOSE :
			for (int i = 0; i < this.size(); i++) 
				values[i] = this.get(i).getClose().doubleValue();
			break;
		case VOLUME :
			for (int i = 0; i < this.size(); i++) 
				values[i] = this.get(i).getVolume();
			break;
		default :
			throw new RuntimeException("Unknown quotation data type");
		}
		return values;
	}
	
	public void add(int index, QuotationUnit element) {
		this.stripedQuotations.getBarList().add(index,element);
	}

	
	public boolean add(QuotationUnit e) {
		return this.stripedQuotations.getBarList().add(e);
	}

	
	public boolean addAll(Collection<? extends QuotationUnit> c) {
		return this.stripedQuotations.getBarList().addAll(c);
	}

	
	public boolean addAll(int index, Collection<? extends QuotationUnit> c) {
		return this.stripedQuotations.getBarList().addAll(index,c);
	}

	
	public void clear() {
		this.stripedQuotations.getBarList().clear();
	}

	
	public boolean contains(Object o) {
		return this.stripedQuotations.getBarList().contains(o);
	}

	
	public boolean containsAll(Collection<?> c) {
		return this.stripedQuotations.getBarList().containsAll(c);
	}

	
	public int indexOf(Object o) {
		return this.stripedQuotations.getBarList().indexOf(o);
	}

	
	public boolean isEmpty() {
		return this.stripedQuotations.getBarList().isEmpty();
	}

	
	public Iterator<QuotationUnit> iterator() {
		return this.stripedQuotations.getBarList().iterator();
	}

	
	public int lastIndexOf(Object o) {
		return this.stripedQuotations.getBarList().lastIndexOf(o);
	}

	
	public ListIterator<QuotationUnit> listIterator() {
		return this.stripedQuotations.getBarList().listIterator();
	}

	
	public ListIterator<QuotationUnit> listIterator(int index) {
		return this.stripedQuotations.getBarList().listIterator(index);
	}

	
	public QuotationUnit remove(int index) {
		return this.stripedQuotations.getBarList().remove(index);
	}

	
	public boolean remove(Object o) {
		return this.stripedQuotations.getBarList().remove(o);
	}

	
	public boolean removeAll(Collection<?> c) {
		return this.stripedQuotations.getBarList().removeAll(c);
	}

	
	public boolean retainAll(Collection<?> c) {
		return this.stripedQuotations.getBarList().retainAll(c);
	}

	
	public QuotationUnit set(int index, QuotationUnit element) {
		return this.stripedQuotations.getBarList().set(index, element);
	}

	
	public List<QuotationUnit> subList(int fromIndex, int toIndex) {
		return this.stripedQuotations.getBarList().subList(fromIndex, toIndex);
	}

	
	public Object[] toArray() {
		return this.stripedQuotations.getBarList().toArray();
	}

	
	public <T> T[] toArray(T[] a) {
		return this.stripedQuotations.getBarList().toArray(a);
	}

	public double getLastClose() throws InvalidAlgorithmParameterException {
		return this.stripedQuotations.getNLastClosed(1)[0];
	}

	public Date getLastDate() {
		return this.stripedQuotations.getLast().getDate();
	}
	
}
