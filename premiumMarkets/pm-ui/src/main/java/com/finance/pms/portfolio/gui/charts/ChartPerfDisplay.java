package com.finance.pms.portfolio.gui.charts;

import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.finance.pms.ActionDialogAction;
import com.finance.pms.CursorFactory;
import com.finance.pms.UserDialog;
import com.finance.pms.MainGui;
import com.finance.pms.MainPMScmd;
import com.finance.pms.PopupMenu;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.StripedCloseAbsoluteRelative;
import com.finance.pms.datasources.db.StripedCloseIndexRelative;
import com.finance.pms.datasources.db.StripedCloseLogRoc;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.portfolio.gui.ActionDialogForm;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;

public class ChartPerfDisplay extends ChartDisplayStrategy {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ChartPerfDisplay.class);
	
	private Quotations refereeQuotations;
	

	public ChartPerfDisplay(ChartsComposite chartTarget) {
		super();
		this.chartTarget = chartTarget;
		populatePopups(chartTarget.getPopusGroup());
		this.chartTarget.getMainChartWraper().initMainPlot(
				ChartMain.PERCENTAGE_FORMAT, "No data available. Check that the portfolio stocks and sliding date ranges are valid. There may not be quotations available for this operation.");
	}

	@Override
	public void highLight(Integer idx, Stock selectedShare, boolean recalculationGranted) {

		chartTarget.getMainChartWraper().setMainYAxisLabel("");
		if (!chartTarget.isVisible() || idx == null || selectedShare == null ) {
			return;
		}
		
		chartTarget.setHighligtedId(idx);
		chartTarget.getHightlitedEventModel().setViewStateParams(selectedShare);
		
		Boolean isShowing = checkIfShowing(selectedShare);
		if (!isShowing) {
			for (SlidingPortfolioShare slidingPortfolioShare : chartTarget.getListShares()) {
				if (slidingPortfolioShare.getStock().equals(selectedShare)) {
					slidingPortfolioShare.setDisplayOnChart(true);
					break;
				}
			}
			chartTarget.getStripedCloseFunction().updateStartDate(chartTarget.getSlidingStartDate());
			chartTarget.getStripedCloseFunction().updateEndDate(chartTarget.getSlidingEndDate());
			chartTarget.getMainChartWraper().updateLineDataSet(chartTarget.getListShares(), chartTarget.getStripedCloseFunction(), getIsApplyColor());

		}
		
		chartTarget.getMainChartWraper().highLightSerie(chartTarget.getHighligtedId(), 3);
	}

	@Override
	public void initRefreshAction() {
		//Nothing
	}

	@Override
	public void endRefreshAction(List<Exception> exceptions) {
		//Nothing
	}

	@Override
	public void populatePopups(final Composite popusGroup) {
		
		cleanPopupsGroup(popusGroup);
		
		final String cmpModeToolTipRoot = "This is to set the comparison mode between several stocks.\nUse the 'Hide / Show stock' button to display several stock on the chart.\n";
		
		{
			final Button closeFunctionBut = new Button(popusGroup, SWT.PUSH);
			closeFunctionBut.setFont(MainGui.DEFAULTFONT);
			closeFunctionBut.setText("Comparison mode ...");
			closeFunctionBut.setToolTipText(cmpModeToolTipRoot+"The current comparison mode is '"+chartTarget.getStripedCloseFunction().lineToolTip()+"'");
			final Set<TransfoInfo> transfos = new HashSet<TransfoInfo>(Arrays.asList(new TransfoInfo[]{
					new TransfoInfo("Change to buy price", new ActionDialogAction() {

						@Override
						public void action(Button targetButton) {
							chartTarget.setStripedCloseFunction( new StripedCloseInitPriceRelative());
							chartTarget.updateCharts(chartTarget.getListShares(), false, false);
						}
					}),
					new TransfoInfo("Change to period start", new ActionDialogAction() {

						@Override
						public void action(Button targetButton) {
							chartTarget.setStripedCloseFunction( new StripedCloseAbsoluteRelative(chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate()));
							chartTarget.updateCharts(chartTarget.getListShares(), false, false);
						}
					}),
					new TransfoInfo("Change to previous day (log ROC)", new ActionDialogAction() {

						@Override
						public void action(Button targetButton) {

							final ActionDialogForm actionDialogForm = new ActionDialogForm(chartTarget.getShell(), "Ok", null, "Root at zero");
							ActionDialogAction actionDialogAction = new ActionDialogAction() {
								@Override
								public void action(Button targetButton) {
									actionDialogForm.name = Boolean.valueOf(((Button)actionDialogForm.control).getSelection()).toString();
									chartTarget.setStripedCloseFunction( new StripedCloseLogRoc(actionDialogForm.name.equals(Boolean.TRUE.toString())));
									chartTarget.updateCharts(chartTarget.getListShares(), false, false);
								}
							};
							Button zeroBut =  new Button(actionDialogForm.getParent(), SWT.CHECK | SWT.LEAD);
							zeroBut.setText("Root at zero");
							zeroBut.setFont(MainGui.DEFAULTFONT);
							zeroBut.setSelection(true);
							actionDialogForm.setControl(zeroBut);
							actionDialogForm.setAction(actionDialogAction);
							actionDialogForm.open();
							
						}
					}),
					new TransfoInfo("Change to Referee", new ActionDialogAction() {

						@Override
						public void action(Button targetButton) {

							String preferedRef = MainPMScmd.getPrefs().get("charts.referee", "Not Defined");
							final ActionDialogForm actionDialogForm = new ActionDialogForm(new Shell(), "Select a new referee ...", "Current referee : "+preferedRef, "Select a new referee ...");
							ActionDialogAction actionDialogAction = new ActionDialogAction() {
								@Override
								public void action(Button targetButton) {
									chartTarget.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
									actionDialogForm.getParent().dispose();
									try {
										Stock selectedReferee = selectReferee();
										if (selectedReferee != null) {
											actionDialogForm.name = selectedReferee.getName();
											relativeIndexSetting(actionDialogForm.name);
											chartTarget.updateCharts(chartTarget.getListShares(), false, false);
										}
									} finally {
										chartTarget.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
									}
								}
							};
							actionDialogForm.setAction(actionDialogAction);
							actionDialogForm.open();
							if (actionDialogForm.name  == null && !preferedRef.equals("Not Defined")) {
								relativeIndexSetting(preferedRef);
								chartTarget.updateCharts(chartTarget.getListShares(), false, false);
							}

						}
					})}));

			closeFunctionBut.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {		
					handleEvent(closeFunctionBut, transfos);
				}

				private void handleEvent(final Button closeFunctionBut, final Set<TransfoInfo> transfos) {
					
					Set<TransfoInfo> selectTransfo = new HashSet<TransfoInfo>();
					for (TransfoInfo transfoInfo : transfos) {
						if (chartTarget.getStripedCloseFunction().lineToolTip().toLowerCase().contains(transfoInfo.info().toLowerCase())) {
							selectTransfo.add(transfoInfo);
						}
					}
					PopupMenu<TransfoInfo> popupMenu = new PopupMenu<TransfoInfo>(chartTarget, closeFunctionBut, transfos, selectTransfo, false, SWT.RADIO, null);
					popupMenu.open(null);
					for (TransfoInfo selctTransUnic : selectTransfo) {
						selctTransUnic.action.action(null);
					}
					closeFunctionBut.setText("Comparison mode ...");
					closeFunctionBut.setToolTipText(cmpModeToolTipRoot +"The current comparison mode is '"+chartTarget.getStripedCloseFunction().lineToolTip()+"'");
					popusGroup.setSize(popusGroup.getBounds().width, popusGroup.getBounds().height);
					popusGroup.layout();

				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					handleEvent(closeFunctionBut, transfos);
				}
			});

		}

		{
			final Button hideStock = new Button(popusGroup, SWT.PUSH);
			hideStock.setFont(MainGui.DEFAULTFONT);
			hideStock.setText("Hide / Show stock ...");
			hideStock.setToolTipText("This will hide and show stocks on the chart for comparison purpose.");
			
			hideStock.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {		
					hideShowShares(hideStock);
				}

				private void hideShowShares(final Button hideStock) {
					
					Set<SlidingPortfolioShare> displayedShares = new HashSet<SlidingPortfolioShare>();

					if (chartTarget.getListShares().size() > 0) {
						for (SlidingPortfolioShare slidingPortfolioShare : chartTarget.getListShares()) {
							if (slidingPortfolioShare.getDisplayOnChart()) {
								displayedShares.add(slidingPortfolioShare);
							} 
						}

						PopupMenu<SlidingPortfolioShare> popupMenu =  new PopupMenu<SlidingPortfolioShare>(chartTarget, hideStock, new TreeSet<SlidingPortfolioShare>(chartTarget.getListShares()), displayedShares, true, SWT.CHECK, null);
						popupMenu.open(null);
						for (SlidingPortfolioShare slidingPortfolioShare : chartTarget.getListShares()) {
							if (displayedShares.contains(slidingPortfolioShare)) {
								slidingPortfolioShare.setDisplayOnChart(true);
							} else {
								slidingPortfolioShare.setDisplayOnChart(false);
							}
						}
						chartTarget.updateCharts(chartTarget.getListShares(), false, false);
					}

				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					hideShowShares(hideStock);
				}
			});

		}
		
		popusGroup.layout();
		
	}
	
	/**
	 * @param selectReferreText 
	 * @throws InvalidAlgorithmParameterException 
	 * 
	 */
	private void relativeIndexSetting(String selectReferreText) {
		
		if (selectReferreText != null && !"Your referee".equals(selectReferreText) && refereeQuotations != null && refereeQuotations.size() != 0) {
			try {
				chartTarget.setStripedCloseFunction(new StripedCloseIndexRelative(refereeQuotations, chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate()));
			} catch (InvalidAlgorithmParameterException e) {
				LOGGER.error("",e);
			}
		} else {
			String preferedRef = MainPMScmd.getPrefs().get("charts.referee", "Not Defined");
			
			Stock stock;
			if (!preferedRef.equals("Not Defined")) {
				try {
					stock = DataSource.getInstance().loadStockBySymbol(preferedRef);
					loadRefereeQuotations(stock);
				} catch (Exception e) {
					LOGGER.debug(e);
					UserDialog inst = new UserDialog(chartTarget.getShell(), SWT.NULL, "Referee unknown or no quotations", null);
					inst.open();
					chartTarget.setStripedCloseFunction(new StripedCloseInitPriceRelative());
				}
			}
		}
		
	}	
	
	/**
	 * Select referee.
	 * 
	 * @author Guillaume Thoreton
	 * @param listShares 
	 */
	private Stock selectReferee() {

		//Open selection window
		NewRefereeDialog pItemDialog = (NewRefereeDialog) NewRefereeDialog.showUI(chartTarget.getShell(), chartTarget);
		Set<Stock> listStock = pItemDialog.getSelectedStocks();
		Stock referree = null;
		if (listStock != null && listStock.size() > 0) {

			referree = listStock.iterator().next();

			try {
				chartTarget.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
		
				QuotationUpdate quotationUpdate = new QuotationUpdate();
				quotationUpdate.getQuotes(new StockList(listStock));
				loadRefereeQuotations(referree);
				MainPMScmd.getPrefs().put("charts.referee", referree.getSymbol());

			} catch (Exception e) {
				UserDialog inst = new UserDialog(chartTarget.getShell(), SWT.NULL, "Invalid referee : "+referree.getFriendlyName()+"\n"+e, null);
				inst.open();
			} finally {
				chartTarget.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
			}

		} else if (pItemDialog instanceof NewRefereeDialog) {
			UserDialog inst = new UserDialog(chartTarget.getShell(), SWT.NULL, "No referee selected please select a stock \n", null);
			inst.open();
		}

		return referree;

	}
	

	private void loadRefereeQuotations(Stock stock) throws InvalidAlgorithmParameterException {
		try {
			if (null == stock) throw new InvalidAlgorithmParameterException("Referee can't be null");
			refereeQuotations  = QuotationsFactories.getFactory().getQuotationsInstance(stock,ChartsComposite.DEFAULT_START_DATE, EventSignalConfig.getNewDate(),true,stock.getMarketValuation().getCurrency(),0,0);
			chartTarget.setStripedCloseFunction(new StripedCloseIndexRelative(refereeQuotations, chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate()));
		} catch (NoQuotationsException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void resetChart() {
		for (SlidingPortfolioShare sShare : chartTarget.getListShares()) {
			sShare.setDisplayOnChart(true);
		}
		chartTarget.setStripedCloseFunction(new StripedCloseAbsoluteRelative(chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate()));
		chartTarget.updateCharts(chartTarget.getListShares(), false, false);
	}

	@Override
	public Boolean getIsApplyColor() {
		return true;
	}

	@Override
	public void highLighPrevious() {
		chartTarget.getHightlitedEventModel().setViewStateParams();
		chartTarget.setHighligtedId(null);
		
	}

	@Override
	public void exportBarChartPng() {
		// NotImplemented
	}

	@Override
	public void refreshView(List<Exception> exceptions) {
		// Nothing
		
	}

}
