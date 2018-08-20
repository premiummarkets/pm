package com.finance.pms.events.operations.nativeops;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.ProvidersInflation;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;

import java.util.*;

public class InflationRationaliserOperation extends PMWithDataOperation {

    private static MyLogger LOGGER = MyLogger.getLogger(InflationRationaliserOperation.class);

    public InflationRationaliserOperation() {
        super("inflationRationaliser", "Applies a reversed inflation rate to the data in an attempt to reduce the inflation bias", new DoubleMapOperation("data to rationalize"));
    }

    public InflationRationaliserOperation(ArrayList<Operation> operands, String outputSelector) {
        this();
        this.setOperands(operands);
        this.setOutputSelector(outputSelector);
    }

    @Override
    public NumericableMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

        SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(0)).getValue(targetStock);

        try {

            Stock inflationStock = ProvidersInflation.inflationStock();
            Quotations inflationQuotations = QuotationsFactories.getFactory()
                    .getQuotationsInstance(
                            inflationStock, getStartDate(targetStock.getStartDate(), thisStartShift), targetStock.getEndDate(),
                            true, Currency.NAN, 1, Quotations.ValidityFilter.CLOSE
                    );

            SortedMap<Date, Double> retMap = new TreeMap<>();
            Date prevDate = data.firstKey();
            for (Date date : data.keySet()) {
                Double inflationAtFirst = inflationQuotations.getClosestCloseForDate(prevDate).doubleValue();
                Double inflationAtSecond = inflationQuotations.getClosestCloseForDate(date).doubleValue();
                Double inflationRate = (inflationAtSecond - inflationAtFirst) / 100;

                Double valueAtDate = data.get(date);
                Double temperedValue = (valueAtDate * 100) / (100 + inflationRate);
                retMap.put(date, temperedValue);
                prevDate = date;
            }

            return new DoubleMapValue(retMap);

        } catch (Exception e) {
            LOGGER.error(targetStock.getStock().getFriendlyName() + " : "  + e, e);
        }

        return new DoubleMapValue();
    }
}
