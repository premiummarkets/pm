/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.CombinedDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.XYDataset;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.StripedCloseFunction;
import com.finance.pms.datasources.db.StripedCloseIndexRelative;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;



// TODO: Auto-generated Javadoc
/**
 * The Class Charts.
 * 
 * @author Guillaume Thoreton
 */
public class Charts {
	
	private static MyLogger LOGGER = MyLogger.getLogger(StripedCloseIndexRelative.class);
	
	/** The Constant DATE_FORMAT. */
	public static final DateFormat DATE_FORMAT = DateFormat.getTimeInstance();
	
	/** The Constant PERCENTAGE_FORMAT. */
	public static final DecimalFormat PERCENTAGE_FORMAT = new DecimalFormat("#0.00 %");
	
	/** The j free time period. */
	private JFreeChartTimePeriod jFreeTimePeriod;
	
	private JFreeChart jFreeChart;

	
	/**
	 * Instantiates a new charts.
	 * 
	 * @param underLyingPortfolioShare the portfolio share
	 * @param displayedUnits the displayed units
	 * @param jFreeTimePeriod the j free time period
	 * 
	 * @author Guillaume Thoreton
	 */
	public Charts(Date startDate, JFreeChartTimePeriod jFreeTimePeriod) {
		super();
		this.jFreeTimePeriod = jFreeTimePeriod;
		
	}
	
	private XYDataset getDataSet(StripedCloseFunction stripedCloseFunction, List<SlidingPortfolioShare> portfolioShares) {
		
		CombinedDataset combinedDataset = new CombinedDataset();
		for (int k=0; k< portfolioShares.size(); k++) {
			
			Quotations quotations = getQuotations(stripedCloseFunction, portfolioShares.get(k));
			combinedDataset.add(buildDataSet(stripedCloseFunction, quotations, portfolioShares.get(k)));

			org.eclipse.swt.graphics.Color color = portfolioShares.get(k).getColor();
			Paint paint = new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue());
			jFreeChart.getXYPlot().getRenderer().setSeriesPaint(k, paint);
			jFreeChart.getXYPlot().getRenderer().setSeriesStroke(k, new BasicStroke(1));
			
		}
		return combinedDataset;
	}

	/**
	 * Inits the chart.
	 * 
	 * @param stripedCloseFunction the striped close function
	 * 
	 * @return the j free chart
	 * 
	 * @author Guillaume Thoreton
	 * @param portfolioShares 
	 */
	public JFreeChart initChart(StripedCloseFunction stripedCloseFunction, List<SlidingPortfolioShare> portfolioShares) {

		DateAxis xAxis = new DateAxis();
		xAxis.setTimeline(tradingTimeLine());
		verticalAxisSetup(xAxis);
		
		if (jFreeTimePeriod.equals(JFreeChartTimePeriod.DAY)) xAxis.setDateFormatOverride(new SimpleDateFormat("dd/MMM/yy"));
		
		CombinedDataset combinedDataset = new CombinedDataset();
	    XYLineAndShapeRenderer lineRenderer = new XYLineAndShapeRenderer(true, false);
	 	Calendar  yearAgo  = Calendar.getInstance();
    	yearAgo.add(Calendar.YEAR, -1);
		lineRenderer.setBaseItemLabelsVisible(true);
		
		for (int k=0; k< portfolioShares.size(); k++) {
			
	    	Quotations bdQuotes = getQuotations(stripedCloseFunction, portfolioShares.get(k));
	    	
	    	combinedDataset.add(buildDataSet(stripedCloseFunction, bdQuotes, portfolioShares.get(k)));
			setUpLineRenderer(bdQuotes, lineRenderer, k, portfolioShares.get(k));
			
			org.eclipse.swt.graphics.Color color = portfolioShares.get(k).getColor();
			Paint paint = new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue());
			lineRenderer.setSeriesPaint(k, paint, true);
		
			LOGGER.trace("Setting colors : "+portfolioShares.get(k).getSymbol()+", color "+paint+"\n");
		}
	
		
		NumberAxis yAxis = new NumberAxis();	    
		XYPlot combinedDomainXYPlot = new XYPlot(combinedDataset, xAxis, yAxis, lineRenderer);
		
		setYAxisRange(combinedDomainXYPlot);
		
	    JFreeChart chart = new JFreeChart(combinedDomainXYPlot);
	    chart.removeLegend();
	    
	    this.jFreeChart = chart;
	    
	    return chart;
	  }

	/**
	 * @param yAxis
	 * @param combinedDomainXYPlot
	 */
	private ValueAxis setYAxisRange(XYPlot combinedDomainXYPlot) {
		combinedDomainXYPlot.getRangeAxis().setAutoRange(true);
		
		((NumberAxis)combinedDomainXYPlot.getRangeAxis()).setAutoRangeIncludesZero(false);
		((NumberAxis)combinedDomainXYPlot.getRangeAxis()).setNumberFormatOverride(PERCENTAGE_FORMAT);
		combinedDomainXYPlot.addRangeMarker(new ValueMarker(0));
		return combinedDomainXYPlot.getRangeAxis();
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
			bdQuotes = QuotationsFactories.getFactory().getQuotationsInstance(portfolioShare.getStock(),startDate,endDate,true, portfolioShare.getTransactionCurrency(),0);
		} catch (NoQuotationsException e) {
			throw new RuntimeException(e);
		}
		return bdQuotes;
	}

	/**
	 * @param bdQuotes
	 * @param lineRenderer
	 * @param serie
	 */
	private void setUpLineRenderer(final Quotations bdQuotes, XYLineAndShapeRenderer lineRenderer,final int serie, final PortfolioShare portfolioShares) {
		
		lineRenderer.setSeriesToolTipGenerator(serie, new XYToolTipGenerator() {
			
			public String generateToolTip(XYDataset dataset, int series, int item) {
				BigDecimal y = BigDecimal.ZERO;
				String x = "Nan";
				BigDecimal closeForDate = BigDecimal.ZERO;
				try {
					y = new BigDecimal(100*dataset.getYValue(series, item)).setScale(2,BigDecimal.ROUND_DOWN);
					Date date = new Date((long)dataset.getXValue(series, item));
					x = new SimpleDateFormat("dd/MM/yyyy").format(date);
					try {
						closeForDate = bdQuotes.getCloseForDate(date);
					} catch (InvalidAlgorithmParameterException e) {
						LOGGER.warn(e,e);
					}
				} catch (Exception e) {
					LOGGER.error(e,e);
				}
				return portfolioShares.getName() +" : " + x +" "+closeForDate+" "+ y +"%" ;
			}
		});
		lineRenderer.setSeriesShape(serie, new Rectangle(new Dimension(30,30)), true);
	}

	/**
	 * @param stripedCloseFunction
	 * @param combinedDataset
	 * @param bdQuotes
	 * @param k
	 */
	private SeriesOHLCDataset buildDataSet(StripedCloseFunction stripedCloseFunction, Quotations bdQuotes, PortfolioShare portfolioShare) {
		ArrayList<OHLCDataItem> ohlcList  = new ArrayList<OHLCDataItem>();
		if (bdQuotes.size() > 0 && portfolioShare.getStock().getLastQuote().after(stripedCloseFunction.getArbitraryStartDate())) {
			stripedCloseFunction.targetShareData(portfolioShare, bdQuotes);
			List<QuotationUnit> quotationUnits = bdQuotes.getQuotationUnits(stripedCloseFunction.getStartDateQuotationIndex(), stripedCloseFunction.getEndDateQuotationIndex());
			Number[] relativeCloses = stripedCloseFunction.relativeCloses();
			ohlcList = this.builtOHLCList(quotationUnits, relativeCloses, stripedCloseFunction.getStartDateQuotationIndex(), stripedCloseFunction.getEndDateQuotationIndex());
		}
		
		return new SeriesOHLCDataset(portfolioShare, ohlcList);
	}

	/**
	 * @param dateAxis
	 */
	private void verticalAxisSetup(DateAxis dateAxis) {
		dateAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY,7));
		dateAxis.setVerticalTickLabels(true);
	}

	private ArrayList<OHLCDataItem> builtOHLCList(List<QuotationUnit> quotationUnits, Number[] relativeCloses, Integer startIdx, Integer endIdx) {
		
		 ArrayList<OHLCDataItem> ret = new ArrayList<OHLCDataItem>();

		 for (int i = 0; i < Math.min(relativeCloses.length, endIdx); i++) {
			 QuotationUnit trade = quotationUnits.get(i+startIdx);
			 OHLCDataItem ohlcDataItem =  new OHLCDataItem(
						 trade.getDate(), 
						 trade.getOpen().doubleValue(), trade.getHigh().doubleValue(), trade.getLow().doubleValue(), relativeCloses[i].doubleValue(), 
						 new Double(trade.getVolume()));
			 ret.add(ohlcDataItem);
		 }
	   
		return ret;
	}

	/**
	 * @param lineRenderer
	 */
	public void highLightSerie(Integer serie) {
		
		for (int i= 0; i < ((XYPlot)jFreeChart.getPlot()).getSeriesCount(); i++) {
			jFreeChart.getXYPlot().getRenderer().setSeriesStroke(i, new BasicStroke(1));
		}
		
		Stroke stroke = new BasicStroke(3);
		jFreeChart.getXYPlot().getRenderer().setSeriesStroke(serie, stroke);
		
	}
	
		
  /**
   * Trading time line.
   * 
   * @return the segmented timeline
   * 
   * @author Guillaume Thoreton
   */
  private SegmentedTimeline tradingTimeLine() {
		SegmentedTimeline timeline = new SegmentedTimeline(SegmentedTimeline.DAY_SEGMENT_SIZE,7,0);
		timeline.setStartTime(SegmentedTimeline.firstMondayAfter1900());
		return timeline;
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

	/**
	 * @param listShares 
	 * @param stripedCloseFunction 
	 * @param chartPanelComponent
	 */
	public void updateDataSet(List<SlidingPortfolioShare> listShares, StripedCloseFunction stripedCloseFunction) {

		XYPlot plot = (XYPlot) jFreeChart.getPlot();
		XYDataset dataSet = getDataSet(stripedCloseFunction, listShares);
		plot.setDataset(dataSet);
		
	}
	
	
}
