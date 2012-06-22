alter table quotations add column OPENVALUE_B DECIMAL(20,4);
alter table quotations add column CLOSEVALUE_B DECIMAL(20,4);
alter table quotations add column HIGH_B DECIMAL(20,4);
alter table quotations add column LOW_B DECIMAL(20,4);

update quotations set OPENVALUE_B=OPENVALUE;
update quotations set CLOSEVALUE_B=CLOSEVALUE;
update quotations set HIGH_B=HIGH;
update quotations set LOW_B=LOW; 

alter table quotations drop column OPENVALUE;
alter table quotations drop column CLOSEVALUE;
alter table quotations drop column HIGH; 
alter table quotations drop column LOW;

RENAME COLUMN QUOTATIONS.OPENVALUE_B TO OPENVALUE;
RENAME COLUMN QUOTATIONS.CLOSEVALUE_B TO CLOSEVALUE;
RENAME COLUMN QUOTATIONS.HIGH_B TO HIGH;
RENAME COLUMN QUOTATIONS.LOW_B TO LOW;

alter table portfolio add column CASHIN_B DECIMAL(20,4) DEFAULT 0;
alter table portfolio add column CASHOUT_B DECIMAL(20,4) DEFAULT 0;
alter table portfolio add column QUANTITY_B DECIMAL(20,4) DEFAULT 1;

update portfolio set CASHIN_B=CASHIN;
update portfolio set CASHOUT_B=CASHOUT;
update portfolio set QUANTITY_B=QUANTITY;

alter table portfolio drop column CASHIN;
alter table portfolio drop column CASHOUT;
alter table portfolio drop column QUANTITY; 

RENAME COLUMN portfolio.CASHIN_B TO CASHIN;
RENAME COLUMN portfolio.CASHOUT_B TO CASHOUT;
RENAME COLUMN portfolio.QUANTITY_B TO QUANTITY;

alter table CURRENCYRATE add column RATE_B DECIMAL(20,4) DEFAULT 1;
update CURRENCYRATE set RATE_B=RATE;
alter table CURRENCYRATE drop column RATE;
RENAME COLUMN CURRENCYRATE.RATE_B TO RATE;

connect 'jdbc:derby:/home/guil/Developpement/Quotes/piggymarketsqueak;upgrade=true';
CALL SYSCS_UTIL.SYSCS_UPDATE_STATISTICS('APP','QUOTATIONS',null);


--select portfolio.symbol, closevalue,BUYDATE from quotations,portfolio,portfolio_name where  quotations.symbol = portfolio.symbol and portfolio_name.name = portfolio.name and portfolio_name.type = 'default' and portfolio.cashin = 0 and portfolio.BUYDATE = quotations.date;
--select * from portfolio join portfolio_name on portfolio_name.name = portfolio.name where portfolio_name.type = 'default' and portfolio.cashin = 0;
--select * from portfolio where  cashin =0 and portfolio.name in (select name from portfolio_name where portfolio_name.type = 'default');

update portfolio set buydate = '2007-07-02' where id in (select id from portfolio join portfolio_name on portfolio_name.name = portfolio.name where portfolio_name.type = 'default' and portfolio.cashin = 0);
update portfolio set quantity = 1  where portfolio.quantity = 0 and portfolio.name in (select name from portfolio_name where portfolio_name.type = 'default');
create view tmp as select distinct portfolio.symbol, closevalue from quotations,portfolio, portfolio_name where quotations.symbol = portfolio.symbol and portfolio_name.name = portfolio.name and portfolio_name.type = 'default' and portfolio.cashin = 0 and portfolio.BUYDATE = quotations.date;
update portfolio set cashin = (select closevalue from tmp where portfolio.symbol = tmp.symbol) where  cashin = 0 and portfolio.name in (select name from portfolio_name where portfolio_name.type = 'default');
update portfolio set AVGBUYPRICE = cashin where quantity = 1 and cashout = 0 and portfolio.name in (select name from portfolio_name where portfolio_name.type = 'default');
drop view tmp;

delete from quotations where symbol = 'N225' and date in ('2011-02-16','2011-02-15');

