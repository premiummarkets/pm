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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
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
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.ActionDialogAction;
import com.finance.pms.AutoCompletePopupMenu;
import com.finance.pms.CursorFactory;
import com.finance.pms.LogComposite;
import com.finance.pms.MainGui;
import com.finance.pms.RefreshableView;
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
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.SymbolNameResolver;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.datasources.web.Indice;
import com.finance.pms.datasources.web.MarketListProvider;
import com.finance.pms.datasources.web.ProvidersList;
import com.finance.pms.portfolio.Portfolio;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.SharesList;

public class ShareListUpdateDialog extends Dialog implements RefreshableView {

	private static final String INDICES = "indices";

	private static final String SHARE_LIST_ID = "shareListId";

	protected static MyLogger LOGGER = MyLogger.getLogger(UserDialog.class);

	private Font biggerFont;

	private Table existingShareLists;
	private Text quoteProvidersCombo;

	private SharesListId newListSharesListId;
	private SortedSet<Indice> newListIndices;

	private Table newShareLists;
	private Group paramsGroup;
	private Text paramsForm;
	private Composite paramsListBound;

	private String symbolProvExtention;
	protected MarketListProvider provider;

	private ActionDialogAction actionDialogAction;
	private ActionDialogAction refreshAction;

	private Table stocks;

	private LogComposite logComposite;


	public ShareListUpdateDialog(Shell parent, LogComposite logComposite) {

		super(new Shell(parent, SWT.DIALOG_TRIM | SWT.RESIZE));
		getParent().setText(MainGui.APP_NAME+" - Add or Update a Stock list.");	

		biggerFont = MainGui.DEFAULTFONT;
		this.logComposite = logComposite;

		initGui();

	}

	protected void initGui() {

		GridLayout layout = new GridLayout();
		getParent().setLayout(layout);
		getParent().setBackground(MainGui.pOPUP_BG);

		SortedSet<String> loadShareListNames = new TreeSet<>(PortfolioMgr.getInstance().getPortfolioDAO().loadValidShareListNames());
		{//Existing

			Group existingGroup = new Group(getParent(), SWT.NONE);	
			existingGroup.setBackground(MainGui.pOPUP_GRP);
			existingGroup.setFont(biggerFont);
			existingGroup.setText("Your current stock lists.");
			GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
			existingGroup.setLayoutData(gridData);
			GridLayout existingGrpLayout = new GridLayout();
			existingGrpLayout.numColumns=2;
			existingGroup.setLayout(existingGrpLayout);

			Composite existingBounds = new Composite(existingGroup, SWT.NONE);
			GridData bgridData = new GridData(SWT.FILL, SWT.FILL, true, true);
			bgridData.horizontalSpan = 2;
			bgridData.heightHint = 150;
			existingBounds.setLayoutData(bgridData);
			existingBounds.setLayout(new GridLayout());

			existingShareLists = new Table(existingBounds, SWT.V_SCROLL | SWT.SINGLE |SWT.FULL_SELECTION);
			existingShareLists.setHeaderVisible(true);
			GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
			existingShareLists.setLayoutData(layoutData);
			existingShareLists.setFont(MainGui.CONTENTFONT);
			existingShareLists.getVerticalBar().setVisible(true);

			String[] titles = {"Market / Stocks List", "Indices", "Description"};
			for (int i=0; i<titles.length; i++) {
				TableColumn column = new TableColumn(existingShareLists, SWT.NONE);
				column.setText(titles [i]);
			}

			for (String shareListName : loadShareListNames) {
				String[] shareListSplit = ProvidersList.shareListSplit(shareListName);
				SharesListId shareListId = SharesListId.valueOf(shareListSplit[0]);

				TableItem item = new TableItem (existingShareLists, SWT.NONE);
				item.setFont(MainGui.CONTENTFONT);
				item.setText (0, shareListId.name());
				item.setText (1, shareListSplit[1]);
				item.setText (2, shareListId.getDescription());	
				item.setData(SHARE_LIST_ID, shareListId);
				item.setData(INDICES, Indice.parseString(shareListSplit[1]));
			}

			for (int i=0; i<titles.length; i++) {
				existingShareLists.getColumn(i).pack();
			}	

			existingShareLists.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handleExistingListSelection();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handleExistingListSelection();
				}

			});	
			existingShareLists.deselectAll();

			{
				Label quoteProviderLabel = new Label(existingGroup, SWT.NONE);
				quoteProviderLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
				quoteProviderLabel.setBackground(MainGui.pOPUP_GRP);
				quoteProviderLabel.setFont(MainGui.DEFAULTFONT);
				quoteProviderLabel.setText("Quotation provider for selected list");
				quoteProvidersCombo = new Text(existingGroup, SWT.NONE);
				GridData qPClayoutData = new GridData(SWT.LEFT, SWT.CENTER, true, false);
				qPClayoutData.horizontalIndent = 20;
				quoteProvidersCombo.setLayoutData(qPClayoutData);
				quoteProvidersCombo.setFont(MainGui.CONTENTFONT);
				quoteProvidersCombo.setToolTipText("This is the default, not editable for the moment.");
				quoteProvidersCombo.setEditable(false);
				//TODO
				//				for (MarketQuotationProviders quoteProvider : MarketQuotationProviders.values()) {
				//					quoteProvidersCombo.add(quoteProvider.name());
				//				}
				quoteProvidersCombo.pack();
			}

			{
				final Button valideButton1 = new Button(existingGroup, SWT.PUSH | SWT.CENTER);
				GridData validateButtLayout = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
				validateButtLayout.horizontalSpan=2;
				valideButton1.setLayoutData(validateButtLayout);
				valideButton1.setText("Update selected list");
				valideButton1.setToolTipText("You can add shares in Portflios using 'Add shares ...' in 'Portfolios' menu");
				valideButton1.setFont(MainGui.DEFAULTFONT);
				valideButton1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
						ShareListUpdateDialog.this.actionDialogAction.action();
					}
				});
			}

		}

		{//Stocks list

			final Group stocksGrp = new Group(getParent(), SWT.NONE);
			stocksGrp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			stocksGrp.setLayout(new GridLayout());
			stocksGrp.setBackground(MainGui.pOPUP_GRP);
			stocksGrp.setFont(biggerFont);
			stocksGrp.setText("Stocks in the selected list - click 'Update selected list' to update.");
			Composite composite = new Composite(stocksGrp, SWT.NONE);
			GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
			layoutData.heightHint = 100;
			composite.setLayoutData(layoutData);
			composite.setLayout(new GridLayout());

			stocks = new Table(composite, SWT.NONE);
			stocks.setHeaderVisible(true);
			stocks.setToolTipText("These stocks can be added to you portfolios using the menu 'Portfolios' -> 'Add Shares...'");
			GridData stocksLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
			stocks.setLayoutData(stocksLayoutData);
			stocks.setFont(MainGui.CONTENTFONT);
			stocks.getVerticalBar().setVisible(true);

			String[] titles = new String[]{"Symbol", "Isin", "Name"};
			for (String title : titles) {
				TableColumn tableColumn = new TableColumn(stocks, SWT.NONE);
				tableColumn.setText(title);
			}

		}

		{
			final Group addGroup = new Group(getParent(), SWT.NONE);
			GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
			addGroup.setLayoutData(gridData);
			GridLayout gridLayout = new GridLayout();
			addGroup.setLayout(gridLayout);

			addGroup.setFont(biggerFont);
			addGroup.setBackground(MainGui.pOPUP_GRP);
			addGroup.setText("Add lists and stocks");

			final CTabFolder cTabFolder = new CTabFolder(addGroup, SWT.NONE);
			cTabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			cTabFolder.setLayout(new GridLayout());
			cTabFolder.setFont(MainGui.DEFAULTFONT);
			cTabFolder.setBackground(MainGui.pOPUP_GRP);
			cTabFolder.setSelectionBackground(MainGui.tAB_SELECTION);
			{
				CTabItem addNewListTab = new CTabItem(cTabFolder, SWT.NONE);
				addNewListTab.setText("Add stocks from the web");
				Group addNewListGrp = addNewList(cTabFolder, new ArrayList<String>(loadShareListNames));
				addNewListTab.setControl(addNewListGrp);
			}
			{
				CTabItem manualAddTab = new CTabItem(cTabFolder, SWT.NONE);
				manualAddTab.setText("Add stocks manually");
				Group manualStockInsertGrp = manualStockInsert(cTabFolder);
				manualAddTab.setControl(manualStockInsertGrp);
			}
			{
				CTabItem fileAddTab = new CTabItem(cTabFolder, SWT.NONE);
				fileAddTab.setText("Add stocks from files");
				Group fileStockInsertGrp = fileStockInsert(cTabFolder);
				fileAddTab.setControl(fileStockInsertGrp);
			}
			cTabFolder.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handleSelection();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handleSelection();
				}

				private void handleSelection() {
					newShareLists.deselectAll();
					clearParamGroup();
					cTabFolder.layout();
					cTabFolder.pack();
					getParent().pack();
				}
			});
			cTabFolder.layout();
			cTabFolder.pack();
			cTabFolder.setSelection(0);

		}

		getParent().layout();
		getParent().pack();

	}

	private void handleExistingListSelection() {

		TableItem tableItem = existingShareLists.getSelection()[0];
		SharesListId shareListId = (SharesListId) tableItem.getData(SHARE_LIST_ID);
		@SuppressWarnings("unchecked")
		SortedSet<Indice> shareListIndices = (SortedSet<Indice>) tableItem.getData(INDICES);
		if (!SharesListId.UNKNOWN.equals(shareListId)) {

			try {
				provider = (MarketListProvider) ProvidersList.getInstance(shareListId.getSharesListCmdParam(), shareListIndices);
				quoteProvidersCombo.setText(provider.defaultMarketQuotationProviders().name());
				quoteProvidersCombo.pack();
				updateStocksList();
			} catch (IllegalArgumentException e) {
				UserDialog dialog = new UserDialog(getParent(), shareListId + " with (" + shareListIndices + ") is not a valid share list. Has it been added by hand?", null);
				dialog.open();
			}

		} else {
			quoteProvidersCombo.setText("None");
			provider = null;
			updateStocksList();
		}

		getParent().layout();

	}

	private void updateStocksList() {

		stocks.removeAll();

		Runnable runnable = new Runnable() {
			public void run() {
				ShareListUpdateDialog.this.getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
				try {

					String providerIndicedShareListName = SharesListId.UNKNOWN.toString();
					if (provider != null) {
						providerIndicedShareListName = ProvidersList.providerIndicedShareListName(provider);
					}
					SharesList loadShareList = PortfolioMgr.getInstance().getPortfolioDAO().loadShareList(providerIndicedShareListName);
					StockList stockList = new StockList(loadShareList.toSortedStocksSet());
					for (Stock stock : stockList) {
						if (!stocks.isDisposed()) {
							TableItem item = new TableItem(stocks, SWT.NONE);
							item.setFont(MainGui.CONTENTFONT);
							item.setText(0, stock.getSymbol());
							item.setText(1, stock.getIsin());
							item.setText(2, stock.getName());
						}
					}
					for (int j = 0; j < 3; j++) {
						if (!stocks.isDisposed()) {
							stocks.getColumn(j).pack();
						}
					}

				} finally  {
					ShareListUpdateDialog.this.getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
				}
			}
		};

		Display.getCurrent().asyncExec(runnable);
	}


	private Group fileStockInsert(CTabFolder cTabFolder) {

		String toolTipTxt = "The file format is CSV and the content must be one line per stock.\n" +
				"Each line containing the following : symbol,ISIN,sector,type,market,currency factor,quotation provider\n" +
				"Please refer to the manual insertion form above for the different options and fields details.\n" +
				"Alternatively have a look at the sample file : list_test.txt";
		Group insertFromFileGroup = new Group(cTabFolder, SWT.NONE);
		insertFromFileGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout addFileGroupL = new GridLayout();
		insertFromFileGroup.setLayout(addFileGroupL);

		insertFromFileGroup.setBackground(MainGui.pOPUP_GRP);
		insertFromFileGroup.setText("Add stocks from a file");
		insertFromFileGroup.setToolTipText(toolTipTxt);
		insertFromFileGroup.setFont(biggerFont);

		Button insertFromFile = new Button(insertFromFileGroup, SWT.PUSH);
		insertFromFile.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		insertFromFile.setText("Upload your own file of stocks ...");
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

		return insertFromFileGroup;

	}

	private Group manualStockInsert(CTabFolder cTabFolder) {

		Group insertManualGroup = new Group(cTabFolder, SWT.NONE);
		insertManualGroup.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		GridLayout addShareManualGroupL = new GridLayout(4,false);
		addShareManualGroupL.makeColumnsEqualWidth=true;
		insertManualGroup.setLayout(addShareManualGroupL);

		insertManualGroup.setBackground(MainGui.pOPUP_GRP);
		insertManualGroup.setText("Add a new stock manually");
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
					symbolExtTxt.setText("NONE");
				}
			}
		});

		//Manual ack button
		Button manualAddOkButton = new Button(insertManualGroup, SWT.PUSH);
		GridData newShareValidateButtonLData = new GridData(GridData.FILL_HORIZONTAL);
		newShareValidateButtonLData.horizontalSpan = 4;
		newShareValidateButtonLData.horizontalAlignment=SWT.FILL;
		manualAddOkButton.setLayoutData(newShareValidateButtonLData);
		manualAddOkButton.setText("Add");
		manualAddOkButton.setToolTipText("The added stock will be available in the UNKNOWN list for addition in portfolios using 'Add shares ...' in 'Portfolios' menu");
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

		return insertManualGroup;
	}


	private Group addNewList(final CTabFolder cTabFolder, List<String> loadShareListNames) {

		final Group newGroup = new Group(cTabFolder, SWT.NONE);

		newGroup.setFont(biggerFont);
		newGroup.setBackground(MainGui.pOPUP_GRP);
		newGroup.setText("Add a new list - click add to proceed");
		newGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout newGroupLayout = new GridLayout();
		newGroup.setLayout(newGroupLayout);

		{
			final Composite listAParams = new Composite(newGroup, SWT.NONE);
			GridData listAParamDataLayout = new GridData(SWT.FILL, SWT.TOP, true, false);
			listAParams.setLayoutData(listAParamDataLayout);
			FillLayout lApLayout = new FillLayout(SWT.HORIZONTAL);
			listAParams.setLayout(lApLayout);
			listAParams.setBackground(MainGui.pOPUP_GRP);

			{
				{
					newShareLists = new Table(listAParams, SWT.NONE | SWT.FULL_SELECTION | SWT.SINGLE);
					newShareLists.setHeaderVisible (true);
					newShareLists.setFont(MainGui.CONTENTFONT);
					newShareLists.getVerticalBar().setVisible(true);

					String[] titles = {"Market / Stocks List", "Description"};
					for (int i=0; i<titles.length; i++) {
						TableColumn column = new TableColumn(newShareLists, SWT.NONE);
						column.setText(titles [i]);
					}

					for (final SharesListId shareListId : SharesListId.values()) {
						if (shareListId.equals(SharesListId.UNKNOWN)) continue;
						if ( shareListId.getIsDownloadable() && (!loadShareListNames.contains(shareListId.getSharesListCmdParam()) || shareListId.getIsIndicesComposite()) ) {
							TableItem item = new TableItem (newShareLists, SWT.NONE);
							item.setFont(MainGui.CONTENTFONT);
							item.setText (0, shareListId.name());
							item.setText (1, shareListId.getDescription());	
						}
					}

					for (int i=0; i< titles.length; i++) {
						newShareLists.getColumn(i).pack();
					}
				}
				{
					paramsGroup = new Group(listAParams, SWT.NONE);
					paramsGroup.setBackground(MainGui.pOPUP_GRP);
					paramsGroup.setFont(biggerFont);
					paramsGroup.setText("Choose your indices");
					paramsGroup.setLayout(new GridLayout());
					paramsGroup.setVisible(false);

					paramsForm = new Text(paramsGroup, SWT.NONE);
					paramsForm.setFont(MainGui.CONTENTFONT);
					GridData paramsFormDL = new GridData(SWT.FILL, SWT.TOP, true, false);
					paramsForm.setLayoutData(paramsFormDL);

					newShareLists.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetSelected(SelectionEvent e) {
							handleNewList();
						}

						@Override
						public void widgetDefaultSelected(SelectionEvent e) {
							handleNewList();
						}

						private void handleNewList() {

							TableItem[] selection = newShareLists.getSelection();
							if (selection.length == 0) return;

							String newList = selection[0].getText();
							newListSharesListId = SharesListId.valueOf(newList);
							clearParamGroup();

							newListIndices = new TreeSet<>();
							if (newListSharesListId.getIsIndicesComposite()) {

								paramsForm.setToolTipText(newListSharesListId.getComment());
								paramsListBound = new Composite(paramsGroup, SWT.NONE);
								GridData paramComboBoundLD = new GridData(SWT.FILL, SWT.FILL, true, true);
								paramComboBoundLD.heightHint = 200;
								paramsListBound.setLayoutData(paramComboBoundLD);
								GridLayout paramComboBoundL = new GridLayout();
								paramComboBoundL.marginHeight = 0;
								paramComboBoundL.marginWidth = 0;
								paramsListBound.setLayout(paramComboBoundL);

								final org.eclipse.swt.widgets.List paramIndicesList = new  org.eclipse.swt.widgets.List(paramsListBound, SWT.V_SCROLL | SWT.MULTI);
								paramIndicesList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
								paramIndicesList.setFont(MainGui.CONTENTFONT);
								paramIndicesList.getVerticalBar().setVisible(true);
								String[] options = newListSharesListId.getOptions();
								for (String param : options) {
									paramIndicesList.add(param);
								}
								paramIndicesList.addSelectionListener(new SelectionListener() {

									@Override
									public void widgetSelected(SelectionEvent e) {
										handleSelection();
									}

									@Override
									public void widgetDefaultSelected(SelectionEvent e) {
										handleSelection();
									}

									private void handleSelection() {
										String[] selection = paramIndicesList.getSelection();
										String indice = "";
										String separator = "";
										for (String string : selection) {
											indice = indice + separator + string;
											separator = ",";
										}
										paramsForm.setText(indice);
										if (newListIndices != null) newListIndices.clear();
										newListIndices.addAll(Indice.parseString(indice));
									}
								});
								paramIndicesList.addControlListener(new ControlListener() {

									@Override
									public void controlResized(ControlEvent e) {
										updateComboSize();
									}

									@Override
									public void controlMoved(ControlEvent e) {
										updateComboSize();
									}

									private void updateComboSize() {
										Point computedSize = paramsListBound.getSize();
										paramIndicesList.setSize(computedSize.x, computedSize.y);
									}

								});
								paramsGroup.pack();
								paramsGroup.setVisible(true);
							} else {
								paramsGroup.setVisible(false);
							}

							getParent().layout();
							getParent().pack();
						}
					});
				}
			}
		}
		{
			final Button valideButton1 = new Button(newGroup, SWT.PUSH | SWT.CENTER);
			GridData validateLayoutData = new GridData(SWT.FILL, SWT.TOP, true, false);
			valideButton1.setLayoutData(validateLayoutData);
			valideButton1.setText("Add");
			valideButton1.setFont(MainGui.DEFAULTFONT);
			valideButton1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent evt) {
					if (newListSharesListId != null) updateExistingShareList();
				}
			});
		}

		return newGroup;
	}

	private void updateExistingShareList() {

		TableItem[] items = existingShareLists.getItems();
		Boolean doesNotContain = true;
		for (TableItem existingItem : items) {
			SharesListId shareListId = (SharesListId) existingItem.getData(SHARE_LIST_ID);
			@SuppressWarnings("unchecked")
			SortedSet<Indice> indices = (SortedSet<Indice>) existingItem.getData(INDICES);
			if (shareListId.equals(newListSharesListId) && indices.size() == newListIndices.size()) {
				Boolean sameIndices = true;
				for (Indice indice : indices) {
					sameIndices = sameIndices && newListIndices.contains(indice);
				}
				doesNotContain = !sameIndices;
				if (!doesNotContain) {
					existingShareLists.setSelection(existingItem);
					handleExistingListSelection();
					break;
				}
			}
		}
		if (doesNotContain) {
			TableItem newItem = new TableItem (existingShareLists, SWT.NONE);
			newItem.setFont(MainGui.CONTENTFONT);
			newItem.setText (0, newListSharesListId.name());
			newItem.setText (1, Indice.formatSet(newListIndices).replaceFirst(",",""));
			newItem.setText (2, newListSharesListId.getDescription());	
			newItem.setData(SHARE_LIST_ID, SharesListId.valueOf(newListSharesListId.name()));
			newItem.setData(INDICES, new TreeSet<Indice>(newListIndices));

			for (int i=0; i< existingShareLists.getColumns().length; i++) {
				existingShareLists.getColumn(i).pack();
			}	
			existingShareLists.setSelection(newItem);
			handleExistingListSelection();
		}
	}


	public void open() {
		getParent().open();
	}

	private void portfolioInsertShareFromForm(Text symbolTxt, Text isinTxt, Text nameTxt, CCombo typeCombo, CCombo marketCombo,Text currencyFactor, CCombo provCombo)  throws InvalidAlgorithmParameterException {

		MarketListProvider unknownProvider = ProvidersList.getMarketListInstance(SharesListId.UNKNOWN.name());//defaults to Yahoo

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
			String name = nameTxt.getText();
			StockCategories category = StockCategories.valueOf(typeCombo.getText());
			SymbolMarketQuotationProvider qProv = new SymbolMarketQuotationProvider(MarketQuotationProviders.valueOf(provCombo.getText()), symbolProvExtention2);
			MarketValuation marketValuation = new MarketValuation(Market.valueOf(marketCombo.getText()), new BigDecimal(currencyFactor.getText()));
			Stock newStock = new Stock(isin, symbol, name, true, category, qProv,  marketValuation, "", TradingMode.CONTINUOUS, 0l);

			//Reset quotations
			try {
				newStock = quotationUpdate.getQuotesForUiForm(unknownProvider, newStock); //TODO use the eventController
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

			//Select the unknown list
			int unkIdx = 0;
			for (TableItem ti : existingShareLists.getItems()) {
				SharesListId data = (SharesListId) ti.getData(SHARE_LIST_ID);
				if (data.equals(SharesListId.UNKNOWN)) break;
				unkIdx++;
			}
			existingShareLists.setSelection(unkIdx);
			handleExistingListSelection();

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
			refreshAction.action();
		} catch (Exception e) {
			LOGGER.warn(e, e);
			UserDialog inst = new UserDialog(getParent().getShell(), "The information typed in is not valid.\nPlease review the 'Insert Manually' form and try again.", (e.getMessage() != null)?e.getMessage():e.toString());
			inst.open();
		} finally {
			getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));	
		}
	}

	private void portfolioAddSharesFromFileMouseDown(SelectionEvent event) {

		MarketListProvider unknownProvider  = ProvidersList.getMarketListInstance(SharesListId.UNKNOWN.name());

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

				refreshAction.action();

				UserDialog inst = new UserDialog(getParent().getShell(), "The following stock were found in the file and updated", stocksInFile.toString());
				inst.open();

			} catch (Exception e) {
				LOGGER.warn(e,e);
				UserDialog inst = new UserDialog(getParent().getShell(), "Wrong file format.",null);
				inst.open();
			}

		}

	}


	private void updateReferencedStock(Stock newStock) {
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

	public MarketListProvider getProvider() {
		return provider;
	}

	public void setAction(ActionDialogAction actionDialogAction) {
		this.actionDialogAction = actionDialogAction;

	}

	public void setRefreshAction(ActionDialogAction actionDialogAction) {
		this.refreshAction = actionDialogAction;
	}

	protected void clearParamGroup() {
		if (paramsListBound != null && !paramsListBound.isDisposed()) {
			paramsListBound.dispose();
			paramsGroup.setVisible(false);
			paramsForm.setText("");
			getParent().getShell().pack();
		}
	}

	@Override
	public void endRefreshAction(List<Exception> exceptions) {
		try {
			logComposite.endJob(exceptions);
		} finally {
			this.setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}
	}

	@Override
	public void initRefreshAction() {
		setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
		logComposite.initRefresh(this);	
	}

	@Override
	public void refreshView(List<Exception> exceptions) {
		handleExistingListSelection();
	}

	@Override
	public Date getAnalysisStartDate() {
		return null;
	}

	@Override
	public Date getAnalysisEndDate() {
		return null;
	}

	@Override
	public void setCursor(Cursor cursor) {
		ShareListUpdateDialog.this.getParent().setCursor(cursor);
	}

}
