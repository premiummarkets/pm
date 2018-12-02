package com.finance.pms.events.scoring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.SortedMap;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.finance.pms.SpringContext;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.dto.PeriodRatingDTO;
import com.finance.pms.events.scoring.dto.TuningResDTO;

public class OTFTuningFinalizerTest {

    private static SpringContext springContext;

    OTFTuningFinalizer finalizer;
    private Quotations quotations;
    private Stock stock;
    private Date startDate;
    private Date endDate;

    private SimpleDateFormat sdf;

    private Date endQDate;

    private SortedMap<Date, Number> qMap;

    private Observer observer;

    @BeforeClass
    public static void oneTimeSetup() {
        springContext = new SpringContext("/home/guil/Developpement/newEclipse/premiumMarkets/pm-forecast/db.properties");
        springContext.loadBeans("/connexions.xml", "/swtclients.xml");
        springContext.refresh();

    }

    @AfterClass
    public static void ontTimeTearDown() {
        springContext.close();
    }

    @Before
    public void setUp() throws Exception {
        finalizer = new OTFTuningFinalizer();

        sdf = new SimpleDateFormat("yyyyMMdd");
        startDate = sdf.parse("20050101");
        endDate = sdf.parse("20150101");
        stock = DataSource.getInstance().loadStockBySymbol("GOOG");
        quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, startDate, endDate, true, stock.getMarketValuation().getCurrency(), 1, ValidityFilter.CLOSE);
        qMap = QuotationsFactories.getFactory().buildExactBMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size()-1);
        endQDate =  quotations.get(quotations.getClosestIndexBeforeOrAtDateOrIndexZero(0, endDate)).getDate();

        observer = new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                System.out.println("Observed : "+o+" "+arg);

            }
        };

    }

    @Test(expected=NotEnoughDataException.class)
    public void testNoEvent() throws Exception {

        //Given
        Collection<EventValue> eventListForEvtDef = new ArrayList<>();
        String noResMsg = "Ooops";

        //When
        finalizer.validPeriods(quotations, stock, startDate, endDate,  eventListForEvtDef, noResMsg);

        //Then
        //assertTrue(validPeriods.isEmpty());
    }

    @Test
    public void testEndBearish() throws Exception, ParseException {

        //Given
        Collection<EventValue> eventListForEvtDef = new ArrayList<>();
        String noResMsg = "Ooops";

        Date evtBul1 = sdf.parse("20050505");
        eventListForEvtDef.add(new EventValue(evtBul1, EventDefinition.NEURALNEUROPH, EventType.BULLISH, "toto"));
        Date evtBear1 = sdf.parse("20060505");
        eventListForEvtDef.add(new EventValue(evtBear1, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));

        //When
        List<PeriodRatingDTO> validPeriods = finalizer.validPeriods(quotations, stock, startDate, endDate,  eventListForEvtDef, noResMsg);

        System.out.println(validPeriods);

        //Then
        //[PeriodRatingDTO [from=Fri May 05 00:00:00 BST 2006, to=Thu Jan 01 00:00:00 GMT 2015, priceAtFrom=197.9107, priceAtTo=526.4, trend=BEARISH, rating=null, configs=[], realised=true]]


        assertTrue(validPeriods.size()==1);
        Iterator<PeriodRatingDTO> iterator = validPeriods.iterator();
        PeriodRatingDTO next = iterator.next();
        assertTrue(next.getFrom().equals(evtBear1));
        assertTrue(next.getTo().equals(endQDate));
        assertTrue(next.getTrend().equals(EventType.BEARISH.name()));

        TuningResDTO res = finalizer.buildResOnValidPeriods(validPeriods, qMap, quotations, stock, startDate, endDate, "toto", EventDefinition.NEURALNEUROPH.toString(), observer);
        
        System.out.println(res);
        
        //Then
        //TuningResDTO [periods=
        //[PeriodRatingDTO [from=Fri May 05 00:00:00 BST 2006, to=2014-12-31, priceAtFrom=197.9107, priceAtTo=526.4, trend=BEARISH, rating=null, 
        //  configs=[], realised=false]], csvLink=autoPortfolioLogs/totoGOOG_NEURAL_BuyAndSellRecords.csv, lastTrend=BEARISH, followProfit=0.0, , stopLossProfit=NaN, 
        //  stockPriceChange=1.6597854487, configRatingFile=null, footNote=null]
        assertEquals(1, res.getPeriods().size());
        PeriodRatingDTO latest = res.getPeriods().get(res.getPeriods().size()-1);
        assertEquals(evtBear1, latest.getFrom());
        assertEquals(endQDate, latest.getTo());
        assertEquals(EventType.BEARISH.name(), res.getLastTrend());
        assertEquals(0.0, res.getFollowProfit(), 0.0001);
        Double closestCloseForDate = quotations.getClosestCloseSpForDate(endDate).doubleValue();
        assertEquals((closestCloseForDate-next.getPriceAtFrom())/next.getPriceAtFrom(), res.getStockPriceChange(), 0.0001);
        assertEquals(startDate, res.getCalculatedStart());
        assertEquals(endDate, res.getCalculatedEnd());
        
    }

    @Test
    public void testEndBearish2() throws Exception, ParseException {

        //Given
        Collection<EventValue> eventListForEvtDef = new ArrayList<>();
        String noResMsg = "Ooops";

        Date evtb1 = sdf.parse("20050405");
        eventListForEvtDef.add(new EventValue(evtb1, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        Date evtB1 = sdf.parse("20050505");
        eventListForEvtDef.add(new EventValue(evtB1, EventDefinition.NEURALNEUROPH, EventType.BULLISH, "toto"));
        Date evtb2 = sdf.parse("20060505");
        eventListForEvtDef.add(new EventValue(evtb2, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));

        //When
        List<PeriodRatingDTO> validPeriods = finalizer.validPeriods(quotations, stock, startDate, endDate,  eventListForEvtDef, noResMsg);

        System.out.println(validPeriods);

        //Then
        //[PeriodRatingDTO [from=Tue Apr 05 00:00:00 BST 2005, to=Thu May 05 00:00:00 BST 2005, priceAtFrom=94.6488, priceAtTo=113.9279, trend=BEARISH, rating=null, configs=[], realised=true], 
        // PeriodRatingDTO [from=Thu May 05 00:00:00 BST 2005, to=Fri May 05 00:00:00 BST 2006, priceAtFrom=113.9279, priceAtTo=197.9107, trend=BULLISH, rating=null, configs=[], realised=true], 
        // PeriodRatingDTO [from=Fri May 05 00:00:00 BST 2006, to=Thu Jan 01 00:00:00 GMT 2015, priceAtFrom=197.9107, priceAtTo=526.4, trend=BEARISH, rating=null, configs=[], realised=true]]

        assertTrue(validPeriods.size()==3);
        Iterator<PeriodRatingDTO> iterator = validPeriods.iterator();
        PeriodRatingDTO next = iterator.next();
        assertTrue(next.getFrom().equals(evtb1));
        assertTrue(next.getTo().equals(evtB1));
        assertTrue(next.getTrend().equals(EventType.BEARISH.name()));
        PeriodRatingDTO next1 = iterator.next();
        assertTrue(next1.getFrom().equals(evtB1));
        assertTrue(next1.getTo().equals(evtb2));
        assertTrue(next1.getTrend().equals(EventType.BULLISH.name()));
        PeriodRatingDTO next3 = iterator.next();
        assertTrue(next3.getFrom().equals(evtb2));
        assertTrue(next3.getTo().equals(endQDate));
        assertTrue(next3.getTrend().equals(EventType.BEARISH.name()));
        
        //When
        TuningResDTO res = finalizer.buildResOnValidPeriods(validPeriods, qMap, quotations, stock, startDate, endDate, "toto", EventDefinition.NEURALNEUROPH.toString(), observer);
        
        System.out.println(res);
        
        //Then
        assertEquals(3, res.getPeriods().size());
        PeriodRatingDTO latestBear = res.getPeriods().get(res.getPeriods().size()-1);
        assertEquals(evtb2, latestBear.getFrom());
        assertEquals(endQDate, latestBear.getTo());
        assertEquals(EventType.BEARISH.name(), res.getLastTrend());
        assertEquals((next1.getPriceAtTo()-next1.getPriceAtFrom())/next1.getPriceAtFrom(), res.getFollowProfit(), 0.0001);
        Double closestCloseForDate = quotations.getClosestCloseSpForDate(endDate).doubleValue();
        assertEquals((closestCloseForDate-next.getPriceAtFrom())/next.getPriceAtFrom(), res.getStockPriceChange(), 0.0001);
        assertEquals(startDate, res.getCalculatedStart());
        assertEquals(endDate, res.getCalculatedEnd());
    }

    @Test
    public void testEndBearishDupls() throws Exception, ParseException {

        //Given
        Collection<EventValue> eventListForEvtDef = new ArrayList<>();
        String noResMsg = "Ooops";

        Date evtb1 = sdf.parse("20050405");
        eventListForEvtDef.add(new EventValue(evtb1, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        eventListForEvtDef.add(new EventValue(sdf.parse("20050406"), EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        Date evtB1 = sdf.parse("20050505");
        eventListForEvtDef.add(new EventValue(evtB1, EventDefinition.NEURALNEUROPH, EventType.BULLISH, "toto"));
        Date evtb2 = sdf.parse("20060505");
        eventListForEvtDef.add(new EventValue(evtb2, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        eventListForEvtDef.add(new EventValue(sdf.parse("20060506"), EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));

        //When
        List<PeriodRatingDTO> validPeriods = finalizer.validPeriods(quotations, stock, startDate, endDate,  eventListForEvtDef, noResMsg);

        System.out.println(validPeriods);

        //Then
        //[PeriodRatingDTO [from=Tue Apr 05 00:00:00 BST 2005, to=Thu May 05 00:00:00 BST 2005, priceAtFrom=94.6488, priceAtTo=113.9279, trend=BEARISH, rating=null, configs=[], realised=true],
        // PeriodRatingDTO [from=Thu May 05 00:00:00 BST 2005, to=Fri May 05 00:00:00 BST 2006, priceAtFrom=113.9279, priceAtTo=197.9107, trend=BULLISH, rating=null, configs=[], realised=true],
        // PeriodRatingDTO [from=Fri May 05 00:00:00 BST 2006, to=Thu Jan 01 00:00:00 GMT 2015, priceAtFrom=197.9107, priceAtTo=526.4, trend=BEARISH, rating=null, configs=[], realised=true]]

        assertTrue(validPeriods.size()==3);
        Iterator<PeriodRatingDTO> iterator = validPeriods.iterator();
        PeriodRatingDTO next = iterator.next();
        assertTrue(next.getFrom().equals(evtb1));
        assertTrue(next.getTo().equals(evtB1));
        assertTrue(next.getTrend().equals(EventType.BEARISH.name()));
        PeriodRatingDTO next1 = iterator.next();
        assertTrue(next1.getFrom().equals(evtB1));
        assertTrue(next1.getTo().equals(evtb2));
        assertTrue(next1.getTrend().equals(EventType.BULLISH.name()));
        PeriodRatingDTO next3 = iterator.next();
        assertTrue(next3.getFrom().equals(evtb2));
        assertTrue(next3.getTo().equals(endQDate));
        assertTrue(next3.getTrend().equals(EventType.BEARISH.name()));
        
        //When
        TuningResDTO res = finalizer.buildResOnValidPeriods(validPeriods, qMap, quotations, stock, startDate, endDate, "toto", EventDefinition.NEURALNEUROPH.toString(), observer);
        
        System.out.println(res);
        
        //Then
        assertEquals(3, res.getPeriods().size());
        PeriodRatingDTO latestBear = res.getPeriods().get(res.getPeriods().size()-1);
        assertEquals(evtb2, latestBear.getFrom());
        assertEquals(endQDate, latestBear.getTo());
        assertEquals(EventType.BEARISH.name(), res.getLastTrend());
        assertEquals((next1.getPriceAtTo()-next1.getPriceAtFrom())/next1.getPriceAtFrom(), res.getFollowProfit(), 0.0001);
        Double closestCloseForDate = quotations.getClosestCloseSpForDate(endDate).doubleValue();
        assertEquals((closestCloseForDate-next.getPriceAtFrom())/next.getPriceAtFrom(), res.getStockPriceChange(), 0.0001);
        assertEquals(startDate, res.getCalculatedStart());
        assertEquals(endDate, res.getCalculatedEnd());
    }

    @Test
    public void testBullishDupls() throws Exception, ParseException {

        //Given
        Collection<EventValue> eventListForEvtDef = new ArrayList<>();
        String noResMsg = "Ooops";

        Date evtb1 = sdf.parse("20050405");
        eventListForEvtDef.add(new EventValue(evtb1, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        eventListForEvtDef.add(new EventValue(sdf.parse("20050406"), EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        Date evtB1 = sdf.parse("20050505");
        eventListForEvtDef.add(new EventValue(evtB1, EventDefinition.NEURALNEUROPH, EventType.BULLISH, "toto"));
        eventListForEvtDef.add(new EventValue(sdf.parse("20050506"), EventDefinition.NEURALNEUROPH, EventType.BULLISH, "toto"));
        Date evtb2 = sdf.parse("20060505");
        eventListForEvtDef.add(new EventValue(evtb2, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        eventListForEvtDef.add(new EventValue(sdf.parse("20060506"), EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));

        //When
        List<PeriodRatingDTO> validPeriods = finalizer.validPeriods(quotations, stock, startDate, endDate,  eventListForEvtDef, noResMsg);

        System.out.println(validPeriods);

        //Then
        //[PeriodRatingDTO [from=Tue Apr 05 00:00:00 BST 2005, to=Thu May 05 00:00:00 BST 2005, priceAtFrom=94.6488, priceAtTo=113.9279, trend=BEARISH, rating=null, configs=[], realised=true],
        // PeriodRatingDTO [from=Thu May 05 00:00:00 BST 2005, to=Fri May 05 00:00:00 BST 2006, priceAtFrom=113.9279, priceAtTo=197.9107, trend=BULLISH, rating=null, configs=[], realised=true], 
        //PeriodRatingDTO [from=Fri May 05 00:00:00 BST 2006, to=Thu Jan 01 00:00:00 GMT 2015, priceAtFrom=197.9107, priceAtTo=526.4, trend=BEARISH, rating=null, configs=[], realised=true]]

        assertTrue(validPeriods.size()==3);

        Iterator<PeriodRatingDTO> iterator = validPeriods.iterator();
        PeriodRatingDTO next = iterator.next();
        assertTrue(next.getFrom().equals(evtb1));
        assertTrue(next.getTo().equals(evtB1));
        assertTrue(next.getTrend().equals(EventType.BEARISH.name()));
        PeriodRatingDTO next1 = iterator.next();
        assertTrue(next1.getFrom().equals(evtB1));
        assertTrue(next1.getTo().equals(evtb2));
        assertTrue(next1.getTrend().equals(EventType.BULLISH.name()));
        PeriodRatingDTO next3 = iterator.next();
        assertTrue(next3.getFrom().equals(evtb2));
        assertTrue(next3.getTo().equals(endQDate));
        assertTrue(next3.getTrend().equals(EventType.BEARISH.name()));
        
        //When
        TuningResDTO res = finalizer.buildResOnValidPeriods(validPeriods, qMap, quotations, stock, startDate, endDate, "toto", EventDefinition.NEURALNEUROPH.toString(), observer);
        
        System.out.println(res);
        
        //Then
        assertEquals(3, res.getPeriods().size());
        PeriodRatingDTO latestBear = res.getPeriods().get(res.getPeriods().size()-1);
        assertEquals(evtb2, latestBear.getFrom());
        assertEquals(endQDate, latestBear.getTo());
        assertEquals(EventType.BEARISH.name(), res.getLastTrend());
        assertEquals((next1.getPriceAtTo()-next1.getPriceAtFrom())/next1.getPriceAtFrom(), res.getFollowProfit(), 0.0001);
        Double closestCloseForDate = quotations.getClosestCloseSpForDate(endDate).doubleValue();
        assertEquals((closestCloseForDate-next.getPriceAtFrom())/next.getPriceAtFrom(), res.getStockPriceChange(), 0.0001);
        assertEquals(startDate, res.getCalculatedStart());
        assertEquals(endDate, res.getCalculatedEnd());


    }

    @Test
    public void testOneDaySpan() throws Exception, ParseException {

        //Given
        Collection<EventValue> eventListForEvtDef = new ArrayList<>();
        String noResMsg = "Ooops";

        Date evtb1 = sdf.parse("20050405");
        eventListForEvtDef.add(new EventValue(evtb1, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        eventListForEvtDef.add(new EventValue(sdf.parse("20050406"), EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        Date evtB1 = sdf.parse("20050505");
        eventListForEvtDef.add(new EventValue(evtB1, EventDefinition.NEURALNEUROPH, EventType.BULLISH, "toto"));
        eventListForEvtDef.add(new EventValue(sdf.parse("20050506"), EventDefinition.NEURALNEUROPH, EventType.BULLISH, "toto"));
        Date evtb2 = sdf.parse("20050507");
        eventListForEvtDef.add(new EventValue(evtb2, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        eventListForEvtDef.add(new EventValue(sdf.parse("20060506"), EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));

        //When
        List<PeriodRatingDTO> validPeriods = finalizer.validPeriods(quotations, stock, startDate, endDate,  eventListForEvtDef, noResMsg);

        System.out.println(validPeriods);

        //Then
        //[PeriodRatingDTO [from=Tue Apr 05 00:00:00 BST 2005, to=Thu May 05 00:00:00 BST 2005, priceAtFrom=94.6488, priceAtTo=113.9279, trend=BEARISH, rating=null, configs=[], realised=true], 
        //PeriodRatingDTO [from=Thu May 05 00:00:00 BST 2005, to=Thu Jan 01 00:00:00 GMT 2015, priceAtFrom=113.9279, priceAtTo=526.4, trend=BULLISH, rating=null, configs=[], realised=false]]

        assertTrue(validPeriods.size()==3);

        Iterator<PeriodRatingDTO> iterator = validPeriods.iterator();
        PeriodRatingDTO next = iterator.next();
        assertTrue(next.getFrom().equals(evtb1));
        assertTrue(next.getTo().equals(evtB1));
        assertTrue(next.getTrend().equals(EventType.BEARISH.name()));
        PeriodRatingDTO next1 = iterator.next();
        assertTrue(next1.getFrom().equals(evtB1));
        assertTrue(next1.getTo().equals(evtb2));
        assertTrue(next1.getTrend().equals(EventType.BULLISH.name()));
        PeriodRatingDTO next3 = iterator.next();
        assertTrue(next3.getFrom().equals(evtb2));
        assertTrue(next3.getTo().equals(endQDate));
        assertTrue(next3.getTrend().equals(EventType.BEARISH.name()));
        
        //When
        TuningResDTO res = finalizer.buildResOnValidPeriods(validPeriods, qMap, quotations, stock, startDate, endDate, "toto", EventDefinition.NEURALNEUROPH.toString(), observer);
        
        System.out.println(res);
        
        //Then
        assertEquals(3, res.getPeriods().size());
        PeriodRatingDTO latestBear = res.getPeriods().get(res.getPeriods().size()-1);
        assertEquals(evtb2, latestBear.getFrom());
        assertEquals(endQDate, latestBear.getTo());
        assertEquals(EventType.BEARISH.name(), res.getLastTrend());
        assertEquals((next1.getPriceAtTo()-next1.getPriceAtFrom())/next1.getPriceAtFrom(), res.getFollowProfit(), 0.0001);
        Double closestCloseForDate = quotations.getClosestCloseSpForDate(endDate).doubleValue();
        assertEquals((closestCloseForDate-next.getPriceAtFrom())/next.getPriceAtFrom(), res.getStockPriceChange(), 0.0001);
        assertEquals(startDate, res.getCalculatedStart());
        assertEquals(endDate, res.getCalculatedEnd());

    }

    @Test(expected=Exception.class)
    public void testErromeousEventsSameDate() throws Exception, ParseException {

        //Given
        Collection<EventValue> eventListForEvtDef = new ArrayList<>();
        String noResMsg = "Ooops";

        eventListForEvtDef.add(new EventValue(sdf.parse("20050405"), EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        eventListForEvtDef.add(new EventValue(sdf.parse("20050406"), EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        eventListForEvtDef.add(new EventValue(sdf.parse("20050505"), EventDefinition.NEURALNEUROPH, EventType.BULLISH, "toto"));
        eventListForEvtDef.add(new EventValue(sdf.parse("20050506"), EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        eventListForEvtDef.add(new EventValue(sdf.parse("20050506"), EventDefinition.NEURALNEUROPH, EventType.BULLISH, "toto"));
        eventListForEvtDef.add(new EventValue(sdf.parse("20060506"), EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));

        //When
        List<PeriodRatingDTO> validPeriods = finalizer.validPeriods(quotations, stock, startDate, endDate,  eventListForEvtDef, noResMsg);

        System.out.println(validPeriods);

        //Then
        //[PeriodRatingDTO [from=Tue Apr 05 00:00:00 BST 2005, to=Thu May 05 00:00:00 BST 2005, priceAtFrom=94.6488, priceAtTo=113.9279, trend=BEARISH, rating=null, configs=[], realised=true], 
        //PeriodRatingDTO [from=Thu May 05 00:00:00 BST 2005, to=Thu Jan 01 00:00:00 GMT 2015, priceAtFrom=113.9279, priceAtTo=526.4, trend=BULLISH, rating=null, configs=[], realised=false]]

        //[PeriodRatingDTO [from=Tue Apr 05 00:00:00 BST 2005, to=Thu May 05 00:00:00 BST 2005, priceAtFrom=94.6488, priceAtTo=113.9279, trend=BEARISH, rating=null, configs=[], realised=true], 
        //PeriodRatingDTO [from=Thu May 05 00:00:00 BST 2005, to=Fri May 06 00:00:00 BST 2005, priceAtFrom=113.9279, priceAtTo=114.4499, trend=BULLISH, rating=null, configs=[], realised=true], 
        //PeriodRatingDTO [from=Fri May 06 00:00:00 BST 2005, to=2014-12-31, priceAtFrom=114.4499, priceAtTo=526.4, trend=BEARISH, rating=null, configs=[], realised=false]]


        //assertTrue(validPeriods.isEmpty());
    }

    @Test
    public void testLastDayBearish() throws Exception, ParseException {

        //Given
        Collection<EventValue> eventListForEvtDef = new ArrayList<>();
        String noResMsg = "Ooops";

        Date evtb1 = sdf.parse("20050405");
        eventListForEvtDef.add(new EventValue(evtb1, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        Date evtB1 = sdf.parse("20050505");
        eventListForEvtDef.add(new EventValue(evtB1, EventDefinition.NEURALNEUROPH, EventType.BULLISH, "toto"));
        Date evtb2 = sdf.parse("20141231");
        eventListForEvtDef.add(new EventValue(evtb2, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));

        //When
        List<PeriodRatingDTO> validPeriods = finalizer.validPeriods(quotations, stock, startDate, endDate,  eventListForEvtDef, noResMsg);

        System.out.println(validPeriods);

        //Then
        //[PeriodRatingDTO [from=Tue Apr 05 00:00:00 BST 2005, to=Thu May 05 00:00:00 BST 2005, priceAtFrom=94.6488, priceAtTo=113.9279, trend=BEARISH, rating=null, configs=[], realised=true],
        //PeriodRatingDTO [from=Thu May 05 00:00:00 BST 2005, to=Thu Jan 01 00:00:00 GMT 2015, priceAtFrom=113.9279, priceAtTo=526.4, trend=BULLISH, rating=null, configs=[], realised=false]]

        assertTrue(validPeriods.size()==3);

        Iterator<PeriodRatingDTO> iterator = validPeriods.iterator();
        PeriodRatingDTO next = iterator.next();
        assertTrue(next.getFrom().equals(evtb1));
        assertTrue(next.getTo().equals(evtB1));
        assertTrue(next.getTrend().equals(EventType.BEARISH.name()));
        PeriodRatingDTO next1 = iterator.next();
        assertTrue(next1.getFrom().equals(evtB1));
        assertTrue(next1.getTo().equals(evtb2));
        assertTrue(next1.getTrend().equals(EventType.BULLISH.name()));
        PeriodRatingDTO next3 = iterator.next();
        assertTrue(next3.getFrom().equals(evtb2));
        assertTrue(next3.getTo().equals(endQDate));
        assertTrue(next3.getTrend().equals(EventType.BEARISH.name()));
        
        //When
        TuningResDTO res = finalizer.buildResOnValidPeriods(validPeriods, qMap, quotations, stock, startDate, endDate, "toto", EventDefinition.NEURALNEUROPH.toString(), observer);
        
        System.out.println(res);
        
        //Then
        assertEquals(3, res.getPeriods().size());
        PeriodRatingDTO latestBear = res.getPeriods().get(res.getPeriods().size()-1);
        assertEquals(evtb2, latestBear.getFrom());
        assertEquals(endQDate, latestBear.getTo());
        assertEquals(EventType.BEARISH.name(), res.getLastTrend());
        assertEquals((next1.getPriceAtTo()-next1.getPriceAtFrom())/next1.getPriceAtFrom(), res.getFollowProfit(), 0.0001);
        Double closestCloseForDate = quotations.getClosestCloseSpForDate(endDate).doubleValue();
        assertEquals((closestCloseForDate-next.getPriceAtFrom())/next.getPriceAtFrom(), res.getStockPriceChange(), 0.0001);
        assertEquals(startDate, res.getCalculatedStart());
        assertEquals(endDate, res.getCalculatedEnd());

    }

    @Test
    public void testFirstDayBearish() throws Exception, ParseException {

        //Given
        Collection<EventValue> eventListForEvtDef = new ArrayList<>();
        String noResMsg = "Ooops";

        Date evtb1 = sdf.parse("20050101");
        eventListForEvtDef.add(new EventValue(evtb1, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        Date evtB1 = sdf.parse("20050505");
        eventListForEvtDef.add(new EventValue(evtB1, EventDefinition.NEURALNEUROPH, EventType.BULLISH, "toto"));
        Date evtb2 = sdf.parse("20060101");
        eventListForEvtDef.add(new EventValue(evtb2, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));

        //When
        List<PeriodRatingDTO> validPeriods = finalizer.validPeriods(quotations, stock, startDate, endDate,  eventListForEvtDef, noResMsg);

        System.out.println(validPeriods);

        //Then
        //Empty

        assertTrue(validPeriods.size()==3);

        Iterator<PeriodRatingDTO> iterator = validPeriods.iterator();
        PeriodRatingDTO next = iterator.next();
        assertTrue(next.getFrom().equals(evtb1));
        assertTrue(next.getTo().equals(evtB1));
        assertTrue(next.getTrend().equals(EventType.BEARISH.name()));
        PeriodRatingDTO next1 = iterator.next();
        assertTrue(next1.getFrom().equals(evtB1));
        assertTrue(next1.getTo().equals(evtb2));
        assertTrue(next1.getTrend().equals(EventType.BULLISH.name()));
        PeriodRatingDTO next3 = iterator.next();
        assertTrue(next3.getFrom().equals(evtb2));
        assertTrue(next3.getTo().equals(endQDate));
        assertTrue(next3.getTrend().equals(EventType.BEARISH.name()));
        
        //When
        TuningResDTO res = finalizer.buildResOnValidPeriods(validPeriods, qMap, quotations, stock, startDate, endDate, "toto", EventDefinition.NEURALNEUROPH.toString(), observer);
        
        System.out.println(res);
        
        //Then
        assertEquals(3, res.getPeriods().size());
        PeriodRatingDTO latestBear = res.getPeriods().get(res.getPeriods().size()-1);
        assertEquals(evtb2, latestBear.getFrom());
        assertEquals(endQDate, latestBear.getTo());
        assertEquals(EventType.BEARISH.name(), res.getLastTrend());
        assertEquals((next1.getPriceAtTo()-next1.getPriceAtFrom())/next1.getPriceAtFrom(), res.getFollowProfit(), 0.0001);
        Double closestCloseForDate = quotations.getClosestCloseSpForDate(endDate).doubleValue();
        assertEquals((closestCloseForDate-next.getPriceAtFrom())/next.getPriceAtFrom(), res.getStockPriceChange(), 0.0001);
        assertEquals(startDate, res.getCalculatedStart());
        assertEquals(endDate, res.getCalculatedEnd());

    }
    

    @Test
    public void testNone() throws Exception, ParseException {

        //Given
        Collection<EventValue> eventListForEvtDef = new ArrayList<>();
        String noResMsg = "Ooops";

        Date evtb1 = sdf.parse("20050405");
        eventListForEvtDef.add(new EventValue(evtb1, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        eventListForEvtDef.add(new EventValue(sdf.parse("20050406"), EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        eventListForEvtDef.add(new EventValue(sdf.parse("20050505"), EventDefinition.NEURALNEUROPH, EventType.NONE, "toto"));
        Date evtB1 = sdf.parse("20050506");
        eventListForEvtDef.add(new EventValue(evtB1, EventDefinition.NEURALNEUROPH, EventType.BULLISH, "toto"));
        eventListForEvtDef.add(new EventValue(sdf.parse("20060508"), EventDefinition.NEURALNEUROPH, EventType.NONE, "toto"));
        Date evtb2 = sdf.parse("20060510");
        eventListForEvtDef.add(new EventValue(evtb2, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));

        //When
        List<PeriodRatingDTO> validPeriods = finalizer.validPeriods(quotations, stock, startDate, endDate, eventListForEvtDef, noResMsg);

        System.out.println(validPeriods);

        //Then
        //[PeriodRatingDTO [from=Tue Apr 05 00:00:00 BST 2005, to=Fri May 06 00:00:00 BST 2005, priceAtFrom=94.6488, priceAtTo=114.4499, trend=BEARISH, rating=null, configs=[], realised=true], 
        //PeriodRatingDTO [from=Fri May 06 00:00:00 BST 2005, to=Wed May 10 00:00:00 BST 2006, priceAtFrom=114.4499, priceAtTo=202.2675, trend=BULLISH, rating=null, configs=[], realised=true], 
        //PeriodRatingDTO [from=Wed May 10 00:00:00 BST 2006, to=Thu Jan 01 00:00:00 GMT 2015, priceAtFrom=202.2675, priceAtTo=526.4, trend=BEARISH, rating=null, configs=[], realised=true]]


        assertTrue(validPeriods.size()==3);
        Iterator<PeriodRatingDTO> iterator = validPeriods.iterator();
        PeriodRatingDTO next = iterator.next();
        assertTrue(next.getFrom().equals(evtb1));
        assertTrue(next.getTo().equals(evtB1));
        assertTrue(next.getTrend().equals(EventType.BEARISH.name()));
        PeriodRatingDTO next1 = iterator.next();
        assertTrue(next1.getFrom().equals(evtB1));
        assertTrue(next1.getTo().equals(evtb2));
        assertTrue(next1.getTrend().equals(EventType.BULLISH.name()));
        PeriodRatingDTO next3 = iterator.next();
        assertTrue(next3.getFrom().equals(evtb2));
        assertTrue(next3.getTo().equals(endQDate));
        assertTrue(next3.getTrend().equals(EventType.BEARISH.name()));
        
        //When
        TuningResDTO res = finalizer.buildResOnValidPeriods(validPeriods, qMap, quotations, stock, startDate, endDate, "toto", EventDefinition.NEURALNEUROPH.toString(), observer);
        
        System.out.println(res);
        
        //Then
        assertEquals(3, res.getPeriods().size());
        PeriodRatingDTO latestBear = res.getPeriods().get(res.getPeriods().size()-1);
        assertEquals(evtb2, latestBear.getFrom());
        assertEquals(endQDate, latestBear.getTo());
        assertEquals(EventType.BEARISH.name(), res.getLastTrend());
        assertEquals((next1.getPriceAtTo()-next1.getPriceAtFrom())/next1.getPriceAtFrom(), res.getFollowProfit(), 0.0001);
        Double closestCloseForDate = quotations.getClosestCloseSpForDate(endDate).doubleValue();
        assertEquals((closestCloseForDate-next.getPriceAtFrom())/next.getPriceAtFrom(), res.getStockPriceChange(), 0.0001);
        assertEquals(startDate, res.getCalculatedStart());
        assertEquals(endDate, res.getCalculatedEnd());

    }
    
    @Test
    public void testCompound() throws Exception, ParseException {

        //Given
        Collection<EventValue> eventListForEvtDef = new ArrayList<>();
        String noResMsg = "Ooops";

        Date evtb1 = sdf.parse("20050405");
        eventListForEvtDef.add(new EventValue(evtb1, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        Date evtB1 = sdf.parse("20050505");
        eventListForEvtDef.add(new EventValue(evtB1, EventDefinition.NEURALNEUROPH, EventType.BULLISH, "toto"));
        Date evtb2 = sdf.parse("20050605");
        eventListForEvtDef.add(new EventValue(evtb2, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        Date evtB2 = sdf.parse("20050705");
        eventListForEvtDef.add(new EventValue(evtB2, EventDefinition.NEURALNEUROPH, EventType.BULLISH, "toto"));
        Date evtb3 = sdf.parse("20050805");
        eventListForEvtDef.add(new EventValue(evtb3, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));

        //When
        List<PeriodRatingDTO> validPeriods = finalizer.validPeriods(quotations, stock, startDate, endDate,  eventListForEvtDef, noResMsg);

        System.out.println(validPeriods);

        //Then
        //[PeriodRatingDTO [from=Tue Apr 05 00:00:00 BST 2005, to=Thu May 05 00:00:00 BST 2005, priceAtFrom=94.6488, priceAtTo=113.9279, trend=BEARISH, rating=null, configs=[], realised=true],
        //PeriodRatingDTO [from=Thu May 05 00:00:00 BST 2005, to=Thu Jan 01 00:00:00 GMT 2015, priceAtFrom=113.9279, priceAtTo=526.4, trend=BULLISH, rating=null, configs=[], realised=false]]

        assertTrue(validPeriods.size()==5);

        Iterator<PeriodRatingDTO> iterator = validPeriods.iterator();
        PeriodRatingDTO next = iterator.next();
        assertTrue(next.getFrom().equals(evtb1));
        assertTrue(next.getTo().equals(evtB1));
        assertTrue(next.getTrend().equals(EventType.BEARISH.name()));
        PeriodRatingDTO next1 = iterator.next();
        assertTrue(next1.getFrom().equals(evtB1));
        assertTrue(next1.getTo().equals(evtb2));
        assertTrue(next1.getTrend().equals(EventType.BULLISH.name()));
        PeriodRatingDTO next3 = iterator.next();
        assertTrue(next3.getFrom().equals(evtb2));
        assertTrue(next3.getTo().equals(evtB2));
        assertTrue(next3.getTrend().equals(EventType.BEARISH.name()));
        PeriodRatingDTO next4 = iterator.next();
        assertTrue(next4.getFrom().equals(evtB2));
        assertTrue(next4.getTo().equals(evtb3));
        assertTrue(next4.getTrend().equals(EventType.BULLISH.name()));
        PeriodRatingDTO next5 = iterator.next();
        assertTrue(next5.getFrom().equals(evtb3));
        assertTrue(next5.getTo().equals(endQDate));
        assertTrue(next5.getTrend().equals(EventType.BEARISH.name()));
        
        //When
        TuningResDTO res = finalizer.buildResOnValidPeriods(validPeriods, qMap, quotations, stock, startDate, endDate, "toto", EventDefinition.NEURALNEUROPH.toString(), observer);
        
        System.out.println(res);
        
        //Then
        assertEquals(5, res.getPeriods().size());
        PeriodRatingDTO latestBear = res.getPeriods().get(res.getPeriods().size()-1);
        assertEquals(evtb3, latestBear.getFrom());
        assertEquals(endQDate, latestBear.getTo());
        assertEquals(EventType.BEARISH.name(), res.getLastTrend());
        double C1 = 1 + (next1.getPriceAtTo()-next1.getPriceAtFrom())/next1.getPriceAtFrom();
        double C2 = C1* ( 1 + (next4.getPriceAtTo()-next4.getPriceAtFrom())/next4.getPriceAtFrom());
        assertEquals(C2 -1, res.getFollowProfit(), 0.0001);
        Double closestCloseForDate = quotations.getClosestCloseSpForDate(endDate).doubleValue();
        assertEquals((closestCloseForDate-next.getPriceAtFrom())/next.getPriceAtFrom(), res.getStockPriceChange(), 0.0001);
        assertEquals(startDate, res.getCalculatedStart());
        assertEquals(endDate, res.getCalculatedEnd());

    }
    
    
    @Test
    public void testCompoundEndBull() throws Exception, ParseException {

        //Given
        Collection<EventValue> eventListForEvtDef = new ArrayList<>();
        String noResMsg = "Ooops";

        Date evtb1 = sdf.parse("20050405");
        eventListForEvtDef.add(new EventValue(evtb1, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        Date evtB1 = sdf.parse("20050505");
        eventListForEvtDef.add(new EventValue(evtB1, EventDefinition.NEURALNEUROPH, EventType.BULLISH, "toto"));
        Date evtb2 = sdf.parse("20050605");
        eventListForEvtDef.add(new EventValue(evtb2, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        Date evtB2 = sdf.parse("20050705");
        eventListForEvtDef.add(new EventValue(evtB2, EventDefinition.NEURALNEUROPH, EventType.BULLISH, "toto"));
        Date evtb3 = sdf.parse("20050805");
        eventListForEvtDef.add(new EventValue(evtb3, EventDefinition.NEURALNEUROPH, EventType.BEARISH, "toto"));
        Date evtB3 = sdf.parse("20050905");
        eventListForEvtDef.add(new EventValue(evtB3, EventDefinition.NEURALNEUROPH, EventType.BULLISH, "toto"));

        //When
        List<PeriodRatingDTO> validPeriods = finalizer.validPeriods(quotations, stock, startDate, endDate,  eventListForEvtDef, noResMsg);

        System.out.println(validPeriods);

        //Then
        //[PeriodRatingDTO [from=Tue Apr 05 00:00:00 BST 2005, to=Thu May 05 00:00:00 BST 2005, priceAtFrom=94.6488, priceAtTo=113.9279, trend=BEARISH, rating=null, configs=[], realised=true],
        //PeriodRatingDTO [from=Thu May 05 00:00:00 BST 2005, to=Thu Jan 01 00:00:00 GMT 2015, priceAtFrom=113.9279, priceAtTo=526.4, trend=BULLISH, rating=null, configs=[], realised=false]]

        assertTrue(validPeriods.size()==6);

        Iterator<PeriodRatingDTO> iterator = validPeriods.iterator();
        PeriodRatingDTO next = iterator.next();
        assertTrue(next.getFrom().equals(evtb1));
        assertTrue(next.getTo().equals(evtB1));
        assertTrue(next.getTrend().equals(EventType.BEARISH.name()));
        PeriodRatingDTO next1 = iterator.next();
        assertTrue(next1.getFrom().equals(evtB1));
        assertTrue(next1.getTo().equals(evtb2));
        assertTrue(next1.getTrend().equals(EventType.BULLISH.name()));
        PeriodRatingDTO next3 = iterator.next();
        assertTrue(next3.getFrom().equals(evtb2));
        assertTrue(next3.getTo().equals(evtB2));
        assertTrue(next3.getTrend().equals(EventType.BEARISH.name()));
        PeriodRatingDTO next4 = iterator.next();
        assertTrue(next4.getFrom().equals(evtB2));
        assertTrue(next4.getTo().equals(evtb3));
        assertTrue(next4.getTrend().equals(EventType.BULLISH.name()));
        PeriodRatingDTO next5 = iterator.next();
        assertTrue(next5.getFrom().equals(evtb3));
        assertTrue(next5.getTo().equals(evtB3));
        assertTrue(next5.getTrend().equals(EventType.BEARISH.name()));
        PeriodRatingDTO next6 = iterator.next();
        assertTrue(next6.getFrom().equals(evtB3));
        assertTrue(next6.getTo().equals(endQDate));
        assertTrue(next6.getTrend().equals(EventType.BULLISH.name()));
        
        //When
        TuningResDTO res = finalizer.buildResOnValidPeriods(validPeriods, qMap, quotations, stock, startDate, endDate, "toto", EventDefinition.NEURALNEUROPH.toString(), observer);
        
        System.out.println(res);
        
        //Then
        assertEquals(6, res.getPeriods().size());
        PeriodRatingDTO latest = res.getPeriods().get(res.getPeriods().size()-1);
        assertEquals(evtB3, latest.getFrom());
        assertEquals(endQDate, latest.getTo());
        assertEquals(EventType.BULLISH.name(), res.getLastTrend());
        double C1 = 1 + (next1.getPriceAtTo()-next1.getPriceAtFrom())/next1.getPriceAtFrom();
        double C2 = C1* (1 + (next4.getPriceAtTo()-next4.getPriceAtFrom())/next4.getPriceAtFrom());
        double C3 = C2* (1 + (next6.getPriceAtTo()-next6.getPriceAtFrom())/next6.getPriceAtFrom());
        assertEquals(C3 -1, res.getFollowProfit(), 0.0001);
        Double closestCloseForDate = quotations.getClosestCloseSpForDate(endDate).doubleValue();
        assertEquals((closestCloseForDate-next.getPriceAtFrom())/next.getPriceAtFrom(), res.getStockPriceChange(), 0.0001);
        assertEquals(startDate, res.getCalculatedStart());
        assertEquals(endDate, res.getCalculatedEnd());

    }


}
