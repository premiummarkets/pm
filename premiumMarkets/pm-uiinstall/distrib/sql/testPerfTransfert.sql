delete from EVENTS where date > '2013-01-01';
delete from PERF_SUPPLEMENT where perfdate > '2013-01-01';
delete from QUOTATIONS where date > '2013-01-01';

update TUNEDCONF join (select symbol,isin, max(date) maxDate from EVENTS where EVENTS.eventdefid not in (503,507) group by SYMBOL, ISIN) E on TUNEDCONF.symbol=E.symbol and TUNEDCONF.isin=E.isin  set TUNEDCONF.lastcalculation=E.maxDate  where TUNEDCONF.lastcalculation <> E.maxDate;
update TUNEDCONF set LASTTUNING='1970-01-01', LASTCALCULATION='1970-01-01' where LASTTUNING > LASTCALCULATION;
insert into TUNEDCONF select EVENTS.symbol, EVENTS.isin, 'preTrainConfs.NEURAL.csv', 'NotUsed', B.minDate, C.maxDate , 1 from EVENTS join (select A.symbol, A.isin, min(A.date) minDate from EVENTS A group by A.symbol, A.isin) as B on B.symbol = EVENTS.symbol and EVENTS.isin = B.ISIN join (select A1.symbol, A1.isin, max(A1.date) maxDate from EVENTS A1 group by A1.symbol, A1.isin) as C on C.symbol = EVENTS.symbol and EVENTS.isin = C.ISIN left join TUNEDCONF on TUNEDCONF.SYMBOL = EVENTS.SYMBOL and TUNEDCONF.ISIN=EVENTS.ISIN where TUNEDCONF.SYMBOL is NULL and EVENTS.eventdefid not in (503,507) group by EVENTS.symbol, EVENTS.isin;



update SHARES join (select QUOTATIONS.SYMBOL, QUOTATIONS.ISIN, max(DATE) maxQDate from QUOTATIONS group by QUOTATIONS.SYMBOL, QUOTATIONS.ISIN) Q on SHARES.symbol=Q.symbol and SHARES.isin=Q.isin set lastquote = Q.maxQDate;

update PERF_SUPPLEMENT as A join (select symbol,isin from PERF_SUPPLEMENT where LATEST=1 group by  SYMBOL,ISIN,NAME,LATEST  having count(*) > 1) as B on A.symbol = B.symbol and A.isin = B.isin set LATEST = 0;
update PERF_SUPPLEMENT  set latest=0;
update PERF_SUPPLEMENT as A join (select symbol,isin, name, max(perfdate) as maxP from PERF_SUPPLEMENT where name <> 'UNKNOWN' group by  SYMBOL,ISIN,NAME) as B on A.symbol = B.symbol and A.isin = B.isin and A.name=B.name and A.perfdate = B.maxP set latest=1;

