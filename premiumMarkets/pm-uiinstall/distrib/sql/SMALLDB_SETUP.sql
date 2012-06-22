--NO
delete from shares where shares.symbol not in (select shares.symbol from shares join portfolio on (portfolio.symbol = shares.symbol or portfolio.symbol = shares.isin ));

delete from quotations where symbol in (select quotations.symbol from quotations left outer join shares on quotations.symbol = shares.symbol where shares.symbol is NULL);


select count(*) from quotations left outer join shares on quotations.symbol = shares.symbol where shares.symbol is NULL


select distinct quotations.symbol, quotations.isin from quotations left outer join shares 
on quotations.symbol=shares.symbol and quotations.isin = shares.isin 
where shares.symbol is null or shares.isin is null
