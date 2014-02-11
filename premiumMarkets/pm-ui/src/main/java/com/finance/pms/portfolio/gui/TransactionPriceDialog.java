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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.ActionDialogAction;
import com.finance.pms.MainGui;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.web.currency.CurrencyConverter;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.Transaction;
import com.finance.pms.portfolio.Transaction.TransactionType;


public class TransactionPriceDialog extends Dialog {


	protected static MyLogger LOGGER = MyLogger.getLogger(TransactionPriceDialog.class);

	private Button newPortfollioValidateButton;

	private Text sharePriceText;
	private Text quantityText;
	private Text transactionAmountText;
	
	private Label amountInLabel;
	private Label amountOutLabel;

	private Text dateText;
	
	private Button resetButton;
	private Button cancelButton;

	private Boolean ok = false;
	private Boolean resetLine = false;

	Transaction transaction;
	SlidingPortfolioShare portfolioShare;

	private ActionDialogAction action;

	private Button sharePricePivot;
	private Button transactionAmountPivot;
	private Button quantityPivot;
	

	public TransactionPriceDialog(Shell parent, SlidingPortfolioShare slidingPortfolioShare, Transaction transaction) {
		super(new Shell(new Shell(parent,SWT.ON_TOP), SWT.DIALOG_TRIM));
		this.transaction = transaction;
		portfolioShare = slidingPortfolioShare;
	}

	public void open() {
		try {
			
			getParent().setText("Premium Markets - Alter portfolio line");
			
			GridLayout dialogShellLayout = new GridLayout();
			dialogShellLayout.verticalSpacing = 8;
			dialogShellLayout.numColumns = 3;
			getParent().layout();			
			getParent().setLayout(dialogShellLayout);
			getParent().setBackground(MainGui.pOPUP_BG);
			
			String valuesToolTipMsg = "In this dialog you can set your transaction details.\nOperation using * / + - are accepted.\nWith the radio button you can freeze one of the values (amount, price or quantity) to use it as a pivot.";
			getParent().setToolTipText(valuesToolTipMsg);
			
			SelectionListener pivotListener = new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					handle();
				}
				
				private void handle() {
					transactionAmountText.setEditable(!transactionAmountPivot.getSelection());
					transactionAmountText.setEnabled(!transactionAmountPivot.getSelection());
					sharePriceText.setEditable(!sharePricePivot.getSelection());
					sharePriceText.setEnabled(!sharePricePivot.getSelection());
					quantityText.setEditable(!quantityPivot.getSelection());
					quantityText.setEnabled(!quantityPivot.getSelection());
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handle();
				}
			};
			
			
			{
				Label newPortfoliolabel = new Label(getParent(), SWT.BORDER);
				newPortfoliolabel.setText(((transaction.getModtype().equals(TransactionType.AIN))?"Buy details : ":"Sell details : ")+ " Please edit ");
				newPortfoliolabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfoliolabelLData.horizontalSpan = 3;
				newPortfoliolabel.setLayoutData(newPortfoliolabelLData);
				newPortfoliolabel.setBackground(MainGui.pOPUP_BG);
			}
			{
				Group  group = new Group(getParent(), SWT.NONE);
				GridData newPortfoliolabelLData = new GridData(SWT.FILL,SWT.FILL,true,false);
				newPortfoliolabelLData.horizontalSpan = 3;
				group.setLayoutData(newPortfoliolabelLData);
				group.setBackground(MainGui.pOPUP_BG);
				group.setLayout(new FillLayout(SWT.HORIZONTAL));
				group.setToolTipText(valuesToolTipMsg);
				
				Button way1 = new Button(group, SWT.RADIO);
				way1.setText("buy");
				way1.setFont(MainGui.DEFAULTFONT);
				way1.setBackground(MainGui.pOPUP_BG);
				way1.setSelection(transaction.getModtype().equals(TransactionType.AIN));
				way1.addSelectionListener(new SelectionListener() {
					
					public void widgetDefaultSelected(SelectionEvent arg0) {
						transaction.setModtype(TransactionType.AIN);
					}
					
					public void widgetSelected(SelectionEvent arg0) {
						transaction.setModtype(TransactionType.AIN);
					}
				});
				Button way2 = new Button(group, SWT.RADIO);
				way2.setText("sell");
				way2.setFont(MainGui.DEFAULTFONT);
				way2.setBackground(MainGui.pOPUP_BG);
				way2.setSelection(transaction.getModtype().equals(TransactionType.AOUT));
				way2.addSelectionListener(new SelectionListener() {
					
					public void widgetDefaultSelected(SelectionEvent arg0) {
						transaction.setModtype(TransactionType.AOUT);
					}
					
					public void widgetSelected(SelectionEvent arg0) {
						transaction.setModtype(TransactionType.AOUT);
					}
				});
				resetButton = new Button(group, SWT.RADIO);
				resetButton.setText("reset transactions");
				resetButton.setToolTipText(
						"If selected, this will delete all previous transactions regarding that line in the current portfolio, replacing it with this transaction."
								+ "\nA quantity 0 will remove the line and all its transactions.");
				resetButton.setFont(MainGui.DEFAULTFONT);
				resetButton.setBackground(MainGui.pOPUP_BG);
				resetButton.setSelection(false);
				resetButton.addSelectionListener(new SelectionListener() {

					public void widgetDefaultSelected(SelectionEvent arg0) {
						refreshValues();
					}

					public void widgetSelected(SelectionEvent arg0) {
						refreshValues();

					}
				});
			}
			final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
			{
				Label dateLabel = new Label(getParent(), SWT.NONE);
				GridData newPortfolioTextLData = new GridData(GridData.FILL_HORIZONTAL);
				dateLabel.setLayoutData(newPortfolioTextLData);
				dateLabel.setText("Transaction date : ");
				dateLabel.setToolTipText("dd MM yyyy");
				dateLabel.setFont(MainGui.CONTENTFONT);
				dateLabel.setBackground(MainGui.pOPUP_BG);
			}
			{
				dateText = new Text(getParent(), SWT.NONE);
				GridData newPortfolioTextLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfolioTextLData.horizontalSpan=2;
				dateText.setLayoutData(newPortfolioTextLData);
				dateText.setText(dateFormat.format(transaction.getDate()));
				dateText.setToolTipText("dd MM yyyy");
				dateText.setFont(MainGui.CONTENTFONT);
				dateText.addListener(SWT.DefaultSelection, new Listener() {
					public void handleEvent(Event e) {
						try {
							Date newTransactionDate = dateFormat.parse(dateText.getText());
							transaction.setDate(newTransactionDate);
							ArrayList<QuotationUnit> loadNStripedQuotationsBefore = DataSource.getInstance().loadNStripedQuotationsBefore(portfolioShare.getStock(), newTransactionDate, 1, true);
							if (loadNStripedQuotationsBefore != null && loadNStripedQuotationsBefore.size() > 0) {
								CurrencyConverter currencyConverter = PortfolioMgr.getInstance().getCurrencyConverter();
								BigDecimal close = loadNStripedQuotationsBefore.get(0).getClose();
								close = currencyConverter.convert(portfolioShare.getStock().getMarketValuation(), portfolioShare.getStock().getMarketValuation().getCurrency(), close, newTransactionDate);
								sharePriceText.setText(close.toString());
								if (sharePricePivot.getSelection()) {//Price is the pivot
									transaction.setTransactionSharePrice(close);
									updateThirdFieldFor("quantity"); //ie the amount will be chaged
								} else {
									updateThirdFieldFor("price"); //ie quantity or amount will be changed depending on the pivot
								}
								refreshValues();
							}
							
						} catch (ParseException e1) {
							LOGGER.warn(e1);
							dateText.setText(dateFormat.format(transaction.getDate()));
						}
					}
				});
			}
			{
				Label newPortfoliolabel = new Label(getParent(), SWT.NONE);
				newPortfoliolabel.setText("Transaction amount : ");
				newPortfoliolabel.setToolTipText(valuesToolTipMsg);
				newPortfoliolabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfoliolabel.setLayoutData(newPortfoliolabelLData);
				newPortfoliolabel.setBackground(MainGui.pOPUP_BG);
			}
			{
				transactionAmountPivot = new Button(getParent(), SWT.RADIO);
				transactionAmountPivot.addSelectionListener(pivotListener);
				transactionAmountPivot.setToolTipText("Freeze value");
				
				transactionAmountText = new Text(getParent(), SWT.BORDER);
				GridData newPortfolioTextLData = new GridData(GridData.FILL_HORIZONTAL);
				transactionAmountText.setLayoutData(newPortfolioTextLData);
				transactionAmountText.setText(transaction.amount().toString());
				transactionAmountText.setToolTipText(valuesToolTipMsg);
				transactionAmountText.setFont(MainGui.CONTENTFONT);
				transactionAmountText.addListener(SWT.DefaultSelection, new Listener() {
					public void handleEvent(Event e) {
						BigDecimal amount = solveCalculation(transactionAmountText.getText());
						transactionAmountText.setText(amount.toString());
						updateThirdFieldFor("amount");
						refreshValues();
					}
				});
			}
			{
				Label newPortfoliolabel = new Label(getParent(), SWT.NONE);
				newPortfoliolabel.setText("Unit price : ");
				newPortfoliolabel.setToolTipText(valuesToolTipMsg);
				newPortfoliolabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfoliolabel.setLayoutData(newPortfoliolabelLData);
				newPortfoliolabel.setBackground(MainGui.pOPUP_BG);
			}
			{
				sharePricePivot = new Button(getParent(), SWT.RADIO);
				sharePricePivot.addSelectionListener(pivotListener);
				sharePricePivot.setToolTipText("Freeze value");
				
				sharePriceText = new Text(getParent(), SWT.BORDER);
				GridData newPortfolioTextLData = new GridData(GridData.FILL_HORIZONTAL);
				sharePriceText.setLayoutData(newPortfolioTextLData);
				sharePriceText.setText(transaction.getTransactionSharePrice().toString());
				sharePriceText.setToolTipText(valuesToolTipMsg);
				sharePriceText.setFont(MainGui.DEFAULTFONT);
				sharePriceText.setEditable(true);
				sharePriceText.setEnabled(true);
				sharePriceText.addListener(SWT.DefaultSelection, new Listener() {
					public void handleEvent(Event e) {
						BigDecimal sharePrice = solveCalculation(sharePriceText.getText());
						sharePriceText.setText(sharePrice.toString());
						updateThirdFieldFor("price");
						refreshValues();
					}
				});
			}
			{
				Label newPortfoliolabel = new Label(getParent(), SWT.NONE);
				newPortfoliolabel.setText("Quantity : ");
				newPortfoliolabel.setToolTipText(valuesToolTipMsg);
				newPortfoliolabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfoliolabel.setLayoutData(newPortfoliolabelLData);
				newPortfoliolabel.setBackground(MainGui.pOPUP_BG);
			}
			{
				quantityPivot = new Button(getParent(), SWT.RADIO);
				quantityPivot.addSelectionListener(pivotListener);
				quantityPivot.setToolTipText("Freeze value");
				
				quantityText = new Text(getParent(), SWT.BORDER);
				GridData newPortfolioTextLData = new GridData(GridData.FILL_HORIZONTAL);
				quantityText.setLayoutData(newPortfolioTextLData);
				quantityText.setText(transaction.getQuantity().toString());
				quantityText.setToolTipText(valuesToolTipMsg);
				quantityText.setFont(MainGui.CONTENTFONT);
				quantityText.addListener(SWT.DefaultSelection, new Listener() {
					
					public void handleEvent(Event e) {
						BigDecimal quatity = solveCalculation(quantityText.getText());
						quantityText.setText(quatity.toString());
						updateThirdFieldFor("quantity");
						refreshValues();
					}
				});
			}
			{
				Label newPortfoliolabel = new Label(getParent(), SWT.NONE);
				newPortfoliolabel.setText("Money in after : ");
				newPortfoliolabel.setToolTipText("Total money in for the line after the transaction.");
				newPortfoliolabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfoliolabelLData.horizontalSpan = 2;
				newPortfoliolabel.setLayoutData(newPortfoliolabelLData);
				newPortfoliolabel.setBackground(MainGui.pOPUP_BG);
			}
			{
				amountInLabel = new Label(getParent(), SWT.NONE);
				GridData newPortfolioTextLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfolioTextLData.horizontalSpan = 1;
				amountInLabel.setLayoutData(newPortfolioTextLData);
				amountInLabel.setText(transaction.fullAmountIn().toString());
				amountInLabel.setToolTipText("Total money in for the line after the transaction.");
				amountInLabel.setFont(MainGui.CONTENTFONT);
				amountInLabel.setBackground(MainGui.pOPUP_BG);
			}
			{
				Label newPortfoliolabel = new Label(getParent(), SWT.NONE);
				newPortfoliolabel.setText("Money out after : ");
				newPortfoliolabel.setToolTipText("Total money out for the line after the transaction.");
				newPortfoliolabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfoliolabelLData.horizontalSpan = 2;
				newPortfoliolabel.setLayoutData(newPortfoliolabelLData);
				newPortfoliolabel.setBackground(MainGui.pOPUP_BG);
			}
			{
				amountOutLabel =  new Label(getParent(), SWT.NONE);
				GridData newPortfolioTextLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfolioTextLData.horizontalSpan = 1;
				amountOutLabel.setLayoutData(newPortfolioTextLData);
				amountOutLabel.setText(transaction.fullAmountOut().toString());
				amountOutLabel.setToolTipText("Total money out for the line after the transaction.");
				amountOutLabel.setFont(MainGui.CONTENTFONT);
				amountOutLabel.setBackground(MainGui.pOPUP_BG);
			}
			{
				cancelButton = new Button(getParent(), SWT.PUSH);
				GridData newPortfollioValidateButtonLData = new GridData();
				newPortfollioValidateButtonLData.horizontalSpan = 2;
				newPortfollioValidateButtonLData.horizontalAlignment = GridData.BEGINNING;
				cancelButton.setLayoutData(newPortfollioValidateButtonLData);
				cancelButton.setText("Cancel");
				cancelButton.setFont(MainGui.DEFAULTFONT);
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
						ok = false;
						newTransactionValidateButtonMouseDown(evt);
					}
				});
			}
			{
				newPortfollioValidateButton = new Button(getParent(), SWT.PUSH);
				GridData newPortfollioValidateButtonLData = new GridData();
				newPortfollioValidateButtonLData.horizontalSpan = 1;
				newPortfollioValidateButtonLData.horizontalAlignment = GridData.END;
				newPortfollioValidateButton.setLayoutData(newPortfollioValidateButtonLData);
				newPortfollioValidateButton.setText("Ok");
				newPortfollioValidateButton.setFont(MainGui.DEFAULTFONT);
				newPortfollioValidateButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
						ok = true;
						resetLine = resetButton.getSelection();
						
						Float quantity = 0f;
						try {
							quantity = new Float(quantityText.getText());
						} catch (NumberFormatException e) {
							LOGGER.warn(e);
						}
						Float amount=0f;
						try {
							amount = new Float(transactionAmountText.getText());
						} catch (NumberFormatException e) {
							LOGGER.warn(e);
						}
						Float sp = (quantity != 0)?amount/quantity:1;
						transaction.setQuantity(quantity);
						transaction.setTransactionSharePrice(sp);
						
						try {
							transaction.setDate(dateFormat.parse(dateText.getText()));
						} catch (ParseException e) {
							LOGGER.warn(e);
						}
						
						newTransactionValidateButtonMouseDown(evt);
					}
				});
			}
			
			sharePricePivot.setSelection(true);
			sharePriceText.setEditable(false);
			sharePriceText.setEnabled(false);
			
			getParent().layout();
			getParent().pack();
			getParent().open();
			
		} catch (Exception e) {
			LOGGER.error("",e);
		}
	}
	
	private void newTransactionValidateButtonMouseDown(EventObject evt) {
		this.action.action(null);
		getParent().close();
	}

	private void refreshValues() {
		if (!this.resetButton.getSelection()) {
			amountInLabel.setText(transaction.fullAmountIn().toString());
			amountOutLabel.setText(transaction.fullAmountOut().toString());
		} else {
			amountInLabel.setText(transaction.amount().toString());
			amountOutLabel.setText("0");
		}
		quantityText.setText(transaction.getQuantity().toString());
		sharePriceText.setText(transaction.getTransactionSharePrice().toString());
		transactionAmountText.setText(transaction.amount().toString());
	}
	
	
	public Boolean getResetLine() {
		return resetLine;
	}
	
	public Boolean getOk() {
		return ok;
	}
	
	
	private BigDecimal solveCalculation(String formula) {
		
		Scanner scanner = new Scanner(formula);
		Pattern pattern = Pattern.compile("\\s*[[-\\*][\\+/]]\\s*");
		ArrayList<BigDecimal> nums;
		try {
			scanner.useDelimiter(pattern);
			nums = new ArrayList<BigDecimal>();
			while (scanner.hasNextBigDecimal()) {
				nums.add(scanner.nextBigDecimal().setScale(4, BigDecimal.ROUND_DOWN));
			}
		} finally {
			scanner.close();
		}
		
		ArrayList<Character> ope = new ArrayList<Character>();
		Scanner scanner2 = new Scanner(formula);
		try {
			Pattern pattern2 = Pattern.compile("\\s*\\d+\\.?\\d*\\s*");
			scanner2.useDelimiter(pattern2);
			while (scanner2.hasNext()) {
				ope.add(scanner2.next().charAt(0));
			}
		} finally {
			scanner2.close();
		}
		
		if (nums.size() == 0) return BigDecimal.ZERO;
		
		BigDecimal result = nums.get(0);
		int numIndex = 1;
		for (Character character:ope) {
			switch(character) {
				case '*' : 
					result=result.multiply(nums.get(numIndex)).setScale(4, BigDecimal.ROUND_DOWN);
					break;
				case '+' :
					result=result.add(nums.get(numIndex)).setScale(4, BigDecimal.ROUND_DOWN);
					break;
				case '/' :
					result=result.divide(nums.get(numIndex), 4, BigDecimal.ROUND_DOWN);
					break;
				case '-' :
					result=result.subtract(nums.get(numIndex));
					break;
			}
			numIndex++;
		}
		
		return result;
		
	}


	public void setAction(ActionDialogAction action) {
		this.action= action;
		
	}
	
	private void updateThirdFieldFor(String changedField) {
		
		if (transactionAmountPivot.getSelection()) {
			Float amount = new Float(transactionAmountText.getText());
			if (changedField.equals("quantity")) {
				Float quantity = new Float(quantityText.getText());
				transaction.setQuantity(quantity);
				
				Float transactionSharePrice = amount/quantity;
				sharePriceText.setText(transactionSharePrice.toString());
				transaction.setTransactionSharePrice(transactionSharePrice);
			} 
			else if (changedField.equals("price")) {
				Float price = new Float(sharePriceText.getText());
				transaction.setTransactionSharePrice(price);
				
				Float quantity = amount/price;
				quantityText.setText(quantity.toString());
				transaction.setQuantity(quantity);
			}
		}
		else if (sharePricePivot.getSelection()) {
			Float price = new Float(sharePriceText.getText());
			if (changedField.equals("quantity")) {
				Float quantity = new Float(quantityText.getText());
				transaction.setQuantity(quantity);
				
				Float amount = price*quantity;
				transactionAmountText.setText(amount.toString());
			} 
			else if (changedField.equals("amount")) {
				Float amount = new Float(transactionAmountText.getText());
				Float quantity = amount/price;
				quantityText.setText(quantity.toString());
				transaction.setQuantity(quantity);
			}
		}
		else if (quantityPivot.getSelection()) {
			Float quantity = new Float(quantityText.getText());
			if (changedField.equals("price")) {
				Float price = new Float(sharePriceText.getText());
				transaction.setTransactionSharePrice(price);
				
				Float amount = quantity*price;
				transactionAmountText.setText(amount.toString());
			} 
			else if (changedField.equals("amount")) {
				Float amount = new Float(transactionAmountText.getText());
				
				Float price = amount/quantity;
				sharePriceText.setText(price.toString());
				transaction.setTransactionSharePrice(price);
			}
		}
	}
 
}
