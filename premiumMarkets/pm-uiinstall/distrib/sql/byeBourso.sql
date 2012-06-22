update shares set QUOTATIONPROVIDER='YAHOO' where QUOTATIONPROVIDER='BOURSORAMA';

--create table quotation_uniq as select * from quotations with no data;
--alter table quotation_uniq drop column symbol ;
--alter table quotation_uniq drop column isin;
--alter table quotation_uniq add column symbol varchar(20) NOT NULL DEFAULT 'NON_AVAILABLE';
--alter table quotation_uniq add column isin varchar(20) NOT NULL DEFAULT 'NON_AVAILABLE';
CREATE TABLE "APP"."QUOTATION_UNIQ" ("DATE" DATE NOT NULL, "SYMBOL" VARCHAR(20) NOT NULL, "ISIN" VARCHAR(20) NOT NULL, "CURRENCY" CHAR(3), "VOLUME" BIGINT DEFAULT 0, "OPENVALUE" DECIMAL(20,4), "CLOSEVALUE" DECIMAL(20,4), "HIGH" DECIMAL(20,4), "LOW" DECIMAL(20,4));
--insert into quotation_uniq select distinct * from quotations;
select count(*) from quotations;
insert into quotation_uniq select distinct * from quotations where exists (select * from shares where shares.symbol=quotations.symbol and shares.isin = quotations.isin);
select count(*) from quotation_uniq;
drop table quotations;
RENAME TABLE quotation_uniq TO quotations;
CREATE INDEX "APP"."SYMBOL_ISIN_DATE_INDEX" ON "APP"."QUOTATIONS" ("SYMBOL", "ISIN", "DATE");
CREATE INDEX "APP"."SYMBOL_ISIN_INDEX" ON "APP"."QUOTATIONS" ("SYMBOL", "ISIN");
SELECT count(*) FROM quotations as t2 where exists(select * from quotations as t1 where t1.symbol=t2.symbol and t1.isin = t2.isin and t1.date=t2.date and (t1.closevalue <> t2.closevalue or t1.volume <> t2.volume or t1.openvalue <> t2.openvalue or t1.high <> t2.high or t1.low <> t2.low));
delete FROM quotations as t2 where exists  (select * from quotations as t1 where t1.symbol=t2.symbol and t1.isin = t2.isin and t1.date=t2.date and (t1.closevalue <> t2.closevalue or t1.volume <> t2.volume or t1.openvalue <> t2.openvalue or t1.high <> t2.high or t1.low <> t2.low));
drop index "SYMBOL_ISIN_DATE_INDEX";
ALTER  TABLE "APP"."QUOTATIONS" ADD CONSTRAINT "QUOTATIONS_SYMBOL_ISIN_DATE"  PRIMARY KEY ("SYMBOL", "ISIN", "DATE");

update shares set lastquote = (select max(date) from quotations where quotations.symbol=shares.symbol and quotations.isin=shares.isin) where exists (select * from quotations where shares.symbol=quotations.symbol and shares.isin = quotations.isin);


--alter table quotations add column symbolvc varchar(20);
--update quotations set symbolvc=symbol;
--alter table quotations add column isinvc varchar(20);
--update quotations set isinvc=isin;
--alter table quotations drop column symbol;
--alter table quotations drop column isin;
--alter table quotations add column symbol varchar(20) NOT NULL;
--alter table quotations add column isin varchar(20) NOT NULL;
--update quotations set isin=isinvc;
--update quotations set symbol=symbolvc;
--alter table quotations drop column symbolvc;
--alter table quotations drop column isinvc;
CREATE TABLE "APP"."EVENTS" ("DATE" DATE NOT NULL, "SYMBOL" VARCHAR(20) NOT NULL, "ISIN" VARCHAR(20) NOT NULL, "EVENTDEF" CHAR(100), "EVENTTYPE" CHAR(1) NOT NULL, "ACCURACY" SMALLINT, "EVENTDEFID" SMALLINT NOT NULL,"ANALYSENAME" VARCHAR(256) NOT NULL DEFAULT 'AutoPortfolio', "MESSAGE" LONG VARCHAR);
ALTER TABLE "APP"."EVENTS" ADD CONSTRAINT "EVENTS_PKEY" UNIQUE ("DATE", "EVENTDEFID", "EVENTTYPE", "ANALYSENAME", "SYMBOL", "ISIN");
CREATE INDEX "APP"."TUNING" ON "APP"."EVENTS" ("DATE", "ANALYSENAME", "EVENTTYPE", "SYMBOL", "ISIN");
CREATE INDEX "APP"."EVENTS_ANALYSENAME_INDEX" ON "APP"."EVENTS" ("ANALYSENAME");
CREATE INDEX "APP"."EVENTS_ANALYSENAME_DATE" ON "APP"."EVENTS" ("ANALYSENAME", "DATE");


ALTER TABLE "APP"."TREND_SUPPLEMENT" DROP CONSTRAINT "FK_TREND_SUPPLEMENT_TO_SHARES";
ALTER TABLE "APP"."PORTFOLIO" DROP CONSTRAINT "FK_PORTFOLIO_TO_SHARES";
ALTER TABLE "APP"."ALERTS" DROP CONSTRAINT "FK_ALERTS_TO_PORTFOLIO";

update SHARES set symbol='LU0294219869.WMORN' where isin='LU0294219869';
update TREND_SUPPLEMENT set symbol='LU0294219869.WMORN' where isin='LU0294219869';
update PORTFOLIO set symbol='LU0294219869.WMORN' where isin='LU0294219869';
update ALERTS set symbol='LU0294219869.WMORN' where isin='LU0294219869';
update QUOTATIONS set symbol='LU0294219869.WMORN' where isin='LU0294219869';
update EVENTS set symbol='LU0294219869.WMORN' where isin='LU0294219869';
update TRANSACTIONS set symbol='LU0294219869.WMORN' where isin='LU0294219869';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'LU0294219869';


update SHARES set symbol='FRBM.NX' where symbol='FRBM';
update TREND_SUPPLEMENT set symbol='FRBM.NX' where symbol='FRBM';
update PORTFOLIO set symbol='FRBM.NX' where symbol='FRBM';
update ALERTS set symbol='FRBM.NX' where symbol='FRBM';
update QUOTATIONS set symbol='FRBM.NX' where symbol='FRBM';
update EVENTS set symbol='FRBM.NX' where symbol='FRBM';
update TRANSACTIONS set symbol='FRBM.NX' where symbol='FRBM';

update SHARES set symbol='FRCG.NX' where symbol='FRCG';
update TREND_SUPPLEMENT set symbol='FRCG.NX' where symbol='FRCG';
update PORTFOLIO set symbol='FRCG.NX' where symbol='FRCG';
update ALERTS set symbol='FRCG.NX' where symbol='FRCG';
update QUOTATIONS set symbol='FRCG.NX' where symbol='FRCG';
update EVENTS set symbol='FRCG.NX' where symbol='FRCG';
update TRANSACTIONS set symbol='FRCG.NX' where symbol='FRCG';

update SHARES set symbol='FRCS.NX' where symbol='FRCS';
update TREND_SUPPLEMENT set symbol='FRCS.NX' where symbol='FRCS';
update PORTFOLIO set symbol='FRCS.NX' where symbol='FRCS';
update ALERTS set symbol='FRCS.NX' where symbol='FRCS';
update QUOTATIONS set symbol='FRCS.NX' where symbol='FRCS';
update EVENTS set symbol='FRCS.NX' where symbol='FRCS';
update TRANSACTIONS set symbol='FRCS.NX' where symbol='FRCS';

update SHARES set symbol='FRFIN.NX' where symbol='FRFIN';
update TREND_SUPPLEMENT set symbol='FRFIN.NX' where symbol='FRFIN';
update PORTFOLIO set symbol='FRFIN.NX' where symbol='FRFIN';
update ALERTS set symbol='FRFIN.NX' where symbol='FRFIN';
update QUOTATIONS set symbol='FRFIN.NX' where symbol='FRFIN';
update EVENTS set symbol='FRFIN.NX' where symbol='FRFIN';
update TRANSACTIONS set symbol='FRFIN.NX' where symbol='FRFIN';

update SHARES set symbol='FRFPR.NX' where symbol='FRFPR';
update TREND_SUPPLEMENT set symbol='FRFPR.NX' where symbol='FRFPR';
update PORTFOLIO set symbol='FRFPR.NX' where symbol='FRFPR';
update ALERTS set symbol='FRFPR.NX' where symbol='FRFPR';
update QUOTATIONS set symbol='FRFPR.NX' where symbol='FRFPR';
update EVENTS set symbol='FRFPR.NX' where symbol='FRFPR';
update TRANSACTIONS set symbol='FRFPR.NX' where symbol='FRFPR';

update SHARES set symbol='FRHC.NX' where symbol='FRHC';
update TREND_SUPPLEMENT set symbol='FRHC.NX' where symbol='FRHC';
update PORTFOLIO set symbol='FRHC.NX' where symbol='FRHC';
update ALERTS set symbol='FRHC.NX' where symbol='FRHC';
update QUOTATIONS set symbol='FRHC.NX' where symbol='FRHC';
update EVENTS set symbol='FRHC.NX' where symbol='FRHC';
update TRANSACTIONS set symbol='FRHC.NX' where symbol='FRHC';

update SHARES set symbol='FRIN.NX' where symbol='FRIN';
update TREND_SUPPLEMENT set symbol='FRIN.NX' where symbol='FRIN';
update PORTFOLIO set symbol='FRIN.NX' where symbol='FRIN';
update ALERTS set symbol='FRIN.NX' where symbol='FRIN';
update QUOTATIONS set symbol='FRIN.NX' where symbol='FRIN';
update EVENTS set symbol='FRIN.NX' where symbol='FRIN';
update TRANSACTIONS set symbol='FRIN.NX' where symbol='FRIN';

update SHARES set symbol='FROG.NX' where symbol='FROG';
update TREND_SUPPLEMENT set symbol='FROG.NX' where symbol='FROG';
update PORTFOLIO set symbol='FROG.NX' where symbol='FROG';
update ALERTS set symbol='FROG.NX' where symbol='FROG';
update QUOTATIONS set symbol='FROG.NX' where symbol='FROG';
update EVENTS set symbol='FROG.NX' where symbol='FROG';
update TRANSACTIONS set symbol='FROG.NX' where symbol='FROG';

update SHARES set symbol='FRTEC.NX' where symbol='FRTEC';
update TREND_SUPPLEMENT set symbol='FRTEC.NX' where symbol='FRTEC';
update PORTFOLIO set symbol='FRTEC.NX' where symbol='FRTEC';
update ALERTS set symbol='FRTEC.NX' where symbol='FRTEC';
update QUOTATIONS set symbol='FRTEC.NX' where symbol='FRTEC';
update EVENTS set symbol='FRTEC.NX' where symbol='FRTEC';
update TRANSACTIONS set symbol='FRTEC.NX' where symbol='FRTEC';

update SHARES set symbol='FRTEL.NX' where symbol='FRTEL';
update TREND_SUPPLEMENT set symbol='FRTEL.NX' where symbol='FRTEL';
update PORTFOLIO set symbol='FRTEL.NX' where symbol='FRTEL';
update ALERTS set symbol='FRTEL.NX' where symbol='FRTEL';
update QUOTATIONS set symbol='FRTEL.NX' where symbol='FRTEL';
update EVENTS set symbol='FRTEL.NX' where symbol='FRTEL';
update TRANSACTIONS set symbol='FRTEL.NX' where symbol='FRTEL';

update SHARES set symbol='FRUT.NX' where symbol='FRUT';
update TREND_SUPPLEMENT set symbol='FRUT.NX' where symbol='FRUT';
update PORTFOLIO set symbol='FRUT.NX' where symbol='FRUT';
update ALERTS set symbol='FRUT.NX' where symbol='FRUT';
update QUOTATIONS set symbol='FRUT.NX' where symbol='FRUT';
update EVENTS set symbol='FRUT.NX' where symbol='FRUT';
update TRANSACTIONS set symbol='FRUT.NX' where symbol='FRUT';


delete from alerts where isin = 'US4642871689';
delete from portfolio where isin = 'US4642871689';
delete from shares where  isin='US4642871689'; 
delete from TRANSACTIONS where  isin='US4642871689'; 
delete from events where  isin='US4642871689'; 
delete from TREND_SUPPLEMENT where  isin='US4642871689'; 
--update SHARES set symbol='3IS1.BE' where symbol='US4642871689';
--update TREND_SUPPLEMENT set symbol='3IS1.BE' where symbol='US4642871689';
--update PORTFOLIO set symbol='3IS1.BE' where symbol='US4642871689';
--update ALERTS set symbol='3IS1.BE' where symbol='US4642871689';
--update QUOTATIONS set symbol='3IS1.BE' where symbol='US4642871689';
--
--update SHARES set symbol='JPJR.BE' where symbol='LU0074838565';
--update TREND_SUPPLEMENT set symbol='JPJR.BE' where symbol='LU0074838565';
--update PORTFOLIO set symbol='JPJR.BE' where symbol='LU0074838565';
--update ALERTS set symbol='JPJR.BE' where symbol='LU0074838565';
--update QUOTATIONS set symbol='JPJR.BE' where symbol='LU0074838565';

--update SHARES set symbol='FJ2Z.BE' where symbol='LU0048575426';
--update TREND_SUPPLEMENT set symbol='FJ2Z.BE' where symbol='LU0048575426';
--update PORTFOLIO set symbol='FJ2Z.BE' where symbol='LU0048575426';
--update ALERTS set symbol='FJ2Z.BE' where symbol='LU0048575426';
--update QUOTATIONS set symbol='FJ2Z.BE' where symbol='LU0048575426';

update SHARES set symbol='PX1.XPAR' where isin='FR0003500008';
update TREND_SUPPLEMENT set symbol='PX1.XPAR' where isin='FR0003500008';
update PORTFOLIO set symbol='PX1.XPAR' where isin='FR0003500008';
update ALERTS set symbol='PX1.XPAR' where isin='FR0003500008';
update QUOTATIONS set symbol='PX1.XPAR' where isin='FR0003500008';
update EVENTS set symbol='PX1.XPAR' where symbol='FR0003500008';
update TRANSACTIONS set symbol='PX1.XPAR' where symbol='FR0003500008';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0003500008';

update shares set CATEGORY ='TRACKERS' where symbol = 'N225';

update SHARES set symbol='CAC.XPAR' where isin='FR0007052782';
update TREND_SUPPLEMENT set symbol='CAC.XPAR' where isin='FR0007052782';
update PORTFOLIO set symbol='CAC.XPAR' where isin='FR0007052782';
update ALERTS set symbol='CAC.XPAR' where isin='FR0007052782';
update QUOTATIONS set symbol='CAC.XPAR' where isin='FR0007052782';
update EVENTS set symbol='CAC.XPAR' where isin='FR0007052782';
update TRANSACTIONS set symbol='CAC.XPAR' where isin='FR0007052782';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0007052782';

update SHARES set symbol='FR0010091173.WMORN' where symbol='FR0010091173';
update TREND_SUPPLEMENT set symbol='FR0010091173.WMORN' where symbol='FR0010091173';
update PORTFOLIO set symbol='FR0010091173.WMORN' where symbol='FR0010091173';
update ALERTS set symbol='FR0010091173.WMORN' where symbol='FR0010091173';
update QUOTATIONS set symbol='FR0010091173.WMORN' where symbol='FR0010091173';
update EVENTS set symbol='FR0010091173.WMORN' where symbol='FR0010091173';
update TRANSACTIONS set symbol='FR0010091173.WMORN' where symbol='FR0010091173';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010091173';

update SHARES set symbol='FR0000937070.WMORN' where symbol='FR0000937070';
update TREND_SUPPLEMENT set symbol='FR0000937070.WMORN' where symbol='FR0000937070';
update PORTFOLIO set symbol='FR0000937070.WMORN' where symbol='FR0000937070';
update ALERTS set symbol='FR0000937070.WMORN' where symbol='FR0000937070';
update QUOTATIONS set symbol='FR0000937070.WMORN' where symbol='FR0000937070';
update EVENTS set symbol='FR0000937070.WMORN' where symbol='FR0000937070';
update TRANSACTIONS set symbol='FR0000937070.WMORN' where symbol='FR0000937070';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0000937070';

update SHARES set symbol='FR0000289902.WMORN' where symbol='FR0000289902';
update TREND_SUPPLEMENT set symbol='FR0000289902.WMORN' where symbol='FR0000289902';
update PORTFOLIO set symbol='FR0000289902.WMORN' where symbol='FR0000289902';
update ALERTS set symbol='FR0000289902.WMORN' where symbol='FR0000289902';
update QUOTATIONS set symbol='FR0000289902.WMORN' where symbol='FR0000289902';
update EVENTS set symbol='FR0000289902.WMORN' where symbol='FR0000289902';
update TRANSACTIONS set symbol='FR0000289902.WMORN' where symbol='FR0000289902';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0000289902';

--- Dj stocks

--update SHARES set symbol=(select symbol from shares where isin='FR0010345504')||'.XPAR' where isin='FR0010345504';
--update TREND_SUPPLEMENT set symbol=(select symbol from shares where isin='FR0010345504') where isin='FR0010345504';
--update PORTFOLIO set symbol=(select symbol from shares where isin='FR0010345504') where isin='FR0010345504';
--update ALERTS set symbol=(select symbol from shares where isin='FR0010345504') where isin='FR0010345504';
--update QUOTATIONS set symbol=(select symbol from shares where isin='FR0010345504') where isin='FR0010345504';
--update EVENTS set symbol=(select symbol from shares where isin='FR0010345504') where isin='FR0010345504';
--update TRANSACTIONS set symbol=(select symbol from shares where isin='FR0010345504') where isin='FR0010345504';
--select  * from shares where isin = 'FR0010345504';
update SHARES set symbol='DJST.XPAR' where isin='FR0010345504';
update TREND_SUPPLEMENT set symbol='DJST.XPAR' where isin='FR0010345504';
update PORTFOLIO set symbol='DJST.XPAR' where isin='FR0010345504';
update ALERTS set symbol='DJST.XPAR' where isin='FR0010345504';
update QUOTATIONS set symbol='DJST.XPAR' where isin='FR0010345504';
update EVENTS set symbol='DJST.XPAR' where isin='FR0010345504';
update TRANSACTIONS set symbol='DJST.XPAR' where isin='FR0010345504';
select  * from shares where isin = 'FR0010345504';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010345504';


update SHARES set symbol=(select symbol from shares where isin='FR0010344853')||'.XPAR' where isin='FR0010344853';
update TREND_SUPPLEMENT set symbol=(select symbol from shares where isin='FR0010344853') where isin='FR0010344853';
update PORTFOLIO set symbol=(select symbol from shares where isin='FR0010344853') where isin='FR0010344853';
update ALERTS set symbol=(select symbol from shares where isin='FR0010344853') where isin='FR0010344853';
update QUOTATIONS set symbol=(select symbol from shares where isin='FR0010344853') where isin='FR0010344853';
update EVENTS set symbol=(select symbol from shares where isin='FR0010344853') where isin='FR0010344853';
update TRANSACTIONS set symbol=(select symbol from shares where isin='FR0010344853') where isin='FR0010344853';
select  * from shares where isin = 'FR0010344853';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010344853';


update SHARES set symbol=(select symbol from shares where isin='FR0010344796')||'.XPAR' where isin='FR0010344796';
update TREND_SUPPLEMENT set symbol=(select symbol from shares where isin='FR0010344796') where isin='FR0010344796';
update PORTFOLIO set symbol=(select symbol from shares where isin='FR0010344796') where isin='FR0010344796';
update ALERTS set symbol=(select symbol from shares where isin='FR0010344796') where isin='FR0010344796';
update QUOTATIONS set symbol=(select symbol from shares where isin='FR0010344796') where isin='FR0010344796';
update EVENTS set symbol=(select symbol from shares where isin='FR0010344796') where isin='FR0010344796';
update TRANSACTIONS set symbol=(select symbol from shares where isin='FR0010344796') where isin='FR0010344796';
select  * from shares where isin = 'FR0010344796';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010344796';

update SHARES set symbol=(select symbol from shares where isin='FR0010344903')||'.XPAR' where isin='FR0010344903';
update TREND_SUPPLEMENT set symbol=(select symbol from shares where isin='FR0010344903') where isin='FR0010344903';
update PORTFOLIO set symbol=(select symbol from shares where isin='FR0010344903') where isin='FR0010344903';
update ALERTS set symbol=(select symbol from shares where isin='FR0010344903') where isin='FR0010344903';
update QUOTATIONS set symbol=(select symbol from shares where isin='FR0010344903') where isin='FR0010344903';
update EVENTS set symbol=(select symbol from shares where isin='FR0010344903') where isin='FR0010344903';
update TRANSACTIONS set symbol=(select symbol from shares where isin='FR0010344903') where isin='FR0010344903';
select  * from shares where isin = 'FR0010344903';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010344903';

update SHARES set symbol=(select symbol from shares where isin='FR0010344838')||'.XPAR' where isin='FR0010344838';
update TREND_SUPPLEMENT set symbol=(select symbol from shares where isin='FR0010344838') where isin='FR0010344838';
update PORTFOLIO set symbol=(select symbol from shares where isin='FR0010344838') where isin='FR0010344838';
update ALERTS set symbol=(select symbol from shares where isin='FR0010344838') where isin='FR0010344838';
update QUOTATIONS set symbol=(select symbol from shares where isin='FR0010344838') where isin='FR0010344838';
update EVENTS set symbol=(select symbol from shares where isin='FR0010344838') where isin='FR0010344838';
update TRANSACTIONS set symbol=(select symbol from shares where isin='FR0010344838') where isin='FR0010344838';
select  * from shares where isin = 'FR0010344838';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010344838';

update SHARES set symbol=(select symbol from shares where isin='FR0010345470')||'.XPAR' where isin='FR0010345470';
update TREND_SUPPLEMENT set symbol=(select symbol from shares where isin='FR0010345470') where isin='FR0010345470';
update PORTFOLIO set symbol=(select symbol from shares where isin='FR0010345470') where isin='FR0010345470';
update ALERTS set symbol=(select symbol from shares where isin='FR0010345470') where isin='FR0010345470';
update QUOTATIONS set symbol=(select symbol from shares where isin='FR0010345470') where isin='FR0010345470';
update EVENTS set symbol=(select symbol from shares where isin='FR0010345470') where isin='FR0010345470';
update TRANSACTIONS set symbol=(select symbol from shares where isin='FR0010345470') where isin='FR0010345470';
select  * from shares where isin = 'FR0010345470';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010345470';

update SHARES set symbol=(select symbol from shares where isin='FR0010345371')||'.XPAR' where isin='FR0010345371';
update TREND_SUPPLEMENT set symbol=(select symbol from shares where isin='FR0010345371') where isin='FR0010345371';
update PORTFOLIO set symbol=(select symbol from shares where isin='FR0010345371') where isin='FR0010345371';
update ALERTS set symbol=(select symbol from shares where isin='FR0010345371') where isin='FR0010345371';
update QUOTATIONS set symbol=(select symbol from shares where isin='FR0010345371') where isin='FR0010345371';
update EVENTS set symbol=(select symbol from shares where isin='FR0010345371') where isin='FR0010345371';
update TRANSACTIONS set symbol=(select symbol from shares where isin='FR0010345371') where isin='FR0010345371';
select  * from shares where isin = 'FR0010345371';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010345371';

update SHARES set symbol=(select symbol from shares where isin='FR0010344929')||'.XPAR' where isin='FR0010344929';
update TREND_SUPPLEMENT set symbol=(select symbol from shares where isin='FR0010344929') where isin='FR0010344929';
update PORTFOLIO set symbol=(select symbol from shares where isin='FR0010344929') where isin='FR0010344929';
update ALERTS set symbol=(select symbol from shares where isin='FR0010344929') where isin='FR0010344929';
update QUOTATIONS set symbol=(select symbol from shares where isin='FR0010344929') where isin='FR0010344929';
update EVENTS set symbol=(select symbol from shares where isin='FR0010344929') where isin='FR0010344929';
update TRANSACTIONS set symbol=(select symbol from shares where isin='FR0010344929') where isin='FR0010344929';
select  * from shares where isin = 'FR0010344929';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010344929';

update SHARES set symbol=(select symbol from shares where isin='FR0010344978')||'.XPAR' where isin='FR0010344978';
update TREND_SUPPLEMENT set symbol=(select symbol from shares where isin='FR0010344978') where isin='FR0010344978';
update PORTFOLIO set symbol=(select symbol from shares where isin='FR0010344978') where isin='FR0010344978';
update ALERTS set symbol=(select symbol from shares where isin='FR0010344978') where isin='FR0010344978';
update QUOTATIONS set symbol=(select symbol from shares where isin='FR0010344978') where isin='FR0010344978';
update EVENTS set symbol=(select symbol from shares where isin='FR0010344978') where isin='FR0010344978';
update TRANSACTIONS set symbol=(select symbol from shares where isin='FR0010344978') where isin='FR0010344978';
select  * from shares where isin = 'FR0010344978';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010344978';

update SHARES set symbol=(select symbol from shares where isin='FR0010270033')||'.XPAR' where isin='FR0010270033';
update TREND_SUPPLEMENT set symbol=(select symbol from shares where isin='FR0010270033') where isin='FR0010270033';
update PORTFOLIO set symbol=(select symbol from shares where isin='FR0010270033') where isin='FR0010270033';
update ALERTS set symbol=(select symbol from shares where isin='FR0010270033') where isin='FR0010270033';
update QUOTATIONS set symbol=(select symbol from shares where isin='FR0010270033') where isin='FR0010270033';
update EVENTS set symbol=(select symbol from shares where isin='FR0010270033') where isin='FR0010270033';
update TRANSACTIONS set symbol=(select symbol from shares where isin='FR0010270033') where isin='FR0010270033';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010270033';

update SHARES set symbol=(select symbol from shares where isin='FR0010345363')||'.XPAR' where isin='FR0010345363';
update TREND_SUPPLEMENT set symbol=(select symbol from shares where isin='FR0010345363') where isin='FR0010345363';
update PORTFOLIO set symbol=(select symbol from shares where isin='FR0010345363') where isin='FR0010345363';
update ALERTS set symbol=(select symbol from shares where isin='FR0010345363') where isin='FR0010345363';
update QUOTATIONS set symbol=(select symbol from shares where isin='FR0010345363') where isin='FR0010345363';
update EVENTS set symbol=(select symbol from shares where isin='FR0010345363') where isin='FR0010345363';
update TRANSACTIONS set symbol=(select symbol from shares where isin='FR0010345363') where isin='FR0010345363';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010345363';

update SHARES set symbol=(select symbol from shares where isin='FR0010344887')||'.XPAR' where isin='FR0010344887';
update TREND_SUPPLEMENT set symbol=(select symbol from shares where isin='FR0010344887') where isin='FR0010344887';
update PORTFOLIO set symbol=(select symbol from shares where isin='FR0010344887') where isin='FR0010344887';
update ALERTS set symbol=(select symbol from shares where isin='FR0010344887') where isin='FR0010344887';
update QUOTATIONS set symbol=(select symbol from shares where isin='FR0010344887') where isin='FR0010344887';
update EVENTS set symbol=(select symbol from shares where isin='FR0010344887') where isin='FR0010344887';
update TRANSACTIONS set symbol=(select symbol from shares where isin='FR0010344887') where isin='FR0010344887';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010344887';

update SHARES set symbol=(select symbol from shares where isin='FR0010344812')||'.XPAR' where isin='FR0010344812';
update TREND_SUPPLEMENT set symbol=(select symbol from shares where isin='FR0010344812') where isin='FR0010344812';
update PORTFOLIO set symbol=(select symbol from shares where isin='FR0010344812') where isin='FR0010344812';
update ALERTS set symbol=(select symbol from shares where isin='FR0010344812') where isin='FR0010344812';
update QUOTATIONS set symbol=(select symbol from shares where isin='FR0010344812') where isin='FR0010344812';
update EVENTS set symbol=(select symbol from shares where isin='FR0010344812') where isin='FR0010344812';
update TRANSACTIONS set symbol=(select symbol from shares where isin='FR0010344812') where isin='FR0010344812';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010344812';

update SHARES set symbol=(select symbol from shares where isin='FR0010344630')||'.XPAR' where isin='FR0010344630';
update TREND_SUPPLEMENT set symbol=(select symbol from shares where isin='FR0010344630') where isin='FR0010344630';
update PORTFOLIO set symbol=(select symbol from shares where isin='FR0010344630') where isin='FR0010344630';
update ALERTS set symbol=(select symbol from shares where isin='FR0010344630') where isin='FR0010344630';
update QUOTATIONS set symbol=(select symbol from shares where isin='FR0010344630') where isin='FR0010344630';
update EVENTS set symbol=(select symbol from shares where isin='FR0010344630') where isin='FR0010344630';
update TRANSACTIONS set symbol=(select symbol from shares where isin='FR0010344630') where isin='FR0010344630';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010344630';

update SHARES set symbol=(select symbol from shares where isin='FR0010344861')||'.XPAR' where isin='FR0010344861';
update TREND_SUPPLEMENT set symbol=(select symbol from shares where isin='FR0010344861') where isin='FR0010344861';
update PORTFOLIO set symbol=(select symbol from shares where isin='FR0010344861') where isin='FR0010344861';
update ALERTS set symbol=(select symbol from shares where isin='FR0010344861') where isin='FR0010344861';
update QUOTATIONS set symbol=(select symbol from shares where isin='FR0010344861') where isin='FR0010344861';
update EVENTS set symbol=(select symbol from shares where isin='FR0010344861') where isin='FR0010344861';
update TRANSACTIONS set symbol=(select symbol from shares where isin='FR0010344861') where isin='FR0010344861';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010344861';

update SHARES set symbol=(select symbol from shares where isin='FR0010344986')||'.XPAR' where isin='FR0010344986';
update TREND_SUPPLEMENT set symbol=(select symbol from shares where isin='FR0010344986') where isin='FR0010344986';
update PORTFOLIO set symbol=(select symbol from shares where isin='FR0010344986') where isin='FR0010344986';
update ALERTS set symbol=(select symbol from shares where isin='FR0010344986') where isin='FR0010344986';
update QUOTATIONS set symbol=(select symbol from shares where isin='FR0010344986') where isin='FR0010344986';
update EVENTS set symbol=(select symbol from shares where isin='FR0010344986') where isin='FR0010344986';
update TRANSACTIONS set symbol=(select symbol from shares where isin='FR0010344986') where isin='FR0010344986';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010344986';

update SHARES set symbol='LYXHEA.XPAR' where isin='FR0010344879';
update TREND_SUPPLEMENT set symbol=(select symbol from shares where isin='FR0010344879') where isin='FR0010344879';
update PORTFOLIO set symbol=(select symbol from shares where isin='FR0010344879') where isin='FR0010344879';
update ALERTS set symbol=(select symbol from shares where isin='FR0010344879') where isin='FR0010344879';
update QUOTATIONS set symbol=(select symbol from shares where isin='FR0010344879') where isin='FR0010344879';
update EVENTS set symbol=(select symbol from shares where isin='FR0010344879') where isin='FR0010344879';
update TRANSACTIONS set symbol=(select symbol from shares where isin='FR0010344879') where isin='FR0010344879';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010344879';

--- Dj stocks

update SHARES set symbol='OIL.XPAR' where isin='FR0010344960';
update TREND_SUPPLEMENT set symbol='OIL.XPAR' where isin='FR0010344960';
update PORTFOLIO  set symbol='OIL.XPAR' where isin='FR0010344960';
update ALERTS set symbol='OIL.XPAR' where isin='FR0010344960';
update QUOTATIONS set symbol='OIL.XPAR' where isin='FR0010344960';
update EVENTS set symbol='OIL.XPAR' where isin='FR0010344960';
update TRANSACTIONS set symbol='OIL.XPAR' where isin='FR0010344960';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010344960';

delete from alerts where symbol='IAPD.NX' and isin = 'IAPD.NX';
delete from portfolio where symbol='IAPD.NX' and isin = 'IAPD.NX';
delete from TRANSACTIONS where  symbol='IAPD.NX' and isin = 'IAPD.NX';
delete from events where  symbol='IAPD.NX' and isin = 'IAPD.NX';
delete from TREND_SUPPLEMENT where symbol='IAPD.NX' and isin = 'IAPD.NX';
delete from quotations where symbol='IAPD.NX' and isin = 'IAPD.NX';
delete from shares where  symbol='IAPD.NX' and isin = 'IAPD.NX';
update SHARES set symbol='IAPD.WMORN'  where isin='IE00B14X4T88';
update TREND_SUPPLEMENT set symbol='IAPD.WMORN'  where isin='IE00B14X4T88';
update PORTFOLIO set symbol='IAPD.WMORN'  where isin='IE00B14X4T88';
update ALERTS set symbol='IAPD.WMORN'  where isin='IE00B14X4T88';
update QUOTATIONS set symbol='IAPD.WMORN'  where isin='IE00B14X4T88';
update EVENTS set symbol='IAPD.WMORN'  where isin='IE00B14X4T88';
update TRANSACTIONS set symbol='IAPD.WMORN'  where isin='IE00B14X4T88';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'IE00B14X4T88';

update SHARES set symbol='CRUD.XPAR' where isin = 'GB00B15KXV33';
update TREND_SUPPLEMENT set symbol='CRUD.XPAR' where isin = 'GB00B15KXV33';
update PORTFOLIO set symbol='CRUD.XPAR' where isin = 'GB00B15KXV33';
update ALERTS set symbol='CRUD.XPAR' where isin = 'GB00B15KXV33';
update QUOTATIONS set symbol='CRUD.XPAR' where isin = 'GB00B15KXV33';
update EVENTS set symbol='CRUD.XPAR' where isin = 'GB00B15KXV33';
update TRANSACTIONS set symbol='CRUD.XPAR' where isin = 'GB00B15KXV33';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'GB00B15KXV33';

update SHARES set symbol='3233S.XPAR' , isin = 'FR0010391201' where symbol='FR0010391201';
update TREND_SUPPLEMENT set symbol='3233S.XPAR', isin = 'FR0010391201' where symbol='FR0010391201';
update PORTFOLIO set symbol='3233S.XPAR', isin = 'FR0010391201' where symbol='FR0010391201';
update ALERTS set symbol='3233S.XPAR', isin = 'FR0010391201' where symbol='FR0010391201';
update QUOTATIONS set symbol='3233S.XPAR', isin = 'FR0010391201' where symbol='FR0010391201';
update EVENTS set symbol='3233S.XPAR', isin = 'FR0010391201' where symbol='FR0010391201';
update TRANSACTIONS set symbol='3233S.XPAR', isin = 'FR0010391201' where symbol='FR0010391201';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010391201';

update SHARES set symbol='LU0074838565.WMORN' , isin = 'LU0074838565' where symbol='LU0074838565';
update TREND_SUPPLEMENT set symbol='LU0074838565.WMORN', isin = 'LU0074838565' where symbol='LU0074838565';
update PORTFOLIO set symbol='LU0074838565.WMORN', isin = 'LU0074838565' where symbol='LU0074838565';
update ALERTS set symbol='LU0074838565.WMORN', isin = 'LU0074838565' where symbol='LU0074838565';
update QUOTATIONS set symbol='LU0074838565.WMORN', isin = 'LU0074838565' where symbol='LU0074838565';
update EVENTS set symbol='LU0074838565.WMORN', isin = 'LU0074838565' where symbol='LU0074838565';
update TRANSACTIONS set symbol='LU0074838565.WMORN', isin = 'LU0074838565' where symbol='LU0074838565';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'LU0074838565';


delete from alerts where isin = 'FR0007063177';
delete from portfolio where isin = 'FR0007063177';
delete from shares where  isin='FR0007063177'; 
delete from TRANSACTIONS where  isin='FR0007063177'; 
delete from events where  isin='FR0007063177'; 
delete from TREND_SUPPLEMENT where  isin='FR0007063177'; 
delete from quotations where isin = 'FR0007063177'; 
--update SHARES set symbol='UST.XPAR' where isin='FR0007063177';
--update TREND_SUPPLEMENT set symbol='UST.XPAR' where isin='FR0007063177';
--update PORTFOLIO set symbol='UST.XPAR' where isin='FR0007063177';
--update ALERTS set symbol='UST.XPAR' where isin='FR0007063177';
--update QUOTATIONS set symbol='UST.XPAR' where isin='FR0007063177';
--update EVENTS set symbol='UST.XPAR' where isin='FR0007063177';
--update TRANSACTIONS set symbol='UST.XPAR' where isin='FR0007063177';
--update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0007063177';

update SHARES set symbol='1008N.XPAR'  where isin='FR0010307439';
update TREND_SUPPLEMENT set symbol='1008N.XPAR' where isin='FR0010307439';
update PORTFOLIO set symbol='1008N.XPAR' where isin='FR0010307439';
update ALERTS set symbol='1008N.XPAR' where isin='FR0010307439';
update QUOTATIONS set symbol='1008N.XPAR' where isin='FR0010307439';
update EVENTS set symbol='1008N.XPAR' where symbol='FR0010307439';
update TRANSACTIONS set symbol='1008N.XPAR' where isin='FR0010307439';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010307439';

update SHARES set symbol='FR0010057075.WMORN' , isin = 'FR0010057075' where symbol='FR0010057075';
update TREND_SUPPLEMENT set symbol='FR0010057075.WMORN', isin = 'FR0010057075' where symbol='FR0010057075';
update PORTFOLIO set symbol='FR0010057075.WMORN', isin = 'FR0010057075' where symbol='FR0010057075';
update ALERTS set symbol='FR0010057075.WMORN', isin = 'FR0010057075' where symbol='FR0010057075';
update QUOTATIONS set symbol='FR0010057075.WMORN', isin = 'FR0010057075' where symbol='FR0010057075';
update EVENTS set symbol='FR0010057075.WMORN', isin = 'FR0010057075' where symbol='FR0010057075';
update TRANSACTIONS set symbol='FR0010057075.WMORN', isin = 'FR0010057075' where symbol='FR0010057075';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010057075';

update SHARES set symbol='FR0010106880.WMORN' , isin = 'FR0010106880' where symbol='FR0010106880';
update TREND_SUPPLEMENT set symbol='FR0010106880.WMORN', isin = 'FR0010106880' where symbol='FR0010106880';
update PORTFOLIO set symbol='FR0010106880.WMORN', isin = 'FR0010106880' where symbol='FR0010106880';
update ALERTS set symbol='FR0010106880.WMORN', isin = 'FR0010106880' where symbol='FR0010106880';
update QUOTATIONS set symbol='FR0010106880.WMORN', isin = 'FR0010106880' where symbol='FR0010106880';
update EVENTS set symbol='FR0010106880.WMORN', isin = 'FR0010106880' where symbol='FR0010106880';
update TRANSACTIONS set symbol='FR0010106880.WMORN', isin = 'FR0010106880' where symbol='FR0010106880';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0010106880';

update SHARES set symbol='LU0048575426.WMORN' , isin = 'LU0048575426' where symbol='LU0048575426';
update TREND_SUPPLEMENT set symbol='LU0048575426.WMORN', isin = 'LU0048575426' where symbol='LU0048575426';
update PORTFOLIO set symbol='LU0048575426.WMORN', isin = 'LU0048575426' where symbol='LU0048575426';
update ALERTS set symbol='LU0048575426.WMORN', isin = 'LU0048575426' where symbol='LU0048575426';
update QUOTATIONS set symbol='LU0048575426.WMORN', isin = 'LU0048575426' where symbol='LU0048575426';
update EVENTS set symbol='LU0048575426.WMORN', isin = 'LU0048575426' where symbol='LU0048575426';
update TRANSACTIONS set symbol='LU0048575426.WMORN', isin = 'LU0048575426' where symbol='LU0048575426';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'LU0048575426';

update SHARES set symbol='LU0164455502.WMORN' , isin = 'LU0164455502' where symbol='LU0164455502';
update TREND_SUPPLEMENT set symbol='LU0164455502.WMORN', isin = 'LU0164455502' where symbol='LU0164455502';
update PORTFOLIO set symbol='LU0164455502.WMORN', isin = 'LU0164455502' where symbol='LU0164455502';
update ALERTS set symbol='LU0164455502.WMORN', isin = 'LU0164455502' where symbol='LU0164455502';
update QUOTATIONS set symbol='LU0164455502.WMORN', isin = 'LU0164455502' where symbol='LU0164455502';
update EVENTS set symbol='LU0164455502.WMORN', isin = 'LU0164455502' where symbol='LU0164455502';
update TRANSACTIONS set symbol='LU0164455502.WMORN', isin = 'LU0164455502' where symbol='LU0164455502';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'LU0164455502';

update SHARES set symbol='FR0000018962.WMORN' where symbol='FR0000018962';
update TREND_SUPPLEMENT set symbol='FR0000018962.WMORN' where symbol='FR0000018962';
update PORTFOLIO set symbol='FR0000018962.WMORN' where symbol='FR0000018962';
update ALERTS set symbol='FR0000018962.WMORN' where symbol='FR0000018962';
update QUOTATIONS set symbol='FR0000018962.WMORN' where symbol='FR0000018962';
update EVENTS set symbol='FR0000018962.WMORN' where symbol='FR0000018962';
update TRANSACTIONS set symbol='FR0000018962.WMORN' where symbol='FR0000018962';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0000018962';

update SHARES set symbol='FR0000188625.XPAR' where isin='FR0000188625';
update TREND_SUPPLEMENT set symbol='FR0000188625.XPAR' where isin='FR0000188625';
update PORTFOLIO set symbol='FR0000188625.XPAR' where isin='FR0000188625';
update ALERTS set symbol='FR0000188625.XPAR' where isin='FR0000188625';
update QUOTATIONS set symbol='FR0000188625.XPAR' where isin='FR0000188625';
update EVENTS set symbol='FR0000188625.XPAR' where isin='FR0000188625';
update TRANSACTIONS set symbol='FR0000188625.XPAR' where isin='FR0000188625';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'FR0000188625';

update SHARES set symbol='GB00B0CTWC01.XPAR' , isin = 'GB00B0CTWC01' where symbol='GB00B0CTWC01';
update TREND_SUPPLEMENT set symbol='GB00B0CTWC01.XPAR', isin = 'GB00B0CTWC01' where symbol='GB00B0CTWC01';
update PORTFOLIO set symbol='GB00B0CTWC01.XPAR', isin = 'GB00B0CTWC01' where symbol='GB00B0CTWC01';
update ALERTS set symbol='GB00B0CTWC01.XPAR', isin = 'GB00B0CTWC01' where symbol='GB00B0CTWC01';
update QUOTATIONS set symbol='GB00B0CTWC01.XPAR', isin = 'GB00B0CTWC01' where symbol='GB00B0CTWC01';
update EVENTS set symbol='GB00B0CTWC01.XPAR', isin = 'GB00B0CTWC01' where symbol='GB00B0CTWC01';
update TRANSACTIONS set symbol='GB00B0CTWC01.XPAR', isin = 'GB00B0CTWC01' where symbol='GB00B0CTWC01';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'GB00B0CTWC01';

delete from alerts where symbol = 'OILBP' and isin = 'GB00B0CTWC01';  
delete from portfolio where symbol = 'OILBP' and isin = 'GB00B0CTWC01';  
delete from quotations where symbol = 'OILBP' and isin = 'GB00B0CTWC01';
delete from TRANSACTIONS where symbol = 'OILBP' and isin = 'GB00B0CTWC01';  
delete from events where  symbol = 'OILBP' and isin = 'GB00B0CTWC01';  
delete from TREND_SUPPLEMENT where  symbol = 'OILBP' and isin = 'GB00B0CTWC01';  
delete from shares where symbol = 'OILBP' and isin = 'GB00B0CTWC01'; 

update SHARES set symbol='OILB.XPAR' where isin = 'GB00B0CTWC01';
update TREND_SUPPLEMENT set symbol='OILB.XPAR' where isin = 'GB00B0CTWC01';
update PORTFOLIO set symbol='OILB.XPAR' where isin = 'GB00B0CTWC01';
update ALERTS set symbol='OILB.XPAR' where isin = 'GB00B0CTWC01';
update QUOTATIONS set symbol='OILB.XPAR' where isin = 'GB00B0CTWC01';
update EVENTS set symbol='OILB.XPAR' where isin = 'GB00B0CTWC01';
update TRANSACTIONS set symbol='OILB.XPAR' where isin = 'GB00B0CTWC01';
update shares set QUOTATIONPROVIDER='INVESTIR' where isin = 'GB00B0CTWC01'; 


ALTER TABLE "APP"."PORTFOLIO" ADD CONSTRAINT "FK_PORTFOLIO_TO_SHARES" FOREIGN KEY ("SYMBOL", "ISIN") REFERENCES "APP"."SHARES" ("SYMBOL", "ISIN") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "APP"."TREND_SUPPLEMENT" ADD CONSTRAINT "FK_TREND_SUPPLEMENT_TO_SHARES" FOREIGN KEY ("SYMBOL", "ISIN") REFERENCES "APP"."SHARES" ("SYMBOL", "ISIN") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "APP"."ALERTS" ADD CONSTRAINT "FK_ALERTS_TO_PORTFOLIO" FOREIGN KEY ("SYMBOL", "ISIN", "NAME") REFERENCES "APP"."PORTFOLIO" ("SYMBOL", "ISIN", "NAME") ON DELETE NO ACTION ON UPDATE NO ACTION;

----

ALTER TABLE "APP"."TREND_SUPPLEMENT" DROP CONSTRAINT "FK_TREND_SUPPLEMENT_TO_SHARES";
ALTER TABLE "APP"."PORTFOLIO" DROP CONSTRAINT "FK_PORTFOLIO_TO_SHARES";
ALTER TABLE "APP"."ALERTS" DROP CONSTRAINT "FK_ALERTS_TO_PORTFOLIO";

update SHARES set symbol='N225' where isin='XC0009692440';
update TREND_SUPPLEMENT set symbol='N225' where isin='XC0009692440';
update PORTFOLIO set symbol='N225' where isin='XC0009692440';
update ALERTS set symbol='N225' where isin='XC0009692440';
update QUOTATIONS set symbol='N225' where isin='XC0009692440';
update EVENTS set symbol='N225' where isin='XC0009692440';
update TRANSACTIONS set symbol='N225' where isin='XC0009692440';

delete from quotations where symbol = 'N225' and date in ('2011-02-15','2011-02-16');

delete from portfolio_name where name = 'ScreeningEuroPortfolioWeather' or name='ScreeningUSPortfolioWeather';

ALTER TABLE "APP"."PORTFOLIO" ADD CONSTRAINT "FK_PORTFOLIO_TO_SHARES" FOREIGN KEY ("SYMBOL", "ISIN") REFERENCES "APP"."SHARES" ("SYMBOL", "ISIN") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "APP"."TREND_SUPPLEMENT" ADD CONSTRAINT "FK_TREND_SUPPLEMENT_TO_SHARES" FOREIGN KEY ("SYMBOL", "ISIN") REFERENCES "APP"."SHARES" ("SYMBOL", "ISIN") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "APP"."ALERTS" ADD CONSTRAINT "FK_ALERTS_TO_PORTFOLIO" FOREIGN KEY ("SYMBOL", "ISIN", "NAME") REFERENCES "APP"."PORTFOLIO" ("SYMBOL", "ISIN", "NAME") ON DELETE NO ACTION ON UPDATE NO ACTION;



