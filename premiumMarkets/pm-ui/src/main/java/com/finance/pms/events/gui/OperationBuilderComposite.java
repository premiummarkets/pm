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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
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
import org.eclipse.swt.widgets.Combo;
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
import com.finance.pms.portfolio.ShareListMgr;
import com.finance.pms.threads.ConfigThreadLocal;

public class OperationBuilderComposite extends Composite {

	public static MyLogger LOGGER = MyLogger.getLogger(OperationBuilderComposite.class);

	private UserDialog dialog;
	private ActionDialog actionDialog;

	protected ParameterizedBuilder parameterizedBuilder;
	protected RefreshableView mainGuiParent;

	private Label errorLabel;
	protected Combo formulaReference;
	protected StyledText editor;
	protected Boolean isSaved;
	private boolean createDefaultIndicatorOnSave;

	private Table tokenAltsTable;
	private Shell popupShell;
	private TableColumn[] tokenAltsColumns;

	private ComboUpdateMonitor comboUpdateMonitor = new ComboUpdateMonitor();

	private Map<Class<? extends Object>, Object> editorListeners;

	public static void main(String... args) {

		SpringContext springContext = new SpringContext(args[0]);
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

		springContext.close();

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
		layout.numColumns = 3;
		this.setLayout(layout);

		{
			Label formulaReferenceLabel = new Label(this, SWT.NONE);
			GridData labelLayoutData = new GridData(SWT.FILL, SWT.TOP, true, false);
			labelLayoutData.horizontalSpan = 3;
			formulaReferenceLabel.setLayoutData(labelLayoutData);
			formulaReferenceLabel.setText(formulaReferenceLabelTxt());
			formulaReferenceLabel.setFont(MainGui.DEFAULTFONT);
			formulaReferenceLabel.setBackground(MainGui.pOPUP_BG);

			formulaReference = new Combo(this, SWT.SINGLE | SWT.SIMPLE | SWT.V_SCROLL);
			GridData refLayoutData = new GridData(SWT.FILL, SWT.TOP, true, false);
			refLayoutData.horizontalSpan = 3;
			formulaReference.setLayoutData(refLayoutData);
			formulaReference.setFont(MainGui.CONTENTFONT);
			String siteUrl = MainPMScmd.getMyPrefs().get("site.url", "none.com");
			formulaReference.setToolTipText("Help available at http://"+siteUrl+"/html/swtui.html#UserGuidances");
			formulaReference.addMouseListener(new MouseListener() {

				@Override
				public void mouseUp(MouseEvent e) {
				}

				@Override
				public void mouseDown(MouseEvent e) {
					handleSaveAndSelection();
				}

				@Override
				public void mouseDoubleClick(MouseEvent e) {
				}
			});
			formulaReference.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handle();
				}

				private void handle() {
					changeEditorText(getFormatedReferenceTxt());
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});
			formulaReference.addKeyListener(new KeyListener() {

				@Override
				public void keyReleased(KeyEvent event) {
					switch (event.keyCode) {
					case SWT.ARROW_DOWN:
						break;
					case SWT.ARROW_UP:
						break;
					case SWT.ESC:
						break;
					case SWT.CR:
						// save
						isSaved = false;
						handleSaveAndSelection();
						// enable editor
						enableEditor();
						editor.setFocus();

						break;
					default:
					}
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}
			});
		}

		{
			Label editorLabel = new Label(this, SWT.NONE);
			GridData labelLayoutData = new GridData(SWT.FILL, SWT.TOP, true, false);
			labelLayoutData.horizontalSpan = 3;
			editorLabel.setLayoutData(labelLayoutData);
			editorLabel.setText("Type in your formula");
			editorLabel.setFont(MainGui.DEFAULTFONT);
			editorLabel.setBackground(MainGui.pOPUP_BG);

			errorLabel = new Label(this, SWT.WRAP);
			GridData errorLabelLayoutData = new GridData(SWT.FILL, SWT.TOP, true, false);
			errorLabelLayoutData.horizontalSpan = 3;
			errorLabelLayoutData.widthHint = 100;
			errorLabel.setLayoutData(errorLabelLayoutData);
			errorLabel.setText("");
			errorLabel.setFont(MainGui.CONTENTFONT);
			errorLabel.setForeground(getDisplay().getSystemColor(SWT.COLOR_RED));

			editor = new StyledText(this, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
			GridData editorLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
			editorLayoutData.horizontalSpan = 3;
			editor.setLayoutData(editorLayoutData);
			editor.setFont(MainGui.CONTENTFONT);
			{
				editorListeners = new HashMap<Class<? extends Object>, Object>();

				initPopup();

				editor.addModifyListener(new ModifyListener() {
					@Override
					public void modifyText(ModifyEvent e) {
						if (!editor.getText().isEmpty()) {
							isSaved = !hasChanged(getFormatedReferenceTxt());
						}
					}
				});

			}
		}
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
										int selectionIndex = formulaReference.getSelectionIndex();
										deleteAllDisabledOrUnused();
										formulaReference.removeAll();
										isSaved = true;
										updateCombo(true);
										if (formulaReference.getItemCount() > 0) {
											forceSelection(selectionIndex % formulaReference.getItemCount());
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
					handleDeleteUnused();
				}
			});
		}
		addThisCompositeExtratButtons();
		{

			Button deleteFormula = new Button(this, SWT.NONE);
			GridData layoutData = new GridData(SWT.END, SWT.TOP, false, false);
			layoutData.horizontalSpan = 3;
			deleteFormula.setLayoutData(layoutData);
			deleteFormula.setText("Delete " + builderLabel());
			deleteFormula.setFont(MainGui.DEFAULTFONT);
			deleteFormula.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handleDeleteOp();
				}

				private void handleDeleteOp() {

					int selectionIndex = formulaReference.getSelectionIndex();

					OperationBuilderComposite.this.getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
					try {
						deleteFormula(getFormatedReferenceTxt());
					} finally {
						OperationBuilderComposite.this.getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
					}

					formulaReference.removeAll();
					isSaved = true;
					updateCombo(true);
					if (formulaReference.getItemCount() > 0) {
						forceSelection(selectionIndex % formulaReference.getItemCount());
					}

				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handleDeleteOp();
				}
			});

		}
		{

			Button saveFormula = new Button(this, SWT.NONE);
			GridData layoutData = new GridData(SWT.END, SWT.TOP, true, false);
			layoutData.horizontalSpan = 3;
			saveFormula.setLayoutData(layoutData);
			saveFormula.setText(saveButtonTxt());
			saveFormula.setFont(MainGui.DEFAULTFONT);
			saveFormula.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handle();
				}

				private void handle() {
					handleSaveAndSelection();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handle();
				}
			});

		}

		isSaved = true;
		updateCombo(false);

		this.layout();

	}

	protected void deleteAllDisabledOrUnused() {
		Map<String, Operation> allOps = parameterizedBuilder.getUserCurrentOperations();
		for (String opId : allOps.keySet()) {
			try {
				parameterizedBuilder.removeFormula(opId, true);
			} catch (IOException e) {
				LOGGER.info(opId + " is used and won't be disabled");
			}
		}
	}

	protected void enableEditor() {
		// Nothing
	}

	protected void initPopup() {

		popupShell = new Shell(getShell(), SWT.ON_TOP | SWT.RESIZE);
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
		tokenAltsColumns[0].setWidth(50);
		tokenAltsColumns[1] = new TableColumn(tokenAltsTable, SWT.LEFT);
		tokenAltsColumns[1].setText("description");
		tokenAltsColumns[1].setWidth(50);
		tokenAltsColumns[2] = new TableColumn(tokenAltsTable, SWT.LEFT);
		tokenAltsColumns[2].setText("synoptic and defaults");
		tokenAltsColumns[2].setWidth(50);

		if (editorListeners.get(VerifyKeyListener.class) != null) editor.removeVerifyKeyListener((VerifyKeyListener) editorListeners.get(VerifyKeyListener.class));
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
		editor.addVerifyKeyListener(vkListener);

		if (editorListeners.get(KeyListener.class) != null) editor.removeKeyListener((KeyListener) editorListeners.get(KeyListener.class));
		KeyListener keyListener = new KeyListener() {

			@Override
			public void keyReleased(KeyEvent event) {
				if (!popupShell.isDisposed() && popupShell.isVisible()) {
					switch (event.keyCode) {
					case SWT.ARROW_DOWN:
					case SWT.ARROW_UP:
					case SWT.CR:
					case SWT.ESC:
						break;
					default:
						LOGGER.info("buildPopupAlternatives key released popup visible");
						buildPopupAlternatives();
					}
				} else {
					LOGGER.info("buildPopupAlternatives key released popup hidden");
					if (event.keyCode != SWT.ESC)
						buildPopupAlternatives();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		};
		editorListeners.put(KeyListener.class, keyListener);
		editor.addKeyListener(keyListener);

		if (editorListeners.get(MouseListener.class) != null) editor.removeMouseListener((MouseListener) editorListeners.get(MouseListener.class));
		MouseListener mouseListener = new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
			}

			@Override
			public void mouseDown(MouseEvent e) {
				LOGGER.info("buildPopupAlternatives mouseDown");
				try {
					buildPopupAlternatives();
				} catch (Exception e1) {
					openDialog(true, "Invalid formula.", e);
				}
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				LOGGER.info("buildPopupAlternatives mouseDoubleClick");
				try {
					buildPopupAlternatives();
				} catch (Exception e1) {
					openDialog(true, "Invalid formula.", e);
				}
			}
		};
		editorListeners.put(MouseListener.class, mouseListener);
		editor.addMouseListener(mouseListener);

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

		if (editorListeners.get(FocusListener.class) != null) editor.removeListener(SWT.FocusOut, (Listener) editorListeners.get(FocusListener.class));
		Listener focusOutListener = new Listener() {
			public void handleEvent(Event event) {
				/* async is needed to wait until focus reaches its new Control */
				getParent().getDisplay().asyncExec(new Runnable() {
					public void run() {
						if (popupShell.isDisposed() || getParent().getDisplay().isDisposed())
							return;
						Control control = getParent().getDisplay().getFocusControl();
						if (control == null || (control != editor && control != tokenAltsTable) && control != popupShell) {
							popupShell.setVisible(false);
						}
					}
				});
			}
		};
		editorListeners.put(FocusListener.class, focusOutListener);
		editor.addListener(SWT.FocusOut, focusOutListener);

		getShell().addListener(SWT.Move, new Listener() {
			public void handleEvent(Event event) {
				getPopupShell().setVisible(false);
			}
		});

	}

	protected void clearEditor() {
		setEditorText("");
		setErrorLabel("");
	}

	protected synchronized void handleSaveAndSelection() {

		String id = getFormatedReferenceTxt();

		if (!isSaved) {

			LOGGER.info("Handling saving of "+id+". Is saved :"+isSaved);

			if (isValidId(id)) {
				saveOrUpdateFormula(id, true);
			}

			LOGGER.info(id+" is saved ? : "+isSaved);

		}
	}

	protected void updateComboAndSelect(String selectedOpRef, Boolean reciprocate) {

		int comboSelectionIdx = formulaReference.getSelectionIndex();

		if (isSaved) {

			formulaReference.removeAll();
			updateCombo(reciprocate);
			String[] items = formulaReference.getItems();
			for (int i = 0; i < items.length; i++) {
				if (selectedOpRef.equals(items[i])) {
					comboSelectionIdx = i;
					break;
				}
			}
			forceSelection(comboSelectionIdx);

		}

	}

	protected void addThisCompositeExtratButtons() {
		{

			Button freshReload = new Button(this, SWT.NONE);
			GridData layoutData = new GridData(SWT.BEGINNING, SWT.TOP, false, false);
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
						parameterizedBuilder.resetCaches();
						updateCombo(true);
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
		{

			Button createDefaultIndicator = new Button(this, SWT.CHECK);
			GridData layoutData = new GridData(SWT.END, SWT.TOP, true, false);
			createDefaultIndicator.setLayoutData(layoutData);
			createDefaultIndicator.setText("Create Trend Calculator on Save");
			createDefaultIndicator.setFont(MainGui.DEFAULTFONT);
			createDefaultIndicator.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent evt) {
					createDefaultIndicatorOnSave = createDefaultIndicator.getSelection();
				}
			});

		}

	}

	private void deleteFormula(String identifier) {

		if (!isValidId(identifier))
			return;
		Operation existingOp = parameterizedBuilder.getCurrentOperations().get(identifier);
		if (isNativeOp(identifier, existingOp))
			return;

		try {
			parameterizedBuilder.removeFormula(identifier, true);
		} catch (IOException e) {
			openDialog(true, "Formula can't be deleted.", e);
			return;
		} catch (Exception e) {
			openDialog(true, "Found invalid formulas while storing data.", e);
		}

		previousCalcsAsDirty(identifier);
		refreshViews();

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
	
	private void openActionDialog(Boolean forceOpening, String title, String errorMsg, Object e, String actionTxt, ActionDialogAction action, Boolean async) {
		String addMessage = (e == null)?null:e.toString();
		boolean firstDialog = (actionDialog == null);
		boolean differentDialog = (actionDialog != null) && !actionDialog.sameDialog(errorMsg, addMessage);
		if (firstDialog || differentDialog || forceOpening) {
			if (e instanceof Exception) LOGGER.warn(e, (Exception) e);
			title = MainGui.APP_NAME + " - " + ((title == null)?"Warning":title);
			if (firstDialog || actionDialog.getParent().isDisposed()) {
				actionDialog = new ActionDialog(getShell(), title, errorMsg, addMessage, actionTxt, action, async);
				actionDialog.open();
			} else {
				actionDialog.updateDialog(title, errorMsg, addMessage, actionTxt, action, async);
			}
		}
	}

	protected void previousCalcsAsDirty(String identifier) {
		Operation operation = parameterizedBuilder.getUserCurrentOperations().get(identifier);
		if (operation == null) {
			LOGGER.warn("No operation was found in User Current Operations for identifier :"+identifier);
		} else {
			try {
				parameterizedBuilder.clearPreviousCalculations(operation);
			} catch (InUseException e) {
				parameterizedBuilder.notifyChanged(operation, ObsMsgType.OPERATION_CrUD);
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

	protected void changeEditorText(String newId) {
		if (newId != null && !newId.isEmpty()) {
			int indexOf = formulaReference.indexOf(newId);
			if (indexOf != -1) {
				forceSelection(indexOf);
			} else {
				clearEditor();
			}
		} else {
			clearEditor();
		}
		//setErrorLabel("");
	}

	protected Boolean hasChanged(String oldId) {

		Boolean hasChanged = false;
		Map<String, Operation> currentOperations = parameterizedBuilder.getUserCurrentOperations();
		if (oldId != null && !oldId.isEmpty()) {
			Operation oldOp = currentOperations.get(oldId);
			if (oldOp != null) {
				String oldFormula = oldOp.getFormulae();
				if (oldFormula == null || !oldFormula.equals(formatedEditorTxt())) {
					hasChanged = true;
				}
			} else {
				hasChanged = true;
			}
		}
		return hasChanged;
	}

	private void saveOrUpdateFormula(final String identifier, Boolean checkOverWrite) {

		LOGGER.info("Save or update of "+identifier+". Is saved :"+isSaved);

		final String formula = formatedEditorTxt();
		if (formula == null || formula.isEmpty()) {//Is valid?
			openDialog(false, "Please fill in a valid formula", null);
			isSaved=false;
			LOGGER.info("Is invalid "+identifier+". Is saved :"+isSaved);
		} else if (!hasChanged(identifier)) {//Has not Changed?
			isSaved=true;
			LOGGER.info("Has not changed "+identifier+". Is saved :"+isSaved);
		} else {//Is valid and has changed
			Operation existingOp = parameterizedBuilder.getCurrentOperations().get(identifier);
			if (existingOp != null && checkOverWrite) {//Already exist?
				LOGGER.info("Already exist, updating "+identifier+". Is saved :"+isSaved);
				if (existingOp.getDisabled()) {//Is disabled?
					LOGGER.info("Is disabled "+identifier+". Is saved :"+isSaved);
					isSaved=true;
				} else {

					LOGGER.info("Launching confirmation dialog "+identifier+". Is saved :"+isSaved);
					updateComboAndSelect(identifier, true); //This is an update so this should have no impact?
					ActionDialogAction action = new ActionDialogAction() {
						@Override
						public void action() {
							LOGGER.info("Saving warning dialog running "+identifier+". Is saved :"+isSaved);
							doSaveFormula(identifier, formula);
							LOGGER.info("Updated and saved "+identifier+". Is saved :"+isSaved);
						}
					};
					openActionDialog(true, "Updating formula", "Do you want to update " + existingOp.getReference() + "?", null, "OK, save.", action, false);
				}

			} else {
				LOGGER.info("Is new "+identifier+". Is saved :" + isSaved);
				doSaveFormula(identifier, formula);
				updateComboAndSelect(identifier, true);
				LOGGER.info("New saved "+identifier+". Is saved :" + isSaved);
			}
		}

	}

	protected void doSaveFormula(final String identifier, String formula) {

		LOGGER.info("Actually persistence of "+identifier+". Is saved :" + isSaved);

		// Save formula
		try {
			// Sanity check
			NextToken checkNextToken = parameterizedBuilder.checkNextToken(formula);
			if (checkNextToken != null) {
				LOGGER.info("Invalid "+identifier+". Is saved :"+isSaved);
				openDialog(false, "Formula " + formula + " can't be saved.\n Please fill in a valid formula", checkNextToken.toString());
				isSaved=false;
			} else {
				LOGGER.info("Adding formula to operation list : "+identifier+". Is saved :"+isSaved);
				parameterizedBuilder.addFormula(identifier, formula);
				isSaved = true;
			}

		} catch (IOException e) {
			LOGGER.info("An error occurred " + identifier + ". Is saved :"+isSaved);
			openDialog(false, "Formula can't be saved.\n Please fill in a valid formula", e);
			isSaved=false;

		} catch (Exception e) {
			LOGGER.info("An error occurred " + identifier + ". Is saved :"+isSaved);
			openDialog(true, "Found invalid formulas while storing data.", e);
			isSaved=false;
		}

		LOGGER.info("Refresh for : "+identifier+", if is saved :"+isSaved);
		if (isSaved) {
			if (createDefaultIndicatorOnSave) {
				parameterizedBuilder.notifyChanged(parameterizedBuilder.getCurrentOperations().get(identifier), ObsMsgType.CREATE_INDICTOR);
			}
			previousCalcsAsDirty(identifier);
			refreshViews();
			LOGGER.info("Refresh done/running : "+identifier+". Is saved :"+isSaved);
		}

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
		for (int i = 0; i < identifier.length(); i++) {
			if (!Character.isLetterOrDigit(identifier.charAt(i)) && identifier.charAt(i) != '_') {
				openDialog(true, "Please fill in a valid identifier", addMessage + "Must not contain white spaces.\n");
				return false;
			}
		}
		return true;
	}

	private String formatedEditorTxt() {
		return format(editor.getText());
	}

	protected String format(String string) {
		// TODO indent return FormulaUtils.indentOperationFormula(formula, previousCaretPosition);
		return string.replaceAll("\n", "").replaceAll(" +", " ").replace(";", ";\n").trim();
	}

	String getFormatedReferenceTxt() {
		return format(formulaReference.getText());
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
				int tokenPosition = ANTLROperationsParserHelper.translatePositionToCaret(editor.getText(), alternative.getHighLighPosition()[0],
						alternative.getHighLighPosition()[1]);
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
				int tokenPosition = ANTLROperationsParserHelper.translatePositionToCaret(editor.getText(), alternative.getHighLighPosition()[0],
						alternative.getHighLighPosition()[1]);
				if (tokenPosition == caretPosition)
					filteredAlts.add(alternative);
			} else {
				filteredAlts.add(alternative);
			}
		}
		nextToken.setAlternatives(filteredAlts);
	}

	protected void updateCombo(boolean reciprocate) {

		if (isSaved) {

			Map<String, Operation> allOps = parameterizedBuilder.getUserCurrentOperations();

			SortedSet<String> itemSet = new TreeSet<String>(allOps.keySet());
			formulaReference.setItems(itemSet.toArray(new String[0]));

			updateEditableOperationLists();

			if (reciprocate) comboUpdateMonitor.notifyObservers();

		}

	}

	protected void updateEditableOperationLists() {
		parameterizedBuilder.updateEditableOperationLists();
	}

	protected void forceSelection(int selected) {

		if (selected != -1 && selected < formulaReference.getItemCount()) {
			formulaReference.select(selected);
			setEditorText(parameterizedBuilder.getUserCurrentOperations().get(getFormatedReferenceTxt()).getFormulae());
			setErrorLabel("");
			checkBoxDisabled();
		}

	}

	protected void checkBoxDisabled() {
		// Nothing
	}

	private void applyAutoCompleteSelection(Table table) {

		// Split existing at caret
		int caretPosition = editor.getCaretOffset();
		String rawEditorTxt = editor.getText();

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

		editor.setCaretOffset(caretOffset);

		LOGGER.info("editor caret position after apply :" + editor.getCaretOffset());

		LOGGER.info("buildPopupAlternatives rerun after apply. Aka similar to new typing");
		buildPopupAlternatives();
	}

	protected void setEditorText(String newFormula) {

		editor.setText(newFormula);

		this.getShell().layout();
		Point size = this.getShell().getSize();
		Point computeSize = this.getShell().computeSize(size.x, SWT.DEFAULT);
		this.getShell().setSize(size.x, Math.max(size.y, computeSize.y));

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

	private synchronized void buildPopupAlternatives() {

		setErrorLabel("");
		editor.setStyleRange(null);

		int caretPosition = editor.getCaretOffset();
		String rawEditorTxt = editor.getText();
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
				buildPopupAlternativesFor(nextToken);
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

	private void buildPopupAlternativesFor(NextToken nextToken) {

		// Process Alts
		List<Alternative> alternatives = nextToken.getAlternatives();
		TableItem[] items = tokenAltsTable.getItems();
		for (int i = 0; i < alternatives.size(); i++) {
			Alternative data = alternatives.get(i);

			String token = data.getAltString();
			String description = (data.getDescription() != null) ? data.getDescription() + "    " : "    ";
			String synoptic = (data.getSynoptic() != null && data.getDefaultValue() == null) ? data.getSynoptic() : ((data.getDefaultValue() != null) ? data
					.getDefaultValue() : "");

			if (i < items.length) {
				items[i].setData(data);
				items[i].setText(0, token);
				items[i].setText(1, description);
				items[i].setText(2, synoptic);
			} else {
				TableItem tableItem = new TableItem(tokenAltsTable, SWT.WRAP);
				tableItem.setData(data);
				tableItem.setText(0, token);
				tableItem.setText(1, description);
				tableItem.setText(2, synoptic);
			}
		}
		tokenAltsTable.remove(alternatives.size(), tokenAltsTable.getItems().length - 1);

		LOGGER.info("Alternatives size " + alternatives.size() + ". Alternatives : " + (alternatives.subList(0, Math.min(alternatives.size(), 20)) + "..."));

		if (tokenAltsTable.getItems().length > 0) {
			tokenAltsTable.select(0);
		}

		int[] position = ANTLROperationsParserHelper.translateCaretToPosition(editor.getText(), editor.getCaretOffset());

		tokenAltsColumns[0].setText("entry @ line " + position[0] + ", column " + position[1]);
		for (TableColumn tableColumn : tokenAltsColumns) {
			tableColumn.pack();
		}

		Point tableDefaultSize = tokenAltsTable.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		tokenAltsTable.setSize(Math.min(tableDefaultSize.x, getShell().getSize().x), Math.min(200, tableDefaultSize.y));

		Caret caret = editor.getCaret();
		Rectangle caretLocation = caret.getBounds();
		Rectangle eventBounds = getParent().getDisplay().map(editor, null, caretLocation);
		Point popupSize = popupShell.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		getPopupShell().setBounds(eventBounds.x, eventBounds.y + eventBounds.height, Math.min(popupSize.x, getShell().getSize().x), Math.min(200, popupSize.y) + 10);
		getPopupShell().setVisible(true);

		LOGGER.debug("Items : " + tokenAltsTable.getItems());
		if (LOGGER.isDebugEnabled()) {
			String itemsStr = "";
			for (TableItem tableItem : tokenAltsTable.getItems()) {
				itemsStr = itemsStr + tableItem.getText(0) + ":" + tableItem.getText(1) + " , ";
			}
			LOGGER.debug("ItemStr size " + tokenAltsTable.getItems().length + ". Items str : " + itemsStr);
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

			int endCaretOffset = ANTLROperationsParserHelper.translatePositionToCaret(editor.getText(), lineNum, endLineOffset);
			endCaretOffset = Math.min(endCaretOffset, editor.getText().length());

			// Style
			LOGGER.info("Style attempt @ line " + lineNum + ", start column " + endLineOffset + ". Alternative : " + alternative);

			int length = alternative.getAltType().equals(ANTLRParserHelper.AltType.DELETE) ? alternative.getAltString().length() : 1;
			if (endCaretOffset <= editor.getText().length() && endCaretOffset - length >= 0) {
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
				editor.setStyleRange(styleRange);
			} else {
				LOGGER.warn("Style out of range @ line " + lineNum + ", start column " + endLineOffset + ". Alternative : " + alternative);
			}

			// error Msg
			String syno = (alternative.getSynoptic() == null) ? "" : " : " + alternative.getSynoptic();
			String addErrorTxt = "@ line " + lineNum + ", start column " + endLineOffset + " : " + alternative.getAltString() + "\n" + alternative.getDescription() + syno;

			setErrorLabel(addErrorTxt.replaceAll("\n", Text.DELIMITER).replace(". ", "." + Text.DELIMITER));

			return true; // TODO several highlight alts??!

		}

		// No highlight
		return false;
	}

	private void setErrorLabel(String error) {

		errorLabel.setText(error);

		this.getShell().layout();
		Point size = this.getShell().getSize();
		Point computeSize = this.getShell().computeSize(size.x, SWT.DEFAULT);
		this.getShell().setSize(size.x, Math.max(size.y, computeSize.y));

	}

	public ComboUpdateMonitor getComboUpdateMonitor() {
		return comboUpdateMonitor;
	}

	public void obsComboUpdateMonitor(ComboUpdateMonitor comboUpdateMonitor) {

		comboUpdateMonitor.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {

				int comboSelectionIdx = formulaReference.getSelectionIndex();

				if (isSaved) {
					updateCombo(false);
					if (formulaReference.getItemCount() > 0) {
						forceSelection(comboSelectionIdx % formulaReference.getItemCount());
					}
				} else {
					updateEditableOperationLists();
				}

			}
		});
	}

	private Shell getPopupShell() {
		if (popupShell.isDisposed())
			initPopup();
		return popupShell;
	}


}
