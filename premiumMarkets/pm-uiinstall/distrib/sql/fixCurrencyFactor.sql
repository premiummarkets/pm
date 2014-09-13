select * from shares where currency = 'GBP' and currencyfactor = 1.00;

--select  shares.SYMBOL, max(quotations.date) from quotations join shares on shares.symbol = quotations.symbol where shares.currency = 'GBP' and shares.currencyfactor = 1.00 group by shares.SYMBOL;

select * from QUOTATIONS where symbol='MIDD.L' order by date;

select * from quotations where CLOSEVALUE > 90*14.6200 and symbol='MIDD.L';
update quotations set OPENVALUE=OPENVALUE/100, CLOSEVALUE=CLOSEVALUE/100, HIGH=HIGH/100, LOW =LOW/100, ORIGIN=1 where CLOSEVALUE > 9*14.6200 and symbol='MIDD.L';

select * from QUOTATIONS where symbol='SLXX.L' order by date;

select * from quotations where CLOSEVALUE > 90*132 and symbol='SLXX.L';
update quotations set OPENVALUE=OPENVALUE/100, CLOSEVALUE=CLOSEVALUE/100, HIGH=HIGH/100, LOW =LOW/100, ORIGIN=1 where CLOSEVALUE > 9*132 and symbol='SLXX.L';


select * from QUOTATIONS where symbol='IGLT.L' order by date;

select * from quotations where CLOSEVALUE > 90*11 and symbol='IGLT.L';
update quotations set OPENVALUE=OPENVALUE/100, CLOSEVALUE=CLOSEVALUE/100, HIGH=HIGH/100, LOW =LOW/100, ORIGIN=1 where CLOSEVALUE > 9*11 and symbol='IGLT.L';

select * from QUOTATIONS where symbol='ISF.L' order by date;

select * from quotations where CLOSEVALUE > 90*6 and symbol='ISF.L';
update quotations set OPENVALUE=OPENVALUE/100, CLOSEVALUE=CLOSEVALUE/100, HIGH=HIGH/100, LOW =LOW/100, ORIGIN=1 where CLOSEVALUE > 9*6 and symbol='ISF.L';