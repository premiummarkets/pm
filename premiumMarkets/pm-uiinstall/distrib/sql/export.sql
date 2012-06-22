create view nasdaq_shares as select shares.* from shares join portfolio on portfolio.isin = shares.isin and portfolio.symbol = shares.symbol where portfolio.name = 'YAHOOINDICES,NDX:NASDAQ,NY:NYSE';
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from nasdaq_shares','/home/guil/Developpement/Quotes/pms/distrib/export/SHARES.dat',null,null,null);

CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from weather','/home/guil/Developpement/Quotes/pms/distrib/export/WEATHER.dat',null,null,null);

create view nasdaq_trend as select trend_supplement.* from trend_supplement join portfolio on portfolio.isin = trend_supplement.isin and portfolio.symbol = trend_supplement.symbol where portfolio.name = 'YAHOOINDICES,NDX:NASDAQ,NY:NYSE';
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from nasdaq_trend','/home/guil/Developpement/Quotes/pms/distrib/export/TREND_SUPPLEMENT.dat',null,null,null);

drop view nasdaq_quotations;
create view nasdaq_quotations as select quotations.* from quotations join portfolio on portfolio.isin = quotations.isin and portfolio.symbol = quotations.symbol where portfolio.name = 'YAHOOINDICES,NDX:NASDAQ,NY:NYSE' and date >= '2006-01-01';
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from nasdaq_quotations','/home/guil/Developpement/Quotes/pms/distrib/export/QUOTATIONS.dat',null,null,null);

create view nasdaq_PORTFOLIO_NAME as select PORTFOLIO_NAME.* from PORTFOLIO_NAME where PORTFOLIO_NAME.name = 'YAHOOINDICES,NDX:NASDAQ,NY:NYSE';
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from nasdaq_PORTFOLIO_NAME','/home/guil/Developpement/Quotes/pms/distrib/export/PORTFOLIO_NAME.dat',null,null,null);

create view nasdaq_PORTFOLIO as select PORTFOLIO.* from PORTFOLIO where PORTFOLIO.name = 'YAHOOINDICES,NDX:NASDAQ,NY:NYSE';
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from nasdaq_PORTFOLIO','/home/guil/Developpement/Quotes/pms/distrib/export/PORTFOLIO.dat',null,null,null);

CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from CURRENCYRATE','/home/guil/Developpement/Quotes/pms/distrib/export/CURRENCYRATE.dat',null,null,null);