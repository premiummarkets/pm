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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.CursorFactory;
import com.finance.pms.MainGui;
import com.finance.pms.UserDialog;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.quotation.QuotationUpdate.StockNotFoundException;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.SharesListId;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.web.Indice;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.portfolio.PortfolioMgr;
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
	private  org.eclipse.swt.widgets.List paramsCombo;

	private Group insertManualGroup;
	private String symbolProvExtention;
	private Group insertFromFileGroup;

	
	protected Providers provider;
	private Boolean isOk = false;


	public ShareListUpdateDialog(Shell parent, int style) {
		
		super(new Shell(parent, SWT.PRIMARY_MODAL | SWT.RESIZE | SWT.SHELL_TRIM), style);
		getParent().setText("Premium Markets - Add or Update a Stock list");
				
		biggerFont = MainGui.DEFAULTFONT;
		
	}
	
	
	public void open() {
		
		GridLayout layout = new GridLayout();
		getParent().setLayout(layout);
		getParent().setBackground(MainGui.pOPUP_BG);
		
		List<String> loadShareListNames = PortfolioMgr.getInstance().getPortfolioDAO().loadShareListNames();
		{//Update existing
			Group existingGroup = new Group(getParent(), SWT.NONE);
			
			existingGroup.setBackground(MainGui.pOPUP_GRP);
			existingGroup.setFont(biggerFont);
			existingGroup.setText("Select an exising list to update in your database - then click 'Update an existing list' to proceed");

			GridData gridData = new GridData(SWT.FILL,SWT.TOP,true, false);
			existingGroup.setLayoutData(gridData);
			
			GridLayout gridLayout = new GridLayout();
			existingGroup.setLayout(gridLayout);

			existingCCombo = new CCombo(existingGroup, SWT.NONE);
			existingCCombo.setFont(MainGui.CONTENTFONT);
			existingCCombo.setLayoutData(new GridData(SWT.FILL,SWT.TOP,true, false));
			for (String shareListName : loadShareListNames) {
				if (!shareListName.equals(SharesListId.UNKNOWN.name())) existingCCombo.add(shareListName);
			}

			existingCCombo.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handle();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handle();
				}

				private void handle() {
					
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
			existDersc.setLayoutData(new GridData(SWT.FILL,SWT.TOP,true, false));
			existDersc.setBackground(MainGui.pOPUP_GRP);
			existDersc.setFont(MainGui.DEFAULTFONT);
			existDersc.setVisible(true);
			
			{
				Button valideButton1 = new Button(existingGroup, SWT.PUSH | SWT.CENTER);
				valideButton1.setLayoutData(new GridData(SWT.RIGHT,SWT.TOP,false, false));
				valideButton1.setText("Update an existing list");
				valideButton1.setToolTipText("The updated content will be available for addition in portoflios using 'Add shares ...' in 'Portfolios' menu");
				valideButton1.setFont(MainGui.DEFAULTFONT);
				valideButton1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
						
						if (provider != null) {
							SharesListId.updatePrefs(provider.getSharesListIdEnum().getSharesListCmdParam(), Indice.formatSet(provider.getIndices()),  MarketQuotationProviders.YAHOO.getCmdParam());
							isOk = true;
						}
						getParent().dispose();
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
			newGroup.setText("Or download a new list from the internet - then click 'Download a new list' to proceed");
			

			GridLayout ngL = new GridLayout();
			newGroup.setLayout(ngL);
			
			newCCombo = new CCombo(newGroup, SWT.NONE);
			newCCombo.setFont(MainGui.CONTENTFONT);
			GridData newCL = new GridData(SWT.FILL,SWT.TOP,true, false);
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
			paramsLabel.setText("You have selected parametrisable list. You must parametrise it using the list below.\nYou can also directly type in a comma separated combination in the blank field.\nUse Ctrl F to search and Ctrl click for multi selection.");
			paramsLabel.setBackground(MainGui.pOPUP_GRP);
			paramsLabel.setFont(MainGui.CONTENTFONT);
			
			paramsForm = new Text(paramsGroup, SWT.NONE);
			paramsForm.setFont(MainGui.CONTENTFONT);
			GridData paramsFormDL = new GridData(SWT.FILL,SWT.TOP,true, false);
			paramsForm.setLayoutData(paramsFormDL);
			
			newCCombo.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handle();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handle();
				}

				private void handle() {
					
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
				Button valideButton1 = new Button(newGroup, SWT.PUSH | SWT.CENTER);
				valideButton1.setLayoutData(new GridData(SWT.RIGHT,SWT.TOP,false, false));
				valideButton1.setText("Download a new list");
				valideButton1.setToolTipText("The downloaded content will be available for addition in portoflios using 'Add shares ...' in 'Portfolios' menu");
				valideButton1.setFont(MainGui.DEFAULTFONT);
				valideButton1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
						
						if (provider != null) {
							SharesListId.updatePrefs(provider.getSharesListIdEnum().getSharesListCmdParam(), Indice.formatSet(provider.getIndices()),  MarketQuotationProviders.YAHOO.getCmdParam());
							isOk = true;
						}
						getParent().dispose();
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
				symbolTxt.setEditable(true);
				symbolTxt.setFont(MainGui.CONTENTFONT);
				symbolTxt.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				final Text isinTxt = new Text(insertManualGroup, SWT.BORDER);
				isinTxt.setEditable(true);
				isinTxt.setFont(MainGui.CONTENTFONT);
				isinTxt.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				final Text nameTxt = new Text(insertManualGroup, SWT.BORDER);
				nameTxt.setEditable(true);
				nameTxt.setFont(MainGui.CONTENTFONT);
				nameTxt.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				final CCombo typeCombo = new CCombo(insertManualGroup, SWT.READ_ONLY|SWT.BORDER);
				typeCombo.setFont(MainGui.DEFAULTFONT);
				for (int j = 0, n = StockCategories.values().length; j < n; j++) {
					typeCombo.add(StockCategories.values()[j].name());
				}
				typeCombo.select(0);
				typeCombo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));


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
				marketCombo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

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
						"It is usally oik to leave it as one.";
				currencyFactorLab.setToolTipText(curFactToolTip);
				currencyFactorTxt.setToolTipText(curFactToolTip);
				currencyFactorTxt.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				currencyFactorTxt.setText("1");
				final CCombo provCombo = new CCombo(insertManualGroup, SWT.READ_ONLY|SWT.BORDER);
				provCombo.setFont(MainGui.DEFAULTFONT);
				provCombo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				provCombo.select(0);
				final CCombo symbolExtTxt = new CCombo(insertManualGroup, SWT.READ_ONLY|SWT.BORDER);
				GridData symExtData = new GridData(SWT.LEFT,SWT.CENTER,true,true);
				symbolExtTxt.setLayoutData(symExtData);
				symbolProvExtention = "NONE";
				symbolExtTxt.add(symbolProvExtention);
				symbolExtTxt.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
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
				String extToolTip = "The extention is specific to the quotation provider.\n" +
						"This will be part of the url to retreive the stock quotations (usually as an extension of the symbol or isin).\n" +
						"Every provider as its own set of extentions.";
				extLab.setToolTipText(extToolTip);
				symbolExtTxt.setToolTipText(extToolTip);
				symbolExtTxt.setFont(MainGui.CONTENTFONT);
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

				Button manualAddOkButton = new Button(insertManualGroup, SWT.PUSH);
				GridData newShareValidateButtonLData = new GridData(GridData.FILL_HORIZONTAL);
				newShareValidateButtonLData.horizontalSpan = 4;
				newShareValidateButtonLData.horizontalAlignment=SWT.END;
				manualAddOkButton.setLayoutData(newShareValidateButtonLData);
				manualAddOkButton.setText("Insert the above");
				manualAddOkButton.setToolTipText("The inserted stock will be available in the UNKNOWN list for addition in portoflios using 'Add shares ...' in 'Portfolios' menu");
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
						"Each line containing the following : symbol,isin,sector,type,market,currency factor,quotation provider\n" +
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
				insertFromFile.setToolTipText("The inserted stocks will be available in the UNKNOWN list for addition in portoflios using 'Add shares ...' in 'Portfolios' menu.\n"+toolTipTxt);
				insertFromFile.setFont(MainGui.DEFAULTFONT);
				insertFromFile.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent evt) {
						getParent().getShell().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
						try {
							portfolioAddSharesFromFileMouseDown(evt);
						} finally {
							getParent().getShell().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
						}
					}
				});
				
			}
		}
		
		getParent().layout();
		getParent().pack();
		getParent().open();
		
		
		Display display = getParent().getDisplay();
		while (!getParent().isDisposed()) {
			try {
				if (!display.readAndDispatch()) display.sleep();
			} catch (RuntimeException e) {
				LOGGER.error("Error in Error dialog Gui : "+e.getMessage(),e);
				LOGGER.debug("Error in Error Dialog Gui : ",e);
			} catch (Error e) {
				LOGGER.error("Error in  Gui : "+e.getMessage(),e);
				LOGGER.debug("Error in  Gui : ",e);
			}
		}
		
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
		Providers unknownProvider  = Providers.setupProvider(SharesListId.UNKNOWN.name());

		
		QuotationUpdate quotationUpdate = new QuotationUpdate();
	
		boolean isExtSet = symbolProvExtention != null && !symbolProvExtention.isEmpty() && !symbolProvExtention.equals("NONE");
		String symbol = symbolTxt.getText()+(isExtSet?"."+symbolProvExtention:"");

		try {
			Stock newStock = quotationUpdate.getQuotesForUi(
					unknownProvider, 
					isinTxt.getText(), symbol, nameTxt.getText(), 
					StockCategories.valueOf(typeCombo.getText()), 
					MarketQuotationProviders.valueOf(provCombo.getText()), new MarketValuation(Market.valueOf(marketCombo.getText()), new BigDecimal(currencyFactor.getText())));
			SharesList sharesListForThisListProvider = unknownProvider.loadSharesListForThisListProvider();
			sharesListForThisListProvider.addShare(newStock);
			PortfolioMgr.getInstance().getPortfolioDAO().saveOrUpdatePortfolio(sharesListForThisListProvider);
		} catch (StockNotFoundException e) {
			UserDialog inst = new UserDialog(getParent().getShell(), "The information typed in is not valid.\nPlease review the 'Insert Manually' form and try again.", 
					(e.getMessage() != null)?e.getMessage():e.toString());
			inst.open();
		}
	}
	
	private void validateNewManualShare(Text symbolTxt,  Text isinTxt,  Text nameTxt,  CCombo typeCombo, CCombo marketCombo,  Text  currencyFactorTxt, CCombo provCombo) {
		getParent().getShell().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
		try {
			portfolioInsertShareFromForm(symbolTxt, isinTxt, nameTxt, typeCombo, marketCombo, currencyFactorTxt, provCombo);
		} catch (Exception e) {
			LOGGER.warn(e, e);
			UserDialog inst = new UserDialog(getParent().getShell(), "The information typed in is not valid.\nPlease review the 'Insert Manually' form and try again.", 
					(e.getMessage() != null)?e.getMessage():e.toString());
			inst.open();
		} finally {
			getParent().getShell().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
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
			} catch (StockNotFoundException e) {
				UserDialog inst = new UserDialog(getParent().getShell(), (e.getMessage() != null)?e.getMessage():e.toString(),null);
				inst.open();
			} catch (Exception e) {
				LOGGER.warn(e,e);
				UserDialog inst = new UserDialog(getParent().getShell(), "Wrong file format.",null);
				inst.open();
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

}
