package com.finance.pms;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Shell;

public class ActionDialog extends UserDialog {
	
	protected ActionDialogAction action;
	String actionTxt;

	public ActionDialog(Shell parent, int style, String title, String erreur, String addMessage, String actionTxt, ActionDialogAction action) {
		super(parent, SWT.DIALOG_TRIM, title, erreur, addMessage);
		this.actionTxt = actionTxt;
		this.action = action;
		
	}

	@Override
	protected void validationButtonTxtAndAction() {
		
		valideButton1.setText(actionTxt);
		valideButton1.setFont(MainGui.DEFAULTFONT);
		valideButton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent evt) {
				action.action(valideButton1);
				validerbutton1MouseDown(evt);
			}
		});
		valideButton1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent evt) {
				if (evt.keyCode == SWT.CR || evt.keyCode == SWT.SPACE) {
					action.action(valideButton1);
					validerbutton1MouseDown(evt);
				}
			}
		});
		valideButton1.setFocus();
	}
	

}
