--DONE
alter table events drop column "ANALYSENAME";
alter table events add column "ANALYSENAME" VARCHAR(100) NOT NULL DEFAULT 'AutoPortfolio';


alter table events drop  constraint "EVENTS_SYMBOL_PKEY";
alter table events drop  constraint "EVENTS_SYMBOL_KEY";
ALTER TABLE "APP"."EVENTS" ADD CONSTRAINT "EVENTS_SYMBOL_PKEY" UNIQUE ("SYMBOL", "ISIN", "DATE", "EVENTDEFID","ANALYSENAME");