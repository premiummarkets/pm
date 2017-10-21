package com.finance.pms.events.scoring.chartUtils;

public class BarChart {

    Double value;
    String toolTip;

    public BarChart( Double value, String toolTip) {
        this.value = value;
        this.toolTip = toolTip;
    }

    public Double getValue() {
        return value;
    }

    public String getToolTip() {
        return toolTip;
    }

}