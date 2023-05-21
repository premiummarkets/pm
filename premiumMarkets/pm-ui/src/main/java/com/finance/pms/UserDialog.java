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
package com.finance.pms;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.RowLayout;
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


	protected Text errorTxt;
	private Text addMsgTxt;
	private String erreur;
	private String addMessage;
	protected Button valideButton;

	public UserDialog(Shell parent, String erreur, String addMessage) {
		super(new Shell(parent, SWT.SHELL_TRIM));
		this.getParent().setText(MainGui.APP_NAME + " - Warning");
		this.erreur = erreur;
		this.addMessage = addMessage;
	}

	protected UserDialog(Shell parent, String title, String erreur, String addMessage) {
		super(new Shell(parent, SWT.SHELL_TRIM));
		this.getParent().setText(title);
		this.erreur = erreur;
		this.addMessage = addMessage;
	}

	public void open() {

		try {

			RowLayout dialogShellLayout = new RowLayout(SWT.VERTICAL);
			dialogShellLayout.fill = true;
			dialogShellLayout.wrap = false;
			this.getParent().setLayout(dialogShellLayout);

			this.getParent().setBackground(MainGui.pOPUP_BG);

			{
				errorTxt = new Text(getParent(), SWT.WRAP);
				errorTxt.setFont(MainGui.DEFAULTFONT);
				errorTxt.setBackground(MainGui.pOPUP_BG);
				errorTxt.setEditable(false);
				if (erreur == null) {
					errorTxt.setVisible(false);
				} else {
					errorTxt.setText(cleanMsg(this.erreur, false));
				}
			}
			initAddMsg();
			initButton();

			layout();

			Point pt = getParent().getDisplay().getCursorLocation();
			int x = Math.max(0, Math.min(getParent().getDisplay().getBounds().width-getParent().getSize().x, pt.x-getParent().getSize().x-10));
			int y = Math.max(0, Math.min(getParent().getDisplay().getBounds().height-getParent().getSize().y, pt.y+10));
			getParent().setLocation(x, y);

			getParent().open();

			getParent().getDisplay().syncExec(new Runnable() {
				@Override
				public void run() {
					try {
						getParent().setActive();
					} catch (Exception e) {
						LOGGER.warn(e, e);
					}
				}
			});


		} catch (Exception e) {
			LOGGER.error("",e);
		}
	}

	private void initButton() {

		valideButton = new Button(getParent(), SWT.PUSH | SWT.CENTER);
		validationButtonTxtAndAction();

	}

	private void initAddMsg() {

		addMsgTxt = new Text(getParent(), SWT.WRAP| SWT.V_SCROLL);
		addMsgTxt.setFont(MainGui.DEFAULTFONT);
		addMsgTxt.setBackground(new Color(getParent().getDisplay(),(int) (MainGui.pOPUP_BG.getRed()*1.05),(int)(MainGui.pOPUP_BG.getGreen()*1.05),(int) (MainGui.pOPUP_BG.getBlue()*1.05)));
		addMsgTxt.setEditable(false);
		addMsgTxt.setCapture(false);
		if (addMessage == null) {
			addMsgTxt.setVisible(false);
		} else {
			addMsgTxt.setText(cleanMsg(this.addMessage, true));
		}

	}

	protected void layout() {

		if (errorTxt != null) {
			errorTxt.pack();
		}

		if (addMsgTxt != null) {
			addMsgTxt.pack();
			addMsgTxt.setSize(addMsgTxt.getSize().x+10, Math.max(addMsgTxt.getSize().y, 200));
		}

		getParent().pack();
		getParent().layout();

	}

	public void updateDialog(String title, String erreur, String addMessage) {

		if (title != null) this.getParent().setText(title);
		this.erreur = erreur;
		this.addMessage = addMessage;

		if (erreur != null && errorTxt != null)  {
			errorTxt.setText(cleanMsg(this.erreur, false));
			errorTxt.setVisible(true);
		} else {
			errorTxt.setText("");
			errorTxt.setVisible(false);
		}
		if (addMessage != null  && addMsgTxt != null) {
			addMsgTxt.setText(cleanMsg(this.addMessage, true));
			addMsgTxt.setVisible(true);
		} else {
			addMsgTxt.setText("");
			addMsgTxt.setVisible(false);
		}
		valideButton.setText("Ok");

		layout();

		getParent().setActive();
		valideButton.setFocus();

	}

	String cleanMsg(String message, Boolean addCR) {

		String cleanMessage = message;
		if (addCR) {
			cleanMessage = message.replaceAll("\\. ", ".\n");
		}
		cleanMessage = cleanMessage.replaceAll("[A-Za-z\\.]+Exception: ", "");
		cleanMessage = cleanMessage.replaceAll("\\[", "").replaceAll("\\]", "");

		return cleanMessage+"\n";
	}

	protected void validationButtonTxtAndAction() {

		valideButton.setText("Ok");
		valideButton.setFont(MainGui.DEFAULTFONT);
		valideButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent evt) {
				dispose();
			}
		});
		valideButton.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("Key released : " + e.keyCode + "," + e.character + "," + e.doit);
				if ((e.keyCode == SWT.CR || e.keyCode == SWT.SPACE)) {
					dispose();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("Key pressed : " + e.keyCode + "," + e.character + "," + e.doit);
			}

		});

	}

	protected void dispose() {
		getParent().dispose();
	}

	public boolean sameDialog(String erreur, String addMessage) {

		if (this.addMessage == null) {
			if (addMessage != null)
				return false;
		} else if (!this.addMessage.equals(addMessage))
			return false;
		if (this.erreur == null) {
			if (erreur != null)
				return false;
		} else if (!this.erreur.equals(erreur))
			return false;
		return true;
	}

}
