package com.finance.pms.events.operations.nativeops;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup;
import com.finance.pms.events.operations.conditional.MultiMapValue;

import java.util.HashMap;
import java.util.Map;

public class DoubleMultiMapValue extends DoubleMapValue implements MultiMapValue {

    protected static MyLogger LOGGER = MyLogger.getLogger(DoubleMultiMapValue.class);

    private Map<String, DoubleMapValue> additionalOutputs;
    private Map<String, ChartedOutputGroup.Type> additionalOutputsTypes;

    public DoubleMultiMapValue() {
        super();
        additionalOutputs = new HashMap<String, DoubleMapValue>();
        additionalOutputsTypes = new HashMap<String, ChartedOutputGroup.Type>();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +" : size is "+map.size() + ((map.size() > 0)?", first key "+map.firstKey()+ ", last key "+map.lastKey():"");
    }

    @Override
    public Object clone() {
        try {
            DoubleMultiMapValue clone = (DoubleMultiMapValue) super.clone();
            clone.additionalOutputs = new HashMap<String, DoubleMapValue>();
            for (String outputKey : additionalOutputs.keySet()) {
                DoubleMapValue  addOutputClone = (DoubleMapValue) (additionalOutputs.get(outputKey)).clone();
                clone.additionalOutputs.put(outputKey, addOutputClone);
                clone.additionalOutputsTypes.put(outputKey, additionalOutputsTypes.get(outputKey));
            }
            return clone;
        } catch (Exception e) {
            LOGGER.error(e,e);
        }
        return null;
    }

    @Override
    public Map<String, DoubleMapValue> getAdditionalOutputs() {
        return additionalOutputs;
    }

    @Override
    public Map<String, ChartedOutputGroup.Type> getAdditionalOutputsTypes() {
        return additionalOutputsTypes;
    }

}
