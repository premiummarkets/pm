package com.finance.pms;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import com.finance.pms.portfolio.InfoObject;

public class PopupMenu<T extends InfoObject>  {

	private final Composite rootParent;
	private Control controlParent;
	private int style;
	private Shell selectionShell;
	
	
	private Set<T> availableOptSet;
	private Set<T> selectionSet;
	
	private Boolean unableSelectAll;
	private ActionDialogAction action;
	private boolean disposeOnDeactivate;
	
	private ActionDialogAction closeAction;


	public PopupMenu(Composite rootParent, Control controlParent, Set<T> availableOptSet, Set<T> selectionList, Boolean disposeOnDeactivate, Boolean unableSelectAll, int style, ActionDialogAction action) {
		super();
		this.rootParent = rootParent;
		this.controlParent = controlParent;
	
		this.style = style;
		
		this.availableOptSet = new TreeSet<T>(new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return o1.info().compareTo(o2.info());
			}
		});
		this.availableOptSet.addAll(availableOptSet);
		this.selectionSet = selectionList;
		
		this.unableSelectAll = unableSelectAll;
		
		this.action = action;
		this.disposeOnDeactivate = disposeOnDeactivate;
		
	}
	
	public PopupMenu(Composite rootParent, Control controlParent, Set<T> availableOptSet, Set<T> selectionList, Boolean disposeOnDeactivate, Boolean unableSelectAll, int style, ActionDialogAction action, ActionDialogAction closeAction) {
		this(rootParent, controlParent, availableOptSet, selectionList, disposeOnDeactivate, unableSelectAll, style, action);
		this.closeAction = closeAction;
	}

	public void open() {
		this.open(null,null);
	}
	
	public void open(Point location, Boolean addWidth) {
		
		selectionShell = new Shell(rootParent.getShell(), SWT.SHELL_TRIM);
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		rowLayout.wrap= true;
		selectionShell.setLayout(rowLayout);
		selectionShell.setFont(MainGui.CONTENTFONT);
		
		selectionShell.addListener(SWT.Deactivate, new Listener() {
			
			public void handleEvent(Event e){	
				if (disposeOnDeactivate) {
					selectionShell.dispose();
				}
			}
		});
		
		selectionShell.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				if (closeAction != null) closeAction.action(null);
			}
			
		});
		
		initPopup();
		
		if (location == null) {
			
			Rectangle parentShellBounds = rootParent.getShell().getBounds();
			Rectangle parentBounds = controlParent.getParent().getBounds();
			Rectangle pparentBounds = controlParent.getParent().getParent().getBounds();
			selectionShell.setBounds(
					parentShellBounds.x + pparentBounds.x +parentBounds.x + controlParent.getBounds().x,  
					parentShellBounds.y + pparentBounds.y +parentBounds.y + controlParent.getBounds().y, 
					selectionShell.getBounds().width, selectionShell.getBounds().height);
		} else {
			
			if (addWidth != null && addWidth) location.x = location.x + selectionShell.getSize().x;
			selectionShell.setLocation(location);
			
		}
		
		selectionShell.open();
				
	}

	protected void initPopup() {
		
		if (unableSelectAll) {
			
			final Button selectAllBut = new Button(selectionShell, style);
			selectAllBut.setText("Select/Deselect all");
			selectAllBut.setFont(MainGui.DEFAULTFONT);
			selectAllBut.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handle();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handle();
				}
				
				private void handle() {
					
					if (selectAllBut.getSelection()) {
						for (Control button : selectionShell.getChildren()) {
							((Button)button).setSelection(true);
							@SuppressWarnings("unchecked")
							T data = (T) ((Button)button).getData();
							if (data != null) selectionSet.add(data);
						}
					} else {
						for (Control button : selectionShell.getChildren()) {
							((Button)button).setSelection(false);
						}
						selectionSet.clear();
					}
					
					if (action != null) action.action(selectAllBut);
					
				}
				
			});
			
		}

		for (final T buttonInfo : availableOptSet) {
			
			final Button button = new Button(selectionShell, style);
			button.setText(buttonInfo.info());
			button.setData(buttonInfo);
			button.setFont(MainGui.DEFAULTFONT);
			button.setToolTipText(buttonInfo.tootTip());
			button.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					handleSelection(buttonInfo, button);
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handleSelection(buttonInfo, button);
					
				}
				
				private void handleSelection(T buttonInfo, final Button button) {
					
					if (button.getSelection()) {
						selectionSet.add(buttonInfo);
					} else {
						selectionSet.remove(buttonInfo);
					}
					
					if (action != null ) action.action(button);
			
				}
				
			});
			
			if (selectionSet.contains(buttonInfo)) button.setSelection(true);
			button.setVisible(true);
		}
		
		selectionShell.layout();
		selectionShell.pack();
	}

	public Shell getSelectionShell() {
		return selectionShell;
	}

	public void updateAction(ActionDialogAction action, Set<T> availEventDefs, Set<T> selectionSet) {
		
		this.action = action;
		this.availableOptSet.clear();
		this.availableOptSet.addAll(availEventDefs);
		this.selectionSet = selectionSet;
		
		Control[] children = selectionShell.getChildren();
		for (Control control : children) {
			control.dispose();
		}
		
		initPopup();
	}
}