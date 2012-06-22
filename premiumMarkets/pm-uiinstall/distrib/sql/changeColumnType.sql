--done
alter table  portfolio  add column QUANTITYB FLOAT;
update portfolio set QUANTITYB = quantity;
alter table portfolio drop column QUANTITY;
alter table portfolio add column QUANTITY FLOAT;
update portfolio set quantity = quantityb;
alter table portfolio drop column QUANTITYb;
alter table  portfolio alter column QUANTITY not null;


alter table  portfolio  add column CASHINB FLOAT;
update portfolio set CASHINB = CASHIN;
alter table portfolio drop column CASHIN;
alter table portfolio add column CASHIN FLOAT;
update portfolio set CASHIN = CASHINb;
alter table portfolio drop column CASHINb;
alter table  portfolio alter column CASHIN not null;

alter table  portfolio  add column CASHOUTB FLOAT;
update portfolio set CASHOUTB = CASHOUT;
alter table portfolio drop column CASHOUT;
alter table portfolio add column CASHOUT FLOAT;
update portfolio set CASHOUT = CASHOUTb;
alter table portfolio drop column CASHOUTb;
alter table  portfolio alter column CASHOUT not null;


alter table  shares  add column SYMBOLB VARCHAR(20);
update shares set SYMBOLB = SYMBOL;
alter table shares drop column SYMBOL;
alter table shares add column SYMBOL VARCHAR(20);
update shares set SYMBOL = SYMBOLb;
alter table shares drop column SYMBOLb;
alter table  shares alter column SYMBOL not null;


alter table  shares  add column isinB VARCHAR(20);
update shares set isinB = isin;
alter table shares drop column isin;
alter table shares add column isin VARCHAR(20);
update shares set isin = isinb;
alter table shares drop column isinb;
alter table  shares alter column isin not null;

alter table  shares  add column nameB VARCHAR(100);
update shares set nameB = name;
alter table shares drop column name;
alter table shares add column name VARCHAR(100);
update shares set name = nameb;
alter table shares drop column nameb;
alter table  shares alter column name not null;

alter table  shares  add column categoryB VARCHAR(16);
update shares set categoryB = category;
alter table shares drop column category;
alter table shares add column category VARCHAR(16);
update shares set category = categoryb;
alter table shares drop column categoryb;

alter table  shares  add column QUOTATIONPROVIDERB VARCHAR(32);
update shares set QUOTATIONPROVIDERB = QUOTATIONPROVIDER;
alter table shares drop column QUOTATIONPROVIDER;
alter table shares add column QUOTATIONPROVIDER VARCHAR(32);
update shares set QUOTATIONPROVIDER = QUOTATIONPROVIDERb;
alter table shares drop column QUOTATIONPROVIDERb;
alter table  shares alter column QUOTATIONPROVIDER not null;

alter table  shares  add column MARKETLISTPROVIDERB VARCHAR(32);
update shares set MARKETLISTPROVIDERB = MARKETLISTPROVIDER;
alter table shares drop column MARKETLISTPROVIDER;
alter table shares add column MARKETLISTPROVIDER VARCHAR(32);
update shares set MARKETLISTPROVIDER = MARKETLISTPROVIDERb;
alter table shares drop column MARKETLISTPROVIDERb;
alter table  shares alter column MARKETLISTPROVIDER not null;

alter table  quotations  add column volumeb BIGINT; --DEFAULT 0;
update quotations set volumeb = volume;
alter table quotations drop column volume;
alter table quotations add column volume BIGINT;
update quotations set volume = volumeb;

alter table quotations drop column volumeb;
alter table quotations alter column volume DEFAULT 0;
