--done
select a.symbol,b.symbol, a.isin,b.isin, a.name , b.name, a.marketlistprovider, b.marketlistprovider
		from shares as a join shares as b on a.isin = b.isin where a.symbol <> b.symbol and a.marketlistprovider = b.marketlistprovider;

delete  from portfolio where symbol like '.%';
delete from shares  where symbol like '.%'; 

delete  from portfolio where isin in (select isin from shares  where  sector_hint is null and CATEGORY = 'DEFAULT_CATEGORY');
delete from TREND_SUPPLEMENT;
delete from shares  where  sector_hint is null and CATEGORY = 'DEFAULT_CATEGORY';
delete from portfolio where isin in (select a.isin from shares as a join shares as b on a.isin = b.isin where a.symbol <> b.symbol and a.marketlistprovider = b.marketlistprovider);
delete from shares where isin in (select a.isin from shares as a join shares as b on a.isin = b.isin where a.symbol <> b.symbol and a.marketlistprovider = b.marketlistprovider);

delete from portfolio where symbol in (select symbol from  shares where symbol not like '%.%' and category = 'DEFAULT_CATEGORY');
delete  from  shares where symbol not like '%.%' and category = 'DEFAULT_CATEGORY';

delete from events where  ANALYSENAME like '%Screen%';

delete from portfolio where isin in (select isin  from shares where  name = isin and symbol = isin);
delete from trend_supplement  where isin in (select isin  from shares where  name = isin and symbol = isin);
delete from shares where  name = isin and symbol = isin;

delete from portfolio where isin in (select isin  from shares where  name = isin and category='DEFAULT_CATEGORY');
delete from trend_supplement  where isin in (select isin  from shares where  name = isin and category='DEFAULT_CATEGORY');
delete from shares where  name = isin and category='DEFAULT_CATEGORY';

delete from portfolio where isin in (select isin  from shares where  symbol = isin and category='DEFAULT_CATEGORY');
delete from trend_supplement  where isin in (select isin  from shares where  symbol = isin and category='DEFAULT_CATEGORY');
delete from shares where symbol = isin and category='DEFAULT_CATEGORY';
----------------------
update shares set YIELD=0 where YIELD=-1;
alter table shares drop column yield;
alter table trend_supplement add column "DIVIDEND" DECIMAL(10,2) DEFAULT 0;

