package com.finance.pms.events.utils;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.easymock.annotation.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.AnalysisClient;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.scoring.CalculationBounds;
import com.finance.pms.events.scoring.TunedConf;
import com.finance.pms.events.scoring.TunedConfId;
import com.finance.pms.events.scoring.TunedConfMgr;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TunedConfMgr.class)
public class EventsStatusCheckerTest {
    
    @Mock
    private TunedConfMgr tunedConfMgr;
    @Mock
    private TunedConf tunedConf;
    
    private SimpleDateFormat simpleDateFormat;
    
    private Stock stock = AnalysisClient.ANY_STOCK;
    private String analysisName = "analisys";
    private EventInfo eventDef = EventDefinition.AAAZERO;
    private EventsStatusChecker checker;


    @Before
    public void setUp() throws Exception {
        PowerMock.mockStatic(TunedConfMgr.class);
        tunedConf = PowerMock.createMock(TunedConf.class);
        tunedConfMgr = PowerMock.createMock(TunedConfMgr.class);
        EasyMock.expect(TunedConfMgr.getInstance()).andReturn(tunedConfMgr);
        EasyMock.expect(tunedConfMgr.loadUniqueNoRetuneConfig(stock, analysisName, eventDef)).andReturn(Optional.of(tunedConf));
        
    }

    @Test
    public void testAutoCalcAndSetDatesBoundsWithIn() throws ParseException, Exception {
        
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date previousStart = simpleDateFormat.parse("2009-12-21");
        Date previousEnd = simpleDateFormat.parse("2017-12-20");

        EasyMock.expect(tunedConf.getFisrtStoredEventCalculationStart()).andReturn(previousStart);
        EasyMock.expect(tunedConf.getLastStoredEventCalculationEnd()).andReturn(previousEnd);
        EasyMock.expect(tunedConf.getTunedConfId()).andReturn(new TunedConfId(stock, analysisName, eventDef.getEventDefinitionRef()));

        
        PowerMock.replayAll();
        
        checker = new EventsStatusChecker(tunedConf);
        
        //when
        CalculationBounds autoCalcAndSetDatesBounds = checker.setDatesBounds(previousStart, previousEnd, false);
        
        //then
        assertEquals(TunedConfMgr.CalcStatus.NONE, autoCalcAndSetDatesBounds.getCalcStatus());
        assertEquals(previousStart, autoCalcAndSetDatesBounds.getStoreStart());
        assertEquals(previousEnd, autoCalcAndSetDatesBounds.getStoreEnd());
        assertEquals(null, autoCalcAndSetDatesBounds.getCalcStart());
        assertEquals(null, autoCalcAndSetDatesBounds.getCalcEnd());
    }
    
    @Test
    public void testAutoCalcAndSetDatesBoundsFirstCalc() throws ParseException, Exception {
        
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date previousStart = DateFactory.dateAtZero(); //simpleDateFormat.parse("2009-12-21");
        Date previousEnd = DateFactory.dateAtZero(); //simpleDateFormat.parse("2017-12-20");

        EasyMock.expect(tunedConf.getFisrtStoredEventCalculationStart()).andReturn(previousStart);
        EasyMock.expect(tunedConf.getLastStoredEventCalculationEnd()).andReturn(previousEnd);
        EasyMock.expect(tunedConf.getTunedConfId()).andReturn(new TunedConfId(stock, analysisName, eventDef.getEventDefinitionRef()));

        
        PowerMock.replayAll();
        
        checker = new EventsStatusChecker(tunedConf);
        
        //when
        Date startRequested = simpleDateFormat.parse("2009-12-21");
        Date endRequested = simpleDateFormat.parse("2017-12-20");
        CalculationBounds autoCalcAndSetDatesBounds = checker.setDatesBounds(startRequested, endRequested, false);
        
        //then
        assertEquals(TunedConfMgr.CalcStatus.IGNORE, autoCalcAndSetDatesBounds.getCalcStatus());
        assertEquals(startRequested, (autoCalcAndSetDatesBounds.getStoreStart()));
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getStoreEnd());
        assertEquals(startRequested, autoCalcAndSetDatesBounds.getCalcStart());
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getCalcEnd());
        
    }
    
    @Test
    public void testAutoCalcAndSetDatesBoundsInc() throws ParseException, Exception {
        
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date previousStart = simpleDateFormat.parse("2009-12-21");
        Date previousEnd = simpleDateFormat.parse("2017-12-20");
        

        EasyMock.expect(tunedConf.getFisrtStoredEventCalculationStart()).andReturn(previousStart);
        EasyMock.expect(tunedConf.getLastStoredEventCalculationEnd()).andReturn(previousEnd);
        EasyMock.expect(tunedConf.getTunedConfId()).andReturn(new TunedConfId(stock, analysisName, eventDef.getEventDefinitionRef()));

        
        PowerMock.replayAll();
        
        checker = new EventsStatusChecker(tunedConf);
        
        //when
        Date startRequested = simpleDateFormat.parse("2009-12-20");
        Date endRequested = simpleDateFormat.parse("2017-12-20");
        CalculationBounds autoCalcAndSetDatesBounds = checker.setDatesBounds(startRequested, endRequested, false);
        
        //then
        assertEquals(TunedConfMgr.CalcStatus.LEFT_INC, autoCalcAndSetDatesBounds.getCalcStatus());
        assertEquals(startRequested, (autoCalcAndSetDatesBounds.getStoreStart()));
        assertEquals(previousEnd, autoCalcAndSetDatesBounds.getStoreEnd());
        assertEquals(startRequested, autoCalcAndSetDatesBounds.getCalcStart());
        assertEquals(previousStart, autoCalcAndSetDatesBounds.getCalcEnd());
    }
    
    @Test
    public void testAutoCalcAndSetDatesBoundsInc2() throws ParseException, Exception {
        
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date previousStart = simpleDateFormat.parse("2009-12-21");
        Date previousEnd = simpleDateFormat.parse("2017-12-20");
        

        EasyMock.expect(tunedConf.getFisrtStoredEventCalculationStart()).andReturn(previousStart);
        EasyMock.expect(tunedConf.getLastStoredEventCalculationEnd()).andReturn(previousEnd);
        EasyMock.expect(tunedConf.getTunedConfId()).andReturn(new TunedConfId(stock, analysisName, eventDef.getEventDefinitionRef()));

        
        PowerMock.replayAll();
        
        checker = new EventsStatusChecker(tunedConf);
        
        //when
        Date startRequested = simpleDateFormat.parse("2009-12-21");
        Date endRequested = simpleDateFormat.parse("2017-12-21");
        CalculationBounds autoCalcAndSetDatesBounds = checker.setDatesBounds(startRequested, endRequested, false);
        
        //then
        assertEquals(TunedConfMgr.CalcStatus.RIGHT_INC, autoCalcAndSetDatesBounds.getCalcStatus());
        assertEquals(previousStart, (autoCalcAndSetDatesBounds.getStoreStart()));
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getStoreEnd());
        assertEquals(previousEnd, autoCalcAndSetDatesBounds.getCalcStart());
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getCalcEnd());
    }
    
    @Test
    public void testAutoCalcAndSetDatesBoundsInc3() throws ParseException, Exception {
        
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date previousStart = simpleDateFormat.parse("2009-12-21");
        Date previousEnd = simpleDateFormat.parse("2015-12-20");
        

        EasyMock.expect(tunedConf.getFisrtStoredEventCalculationStart()).andReturn(previousStart);
        EasyMock.expect(tunedConf.getLastStoredEventCalculationEnd()).andReturn(previousEnd);
        EasyMock.expect(tunedConf.getTunedConfId()).andReturn(new TunedConfId(stock, analysisName, eventDef.getEventDefinitionRef()));

        
        PowerMock.replayAll();
        
        checker = new EventsStatusChecker(tunedConf);
        
        //when
        Date startRequested = simpleDateFormat.parse("2010-12-21");
        Date endRequested = simpleDateFormat.parse("2017-12-21");
        CalculationBounds autoCalcAndSetDatesBounds = checker.setDatesBounds(startRequested, endRequested, false);
        
        //then
        assertEquals(TunedConfMgr.CalcStatus.RIGHT_INC, autoCalcAndSetDatesBounds.getCalcStatus());
        assertEquals(previousStart, (autoCalcAndSetDatesBounds.getStoreStart()));
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getStoreEnd());
        assertEquals(previousEnd, autoCalcAndSetDatesBounds.getCalcStart());
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getCalcEnd());
    }
    
    @Test
    public void testAutoCalcAndSetDatesBoundsInc4() throws ParseException, Exception {
        
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date previousStart = simpleDateFormat.parse("2009-12-21");
        Date previousEnd = simpleDateFormat.parse("2015-12-20");
        

        EasyMock.expect(tunedConf.getFisrtStoredEventCalculationStart()).andReturn(previousStart);
        EasyMock.expect(tunedConf.getLastStoredEventCalculationEnd()).andReturn(previousEnd);
        EasyMock.expect(tunedConf.getTunedConfId()).andReturn(new TunedConfId(stock, analysisName, eventDef.getEventDefinitionRef()));

        
        PowerMock.replayAll();
        
        checker = new EventsStatusChecker(tunedConf);
        
        //when
        Date startRequested = simpleDateFormat.parse("2008-12-21");
        Date endRequested = simpleDateFormat.parse("2012-12-21");
        CalculationBounds autoCalcAndSetDatesBounds = checker.setDatesBounds(startRequested, endRequested, false);
        
        //then
        assertEquals(TunedConfMgr.CalcStatus.LEFT_INC, autoCalcAndSetDatesBounds.getCalcStatus());
        assertEquals(startRequested, (autoCalcAndSetDatesBounds.getStoreStart()));
        assertEquals(previousEnd, autoCalcAndSetDatesBounds.getStoreEnd());
        assertEquals(startRequested, autoCalcAndSetDatesBounds.getCalcStart());
        assertEquals(previousStart, autoCalcAndSetDatesBounds.getCalcEnd());
    }
    
    @Test
    public void testAutoCalcAndSetDatesBoundsOutSide() throws ParseException, Exception {
        
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date previousStart = simpleDateFormat.parse("2009-12-21");
        Date previousEnd = simpleDateFormat.parse("2017-12-20");
        EasyMock.expect(tunedConf.getTunedConfId()).andReturn(new TunedConfId(stock, analysisName, eventDef.getEventDefinitionRef()));
        

        EasyMock.expect(tunedConf.getFisrtStoredEventCalculationStart()).andReturn(previousStart);
        EasyMock.expect(tunedConf.getLastStoredEventCalculationEnd()).andReturn(previousEnd);

        
        PowerMock.replayAll();
        
        checker = new EventsStatusChecker(tunedConf);
        
        //when
        Date startRequested = simpleDateFormat.parse("2009-12-20");
        Date endRequested = simpleDateFormat.parse("2017-12-21");
        CalculationBounds autoCalcAndSetDatesBounds = checker.setDatesBounds(startRequested, endRequested, false);
        
        //then
        assertEquals(TunedConfMgr.CalcStatus.FULL_RANGE, autoCalcAndSetDatesBounds.getCalcStatus());
        assertEquals(startRequested, (autoCalcAndSetDatesBounds.getStoreStart()));
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getStoreEnd());
        assertEquals(startRequested, autoCalcAndSetDatesBounds.getCalcStart());
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getCalcEnd());
    }
    
    @Test
    public void testAutoCalcAndSetDatesBoundsOutSide2() throws ParseException, Exception {
        
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date previousStart = simpleDateFormat.parse("2009-12-21");
        Date previousEnd = simpleDateFormat.parse("2017-12-20");
        

        EasyMock.expect(tunedConf.getFisrtStoredEventCalculationStart()).andReturn(previousStart);
        EasyMock.expect(tunedConf.getLastStoredEventCalculationEnd()).andReturn(previousEnd);
        EasyMock.expect(tunedConf.getTunedConfId()).andReturn(new TunedConfId(stock, analysisName, eventDef.getEventDefinitionRef()));

        
        PowerMock.replayAll();
        
        checker = new EventsStatusChecker(tunedConf);
        
        //when
        Date startRequested = simpleDateFormat.parse("2000-12-20");
        Date endRequested = simpleDateFormat.parse("2009-12-20");
        CalculationBounds autoCalcAndSetDatesBounds = checker.setDatesBounds(startRequested, endRequested, false);
        
        //then
        assertEquals(TunedConfMgr.CalcStatus.FULL_RANGE, autoCalcAndSetDatesBounds.getCalcStatus());
        assertEquals(startRequested, (autoCalcAndSetDatesBounds.getStoreStart()));
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getStoreEnd());
        assertEquals(startRequested, autoCalcAndSetDatesBounds.getCalcStart());
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getCalcEnd());
    }
    
    @Test
    public void testAutoCalcAndSetDatesBoundsOutSide3() throws ParseException, Exception {
        
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date previousStart = simpleDateFormat.parse("2009-12-21");
        Date previousEnd = simpleDateFormat.parse("2017-12-20");
        

        EasyMock.expect(tunedConf.getFisrtStoredEventCalculationStart()).andReturn(previousStart);
        EasyMock.expect(tunedConf.getLastStoredEventCalculationEnd()).andReturn(previousEnd);
        EasyMock.expect(tunedConf.getTunedConfId()).andReturn(new TunedConfId(stock, analysisName, eventDef.getEventDefinitionRef()));

        
        PowerMock.replayAll();
        
        checker = new EventsStatusChecker(tunedConf);
        
        //when
        Date startRequested = simpleDateFormat.parse("2017-12-21");
        Date endRequested = simpleDateFormat.parse("2019-12-20");
        CalculationBounds autoCalcAndSetDatesBounds = checker.setDatesBounds(startRequested, endRequested, false);
        
        //then
        assertEquals(TunedConfMgr.CalcStatus.FULL_RANGE, autoCalcAndSetDatesBounds.getCalcStatus());
        assertEquals(startRequested, (autoCalcAndSetDatesBounds.getStoreStart()));
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getStoreEnd());
        assertEquals(startRequested, autoCalcAndSetDatesBounds.getCalcStart());
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getCalcEnd());
    }

}
