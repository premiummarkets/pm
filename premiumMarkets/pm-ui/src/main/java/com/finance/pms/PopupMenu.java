package com.finance.pms;

import java.util.Set;

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
	private Set<T> buttonInfoList;
	private Set<T> selectionList;
	private Control parent;

	public PopupMenu(Control parent, Control root, Set<T> buttonInfoList, Set<T> selectionList, int style) {
		super();
		this.root = root;
		this.style = style;
		this.buttonInfoList = buttonInfoList;
		this.selectionList = selectionList;
		this.parent = parent;
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

		for (final T buttonInfo : buttonInfoList) {
			
			final Button button = new Button(selectionShell, style);
			button.setText(buttonInfo.info());
			button.setFont(MainGui.DEFAULTFONT);
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
						selectionList.add(buttonInfo);
					} else {
						selectionList.remove(buttonInfo);
					}
				}
				
			});
			
			if (selectionList.contains(buttonInfo)) button.setSelection(true);
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