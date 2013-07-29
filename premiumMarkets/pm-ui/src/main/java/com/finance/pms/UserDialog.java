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
package com.finance.pms;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.admin.install.logging.MyLogger;

/**
 * The Class ErrorDialog.
 * 
 * @author Guillaume Thoreton
 */
public class UserDialog extends Dialog {
	

	protected static MyLogger LOGGER = MyLogger.getLogger(UserDialog.class);


	private Text errorLabel1;
	private Text textArea;
	private String erreur;
	private String addMessage;
	protected Button valideButton1;
	
	private Boolean isOk = false;


	public UserDialog(Shell parent, String erreur, String addMessage) {
		super(new Shell(new Shell(parent,SWT.ON_TOP), SWT.SHEET | SWT.RESIZE));
		//super(new Shell(new Shell(SWT.ON_TOP), SWT.SHEET | SWT.RESIZE));
		this.getParent().setText("Premium Markets - Warning");
		this.erreur = erreur;
		this.addMessage = addMessage;
	}
	
	public UserDialog(Shell parent, int style, String erreur, String addMessage) {
		super(new Shell(new Shell(parent,SWT.ON_TOP), style));
		//super(new Shell(new Shell(SWT.ON_TOP),style));
		this.getParent().setText("Premium Markets - Warning");
		this.erreur = erreur;
		this.addMessage = addMessage;
	}
	
	protected UserDialog(Shell parent, int style, String title, String erreur, String addMessage) {
		super(new Shell(new Shell(parent,SWT.ON_TOP), style));
		//super(new Shell(new Shell(SWT.ON_TOP), style));
		this.getParent().setText(title);
		this.erreur = erreur;
		this.addMessage = addMessage;
	
	}
	
	public void open() {
		open(true);
	}

	/**
	 * Open.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void open(Boolean focusOnAckButton) {
		try {

			GridLayout dialogShellLayout = new GridLayout();
			this.getParent().setLayout(dialogShellLayout);
			this.getParent().setBackground(MainGui.pOPUP_BG);
			
			if (erreur != null) {
				errorLabel1 = new Text(getParent(), SWT.WRAP);
				GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
				errorLabel1.setLayoutData(layoutData);
				errorLabel1.setFont(MainGui.DEFAULTFONT);
				errorLabel1.setBackground(MainGui.pOPUP_BG);
				errorLabel1.setText(cleanMsg(this.erreur, true));
				errorLabel1.setEditable(false);
				
			}
			if (addMessage != null) {
				textArea = new Text(getParent(), SWT.WRAP| SWT.V_SCROLL);
				textArea.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
				textArea.setFont(MainGui.DEFAULTFONT);
				textArea.setBackground(MainGui.pOPUP_BG);
				textArea.setEditable(false);
				textArea.setText(cleanMsg(this.addMessage, false));
			}
			{
				valideButton1 = new Button(getParent(), SWT.PUSH | SWT.CENTER);
				GridData validerbutton1LData = new GridData(SWT.CENTER, SWT.BOTTOM, false, false);
				valideButton1.setLayoutData(validerbutton1LData);
				validationButtonTxtAndAction();
			}
			
			if (errorLabel1 != null) {
				errorLabel1.pack();
				if (errorLabel1.getSize().y < 100) ((GridData)errorLabel1.getLayoutData()).heightHint = 100;
			}
			
			getParent().pack();
			if (textArea != null) {
				Point computeSize = textArea.computeSize(SWT.DEFAULT, SWT.DEFAULT);
				textArea.setSize(textArea.getSize().x, Math.max(computeSize.y, 200));
			}
			getParent().layout();
			
			
			getParent().open();
			getParent().setActive();
			if (focusOnAckButton) valideButton1.setFocus();
			
		} catch (Exception e) {
			LOGGER.error("",e);
		}
	}

	private String cleanMsg(String message, Boolean noCR) {
	
		String cleanMessage = message;
		if (!noCR) cleanMessage = message.replaceAll("\\. ", ".\n"); //else cleanMessage = message.replaceAll("\\.\n", ". ").replaceAll("\n", ". ");
		cleanMessage = cleanMessage.replaceAll("[A-Za-z\\.]+Exception: ", "");
		cleanMessage = cleanMessage.replaceAll("\\[", "").replaceAll("\\]", "");
		
		return cleanMessage;
	}

	protected void validationButtonTxtAndAction() {
		valideButton1.setText("Ok");
		valideButton1.setFont(MainGui.DEFAULTFONT);
		valideButton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent evt) {
				validerbutton1MouseDown(evt);
			}
		});
		valideButton1.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("Key released : "+e.keyCode+","+e.character+","+e.doit);
				if ((e.keyCode == SWT.CR || e.keyCode == SWT.SPACE)) {
					validerbutton1MouseDown(e);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("Key pressed : "+e.keyCode+","+e.character+","+e.doit);
			}
		
		});
			
	}

	/**
	 * Validerbutton1 mouse down.
	 * 
	 * @param evt the evt
	 * 
	 * @author Guillaume Thoreton
	 */
	protected void validerbutton1MouseDown(TypedEvent evt) {
		isOk = true;
		getParent().dispose();
	}

	public Boolean getIsOk() {
		return isOk;
	}

}
