package com.finance.pms.events.scoring;

import java.util.Date;

public class CalculationBounds {

    TunedConfMgr.CalcStatus calcStatus;
    Date pmStart;
    Date pmEnd;

    public CalculationBounds(TunedConfMgr.CalcStatus calcStatus, Date pmStart, Date pmEnd) {
        super();
        this.calcStatus = calcStatus;
        this.pmStart = pmStart;
        this.pmEnd = pmEnd;
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

    @Override
    public String toString() {
        return "CalculationBounds [calcStatus=" + calcStatus + ", pmStart=" + pmStart + ", pmEnd=" + pmEnd + "]";
    }

}
