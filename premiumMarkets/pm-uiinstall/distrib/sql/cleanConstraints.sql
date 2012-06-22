--done
alter table ALERTS_ALERTS drop constraint ALERTS_ALERTS_ID_PKEY;
alter table ALERTS_ALERTS drop  constraint FK_ALERTSET;
alter table ALERTS drop constraint ALERTS_ID_PKEY;
alter table EVENTS drop constraint EVENTS_SYMBOL_PKEY;
alter table portfolio drop constraint FK_PF2_STOCK;
alter table PORTFOLIO drop constraint FK_PORTFOLIO;
alter table PORTFOLIO_NAME  drop constraint SQL080630224258290; 
alter table alerts  drop constraint FK_AS_PORTFOLIO;
alter table portfolio  drop constraint ID_PK;
alter table alerts drop constraint ALERTS_ID_PKEY;
alter table quotations drop constraint  QUOTATIONS_PKEY; 
alter table portfolio drop constraint FK_STOCK;
alter table shares drop constraint SHARES_PKEY; 

--SELECT TABLENAME, CONGLOMERATENAME FROM SYS.SYSCONGLOMERATES,SYS.SYSCONSTRAINTS,SYS.SYStables
--	WHERE SYS.SYSCONGLOMERATES.TABLEID = sys.SYSCONSTRAINTS.TABLEID
--	AND sys.SYSTABLES.TABLEID = SYS.SYSCONGLOMERATES.TABLEID
--	ANd SYS.SYSTABLES.TABLENAME = 'PORTFOLIO'; 

drop index SYMBOL_ISIN_DATE_INDEX;
drop index SYMBOL_ISIN_INDEX;
drop index SYMBOL_DATE_INDEX;
drop index SYMBOL_INDEX;

drop index EVENTS_ANALYSENAME_DATE;
drop index EVENTS_SYMBOL_DATE_ISIN_INDEX;
drop index EVENTS_DATE_INDEX;
drop index EVENTS_ANALYSENAME_INDEX;



update portfolio set ISIN='FR0000054314' where  symbol = 'LAC.PA';
update shares set symbol = '10308438.PA' where isin ='FR0010773507';
update portfolio set ISIN='FR0000121220' where isin = 'FR0010618413';
delete from shares where isin = 'FR0010618413';

update portfolio set SYMBOL='BRE' where symbol ='BRES';
delete from shares where isin = 'FR0010345389' and symbol = 'BRES';

update shares set MARKETLISTPROVIDER = 'NYSE' where isin in ('LU0048575426','LU0074838565');
update shares set LASTQUOTE = '1970-01-01' where isin in ('LU0048575426','LU0074838565');
delete from quotations where isin in  ('LU0048575426','LU0074838565');




