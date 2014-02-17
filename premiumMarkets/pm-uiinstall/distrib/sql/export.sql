--update shares set QUOTATIONPROVIDER='INFLATION'  where symbol = 'Inflation';

drop view distrib_shares;
create view distrib_shares as select shares.* from shares join portfolio on portfolio.isin = shares.isin and portfolio.symbol = shares.symbol where portfolio.name in ('YAHOOINDICES,DJI:DJI,NDX:NASDAQ','NSEINDICES,CNX100:NSE,NIFTY:NSE') or (portfolio.symbol='Inflation' and portfolio.name='UNKNOWN');
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from distrib_shares','/home/guil/Developpement/newEclipse/premiumMarkets/pm-uiinstall/distrib/export/SHARES.dat',null,null,null);

--CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from weather','/home/guil/Developpement/newEclipse/premiumMarkets/pm-uiinstall/distrib/export/WEATHER.dat',null,null,null);

--create view nasdaq_trend as select trend_supplement.* from trend_supplement join portfolio on portfolio.isin = trend_supplement.isin and portfolio.symbol = trend_supplement.symbol where portfolio.name = 'YAHOOINDICES,NDX:NASDAQ,NY:NYSE';
--CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from nasdaq_trend','/home/guil/Developpement/newEclipse/premiumMarkets/pm-uiinstall/distrib/export/TREND_SUPPLEMENT.dat',null,null,null);

drop view distrib_quotations;
create view distrib_quotations as select quotations.* from quotations join portfolio on portfolio.isin = quotations.isin and portfolio.symbol = quotations.symbol where portfolio.name in ('YAHOOINDICES,DJI:DJI,NDX:NASDAQ','NSEINDICES,CNX100:NSE,NIFTY:NSE') or (portfolio.symbol='Inflation' and portfolio.name='UNKNOWN');
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from distrib_quotations','/home/guil/Developpement/newEclipse/premiumMarkets/pm-uiinstall/distrib/export/QUOTATIONS.dat',null,null,null);

drop view distrib_PORTFOLIO_NAME;
create view distrib_PORTFOLIO_NAME as select PORTFOLIO_NAME.* from PORTFOLIO_NAME where PORTFOLIO_NAME.name in ('YAHOOINDICES,DJI:DJI,NDX:NASDAQ','NSEINDICES,CNX100:NSE,NIFTY:NSE','UNKNOWN','Sample');
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from distrib_PORTFOLIO_NAME','/home/guil/Developpement/newEclipse/premiumMarkets/pm-uiinstall/distrib/export/PORTFOLIO_NAME.dat',null,null,null);

drop view distrib_PORTFOLIO;
create view distrib_PORTFOLIO as select PORTFOLIO.* from PORTFOLIO where PORTFOLIO.name in ('YAHOOINDICES,DJI:DJI,NDX:NASDAQ','NSEINDICES,CNX100:NSE,NIFTY:NSE','Sample') or (portfolio.symbol='Inflation' and portfolio.name='UNKNOWN');
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from distrib_PORTFOLIO','/home/guil/Developpement/newEclipse/premiumMarkets/pm-uiinstall/distrib/export/PORTFOLIO.dat',null,null,null);

drop view distrib_TRANSACTIONS;
create view distrib_TRANSACTIONS as select TRANSACTIONS.* from TRANSACTIONS join PORTFOLIO on TRANSACTIONS.symbol=PORTFOLIO.symbol and TRANSACTIONS.isin=PORTFOLIO.isin where PORTFOLIO.name='Sample';
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from distrib_TRANSACTIONS','/home/guil/Developpement/newEclipse/premiumMarkets/pm-uiinstall/distrib/export/TRANSACTIONS.dat',null,null,null);

CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from CURRENCYRATE','/home/guil/Developpement/newEclipse/premiumMarkets/pm-uiinstall/distrib/export/CURRENCYRATE.dat',null,null,null);