package com.finance.pms.portfolio.gui.charts;

import java.awt.Color;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedMap;

import javax.swing.ToolTipManager;
import javax.swing.UIManager;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.xy.XYDataset;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventDefinition;

public class ChartIndicator extends Chart {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ChartIndicator.class);
	
//	String mainIndicator;
//	String bearishIndicator;
//	String bullishIndicator;
//	String signalLine;
//	String lowerThreshold;
//	String upperThreshold;
	public static Color[] colors = new Color[] {Color.BLACK,Color.ORANGE,Color.GREEN,Color.RED,Color.YELLOW,Color.BLUE};
	
	
	private JFreeChart jFreeChart;

	public ChartIndicator() {
		
		UIManager.put("ToolTip.background", new java.awt.Color(239, 203, 152, 255));
		UIManager.put("ToolTip.foreground", java.awt.Color.BLACK);
		
		ToolTipManager.sharedInstance().setInitialDelay(0);
		ToolTipManager.sharedInstance().setDismissDelay(60000);
		
		XYBarRenderer.setDefaultShadowsVisible(false);
		XYBarRenderer.setDefaultBarPainter(new StandardXYBarPainter());
	}

	public JFreeChart initChart(Date slidingStartDate, Date slidingEndDate) {
		
		DateAxis xAxis = new DateAxis();
		xAxis.setTimeline(tradingTimeLine());
		xAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, domainTicksMultiple(slidingStartDate, slidingEndDate)));
		xAxis.setDateFormatOverride(new SimpleDateFormat("dd MMM yy"));
		
		NumberAxis yAxis = new NumberAxis();	 
		yAxis.setAutoRange(true);
		yAxis.setAutoRangeIncludesZero(false);

	    XYLineAndShapeRenderer lineRenderer = new XYLineAndShapeRenderer(true, false);
		lineRenderer.setBaseItemLabelsVisible(true);	
		
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		XYPlot plot = new XYPlot(dataset, xAxis, yAxis, lineRenderer);
		plot.addRangeMarker(new ValueMarker(0));
	
		//create
		this.jFreeChart = new JFreeChart(plot);
	    this.jFreeChart.removeLegend();
	    
	    return this.jFreeChart;
	}

	public void updateChart(EventDefinition chartedEvtDef, SortedMap<Date, double[]> serie) {
		
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy");
		final NumberFormat numberFormat = NumberFormat.getInstance();
		
		XYItemRenderer renderer = jFreeChart.getXYPlot().getRenderer();
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		String[] eventDefDescriptor = chartedEvtDef.getEventDefDescriptor().descriptionArray();
		
		int validDomainCpt = 0;
		for (int i = 0; i < eventDefDescriptor.length; i++) {
			if (eventDefDescriptor[i] != null) {
				
				final String domain = eventDefDescriptor[i];
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
				//renderer.setSeriesFillPaint(seriesIdx, serieDef.getSerieColor());
				//renderer.setSeriesStroke(seriesIdx, new BasicStroke(serieDef.getSerieSize()));
				
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
								"<b>"+  domain +"</b> on " + x +"<br>" +
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

		jFreeChart.getXYPlot().setDataset(dataset);
		
	}
	
	

}
