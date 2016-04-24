-------------------------------
update shares set MARKETLISTPROVIDER='EURONEXT' where symbol = 'ICOV.L';
update quotations set currency = 'EUR' where symbol = 'ICOV.L';
update quotations set closevalue = closevalue / 100 , OPENVALUE = OPENVALUE /100 , HIGH = HIGH / 100, LOW = LOW / 100 where symbol ='ICOV.L';
--update shares set QUOTATIONPROVIDER = 'GOOGLE'  where symbol = 'ICOV.L';
update shares set QUOTATIONPROVIDER = 'DEFAULT'  where symbol = 'ICOV.L';


-- update quotations set closevalue = closevalue * 100 , OPENVALUE = OPENVALUE *100 , HIGH = HIGH * 100, LOW = LOW * 100 where symbol ='ICOV.L' and date < '2010-03-22';