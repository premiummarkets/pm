update shares set MARKETLISTPROVIDER='NYSE'  where symbol = 'DJI';
update quotations set CURRENCY='USD'  where symbol = 'DJI';

update shares set MARKETLISTPROVIDER='NYSE'  where symbol = 'NOV';
update quotations set CURRENCY='USD'  where symbol = 'NOV';
delete  from portfolio where symbol = 'NOV' and portfolio.name not like '%:NYSE%' and portfolio.name  like 'YAHOOINDICES%';

update shares set MARKETLISTPROVIDER='NASDAQ'  where symbol = 'IXIC';
update quotations set CURRENCY='USD'  where symbol = 'IXIC';

delete from quotations  where symbol = 'RSC.PA' and date < '2011-03-03'; 
update quotations set CURRENCY='EUR'  where symbol = 'RSC.PA';

delete from portfolio where isin = 'AMEX:XLE';
delete from portfolio where isin = 'AMEX:XLB';
delete from shares where isin = 'AMEX:XLE';
delete from shares where isin = 'AMEX:XLB';
delete from quotations  where isin in ('AMEX:XLE','AMEX:XLB');

delete from quotations where symbol = 'PERW.PA';
update shares set lastquote = '1970-01-01' where symbol = 'PERW.PA';

update quotations set CURRENCY='EUR'  where symbol = 'EADT.PA';

update shares set name='Industrial Select Sector SPDR' where symbol ='XLI';




