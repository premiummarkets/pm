
/pm-uiinstall/distrib/sql/rebuildEventTable.sql
/pm-uiinstall/distrib/sql/rebuildEventKeys.sql

delete from TUNEDCONF;
delete from PERF_SUPPLEMENT where PERFDATE > '2014-01-01';
update PERF_SUPPLEMENT as A join (select symbol,isin, name, max(perfdate) as maxP from PERF_SUPPLEMENT where name <> 'UNKNOWN' group by  SYMBOL,ISIN,NAME) as B on A.symbol = B.symbol and A.isin = B.isin and A.name=B.name and A.perfdate = B.maxP set latest=1;