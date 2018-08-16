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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.finance.pms.ActionDialogAction;
import com.finance.pms.CursorFactory;
import com.finance.pms.MainGui;
import com.finance.pms.PopupMenu;
import com.finance.pms.SpringContext;
import com.finance.pms.UserDialog;
import com.finance.pms.datasources.EventModel;
import com.finance.pms.events.calculation.antlr.EditorIndsLexerDelegate;
import com.finance.pms.events.calculation.antlr.ParameterizedBuilder;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.conditional.EventInfoOpsCompoOperation;

public class IndicatorBuilderComposite extends OperationBuilderComposite {

	private Button disableFormula;

	public IndicatorBuilderComposite(Composite parent, MainGui mainGui, ComboUpdateMonitor comboUpdateMonitor) {
		super(parent, mainGui);
		
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

	@Override
	protected ParameterizedBuilder initParameterizedBuilder() {
		return (ParameterizedBuilder) SpringContext.getSingleton().getBean("parameterizedIndicatorsBuilder");
	}

	protected String builderLabel() {
		return "Trends Calculator";
	}

	@Override
	protected void addThisCompositeExtratButtons() {
		
		{
			Button duplicate = new Button(this, SWT.NONE);
			GridData layoutData = new GridData(SWT.BEGINNING, SWT.TOP, false, false);
			layoutData.horizontalSpan = 1;
			duplicate.setLayoutData(layoutData);
			duplicate.setText("Duplicate");
			duplicate.setFont(MainGui.DEFAULTFONT);
			duplicate.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handleDuplicateFormula();
				}

				private void handleDuplicateFormula() {
					IndicatorBuilderComposite.this.getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
					try {
						 duplicateFormula(getFormatedReferenceTxt());
					} finally {
						IndicatorBuilderComposite.this.getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
					}
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handleDuplicateFormula();
				}
			});
		}
		
		{
			disableFormula = new Button(this, SWT.NONE);
			GridData layoutData = new GridData(SWT.BEGINNING, SWT.TOP, false, false);
			layoutData.horizontalSpan = 1;
			disableFormula.setLayoutData(layoutData);
			disableFormula.setText("Enable ...");
			disableFormula.setFont(MainGui.DEFAULTFONT);
			disableFormula.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handleEnableDisableFormula();
				}

				@SuppressWarnings({ "rawtypes", "unchecked" })
				private void handleEnableDisableFormula() {

					final Set<EventInfoOpsCompoOperation> availableOperations = new TreeSet(parameterizedBuilder.getCurrentOperations().values());
					final Set<EventInfoOpsCompoOperation> enabledOperations = new TreeSet(parameterizedBuilder.getUserEnabledOperations().values());
					availableOperations.remove(parameterizedBuilder.getCurrentOperations().get("operationscompositionner"));//XXX
					
					ActionDialogAction closeAction = new ActionDialogAction() {
						
						@Override
						public void action() {
							for (Operation eventInfo : availableOperations) {
								if (enabledOperations.contains(eventInfo)) {
									if (eventInfo.getDisabled()) enableFormula(eventInfo.getReference());
								} else {
									if (!eventInfo.getDisabled()) disableFormula(eventInfo.getReference());
								}
							}
							refreshViews();
							checkBoxDisabled();
						}
					};
					PopupMenu<EventInfoOpsCompoOperation> popupMenu = new PopupMenu<EventInfoOpsCompoOperation>(IndicatorBuilderComposite.this, disableFormula, availableOperations, enabledOperations, false, true, SWT.CHECK, null, closeAction, false);
					popupMenu.open();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handleEnableDisableFormula();
				}
			});
			
		}
		
	}

	private void enableFormula(String identifier) {

		if (!isValidId(identifier)) return;
		Operation existingOp = parameterizedBuilder.getCurrentOperations().get(identifier);
		if(isNativeOp(identifier, existingOp)) return;

		try {

			parameterizedBuilder.enableFormula(identifier);

		} catch (IOException e) {
			UserDialog dialog = new UserDialog(getShell(), "Formula can't be enabled.", e.toString());
			LOGGER.warn(e,e);
			dialog.open();
			return;
		} catch (Exception e) {
			UserDialog dialog = new UserDialog(getShell(), "Found invalid formulas while storing data.", e.toString());
			LOGGER.warn(e,e);
			dialog.open();
		} 
		
		previousCalcsAsDirty(identifier);

	}

	private void disableFormula(String identifier) {

		if (!isValidId(identifier)) return;
		Operation existingOp = parameterizedBuilder.getCurrentOperations().get(identifier);
		if(isNativeOp(identifier, existingOp)) return;

		try {

			parameterizedBuilder.disableFormula(identifier);

		} catch (IOException e) {
			UserDialog dialog = new UserDialog(getShell(), "Formula can't be disabled.", e.toString());
			LOGGER.warn(e,e);
			dialog.open();
			return;
		} catch (Exception e) {
			UserDialog dialog = new UserDialog(getShell(), "Found invalid formulas while storing data.", e.toString());
			LOGGER.warn(e,e);
			dialog.open();

		} 

		previousCalcsAsDirty(identifier);

	}

	@Override
	protected void checkBoxDisabled() {
		
		if (formulaReference != null && formulaReference.getSelectionIndex() != -1 && formulaReference.getSelectionIndex() < formulaReference.getItems().length) {
			String selectItem = formulaReference.getItem(formulaReference.getSelectionIndex());
			Boolean disabled = parameterizedBuilder.getCurrentOperations().get(selectItem).getDisabled();
			boolean isDisabled = (disabled != null) && disabled;
			editor.setEnabled(!isDisabled);
			editor.setEditable(!isDisabled);
			disableFormula.setSelection(isDisabled);
			if (isDisabled) {
				editor.setStyleRange(new StyleRange(0,editor.getText().length(),getDisplay().getSystemColor(SWT.COLOR_GRAY),null,SWT.ITALIC));
			}  else {
				editor.setStyleRange(null);
			}
			
		} else {
			editor.setEnabled(disableFormula.getEnabled());
			editor.setEditable(disableFormula.getEnabled());
		}
		
	}

	@Override
	protected String trimPartialFormula(String preCaretUnTrimed) {
		return preCaretUnTrimed;
	}

	@Override
	protected List<String> syntaxTokens() {
		return EditorIndsLexerDelegate.SYNTAX_TOKENS;
	}

	@Override
	protected void clearEditor() {
		super.clearEditor();
		disableFormula.setSelection(false);
		editor.setEnabled(true);
		editor.setEditable(true);
	}
	

	@Override
	protected void enableEditor() {
		editor.setEnabled(true);
		editor.setEditable(true);
		editor.setStyleRange(null);
	}
	
	@Override
	protected Boolean checkIdCharacters(String identifier, String addMessage) {
		for (int i = 0; i < identifier.length(); i++) {
			char charAt = identifier.charAt(i);
			if (!Character.isLetterOrDigit(charAt) && !Character.isWhitespace(charAt)) {
				UserDialog dialog = new UserDialog(getShell(), "Please fill in a valid identifier", addMessage);
				dialog.open();
				return false;
			}
		}
		return true;
	}

	@Override
	protected void previousCalcsAsDirty(String identifier) {
		
		EventInfoOpsCompoOperation operation = (EventInfoOpsCompoOperation) parameterizedBuilder.getUserCurrentOperations().get(identifier);
		EventModel.dirtyCacheFor(operation);
		EventModel.updateEventInfoStamp();
		
	}

	@Override
	protected void deleteAllDisabledOrUnused() {
		Map<String, Operation> allOps = parameterizedBuilder.getUserCurrentOperations();
		for (Operation indicator: allOps.values()) {
			try {
				if (indicator.getDisabled()) parameterizedBuilder.removeFormula(indicator.getReference(), false);
			} catch (IOException e) {
				LOGGER.error(e,e);
			}
		}
	}

	protected void duplicateFormula(String identifier) {
		
		if (!isValidId(identifier)) return;
		Operation existingOp = parameterizedBuilder.getCurrentOperations().get(identifier);
		if (isNativeOp(identifier, existingOp)) return;
		
		try {
			
			Operation duplicatedOperation = parameterizedBuilder.duplicateOperation(existingOp, new HashMap<String, Operation>());
			updateComboAndSelect(duplicatedOperation.getReference(), true);
			refreshViews();
			
		} catch (IOException e) {
			UserDialog dialog = new UserDialog(getShell(), "Formula can't be duplicated.", e.toString());
			LOGGER.warn(e, e);
			dialog.open();
			return;
		} catch (Exception e) {
			UserDialog dialog = new UserDialog(getShell(), "Found invalid formulas while storing data.", e.toString());
			LOGGER.warn(e, e);
			dialog.open();
		}
		
	}

}
