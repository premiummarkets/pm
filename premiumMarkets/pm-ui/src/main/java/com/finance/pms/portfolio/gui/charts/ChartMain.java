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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.ToolTipManager;
import javax.swing.UIManager;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.axis.Timeline;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.Layer;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventType;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.QuotationUnit.ORIGIN;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.SplitOption;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.chartUtils.BarChart;
import com.finance.pms.events.scoring.chartUtils.BarSettings;
import com.finance.pms.events.scoring.chartUtils.ChartIndicLineSeriesDataSetBuilder;
import com.finance.pms.events.scoring.chartUtils.DataSetBarDescr;
import com.finance.pms.events.scoring.chartUtils.MyTimeSeriesCollection;
import com.finance.pms.events.scoring.dto.PeriodRatingDTO;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;
import com.tictactec.ta.lib.MInteger;

/**
 * The Class Charts.
 * 
 * @author Guillaume Thoreton
 */
public class ChartMain extends Chart {

	private static MyLogger LOGGER = MyLogger.getLogger(ChartMain.class);

	public static final DateFormat DATE_FORMAT = DateFormat.getTimeInstance();
	public static final DecimalFormat PERCENTAGE_FORMAT = new DecimalFormat("#0.00%");
	public static final NumberFormat NUMBER_FORMAT = new DecimalFormat("#0.0000000000");
	private static final Integer CHARTS_TOTAL_WEIGHT = 100;

	private ChartPanel chartPanel;
	private BarChartDisplayStrategy barChartDisplayStrategy;

	private Boolean disableSplitFix = false;
	private Boolean useCalculatedSplitFixOnly = false;
	
	private Boolean useOneRange = false;
	private Boolean useNoGroupRanges = false;

	private JFreeChartTimePeriod jFreeTimePeriod;
	private JFreeChart jFreeChart;

	private DateAxis xAxis;
	private NumberAxis mainYAxis;
	private XYPlot mainPlot;

	private XYPlot indicPlot;
	private Integer indicPlotWeight = CHARTS_TOTAL_WEIGHT/2;

	private Map<ValueMarker, XYTextAnnotation> vLineAnnotations;
	private Map<ValueMarker, XYTextAnnotation> vTransAnnotations;
	private ValueMarker axisMarker;

	private Frame chartFrame;



	
	public ChartMain(Date startDate, JFreeChartTimePeriod jFreeTimePeriod) {
		super();

		this.jFreeTimePeriod = jFreeTimePeriod;
		UIManager.put("ToolTip.background", new java.awt.Color(239, 203, 152, 255));
		UIManager.put("ToolTip.foreground", java.awt.Color.BLACK);

		//ToolTipManager.sharedInstance().setInitialDelay(100);
		ToolTipManager.sharedInstance().setInitialDelay(20);
		//ToolTipManager.sharedInstance().setReshowDelay(0);
		ToolTipManager.sharedInstance().setDismissDelay(120000);

		XYBarRenderer.setDefaultShadowsVisible(false);
		XYBarRenderer.setDefaultBarPainter(new StandardXYBarPainter());

		barChartDisplayStrategy = new ChartBarSquare(this);

		vLineAnnotations = new HashMap<ValueMarker, XYTextAnnotation>();
		vTransAnnotations = new HashMap<ValueMarker, XYTextAnnotation>();

	}

	private XYDataset buildLineDataSet(final StripedCloseFunction stripedCloseFunction, final List<SlidingPortfolioShare> portfolioShares, final Boolean applyColors) {

		final MyTimeSeriesCollection combinedDataset = new MyTimeSeriesCollection();

		//Y axis number format
		this.mainYAxis.setNumberFormatOverride(stripedCloseFunction.getNumberFormat());

		//Check if empty
		Boolean isAnyShowing = false;
		for (SlidingPortfolioShare slidingPortfolioShare : portfolioShares) {
			isAnyShowing = isAnyShowing || slidingPortfolioShare.getDisplayOnChart();
		}
		if (!isAnyShowing) return combinedDataset;

		//Build lines
		for (int k = 0; k < portfolioShares.size(); k++) {

			SlidingPortfolioShare kthPs = portfolioShares.get(k);
			TimeSeries lineSerie;
			XYItemRenderer renderer = mainPlot.getRenderer();

			if (kthPs.getDisplayOnChart()) {

				try {
					Date startDate = stripedCloseFunction.getArbitraryStartDateForCalculation(kthPs.getStock());
					Date endDate = stripedCloseFunction.getArbitraryEndDate();
					final Quotations quotations = getQuotationUnitsAllClose(kthPs.getStock(), kthPs.getTransactionCurrency(), startDate, endDate);
					//FIXME reflect the split selection in the context menu...
					//FIXME move the responsibility of the the quotations init inside the StripedClose Function and use its return here??
					lineSerie = buildLineSeries(stripedCloseFunction, quotations, kthPs); 

					Paint paint = java.awt.Color.BLACK;
					if (applyColors) {
						org.eclipse.swt.graphics.Color color = kthPs.getColor();
						paint = new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue());
					}

					renderer.setSeriesPaint(k, paint);
					renderer.setSeriesStroke(k, new BasicStroke(1));

					final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy");
					XYToolTipGenerator xyToolTpGen = new XYToolTipGenerator() {

						public String generateToolTip(XYDataset dataset, int series, int item) {

							String y = "NaN";
							String x = "NaN";
							QuotationUnit closeForDate;
							try {

								Date date = new Date((long) dataset.getXValue(series, item));
								x = simpleDateFormat.format(date);
								SlidingPortfolioShare slPShare = portfolioShares.get(series);

								try {
									closeForDate = quotations.get(quotations.getClosestIndexBeforeOrAtDateOrIndexZero(0, date));
								} catch (Exception e) {
									LOGGER.warn(e);
									closeForDate = new QuotationUnit(
											slPShare.getStock(), slPShare.getTransactionCurrency(), date,
											BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0l, ORIGIN.USER, BigDecimal.ONE, null);
								}

								String variationAddInfo = "";
								if (!stripedCloseFunction.lineToolTip().isEmpty()) {
									y = stripedCloseFunction.formatYValue(dataset.getYValue(series, item));
									variationAddInfo = "<br>Value: " + y + " (" + stripedCloseFunction.lineToolTip() + ")";
								}

								String origin = (closeForDate.getOrigin().equals(ORIGIN.USER)) ?
										ORIGIN.USER.name().toLowerCase()
										: slPShare.getStock().getSymbolMarketQuotationProvider().getMarketQuotationProvider().getCmdParam();
										String trCurrency = slPShare.getTransactionCurrency().name();
										String stockCurrency = slPShare.getStock().getMarketValuation().getCurrency().name();

										String string = 
												"<html>" +
														"<font size='2'>" + "<b>" + slPShare.getFriendlyName() + "</b> On the " + x + "<br>" + "</font>" +
														"<table CELLSPACING=0 CELLPADDING=1>" +
														"<tr><td><font size='2'>Open</font></td><td><font size='2'>"+
														NUMBER_FORMAT.format(closeForDate.getOpenSplit()) +
														"</font></td></tr>" +
														"<tr><td><font size='2'>High</font></td><td><font size='2'>" +
														NUMBER_FORMAT.format(closeForDate.getHighSplit())
														+ "</font></td></tr>" +
														"<tr><td><font size='2'>Low</font></td><td><font size='2'>" +
														NUMBER_FORMAT.format(closeForDate.getLowSplit()) +
														"</font></td></tr>" +
														"<tr><td><font size='2'>Close</font></td><td><font size='2'>" +
														NUMBER_FORMAT.format(closeForDate.getCloseSplit())
														+ "</font></td></tr>" +
														"<tr><td><font size='2'>Volume&nbsp;</font></td><td><font size='2'>" + closeForDate.getVolumeSplit() + "</td></tr>" +
														"</table>" +
														"<font size='2'>" +
														"(Source: " + origin +", Split: " + NUMBER_FORMAT.format(closeForDate.getSplit()) + ", Currency: " + stockCurrency + " here in " + trCurrency + ")" +
														variationAddInfo +
														"</font>" +
														"</html>";
										return string;

							} catch (Exception e) {
								LOGGER.error(e, e);
							}
							return "NaN";

						}
					};

					//renderer.setSeriesToolTipGenerator(k, xyToolTpGen);
					combinedDataset.addToolTipGenerator(k, xyToolTpGen);
					renderer.setSeriesShape(k, new Rectangle(new Dimension(100, 100)));

				} catch (NoQuotationsException | NotEnoughDataException e) {
					LOGGER.warn(kthPs + " has no or not enough quotations available and won't be displayed");
					lineSerie = new TimeSeries(kthPs.getName());
				} catch (Exception e) {
					LOGGER.warn(kthPs + " error building line series ", e);
					lineSerie = new TimeSeries(kthPs.getName());
				}
				
			} else {
				lineSerie = new TimeSeries(kthPs.getName());
			}

			Boolean displayOnChart = (lineSerie.getItemCount() == 0) ? false : kthPs.getDisplayOnChart();
			renderer.setSeriesVisible(k, displayOnChart);
			combinedDataset.addSeries(lineSerie);

		}

		return combinedDataset;
	}


	public JFreeChart initChart(Date arbitraryStartDate, Date arbitraryEndDate) {

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				
				//Domain
				xAxis = new DateAxis();
				xAxis.setAutoRange(true);
				xAxis.setTimeline(tradingTimeLine());
				xAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, domainTicksMultiple(arbitraryStartDate, arbitraryEndDate)));
				xAxis.setMinorTickMarksVisible(true);
				if (jFreeTimePeriod.equals(JFreeChartTimePeriod.DAY)) xAxis.setDateFormatOverride(new SimpleDateFormat("dd MMM yy"));

				///Stock upper chart Y axis
				//Line Y axis
				mainYAxis = new NumberAxis();
				mainYAxis.setAutoRange(true);
				mainYAxis.setAutoRangeIncludesZero(false);
				mainYAxis.setTickLabelFont(mainYAxis.getTickLabelFont().deriveFont(7f));
				mainYAxis.setLabelFont(mainYAxis.getLabelFont().deriveFont(10f));

				//Indicators lower chart Y axis (indicators is not showing at init)
				//indicYAxis = initYAxis();

				/// Plots
				//Lines
				XYLineAndShapeRenderer lineRenderer = new XYLineAndShapeRenderer(true, false);
				lineRenderer.setBaseItemLabelsVisible(true);
				TimeSeriesCollection lineDataset = new TimeSeriesCollection();
				mainPlot = new XYPlot(lineDataset, null, null, lineRenderer);
				mainPlot.addRangeMarker(new ValueMarker(0));
				mainPlot.setRangeAxis(0, mainYAxis);
				mainPlot.setDomainMinorGridlinesVisible(true);

				//Bars
				TimeSeriesCollection barDataset = new TimeSeriesCollection();
				XYBarRenderer barRenderer = new XYBarRenderer();
				mainPlot.setRenderer(1, barRenderer);
				barChartDisplayStrategy.setBarYAxis();
				mainPlot.setDataset(1, barDataset);

				//Indicator plot
				//This is not created from start

				//Combine
				CombinedDomainXYPlot combinedDomainXYPlot = new CombinedDomainXYPlot(xAxis);
				combinedDomainXYPlot.add(mainPlot, CHARTS_TOTAL_WEIGHT);

				//Create
				ChartMain.this.jFreeChart = new JFreeChart(combinedDomainXYPlot);
				ChartMain.this.jFreeChart.removeLegend();

			}
		};

		try {
			EventQueue.invokeAndWait(runnable);
		} catch (Exception e) {
			LOGGER.error(e,e);
		}

		return this.jFreeChart;
	}


	public void updateBarDataSet(Stock selectedShare, Date slideStart, Date slideEnd, Boolean autoAdjustTimeLine, 
									final SortedMap<DataSetBarDescr, SortedMap<Date, BarChart>> barSeries, 
									final int lineSerieIdx, final BarSettings barSettings, final Rectangle2D plotArea) {

		Runnable runnable = new Runnable() {

			public void run() {

				try {
					
					setCursor(java.awt.Cursor.WAIT_CURSOR);
					
					Timeline segmentedTimeline = adjustedTimeLine(selectedShare, slideStart, slideEnd, autoAdjustTimeLine);
					DateAxis domainAxis = (DateAxis) mainPlot.getDomainAxis();
					domainAxis.setTimeline(segmentedTimeline);

					MyTimeSeriesCollection barDataSets = new MyTimeSeriesCollection();

					XYBarRenderer renderer = (XYBarRenderer) mainPlot.getRenderer(1);
					renderer.removeAnnotations();

					renderer.setMargin(0);
					renderer.setBarPainter(new XYBarPainter() {

						@Override
						public void paintBarShadow(Graphics2D g2, XYBarRenderer renderer, int row, int column, RectangularShape bar, RectangleEdge base, boolean pegShadow) {
						}

						@Override
						public void paintBar(Graphics2D g2, XYBarRenderer renderer, int row, int column, RectangularShape bar, RectangleEdge base) {

							int nbSeries = barSeries.size()/3;
							int seriesIdx = (row/3) +1;
							//System.out.printf("%f %f %f %f %d %d %d\n", bar.getX(), bar.getY(), bar.getWidth(), bar.getHeight(), row/3, ((int) bar.getX()) % (barSeries.size()/3), row);
							//if ( barSettings.getSideBySide() && ((int) bar.getX()) % (barSeries.size()/3) != row/3 ) return;

							double width = bar.getWidth();
							double height = bar.getHeight();
							double x = bar.getX() - width/2; //Shift to the left half width to put the end day in the middle of the bar
							double y = bar.getY();

							if (barSettings.getSideBySide()) {
								width = width/nbSeries;
								x = x + width * seriesIdx;
							}
							if (!barSettings.getIsZeroBased()) {
								height = height/seriesIdx ;
							}

							bar.setFrame(x, y, width, height);

							g2.setColor((Color) renderer.getSeriesPaint(row));
							g2.fill(bar);
							g2.draw(bar);

						}
					});

					TimeSeries lineSerie = ((TimeSeriesCollection) mainPlot.getDataset(0)).getSeries(lineSerieIdx);
					double maxBarValue = barChartDisplayStrategy.maxBarValue(lineSerie);
					int eventDefSerieIdx = 0;
					final SimpleDateFormat df = new SimpleDateFormat("yyyy MM dd");
					final NumberFormat pf = new DecimalFormat("#0.00%");
					final NumberFormat nf = new DecimalFormat("#0.00");
					for (final DataSetBarDescr barSeriesDescr : barSeries.keySet()) {
						SortedMap<Date, BarChart> barSerie = barSeries.get(barSeriesDescr);

						TimeSeries barDataSet = barChartDisplayStrategy.buildBarTimeSeries(barSeriesDescr.getSerieName(), barSerie, lineSerie);
						barDataSets.addSeries(barDataSet);

						//Annotation
						if (barSeriesDescr.isLabeled()) {
							RegularTimePeriod annTP = lineSerie.getTimePeriod(Math.min(lineSerie.getItemCount()-1, 5));
							Double annV = maxBarValue * eventDefSerieIdx / barSeries.size();
							String compoundPReal = pf.format(barSeriesDescr.getForecastProfit());
							String compoundPUnReal = pf.format(barSeriesDescr.getForecastProfitUnReal());
							//String reinvest = pf.format(serieDef.getForecastReinvest());
							String priceChange = pf.format(barSeriesDescr.getStockPriceChange());
							//avgROC, failureRatio, failureWeigh, successWeigh, minROC, maxROC, varianceOfROC
							Map<String, Double> bullStats = barSeriesDescr.getBullStats();
							Map<String, Double> bearStats = barSeriesDescr.getBearStats();
							String annotationTxt = barSeriesDescr.getEventDisplayeDef() + 
									" ("
									+ "r" + compoundPReal + " / ur" + compoundPUnReal + " V. b&h" + priceChange //+ " / ri"  + reinvest + " V. b&h" + priceChange
									+ " / avg" + pf.format(bullStats.get("avgROC"))
									+ " / flg" + pf.format(Math.log(Math.abs(bullStats.get("failureWeigh"))/bullStats.get("successWeigh")))
									+ " / min" + pf.format(bullStats.get("minROC")) + " / max" + pf.format(bullStats.get("maxROC"))
									+ ")";
							Date[] dateRange = barSeriesDescr.getDateRange();
							//LOGGER.info("Indicator stats from " + dateRange[0] + " to " + dateRange[1] + ": " + annotationTxt);
							XYTextAnnotation annotation = new XYTextAnnotation(annotationTxt, annTP.getFirstMillisecond(), annV);
							annotation.setTextAnchor(TextAnchor.BASELINE_LEFT);
							String annotationToolTip = "<html>" 
								+ barSeriesDescr.getEventDisplayeDef() + "<br>"
								+ "Calc range: from " + df.format(dateRange[0]) + " to " + df.format(dateRange[1])  + "<br>"
								+ "Compound: " + "r" + compoundPReal + " / ur" + compoundPUnReal + " V. b&h" + priceChange + "<br>" // + reinvest + "(ri) " + " V. " + priceChange + "(b&h)<br>"
								
								+ "Bull Pred Stats: Avg profit " + pf.format(bullStats.get("avgROC")) + ", Failed buy ratio " + pf.format(bullStats.get("failureRatio")) 
								+ ", Failure weight " + pf.format(Math.abs(bullStats.get("failureWeigh"))) 
								+ ", Failed log (=ln(failed weight/success weight)) " + pf.format(Math.log(Math.abs(bullStats.get("failureWeigh"))/bullStats.get("successWeigh")))
								+ ", Min/Max profit " + pf.format(bullStats.get("minROC")) + "/" + pf.format(bullStats.get("maxROC")) + ", Profit std " + pf.format(Math.sqrt(bullStats.get("varianceOfROC")))
								+ "<br>" 
								+ "&ensp; Avg duration " + nf.format(bullStats.get("avgDuration")) + ", Std duration " + nf.format(Math.sqrt(bullStats.get("varianceDuration")))
								+ ", Min duration " + nf.format(bullStats.get("minDuration")) + ", Max duration " + nf.format(bullStats.get("maxDuration"))
								
								+ "<br>"
								+ "Bear Pred stats: Avg loss " + pf.format(bearStats.get("avgROC")) + ", Failed sell ratio " + pf.format(bearStats.get("failureRatio")) 
								+ ", Failure weight " + pf.format(bearStats.get("failureWeigh")) 
								+ ", Failed log (=ln(failed weight/success weight)) " + pf.format(Math.log(bearStats.get("failureWeigh")/Math.abs(bearStats.get("successWeigh"))))
								+ ", Min/Max loss " + pf.format(bearStats.get("minROC")) + "/" + pf.format(bearStats.get("maxROC")) + ", Loss std " + pf.format(Math.sqrt(bearStats.get("varianceOfROC")))
								+ "<br>" 
								+ "&ensp; Avg duration " + nf.format(bearStats.get("avgDuration")) + ", Std duration " + nf.format(Math.sqrt(bearStats.get("varianceDuration")))
								+ ", Min duration " + nf.format(bearStats.get("minDuration")) + ", Max duration " + nf.format(bearStats.get("maxDuration"))
								+ "</html>";
							
							annotation.setToolTipText(annotationToolTip);
							annotation.setPaint(Color.BLUE);
							Color transpWhite = new Color(1f, 1f, 1f, 0.5f);
							annotation.setBackgroundPaint(transpWhite);
							renderer.addAnnotation(annotation);
						}

						//ToolTip
						XYToolTipGenerator xyToolTpGen = new XYToolTipGenerator() {

							public String generateToolTip(XYDataset dataset, int series, int item) {

								String x = "NaN";
								EventType type = EventType.NONE;
								String desrc = "Indeterministic simultaneous BULLISH and BEARISH";
								String barTip = "";
								try {

									Date date = new Date((long) dataset.getXValue(series, item));
									x = df.format(date);
									
									barTip = barSerie.get(date).getToolTip();
									String[] barToolTipSplit = barTip.split("\n");
									switch (barSeriesDescr.getEventType()) {
									case BULLISH:
										type = EventType.BULLISH;
										desrc = barSeriesDescr.getEventDefDescriptor().getHtmlBullishDescription();
										if (barToolTipSplit.length == 2) barTip = barToolTipSplit[0]; //bull:
										break;
									case BEARISH:
										type = EventType.BEARISH;
										desrc = barSeriesDescr.getEventDefDescriptor().getHtmlBearishDescription();
										if (barToolTipSplit.length == 2) barTip = barToolTipSplit[1]; //bear:
										break;
									default:
										break;
									}
									barTip = barTip.replace("\n", "<br>").replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").replaceAll(" ", "&nbsp;");
									
									String profitTip = "";
									if (barSeriesDescr.getTuningRes() != null) {
										try {
											List<PeriodRatingDTO> periods = barSeriesDescr.getTuningRes().getPeriods();
											PeriodRatingDTO period = periods.stream().filter(p -> p.getFrom().equals(date)).findFirst().orElse(null);
											if (period == null) {
												profitTip = "";
											} else {
												double compoundReal = barSeriesDescr.getTuningRes().getForecastProfitAt(date);
												double compoundUnReal = barSeriesDescr.getTuningRes().getForecastProfitAtUnReal(date);
												double priceChange = barSeriesDescr.getTuningRes().getPriceChangeAtPeriodEnding(date);
												profitTip = period.toToolTip() + " (cmpnd " + 
														"r" + pf.format(compoundReal) + " / ur" + pf.format(compoundUnReal)  + 
														" V. b&h" + pf.format(priceChange) 	+ " " + 
														") ";
												LOGGER.debug(((period.getTrend().equals(EventType.BEARISH.name()))?"Buy":"Sell") + " at " + date + " : " + profitTip);
											}
										} catch (Exception e) {
											LOGGER.warn(e,e);
										}
									}

									return "<html>" + "<font size='2'>" +
									"<b>" + barSeriesDescr.getStockDescr() + "</b><br>" +
									"<b>" + barSeriesDescr.getEventDisplayeDef() + "</b> on the " + x + "<br>" +
									"Trend&nbsp;&nbsp;&nbsp;: " + type + "<br>" +
									"Descr&nbsp;&nbsp;&nbsp;: " + desrc + "<br>" +
									((profitTip.isEmpty())?"":"Ending Period&nbsp;&nbsp;&nbsp;: " + profitTip + "<br>") +
									barTip + "<br>" +
									"</font>" + "</html>";

								} catch (Exception e) {
									LOGGER.warn(e, e);
								}
								return "NaN";

							}
						};
						//renderer.setSeriesToolTipGenerator(eventDefSerieIdx, xyToolTpGen);
						barDataSets.addToolTipGenerator(eventDefSerieIdx, xyToolTpGen);

						//Labels
						renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER, TextAnchor.CENTER, -1.57));
						//renderer.setBaseItemLabelFont(renderer.getBaseItemLabelFont().deriveFont(0,8f));
						renderer.setSeriesItemLabelGenerator(eventDefSerieIdx, new XYItemLabelGenerator() {
							@Override
							public String generateLabel(XYDataset dataset, int series, int item) {
								if (barSeriesDescr.getTuningRes() != null) {
									try {
										Date date = new Date((long) dataset.getXValue(series, item));
										List<PeriodRatingDTO> periods = barSeriesDescr.getTuningRes().getPeriods();
										PeriodRatingDTO period = periods.stream().filter(p -> p.getFrom().equals(date)).findFirst().orElse(null);
										if (period == null) return "";
										String note = period.validityTag()?"×":"°";
										return note;
									} catch (Exception e) {
										LOGGER.warn(e,e);
									}
								}
								return "";
							}
						});
						renderer.setSeriesItemLabelsVisible(eventDefSerieIdx, true);

						//Bars
						renderer.setSeriesStroke(eventDefSerieIdx, new BasicStroke(barSeriesDescr.getSerieStrokeSize()));
						renderer.setSeriesOutlinePaint(eventDefSerieIdx, barSeriesDescr.getSerieColor());
						renderer.setSeriesPaint(eventDefSerieIdx, barSeriesDescr.getSerieColor());
						renderer.setSeriesFillPaint(eventDefSerieIdx, barSeriesDescr.getSerieColor());

						eventDefSerieIdx++;

					}

					mainPlot.setDataset(1, barDataSets);

					resetVerticalLines(plotArea);

					renderer.annotationChanged(null);

				} catch (Exception e) {
					LOGGER.error(e,e);
				} finally {
					setCursor(java.awt.Cursor.DEFAULT_CURSOR);
				}
			}

		};

		try {
			EventQueue.invokeLater(runnable);
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
	}

	public void updateLineDataSet(final List<SlidingPortfolioShare> listShares, final StripedCloseFunction stripedCloseFunction, final Boolean applyColors) {

		Runnable runnable = new Runnable() {

			public void run() {
				
				XYDataset dataSet = buildLineDataSet(stripedCloseFunction, listShares, applyColors);
				Date arbitraryStartDate = stripedCloseFunction.getArbitraryStartDateForChart();
				Date arbitraryEndDate = stripedCloseFunction.getArbitraryEndDate();
				xAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, domainTicksMultiple(arbitraryStartDate, arbitraryEndDate)));
				xAxis.setRange(arbitraryStartDate, arbitraryEndDate);
				Rectangle2D plotArea = chartPanel.getScreenDataArea();
				try {
					setCursor(java.awt.Cursor.WAIT_CURSOR);
					
					mainPlot.setDataset(dataSet);

					if (axisMarker != null ) {
						mainPlot.removeRangeMarker(axisMarker);
						axisMarker = null;
					}
					if (stripedCloseFunction.isRelative()) {
						axisMarker = new ValueMarker(0, Color.BLACK, new BasicStroke(1f),Color.BLACK, new BasicStroke(1f), .5f);
						mainPlot.addRangeMarker(axisMarker);
					}

				} catch (IllegalArgumentException e) {
					LOGGER.warn(e, e);
					resetBarChart();
					resetIndicChart();
					updateLineDataSet(listShares, stripedCloseFunction, applyColors);
				}  finally {
					setCursor(java.awt.Cursor.DEFAULT_CURSOR);
				}

				resetVerticalLines(plotArea);
				
				removeVTrans();
				listShares.stream()
					.forEach(ps -> {
						if (ps.getDisplayOnChart() && ps.isChartTransactions()) {
							ps.getTransactions(false).stream()
							.forEach(t -> {
								Paint lineColor = (t.getQuantity().compareTo(BigDecimal.ZERO) > 0)?Color.cyan:Color.blue;
								String text = 
										t.toChart()
										+ " / reinv. g. " + PERCENTAGE_FORMAT.format(ps.getGainReinvestedPercent(null, t.getDate(), ps.getTransactionCurrency(), false));
										//TODO create a transaction gain a like annualisedGain?
										//TODO how to compare with b&h (annualised b&h OR transaction span b&h)??
										//+ " / tr gain " + PERCENTAGE_FORMAT.format(t.get...)
										//+ " / gain " + PERCENTAGE_FORMAT.format(ps.getGainTotalPercent(null, t.getDate(), ps.getTransactionCurrency(), false));
								addVTrans(
										t.getDate().getTime(), 
											text,
										lineColor);
							});
						}
				});
			}

		};

		EventQueue.invokeLater(runnable);
	}

	public void updateIndicDataSet(Stock selectedShare, Date slideStart, Date slideEnd, Boolean autoAdjustTimeLine, final Map<EventInfo, SortedMap<Date, double[]>> eventsSeries, final Rectangle2D plotArea) {

		Runnable runnable = new Runnable() {

			public void run() {

				try {
					
					setCursor(java.awt.Cursor.WAIT_CURSOR);
					
					//Display empty message
					CombinedDomainXYPlot combinedDomainXYPlot = (CombinedDomainXYPlot) jFreeChart.getXYPlot();

					//Check if Indic plot present
					@SuppressWarnings("unchecked") List<XYPlot> subplots = combinedDomainXYPlot.getSubplots();

					if (subplots.size() == 1) { //Indic plot is not present: we create it
						XYLineAndShapeRenderer indicRenderer = new XYLineAndShapeRenderer(true, false);
						indicRenderer.setBaseItemLabelsVisible(true, false); //??

						indicPlot = new XYPlot(null, null, null , indicRenderer);
						//indicPlot.addRangeMarker(0, new ValueMarker(0), Layer.FOREGROUND, false);
						indicPlot.setNoDataMessage("No indicator output available. Click 'Calculations Update' or 'Calculator Settings'.\nAlso check that the selected stock and date ranges are valid.");
						indicPlot.setDomainMinorGridlinesVisible(true);
					}
					
					//Domain WE fix
					Timeline segmentedTimeline = adjustedTimeLine(selectedShare, slideStart, slideEnd, autoAdjustTimeLine);
					DateAxis domainAxis = (DateAxis) combinedDomainXYPlot.getDomainAxis();
					domainAxis.setTimeline(segmentedTimeline);

					//Chart
					SortedSet<Date> fullDateSet = new TreeSet<>();
					for (SortedMap<Date, double[]> output : eventsSeries.values()) {
						fullDateSet.addAll(output.keySet());
					}
					ChartIndicLineSeriesDataSetBuilder dataSetBuilder = new ChartIndicLineSeriesDataSetBuilder(indicPlot, useOneRange, useNoGroupRanges, fullDateSet, eventsSeries);
					dataSetBuilder.build();

					//Combine group
					if (subplots.size() == 1) {
						mainPlot.setWeight(CHARTS_TOTAL_WEIGHT - indicPlotWeight);
						combinedDomainXYPlot.add(indicPlot, indicPlotWeight);
					}

					resetVerticalLines(plotArea);
					ValueAxis rangeAxis = indicPlot.getRangeAxis();
					if (rangeAxis != null) {
						//rangeAxis.setAutoRange(true);
						//rangeAxis.setAutoRangeMinimumSize(Double.MIN_VALUE);
					}

				} catch (Exception e) {
					LOGGER.warn("Can't refresh indicator chart : "+ e, e);
				} finally {
					setCursor(java.awt.Cursor.DEFAULT_CURSOR);
				}
			}

		};

		EventQueue.invokeLater(runnable);
	}
	
	private Timeline adjustedTimeLine(Stock selectedShare, Date slideStart, Date slideEnd, Boolean autoAdjustTimeLine) throws NoQuotationsException, NotEnoughDataException {
		Timeline segmentedTimeline;
		if (autoAdjustTimeLine) {
			Quotations quotations = getQuotationUnitsAllClose(selectedShare, Currency.NAN, slideStart, slideEnd);
			SortedMap<Date, double[]> quotationsMap = QuotationsFactories.getFactory().buildExactMapFromQuotationsClose(quotations);
			boolean includeWeekends = quotationsMap.keySet().stream().anyMatch(d -> Instant.ofEpochMilli(d.getTime()).atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek().getValue() >= 6);
			LOGGER.info("Including weekends - continous quotations: " + includeWeekends);
			segmentedTimeline = includeWeekends?new SegmentedTimeline(SegmentedTimeline.DAY_SEGMENT_SIZE,7,0):SegmentedTimeline.newMondayThroughFridayTimeline();
		} else {
			segmentedTimeline = new SegmentedTimeline(SegmentedTimeline.DAY_SEGMENT_SIZE,7,0);
		}
		return segmentedTimeline;
	}
	
	public void setChartPanel(ChartPanel chartPanel) {
		this.chartPanel = chartPanel;
	}
	
	private void setCursor(int cursor) {
		Cursor awtPredefinedCursor = java.awt.Cursor.getPredefinedCursor(cursor);
		chartFrame.setCursor(awtPredefinedCursor);
		chartPanel.setCursor(awtPredefinedCursor);
		if (chartPanel.getComponents().length > 0) {
			chartPanel.getComponent(0).setCursor(awtPredefinedCursor);
		}
	}

	private Quotations getQuotationUnitsAllClose(Stock stock, Currency transactCurrency, Date startDate, Date endDate) throws NoQuotationsException {
		
		try {
			
			transactCurrency = (Currency.NAN.equals(transactCurrency))?stock.getMarketValuation().getCurrency():transactCurrency;
			Quotations bdQuotes;
			if (disableSplitFix) {
				bdQuotes = QuotationsFactories.getFactory()
									.getRawQuotationsInstance(stock, startDate, endDate, true, transactCurrency, 1, SplitOption.RAW, ValidityFilter.CLOSE);
			} else if (useCalculatedSplitFixOnly) {
				bdQuotes = QuotationsFactories.getFactory()
									.getRawQuotationsInstance(stock, startDate, endDate, true, transactCurrency, 1, SplitOption.SPLITFREE_CALCULATEDONLY, ValidityFilter.CLOSE);
			} else {
				bdQuotes = QuotationsFactories.getFactory().getSplitFreeQuotationsInstance(stock, startDate, endDate, true, transactCurrency, 1, ValidityFilter.CLOSE);
			}
			return bdQuotes;
		} catch (NoQuotationsException e) {
			throw e;
		}

	}

	private TimeSeries buildLineSeries(StripedCloseFunction stripedCloseFunction, Quotations bdQuotes, SlidingPortfolioShare portfolioShare) {

		TimeSeries timeSeries = new TimeSeries(portfolioShare.getName());

		if (bdQuotes.size() > 0 && portfolioShare.getStock().getLastQuote().after(stripedCloseFunction.getArbitraryStartDateForChart())) {

			MInteger startIdx = new MInteger();
			MInteger endIdx = new MInteger();
			SortedMap<Date, Double> relativeCloses = stripedCloseFunction.targetShareData(portfolioShare, bdQuotes, startIdx, endIdx);
			//List<QuotationUnit> quotationUnits = bdQuotes.getQuotationUnits(startIdx.value, endIdx.value);

			//for (int i = 0; i <= Math.min(relativeCloses.size()-1, endIdx.value-startIdx.value); i++) {
			for (Date date: relativeCloses.keySet()) {
				//QuotationUnit trade = quotationUnits.get(i);
				RegularTimePeriod period = new Day(date);
				Number value = relativeCloses.get(date);
				timeSeries.add(new TimeSeriesDataItem(period, value));
			}

		}  else {
			portfolioShare.setDisplayOnChart(false);
		}

		return timeSeries;

	}

	public void highLightSerie(final Integer serie, final float highlightStroke) {

		Runnable runnable = new Runnable() {

			public void run() {
				for (int i = 0; i < mainPlot.getSeriesCount(); i++) {
					mainPlot.getRenderer().setSeriesStroke(i, new BasicStroke(1));
				}
				Stroke stroke = new BasicStroke(highlightStroke);
				mainPlot.getRenderer().setSeriesStroke(serie, stroke);
			}

		};

		EventQueue.invokeLater(runnable);
	}


	@SuppressWarnings("unused") //Test
	private void exportQuotations(String filename, ArrayList<OHLCDataItem> data) {

		File export = new File(filename);
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

		try {
			FileWriter fos = new FileWriter(export);
			fos.write("DATE,QUOTE\n");
			for (int i = 0; i < data.size(); i++) {
				fos.write(df.format(data.get(i).getDate()) + "," + data.get(i).getClose() + "\n");
			}
			fos.close();
		} catch (IOException e) {
			LOGGER.error("", e);
		}

	}

	public void resetLineChart() {
		Runnable runnable = new Runnable() {
			public void run() {
				mainPlot.setDataset(0, new TimeSeriesCollection());
			}
		};

		EventQueue.invokeLater(runnable);
	}

	public void resetBarChart() {

		Runnable runnable = new Runnable() {
			public void run() {
				mainPlot.setDataset(1, new TimeSeriesCollection());
				mainPlot.getRenderer(1).removeAnnotations();
			}
		};

		EventQueue.invokeLater(runnable);
	}

	public void resetIndicChart() {

		Runnable runnable = new Runnable() {

			public void run() {
				@SuppressWarnings("unchecked") List<XYPlot> subplots = ((CombinedDomainXYPlot) jFreeChart.getXYPlot()).getSubplots();
				if (indicPlot != null && subplots.size() == 2) {

					indicPlotWeight = subplots.get(1).getWeight();

					indicPlot.clearRangeAxes();
					indicPlot.clearDomainAxes();
					((CombinedDomainXYPlot) jFreeChart.getXYPlot()).remove(indicPlot);
					indicPlot = null;

				}
			}

		};

		EventQueue.invokeLater(runnable);
	}

	protected XYPlot getMainPlot() {
		return mainPlot;
	}

	protected NumberAxis getMainYAxis() {
		return mainYAxis;
	}

	public void initMainPlot(final NumberFormat yAxisFormat, final String noDataMsg) {

		Runnable runnable = new Runnable() {

			public void run() {
				mainYAxis.setNumberFormatOverride(yAxisFormat);
				mainPlot.setNoDataMessage(noDataMsg);
			}

		};
		try {
			EventQueue.invokeAndWait(runnable);
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
	}

	public void setMainYAxisLabel(final String label) {

		Runnable runnable = new Runnable() {

			public void run() {
				mainYAxis.setLabel(label);
			}

		};
		EventQueue.invokeLater(runnable);
	}

	public void exportChart(String path) throws FileNotFoundException, IOException {
		mainPlot.getRenderer(1).removeAnnotations();
		ChartUtilities.writeChartAsPNG(new FileOutputStream(new File(path)), this.jFreeChart, 700, 600);
	}

	public void slideChart(int increment, Rectangle2D plotarea) {

		CombinedDomainXYPlot combinedDomainXYPlot = (CombinedDomainXYPlot) jFreeChart.getXYPlot();
		@SuppressWarnings("unchecked") List<XYPlot> subplots = combinedDomainXYPlot.getSubplots();

		if (subplots.size() != 2) {
			return;
		} else {
			int upChartweight = subplots.get(0).getWeight();
			int newUpChartWeight = upChartweight-increment;
			if (newUpChartWeight <= 0 || newUpChartWeight >= CHARTS_TOTAL_WEIGHT) return;
			subplots.get(0).setWeight(newUpChartWeight);
			int lowChartweight = subplots.get(1).getWeight();
			int newLowChartWeight = lowChartweight+increment;
			if (newLowChartWeight <= 0 || newLowChartWeight >= CHARTS_TOTAL_WEIGHT) return;
			subplots.get(1).setWeight(newLowChartWeight);
			indicPlotWeight = newLowChartWeight;

			resetVerticalLines(plotarea);
		}

	}

	public Boolean isSlidingArea(double chartY, double mouseY) {

		CombinedDomainXYPlot combinedDomainXYPlot = (CombinedDomainXYPlot) jFreeChart.getXYPlot();
		@SuppressWarnings("unchecked") List<XYPlot> subplots = combinedDomainXYPlot.getSubplots();
		double xAxisDim = 20;

		if (subplots.size() != 2) {
			return false;
		} else {
			double mousePos = mouseY/(chartY-xAxisDim);
			double slidingArea = (double)subplots.get(0).getWeight()/(double)CHARTS_TOTAL_WEIGHT;
			if (slidingArea + .01 >= mousePos && mousePos >= slidingArea - .01) {
				return true;
			}
		}

		return false;
	}

	public void addVLineAt(Point2D clickPoint, Rectangle2D plotArea) {

		ValueMarker vLineAt = checkVLineAt(clickPoint, plotArea);
		List<ValueMarker> hLineAt = checkHLineAt(clickPoint, plotArea);

		if (vLineAt == null) {

			if (hLineAt.size() > 0) {
				for (ValueMarker valueMarker : hLineAt) {
					removeHLine(valueMarker);
				}
			}

			long chartX = point2DToTime(clickPoint, plotArea);
			ValueMarker vDomainMarker = new ValueMarker(chartX);
			vDomainMarker.setPaint(Color.ORANGE);
			vDomainMarker.setStroke(new BasicStroke(1.5f, BasicStroke.JOIN_MITER, BasicStroke.JOIN_MITER, 10.0f, new float[]{1.0f}, 0.0f)); //set the value new float[]{1.0f}as 1.0f to avoiding dashing of marker

			mainPlot.addDomainMarker(vDomainMarker);
			if (indicPlot != null) {
				indicPlot.addDomainMarker(vDomainMarker);
			}

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
			String text = simpleDateFormat.format(chartX);
			XYTextAnnotation vLineLabel = createVLineAnnotation(chartX, plotArea.getHeight(), text);
			vLineAnnotations.put(vDomainMarker, vLineLabel);
			mainPlot.addAnnotation(vLineLabel);

		} else {

			removeVLineMarkerAndAnnotation(vLineAt);

			List<Double> chartYs = point2DToRange(clickPoint, plotArea);

			if (indicPlot == null || clickPoint.getY() < plotArea.getHeight()*((double)(CHARTS_TOTAL_WEIGHT - indicPlotWeight)/(double)CHARTS_TOTAL_WEIGHT)) {

				ValueMarker vRangeMarker = new ValueMarker(chartYs.get(0));
				vRangeMarker.setPaint(Color.ORANGE);
				vRangeMarker.setLabelAnchor(RectangleAnchor.LEFT);
				vRangeMarker.setLabelTextAnchor(TextAnchor.BOTTOM_LEFT);
				vRangeMarker.setLabelFont(mainYAxis.getTickLabelFont().deriveFont(8f));
				vRangeMarker.setLabel(mainYAxis.getNumberFormatOverride().format(chartYs.get(0)));
				vRangeMarker.setStroke(new BasicStroke(1.5f, BasicStroke.JOIN_MITER, BasicStroke.JOIN_MITER, 10.0f, new float[]{1.0f},0.0f)); //set the value new float[]{1.0f}as 1.0f to avoiding dashing of marker
				mainPlot.addRangeMarker(vRangeMarker);

			} else {
				for (int i= 0; i < indicPlot.getRangeAxisCount(); i++) {

					Double value = chartYs.get(i);

					if (value != null) {
						ValueMarker vRangeMarker = new ValueMarker(value);
						vRangeMarker.setPaint(Color.ORANGE);
						vRangeMarker.setLabelAnchor(RectangleAnchor.LEFT);
						vRangeMarker.setLabelOffsetType(LengthAdjustmentType.CONTRACT);
						vRangeMarker.setLabelOffset(new RectangleInsets(0, 50.0*i, 0, 0));
						vRangeMarker.setLabelTextAnchor(TextAnchor.BOTTOM_LEFT);
						vRangeMarker.setLabelFont(indicPlot.getRangeAxis(i).getTickLabelFont().deriveFont(8f));
						NumberFormat numberFormatOverride = ((NumberAxis)indicPlot.getRangeAxis(i)).getNumberFormatOverride();
						if (numberFormatOverride == null) numberFormatOverride = new DecimalFormat("#0.0000");
						vRangeMarker.setLabel((i+1)+"/ "+numberFormatOverride.format(value));
						vRangeMarker.setStroke(new BasicStroke(1.5f, BasicStroke.JOIN_MITER, BasicStroke.JOIN_MITER, 10.0f, new float[]{1.0f},0.0f)); //set the value new float[]{1.0f}as 1.0f to avoiding dashing of marker
						indicPlot.addRangeMarker(i, vRangeMarker, Layer.FOREGROUND);
					}

				}
			}

		}

	}

	protected XYTextAnnotation createVLineAnnotation(long chartX, double totalHeight, String text) {

		double chartY = mainYAxis.getLowerBound() + 50*mainYAxis.getRange().getLength() / (totalHeight*((double)(CHARTS_TOTAL_WEIGHT-indicPlotWeight)/CHARTS_TOTAL_WEIGHT));

		XYTextAnnotation vLineLabel = new XYTextAnnotation(text, chartX, chartY);
		vLineLabel.setFont(mainYAxis.getTickLabelFont().deriveFont(10f));
		vLineLabel.setRotationAnchor(TextAnchor.BASELINE_CENTER);
		vLineLabel.setTextAnchor(TextAnchor.BASELINE_CENTER);
		vLineLabel.setRotationAngle(-3.14 / 2);
		vLineLabel.setPaint(Color.black);

		return vLineLabel;
	}


	public long point2DToTime(Point2D clickPoint, Rectangle2D plotArea) {
		long chartX = (long) xAxis.java2DToValue(clickPoint.getX(), plotArea, mainPlot.getDomainAxisEdge());
		long chartXRounded = DateFactory.midnithDate(new Date(chartX)).getTime();
		chartXRounded = (chartX-chartXRounded > 24 * 3600 * 1000 /2)?chartXRounded+24 * 3600 * 1000:chartXRounded;
		return chartX;
	}

	private List<Double> point2DToRange(Point2D clickPoint, Rectangle2D plotArea) {

		List<Double> chartY = new ArrayList<Double>();
		double mainH = (indicPlot == null)? plotArea.getHeight() : plotArea.getHeight()*(double)(CHARTS_TOTAL_WEIGHT - indicPlotWeight)/(double)CHARTS_TOTAL_WEIGHT;
		if (clickPoint.getY() < mainH) { //click within the bar chart
			Rectangle2D.Double doubleRectanble = new Rectangle2D.Double(plotArea.getX(), plotArea.getY(), plotArea.getWidth(), mainH);
			chartY.add(mainYAxis.java2DToValue(clickPoint.getY(), doubleRectanble, mainPlot.getRangeAxisEdge()));
		} else if (indicPlot != null) { //click below the bar chart and there is an indicator plot visible.
			Rectangle2D.Double doubleRectanble = new Rectangle2D.Double(plotArea.getX(), plotArea.getY() + mainH + 4, plotArea.getWidth(), plotArea.getHeight()*((double)(indicPlotWeight)/(double)CHARTS_TOTAL_WEIGHT));
			int rangeAxisCount = indicPlot.getRangeAxisCount();
			for (int i = 0; i < rangeAxisCount; i++) {
				if (indicPlot.getRangeAxis(i) != null) {
					chartY.add(indicPlot.getRangeAxis(i).java2DToValue(clickPoint.getY(), doubleRectanble, indicPlot.getRangeAxisEdge(i)));
				} else {
					chartY.add(null);
				}
			}
		}

		return chartY;

	}


	public ValueMarker checkVLineAt(Point2D point2d, Rectangle2D rectangle2d) {

		Point2D lowerClickPoint = new Point( (int) point2d.getX()-1, (int)point2d.getY());
		Point2D higherClickPoint = new Point( (int) point2d.getX()+1, (int)point2d.getY());
		long lowerTime = point2DToTime(lowerClickPoint, rectangle2d);
		long higherTime = point2DToTime(higherClickPoint, rectangle2d);

		return checkVLineAt(lowerTime, higherTime);

	}

	private ValueMarker checkVLineAt(long lowerTime, long higherTime) {
		@SuppressWarnings("unchecked")
		Collection<ValueMarker> mainDomainMarkers = mainPlot.getDomainMarkers(Layer.FOREGROUND);
		if (mainDomainMarkers != null) {
			for (ValueMarker valueMarker : mainDomainMarkers) {
				if (lowerTime <= valueMarker.getValue() && valueMarker.getValue() <= higherTime) {
					return valueMarker;
				}
			}
		}
		return null;
	}

	public List<ValueMarker> checkHLineAt(Point2D clickPoint, Rectangle2D plotArea) {

		double mainH = (indicPlot == null)? plotArea.getHeight() : plotArea.getHeight()*(double)(CHARTS_TOTAL_WEIGHT - indicPlotWeight)/(double)CHARTS_TOTAL_WEIGHT;

		List<ValueMarker> valueMarkers = new ArrayList<ValueMarker>();
		if (clickPoint.getY() < mainH) {

			Point2D lowerClickPoint = new Point( (int) clickPoint.getX(), (int) clickPoint.getY()+1);
			Point2D higherClickPoint = new Point( (int) clickPoint.getX(), (int) clickPoint.getY()-1);
			List<Double> lowerTime = point2DToRange(lowerClickPoint, plotArea);
			List<Double> higherTime = point2DToRange(higherClickPoint, plotArea);

			@SuppressWarnings("unchecked")
			Collection<ValueMarker> mainRangeMarkers = mainPlot.getRangeMarkers(Layer.FOREGROUND);
			if (mainRangeMarkers != null) {
				for (ValueMarker valueMarker : mainRangeMarkers) {
					if (lowerTime.get(0) <= valueMarker.getValue() && valueMarker.getValue() <= higherTime.get(0)) {
						valueMarkers.add(valueMarker);
					}
				}
			}

		} else if (indicPlot != null) {

			Point2D lowerClickPoint = new Point( (int) clickPoint.getX(), (int) clickPoint.getY()+1);
			Point2D higherClickPoint = new Point( (int) clickPoint.getX(), (int) clickPoint.getY()-1);
			List<Double> lowerTime = point2DToRange(lowerClickPoint, plotArea);
			List<Double> higherTime = point2DToRange(higherClickPoint, plotArea);

			int rangeAxisCount = indicPlot.getRangeAxisCount();
			for (int i = 0; i < rangeAxisCount; i++) {
				@SuppressWarnings("unchecked")
				Collection<ValueMarker> indicRangeMarkers = indicPlot.getRangeMarkers(i, Layer.FOREGROUND);
				if (indicRangeMarkers != null) {
					for (ValueMarker valueMarker : indicRangeMarkers) {
						if (lowerTime.get(i) != null && higherTime.get(i) != null) {
							if (lowerTime.get(i) <= valueMarker.getValue() && valueMarker.getValue() <= higherTime.get(i)) {
								valueMarkers.add(valueMarker);
							}
						}
					}
				}
			}

		}

		return valueMarkers;

	}

	public Boolean removeVLineAt(Point2D point2d, Rectangle2D rectangle2d) {
		ValueMarker vLineAt = checkVLineAt(point2d, rectangle2d);
		if (vLineAt != null) {
			removeVLineMarkerAndAnnotation(vLineAt);
			return true;
		} else {
			return false;
		}
	}

	public Boolean removeHLineAt(Point2D point2d, Rectangle2D rectangle2d) {
		List<ValueMarker> hLineAt = checkHLineAt(point2d, rectangle2d);
		if (hLineAt.size() > 0) {
			for (ValueMarker valueMarker : hLineAt) {
				removeHLine(valueMarker);
			}
			return true;
		} else {
			return false;
		}
	}

	protected void removeHLine(ValueMarker hLineAt) {
		mainPlot.removeRangeMarker(hLineAt);
		if (indicPlot != null) {
			int rangeAxisCount = indicPlot.getRangeAxisCount();
			for (int i = 0; i < rangeAxisCount; i++) {
				indicPlot.removeRangeMarker(i, hLineAt, Layer.FOREGROUND);
			}
		}
	}

	private void removeVLineMarkerAndAnnotation(ValueMarker valueMarker) {
		
		mainPlot.removeDomainMarker(valueMarker);
		if (indicPlot != null) {
			indicPlot.removeDomainMarker(valueMarker);
		}
		
		XYTextAnnotation annot = vLineAnnotations.remove(valueMarker);
		if (annot != null) mainPlot.removeAnnotation(annot);

	}

	public void removeVLines() {

		for (ValueMarker marker : vLineAnnotations.keySet()) {
			mainPlot.removeDomainMarker(marker);
			mainPlot.removeAnnotation(vLineAnnotations.get(marker));
		}
		vLineAnnotations.clear();

		if (indicPlot != null) {
			indicPlot.clearDomainMarkers();
		}
	}
	
	public void removeVTrans() {

		for (ValueMarker marker : vTransAnnotations.keySet()) {
			mainPlot.removeDomainMarker(marker);
			mainPlot.removeAnnotation(vTransAnnotations.get(marker));
		}
		vTransAnnotations.clear();
	}

	public void removeHLines() {

		mainPlot.clearRangeMarkers();
		if (indicPlot != null) {
			indicPlot.clearRangeMarkers();
		}

	}

	@SuppressWarnings("unchecked")
	public void resetVerticalLines(Rectangle2D plotArea) {

		Collection<ValueMarker> mainDomainMarkers = mainPlot.getDomainMarkers(Layer.FOREGROUND);

		Collection<ValueMarker> indicDomainMarkers = null;
		if (indicPlot != null) {
			indicDomainMarkers = indicPlot.getDomainMarkers(Layer.FOREGROUND);
		}

		if (mainDomainMarkers != null) {
			for (ValueMarker valueMarker : mainDomainMarkers) {
				
				if (indicPlot != null && (indicDomainMarkers == null || !indicDomainMarkers.contains(valueMarker))) {
					indicPlot.addDomainMarker(valueMarker);
				}

				long chartX = Double.valueOf(valueMarker.getValue()).longValue();
				XYTextAnnotation existingAnnotation = vLineAnnotations.remove(valueMarker);
				if (existingAnnotation != null) {
					mainPlot.removeAnnotation(existingAnnotation);
					String text = existingAnnotation.getText();
					XYTextAnnotation newAnnotation = createVLineAnnotation(chartX, plotArea.getHeight(), text);
					mainPlot.addAnnotation(newAnnotation);
					vLineAnnotations.put(valueMarker, newAnnotation);
				}
				
			}
		}

	}
	
	public void setDisableSplitFix(Boolean disableSplitFix) {
		this.disableSplitFix = disableSplitFix;
	}

	public void setUseCalculatedSplitFixOnly(Boolean useCalculatedSplitFixOnly) {
		this.useCalculatedSplitFixOnly = useCalculatedSplitFixOnly;
	}
	
	public void setUseOneRange(Boolean useOneRange) {
		this.useOneRange = useOneRange;
	}
	
	public void setUseNoGroupRanges(Boolean useNoGroupRanges) {
		this.useNoGroupRanges = useNoGroupRanges;
	}
	
	public void setFrame(Frame chartFrame) {
		this.chartFrame = chartFrame;
		
	}

	private void addVTrans(double xDomainValue, String text, Paint lineColor) {
		
		ValueMarker vDomainMarker = new ValueMarker(xDomainValue);
		vDomainMarker.setPaint(lineColor);
		vDomainMarker.setStroke(new BasicStroke(1.5f, BasicStroke.JOIN_MITER, BasicStroke.JOIN_MITER, 10.0f, new float[]{1.0f}, 0.0f));
		mainPlot.addDomainMarker(vDomainMarker);
		
		long chartX = Double.valueOf(vDomainMarker.getValue()).longValue();
		XYTextAnnotation vLineLabel = createVLineAnnotation(chartX, chartPanel.getScreenDataArea().getHeight(), text);
		vTransAnnotations.put(vDomainMarker, vLineLabel);
		mainPlot.addAnnotation(vLineLabel);
		
	}
	
//	private void removeVTrans(long xDomainValue) {
//		ValueMarker vDomainMarker = checkVLineAt(xDomainValue-1, xDomainValue+1);
//		if (vDomainMarker != null) {
//			removeVline(vDomainMarker);
//		}
//		
//	}

}
