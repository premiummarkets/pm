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
import java.math.RoundingMode;
import java.text.NumberFormat;
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
import com.finance.pms.datasources.files.Transaction;
import com.finance.pms.datasources.files.TransactionType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;


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

	private NumberFormat moneysFormat;
	private NumberFormat quantityFormat;

	private BigDecimal fullAmountIn;
	private BigDecimal fullAmountOut;
	

	public TransactionPriceDialog(Shell parent, SlidingPortfolioShare slidingPortfolioShare, Transaction transaction) {
		super(new Shell(new Shell(parent,SWT.ON_TOP), SWT.DIALOG_TRIM));
		
		this.transaction = transaction;
		this.fullAmountIn= slidingPortfolioShare.getCashin(null, transaction.getDate(), slidingPortfolioShare.getTransactionCurrency());
		this.fullAmountOut= slidingPortfolioShare.getCashout(null, transaction.getDate(), slidingPortfolioShare.getTransactionCurrency());;
		
		this.portfolioShare = slidingPortfolioShare;
		
		moneysFormat = NumberFormat.getNumberInstance();
		moneysFormat.setRoundingMode(RoundingMode.HALF_EVEN);
		moneysFormat.setMinimumFractionDigits(2);
		moneysFormat.setMaximumFractionDigits(2);
		
		
		quantityFormat = NumberFormat.getNumberInstance();
		quantityFormat.setRoundingMode(RoundingMode.HALF_EVEN);
		quantityFormat.setMinimumFractionDigits(5);
		quantityFormat.setMaximumFractionDigits(5);
		
	}

	public void open() {
		try {
			
			getParent().setText(MainGui.APP_NAME+" - Alter Portfolio line");
			
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
				String string;
				if (portfolioShare.getExternalAccount() != null) {
					string = "WARNING :\nThis line as been imported using GNUCASH HTML report.\nBe aware that changes on this line will be discarded when re importing while using the same report file name.";
				} else {
					string = "Please edit the transaction details.";
				}
				
				newPortfoliolabel.setText(string);
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
							BigDecimal close = null;
							try {
								Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(portfolioShare.getStock(), newTransactionDate, true, portfolioShare.getStock().getMarketValuation().getCurrency(), ValidityFilter.CLOSE);
								close = quotations.getClosestCloseSpForDate(newTransactionDate);
							} catch (Exception exc) {
								LOGGER.warn(exc,exc);
							}
							if (close != null) {
								sharePriceText.setText(moneysFormat.format(close));
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
				transactionAmountText.setText(moneysFormat.format(amount()));
				transactionAmountText.setToolTipText(valuesToolTipMsg);
				transactionAmountText.setFont(MainGui.CONTENTFONT);
				transactionAmountText.addListener(SWT.DefaultSelection, new Listener() {
					public void handleEvent(Event e) {
						BigDecimal amount = solveCalculation(transactionAmountText.getText());
						transactionAmountText.setText(moneysFormat.format(amount));
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
				sharePriceText.setText(moneysFormat.format(transaction.getTransactionSharePrice()));
				sharePriceText.setToolTipText(valuesToolTipMsg);
				sharePriceText.setFont(MainGui.DEFAULTFONT);
				sharePriceText.setEditable(true);
				sharePriceText.setEnabled(true);
				sharePriceText.addListener(SWT.DefaultSelection, new Listener() {
					public void handleEvent(Event e) {
						BigDecimal sharePrice = solveCalculation(sharePriceText.getText());
						sharePriceText.setText(moneysFormat.format(sharePrice));
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
				quantityText.setText(quantityFormat.format(transaction.getQuantity()));
				quantityText.setToolTipText(valuesToolTipMsg);
				quantityText.setFont(MainGui.CONTENTFONT);
				quantityText.addListener(SWT.DefaultSelection, new Listener() {
					
					public void handleEvent(Event e) {
						BigDecimal quantity = solveCalculation(quantityText.getText());
						quantityText.setText(quantityFormat.format(quantity));
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
				amountInLabel.setText(moneysFormat.format(inHistoryForTheLine())+" "+portfolioShare.getTransactionCurrency());
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
				amountOutLabel.setText(moneysFormat.format(outHistoryForTheLine())+" "+portfolioShare.getTransactionCurrency());
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
							quantity = new Float(formatedQuantity().toString());
						} catch (NumberFormatException e) {
							LOGGER.warn(e);
						}
						Float amount=0f;
						try {
							amount = new Float(formatedAmount().toString());
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
		this.action.action();
		getParent().close();
	}

	private void refreshValues() {
		if (!this.resetButton.getSelection()) {
			amountInLabel.setText(moneysFormat.format(inHistoryForTheLine())+" "+portfolioShare.getTransactionCurrency());
			amountOutLabel.setText(moneysFormat.format(outHistoryForTheLine())+" "+portfolioShare.getTransactionCurrency());
		} else {
			amountInLabel.setText(moneysFormat.format(amount())+" "+portfolioShare.getTransactionCurrency());
			amountOutLabel.setText(moneysFormat.format(BigDecimal.ZERO)+" "+portfolioShare.getTransactionCurrency());
		}
		quantityText.setText(quantityFormat.format(transaction.getQuantity()));
		sharePriceText.setText(moneysFormat.format(transaction.getTransactionSharePrice()));
		transactionAmountText.setText(moneysFormat.format(amount()));
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
		ArrayList<BigDecimal> nums = new ArrayList<BigDecimal>();
		try {
			scanner.useDelimiter(pattern);
			while (scanner.hasNextBigDecimal()) {
				nums.add(scanner.nextBigDecimal().setScale(10, BigDecimal.ROUND_HALF_EVEN));
			}
		} finally {
			scanner.close();
		}
		
		ArrayList<Character> ope = new ArrayList<Character>();
		Scanner scanner2 = new Scanner(formula);
		try {
			Pattern pattern2 = Pattern.compile("\\s*[\\d,]+\\.?\\d*\\s*");
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
					result=result.multiply(nums.get(numIndex)).setScale(10, BigDecimal.ROUND_HALF_EVEN);
					break;
				case '+' :
					result=result.add(nums.get(numIndex)).setScale(10, BigDecimal.ROUND_HALF_EVEN);
					break;
				case '/' :
					result=result.divide(nums.get(numIndex), 10, BigDecimal.ROUND_HALF_EVEN);
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
			Float amount = new Float(formatedAmount().toString());
			if (changedField.equals("quantity")) {
				Float quantity = new Float(formatedQuantity().toString());
				transaction.setQuantity(quantity);
				
				Float transactionSharePrice = amount/quantity;
				sharePriceText.setText(moneysFormat.format(transactionSharePrice));
				transaction.setTransactionSharePrice(transactionSharePrice);
			} 
			else if (changedField.equals("price")) {
				Float price = new Float(formatedSharePrice().toString());
				transaction.setTransactionSharePrice(price);
				
				Float quantity = amount/price;
				quantityText.setText(quantityFormat.format(quantity));
				transaction.setQuantity(quantity);
			}
		}
		else if (sharePricePivot.getSelection()) {
			Float price = new Float(formatedSharePrice().toString());
			if (changedField.equals("quantity")) {
				Float quantity = new Float(formatedQuantity().toString());
				transaction.setQuantity(quantity);
				
				Float amount = price*quantity;
				transactionAmountText.setText(moneysFormat.format(amount));
			} 
			else if (changedField.equals("amount")) {
				Float amount = new Float(formatedAmount().toString());
				Float quantity = amount/price;
				quantityText.setText(quantityFormat.format(quantity));
				transaction.setQuantity(quantity);
			}
		}
		else if (quantityPivot.getSelection()) {
			Float quantity = new Float(formatedQuantity().toString());
			if (changedField.equals("price")) {
				Float price = new Float(formatedSharePrice().toString());
				transaction.setTransactionSharePrice(price);
				
				Float amount = quantity*price;
				transactionAmountText.setText(moneysFormat.format(amount));
			} 
			else if (changedField.equals("amount")) {
				//Float amount = new Float(formatedAmount().toString());
				//Float price = amount/quantity;
				BigDecimal amount = new BigDecimal(formatedAmount().toString()).setScale(10, BigDecimal.ROUND_HALF_EVEN);
				BigDecimal price = amount.divide(new BigDecimal(quantity), 10, BigDecimal.ROUND_HALF_EVEN);
				sharePriceText.setText(moneysFormat.format(price));
				transaction.setTransactionSharePrice(price);
			}
		}
	}

	private Number formatedQuantity() {
		try {
			return quantityFormat.parse(quantityText.getText());
		} catch (ParseException e) {
			LOGGER.warn(quantityText.getText() + " is invalid : " + e);
			throw new RuntimeException(e);
		}
	}

	private Number formatedSharePrice() {
		try {
			return moneysFormat.parse(sharePriceText.getText());
		} catch (ParseException e) {
			LOGGER.warn(sharePriceText.getText() + " is invalid : " + e);
			throw new RuntimeException(e);
		}
	}

	private Number formatedAmount() {
		try {
			return moneysFormat.parse(transactionAmountText.getText());
		} catch (ParseException e) {
			LOGGER.warn(sharePriceText.getText() + " is invalid : " + e);
			throw new RuntimeException(e);
		}
	}

	
	public BigDecimal inHistoryForTheLine() {
		
		if (transaction.getModtype().equals(TransactionType.AIN)) {
			BigDecimal fullin =  this.fullAmountIn.add(amount());
			return fullin;
		}
		return fullAmountIn;
	
	}

	private BigDecimal amount() {
		return transaction.getTransactionSharePrice().multiply(transaction.getQuantity()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public BigDecimal outHistoryForTheLine() {
		
		if (transaction.getModtype().equals(TransactionType.AOUT)) {
			BigDecimal fullout = this.fullAmountOut.add(amount());
			return fullout;
		} 
		return this.fullAmountOut;
			
	}
 
}
