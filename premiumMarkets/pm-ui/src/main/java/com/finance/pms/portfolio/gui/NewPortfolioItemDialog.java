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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.MainGui;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;
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
	
	/** The dialog shell. */
	private Shell dialogShell;
	
	/** The share car group. */
	private Group shareCarGroup;
	
	/** The symbol label. */
	private Label symbolLabel;
	
	/** The symbol table. */
	protected Table symbolTable;
	
	/**
	 * The Enum Titles.
	 * 
	 * @author Guillaume Thoreton
	 */
	enum Titles {Symbol,Name,Category};
	
	/** The st. */
	private StockList stockList;
	
	/** The selected stocks. */
	private List<Stock> selectedStocks;
	
	/** The quantity label. */
	protected Label quantityLabel;
	
	/** The quantity text. */
	protected Text quantityText;
	
	/** The selected quantity. */
	private BigDecimal selectedQuantity;
	
	/** The monitor label. */
	protected Label monitorLabel;
	
	/** The moni combo. */
	protected CCombo moniCombo;
	
	/** The selected monitor level. */
	private MonitorLevel selectedMonitorLevel;
	
	/** The new portfollio validate button. */
	private Button newPortfollioValidateButton;
	
	/** The already scaned. */
	private Collection<PortfolioShare> alreadyBought;

	/**
	 * Instantiates a new new portfolio item dialog.
	 * 
	 * @param parent the parent
	 * @param style the style
	 * @param alreadyBought the already scaned
	 * 
	 * @author Guillaume Thoreton
	 */
	public NewPortfolioItemDialog(Shell parent, int style, Collection<PortfolioShare> alreadyBought) {
		super(parent, style);
		this.selectedMonitorLevel = MonitorLevel.ANY;
		this.selectedQuantity = BigDecimal.ONE;
		this.selectedStocks = new ArrayList<Stock>();
		
		this.alreadyBought = alreadyBought;
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
		showUI(new ArrayList<PortfolioShare>());
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
	public static NewPortfolioItemDialog showUI(Collection<PortfolioShare> alreadyScanned) {
		NewPortfolioItemDialog inst = null;
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		inst = new NewPortfolioItemDialog(shell, SWT.NULL, alreadyScanned);
		try {
			inst.open();
			swtLoop(inst, display);
		} catch (Exception e) {
			LOGGER.error("", e);
		}
		return inst;
	}

	/**
	 * Swt loop.
	 * 
	 * @param inst the inst
	 * @param display the display
	 */
	public static void swtLoop(NewPortfolioItemDialog inst, Display display) {
		
		inst.dialogShell.layout();
		inst.dialogShell.pack();
		inst.dialogShell.open();
		
		while (!inst.dialogShell.isDisposed()) {
			try {
				if (!display.readAndDispatch())
					display.sleep();
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
		
		dialogShell = new Shell(getShell(), SWT.DIALOG_TRIM | SWT.RESIZE);
		GridLayout dialogShellLayout = new GridLayout();
		dialogShellLayout.verticalSpacing = 30;
		dialogShell.setLayout(dialogShellLayout);
		dialogShell.setBackground(new Color(getDisplay(), 204, 204, 255));
		dialogShell.setText("Premium Markets - Select a share.");
		
		{
			shareCarGroup = new Group(dialogShell, SWT.NONE);
			GridLayout portfolioBoutonsGroupLayout = new GridLayout();
			portfolioBoutonsGroupLayout.numColumns = 2;
			shareCarGroup.setLayout(portfolioBoutonsGroupLayout);
			GridData portfolioBoutonsGroupLData = new GridData(GridData.FILL_BOTH);
			shareCarGroup.setLayoutData(portfolioBoutonsGroupLData);
			//shareCarGroup.setText("Current market is "+MainPMScmd.getPrefs().get("quotes.listprovider", "euronext")+". Use the Quotations and Settings menus to refresh or load a new market.");
			shareCarGroup.setFont(MainGui.DEFAULTFONT);
			{
				symbolLabel = new Label(shareCarGroup, SWT.BORDER);
				symbolLabel.setText("Stock :");
				symbolLabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				symbolLabel.setLayoutData(newPortfoliolabelLData);
			}
			{
				symbolTable = new Table(shareCarGroup, SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);
				symbolTable.setFont(MainGui.DEFAULTFONT);
				symbolTable.setHeaderVisible(true);
				for (int j = 0; j < Titles.values().length; j++) {
					TableColumn column = new TableColumn(symbolTable, SWT.NONE);
					column.setText(Titles.values()[j].toString());
				}
				
				stockList = new StockList();
				stockList.addAll(DataSource.getInstance().loadAllStocks());
				
				for (PortfolioShare portfolioShare : alreadyBought) {
					stockList.remove(portfolioShare.getStock());
				}
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
				symbolTable.setBounds(0, 0, 100, 100);
				symbolTable.addFocusListener(new FocusListener() {
					public void focusGained(FocusEvent arg0) {
						// TODO Auto-generated method stub
					}

					public void focusLost(FocusEvent arg0) {
						//combo.dispose();
					}
				});
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_BOTH);
				newPortfoliolabelLData.heightHint = 800;
				symbolTable.setLayoutData(newPortfoliolabelLData);
			}
			{
				quantityLabel = new Label(shareCarGroup, SWT.BORDER);
				quantityLabel.setText("Quantity :");
				quantityLabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				quantityLabel.setLayoutData(newPortfoliolabelLData);
			}
			{
				quantityText = new Text(shareCarGroup, SWT.BORDER);
				GridData newShareTextLData = new GridData(GridData.FILL_HORIZONTAL);
				quantityText.setLayoutData(newShareTextLData);
				quantityText.setFont(MainGui.CONTENTFONT);
				quantityText.setText(this.selectedQuantity.toString());
			}
			{
				monitorLabel = new Label(shareCarGroup, SWT.BORDER);
				monitorLabel.setText("Monitor level :");
				monitorLabel.setFont(MainGui.DEFAULTFONT);
				GridData newPortfoliolabelLData = new GridData(GridData.FILL_HORIZONTAL);
				monitorLabel.setLayoutData(newPortfoliolabelLData);
			}
			{
				moniCombo = new CCombo(shareCarGroup, SWT.READ_ONLY);
				moniCombo.setFont(MainGui.DEFAULTFONT);
				for (int j = 0, n = MonitorLevel.values().length; j < n; j++) {
					moniCombo.add(MonitorLevel.values()[j].getMonitorLevel());
				}
				moniCombo.select(moniCombo.indexOf(MonitorLevel.ANY.getMonitorLevel()));
				moniCombo.addFocusListener(new FocusListener() {
					public void focusGained(FocusEvent arg0) {
						// TODO Auto-generated method stub
					}

					public void focusLost(FocusEvent arg0) {
						//combo.dispose();
					}
				});
			}
			{
				newPortfollioValidateButton = new Button(shareCarGroup, SWT.BORDER);
				GridData newShareValidateButtonLData = new GridData(GridData.HORIZONTAL_ALIGN_END);
				newShareValidateButtonLData.horizontalSpan = 2;
				newPortfollioValidateButton.setLayoutData(newShareValidateButtonLData);
				newPortfollioValidateButton.setText("Ok");
				newPortfollioValidateButton.setFont(MainGui.DEFAULTFONT);
				newPortfollioValidateButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
						newPortfollioItemValidateButtonMouseDown(evt);
					}
				});
			}
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
	private void newPortfollioItemValidateButtonMouseDown(MouseEvent evt) {
		
		selectedStocks = new ArrayList<Stock>();
		int[] selections = symbolTable.getSelectionIndices();
		for (int i = 0; i < selections.length; i++) {
				selectedStocks.add(stockList.get(selections[i]));
		}
		selectedQuantity = new BigDecimal(quantityText.getText());
		selectedMonitorLevel = MonitorLevel.valueOfString(moniCombo.getText());
		dialogShell.dispose();
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
