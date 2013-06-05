package com.finance.pms.portfolio.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.ActionDialog;
import com.finance.pms.ActionDialogAction;

public class ActionDialogForm extends ActionDialog {

	public Control control; 
	public String name;
	
	public ActionDialogForm(Shell shell,String buttonText,  String formText, String title) {
		super(shell, SWT.NONE, title, formText, null, buttonText, null);
	}

	public String getName() {
		return name;
	}
	

	public void setControl(Control control) {
		this.control = control;
		if (this.control instanceof Text) {
			this.control.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent evt) {
					if (evt.keyCode == SWT.CR) {
						action.action(valideButton1);
						validerbutton1MouseDown(evt);
					}
				}
			});
		}
	}
	
	public void setAction(ActionDialogAction actionDialogAction) {
		this.action = actionDialogAction;
	}

	
}