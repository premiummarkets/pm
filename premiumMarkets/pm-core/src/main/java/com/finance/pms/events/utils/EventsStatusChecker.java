package com.finance.pms.events.utils;

import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	private Boolean isDirtyConf;


    public EventsStatusChecker(TunedConf tunedConf) {
        super();
        this.stock = tunedConf.getTunedConfId().getStock();
        this.currentTunedConfStart = tunedConf.getLastCalculationStart();
        this.currentTunedConfEnd = tunedConf.getLastCalculationEnd();
        this.isDirtyConf = tunedConf.getDirty();

    }

    private void endDateToLastEventConsistencyCheck(Date endDate) throws InvalidAlgorithmParameterException {
        Date lastQuote = stock.getLastQuote();
		if ( currentTunedConfEnd.after(lastQuote) ) {
            throw new InvalidAlgorithmParameterException("Data inconsistency detected for stock: " + stock + ". last quote is: " + lastQuote +  " and last event calculated is: " + currentTunedConfEnd);
        }
    }

    public CalculationBounds autoCalcAndSetDatesBounds(Date startDate, Date endDate) throws InvalidAlgorithmParameterException {
        
        endDateToLastEventConsistencyCheck(endDate);
        
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String currCfStartStr = df.format(currentTunedConfStart);
		String currCfEndStr = df.format(currentTunedConfEnd);
		String startDateStr = df.format(startDate);
		String endDateStr = df.format(endDate);
		String calcIsFrom = "Requested calculation is from " + startDateStr + " to " + endDateStr + ". ";
		String calcWasFrom = "Previous calculation was from " + currCfStartStr + " to " + currCfEndStr + ". ";
		
        if ( isDirtyConf ) {
            LOGGER.info(
                    "New dates for " + stock + ": Old Calculation is dirty => RESET. " +
                    calcIsFrom + calcWasFrom + "New calculation will be from " + startDateStr + " to " + endDateStr);
            return new CalculationBounds(CalcStatus.RESET, startDate, endDate, startDate, endDate);
        }
		
		if (currentTunedConfEnd.equals(DateFactory.dateAtZero())) {//No previous calculation
            LOGGER.info(
                    "New dates for " + stock + ": No previous event was found for this calculation => IGNORE first calculation. "  +
                    calcIsFrom + calcWasFrom + "New calculation will be from " + startDateStr + " to " + endDateStr);
            return new CalculationBounds(CalcStatus.IGNORE, startDate, endDate, startDate, endDate);
        }

        //New calculation is fully outside previous => RESET to avoid blank gaps
        if (startDate.compareTo(currentTunedConfEnd) > 0 || endDate.compareTo(currentTunedConfStart) < 0 || (startDate.compareTo(currentTunedConfStart) < 0 && currentTunedConfEnd.compareTo(endDate) < 0) ) {
            LOGGER.info(
                    "New dates for " + stock + ": New calculation is fully outside previous => RESET to avoid blank gaps. " +
                    calcIsFrom + calcWasFrom + "New calculation will be from " + startDateStr + " to " + endDateStr);
            return new CalculationBounds(CalcStatus.RESET, startDate, endDate, startDate, endDate);
        }

        //New calculation ends within previous => LEFT_INC
        if (startDate.compareTo(currentTunedConfStart) < 0 && endDate.compareTo(currentTunedConfStart) >= 0 && endDate.compareTo(currentTunedConfEnd) <= 0) {
            LOGGER.info(
                    "New dates for " + stock + ": New calculation ends within previous => LEFT_INC. " +
                    calcIsFrom + calcWasFrom +  "New calculation will be from " + startDateStr + " to " +  currCfStartStr);
            return new CalculationBounds(CalcStatus.LEFT_INC, startDate, currentTunedConfStart, startDate, currentTunedConfEnd);
        }

        //New calculation starts within previous => RIGHT_INC
        if (startDate.compareTo(currentTunedConfStart) >= 0 && startDate.compareTo(currentTunedConfEnd) <= 0 && endDate.compareTo(currentTunedConfEnd) > 0) {
            LOGGER.info(
                    "New dates for " + stock + ": New calculation starts within previous => RIGHT_INC. " +
                    calcIsFrom + calcWasFrom +  "New calculation will be from " + currCfEndStr + " to " + endDateStr);
            return new CalculationBounds(CalcStatus.RIGHT_INC, currentTunedConfEnd, endDate, currentTunedConfStart, endDate);
        }

        //New calculation fully within previous => NONE
        if (startDate.compareTo(currentTunedConfStart) >= 0 && endDate.compareTo(currentTunedConfEnd) <= 0) {
            LOGGER.info(
                    "New dates for " + stock + ": New calculation fully within previous => NONE. " +
                    calcIsFrom + calcWasFrom +  "New calculation is not necessary");
            return new CalculationBounds(CalcStatus.NONE, null, null, currentTunedConfStart, currentTunedConfEnd);
        }

        throw new RuntimeException(String.format("Case not handled start %s, end %s, first event %s, last event %s", startDateStr, endDateStr, currCfStartStr, currCfEndStr));

    }

}
