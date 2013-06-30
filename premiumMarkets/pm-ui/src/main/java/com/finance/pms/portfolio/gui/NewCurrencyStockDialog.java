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
		
		super(new Shell(parent, SWT.PRIMARY_MODAL | SWT.SHELL_TRIM), style);
		getParent().setText("Add a currency");
		
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
