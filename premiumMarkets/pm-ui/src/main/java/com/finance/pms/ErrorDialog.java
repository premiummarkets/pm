/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
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
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.admin.install.logging.MyLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class ErrorDialog.
 * 
 * @author Guillaume Thoreton
 */
public class ErrorDialog extends Dialog {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(ErrorDialog.class);

	/** The dialog shell. */
	//private Shell dialogShell;

	/** The Validerbutton1. */
	private Button valideButton1;

	/** The Errorlabel1. */
	private Label errorLabel1;
	
	private Text textArea;

	/** The erreur. */
	private String erreur;
	private String addMessage;
	
	private Boolean isOk = false;
	
	
//	private static List<Field> fields = new ArrayList<Field>();
//	private int cpt = 0;
//	
//	static {
//		Field[] fields = SWT.class.getFields();
//		for (Field swtField : fields) {
//			if (swtField.getName().contains("CURSOR_")) {
//				ErrorDialog.fields.add(swtField);
//			}
//		}
//	}

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void main(String[] args) {
		try {
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			ErrorDialog inst = new ErrorDialog(shell, SWT.NULL, "Hi,\n This data update can take up to 20 minutes against several hours if no data base is pre selected.\n Cheers!\n", null);
			inst.open();
		} catch (Exception e) {
			LOGGER.debug("",e);
		}
	}

	/**
	 * Instantiates a new error dialog.
	 * 
	 * @param parent the parent
	 * @param style the style
	 * @param erreur the erreur
	 * @param addMessage TODO
	 * @author Guillaume Thoreton
	 */
	public ErrorDialog(Shell parent, int style, String erreur, String addMessage) {
		super(new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE));
		this.erreur = erreur;
		this.addMessage = addMessage;
	}

	/**
	 * Open.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void open() {
		try {

			GridLayout dialogShellLayout = new GridLayout();
			dialogShellLayout.verticalSpacing = 20;
			this.getParent().setLayout(dialogShellLayout);
			this.getParent().setBackground(new Color(getParent().getDisplay(),239, 183,103));
			{
				errorLabel1 = new Label(getParent(), SWT.WRAP);
				errorLabel1.setText(this.erreur);
				errorLabel1.setFont(MainGui.DEFAULTFONT);
				GridData Errorlabel1LData = new GridData();
				Errorlabel1LData.verticalAlignment = GridData.FILL;
				Errorlabel1LData.horizontalAlignment = GridData.FILL;
				Errorlabel1LData.grabExcessVerticalSpace = true;
				Errorlabel1LData.grabExcessHorizontalSpace = true;
				errorLabel1.setLayoutData(Errorlabel1LData);
				errorLabel1.setAlignment(SWT.CENTER);
				errorLabel1.setBackground(new Color(getParent().getDisplay(),239, 183,103));
			}
			if (addMessage != null) {
				textArea = new Text(getParent(), SWT.WRAP|SWT.V_SCROLL);
				textArea.setText(this.addMessage);
				textArea.setFont(MainGui.DEFAULTFONT);
			
				GridData Errorlabel1LData = new GridData();
				Errorlabel1LData.verticalAlignment = GridData.FILL;
				Errorlabel1LData.horizontalAlignment = GridData.FILL;
				Errorlabel1LData.grabExcessVerticalSpace = true;
				Errorlabel1LData.grabExcessHorizontalSpace = true;
				textArea.setLayoutData(Errorlabel1LData);
				textArea.setBackground(new Color(getParent().getDisplay(),239, 183,103));
			}
			{
				valideButton1 = new Button(getParent(), SWT.PUSH | SWT.CENTER);
				GridData Validerbutton1LData = new GridData(GridData.FILL_HORIZONTAL);
				Validerbutton1LData.horizontalAlignment = GridData.CENTER;
				valideButton1.setLayoutData(Validerbutton1LData);
				valideButton1.setText("Ok");
				valideButton1.setFont(MainGui.DEFAULTFONT);
				valideButton1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
						Validerbutton1MouseDown(evt);
//						try {
//							Field field = fields.get(cpt++);
//							int cursor = field.getInt(null);
//							System.out.println("Cursor : "+field.getName());
//							getParent().setCursor(new Cursor(getParent().getDisplay(), cursor));
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
					}
				});
			}
			// dialogShell.layout();
			getParent().pack();
			getParent().open();
			Display display = getParent().getDisplay();
			
			while (!getParent().isDisposed()) {
				try {
					if (!display.readAndDispatch())
						display.sleep();
				} catch (RuntimeException e) {
					LOGGER.error("Error in Error dialog Gui : "+e.getMessage(),e);
					LOGGER.debug("Error in Error Dialog Gui : ",e);
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
	 * Validerbutton1 mouse down.
	 * 
	 * @param evt the evt
	 * 
	 * @author Guillaume Thoreton
	 */
	private void Validerbutton1MouseDown(MouseEvent evt) {
		LOGGER.debug("Validerbutton1.mouseDown, event=" + evt);
		isOk = true;
		getParent().dispose();
	}

	public Boolean getIsOk() {
		return isOk;
	}
	
	

}
