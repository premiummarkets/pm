package com.finance.pms.events.utils;

import static org.junit.Assert.assertEquals;

import java.security.InvalidAlgorithmParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private EventInfo eventDef = EventDefinition.NEURAL;
    private EventsStatusChecker checker;


    @Before
    public void setUp() throws Exception {
        PowerMock.mockStatic(TunedConfMgr.class);
        tunedConf = PowerMock.createMock(TunedConf.class);
        tunedConfMgr = PowerMock.createMock(TunedConfMgr.class);
        EasyMock.expect(TunedConfMgr.getInstance()).andReturn(tunedConfMgr);
        EasyMock.expect(tunedConfMgr.loadUniqueNoRetuneConfig(stock, analysisName, eventDef.getEventDefinitionRef())).andReturn(tunedConf);
        
    }

    @Test
    public void testAutoCalcAndSetDatesBoundsWithIn() throws ParseException, InvalidAlgorithmParameterException {
        
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date previousStart = simpleDateFormat.parse("2009-12-21");
        Date previousEnd = simpleDateFormat.parse("2017-12-20");

        EasyMock.expect(tunedConf.getLastCalculationStart()).andReturn(previousStart);
        EasyMock.expect(tunedConf.getLastCalculationEnd()).andReturn(previousEnd);

        
        PowerMock.replayAll();
        
        checker = new EventsStatusChecker(stock, analysisName, eventDef);
        
        //when
        CalculationBounds autoCalcAndSetDatesBounds = checker.autoCalcAndSetDatesBounds(previousStart, previousEnd);
        
        //then
        assertEquals(TunedConfMgr.CalcStatus.NONE, autoCalcAndSetDatesBounds.getCalcStatus());
        assertEquals(previousStart, autoCalcAndSetDatesBounds.getNewTunedConfStart());
        assertEquals(previousEnd, autoCalcAndSetDatesBounds.getNewTunedConfEnd());
        assertEquals(null, autoCalcAndSetDatesBounds.getPmStart());
        assertEquals(null, autoCalcAndSetDatesBounds.getPmEnd());
    }
    
    @Test
    public void testAutoCalcAndSetDatesBoundsFirstCalc() throws ParseException, InvalidAlgorithmParameterException {
        
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date previousStart = DateFactory.dateAtZero(); //simpleDateFormat.parse("2009-12-21");
        Date previousEnd = DateFactory.dateAtZero(); //simpleDateFormat.parse("2017-12-20");

        EasyMock.expect(tunedConf.getLastCalculationStart()).andReturn(previousStart);
        EasyMock.expect(tunedConf.getLastCalculationEnd()).andReturn(previousEnd);

        
        PowerMock.replayAll();
        
        checker = new EventsStatusChecker(stock, analysisName, eventDef);
        
        //when
        Date startRequested = simpleDateFormat.parse("2009-12-21");
        Date endRequested = simpleDateFormat.parse("2017-12-20");
        CalculationBounds autoCalcAndSetDatesBounds = checker.autoCalcAndSetDatesBounds(startRequested, endRequested);
        
        //then
        assertEquals(TunedConfMgr.CalcStatus.IGNORE, autoCalcAndSetDatesBounds.getCalcStatus());
        assertEquals(startRequested, (autoCalcAndSetDatesBounds.getNewTunedConfStart()));
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getNewTunedConfEnd());
        assertEquals(startRequested, autoCalcAndSetDatesBounds.getPmStart());
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getPmEnd());
        
    }
    
    @Test
    public void testAutoCalcAndSetDatesBoundsInc() throws ParseException, InvalidAlgorithmParameterException {
        
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date previousStart = simpleDateFormat.parse("2009-12-21");
        Date previousEnd = simpleDateFormat.parse("2017-12-20");
        

        EasyMock.expect(tunedConf.getLastCalculationStart()).andReturn(previousStart);
        EasyMock.expect(tunedConf.getLastCalculationEnd()).andReturn(previousEnd);

        
        PowerMock.replayAll();
        
        checker = new EventsStatusChecker(stock, analysisName, eventDef);
        
        //when
        Date startRequested = simpleDateFormat.parse("2009-12-20");
        Date endRequested = simpleDateFormat.parse("2017-12-20");
        CalculationBounds autoCalcAndSetDatesBounds = checker.autoCalcAndSetDatesBounds(startRequested, endRequested);
        
        //then
        assertEquals(TunedConfMgr.CalcStatus.INC, autoCalcAndSetDatesBounds.getCalcStatus());
        assertEquals(startRequested, (autoCalcAndSetDatesBounds.getNewTunedConfStart()));
        assertEquals(previousEnd, autoCalcAndSetDatesBounds.getNewTunedConfEnd());
        assertEquals(startRequested, autoCalcAndSetDatesBounds.getPmStart());
        assertEquals(previousStart, autoCalcAndSetDatesBounds.getPmEnd());
    }
    
    @Test
    public void testAutoCalcAndSetDatesBoundsInc2() throws ParseException, InvalidAlgorithmParameterException {
        
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date previousStart = simpleDateFormat.parse("2009-12-21");
        Date previousEnd = simpleDateFormat.parse("2017-12-20");
        

        EasyMock.expect(tunedConf.getLastCalculationStart()).andReturn(previousStart);
        EasyMock.expect(tunedConf.getLastCalculationEnd()).andReturn(previousEnd);

        
        PowerMock.replayAll();
        
        checker = new EventsStatusChecker(stock, analysisName, eventDef);
        
        //when
        Date startRequested = simpleDateFormat.parse("2009-12-21");
        Date endRequested = simpleDateFormat.parse("2017-12-21");
        CalculationBounds autoCalcAndSetDatesBounds = checker.autoCalcAndSetDatesBounds(startRequested, endRequested);
        
        //then
        assertEquals(TunedConfMgr.CalcStatus.INC, autoCalcAndSetDatesBounds.getCalcStatus());
        assertEquals(previousStart, (autoCalcAndSetDatesBounds.getNewTunedConfStart()));
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getNewTunedConfEnd());
        assertEquals(previousEnd, autoCalcAndSetDatesBounds.getPmStart());
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getPmEnd());
    }
    
    @Test
    public void testAutoCalcAndSetDatesBoundsOutSide() throws ParseException, InvalidAlgorithmParameterException {
        
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date previousStart = simpleDateFormat.parse("2009-12-21");
        Date previousEnd = simpleDateFormat.parse("2017-12-20");
        

        EasyMock.expect(tunedConf.getLastCalculationStart()).andReturn(previousStart);
        EasyMock.expect(tunedConf.getLastCalculationEnd()).andReturn(previousEnd);

        
        PowerMock.replayAll();
        
        checker = new EventsStatusChecker(stock, analysisName, eventDef);
        
        //when
        Date startRequested = simpleDateFormat.parse("2009-12-20");
        Date endRequested = simpleDateFormat.parse("2017-12-21");
        CalculationBounds autoCalcAndSetDatesBounds = checker.autoCalcAndSetDatesBounds(startRequested, endRequested);
        
        //then
        assertEquals(TunedConfMgr.CalcStatus.RESET, autoCalcAndSetDatesBounds.getCalcStatus());
        assertEquals(startRequested, (autoCalcAndSetDatesBounds.getNewTunedConfStart()));
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getNewTunedConfEnd());
        assertEquals(startRequested, autoCalcAndSetDatesBounds.getPmStart());
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getPmEnd());
    }
    
    @Test
    public void testAutoCalcAndSetDatesBoundsOutSide2() throws ParseException, InvalidAlgorithmParameterException {
        
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date previousStart = simpleDateFormat.parse("2009-12-21");
        Date previousEnd = simpleDateFormat.parse("2017-12-20");
        

        EasyMock.expect(tunedConf.getLastCalculationStart()).andReturn(previousStart);
        EasyMock.expect(tunedConf.getLastCalculationEnd()).andReturn(previousEnd);

        
        PowerMock.replayAll();
        
        checker = new EventsStatusChecker(stock, analysisName, eventDef);
        
        //when
        Date startRequested = simpleDateFormat.parse("2000-12-20");
        Date endRequested = simpleDateFormat.parse("2009-12-20");
        CalculationBounds autoCalcAndSetDatesBounds = checker.autoCalcAndSetDatesBounds(startRequested, endRequested);
        
        //then
        assertEquals(TunedConfMgr.CalcStatus.RESET, autoCalcAndSetDatesBounds.getCalcStatus());
        assertEquals(startRequested, (autoCalcAndSetDatesBounds.getNewTunedConfStart()));
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getNewTunedConfEnd());
        assertEquals(startRequested, autoCalcAndSetDatesBounds.getPmStart());
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getPmEnd());
    }
    
    @Test
    public void testAutoCalcAndSetDatesBoundsOutSide3() throws ParseException, InvalidAlgorithmParameterException {
        
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date previousStart = simpleDateFormat.parse("2009-12-21");
        Date previousEnd = simpleDateFormat.parse("2017-12-20");
        

        EasyMock.expect(tunedConf.getLastCalculationStart()).andReturn(previousStart);
        EasyMock.expect(tunedConf.getLastCalculationEnd()).andReturn(previousEnd);

        
        PowerMock.replayAll();
        
        checker = new EventsStatusChecker(stock, analysisName, eventDef);
        
        //when
        Date startRequested = simpleDateFormat.parse("2017-12-21");
        Date endRequested = simpleDateFormat.parse("2019-12-20");
        CalculationBounds autoCalcAndSetDatesBounds = checker.autoCalcAndSetDatesBounds(startRequested, endRequested);
        
        //then
        assertEquals(TunedConfMgr.CalcStatus.RESET, autoCalcAndSetDatesBounds.getCalcStatus());
        assertEquals(startRequested, (autoCalcAndSetDatesBounds.getNewTunedConfStart()));
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getNewTunedConfEnd());
        assertEquals(startRequested, autoCalcAndSetDatesBounds.getPmStart());
        assertEquals(endRequested, autoCalcAndSetDatesBounds.getPmEnd());
    }

}
