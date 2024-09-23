package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;

public class DateRangeNormalizer<T> extends Normalizer<T> {
    
    private Date lowerRange;
    private Date upperRange;

    public DateRangeNormalizer(Class<T> genType, Date start, Date end, double minNorm, double maxNorm, Date lowerRange, Date upperRange) {
        super(genType, start, end, minNorm, maxNorm, Double.NaN);
        this.lowerRange = lowerRange;
        this.upperRange = upperRange;
        if (lowerRange.before(start) || upperRange.after(end)) throw new RuntimeException("Dates out of range!");
    }

    public SortedMap<Date, T> normalised(SortedMap<Date, T> data) {
        return super.normalised(data);
    }

    @Override
    protected double[] calculateMinMax(SortedMap<Date, T> subD) {
        return super.calculateMinMax(subD.subMap(lowerRange, upperRange));
    }

}
