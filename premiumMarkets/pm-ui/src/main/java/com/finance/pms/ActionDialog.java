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
		super(parent, (style == SWT.NONE)?SWT.DIALOG_TRIM|SWT.RESIZE:style, title, erreur, addMessage);
		this.actionTxt = actionTxt;
		this.action = action;
		
	}
	
	@Override
	public void open() {
		open(false);
	}

	@Override
	protected void validationButtonTxtAndAction() {

		valideButton1.setText(actionTxt);
		valideButton1.setFont(MainGui.DEFAULTFONT);
		valideButton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent evt) {
				doAction();
			}
		});
		valideButton1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent evt) {
				if (evt.keyCode == SWT.CR || evt.keyCode == SWT.SPACE) {
					doAction();
				}
			}
		});
	}
	

	protected void doAction() {
		
		valideButton1.setCapture(true);
		valideButton1.forceFocus();
		
		getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
		new Thread(new Runnable() {
			public void run() {
				ActionDialog.this.getParent().getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						try {
							action.action(valideButton1);
						} finally {
							if (!getParent().isDisposed()) getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
							validerbutton1MouseDown();
						}
					}
				});
			}
		}).start();
	}


	public void updateDialog(String title, String erreur, String addMessage, String actionTxt, ActionDialogAction action) {
		
		super.updateDialog(title, erreur, addMessage);
		
		this.actionTxt = actionTxt;
		this.action = action;
		valideButton1.setText(actionTxt);
		
		layout();
		
		getParent().setActive();
		valideButton1.setFocus();
	}


	public boolean sameDialog(String erreur, String addMessage, String actionTxt) {
		
		if (!super.sameDialog(erreur, addMessage))
			return false;
		if (this.actionTxt == null) {
			if (actionTxt != null)
				return false;
		} else if (!this.actionTxt.equals(actionTxt))
			return false;
		return true;
	}

	
	
}
