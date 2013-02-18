delete from USERS  where NAME='Sample Portfolio';
delete from PORTFOLIO where NAME='Sample Portfolio';
delete from PORTFOLIO_NAME where NAME='Sample Portfolio';


update PERF_SUPPLEMENT as A join (select symbol,isin, name, max(perfdate) as maxP from PERF_SUPPLEMENT where name <> 'UNKNOWN' group by  SYMBOL,ISIN,NAME) as B on A.symbol = B.symbol and A.isin = B.isin and A.name=B.name and A.perfdate = B.maxP set latest=1;