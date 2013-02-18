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
