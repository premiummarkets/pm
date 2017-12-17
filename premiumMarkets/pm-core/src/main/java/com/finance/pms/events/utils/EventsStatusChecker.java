package com.finance.pms.events.utils;

import java.security.InvalidAlgorithmParameterException;
import java.util.Date;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.scoring.CalculationBounds;
import com.finance.pms.events.scoring.TunedConfMgr.CalcStatus;

public class EventsStatusChecker {

    private static MyLogger LOGGER = MyLogger.getLogger(EventsStatusChecker.class);

    private Stock stock;

    private Date lastCalculatedEvent;
    private Date firstCalculatedEvent;


    public EventsStatusChecker(Stock stock, String analysisName, EventInfo eventDef) {
        super();
        this.stock = stock;
        this.lastCalculatedEvent = DataSource.getInstance().getEdgeEventDateFor("max", stock, analysisName, eventDef.getEventDefinitionRef());
        this.firstCalculatedEvent = DataSource.getInstance().getEdgeEventDateFor("min", stock, analysisName, eventDef.getEventDefinitionRef());

    }

    public void endDateToLastEventConsistencyCheck(Date endDate) throws InvalidAlgorithmParameterException {

        //Inconsistency check
        if ( lastCalculatedEvent != null && lastCalculatedEvent.after(stock.getLastQuote()) ) {
            throw new InvalidAlgorithmParameterException("Data inconsistency detected for stock : "+stock+". last quote is : "+stock.getLastQuote()+ " and last event calculated is : "+lastCalculatedEvent);
        }

    }

    public CalculationBounds autoCalcAndSetDatesBounds(Date startDate, Date endDate) {
        
        if (lastCalculatedEvent == null) {
            LOGGER.info(
                    "New dates for "+stock+" : No previous event was found for this calculation => RESET to avoid blank gaps. Requested calculation is from "+startDate+" to "+endDate+". "+
                            ". Previous calculation is from "+firstCalculatedEvent+" to "+lastCalculatedEvent+
                            ". New calculation will be from "+startDate+" to "+endDate);
            new CalculationBounds(CalcStatus.IGNORE, startDate, endDate);
        }

        if (startDate.compareTo(lastCalculatedEvent) > 0 || firstCalculatedEvent.compareTo(endDate) < 0) {//New calculation is fully outside previous => RESET to avoid blank gaps
            LOGGER.info(
                    "New dates for "+stock+" : New calculation is fully outside previous => RESET to avoid blank gaps. Requested calculation is from "+startDate+" to "+endDate+
                            ". Previous calculation is from "+firstCalculatedEvent+" to "+lastCalculatedEvent+
                            ". New calculation will be from "+startDate+" to "+endDate);
            return new CalculationBounds(CalcStatus.RESET, startDate, endDate);
        }

        if (startDate.compareTo(firstCalculatedEvent) < 0 && endDate.compareTo(firstCalculatedEvent) >= 0 && endDate.compareTo(lastCalculatedEvent) <= 0) { //New calculation ends within previous => INC
            LOGGER.info(
                    "New dates for "+stock+" : New calculation ends within previous => INC. Requested calculation is from "+startDate+" to "+endDate+". "+
                            ". Previous calculation is from "+firstCalculatedEvent+" to "+lastCalculatedEvent+
                            ". New calculation will be from "+startDate+" to "+firstCalculatedEvent);
            return new CalculationBounds(CalcStatus.INC, startDate, firstCalculatedEvent);
        }

        if (startDate.compareTo(firstCalculatedEvent) >= 0 && startDate.compareTo(lastCalculatedEvent) <= 0 && endDate.compareTo(lastCalculatedEvent) > 0) { //New calculation starts within previous => INC
            LOGGER.info(
                    "New dates for "+stock+" : New calculation starts within previous => INC. Requested calculation is from "+startDate+" to "+endDate+
                            ". Previous calculation is from "+firstCalculatedEvent+" to "+lastCalculatedEvent+
                            ". New calculation will be from "+lastCalculatedEvent+" to "+endDate);
            return new CalculationBounds(CalcStatus.INC, lastCalculatedEvent, endDate);
        }

        if (startDate.compareTo(firstCalculatedEvent) >= 0 && endDate.compareTo(lastCalculatedEvent) <= 0) { //New calculation fully within previous => NONE
            LOGGER.info(
                    "New dates for "+stock+" : New calculation fully within previous => NONE. Requested calculation is from "+startDate+" to "+endDate+
                            ". Previous calculation is from "+firstCalculatedEvent+" to "+lastCalculatedEvent+
                            ". New calculation is not necessary");
            return new CalculationBounds(CalcStatus.NONE, null, null);
        }

        throw new RuntimeException(String.format("Case not handled start %s, end %s, first event %s, last event %s", startDate, endDate, firstCalculatedEvent, lastCalculatedEvent));

    }

    public Date getLastCalculatedEvent() {
        return lastCalculatedEvent;
    }

    public Date getFirstCalculatedEvent() {
        return firstCalculatedEvent;
    }


}
