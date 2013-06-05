/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
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
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.MainGui;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.portfolio.Transaction;
import com.finance.pms.portfolio.Transaction.TransactionType;




// TODO: Auto-generated Javadoc
/**
 * The Class NewPortfolioDialog.
 * 
 * @author Guillaume Thoreton
 */
public class TransactionPriceDialog {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(TransactionPriceDialog.class);

	/** The dialog shell. */
	private Shell dialogShell;
	
	/** The new portfoliolabel. */
	private Label newPortfoliolabel;
	
	/** The new portfollio validate button. */
	private Button newPortfollioValidateButton;
	
	/** The new portfolio text. */
	private Text sharePriceText;
	private Text quantityText;
	private Text amountInText;
	private Text amountOutText;
	private Text transactionAmountText;
	
	private Button resetButton;
	private Button cancelButton;
	
	private Boolean ok = false;
	private Boolean reset = false;

	Transaction transaction;
	Composite portfolioTable;

	
	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void main(String[] args) {
		try {
	//		Display display = Display.getDefault();
			//Shell shell = new Shell(display);
			String dbfile = args[0];
			SpringContext ctx = new SpringContext(dbfile);
			//ctx.setDataSource(dbfile);
			//ctx.setProvidersSource(dbfile);
			ctx.setMasSource(dbfile,"false");
			ctx.loadBeans(new String[] {"/connexions.xml", "/swtclients.xml","talibanalysisservices.xml","masanalysisservices.xml"});
			ctx.refresh();
		
			//FIXME tests
//			PortfolioShare pstmp = new PortfolioShare("FTE.PA",3F,new Date(),40F,0F,MonitorLevel.ANY,BigDecimal.TEN,Currency.NAN);
			
//			pstmp.setPortfolio(new UserPortfolio("Toto"));
			
//			pstmp.setQuantity(2F);
//			SelectPriceDialog inst = new SelectPriceDialog(shell,pstmp,TransactionType.QUANTITY,3F,40F,0F);
//			
//			pstmp.setCashin(60F);
//			SelectPriceDialog inst = new SelectPriceDialog(shell,pstmp,TransactionType.AIN,3F,40F,0F);
//			TransactionPriceDialog inst = new TransactionPriceDialog(shell,new Transaction(100F,40F,1F,11F,TransactionType.NULL));
//			pstmp.setCashout(20F);
//			SelectPriceDialog inst = new SelectPriceDialog(shell,pstmp,TransactionType.AOUT,3F,40F,0F);
			
			
//			inst.open();
			
		} catch (Exception e) {
			LOGGER.debug("",e);
		}
	}
	

	public TransactionPriceDialog(Composite portfolioTable,Transaction transaction) {

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
			dialogShell = new Shell(portfolioTable.getDisplay(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
			Rectangle portfolioTableBounds = portfolioTable.getBounds();
			Rectangle shellBounds = portfolioTable.getShell().getBounds();
			dialogShell.setLocation(shellBounds.x+shellBounds.width/2,shellBounds.y+portfolioTableBounds.y+portfolioTableBounds.height);
			
			dialogShell.addShellListener(new ShellAdapter() {
				@Override
				public void shellClosed(ShellEvent evt) {
					//newTransactionValidateButtonMouseDown(evt);
				}
			});
			
			GridLayout dialogShellLayout = new GridLayout();
			dialogShellLayout.verticalSpacing = 8;
			dialogShellLayout.numColumns = 2;
			dialogShell.layout();			
			dialogShell.setLayout(dialogShellLayout);
			dialogShell.pack();
			dialogShell.setSize(400,400);
			dialogShell.setBackground(MainGui.pOPUP_BG);
			
			{
				newPortfoliolabel = new Label(dialogShell, SWT.BORDER);
				newPortfoliolabel.setText(((transaction.getModtype().equals(TransactionType.AIN))?"Buy details : ":"Sell details : ")+ " Please edit ");
				newPortfoliolabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfoliolabelLData.horizontalSpan = 2;
				newPortfoliolabel.setLayoutData(newPortfoliolabelLData);
				newPortfoliolabel.setBackground(MainGui.pOPUP_BG);
			}
			//if (transaction.getModtype().equals(TransactionType.NULL)) 
			{
				Button way1 = new Button(dialogShell, SWT.RADIO);
				way1.setText("buy");
				way1.setFont(MainGui.DEFAULTFONT);
				way1.setSelection(transaction.getModtype().equals(TransactionType.AIN));
				way1.addSelectionListener(new SelectionListener() {
					
					public void widgetDefaultSelected(SelectionEvent arg0) {
						// TODO Auto-generated method stub
					}
					
					public void widgetSelected(SelectionEvent arg0) {
						transaction.setModtype(TransactionType.AIN);
					}
				});
				Button way2 = new Button(dialogShell, SWT.RADIO);
				way2.setText("sell");
				way2.setFont(MainGui.DEFAULTFONT);
				way2.setSelection(transaction.getModtype().equals(TransactionType.AOUT));
				way2.addSelectionListener(new SelectionListener() {
					
					public void widgetDefaultSelected(SelectionEvent arg0) {
						// TODO Auto-generated method stub
					}
					
					public void widgetSelected(SelectionEvent arg0) {
						transaction.setModtype(TransactionType.AOUT);
					}
				});
			}
			{
				newPortfoliolabel = new Label(dialogShell, SWT.NONE);
				newPortfoliolabel.setText("Unit price : ");
				newPortfoliolabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfoliolabel.setLayoutData(newPortfoliolabelLData);
				newPortfoliolabel.setBackground(MainGui.pOPUP_BG);
			}
			{
				sharePriceText = new Text(dialogShell, SWT.BORDER);
				GridData newPortfolioTextLData = new GridData(GridData.FILL_HORIZONTAL);
				sharePriceText.setLayoutData(newPortfolioTextLData);
				sharePriceText.setText(transaction.getTransactionPrice().toString());
				sharePriceText.setFont(MainGui.DEFAULTFONT);
				sharePriceText.setEditable(true);
				sharePriceText.setEnabled(true);
				sharePriceText.addListener(SWT.DefaultSelection, new Listener() {
					public void handleEvent(Event e) {
						BigDecimal sharePrice = solveCalculation(sharePriceText.getText());
						transaction.setTransactionSharePrice(sharePrice.floatValue());
						refreshValues();
					}
				});
			}
			{
				newPortfoliolabel = new Label(dialogShell, SWT.NONE);
				newPortfoliolabel.setText("Transaction amount : ");
				newPortfoliolabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfoliolabel.setLayoutData(newPortfoliolabelLData);
				newPortfoliolabel.setBackground(MainGui.pOPUP_BG);
			}
			{
				transactionAmountText = new Text(dialogShell, SWT.BORDER);
				GridData newPortfolioTextLData = new GridData(GridData.FILL_HORIZONTAL);
				transactionAmountText.setLayoutData(newPortfolioTextLData);
				transactionAmountText.setText(transaction.amount().toString());
				transactionAmountText.setFont(MainGui.CONTENTFONT);
				transactionAmountText.addListener(SWT.DefaultSelection, new Listener() {
					public void handleEvent(Event e) {
						BigDecimal amount = solveCalculation(transactionAmountText.getText());
						Float quantity = new Float(quantityText.getText());
						Float sp = (quantity != 0)?amount.floatValue()/quantity:0;
						transaction.setTransactionSharePrice(sp);
						refreshValues();
					}
				});
			}
			{
				newPortfoliolabel = new Label(dialogShell, SWT.NONE);
				newPortfoliolabel.setText("Quantity : ");
				newPortfoliolabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfoliolabel.setLayoutData(newPortfoliolabelLData);
				newPortfoliolabel.setBackground(MainGui.pOPUP_BG);
			}
			{
				quantityText = new Text(dialogShell, SWT.BORDER);
				GridData newPortfolioTextLData = new GridData(GridData.FILL_HORIZONTAL);
				quantityText.setLayoutData(newPortfolioTextLData);
				quantityText.setText(transaction.getQuantity().toString());
				quantityText.setFont(MainGui.CONTENTFONT);
				quantityText.addListener(SWT.DefaultSelection, new Listener() {
					
					public void handleEvent(Event e) {
						Float quantity = new Float(quantityText.getText());
						Float amount = new Float(transactionAmountText.getText());
						Float sp = (quantity != 0)?amount/quantity:1;
						transaction.setQuantity(quantity);
						transaction.setTransactionSharePrice(sp);
						refreshValues();
					}
				});
			}
			{
				newPortfoliolabel = new Label(dialogShell, SWT.NONE);
				newPortfoliolabel.setText("Total money in for the line : ");
				newPortfoliolabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfoliolabel.setLayoutData(newPortfoliolabelLData);
				newPortfoliolabel.setBackground(MainGui.pOPUP_BG);
			}
			{
				amountInText = new Text(dialogShell, SWT.BORDER);
				GridData newPortfolioTextLData = new GridData(GridData.FILL_HORIZONTAL);
				amountInText.setLayoutData(newPortfolioTextLData);
				amountInText.setText(transaction.fullAmountIn().toString());
				amountInText.setFont(MainGui.CONTENTFONT);
				amountInText.setEditable(false);
				amountInText.setEnabled(true);
			}
			{
				newPortfoliolabel = new Label(dialogShell, SWT.NONE);
				newPortfoliolabel.setText("Total money out for the line : ");
				newPortfoliolabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfoliolabel.setLayoutData(newPortfoliolabelLData);
				newPortfoliolabel.setBackground(MainGui.pOPUP_BG);
			}
			{
				amountOutText = new Text(dialogShell, SWT.BORDER);
				GridData newPortfolioTextLData = new GridData(GridData.FILL_HORIZONTAL);
				amountOutText.setLayoutData(newPortfolioTextLData);
				amountOutText.setText(transaction.fullAmountOut().toString());
				amountOutText.setFont(MainGui.CONTENTFONT);
				amountOutText.setEditable(false);
				amountOutText.setEnabled(true);
			}
			{
				resetButton = new Button(dialogShell, SWT.CHECK);
				GridData newPortfollioValidateButtonLData = new GridData();
				newPortfollioValidateButtonLData.horizontalSpan = 2;
				newPortfollioValidateButtonLData.horizontalAlignment = GridData.BEGINNING;
				resetButton.setLayoutData(newPortfollioValidateButtonLData);
				resetButton.setText("Reset to new share?");
				resetButton.setFont(MainGui.DEFAULTFONT);
				resetButton.addSelectionListener(new SelectionListener() {
					
					public void widgetDefaultSelected(SelectionEvent arg0) {
						// TODO Auto-generated method stub
					}
					
					public void widgetSelected(SelectionEvent arg0) {
						refreshValues();
						
					}
				});
			}
			{
				cancelButton = new Button(dialogShell, SWT.PUSH);
				GridData newPortfollioValidateButtonLData = new GridData();
				newPortfollioValidateButtonLData.horizontalSpan = 1;
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
				newPortfollioValidateButton = new Button(dialogShell, SWT.PUSH);
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
						newTransactionValidateButtonMouseDown(evt);
					}
				});
			}
			dialogShell.layout();
			dialogShell.open();
			Display display = dialogShell.getDisplay();
			while (!dialogShell.isDisposed()) {
				try {
					if (!display.readAndDispatch()) display.sleep();
				} catch (RuntimeException e) {
					LOGGER.error("Error in New Portfolio Dialog Gui : "+e.getMessage(),e);
					LOGGER.debug("Error in New Portfolio Dialog Gui : ",e);
				} catch (Error e) {
					LOGGER.error("Error in  Gui : "+e.getMessage(),e);
					LOGGER.debug("Error in  Gui : ",e);
				}
			}
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
		LOGGER.debug("Transaction : " +this.transaction);
		dialogShell.close();
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
		sharePriceText.setText(transaction.getTransactionPrice().toString());
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
		//Pattern pattern = Pattern.compile("\\s*[\\*\\+-/]\\s*");
		Pattern pattern = Pattern.compile("\\s*[[-\\*][\\+/]]\\s*");
		scanner.useDelimiter(pattern);
		ArrayList<BigDecimal> nums = new ArrayList<BigDecimal>();
		while (scanner.hasNextBigDecimal()) {
			//System.out.print(scanner.nextBigDecimal().setScale(2, BigDecimal.ROUND_DOWN)+";");
			nums.add(scanner.nextBigDecimal().setScale(2, BigDecimal.ROUND_DOWN));
		}
		
		ArrayList<Character> ope = new ArrayList<Character>();
		Scanner scanner2 = new Scanner(formula);
		Pattern pattern2 = Pattern.compile("\\s*\\d+\\.?\\d*\\s*");
		scanner2.useDelimiter(pattern2);
		while (scanner2.hasNext()) {
			//System.out.print(scanner2.next()+";");
			ope.add(scanner2.next().charAt(0));
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
	


}
