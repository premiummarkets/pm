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

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TypedListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.labels.XYToolTipGenerator;

import com.finance.pms.ActionDialogAction;
import com.finance.pms.CursorFactory;
import com.finance.pms.LogComposite;
import com.finance.pms.MainGui;
import com.finance.pms.MainPMScmd;
import com.finance.pms.RefreshableView;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.EventModel;
import com.finance.pms.datasources.RefreshChartHighlighted;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.InvalidParameterException;
import com.finance.pms.events.calculation.SelectedIndicatorsCalculationService;
import com.finance.pms.events.scoring.chartUtils.MyTimeSeriesCollection;
import com.finance.pms.portfolio.gui.ActionDialogForm;
import com.finance.pms.portfolio.gui.PortfolioComposite;
import com.finance.pms.portfolio.gui.SlidingPortfolioShare;
import com.finance.pms.portfolio.gui.charts.ChartDisplayStrategy.PopupType;

/**
 * The Class ChartsComposite.
 * 
 * @author Guillaume Thoreton
 */
public class ChartsComposite extends SashForm implements RefreshableView {

	private final class MyChartPanel extends ChartPanel {
		private static final long serialVersionUID = 1L;

		public MyChartPanel(JFreeChart chart, boolean properties, boolean save, boolean print, boolean zoom, boolean tooltips) {
			super(chart, properties, save, print, zoom, tooltips);
		}

		@Override
		public void restoreAutoBounds(){
			// Do nothing (disable mouse left drag zoom)
		}

		@Override
		public String getToolTipText(MouseEvent e) {
			String result = null;
		    if (this.getChartRenderingInfo() != null) {
		        EntityCollection entities = this.getChartRenderingInfo().getEntityCollection();
		        if (entities != null) {
		            Insets insets = getInsets();
		            ChartEntity entity = entities.getEntity(
		                    (int) ((e.getX() - insets.left) / this.getScaleX()),
		                    (int) ((e.getY() - insets.top) / this.getScaleY()));
		            if (entity != null) {
		            	if (entity instanceof XYItemEntity && ((XYItemEntity) entity).getDataset() instanceof MyTimeSeriesCollection) {
		            		XYItemEntity xYItemEntity = (XYItemEntity) entity;
							XYToolTipGenerator xyToolTipGenerator = ((MyTimeSeriesCollection)xYItemEntity.getDataset()).getToolTipGenerator(xYItemEntity.getSeriesIndex());
		            		if (xyToolTipGenerator != null) {
		            			result = xyToolTipGenerator.generateToolTip(xYItemEntity.getDataset(), xYItemEntity.getSeriesIndex(), xYItemEntity.getItem());
		            		} else {
		            			result = entity.getToolTipText();
		            		}
		            	} else {
		            		result = entity.getToolTipText();
		            	}
		            }
		        }
		    }
		    return result;
		}
	}


	private static final int MIN_SLIDER_SELECTION = 0;
	private static final int MAX_SLIDER_SELECTION = 100;
	private static final int THUMB_SLIDER_SIZE = 1;

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
	private Map<String, ChartDisplayStrategy> chartDisplayStrategyCache;
	private ChartDisplayStrategy chartDisplayStrategy;

	private List<SlidingPortfolioShare> currentTabShareList;

	private Integer highligtedId;
	private EventModel<RefreshChartHighlighted, Stock> hightlitedEventModel;

	private ChartPanel mainChartPanel;
	private Composite mainChartComposite;
	private ChartMain mainChartWraper;

	//TODO mv to ChartIndicatorDisplay Strategy (+ getInstance instead of new one Strategy object to keep state)
	private SortedSet<EventInfo> chartedEvtDefsTrends;

	public Group chartBoutonsGroup;
	
	private Group popusGroup;

	private Group slidingGroup;
	
	private Slider sliderStart;
	private Button buttonStart;

	private Slider sliderEnd;
	private Button buttonEnd;

	private Date slidingStartDate;
	private Date slidingEndDate;
	private Date buttonStartDate;
	private Date buttonEndDate;
	private Boolean sliderSelection;

	private Point panelClickPosition;
	private Boolean isInPanelChartSlider;

	public ChartsComposite(Composite parent, int style, LogComposite logComposite) {
		super(parent, style);

		this.currentTabShareList = new ArrayList<SlidingPortfolioShare>();

		try {
			//this.slidingEndDate = DateFactory.midnithDate(new SimpleDateFormat("yyyyMMdd").parse("20221003"));
			String savedButtonEndDate = MainPMScmd.getMyPrefs().get("ui.button.enddate", null);
			if (savedButtonEndDate != null) {
				this.buttonEndDate = DateFormat.getDateInstance(DateFormat.MEDIUM).parse(savedButtonEndDate);
			} else {
				this.buttonEndDate = DateFactory.midnithDate(DateFactory.getNowEndDate());
			}
			this.slidingEndDate = this.buttonEndDate;
			//this.slidingStartDate = DateFactory.midnithDate(new SimpleDateFormat("yyyyMMdd").parse("20180903"));
			String savedButtonStartDate = MainPMScmd.getMyPrefs().get("ui.button.startdate", null);
			if (savedButtonStartDate != null) {
				this.buttonStartDate = DateFormat.getDateInstance(DateFormat.MEDIUM).parse(savedButtonStartDate);
			} else {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(this.buttonEndDate);
				calendar.add(Calendar.YEAR, -1);
				this.buttonStartDate = DateFactory.midnithDate(calendar.getTime());
			}
			this.slidingStartDate = this.buttonStartDate;
		} catch (ParseException e) {
			LOGGER.error(e, e);
		}

		this.logComposite = logComposite;
		this.hightlitedEventModel = EventModel.getInstance(new RefreshChartHighlighted(), logComposite);

		this.chartedEvtDefsTrends = initChartedEvtDefsTrendsSet();

		this.sliderSelection = false;

		this.initGUI();
		
		chartDisplayStrategyCache = new HashMap<>();
		chartDisplayStrategy = new ChartIndicatorDisplay(this, logComposite); //new ChartPerfDisplay(this);
		addChartDisplayStrategyCache(chartDisplayStrategy.getClass().getName(), chartDisplayStrategy);

	}
	
	public ChartDisplayStrategy getChartDisplayStrategyCache(String name) {
		return chartDisplayStrategyCache.get(name);
	}
	
	public void addChartDisplayStrategyCache(String name, ChartDisplayStrategy chartDisplayStrategy) {
		chartDisplayStrategyCache.put(name, chartDisplayStrategy);
	}

	protected TreeSet<EventInfo> initChartedEvtDefsTrendsSet() {
		return new TreeSet<EventInfo>(new Comparator<EventInfo>() {
			@Override
			public int compare(EventInfo o1, EventInfo o2) {
				return o1.info().compareTo(o2.info());
			}
		});

	}

	public void resetChart() {
		this.currentTabShareList = new ArrayList<SlidingPortfolioShare>();
		getHightlitedEventModel().resetOtherViewParams();
		setHighligtedId(null);
		chartDisplayStrategy.resetChart(true);

	}

	public void updateChartsWith(List<SlidingPortfolioShare> listShares) {

		this.currentTabShareList = listShares;
		updateCharts(false);

	}

	public void rowSelectioHighLight(Integer idx, Stock selectedShare, Boolean recalculationGranted) {
		chartDisplayStrategy.cleanPreviousStockSelection();
		chartDisplayStrategy.highLight(idx, selectedShare, recalculationGranted);
		
	}

	void updateCharts(Boolean grantEventsUpdate, PopupType... popupTypes) {

		chartDisplayStrategy.getStripedCloseFunction().updateStartDate(slidingStartDate);
		chartDisplayStrategy.getStripedCloseFunction().updateEndDate(slidingEndDate);

		int previousSelection = retreivePreviousSelection();
		if (previousSelection != -1) {
			getHightlitedEventModel().setViewParamRoot(getCurrentTabShareList().get(previousSelection).getStock());
			setHighligtedId(previousSelection);
		} else {
			getHightlitedEventModel().resetOtherViewParams();
			setHighligtedId(null);
		}
		chartDisplayStrategy.resetChart(false, popupTypes);

		Stock viewStateParams = hightlitedEventModel.getViewParamRoot();
		if (viewStateParams != null) {
			LOGGER.info("Calling highLight from updateCharts.");
			chartDisplayStrategy.highLight(getHighligtedId(), viewStateParams, grantEventsUpdate, popupTypes);
		}

	}


	private Integer retreivePreviousSelection() {
		return portfolioComposite.getCurrentShareSelectionIdx();
	}

	public void myPack() {
		Rectangle chartShashBounds = this.getClientArea();

		org.eclipse.swt.graphics.Point chartPrefSize = this.chartBoutonsGroup.computeSize(SWT.DEFAULT, SWT.DEFAULT); 
		org.eclipse.swt.graphics.Point chartCompositeSize = this.chartBoutonsGroup.computeSize(chartPrefSize.x, Math.max(50,chartPrefSize.y));
		this.chartBoutonsGroup.setSize(chartCompositeSize);
		Rectangle chartButtonsBounds = this.chartBoutonsGroup.getBounds();
		int xChart = 100*chartButtonsBounds.height/chartShashBounds.height;
		if ((100 -xChart) < 20) {
			xChart=30;
		}
		this.setWeights(new int[]{100-xChart, xChart});
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

			this.addListener(SWT.Hide, new Listener() {

				@Override
				public void handleEvent(Event event) {
					ChartsComposite.this.shutDownDisplay();
				}
			});

			{
				try {
					// Set cross-platform Java L&F (also called "Metal")
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				} 
				catch (UnsupportedLookAndFeelException e) {
					LOGGER.error(e);
				}
				catch (ClassNotFoundException e) {
					LOGGER.error(e);
				}
				catch (InstantiationException e) {
					LOGGER.error(e);
				}
				catch (IllegalAccessException e) {
					LOGGER.error(e);
				}

				mainChartComposite = new Composite(this, SWT.EMBEDDED | SWT.NO_BACKGROUND);
				final Frame chartFrame = SWT_AWT.new_Frame(mainChartComposite);
				final Panel rootHeavyPanel = new Panel();
				rootHeavyPanel.setLayout(new BorderLayout());
				chartFrame.add(rootHeavyPanel);

				mainChartWraper = new ChartMain(ChartsComposite.DEFAULT_START_DATE, JFreeChartTimePeriod.DAY);
				mainChartPanel = new MyChartPanel(mainChartWraper.initChart(this.slidingStartDate, this.slidingEndDate), true, true, true, false, true);
				mainChartWraper.setChartPanel(mainChartPanel);
				mainChartWraper.setFrame(chartFrame);
				
				mainChartPanel.setMouseZoomable(false, false);
				mainChartPanel.setMinimumDrawWidth(0);
				mainChartPanel.setMinimumDrawHeight(0);
				mainChartPanel.setMaximumDrawWidth(Display.getCurrent().getClientArea().width);
				mainChartPanel.setMaximumDrawHeight(Display.getCurrent().getClientArea().height);

				mainChartPanel.getPopupMenu().addSeparator();
				JMenuItem deleteVLines = new JMenuItem("Remove vertical lines");
				deleteVLines.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						chartDisplayStrategy.removeVLines();
					}
				});
				mainChartPanel.getPopupMenu().add(deleteVLines);
				JMenuItem deleteHLines = new JMenuItem("Remove horizontal lines");
				deleteHLines.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						chartDisplayStrategy.removeHLines();
					}
				});
				mainChartPanel.getPopupMenu().add(deleteHLines);

				mainChartPanel.getPopupMenu().addSeparator();
				JMenuItem lookAndFeelInfoItem = new JMenuItem("Look and feel Info");
				lookAndFeelInfoItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						LOGGER.info(UIManager.getLookAndFeel());
					}
				});
				mainChartPanel.getPopupMenu().add(lookAndFeelInfoItem);

				mainChartPanel.addMouseListener(new ClickListener() {

					@Override
					public void singleClick(final MouseEvent event) {

						Display.getDefault().asyncExec(new Runnable() {
							@Override
							public void run() {
								Boolean isSlidingArea = getMainChartWraper().isSlidingArea(mainChartComposite.getSize().y, event.getPoint().y);
								if (isSlidingArea) return;

								int button = event.getButton();
								if (button == MouseEvent.BUTTON1) {
									Point2D clickPoint = mainChartPanel.translateScreenToJava2D(event.getPoint());
									Rectangle2D plotArea = mainChartPanel.getScreenDataArea();

									if (plotArea.getMaxY() > clickPoint.getY() && clickPoint.getY() > plotArea.getY() && plotArea.getMaxX() > clickPoint.getX() && clickPoint.getX() > plotArea.getX()) {
										chartDisplayStrategy.addVLineAt(clickPoint, plotArea);
									}

								}
							}
						});
					}

					@Override
					public void doubleClick(final MouseEvent event) {

						Display.getDefault().syncExec(new Runnable() {
							@Override
							public void run() {
								Boolean isSlidingArea = getMainChartWraper().isSlidingArea(mainChartComposite.getSize().y, event.getPoint().y);
								if (isSlidingArea) return;

								int button = event.getButton();
								if (button == MouseEvent.BUTTON1){
									Point2D clickPoint = getPointCoordinates(event.getPoint());
									Rectangle2D plotArea = mainChartPanel.getScreenDataArea();

									if (plotArea.getMaxY() > clickPoint.getY() && clickPoint.getY() > plotArea.getY() && plotArea.getMaxX() > clickPoint.getX() && clickPoint.getX() > plotArea.getX()) {
										chartDisplayStrategy.removeVLineAt(clickPoint, plotArea);
										chartDisplayStrategy.removeHLineAt(clickPoint, plotArea);
									}
								}
							}
						});
					}

				});

				//Slider
				mainChartPanel.addMouseListener(new MouseAdapter() {

					@Override
					public void mousePressed(final MouseEvent e) {
						panelClickPosition = e.getPoint();
						Display.getDefault().asyncExec(new Runnable() {

							@Override
							public void run() {
								Boolean isSlidingArea = getMainChartWraper().isSlidingArea(mainChartComposite.getSize().y, e.getPoint().y);
								if (isSlidingArea) {
									isInPanelChartSlider = true;
								} else {
									isInPanelChartSlider = false;
								}
							}

						});
					}

					@Override
					public void mouseReleased(final MouseEvent e) {
						Display.getDefault().asyncExec(new Runnable() {

							@Override
							public void run() {
								if (isInPanelChartSlider != null && isInPanelChartSlider && panelClickPosition != null) {
									int indicChartSlideIncrement = (int) ((((double) panelClickPosition.y - e.getPoint().y)/(double) mainChartComposite.getSize().y)*100);
									chartDisplayStrategy.slideChart(indicChartSlideIncrement);
								}
								isInPanelChartSlider = false;
							}

						});
					}

				});


//				//focus bizz (useful?)
//				mainChartPanel.addMouseMotionListener(new MouseMotionListener() {
//
//					@Override
//					public void mouseMoved(final MouseEvent e) {
//
//						//Sliding
//						if (!closeRequested) {
//							Display.getDefault().asyncExec(new Runnable() {
//
//								@Override
//								public void run() {
//
//									if (!closeRequested) {
//										try {
//											Cursor cursor = ChartsComposite.this.getCursor();
//											if (cursor == null || (!cursor.equals(CursorFactory.getCursor(SWT.CURSOR_WAIT)) && !cursor.equals(CursorFactory.getCursor(SWT.CURSOR_APPSTARTING)))) {	
//												Boolean isSlidingArea = getMainChartWraper().isSlidingArea(mainChartComposite.getSize().y, e.getPoint().y);
//												if (isSlidingArea) {
//													ChartsComposite.this.setCursor(CursorFactory.getCursor(SWT.CURSOR_SIZENS));
//												} else {
//													ChartsComposite.this.setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
//												}
//
//											}
//
//										} catch (Throwable e) {
//											try {
//												ChartsComposite.this.setCursor(CursorFactory.getCursor(SWT.CURSOR_ARROW));
//											} catch (Throwable e1) {
//												LOGGER.warn(e1,e1);
//											}
//											LOGGER.warn(e,e);
//										}
//									}
//
//								}
//							});
//						}
//
//
//						//Gain focus mgnt
//						if (!closeRequested) {
//							Display.getDefault().asyncExec(new Runnable() {
//								public void run() {
//									try {
//										if (!closeRequested) {
//											if (!mainChartComposite.isDisposed() && !mainChartComposite.isFocusControl()) {
//												int cpt = 0;
//												while (chartPanelFocusGain && cpt < 200) {
//													Thread.sleep(10);
//													cpt++;
//												}
//												if (chartPanelFocusGain && !mainChartComposite.isDisposed()) {
//													mainChartComposite.forceFocus();
//												}
//											}
//										}
//									} catch (Throwable e) {
//										LOGGER.warn(e,e);
//									}
//								}
//							});
//						}
//
//					}
//
//					@Override
//					public void mouseDragged(MouseEvent e) {
//						//Nothing
//					}
//
//				});

				mainChartComposite.addKeyListener(new org.eclipse.swt.events.KeyListener() {

					@Override
					public void keyReleased(org.eclipse.swt.events.KeyEvent e) {
						if (((e.stateMask & SWT.CTRL) == SWT.CTRL) && ((e.stateMask & SWT.ALT) == SWT.ALT) && (e.keyCode == 'p')) {
							try {
								chartDisplayStrategy.exportBarChartPng();
							} catch (InvalidParameterException exception) {
								chartDisplayStrategy.showPopupDialog( exception.getMessage(), "Ok", null, null);
							}
						}
						if (e.keyCode == SWT.ARROW_UP) {
							chartDisplayStrategy.slideChart(+1);
						}
						if (e.keyCode == SWT.ARROW_DOWN) {
							chartDisplayStrategy.slideChart(-1);
						}
					}

					@Override
					public void keyPressed(org.eclipse.swt.events.KeyEvent e) {
						//
					}
				});

				mainChartComposite.addMouseTrackListener(new MouseTrackListener() {

					@Override
					public void mouseHover(org.eclipse.swt.events.MouseEvent e) {
					}

					@Override
					public void mouseExit(org.eclipse.swt.events.MouseEvent e) {
					}

					@Override
					public void mouseEnter(org.eclipse.swt.events.MouseEvent e) {
						mainChartPanel.requestFocusInWindow();
					}
				});

				rootHeavyPanel.add(mainChartPanel);
				chartFrame.pack();
				chartFrame.setVisible(true);

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

				chartBoutonsGroup.setText("Portfolio charting: " + SelectedIndicatorsCalculationService.getAnalysisName());
				chartBoutonsGroup.setFont(MainGui.DEFAULTFONT);
				chartBoutonsGroup.setBackground(innerBgColor);

				{
					popusGroup = new Group(chartBoutonsGroup, SWT.NONE);
					GridData popusGroupData = new GridData(SWT.FILL, SWT.FILL, true, false);
					popusGroup.setLayoutData(popusGroupData);
					popusGroup.setBackground(innerBgColor);
					RowLayout popusGroupL = new RowLayout(SWT.HORIZONTAL);
					popusGroupL.justify = true;
					popusGroupL.fill = true;
					popusGroupL.wrap = false;
					popusGroupL.marginHeight = 0;
					popusGroup.setLayout(popusGroupL);
				}

				//Sliding
				{

					slidingGroup = new Group(chartBoutonsGroup, SWT.NONE);
					GridData slidingGroupData = new GridData(SWT.FILL, SWT.FILL,true, false);
					slidingGroup.setLayoutData(slidingGroupData);
					slidingGroup.setBackground(innerBgColor);
					GridLayout slidingGroupL = new GridLayout();
					slidingGroupL.numColumns = 5;
					slidingGroupL.marginHeight = 0;
					slidingGroupL.verticalSpacing = 	0;
					slidingGroup.setLayout(slidingGroupL);
					///start
					{
//						startDateLabel = new Label(slidingGroup, SWT.NONE);
//						GridData startOneYearBackData = new GridData(SWT.BEGINNING, SWT.FILL,false, false);
//						startOneYearBackData.horizontalSpan=2;
//						startDateLabel.setLayoutData(startOneYearBackData);
//						startDateLabel.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingStartDate)+"");
//						startDateLabel.setBackground(innerBgColor);
//						startDateLabel.setFont(MainGui.DEFAULTFONT);
						
						buttonStart = new Button(slidingGroup, SWT.PUSH);
						GridData startOneYearBackData = new GridData(SWT.BEGINNING, SWT.FILL,false, false);
						startOneYearBackData.horizontalSpan=2;
						buttonStart.setLayoutData(startOneYearBackData);
				        buttonStart.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingStartDate) + "");
				        buttonStart.setBackground(innerBgColor);
				        buttonStart.setFont(MainGui.DEFAULTFONT);
				        buttonStart.addSelectionListener(new SelectionAdapter() {
				              @Override     
				              public void widgetSelected(SelectionEvent e) {
				      			final Shell dialog = new Shell (ChartsComposite.this.getShell(), SWT.DIALOG_TRIM);
				    			dialog.setLayout (new GridLayout (3, false));

				    			final DateTime swtDT = new DateTime (dialog, SWT.CALENDAR | SWT.BORDER);
				    			Calendar calendar = Calendar.getInstance();
				    			calendar.setTime(buttonStartDate);
				    			swtDT.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
				    			new Label (dialog, SWT.NONE);
				    			Button ok = new Button (dialog, SWT.PUSH);
				    			ok.setText ("OK");
				    			ok.setLayoutData(new GridData (SWT.FILL, SWT.CENTER, false, false));
				    			ok.addSelectionListener (new SelectionAdapter() {
				    				public void widgetSelected(SelectionEvent e) {
			                    	  Calendar calendar = Calendar.getInstance();
			                    	  calendar.set(swtDT.getYear(), swtDT.getMonth(), swtDT.getDay());
			                    	  buttonStartDate = DateFactory.midnithDate(calendar.getTime());
			                    	  
			                    	  String format = DateFormat.getDateInstance(DateFormat.MEDIUM).format(buttonStartDate);
				                  	  MainPMScmd.getMyPrefs().put("ui.button.startdate", format);
				                	  MainPMScmd.getMyPrefs().flushy();
			                		
									  sliderStart.setSelection(MIN_SLIDER_SELECTION);
			                    	  startSliderUpdateConditional(buttonStartDate, buttonEndDate, sliderStart, buttonStart, sliderEnd, buttonEnd);
			                    	  
			                    	  buttonStart.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingStartDate));
			                    	  buttonStart.setFont(MainGui.DEFAULTFONT);
			                    	  
			                    	  sliderChangesApply();
				    				  dialog.close ();
				    				}
				    			}
				    			);
				    			dialog.setDefaultButton (ok);
				    			dialog.pack ();
				    			dialog.open ();
				             }
				        });
				        buttonStart.addListener(SWT.MouseDown, new Listener() {
				        	@Override
				        	public void handleEvent(Event event) {
				        		if (event.button == 3) { // Right mouse button
				        			ActionDialogForm dialog = new ActionDialogForm(buttonStart.getShell(), "Ok", "dd/MM/yyyy", "Please enter a date");
				        			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				        			Text dateText = new Text(dialog.getParent(), SWT.NONE | SWT.CENTER | SWT.BORDER);
				        			dateText.setText(dateFormat.format(buttonStartDate));
				        			ActionDialogAction setDateAction = new ActionDialogAction() {
				        				@Override
				        				public void action() {
				        					dialog.values[0] = dateText.getText();
				        					try {
				        						dateFormat.setLenient(false);
				        						String input = (String) dialog.values[0];
				        						Date enteredDate = dateFormat.parse(input);
				        						
				        						// Update buttonStartDate and sliderStart
					        					buttonStartDate = DateFactory.midnithDate(enteredDate);
					        					String formattedDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(buttonStartDate);
					        					MainPMScmd.getMyPrefs().put("ui.button.startdate", formattedDate);
					        					MainPMScmd.getMyPrefs().flushy();

					        					sliderStart.setSelection(MIN_SLIDER_SELECTION);
					        					startSliderUpdateConditional(buttonStartDate, buttonEndDate, sliderStart, buttonStart, sliderEnd, buttonEnd);

					        					buttonStart.setText(formattedDate);
					        					buttonStart.setFont(MainGui.DEFAULTFONT);
					        					
					        					sliderChangesApply();
				        					} catch (ParseException e) {
				        						//None
				        					}
				        				};
				        			};

				        			dialog.setControl(dateText);
				        			dialog.setAction(setDateAction);
				        			dialog.open();
				        		}
				        	}
				        });
					}
					///sliding sliders
					Composite slidingSliderGroup = new Composite(slidingGroup, SWT.NONE);
					slidingSliderGroup.setSize(1000, SWT.DEFAULT);
					GridData slidersGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
					slidersGridData.verticalSpan = 2;
					slidingSliderGroup.setLayoutData(slidersGridData);
					slidingSliderGroup.setBackground(innerBgColor);
					slidingSliderGroup.setForeground(innerBgColor);
					FillLayout slidingSliderGroupL = new FillLayout(SWT.VERTICAL);
					slidingSliderGroup.setLayout(slidingSliderGroupL);
					{
						sliderStart = new Slider(slidingSliderGroup, SWT.HORIZONTAL);
						sliderStart.setThumb(THUMB_SLIDER_SIZE);
						sliderStart.setMinimum(MIN_SLIDER_SELECTION);
						sliderStart.setMaximum(MAX_SLIDER_SELECTION);
						sliderStart.setSelection(MIN_SLIDER_SELECTION);
						sliderStart.addListener(SWT.MouseExit, new Listener() {

							public void handleEvent(Event arg0) {
								sliderChangesApply();
							}

						});
					}	
					{
						sliderEnd = new Slider(slidingSliderGroup, SWT.HORIZONTAL);
						sliderEnd.setThumb(THUMB_SLIDER_SIZE);
						sliderEnd.setMinimum(MIN_SLIDER_SELECTION + THUMB_SLIDER_SIZE);
						sliderEnd.setMaximum(MAX_SLIDER_SELECTION + THUMB_SLIDER_SIZE);
						sliderEnd.setSelection(MAX_SLIDER_SELECTION);
						sliderEnd.addListener(SWT.MouseExit, new Listener() {

							public void handleEvent(Event arg0) {
								sliderChangesApply();
							}

						});
					}
					//end
					{							
						buttonEnd = new Button(slidingGroup, SWT.PUSH);
						GridData endOneYearBackData = new GridData(SWT.BEGINNING, SWT.FILL,false, false);
						endOneYearBackData.horizontalSpan=2;
						buttonEnd.setLayoutData(endOneYearBackData);
						buttonEnd.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingEndDate) + "");
						buttonEnd.setBackground(innerBgColor);
						buttonEnd.setFont(MainGui.DEFAULTFONT);
						buttonEnd.addSelectionListener(new SelectionAdapter() {
							@Override     
							public void widgetSelected(SelectionEvent e) {
								final Shell dialog = new Shell (ChartsComposite.this.getShell(), SWT.DIALOG_TRIM);
								dialog.setLayout (new GridLayout (3, false));

								final DateTime swtDT = new DateTime (dialog, SWT.CALENDAR | SWT.BORDER);
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(slidingEndDate);
								swtDT.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
								new Label (dialog, SWT.NONE);
								Button ok = new Button (dialog, SWT.PUSH);
								ok.setText ("OK");
								ok.setLayoutData(new GridData (SWT.FILL, SWT.CENTER, false, false));
								ok.addSelectionListener (new SelectionAdapter() {
									public void widgetSelected(SelectionEvent e) {
										Calendar calendar = Calendar.getInstance();
										calendar.set(swtDT.getYear(), swtDT.getMonth(), swtDT.getDay());
										buttonEndDate = DateFactory.midnithDate(calendar.getTime());

										String format = DateFormat.getDateInstance(DateFormat.MEDIUM).format(buttonEndDate);
										MainPMScmd.getMyPrefs().put("ui.button.enddate", format);
										MainPMScmd.getMyPrefs().flushy();
										
										sliderEnd.setSelection(MAX_SLIDER_SELECTION);
										endSliderUpdateConditional(buttonStartDate, buttonEndDate, sliderEnd, buttonEnd, sliderStart, buttonStart);
										buttonEnd.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingEndDate));
										buttonEnd.setFont(MainGui.DEFAULTFONT);
										sliderChangesApply();
										dialog.close ();
									}
								}
				    			);
				    			dialog.setDefaultButton (ok);
				    			dialog.pack ();
				    			dialog.open ();
				             }
				        });
						buttonEnd.addListener(SWT.MouseDown, new Listener() {
				        	@Override
				        	public void handleEvent(Event event) {
				        		if (event.button == 3) { // Right mouse button
				        			ActionDialogForm dialog = new ActionDialogForm(buttonEnd.getShell(), "Ok", "dd/MM/yyyy", "Please enter a date");
				        			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				        			Text dateText = new Text(dialog.getParent(), SWT.NONE | SWT.CENTER | SWT.BORDER);
				        			dateText.setText(dateFormat.format(buttonEndDate));
				        			ActionDialogAction setDateAction = new ActionDialogAction() {
				        				@Override
				        				public void action() {
				        					dialog.values[0] = dateText.getText();
				        					try {
				        						dateFormat.setLenient(false);
				        						String input = (String) dialog.values[0];
				        						Date enteredDate = dateFormat.parse(input);
				     
				        						// Update buttonStartDate and sliderStart
				        						buttonEndDate = DateFactory.midnithDate(enteredDate);

												String format = DateFormat.getDateInstance(DateFormat.MEDIUM).format(buttonEndDate);
												MainPMScmd.getMyPrefs().put("ui.button.enddate", format);
												MainPMScmd.getMyPrefs().flushy();
												
												sliderEnd.setSelection(MAX_SLIDER_SELECTION);
												endSliderUpdateConditional(buttonStartDate, buttonEndDate, sliderEnd, buttonEnd, sliderStart, buttonStart);
												buttonEnd.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingEndDate));
												buttonEnd.setFont(MainGui.DEFAULTFONT);
												sliderChangesApply();
				        					} catch (ParseException e) {
				        						//None
				        					}
				        				};
				        			};

				        			dialog.setControl(dateText);
				        			dialog.setAction(setDateAction);
				        			dialog.open();
				        		}
				        	}
				        });
					}

					//button start
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
								buttonStartDate = calendar.getTime();

								String format = DateFormat.getDateInstance(DateFormat.MEDIUM).format(buttonStartDate);
								MainPMScmd.getMyPrefs().put("ui.button.startdate", format);
								MainPMScmd.getMyPrefs().flushy();

								sliderStart.setSelection(MIN_SLIDER_SELECTION);
								startSliderUpdateConditional(buttonStartDate, slidingEndDate, sliderStart, buttonStart, sliderEnd, buttonEnd);
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
									buttonStartDate = calendar.getTime();

									String format = DateFormat.getDateInstance(DateFormat.MEDIUM).format(buttonStartDate);
									MainPMScmd.getMyPrefs().put("ui.button.startdate", format);
									MainPMScmd.getMyPrefs().flushy();

									sliderStart.setSelection(MIN_SLIDER_SELECTION);
									startSliderUpdateConditional(buttonStartDate, buttonEndDate, sliderStart, buttonStart, sliderEnd, buttonEnd);
								} else {
									chartDisplayStrategy.showPopupDialog("To move the start date further forward, you will need to move the end date first.", "Ok", null, null);
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
					
					//button end
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
									buttonEndDate = calendar.getTime();
									
									String format = DateFormat.getDateInstance(DateFormat.MEDIUM).format(buttonEndDate);
									MainPMScmd.getMyPrefs().put("ui.button.enddate", format);
									MainPMScmd.getMyPrefs().flushy();
									
									sliderEnd.setSelection(MAX_SLIDER_SELECTION);
									endSliderUpdateConditional(buttonEndDate, buttonEndDate, sliderEnd, buttonEnd, sliderStart, buttonStart);
								} else {
									chartDisplayStrategy.showPopupDialog("To move the end date further backward, you will need to move the start date first.", "Ok", null, null);
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
								Date newDate = DateFactory.getNowEndDate();
								if (calendar.getTime().after(newDate)) {
									calendar.setTime(newDate);
								}
								buttonEndDate = calendar.getTime();
								
								String format = DateFormat.getDateInstance(DateFormat.MEDIUM).format(buttonEndDate);
								MainPMScmd.getMyPrefs().put("ui.button.enddate", format);
								MainPMScmd.getMyPrefs().flushy();
								
								sliderEnd.setSelection(MAX_SLIDER_SELECTION);
								endSliderUpdateConditional(buttonStartDate, buttonEndDate, sliderEnd, buttonEnd, sliderStart, buttonStart);
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

					sliderStart.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event event) {
							startSliderUpdateConditional(buttonStartDate, buttonEndDate, sliderStart, buttonStart, sliderEnd, buttonEnd);
						}
					});

					sliderEnd.addListener(SWT.Selection, new Listener() {

						public void handleEvent(Event event) {
							endSliderUpdateConditional(buttonStartDate, buttonEndDate, sliderEnd, buttonEnd, sliderStart, buttonStart);
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

	private void startSliderUpdateConditional(Date minDate, Date maxDate, final Slider sliderStart, final Button startDateButton, Slider sliderEnd, Button endDateButton) {

		synchronized (sliderSelection) {
			sliderSelection = true;
		}

		Integer startSliderValue = sliderStart.getSelection();
		
		//If the end slider date is reach we push the end slider
		if (sliderEnd.getSelection() - sliderStart.getSelection() <= THUMB_SLIDER_SIZE) {
			if (sliderEnd.getSelection() <= MAX_SLIDER_SELECTION - THUMB_SLIDER_SIZE) {
				int endValue = startSliderValue + THUMB_SLIDER_SIZE;
				sliderEnd.setSelection(endValue);
				endSliderUpdate(minDate, maxDate, sliderEnd, endDateButton, endValue);
			} else {
				sliderEnd.setSelection(99);
				endSliderUpdate(minDate, maxDate, sliderEnd, endDateButton, 99);
				startSliderValue = MAX_SLIDER_SELECTION - THUMB_SLIDER_SIZE;
				sliderStart.setSelection(startSliderValue);
			}

		}

		startSliderUpdate(minDate, maxDate, sliderStart, startDateButton, startSliderValue);

		slidingGroup.layout();
	}


	private void startSliderUpdate(Date minDate, Date maxDate, final Slider sliderStart, final Button startDateButton, Integer sliderValue) {

		Integer perCentValue = sliderValue*100 / (MAX_SLIDER_SELECTION - MIN_SLIDER_SELECTION); //int [0,100]

		//Update sliding start date
		Long diffDateInDays = (maxDate.getTime() - minDate.getTime())/(1000*3600*24);
		Long nbDaySinceMin  = perCentValue * diffDateInDays /100;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(minDate);
		calendar.add(Calendar.DAY_OF_YEAR, nbDaySinceMin.intValue());
		slidingStartDate = DateFactory.midnithDate(calendar.getTime());
		
		//Update slider start text
		String format = DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingStartDate);
		startDateButton.setText(format);
		startDateButton.setFont(MainGui.DEFAULTFONT);

	}


	private void endSliderUpdateConditional(Date minDate, Date maxDate, Slider sliderEnd,  Button endDateButton,  Slider sliderStart, Button startDateButton) {

		synchronized (sliderSelection) {
			sliderSelection = true;
		}

		Integer sliderValue = sliderEnd.getSelection();

		if (sliderEnd.getSelection() - sliderStart.getSelection() <= THUMB_SLIDER_SIZE) {
			if (sliderStart.getSelection() >= THUMB_SLIDER_SIZE) {
				int startValue = sliderValue - THUMB_SLIDER_SIZE;
				sliderStart.setSelection(startValue);
				startSliderUpdate(minDate, maxDate, sliderStart, startDateButton, startValue);
			} else {
				sliderStart.setSelection(MIN_SLIDER_SELECTION);
				startSliderUpdate(minDate, maxDate, sliderStart, startDateButton, MIN_SLIDER_SELECTION);
				sliderValue = THUMB_SLIDER_SIZE;
				sliderEnd.setSelection(THUMB_SLIDER_SIZE);
			}
		}

		endSliderUpdate(minDate, maxDate, sliderEnd, endDateButton, sliderValue);

		slidingGroup.layout();
	}


	private void endSliderUpdate(Date minDate, Date maxDate, Slider sliderEndDate, Button endDateButton, Integer sliderValue) {

		Integer perCentValue = sliderValue*100 / (MAX_SLIDER_SELECTION - MIN_SLIDER_SELECTION); //int [0-100]

		Long diffDate = (maxDate.getTime() - minDate.getTime())/(1000*3600*24);
		Long nbDaySinceMin  = perCentValue * diffDate /100;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(minDate);
		calendar.add(Calendar.DAY_OF_YEAR, nbDaySinceMin.intValue());
		slidingEndDate = DateFactory.midnithDate(calendar.getTime());

		String format = DateFormat.getDateInstance(DateFormat.MEDIUM).format(slidingEndDate);
		endDateButton.setText(format);
		endDateButton.setFont(MainGui.DEFAULTFONT);

	}

	protected void rootShellClosed(DisposeEvent evt) {
		ChartsComposite.this.shutDownDisplay();
		mainChartComposite.dispose();
	}


	public void setComposite(PortfolioComposite composite) {
		this.portfolioComposite = composite;

		mainChartPanel.getPopupMenu().addSeparator();
		JMenuItem manQuote = new JMenuItem("Add a quotation");
		manQuote.addActionListener(portfolioComposite.new ManualQuotationSelectionListener());
		mainChartPanel.getPopupMenu().add(manQuote);

	}

	@Override
	public void setCursor(Cursor cursor) {

        super.setCursor(cursor);

		final java.awt.Cursor awtPredefinedCursor;
		boolean isNotDefault = isNotDefault(cursor);
		if (cursor != null && isNotDefault) {

			LOGGER.debug("Cursor charts count +:");
			if (cursor.equals(CursorFactory.getCursor(SWT.CURSOR_APPSTARTING))) {
				awtPredefinedCursor = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR);
			} else if (cursor.equals(CursorFactory.getCursor(SWT.CURSOR_SIZENS))) {
				awtPredefinedCursor = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR);
			} else {
				awtPredefinedCursor = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR);
			}

		} else {
			super.getCursor();
			if (isNotDefault(super.getCursor())) LOGGER.debug("Cursor charts count -:");
			awtPredefinedCursor = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR);
		}

		final Frame frame = SWT_AWT.getFrame(mainChartComposite);
		Runnable runnable = new Runnable() {
			public void run() {
				frame.setCursor(awtPredefinedCursor);
				mainChartPanel.setCursor(awtPredefinedCursor);
				if (mainChartPanel.getComponents().length > 0) {
					mainChartPanel.getComponent(0).setCursor(awtPredefinedCursor);
				}
			}
		};
		
		EventQueue.invokeLater(runnable);
//		try {
//			EventQueue.invokeAndWait(runnable);
//		} catch (Exception e) {
//			LOGGER.error(e, e);
//		}

	}


	private boolean isNotDefault(Cursor cursor) {
		return cursor.equals(CursorFactory.getCursor(SWT.CURSOR_WAIT)) || cursor.equals(CursorFactory.getCursor(SWT.CURSOR_APPSTARTING)) || cursor.equals(CursorFactory.getCursor(SWT.CURSOR_SIZENS));
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

		EventDefinition.refreshMaxPassPrefsEventInfo();

		Stock viewStateParams = hightlitedEventModel.getViewParamRoot();
		if (viewStateParams != null) {
			checkChartSelectionValidity();
			LOGGER.info("Calling highLight from refreshView.");
			chartDisplayStrategy.highLight(highligtedId, viewStateParams, false);
		}
		chartDisplayStrategy.refreshView(exceptions);

		if (isVisible()) {
			Shell[] childrenShells = this.getShell().getShells();
			for (Shell child : childrenShells) {
				if (child.getText().contains("Warning")) child.forceActive();
			}
		}

	}

	private void checkChartSelectionValidity() {

		SortedSet<EventInfo> updatedChartedEvtDefsTrends = initChartedEvtDefsTrendsSet();
		for (EventInfo eventInfo : chartedEvtDefsTrends) {
			try {
				updatedChartedEvtDefsTrends.add(EventDefinition.valueOfEventInfo(eventInfo.getEventDefinitionRef()));
			} catch (NoSuchFieldException e) {
				LOGGER.warn("Event info as been disabled or deleted. Removing from chart trend selection : " + eventInfo);
			}
		}
		chartedEvtDefsTrends = updatedChartedEvtDefsTrends;
	}

	@Override
	public Date getAnalysisStartDate() {
		return this.slidingStartDate;
	}

	@Override
	public Date getAnalysisEndDate() {
		return this.slidingEndDate;
	}

	private void sliderChangesApply() {
		if (sliderSelection) {
			synchronized (sliderSelection) {
				sliderSelection = false;
				chartedEvtDefsTrends.stream().forEach(dsEi -> EventModel.dirtyCacheFor(dsEi, false));
				updateCharts(true);
				portfolioComposite.slidingDateChange();
			}
		}
	}

	public void shutDownDisplay() {
		Shell[] childrenShells = this.getShell().getShells();
		for (Shell child : childrenShells) {
			child.dispose();
		}
		this.chartDisplayStrategy.shutDownDisplay();
	}

	public Integer getHighligtedId() {
		return highligtedId;
	}


	public void setHighligtedId(Integer highligtedId) {
		this.highligtedId = highligtedId;
	}


	public EventModel<RefreshChartHighlighted, Stock> getHightlitedEventModel() {
		return hightlitedEventModel;
	}

	public Set<EventInfo> getChartedEvtDefsTrends() {
		return chartedEvtDefsTrends;
	}


	public ChartMain getMainChartWraper() {
		return mainChartWraper;
	}

	public List<SlidingPortfolioShare> getCurrentTabShareList() {
		return currentTabShareList;
	}


	public LogComposite getLogComposite() {
		return logComposite;
	}


	void updateDisplay(ChartDisplayStrategy chartDisplayStrategy) {
		updateButtonsToolTips(chartDisplayStrategy);
	}

	public void updateDisplay() {
		updateButtonsToolTips(this.chartDisplayStrategy);
	}


	private void updateButtonsToolTips(ChartDisplayStrategy chartDisplayStrategy) {
		chartDisplayStrategy.updateButtonsToolTips();
	}

	public void setChartDisplayStrategy(ChartDisplayStrategy chartDisplayStrategy) {
		
		this.chartDisplayStrategy = chartDisplayStrategy;

		//XXX XXX
		if (MainGui.viewEventsMenuItem.getSelection()) {
			MainGui.viewEventsMenuItem.setSelection(false);
			Listener[] listeners = MainGui.viewEventsMenuItem.getListeners(SWT.Selection);
			((SelectionListener)((TypedListener)listeners[0]).getEventListener()).widgetSelected(null);
			updateCharts(true);
		} else {
			updateCharts(true);
		}

	}

	public Group getPopusGroup() {
		return popusGroup;
	}

	public SlidingPortfolioShare getCurrentLineSelection() {
		return portfolioComposite.getCurrentShareSelection();
	}

	public Point2D getPointCoordinates(Point point) {
		return mainChartPanel.translateScreenToJava2D(point);
	}

	public Rectangle2D getPlotChartDimensions() {
		return this.mainChartPanel.getScreenDataArea();
	}


	public Point getPanelClickPosition() {
		return panelClickPosition;
	}
	
}