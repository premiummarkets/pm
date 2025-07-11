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
package com.finance.pms.portfolio.gui.charts;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.finance.pms.ActionDialog;
import com.finance.pms.ActionDialogAction;
import com.finance.pms.MainGui;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.portfolio.InfoObject;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;

public abstract class ChartDisplayStrategy {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ChartDisplayStrategy.class);

	
	protected enum PopupType {EVTCHARTING, EVTTREND};

	class TransfoInfo implements InfoObject {
		String info;
		ActionDialogAction action;

		public TransfoInfo(String info, ActionDialogAction action) {
			super();
			this.info = info;
			this.action = action;
		}

		@Override
		public String info() {
			return info;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((info == null) ? 0 : info.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TransfoInfo other = (TransfoInfo) obj;
			if (info == null) {
				if (other.info != null)
					return false;
			} else if (!info.equals(other.info))
				return false;
			return true;
		}

		@Override
		public String toolTip() {
			return info();
		}

	}

	protected ChartsComposite chartTarget;
	private StripedCloseFunction stripedCloseFunction;
	private ActionDialog popupDialog;

	public abstract void highLight(Integer idx, Stock selectedShare, Boolean recalculationGranted, PopupType... popupTypes);

	public abstract void initRefreshAction();

	public abstract void endRefreshAction(List<Exception> exceptions);

	public abstract void populatePopups(Composite popusGroup);

	protected void cleanPopupButtonsGroup(Composite popusGroup) {
		Control[] children = popusGroup.getChildren();
		for (Control control : children) {
			try {
				control.dispose();
			} catch (Exception e) {
				LOGGER.warn("Could not dispose " + control);
			}
		}
	}

	protected Boolean checkIfShowing(Stock selectedShare) {
		for (SlidingPortfolioShare slidingPortfolioShare : chartTarget.getCurrentTabShareList()) {
			if (slidingPortfolioShare.getStock().equals(selectedShare) && slidingPortfolioShare.getDisplayOnChart()) {
				return true;
			}
		}
		return false;
	}

	public abstract void resetChart(Boolean resetDisplayedList, PopupType ...popupTypes);

	public abstract Boolean getIsApplyColor();

	public abstract void exportBarChartPng();

	public  abstract void refreshView(List<Exception> exceptions);

	public abstract void shutDownDisplay();

	public abstract void updateButtonsToolTips();

	public void slideChart(int increment) {
		chartTarget.getMainChartWraper().slideChart(increment, chartTarget.getPlotChartDimensions());
	}

	public void addVLineAt(Point2D point2D, Rectangle2D rectangle2D) {
		chartTarget.getMainChartWraper().addVLineAt(point2D, rectangle2D);
	}

	public Boolean removeVLineAt(Point2D point2D, Rectangle2D rectangle2D) {
		return chartTarget.getMainChartWraper().removeVLineAt(point2D, rectangle2D);
	}

	public void removeVLines() {
		chartTarget.getMainChartWraper().removeVLines();
	}

	public Boolean removeHLineAt(Point2D point2D, Rectangle2D rectangle2D) {
		return chartTarget.getMainChartWraper().removeHLineAt(point2D, rectangle2D);
	}

	public void removeHLines() {
		chartTarget.getMainChartWraper().removeHLines();
	}

	protected void showPopupDialog(final String errorMessage, final String buttonTxt, final String addMessage, ActionDialogAction action) {

		if (action == null) {
			action = new ActionDialogAction() {
				@Override
				public void action() {
				}
			};
		}

		final ActionDialogAction fAction = action;
		Runnable runnable = new Runnable() {
			public void run() {
				if (popupDialog == null || popupDialog.getParent().isDisposed()) {
					popupDialog = new ActionDialog(chartTarget.getShell(), "Warning", errorMessage, addMessage, buttonTxt, fAction);
					popupDialog.open();
				} else {
					if (!popupDialog.sameDialog(errorMessage, addMessage, buttonTxt)) {
						popupDialog.updateDialog(MainGui.APP_NAME + " - Warning", errorMessage, addMessage, buttonTxt, fAction);
					}
				}
			}
		};
		Display.getDefault().asyncExec(runnable);

	}

	protected synchronized void hidePopupDialog() {
		if (popupDialog != null) popupDialog.getParent().dispose();
	}

	public abstract void cleanPreviousStockSelection();
	
	public StripedCloseFunction getStripedCloseFunction() {
		return stripedCloseFunction;
	}

	public void setStripedCloseFunction(StripedCloseFunction stripedCloseFunction) {
		this.stripedCloseFunction = stripedCloseFunction;
	}

	public abstract void init(ChartsComposite chartTarget);

}
