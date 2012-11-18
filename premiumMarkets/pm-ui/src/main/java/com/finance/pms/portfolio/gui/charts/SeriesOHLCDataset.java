/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.OHLCDataset;

import com.finance.pms.portfolio.PortfolioShare;

// TODO: Auto-generated Javadoc
/**
 * The Class SeriesOHLCDataset.
 * 
 * @author Guillaume Thoreton
 */
public class SeriesOHLCDataset extends AbstractXYDataset implements OHLCDataset {
  
  /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1562189256696542557L;

/** The series key. */
  private Comparable<PortfolioShare>   key;
  
  /** Storage for the data items. */
  private List<OHLCDataItem> data;
  
  /**
   * Creates a new dataset.
   * 
   * @param serieReference the series key.
   * @param data the data items.
   */
  public SeriesOHLCDataset(Comparable<PortfolioShare> serieReference, ArrayList<OHLCDataItem> data) {
    this.key = serieReference;
    this.data = data;
  }
  
  /**
   * Returns the series key.
   * 
   * @param series the series index (ignored).
   * 
   * @return The series key.
   */
  @Override
public Comparable<PortfolioShare> getSeriesKey(int series) {
    return this.key;
  }
  
  /**
   * Returns the x-value for a data item.
   * 
   * @param series the series index (ignored).
   * @param item the item index (zero-based).
   * 
   * @return The x-value.
   */
  public Number getX(int series, int item) {
    return new Long(this.data.get(item).getDate().getTime());
  }
  
  /**
   * Returns the x-value for a data item as a date.
   * 
   * @param series the series index (ignored).
   * @param item the item index (zero-based).
   * 
   * @return The x-value as a date.
   */
  public Date getXDate(int series, int item) {
    return this.data.get(item).getDate();
  }
  
  /**
   * Returns the y-value.
   * 
   * @param series the series index (ignored).
   * @param item the item index (zero-based).
   * 
   * @return The y value.
   */
  public Number getY(int series, int item) {
    return getClose(series, item);
  }
  
  /**
   * Returns the high value.
   * 
   * @param series the series index (ignored).
   * @param item the item index (zero-based).
   * 
   * @return The high value.
   */
  public Number getHigh(int series, int item) {
    return this.data.get(item).getHigh();
  }
  
  /**
   * Returns the high-value (as a double primitive) for an item within a series.
   * 
   * @param series the series (zero-based index).
   * @param item the item (zero-based index).
   * 
   * @return The high-value.
   */
  public double getHighValue(int series, int item) {
    double result = Double.NaN;
    Number high = getHigh(series, item);
    if (high != null) {
      result = high.doubleValue();
    }
    return result;
  }
  
  /**
   * Returns the low value.
   * 
   * @param series the series index (ignored).
   * @param item the item index (zero-based).
   * 
   * @return The low value.
   */
  public Number getLow(int series, int item) {
    return this.data.get(item).getLow();
  }
  
  /**
   * Returns the low-value (as a double primitive) for an item within a series.
   * 
   * @param series the series (zero-based index).
   * @param item the item (zero-based index).
   * 
   * @return The low-value.
   */
  public double getLowValue(int series, int item) {
    double result = Double.NaN;
    Number low = getLow(series, item);
    if (low != null) {
      result = low.doubleValue();
    }
    return result;
  }
  
  /**
   * Returns the open value.
   * 
   * @param series the series index (ignored).
   * @param item the item index (zero-based).
   * 
   * @return The open value.
   */
  public Number getOpen(int series, int item) {
    return this.data.get(item).getOpen();
  }
  
  /**
   * Returns the open-value (as a double primitive) for an item within a series.
   * 
   * @param series the series (zero-based index).
   * @param item the item (zero-based index).
   * 
   * @return The open-value.
   */
  public double getOpenValue(int series, int item) {
    double result = Double.NaN;
    Number open = getOpen(series, item);
    if (open != null) {
      result = open.doubleValue();
    }
    return result;
  }
  
  /**
   * Returns the close value.
   * 
   * @param series the series index (ignored).
   * @param item the item index (zero-based).
   * 
   * @return The close value.
   */
  public Number getClose(int series, int item) {
    return this.data.get(item).getClose();
  }
  
  /**
   * Returns the close-value (as a double primitive) for an item within a
   * series.
   * 
   * @param series the series (zero-based index).
   * @param item the item (zero-based index).
   * 
   * @return The close-value.
   */
  public double getCloseValue(int series, int item) {
    double result = Double.NaN;
    Number close = getClose(series, item);
    if (close != null) {
      result = close.doubleValue();
    }
    return result;
  }
  
  /**
   * Returns the trading volume.
   * 
   * @param series the series index (ignored).
   * @param item the item index (zero-based).
   * 
   * @return The trading volume.
   */
  public Number getVolume(int series, int item) {
    return this.data.get(item).getVolume();
  }
  
  /**
   * Returns the volume-value (as a double primitive) for an item within a
   * series.
   * 
   * @param series the series (zero-based index).
   * @param item the item (zero-based index).
   * 
   * @return The volume-value.
   */
  public double getVolumeValue(int series, int item) {
    double result = Double.NaN;
    Number volume = getVolume(series, item);
    if (volume != null) {
      result = volume.doubleValue();
    }
    return result;
  }
  
  /**
   * Returns the series count.
   * 
   * @return 1.
   */
  @Override
public int getSeriesCount() {
    return 1;
  }
  
  /**
   * Returns the item count for the specified series.
   * 
   * @param series the series index (ignored).
   * 
   * @return The item count.
   */
  public int getItemCount(int series) {
    return this.data.size();
  }
  
  /**
   * Gets the data.
   * 
   * @return the data
   */
  public List<OHLCDataItem> getData() {
    return this.data;
  }
  
}
