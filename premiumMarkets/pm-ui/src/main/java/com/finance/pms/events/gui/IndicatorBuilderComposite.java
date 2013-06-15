package com.finance.pms.events.gui;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.finance.pms.MainGui;
import com.finance.pms.SpringContext;
import com.finance.pms.UserDialog;
import com.finance.pms.events.calculation.antlr.EditorIndsLexerDelegate;
import com.finance.pms.events.calculation.antlr.ParameterizedBuilder;
import com.finance.pms.events.operations.Operation;

public class IndicatorBuilderComposite extends OperationBuilderComposite {

	private Button disableFormula;

	public IndicatorBuilderComposite(Composite parent, MainGui mainGui, ComboUpdateMonitor comboUpdateMonitor) {
		super(parent, mainGui);
		
		comboUpdateMonitor.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				updateCombo();
			}
		});
	}

	@Override
	protected ParameterizedBuilder initParameterizedBuilder() {
		return (ParameterizedBuilder) SpringContext.getSingleton().getBean("parameterizedIndicatorsBuilder");
	}

	protected String builderLabel() {
		return "Events Calculator";
	}

	@Override
	protected void addExtratButtons() {
		
		{
			disableFormula = new Button(this, SWT.CHECK);
			GridData layoutData = new GridData(SWT.BEGINNING,SWT.TOP,true,false);
			layoutData.horizontalSpan =2;
			disableFormula.setLayoutData(layoutData);
			disableFormula.setText("Disabled "+builderLabel());
			disableFormula.setFont(MainGui.DEFAULTFONT);
			disableFormula.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handle();
				}

				private void handle() {
					if (disableFormula.getSelection()) {
						disableFormula(getFormatedReferenceTxt());
					} else {
						enableFormula(getFormatedReferenceTxt());
					}
					
					checkBoxDisabled();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handle();
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
			UserDialog dialog = new UserDialog(getShell(), SWT.NONE, "Formula can't be enabled.", e.toString());
			LOGGER.warn(e,e);
			dialog.open();
			return;
		} catch (Exception e) {
			UserDialog dialog = new UserDialog(getShell(), SWT.NONE, "Found invalid formulas while storing data.", e.toString());
			LOGGER.warn(e,e);
			dialog.open();
		} 
		
		checkBoxDisabled();
		clearPreviousCalcsWarning();

	}

	private void disableFormula(String identifier) {

		if (!isValidId(identifier)) return;
		Operation existingOp = parameterizedBuilder.getCurrentOperations().get(identifier);
		if(isNativeOp(identifier, existingOp)) return;

		try {

			parameterizedBuilder.disableFormula(identifier);

		} catch (IOException e) {
			UserDialog dialog = new UserDialog(getShell(), SWT.NONE, "Formula can't be disabled.", e.toString());
			LOGGER.warn(e,e);
			dialog.open();
			return;
		} catch (Exception e) {
			UserDialog dialog = new UserDialog(getShell(), SWT.NONE, "Found invalid formulas while storing data.", e.toString());
			LOGGER.warn(e,e);
			dialog.open();

		} 
		
		checkBoxDisabled();
		clearPreviousCalcsWarning();

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
	protected Boolean checkIdCharacters(String identifier, String addMessage) {
		for (int i = 0; i < identifier.length(); i++) {
			char charAt = identifier.charAt(i);
			if (!Character.isLetterOrDigit(charAt) && !Character.isWhitespace(charAt)) {
				UserDialog dialog = new UserDialog(getShell(), SWT.NONE, "Please fill in a valid identifier", addMessage);
				dialog.open();
				return false;
			}
		}
		return true;
	}
	
	
	

}
