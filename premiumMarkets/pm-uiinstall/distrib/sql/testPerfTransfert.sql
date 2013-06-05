delete from EVENTS where date > '2013-04-01';
delete from PERF_SUPPLEMENT where perfdate > '2013-04-01';
delete from QUOTATIONS where date > '2013-04-01';

update TUNEDCONF join (select symbol,isin, max(date) maxDate from EVENTS where EVENTS.eventdefid not in (503,508,507,201,302,401) group by SYMBOL, ISIN) E on TUNEDCONF.symbol=E.symbol and TUNEDCONF.isin=E.isin  set TUNEDCONF.lastcalculatedevent=E.maxDate  where TUNEDCONF.lastcalculatedevent <> E.maxDate and  TUNEDCONF.CONFIGFILE='preTrainConfs.NEURAL.csv';;
update TUNEDCONF set lastcalculationstart='1970-01-01', lastcalculatedevent=NULL, lastcalculationend='1970-01-01' where lastcalculationstart > lastcalculatedevent or lastcalculatedevent is NULL;

update SHARES join (select QUOTATIONS.SYMBOL, QUOTATIONS.ISIN, max(DATE) maxQDate from QUOTATIONS group by QUOTATIONS.SYMBOL, QUOTATIONS.ISIN) Q on SHARES.symbol=Q.symbol and SHARES.isin=Q.isin set lastquote = Q.maxQDate;

update PERF_SUPPLEMENT as A join (select symbol,isin from PERF_SUPPLEMENT where LATEST=1 group by  SYMBOL,ISIN,NAME,LATEST  having count(*) > 1) as B on A.symbol = B.symbol and A.isin = B.isin set LATEST = 0;
update PERF_SUPPLEMENT  set latest=0;
update PERF_SUPPLEMENT as A join (select symbol,isin, name, max(perfdate) as maxP from PERF_SUPPLEMENT where name <> 'UNKNOWN' group by  SYMBOL,ISIN,NAME) as B on A.symbol = B.symbol and A.isin = B.isin and A.name=B.name and A.perfdate = B.maxP set latest=1;

delete from PERF_SUPPLEMENT where symbol='FTE.PA';
delete from TUNEDCONF where symbol='FTE.PA';
delete from ALERTS where symbol='FTE.PA';
delete from PORTFOLIO where symbol='FTE.PA';
delete from TREND_SUPPLEMENT where symbol='FTE.PA';
delete from SHARES where symbol='FTE.PA';


