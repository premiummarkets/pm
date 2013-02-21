insert into PORTFOLIO_NAME (select 'MonitorBuy', TYPE, TOTALINAMOUNTEVER, TOTALOUTAMOUNTEVER, BUYPONDERATIONRULE, SELLPONDERATIONRULE, PORTFOLIOCURRENCY  from portfolio_name where name = 'monitorBuy');
insert into portfolio  (select SYMBOL, ISIN, QUANTITY, CASHIN, CASHOUT, 'MonitorBuy', MONITOR, BUYDATE, AVGBUYPRICE, TRANSACTIONCURRENCY, ACCOUNT from portfolio  where name = 'monitorBuy');

update  ALERTS  set name = 'MonitorBuy' where name = 'monitorBuy';

delete from portfolio where name = 'monitorBuy';
delete from portfolio_name  where name = 'monitorBuy';
