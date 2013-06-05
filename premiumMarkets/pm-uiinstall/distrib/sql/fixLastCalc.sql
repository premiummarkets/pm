-- Fix tuned conf calculation dates
--select TUNEDCONF.lastcalculatedevent,TUNEDCONF.CONFIGFILE, SHARES.*  from TUNEDCONF,SHARES where TUNEDCONF.symbol=SHARES.symbol and TUNEDCONF.isin=SHARES.isin and SHARES.lastquote < TUNEDCONF.lastcalculatedevent; -- and  TUNEDCONF.CONFIGFILE='preTrainConfs.NEURAL.csv';
--select TUNEDCONF.* from TUNEDCONF where TUNEDCONF.lastcalculatedevent > (select max(date) from EVENTS where EVENTS.eventdefid not in (503,507) and TUNEDCONF.symbol=EVENTS.symbol and TUNEDCONF.isin=EVENTS.isin);
select TUNEDCONF.*, maxDate from TUNEDCONF join (select symbol,isin, max(date) maxDate from EVENTS where EVENTS.eventdefid not in (503,508,507,201,302,401) group by SYMBOL, ISIN) E on TUNEDCONF.symbol=E.symbol and TUNEDCONF.isin=E.isin  where TUNEDCONF.lastcalculatedevent <> E.maxDate and  TUNEDCONF.CONFIGFILE='preTrainConfs.NEURAL.csv';
update TUNEDCONF join (select symbol,isin, max(date) maxDate from EVENTS where EVENTS.eventdefid not in (503,508,507,201,302,401) group by SYMBOL, ISIN) E on TUNEDCONF.symbol=E.symbol and TUNEDCONF.isin=E.isin  set TUNEDCONF.lastcalculatedevent=E.maxDate  where TUNEDCONF.lastcalculatedevent <> E.maxDate and  TUNEDCONF.CONFIGFILE='preTrainConfs.NEURAL.csv';;
-- derby  update TUNEDCONF set TUNEDCONF.lastcalculatedevent=(select date(max(date))from EVENTS where EVENTS.eventdefid not in (503,507,201,302,401) and EVENTS.symbol=tunedconf.symbol and events.isin= tunedconf.isin group by events.symbol,events.isin);
-- !! run these updates below as well !! (no events confs and no confs events)
select * from TUNEDCONF where lastcalculationstart > lastcalculatedevent;
update TUNEDCONF set lastcalculationstart='1970-01-01', lastcalculatedevent=NULL, lastcalculationend='1970-01-01' where lastcalculationstart > lastcalculatedevent or lastcalculatedevent is NULL;

--TODO : foreign key tunedconf => shares
delete from EVENTS where not exists (select * from SHARES where  EVENTS.symbol=SHARES.symbol and EVENTS.isin = SHARES.isin );
delete from TUNEDCONF where not exists (select * from SHARES where  TUNEDCONF.symbol=SHARES.symbol and TUNEDCONF.isin = SHARES.isin );

select EVENTS.symbol, EVENTS.isin, 'preTrainConfs.NEURAL.csv', B.minDate, C.maxDate from EVENTS join (select A.symbol, A.isin, min(A.date) minDate from EVENTS A group by A.symbol, A.isin) as B on B.symbol = EVENTS.symbol and EVENTS.isin = B.ISIN join (select A1.symbol, A1.isin, max(A1.date) maxDate from EVENTS A1 group by A1.symbol, A1.isin) as C on C.symbol = EVENTS.symbol and EVENTS.isin = C.ISIN left join TUNEDCONF on TUNEDCONF.SYMBOL = EVENTS.SYMBOL and TUNEDCONF.ISIN=EVENTS.ISIN where TUNEDCONF.SYMBOL is NULL and EVENTS.eventdefid not in (503,507) group by EVENTS.symbol, EVENTS.isin;
insert into TUNEDCONF select EVENTS.symbol, EVENTS.isin, 'preTrainConfs.NEURAL.csv', B.minDate, C.maxDate, C.maxDate from EVENTS join (select A.symbol, A.isin, min(A.date) minDate from EVENTS A group by A.symbol, A.isin) as B on B.symbol = EVENTS.symbol and EVENTS.isin = B.ISIN join (select A1.symbol, A1.isin, max(A1.date) maxDate from EVENTS A1 group by A1.symbol, A1.isin) as C on C.symbol = EVENTS.symbol and EVENTS.isin = C.ISIN left join TUNEDCONF on TUNEDCONF.SYMBOL = EVENTS.SYMBOL and TUNEDCONF.ISIN=EVENTS.ISIN where TUNEDCONF.SYMBOL is NULL and EVENTS.eventdefid not in (503,507) group by EVENTS.symbol, EVENTS.isin;
update TUNEDCONF set lastcalculationend = lastcalculatedevent where lastcalculatedevent is not NULL;


--Check no upper case in eventType  (should return 0)
select count(*) from EVENTS where EVENTTYPE COLLATE latin1_bin = 'B';
select count(*) from EVENTS where EVENTTYPE COLLATE latin1_bin = 'S';

-- Check events dates before or equal last quotation date
select * from EVENTS where date not like '%00:00:00';
update EVENTS set date=DATE(date) where date not like '%00:00:00';
--derby select * from EVENTS where  timestamp(date(EVENTS.date),'00.00.00') <> EVENTS.date;
--delete from EVENTS where date not like '%00:00:00';
select SHARES.lastquote, EVENTS.* from EVENTS, SHARES where SHARES.symbol=EVENTS.symbol and SHARES.isin=EVENTS.isin and EVENTS.date > DATE_ADD(SHARES.lastquote, INTERVAL 1 DAY) and EVENTS.eventdefid not in (503,507);
select SHARES.lastquote, EVENTS.* from EVENTS, SHARES where SHARES.symbol=EVENTS.symbol and SHARES.isin=EVENTS.isin and EVENTS.date >  DATE_ADD(SHARES.lastquote, INTERVAL 1 DAY);
--select distinct(SHARES.symbol) from SHARES, EVENTS where SHARES.symbol=EVENTS.symbol and SHARES.isin=EVENTS.isin and EVENTS.date >  DATE_ADD(SHARES.lastquote, INTERVAL 1 DAY) and EVENTS.eventdefid in (503,507);
--delete from EVENTS where EVENTS.eventdefid in (503,507);


-- Fix Perf after last quotations
--select PERF_SUPPLEMENT.* from PERF_SUPPLEMENT join EVENTS on  PERF_SUPPLEMENT.symbol = EVENTS.symbol and PERF_SUPPLEMENT.isin=EVENTS.isin where PERF_SUPPLEMENT.PERFDATE > EVENTS.DATE;
--select PERF_SUPPLEMENT.* from PERF_SUPPLEMENT where  PERF_SUPPLEMENT.PERFDATE > (select max(DATE) from EVENTS where PERF_SUPPLEMENT.symbol = EVENTS.symbol and PERF_SUPPLEMENT.isin=EVENTS.isin);
select PERF_SUPPLEMENT.* from PERF_SUPPLEMENT join SHARES on  PERF_SUPPLEMENT.symbol = SHARES.symbol and PERF_SUPPLEMENT.isin=SHARES.isin where PERF_SUPPLEMENT.PERFDATE > SHARES.LASTQUOTE;
delete from PERF_SUPPLEMENT where PERF_SUPPLEMENT.PERFDATE > (select SHARES.LASTQUOTE FROM SHARES where PERF_SUPPLEMENT.symbol = SHARES.symbol and PERF_SUPPLEMENT.isin=SHARES.isin);
select PERF_SUPPLEMENT.*, TUNEDCONF.*  from PERF_SUPPLEMENT join TUNEDCONF on  PERF_SUPPLEMENT.symbol = TUNEDCONF.symbol and PERF_SUPPLEMENT.isin=TUNEDCONF.isin where PERF_SUPPLEMENT.PERFDATE > TUNEDCONF.lastcalculatedevent and  TUNEDCONF.CONFIGFILE='preTrainConfs.NEURAL.csv';
delete from PERF_SUPPLEMENT where PERF_SUPPLEMENT.PERFDATE > (select TUNEDCONF.lastcalculatedevent FROM TUNEDCONF where PERF_SUPPLEMENT.symbol = TUNEDCONF.symbol and PERF_SUPPLEMENT.isin=TUNEDCONF.isin and TUNEDCONF.CONFIGFILE='preTrainConfs.NEURAL.csv';
delete from PERF_SUPPLEMENT where PERF_SUPPLEMENT.PERFDATE='0000-00-00';
select * from PERF_SUPPLEMENT where PERF_SUPPLEMENT.PERFDATE <= DATE_SUB(NOW(), INTERVAL 1 MONTH);
update PERF_SUPPLEMENT set latest=0 where PERF_SUPPLEMENT.PERFDATE <= DATE_SUB(NOW(), INTERVAL 1 MONTH) and latest=1;

-- Fix Perf latest duplicates
select SYMBOL,ISIN,NAME,LATEST, count(*) from PERF_SUPPLEMENT where LATEST=1 group by  SYMBOL,ISIN,NAME,LATEST  having count(*) > 1;
select * from PERF_SUPPLEMENT where symbol in (select SYMBOL from PERF_SUPPLEMENT where LATEST=1 group by  SYMBOL,ISIN,NAME,LATEST  having count(*) > 1) and latest=1;
update PERF_SUPPLEMENT as A join (select symbol,isin from PERF_SUPPLEMENT where LATEST=1 group by  SYMBOL,ISIN,NAME,LATEST  having count(*) > 1) as B on A.symbol = B.symbol and A.isin = B.isin set LATEST = 0;
--ReSet Perf latest to 1
update PERF_SUPPLEMENT  set latest=0;
update PERF_SUPPLEMENT as A join (select symbol,isin, name, max(perfdate) as maxP from PERF_SUPPLEMENT where name <> 'UNKNOWN' group by  SYMBOL,ISIN,NAME) as B on A.symbol = B.symbol and A.isin = B.isin and A.name=B.name and A.perfdate = B.maxP set latest=1;



-- Duplicate portfolio
select PORTFOLIO.* from PORTFOLIO, (select SYMBOL,ISIN, count(*) from PORTFOLIO group by  SYMBOL,ISIN having count(*) > 1) A where PORTFOLIO.SYMBOL=A.SYMBOL and  PORTFOLIO.ISIN=A.ISIN; 
CREATE TEMPORARY TABLE DUP_PORT_TMP as select PORTFOLIO.* from PORTFOLIO, (select SYMBOL,ISIN, count(*) from PORTFOLIO group by  SYMBOL,ISIN having count(*) > 1) A where PORTFOLIO.SYMBOL=A.SYMBOL and  PORTFOLIO.ISIN=A.ISIN;
delete from PORTFOLIO where exists (select * from DUP_PORT_TMP where DUP_PORT_TMP.SYMBOL=PORTFOLIO.SYMBOL and DUP_PORT_TMP.ISIN=PORTFOLIO.ISIN) and PORTFOLIO.NAME='UNKNOWN';

--Fix perf Unique PORTFOLIO
select A.SYMBOL,A.ISIN, A.perfdate,A.NAME,B.NAME from PERF_SUPPLEMENT as A join  PERF_SUPPLEMENT as B on A.SYMBOL = B.SYMBOL and A.ISIN=B.ISIN  where  A.PERFDATE=B.PERFDATE and A.NAME <> B.NAME  group by  SYMBOL,ISIN;
select PERF_SUPPLEMENT.*, B.NAME from PERF_SUPPLEMENT join PERF_SUPPLEMENT as B on PERF_SUPPLEMENT.SYMBOL = B.SYMBOL and PERF_SUPPLEMENT.ISIN=B.ISIN  where PERF_SUPPLEMENT.NAME <> B.NAME and PERF_SUPPLEMENT.LATEST=1 group by PERF_SUPPLEMENT.SYMBOL, PERF_SUPPLEMENT.ISIN, PERF_SUPPLEMENT.NAME;
CREATE TEMPORARY TABLE PERF_TMP as select A.SYMBOL,A.ISIN, A.perfdate,A.NAME from PERF_SUPPLEMENT as A join  PERF_SUPPLEMENT as B on A.SYMBOL = B.SYMBOL and A.ISIN=B.ISIN  where  A.PERFDATE=B.PERFDATE and A.NAME <> B.NAME and A.NAME='UNKNOWN' group by  SYMBOL,ISIN;
delete from PERF_SUPPLEMENT where exists (select * from  PERF_TMP where PERF_SUPPLEMENT.SYMBOL = PERF_TMP.SYMBOL and PERF_SUPPLEMENT.ISIN = PERF_TMP.ISIN and PERF_SUPPLEMENT.PERFDATE = PERF_TMP.PERFDATE and PERF_SUPPLEMENT.NAME = PERF_TMP.NAME);
update PERF_SUPPLEMENT join (select A.* from PERF_SUPPLEMENT A join PERF_SUPPLEMENT as B on A.SYMBOL = B.SYMBOL and A.ISIN=B.ISIN  where A.NAME <> B.NAME and A.LATEST=1 group by A.SYMBOL, A.ISIN, A.NAME) as C on C.SYMBOL=PERF_SUPPLEMENT.SYMBOL and C.ISIN=PERF_SUPPLEMENT.ISIN set PERF_SUPPLEMENT.NAME=C.NAME;

--Check on NON obsolet stocks with NO LATEST PERFS
select SHARES.*, PORTFOLIO.NAME, PERF_SUPPLEMENT.PERFDATE from SHARES left join PERF_SUPPLEMENT on SHARES.SYMBOL=PERF_SUPPLEMENT.SYMBOL join PORTFOLIO on PORTFOLIO.SYMBOL=SHARES.SYMBOL where PERF_SUPPLEMENT.SYMBOL is NULL and SHARES.LASTQUOTE > SUBDATE(CURRENT_DATE(),'31') order by PORTFOLIO.NAME;

--Update last quote in SHARES V. QUOTATIONS
select SHARES.* from SHARES join (select QUOTATIONS.SYMBOL, QUOTATIONS.ISIN, max(DATE) maxQDate from QUOTATIONS group by QUOTATIONS.SYMBOL, QUOTATIONS.ISIN) Q on SHARES.symbol=Q.symbol and SHARES.isin=Q.isin where SHARES.lastquote <> Q.maxQDate;
update SHARES join (select QUOTATIONS.SYMBOL, QUOTATIONS.ISIN, max(DATE) maxQDate from QUOTATIONS group by QUOTATIONS.SYMBOL, QUOTATIONS.ISIN) Q on SHARES.symbol=Q.symbol and SHARES.isin=Q.isin set lastquote = Q.maxQDate;

--!!!Not in prod : Check latest trend in EVENTS V. PERF_SUPPLEMENTS (here for DJI)
--!!!Not in prod : run  adminGWT.html?mrk=YAHOOINDICES,DJI:NYSE&startIdx=0&endIdx=40&eventDef=NEURAL
 select EVENTS.SYMBOL, EVENTS.ISIN, EVENTS.DATE, EVENTS.EVENTTYPE, PERF_SUPPLEMENT.TRENDFORECAST from EVENTS \
 join PORTFOLIO on PORTFOLIO.SYMBOL=EVENTS.SYMBOL and PORTFOLIO.ISIN=EVENTS.ISIN \
 join (select EVENTS.SYMBOL, EVENTS.ISIN, max(EVENTS.date) maxDATE from EVENTS where EVENTS.EVENTDEF='NEURAL' group by EVENTS.SYMBOL, EVENTS.ISIN) maxEVTS on maxEVTS.maxDATE = EVENTS.DATE and maxEVTS.SYMBOL = EVENTS.SYMBOL and maxEVTS.ISIN=EVENTS.ISIN \
 join PERF_SUPPLEMENT on  PERF_SUPPLEMENT.SYMBOL = EVENTS.SYMBOL and PERF_SUPPLEMENT.ISIN=EVENTS.ISIN \
 where PORTFOLIO.NAME='YAHOOINDICES,DJI:NYSE' and EVENTS.EVENTDEF='NEURAL' and PERF_SUPPLEMENT.LATEST=1;
 
--Fix portfolio descrepencies V PERF Table
 select * from PERF_SUPPLEMENT left join PORTFOLIO on PERF_SUPPLEMENT.SYMBOL = PORTFOLIO.SYMBOL and PERF_SUPPLEMENT.ISIN = PORTFOLIO.ISIN and PERF_SUPPLEMENT.NAME = PORTFOLIO.NAME where PERF_SUPPLEMENT.latest=1 and PORTFOLIO.SYMBOL is NULL;
 insert into PORTFOLIO \
 select SYMBOL, ISIN, ''--fill it in --', QUANTITY, CASHIN, CASHOUT, MONITOR, BUYDATE, AVGBUYPRICE, TRANSACTIONCURRENCY, ACCOUNT from PORTFOLIO  where PORTFOLIO.SYMBOL in \
 (select PERF_SUPPLEMENT.SYMBOL from PERF_SUPPLEMENT left join PORTFOLIO on PERF_SUPPLEMENT.SYMBOL = PORTFOLIO.SYMBOL and PERF_SUPPLEMENT.ISIN = PORTFOLIO.ISIN and PERF_SUPPLEMENT.NAME = PORTFOLIO.NAME where PERF_SUPPLEMENT.latest=1 and PORTFOLIO.SYMBOL is NULL);
 

