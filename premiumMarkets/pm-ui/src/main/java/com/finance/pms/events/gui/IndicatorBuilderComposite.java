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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.finance.pms.CursorFactory;
import com.finance.pms.MainGui;
import com.finance.pms.SpringContext;
import com.finance.pms.UserDialog;
import com.finance.pms.datasources.EventModel;
import com.finance.pms.events.calculation.antlr.EditorIndsLexerDelegate;
import com.finance.pms.events.calculation.antlr.ParameterizedBuilder;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.conditional.EventInfoOpsCompoOperation;

public class IndicatorBuilderComposite extends OperationBuilderComposite {

	public IndicatorBuilderComposite(Composite parent, MainGui mainGui) {
		super(parent, mainGui);

//		comboUpdateMonitor.addObserver(new Observer() {
//			@Override
//			public void update(Observable o, Object arg) {
//				
//				if (isSaved) {
//					int comboSelectionIdx = formulaReferenceCombo.getSelectionIndex();
//					updateCombo(false);
//					if (formulaReferenceCombo.getItemCount() > 0) {
//						forceSelection(comboSelectionIdx % formulaReferenceCombo.getItemCount());
//					}
//				} else {
//					updateEditableOperationLists();
//				}
//
//			}
//		});
//		obsComboUpdateMonitor(comboUpdateMonitor);
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
			GridData layoutData = new GridData(SWT.BEGINNING, SWT.TOP, true, false);
			layoutData.horizontalSpan = BUTTONS_COLS_SPAN -1;
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

	}

	@Override
	protected int editorHeight() {
		return super.editorHeight()/3;
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
		List<Character> validSpecialChars = Arrays.asList(new Character[] {'_','-','.'});
		for (int i = 0; i < identifier.length(); i++) {
			char charAt = identifier.charAt(i);
			if (!Character.isLetterOrDigit(charAt) && !Character.isWhitespace(charAt) && !validSpecialChars.contains(identifier.charAt(i))) {
				UserDialog dialog = new UserDialog(getShell(), "Please fill in a valid identifier", addMessage);
				dialog.open();
				return false;
			}
		}
		return true;
	}

	@Override
	protected void clearPreviousCalculationsUsing(String identifier) {

		EventInfoOpsCompoOperation operation = (EventInfoOpsCompoOperation) parameterizedBuilder.getUserCurrentOperations().get(identifier);
		EventModel.dirtyCacheFor(operation);
		EventModel.updateEventInfoStamp();

	}

	@Override
	protected void deleteAllUnused() {
		Map<String, Operation> allOps = parameterizedBuilder.getThisParserCompliantUserCurrentOperations();
		for (Operation indicator: allOps.values()) {
			try {
				if (indicator.getDisabled()) parameterizedBuilder.removeFormula(indicator.getReference());
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
