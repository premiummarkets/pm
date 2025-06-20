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

import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import com.finance.pms.ActionDialog;
import com.finance.pms.ActionDialogAction;
import com.finance.pms.CursorFactory;
import com.finance.pms.MainGui;
import com.finance.pms.MainPMScmd;
import com.finance.pms.PopupMenu;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.portfolio.gui.ActionDialogForm;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;

public class ChartPerfDisplay extends ChartDisplayStrategy {

	private static final String NOT_DEFINED = "Not Defined";

	private static MyLogger LOGGER = MyLogger.getLogger(ChartPerfDisplay.class);

	private Quotations refereeQuotations;
	private Boolean isShutDown;

	private Button comparisonModeBut;
	private String cmpModeToolTipRoot;

	public ChartPerfDisplay(ChartsComposite chartTarget) {
		super();
		this.chartTarget = chartTarget;
		
		StripedCloseFunction stripedCloseFunction = this.getStripedCloseFunction();
		this.setStripedCloseFunction((stripedCloseFunction != null)?stripedCloseFunction:new StripedCloseRelativeToInvested(true, this.chartTarget.getSlidingStartDate(), this.chartTarget.getSlidingEndDate()));
		
		init(chartTarget);

	}
	
	@Override
	public void init(ChartsComposite chartTarget) {
		isShutDown = false;
		
		populatePopups(chartTarget.getPopusGroup());
		
		this.chartTarget.getMainChartWraper()
			.initMainPlot(
					ChartMain.PERCENTAGE_FORMAT,
					"No data available. Check that the portfolio stocks and sliding date ranges. There may be no quotations available."
			);

		resetChart(true);
	}

	@Override
	public void highLight(Integer idx, Stock selectedShare, Boolean recalculationGranted, PopupType... popupTypes) {

		try {

			this.chartTarget.getShell().setEnabled(false);
			this.chartTarget.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));

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

			this.getStripedCloseFunction().updateStartDate(chartTarget.getSlidingStartDate());
			this.getStripedCloseFunction().updateEndDate(chartTarget.getSlidingEndDate());
			chartTarget.getMainChartWraper().updateLineDataSet(chartTarget.getCurrentTabShareList(), this.getStripedCloseFunction(), getIsApplyColor());

			chartTarget.getMainChartWraper().highLightSerie(chartTarget.getHighligtedId(), 3);

		} finally {

			this.chartTarget.getShell().setEnabled(true);
			this.chartTarget.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));

		}
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
		
		{
			Group chartSettingsGroup = new Group(popupButtonsGroup, SWT.NONE);
			RowLayout chartSettingsGroupGroupL = new RowLayout(SWT.VERTICAL);
			chartSettingsGroupGroupL.justify = true;
			chartSettingsGroupGroupL.fill=true;
			chartSettingsGroupGroupL.wrap=false;
			chartSettingsGroupGroupL.marginHeight=0;
			chartSettingsGroup.setLayout(chartSettingsGroupGroupL);
			{
				final Button splitBut =  new Button(chartSettingsGroup, SWT.CHECK | SWT.LEAD);
				splitBut.setFont(MainGui.DEFAULTFONT);
				splitBut.setText("Disable split fix");
				splitBut.setToolTipText("Disable split/merge fixes and show the raw values as stored in the database.");
				splitBut.setSelection(false);
				splitBut.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						disableSplit();
					}

					private void disableSplit() {
						chartTarget.getMainChartWraper().setDisableSplitFix(splitBut.getSelection());
						chartTarget.updateCharts(false);
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						disableSplit();
					}
				});
			}
			
			{
				final Button splitCalcOnlyBut =  new Button(chartSettingsGroup, SWT.CHECK | SWT.LEAD);
				splitCalcOnlyBut.setFont(MainGui.DEFAULTFONT);
				splitCalcOnlyBut.setText("Use calculated split fix only");
				splitCalcOnlyBut.setToolTipText("Ignore the potential split indicators potentially stored with the raw values and infer the potential splits/merges");
				splitCalcOnlyBut.setSelection(false);
				
				splitCalcOnlyBut.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						useCalculatedSplitFixOnly();
					}

					private void useCalculatedSplitFixOnly() {
						chartTarget.getMainChartWraper().setUseCalculatedSplitFixOnly(splitCalcOnlyBut.getSelection());
						chartTarget.updateCharts(false);
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						useCalculatedSplitFixOnly();
					}
				});
			}
		}

		{
			cmpModeToolTipRoot = "This is to set the comparison mode between several stocks.\nUse the 'Hide / Show stock' button to display several stock on the chart.\n";
			comparisonModeBut = new Button(popupButtonsGroup, SWT.PUSH);
			comparisonModeBut.setFont(MainGui.DEFAULTFONT);
			comparisonModeBut.setText("Comparison mode ...");

			final Set<TransfoInfo> transfosInitial = new HashSet<TransfoInfo>(Arrays.asList(new TransfoInfo[]{

					new TransfoInfo("Change to Invested per unit", new ActionDialogAction() {

						@Override
						public void action() {

							Boolean isIncludeMoneyOutSelected = true;
							if (ChartPerfDisplay.this.getStripedCloseFunction() instanceof StripedCloseRelativeToInvested) {
								isIncludeMoneyOutSelected = ((StripedCloseRelativeToInvested) ChartPerfDisplay.this.getStripedCloseFunction()).getIncludeMoneyOut();
							}

							final ActionDialogForm actionDialogForm = new ActionDialogForm(chartTarget.getShell(), "Ok", null, "Log ROC settings");
							Group priceToogleGrp = new Group(actionDialogForm.getParent(), SWT.NONE);
							priceToogleGrp.setBackground(MainGui.pOPUP_BG);
							priceToogleGrp.setLayout(new FillLayout(SWT.VERTICAL));
							final Button bPrice =  new Button(priceToogleGrp, SWT.RADIO);
							bPrice.setBackground(MainGui.pOPUP_BG);
							bPrice.setText("Relative to average buy price");
							bPrice.setToolTipText("The result will be displayed relative to the basis, reflecting the unrealized gain. Sum(in)/quantity");
							bPrice.setFont(MainGui.DEFAULTFONT);
							bPrice.setSelection(!isIncludeMoneyOutSelected);
							final Button uPrice =  new Button(priceToogleGrp, SWT.RADIO);
							uPrice.setBackground(MainGui.pOPUP_BG);
							uPrice.setText("Relative to cost per unit");
							uPrice.setToolTipText("The result will be displayed relative to the cost per unit, taking in account the moneys out. (Sum(in) - Sum(out))/quantity");
							uPrice.setFont(MainGui.DEFAULTFONT);
							uPrice.setSelection(isIncludeMoneyOutSelected);
							ActionDialogAction actionDialogAction = new ActionDialogAction() {
								@Override
								public void action() {
									actionDialogForm.values[0] = Boolean.valueOf(uPrice.getSelection());
									ChartPerfDisplay.this.setStripedCloseFunction(new StripedCloseRelativeToInvested((Boolean)actionDialogForm.values[0], chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate()));
									chartTarget.updateCharts(false);
								}
							};

							actionDialogForm.setControl(bPrice, uPrice);
							actionDialogForm.setAction(actionDialogAction);
							actionDialogForm.open();

						}
					}),
					new TransfoInfo("Change to Period start", new ActionDialogAction() {

						@Override
						public void action() {
							ChartPerfDisplay.this.setStripedCloseFunction(new StripedCloseRelativeToStart(chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate()));
							chartTarget.updateCharts(false);
						}
					}),
					new TransfoInfo("Change to Previous day (log ROC)", new ActionDialogAction() {
						
						@Override
						public void action() {
							
							Integer previousSmthPeriod = Integer.valueOf(MainPMScmd.getMyPrefs().get("charts.logROCPeriod", "21"));

							final ActionDialogForm actionDialogForm = new ActionDialogForm(chartTarget.getShell(), "Ok", null, "Log ROC settings");
							
							final Button zeroBut =  new Button(actionDialogForm.getParent(), SWT.CHECK | SWT.LEAD);
							zeroBut.setBackground(MainGui.pOPUP_BG);
							zeroBut.setFont(MainGui.DEFAULTFONT);
							zeroBut.setText("Adjust start all from 0");
							zeroBut.setToolTipText("If selected, the resulting ROCS will all be starting from 0 at the start of the period and than be drawn relative to this point.\nIf not the resulting ROCs will naturally oscillate around 0.");
							zeroBut.setSelection(false);
							
							final Text smthPeriodTxt = new Text(actionDialogForm.getParent(), SWT.NONE | SWT.CENTER | SWT.BORDER);
							smthPeriodTxt.setFont(MainGui.CONTENTFONT);
							smthPeriodTxt.setText(previousSmthPeriod.toString());
							smthPeriodTxt.setToolTipText("In order to reflect some trend, the ROC must be calculated after smoothing the quotations.\nEnter here the smoothing period number.");
							
							final Button normTick =  new Button(actionDialogForm.getParent(), SWT.CHECK | SWT.LEAD);
							normTick.setBackground(MainGui.pOPUP_BG);
							normTick.setFont(MainGui.DEFAULTFONT);
							normTick.setText("Normalise outputs");
							normTick.setToolTipText("All outputs will be normalise between -1 and 1 before being added to the chart.");
							normTick.setSelection(true);
							
							ActionDialogAction actionDialogAction = new ActionDialogAction() {
								@Override
								public void action() {
									actionDialogForm.values[0] = Boolean.valueOf(zeroBut.getSelection());

									try {
										actionDialogForm.values[1] = Integer.valueOf(smthPeriodTxt.getText());
									} catch (NumberFormatException e) {
										actionDialogForm.values[1] = previousSmthPeriod.toString();
									}

									actionDialogForm.values[2] = normTick.getSelection();
									
									try {
										MainPMScmd.getMyPrefs().put("charts.logROCPeriod", actionDialogForm.values[1].toString());
										MainPMScmd.getMyPrefs().flushy();
									} catch (Exception e) {
										LOGGER.warn(e,e);
									}

									ChartPerfDisplay.this.setStripedCloseFunction(
											new StripedCloseLogRoc(
													chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate(), 
													(Boolean) actionDialogForm.values[0], (Integer) actionDialogForm.values[1], (Boolean) actionDialogForm.values[2]));
									chartTarget.updateCharts(false);
								}
							};

							actionDialogForm.setControl(zeroBut, smthPeriodTxt, normTick);
							actionDialogForm.setAction(actionDialogAction);
							actionDialogForm.open();

						}
					}),
					new TransfoInfo("Change to Referee", new ActionDialogAction() {

						@Override
						public void action() {

							String refereeRef = MainPMScmd.getMyPrefs().get("charts.referee", NOT_DEFINED+"||-||"+NOT_DEFINED);

							String symbolSplit = NOT_DEFINED;
							String isinSplit = NOT_DEFINED;
							try {
								symbolSplit = refereeRef.split("\\|\\|-\\|\\|")[0];
								isinSplit = refereeRef.split("\\|\\|-\\|\\|")[1];
							} catch (Exception e) {
								LOGGER.warn("Invalid previous referee: " + refereeRef);
							}

							final String previousSymbol = symbolSplit;
							final String previousIsin = isinSplit;
							final Stock previousReferee = relativeToRefereeSetting(previousSymbol, previousIsin);
							if (previousReferee != null) chartTarget.updateCharts(false);

							ActionDialogAction actionDialogAction = new ActionDialogAction() {
								@Override
								public void action() {
									chartTarget.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
									try {
										selectNewReferee(previousReferee);
									} finally {
										chartTarget.getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
									}
								}
							};

							ActionDialog actionDialogForm = new ActionDialog(
									chartTarget.getShell(), 
									"Select a new referee ...", null, "Current referee : "+((previousReferee!=null)?previousReferee.getFriendlyName():NOT_DEFINED), "Select a new referee ...", actionDialogAction);

							actionDialogForm.open();

						}
					}),
					new TransfoInfo("Normalized", new ActionDialogAction() {

						@Override
						public void action() {
							ChartPerfDisplay.this.setStripedCloseFunction(new StripedCloseNormalized(chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate()));
							chartTarget.updateCharts(false);
						}
					}),
					new TransfoInfo("Real Price", new ActionDialogAction() {

						@Override
						public void action() {
							ChartPerfDisplay.this.setStripedCloseFunction(new StripedCloseRealPrice(chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate()));
							chartTarget.updateCharts(false);
						}
					})
			}));

			comparisonModeBut.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					selectComparisonMode(comparisonModeBut, transfosInitial);
				}

				private void selectComparisonMode(final Button closeFunctionBut, final Set<TransfoInfo> transfos) {

					final Set<TransfoInfo> selectedTransfo = new HashSet<TransfoInfo>();
					for (TransfoInfo transfoInfo : transfos) {
						if (ChartPerfDisplay.this.getStripedCloseFunction().lineToolTip().toLowerCase().contains(transfoInfo.info().toLowerCase())) {
							selectedTransfo.add(transfoInfo);
						}
					}

					ActionDialogAction actionDialogAction = new ActionDialogAction() {

						@Override
						public void action() {
							for (TransfoInfo selctTransUnic : selectedTransfo) {
								selctTransUnic.action.action();
							}

						}
					};
					PopupMenu<TransfoInfo> popupMenu = new PopupMenu<TransfoInfo>(chartTarget, closeFunctionBut, transfos, selectedTransfo, true, false, SWT.RADIO, actionDialogAction);
					popupMenu.open();

				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					selectComparisonMode(comparisonModeBut, transfosInitial);
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

					//SlidingPortfolioShare selectedPS = chartTarget.getCurrentLineSelection();
					final TreeSet<SlidingPortfolioShare> availShares = new TreeSet<SlidingPortfolioShare>(currentTabShareList);
					//if (selectedPS != null) availShares.remove(selectedPS);

					if (availShares.size() > 0) {

						for (SlidingPortfolioShare slidingPortfolioShare : availShares) {
							if (slidingPortfolioShare.getDisplayOnChart()) {
								displayedShares.add(slidingPortfolioShare);
							} 
						}

						ActionDialogAction action = new ActionDialogAction() {

							@Override
							public void action() {
								if (!isShutDown) {
									for (SlidingPortfolioShare slidingPortfolioShare : availShares) {
										if (displayedShares.contains(slidingPortfolioShare)) {
											slidingPortfolioShare.setDisplayOnChart(true);
										} else {
											slidingPortfolioShare.setDisplayOnChart(false);
										}
									}
									chartTarget.updateCharts(false);
								}
							}
						};
						PopupMenu<SlidingPortfolioShare> popupMenu =  new PopupMenu<SlidingPortfolioShare>(chartTarget, hideStock, availShares, displayedShares, true, true, SWT.CHECK, action);
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
		chartTarget.myPack();

	}

	Stock relativeToRefereeSetting(String previousSymbol, String previousIsin) {

		if (previousSymbol != null && !NOT_DEFINED.equals(previousSymbol)) {//already set

			if (refereeQuotations != null && refereeQuotations.size() != 0) {//Initialised

				try {
					this.setStripedCloseFunction(new StripedCloseRelativeToReferee(refereeQuotations, chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate()));
				} catch (InvalidAlgorithmParameterException e) {
					LOGGER.error("",e);
				}

			} else {//Set but not initialised

				try {
					Stock stock = DataSource.getInstance().getShareDAO().loadStockBy(previousSymbol, previousIsin);
					loadRefereeQuotations(stock);
				} catch (Exception e) {
					LOGGER.warn("Unable to initialise referee "+previousSymbol+ " " + previousIsin);
					this.setStripedCloseFunction(new StripedCloseRelativeToStart(chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate()));
				}
			}

		}

		return  (refereeQuotations != null)?refereeQuotations.getStock():null;

	}

	void loadRefereeQuotations(Stock stock) throws InvalidAlgorithmParameterException {
		try {
			if (null == stock) throw new InvalidAlgorithmParameterException("Referee can't be null");
			refereeQuotations  = QuotationsFactories.getFactory().getSplitFreeQuotationsInstance(stock, ChartsComposite.DEFAULT_START_DATE, DateFactory.getNowEndDate(), true, stock.getMarketValuation().getCurrency(), 1, ValidityFilter.CLOSE);
			this.setStripedCloseFunction(new StripedCloseRelativeToReferee(refereeQuotations, chartTarget.getSlidingStartDate(), chartTarget.getSlidingEndDate()));
		} catch (NoQuotationsException e) {
			throw new RuntimeException(e);
		}
	}


	private void selectNewReferee(Stock previousReferee) {
		//Open selection window
		NewRefereeDialog.showUI(chartTarget.getShell(), chartTarget, this);
	}

	@Override
	public void resetChart(Boolean resetDisplayedList, PopupType ...popupTypes) {

		if (resetDisplayedList) {
			for (SlidingPortfolioShare sShare : chartTarget.getCurrentTabShareList()) {
				sShare.setDisplayOnChart(true);
			}
		}

		chartTarget.getMainChartWraper().resetBarChart();
		chartTarget.getMainChartWraper().resetIndicChart();
		chartTarget.getMainChartWraper().updateLineDataSet(chartTarget.getCurrentTabShareList(), this.getStripedCloseFunction(), getIsApplyColor());

	}

	@Override
	public Boolean getIsApplyColor() {
		return true;
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

		chartTarget.getMainChartWraper().resetBarChart();
		chartTarget.getMainChartWraper().resetIndicChart();

		isShutDown = true;

	}

	@Override
	public void updateButtonsToolTips() {

		if (!isShutDown) {
			comparisonModeBut.setText("Comparison mode ...");
			comparisonModeBut.setToolTipText(cmpModeToolTipRoot + "The current comparison mode is '" + this.getStripedCloseFunction().lineToolTip() + "'");
			chartTarget.chartBoutonsGroup.setSize(chartTarget.chartBoutonsGroup.getBounds().width, chartTarget.chartBoutonsGroup.getBounds().height);
			chartTarget.chartBoutonsGroup.layout();
		}

	}

	@Override
	public void cleanPreviousStockSelection() {
		//Nothing
	}


}
