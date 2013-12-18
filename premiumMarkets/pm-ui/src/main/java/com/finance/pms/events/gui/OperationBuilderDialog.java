package com.finance.pms.events.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import com.finance.pms.MainGui;

public class OperationBuilderDialog {

	protected Shell shell;
	protected Shell parent;

	private MainGui mainGui;

	public OperationBuilderDialog(Shell parent, MainGui mainGui) {
		this.parent = parent;
		this.mainGui = mainGui;
	}

	public Object open(Point location, int width) {
	
        shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.RESIZE);
        shell.setText("Premium Markets - Customise and Create indicators ...");
        
        initGui();
        
        if (location != null) {
			shell.setLocation(location);
			Point computeSize = shell.computeSize(width, SWT.DEFAULT);
			shell.setSize(computeSize);
		}
		shell.open();
        
        return null;
        
	}

	private void initGui() {
		
		shell.setLayout(new GridLayout());
		shell.setBackground(MainGui.pOPUP_BG);
		
		Group opGrp = new Group(shell, SWT.NONE);
		opGrp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		opGrp.setLayout(new GridLayout());
		opGrp.setText("Customise and Create Operations");
		opGrp.setFont(MainGui.DEFAULTFONT);
		opGrp.setBackground(MainGui.pOPUP_GRP);
		OperationBuilderComposite operationBuilderDialog = new OperationBuilderComposite(opGrp, mainGui);
		operationBuilderDialog.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Group indcGrp = new Group(shell, SWT.NONE);
		indcGrp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		indcGrp.setLayout(new GridLayout());
		indcGrp.setText("Customise and Create Trends Calculators");
		indcGrp.setFont(MainGui.DEFAULTFONT);
		indcGrp.setBackground(MainGui.pOPUP_GRP);
		IndicatorBuilderComposite indicatorBuilderComposite = new IndicatorBuilderComposite(indcGrp, mainGui, operationBuilderDialog.getComboUpdateMonitor());
		operationBuilderDialog.obsComboUpdateMonitor(indicatorBuilderComposite.getComboUpdateMonitor());
		indicatorBuilderComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		shell.layout();
		
		operationBuilderDialog.forceSelection(0);
		indicatorBuilderComposite.forceSelection(0);
		
	}

	public Shell getShell() {
		return shell;
	}


}
