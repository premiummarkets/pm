package com.finance.pms;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Shell;

public class ActionDialog extends ErrorDialog {
	
	ActionDialogAction action;
	String actionTxt;

	public ActionDialog(Shell parent, int style, String erreur, String addMessage, String actionTxt, ActionDialogAction action) {
		super(parent, style, erreur, addMessage);
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
	}
	

}
