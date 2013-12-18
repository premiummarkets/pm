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
import org.eclipse.swt.widgets.Control;

import com.finance.pms.ActionDialog;
import com.finance.pms.ActionDialogAction;
import com.finance.pms.CursorFactory;
import com.finance.pms.MainGui;
import com.finance.pms.MainPMScmd;
import com.finance.pms.PopupMenu;
import com.finance.pms.UserDialog;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.StripedCloseLogRoc;
import com.finance.pms.datasources.db.StripedCloseRelativeToReferee;
import com.finance.pms.datasources.db.StripedCloseRelativeToStart;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.portfolio.gui.ActionDialogForm;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;

public class ChartPerfDisplay extends ChartDisplayStrategy {
	
	private static final String NOT_DEFINED = "Not Defined";

	private static MyLogger LOGGER = MyLogger.getLogger(ChartPerfDisplay.class);
	
	private Quotations refereeQuotations;
	private boolean isShutDown = false;

	private Button closeFunctionBut;
	private String cmpModeToolTipRoot;
	

	public ChartPerfDisplay(ChartsComposite chartTarget) {
		super();
		this.chartTarget = chartTarget;
		populatePopups(chartTarget.getPopusGroup());
		this.chartTarget.getMainChartWraper().initMainPlot(ChartMain.PERCENTAGE_FORMAT, "No data available. Check that the portfolio stocks and sliding date ranges. There may be no quotations available.");
	}

	@Override
	public void highLight(Integer idx, Stock selectedShare, Boolean recalculationGranted) {

		chartTarget.getMainChartWraper().setMainYAxisLabel("");
		if ( idx == null || selectedShare == null ) {
			return;
		}
		
		chartTarget.setHighligtedId(idx);
		chartTarget.getHightlitedEventModel().setViewParamRoot(selectedShare);
		
		if (chartTarget.isDisposed() || !chartTarget.isVisible()) {
			return;
		}
		
		Boolean isShowing = checkIfShowing(selectedShare);
		if (!isShowing) {
			for (SlidingPortfolioShare slidingPortfolioShare : chartTarget.getCurrentTabShareList()) {
				if (slidingPortfolioShare.getStock().equals(selectedShare)) {
					slidingPortfolioShare.setDisplayOnChart(true);
					break;
				}
			}
		}
		
		chartTarget.getStripedCloseFunction().updateStartDate(chartTarget.getSlidingStartDate());
		chartTarget.getStripedCloseFunction().updateEndDate(chartTarget.getSlidingEndDate());
		chartTarget.getMainChartWraper().updateLineDataSet(chartTarget.getCurrentTabShareList(), chartTarget.getStripedCloseFunction(), getIsApplyColor(), chartTarget.getPlotChart());
		
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
	public void populatePopups(final Composite popupButtonsGroup) {
		
		cleanPopupButtonsGroup(popupButtonsGroup);
		
		cmpModeToolTipRoot = "This is to set the comparison mode between several stocks.\nUse the 'Hide / Show stock' button to display several stock on the chart.\n";
		
		{
			closeFunctionBut = new Button(popupButtonsGroup, SWT.PUSH);
			closeFunctionBut.setFont(MainGui.DEFAULTFONT);
			closeFunctionBut.setText("Comparison mode ...");
			
			final Set<TransfoInfo> transfos = new HashSet<TransfoInfo>(Arrays.asList(new TransfoInfo[]{
					
					new TransfoInfo("Change to Buy price", new ActionDialogAction() {

						@Override
						public void action(Control targetControl) {
							chartTarget.setStripedCloseFunction( new StripedCloseRelativeToBuyPrice());
							chartTarget.updateCharts(chartTarget.getCurrentTabShareList(), true, true, false);
						}
					}),
					new TransfoInfo("Change to Period start", new ActionDialogAction() {

						@Override
						public void action(Control targetControl) {
							chartTarget.setStripedCloseFunction( new StripedCloseRelativeToStart(chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate()));
							chartTarget.updateCharts(chartTarget.getCurrentTabShareList(),  true, true, false);
						}
					}),
					new TransfoInfo("Change to Previous day (log ROC)", new ActionDialogAction() {

						@Override
						public void action(Control targetControl) {

							final ActionDialogForm actionDialogForm = new ActionDialogForm(chartTarget.getShell(), "Ok", null, "Root at zero");
							ActionDialogAction actionDialogAction = new ActionDialogAction() {
								@Override
								public void action(Control targetControl) {
									actionDialogForm.values[0] = Boolean.valueOf(((Button)actionDialogForm.controls[0]).getSelection()).toString();
									chartTarget.setStripedCloseFunction( new StripedCloseLogRoc(actionDialogForm.values[0].equals(Boolean.TRUE.toString())));
									chartTarget.updateCharts(chartTarget.getCurrentTabShareList(), true, true, false);
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
						public void action(Control targetControl) {

							String refereeRef = MainPMScmd.getPrefs().get("charts.referee", NOT_DEFINED+"||-||"+NOT_DEFINED);
							final String previousSymbol = refereeRef.split("\\|\\|-\\|\\|")[0];
							final String previousIsin = refereeRef.split("\\|\\|-\\|\\|")[1];
							final Stock previousReferee = relativeToRefereeSetting(previousSymbol, previousIsin);
							
							if (previousReferee != null) chartTarget.updateCharts(chartTarget.getCurrentTabShareList(), true, true, false);
							
							ActionDialogAction actionDialogAction = new ActionDialogAction() {
								@Override
								public void action(Control targetControl) {
									chartTarget.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
									try {
										Stock selectedReferee = selectNewReferee(previousReferee);
										if (selectedReferee != null) {
											relativeToRefereeSetting(selectedReferee.getSymbol(), selectedReferee.getIsin());
											chartTarget.updateCharts(chartTarget.getCurrentTabShareList(), true, true, false);
										}
									} finally {
										chartTarget.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
									}
								}
							};
							
							ActionDialog actionDialogForm = new ActionDialog(
									chartTarget.getShell(), SWT.NONE, 
									"Select a new referee ...", null, "Current referee : "+((previousReferee!=null)?previousReferee.getFriendlyName():NOT_DEFINED), "Select a new referee ...", actionDialogAction);

							actionDialogForm.open();

						}
					})}));

			closeFunctionBut.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {		
					selectComparisonMode(closeFunctionBut, transfos);
				}

				private void selectComparisonMode(final Button closeFunctionBut, final Set<TransfoInfo> transfos) {
					
					final Set<TransfoInfo> selectTransfo = new HashSet<TransfoInfo>();
					for (TransfoInfo transfoInfo : transfos) {
						if (chartTarget.getStripedCloseFunction().lineToolTip().toLowerCase().contains(transfoInfo.info().toLowerCase())) {
							selectTransfo.add(transfoInfo);
						}
					}
					
					ActionDialogAction actionDialogAction = new ActionDialogAction() {
						
						@Override
						public void action(Control targetControl) {
							for (TransfoInfo selctTransUnic : selectTransfo) {
								selctTransUnic.action.action(null);
							}
							
						}
					};
					PopupMenu<TransfoInfo> popupMenu = new PopupMenu<TransfoInfo>(chartTarget, closeFunctionBut, transfos, selectTransfo, true, false, SWT.RADIO, actionDialogAction);
					popupMenu.open();
						
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					selectComparisonMode(closeFunctionBut, transfos);
				}
			});

		}

		{
			final Button hideStock = new Button(popupButtonsGroup, SWT.PUSH);
			hideStock.setFont(MainGui.DEFAULTFONT);
			hideStock.setText("Hide / Show stock ...");
			hideStock.setToolTipText("This will hide and show stocks on the chart for comparison purpose.\nNote that the selected line in the portfolio can't be hidden");
			
			hideStock.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {		
					hideShowShares(hideStock);
				}

				private void hideShowShares(final Button hideStock) {
					
					final Set<SlidingPortfolioShare> displayedShares = new HashSet<SlidingPortfolioShare>();
					final List<SlidingPortfolioShare> currentTabShareList = chartTarget.getCurrentTabShareList();
					
					SlidingPortfolioShare selectedPS = chartTarget.getCurrentLineSelection();
					final TreeSet<SlidingPortfolioShare> availShares = new TreeSet<SlidingPortfolioShare>(currentTabShareList);
					if (selectedPS != null) availShares.remove(selectedPS);
					
					if (availShares.size() > 0) {
		
						for (SlidingPortfolioShare slidingPortfolioShare : availShares) {
							if (slidingPortfolioShare.getDisplayOnChart()) {
								displayedShares.add(slidingPortfolioShare);
							} 
						}
						
						ActionDialogAction action = new ActionDialogAction() {
							
							@Override
							public void action(Control targetControl) {
								if (!isShutDown) {
									for (SlidingPortfolioShare slidingPortfolioShare : availShares) {
										if (displayedShares.contains(slidingPortfolioShare)) {
											slidingPortfolioShare.setDisplayOnChart(true);
										} else {
											slidingPortfolioShare.setDisplayOnChart(false);
										}
									}
									chartTarget.updateCharts(currentTabShareList, true, true, false);
								}
							}
						};
						PopupMenu<SlidingPortfolioShare> popupMenu =  new PopupMenu<SlidingPortfolioShare>(chartTarget, hideStock, availShares , displayedShares, true, true, SWT.CHECK, action);
						popupMenu.open();
						
					}

				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					hideShowShares(hideStock);
				}
			});

		}
		
		popupButtonsGroup.layout();
		
	}
	
	/**
	 * @param previousSymbol 
	 * @param previousIsin 
	 * @throws InvalidAlgorithmParameterException 
	 * 
	 */
	private Stock relativeToRefereeSetting(String previousSymbol, String previousIsin) {
			
		if (previousSymbol != null && !NOT_DEFINED.equals(previousSymbol)) {//Already set
			
			if (refereeQuotations != null && refereeQuotations.size() != 0) {//Initialised
				
				try {
					chartTarget.setStripedCloseFunction(new StripedCloseRelativeToReferee(refereeQuotations, chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate()));
				} catch (InvalidAlgorithmParameterException e) {
					LOGGER.error("",e);
				}
				
			} else {//Not initialised
				try {
					//Stock stock = DataSource.getInstance().loadStockBySymbol(previousSymbol);
					Stock stock = DataSource.getInstance().getShareDAO().loadStockBy(previousSymbol, previousIsin);
					loadRefereeQuotations(stock);
				} catch (Exception e) {
					LOGGER.debug(e);
					UserDialog inst = new UserDialog(chartTarget.getShell(), "Referee unknown or no quotations", null);
					inst.open();
					chartTarget.setStripedCloseFunction(new StripedCloseRelativeToBuyPrice());
				}
			}
			
		} 
		
		return  (refereeQuotations != null)?refereeQuotations.getStock():null;
		
	}	
	
	/**
	 * Select referee.
	 * 
	 * @author Guillaume Thoreton
	 * @param listShares 
	 */
	private Stock selectNewReferee(Stock previousReferee) {

		//Stock referree = DataSource.getInstance().getShareDAO().loadStockBy(previousSymbol, previousIsin);
		Stock newReferree = previousReferee;
		
		//Open selection window
		NewRefereeDialog pItemDialog = (NewRefereeDialog) NewRefereeDialog.showUI(chartTarget.getShell(), chartTarget);
		Set<Stock> listStock = pItemDialog.getSelectedStocks();
		
		if (listStock != null && listStock.size() > 0) {

			try {
				newReferree = listStock.iterator().next();

				chartTarget.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));

				QuotationUpdate quotationUpdate = new QuotationUpdate();
				quotationUpdate.getQuotes(new StockList(listStock));
				loadRefereeQuotations(newReferree);
				try {
					MainPMScmd.getPrefs().put("charts.referee", newReferree.getSymbol()+"||-||"+newReferree.getIsin());
					MainPMScmd.getPrefs().flush();
				} catch (Exception e) {
					LOGGER.warn(e,e);
				}

			} catch (Exception e) {

				UserDialog inst = new UserDialog(chartTarget.getShell(), "Sorry. Invalid referee : "+newReferree.getFriendlyName()+"\n"+e, null);
				inst.open();
				newReferree = previousReferee;

			} finally {
				chartTarget.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
			}

		} else {
			UserDialog inst = new UserDialog(chartTarget.getShell(), "No referee selected please select a stock \n", null);
			inst.open();
		}

		return newReferree;

	}
	

	private void loadRefereeQuotations(Stock stock) throws InvalidAlgorithmParameterException {
		try {
			if (null == stock) throw new InvalidAlgorithmParameterException("Referee can't be null");
			refereeQuotations  = QuotationsFactories.getFactory().getQuotationsInstance(stock,ChartsComposite.DEFAULT_START_DATE, EventSignalConfig.getNewDate(),true,stock.getMarketValuation().getCurrency(),0,0);
			chartTarget.setStripedCloseFunction(new StripedCloseRelativeToReferee(refereeQuotations, chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate()));
		} catch (NoQuotationsException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void resetChart() {
		
		for (SlidingPortfolioShare sShare : chartTarget.getCurrentTabShareList()) {
			sShare.setDisplayOnChart(true);
		}
		
		//TODO keep object an state
		chartTarget.setStripedCloseFunction(new StripedCloseRelativeToStart(chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate()));
		
		lightResetChart();
		
	}
	

	@Override
	public void lightResetChart() {
		
		chartTarget.getMainChartWraper().resetBarChart();
		chartTarget.getMainChartWraper().resetIndicChart();
		
		chartTarget.getMainChartWraper().updateLineDataSet(chartTarget.getCurrentTabShareList(), chartTarget.getStripedCloseFunction(), getIsApplyColor(), chartTarget.getPlotChart());
		
	}

	@Override
	public Boolean getIsApplyColor() {
		return true;
	}

	@Override
	public int retreivePreviousSelection() {
		
		if (chartTarget.getHightlitedEventModel().getViewParamRoot() != null) {
			
			Stock stock = chartTarget.getHightlitedEventModel().getViewParamRoot();
			int cpt = 0;
			for (SlidingPortfolioShare slidingPortfolioShare : chartTarget.getCurrentTabShareList()) {
				if (stock.equals(slidingPortfolioShare.getStock())) return cpt;
				cpt++;
			}
		} 
		
		return -1;
		
	}

	@Override
	public void exportBarChartPng() {
		// NotImplemented
	}

	@Override
	public void refreshView(List<Exception> exceptions) {
		// Nothing
		
	}

	@Override
	public void shutDownDisplay() {

		for (SlidingPortfolioShare slidingPortfolioShare : chartTarget.getCurrentTabShareList()) {
			slidingPortfolioShare.setDisplayOnChart(false);
		}
		
		chartTarget.getMainChartWraper().resetBarChart();
		chartTarget.getMainChartWraper().resetIndicChart();
		
		isShutDown = true;
		
	}

	@Override
	public void updateButtonsToolTips() {
		
		if (!isShutDown) {
			closeFunctionBut.setText("Comparison mode ...");
			closeFunctionBut.setToolTipText(cmpModeToolTipRoot +"The current comparison mode is '"+chartTarget.getStripedCloseFunction().lineToolTip()+"'");
			chartTarget.chartBoutonsGroup.setSize(chartTarget.chartBoutonsGroup.getBounds().width, chartTarget.chartBoutonsGroup.getBounds().height);
			chartTarget.chartBoutonsGroup.layout();
		}
		
	}


}
