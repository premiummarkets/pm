package com.finance.pms.portfolio.gui.charts;

import java.util.prefs.BackingStoreException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.ActionDialogAction;
import com.finance.pms.MainGui;
import com.finance.pms.MainPMScmd;
import com.finance.pms.UserDialog;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.scoring.chartUtils.BarSettings;

public class BarSettingsDialog {
	
	private static MyLogger LOGGER = MyLogger.getLogger(BarSettingsDialog.class);
	
	private Shell shell;
	private Shell parent;
	private ChartsComposite chartTarget;

	private ActionDialogAction action;

	private BarSettings barChartSettings;
	private Double initialAlphaDividend;
	
	private Button sideBySide;

	
	public BarSettingsDialog(ChartsComposite chartTarget, BarSettings barSettings, ActionDialogAction action) {
		this.barChartSettings = barSettings;
		this.parent= chartTarget.getShell();
		this.chartTarget = chartTarget;
		this.action = action;
		initialAlphaDividend = barChartSettings.getAlphaDividend();
	}

	public BarSettings getBarChartSettings() {
		return barChartSettings;
	}
	
	public BarSettings open(Point location) {
		
        shell = new Shell(parent.getShell(), SWT.DIALOG_TRIM);
        shell.setText("Premium Markets - Trend Display Settings ...");
        
        shell.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent event) {
				try {
					MainPMScmd.getPrefs().flush();
				} catch (BackingStoreException e) {
					LOGGER.error(e,e);
				}
				
			}
		});
        
        initGui();
        
        shell.setLocation(location);
        
		shell.layout();
		shell.pack();
        
        shell.open();
        
        return barChartSettings;
        
	}
	
	

	private void initGui() {

		GridLayout layout = new GridLayout(2, true);
		shell.setLayout(layout);
		shell.setBackground(MainGui.pOPUP_BG);
		
		{
			Label evtOccSpanLab = new Label(shell, SWT.NONE);
			GridData labelLayoutData = new GridData(SWT.FILL,SWT.TOP,true,false);
			evtOccSpanLab.setLayoutData(labelLayoutData);
			evtOccSpanLab.setBackground(MainGui.pOPUP_BG);
			evtOccSpanLab.setFont(MainGui.DEFAULTFONT);
			evtOccSpanLab.setText("Trend event days span");
			evtOccSpanLab.setToolTipText("Trend events can be represented with a validity span, reflected in the width of the bars.\nZero means that the trend event is valid until an other event occurs.");
			
			final Text evtOccSpanTxt = new Text(shell, SWT.NONE);
			GridData txtLayoutData = new GridData(SWT.FILL,SWT.TOP,true,false);
			evtOccSpanTxt.setLayoutData(txtLayoutData);
			evtOccSpanTxt.setFont(MainGui.DEFAULTFONT);
			evtOccSpanTxt.setText(""+barChartSettings.getMaxFill());

			Listener listener = new Listener() {
				public void handleEvent(Event e) {
					try {
						barChartSettings.setMaxFill(Integer.valueOf(evtOccSpanTxt.getText()));
						action.action(null);
					} catch (NumberFormatException pe) {
						UserDialog inst = new UserDialog(shell, pe.getMessage(), null);
						inst.open();
						evtOccSpanTxt.setText(""+barChartSettings.getMaxFill());
					}
				}
			};
			evtOccSpanTxt.addListener (SWT.FocusOut, listener);
			evtOccSpanTxt.addListener (SWT.DefaultSelection, listener);
			
		}
		{
			final Button isReachTop = new Button(shell, SWT.CHECK);
			GridData layoutData = new GridData(SWT.FILL,SWT.TOP,true,false);
			layoutData.horizontalSpan = 2;
			isReachTop.setLayoutData(layoutData);
			isReachTop.setBackground(MainGui.pOPUP_BG);
			isReachTop.setFont(MainGui.DEFAULTFONT);
			isReachTop.setText("Draw bars to the top");
			isReachTop.setToolTipText("All the bars will at the top, overlapping each other with transparency.\nIf unchecked, bars stop at label level.");
			isReachTop.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					handle();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handle();
				}

				private void handle() {
					barChartSettings.setIsReachTop(isReachTop.getSelection());
					action.action(null);
					
				}
			});
			isReachTop.setSelection(barChartSettings.getIsReachTop());
		}
		{
			final Button isZeroBase = new Button(shell, SWT.CHECK);
			GridData layoutData = new GridData(SWT.FILL,SWT.TOP,true,false);
			layoutData.horizontalSpan = 2;
			isZeroBase.setLayoutData(layoutData);
			isZeroBase.setBackground(MainGui.pOPUP_BG);
			isZeroBase.setFont(MainGui.DEFAULTFONT);
			isZeroBase.setText("Draw bars to the base");
			isZeroBase.setToolTipText("All the bars will start at zero, overlapping each other with transparency.\nIf unchecked, bars stop at label level.");
			isZeroBase.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					handle();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handle();
				}

				private void handle() {
					barChartSettings.setIsZerobased(isZeroBase.getSelection());
					action.action(null);
					
				}
			});
			isZeroBase.setSelection(barChartSettings.getIsZeroBased());
		}
		{
			sideBySide = new Button(shell, SWT.CHECK);
			GridData layoutData = new GridData(SWT.FILL,SWT.TOP,true,false);
			layoutData.horizontalSpan = 2;
			sideBySide.setLayoutData(layoutData);
			sideBySide.setBackground(MainGui.pOPUP_BG);
			sideBySide.setFont(MainGui.DEFAULTFONT);
			sideBySide.setText("Display side by side");
			sideBySide.setToolTipText(
					"Bar are displayed side by side, form top to bottom.\n" +
					"For this to be working, the event validity days span has to be above the number of indicators involved.");
			sideBySide.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					handle();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handle();
				}

				private void handle() {
					barChartSettings.setSideBySide(sideBySide.getSelection());
					action.action(null);
				}
			});
			sideBySide.addMouseMoveListener(new MouseMoveListener() {
				
				@Override
				public void mouseMove(MouseEvent e) {
					sideBySide.setToolTipText(
							"Bar are displayed side by side, form top to bottom.\n" +
							"For this to be working, the event validity days span has to be above the number of indicators involved.\n" +
							"I.e. : "+chartTarget.getChartedEvtDefsTrends().size());
				}
			});
			sideBySide.setSelection(barChartSettings.getSideBySide());
		}
		{
			final Button isGradient = new Button(shell, SWT.CHECK);
			GridData layoutData = new GridData(SWT.FILL,SWT.TOP,true,false);
			layoutData.horizontalSpan = 2;
			isGradient.setLayoutData(layoutData);
			isGradient.setBackground(MainGui.pOPUP_BG);
			isGradient.setFont(MainGui.DEFAULTFONT);
			isGradient.setText("Progressive opacity");
			isGradient.setToolTipText("Bar are displayed using a progressive opacity.\nIf unchecked, bars are using the same opacity");
			isGradient.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					handle();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handle();
				}

				private void handle() {
					barChartSettings.setIsGradient(isGradient.getSelection());
					action.action(null);
				}
			});
			isGradient.setSelection(barChartSettings.getIsGradiant());
		}
		{
			final Spinner alphaSpinner = new Spinner(shell, SWT.WRAP|SWT.READ_ONLY);
			GridData layoutData = new GridData(SWT.FILL,SWT.TOP,false,false);
			layoutData.horizontalSpan = 2;
			alphaSpinner.setLayoutData(layoutData);
			alphaSpinner.setFont(MainGui.DEFAULTFONT);
			int digits = 1;
			alphaSpinner.setDigits(digits);
			alphaSpinner.setMinimum(5);
			alphaSpinner.setMaximum(EventDefinition.loadMaxPassPrefsEventInfo().size()*10);
			alphaSpinner.setIncrement(5);
			alphaSpinner.setSelection((int)(barChartSettings.getAlphaDividend()*Math.pow(10, digits)));
			alphaSpinner.setToolTipText(
					"For a better visibility of the result, You can change the opacity of the charted trend.\n" +
							"You must select a share in the portfolio to display its analysis\n" +
							"You also must choose the trends you want to display using the '"+ChartIndicatorDisplay.TRENDBUTTXT+"' button.");
			alphaSpinner.addSelectionListener(new SelectionAdapter() {

				public void widgetSelected(SelectionEvent e) {
					int selection = alphaSpinner.getSelection();
					int digits = alphaSpinner.getDigits();
					barChartSettings.setAlphaDividend(selection / Math.pow(10, digits));
				}
			});
			alphaSpinner.addListener(SWT.MouseExit, new Listener() {

				public void handleEvent(Event arg0) {
					if (!barChartSettings.getAlphaDividend().equals(initialAlphaDividend)) {
						action.action(null);
						initialAlphaDividend = barChartSettings.getAlphaDividend();
					}
				}

			});
		}

		
	}

	public Shell getShell() {
		return shell;
	}

}
