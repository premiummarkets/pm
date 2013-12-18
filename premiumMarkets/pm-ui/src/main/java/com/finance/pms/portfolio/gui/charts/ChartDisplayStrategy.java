package com.finance.pms.portfolio.gui.charts;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.finance.pms.ActionDialog;
import com.finance.pms.ActionDialogAction;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.portfolio.InfoObject;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;

public abstract class ChartDisplayStrategy {
	
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
		public String tootTip() {
			return info();
		}

	}

	protected ChartsComposite chartTarget;
	private ActionDialog popupDialog;
	
	public abstract void highLight(Integer idx, Stock selectedShare, Boolean recalculationGranted);

	public abstract void initRefreshAction();

	public abstract void endRefreshAction(List<Exception> exceptions);

	public abstract void populatePopups(Composite popusGroup);

	protected void cleanPopupButtonsGroup(Composite popusGroup) {
		Control[] children = popusGroup.getChildren();
		for (Control control : children) {
			control.dispose();
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

	public abstract void resetChart();
	
	public abstract void lightResetChart();

	public abstract Boolean getIsApplyColor();

	public abstract int retreivePreviousSelection();

	public abstract void exportBarChartPng();

	public  abstract void refreshView(List<Exception> exceptions);

	public abstract void shutDownDisplay();

	public abstract void updateButtonsToolTips();

	public void slideChart(int increment) {
		chartTarget.getMainChartWraper().slideChart(increment, chartTarget.getPlotChart());
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
				public void action(Control targetControl) {
				}
			};
		}
		
		final ActionDialogAction fAction = action;
		Runnable runnable = new Runnable() {
			public void run() {
				if (popupDialog == null || popupDialog.getParent().isDisposed()) {
					popupDialog = new ActionDialog(chartTarget.getShell(), SWT.NONE, "Warning", errorMessage, addMessage, buttonTxt, fAction);
					popupDialog.open(true);
				} else {
					if (!popupDialog.sameDialog(errorMessage, addMessage, buttonTxt)) {
						popupDialog.updateDialog("Warning", errorMessage, addMessage, buttonTxt, fAction);
					}
				}
			}
		};
		Display.getDefault().syncExec(runnable);
		
	}

	protected synchronized void hidePopupDialog() {
		if (popupDialog != null) popupDialog.getParent().dispose();
	}

}
