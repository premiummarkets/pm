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
package com.finance.pms.sharelists.gui;

import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.util.List;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.ActionDialogAction;
import com.finance.pms.AutoCompletePopupMenu;
import com.finance.pms.CursorFactory;
import com.finance.pms.MainGui;
import com.finance.pms.UserDialog;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.quotation.QuotationUpdate.QuotationUpdateException;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.SharesListId;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.SymbolNameResolver;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.datasources.web.Indice;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.portfolio.Portfolio;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.SharesList;

public class ShareListUpdateDialog extends Dialog {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(UserDialog.class);
	
	private Font biggerFont;

	private CCombo existingCCombo;
	private Label existDersc;
	
	private CCombo newCCombo;
	private Label newDersc;
	private Composite paramsComboBound;
	private Group paramsGroup;
	private Text paramsForm;
	private org.eclipse.swt.widgets.List paramsCombo;

	private Group insertManualGroup;
	private String symbolProvExtention;
	private Group insertFromFileGroup;

	
	protected Providers provider;
	private Boolean isOk = false;

	private ActionDialogAction actionDialogAction;
	private ActionDialogAction refreshAction;

	public ShareListUpdateDialog(Shell parent) {
		
		super(new Shell(parent, SWT.DIALOG_TRIM | SWT.RESIZE));
		getParent().setText("Premium Markets - Add or Update a Stock list");	
		biggerFont = MainGui.DEFAULTFONT;
		initGui();
		
	}


	protected void initGui() {
		
		GridLayout layout = new GridLayout();
		getParent().setLayout(layout);
		getParent().setBackground(MainGui.pOPUP_BG);
		
		List<String> loadShareListNames = PortfolioMgr.getInstance().getPortfolioDAO().loadShareListNames();
		{//Update existing
			Group existingGroup = new Group(getParent(), SWT.NONE);
			
			existingGroup.setBackground(MainGui.pOPUP_GRP);
			existingGroup.setFont(biggerFont);
			existingGroup.setText("Select an existing list to update in your database - then click 'Update an existing list' to proceed");

			GridData gridData = new GridData(SWT.FILL,SWT.TOP,true, false);
			existingGroup.setLayoutData(gridData);
			
			GridLayout gridLayout = new GridLayout();
			existingGroup.setLayout(gridLayout);

			existingCCombo = new CCombo(existingGroup, SWT.NONE);
			existingCCombo.setFont(MainGui.CONTENTFONT);
			existingCCombo.setLayoutData(new GridData(SWT.FILL,SWT.TOP, true, false));
			for (String shareListName : loadShareListNames) {
				if (!shareListName.equals(SharesListId.UNKNOWN.name())) existingCCombo.add(shareListName);
			}

			existingCCombo.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handleExistingList();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handleExistingList();
				}

				private void handleExistingList() {
					
					newCCombo.deselectAll();
					newDersc.setVisible(false);
					disposeParams();
					
					ShareListUpdateDialog.this.getParent().pack();
					
					String item = existingCCombo.getItem(existingCCombo.getSelectionIndex());
					try {
						provider = Providers.setupProvider(item);
						existDersc.setText(provider.getSharesListIdEnum().getDescription());
						existDersc.setVisible(true);
					} catch (java.lang.IllegalArgumentException e) {
						UserDialog dialog = new UserDialog(getParent(), item+" is not a valid share list. Has it been added by hand?", null);
						dialog.open();
					}
					
					getParent().layout();
				}

			});	
			
			existDersc = new Label(existingGroup, SWT.NONE);
			existDersc.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
			existDersc.setBackground(MainGui.pOPUP_GRP);
			existDersc.setFont(MainGui.DEFAULTFONT);
			existDersc.setVisible(true);
			
			{
				final Button valideButton1 = new Button(existingGroup, SWT.PUSH | SWT.CENTER);
				valideButton1.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false));
				valideButton1.setText("Update an existing list");
				valideButton1.setToolTipText("The updated content will be available for addition in Portoflios using 'Add shares ...' in 'Portfolios' menu");
				valideButton1.setFont(MainGui.DEFAULTFONT);
				valideButton1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
						
						if (provider != null) {
							SharesListId.updatePrefs(provider.getSharesListIdEnum().getSharesListCmdParam(), Indice.formatSet(provider.getIndices()),  MarketQuotationProviders.YAHOO.getCmdParam());
							isOk = true;
						}
						try {
							getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
							ShareListUpdateDialog.this.actionDialogAction.action(valideButton1);
						} finally {
							getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
						}
						
					}
				});
			}
			
		}
		
		{//Add new
			final Group newGroup = new Group(getParent(), SWT.NONE);
			GridData gridData = new GridData(SWT.FILL,SWT.FILL,true, true);
			newGroup.setLayoutData(gridData);
			
			newGroup.setFont(biggerFont);
			newGroup.setBackground(MainGui.pOPUP_GRP);
			newGroup.setText("Or download a new list from the Internet - then click 'Download a new list' to proceed");
			

			GridLayout ngL = new GridLayout();
			newGroup.setLayout(ngL);
			
			newCCombo = new CCombo(newGroup, SWT.NONE);
			newCCombo.setFont(MainGui.CONTENTFONT);
			GridData newCL = new GridData(SWT.FILL ,SWT.TOP, true, false);
			newCCombo.setLayoutData(newCL);
			
			for (final SharesListId shareListId : SharesListId.values()) {
				if (shareListId.equals(SharesListId.UNKNOWN)) continue;
				if (!loadShareListNames.contains(shareListId.getSharesListCmdParam()) || shareListId.getIsIndicesComposite()) {
					newCCombo.add(shareListId.name());
				}
			}
			
			newDersc = new Label(newGroup, SWT.NONE);
			GridData newDerscDL = new GridData(SWT.FILL,SWT.TOP,true, false);
			newDersc.setLayoutData(newDerscDL);
			
			newDersc.setFont(MainGui.DEFAULTFONT);
			newDersc.setBackground(MainGui.pOPUP_GRP);
			newDersc.setVisible(false);
			
			paramsGroup = new Group(newGroup, SWT.NONE);
			paramsGroup.setBackground(MainGui.pOPUP_GRP);
			paramsGroup.setFont(biggerFont);
			paramsGroup.setText("Choose your indices");
			GridData paramGL = new GridData(SWT.FILL,SWT.FILL,true, true);
			paramsGroup.setLayoutData(paramGL);
			GridLayout paramsGroupL = new GridLayout();
			paramsGroup.setLayout(paramsGroupL);
			paramsGroup.setVisible(false);
			
			Label paramsLabel = new Label(paramsGroup, SWT.NONE);
			paramsLabel.setText(
					"You have selected a customisable list. You must customise it using the list below.\n"
					+ "You can also directly type in a comma separated combination in the blank field.\n"
					+ "Use Ctrl F to search and Ctrl click for multi selection.");
			paramsLabel.setBackground(MainGui.pOPUP_GRP);
			paramsLabel.setFont(MainGui.CONTENTFONT);
			
			paramsForm = new Text(paramsGroup, SWT.NONE);
			paramsForm.setFont(MainGui.CONTENTFONT);
			GridData paramsFormDL = new GridData(SWT.FILL,SWT.TOP,true, false);
			paramsForm.setLayoutData(paramsFormDL);
			
			newCCombo.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handleNewList();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handleNewList();
				}

				private void handleNewList() {
					
					existingCCombo.deselectAll();
					existDersc.setVisible(false);
					disposeParams();
					
					String item = newCCombo.getItem(newCCombo.getSelectionIndex());
					provider = Providers.setupProvider(item);
					
					newDersc.setText(provider.getSharesListIdEnum().getDescription());
					newDersc.setVisible(true);
					
					if (provider.getSharesListIdEnum().getIsIndicesComposite()) {
						
						paramsForm.setToolTipText(provider.getSharesListIdEnum().getComment());
						paramsComboBound = new Composite(paramsGroup, SWT.NONE);
						GridData paramComboBoundLD = new GridData(SWT.FILL,SWT.FILL,true, true);
						paramComboBoundLD.heightHint=200;
						paramsComboBound.setLayoutData(paramComboBoundLD);
						GridLayout paramComboBoundL = new GridLayout();
						paramComboBoundL.marginHeight = 0;
						paramComboBoundL.marginWidth = 0;
						paramsComboBound.setLayout(paramComboBoundL);
						
						paramsCombo = new  org.eclipse.swt.widgets.List(paramsComboBound, SWT.V_SCROLL | SWT.MULTI);
						GridData paramGL = new GridData(SWT.FILL,SWT.FILL,true, true);
						paramsCombo.setLayoutData(paramGL);
						
						paramsCombo.setFont(MainGui.CONTENTFONT);
						paramsCombo.getVerticalBar().setVisible(true);
						String[] options = provider.getSharesListIdEnum().getOptions();
						for (String param : options) {
							paramsCombo.add(param);
						}
						paramsCombo.addSelectionListener(new SelectionListener() {
							
							@Override
							public void widgetSelected(SelectionEvent e) {
								handleButton();
							}
							
							@Override
							public void widgetDefaultSelected(SelectionEvent e) {
								handleButton();
							}

							private void handleButton() {
								String[] selection = paramsCombo.getSelection();
								String txt = "";
								for (String string : selection) {
									txt = txt + ","+ string;
								}
								paramsForm.setText(txt);
								provider.addIndices(Indice.parseString(txt),true);
							}
						});
						paramsCombo.addControlListener(new ControlListener() {
							
							@Override
							public void controlResized(ControlEvent e) {
								updateComboSize();
								
							}
							
							@Override
							public void controlMoved(ControlEvent e) {
								updateComboSize();
								
							}
						});
						
						paramsGroup.setVisible(true);
						paramsGroup.pack();
						getParent().layout();
						getParent().pack();
					} 	
				}

			});
			
			{
				final Button valideButton1 = new Button(newGroup, SWT.PUSH | SWT.CENTER);
				valideButton1.setLayoutData(new GridData(SWT.RIGHT,SWT.TOP,false, false));
				valideButton1.setText("Download a new list");
				valideButton1.setToolTipText("The download content will be available for addition in Portfolios using 'Add shares ...' in 'Portfolios' menu");
				valideButton1.setFont(MainGui.DEFAULTFONT);
				valideButton1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
						
						if (provider != null) {
							SharesListId.updatePrefs(provider.getSharesListIdEnum().getSharesListCmdParam(), Indice.formatSet(provider.getIndices()),  MarketQuotationProviders.YAHOO.getCmdParam());
							isOk = true;
						}
						try {
							getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
							ShareListUpdateDialog.this.actionDialogAction.action(valideButton1);
						} finally  {
							getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
						}
					}
				});
			}
		}
		{//Manual addition
			Group userFeedGroup = new Group(getParent(), SWT.NONE);

			userFeedGroup.setBackground(MainGui.pOPUP_GRP);
			GridData gridData = new GridData(SWT.FILL,SWT.FILL,true, true);
			userFeedGroup.setLayoutData(gridData);
			userFeedGroup.setLayout(new GridLayout());

			userFeedGroup.setFont(biggerFont);
			userFeedGroup.setText("Alternatively, insert your own stocks and shares");
			userFeedGroup.setToolTipText(
					"Additionally, you can insert your own stocks if not available in the above lists from the web.\n " +
					"You can either insert one stock manually using the form below or upload a list from a file.\n " +
					"The stock(s) inserted will then be available in the UNKNOWN list for addition to portfolios.");
			
			{
				insertManualGroup = new Group(userFeedGroup, SWT.NONE);
				insertManualGroup.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
				GridLayout addShareManualGroupL = new GridLayout(4,false);
				addShareManualGroupL.makeColumnsEqualWidth=true;
				insertManualGroup.setLayout(addShareManualGroupL);

				insertManualGroup.setBackground(MainGui.pOPUP_GRP);
				insertManualGroup.setText("Insert Manually");
				insertManualGroup.setFont(biggerFont);

				Label symbolLab = new Label(insertManualGroup, SWT.NONE);
				symbolLab.setBackground(MainGui.pOPUP_GRP);
				symbolLab.setText("Symbol");
				symbolLab.setFont(MainGui.DEFAULTFONT);
				Label isinLab = new Label(insertManualGroup, SWT.NONE);
				isinLab.setBackground(MainGui.pOPUP_GRP);
				isinLab.setText("Isin");
				isinLab.setFont(MainGui.DEFAULTFONT);
				Label nameLab = new Label(insertManualGroup, SWT.NONE);
				nameLab.setBackground(MainGui.pOPUP_GRP);
				nameLab.setText("Name");
				nameLab.setFont(MainGui.DEFAULTFONT);
				Label typeLab = new Label(insertManualGroup, SWT.NONE);
				typeLab.setBackground(MainGui.pOPUP_GRP);
				typeLab.setText("Type");
				typeLab.setFont(MainGui.DEFAULTFONT);
				
				final Text symbolTxt = new Text(insertManualGroup, SWT.BORDER);
				{
					symbolTxt.setEditable(true);
					symbolTxt.setFont(MainGui.CONTENTFONT);
					symbolTxt.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
					AutoCompletePopupMenu<Stock> autoCompletePopupMenu = new AutoCompletePopupMenu<Stock>(getParent().getShell(), insertManualGroup, symbolTxt) {
						
						@Override
						public String transalateALike(Stock alike) {
							return alike.getSymbol();
						}
						
						@Override
						public List<Stock> loadAlikes(String typedInString) {
							List<Stock> alikes = DataSource.getInstance().getShareDAO().loadSharesLike(typedInString, 50);
							return alikes;
						}
					};
					autoCompletePopupMenu.initPopupMenu();
				}
				
				final Text isinTxt = new Text(insertManualGroup, SWT.BORDER);
				isinTxt.setEditable(true);
				isinTxt.setFont(MainGui.CONTENTFONT);
				isinTxt.setLayoutData(new GridData(SWT.FILL,SWT.TOP, true, false));
				final Text nameTxt = new Text(insertManualGroup, SWT.BORDER);
				nameTxt.setEditable(true);
				nameTxt.setFont(MainGui.CONTENTFONT);
				nameTxt.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
				final CCombo typeCombo = new CCombo(insertManualGroup, SWT.READ_ONLY|SWT.BORDER);
				typeCombo.setFont(MainGui.CONTENTFONT);
				for (int j = 0, n = StockCategories.values().length; j < n; j++) {
					typeCombo.add(StockCategories.values()[j].name());
				}
				typeCombo.select(0);
				typeCombo.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));


				Label marketLab = new Label(insertManualGroup, SWT.NONE);
				marketLab.setBackground(MainGui.pOPUP_GRP);
				marketLab.setText("Market");
				marketLab.setFont(MainGui.DEFAULTFONT);
				Label currencyFactorLab = new Label(insertManualGroup, SWT.NONE);
				currencyFactorLab.setBackground(MainGui.pOPUP_GRP);
				currencyFactorLab.setText("Currency Unit factor");
				currencyFactorLab.setFont(MainGui.DEFAULTFONT);
				Label providerLab = new Label(insertManualGroup, SWT.NONE);
				providerLab.setBackground(MainGui.pOPUP_GRP);
				providerLab.setText("Quotations provider");
				providerLab.setFont(MainGui.DEFAULTFONT);
				Label extLab = new Label(insertManualGroup, SWT.NONE);
				extLab.setBackground(MainGui.pOPUP_GRP);
				extLab.setText("Symbol extension");
				extLab.setFont(MainGui.DEFAULTFONT);

				final CCombo marketCombo = new CCombo(insertManualGroup, SWT.READ_ONLY|SWT.BORDER);
				marketCombo.setFont(MainGui.DEFAULTFONT);
				for (int j = 0, n = Market.values().length; j < n; j++) {
					marketCombo.add(Market.values()[j].name());
				}
				marketCombo.select(0);
				marketCombo.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

				final Text currencyFactorTxt = new Text(insertManualGroup, SWT.BORDER);

				marketCombo.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						handleMarketSelect();	
					}

					private void handleMarketSelect() {
						Market m = Market.valueOf(marketCombo.getItem(marketCombo.getSelectionIndex()));
						currencyFactorTxt.setText(m.getDefaultCurrencyFactor().toString());
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						handleMarketSelect();	
					}
				});
				currencyFactorTxt.setEditable(true);
				currencyFactorTxt.setFont(MainGui.CONTENTFONT);
				String curFactToolTip = "This is to deal with quotations available in fractions of the main currency.\n" +
						"For instance for quotations in Pence, we use GBP as currency but specify a unit factor 100 here for conversion purpose.\n" +
						"It is usually OK to leave it as one.";
				currencyFactorLab.setToolTipText(curFactToolTip);
				currencyFactorTxt.setToolTipText(curFactToolTip);
				currencyFactorTxt.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
				currencyFactorTxt.setText("1");
				final CCombo provCombo = new CCombo(insertManualGroup, SWT.READ_ONLY|SWT.BORDER);
				provCombo.setFont(MainGui.DEFAULTFONT);
				provCombo.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
				provCombo.select(0);
				final CCombo symbolExtTxt = new CCombo(insertManualGroup, SWT.READ_ONLY|SWT.BORDER);
				GridData symExtData = new GridData(SWT.LEFT, SWT.TOP, true, false);
				symbolExtTxt.setLayoutData(symExtData);
				symbolExtTxt.setFont(MainGui.CONTENTFONT);
				symbolProvExtention = "NONE";
				symbolExtTxt.add(symbolProvExtention);
				symbolExtTxt.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
				for (int j = 0, n = MarketQuotationProviders.values().length; j < n; j++) {
					provCombo.add(MarketQuotationProviders.values()[j].name());
				}
				provCombo.setEditable(false);
				provCombo.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						symbolExtTxt.removeAll();
						symbolProvExtention = "NONE";
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						symbolExtTxt.removeAll();
						symbolProvExtention = "NONE";
					}
				});
				symbolExtTxt.setEditable(true);
				String extToolTip = "The extension is specific to the quotation provider.\n" +
						"This will be part of the URL to retrieve the stock quotations (usually as an extension of the symbol or ISIN).\n" +
						"Every provider as its own set of extensions.";
				extLab.setToolTipText(extToolTip);
				symbolExtTxt.setToolTipText(extToolTip);
				symbolExtTxt.addFocusListener(new FocusListener() {

					@Override
					public void focusLost(FocusEvent e) {
					}

					@Override
					public void focusGained(FocusEvent e) {
						symbolExtTxt.add("NONE");

						String provStr = provCombo.getText();
						if (provStr != null && !provStr.isEmpty()) {
							try {
								MarketQuotationProviders provider = MarketQuotationProviders.valueOf(provStr);
								Set<String> extensionsForMarket = Market.getExtensionFor(provider);
								for (String ext : extensionsForMarket) {
									symbolExtTxt.add(ext);
								}
								symbolExtTxt.pack();
							} catch (Exception e1) {
								LOGGER.error("Invalid quotation provider market? : "+provStr,e1);
							}
						}
					}
				});
				symbolExtTxt.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						symbolProvExtention = symbolExtTxt.getText();
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						symbolProvExtention = symbolExtTxt.getText();
					}
				});
				symbolExtTxt.addModifyListener(new ModifyListener() {

					@Override
					public void modifyText(ModifyEvent e) {
						symbolProvExtention = symbolExtTxt.getText();	
					}
				});
				provCombo.select(0);
				
				symbolTxt.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						handleSymbolSelection();
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						handleSymbolSelection();
					}

					private void handleSymbolSelection() {
						Stock stockBySymbol = DataSource.getInstance().loadStockBySymbol(symbolTxt.getText());
						if (stockBySymbol != null) {
							isinTxt.setText(stockBySymbol.getIsin());
							nameTxt.setText(stockBySymbol.getName());
							typeCombo.setText(stockBySymbol.getCategory().name());
							marketCombo.setText(stockBySymbol.getMarket().name());
							currencyFactorTxt.setText(stockBySymbol.getMarketValuation().getCurrencyFactor().toString());
							provCombo.setText(stockBySymbol.getSymbolMarketQuotationProvider().getMarketQuotationProvider().name());
							//symbolExtTxt.setText(Market.getExtensionFor(stockBySymbol.getSymbolMarketQuotationProvider().getMarketQuotationProvider(), stockBySymbol.getMarket()));
							symbolExtTxt.setText("NONE");
						}
					}
				});

				//Manual ack button
				Button manualAddOkButton = new Button(insertManualGroup, SWT.PUSH);
				GridData newShareValidateButtonLData = new GridData(GridData.FILL_HORIZONTAL);
				newShareValidateButtonLData.horizontalSpan = 4;
				newShareValidateButtonLData.horizontalAlignment=SWT.END;
				manualAddOkButton.setLayoutData(newShareValidateButtonLData);
				manualAddOkButton.setText("Insert the above");
				manualAddOkButton.setToolTipText("The inserted stock will be available in the UNKNOWN list for addition in portfolios using 'Add shares ...' in 'Portfolios' menu");
				manualAddOkButton.setFont(MainGui.DEFAULTFONT);
				manualAddOkButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
						validateNewManualShare(symbolTxt, isinTxt, nameTxt, typeCombo, marketCombo, currencyFactorTxt, provCombo);
					}
				});
				manualAddOkButton.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						if (e.keyCode == SWT.CR) {
							validateNewManualShare(symbolTxt, isinTxt, nameTxt, typeCombo, marketCombo, currencyFactorTxt, provCombo);
						}
					}
				});
			}
			{
				String toolTipTxt = "The file format is CSV and the content must be one line per stock.\n" +
						"Each line containing the following : symbol,ISIN,sector,type,market,currency factor,quotation provider\n" +
						"Please refer to the manual insertion form above for the different options and fields details.\n" +
						"Alternatively have a look at the sample file : list_test.txt";
				insertFromFileGroup = new Group(userFeedGroup, SWT.NONE);
				insertFromFileGroup.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
				GridLayout addFileGroupL = new GridLayout();
				insertFromFileGroup.setLayout(addFileGroupL);
				
				insertFromFileGroup.setBackground(new Color(getParent().getParent().getDisplay(), 239, 203, 152));
				insertFromFileGroup.setText("Insert From a file");
				insertFromFileGroup.setToolTipText(toolTipTxt);
				insertFromFileGroup.setFont(biggerFont);
				
				Button insertFromFile = new Button(insertFromFileGroup, SWT.PUSH);
				insertFromFile.setLayoutData(new GridData(SWT.END,SWT.DEFAULT,true,false));
				insertFromFile.setText("Insert your own file of stocks ...");
				insertFromFile.setToolTipText("The inserted stocks will be available in the UNKNOWN list for addition in Portfolios using 'Add shares ...' in 'Portfolios' menu.\n"+toolTipTxt);
				insertFromFile.setFont(MainGui.DEFAULTFONT);
				insertFromFile.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent evt) {
						
						try {
							getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
							portfolioAddSharesFromFileMouseDown(evt);
						} finally {
							getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
						}
					}
				});
				
			}
		}
		
		getParent().pack();
		getParent().layout();
		
		Point computeSize = getParent().computeSize(SWT.DEFAULT, SWT.DEFAULT);
		getParent().setSize((int)(computeSize.x*.8), computeSize.y);
		
			
	}


	public void open() {
		getParent().open();
	}
	
	private void updateComboSize() {
		Point computedSize = paramsComboBound.getSize();
		paramsCombo.setSize(computedSize.x, computedSize.y);
	}
	
	private void portfolioInsertShareFromForm(Text symbolTxt, Text isinTxt, Text nameTxt, CCombo typeCombo, CCombo marketCombo,Text currencyFactor, CCombo provCombo)  throws InvalidAlgorithmParameterException {
		
		newCCombo.deselectAll();
		newDersc.setVisible(false);
		existingCCombo.deselectAll();
		existDersc.setVisible(false);
		Providers unknownProvider  = Providers.setupProvider(SharesListId.UNKNOWN.name());//defaults to Yahoo

		
		QuotationUpdate quotationUpdate = new QuotationUpdate();
		
		try {
			
			//Create or update the stock
			String symbolProvExtention2 = SymbolNameResolver.UNKNOWNEXTENSIONCLUE;
			boolean isExtSet = symbolProvExtention != null && !symbolProvExtention.isEmpty() && !symbolProvExtention.equals("NONE");
			if (isExtSet) {
				symbolProvExtention2 = symbolProvExtention;
			}

			String isin = isinTxt.getText();
			String symbol = symbolTxt.getText();
			//Stock newStock = DataSource.getInstance().getShareDAO().loadStockBy(symbol, isin);
			String name = nameTxt.getText();
			StockCategories category = StockCategories.valueOf(typeCombo.getText());
			SymbolMarketQuotationProvider qProv = new SymbolMarketQuotationProvider(MarketQuotationProviders.valueOf(provCombo.getText()), symbolProvExtention2);
			MarketValuation marketValuation = new MarketValuation(Market.valueOf(marketCombo.getText()), new BigDecimal(currencyFactor.getText()));
			Stock newStock = new Stock(isin, symbol, name, true, category, qProv,  marketValuation, "", TradingMode.CONTINUOUS, 0l);
			
			//Reset quotations
			try {
				newStock = quotationUpdate.getQuotesForUiForm(unknownProvider, newStock);
			} catch (QuotationUpdateException e) {
				
				if (!e.getStockNotFound().isEmpty()) {
					newStock = e.getStockNotFound().keySet().iterator().next();
					UserDialog inst = new UserDialog(getParent().getShell(), 
							"The stock quotations could not be found using your settings. It may be that the information typed in is not valid.\n"+
							"You may want to review the 'Insert Manually' form information and try again.\n" +
							"If you know this stock is listed with one of the available provider try and use the exact same SYMBOL and ISIN as the one used be the provider.\n"+
							"Note that the stock informations will nevertheless be inserted but will lake quotations data.", null);
					inst.open();
				} else {
					throw e;
				}
				
			}
			
			//Save or update stock and unknown share list
			if (newStock != null) {
				DataSource.getInstance().getShareDAO().saveOrUpdateStock(newStock);
				SharesList sharesListForThisListProvider = unknownProvider.loadSharesListForThisListProvider();
				sharesListForThisListProvider.addShare(newStock);
				PortfolioMgr.getInstance().getPortfolioDAO().saveOrUpdatePortfolio(sharesListForThisListProvider);
			} else {
				String message = "Stock is null for "+name+" / "+symbol+" / "+isin;
				LOGGER.error(message);
				throw new Exception(message);
			}
			
			updateReferencedStock(newStock);
			
		} catch (Exception e) {
			LOGGER.warn(e, e);
			UserDialog inst = new UserDialog(getParent().getShell(), 
					"An error occurred. It may be that the information typed in is not valid.\n"+
					"You may want to review the 'Insert Manually' form information and try again.", (e.getMessage() != null)?e.getMessage():e.toString());
			inst.open();
		}
	}
	
	private void validateNewManualShare(Text symbolTxt,  Text isinTxt,  Text nameTxt,  CCombo typeCombo, CCombo marketCombo,  Text  currencyFactorTxt, CCombo provCombo) {
		
		try {
			getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
			portfolioInsertShareFromForm(symbolTxt, isinTxt, nameTxt, typeCombo, marketCombo, currencyFactorTxt, provCombo);
			refreshAction.action(null);
		} catch (Exception e) {
			LOGGER.warn(e, e);
			UserDialog inst = new UserDialog(getParent().getShell(), "The information typed in is not valid.\nPlease review the 'Insert Manually' form and try again.", (e.getMessage() != null)?e.getMessage():e.toString());
			inst.open();
		} finally {
			getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));	
		}
	}
	
	private void portfolioAddSharesFromFileMouseDown(SelectionEvent event) {
		
		newCCombo.deselectAll();
		newDersc.setVisible(false);
		existingCCombo.deselectAll();
		existDersc.setVisible(false);
		Providers unknownProvider  = Providers.setupProvider(SharesListId.UNKNOWN.name());
		
		//Update share list
		String[] filterExtensions = {"*.txt"};
		FileDialog fileDialog = new FileDialog(getParent().getShell(), SWT.OPEN);
		fileDialog.setText("Choose a file containing the list of new shares");

		fileDialog.setFilterExtensions(filterExtensions);
		String selectedFile = fileDialog.open();

		if (null != selectedFile) {
			
			QuotationUpdate quotationUpdate = new QuotationUpdate();
			try {
				
				List<Stock> stocksInFile = quotationUpdate.getQuotesForListInFile(selectedFile, unknownProvider);
				SharesList sharesListForThisListProvider = unknownProvider.loadSharesListForThisListProvider();
				sharesListForThisListProvider.addShares(stocksInFile);
				PortfolioMgr.getInstance().getPortfolioDAO().saveOrUpdatePortfolio(sharesListForThisListProvider);
				
				//Update references
				for (Stock newStock : stocksInFile) {
					updateReferencedStock(newStock);
				}
				
				refreshAction.action(null);
				
				UserDialog inst = new UserDialog(getParent().getShell(), "The following stock were found in the file and updated", stocksInFile.toString());
				inst.open();
				
			} catch (Exception e) {
				LOGGER.warn(e,e);
				UserDialog inst = new UserDialog(getParent().getShell(), "Wrong file format.",null);
				inst.open();
			}
			
		}
		
	}


	protected void updateReferencedStock(Stock newStock) {
		for (Portfolio portfolio : PortfolioMgr.getInstance().getVisiblePortfolios()) {
			for (PortfolioShare ps : portfolio.getListShares().values()) {
				Stock stock = ps.getStock();
				if (stock.equals(newStock)) {
					stock.setName(newStock.getName());
					stock.setCategory(newStock.getCategory());
					stock.setSymbolMarketQuotationProvider(newStock.getSymbolMarketQuotationProvider());
					stock.setMarketValuation(newStock.getMarketValuation());
					stock.setSectorHint(newStock.getSectorHint());
					stock.setCapitalisation(newStock.getCapitalisation());
					stock.setTradingMode(newStock.getTradingMode());
					portfolio.setIsUiDirty(true);
				}
			}
		}
	}


	public Boolean getIsOk() {
		return isOk;
	}


	public Providers getProvider() {
		return provider;
	}

	private void disposeParams() {
		if (paramsComboBound != null && !paramsComboBound.isDisposed()) {
			paramsComboBound.dispose();
			paramsGroup.setVisible(false);
			paramsForm.setText("");
			getParent().getShell().pack();
		}
	}

	public void setAction(ActionDialogAction actionDialogAction) {
		this.actionDialogAction = actionDialogAction;
		
	}
	
	public void setRefreshAction(ActionDialogAction actionDialogAction) {
		this.refreshAction = actionDialogAction;
	}

}
