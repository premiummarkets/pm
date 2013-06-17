package com.finance.pms.events.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import com.finance.pms.MainGui;
import com.finance.pms.admin.install.logging.MyLogger;

public class OperationBuilderDialog {
	
	private static MyLogger LOGGER = MyLogger.getLogger(OperationBuilderDialog.class);
	
	protected Shell shell;
	protected Shell parent;

	private MainGui mainGui;

	public OperationBuilderDialog(Shell parent, MainGui mainGui) {
		this.parent = parent;
		this.mainGui = mainGui;
	}

	public Object open() {
	
        shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.RESIZE);
        shell.setText("Premium Markets - Parameterise and Create indicators ...");
        
        initGui();
        
        shell.open();
        Display display = parent.getDisplay();
        while (!shell.isDisposed()) {
                try {
                	 if (!display.readAndDispatch()) display.sleep();
    			} catch (RuntimeException e) {
    				LOGGER.error("Error in OperationBuilderDialog Gui : " + e.getMessage(),e);
    			} catch (Error e) {
    				LOGGER.error("Error in  Gui : " + e.getMessage(),e);
    			}
        }
        
        return null;
        
	}

	private void initGui() {
		
		shell.setLayout(new GridLayout());
		shell.setBackground(MainGui.pOPUP_BG);
		
		Group opGrp = new Group(shell, SWT.NONE);
		opGrp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		opGrp.setLayout(new GridLayout());
		opGrp.setText("Parameterise and Create indicators' calculation");
		opGrp.setFont(MainGui.DEFAULTFONT);
		opGrp.setBackground(MainGui.pOPUP_GRP);
		OperationBuilderComposite operationBuilderDialog = new OperationBuilderComposite(opGrp, mainGui);
		operationBuilderDialog.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Group indcGrp = new Group(shell, SWT.NONE);
		indcGrp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		indcGrp.setLayout(new GridLayout());
		indcGrp.setText("Parameterise and Create events' calculation");
		indcGrp.setFont(MainGui.DEFAULTFONT);
		indcGrp.setBackground(MainGui.pOPUP_GRP);
		IndicatorBuilderComposite indicatorBuilderComposite = new IndicatorBuilderComposite(indcGrp, mainGui, operationBuilderDialog.getComboUpdateMonitor());
		indicatorBuilderComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		shell.layout();
		
	}


}
