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
package com.finance.pms.admin.config;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.MainGui;
import com.finance.pms.MainPMScmd;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.DbInstaller;
import com.finance.pms.admin.NoPreparedDbException;
import com.finance.pms.admin.OtherException;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.MyDBConnection;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.SharesListId;
import com.finance.pms.datasources.web.Indice;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.portfolio.PortfolioMgr;

// TODO: Auto-generated Javadoc
/**
 * The Class DbSettings.
 * 
 * @author Guillaume Thoreton
 */
public class MarketsSettings extends Composite {

	protected static MyLogger LOGGER = MyLogger.getLogger(MarketsSettings.class);
	private final String dbNamePrefix = "initialdb_";
	private final String dbNameSuffix = ".tar.bz2";
	private static List<String> availabledb = new ArrayList<String>();
	
	static {
		availabledb.add("euronext-yahoo");
		availabledb.add("nasdaq-yahoo");
	}
	
	SharesListId currentMarketListProvider = SharesListId.valueOfCmd(MainPMScmd.getPrefs().get("quotes.listprovider", SharesListId.YAHOOINDICES.getSharesListCmdParam()));
	MarketQuotationProviders currentQuotationProvider = MarketQuotationProviders.valueOfCmd(MainPMScmd.getPrefs().get("quotes.provider", MarketQuotationProviders.YAHOO.getCmdParam()));
	Providers yahooProvider = Providers.getInstance(SharesListId.YAHOOINDICES.getSharesListCmdParam());

	Button shareListRadio[];
	CCombo quotationSourceCombo[];
	private Label yahooIndicesChooserLabel;
	private Text yahooIndicesChooserInput;
	private int sharesListSize;
	private final int CUSTOMLISTRADIOPOSITION = SharesListId.YAHOOINDICES.ordinal() - 1;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void main(String[] args) {
		
		
		SpringContext springContext = new SpringContext();
		springContext.setDataSource(args[0]);
		springContext.loadBeans("/connexions.xml", "/swtclients.xml", "/talibanalysisservices.xml");
		springContext.refresh();
		
		Shell shell = new Shell(new Display());
		shell.setLayout(new FillLayout());
		@SuppressWarnings("unused") MarketsSettings marketsSettings = new MarketsSettings(shell);
		shell.open();
		
		while (!shell.isDisposed()) {
			try {
				if (!shell.isDisposed() && !shell.getDisplay().readAndDispatch())
					shell.getDisplay().sleep();
			} catch (RuntimeException e) {
				LOGGER.error("Error in Market Settings Gui : " + e.getMessage(), e);
				LOGGER.debug("Error in Market Settings Gui : ", e);
			} catch (Error e) {
				LOGGER.error("Error in Event Gui : " + e.getMessage(), e);
				LOGGER.debug("Error in Event Gui : ", e);
			}
		}
	}

	/**
	 * Instantiates a new db settings.
	 * 
	 * @param shell
	 *            the shell
	 * 
	 * @author Guillaume Thoreton
	 */
	public MarketsSettings(Composite parent) {
	
		super(parent,SWT.RESIZE);
		this.init();
		this.pack();
	}

	/**
	 * Open.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void init() {
		try {
			
			GridLayout compositeLayout = new GridLayout();
			compositeLayout.numColumns = 2;
			compositeLayout.verticalSpacing = 15;
			
			this.setBackground(new Color(getDisplay(), 239, 183, 103));
			this.setLayout(compositeLayout);
			
			this.initGui();
		} catch (Exception e) {
			LOGGER.error("", e);
		}
	}

	/**
	 * Inits the gui.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void initGui() {
		
		GridData txtDescrLayoutData = new GridData(GridData.FILL_HORIZONTAL);
		txtDescrLayoutData.grabExcessHorizontalSpace = true;
		txtDescrLayoutData.grabExcessVerticalSpace = false;
		txtDescrLayoutData.horizontalSpan = 2;
		
		{
			Text tabDescr = new Text(this, SWT.WRAP);
			tabDescr.setLayoutData(txtDescrLayoutData);
			tabDescr.setBackground(new Color(this.getDisplay(), 239, 203, 152));
			tabDescr.setText("You can choose the list of shares you are interested in either \n\t by using yahoo indices \n\t or by selecting a full market place.");
			tabDescr.setFont(MainGui.DEFAULTFONT);
			
			//Init widget arrays
			GridData titleLayoutData = new GridData(GridData.FILL_HORIZONTAL);
			titleLayoutData.grabExcessHorizontalSpace = true;
			titleLayoutData.grabExcessVerticalSpace = false;
			titleLayoutData.horizontalSpan = 2;
			
			List<String> shareListNames = PortfolioMgr.getInstance().getPortfolioDAO().loadShareListNames();
			SharesListId[] shareListIds = SharesListId.values();
			sharesListSize = shareListIds.length - 1 + shareListNames.size();
			shareListRadio = new Button[sharesListSize];
			quotationSourceCombo = new CCombo[sharesListSize];
			
			Label yahooIndicesLabel = new Label(this, SWT.NONE);
			yahooIndicesLabel.setLayoutData(titleLayoutData);
			yahooIndicesLabel.setBackground(new Color(this.getDisplay(), 239, 203, 152));
			yahooIndicesLabel.setText("Custom your own share list using Yahoo indices : ");
			yahooIndicesLabel.setFont(MainGui.DEFAULTFONT);
			{
				//Custom form
				marketRow(
						CUSTOMLISTRADIOPOSITION, SharesListId.YAHOOINDICES, SharesListId.YAHOOINDICES.getSharesListCmdParam(), SharesListId.YAHOOINDICES.getDescription(), 
						SharesListId.YAHOOINDICES.name()+Indice.formatName(yahooProvider.getIndices()));
				yahooIndicesChooserInput = new Text(this, SWT.NONE);
				GridData inputIndicesLayouts = new GridData(GridData.FILL_HORIZONTAL);
				inputIndicesLayouts.horizontalSpan = 2;
				yahooIndicesChooserInput.setLayoutData(inputIndicesLayouts);
				String txt = Indice.formatToString(yahooProvider.getIndices());
				yahooIndicesChooserInput.setText(txt);
				yahooIndicesChooserInput.setFont(MainGui.CONTENTFONT);
				yahooIndicesChooserInput.addModifyListener(new ModifyListener() {
					
					public void modifyText(ModifyEvent e) {
						shareListRadio[CUSTOMLISTRADIOPOSITION].setSelection(true);
						Event event = new Event();
						event.type = SWT.MouseDown;
						shareListRadio[CUSTOMLISTRADIOPOSITION].notifyListeners(SWT.MouseDown, event);
					}
				});
				yahooIndicesChooserLabel = new Label(this, SWT.NONE);
				GridData desrcIndicesLayouts = new GridData(GridData.FILL_HORIZONTAL);
				desrcIndicesLayouts.horizontalSpan = 2;
				yahooIndicesChooserLabel.setLayoutData(desrcIndicesLayouts);
				yahooIndicesChooserLabel.setText("Your custom indice format is <YAHOOINDICE>:<MARKET>.\n " +
						"Where MARKET is like : "+ Arrays.asList(Market.values()) + ".\n" +
						"(ex : NDX:NASDAQ,NY:NYSE,FTLC:LSE,SBF250:EURONEXT... standing for nasdaq-100, nyse comp index, ftse 350 ...)\n" +
						"You can specify several indices commat separeted.");
				yahooIndicesChooserLabel.setFont(MainGui.DEFAULTFONT);
				yahooIndicesChooserLabel.setBackground(new Color(this.getDisplay(), 239, 203, 152));
				
				//Existing custom listings
				Label existingIndicesLabel = new Label(this, SWT.NONE);
				existingIndicesLabel.setLayoutData(titleLayoutData);
				existingIndicesLabel.setBackground(new Color(this.getDisplay(), 239, 203, 152));
				existingIndicesLabel.setText("Already created share lists based on Yahoo indices in your database : ");
				existingIndicesLabel.setFont(MainGui.DEFAULTFONT);
				int i = 0;
				for (String sharelistName : shareListNames) {
					if (sharelistName.contains(SharesListId.YAHOOINDICES.name()))  {
						marketRow(CUSTOMLISTRADIOPOSITION + ++i, SharesListId.YAHOOINDICES, SharesListId.YAHOOINDICES.getSharesListCmdParam(), sharelistName.replaceAll(SharesListId.YAHOOINDICES.name() + ",*", ""), sharelistName);
					} else {
						sharesListSize --;
					}
				}
			}
			
			//Other markets
			Label otherSourcesLabel = new Label(this, SWT.NONE);
			otherSourcesLabel.setLayoutData(titleLayoutData);
			otherSourcesLabel.setBackground(new Color(this.getDisplay(), 239, 203, 152));
			otherSourcesLabel.setText("Other predefined share lists based on market places : ");
			otherSourcesLabel.setFont(MainGui.DEFAULTFONT);
			for (int i = 0; i < shareListIds.length - 1; i++) {
				if (shareListIds[i + 1].equals(SharesListId.YAHOOINDICES)) continue;
				marketRow(i, 
						shareListIds[i + 1], 
						shareListIds[i + 1].getSharesListCmdParam(), 
						shareListIds[i + 1].getDescription(),
						shareListIds[i + 1].getSharesListCmdParam());
			}
			
			yahooIndicesChooserLabel.setEnabled(shareListRadio[CUSTOMLISTRADIOPOSITION].getSelection());
			yahooIndicesChooserInput.setEnabled(shareListRadio[CUSTOMLISTRADIOPOSITION].getSelection());
			
		}
		{
			{
				{
					final Button cancel = new Button(this, SWT.BORDER);
					GridData newPortfollioValidateButtonLData = new GridData();
					newPortfollioValidateButtonLData.horizontalSpan = 1;
					newPortfollioValidateButtonLData.horizontalAlignment = GridData.END;
					cancel.setLayoutData(newPortfollioValidateButtonLData);
					cancel.setText("Cancel");
					cancel.setFont(MainGui.DEFAULTFONT);
					cancel.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent arg0) {
							getShell().close();
						}
					});
				}
				{
					Button save = new Button(this, SWT.BORDER);
					GridData newPortfollioValidateButtonLData = new GridData();
					newPortfollioValidateButtonLData.horizontalSpan = 1;
					newPortfollioValidateButtonLData.horizontalAlignment = GridData.END;
					save.setLayoutData(newPortfollioValidateButtonLData);
					save.setText("Ok");
					save.setFont(MainGui.DEFAULTFONT);
					save.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent arg0) {
							
							final Button validate = ((Button) arg0.getSource());
							validate.setEnabled(false);
							//cancel.setEnabled(false);
							for (int k = 0; k < sharesListSize; k++) {
								if (shareListRadio[k].getSelection()) {
									SharesListId shareList = SharesListId.UNKNOWN;
									if (k < CUSTOMLISTRADIOPOSITION) {
										shareList = SharesListId.valueOfCmd(shareListRadio[k].getText().split(" ")[0]);
									} else if (k == CUSTOMLISTRADIOPOSITION) {
										yahooProvider.addIndices(Indice.parseString(yahooIndicesChooserInput.getText()), true);
										shareList = SharesListId.YAHOOINDICES;
									} else if (k > CUSTOMLISTRADIOPOSITION) {
										try {
											yahooProvider.addIndices(Indice.parseString(shareListRadio[k].getText().split(" ")[1].replace("(", "").replace(")", "")), true);
											shareList = SharesListId.YAHOOINDICES;
										} catch (ArrayIndexOutOfBoundsException e) {
											LOGGER.debug("This is not an indices list. " + shareListRadio[k].getText());
											shareList = SharesListId.valueOf(shareListRadio[k].getText().split(" ")[1].replace("(", "").replace(")", ""));
										}
									}
									//TODO :several share lists ie use the share lists in db instead of props.
									MainPMScmd.getPrefs().put("quotes.listprovider", shareList.getSharesListCmdParam());
									MainPMScmd.getPrefs().put("quotes.listproviderindices", Indice.formatToString(yahooProvider.getIndices()));
									MainPMScmd.getPrefs().put("quotes.provider", quotationSourceCombo[k].getText());
									try {
										MainPMScmd.getPrefs().flush();
									} catch (BackingStoreException e) {
										LOGGER.error(e,e);
									}
								}
							}
							getShell().dispose();
						}
					});
				}
			}
		}
		getShell().pack();
	}

	/**
	 * @param shareListSource
	 * @param shareListRadio
	 * @param quotationSourceCombo
	 * @param currentMarketListProvider
	 * @param currentQuotationProvider
	 * @param i
	 * @param shareListSource
	 * @param shareListName 
	 * @param complement
	 */
	private void marketRow(int i, SharesListId shareListSource, String cmdParam, String comment, String shareListName) {
		
		final int fi = i;
		
		//Radios
		shareListRadio[i] = new Button(this, SWT.RADIO);
		shareListRadio[i].setText(cmdParam + " (" + comment + ")");
		shareListRadio[i].setFont(MainGui.DEFAULTFONT);
		shareListRadio[i].addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				super.widgetSelected(arg0);
				quotationSourceCombo[fi].setEnabled(((Button) arg0.getSource()).getSelection());
				if (fi == CUSTOMLISTRADIOPOSITION) {
					yahooIndicesChooserLabel.setEnabled(((Button) arg0.getSource()).getSelection());
					yahooIndicesChooserInput.setEnabled(((Button) arg0.getSource()).getSelection());
				}
			}
		});
		
		String nameExtension = Indice.formatName(yahooProvider.getIndices());
		boolean isRadioCurrentShareList = 
				(SharesListId.YAHOOINDICES.equals(currentMarketListProvider) && shareListName.equals(currentMarketListProvider.name()+nameExtension)) ||
				!SharesListId.YAHOOINDICES.equals(currentMarketListProvider) && shareListSource.equals(currentMarketListProvider);
		
		if (isRadioCurrentShareList) {
			shareListRadio[i].setSelection(true);
			shareListRadio[i].setFocus();
			if (i != CUSTOMLISTRADIOPOSITION ) shareListRadio[CUSTOMLISTRADIOPOSITION].setSelection(false);
		} else {
			shareListRadio[i].setSelection(false);
		}
		
		//Combos
		quotationSourceCombo[i] = new CCombo(this, SWT.READ_ONLY);
		for (int j = 0, n = MarketQuotationProviders.values().length; j < n; j++) {
			quotationSourceCombo[i].add(MarketQuotationProviders.values()[j].getCmdParam());
			quotationSourceCombo[i].select(0);
		}
		
		if (isRadioCurrentShareList) {
			for (int j = 0; j < MarketQuotationProviders.values().length; j++) {
				if (MarketQuotationProviders.values()[j].equals(currentQuotationProvider)) {
					quotationSourceCombo[i].select(j);
				}
			}
		}
		quotationSourceCombo[i].pack();
		quotationSourceCombo[i].setEnabled(shareListRadio[i].getSelection());
	}

	/**
	 * Check pre loaded db.
	 * 
	 * @param marketListP
	 *            the market list p
	 * @param database
	 *            the database
	 * @param marketQuoteP
	 *            the market quote p
	 * @param fi
	 *            the fi
	 * 
	 * @author Guillaume Thoreton
	 */
	@SuppressWarnings("unused")
	private Boolean checkPreLoadedDB(String marketListP, final Button[] database, String marketQuoteP, final int fi) {
		if (MarketsSettings.availabledb.contains(marketListP + "-" + marketQuoteP)) {
			database[fi].setEnabled(true);
			database[fi].setSelection(true);
		} else {
			database[fi].setEnabled(false);
			database[fi].setSelection(false);
		}
		return MarketsSettings.availabledb.contains(marketListP + "-" + marketQuoteP);
	}

	@SuppressWarnings("unused")
	private void truncateDB() {
		MyDBConnection conn = DataSource.getConnectionFromPool();
		DbInstaller dbInstaller = new DbInstaller();
		String expDir = System.getProperty("installdir");
		File destFolder = new File(expDir);
		try {
			dbInstaller.importDB(destFolder, DbInstaller.extractDir, conn.getConn());
		} catch (SQLException e) {
			LOGGER.error("Problem acces db", e);
		} catch (OtherException e) {
			LOGGER.error("Other error", e);
		} finally {
			DataSource.realesePoolConnection(conn);
		}
	}

	/**
	 * @param markets
	 * @param combo
	 * @param index
	 */
	@SuppressWarnings("unused")
	private void downloadAlreadyMadeDB(final SharesListId[] markets, final CCombo[] combo, int index) {
		final int finalIndex = index;
		final String ccomboTxt = combo[finalIndex].getText();
		Thread t = new Thread() {
			@Override
			public void run() {

				MyDBConnection conn = DataSource.getConnectionFromPool();
				try {
					String dbName = markets[finalIndex + 1].getSharesListCmdParam() + "-" + ccomboTxt;
					//http://heanet.dl.sourceforge.net/sourceforge/pmsqueak/initialdb-nasdaq-yahoo.jar
					//http://downloads.sourceforge.net/pmsqueak/initialdb-nasdaq-yahoo.jar?use_mirror=osdn
					URL initialdbURL = new URL("http://downloads.sourceforge.net/pmsqueak/initialdb-" + dbName + ".jar?use_mirror=osdn");
					initialdbURL.openConnection().setConnectTimeout(5);
					
					try {
						DbInstaller dbInstaller = new DbInstaller();
			
						String expDir = System.getProperty("installdir");
						LOGGER.debug("unpackaging in :" + expDir);
						File destFolder = new File(expDir);
						destFolder.mkdir();
						dbInstaller.extractDB(initialdbURL, dbNamePrefix + dbName + dbNameSuffix, destFolder);
						dbInstaller.importDB(destFolder, DbInstaller.extractDir + "_" + dbName, conn.getConn());
					} catch (NoPreparedDbException e) {
						LOGGER.debug("No preset db for : " + initialdbURL, e);
					} catch (OtherException e) {
						LOGGER.error("Problem updating db", e);
					}
				} catch (IOException e) {
					LOGGER.error("Problem downloading db", e);
				} catch (SQLException e) {
					LOGGER.error("Problem connecting db", e);
				} finally {
					DataSource.realesePoolConnection(conn);
					//FIXME
//					this.getDisplay().syncExec(new Runnable() {
//						public void run() {
//							getShell().setCursor(new Cursor(this.getDisplay(), SWT.CURSOR_ARROW));
//							getShell().close();
//							getShell().dispose();
//						}
//					});
				}
			}
		};
		t.start();
		while (!this.isDisposed() && t.isAlive()) {
			try {
				if (!this.getDisplay().readAndDispatch())
					this.getDisplay().sleep();
			} catch (RuntimeException e) {
				LOGGER.error("Error in MarketSettings Gui : " + e.getMessage(), e);
				LOGGER.debug("Error in MarketSettings Gui : ", e);
			} catch (Error e) {
				LOGGER.error("Error in  Gui : " + e.getMessage(), e);
				LOGGER.debug("Error in  Gui : ", e);
			}
		}
	}

	/**
	 * Inits the data.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void initData() {
	}
}
