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
import java.util.ArrayList;
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
import org.eclipse.swt.widgets.Composite;
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
import com.finance.pms.portfolio.Transaction;
import com.finance.pms.portfolio.Transaction.TransactionType;


public class TransactionPriceDialog extends Dialog {


	protected static MyLogger LOGGER = MyLogger.getLogger(TransactionPriceDialog.class);

	private Button newPortfollioValidateButton;

	private Text sharePriceText;
	private Text quantityText;
	private Label amountInText;
	private Label amountOutText;
	private Text transactionAmountText;
	
	private Button resetButton;
	private Button cancelButton;

	private Boolean ok = false;
	private Boolean reset = false;

	Transaction transaction;
	Composite portfolioTable;

	private ActionDialogAction action;

	private Button sharePricePivot;
	private Button transactionAmountPivot;
	private Button quatityPivot;
	

	public TransactionPriceDialog(Shell parent, Composite portfolioTable,Transaction transaction) {
		super(new Shell(new Shell(parent,SWT.ON_TOP), SWT.DIALOG_TRIM));
		this.transaction = transaction;
		this.portfolioTable = portfolioTable;
	}


	/**
	 * Open.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void open() {
		try {
			
			getParent().setText("Premium Markets - Alter portfolio line");
			
			GridLayout dialogShellLayout = new GridLayout();
			dialogShellLayout.verticalSpacing = 8;
			dialogShellLayout.numColumns = 3;
			getParent().layout();			
			getParent().setLayout(dialogShellLayout);
			//getParent().setSize(400,400);
			getParent().setBackground(MainGui.pOPUP_BG);
			
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
					quantityText.setEditable(!quatityPivot.getSelection());
					quantityText.setEnabled(!quatityPivot.getSelection());
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
			}
			{
				Label newPortfoliolabel = new Label(getParent(), SWT.NONE);
				newPortfoliolabel.setText("Transaction amount : ");
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
				transactionAmountText.setFont(MainGui.CONTENTFONT);
				transactionAmountText.addListener(SWT.DefaultSelection, new Listener() {
					public void handleEvent(Event e) {
						BigDecimal amount = solveCalculation(transactionAmountText.getText());
						transactionAmountText.setText(amount.toString());
						pivotValue("amount");
						refreshValues();
					}
				});
			}
			{
				Label newPortfoliolabel = new Label(getParent(), SWT.NONE);
				newPortfoliolabel.setText("Unit price : ");
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
				sharePriceText.setFont(MainGui.DEFAULTFONT);
				sharePriceText.setEditable(true);
				sharePriceText.setEnabled(true);
				sharePriceText.addListener(SWT.DefaultSelection, new Listener() {
					public void handleEvent(Event e) {
						BigDecimal sharePrice = solveCalculation(sharePriceText.getText());
						sharePriceText.setText(sharePrice.toString());
						pivotValue("price");
						refreshValues();
					}
				});
			}
			{
				Label newPortfoliolabel = new Label(getParent(), SWT.NONE);
				newPortfoliolabel.setText("Quantity : ");
				newPortfoliolabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfoliolabel.setLayoutData(newPortfoliolabelLData);
				newPortfoliolabel.setBackground(MainGui.pOPUP_BG);
			}
			{
				quatityPivot = new Button(getParent(), SWT.RADIO);
				quatityPivot.addSelectionListener(pivotListener);
				quatityPivot.setToolTipText("Freeze value");
				
				quantityText = new Text(getParent(), SWT.BORDER);
				GridData newPortfolioTextLData = new GridData(GridData.FILL_HORIZONTAL);
				quantityText.setLayoutData(newPortfolioTextLData);
				quantityText.setText(transaction.getQuantity().toString());
				quantityText.setFont(MainGui.CONTENTFONT);
				quantityText.addListener(SWT.DefaultSelection, new Listener() {
					
					public void handleEvent(Event e) {
						BigDecimal quatity = solveCalculation(quantityText.getText());
						quantityText.setText(quatity.toString());
						pivotValue("quantity");
						refreshValues();
					}
				});
			}
			{
				Label newPortfoliolabel = new Label(getParent(), SWT.NONE);
				newPortfoliolabel.setText("Total money in for the line : ");
				newPortfoliolabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfoliolabelLData.horizontalSpan = 2;
				newPortfoliolabel.setLayoutData(newPortfoliolabelLData);
				newPortfoliolabel.setBackground(MainGui.pOPUP_BG);
			}
			{
				amountInText = new Label(getParent(), SWT.NONE);
				GridData newPortfolioTextLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfolioTextLData.horizontalSpan = 1;
				amountInText.setLayoutData(newPortfolioTextLData);
				amountInText.setText(transaction.fullAmountIn().toString());
				amountInText.setFont(MainGui.CONTENTFONT);
				amountInText.setBackground(MainGui.pOPUP_BG);
			}
			{
				Label newPortfoliolabel = new Label(getParent(), SWT.NONE);
				newPortfoliolabel.setText("Total money out for the line : ");
				newPortfoliolabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfoliolabelLData.horizontalSpan = 2;
				newPortfoliolabel.setLayoutData(newPortfoliolabelLData);
				newPortfoliolabel.setBackground(MainGui.pOPUP_BG);
			}
			{
				amountOutText =  new Label(getParent(), SWT.NONE);
				GridData newPortfolioTextLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfolioTextLData.horizontalSpan = 1;
				amountOutText.setLayoutData(newPortfolioTextLData);
				amountOutText.setText(transaction.fullAmountOut().toString());
				amountOutText.setFont(MainGui.CONTENTFONT);
				amountOutText.setBackground(MainGui.pOPUP_BG);
			}
			{
				resetButton = new Button(getParent(), SWT.CHECK);
				GridData newPortfollioValidateButtonLData = new GridData();
				newPortfollioValidateButtonLData.horizontalSpan = 3;
				newPortfollioValidateButtonLData.horizontalAlignment = GridData.BEGINNING;
				resetButton.setLayoutData(newPortfollioValidateButtonLData);
				resetButton.setText("Reset to new share?");
				resetButton.setFont(MainGui.DEFAULTFONT);
				resetButton.addSelectionListener(new SelectionListener() {
					
					public void widgetDefaultSelected(SelectionEvent arg0) {
						refreshValues();
					}
					
					public void widgetSelected(SelectionEvent arg0) {
						refreshValues();
						
					}
				});
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
						reset = resetButton.getSelection();
						
						Float quantity = new Float(quantityText.getText());
						Float amount = new Float(transactionAmountText.getText());
						Float sp = (quantity != 0)?amount/quantity:1;
						transaction.setQuantity(quantity);
						transaction.setTransactionSharePrice(sp);
						
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
	
	/**
	 * New portfollio validate button mouse down.
	 * 
	 * @param evt the evt
	 * 
	 * @author Guillaume Thoreton
	 */
	private void newTransactionValidateButtonMouseDown(EventObject evt) {
		this.action.action(null);
		getParent().close();
	}

	private void refreshValues() {
		if (!this.resetButton.getSelection()) {
			amountInText.setText(transaction.fullAmountIn().toString());
			amountOutText.setText(transaction.fullAmountOut().toString());
		} else {
			amountInText.setText(transaction.amount().toString());
			amountOutText.setText("0");
		}
		quantityText.setText(transaction.getQuantity().toString());
		sharePriceText.setText(transaction.getTransactionSharePrice().toString());
		transactionAmountText.setText(transaction.amount().toString());
	}
	
	
	public Boolean getReset() {
		return reset;
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
				nums.add(scanner.nextBigDecimal().setScale(2, BigDecimal.ROUND_DOWN));
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
		
		BigDecimal result = nums.get(0);
		int numIndex = 1;
		for (Character character:ope) {
			switch(character) {
				case '*' : 
					result=result.multiply(nums.get(numIndex)).setScale(2, BigDecimal.ROUND_DOWN);
					break;
				case '+' :
					result=result.add(nums.get(numIndex)).setScale(2, BigDecimal.ROUND_DOWN);
					break;
				case '/' :
					result=result.divide(nums.get(numIndex),2,BigDecimal.ROUND_DOWN);
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
	
	private void pivotValue(String changedField) {
		
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
				//transaction.setTransactionPrice(amount);
			} 
			else if (changedField.equals("amount")) {
				Float amount = new Float(transactionAmountText.getText());
				//transaction.setTransactionPrice(amount);
				
				Float quantity = amount/price;
				quantityText.setText(quantity.toString());
				transaction.setQuantity(quantity);
			}
		}
		else if (quatityPivot.getSelection()) {
			Float quantity = new Float(quantityText.getText());
			if (changedField.equals("price")) {
				Float price = new Float(sharePriceText.getText());
				transaction.setTransactionSharePrice(price);
				
				Float amount = quantity*price;
				transactionAmountText.setText(amount.toString());
				//transaction.setTransactionPrice(amount);
			} 
			else if (changedField.equals("amount")) {
				Float amount = new Float(transactionAmountText.getText());
				//transaction.setTransactionPrice(amount);
				
				Float price = amount/quantity;
				sharePriceText.setText(price.toString());
				transaction.setTransactionSharePrice(price);
			}
		}
	}
 
}
