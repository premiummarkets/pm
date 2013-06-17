/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.portfolio.gui.charts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.RectangularShape;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SortedMap;

import javax.swing.ToolTipManager;
import javax.swing.UIManager;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
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
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.StripedCloseFunction;
import com.finance.pms.datasources.db.StripedCloseRelativeToReferee;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventType;
import com.finance.pms.events.calculation.EventDefDescriptor;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.chartUtils.DataSetBarDescr;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;

/**
 * The Class Charts.
 * 
 * @author Guillaume Thoreton
 */
public class ChartMain extends Chart {
	
	private static MyLogger LOGGER = MyLogger.getLogger(StripedCloseRelativeToReferee.class);
	
	public static final DateFormat DATE_FORMAT = DateFormat.getTimeInstance();
	public static final DecimalFormat PERCENTAGE_FORMAT = new DecimalFormat("#0.00 %");
	public static final NumberFormat NUMBER_FORMAT = new DecimalFormat("#0.00");
	
	private BarChartDisplayStrategy barChartDisplayStrategy; 

	private JFreeChartTimePeriod jFreeTimePeriod;
	private JFreeChart jFreeChart;

	private DateAxis xAxis;
	private NumberAxis mainYAxis;
	private XYPlot mainPlot;

	private XYPlot indicPlot;
	
	public ChartMain(Date startDate, JFreeChartTimePeriod jFreeTimePeriod) {
		super();
		this.jFreeTimePeriod = jFreeTimePeriod;
		UIManager.put("ToolTip.background", new java.awt.Color(239, 203, 152, 255));
		UIManager.put("ToolTip.foreground", java.awt.Color.BLACK);
		
		ToolTipManager.sharedInstance().setInitialDelay(0);
		ToolTipManager.sharedInstance().setReshowDelay(Integer.MAX_VALUE);
		ToolTipManager.sharedInstance().setDismissDelay(120000);
		ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);
		
		XYBarRenderer.setDefaultShadowsVisible(false);
		XYBarRenderer.setDefaultBarPainter(new StandardXYBarPainter());
		
		//barChartDisplayStrategy = new ChartBarOnQuotes(this);
		barChartDisplayStrategy = new ChartBarSquare(this);
		
	}
	
	private XYDataset buildLineDataSet(final StripedCloseFunction stripedCloseFunction, final List<SlidingPortfolioShare> portfolioShares, final Boolean applyColors) {

		final TimeSeriesCollection combinedDataset = new TimeSeriesCollection();

		//Check if empty
		Boolean isAnyShowing = false;
		for (SlidingPortfolioShare slidingPortfolioShare : portfolioShares) {
			isAnyShowing = isAnyShowing || slidingPortfolioShare.getDisplayOnChart();
		}
		if (!isAnyShowing) return combinedDataset;

		//Build lines
		for (int k = 0; k < portfolioShares.size(); k++) {

			final Quotations quotations = getQuotations(stripedCloseFunction, portfolioShares.get(k));

			TimeSeries lineSerie = buildLineSeries(stripedCloseFunction, quotations, portfolioShares.get(k));
			combinedDataset.addSeries(lineSerie);

			Paint paint = java.awt.Color.BLACK;
			if (applyColors) {
				org.eclipse.swt.graphics.Color color = portfolioShares.get(k).getColor();
				paint = new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue());
			}

			final XYItemRenderer renderer = mainPlot.getRenderer();

			renderer.setSeriesPaint(k, paint);
			renderer.setSeriesStroke(k, new BasicStroke(1));

			final int kf = k;
			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy");
			final NumberFormat percentInstance = new DecimalFormat("#0.00 %");
			XYToolTipGenerator xyToolTpGen = new XYToolTipGenerator() {

				public String generateToolTip(XYDataset dataset, int series, int item) {

					String y = "NaN";
					String x = "NaN";
					QuotationUnit closeForDate;
					try {
						Date date = new Date((long) dataset.getXValue(series, item));
						x = simpleDateFormat.format(date);
						closeForDate = quotations.get(quotations.getClosestIndexForDate(0, date));

						String variationAddInfo = "";
						if (!stripedCloseFunction.lineToolTip().isEmpty()) {
							y = percentInstance.format(dataset.getYValue(series, item));
							variationAddInfo = "<br>Variation : " + y + " " + stripedCloseFunction.lineToolTip();
						}

						return "<html>" + "<font size='2'>" + "<b>" + portfolioShares.get(kf).getFreindlyName() + "</b> On the " + x + "<br>"
						+ "Open&nbsp;&nbsp;&nbsp;: " + closeForDate.getOpen() + "<br>" + "High&nbsp;&nbsp;&nbsp;: " + closeForDate.getHigh()
						+ "<br>" + "Low&nbsp;&nbsp;&nbsp;&nbsp;: " + closeForDate.getLow() + "<br>" + "Close&nbsp;&nbsp;: "
						+ closeForDate.getClose() + "<br>" + "Volume : " + closeForDate.getVolume() + variationAddInfo + "</font>" + "</html>";
					} catch (Exception e) {
						LOGGER.debug(e, e);
					}
					return "NaN";

				}
			};

			renderer.setSeriesToolTipGenerator(k, xyToolTpGen);
			renderer.setSeriesShape(k, new Rectangle(new Dimension(100, 100)));
			Boolean displayOnChart = (lineSerie.getItemCount() == 0) ? false : portfolioShares.get(k).getDisplayOnChart();
			renderer.setSeriesVisible(k, displayOnChart);

		}


		return combinedDataset;
	}


	public JFreeChart initChart(final StripedCloseFunction stripedCloseFunction) {
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				
				Date arbitraryStartDate = stripedCloseFunction.getArbitraryStartDate();
				Date arbitraryEndDate = stripedCloseFunction.getArbitraryEndDate();

				xAxis = new DateAxis();
				xAxis.setAutoRange(true);
				xAxis.setTimeline(tradingTimeLine());
				xAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, domainTicksMultiple(arbitraryStartDate, arbitraryEndDate)));
				if (jFreeTimePeriod.equals(JFreeChartTimePeriod.DAY)) xAxis.setDateFormatOverride(new SimpleDateFormat("dd MMM yy"));
				
				///	Y axis
				//Line Y axis
				mainYAxis = new NumberAxis();	 
				mainYAxis.setAutoRange(true);
				mainYAxis.setAutoRangeIncludesZero(false);
				mainYAxis.setTickLabelFont(mainYAxis.getTickLabelFont().deriveFont(7f));
				mainYAxis.setLabelFont(mainYAxis.getLabelFont().deriveFont(10f));
				
				//Indicator Y axis (indicator is not showing at init)
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
				combinedDomainXYPlot.add(mainPlot, 3);
				
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

	protected NumberAxis initYAxis() {
		NumberAxis indicYAxis = new NumberAxis();	 
		indicYAxis.setAutoRange(true);
		indicYAxis.setAutoRangeIncludesZero(false);
		indicYAxis.setTickLabelFont(indicYAxis.getTickLabelFont().deriveFont(7f));
		indicYAxis.setLabelFont(indicYAxis.getLabelFont().deriveFont(10f));
		return indicYAxis;
	}
	
	
	public void updateBarDataSet(final SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barSeries, final int lineSerieIdx, final Boolean isZeroBased) {
		
		Runnable runnable = new Runnable() {
			
			public void run() {
				
				TimeSeriesCollection barDataSets = new TimeSeriesCollection();
				
				XYBarRenderer renderer = (XYBarRenderer) mainPlot.getRenderer(1);
				renderer.removeAnnotations();
				
				//TODO review with the chart options
				//renderer.setBase(serieDef.getBase());
				//renderer.setUseYInterval(true);
				renderer.setMargin(0);
				renderer.setBarPainter(new XYBarPainter() {
					
					@Override
					public void paintBarShadow(Graphics2D g2, XYBarRenderer renderer, int row, int column, RectangularShape bar, RectangleEdge base, boolean pegShadow) {
					}
					
					@Override
					public void paintBar(Graphics2D g2, XYBarRenderer renderer, int row, int column, RectangularShape bar, RectangleEdge base) {
						
						//System.out.printf("%f %f %f %f %d %f \n", bar.getX(), bar.getY(), bar.getWidth(), bar.getHeight(), seriesIdx, barHeight);
						if (isZeroBased) {
							bar.setFrame(bar.getX(), bar.getY(), bar.getWidth(), bar.getHeight());
						} else {
							int seriesIdx = (row/3) +1;
							double barHeight = bar.getHeight()/seriesIdx ;
							bar.setFrame(bar.getX(), bar.getY(), bar.getWidth(), barHeight);
						}
			       
			            g2.setColor((Color) renderer.getSeriesPaint(row));
			            g2.fill(bar);
			            g2.draw(bar);
					}
				});
				
				TimeSeries lineSerie = ((TimeSeriesCollection) mainPlot.getDataset(0)).getSeries(lineSerieIdx);
				double maxBarValue = barChartDisplayStrategy.maxBarValue(lineSerie);
				int eventDefSerieIdx = 0;
				final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy");
				final NumberFormat percentInstance = new DecimalFormat("#0.00 %");
				for (final DataSetBarDescr serieDef : barSeries.keySet()) {

					TimeSeries barDataSet = barChartDisplayStrategy.buildBarTimeSeries(serieDef.getSerieName(), barSeries.get(serieDef), lineSerie);
					barDataSets.addSeries(barDataSet);

					//Annotation
					if (serieDef.isLabeled()) {
						RegularTimePeriod annTP = lineSerie.getTimePeriod(Math.min(lineSerie.getItemCount() - 1, 0));
						Double annV = maxBarValue * eventDefSerieIdx / barSeries.size();
						XYTextAnnotation annotation = new XYTextAnnotation(serieDef.getEventDisplayeDef() + " (" + percentInstance.format(serieDef.getProfit())+ ")", annTP.getFirstMillisecond(), annV);
						annotation.setTextAnchor(TextAnchor.BASELINE_LEFT);
						annotation.setToolTipText("<html>" + serieDef.getEventDisplayeDef() + "<br>" + serieDef.getTuningResStr() + "</html>");
						annotation.setPaint(Color.GRAY);
						renderer.addAnnotation(annotation);
					}

					//ToolTip
					XYToolTipGenerator xyToolTpGen = new XYToolTipGenerator() {

						public String generateToolTip(XYDataset dataset, int series, int item) {

							String x = "NaN";
							EventType type = EventType.NONE;
							String desrc = "Indeterministic simultaneous BULLISH and BEARISH";
							try {

								Date date = new Date((long) dataset.getXValue(series, item));
								x = simpleDateFormat.format(date);

								switch (serieDef.getId() % 3) {
								case 0:
									type = EventType.BULLISH;
									desrc = serieDef.getEventDefDescriptor().getHtmlBullishDescription();
									break;
								case 2:
									type = EventType.BEARISH;
									desrc = serieDef.getEventDefDescriptor().getHtmlBearishDescription();
									break;
								default:
									break;
								}

								return "<html>" + "<font size='2'>" + "<b>" + serieDef.getStockDescr() + "</b><br>" + "<b>" + serieDef.getEventDisplayeDef()
										+ "</b> on the " + x + "<br>" + "Trend&nbsp;&nbsp;&nbsp;: " + type + "<br>" + "Descr&nbsp;&nbsp;&nbsp;: " + desrc
										+ "<br>" + "</font>" + "</html>";

							} catch (Exception e) {
								LOGGER.debug(e, e);
							}
							return "NaN";

						}
					};
					
					renderer.setSeriesToolTipGenerator(eventDefSerieIdx, xyToolTpGen);
					renderer.setSeriesItemLabelsVisible(eventDefSerieIdx, true);
					renderer.setSeriesStroke(eventDefSerieIdx, new BasicStroke(serieDef.getSerieStrokeSize()));
					renderer.setSeriesOutlinePaint(eventDefSerieIdx, serieDef.getSerieColor());
					renderer.setSeriesPaint(eventDefSerieIdx, serieDef.getSerieColor());
					renderer.setSeriesFillPaint(eventDefSerieIdx, serieDef.getSerieColor());
		
					eventDefSerieIdx++;
					
				}
				
				mainPlot.setDataset(1, barDataSets);
			}
			
		};
		
		EventQueue.invokeLater(runnable);
		
	}

	/**
	 * @param listShares 
	 * @param stripedCloseFunction 
	 * @param applyColors 
	 * @param chartPanelComponent
	 */
	public void updateLineDataSet(final List<SlidingPortfolioShare> listShares, final StripedCloseFunction stripedCloseFunction, final Boolean applyColors) {

		Runnable runnable = new Runnable() {
			
			public void run() {
				XYDataset dataSet = buildLineDataSet(stripedCloseFunction, listShares, applyColors);
				Date arbitraryStartDate = stripedCloseFunction.getArbitraryStartDate();
				Date arbitraryEndDate = stripedCloseFunction.getArbitraryEndDate();
				xAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, domainTicksMultiple(arbitraryStartDate, arbitraryEndDate)));
				try {
					mainPlot.setDataset(dataSet);
				} catch (IllegalArgumentException e) {
					LOGGER.warn(e, e);
					resetBarChart();
					resetIndicChart();
					updateLineDataSet(listShares, stripedCloseFunction, applyColors);
				}
			}
			
		};
		
		EventQueue.invokeLater(runnable);
		
	}
	
	public void updateIndicDataSet(final EventInfo chartedEvtDef, final SortedMap<Date, double[]> serie) throws NoSuchElementException {

		Runnable runnable = new Runnable() {
			
			public void run() {
				
				final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy");
				final NumberFormat numberFormat = new DecimalFormat("0.000000");
				CombinedDomainXYPlot combinedDomainXYPlot = (CombinedDomainXYPlot) jFreeChart.getXYPlot();
				
				//Check if indic plot present
				@SuppressWarnings("rawtypes")
				List subplots = combinedDomainXYPlot.getSubplots();
				if (subplots.size() == 1) {
					XYLineAndShapeRenderer indicRenderer = new XYLineAndShapeRenderer(true, false);
					indicRenderer.setBaseItemLabelsVisible(true, false);

					indicPlot = new XYPlot(null, null, null, indicRenderer);
					indicPlot.addRangeMarker(0, new ValueMarker(0), Layer.FOREGROUND, false);
					indicPlot.setNoDataMessage("No indicator output is available. Check that the stocks and date ranges are valid.");
					indicPlot.setDomainMinorGridlinesVisible(true);
				}
				indicPlot.clearRangeAxes();
				
				final EventDefDescriptor eventDefDescriptor = chartedEvtDef.getEventDefDescriptor();
				String[] eventDefDescriptorArray = new String[0];
				if (eventDefDescriptor != null) {
					eventDefDescriptorArray = eventDefDescriptor.descriptionArray();
				}
				
				for (int groupIdx = 0; groupIdx < eventDefDescriptor.getGroupsCount(); groupIdx++) {

					//Build and add Data set for group
					XYItemRenderer renderer = indicPlot.getRenderer(groupIdx);
					if (renderer == null) {
						renderer = new XYLineAndShapeRenderer(true, false);
						renderer.setBaseItemLabelsVisible(true, false);
						indicPlot.setRenderer(groupIdx, renderer);
					}
					ValueAxis rangeAxis = indicPlot.getRangeAxis(groupIdx);
					if (rangeAxis == null) {
						rangeAxis = initYAxis();
						if (groupIdx == 0) {
							rangeAxis.setLabel(chartedEvtDef.getEventReadableDef());
						} else {
							rangeAxis.setVisible(false);
						}
						indicPlot.setRangeAxis(groupIdx, rangeAxis);
						indicPlot.mapDatasetToRangeAxis(groupIdx, groupIdx);
					}
					TimeSeriesCollection dataset = new TimeSeriesCollection();

					Integer[] outputIndexes = eventDefDescriptor.getIndexesForGroup(groupIdx);
					int serieIdx = 0;
					for (int k = 0; k < outputIndexes.length; k++) {

						int outputIdx = outputIndexes[k];
						final String domain = eventDefDescriptorArray[outputIdx];
						TimeSeries timeSerie = new TimeSeries(domain);
						Boolean isNotNan = false;
						for (Date date : serie.keySet()) {
							double[] ds = serie.get(date);
							RegularTimePeriod period = new Day(date);
							Number value = ds[outputIdx];
							isNotNan = isNotNan || (!((Double) value).isNaN() && !((Double) value).isInfinite());
							//Double.NEGATIVE_INFINITY act as a marker for data not available but line still have to be drawn.
							if (!((Double) value).isInfinite()) {
								TimeSeriesDataItem item = new TimeSeriesDataItem(period, value);
								timeSerie.add(item, false);
							}
						}
						if (isNotNan) {

							dataset.addSeries(timeSerie);

							renderer.setSeriesPaint(serieIdx, eventDefDescriptor.getColor(outputIdx));
							renderer.setSeriesShape(serieIdx, new Rectangle(new Dimension(100, 100)));

							XYToolTipGenerator xyToolTpGen = new XYToolTipGenerator() {

								public String generateToolTip(XYDataset dataset, int series, int item) {

									String y = "NaN";
									String x = "NaN";
									try {
										y = numberFormat.format(dataset.getYValue(series, item));
										Date date = new Date((long) dataset.getXValue(series, item));
										x = simpleDateFormat.format(date);
										return "<html>" + "<font size='2'>" + "<b>" + domain + "</b> on the " + x + "<br>"
												+ ((eventDefDescriptor.displayValues()) ? "Value : " + y : "") + "</font>" + "</html>";
									} catch (Exception e) {
										LOGGER.debug(e, e);
									}
									return "NaN";

								}
							};

							renderer.setSeriesToolTipGenerator(serieIdx, xyToolTpGen);
							serieIdx++;
						}

					}

					indicPlot.setDataset(groupIdx, dataset);
				}
				//Combine
				if (subplots.size() == 1) {
					combinedDomainXYPlot.add(indicPlot, 1);
				}
			}
			
		};
		
		EventQueue.invokeLater(runnable);

	}

	/**
	 * @param stripedCloseFunction
	 * @param k
	 * @return
	 */
	private Quotations getQuotations(StripedCloseFunction stripedCloseFunction, PortfolioShare portfolioShare) {
		
		Date startDate = stripedCloseFunction.getArbitraryStartDate();
		Date endDate = stripedCloseFunction.getArbitraryEndDate();
		Quotations bdQuotes;
		try {
			bdQuotes = QuotationsFactories.getFactory().getQuotationsInstance(portfolioShare.getStock(),startDate,endDate,true, portfolioShare.getTransactionCurrency(),0,0);
		} catch (NoQuotationsException e) {
			throw new RuntimeException(e);
		}
		return bdQuotes;
		
	}


	private TimeSeries buildLineSeries(StripedCloseFunction stripedCloseFunction, Quotations bdQuotes, SlidingPortfolioShare portfolioShare) {

		TimeSeries timeSeries = new TimeSeries(portfolioShare.getName());
		
		if (bdQuotes.size() > 0 && portfolioShare.getStock().getLastQuote().after(stripedCloseFunction.getArbitraryStartDate())) {
			
			stripedCloseFunction.targetShareData(portfolioShare, bdQuotes);
			List<QuotationUnit> quotationUnits = bdQuotes.getQuotationUnits(stripedCloseFunction.getStartDateQuotationIndex(), stripedCloseFunction.getEndDateQuotationIndex());
			
			Number[] relativeCloses = stripedCloseFunction.relativeCloses();
			for (int i = 0; i < Math.min(relativeCloses.length, stripedCloseFunction.getEndDateQuotationIndex()); i++) {
				QuotationUnit trade = quotationUnits.get(i+stripedCloseFunction.getStartDateQuotationIndex());
				RegularTimePeriod period = new Day(trade.getDate());
				Number value = relativeCloses[i].doubleValue();
				timeSeries.add(new TimeSeriesDataItem(period, value));
			}
			
		}  else {
			
			portfolioShare.setDisplayOnChart(false);
			
		}
		
		return timeSeries;
		
	}


	/**
	 * @param highlightStroke 
	 * @param lineRenderer
	 */
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
			fos.flush();
		} catch (IOException e) {
			LOGGER.error("", e);
		}
		
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
				if (indicPlot != null && ((CombinedDomainXYPlot) jFreeChart.getXYPlot()).getSubplots().size() == 2) {
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
		EventQueue.invokeLater(runnable);
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
		
		ChartUtilities.writeChartAsPNG(new FileOutputStream(new File(path)), this.jFreeChart, 700, 600);
		
	}	
}
