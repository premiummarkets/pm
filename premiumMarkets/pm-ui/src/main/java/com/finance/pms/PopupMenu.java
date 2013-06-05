package com.finance.pms;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import com.finance.pms.portfolio.InfoObject;

public class PopupMenu<T extends InfoObject>  {

	private final Control root;
	private int style;
	private Set<T> availableOptSet;
	private Set<T> selectionSet;
	private Control parent;
	
	private Boolean unableSelectAll;

	public PopupMenu(Control parent, Control root, Set<T> availableOptSet, Set<T> selectionList, Boolean unableSelectAll, int style) {
		super();
		this.root = root;
		this.style = style;
		
		this.availableOptSet = new TreeSet<T>(new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return o1.info().compareTo(o2.info());
			}
		});
		this.availableOptSet.addAll(availableOptSet);
		
		this.selectionSet = selectionList;
		this.parent = parent;
		this.unableSelectAll = unableSelectAll;
	}

	public void open() {
		
		final Shell selectionShell =  new Shell(this.parent.getShell());
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		selectionShell.setLayout(rowLayout);
		selectionShell.setFont(MainGui.CONTENTFONT);
		
		selectionShell.addListener(SWT.Deactivate, new Listener() {
			public void handleEvent(Event e){	
				selectionShell.setVisible(false);
			}
		});
		
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
				}
				
			});
			
			if (selectionSet.contains(buttonInfo)) button.setSelection(true);
			button.setVisible(true);
		}
		
		selectionShell.pack();
		
		Rectangle parentShellBounds = parent.getShell().getBounds();
		Rectangle parentBounds = root.getParent().getBounds();
		Rectangle pparentBounds = root.getParent().getParent().getBounds();
		selectionShell.setBounds(
				parentShellBounds.x + pparentBounds.x +parentBounds.x + root.getBounds().x,  
				parentShellBounds.y + pparentBounds.y +parentBounds.y + root.getBounds().y, 
				selectionShell.getBounds().width, selectionShell.getBounds().height);
		
		selectionShell.open();
		selectionShell.setFocus();
		
		Display display = selectionShell.getDisplay();
		while (!selectionShell.isDisposed () && selectionShell.isVisible ()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		
		if (!selectionShell.isDisposed ()) {
			selectionShell.dispose();
		}
				
	}
}