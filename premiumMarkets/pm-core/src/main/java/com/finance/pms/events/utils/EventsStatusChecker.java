package com.finance.pms.events.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.scoring.CalculationBounds;
import com.finance.pms.events.scoring.TunedConf;
import com.finance.pms.events.scoring.TunedConfMgr.CalcStatus;

public class EventsStatusChecker {

    private static MyLogger LOGGER = MyLogger.getLogger(EventsStatusChecker.class);

    private Stock stock;

    private Date currentTunedConfEnd;
    private Date currentTunedConfStart;


    public EventsStatusChecker(TunedConf tunedConf) {
        super();
        this.stock = tunedConf.getTunedConfId().getStock();
        this.currentTunedConfStart = tunedConf.getFisrtStoredEventCalculationStart();
        this.currentTunedConfEnd = tunedConf.getLastStoredEventCalculationEnd();
    }

    private void endDateToLastEventConsistencyCheck(Date endDate) throws RuntimeException {
        Date lastQuote = stock.getLastQuote();
		if (currentTunedConfEnd.after(lastQuote) ) {
            throw new RuntimeException("Data inconsistency detected for stock: " + stock + ". last quote is: " + lastQuote +  " and last event calculated is: " + currentTunedConfEnd);
        }
    }

    public CalculationBounds setDatesBounds(Date startDate, Date endDate, boolean isForbidOverride) throws Exception {
        
        endDateToLastEventConsistencyCheck(endDate);
        
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String currCfStartStr = df.format(currentTunedConfStart);
		String currCfEndStr = df.format(currentTunedConfEnd);
		String startDateStr = df.format(startDate);
		String endDateStr = df.format(endDate);
		String calcIsFrom = "Requested calculation is from " + startDateStr + " to " + endDateStr + ". ";
		String calcWasFrom = "Previous calculation was from " + currCfStartStr + " to " + currCfEndStr + ". ";
		
		if (currentTunedConfEnd.equals(DateFactory.dateAtZero())) {//No previous calculation
            CalculationBounds calculationBounds = new CalculationBounds(CalcStatus.FULL_RANGE, startDate, endDate, startDate, endDate, startDate, endDate);
            LOGGER.info(
                    "New dates for " + stock + ": No previous event was found for this calculation => RESET. First calculation. "  +
                    calcIsFrom + calcWasFrom + ". " + calculationBounds);
			return calculationBounds;
        }

        //New calculation is fully outside previous => RESET to avoid blank gaps
        if ((startDate.compareTo(currentTunedConfEnd) > 0 || endDate.compareTo(currentTunedConfStart) < 0 || 
        			(startDate.compareTo(currentTunedConfStart) < 0 && currentTunedConfEnd.compareTo(endDate) < 0)
        	)
        ) {
        	if (isForbidOverride) {
				throw new Exception("New dates for " + stock + ": New calculation is fully outside previous => RESET to avoid blank gaps. " +
						String.format("Case not handled isNoOverride %s start %s, end %s, first event %s, last event %s", isForbidOverride, startDateStr, endDateStr, currCfStartStr, currCfEndStr));
        	} else {
            	CalculationBounds calculationBounds = new CalculationBounds(CalcStatus.FULL_RANGE, startDate, endDate, startDate, endDate, startDate, endDate);
            	LOGGER.info(
                        "New dates for " + stock + ": New calculation is fully outside previous => RESET to avoid blank gaps. " +
                        calcIsFrom + calcWasFrom + ". " + calculationBounds);
				return calculationBounds;
        	} 
        }

        //New calculation ends within previous => LEFT_INC
        if (startDate.compareTo(currentTunedConfStart) < 0 && endDate.compareTo(currentTunedConfStart) >= 0 && endDate.compareTo(currentTunedConfEnd) <= 0) {
        	if (isForbidOverride) {
        		throw new Exception("New dates for " + stock + ": New calculation ends within previous => LEFT_INC. " + "(isNoOverride: " + isForbidOverride + ") " +
						String.format("Case not handled isNoOverride %s start %s, end %s, first event %s, last event %s", isForbidOverride, startDateStr, endDateStr, currCfStartStr, currCfEndStr));
        	}
            CalculationBounds calculationBounds = new CalculationBounds(CalcStatus.LEFT_INC, startDate, endDate, startDate, currentTunedConfEnd, startDate, endDate);
            LOGGER.info(
                    "New dates for " + stock + ": New calculation ends within previous => LEFT_INC. " + "(isNoOverride: " + isForbidOverride + ") " +
                    calcIsFrom + calcWasFrom + ". " + calculationBounds);
            return calculationBounds;
        }

        //New calculation starts within previous => RIGHT_INC
        if (startDate.compareTo(currentTunedConfStart) >= 0 && startDate.compareTo(currentTunedConfEnd) <= 0 && endDate.compareTo(currentTunedConfEnd) > 0) {
            Date deltaStoreStart = startDate;
        	if (isForbidOverride) {
        		deltaStoreStart = DateUtils.addDays(currentTunedConfEnd, 1);
        	}
			CalculationBounds calculationBounds = new CalculationBounds(CalcStatus.RIGHT_INC, startDate, endDate, currentTunedConfStart, endDate, deltaStoreStart, endDate);
            LOGGER.info(
                    "New dates for " + stock + ": New calculation starts within previous => RIGHT_INC. " + "(isNoOverride: " + isForbidOverride + ") " +
                    calcIsFrom + calcWasFrom + ". " + calculationBounds);
			return calculationBounds;
        }

        //New calculation fully within previous => Full.
        if (startDate.compareTo(currentTunedConfStart) >= 0 && endDate.compareTo(currentTunedConfEnd) <= 0) {
        	if (isForbidOverride) {
				throw new Exception( "New dates for " + stock + ": New calculation fully within previous => NONE. " +
						String.format("Case not handled isNoOverride %s start %s, end %s, first event %s, last event %s", isForbidOverride, startDateStr, endDateStr, currCfStartStr, currCfEndStr));
        	} else {
	            CalculationBounds calculationBounds = new CalculationBounds(CalcStatus.NONE, startDate, endDate, currentTunedConfStart, currentTunedConfEnd, startDate, endDate);
	            LOGGER.info(
	                    "New dates for " + stock + ": New calculation fully within previous => NONE. " +
	                    calcIsFrom + calcWasFrom + ". " + calculationBounds);
	            return calculationBounds;
        	}
        }

        throw new Exception(String.format("Case not handled isNoOverride %s start %s, end %s, first event %s, last event %s", isForbidOverride, startDateStr, endDateStr, currCfStartStr, currCfEndStr));

    }

}
