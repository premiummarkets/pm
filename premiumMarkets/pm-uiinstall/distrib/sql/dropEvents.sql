--connect 'jdbc:derby:/home/guil/Developpement/Quotes/piggymarketsqueak-intra';
connect 'jdbc:derby:TATA/derby/piggymarketsqueak';

drop table events;
CREATE TABLE "APP"."EVENTS" ("DATE" TIMESTAMP NOT NULL, "SYMBOL" VARCHAR(20) NOT NULL, "ISIN" VARCHAR(20) NOT NULL, "EVENTDEF" CHAR(100), "EVENTTYPE" CHAR(1) NOT NULL, "ACCURACY" SMALLINT, "EVENTDEFID" SMALLINT NOT NULL, "EVENTDEFEXTENSION" VARCHAR(100) NOT NULL DEFAULT '', "ANALYSENAME" VARCHAR(256) NOT NULL DEFAULT 'AutoPortfolio', "MESSAGE" LONG VARCHAR);
ALTER TABLE "APP"."EVENTS" ADD CONSTRAINT "EVENTS_PKEY" UNIQUE ( "SYMBOL", "ISIN",  "ANALYSENAME", "DATE", "EVENTDEFID", "EVENTDEFEXTENSION");
--CREATE INDEX "APP"."EVENTS_STOCK_ANAME_DATE_ID" ON "APP"."EVENTS" ("SYMBOL", "ISIN", "ANALYSENAME", "DATE", "EVENTDEFID");  --Used
--CREATE INDEX "APP"."EVENTS_STOCK_ANAME_DATE_TYPE" ON "APP"."EVENTS" ("SYMBOL", "ISIN", "ANALYSENAME", "DATE", "EVENTTYPE"); --Used
--CREATE INDEX "APP"."EVENTS_STOCK_ANAME_TYPE_ID" ON "APP"."EVENTS" ("SYMBOL", "ISIN", "ANALYSENAME", "EVENTTYPE", "EVENTDEFID"); -- Used
--CREATE INDEX "APP"."EVENTS_ANAME_DATE_ID" ON "APP"."EVENTS" ("ANALYSENAME", "DATE", "EVENTDEFID"); -- Used
--CREATE INDEX "APP"."EVENTS_ANAME_DATE" ON "APP"."EVENTS" ("ANALYSENAME", "DATE"); -- Used
--CREATE INDEX "APP"."EVENTS_ANAME" ON "APP"."EVENTS" ("ANALYSENAME");  --Used
DROP INDEX "EVENTS_ANAME";
DROP INDEX "EVENTS_ANAME_DATE";
DROP INDEX "EVENTS_ANAME_DATE_ID";
DROP INDEX "EVENTS_STOCK_ANAME_TYPE_ID";
DROP INDEX "EVENTS_STOCK_ANAME_DATE_TYPE_ID";
DROP INDEX "EVENTS_STOCK_ANAME_DATE_TYPE";
DROP INDEX "EVENTS_STOCK_ANAME_DATE_ID";

CREATE INDEX "EVENTS_ANAME" on "EVENTS" ("ANALYSENAME");-- /minor usage?
CREATE INDEX "EVENTS_ANAME_DATE" on "EVENTS" ("ANALYSENAME","DATE"); -- /minor usage?
CREATE INDEX "EVENTS_ANAME_DATE_ID" on "EVENTS" ("ANALYSENAME","DATE","EVENTDEFID"); -- /minor usage?
CREATE INDEX "EVENTS_STOCK_ANAME_DATE_TYPE_ID" on "EVENTS" ("SYMBOL","ISIN","ANALYSENAME","DATE","EVENTTYPE","EVENTDEFID"); --
CREATE INDEX "EVENTS_STOCK_ANAME_DATE_TYPE" on "EVENTS" ("SYMBOL","ISIN","ANALYSENAME","DATE","EVENTTYPE");--
CREATE INDEX "EVENTS_STOCK_ANAME_DATE_ID" on "EVENTS" ("SYMBOL","ISIN","ANALYSENAME","DATE","EVENTDEFID");--	

delete from alerts;
delete from portfolio where portfolio.name in (select name from portfolio_name where type = 'TuningAutoPortfolio');
delete from portfolio_name where type = 'TuningAutoPortfolio';
delete from TUNEDCONF;

