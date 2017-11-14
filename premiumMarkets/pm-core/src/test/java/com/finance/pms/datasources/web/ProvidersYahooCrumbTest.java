package com.finance.pms.datasources.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.finance.pms.SpringContext;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;

public class ProvidersYahooCrumbTest {

    private SpringContext springContext;

    private ProvidersYahooCrumb providersYahooCrumb;


    @Before
    public void setUp() throws Exception {

        springContext = new SpringContext("/home/guil/Developpement/newEclipse/premiumMarkets/pm-forecast/db.properties");
        springContext.loadBeans("connexions.xml", "swtclients.xml","talibanalysisservices.xml");
        springContext.refresh();

        providersYahooCrumb = springContext.getBean(ProvidersYahooCrumb.class);

    }

    @After
    public void tearDown() {
        springContext.close();
    }

    @Test
    public void testResolveURL() throws Exception {

        Stock stock = new Stock();
        stock.setSymbol("GOOG");
        stock.setIsin("US38259P5089");
        stock.setName("Google Inc.");
        stock.setMarketValuation(new MarketValuation(Market.NASDAQ));
        stock.setCategory(StockCategories.DEFAULT_CATEGORY);

        Date start = new SimpleDateFormat("dd/MM/yyyy").parse("10/03/2016");
        Date end =  new SimpleDateFormat("dd/MM/yyyy").parse("15/03/2016");

        MyUrl resolveUrlFor = providersYahooCrumb.resolveUrlFor(stock, start, end);

        
        //https://query1.finance.yahoo.com/v7/finance/download/GOOG?period1=1505260643&period2=1507852643&interval=1d&events=history&crumb=9Gm6dOkKT.K
        //https://query1.finance.yahoo.com/v7/finance/download/GOOG?period1=1505260794&period2=1507852794&interval=1d&events=history&crumb=9Gm6dOkKT.K
        //https://query1.finance.yahoo.com/v7/finance/download/GOOG?period1=1457568000000&period2=1458000000000interval=1d&events=history&crumb=7k217nhcu00vo
        //https://query1.finance.yahoo.com/v7/finance/download/GOOG?period1=1457568000&period2=1458000000&interval=1d&events=history&crumb=bplc8ghcu0170
        
        //ucs=sfcTs=1424209824&sfc=1&hs=5&eup=1&bnpt=1500288246; PRF=%3Dundefined%26t%3DGOOG%252BWU%252BAAPL%252BCSV%252BSAUS%252B2800.HK%252BGE%252BBULLP.PA%252BINXG.L%252BISF.L%252BBULLP.AS%252BFRAK%252BSLXX.L%252BCOUK.L%252BGILS.L%26cp%3Dinteractive4%26cd%3Dsymbol%26qsp-cht-slt-fc%3D1%26qct%3Dline; B=cdd94mtboqgfs&b=4&d=cRlp6nRpYEJhk1xAd1qzhEU08iw-&s=21&i=owzBeF7Vhg0PQBo09i46; T=z=qi03ZBq2b8ZBCS0fffYRrGl&a=QAE&sk=DAAoaTMJpZ5BCC&ks=EAAKcxkoxH4hooz9kerRa44Dg--~G&kt=EAA.XuNUy0Zjkv5U_ALsMioVA--~I&ku=FAArWITkvq0KZjYV7iCnhyRI1kfJjQQy1r6UGot9UpIyyYCsta91M..JUnAkH.4HtoJOtmR3JNWe9JnVNTorwCz0Re1T1D32Hl6lj1mZz8s_ZAHXXCF3WNLNrWfWQAjq3gZtfnOYUubeDCbruKzVGY2VSncWQ0_9HpCrB65z_GZfYc-~A&d=bnMBeWFob28BZwFGSk9UNlVRRkU2UVpJUlA3S1JSUU1BRDNYRQFhAVFBRQFhYwFBTDN1UUZjdAFhbAFwcmVtbWl1bW1hcmtldHMBc2MBbWJyX3JlZ2lzdHJhdGlvbgFmcwFCMWlERTdGWjMwaXEBenoBcWkwM1pCQTdF&af=JnRzPTE1MDc4MDUzNTQmcHM9Y2VjMUVfdTMyS2ZRX2F6RWZITjBGQS0t; F=d=6Ca7PL49vCLX0aMs9uuXbrXYPyLw5L5FOEq.7kvx9RuCklY-; PH=fn=Ep0c0MvdInjT2ixwH4zhtg--&l=en-US; Y=v=1&n=5v1sjh64dfk6q&l=apj2feu213ubfwnjwcimfxa0lnv1kk30n0m03mua/o&p=n2h000000000000&r=12m&lg=en-US&intl=us; SSL=v=1&s=mObSb.sfuYfC.iEA.mCPY4pv3.MCchwIfatpGGvwbdGs4E3bT73iHlSPn3pSzeBbMq91lu7r50jibA0FsqFyMQ--&kv=0&ku=ETicIq05v1q1OIf09M9GgG0MYFjTlZy.1Ar.AnkLB5H1C9A3x__BxG1l_nUa0CqAof5rpX2RY30NX.tc6p8434Nf0i7CA3vdnZzgVZg.k.qHi.GOVxvpv2laHslo3gcCf1g7p7XCjNyqGgwA_5HP2r3y2S3zu.52vPQkna2FC2M-~A; AO=u=1; DNT=1
        System.out.println(resolveUrlFor.getUrl());
        System.out.println(resolveUrlFor.getCookieString());
        
    }
    
    @Test
    public void testGetQuotes() throws Exception {

        Stock stock = new Stock();
        stock.setSymbol("IXIC");
        stock.setIsin("IXIC");
        stock.setName("NASDAQ COMPOSITE");
        stock.setMarketValuation(new MarketValuation(Market.NASDAQ));
        stock.setCategory(StockCategories.INDICES_OTHER);

        Date start = new SimpleDateFormat("yyyy-MM-dd").parse("2017-10-26");
        Date end =  new SimpleDateFormat("yyyy-MM-dd").parse("2017-10-27");

        providersYahooCrumb.getQuotes(stock, start, end);

    }
    
    
    @Test
    public void testCrumb() throws Exception {

        File ctest = new File("/home/guil/Developpement/tmp/ctest.html");
        String crumb = providersYahooCrumb.getCrumb(new FileInputStream(ctest));
        
        System.out.println(crumb);

    }
    
    @Test
    public void testUnicod() throws UnsupportedEncodingException {
        //\u002Fbgn01cWy9b => %5Cu002Fbgn01cWy9b
        //String unicoded = "Exr3SkLUf\u002Fm";
        String unicoded = "Exr3SkLUf\u002Fm";
        byte[] utf8 = unicoded.getBytes("UTF-8");
        String string = new String(utf8, "UTF-8");
        
        System.out.println("Unicoded :"+unicoded+" and from utf-8 back to string : "+string);
        
        String encodedUnicoded = URLEncoder.encode(unicoded,"UTF-8");
        String encodedString = URLEncoder.encode(string,"UTF-8");
        System.out.println("Url encoded from unicoded:"+encodedUnicoded+" and from string : "+encodedString);
    }

}
