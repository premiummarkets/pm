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
	private int tabIx;



	public NewCurrencyStockDialog(int tabIdx, Shell parent, int style, PortfolioComposite caller) {
		
		//super(new Shell(parent, SWT.PRIMARY_MODAL | SWT.SHELL_TRIM), style);
		super(new Shell(parent, SWT.DIALOG_TRIM | SWT.RESIZE));
		getParent().setText("Premium Markets - Add a currency");
		
		this.caller = caller;
		this.tabIx = tabIdx;
				
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
		fromCombo.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				handle();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				handle();
			}

			private void handle() {
				fromCurrency = Currency.valueOf(fromCombo.getItem(fromCombo.getSelectionIndex()));
			}
		});
		
		Label targetCurrency = new Label(getParent(), SWT.NONE);
		targetCurrency.setText("Target currency");
		targetCurrency.setBackground(MainGui.pOPUP_BG);
		final CCombo toCombo = new CCombo(getParent(), SWT.NONE);
		toCombo.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				handle();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				handle();
			}

			private void handle() {
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
				handle();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				handle();
			}

			private void handle() {
				CurrencyStockBuilder currencyToStockBuilder = new CurrencyStockBuilder(fromCurrency, toCurrency);
				
				getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
				try {
					Stock currencyStock = currencyToStockBuilder.buildStock();
					Set<Stock> stocks = new TreeSet<Stock>();
					stocks.add(currencyStock);
					((PortfolioComposite) caller).addShares(tabIx, stocks, BigDecimal.ONE , MonitorLevel.BEARISH);
					((PortfolioComposite) caller).refreshPortfolioTotalsInfos(((PortfolioComposite) caller).getCurrentTabSelection());
				} catch (Exception e) {
					LOGGER.error(e,e);

				} finally {
					getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));

				}
			}
		});
		
		getParent().layout();
		getParent().pack();
		getParent().open();
		
		
//		Display display = getParent().getDisplay();
//		while (!getParent().isDisposed()) {
//			try {
//				if (!display.readAndDispatch()) display.sleep();
//			} catch (RuntimeException e) {
//				LOGGER.error("Error in Error dialog Gui : "+e.getMessage(),e);
//				LOGGER.debug("Error in Error Dialog Gui : ",e);
//			} catch (Error e) {
//				LOGGER.error("Error in  Gui : "+e.getMessage(),e);
//				LOGGER.debug("Error in  Gui : ",e);
//			}
//		}
		
	}
	

}
