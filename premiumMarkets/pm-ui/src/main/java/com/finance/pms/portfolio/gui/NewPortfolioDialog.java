/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.MainGui;
import com.finance.pms.admin.install.logging.MyLogger;



// TODO: Auto-generated Javadoc
/**
 * The Class NewPortfolioDialog.
 * 
 * @author Guillaume Thoreton
 */
public class NewPortfolioDialog extends org.eclipse.swt.widgets.Dialog {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(NewPortfolioDialog.class);

	/** The dialog shell. */
	private Shell dialogShell;
	
	/** The new portfoliolabel. */
	private Label newPortfoliolabel;
	
	/** The new portfollio validate button. */
	private Button newPortfollioValidateButton;
	
	/** The new portfolio text. */
	private Text newPortfolioText;

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
			NewPortfolioDialog inst = new NewPortfolioDialog(shell, SWT.NULL);
			inst.open();
		} catch (Exception e) {
			LOGGER.debug("",e);
		}
	}

	/**
	 * Instantiates a new new portfolio dialog.
	 * 
	 * @param parent the parent
	 * @param style the style
	 * 
	 * @author Guillaume Thoreton
	 */
	public NewPortfolioDialog(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Open.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void open() {
		try {
			Shell parent = getParent();
			dialogShell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
			{
				//Register as a resource user - SWTResourceManager will handle the obtaining and disposing of resources
				//SWTResourceManager.registerResourceUser(dialogShell);
			}

			GridLayout dialogShellLayout = new GridLayout();
			//dialogShellLayout.marginTop = 10;
			dialogShellLayout.verticalSpacing = 30;
			dialogShell.layout();			
			dialogShell.setLayout(dialogShellLayout);
			dialogShell.pack();
			dialogShell.setSize(200, 200);
			dialogShell.setBackground(new Color(dialogShell.getDisplay(),239, 183,103));
			{
				newPortfoliolabel = new Label(dialogShell, SWT.BORDER);
				newPortfoliolabel.setText("New portfolio : ");
				newPortfoliolabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfoliolabel.setLayoutData(newPortfoliolabelLData);
				newPortfoliolabel.setBackground(new Color(dialogShell.getDisplay(),239, 183,103));
			}
			{
				newPortfolioText = new Text(dialogShell, SWT.BORDER);
				GridData newPortfolioTextLData = new GridData(GridData.FILL_HORIZONTAL);
				newPortfolioText.setLayoutData(newPortfolioTextLData);
				newPortfolioText.setText("Enter name");
				newPortfolioText.setFont(MainGui.CONTENTFONT);
			}
			{
				newPortfollioValidateButton = new Button(dialogShell, SWT.BORDER);
				GridData newPortfollioValidateButtonLData = new GridData();
				newPortfollioValidateButtonLData.horizontalAlignment = GridData.END;
				newPortfollioValidateButton.setLayoutData(newPortfollioValidateButtonLData);
				newPortfollioValidateButton.setText("Validate");
				newPortfollioValidateButton.setFont(MainGui.DEFAULTFONT);
				newPortfollioValidateButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
						//newPortfollioValidateButtonMouseDown(evt);
					}
				});
			}
			dialogShell.layout();
			dialogShell.open();
			Display display = dialogShell.getDisplay();
			while (!dialogShell.isDisposed()) {
				try {
					if (!display.readAndDispatch())
						display.sleep();
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
	
//	/**
//	 * New portfollio validate button mouse down.
//	 * 
//	 * @param evt the evt
//	 * 
//	 * @author Guillaume Thoreton
//	 */
//	private void newPortfollioValidateButtonMouseDown(MouseEvent evt) {
//		
//		String name = newPortfolioText.getText();
//		PortfolioMgr.getInstance().addPortfolio(new Portfolio(name));
//		dialogShell.close();
//		PortfolioComposite.showGUI();
//	}

}
