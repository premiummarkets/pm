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
package com.finance.pms;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * The additional param runAsync means we want the action to be run in parallel of the caller execution
 * This can be done in two ways :  
 *  with runInthread it will spawn a new Thread and execute a sync from there {@link org.eclipse.swt.widgets.Display#syncExec}
 *  without run in Thread the standard SWT aSync will be used {@link org.eclipse.swt.widgets.Display#asyncExec}
 *
 */
public class ActionDialog extends UserDialog {

    protected ActionDialogAction errorHandler;

    protected ActionDialogAction action;
    String actionTxt;
    private boolean runInThread;

    private boolean runASync;

    public ActionDialog(Shell parent, String title, String erreur, String addMessage, String actionTxt, ActionDialogAction action) {
        super(parent, title, erreur, addMessage);
        this.actionTxt = actionTxt;
        this.action = action;
        runInThread = true;
        runASync = true;
    }

    public ActionDialog(Shell parent, String title, String erreur, String addMessage, String actionTxt, ActionDialogAction action, Boolean runASync) {
        super(parent, title, erreur, addMessage);
        this.actionTxt = actionTxt;
        this.action = action;
        this.runASync = runASync;
    }

    @Override
    public void open() {
        super.open();
    }

    @Override
    protected void validationButtonTxtAndAction() {

        valideButton.setText(actionTxt);
        valideButton.setFont(MainGui.DEFAULTFONT);
        valideButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDown(MouseEvent evt) {
                doAction();
            }
        });
        valideButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.keyCode == SWT.CR || evt.keyCode == SWT.SPACE) {
                    doAction();
                }
            }
        });
    }

    protected void doAction() {

        valideButton.setCapture(true);
        valideButton.forceFocus();

        getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
        Runnable uiAsyncRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    action.action();
                    Display.getDefault().wake();
                } catch (Exception e) {
                    LOGGER.error(e);
                    if (errorHandler != null) {
                        errorHandler.action();
                    } else {
                        try {
                            ActionDialog.this.errorTxt.setText(cleanMsg(e.toString(), true));
                            ActionDialog.this.errorTxt.setVisible(true);
                            ActionDialog.this.getParent().pack();
                        } catch (Exception e1) {
                            LOGGER.warn(e,e);
                        }
                    }
                } finally {
                    if (!getParent().isDisposed()) getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
                }
            }
        };

        if (runASync) {
            if (runInThread) {
                new Thread(new Runnable() {
                    public void run() {
                        ActionDialog.this.getParent().getDisplay().syncExec(uiAsyncRunnable);
                        Display.getDefault().wake();
                    }
                }).start();
            } else {
                ActionDialog.this.getParent().getDisplay().asyncExec(uiAsyncRunnable);
                Display.getDefault().wake();
            }
        } else {
            uiAsyncRunnable.run();
        }

        try {
            dispose();
        } catch (Exception e) {
            LOGGER.warn(e,e);
        }

    }


    public void updateDialog(String title, String erreur, String addMessage, String actionTxt, ActionDialogAction action) {

        super.updateDialog(title, erreur, addMessage);

        this.actionTxt = actionTxt;
        this.action = action;
        valideButton.setText(actionTxt);

        layout();

        getParent().setActive();
        valideButton.setFocus();
    }


    public boolean sameDialog(String erreur, String addMessage, String actionTxt) {

        if (!super.sameDialog(erreur, addMessage))
            return false;
        if (this.actionTxt == null) {
            if (actionTxt != null)
                return false;
        } else if (!this.actionTxt.equals(actionTxt))
            return false;
        return true;
    }

    public void setErrorHandler(ActionDialogAction errorHandler) {
        this.errorHandler = errorHandler;
    }

}
