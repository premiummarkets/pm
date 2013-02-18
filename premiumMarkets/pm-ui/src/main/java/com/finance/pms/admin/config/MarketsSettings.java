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
package com.finance.pms.admin.config;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
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

import com.finance.pms.ErrorDialog;
import com.finance.pms.MainGui;
import com.finance.pms.MainPMScmd;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.DbInstaller;
import com.finance.pms.admin.NoPreparedDbException;
import com.finance.pms.admin.OtherException;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.MyDBConnection;
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
	
	SharesListId currentMarketShareListId = SharesListId.valueOfCmd(MainPMScmd.getPrefs().get("quotes.listprovider", SharesListId.YAHOOINDICES.getSharesListCmdParam()));
	String currentMarketListIndices = MainPMScmd.getPrefs().get("quotes.listproviderindices", "");
	String currentShareListName = (currentMarketListIndices.isEmpty())?currentMarketShareListId.name():currentMarketShareListId.name()+","+currentMarketListIndices;
	MarketQuotationProviders currentQuotationProvider = MarketQuotationProviders.valueOfCmd(MainPMScmd.getPrefs().get("quotes.provider", MarketQuotationProviders.YAHOO.getCmdParam()));
	
	String selectedMarketListIndices = currentMarketListIndices;
	SharesListId  selectedMarketShareListId = currentMarketShareListId;
	String selectedQuotationProvider = currentQuotationProvider.getCmdParam();

	Button shareListRadio[];
	CCombo quotationSourceCombo[];
	private Label[] indicesChooserLabels;
	private Text[] indicesChooserInput;
	private int sharesListSize;

	private final Font biggerFont;
	private List<String> shareListNames;
	{
		FontData[] defaultFontData = MainGui.DEFAULTFONT.getFontData();
		defaultFontData[0].setHeight(11);
		biggerFont = new Font(getDisplay(), defaultFontData);
	}
	
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


	private String checkDefaultIndices(List<String> shareListNames, SharesListId sharesListId) {

		Set<Indice> indices = new TreeSet<Indice>();
		if (currentMarketShareListId.equals(sharesListId) && !currentMarketListIndices.isEmpty()) {
			return currentMarketListIndices;
		} else {
			for (String shareListName : shareListNames) {
				if (shareListName.contains(sharesListId.name())) {
					indices.addAll(Indice.parseString(shareListName));
				}
			} 
		}
		
		return Indice.formatSet(indices);

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
			tabDescr.setText(
					"Choose the list of shares you are interested in.\n" +
					"\tSome lists are based on indices components like yahoo indices or NSE indices. You can select several indices and build you own list.\n" +
					"\tOthers contains a full market place.\n" +
					"Note that the only available quotation provider is yahoo for bulk selection.\n" +
					"You can select other providers when adding individual stocks in the portfolio manager interface.");
			tabDescr.setFont(MainGui.DEFAULTFONT);
			
			//Init widget arrays
			GridData titleLayoutData = new GridData(GridData.FILL_HORIZONTAL);
			titleLayoutData.grabExcessHorizontalSpace = true;
			titleLayoutData.grabExcessVerticalSpace = false;
			titleLayoutData.horizontalSpan = 2;
			
			shareListNames = PortfolioMgr.getInstance().getPortfolioDAO().loadShareListNames();
			SharesListId[] shareListIds = SharesListId.values();
			sharesListSize = shareListIds.length + shareListNames.size();
			indicesChooserInput = new Text[sharesListSize];
			indicesChooserLabels = new Label[sharesListSize];
			shareListRadio = new Button[sharesListSize];
			quotationSourceCombo = new CCombo[sharesListSize];
			
			{
				int radioIdx = 0;
				for (final SharesListId shareListId : SharesListId.values()) {
					
					if (shareListId.equals(SharesListId.UNKNOWN)) continue;
					
					final int fRadioIdx = radioIdx;

					if (shareListId.getIsIndicesComposite()) {//composite => Custom form + indices list
					
							//custom text area radio 
							String customIndices = checkDefaultIndices(shareListNames,shareListId);
							Label customIndicesLabel = new Label(this, SWT.NONE);
							customIndicesLabel.setLayoutData(titleLayoutData);
							customIndicesLabel.setBackground(new Color(this.getDisplay(), 239, 203, 152));
							customIndicesLabel.setText("Create a new share list using "+shareListId.getDescription()+" : ");
							customIndicesLabel.setFont(biggerFont);
							marketRow(radioIdx, shareListId, shareListId.getSharesListCmdParam(), customIndices, new RadioAction() {

								@Override
								public void action() {
									selectedMarketShareListId = shareListId;
									selectedMarketListIndices = indicesChooserInput[fRadioIdx].getText().trim().toUpperCase();
									selectedQuotationProvider = quotationSourceCombo[fRadioIdx].getText();
								}
								
							});	
							
							//Custom form
							indicesChooserInput[radioIdx] = new Text(this, SWT.NONE);
							GridData inputIndicesLayouts = new GridData(GridData.FILL_HORIZONTAL);
							inputIndicesLayouts.horizontalSpan = 2;
							indicesChooserInput[radioIdx].setLayoutData(inputIndicesLayouts);
							indicesChooserInput[radioIdx].setText(customIndices);
							indicesChooserInput[radioIdx].setFont(MainGui.CONTENTFONT);
							indicesChooserInput[radioIdx].addModifyListener(new ModifyListener() {
								
								public void modifyText(ModifyEvent e) {
									shareListRadio[fRadioIdx].setSelection(true);
									Event event = new Event();
									event.type = SWT.MouseDown;
									shareListRadio[fRadioIdx].notifyListeners(SWT.MouseDown, event);
				
								}
								
							});
							indicesChooserInput[radioIdx].addFocusListener(new FocusListener() {
								
								@Override
								public void focusLost(FocusEvent e) {
									selectedMarketListIndices = indicesChooserInput[fRadioIdx].getText().trim().toUpperCase();
									
								}
								
								@Override
								public void focusGained(FocusEvent e) {
									selectedMarketListIndices = indicesChooserInput[fRadioIdx].getText().trim().toUpperCase();
									
								}
							});
							indicesChooserInput[radioIdx].addModifyListener(new ModifyListener() {
								
								@Override
								public void modifyText(ModifyEvent e) {
									selectedMarketListIndices = indicesChooserInput[fRadioIdx].getText().trim().toUpperCase();
									
								}
							});
							indicesChooserInput[radioIdx].setEnabled(shareListRadio[radioIdx].getSelection());
							indicesChooserLabels[radioIdx] = new Label(this, SWT.NONE);
							GridData desrcIndicesLayouts = new GridData(GridData.FILL_HORIZONTAL);
							desrcIndicesLayouts.horizontalSpan = 2;
							indicesChooserLabels[radioIdx].setLayoutData(desrcIndicesLayouts);
							indicesChooserLabels[radioIdx].setText(shareListId.getComment());
							indicesChooserLabels[radioIdx].setFont(MainGui.DEFAULTFONT);
							indicesChooserLabels[radioIdx].setBackground(new Color(this.getDisplay(), 239, 203, 152));
							indicesChooserLabels[radioIdx].setEnabled(shareListRadio[radioIdx].getSelection());
						
						
							//Existing custom indices
							Label existingIndicesLabel = new Label(this, SWT.NONE);
							existingIndicesLabel.setLayoutData(titleLayoutData);
							existingIndicesLabel.setBackground(new Color(this.getDisplay(), 239, 203, 152));
							existingIndicesLabel.setText("Existing composite share lists based on "+shareListId.getDescription()+" in your database : ");
							existingIndicesLabel.setFont(biggerFont);
							int cpt = 0;
							for (String sharelistName : shareListNames) {
								
								if (sharelistName.contains(shareListId.name()))  {
									
									if (cpt ==0) radioIdx++;
									final int findiceRadioIx = radioIdx;
									//indice radio 
									String[] split = sharelistName.split(",");
									String existingIndices = (split.length > 1)?split[1]:"";
									marketRow(radioIdx, shareListId, sharelistName, existingIndices, new RadioAction() {

										@Override
										public void action() {
											selectedMarketShareListId = shareListId;
											selectedMarketListIndices = shareListRadio[findiceRadioIx].getText().split(" ")[1].replace("(", "").replace(")", "");
											selectedQuotationProvider = quotationSourceCombo[findiceRadioIx].getText();
											
										}
										
									});
									radioIdx++;
									cpt++;
								}
							}
							if (cpt == 0) {
								Label noneLabel = new Label(this, SWT.NONE);
								noneLabel.setLayoutData(titleLayoutData);
								noneLabel.setBackground(new Color(this.getDisplay(), 239, 203, 152));
								noneLabel.setText("None. You can create a list based on "+shareListId.getDescription()+" using the text box above.");
								noneLabel.setFont(MainGui.DEFAULTFONT);
							}
							
					} else {
						
						//Other markets
						Label otherSourcesLabel = new Label(this, SWT.NONE);
						otherSourcesLabel.setLayoutData(titleLayoutData);
						otherSourcesLabel.setBackground(new Color(this.getDisplay(), 239, 203, 152));
						otherSourcesLabel.setText("Predefined share list based on "+shareListId.getDescription()+" : ");
						otherSourcesLabel.setFont(biggerFont);
						
						marketRow(radioIdx, shareListId, shareListId.getSharesListCmdParam(), "", new RadioAction() {

							@Override
							public void action() {
								selectedMarketShareListId = shareListId;
								selectedMarketListIndices = "";
								selectedQuotationProvider = quotationSourceCombo[fRadioIdx].getText();
								
							}
							
						});
					}
					
					radioIdx++;
				}
			}
			
		}
		{
			{
				{
					final Button cancel = new Button(this, SWT.PUSH);
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
					Button save = new Button(this, SWT.PUSH);
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
							
							if (selectedMarketShareListId.getIsIndicesComposite()) {
								if (selectedMarketListIndices.isEmpty()) {
									selectedMarketListIndices = checkDefaultIndices(shareListNames, selectedMarketShareListId);
								}
								if (selectedMarketListIndices.isEmpty()) {
									ErrorDialog dialog = new ErrorDialog(getShell(), 0, "You have selected a list based on "+selectedMarketShareListId.getDescription()+".\nYou must specify one or more indices to include.", null);
									dialog.open();
									validate.setEnabled(true);
									return;
								}
								Providers provider = Providers.getInstance(selectedMarketShareListId.getSharesListCmdParam());
								provider.addIndices(Indice.parseString(selectedMarketListIndices), true);
							}
							
							SharesListId.updatePrefs(selectedMarketShareListId.getSharesListCmdParam(), selectedMarketListIndices,  selectedQuotationProvider);
							
							getShell().dispose();
						}


					});
				}
			}
		}
		getShell().pack();
	}
	
	interface RadioAction {
		
		public void action();
		
	}

	/**
	 * @param shareListId
	 * @param shareListRadio
	 * @param quotationSourceCombo
	 * @param currentMarketShareListId
	 * @param currentQuotationProvider
	 * @param i
	 * @param shareListId
	 * @param shareListName 
	 * @param complement
	 */
	private void marketRow(int radioIdx, SharesListId shareListId, String shareListName, String indices, final RadioAction radioAction) {
		
		final int fRadioIdx = radioIdx;
		
		//Radios
		shareListRadio[radioIdx] = new Button(this, SWT.RADIO | SWT.LEAD);
		String[] sharelistNameSplit = shareListName.split(",");
		shareListRadio[radioIdx].setText(shareListId.getSharesListCmdParam() + ((sharelistNameSplit.length > 1)?" (" + sharelistNameSplit[1] + ")":""));
		shareListRadio[radioIdx].setFont(MainGui.DEFAULTFONT);
		shareListRadio[radioIdx].addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				super.widgetSelected(arg0);
				quotationSourceCombo[fRadioIdx].setEnabled(((Button) arg0.getSource()).getSelection());
				if (indicesChooserLabels[fRadioIdx] != null) {
					indicesChooserLabels[fRadioIdx].setEnabled(((Button) arg0.getSource()).getSelection());
					indicesChooserInput[fRadioIdx].setEnabled(((Button) arg0.getSource()).getSelection());
				}
				
				if (((Button) arg0.getSource()).getSelection()) radioAction.action();
			
			}
		});
		
		boolean isRadioCurrentShareList = currentMarketShareListId.equals(shareListId) && currentMarketListIndices.equals(indices);
		
		if (isRadioCurrentShareList) {
			shareListRadio[radioIdx].setFocus();
		}
		shareListRadio[radioIdx].setSelection(isRadioCurrentShareList);
		
		//Combos
		quotationSourceCombo[radioIdx] = new CCombo(this, SWT.READ_ONLY);
		//for (int j = 0, n = MarketQuotationProviders.values().length; j < n; j++) {
		for (int j = 0, n = 1; j < n; j++) {
			quotationSourceCombo[radioIdx].add(MarketQuotationProviders.values()[j].getCmdParam());
			quotationSourceCombo[radioIdx].select(0);
		}
		
		if (isRadioCurrentShareList) {
			//for (int j = 0; j < MarketQuotationProviders.values().length; j++) {
			for (int j = 0; j < 1; j++) {
				if (MarketQuotationProviders.values()[j].equals(currentQuotationProvider)) {
					quotationSourceCombo[radioIdx].select(j);
				}
			}
		}
		quotationSourceCombo[radioIdx].pack();
		quotationSourceCombo[radioIdx].setEnabled(isRadioCurrentShareList);
		quotationSourceCombo[radioIdx].addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectedQuotationProvider =  quotationSourceCombo[fRadioIdx].getText();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				selectedQuotationProvider =  quotationSourceCombo[fRadioIdx].getText();
				
			}
		});
		
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
