package com.finance.pms;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public abstract class AutoCompletePopupMenu<T> {
	
	private Shell parentShell;
	private Composite container;
	private Text textBox;

	public AutoCompletePopupMenu(Shell parentShell, Composite container, Text textBox) {
		super();
		this.parentShell = parentShell;
		this.container = container;
		this.textBox = textBox;
	}
	
	public void initPopupMenu() 	{
		
		final Shell popupShell = new Shell(parentShell, SWT.ON_TOP);
		parentShell.addListener(SWT.Traverse, new Listener() {
			public void handleEvent(Event event) {
				switch (event.detail) {
				case SWT.TRAVERSE_ESCAPE:
					event.detail = SWT.TRAVERSE_NONE;
					event.doit = false;
					break;
				}
			}
		});
		parentShell.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent evt) {
				if (!popupShell.isDisposed()) popupShell.dispose();
			}
		});
		popupShell.setLayout(new FillLayout());
		final Table table = new Table(popupShell, SWT.SINGLE);
		table.setFont(MainGui.CONTENTFONT);
		
		textBox.addListener(SWT.KeyDown, new Listener() {
			public void handleEvent(Event event) {
				switch (event.keyCode) {
					case SWT.ARROW_DOWN:
						if (popupShell.isVisible() && table.getItemCount() != 0) {
							int index = (table.getSelectionIndex() + 1) % table.getItemCount();
							table.setSelection(index);
							event.doit = false;
						}
						break;
					case SWT.ARROW_UP:
						if (popupShell.isVisible() && table.getItemCount() != 0) {
							int index = table.getSelectionIndex() - 1;
							if (index < 0) index = table.getItemCount() - 1;
							table.setSelection(index);
							event.doit = false;
						}
						break;
					case SWT.CR:
						if (popupShell.isVisible() && table.getSelectionIndex() != -1) {
							textBox.setText(table.getSelection()[0].getText());
							popupShell.setVisible(false);
						}
						break;
					case SWT.ESC:
						popupShell.setVisible(false);
						break;
				}
			}
		});
		textBox.addListener(SWT.Modify, new Listener() {

			public void handleEvent(Event event) {
				final String typedInString = textBox.getText();
				if (typedInString.length() == 0) {
					popupShell.setVisible(false);
				} else {
					List<T> alikes = loadAlikes(typedInString);
					
					TableItem[] items = table.getItems();
					for (int i = 0; i < alikes.size(); i++) {
						if(i < items.length) {
							items[i].setText(transalateALike(alikes.get(i)));
						} else {
							TableItem tableItem = new TableItem(table, SWT.NONE);
							tableItem.setText(transalateALike(alikes.get(i)));
						}
					}
					table.remove(alikes.size(), table.getItems().length-1);
					
					if (table.getItems().length > 0) {
						table.select(0);
					}
					
					Rectangle textBounds = parentShell.getDisplay().map(container, null, textBox.getBounds());
					popupShell.setBounds(textBounds.x, textBounds.y + textBounds.height, textBounds.width, 150);
					popupShell.setVisible(true);
				}
			}

		});

		table.addListener(SWT.DefaultSelection, new Listener() {
			public void handleEvent(Event event) {
				textBox.setText(table.getSelection()[0].getText());
				popupShell.setVisible(false);
			}
		});
		table.addListener(SWT.KeyDown, new Listener() {
			public void handleEvent(Event event) {
				if (event.keyCode == SWT.ESC) {
					popupShell.setVisible(false);
				}
			}
		});

		Listener focusOutListener = new Listener() {
			public void handleEvent(Event event) {
				/* async is needed to wait until focus reaches its new Control */
				parentShell.getDisplay().asyncExec(new Runnable() {
					public void run() {
						if (popupShell.isDisposed() || parentShell.getDisplay().isDisposed()) return;
						Control control = parentShell.getDisplay().getFocusControl();
						if (control == null || (control != textBox && control != table) && control != popupShell) {
							popupShell.setVisible(false);
						}
					}
				});
			}
		};
		table.addListener(SWT.FocusOut, focusOutListener);
		textBox.addListener(SWT.FocusOut, focusOutListener);

		parentShell.addListener(SWT.Move, new Listener() {
			public void handleEvent(Event event) {
				popupShell.setVisible(false);
			}
		});
	}

	public Text getTextBox() {
		return textBox;
	}
	
	public abstract String transalateALike(T alike);
	
	public abstract List<T> loadAlikes(String typedInString);

}
