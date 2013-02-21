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
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import javax.swing.ToolTipManager;
import javax.swing.UIManager;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.Layer;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.StripedCloseFunction;
import com.finance.pms.datasources.db.StripedCloseIndexRelative;
import com.finance.pms.events.EventDefDescriptor;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventType;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.DataSetBarDescr;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;

/**
 * The Class Charts.
 * 
 * @author Guillaume Thoreton
 */
public class ChartMain extends Chart {
	
	private static MyLogger LOGGER = MyLogger.getLogger(StripedCloseIndexRelative.class);
	
	public static final DateFormat DATE_FORMAT = DateFormat.getTimeInstance();
	public static final DecimalFormat PERCENTAGE_FORMAT = new DecimalFormat("#0.00 %");
	public static Color[] colors = new Color[] {Color.BLACK,Color.ORANGE,Color.GREEN,Color.RED,Color.YELLOW,Color.BLUE};

	private JFreeChartTimePeriod jFreeTimePeriod;
	private JFreeChart jFreeChart;

	private DateAxis xAxis;
	private NumberAxis mainYAxis;

	private XYPlot mainPlot;
	private XYPlot indicPlot;

	private NumberAxis indicYAxis;

	
	public ChartMain(Date startDate, JFreeChartTimePeriod jFreeTimePeriod) {
		super();
		this.jFreeTimePeriod = jFreeTimePeriod;
		UIManager.put("ToolTip.background", new java.awt.Color(239, 203, 152, 255));
		UIManager.put("ToolTip.foreground", java.awt.Color.BLACK);
		
		ToolTipManager.sharedInstance().setInitialDelay(0);
		ToolTipManager.sharedInstance().setDismissDelay(60000);
		
		XYBarRenderer.setDefaultShadowsVisible(false);
		XYBarRenderer.setDefaultBarPainter(new StandardXYBarPainter());
		
	}
	
	private XYDataset buildLineDataSet(final StripedCloseFunction stripedCloseFunction, final List<SlidingPortfolioShare> portfolioShares) {

		TimeSeriesCollection combinedDataset = new TimeSeriesCollection();

		for (int k=0; k< portfolioShares.size(); k++) {

			final Quotations quotations = getQuotations(stripedCloseFunction, portfolioShares.get(k));

			TimeSeries lineSerie = buildLineSeries(stripedCloseFunction, quotations, portfolioShares.get(k));

			combinedDataset.addSeries(lineSerie);

			org.eclipse.swt.graphics.Color color = portfolioShares.get(k).getColor();
			Paint paint = new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue());
			final XYItemRenderer renderer = mainPlot.getRenderer();

			renderer.setSeriesPaint(k, paint);
			renderer.setSeriesStroke(k, new BasicStroke(1));

			final int kf = k;
			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy");
			final NumberFormat percentInstance = NumberFormat.getPercentInstance();
			XYToolTipGenerator xyToolTpGen = new XYToolTipGenerator() {

				public String generateToolTip(XYDataset dataset, int series, int item) {

					String y = "NaN";
					String x = "NaN";
					QuotationUnit closeForDate;
					try {
						y =  percentInstance.format(dataset.getYValue(series, item));
						Date date = new Date((long) dataset.getXValue(series, item));
						x = simpleDateFormat.format(date);
						closeForDate = quotations.get(quotations.getClosestIndexForDate(0, date));
						return "<html>"+
						"<b>"+ portfolioShares.get(kf).getSymbol() +"</b> on the " + x +"<br>" +
						"Open&nbsp;&nbsp;&nbsp;: "+closeForDate.getOpen()+"<br>" +
						"High&nbsp;&nbsp;&nbsp;: "+closeForDate.getHigh()+"<br>" +
						"Low&nbsp;&nbsp;&nbsp;&nbsp;: "+closeForDate.getLow()+"<br>" +
						"Close&nbsp;&nbsp;: "+closeForDate.getClose()+"<br>" +
						"Volume : "+closeForDate.getVolume()+"<br>" +
						"Variation : "+ y + " " + stripedCloseFunction.lineToolTip() +
						"</html>" ;
					} catch (Exception e) {
						LOGGER.debug(e,e);
					}
					return "NaN";

				}
			};

			renderer.setSeriesToolTipGenerator(k, xyToolTpGen);
			renderer.setSeriesShape(k, new Rectangle(new Dimension(100,100)));
			Boolean displayOnCahrt = (lineSerie.getItemCount() == 0)?false:portfolioShares.get(k).getDisplayOnChart();
			renderer.setSeriesVisible(k, displayOnCahrt);

		}

		return combinedDataset;
	}


	public JFreeChart initChart(StripedCloseFunction stripedCloseFunction) {
		
		Date arbitraryStartDate = stripedCloseFunction.getArbitraryStartDate();
		Date arbitraryEndDate = stripedCloseFunction.getArbitraryEndDate();

		xAxis = new DateAxis();
		xAxis.setAutoRange(true);
		xAxis.setTimeline(tradingTimeLine());
		xAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, domainTicksMultiple(arbitraryStartDate, arbitraryEndDate)));
		if (jFreeTimePeriod.equals(JFreeChartTimePeriod.DAY)) xAxis.setDateFormatOverride(new SimpleDateFormat("dd MMM yy"));
		
		mainYAxis = new NumberAxis();	 
		mainYAxis.setAutoRange(true);
		mainYAxis.setAutoRangeIncludesZero(false);
		mainYAxis.setNumberFormatOverride(PERCENTAGE_FORMAT);
		mainYAxis.setTickLabelFont(mainYAxis.getTickLabelFont().deriveFont(8f));
		
		//Main plot
		//lines
	    XYLineAndShapeRenderer lineRenderer = new XYLineAndShapeRenderer(true, false);
		lineRenderer.setBaseItemLabelsVisible(true);	
		
		TimeSeriesCollection lineDataset = new TimeSeriesCollection();
		mainPlot = new XYPlot(lineDataset, null, mainYAxis, lineRenderer);
		mainPlot.addRangeMarker(new ValueMarker(0));
		
		//bars
		TimeSeriesCollection barDataset = new TimeSeriesCollection();
		XYBarRenderer barRenderer = new XYBarRenderer();
		mainPlot.setDataset(1, barDataset);
		mainPlot.setRenderer(1, barRenderer);
		
		NumberAxis barYAxis = new NumberAxis();
		barYAxis.setRange(new Range(0, 100));
		barYAxis.setVisible(false);
		mainPlot.setRangeAxis(1, barYAxis);
		mainPlot.setNoDataMessage("No data available. Check that the stocks and date ranges are valid. There may not be quotations available for this operation.");
		
		//Indicator Y axis (indicator is not showing at init)
		indicYAxis = new NumberAxis();	 
		indicYAxis.setAutoRange(true);
		indicYAxis.setAutoRangeIncludesZero(false);
		indicYAxis.setTickLabelFont(indicYAxis.getTickLabelFont().deriveFont(7f));
		indicYAxis.setLabelFont(indicYAxis.getLabelFont().deriveFont(10f));

		
		//Combine
		CombinedDomainXYPlot combinedDomainXYPlot = new CombinedDomainXYPlot(xAxis);
		combinedDomainXYPlot.add(mainPlot, 3);
		
	
		//Create
		this.jFreeChart = new JFreeChart(combinedDomainXYPlot);
	    this.jFreeChart.removeLegend();
	    
	    return this.jFreeChart;
	  }


	private TimeSeries buildBarTimeSeries(String name, SortedMap<Date,Double> barSerie) {
		
		TimeSeries timeSerie = new TimeSeries(name);
		for (Date date : barSerie.keySet()) {
			RegularTimePeriod period = new Day(date);
			Number value = barSerie.get(date);
			TimeSeriesDataItem item = new TimeSeriesDataItem(period, value);
			timeSerie.add(item, false);
		}
		
		return timeSerie;
	}
	
	
	public void updateBarDataSet(final SortedMap<DataSetBarDescr, SortedMap<Date, Double>> barSeries) {
		
		TimeSeriesCollection barDataSets = new TimeSeriesCollection();
		
		int seriesIdx = 0;
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy");
		for (final DataSetBarDescr serieDef : barSeries.keySet()) {
			
			TimeSeries barDataSet = buildBarTimeSeries(serieDef.getSerieName(), barSeries.get(serieDef));
			barDataSets.addSeries(barDataSet);
			
			XYBarRenderer renderer = (XYBarRenderer) mainPlot.getRenderer(1);
			
			String[] serieName = serieDef.getSerieName().split(" ");
			final EventDefinition valueOf = EventDefinition.valueOf(serieName[0]);
			XYItemLabelGenerator generator = new XYItemLabelGenerator() {
				
				@Override
				public String generateLabel(XYDataset dataset, int series, int item) {
					
					if (item == (dataset.getItemCount(series)/3)+1) {
						if (serieDef.isLabeled()) return valueOf.getEventDef();
					}
					
					return null;

				}
				
			};
			renderer.setSeriesItemLabelGenerator(seriesIdx, generator);
			
			XYToolTipGenerator xyToolTpGen = new XYToolTipGenerator() {

				public String generateToolTip(XYDataset dataset, int series, int item) {
					
					String x = "NaN";
					try {
						Date date = new Date((long) dataset.getXValue(series, item));
						x = simpleDateFormat.format(date);
						
						return "<html>"+
						"<b>"+serieDef.getOther()+"</b><br>" +
						"<b>"+valueOf.getEventDef()+"</b> on the " + x +"<br>" +
						"Trend&nbsp;&nbsp;&nbsp;: "+((serieDef.getId() % 2 == 0)?EventType.BULLISH:EventType.BEARISH)+"<br>" +
						"Descr&nbsp;&nbsp;&nbsp;: "+((serieDef.getId() % 2 == 0)?valueOf.getEventDefDescriptor().getHtmlBullishDescription():valueOf.getEventDefDescriptor().getHtmlBearishDescription())+"<br>" +
						"</html>" ;
					} catch (Exception e) {
						LOGGER.debug(e,e);
					}
					return "NaN";

				}
			};
			renderer.setSeriesToolTipGenerator(seriesIdx, xyToolTpGen);
			
			renderer.setSeriesItemLabelsVisible(seriesIdx, true);
			renderer.setSeriesStroke(seriesIdx, new BasicStroke(serieDef.getSerieSize()));
			renderer.setSeriesPaint(seriesIdx, serieDef.getSerieColor());
			renderer.setSeriesFillPaint(seriesIdx, serieDef.getSerieColor());
			
			seriesIdx++;
		}
		
		mainPlot.setDataset(1,barDataSets);
		
	}
	
	/**
	 * @param listShares 
	 * @param stripedCloseFunction 
	 * @param chartPanelComponent
	 */
	public void updateLineDataSet(List<SlidingPortfolioShare> listShares, StripedCloseFunction stripedCloseFunction) {

		XYDataset dataSet = buildLineDataSet(stripedCloseFunction, listShares);
		Date arbitraryStartDate = stripedCloseFunction.getArbitraryStartDate();
		Date arbitraryEndDate = stripedCloseFunction.getArbitraryEndDate();
		xAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, domainTicksMultiple(arbitraryStartDate,arbitraryEndDate)));
		try {
			mainPlot.setDataset(dataSet);
		} catch (IllegalArgumentException e) {
			LOGGER.warn(e,e);
			resetBarChart();
			resetIndicChart();
			updateLineDataSet(listShares, stripedCloseFunction);
		}
		
	}
	
	public void updateIndicDataSet(EventDefinition chartedEvtDef, SortedMap<Date, double[]> serie) {

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy");
		final NumberFormat numberFormat = NumberFormat.getInstance();
		
		CombinedDomainXYPlot combinedDomainXYPlot = (CombinedDomainXYPlot)jFreeChart.getXYPlot();
		
		//Check if indic plot present
		@SuppressWarnings("rawtypes")
		List subplots = combinedDomainXYPlot.getSubplots();
		if (subplots.size() == 1) {
			XYLineAndShapeRenderer indicRenderer = new XYLineAndShapeRenderer(true, false);
			indicRenderer.setBaseItemLabelsVisible(true, false);	

			indicPlot = new XYPlot(null, null, indicYAxis, indicRenderer);
			indicPlot.addRangeMarker(0, new ValueMarker(0), Layer.FOREGROUND, false);
			indicPlot.setNoDataMessage("No indicator output is available. Check that the stocks and date ranges are valid.");
		}

		//Build and add Data set
		XYItemRenderer renderer = indicPlot.getRenderer();
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		EventDefDescriptor eventDefDescriptor = chartedEvtDef.getEventDefDescriptor();
		String[] eventDefDescriptorArray = new String[0];
		if (eventDefDescriptor != null) {
			eventDefDescriptorArray = eventDefDescriptor.descriptionArray();
		}

		int validDomainCpt = 0;
		for (int i = 0; i < eventDefDescriptorArray.length; i++) {
			if (eventDefDescriptorArray[i] != null) {

				final String domain = eventDefDescriptorArray[i];
				TimeSeries timeSerie = new TimeSeries(domain);

				for (Date date : serie.keySet()) {
					double[] ds = serie.get(date);
					RegularTimePeriod period = new Day(date);
					Number value = ds[validDomainCpt];
					TimeSeriesDataItem item = new TimeSeriesDataItem(period, value);
					timeSerie.add(item, false);
				}

				dataset.addSeries(timeSerie);

				renderer.setSeriesPaint(validDomainCpt, colors[i]);
				renderer.setSeriesShape(validDomainCpt, new Rectangle(new Dimension(100,100)));

				XYToolTipGenerator xyToolTpGen = new XYToolTipGenerator() {

					public String generateToolTip(XYDataset dataset, int series, int item) {

						String y = "NaN";
						String x = "NaN";
						try {
							y =  numberFormat.format(dataset.getYValue(series, item));
							Date date = new Date((long) dataset.getXValue(series, item));
							x = simpleDateFormat.format(date);
							return 
									"<html>"+
									"<b>"+  domain +"</b> on the " + x +"<br>" +
									"Value : "+ y  +
									"</html>";
						} catch (Exception e) {
							LOGGER.debug(e,e);
						}
						return "NaN";

					}
				};
				renderer.setSeriesToolTipGenerator(validDomainCpt, xyToolTpGen);

				validDomainCpt++;

			}
		}

		indicPlot.setDataset(dataset);
		indicYAxis.setLabel(chartedEvtDef.getEventDef());

		//Combine
		if (subplots.size() == 1) {
			combinedDomainXYPlot.add(indicPlot, 1);
		}

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
	 * @param lineRenderer
	 */
	public void highLightSerie(Integer serie) {
		
		for (int i= 0; i < mainPlot.getSeriesCount(); i++) {
			mainPlot.getRenderer().setSeriesStroke(i, new BasicStroke(1));
		}
		
		Stroke stroke = new BasicStroke(3);
		mainPlot.getRenderer().setSeriesStroke(serie, stroke);
		
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


	
	public double getMainYAxisMax() {
		return mainYAxis.getRange().getUpperBound();
	}

	public void resetBarChart() {
		mainPlot.setDataset(1, new TimeSeriesCollection());
		
	}
	
	public void resetIndicChart() {
		if (indicPlot != null && ((CombinedDomainXYPlot)jFreeChart.getXYPlot()).getSubplots().size() == 2) {
			((CombinedDomainXYPlot)jFreeChart.getXYPlot()).remove(indicPlot);
			indicPlot = null;
		}
	}

	
	
}
