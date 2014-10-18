/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.portfolio.gui;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.finance.pms.CursorFactory;
import com.finance.pms.MainGui;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.currency.CurrencyStockBuilder;
import com.finance.pms.portfolio.MonitorLevel;

public class NewCurrencyStockDialog extends Dialog {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(NewCurrencyStockDialog.class);
	
	private PortfolioComposite caller;

	private Currency fromCurrency;
	private Currency toCurrency;


	public NewCurrencyStockDialog(Shell parent, int style, PortfolioComposite caller) {
		
		super(new Shell(parent, SWT.DIALOG_TRIM | SWT.RESIZE));
		getParent().setText(MainGui.APP_NAME+" - Add a currency");
		
		this.caller = caller;
				
	}
	
	
	public void open() {
		
		GridLayout layout = new GridLayout();
		getParent().setLayout(layout);
		getParent().setBackground(MainGui.pOPUP_BG);
		RowLayout layout2 = new RowLayout(SWT.VERTICAL);
		layout2.fill = true;
		layout2.wrap = false;
		getParent().setLayout(layout2);
		
		Label referenceCurrency = new Label(getParent(), SWT.NONE);
		referenceCurrency.setText("Reference currency");
		referenceCurrency.setBackground(MainGui.pOPUP_BG);
		final CCombo fromCombo = new CCombo(getParent(), SWT.NONE);
		fromCombo.setEditable(false);
		fromCombo.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleSelectFromCurrency();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				handleSelectFromCurrency();
			}

			private void handleSelectFromCurrency() {
				fromCurrency = Currency.valueOf(fromCombo.getItem(fromCombo.getSelectionIndex()));
			}
		});
		
		Label targetCurrency = new Label(getParent(), SWT.NONE);
		targetCurrency.setText("Target currency");
		targetCurrency.setBackground(MainGui.pOPUP_BG);
		final CCombo toCombo = new CCombo(getParent(), SWT.NONE);
		toCombo.setEditable(false);
		toCombo.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleSelectToCurrency();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				handleSelectToCurrency();
			}

			private void handleSelectToCurrency() {
				toCurrency = Currency.valueOf(toCombo.getItem(toCombo.getSelectionIndex()));
			}
		});
		
		SortedSet<Currency> currencies = new TreeSet<Currency>(new Comparator<Currency>() {
			@Override
			public int compare(Currency o1, Currency o2) {
				return o1.name().compareTo(o2.name());
			}
		});
		
		currencies.addAll(Arrays.asList(Currency.values()));
		for (Currency currency : currencies) {
			fromCombo.add(currency.name());
			toCombo.add(currency.name());
		}
		
		Button validateButton = new Button(getParent(), SWT.PUSH);
		validateButton.setText("Ok");
		validateButton.addSelectionListener(new SelectionListener() {
		
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleSelectCurrencyExch();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				handleSelectCurrencyExch();
			}

			private void handleSelectCurrencyExch() {
				
				CurrencyStockBuilder currencyToStockBuilder = new CurrencyStockBuilder(fromCurrency, toCurrency);
				getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
				try {
					Stock currencyStock = currencyToStockBuilder.buildAndFetchRates();
					Set<Stock> stocks = new TreeSet<Stock>();
					stocks.add(currencyStock);
					int currentTabSelection = ((PortfolioComposite) caller).getCurrentTabSelection();
					if (currentTabSelection != -1) {
						((PortfolioComposite) caller).addShares(currentTabSelection, stocks, BigDecimal.ONE, MonitorLevel.BEARISH);
						((PortfolioComposite) caller).refreshPortfolioTotalsInfos(currentTabSelection);
					}
				} catch (Exception e) {
					LOGGER.error(e,e);
				} finally {
					getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
				}
				
			}
		});
		
		fromCombo.select(fromCombo.indexOf(Currency.EUR.name()));
		toCombo.select(toCombo.indexOf(Currency.GBP.name()));
		
		fromCurrency = Currency.valueOf(fromCombo.getItem(fromCombo.getSelectionIndex()));
		toCurrency = Currency.valueOf(toCombo.getItem(toCombo.getSelectionIndex()));
		
		getParent().layout();
		getParent().pack();
		getParent().open();
		
	}
	

}
