package com.finance.pms.events.scoring;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalculationBounds {

    TunedConfMgr.CalcStatus calcStatus;
    Date calcStart;
    Date calcEnd;
    Date storeStart;
    Date storeEnd;
    Date deltaStoreStart;
    Date deltaStoreEnd;

    public CalculationBounds(TunedConfMgr.CalcStatus calcStatus, Date calcStart, Date calcEnd, Date storeStart, Date storeEnd, Date deltaStoreStart, Date deltaStoreEnd) {
        super();
        this.calcStatus = calcStatus;
        this.calcStart = calcStart;
        this.calcEnd = calcEnd;
        this.storeStart = storeStart;
        this.storeEnd = storeEnd;
        this.deltaStoreStart = deltaStoreStart;
        this.deltaStoreEnd = deltaStoreEnd;
    }
    
	public Date getDeltaStoreStart() {
		return deltaStoreStart;
	}
	
	public Date getDeltaStoreEnd() {
		return deltaStoreEnd;
	}

    public TunedConfMgr.CalcStatus getCalcStatus() {
        return calcStatus;
    }

    public Date getCalcStart() {
        return calcStart;
    }

    public Date getCalcEnd() {
        return calcEnd;
    }

    public Date getStoreStart() {
        return storeStart;
    }

    public Date getStoreEnd() {
        return storeEnd;
    }

    @Override
    public String toString() {
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return "CalculationBounds [calcStatus=" + calcStatus + 
        		", calcStart=" + ((calcStart != null)?df.format(calcStart):"null") + 
        		", calcEnd=" + ((calcEnd != null)?df.format(calcEnd):"null") + 
        		", storeStart=" + ((storeStart !=null)?df.format(storeStart):"null") + 
        		", storeEnd=" + ((storeEnd != null)?df.format(storeEnd):"null") +
        		", deltaStoreStart=" + ((deltaStoreStart != null)?df.format(deltaStoreStart):"null") +
        		", deltaStoreEnd=" + ((deltaStoreEnd != null)?df.format(deltaStoreEnd):"null") + "]";
    }

}
