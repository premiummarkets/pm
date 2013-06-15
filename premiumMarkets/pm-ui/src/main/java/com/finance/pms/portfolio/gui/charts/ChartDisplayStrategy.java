package com.finance.pms.portfolio.gui.charts;

import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

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
	
	public abstract void highLight(Integer idx, final Stock selectedShare, boolean recalculationGranted);

	public abstract void initRefreshAction();

	public abstract void endRefreshAction(List<Exception> exceptions);

	public abstract void populatePopups(Composite popusGroup);

	protected void cleanPopupsGroup(Composite popusGroup) {
		Control[] children = popusGroup.getChildren();
		for (Control control : children) {
			control.dispose();
		}
	}
	
	protected Boolean checkIfShowing(Stock selectedShare) {
		for (SlidingPortfolioShare slidingPortfolioShare : chartTarget.getListShares()) {
			if (slidingPortfolioShare.getStock().equals(selectedShare) && slidingPortfolioShare.getDisplayOnChart()) {
				return true;
			}
		}
		return false;
	}

	public abstract void resetChart();

	public abstract Boolean getIsApplyColor();

	public abstract void highLighPrevious();

	public abstract void exportBarChartPng();

	public  abstract void refreshView(List<Exception> exceptions);

	public abstract void shutDownDisplay();

}
