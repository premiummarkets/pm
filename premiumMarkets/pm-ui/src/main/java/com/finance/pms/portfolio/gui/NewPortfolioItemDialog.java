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
package com.finance.pms.portfolio.gui;

import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.CursorFactory;
import com.finance.pms.ErrorDialog;
import com.finance.pms.MainGui;
import com.finance.pms.MainPMScmd;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.quotation.QuotationUpdate.StockNotFoundException;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.portfolio.MonitorLevel;
import com.finance.pms.portfolio.PortfolioShare;


// TODO: Auto-generated Javadoc
/**
 * The Class NewPortfolioItemDialog.
 * 
 * @author Guillaume Thoreton
 */
public class NewPortfolioItemDialog extends org.eclipse.swt.widgets.Composite {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(NewPortfolioDialog.class);
	

	protected static NewPortfolioItemDialog inst;
	
	private Composite composite;

	private Group shareListGroup;
	protected Group addShareManualGroup;
	protected Button addFromFile;
	private String ext;
	
	private Label symbolLabel;
	protected Table symbolTable;
	protected Label quantityLabel;
	protected Text quantityText;
	
	enum Titles {Symbol,Name,Category};
	
	private StockList stockList;
	private Collection<PortfolioShare> alreadyBought;

	private List<Stock> selectedStocks;
	private BigDecimal selectedQuantity;
	protected Label monitorLabel;
	protected CCombo moniCombo;
	private MonitorLevel selectedMonitorLevel;

	private Button newPortfollioValidateButton;
	protected Button newPortfollioAddButton;
	

	/**
	 * Instantiates a new new portfolio item dialog.
	 * 
	 * @param parent the parent
	 * @param style the style
	 * @param alreadyBought the already scaned
	 * 
	 * @author Guillaume Thoreton
	 * @param composite 
	 */
	public NewPortfolioItemDialog(Shell parent, int style, Collection<PortfolioShare> alreadyBought, Composite composite) {
		super(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE | style);
		this.selectedMonitorLevel = MonitorLevel.BEARISH;
		this.selectedQuantity = BigDecimal.ONE;
		this.selectedStocks = new ArrayList<Stock>();
		
		this.alreadyBought = alreadyBought;
		
		this.composite = composite;
	}

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void main(String[] args) {
		SpringContext ctx = new SpringContext();
		ctx.setDataSource(args[0]);
		ctx.loadBeans(new String[] { "/connexions.xml", "/swtclients.xml" });
		ctx.refresh();
		showUI(new ArrayList<PortfolioShare>(), new Shell(), null);
	}

	/**
	 * Show ui.
	 * 
	 * @param alreadyScanned the already scaned
	 * 
	 * @return the new portfolio item dialog
	 * 
	 * @author Guillaume Thoreton
	 */
	public static NewPortfolioItemDialog showUI(Collection<PortfolioShare> alreadyScanned, Shell shell, PortfolioComposite composite) {
		
		if (inst == null || inst.isDisposed()) {

			Shell piShell = new Shell(shell, SWT.RESIZE | SWT.DIALOG_TRIM);
			inst = new NewPortfolioItemDialog(piShell, SWT.NULL, alreadyScanned, composite);
			try {
				inst.open();
				swtLoop();
			} catch (Exception e) {
				LOGGER.error("", e);
			}
		} else {
			inst.forceFocus();
		}
		return inst;
	}

	/**
	 * Swt loop.
	 * 
	 * @param inst the inst
	 * @param display the display
	 */
	public static void swtLoop() {
		
		inst.layout();
		inst.pack();
		
		inst.getShell().layout();
		inst.getShell().pack();
		inst.getShell().open();
		
		while (!inst.isDisposed() && !inst.getShell().isDisposed()) {
			try {
				if (!inst.getDisplay().readAndDispatch())
					inst.getDisplay().sleep();
			} catch (RuntimeException e) {
				LOGGER.error("Error in New Portfolio Item Gui : " + e.getMessage(),e);
				LOGGER.debug("Error in New Portfolio Item Gui : ", e);
			} catch (Error e) {
				LOGGER.error("Error in  Gui : " + e.getMessage(),e);
				LOGGER.debug("Error in  Gui : ", e);
			}
		}
	}

	/**
	 * Open.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void open() {
		
		GridLayout dialogShellLayout = new GridLayout();
		dialogShellLayout.verticalSpacing = 15;
		this.setLayout(dialogShellLayout);
		this.setBackground(new Color(getDisplay(), 204, 204, 255));
		this.getShell().setText("Premium Markets - Select a share.");
		
		{
			addFromFile = new Button(this, SWT.NONE);
			addFromFile.setText("Add new shares to list from a file ...");
			addFromFile.setFont(MainGui.DEFAULTFONT);
			addFromFile.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent evt) {
					getShell().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
					try {
						portfolioAddSharesFromFileMouseDown(evt);
					} finally {
						getShell().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
					}
				}
			});
		}
		{
			addShareManualGroup = new Group(this, SWT.NONE);
			GridLayout portfolioBoutonsGroupLayout = new GridLayout();
			portfolioBoutonsGroupLayout.numColumns=7;
			addShareManualGroup.setLayout(portfolioBoutonsGroupLayout);
			addShareManualGroup.setText("Add new share manually");
			addShareManualGroup.setFont(MainGui.DEFAULTFONT);
			//FR0000172124.WMORN,FR0000172124,AXA EURO 7-10 (C),SICAV,EURONEXT,investir
			
			Label symbolLab = new Label(addShareManualGroup, SWT.NONE);
			symbolLab.setText("Ext. symbol");
			symbolLab.setFont(MainGui.DEFAULTFONT);
			Label isinLab = new Label(addShareManualGroup, SWT.NONE);
			isinLab.setText("Isin");
			isinLab.setFont(MainGui.DEFAULTFONT);
			Label nameLab = new Label(addShareManualGroup, SWT.NONE);
			nameLab.setText("Name");
			nameLab.setFont(MainGui.DEFAULTFONT);
			Label typeLab = new Label(addShareManualGroup, SWT.NONE);
			typeLab.setText("Type");
			typeLab.setFont(MainGui.DEFAULTFONT);
			Label marketLab = new Label(addShareManualGroup, SWT.NONE);
			marketLab.setText("Market");
			marketLab.setFont(MainGui.DEFAULTFONT);
			Label providerLab = new Label(addShareManualGroup, SWT.NONE);
			providerLab.setText("Provider");
			providerLab.setFont(MainGui.DEFAULTFONT);
			Label extLab = new Label(addShareManualGroup, SWT.NONE);
			extLab.setText("Symbol extension");
			extLab.setFont(MainGui.DEFAULTFONT);
			
			final Text symbolTxt = new Text(addShareManualGroup, SWT.NONE);
			symbolTxt.setEditable(true);
			symbolTxt.setFont(MainGui.CONTENTFONT);
			final Text isinTxt = new Text(addShareManualGroup, SWT.NONE);
			isinTxt.setEditable(true);
			isinTxt.setFont(MainGui.CONTENTFONT);
			final Text nameTxt = new Text(addShareManualGroup, SWT.NONE);
			nameTxt.setEditable(true);
			nameTxt.setFont(MainGui.CONTENTFONT);
			final CCombo typeCombo = new CCombo(addShareManualGroup, SWT.NONE);
			typeCombo.setFont(MainGui.DEFAULTFONT);
			for (int j = 0, n = StockCategories.values().length; j < n; j++) {
				typeCombo.add(StockCategories.values()[j].name());
			}
			typeCombo.setEditable(false);
			final CCombo marketCombo = new CCombo(addShareManualGroup, SWT.NONE);
			marketCombo.setFont(MainGui.DEFAULTFONT);
			for (int j = 0, n = Market.values().length; j < n; j++) {
				marketCombo.add(Market.values()[j].name());
			}
			marketCombo.setEditable(false);
			final CCombo provCombo = new CCombo(addShareManualGroup, SWT.NONE);
			final CCombo symbolExtTxt = new CCombo(addShareManualGroup, SWT.NONE);
			ext = "NONE";
			provCombo.setFont(MainGui.DEFAULTFONT);
			for (int j = 0, n = MarketQuotationProviders.values().length; j < n; j++) {
				provCombo.add(MarketQuotationProviders.values()[j].name());
			}
			provCombo.setEditable(false);
			provCombo.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					symbolExtTxt.removeAll();
					ext = "NONE";
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					symbolExtTxt.removeAll();
					ext = "NONE";
				}
			});
			symbolExtTxt.setEditable(true);
			symbolExtTxt.setToolTipText(
					"The extention is specific to the quotation provider.\n" +
					"This will be part of the url to retreive the stock quotations (usually as an extension of the symbol or isin).\n" +
					"Every provider as its own set of extentions.");
			symbolExtTxt.setFont(MainGui.CONTENTFONT);
			symbolExtTxt.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					//symbolExtTxt.removeAll();
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					symbolExtTxt.add("NONE");

					String provStr = provCombo.getText();
					if (provStr != null) {
						MarketQuotationProviders provider = MarketQuotationProviders.valueOf(provStr);
						Set<String> extensionsForMarket = Market.getExtensionFor(provider);
						for (String ext : extensionsForMarket) {
							symbolExtTxt.add(ext);
						}
						symbolExtTxt.pack();
					}
				}
			});
			symbolExtTxt.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					ext = symbolExtTxt.getText();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					ext = symbolExtTxt.getText();
				}
			});
			symbolExtTxt.addModifyListener(new ModifyListener() {
				
				@Override
				public void modifyText(ModifyEvent e) {
					ext = symbolExtTxt.getText();	
				}
			});
			
			Button manualAddOkButton = new Button(addShareManualGroup, SWT.BORDER);
			GridData newShareValidateButtonLData = new GridData(GridData.HORIZONTAL_ALIGN_END);
			newShareValidateButtonLData.horizontalSpan = 7;
			manualAddOkButton.setLayoutData(newShareValidateButtonLData);
			manualAddOkButton.setText("Add new share to list");
			manualAddOkButton.setFont(MainGui.DEFAULTFONT);
			manualAddOkButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent evt) {
					validateNewManualShare(symbolTxt, isinTxt, nameTxt, typeCombo, marketCombo, provCombo);
				}
			});
			manualAddOkButton.addKeyListener(new KeyAdapter() {

				@Override
				public void keyReleased(KeyEvent e) {
					if (e.keyCode == SWT.CR) {
						validateNewManualShare(symbolTxt, isinTxt, nameTxt, typeCombo, marketCombo, provCombo);
					}
				}
				
			});
		}
		
		{
			shareListGroup = new Group(this, SWT.NONE);
			GridLayout portfolioBoutonsGroupLayout = new GridLayout();
			portfolioBoutonsGroupLayout.numColumns = 2;
			shareListGroup.setLayout(portfolioBoutonsGroupLayout);
			GridData portfolioBoutonsGroupLData = new GridData(GridData.FILL_BOTH);
			shareListGroup.setLayoutData(portfolioBoutonsGroupLData);
			shareListGroup.setText("Select your shares");
			shareListGroup.setFont(MainGui.DEFAULTFONT);
			{
				symbolLabel = new Label(shareListGroup, SWT.BORDER);
				symbolLabel.setText("Stock :");
				symbolLabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
				symbolLabel.setLayoutData(newPortfoliolabelLData);
			}
			{
				symbolTable = new Table(shareListGroup, SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);
				symbolTable.setFont(MainGui.DEFAULTFONT);
				symbolTable.setHeaderVisible(true);
				for (int j = 0; j < Titles.values().length; j++) {
					TableColumn column = new TableColumn(symbolTable, SWT.NONE);
					column.setText(Titles.values()[j].toString());
				}
				
				initShareScrollList();
				//symbolTable.setBounds(0, 0, 175, 100);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_BOTH);
				newPortfoliolabelLData.heightHint = 500;
				newPortfoliolabelLData.widthHint = 600;
				symbolTable.setLayoutData(newPortfoliolabelLData);
			}
			{
				quantityLabel = new Label(shareListGroup, SWT.BORDER);
				quantityLabel.setText("Quantity :");
				quantityLabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				quantityLabel.setLayoutData(newPortfoliolabelLData);
			}
			{
				quantityText = new Text(shareListGroup, SWT.BORDER);
				GridData newShareTextLData = new GridData(GridData.FILL_HORIZONTAL);
				quantityText.setLayoutData(newShareTextLData);
				quantityText.setFont(MainGui.CONTENTFONT);
				quantityText.setText(this.selectedQuantity.toString());
			}
			{
				monitorLabel = new Label(shareListGroup, SWT.BORDER);
				monitorLabel.setText("Monitor level :");
				monitorLabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				monitorLabel.setLayoutData(newPortfoliolabelLData);
			}
			{
				moniCombo = new CCombo(shareListGroup, SWT.READ_ONLY);
				moniCombo.setFont(MainGui.DEFAULTFONT);
				for (int j = 0, n = MonitorLevel.values().length; j < n; j++) {
					moniCombo.add(MonitorLevel.values()[j].getMonitorLevel());
				}
				moniCombo.select(moniCombo.indexOf(MonitorLevel.BEARISH.getMonitorLevel()));
			}
			{
				newPortfollioAddButton = new Button(shareListGroup, SWT.BORDER);
				GridData newShareValidateButtonLData = new GridData(GridData.HORIZONTAL_ALIGN_END);
				newShareValidateButtonLData.horizontalSpan = 1;
				newPortfollioAddButton.setLayoutData(newShareValidateButtonLData);
				newPortfollioAddButton.setText("Add");
				newPortfollioAddButton.setFont(MainGui.DEFAULTFONT);
				final NewPortfolioItemDialog pC = this;
				newPortfollioAddButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
						newPortfollioItemAddButtonMouseDown();
						((PortfolioComposite)composite).addShares(pC);
					}
				});
			}
			{
				newPortfollioValidateButton = new Button(shareListGroup, SWT.BORDER);
				GridData newShareValidateButtonLData = new GridData(GridData.HORIZONTAL_ALIGN_END);
				newShareValidateButtonLData.horizontalSpan = 1;
				newPortfollioValidateButton.setLayoutData(newShareValidateButtonLData);
				newPortfollioValidateButton.setText("Ok");
				newPortfollioValidateButton.setFont(MainGui.DEFAULTFONT);
				newPortfollioValidateButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
						newPortfollioItemValidateButtonMouseDown();
					}
				});
				newPortfollioValidateButton.addKeyListener(new KeyAdapter() {

					@Override
					public void keyReleased(KeyEvent e) {
						if (e.keyCode == SWT.CR) {
							newPortfollioItemValidateButtonMouseDown();
						}
					}
					
				});
			}
			
			shareListGroup.pack();
		}
	}
	
	private void validateNewManualShare(Text symbolTxt,  Text isinTxt,  Text nameTxt,  CCombo typeCombo, CCombo marketCombo,  CCombo provCombo) {
		getShell().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
		try {
			portfolioAddShareFromForm(symbolTxt, isinTxt, nameTxt, typeCombo, marketCombo, provCombo);
		} catch (Exception e) {
			LOGGER.warn(e, e);
			ErrorDialog inst = new ErrorDialog(getShell(), SWT.NULL,(e.getMessage() != null)?e.getMessage():e.toString(), null);
			inst.open();
		} finally {
			getShell().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}
	}

	private void initShareScrollList() {
		
		stockList = new StockList();
		stockList.addAll(DataSource.getInstance().loadAllStocks());
		
		for (PortfolioShare portfolioShare : alreadyBought) {
			stockList.remove(portfolioShare.getStock());
		}
		
		symbolTable.removeAll();
		updateTableDisplay();
		for (int j = 0; j < Titles.values().length; j++) {
			symbolTable.getColumn(j).addSelectionListener(new SelectionListener() {
				public void widgetDefaultSelected(SelectionEvent arg0) {
					LOGGER.debug("Column selected" + ((TableColumn) arg0.getSource()).getText());
					sortColumn(((TableColumn) arg0.getSource()).getText());
				}

				public void widgetSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
				}
			});
		}
	}
	
	private void portfolioAddShareFromForm(Text symbolTxt, Text isinTxt, Text nameTxt, CCombo typeCombo, CCombo marketCombo, CCombo provCombo) 
																																		throws InvalidAlgorithmParameterException {
		QuotationUpdate quotationUpdate = new QuotationUpdate();
		String listStProvider = MainPMScmd.getPrefs().get("quotes.listprovider","euronext");

		boolean isExtSet = ext != null && !ext.isEmpty() && !ext.equals("NONE");
		String symbol = symbolTxt.getText()+(isExtSet?"."+ext:"");

		try {
			quotationUpdate.getQuotesForUi(
					listStProvider, 
					isinTxt.getText(), symbol, nameTxt.getText(), 
					StockCategories.valueOf(typeCombo.getText()), 
					MarketQuotationProviders.valueOf(provCombo.getText()), Market.valueOf(marketCombo.getText()));
		} catch (StockNotFoundException e) {
			ErrorDialog inst = new ErrorDialog(getShell(), SWT.NULL,(e.getMessage() != null)?e.getMessage():e.toString(), null);
			inst.open();
		}

		initShareScrollList();
	}
	
	private void portfolioAddSharesFromFileMouseDown(SelectionEvent event) {
		
		//Update share list
		String[] filterExtensions = {"*.txt"};
		FileDialog fileDialog = new FileDialog(getShell(), SWT.OPEN);
		fileDialog.setText("Choose a file containing the list of new shares");

		fileDialog.setFilterExtensions(filterExtensions);
		String selectedFile = fileDialog.open();

		if (null != selectedFile) {
			String listStProvider = MainPMScmd.getPrefs().get("quotes.listprovider","euronext");
			QuotationUpdate quotationUpdate = new QuotationUpdate();
			try {
				quotationUpdate.getQuotesForListInFile(selectedFile, listStProvider);
			} catch (StockNotFoundException e) {
				ErrorDialog inst = new ErrorDialog(getShell(), SWT.NULL,(e.getMessage() != null)?e.getMessage():e.toString(), null);
				inst.open();
			}
			
			initShareScrollList();
		}
		
		
	
	}

	/**
	 * Update table display.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void updateTableDisplay() {

		for (Stock s : stockList) {
			
			TableItem item = new TableItem(symbolTable, SWT.NONE);
			item.setFont(MainGui.CONTENTFONT);
			item.setText(0, s.getRefName());
			item.setText(1, s.getName());
			item.setText(2, s.getCategory().getCategory());
		}

		for (int j = 0; j < Titles.values().length; j++) {
			symbolTable.getColumn(j).pack();
		}
	}
	
	/**
	 * Sort column.
	 * 
	 * @param colStr the col str
	 * 
	 * @author Guillaume Thoreton
	 */
	private void sortColumn(String colStr) {
		
		int colNum = Titles.valueOf(colStr).ordinal();
		
		switch (colNum) {
		case 1:
			Collections.sort(stockList,new Comparator<Stock>() {
				public int compare(Stock o1, Stock o2) {
					int ret = o1.getName().trim().toLowerCase().compareTo(o2.getName().trim().toLowerCase());
					ret = (ret == 0)? o1.getRefName().trim().toLowerCase().compareTo(o2.getRefName().trim().toLowerCase()):ret;
					ret = (ret == 0)? o1.getCategory().getCategory().compareTo(o2.getCategory().getCategory()):ret;
					return ret;
				}						
			});
			break;
		case 0:
			Collections.sort(stockList,new Comparator<Stock>() {
				public int compare(Stock o1, Stock o2) {
					int ret = o1.getRefName().trim().toLowerCase().compareTo(o2.getRefName().trim().toLowerCase());
					ret = (ret == 0)? o1.getName().trim().toLowerCase().compareTo(o2.getName().trim().toLowerCase()):ret;
					ret = (ret == 0)? o1.getCategory().getCategory().compareTo(o2.getCategory().getCategory()):ret;
					return ret;
				}						
			});
			break;
		case 2:
			Collections.sort(stockList,new Comparator<Stock>() {
				public int compare(Stock o1, Stock o2) {
					int ret = o1.getCategory().getCategory().compareTo(o2.getCategory().getCategory());
					ret = (ret == 0)? o1.getName().trim().toLowerCase().compareTo(o2.getName().trim().toLowerCase()):ret;
					ret = (ret == 0)? o1.getRefName().trim().toLowerCase().compareTo(o2.getRefName().trim().toLowerCase()):ret;
					return ret;
				}						
			});
			break;
		default:
			break;
		}

		symbolTable.removeAll();
		updateTableDisplay();
	}
	
	/**
	 * New portfollio item validate button mouse down.
	 * 
	 * @param evt the evt
	 * 
	 * @author Guillaume Thoreton
	 */
	private void newPortfollioItemAddButtonMouseDown() {
		
		selectedStocks = new ArrayList<Stock>();
		int[] selections = symbolTable.getSelectionIndices();
		for (int i = 0; i < selections.length; i++) {
			selectedStocks.add(stockList.get(selections[i]));
		}
		selectedQuantity = new BigDecimal(quantityText.getText());
		selectedMonitorLevel = MonitorLevel.valueOfString(moniCombo.getText());
		
	}

	/**
	 * New portfollio item validate button mouse down.
	 * 
	 * @param evt the evt
	 * 
	 * @author Guillaume Thoreton
	 */
	private void newPortfollioItemValidateButtonMouseDown() {
		
		selectedStocks = new ArrayList<Stock>();
		int[] selections = symbolTable.getSelectionIndices();
		for (int i = 0; i < selections.length; i++) {
			selectedStocks.add(stockList.get(selections[i]));
		}
		selectedQuantity = new BigDecimal(quantityText.getText());
		selectedMonitorLevel = MonitorLevel.valueOfString(moniCombo.getText());
		getShell().dispose();
	}

	/**
	 * Gets the selected stocks.
	 * 
	 * @return the selected stocks
	 */
	public List<Stock> getSelectedStocks() {
		return selectedStocks;
	}

	/**
	 * Gets the selected quantity.
	 * 
	 * @return the selected quantity
	 */
	public BigDecimal getSelectedQuantity() {
		return selectedQuantity;
	}

	/**
	 * Gets the selected monitor level.
	 * 
	 * @return the selected monitor level
	 */
	public MonitorLevel getSelectedMonitorLevel() {
		return selectedMonitorLevel;
	}
}
