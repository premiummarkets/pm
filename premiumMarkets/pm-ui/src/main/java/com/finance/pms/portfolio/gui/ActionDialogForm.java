package com.finance.pms.portfolio.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.finance.pms.ActionDialog;
import com.finance.pms.ActionDialogAction;

public class ActionDialogForm extends ActionDialog {

	public Control control; 
	public String name;
	
	public ActionDialogForm(Shell shell,String buttonText,  String formText, String title) {
		super(shell, SWT.NONE, title, formText, null, buttonText, null);
		//this.name = title;
	}

	public String getName() {
		return name;
	}
	

	public void setControl(Control control) {
		this.control = control;
	}
	
	public void setAction(ActionDialogAction actionDialogAction) {
		this.action = actionDialogAction;
	}

	
}