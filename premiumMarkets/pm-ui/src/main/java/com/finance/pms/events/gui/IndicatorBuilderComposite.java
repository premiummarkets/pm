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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

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
		if (operation != null) {
			EventModel.dirtyCacheFor(operation, true);
			EventModel.updateEventInfoStamp();
		}

	}

	@Override
	protected void deleteAllUnused() {
		Map<String, Operation> allOps = parameterizedBuilder.getThisParserCompliantUserCurrentOperations();
		for (Operation indicator: allOps.values()) {
			if (indicator.getDisabled()) {
				List<Operation> usingOps = parameterizedBuilder.removeFormula(indicator.getReference());
				if (!usingOps.isEmpty()) {
					LOGGER.error(indicator.getReference() + " is in use, and can't be deleted, by " + usingOps.stream().map(o -> o.getReference()).reduce((a,o) -> a + ", " + o));
				}
			}
			
		}
	}

}
