/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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

import java.util.Date;

import org.jfree.chart.axis.SegmentedTimeline;

import com.finance.pms.events.quotations.QuotationsFactories;

public class Chart {

	protected int domainTicksMultiple(Date arbitraryStartDate, Date arbitraryEndDate) {
		int totalDays  = QuotationsFactories.getFactory().nbOpenIncrementBetween(arbitraryStartDate, arbitraryEndDate);		
		int ret = Math.max(1, 14*totalDays/52);
		return ret;
	}

	/**
	   * Trading time line.
	   * 
	   * @return the segmented timeline
	   * 
	   * @author Guillaume Thoreton
	   */
	protected SegmentedTimeline tradingTimeLine() {
		SegmentedTimeline timeline = new SegmentedTimeline(SegmentedTimeline.DAY_SEGMENT_SIZE,7,0);
		timeline.setStartTime(SegmentedTimeline.firstMondayAfter1900());
		return timeline;
	}

}
