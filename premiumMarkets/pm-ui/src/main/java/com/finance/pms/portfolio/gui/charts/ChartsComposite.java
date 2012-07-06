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

import java.awt.BorderLayout;
import java.awt.Panel;
import java.security.InvalidAlgorithmParameterException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Text;
import org.jfree.chart.ChartPanel;

import com.finance.pms.CursorFactory;
import com.finance.pms.ErrorDialog;
import com.finance.pms.MainGui;
import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.StripedCloseAbsoluteRelative;
import com.finance.pms.datasources.db.StripedCloseDayToDay;
import com.finance.pms.datasources.db.StripedCloseFunction;
import com.finance.pms.datasources.db.StripedCloseIndexRelative;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.portfolio.gui.NewPortfolioItemDialog;
import com.finance.pms.portfolio.gui.PortfolioComposite;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;

/**
 * The Class ChartsComposite.
 * 
 * @author Guillaume Thoreton
 */
public class ChartsComposite extends SashForm {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(ChartsComposite.class);
	
	/** The Constant DISPLAYED_VALUES. */
	public static Date DEFAULT_START_DATE;
	static {
		try {
			DEFAULT_START_DATE = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1970");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private java.awt.Frame chartFrame;				

	private Text selectReferreText;
	private StripedCloseFunction stripedCloseFunction;
	private Quotations refereeQuotations;

	private List<SlidingPortfolioShare> listShares;
	
	private Charts chartWraper;
	
	private Date slidingStartDate;
	private Date slidingEndDate;
	private Date firstStartDate;
	
	private PortfolioComposite composite;
	private Composite cComposite;


	/**
	 * Instantiates a new charts composite.
	 * 
	 * @param parent the arg0
	 * @param style the arg1
	 * 
	 * @author Guillaume Thoreton
	 */
	public ChartsComposite(Composite parent, int style) {
		super(parent, style);
		
		this.listShares = new ArrayList<SlidingPortfolioShare>();
		
		slidingEndDate = EventSignalConfig.getNewDate();
		
		Calendar  calendar  = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -1);
		slidingStartDate = calendar.getTime();
		
		this.stripedCloseFunction = new StripedCloseAbsoluteRelative(slidingStartDate,slidingEndDate);
		
		this.initGUI();
	}

	/**
	 * Sets the share list.
	 * 
	 * @param listShares the new share list
	 */
	public void resetShareList(List<SlidingPortfolioShare> listShares) {
		this.listShares = listShares;
		updateChart(listShares);
	}
	
	public void highLight(Integer idx) {
		chartWraper.highLightSerie(idx);
	}


	/**
	 * Update chart.
	 * 
	 * @author Guillaume Thoreton
	 * @param listShares 
	 */
	private void updateChart(List<SlidingPortfolioShare> listShares) {
		
		stripedCloseFunction.updateStartDate(slidingStartDate);
		stripedCloseFunction.updateEndDate(slidingEndDate);

		if (((Panel)chartFrame.getComponent(0)).getComponents().length > 0) {
			chartWraper.updateDataSet(listShares, stripedCloseFunction);		
		} else {
			chartWraper = new Charts(ChartsComposite.DEFAULT_START_DATE,JFreeChartTimePeriod.DAY);
			ChartPanel chartPanel = new ChartPanel(chartWraper.initChart(stripedCloseFunction,listShares));
			((Panel)chartFrame.getComponent(0)).add(chartPanel);
		}
		
		chartFrame.setVisible(true);
			
	}
	
	/**
	 * Inits the gui.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void initGUI() {
		try {
			
			this.setOrientation(SWT.VERTICAL);
			
			this.setBackground(new Color(getDisplay(),239,120,103));
			Color innerBgColor = new Color(getDisplay(),241,200,195);
		
			this.addDisposeListener(new DisposeListener() {
				
				public void widgetDisposed(DisposeEvent evt) {
					rootShellClosed(evt);
				}
			});
			
			{
				cComposite = new Composite(this, SWT.EMBEDDED | SWT.NO_BACKGROUND);
				cComposite.setLayout(new FillLayout());
				chartFrame = SWT_AWT.new_Frame(cComposite);
				Panel heavyWeightPanel = new Panel(new BorderLayout());
				chartFrame.add(heavyWeightPanel);
				updateChart(new ArrayList<SlidingPortfolioShare>());
				chartFrame.setVisible(true);
			}
			{
				final Group portfolioBoutonsGroup = new Group(this, SWT.NONE);
				portfolioBoutonsGroup.setBackground(innerBgColor);
				GridLayout portfolioBoutonsGroupLayout = new GridLayout();
				portfolioBoutonsGroupLayout.numColumns = 3;
				portfolioBoutonsGroupLayout.makeColumnsEqualWidth=true;
				portfolioBoutonsGroup.setLayout(portfolioBoutonsGroupLayout);
				
				GridData portfolioBoutonsGroupLData = new GridData(GridData.FILL_HORIZONTAL);
				portfolioBoutonsGroup.setLayoutData(portfolioBoutonsGroupLData);
				
				portfolioBoutonsGroup.setText("Portfolios charting : ");
				portfolioBoutonsGroup.setFont(MainGui.DEFAULTFONT);
				portfolioBoutonsGroup.setBackground(innerBgColor);
				final Button addRefereeCheck;
				final Button absoluteReferenceCheck;
				final Button relativePriceCheck;
				final Button houseDrvCheck;
				final Button selectRefereeButton;
				{
					relativePriceCheck = new Button(portfolioBoutonsGroup, SWT.RADIO | SWT.LEFT);
					GridData portfolioDeletePortfoliobuttonData = new GridData(GridData.FILL_HORIZONTAL);
					portfolioDeletePortfoliobuttonData.horizontalSpan=3;
					relativePriceCheck.setLayoutData(portfolioDeletePortfoliobuttonData);
					relativePriceCheck.setText("Buy price relative strenght");
					relativePriceCheck.setFont(MainGui.DEFAULTFONT);
					relativePriceCheck.setSelection(false);
				}
				{
					absoluteReferenceCheck = new Button(portfolioBoutonsGroup, SWT.RADIO | SWT.LEFT);
					GridData portfolioDeletePortfoliobuttonData = new GridData(GridData.FILL_HORIZONTAL);
					portfolioDeletePortfoliobuttonData.horizontalSpan=3;
					absoluteReferenceCheck.setLayoutData(portfolioDeletePortfoliobuttonData);
					absoluteReferenceCheck.setText("Real value");
					absoluteReferenceCheck.setFont(MainGui.DEFAULTFONT);
					absoluteReferenceCheck.setSelection(true);
				}
				{
					houseDrvCheck = new Button(portfolioBoutonsGroup, SWT.RADIO | SWT.LEFT);
					GridData portfolioDeletePortfoliobuttonData = new GridData(GridData.FILL_HORIZONTAL);
					portfolioDeletePortfoliobuttonData.horizontalSpan=3;
					houseDrvCheck.setLayoutData(portfolioDeletePortfoliobuttonData);
					houseDrvCheck.setFont(MainGui.DEFAULTFONT);
					houseDrvCheck.setText("Day to day log change");
				}
				{
					addRefereeCheck = new Button(portfolioBoutonsGroup, SWT.RADIO | SWT.LEFT);
					GridData portfolioDeletePortfoliobuttonData = new GridData(GridData.FILL_HORIZONTAL);
					portfolioDeletePortfoliobuttonData.horizontalSpan=3;
					addRefereeCheck.setLayoutData(portfolioDeletePortfoliobuttonData);
					addRefereeCheck.setFont(MainGui.DEFAULTFONT);
					addRefereeCheck.setText("Referee relative strenght");
				}
				{
					selectRefereeButton = new Button(portfolioBoutonsGroup, SWT.PUSH | SWT.CENTER);
					GridData portfolioAddbuttonData = new GridData(GridData.FILL_HORIZONTAL);
					selectRefereeButton.setLayoutData(portfolioAddbuttonData);
					selectRefereeButton.setText("Select referee ...");
					selectRefereeButton.setFont(MainGui.DEFAULTFONT);
					selectRefereeButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent evt) {
							portfolioBoutonsGroup.getParent().getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
							try {
								selectReferee(listShares);
							} finally {
								portfolioBoutonsGroup.getParent().getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
							}
						}
					});
					selectRefereeButton.setEnabled(false);
				}
				{
					selectReferreText = new Text(portfolioBoutonsGroup, SWT.CENTER);
					GridData portfolioAddbuttonData = new GridData(GridData.FILL_HORIZONTAL);
					portfolioAddbuttonData.horizontalSpan=2;
					selectReferreText.setLayoutData(portfolioAddbuttonData);
					selectReferreText.setText("Your referee");
					selectReferreText.setFont(MainGui.DEFAULTFONT);
					selectReferreText.setEnabled(false);
				}
				
				final Slider sliderStartDate;
				final Label startDateLabel;
				{
					startDateLabel = new Label(portfolioBoutonsGroup, SWT.RIGHT);
					GridData portfolioAddbuttonData = new GridData(GridData.FILL_HORIZONTAL);
					startDateLabel.setLayoutData(portfolioAddbuttonData);
					startDateLabel.setText("Sliding period start : "+DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingStartDate)+"");
					startDateLabel.setBackground(innerBgColor);
					startDateLabel.setFont(MainGui.DEFAULTFONT);
				}
				{
					sliderStartDate = new Slider(portfolioBoutonsGroup, SWT.HORIZONTAL);
					GridData portfolioAddbuttonData = new GridData(GridData.FILL_HORIZONTAL);
					portfolioAddbuttonData.horizontalSpan = 2;
					sliderStartDate.setLayoutData(portfolioAddbuttonData);
					sliderStartDate.setThumb(1);
					sliderStartDate.addListener(SWT.MouseExit, new Listener() {

						public void handleEvent(Event arg0) {
							stripedCloseFunction.updateStartDate(slidingStartDate);
							updateChart(listShares);
							composite.slidingDateChange();
						}
						
					});
				}
				
				final Slider sliderEndDate;
				final Label endDateLabel;
				{
					endDateLabel = new Label(portfolioBoutonsGroup, SWT.RIGHT);
					GridData portfolioAddbuttonData = new GridData(GridData.FILL_HORIZONTAL);
					endDateLabel.setLayoutData(portfolioAddbuttonData);
					endDateLabel.setText("Sliding period end : "+DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingEndDate));
					endDateLabel.setBackground(innerBgColor);
					endDateLabel.setFont(MainGui.DEFAULTFONT);
				}
				{
					sliderEndDate = new Slider(portfolioBoutonsGroup, SWT.HORIZONTAL);
					GridData portfolioAddbuttonData = new GridData(GridData.FILL_HORIZONTAL);
					portfolioAddbuttonData.horizontalSpan=2;
					sliderEndDate.setLayoutData(portfolioAddbuttonData);
					sliderEndDate.setThumb(1);
					sliderEndDate.setSelection(100);
					sliderEndDate.addListener(SWT.MouseExit, new Listener() {

						public void handleEvent(Event arg0) {
							stripedCloseFunction.updateEndDate(slidingEndDate);
							updateChart(listShares);
							composite.slidingDateChange();
						}
						
					});
				}
				
				sliderStartDate.addListener(SWT.Selection, new Listener() {
				      public void handleEvent(Event event) {
				    	startSliderUpdate(sliderStartDate, startDateLabel);
				      }
				});
				
				sliderEndDate.addListener(SWT.Selection, new Listener() {
				      public void handleEvent(Event event) {
				    	Integer sliderValue = sliderEndDate.getSelection();
				    	Date maxDate= EventSignalConfig.getNewDate();
				    	Date minDate= minimumDate();
				    	Integer maxSlider= sliderEndDate.getMaximum();
				    	Integer minSlider= sliderEndDate.getMinimum();
				    	Integer perCentValue = sliderValue*100/ (maxSlider - minSlider - sliderEndDate.getThumb());
				    	Long diffDate = (maxDate.getTime() - minDate.getTime())/(1000*3600*24);
				    	Long nbDaySinceMin  = perCentValue * diffDate /100;
				    	Calendar calendar = Calendar.getInstance();
				    	calendar.setTime(minDate);
				    	calendar.add(Calendar.DAY_OF_YEAR, nbDaySinceMin.intValue());
				    	slidingEndDate = calendar.getTime();
				    	
				    	endDateLabel.setText("Sliding period End : "+DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingEndDate));
				    	endDateLabel.setFont(MainGui.DEFAULTFONT);
				    	
				      }
				});
				
				{
					Label firstDate = new Label(portfolioBoutonsGroup, SWT.RIGHT);
					GridData firstDateGrid = new GridData(GridData.FILL_HORIZONTAL);
					firstDate.setLayoutData(firstDateGrid);
					final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
					firstDate.setText("Data start ("+simpleDateFormat.toPattern()+") : ");
					firstDate.setBackground(innerBgColor);
					firstDate.setFont(MainGui.DEFAULTFONT);
					
					final Text firstDateTxt = new Text(portfolioBoutonsGroup, SWT.LEFT);
					GridData firstDateGridTxt = new GridData(GridData.FILL_HORIZONTAL);
					firstDateGridTxt.horizontalSpan=2;
					firstDateTxt.setLayoutData(firstDateGridTxt);
					firstDateTxt.setText(simpleDateFormat.format(minimumDate()));
					firstDateTxt.setFont(MainGui.CONTENTFONT);
					firstDateTxt.addListener(SWT.DefaultSelection, new Listener() {

						public void handleEvent(Event arg0) {
							try {
								firstStartDate = simpleDateFormat.parse(firstDateTxt.getText());
								sliderStartDate.setSelection(0);
								startSliderUpdate(sliderStartDate, startDateLabel);
								updateChart(listShares);
								
							} catch (ParseException e) {
								ErrorDialog inst = new ErrorDialog(getShell(), SWT.NULL, "Can't parse date "+firstDateTxt.getText(), null);
								inst.open();
							}
						}
			
					});
				}
				
				relativePriceCheck.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseDown(MouseEvent evt) {
						selectReferreText.setEnabled(false);
						selectRefereeButton.setEnabled(false);
						sliderStartDate.setEnabled(true);
						startDateLabel.setEnabled(true);
						sliderEndDate.setEnabled(true);
						endDateLabel.setEnabled(true);
						stripedCloseFunction =  new StripedCloseInitPriceRelative();
						
						updateChart(listShares);
					}
				});
				
				absoluteReferenceCheck.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
					
						selectReferreText.setEnabled(false);
						selectRefereeButton.setEnabled(false);
						sliderStartDate.setEnabled(true);
						startDateLabel.setEnabled(true);
						sliderEndDate.setEnabled(true);
						endDateLabel.setEnabled(true);
						stripedCloseFunction =  new StripedCloseAbsoluteRelative(slidingStartDate, slidingEndDate);
						
						updateChart(listShares);
					}
				});
				
				houseDrvCheck.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
		
						selectReferreText.setEnabled(false);
						selectRefereeButton.setEnabled(false);
						sliderStartDate.setEnabled(true);
						startDateLabel.setEnabled(true);
						sliderEndDate.setEnabled(true);
						endDateLabel.setEnabled(true);
						stripedCloseFunction =  new StripedCloseDayToDay();
						
						updateChart(listShares);
					}
				});
				
				addRefereeCheck.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent evt) {
		
						selectReferreText.setEnabled(true);
						selectRefereeButton.setEnabled(true);
						sliderStartDate.setEnabled(true);
						startDateLabel.setEnabled(true);
						sliderEndDate.setEnabled(true);
						endDateLabel.setEnabled(true);
						try {
							relativeIndexSetting();
						} catch (InvalidAlgorithmParameterException e) {
							relativePriceCheck.setSelection(true);
						}
						
						updateChart(listShares);
					}
				});
			}
			
			this.setWeights(new int[]{75,25});
			
			this.layout();
			
			
		} catch (Exception e) {
			LOGGER.error("",e);
		}
	}
	
	private void startSliderUpdate(final Slider sliderStartDate, final Label startDateLabel) {
		Integer sliderValue = sliderStartDate.getSelection();
    	Date maxDate= EventSignalConfig.getNewDate();
    	Date minDate= minimumDate();
    	Integer maxSlider= sliderStartDate.getMaximum();
    	Integer minSlider= sliderStartDate.getMinimum();
    	Integer perCentValue = sliderValue*100/(maxSlider - minSlider - sliderStartDate.getThumb());
    	Long diffDateInDays = (maxDate.getTime() - minDate.getTime())/(1000*3600*24);
    	Long nbDaySinceMin  = perCentValue * diffDateInDays /100;
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(minDate);
    	calendar.add(Calendar.DAY_OF_YEAR, nbDaySinceMin.intValue());
    	slidingStartDate = calendar.getTime();
    	
    	startDateLabel.setText("Sliding period Start : "+DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingStartDate));
    	startDateLabel.setFont(MainGui.DEFAULTFONT);
	}
	
	protected void rootShellClosed(DisposeEvent evt) {
		chartFrame.dispose();	
	}

	/**
	 * @param minDate
	 * @return
	 */
	private Date minimumDate() {
    	if (firstStartDate == null) {
    		Calendar  calendar  = Calendar.getInstance();
        	calendar.add(Calendar.YEAR, -1);
    		firstStartDate = calendar.getTime();
    	}
    	return firstStartDate;
	}
	
	/**
	 * Load referee quotations.
	 * 
	 * @param stock the s
	 * 
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 */
	private void loadRefereeQuotations(Stock stock) throws InvalidAlgorithmParameterException {
		
			try {
				if (null == stock) throw new InvalidAlgorithmParameterException("Referee can't be null");
					
				refereeQuotations  = QuotationsFactories.getFactory().getQuotationsInstance(stock,ChartsComposite.DEFAULT_START_DATE, EventSignalConfig.getNewDate(),true,stock.getMarket().getCurrency(),0);

				stripedCloseFunction =  new StripedCloseIndexRelative(refereeQuotations, slidingStartDate, slidingEndDate);
				selectReferreText.setText(stock.getName());
				selectReferreText.setFont(MainGui.CONTENTFONT);
			} catch (NoQuotationsException e) {
				throw new RuntimeException(e);
			}
	}
	
	/**
	 * Select referee.
	 * 
	 * @author Guillaume Thoreton
	 * @param listShares 
	 */
	private void selectReferee(List<SlidingPortfolioShare> listShares) {
		//open selection window
		NewPortfolioItemDialog pItemDialog = NewRefereeDialog.showUI(getShell());
		List<Stock> listStock = pItemDialog.getSelectedStocks();
		if (listStock.size() > 0) {
			try {
				loadRefereeQuotations(listStock.get(0));

				MainPMScmd.getPrefs().put("charts.referee", listStock.get(0).getSymbol());
				updateChart(listShares);

			} catch (InvalidAlgorithmParameterException e) {
				LOGGER.debug(e);
				ErrorDialog inst = new ErrorDialog(this.getShell(), SWT.NULL,
						"Not enougth quotations for "+listStock.get(0).getName()+"\n"+e, null);
				inst.open();
			}
		} else if (pItemDialog instanceof NewRefereeDialog) {
			LOGGER.error(Thread.currentThread().getStackTrace()[0].getMethodName());
			ErrorDialog inst = new ErrorDialog(this.getShell(), SWT.NULL, "No referee selected please select a stock \n", null);
			inst.open();
		}

	}

	/**
	 * @throws InvalidAlgorithmParameterException 
	 * 
	 */
	private void relativeIndexSetting() throws InvalidAlgorithmParameterException {
		if ( !"Your referee".equals(selectReferreText.getText()) && refereeQuotations != null && refereeQuotations.size() != 0) {
			try {
				stripedCloseFunction =  new StripedCloseIndexRelative(refereeQuotations, slidingStartDate, slidingEndDate);
			} catch (InvalidAlgorithmParameterException e) {
				LOGGER.error("",e);
			}
		} else {
			String preferedRef = MainPMScmd.getPrefs().get("charts.referee", "NotDef");
			
			Stock stock;
			if (!preferedRef.equals("NotDef")) {
				try {
					stock = DataSource.getInstance().loadStockBySymbol(preferedRef);
					loadRefereeQuotations(stock);
				} catch (Exception e) {
					LOGGER.debug(e);
					ErrorDialog inst = new ErrorDialog(getShell(), SWT.NULL, "Referree unknown or no quotations", null);
					inst.open();
					stripedCloseFunction =  new StripedCloseInitPriceRelative();
					throw new InvalidAlgorithmParameterException(e);
				}
			}
		}
	}	
	

	public void setComposite(PortfolioComposite composite) {
		this.composite = composite;
	}

	@Override
	public void setCursor(Cursor arg0) {
		super.setCursor(arg0);
		
		java.awt.Cursor awtPredefinedCursor;
		if (arg0.equals(CursorFactory.getCursor(SWT.CURSOR_WAIT)) || arg0.equals(CursorFactory.getCursor(SWT.CURSOR_APPSTARTING))) {
			
			if (arg0.equals(CursorFactory.getCursor(SWT.CURSOR_APPSTARTING))) {
				//awtPredefinedCursor = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.CROSSHAIR_CURSOR);
				awtPredefinedCursor = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR);
			} else {
				awtPredefinedCursor = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR);
			}

		} else {
			awtPredefinedCursor = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR);
		}
		
		this.chartFrame.setCursor(awtPredefinedCursor);
		this.chartFrame.getComponent(0).setCursor(awtPredefinedCursor);
		if ( ((Panel)chartFrame.getComponent(0)).getComponents().length > 0) {
			((JPanel)((Panel)this.chartFrame.getComponent(0)).getComponent(0)).setCursor(awtPredefinedCursor);
		} 
		
	}
	
	public Date getSlidingStartDate() {
		return slidingStartDate;
	}

	public Date getSlidingEndDate() {
		return slidingEndDate;
	}
	
}