alter table ALERTS drop constraint FK_ALERTS_TO_PORTFOLIO;
alter table PORTFOLIO drop constraint FK_PORTFOLIO_TO_SHARES;
alter table TREND_SUPPLEMENT drop constraint FK_TREND_SUPPLEMENT_TO_SHARES;
update shares set symbol = 'DVY' where isin = 'US4642871689';
update TREND_SUPPLEMENT set symbol = 'DVY' where isin = 'US4642871689';
update portfolio set symbol = 'DVY' where isin = 'US4642871689';
update alerts set symbol = 'DVY' where isin = 'US4642871689';
update EVENTS set symbol = 'DVY' where isin = 'US4642871689';
update QUOTATIONS set symbol = 'DVY' where isin = 'US4642871689';
update TRANSACTIONS set symbol = 'DVY' where isin = 'US4642871689';
update TUNEDCONF set symbol = 'DVY' where isin = 'US4642871689';
update shares set symbol = 'DVY' where isin = 'US4642871689';
delete from quotations where isin = 'US4642871689';
update shares set LASTQUOTE = '1970-01-01', MARKETLISTPROVIDER = 'NYSE'  where isin = 'US4642871689';

update TREND_SUPPLEMENT set symbol = 'PHG.PA' where isin = 'FR0010344978';
update portfolio set symbol = 'PHG.PA' where isin = 'FR0010344978';
update alerts set symbol = 'PHG.PA' where isin = 'FR0010344978';
update EVENTS set symbol = 'PHG.PA' where isin = 'FR0010344978';
update QUOTATIONS set symbol = 'PHG.PA' where isin = 'FR0010344978';
update TRANSACTIONS set symbol = 'PHG.PA' where isin = 'FR0010344978';
update TUNEDCONF set symbol = 'PHG.PA' where isin = 'FR0010344978';
update shares set symbol = 'PHG.PA' where isin = 'FR0010344978';
update shares set QUOTATIONPROVIDER = 'DEFAULT' where isin = 'FR0010344978';


update TREND_SUPPLEMENT set symbol = 'CRUD.XPAR' where isin = 'GB00B15KXV33';
update portfolio set symbol = 'CRUD.XPAR' where isin = 'GB00B15KXV33';
update alerts set symbol = 'CRUD.XPAR' where isin = 'GB00B15KXV33';
update EVENTS set symbol = 'CRUD.XPAR' where isin = 'GB00B15KXV33';
--update QUOTATIONS set symbol = 'CRUD.L' where isin = 'GB00B15KXV33';
update TRANSACTIONS set symbol = 'CRUD.XPAR' where isin = 'GB00B15KXV33';
update TUNEDCONF set symbol = 'CRUD.XPAR' where isin = 'GB00B15KXV33';
update shares set symbol = 'CRUD.XPAR' where isin = 'GB00B15KXV33';
update shares set QUOTATIONPROVIDER = 'INVESTIR',  MARKETLISTPROVIDER = 'EURONEXT', LASTQUOTE = '1970-01-01' where isin = 'GB00B15KXV33';


ALTER TABLE "APP"."PORTFOLIO" ADD CONSTRAINT "FK_PORTFOLIO_TO_SHARES" FOREIGN KEY ("SYMBOL", "ISIN") REFERENCES "APP"."SHARES" ("SYMBOL", "ISIN") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "APP"."TREND_SUPPLEMENT" ADD CONSTRAINT "FK_TREND_SUPPLEMENT_TO_SHARES" FOREIGN KEY ("SYMBOL", "ISIN") REFERENCES "APP"."SHARES" ("SYMBOL", "ISIN") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "APP"."ALERTS" ADD CONSTRAINT "FK_ALERTS_TO_PORTFOLIO" FOREIGN KEY ("SYMBOL", "ISIN", "NAME") REFERENCES "APP"."PORTFOLIO" ("SYMBOL", "ISIN", "NAME") ON DELETE NO ACTION ON UPDATE NO ACTION;

insert into quotations values ('2012-01-18','IAPD.WMORN','IE00B14X4T88','EUR',0,22.3000,22.3000,22.3000,22.3000);
update shares set lastquote = '2012-01-18' where isin = 'IE00B14X4T88';

alter table ALERTS drop constraint FK_ALERTS_TO_PORTFOLIO;
alter table PORTFOLIO drop constraint FK_PORTFOLIO_TO_SHARES;
alter table TREND_SUPPLEMENT drop constraint FK_TREND_SUPPLEMENT_TO_SHARES;
update shares set QUOTATIONPROVIDER = 'INVESTIR' where isin = 'FR0010344978';
update TREND_SUPPLEMENT set symbol = 'PHG.XPAR' where isin = 'FR0010344978';
update portfolio set symbol = 'PHG.XPAR' where isin = 'FR0010344978';
update alerts set symbol = 'PHG.XPAR' where isin = 'FR0010344978';
update EVENTS set symbol = 'PHG.XPAR' where isin = 'FR0010344978';
update QUOTATIONS set symbol = 'PHG.XPAR' where isin = 'FR0010344978';
update TRANSACTIONS set symbol = 'PHG.XPAR' where isin = 'FR0010344978';
update TUNEDCONF set symbol = 'PHG.XPAR' where isin = 'FR0010344978';
update shares set symbol = 'PHG.XPAR' where isin = 'FR0010344978';
ALTER TABLE "APP"."PORTFOLIO" ADD CONSTRAINT "FK_PORTFOLIO_TO_SHARES" FOREIGN KEY ("SYMBOL", "ISIN") REFERENCES "APP"."SHARES" ("SYMBOL", "ISIN") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "APP"."TREND_SUPPLEMENT" ADD CONSTRAINT "FK_TREND_SUPPLEMENT_TO_SHARES" FOREIGN KEY ("SYMBOL", "ISIN") REFERENCES "APP"."SHARES" ("SYMBOL", "ISIN") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "APP"."ALERTS" ADD CONSTRAINT "FK_ALERTS_TO_PORTFOLIO" FOREIGN KEY ("SYMBOL", "ISIN", "NAME") REFERENCES "APP"."PORTFOLIO" ("SYMBOL", "ISIN", "NAME") ON DELETE NO ACTION ON UPDATE NO ACTION;


