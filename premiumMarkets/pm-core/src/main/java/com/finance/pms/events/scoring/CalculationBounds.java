package com.finance.pms.events.scoring;

import java.util.Date;

public class CalculationBounds {

    TunedConfMgr.CalcStatus calcStatus;
    Date pmStart;
    Date pmEnd;
    Date newTunedConfStart;
    Date newTunedConfEnd;

    public CalculationBounds(TunedConfMgr.CalcStatus calcStatus, Date pmStart, Date pmEnd, Date newTunedConfStart, Date newTunedConfEnd) {
        super();
        this.calcStatus = calcStatus;
        this.pmStart = pmStart;
        this.pmEnd = pmEnd;
        this.newTunedConfStart = newTunedConfStart;
        this.newTunedConfEnd = newTunedConfEnd;
    }

    public TunedConfMgr.CalcStatus getCalcStatus() {
        return calcStatus;
    }

    public Date getPmStart() {
        return pmStart;
    }

    public Date getPmEnd() {
        return pmEnd;
    }

    public Date getNewTunedConfStart() {
        return newTunedConfStart;
    }

    public Date getNewTunedConfEnd() {
        return newTunedConfEnd;
    }

    @Override
    public String toString() {
        return "CalculationBounds [calcStatus=" + calcStatus + ", pmStart=" + pmStart + ", pmEnd=" + pmEnd + "]";
    }

}
