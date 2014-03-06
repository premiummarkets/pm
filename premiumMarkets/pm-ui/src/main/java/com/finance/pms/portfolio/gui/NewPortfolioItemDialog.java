/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.AutoCompletePopupMenu;
import com.finance.pms.CursorFactory;
import com.finance.pms.MainGui;
import com.finance.pms.UserDialog;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.portfolio.MonitorLevel;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.PortfolioShare;

/**
 * The Class NewPortfolioItemDialog.
 * 
 * @author Guillaume Thoreton
 */
public class NewPortfolioItemDialog extends org.eclipse.swt.widgets.Composite {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(ActionDialogForm.class);
	
	private static NewPortfolioItemDialog runningInst = null;
	private static Font biggerFont;
	enum Titles {Symbol,Isin,Name,Category};
	
	protected Composite caller;

	
	private Providers selectedProvider;
	private Composite listCmp;
	private Table symbolTable;

	private CCombo moniCombo;

	protected StockList stockList;
	protected Composite ctrlComposite;

	public NewPortfolioItemDialog(Composite parent, int style, Composite caller) {
		
		super(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | style);
		
		this.stockList = new StockList();
		this.caller = caller;
		biggerFont =  MainGui.DEFAULTFONT;
	}

	public static NewPortfolioItemDialog showUI(int tabIdx, Collection<PortfolioShare> alreadyScanned, Shell shell, PortfolioComposite composite) {
		
		if (NewPortfolioItemDialog.runningInst != null && !NewPortfolioItemDialog.runningInst.isDisposed()) {
			NewPortfolioItemDialog.runningInst.forceFocus();
			return runningInst;
		}

		NewPortfolioItemDialog inst = null;
		try {
			Shell piShell = new Shell(shell, SWT.DIALOG_TRIM|SWT.RESIZE);
			piShell.setText("Premium Markets - Share selection.");
			piShell.setFont(MainGui.DEFAULTFONT);
			piShell.setLayout(new FillLayout(SWT.VERTICAL));

			inst = new NewPortfolioItemDialog(piShell, SWT.RESIZE, composite);
			inst.initGui(SWT.MULTI);

			piShell.layout();
			piShell.pack();				
			piShell.open();
			
			runningInst = inst;


		} catch (Exception e) {

			try {
				LOGGER.error(e,e);
				if (inst != null) inst.dispose();
			} finally {
				inst = null;
			}

		}
		
		return inst;
	}
	
	public void initGui(int stockSelectionMode) {
		this.initGui(stockSelectionMode, true);
	}

	protected void initGui(int stockSelectionMode, Boolean displayControls) {
		
		this.setLayout(new GridLayout());
		this.setBackground(MainGui.pOPUP_BG);
		
		//Auto complete
		{
			final Group autocompleteGroup = new Group(this, SWT.NONE);
			autocompleteGroup.setLayoutData(new GridData(SWT.FILL,SWT.TOP,true,true));
			autocompleteGroup.setLayout(new GridLayout());
			
			autocompleteGroup.setBackground(MainGui.pOPUP_GRP);
			autocompleteGroup.setFont(biggerFont);
			autocompleteGroup.setText("Find a share");
			String toolTiptxt = "Search for a stock in lists existing in your database. You can update these lists using the 'Stock lists and Markets' menu";
			autocompleteGroup.setToolTipText(toolTiptxt);
			
//			final Text text = new Text(autocompleteGroup, SWT.SINGLE | SWT.BORDER);
//			text.setLayoutData(new GridData(SWT.FILL,SWT.TOP, true, false));
//			text.setToolTipText(toolTiptxt);
//			text.setFont(MainGui.CONTENTFONT);
//			{
//				final Shell popupShell = new Shell(getShell(), SWT.ON_TOP);
//				getShell().addListener(SWT.Traverse, new Listener() {
//					public void handleEvent(Event event) {
//						switch (event.detail) {
//						case SWT.TRAVERSE_ESCAPE:
//							event.detail = SWT.TRAVERSE_NONE;
//							event.doit = false;
//							break;
//						}
//					}
//				});
//				getShell().addShellListener(new ShellAdapter() {
//					@Override
//					public void shellClosed(ShellEvent evt) {
//						if (!popupShell.isDisposed()) popupShell.dispose();
//					}
//				});
//				popupShell.setLayout(new FillLayout());
//				final Table table = new Table(popupShell, SWT.SINGLE);
//				table.setFont(MainGui.CONTENTFONT);
//				
//				text.addListener(SWT.KeyDown, new Listener() {
//					public void handleEvent(Event event) {
//						switch (event.keyCode) {
//							case SWT.ARROW_DOWN:
//								if (popupShell.isVisible() && table.getItemCount() != 0) {
//									int index = (table.getSelectionIndex() + 1) % table.getItemCount();
//									table.setSelection(index);
//									event.doit = false;
//								}
//								break;
//							case SWT.ARROW_UP:
//								if (popupShell.isVisible() && table.getItemCount() != 0) {
//									int index = table.getSelectionIndex() - 1;
//									if (index < 0) index = table.getItemCount() - 1;
//									table.setSelection(index);
//									event.doit = false;
//								}
//								break;
//							case SWT.CR:
//								if (popupShell.isVisible() && table.getSelectionIndex() != -1) {
//									text.setText(table.getSelection()[0].getText());
//									popupShell.setVisible(false);
//								}
//								break;
//							case SWT.ESC:
//								popupShell.setVisible(false);
//								break;
//						}
//					}
//				});
//				text.addListener(SWT.Modify, new Listener() {
//					public void handleEvent(Event event) {
//						final String string = text.getText();
//						if (string.length() == 0) {
//							popupShell.setVisible(false);
//						} else {
//							List<Stock> likeShares = DataSource.getInstance().getShareDAO().loadSharesLike(string, 50);
//							
//							TableItem[] items = table.getItems();
//							for (int i = 0; i < likeShares.size(); i++) {
//								if(i < items.length) {
//									items[i].setText(likeShares.get(i).getFriendlyName());
//								} else {
//									TableItem tableItem = new TableItem(table, SWT.NONE);
//									tableItem.setText(likeShares.get(i).getFriendlyName());
//								}
//							}
//							table.remove(likeShares.size(), table.getItems().length-1);
//							
//							if (table.getItems().length > 0) {
//								table.select(0);
//							}
//							
//							Rectangle textBounds = getDisplay().map(autocompleteGroup, null, text.getBounds());
//							popupShell.setBounds(textBounds.x, textBounds.y + textBounds.height, textBounds.width, 150);
//							popupShell.setVisible(true);
//						}
//					}
//				});
//
//				table.addListener(SWT.DefaultSelection, new Listener() {
//					public void handleEvent(Event event) {
//						text.setText(table.getSelection()[0].getText());
//						popupShell.setVisible(false);
//					}
//				});
//				table.addListener(SWT.KeyDown, new Listener() {
//					public void handleEvent(Event event) {
//						if (event.keyCode == SWT.ESC) {
//							popupShell.setVisible(false);
//						}
//					}
//				});
//
//				Listener focusOutListener = new Listener() {
//					public void handleEvent(Event event) {
//						/* async is needed to wait until focus reaches its new Control */
//						getDisplay().asyncExec(new Runnable() {
//							public void run() {
//								if (popupShell.isDisposed() || getDisplay().isDisposed()) return;
//								Control control = getDisplay().getFocusControl();
//								if (control == null || (control != text && control != table) && control != popupShell) {
//									popupShell.setVisible(false);
//								}
//							}
//						});
//					}
//				};
//				table.addListener(SWT.FocusOut, focusOutListener);
//				text.addListener(SWT.FocusOut, focusOutListener);
//
//				getShell().addListener(SWT.Move, new Listener() {
//					public void handleEvent(Event event) {
//						popupShell.setVisible(false);
//					}
//				});
//			}
		
			{
				Text textBox = new Text(autocompleteGroup, SWT.SINGLE | SWT.BORDER);
				textBox.setLayoutData(new GridData(SWT.FILL,SWT.TOP, true, false));
				textBox.setToolTipText(toolTiptxt);
				textBox.setFont(MainGui.CONTENTFONT);
				
				final AutoCompletePopupMenu<Stock> autoCompletePopupMenu = new AutoCompletePopupMenu<Stock>(getShell(), autocompleteGroup, textBox) {

					@Override
					public String transalateALike(Stock alike) {
						return alike.getFriendlyName();
					}

					@Override
					public List<Stock> loadAlikes(String typedInString) {
						List<Stock> alikes = DataSource.getInstance().getShareDAO().loadSharesLike(typedInString, 50);
						return alikes;
					}
					
				};
				autoCompletePopupMenu.initPopupMenu();
				
				Button autoCompletAddBut = new Button(autocompleteGroup, SWT.PUSH);
				autoCompletAddBut.setLayoutData(new GridData(SWT.END,SWT.TOP,false,false));

				String addSearchLabel = addSearchSelectionLabel();
				autoCompletAddBut.setText(addSearchLabel);
				autoCompletAddBut.setFont(MainGui.DEFAULTFONT);
				autoCompletAddBut.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
						addSearchSelection(autoCompletePopupMenu.getTextBox());
					}

				});
			}
		}
		
		//List selection
		List<String> loadShareListNames = PortfolioMgr.getInstance().getPortfolioDAO().loadShareListNames();
		{
			Group sharelistSelectionGroup = new Group(this, SWT.NONE);
			sharelistSelectionGroup.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
			sharelistSelectionGroup.setLayout(new GridLayout());
			
			sharelistSelectionGroup.setBackground(MainGui.pOPUP_GRP);
			sharelistSelectionGroup.setFont(biggerFont);
			sharelistSelectionGroup.setText("Or use your selection lists");
			sharelistSelectionGroup.setToolTipText("Select stocks in lists existing in your database. You can update these lists using the 'Stock lists and Markets' menu");
			
			{
				final CCombo existingCCombo = new CCombo(sharelistSelectionGroup, SWT.NONE);
				existingCCombo.setLayoutData(new GridData(SWT.FILL,SWT.TOP,true, false));
				
				existingCCombo.setToolTipText("Select stocks in lists existing in your database. You can update these lists using the 'Stock lists and Markets' menu");
				existingCCombo.setFont(MainGui.CONTENTFONT);
				for (String shareListName : loadShareListNames) {
					existingCCombo.add(shareListName);
				}

				final Group shareListGroup = new Group(sharelistSelectionGroup, SWT.NONE);
				shareListGroup.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, true));
				shareListGroup.setLayout(new GridLayout());
				
				shareListGroup.setBackground(MainGui.pOPUP_GRP);
				shareListGroup.setText("Select your shares");
				shareListGroup.setToolTipText("Double click on the headers then CTRL F to search. You can update these lists using the 'Stock lists and Markets' menu");
				shareListGroup.setFont(biggerFont);
				{
					listCmp = new Composite(shareListGroup, SWT.NONE);
					GridData listCmpL = new GridData(SWT.FILL,SWT.FILL, true, true);
					listCmpL.heightHint = 200;
					listCmp.setLayoutData(listCmpL);
					listCmp.setLayout(new GridLayout());
					
					symbolTable = new Table(listCmp, SWT.V_SCROLL | stockSelectionMode | SWT.FULL_SELECTION);
					GridData symbolTableL = new GridData(SWT.FILL, SWT.FILL, true, true);
					symbolTableL.exclude=true;
					symbolTable.setLayoutData(symbolTableL); 
					
					symbolTable.setFont(MainGui.DEFAULTFONT);
					symbolTable.setHeaderVisible(true);	
					for (int j = 0; j < Titles.values().length; j++) {
						TableColumn column = new TableColumn(symbolTable, SWT.NONE);
						column.setText(Titles.values()[j].toString());
					}
					symbolTable.getVerticalBar().setVisible(true);
					symbolTable.setToolTipText("Columns can be sorted by double clicking on the header. Order one column and do CTRL F to search.");
					
				}
				shareListGroup.addControlListener(new ControlListener() {
					
					@Override
					public void controlResized(ControlEvent e) {
						updateTableSize();
					}
					
					@Override
					public void controlMoved(ControlEvent e) {
						updateTableSize();
						
					}
				});
				
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

						String item = existingCCombo.getItem(existingCCombo.getSelectionIndex());
						try {

							selectedProvider = Providers.setupProvider(item);
							shareListGroup.setText("Select shares in "+selectedProvider.getSharesListIdEnum().getDescription());

							getShell().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
							try {
								stockList= new StockList(PortfolioMgr.getInstance().getPortfolioDAO().loadShareList(item).toSortedStocksSet());
								initShareScrollList();
							} finally {
								getShell().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
							}
							
					
						} catch (java.lang.IllegalArgumentException e) {
							UserDialog dialog = new UserDialog(NewPortfolioItemDialog.this.getShell(), item+" is not a valid share list. Has it been added by hand?", null);
							dialog.open();

						}
					}

				});	
				
			}
			{
				Button toListSelectAddBut = new Button(sharelistSelectionGroup, SWT.PUSH);
				toListSelectAddBut.setLayoutData(new GridData(SWT.END, SWT.TOP, false, false));
				
				toListSelectAddBut.setText(addListLabel());
				toListSelectAddBut.setFont(MainGui.DEFAULTFONT);
				toListSelectAddBut.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
						addListSelection();
					}

				});
			}
			
		}
		
		
		//Add ctrl composite
		if (displayControls)
		{
		    
			ctrlComposite = new Composite(this, SWT.NONE);
			ctrlComposite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
			GridLayout ctrlCompositeL = new GridLayout();
			ctrlCompositeL.marginHeight=0;
			ctrlCompositeL.marginWidth=0;
			ctrlComposite.setLayout(ctrlCompositeL);
			
			ctrlComposite.setBackground(MainGui.pOPUP_BG);
			{
				Group optionsGrp = new Group(ctrlComposite, SWT.NONE);
				optionsGrp.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
				
				optionsGrp.setBackground(MainGui.pOPUP_GRP);
				optionsGrp.setText("Options");
				optionsGrp.setFont(biggerFont);
				RowLayout addOptGroupL = new RowLayout(SWT.HORIZONTAL);
				addOptGroupL.justify=true;
				optionsGrp.setLayout(addOptGroupL);
//				{
//					Label quantityLabel = new Label(optionsGrp, SWT.NONE);
//					quantityLabel.setBackground(MainGui.pOPUP_GRP);
//					quantityLabel.setText("Quantity :");
//					quantityLabel.setFont(MainGui.DEFAULTFONT);
//				}
//				{
//					quantityText = new Text(optionsGrp, SWT.BORDER);
//					RowData rowData = new RowData(100,SWT.DEFAULT);
//					quantityText.setLayoutData(rowData);
//					quantityText.setFont(MainGui.CONTENTFONT);
//					quantityText.setText(BigDecimal.ONE.toString());
//				}
				{
					Label monitorLabel = new Label(optionsGrp, SWT.NONE);
					monitorLabel.setBackground(MainGui.pOPUP_GRP);
					monitorLabel.setText("Monitor level :");
					monitorLabel.setFont(MainGui.DEFAULTFONT);
				}
				{
					moniCombo = new CCombo(optionsGrp, SWT.READ_ONLY|SWT.BORDER);
					moniCombo.setFont(MainGui.DEFAULTFONT);
					for (int j = 0, n = MonitorLevel.values().length; j < n; j++) {
						moniCombo.add(MonitorLevel.values()[j].getMonitorLevel());
					}
					moniCombo.select(moniCombo.indexOf(MonitorLevel.BEARISH.getMonitorLevel()));
				}
			}
			
		}

		this.pack();
		
		initShareScrollList();
	}

	protected String addSearchSelectionLabel() {
		return "Add Search selection to Current Portfolio";
	}

	protected String addListLabel() {
		return "Add List selection to Current Portfolio";
	}

	private void initShareScrollList() {
		
		symbolTable.removeAll();
		updateTableDisplay();
		for (int j = 0; j < Titles.values().length; j++) {
			symbolTable.getColumn(j).addSelectionListener(new SelectionListener() {
				
				public void widgetDefaultSelected(SelectionEvent arg0) {
					handle(arg0);
				}

				private void handle(SelectionEvent arg0) {
					LOGGER.debug("Column selected" + ((TableColumn) arg0.getSource()).getText());
					getShell().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
					try {
						sortColumn(((TableColumn) arg0.getSource()).getText());
					} finally {
						getShell().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
					}
					
				}

				public void widgetSelected(SelectionEvent arg0) {
					handle(arg0);
				}
				
			});
		}
		
		updateTableSize();
	}

	private void updateTableDisplay() {

		for (Stock s : stockList) {
			
			TableItem item = new TableItem(symbolTable, SWT.NONE);
			item.setFont(MainGui.CONTENTFONT);
			item.setText(0, s.getSymbol());
			item.setText(1, s.getIsin());
			item.setText(2, s.getName());
			item.setText(3, s.getCategory().getCategory());
		}

		for (int j = 0; j < Titles.values().length; j++) {
			
			if (Titles.values()[j].equals(Titles.Name)) {
				symbolTable.getColumn(j).setWidth(100);
			} else {
				symbolTable.getColumn(j).pack();
			}
		}
	}

	private void sortColumn(String colStr) {
		
		int colNum = Titles.valueOf(colStr).ordinal();
		
		switch (colNum) {
		case 0:
			Collections.sort(stockList,new Comparator<Stock>() {
				public int compare(Stock o1, Stock o2) {
					int ret = o1.getSymbol().trim().toLowerCase().compareTo(o2.getSymbol().trim().toLowerCase());
					ret = (ret == 0)? o1.getName().trim().toLowerCase().compareTo(o2.getName().trim().toLowerCase()):ret;
					ret = (ret == 0)? o1.getCategory().getCategory().compareTo(o2.getCategory().getCategory()):ret;
					return ret;
				}						
			});
			break;
		case 1:
			Collections.sort(stockList,new Comparator<Stock>() {
				public int compare(Stock o1, Stock o2) {
					int ret = o1.getIsin().trim().toLowerCase().compareTo(o2.getIsin().trim().toLowerCase());
					ret = (ret == 0)? o1.getName().trim().toLowerCase().compareTo(o2.getName().trim().toLowerCase()):ret;
					ret = (ret == 0)? o1.getCategory().getCategory().compareTo(o2.getCategory().getCategory()):ret;
					return ret;
				}						
			});
			break;
		case 2:
			Collections.sort(stockList,new Comparator<Stock>() {
				public int compare(Stock o1, Stock o2) {
					int ret = o1.getName().trim().toLowerCase().compareTo(o2.getName().trim().toLowerCase());
					ret = (ret == 0)? o1.getSymbol().trim().toLowerCase().compareTo(o2.getSymbol().trim().toLowerCase()):ret;
					ret = (ret == 0)? o1.getCategory().getCategory().compareTo(o2.getCategory().getCategory()):ret;
					return ret;
				}						
			});
			break;
		case 3:
			Collections.sort(stockList,new Comparator<Stock>() {
				public int compare(Stock o1, Stock o2) {
					int ret = o1.getCategory().getCategory().compareTo(o2.getCategory().getCategory());
					ret = (ret == 0)? o1.getName().trim().toLowerCase().compareTo(o2.getName().trim().toLowerCase()):ret;
					ret = (ret == 0)? o1.getSymbol().trim().toLowerCase().compareTo(o2.getSymbol().trim().toLowerCase()):ret;
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

	private void addSearchSelection(final Text text) {
		try {
			try {
				getShell().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
				String autoCompleteTxt = text.getText();
				Pattern pattern = Pattern.compile(".*\\((.*) / (.*)\\)");
				Matcher matcher = pattern.matcher(autoCompleteTxt);
				matcher.matches();
				Stock stock = DataSource.getInstance().getShareDAO().loadStockBy(matcher.group(1), matcher.group(2));
				Set<Stock> stocks = new HashSet<Stock>();
				stocks.add(stock);
				addSelection(stocks);
			} finally {
				getShell().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
			}
			
		} catch (IllegalStateException e) {
			LOGGER.warn(e,e);
			UserDialog inst = new UserDialog(getShell(), "Invalid share description\n" +
			"To search for a share, type the first letters of its name, symbol or isin and select it in the drop down box.\n" +
			"If the share you are looking for is not present, use the 'Stock lists and Markets' menu to add it.",
					null);
			inst.open();
		} catch (Exception e) {
			LOGGER.error(e,e);
			UserDialog inst = new UserDialog(getShell(), "Error adding share.\nInvalid share description\n"+e,null);
			inst.open();
		}
	}
	
	protected void addSelection(Set<Stock> stocks) {
		//addAction(stocks, new BigDecimal(quantityText.getText()), MonitorLevel.valueOfString(moniCombo.getText()));
		addAction(stocks, BigDecimal.ZERO, MonitorLevel.valueOfString(moniCombo.getText()));
	} 

	private void addListSelection() {
		try {
			try {
				getShell().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
				addSelection(selection());
			} finally {
				getShell().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
			}
			
		} catch (Exception e) {
			LOGGER.error(e,e);
			UserDialog inst = new UserDialog(getShell(), "Error adding share. \n"+e,null);
			inst.open();
		}
		symbolTable.deselectAll();
	}


	private void addAction(Set<Stock> stocks, BigDecimal quantity, MonitorLevel monitorLevel) {
		PortfolioComposite portfolioComposite = (PortfolioComposite) caller;
		int currentTabSelection = portfolioComposite.getCurrentTabSelection();
		if (currentTabSelection != -1) {
			portfolioComposite.addShares(currentTabSelection, stocks, quantity, monitorLevel);
			portfolioComposite.refreshPortfolioTotalsInfos(currentTabSelection);
		}
	}
	


	private Set<Stock> selection() {
		Set<Stock> selectedStocks = new HashSet<Stock>();
		int[] selections = symbolTable.getSelectionIndices();
		for (int i = 0; i < selections.length; i++) {
			selectedStocks.add(stockList.get(selections[i]));
		}	
		symbolTable.deselectAll();
		return selectedStocks;
	}

	private void updateTableSize() {
		Point computedSize = listCmp.getSize();
		symbolTable.setSize(computedSize.x, computedSize.y);
		symbolTable.layout();
	}
}
