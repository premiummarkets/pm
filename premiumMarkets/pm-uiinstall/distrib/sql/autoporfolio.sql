alter table events alter column message set data type VARCHAR(256);
alter table TREND_SUPPLEMENT add column "TRENDDATE" DATE NOT NULL DEFAULT '1970-01-01';
alter table trend_supplement drop constraint "TREND_SUPPLEMENT_PKEY";
ALTER TABLE "APP"."TREND_SUPPLEMENT" ADD CONSTRAINT "TREND_SUPPLEMENT_PKEY" UNIQUE ("TRENDDATE","SYMBOL", "ISIN");

alter table PORTFOLIO_NAME add column buyPonderationRule blob;
alter table PORTFOLIO_NAME add column sellPonderationRule blob;

alter table events alter column ANALYSENAME set data type VARCHAR(256);

CREATE INDEX "APP"."TUNING" ON "APP"."EVENTS" ("DATE","ANALYSENAME","EVENTTYPE","SYMBOL", "ISIN");

--alter table TREND_SUPPLEMENT drop column "TRENDDATE";
----alter table trend_supplement drop constraint "TREND_SUPPLEMENT_PKEY";
--alter table TREND_SUPPLEMENT add column "TRENDDATE" VARCHAR(20) NOT NULL DEFAULT '0'; 
--ALTER TABLE "APP"."TREND_SUPPLEMENT" ADD CONSTRAINT "TREND_SUPPLEMENT_PKEY" UNIQUE ("TRENDDATE","SYMBOL", "ISIN");

--TODO
select count(*) from quotations;
create table quotation_uniq as select distinct * from quotations;
select count(*) from quotation_uniq;
-- check and copy table
ALTER TABLE "APP"."QUOTATIONS" ADD CONSTRAINT "QUOTATIONS_PKEY" UNIQUE ("DATE","SYMBOL","ISIN");
-- END TODO

delete from ALERTS;
ALTER TABLE "APP"."ALERTS" ADD COLUMN "SYMBOL" VARCHAR(20);
ALTER TABLE "APP"."ALERTS" ADD COLUMN "ISIN" VARCHAR(20);
ALTER TABLE "APP"."ALERTS" ADD COLUMN "NAME" VARCHAR(255);
ALTER TABLE "APP"."ALERTS" DROP CONSTRAINT "FK_ALERTS_TO_PORTFOLIO" ;
ALTER TABLE "APP"."PORTFOLIO" DROP CONSTRAINT  "ID_PK" ;
CREATE TABLE "APP"."PORTFOLIOTMP" ("SYMBOL" VARCHAR(20) NOT NULL, "ISIN" VARCHAR(20) NOT NULL, "QUANTITY" NUMERIC(19,5), "CASHIN" NUMERIC(19,2), "CASHOUT" NUMERIC(19,2), "NAME" VARCHAR(255) NOT NULL, "MONITOR" SMALLINT NOT NULL DEFAULT 0, "BUYDATE" DATE DEFAULT '01/01/1970', "AVGBUYPRICE" DECIMAL(9,2) DEFAULT 0, "TRANSACTIONCURRENCY" CHAR(3) NOT NULL DEFAULT 'EUR');
insert into "APP"."PORTFOLIOTMP" select distinct symbol, isin, QUANTITY , CASHIN, CASHOUT ,name, monitor, buydate, avgbuyprice , TRANSACTIONCURRENCY from portfolio;
select symbol, isin, name, count(*) from PORTFOLIOTMP GROUP BY  symbol, isin, name having count(*) >1;
delete from PORTFOLIOTMP where symbol in (select symbol from PORTFOLIOTMP where name = 'YAHOOINDICES,NDX:NASDAQ,NY:NYSE' GROUP BY  symbol having count(*) >1) and name = 'YAHOOINDICES,NDX:NASDAQ,NY:NYSE' and cashin = 0;
ALTER TABLE "APP"."PORTFOLIOTMP" ADD CONSTRAINT "PORTFOLIO_PKEY" PRIMARY KEY ("SYMBOL", "ISIN", "NAME");
rename table portfoliotmp to portfolio;
ALTER TABLE "APP"."ALERTS" ADD CONSTRAINT "FK_ALERTS_TO_PORTFOLIO" FOREIGN KEY ("SYMBOL", "ISIN", "NAME") REFERENCES "APP"."PORTFOLIO" ("SYMBOL", "ISIN","NAME") ON DELETE NO ACTION ON UPDATE NO ACTION;
alter table alerts drop column PORTFOLIOSHARE_ID;
ALTER TABLE "APP"."TREND_SUPPLEMENT" DROP CONSTRAINT "FK_TREND_SUPPLEMENT_TO_SHARES";
ALTER TABLE "APP"."ALERTS_ALERTS" DROP CONSTRAINT "FK_ALERTS_ALERTS_TO_ALERTS";
--ALTER TABLE "APP"."PORTFOLIO" DROP CONSTRAINT "FK_PORTFOLIO_TO_SHARES" ;
--ALTER TABLE "APP"."PORTFOLIO" DROP CONSTRAINT "FK_PORTFOLIO_TO_PORTFOLIO_NAME";
ALTER TABLE "APP"."TREND_SUPPLEMENT" ADD CONSTRAINT "FK_TREND_SUPPLEMENT_TO_SHARES" FOREIGN KEY ("SYMBOL", "ISIN") REFERENCES "APP"."SHARES" ("SYMBOL", "ISIN") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "APP"."ALERTS_ALERTS" ADD CONSTRAINT "FK_ALERTS_ALERTS_TO_ALERTS" FOREIGN KEY ("ALERTS_ID") REFERENCES "APP"."ALERTS" ("ID") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "APP"."PORTFOLIO" ADD CONSTRAINT "FK_PORTFOLIO_TO_SHARES" FOREIGN KEY ("SYMBOL", "ISIN") REFERENCES "APP"."SHARES" ("SYMBOL", "ISIN") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "APP"."PORTFOLIO" ADD CONSTRAINT "FK_PORTFOLIO_TO_PORTFOLIO_NAME" FOREIGN KEY ("NAME") REFERENCES "APP"."PORTFOLIO_NAME" ("NAME") ON DELETE NO ACTION ON UPDATE NO ACTION;


--alter table ALERTS_ALERTS alter column OPTIONALMESSAGE set data type varchar(1000);
alter table ALERTS_ALERTS drop column OPTIONALMESSAGE;
alter table ALERTS_ALERTS add column OPTIONALMESSAGE long varchar;
alter table events drop column MESSAGE;
alter table events add  column message long varchar;

update portfolio set monitor = 0 where name in (select name from portfolio_name where type='default' and buyponderationrule is null);



--checks
select count(*) from trend_supplement, portfolio where portfolio.symbol = trend_supplement.symbol and portfolio.name = 'YAHOOINDICES,FTSE:LSE,SBF250:EURONEXT' and trend_supplement.trenddate = CURRENT_DATE;
select count(*) from  portfolio where name = 'YAHOOINDICES,FTSE:LSE,SBF250:EURONEXT';
select count(*) from trend_supplement, portfolio where portfolio.symbol = trend_supplement.symbol and portfolio.name = 'YAHOOINDICES,NDX:NASDAQ,NY:NYSE' and trend_supplement.trenddate = CURRENT_DATE;
select count(*) from  portfolio where name = 'YAHOOINDICES,NDX:NASDAQ,NY:NYSE';
select count(*) from trend_supplement , portfolio where portfolio.symbol = trend_supplement.symbol and portfolio.name = 'YAHOOINDICES,FTSE:LSE,SBF250:EURONEXT'  and trend_supplement.trenddate = CURRENT_DATE and ((trend_supplement.BOURSOMEANRECOMMENDATIONs=0 AND trend_supplement.BOURSOBNAGROWTH=0 ) OR (trend_supplement.YAHOOMEANRECOMMENDATIONS=0 AND trend_supplement.YAHOOFORWARDPE=0 ));
select count(*) from trend_supplement , portfolio where portfolio.symbol = trend_supplement.symbol and portfolio.name = 'YAHOOINDICES,FTSE:LSE,SBF250:EURONEXT'  and trend_supplement.trenddate = CURRENT_DATE and ((trend_supplement.BOURSOMEANRECOMMENDATIONs=0 AND trend_supplement.BOURSOBNAGROWTH=0 ) AND (trend_supplement.YAHOOMEANRECOMMENDATIONS=0 AND trend_supplement.YAHOOFORWARDPE=0 ));
select count(*) from trend_supplement , portfolio where portfolio.symbol = trend_supplement.symbol and portfolio.name = 'YAHOOINDICES,NDX:NASDAQ,NY:NYSE'  and trend_supplement.trenddate = CURRENT_DATE and ((trend_supplement.BOURSOMEANRECOMMENDATIONs=0 AND trend_supplement.BOURSOBNAGROWTH=0 ) OR (trend_supplement.YAHOOMEANRECOMMENDATIONS=0 AND trend_supplement.YAHOOFORWARDPE=0 ));
select count(*) from trend_supplement , portfolio where portfolio.symbol = trend_supplement.symbol and portfolio.name = 'YAHOOINDICES,NDX:NASDAQ,NY:NYSE'  and trend_supplement.trenddate = CURRENT_DATE and ((trend_supplement.BOURSOMEANRECOMMENDATIONs=0 AND trend_supplement.BOURSOBNAGROWTH=0 ) AND (trend_supplement.YAHOOMEANRECOMMENDATIONS=0 AND trend_supplement.YAHOOFORWARDPE=0 ));


--ALTER TABLE "APP"."ALERTS" DROP CONSTRAINT "FK_ALERTS_TO_PORTFOLIO";
--ALTER TABLE "APP"."ALERTS" ADD CONSTRAINT "FK_ALERTS_TO_PORTFOLIO" FOREIGN KEY ("SYMBOL", "ISIN", "NAME") REFERENCES "APP"."PORTFOLIO" ("SYMBOL", "ISIN","NAME") ON DELETE CASCADE ON UPDATE CASCADE;


alter table portfolio drop column AVGBUYPRICE;