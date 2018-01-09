package com.finance.pms.events.utils;

import java.security.InvalidAlgorithmParameterException;
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


    public EventsStatusChecker(TunedConf tunedConf) {
        super();
        this.stock = tunedConf.getTunedConfId().getStock();
        this.currentTunedConfStart = tunedConf.getLastCalculationStart();
        this.currentTunedConfEnd = tunedConf.getLastCalculationEnd();

    }

    private void endDateToLastEventConsistencyCheck(Date endDate) throws InvalidAlgorithmParameterException {
        if ( currentTunedConfEnd.after(stock.getLastQuote()) ) {
            throw new InvalidAlgorithmParameterException("Data inconsistency detected for stock : "+stock+". last quote is : "+stock.getLastQuote()+ " and last event calculated is : "+currentTunedConfEnd);
        }
    }

    public CalculationBounds autoCalcAndSetDatesBounds(Date startDate, Date endDate) throws InvalidAlgorithmParameterException {
        
        endDateToLastEventConsistencyCheck(endDate);

        if (currentTunedConfEnd.equals(DateFactory.dateAtZero())) {//No previous calculation
            LOGGER.info(
                    "New dates for "+stock+" : No previous event was found for this calculation => IGNORE first calculation. Requested calculation is from "+startDate+" to "+endDate+". "+
                            ". Previous calculation is from "+currentTunedConfStart+" to "+currentTunedConfEnd+
                            ". New calculation will be from "+startDate+" to "+endDate);
            return new CalculationBounds(CalcStatus.IGNORE, startDate, endDate, startDate, endDate);
        }

        //New calculation is fully outside previous => RESET to avoid blank gaps
        if (startDate.compareTo(currentTunedConfEnd) > 0 || endDate.compareTo(currentTunedConfStart) < 0 || (startDate.compareTo(currentTunedConfStart) < 0 && currentTunedConfEnd.compareTo(endDate) < 0) ) {
            LOGGER.info(
                    "New dates for "+stock+" : New calculation is fully outside previous => RESET to avoid blank gaps. Requested calculation is from "+startDate+" to "+endDate+
                    ". Previous calculation is from "+currentTunedConfStart+" to "+currentTunedConfEnd+
                    ". New calculation will be from "+startDate+" to "+endDate);
            return new CalculationBounds(CalcStatus.RESET, startDate, endDate, startDate, endDate);
        }

        //New calculation ends within previous => INC
        if (startDate.compareTo(currentTunedConfStart) < 0 && endDate.compareTo(currentTunedConfStart) >= 0 && endDate.compareTo(currentTunedConfEnd) <= 0) {
            LOGGER.info(
                    "New dates for "+stock+" : New calculation ends within previous => INC. Requested calculation is from "+startDate+" to "+endDate+". "+
                            ". Previous calculation is from "+currentTunedConfStart+" to "+currentTunedConfEnd+
                            ". New calculation will be from "+startDate+" to "+currentTunedConfStart);
            return new CalculationBounds(CalcStatus.INC, startDate, currentTunedConfStart, startDate, currentTunedConfEnd);
        }

        //New calculation starts within previous => INC
        if (startDate.compareTo(currentTunedConfStart) >= 0 && startDate.compareTo(currentTunedConfEnd) <= 0 && endDate.compareTo(currentTunedConfEnd) > 0) {
            LOGGER.info(
                    "New dates for "+stock+" : New calculation starts within previous => INC. Requested calculation is from "+startDate+" to "+endDate+
                    ". Previous calculation is from "+currentTunedConfStart+" to "+currentTunedConfEnd+
                    ". New calculation will be from "+currentTunedConfEnd+" to "+endDate);
            return new CalculationBounds(CalcStatus.INC, currentTunedConfEnd, endDate, currentTunedConfStart, endDate);
        }

        //New calculation fully within previous => NONE
        if (startDate.compareTo(currentTunedConfStart) >= 0 && endDate.compareTo(currentTunedConfEnd) <= 0) {
            LOGGER.info(
                    "New dates for "+stock+" : New calculation fully within previous => NONE. Requested calculation is from "+startDate+" to "+endDate+
                    ". Previous calculation is from "+currentTunedConfStart+" to "+currentTunedConfEnd+
                    ". New calculation is not necessary");
            return new CalculationBounds(CalcStatus.NONE, null, null, currentTunedConfStart, currentTunedConfEnd);
        }

        throw new RuntimeException(String.format("Case not handled start %s, end %s, first event %s, last event %s", startDate, endDate, currentTunedConfStart, currentTunedConfEnd));

    }

}
