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
package com.finance.pms.events.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Caret;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.ActionDialog;
import com.finance.pms.ActionDialogAction;
import com.finance.pms.AutoCompletePopupMenu;
import com.finance.pms.CursorFactory;
import com.finance.pms.MainGui;
import com.finance.pms.MainPMScmd;
import com.finance.pms.RefreshableView;
import com.finance.pms.SpringContext;
import com.finance.pms.UserDialog;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.config.IndicatorsConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.EventModel;
import com.finance.pms.datasources.EventRefreshController;
import com.finance.pms.datasources.RefreshFourToutStrategyEngine;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.antlr.ANTLROperationsParserHelper;
import com.finance.pms.events.calculation.antlr.ANTLRParserHelper;
import com.finance.pms.events.calculation.antlr.Alternative;
import com.finance.pms.events.calculation.antlr.EditorLexerDelegate;
import com.finance.pms.events.calculation.antlr.EditorOpsLexerDelegate;
import com.finance.pms.events.calculation.antlr.NextToken;
import com.finance.pms.events.calculation.antlr.NextToken.TokenType;
import com.finance.pms.events.calculation.antlr.ParameterizedBuilder;
import com.finance.pms.events.calculation.antlr.ParameterizedBuilder.InUseException;
import com.finance.pms.events.calculation.antlr.ParameterizedBuilder.ObsMsgType;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.conditional.EventInfoOpsCompoOperation;
import com.finance.pms.portfolio.ShareListMgr;
import com.finance.pms.portfolio.gui.ActionDialogForm;
import com.finance.pms.threads.ConfigThreadLocal;

public class OperationBuilderComposite extends Composite {

	protected static final int BUTTONS_COLS_SPAN = 4;

	public static MyLogger LOGGER = MyLogger.getLogger(OperationBuilderComposite.class);

	private UserDialog dialog;
	private ActionDialog actionDialog;

	protected ParameterizedBuilder parameterizedBuilder;
	protected RefreshableView mainGuiParent;

	private Table tokenAltsTable;
	private Shell popupShell;
	private TableColumn[] tokenAltsColumns;

	private ComboUpdateMonitor comboUpdateMonitor = new ComboUpdateMonitor();
	Editor editorHolder;

	private Map<Class<? extends Object>, Object> editorListeners;
	
	public static void main(String... args) {

		try (SpringContext springContext = new SpringContext(args[0])) {
			springContext.loadBeans("/connexions.xml", "/swtclients.xml", "/talibanalysisservices.xml");
			springContext.refresh();

			ShareListMgr shareListMgr = (ShareListMgr) SpringContext.getSingleton().getBean("shareListMgr");
			ConfigThreadLocal.set(EventSignalConfig.EVENT_SIGNAL_NAME, shareListMgr.initPkgDependentConfig());
			ConfigThreadLocal.set("indicatorParams", new IndicatorsConfig());
			springContext.optionalPostInit();

			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, new EventSignalConfig());

			final Shell shell = new Shell(Display.getCurrent(), SWT.DIALOG_TRIM | SWT.RESIZE);
			shell.setText("Customise and Create indicators ...");
			shell.setLayout(new GridLayout());

			//IndicatorBuilderComposite builderComposite = new IndicatorBuilderComposite(shell, null, new ComboUpdateMonitor());
			OperationBuilderComposite builderComposite = new OperationBuilderComposite(shell, null);

			builderComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			builderComposite.parameterizedBuilder.updateEditableOperationLists();
			builderComposite.mainGuiParent = new RefreshableView() {

				@Override
				public void refreshView(List<Exception> exceptions) {
					// TODO Auto-generated method stub
				}

				@Override
				public void initRefreshAction() {
					// TODO Auto-generated method stub
				}

				@Override
				public Date getAnalysisStartDate() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public Date getAnalysisEndDate() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void endRefreshAction(List<Exception> exceptions) {
					// TODO Auto-generated method stub
				}

				@Override
				public void setCursor(Cursor cursor) {
					// TODO Auto-generated method stub
				}
			};

			shell.addShellListener(new ShellAdapter() {
				@Override
				public void shellClosed(ShellEvent evt) {
					shell.dispose();
				}
			});
			
			MainGui.setupAppDefaultFont(shell.getDisplay(), shell);
			MainGui.setupAppDefaultColors(shell.getDisplay());

			shell.layout();
			shell.open();
			Display display = shell.getDisplay();
			while (!shell.isDisposed()) {
				try {
					if (!display.readAndDispatch())
						display.sleep();
				} catch (RuntimeException e) {
					e.printStackTrace();
				} catch (Error e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//private Label errorLabel;
	//protected Text formulaReferenceTxt;
	//protected StyledText editor;
	//protected Boolean isSaved;
	public class Editor {

		private Label errorLabel;
		private Text formulaReferenceTxt;
		private StyledText editor;
		
		protected Label getErrorLabel() {
			return errorLabel;
		}
		
		protected StyledText getEditor() {
			return editor;
		}
		
		protected Editor(Label errorLabel, Text formulaReferenceTxt, StyledText editor) {
			super();
			this.errorLabel = errorLabel;
			this.formulaReferenceTxt = formulaReferenceTxt;
			this.editor = editor;
		}
		
		protected Boolean isSaved(String id) {
			if (id == null || id.isBlank()) return true;
			return !hasChanged(id);
		};
		
		protected String getFormatedReferenceTxt() {
			return formulaReferenceTxt.getText();
		}
		
		protected void forceSelection(String selected, Boolean saveCurrent) {
			if (saveCurrent) {
				handleSave((String) formulaReferenceTxt.getData());
			}
			clearEditor();
			
			formulaReferenceTxt.setData(selected);
			if (selected != null) formulaReferenceTxt.setText(selected);
			
			if (selected != null && !selected.isBlank()) {
				Operation operation = parameterizedBuilder.getUserCurrentOperations().get(selected);
				if (operation != null) {
					setEditorText(operation.getFormulae());
					setErrorLabel("");
				} 
			}
		}

		protected void clearEditor() {
			formulaReferenceTxt.setData("");
			formulaReferenceTxt.setText("");
			setEditorText("");
			setErrorLabel("");
		}
		
		private Boolean hasChanged(String currentSelection) {
			Boolean hasChanged = false;
			Map<String, Operation> currentOperations = parameterizedBuilder.getUserCurrentOperations();
			if (currentSelection != null && !currentSelection.isBlank() && !getEditor().getText().isBlank()) {
				Operation oldOp = currentOperations.get(currentSelection);
				if (oldOp != null) {
					String oldFormula = oldOp.getFormulae();
					if (oldFormula == null || !oldFormula.equals(editor.getText())) {
						hasChanged = true;
					}
				} else {
					hasChanged = true;
				}
			}
			return hasChanged;
		}
		
	}

	public OperationBuilderComposite(Composite parent, MainGui mainGui) {

		super(parent, SWT.NONE);
		parameterizedBuilder = initParameterizedBuilder();
		this.mainGuiParent = mainGui;
		initGui();

		this.addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent e) {
				// TODO warning on unsaved?
			}

		});

	}

	protected ParameterizedBuilder initParameterizedBuilder() {
		return (ParameterizedBuilder) SpringContext.getSingleton().getBean("parameterizedOperationBuilder");
	}

	private void initGui() {

		GridLayout layout = new GridLayout();
		layout.numColumns = BUTTONS_COLS_SPAN;
		this.setLayout(layout);
		//this.setSize(SWT.DEFAULT, 300);
		
		{//TODO move to Editor holder??
			Text formulaReferenceTxt;
			{
				Label formulaReferenceLabel = new Label(this, SWT.NONE);
				GridData labelLayoutData = new GridData(SWT.FILL, SWT.TOP, true, false);
				labelLayoutData.horizontalSpan = BUTTONS_COLS_SPAN;
				formulaReferenceLabel.setLayoutData(labelLayoutData);
				formulaReferenceLabel.setText(formulaReferenceLabelTxt());
				formulaReferenceLabel.setFont(MainGui.DEFAULTFONT);
				formulaReferenceLabel.setBackground(MainGui.pOPUP_BG);
				
				formulaReferenceTxt = new Text(this, SWT.SINGLE | SWT.BORDER);
				GridData refLayoutData = new GridData(SWT.FILL, SWT.TOP, true, false);
				refLayoutData.horizontalSpan = BUTTONS_COLS_SPAN;
				formulaReferenceTxt.setLayoutData(refLayoutData);
				formulaReferenceTxt.setFont(MainGui.CONTENTFONT);
				String siteUrl = MainPMScmd.getMyPrefs().get("site.url", "none.com");
				formulaReferenceTxt.setToolTipText("Help available at http://"+siteUrl+"/html/swtui.html#UserGuidances");
				
				AutoCompletePopupMenu<Operation> autoCompletePopupMenu = new AutoCompletePopupMenu<Operation>(getParent().getShell(), this, formulaReferenceTxt) {
	
					@Override
					public String translateALike(Operation alike) {
						return alike.getReference();
					}
	
					@Override
					public List<Operation> loadAlikes(String typedInString) {
						Map<String, Operation> allOps = parameterizedBuilder.getThisParserCompliantUserCurrentOperations();
						final String fTypedInString;
						if (typedInString == null) {
							fTypedInString = ".*";
						} else {
							fTypedInString = ".*" + typedInString + ".*";
						}
						List<Operation> itemSet = allOps.values().stream()
								.filter(o -> o.getReference().toLowerCase().matches(fTypedInString.toLowerCase()))
								.sorted((o1, o2) -> o1.getReference().compareTo(o2.getReference()))
								.collect(Collectors.toList());
						return itemSet;
					}

					@Override
					public void selectionAction(String typedInString) {
						editorHolder.forceSelection(editorHolder.getFormatedReferenceTxt(), true);
					}
				};
				autoCompletePopupMenu.initPopupMenu();
				
				formulaReferenceTxt.addListener(SWT.FocusIn, new Listener() {
					public void handleEvent(Event event) {
						if (!editorHolder.isSaved(editorHolder.getFormatedReferenceTxt())) {
							handleSave(editorHolder.getFormatedReferenceTxt());
							editorHolder.clearEditor();
							event.doit = false;
						}
					}
				});
			}
			Label errorLabel;
			StyledText editor;
			{
				Label editorLabel = new Label(this, SWT.NONE);
				GridData labelLayoutData = new GridData(SWT.FILL, SWT.TOP, true, false);
				labelLayoutData.horizontalSpan = BUTTONS_COLS_SPAN;
				editorLabel.setLayoutData(labelLayoutData);
				editorLabel.setText("Type in your formula (Alt+DownArrow for completion)");
				editorLabel.setFont(MainGui.DEFAULTFONT);
				editorLabel.setBackground(MainGui.pOPUP_BG);
	
				errorLabel = new Label(this, SWT.WRAP);
				GridData errorLabelLayoutData = new GridData(SWT.FILL, SWT.TOP, true, false);
				errorLabelLayoutData.horizontalSpan = BUTTONS_COLS_SPAN;
				errorLabelLayoutData.widthHint = 100;
				errorLabel.setLayoutData(errorLabelLayoutData);
				errorLabel.setText("");
				errorLabel.setFont(MainGui.CONTENTFONT);
				errorLabel.setForeground(getDisplay().getSystemColor(SWT.COLOR_RED));
	
				editor = new StyledText(this, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
				GridData editorLayoutData = new GridData(SWT.FILL, SWT.TOP, true, true);
				editorLayoutData.horizontalSpan = BUTTONS_COLS_SPAN;
				editorLayoutData.heightHint = editorHeight();
				editor.setLayoutData(editorLayoutData);
				editor.setFont(MainGui.CONTENTFONT);
			}
			this.editorHolder = new Editor(errorLabel, formulaReferenceTxt, editor);
			{
				editorListeners = new HashMap<Class<? extends Object>, Object>();
				initEditorPopup();
			}
		}
		//right columns
		{
			Button saveFormula = new Button(this, SWT.NONE);
			GridData layoutData = new GridData(SWT.BEGINNING, SWT.TOP, false, false);
			saveFormula.setLayoutData(layoutData);
			saveFormula.setText(saveButtonTxt());
			saveFormula.setFont(MainGui.DEFAULTFONT);
			saveFormula.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handle();
				}

				private void handle() {
					handleSave(editorHolder.getFormatedReferenceTxt());
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handle();
				}
			});
		}
		{
			Button renameFormula = new Button(this, SWT.NONE);
			GridData layoutData = new GridData(SWT.BEGINNING, SWT.TOP, true, false);
			//layoutData.horizontalSpan = 3;
			renameFormula.setLayoutData(layoutData);
			renameFormula.setText("Rename " + builderLabel());
			renameFormula.setFont(MainGui.DEFAULTFONT);
			renameFormula.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handle();
				}

				private void handle() {
					handleRename();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handle();
				}
			});
		}
		//left columns
		{
			Button deleteUnused = new Button(this, SWT.NONE);
			GridData layoutData = new GridData(SWT.BEGINNING, SWT.TOP, false, false);
			deleteUnused.setLayoutData(layoutData);
			deleteUnused.setText("Delete all unused and disabled");
			deleteUnused.setToolTipText("Unused and disabled formulas will be moved to the trashed folder.");
			deleteUnused.setFont(MainGui.DEFAULTFONT);
			deleteUnused.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handleDeleteUnused();
				}

				private void handleDeleteUnused() {

					openActionDialog(
							true, null, null, null, "Please, confirm the deletion of all unused/disabled " + builderLabel()+".",
							new ActionDialogAction() {
								@Override
								public void action() {
									try {
										OperationBuilderComposite.this.getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
										handleSave(editorHolder.getFormatedReferenceTxt());
										deleteAllUnused();
										updateOperationList(true, editorHolder.getFormatedReferenceTxt(), false);
									} finally {
										OperationBuilderComposite.this.getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
									}
								}
							},
							false);

				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handleDeleteUnused();
				}
			});
		}
		{
			Button deleteFormula = new Button(this, SWT.NONE);
			GridData layoutData = new GridData(SWT.BEGINNING, SWT.TOP, false, false);
			//layoutData.horizontalSpan = 3;
			deleteFormula.setLayoutData(layoutData);
			deleteFormula.setText("Delete " + builderLabel());
			deleteFormula.setFont(MainGui.DEFAULTFONT);
			deleteFormula.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handleDeleteOp();
				}

				private void handleDeleteOp() {
					openActionDialog(true, null, null, null, "Please, confirm the deletion of " + editorHolder.getFormatedReferenceTxt() + ".",
							new ActionDialogAction() {
								@Override
								public void action() {
									try {
										OperationBuilderComposite.this.getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
										try {
											deleteFormula(editorHolder.getFormatedReferenceTxt());
											updateOperationList(true, null, false);
										} finally {
											OperationBuilderComposite.this.getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
										}
									} finally {
										OperationBuilderComposite.this.getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
									}
								}
							},
							false);
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handleDeleteOp();
				}
			});
		}
		//right columns
		{
			Button checkInUse = new Button(this, SWT.NONE);
			GridData layoutData = new GridData(SWT.BEGINNING, SWT.TOP, false, false);
			//layoutData.horizontalSpan = 3;
			checkInUse.setLayoutData(layoutData);
			checkInUse.setText("Usage of " + builderLabel());
			checkInUse.setFont(MainGui.DEFAULTFONT);
			checkInUse.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handle();
				}

				private void handle() {
					handleCheckInUse();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handle();
				}
			});
		}
		addThisCompositeExtratButtons();

		updateOperationList(false, null, false);
		this.layout();

	}

	protected int editorHeight() {
		return 400;
	}

	protected void deleteAllUnused() {
		Map<String, Operation> allOps = parameterizedBuilder.getThisParserCompliantUserCurrentOperations();
		for (String opId : allOps.keySet()) {
			List<Operation> usingOps = parameterizedBuilder.removeFormula(opId);
			if (!usingOps.isEmpty()) {
				LOGGER.error(opId + " is in use, and can't be deleted, by " + usingOps.stream().map(o -> o.getReference()).reduce((a,o) -> a + ", " + o));
			}
		}
	}

	protected void enableEditor() {
		// Nothing
	}

	protected void initEditorPopup() {

		popupShell = new Shell(getShell(), SWT.ON_TOP | SWT.RESIZE | SWT.NO_TRIM);
		getShell().addListener(SWT.Traverse, new Listener() {
			public void handleEvent(Event event) {
				switch (event.detail) {
				case SWT.TRAVERSE_ESCAPE:
					event.detail = SWT.TRAVERSE_NONE;
					event.doit = false;
					break;
				}
			}
		});
		getShell().addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent evt) {
				if (!popupShell.isDisposed())
					popupShell.dispose();
			}
		});
		popupShell.setLayout(new FillLayout());
		//popupShell.setText("Hello");

		tokenAltsTable = new Table(popupShell, SWT.SINGLE);
		tokenAltsTable.setFont(MainGui.CONTENTFONT);
		tokenAltsTable.setHeaderVisible(true);
		tokenAltsColumns = new TableColumn[3];
		tokenAltsColumns[0] = new TableColumn(tokenAltsTable, SWT.LEFT);
		tokenAltsColumns[1] = new TableColumn(tokenAltsTable, SWT.LEFT);
		tokenAltsColumns[1].setText("description");
		tokenAltsColumns[2] = new TableColumn(tokenAltsTable, SWT.LEFT);
		tokenAltsColumns[2].setText("synoptic and defaults");

		if (editorListeners.get(VerifyKeyListener.class) != null) editorHolder.getEditor().removeVerifyKeyListener((VerifyKeyListener) editorListeners.get(VerifyKeyListener.class));
		VerifyKeyListener vkListener = new VerifyKeyListener() {

			@Override
			public void verifyKey(VerifyEvent event) {
				if (!popupShell.isDisposed() && popupShell.isVisible()) {
					switch (event.keyCode) {
					case SWT.ARROW_DOWN:
						int index = (tokenAltsTable.getSelectionIndex() + 1) % tokenAltsTable.getItemCount();
						tokenAltsTable.setSelection(index);
						event.doit = false;
						break;
					case SWT.ARROW_UP:
						index = tokenAltsTable.getSelectionIndex() - 1;
						if (index < 0)
							index = tokenAltsTable.getItemCount() - 1;
						tokenAltsTable.setSelection(index);
						event.doit = false;
						break;
					case SWT.CR:
						if (popupShell.isVisible() && tokenAltsTable.getSelectionIndex() != -1) {
							applyAutoCompleteSelection(tokenAltsTable);
							event.doit = false;
						}
						break;
					case SWT.ESC:
						popupShell.setVisible(false);
						break;
					default:
					}
				}
			}
		};
		editorListeners.put(VerifyKeyListener.class, vkListener);
		editorHolder.getEditor().addVerifyKeyListener(vkListener);

		if (editorListeners.get(KeyListener.class) != null) editorHolder.getEditor().removeKeyListener((KeyListener) editorListeners.get(KeyListener.class));
		KeyListener keyListener = new KeyListener() {

			@Override
			public void keyReleased(KeyEvent event) {
				if (!popupShell.isDisposed() && popupShell.isVisible()) {//Popup is visible
				} else { //Popup is hidden (we build the popup hidden just to validate the typing ..)
					LOGGER.info("buildPopupAlternatives key released popup hidden");
					buildPopupAlternatives(false);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (!popupShell.isDisposed() && popupShell.isVisible()) { //Popup is visible
					switch (e.keyCode) { 
					case SWT.ARROW_DOWN: //Popup menu actions
					case SWT.ARROW_UP:
					case SWT.CR:
					case SWT.ESC:
						break;	
					default:	//Rebuild the popup
						LOGGER.info("buildPopupAlternatives key released popup visible");
						buildPopupAlternatives(true);
					}
				} else { //Popup is hidden
					if ((e.stateMask & SWT.ALT) != 0) { //ALT Pressed
						if(e.keyCode == SWT.ARROW_DOWN) { //Space Pressed
							buildPopupAlternatives(true);
						}
					}
				}
			}
		};
		editorListeners.put(KeyListener.class, keyListener);
		editorHolder.getEditor().addKeyListener(keyListener);

		if (editorListeners.get(MouseListener.class) != null) editorHolder.getEditor().removeMouseListener((MouseListener) editorListeners.get(MouseListener.class));
		MouseListener mouseListener = new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
			}

			@Override
			public void mouseDown(MouseEvent event) {
				if (event.button == 3 && event.count == 1) {
					LOGGER.info("buildPopupAlternatives mouseDown");
					try {
						buildPopupAlternatives(true);
					} catch (Exception e1) {
						openDialog(true, "Invalid formula.", event);
					}
				}
			}

			@Override
			public void mouseDoubleClick(MouseEvent event) {
				if (event.button == 3 && event.count == 1) {
					LOGGER.info("buildPopupAlternatives mouseDoubleClick");
					try {
						buildPopupAlternatives(true);
					} catch (Exception e1) {
						openDialog(true, "Invalid formula.", event);
					}
				}
			}
		};
		editorListeners.put(MouseListener.class, mouseListener);
		editorHolder.getEditor().addMouseListener(mouseListener);

		tokenAltsTable.addListener(SWT.DefaultSelection, new Listener() {
			public void handleEvent(Event event) {
				applyAutoCompleteSelection(tokenAltsTable);
			}
		});
		tokenAltsTable.addListener(SWT.KeyDown, new Listener() {
			public void handleEvent(Event event) {
				switch (event.keyCode) {
				case SWT.ESC:
					getPopupShell().setVisible(false);
					break;
				case SWT.CR:
					applyAutoCompleteSelection(tokenAltsTable);
					event.doit = false;
					break;
				case SWT.ARROW_DOWN:
				case SWT.ARROW_UP:
					break;
				default:
					event.doit = false;
				}
			}
		});

		if (editorListeners.get(FocusListener.class) != null) editorHolder.getEditor().removeListener(SWT.FocusOut, (Listener) editorListeners.get(FocusListener.class));
		Listener focusOutListener = new Listener() {
			public void handleEvent(Event event) {
				/* async is needed to wait until focus reaches its new Control */
				getParent().getDisplay().asyncExec(new Runnable() {
					public void run() {
						if (popupShell.isDisposed() || getParent().getDisplay().isDisposed())
							return;
						Control control = getParent().getDisplay().getFocusControl();
						if (control == null || (control != editorHolder.getEditor() && control != tokenAltsTable) && control != popupShell) {
							popupShell.setVisible(false);
						}
					}
				});
			}
		};
		editorListeners.put(FocusListener.class, focusOutListener);
		editorHolder.getEditor().addListener(SWT.FocusOut, focusOutListener);

		getShell().addListener(SWT.Move, new Listener() {
			public void handleEvent(Event event) {
				getPopupShell().setVisible(false);
			}
		});

	}

	protected synchronized void handleSave(String currentReference) {
		if (!editorHolder.isSaved(currentReference)) {
			if (isValidId(currentReference)) {
				saveOrUpdateFormula(currentReference, true);
			}
		}
	}
	
	protected synchronized void handleCheckInUse() {

		String id = editorHolder.getFormatedReferenceTxt();
		
		Operation operation = parameterizedBuilder.getUserCurrentOperations().get(id);
		if (operation == null) {
			LOGGER.warn("No operation was found in User Current Operations for identifier: " + id);
		} else {
			List<Operation> using = parameterizedBuilder.checkInUse(operation);
			String UsingOperations = using.stream().filter(o -> !(o instanceof EventInfoOpsCompoOperation)).map(o -> o.getReference()).reduce((a, e) -> e + ", " + a).orElse("none");
			String UsingIndicators = using.stream().filter(o -> (o instanceof EventInfoOpsCompoOperation)).map(o -> o.getReference()).reduce((a, e) -> e + ", " + a).orElse("none");
			openDialog(true, "Operations: " + UsingOperations + "\nIndicators: " + UsingIndicators, null);
		}
		
	}
	
	protected synchronized void handleRename() {

		String oldId = editorHolder.getFormatedReferenceTxt();
		handleSave(oldId);
		
		ActionDialogForm renameActionDialog = new ActionDialogForm(getShell(), "Ok", null, "Rename");
		final Text newIdText = new Text(renameActionDialog.getParent(), SWT.NONE | SWT.CENTER | SWT.BORDER);
		newIdText.setText(oldId);
		
		ActionDialogAction renameAction = new ActionDialogAction() {
			@Override
			public void action() {
				
				renameActionDialog.values[0] = newIdText.getText();
				try {
					String newId = (String) renameActionDialog.values[0];
					parameterizedBuilder.renameFormula(oldId, newId);
					updateOperationList(true, newId, false);
					clearPreviousCalculationsUsing(oldId);
					refreshViews();		
				} catch (Exception e) {
					openDialog(true, "Can't rename.", e);
				}
				
			}
			
		};
		
		renameActionDialog.setControl(newIdText);
		renameActionDialog.setAction(renameAction);
		renameActionDialog.open();
		
	}

	protected void addThisCompositeExtratButtons() {
		{
			Button format = new Button(this, SWT.NONE);
			GridData layoutData = new GridData(SWT.BEGINNING, SWT.TOP, true, false);
			//layoutData.horizontalSpan = 3;
			format.setLayoutData(layoutData);
			format.setText("Format " + builderLabel());
			format.setFont(MainGui.DEFAULTFONT);
			format.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handle();
				}

				private void handle() {
					String identifier = editorHolder.getFormatedReferenceTxt();
					try {
						//handleSave(getToolTipText()); //We need to save make sure the operation is up to date first
						//Operation existingOp = parameterizedBuilder.getCurrentOperations().get(identifier);
						Operation existingOp = parameterizedBuilder.buildOneTimeOperation(identifier + "_formated", editorHolder.getEditor().getText());
						if (existingOp == null) throw new Exception("Invalid syntax.");
						
						String formulaeFormated = existingOp.toFormulaeFormated(80, o -> o.toFormulaeDevelopped());
						setEditorText(formulaeFormated);
						
						existingOp.semanticValidation();
						
					} catch (Exception e) {
						openDialog(true, identifier + " operation is not valid.\nPlease fix.", e);
					}
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handle();
				}
			});
		}
		//left columns
		{

			Button freshReload = new Button(this, SWT.NONE);
			GridData layoutData = new GridData(SWT.BEGINNING, SWT.TOP, false, false);
			//layoutData.horizontalSpan = BUTTONS_COLS_SPAN -1;
			freshReload.setLayoutData(layoutData);
			freshReload.setText("Reload all formulas");
			freshReload.setFont(MainGui.DEFAULTFONT);
			freshReload.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handleReloadOps();
				}

				private void handleReloadOps() {
					OperationBuilderComposite.this.getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
					try {
						handleSave(editorHolder.getFormatedReferenceTxt());
						parameterizedBuilder.resetCaches();
						updateOperationList(true, editorHolder.getFormatedReferenceTxt(), false);
					} finally {
						OperationBuilderComposite.this.getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
					}
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handleReloadOps();
				}

			});
		}
	}

	private void deleteFormula(String identifier) {

		if (!isValidId(identifier)) return;
		Operation existingOp = parameterizedBuilder.getCurrentOperations().get(identifier);
		if (isNativeOp(identifier, existingOp)) return;

		try {
			List<Operation> usingOps = parameterizedBuilder.removeFormula(identifier);
			if (!usingOps.isEmpty()) {
				String reduce = usingOps.stream().map(o -> o.getReference()).reduce((a,o) -> a + ", " + o).get();
				ActionDialogAction action = new ActionDialogAction() {
						@Override
						public void action() {
							for (Operation op : usingOps) {
								deleteFormula(op.getReference());
							}
							updateOperationList(true, null, false);
							List<Operation> remains = parameterizedBuilder.removeFormula(identifier);
							if (!remains.isEmpty()) {
								throw new RuntimeException("Can't delete " + identifier + " because it is used by " + remains.stream().map(o -> o.getReference()).reduce((a,o) -> a + ", " + o).get());
							}
							clearPreviousCalculationsUsing(identifier);
							refreshViews();
						}
					};
				ActionDialog delDialog = new ActionDialog(getShell(), "Operation is used", editorHolder.getFormatedReferenceTxt() + " is used by " + reduce, null, "Delete them too?", action);
				delDialog.open();
			} else {
				clearPreviousCalculationsUsing(identifier);
				refreshViews();
			}
		} catch (Exception e) {
			openDialog(true, "Found invalid formulas while storing data.", e);
		}

	}

	private void openDialog(Boolean forceOpening, String errorMsg, Object e) {
		String addMessage = (e == null)?null:e.toString();
		boolean firstDialog = (dialog == null);
		boolean differentDialog = (dialog != null) && !dialog.sameDialog(errorMsg, addMessage);
		if (firstDialog || differentDialog || forceOpening) {
			if (e instanceof Exception) LOGGER.warn(e, (Exception) e);
			if (firstDialog || dialog.getParent().isDisposed()) {
				dialog = new UserDialog(getShell(), errorMsg, addMessage);
				dialog.open();
			} else {
				dialog.updateDialog(MainGui.APP_NAME + " - Warning", errorMsg, addMessage);
			}
		}
	}
	
	private void openActionDialog(Boolean forceOpening, String title, String errorMsg, Object msgObjectException, String actionTxt, ActionDialogAction action, Boolean async) {
		String addMessage = (msgObjectException == null)?null:msgObjectException.toString();
		boolean firstDialog = (actionDialog == null);
		boolean differentDialog = (actionDialog != null) && !actionDialog.sameDialog(errorMsg, addMessage);
		if (firstDialog || differentDialog || forceOpening) {
			if (msgObjectException instanceof Exception) LOGGER.warn(msgObjectException, (Exception) msgObjectException);
			title = MainGui.APP_NAME + " - " + ((title == null)?"Warning":title);
			if (firstDialog || actionDialog.getParent().isDisposed()) {
				actionDialog = new ActionDialog(getShell(), title, errorMsg, addMessage, actionTxt, action, async);
				actionDialog.open();
			} else {
				actionDialog.updateDialog(title, errorMsg, addMessage, actionTxt, action, async);
			}
		}
	}

	protected void clearPreviousCalculationsUsing(String identifier) {
		Operation operation = parameterizedBuilder.getUserCurrentOperations().get(identifier);
		if (operation == null) {
			LOGGER.warn("No operation was found in User Current Operations for identifier: " + identifier);
		} else {
			try {
				parameterizedBuilder.clearPreviousCalculationsUsing(operation);
			} catch (InUseException e) {
				parameterizedBuilder.notifyChanged(operation, Optional.empty(), ObsMsgType.OPERATION_CrUD);
			}
		}
	}

	protected void refreshViews() {
		EventRefreshController eventRefreshController = 
				new EventRefreshController(EventModel.getInstance(new RefreshFourToutStrategyEngine()), mainGuiParent, ConfigThreadLocal.get(EventSignalConfig.EVENT_SIGNAL_NAME)) {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				LOGGER.guiInfo("Refreshing views");
				this.updateEventRefreshModelState(0l);
				super.widgetSelected(evt);
			}
		};
		eventRefreshController.widgetSelected(null);
	}

	protected String saveButtonTxt() {
		return "Save " + builderLabel() + " Formula";
	}

	protected String formulaReferenceLabelTxt() {
		return builderLabel() + " Formula Identifier";
	}

	protected String builderLabel() {
		return "Operation";
	}

	private void saveOrUpdateFormula(final String identifier, Boolean checkOverWrite) {

		final String formula = editorHolder.getEditor().getText();
		if (formula == null || formula.isEmpty()) {//Is empty formulae
			openDialog(true, "Please fill in a valid formula", null);
		} else if (!editorHolder.hasChanged(identifier)) {//Has not Changed?
			LOGGER.info("Has not changed " + identifier);
		} else { //Is valid and has changed
			ActionDialogAction action = new ActionDialogAction() {
				@Override
				public void action() {
					LOGGER.info("Saving warning dialog running " + identifier + ".");
					Boolean saved = doSaveFormula(identifier, formula);
					if (saved) {
						updateOperationList(true, identifier, false);
						LOGGER.info("Updated and saved " + identifier + ".");
					}
					Optional<Operation> defaultIndicator = parameterizedBuilder.createDefaultIndicator(identifier);
					if (defaultIndicator.isPresent()) {
						openDialog(true, "Default indicator: " + defaultIndicator.get().getReference(), null);
					}
				}
				
			};
			Operation existingOp = parameterizedBuilder.getCurrentOperations().get(identifier);
			if (existingOp != null && checkOverWrite) {//Already exist?
				LOGGER.info("Already exist, updating " + identifier + ".");
				if (existingOp.getDisabled()) {//Is disabled?
					LOGGER.info("Is disabled " + identifier + ".");
				} else {
					Boolean isExistingOpAnIndicator = existingOp instanceof EventInfo;
					Boolean isThisFormulaAnIndicator = this instanceof IndicatorBuilderComposite;
					if (isExistingOpAnIndicator == isThisFormulaAnIndicator) {
						openActionDialog(true, "Updating formula", "Do you want to update " + existingOp.getReference() + "?", null, "OK, update.", action, false);
					} else {
						openDialog(true, existingOp.getReference() + " operation name alreday used.\nPlease change.", null);
					}
				}
			} else {
				LOGGER.info("Is new " + identifier + ".");
				openActionDialog(true, 
						"Updating formula", "Do you want to save new " + identifier + "?", 
						null, "OK, save.", action, false);
				LOGGER.info("New saved " + identifier + ".");
			}
		}

	}

	protected Boolean doSaveFormula(final String identifier, String formula) {

		LOGGER.info("Actually persistence of " + identifier + ".");

		// Save formula
		try {
			// Sanity check
			NextToken checkNextToken = parameterizedBuilder.checkNextToken(formula);
			if (checkNextToken != null) {
				LOGGER.info("Invalid " + identifier + ".");
				openDialog(true, "Formula " + formula + " can't be saved.\n Please fill in a valid formula", checkNextToken.toString());
			} else {
				LOGGER.info("Adding formula to operation list: " + identifier + ".");
				parameterizedBuilder.addFormula(identifier, formula);
			}

		} catch (IOException e) {
			LOGGER.info("An error occurred " + identifier + ".");
			openDialog(true, "Formula can't be saved.\n Please fill in a valid formula", e);
		} catch (Exception e) {
			LOGGER.info("An error occurred " + identifier + ".");
			openDialog(true, "Found invalid formulas while storing data.", e);
		}

		LOGGER.info("Refresh for: " + identifier + ", if is saved: ");
		if (editorHolder.isSaved(identifier)) {
			clearPreviousCalculationsUsing(identifier);
			refreshViews();
			LOGGER.info("Refresh done/running: " + identifier + ".");
		}
		
		return editorHolder.isSaved(identifier);

	}

	protected Boolean isNativeOp(String identifier, Operation existingOp) {
		Boolean isNativeOp = false;
		if ((existingOp != null && existingOp.isNative()) || (existingOp = parameterizedBuilder.getNativeOperations().get(identifier)) != null) {
			openDialog(true, "The identifier you have chosen clashes with a native identifier :\n" + existingOp.getReference() + ", " + existingOp.getDescription()+ ".\n" +
					"Use an other one, for instance " + identifier + "1 or my" + identifier + " and save again.", null);
			isNativeOp = true;
		}
		return isNativeOp;
	}

	protected Boolean isValidId(String identifier) {

		String addMessage = "The identifier must contain at least two characters or number,\n" + "Must not be already taken.\n"
				+ "Must not be on of the key words : " + EditorLexerDelegate.HISTORICALDATA_TOKENS + " ...\n" + "Must not be on of the key words : "
				+ EditorLexerDelegate.MATYPES_TOKENS + " ...\n" + "Must not contain special characters.\n";

		Boolean isIdEmpty = false;
		Boolean isValid = true;

		if (identifier == null || identifier.length() < 2 || EditorLexerDelegate.HISTORICALDATA_TOKENS.contains(identifier)
				|| EditorLexerDelegate.MATYPES_TOKENS.contains(identifier)) {
			openDialog(true, "Please fill in a valid identifier", addMessage);
			isIdEmpty = true;

		} else {

			isValid = checkIdCharacters(identifier, addMessage);

			if (isValid) {
				Operation existingOp = parameterizedBuilder.getCurrentOperations().get(identifier);
				if (isNativeOp(identifier, existingOp)) {
					isValid = false;
				}
			}
		}

		return !isIdEmpty && isValid;
	}

	protected Boolean checkIdCharacters(String identifier, String addMessage) {
		List<Character> validSpecialChars = Arrays.asList(new Character[] {'_','-','.'});
		for (int i = 0; i < identifier.length(); i++) {
			if (!Character.isLetterOrDigit(identifier.charAt(i)) && !validSpecialChars.contains(identifier.charAt(i))) {
				openDialog(true, "Please fill in a valid identifier", addMessage + "Must not contain white spaces.\n");
				return false;
			}
		}
		return true;
	}

	protected NextToken expectedTokens(String formula) {
		LOGGER.info("Checking formula : " + formula);
		NextToken nextToken = parameterizedBuilder.checkNextToken(formula);
		return nextToken;
	}

	private void filterOutExistingSyntax(String formula, NextToken nextToken) {
		List<Alternative> filteredAlts = new ArrayList<Alternative>();
		for (Alternative alternative : nextToken.getAlternatives()) {
			if (alternative.getHighLighPosition()[0] != -1 && alternative.getHighLighPosition()[1] != -1) {
				int tokenPosition = ANTLROperationsParserHelper.translatePositionToCaret(editorHolder.getEditor().getText(), alternative.getHighLighPosition()[0], alternative.getHighLighPosition()[1]);
				if (tokenPosition < formula.length()
						&& (alternative.getTokenType().equals(TokenType.SYNTAX) || alternative.getTokenType().equals(TokenType.KEYWORDS))) {
					String trimedF = formula.substring(tokenPosition).trim();
					String nt = trimedF.replaceFirst(" .*", " ");
					if (nt.startsWith(alternative.getAltString().replaceFirst(" *", ""))) {
						// Don't add
					} else {
						filteredAlts.add(alternative);
					}
				} else {
					filteredAlts.add(alternative);
				}
			} else {
				filteredAlts.add(alternative);
			}
		}
		nextToken.setAlternatives(filteredAlts);

	}

	private void filterOutWrongPosition(NextToken nextToken, int caretPosition) {
		List<Alternative> filteredAlts = new ArrayList<Alternative>();
		for (Alternative alternative : nextToken.getAlternatives()) {
			if (alternative.getHighLighPosition()[0] != -1 && alternative.getHighLighPosition()[1] != -1) {
				int tokenPosition = ANTLROperationsParserHelper.translatePositionToCaret(editorHolder.getEditor().getText(), alternative.getHighLighPosition()[0],
						alternative.getHighLighPosition()[1]);
				if (tokenPosition == caretPosition)
					filteredAlts.add(alternative);
			} else {
				filteredAlts.add(alternative);
			}
		}
		nextToken.setAlternatives(filteredAlts);
	}

	protected void updateOperationList(boolean reciprocate, String nextSelection, Boolean saveCurrentOnNextSelection) {
		parameterizedBuilder.updateEditableOperationLists();
		if (reciprocate) comboUpdateMonitor.notifyObservers();
		editorHolder.forceSelection(nextSelection, saveCurrentOnNextSelection);
	}

	private void applyAutoCompleteSelection(Table table) {

		// Split existing at caret
		int caretPosition = editorHolder.getEditor().getCaretOffset();
		String rawEditorTxt = editorHolder.getEditor().getText();

		String preCaretUnTrimed = rawEditorTxt.substring(0, Math.min(rawEditorTxt.length(), caretPosition));
		String preCaret = trimPartialFormula(preCaretUnTrimed);
		String postCaret = "";
		if (caretPosition < rawEditorTxt.length()) {
			String postCaretUnTrimed = rawEditorTxt.substring(caretPosition);
			postCaret = trimPartialFormula(postCaretUnTrimed);
		}

		// Replace
		TableItem tableItem = table.getSelection()[0];
		Alternative alt = (Alternative) tableItem.getData();

		String replaced = preCaret;
		String preCaretAfterRepalcement = preCaret;
		switch (alt.getTokenType()) {
		case CONSTANTTOKEN: {
			String replacement = (alt.getDefaultValue() != null) ? alt.getDefaultValue() : "";
			int startOfInstert = startOfInsert(preCaret, replacement);
			String keptPreCaret = preCaret.substring(0, startOfInstert);
			int endOfInsert = endOfInsertToNextStop(postCaret, syntaxTokens());
			String keptPostCaret = postCaret.substring(endOfInsert);
			preCaretAfterRepalcement = keptPreCaret + replacement;
			replaced = preCaretAfterRepalcement + keptPostCaret;
		}
		break;
		case SYNTAX: {
			String replacement = tableItem.getText();
			String keptPostCaret = (!postCaret.isEmpty() && postCaret.charAt(0) == replacement.charAt(0)) ? postCaret.substring(1) : postCaret;
			preCaretAfterRepalcement = preCaret + replacement;
			replaced = preCaret + replacement + keptPostCaret;
		}
		break;
		case DATATOKEN:
		case KEYWORDS: {
			String replacement = tableItem.getText();
			int startOfInstert = startOfInsert(preCaret, replacement);
			String keptPreCaret = preCaret.substring(0, startOfInstert);
			int endOfInsert = endOfInsertToNextStop(postCaret, syntaxTokens());
			String keptPostCaret = postCaret.substring(endOfInsert);
			preCaretAfterRepalcement = keptPreCaret + replacement;
			replaced = preCaretAfterRepalcement + keptPostCaret;
		}
		break;
		case DELETE: {
			String toDelete = tableItem.getText();
			preCaretAfterRepalcement = preCaret.substring(0, preCaret.length() - toDelete.length());
			replaced = preCaretAfterRepalcement + postCaret;
		}
		break;
		}

		// Finally
		String newFormula = replaced;
		setEditorText(newFormula);
		int caretOffset = preCaretAfterRepalcement.length();

		// String[] indent = indent(replaced,
		// preCaretAfterRepalcement.length());
		// String newFormula = indent[0]+indent[1];
		// editor.setText(newFormula);
		// int caretOffset = indent[0].length();

		editorHolder.getEditor().setCaretOffset(caretOffset);

		LOGGER.info("editor caret position after apply: " + editorHolder.getEditor().getCaretOffset());

		LOGGER.info("buildPopupAlternatives rerun after apply. Aka similar to new typing");
		buildPopupAlternatives(true);
	}

	protected void setEditorText(String newFormula) {

		editorHolder.getEditor().setText(newFormula);

		this.getShell().layout();
//		Point size = this.getShell().getSize();
//		Point computeSize = this.getShell().computeSize(size.x, SWT.DEFAULT);
//		this.getShell().setSize(size.x, Math.max(size.y, computeSize.y));

	}

	protected List<String> syntaxTokens() {
		return EditorOpsLexerDelegate.SYNTAX_TOKENS;
	}

	protected String trimPartialFormula(String preCaretUnTrimed) {
		return preCaretUnTrimed.trim();
	}

	private int endOfInsertToNextStop(String postCaret, List<String> replaceStops) {
		int endIndexOf = 0;
		for (; endIndexOf < postCaret.length() && !replaceStops.contains(postCaret.charAt(endIndexOf) + ""); endIndexOf++) {
		}
		return endIndexOf;
	}

	private int startOfInsert(String preCaret, String replacement) {

		int firstIndexOf = preCaret.length();

		for (int i = replacement.length(); i > 0; i--) {
			String toMatch = replacement.substring(0, i);
			Boolean match = true;
			for (int j = toMatch.length() - 1; j >= 0; j--) {
				int index = preCaret.length() - toMatch.length() + j;
				if (index < 0 || toMatch.charAt(j) != preCaret.charAt(index)) {
					match = false;
					break;
				}
			}
			if (match) {
				firstIndexOf = preCaret.length() - toMatch.length();
				break;
			}
		}

		return firstIndexOf;
	}

	private synchronized void buildPopupAlternatives(boolean showPopUp) {

		setErrorLabel("");
		editorHolder.getEditor().setStyleRange(null);

		int caretPosition = editorHolder.getEditor().getCaretOffset();
		String rawEditorTxt = editorHolder.getEditor().getText();
		String preCaretString = rawEditorTxt.substring(0, Math.min(rawEditorTxt.length(), caretPosition));

		// Check the pre caret for suggestions
		NextToken nextToken = expectedTokens(preCaretString);
		if (nextToken != null && nextToken.getAlternatives().size() > 0) {
			filterOutWrongPosition(nextToken, caretPosition);
			filterOutExistingSyntax(rawEditorTxt, nextToken);
		}

		// Check the whole expression for errors and display
		NextToken errorToken = expectedTokens(rawEditorTxt);
		if (errorToken != null && errorToken.getAlternatives().size() > 0 && nextToken != null && nextToken.getAlternatives().size() > 0) {
			filterOutUnWantedDeletes(errorToken, nextToken);
		}

		// Highligt and msgs
		if (errorToken != null && errorToken.getAlternatives().size() > 0) {
			editorStylingsAndErrorLabel(errorToken.getAlternatives());
		}

		// Build suggestion
		if (nextToken != null && nextToken.getAlternatives().size() > 0) {
			if (nextToken.getAlternatives().size() > 0) {
				buildPopupAlternativesFor(nextToken, showPopUp);
			} else {
				getPopupShell().setVisible(false);
			}

		} else {
			getPopupShell().setVisible(false);
		}

	}

	protected void filterOutUnWantedDeletes(NextToken errorToken, NextToken nextToken) {
		List<Alternative> filteredErrorAlts = new ArrayList<Alternative>(errorToken.getAlternatives());
		for (Alternative errorAlt : errorToken.getAlternatives()) {
			boolean isDataOrConstSuggs = errorAlt.getTokenType().equals(TokenType.DATATOKEN) || errorAlt.getTokenType().equals(TokenType.CONSTANTTOKEN);
			//TODO add an other DATA type to avoid filtering here
			if (isDataOrConstSuggs) {
				filteredErrorAlts.remove(errorAlt);
			} else {
				for (Alternative nextAlt : nextToken.getAlternatives()) {

					boolean isDeleteRedondantWithNonDeleteSuggs = !nextAlt.getTokenType().equals(TokenType.DELETE)
							&& errorAlt.getTokenType().equals(TokenType.DELETE);
					boolean isSameAltAsSuggs = nextAlt.getAltString().startsWith(errorAlt.getAltString());
					boolean isSamePositionAsSuggs = errorAlt.getHighLighPosition()[0] == nextAlt.getHighLighPosition()[0]
							&& errorAlt.getHighLighPosition()[1] == nextAlt.getHighLighPosition()[1];

					if ((isDeleteRedondantWithNonDeleteSuggs && isSameAltAsSuggs && isSamePositionAsSuggs)) {
						filteredErrorAlts.remove(errorAlt);
					}
				}
			}
		}
		errorToken.setAlternatives(filteredErrorAlts);
	}

	private void buildPopupAlternativesFor(NextToken nextToken, boolean showContextPopup) {

		// Process Alts
		List<Alternative> alternatives = nextToken.getAlternatives();
		TableItem[] items = tokenAltsTable.getItems();
		for (int i = 0; i < alternatives.size(); i++) {
			Alternative data = alternatives.get(i);

			String token = data.getAltString();
			String description = (data.getDescription() != null) ? data.getDescription().trim() : "####";
			String synoptic = (data.getSynoptic() != null && data.getDefaultValue() == null) ? 
										data.getSynoptic().trim() : 
										((data.getDefaultValue() != null) ? data.getDefaultValue().trim() : "####");

			if (i < items.length) {
				items[i].setData(data);
				items[i].setText(0, token);
				items[i].setText(1, description);
				items[i].setText(2, synoptic);
			} else {
				TableItem tableItem = new TableItem(tokenAltsTable, SWT.WRAP | SWT.LEFT);
				tableItem.setData(data);
				tableItem.setText(0, token);
				tableItem.setText(1, description);
				tableItem.setText(2, synoptic);
			}
		}
		tokenAltsTable.remove(alternatives.size(), tokenAltsTable.getItems().length - 1);

		LOGGER.info("Alternatives size " + alternatives.size() + ". Alternatives: " + (alternatives.subList(0, Math.min(alternatives.size(), 20)) + "..."));

		if (tokenAltsTable.getItems().length > 0) {
			tokenAltsTable.select(0);
		}

		int[] position = ANTLROperationsParserHelper.translateCaretToPosition(editorHolder.getEditor().getText(), editorHolder.getEditor().getCaretOffset());

		tokenAltsColumns[0].setText("entry @ line " + position[0] + ", column " + position[1]);
		for (TableColumn tableColumn : tokenAltsColumns) {
			tableColumn.pack();
		}

		Point tableDefaultSize = tokenAltsTable.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		tokenAltsTable.setSize(Math.min(tableDefaultSize.x, getShell().getSize().x), Math.min(200, tableDefaultSize.y));

		Caret caret = editorHolder.getEditor().getCaret();
		Rectangle caretLocation = caret.getBounds();
		Rectangle eventBounds = getParent().getDisplay().map(editorHolder.getEditor(), null, caretLocation);
		Point popupSize = popupShell.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		getPopupShell().setBounds(eventBounds.x, eventBounds.y + eventBounds.height, Math.min(popupSize.x, getShell().getSize().x), Math.min(200, popupSize.y) + 10);
		getPopupShell().setVisible(showContextPopup);

		if (LOGGER.isDebugEnabled()) LOGGER.debug("Items: " + tokenAltsTable.getItems());
		if (LOGGER.isDebugEnabled()) {
			String itemsStr = "";
			for (TableItem tableItem : tokenAltsTable.getItems()) {
				itemsStr = itemsStr + tableItem.getText(0) + ": " + tableItem.getText(1) + " , ";
			}
			if (LOGGER.isDebugEnabled()) LOGGER.debug("ItemStr size " + tokenAltsTable.getItems().length + ". Items str: " + itemsStr);
		}

	}

	private Boolean editorStylingsAndErrorLabel(List<Alternative> alternatives) {

		for (Alternative alternative : alternatives) {

			if (alternative.getTokenType().equals(TokenType.DATATOKEN))
				continue;

			int lineNum = alternative.getHighLighPosition()[0];
			int endLineOffset = alternative.getHighLighPosition()[1];
			if (alternative.getAltType().equals(ANTLRParserHelper.AltType.SUGGESTION)) {
				endLineOffset++;
			}

			int endCaretOffset = ANTLROperationsParserHelper.translatePositionToCaret(editorHolder.getEditor().getText(), lineNum, endLineOffset);
			endCaretOffset = Math.min(endCaretOffset, editorHolder.getEditor().getText().length());

			// Style
			LOGGER.info("Style attempt @ line " + lineNum + ", start column " + endLineOffset + ". Alternative: " + alternative);

			int length = alternative.getAltType().equals(ANTLRParserHelper.AltType.DELETE) ? alternative.getAltString().length() : 1;
			if (endCaretOffset <= editorHolder.getEditor().getText().length() && endCaretOffset - length >= 0) {
				StyleRange styleRange = new StyleRange();
				styleRange.start = endCaretOffset - length;
				styleRange.length = length;
				if (alternative.getAltType().equals(ANTLRParserHelper.AltType.DELETE)) {
					styleRange.strikeout = true;
				} else {
					styleRange.strikeout = false;
				}
				styleRange.underline = true;
				styleRange.underlineColor = getDisplay().getSystemColor(SWT.COLOR_RED);
				editorHolder.getEditor().setStyleRange(styleRange);
			} else {
				LOGGER.warn("Style out of range @ line " + lineNum + ", start column " + endLineOffset + ". Alternative: " + alternative);
			}

			// error Msg
			String syno = (alternative.getSynoptic() == null) ? "" : ": " + alternative.getSynoptic();
			String addErrorTxt = "@ line " + lineNum + ", start column " + endLineOffset + " : " + alternative.getAltString() + "\n" + alternative.getDescription() + syno;

			setErrorLabel(addErrorTxt.replaceAll("\n", Text.DELIMITER).replace(". ", "." + Text.DELIMITER));

			return true; // TODO several highlight alts??!

		}

		// No highlight
		return false;
	}

	private void setErrorLabel(String error) {

		editorHolder.getErrorLabel().setText(error);

		this.getShell().layout();
		Point size = this.getShell().getSize();
		Point computeSize = this.getShell().computeSize(size.x, SWT.DEFAULT);
		this.getShell().setSize(size.x, Math.max(size.y, computeSize.y));

	}

	public ComboUpdateMonitor getComboUpdateMonitor() {
		return comboUpdateMonitor;
	}

	public void opsListUpdateMonitor(ComboUpdateMonitor comboUpdateMonitor) {

		comboUpdateMonitor.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {

				String comboSelectionId = editorHolder.getFormatedReferenceTxt();
				updateOperationList(false, comboSelectionId, false);
				
			}
		});
	}

	private Shell getPopupShell() {
		if (popupShell.isDisposed())
			initEditorPopup();
		return popupShell;
	}


}
