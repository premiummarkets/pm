package com.finance.pms;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public abstract class TableToolTip implements Listener {


	public TableToolTip() {
		super();
	}


	@Override
	public void handleEvent(Event event) {

		switch (event.type) {
		case SWT.MouseHover: 
			{
				buildAndShowToolTip(event);
			}
		}

	}

	protected abstract void buildAndShowToolTip(Event event);
	
	
	public Shell showTooltip(Shell parent, int x, int y, String txt) {
		
		Shell tooltip = new Shell(parent, SWT.TOOL | SWT.ON_TOP);
		tooltip.setLayout(new GridLayout());

		tooltip.setBackground(tooltip.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
		tooltip.setBackgroundMode(SWT.INHERIT_FORCE);

		Label lbContent = new Label(tooltip, SWT.NONE);
		lbContent.setFont(MainGui.CONTENTFONT);
		lbContent.setForeground(tooltip.getDisplay().getSystemColor(SWT.COLOR_INFO_FOREGROUND));
		lbContent.setText(txt);

		Point lbContentSize = lbContent.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		int width = lbContentSize.x + 10;
		int height = lbContentSize.y + 10;

		tooltip.setBounds(x + 5, y , width, height);
		tooltip.setVisible(true);
		return tooltip;
	}

}
