package com.finance.pms.events.operations;

import java.util.SortedMap;

import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;

public interface EventsAnalyser {

    SortedMap<EventKey, EventValue> analyse(SortedMap<EventKey, EventValue> events);

    String getEgFileBaseName();
   

}
