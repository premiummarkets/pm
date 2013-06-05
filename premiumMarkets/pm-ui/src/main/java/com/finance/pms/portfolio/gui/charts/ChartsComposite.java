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

import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Slider;
import org.jfree.chart.ChartPanel;

import com.finance.pms.CursorFactory;
import com.finance.pms.ErrorDialog;
import com.finance.pms.LogComposite;
import com.finance.pms.MainGui;
import com.finance.pms.RefreshableView;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.EventModel;
import com.finance.pms.datasources.EventRefreshException;
import com.finance.pms.datasources.RefreshChartHightlited;
import com.finance.pms.datasources.db.StripedCloseAbsoluteRelative;
import com.finance.pms.datasources.db.StripedCloseFunction;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.portfolio.gui.PortfolioComposite;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;

/**
 * The Class ChartsComposite.
 * 
 * @author Guillaume Thoreton
 */
public class ChartsComposite extends SashForm implements RefreshableView {


	protected static MyLogger LOGGER = MyLogger.getLogger(ChartsComposite.class);
	
	public static Date DEFAULT_START_DATE;
	static {
		try {
			DEFAULT_START_DATE = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1970");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private PortfolioComposite portfolioComposite;
	private LogComposite logComposite;
	private ChartDisplayStrategy chartDisplayStrategy;
	
	private StripedCloseFunction stripedCloseFunction;
	private List<SlidingPortfolioShare> listShares;

	private Integer highligtedId;
	private EventModel<RefreshChartHightlited> hightlitedEventModel;
	
	private ChartPanel mainChartPanel;
	private Composite mainChartComposite;
	private ChartMain mainChartWraper;
	
	private Set<EventInfo> chartedEvtDefsTrends;
	private EventInfo chartedEvtDef;

	public Group chartBoutonsGroup;
	private Group popusGroup;
	
	private Group slidingGroup;
	private Slider sliderStartDate;
	private Label startDateLabel;
	private Slider sliderEndDate;
	private Label endDateLabel;
	
	private Date slidingStartDate;
	private Date slidingEndDate;
	private Date firstStartDate;
	private Date lastEndDate;

	private Boolean sliderSelection;
	private Boolean closeRequested = false;


	public ChartsComposite(Composite parent, int style, LogComposite logComposite) {
		super(parent, style);
		
		this.listShares = new ArrayList<SlidingPortfolioShare>();
		
		this.slidingEndDate = DateFactory.midnithDate(EventSignalConfig.getNewDate());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -1);
		this.slidingStartDate = calendar.getTime();
		
		this.logComposite = logComposite;
		this.hightlitedEventModel = EventModel.getInstance(new RefreshChartHightlited(), logComposite);
		
		chartedEvtDefsTrends = initChartedEvtDefsTrendsSet();
		chartedEvtDef = EventDefinition.ZERO;
		
		sliderSelection = false;
		
		this.setToolTipText("Sash : Click on this border and drag to resize");
		super.setCursor(new Cursor(getDisplay(), SWT.CURSOR_CROSS));
		
		this.stripedCloseFunction = new StripedCloseAbsoluteRelative(slidingStartDate, slidingEndDate);
		this.initGUI();
		chartDisplayStrategy = new ChartPerfDisplay(this);
		chartDisplayStrategy.resetChart();
		
	}


	protected TreeSet<EventInfo> initChartedEvtDefsTrendsSet() {
		return new TreeSet<EventInfo>(new Comparator<EventInfo>() {

			@Override
			public int compare(EventInfo o1, EventInfo o2) {
				return o1.info().compareTo(o2.info());
			}
		});
	}


	public void resetShareList(List<SlidingPortfolioShare> listShares, Boolean portfolioHasChanged) {
		
		this.listShares = listShares;
		updateCharts(listShares, portfolioHasChanged, false);
		
	}
	
	public void highLight(Integer idx, final Stock selectedShare, boolean recalculationGranted) {
		chartDisplayStrategy.highLight(idx, selectedShare, recalculationGranted);
	}

	void updateCharts(List<SlidingPortfolioShare> listShares, Boolean portfolioHasChanged, boolean grantEventsUpdate) {
		
		//Quotation line
		stripedCloseFunction.updateStartDate(slidingStartDate);
		stripedCloseFunction.updateEndDate(slidingEndDate);
		mainChartWraper.updateLineDataSet(listShares, stripedCloseFunction, chartDisplayStrategy.getIsApplyColor());
		
		mainChartWraper.resetBarChart();
		mainChartWraper.resetIndicChart();
		
		//indic, bar and highlight
		if (!portfolioHasChanged) {
			
			Object[] viewStateParams = hightlitedEventModel.getViewStateParams();
			if (viewStateParams != null && viewStateParams.length == 1) {
				highLight(this.highligtedId, (Stock) viewStateParams[0], grantEventsUpdate);
			}
			
		} else {
			
			chartDisplayStrategy.highLighPrevious();
			
		}

	}
	
	
	private void initGUI() {
		try {
			
			this.setOrientation(SWT.VERTICAL);
			
			this.setBackground(MainGui.cHART_DARKER);
			Color innerBgColor = MainGui.cHART_LIGHT;
		
			this.addDisposeListener(new DisposeListener() {
				
				public void widgetDisposed(DisposeEvent evt) {
					rootShellClosed(evt);
				}
			});
			
			{
				mainChartComposite = new Composite(this, SWT.EMBEDDED | SWT.NO_BACKGROUND);
				Frame chartFrame = SWT_AWT.new_Frame(mainChartComposite);
				mainChartWraper = new ChartMain(ChartsComposite.DEFAULT_START_DATE, JFreeChartTimePeriod.DAY);
				mainChartPanel = new ChartPanel(mainChartWraper.initChart(stripedCloseFunction));
				chartFrame.add(mainChartPanel);
				chartFrame.setVisible(true);
				mainChartPanel.addMouseMotionListener(new MouseMotionListener() {
					
					@Override
					public void mouseMoved(java.awt.event.MouseEvent e) {
						synchronized (closeRequested) {
							if (!closeRequested) {
								Display.getDefault().asyncExec(new Runnable() {
									public void run() {
										if (!mainChartComposite.isFocusControl()) mainChartComposite.forceFocus();
									}
								});
							}
						}
					}
					
					@Override
					public void mouseDragged(java.awt.event.MouseEvent e) {
						synchronized (closeRequested) {
							if (!closeRequested) {
								Display.getDefault().asyncExec(new Runnable() {
									public void run() {
										if (!mainChartComposite.isFocusControl()) mainChartComposite.forceFocus();
									}
								});
							}
						}
					}
				});
				mainChartPanel.setInitialDelay(0);
				mainChartPanel.setReshowDelay(Integer.MAX_VALUE);
				mainChartPanel.addMouseMotionListener(new MouseMotionListener() {
					
					@Override
					public void mouseMoved(MouseEvent e) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						
					}
					
					@Override
					public void mouseDragged(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});

				mainChartComposite.addKeyListener(new org.eclipse.swt.events.KeyListener() {
					
					@Override
					public void keyReleased(org.eclipse.swt.events.KeyEvent e) {
						if (((e.stateMask & SWT.CTRL) == SWT.CTRL) && ((e.stateMask & SWT.ALT) == SWT.ALT) && (e.keyCode == 'p')) {
							chartDisplayStrategy.exportBarChartPng();
						}
					}
					
					@Override
					public void keyPressed(org.eclipse.swt.events.KeyEvent e) {
						//
					}
				});
			}
			{
				chartBoutonsGroup = new Group(this, SWT.NONE);
				chartBoutonsGroup.setBackground(innerBgColor);
				GridLayout portfolioBoutonsGroupLayout = new GridLayout();
				portfolioBoutonsGroupLayout.numColumns = 1;
				portfolioBoutonsGroupLayout.verticalSpacing=0;
				portfolioBoutonsGroupLayout.marginHeight=0;
				chartBoutonsGroup.setLayout(portfolioBoutonsGroupLayout);
				GridData portfolioInfosGroupData = new GridData(GridData.FILL_HORIZONTAL);
				chartBoutonsGroup.setLayoutData(portfolioInfosGroupData);
				
				chartBoutonsGroup.setText("Portfolios charting : ");
				chartBoutonsGroup.setFont(MainGui.DEFAULTFONT);
				chartBoutonsGroup.setBackground(innerBgColor);
				
				{
					popusGroup = new Group(chartBoutonsGroup, SWT.NONE);
					GridData popusGroupData = new GridData(SWT.FILL, SWT.FILL,true, false);
					popusGroup.setLayoutData(popusGroupData);
					popusGroup.setBackground(innerBgColor);
					RowLayout popusGroupL = new RowLayout(SWT.HORIZONTAL);
					popusGroupL.justify = true;
					popusGroupL.fill=true;
					popusGroupL.wrap=false;
					popusGroupL.marginHeight=0;
					popusGroup.setLayout(popusGroupL);
					
				}
				
				//Sliding
				{
					
					slidingGroup = new Group(chartBoutonsGroup, SWT.NONE);
					GridData slidingGroupData = new GridData(SWT.FILL, SWT.FILL,true, false);
					slidingGroup.setLayoutData(slidingGroupData);
					slidingGroup.setBackground(innerBgColor);
					GridLayout slidingGroupL = new GridLayout();
					slidingGroupL.numColumns=5;
					slidingGroupL.marginHeight=0;
					slidingGroupL.verticalSpacing=0;
					slidingGroup.setLayout(slidingGroupL);
					///start
					{
						startDateLabel = new Label(slidingGroup, SWT.NONE);
						GridData startOneYearBackData = new GridData(SWT.BEGINNING, SWT.FILL,false, false);
						startOneYearBackData.horizontalSpan=2;
						startDateLabel.setLayoutData(startOneYearBackData);
						startDateLabel.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingStartDate)+"");
						startDateLabel.setBackground(innerBgColor);
						startDateLabel.setFont(MainGui.DEFAULTFONT);
					}
					///sliding sliders
					Composite slidingSliderGroup = new Composite(slidingGroup, SWT.NONE);
					slidingSliderGroup.setSize(1000, SWT.DEFAULT);
					GridData slidersGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
					slidersGridData.verticalSpan=2;
					slidingSliderGroup.setLayoutData(slidersGridData);
					slidingSliderGroup.setBackground(innerBgColor);
					slidingSliderGroup.setForeground(innerBgColor);
					FillLayout slidingSliderGroupL = new FillLayout(SWT.VERTICAL);
					slidingSliderGroup.setLayout(slidingSliderGroupL);
					{
						sliderStartDate = new Slider(slidingSliderGroup, SWT.HORIZONTAL);
						sliderStartDate.setThumb(1);
						sliderStartDate.setMaximum(100);
						sliderStartDate.addListener(SWT.MouseExit, new Listener() {

							public void handleEvent(Event arg0) {
								sliderChangesApply();
							}

						});
					}	
					{
						sliderEndDate = new Slider(slidingSliderGroup, SWT.HORIZONTAL);
						sliderEndDate.setThumb(1);
						sliderEndDate.setMinimum(0);
						sliderEndDate.setSelection(100);
						sliderEndDate.addListener(SWT.MouseExit, new Listener() {

							public void handleEvent(Event arg0) {
								sliderChangesApply();
							}

						});
					}
					//end
					{
						endDateLabel = new Label(slidingGroup, SWT.NONE);
						GridData endOneYearBackData = new GridData(SWT.END, SWT.FILL,false, false);
						endOneYearBackData.horizontalSpan=2;
						endDateLabel.setLayoutData(endOneYearBackData);
						endDateLabel.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingEndDate));
						endDateLabel.setBackground(innerBgColor);
						endDateLabel.setFont(MainGui.DEFAULTFONT);
					}
					
					//but start
					{
						Button startOneYearBack = new Button(slidingGroup, SWT.ARROW|SWT.LEFT);
						GridData startOneYearBackData = new GridData(SWT.END, SWT.FILL,false, true);
						startOneYearBack.setLayoutData(startOneYearBackData);
						startOneYearBack.setToolTipText("Move start date one year backward.");
						startOneYearBack.addSelectionListener(new SelectionListener() {

							@Override
							public void widgetSelected(SelectionEvent e) {
								handle();
							}

							private void handle() {
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(slidingStartDate);
								calendar.add(Calendar.YEAR, -1);
								firstStartDate = calendar.getTime();
								sliderStartDate.setSelection(0);
								startSliderUpdateConditional(sliderStartDate, startDateLabel, sliderEndDate, endDateLabel);
							}

							@Override
							public void widgetDefaultSelected(SelectionEvent e) {
								handle();

							}
						});
						startOneYearBack.addListener(SWT.MouseExit, new Listener() {
							
							@Override
							public void handleEvent(Event event) {
								sliderChangesApply();
							}
						});
					}
					
					{
						Button startOneYearForward = new Button(slidingGroup, SWT.ARROW|SWT.RIGHT);
						GridData startOneYearBackData = new GridData(SWT.BEGINNING, SWT.FILL,false, true);
						startOneYearForward.setLayoutData(startOneYearBackData);
						startOneYearForward.setToolTipText("Move start date one year forward.");
						startOneYearForward.addSelectionListener(new SelectionListener() {

							@Override
							public void widgetSelected(SelectionEvent e) {
								handle();
							}

							private void handle() {
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(slidingStartDate);
								calendar.add(Calendar.YEAR, +1);
								if (calendar.getTime().before(slidingEndDate)) {
									firstStartDate = calendar.getTime();
									sliderStartDate.setSelection(0);
									startSliderUpdateConditional(sliderStartDate, startDateLabel, sliderEndDate, endDateLabel);
								} else {
									ErrorDialog dialog = new ErrorDialog(getShell(), SWT.NONE, "To move the start date further forward, you will need to move the end date first.", null);
									dialog.open();
								}
							}

							@Override
							public void widgetDefaultSelected(SelectionEvent e) {
								handle();

							}
						});
						startOneYearForward.addListener(SWT.MouseExit, new Listener() {

							@Override
							public void handleEvent(Event event) {
								sliderChangesApply();
							}
						});
					}
					///but end
					
					{
						Button endOneYearBack = new Button(slidingGroup, SWT.ARROW|SWT.LEFT);
						GridData endOneYearBackData = new GridData(SWT.END, SWT.FILL,false, true);
						endOneYearBack.setLayoutData(endOneYearBackData);
						endOneYearBack.setToolTipText("Move end date one year backward.");
						endOneYearBack.addSelectionListener(new SelectionListener() {

							@Override
							public void widgetSelected(SelectionEvent e) {
								handle();
							}

							private void handle() {
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(slidingEndDate);
								calendar.add(Calendar.YEAR, -1);
								if (calendar.getTime().after(slidingStartDate)) {
									lastEndDate = calendar.getTime();
									sliderEndDate.setSelection(100);
									endSliderUpdateConditional(sliderEndDate, endDateLabel, sliderStartDate, startDateLabel);
								} else {
									ErrorDialog dialog = new ErrorDialog(getShell(), SWT.NONE, "To move the end date further backward, you will need to move the start date first.", null);
									dialog.open();
								}
							}

							@Override
							public void widgetDefaultSelected(SelectionEvent e) {
								handle();
							}
						});
						endOneYearBack.addListener(SWT.MouseExit, new Listener() {

							@Override
							public void handleEvent(Event event) {
								sliderChangesApply();
							}
						});
					}
					{
						Button endOneYearForward = new Button(slidingGroup, SWT.ARROW|SWT.RIGHT);
						GridData endOneYearBackData = new GridData(SWT.BEGINNING, SWT.FILL,false, true);
						endOneYearForward.setLayoutData(endOneYearBackData);
						endOneYearForward.setToolTipText("Move end date one year forward.");
						endOneYearForward.addSelectionListener(new SelectionListener() {

							@Override
							public void widgetSelected(SelectionEvent e) {
								handle();
							}

							private void handle() {
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(slidingEndDate);
								calendar.add(Calendar.YEAR, +1);
								Date newDate = EventSignalConfig.getNewDate();
								if (calendar.getTime().after(newDate)) {
									calendar.setTime(newDate);
								}
								lastEndDate = calendar.getTime();
								sliderEndDate.setSelection(100);
								endSliderUpdateConditional(sliderEndDate, endDateLabel, sliderStartDate, startDateLabel);
							}

							@Override
							public void widgetDefaultSelected(SelectionEvent e) {
								handle();

							}
						});
						endOneYearForward.addListener(SWT.MouseExit, new Listener() {

							@Override
							public void handleEvent(Event event) {
								sliderChangesApply();
							}
						});
					}
					
					
					sliderStartDate.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event event) {
							startSliderUpdateConditional(sliderStartDate, startDateLabel, sliderEndDate, endDateLabel);
						}
					});

					sliderEndDate.addListener(SWT.Selection, new Listener() {

						public void handleEvent(Event event) {
							endSliderUpdateConditional(sliderEndDate, endDateLabel, sliderStartDate, startDateLabel);
						}
					});
					
					slidingGroup.layout();
				}
				
			}

			this.layout();	
			this.pack();
			
			
		} catch (Exception e) {
			LOGGER.error("",e);
		}
	}
	
	private void startSliderUpdateConditional(final Slider sliderStartDate, final Label startDateLabel, Slider sliderEndDate,  Label endDateLabel) {
		
		synchronized (sliderSelection) {
			sliderSelection = true;
		}
		
		Integer startSliderValue = sliderStartDate.getSelection();
		
		if (sliderEndDate.getSelection() - sliderStartDate.getSelection() <= 10) {
			if (sliderEndDate.getSelection() <= 90) {
				int endValue = startSliderValue+10;
				sliderEndDate.setSelection(endValue);
				endSliderUpdate(sliderEndDate, endDateLabel, endValue);
			} else {
				sliderEndDate.setSelection(99);
				endSliderUpdate(sliderEndDate, endDateLabel, 99);
				startSliderValue=90;
				sliderStartDate.setSelection(90);
			}

		}
		
    	startSliderUpdate(sliderStartDate, startDateLabel, startSliderValue);
    	
    	slidingGroup.layout();
	}


	private void startSliderUpdate(final Slider sliderStartDate, final Label startDateLabel, Integer sliderValue) {
		
		Date maxDate= maxDate();
    	Date minDate= minimumDate();
    	Integer maxSlider= 100;
    	Integer minSlider= 0;
    	Integer perCentValue = sliderValue*100/(maxSlider - minSlider - sliderStartDate.getThumb());
    
    	Long diffDateInDays = (maxDate.getTime() - minDate.getTime())/(1000*3600*24);
    	Long nbDaySinceMin  = perCentValue * diffDateInDays /100;
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(minDate);
    	calendar.add(Calendar.DAY_OF_YEAR, nbDaySinceMin.intValue());
    	slidingStartDate = calendar.getTime();
    	
    	startDateLabel.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingStartDate));
    	startDateLabel.setFont(MainGui.DEFAULTFONT);
	}
	

	private void endSliderUpdateConditional( Slider sliderEndDate,  Label endDateLabel,  Slider sliderStartDate, Label startDateLabel) {
		
		synchronized (sliderSelection) {
			sliderSelection = true;
		}
	
		
		Integer sliderValue = sliderEndDate.getSelection();
    	
		if (sliderEndDate.getSelection() - sliderStartDate.getSelection() <= 10) {
			if (sliderStartDate.getSelection() >= 10) {
				int startValue = sliderValue-10;
				sliderStartDate.setSelection(startValue);
				startSliderUpdate(sliderStartDate, startDateLabel, startValue);
			} else {
				sliderStartDate.setSelection(0);
				startSliderUpdate(sliderStartDate, startDateLabel, 0);
				sliderValue = 10;
				sliderEndDate.setSelection(10);
			}
    	}
    	
    	endSliderUpdate(sliderEndDate, endDateLabel, sliderValue);
    	
    	slidingGroup.layout();
	}


	private void endSliderUpdate(Slider sliderEndDate, Label endDateLabel, Integer sliderValue) {
		
		Date maxDate= maxDate();
    	Date minDate= minimumDate();
    	Integer maxSlider= 100;
    	Integer minSlider= 0;
    	Integer perCentValue = sliderValue*100/ (maxSlider - minSlider - sliderEndDate.getThumb());
    
    	Long diffDate = (maxDate.getTime() - minDate.getTime())/(1000*3600*24);
    	Long nbDaySinceMin  = perCentValue * diffDate /100;
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(minDate);
    	calendar.add(Calendar.DAY_OF_YEAR, nbDaySinceMin.intValue());
    	slidingEndDate = calendar.getTime();
    	
    	endDateLabel.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingEndDate));
    	endDateLabel.setFont(MainGui.DEFAULTFONT);
	}
	
	protected void rootShellClosed(DisposeEvent evt) {
		
		synchronized (closeRequested) {
			closeRequested = true;
			SWT_AWT.getFrame(mainChartComposite).dispose();	
		}
		
	}


	private Date minimumDate() {
    	if (firstStartDate == null) {
    		Calendar  calendar  = Calendar.getInstance();
        	calendar.add(Calendar.YEAR, -1);
    		firstStartDate = DateFactory.midnithDate(calendar.getTime());
    	}
    	return firstStartDate;
	}
	
	private Date maxDate() {
    	if (lastEndDate == null) {
    		lastEndDate = DateFactory.midnithDate(EventSignalConfig.getNewDate());
    	}
    	return lastEndDate;
	}


	public void setComposite(PortfolioComposite composite) {
		this.portfolioComposite = composite;
	}

	@Override
	public void setCursor(Cursor arg0) {
		super.setCursor(arg0);
		
		java.awt.Cursor awtPredefinedCursor;
		if (arg0.equals(CursorFactory.getCursor(SWT.CURSOR_WAIT)) || arg0.equals(CursorFactory.getCursor(SWT.CURSOR_APPSTARTING))) {
			
			if (arg0.equals(CursorFactory.getCursor(SWT.CURSOR_APPSTARTING))) {
				awtPredefinedCursor = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR);
			} else {
				awtPredefinedCursor = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR);
			}

		} else {
			awtPredefinedCursor = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR);
		}
		
		SWT_AWT.getFrame(mainChartComposite).setCursor(awtPredefinedCursor); 
		if ( mainChartPanel.getComponents().length > 0) {
			mainChartPanel.getComponent(0).setCursor(awtPredefinedCursor);
		}
		
	}
	
	public Date getSlidingStartDate() {
		return slidingStartDate;
	}

	public Date getSlidingEndDate() {
		return slidingEndDate;
	}
	

	@Override
	public void initRefreshAction() {
		logComposite.initRefresh(this);
		getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_WAIT));
		chartDisplayStrategy.initRefreshAction();
	}


	@Override
	public void endRefreshAction(List<Exception> exceptions) {
		
		chartDisplayStrategy.endRefreshAction(exceptions);
		try {
			logComposite.endJob(exceptions);
		} finally {
			getParent().getParent().setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
		}	
	}


	@Override
	public void refreshView(List<Exception> exceptions) {
		
		Object[] viewStateParams = hightlitedEventModel.getViewStateParams();
		if (viewStateParams != null && viewStateParams.length == 1) {
			
			checkChartSelectionValidity();
			highLight(highligtedId, (Stock) viewStateParams[0], false);
			
		}
		if (viewStateParams != null && viewStateParams.length == 1 && isVisible()) {
			for (Exception exception : exceptions) {
				if (exception instanceof EventRefreshException) {
					ErrorDialog dialog = new ErrorDialog(getShell(), SWT.NONE, 
							"Couldn't refresh all analysis for "+((Stock) viewStateParams[0]).getFriendlyName()+". Check that date bounds are not out of range.", 
							exceptions.toString());
					exceptions.clear();
					dialog.open();
					break;
				}
			}
		}
		
	}
	

	protected void checkChartSelectionValidity() {
		try {
			chartedEvtDef = EventDefinition.valueOfEventInfo(chartedEvtDef.getEventDefinitionRef());
		} catch (NoSuchFieldException e) {
			LOGGER.warn("Event info as been disabled or deleted. Removing from chart indicators selection : "+chartedEvtDef);
			chartedEvtDef = EventDefinition.ZERO;
		}
		
		//List<EventInfo> toRemove = new ArrayList<EventInfo>();
		Set<EventInfo> updatedChartedEvtDefsTrends = initChartedEvtDefsTrendsSet();
		for (EventInfo eventInfo : chartedEvtDefsTrends) {
			try {
				updatedChartedEvtDefsTrends.add(EventDefinition.valueOfEventInfo(eventInfo.getEventDefinitionRef()));
			} catch (NoSuchFieldException e) {
				LOGGER.warn("Event info as been disabled or deleted. Removing from chart trend selection : "+eventInfo);
				//toRemove.add(eventInfo);
			}
		}
		chartedEvtDefsTrends = updatedChartedEvtDefsTrends;
	}


	@Override
	public Date getAnalysisStartDate() {
	
		Calendar slidingStartCal = Calendar.getInstance();
		slidingStartCal.setTime(this.slidingStartDate);
		QuotationsFactories.getFactory().incrementDateExtraLarge(slidingStartCal, -1);
		return slidingStartCal.getTime();
	}


	private void sliderChangesApply() {
		if (sliderSelection) {
			synchronized (sliderSelection) {
				sliderSelection = false;
				updateCharts(listShares, false, true);
				portfolioComposite.slidingDateChange();
			}
		}
	}
	

	public void resetChart() {
		this.chartDisplayStrategy.resetChart();
	}


	public Integer getHighligtedId() {
		return highligtedId;
	}


	public void setHighligtedId(Integer highligtedId) {
		this.highligtedId = highligtedId;
	}


	public EventModel<RefreshChartHightlited> getHightlitedEventModel() {
		return hightlitedEventModel;
	}


	public void setHightlitedEventModel(EventModel<RefreshChartHightlited> hightlitedEventModel) {
		this.hightlitedEventModel = hightlitedEventModel;
	}


	public Set<EventInfo> getChartedEvtDefsTrends() {
		return chartedEvtDefsTrends;
	}

	public EventInfo getChartedEvtDef() {
		return chartedEvtDef;
	}


	public void setChartedEvtDef(EventInfo chartedEvtDef) {
		this.chartedEvtDef = chartedEvtDef;
	}


	public ChartMain getMainChartWraper() {
		return mainChartWraper;
	}


	public StripedCloseFunction getStripedCloseFunction() {
		return stripedCloseFunction;
	}


	public List<SlidingPortfolioShare> getListShares() {
		return listShares;
	}


	public LogComposite getLogComposite() {
		return logComposite;
	}


	public void setStripedCloseFunction(StripedCloseFunction stripedCloseFunction) {
		this.stripedCloseFunction = stripedCloseFunction;
	}


	public void setChartDisplayStrategy(ChartDisplayStrategy chartDisplayStrategy) {
		this.chartDisplayStrategy = chartDisplayStrategy;
	}


	public Group getPopusGroup() {
		return popusGroup;
	}
	
}