select TUNEDCONF.lastcalculation,TUNEDCONF.CONFIGFILE, SHARES.*  from TUNEDCONF,SHARES where TUNEDCONF.symbol=SHARES.symbol and TUNEDCONF.isin=SHARES.isin and SHARES.lastquote < TUNEDCONF.lastcalculation; -- and  TUNEDCONF.CONFIGFILE='preTrainConfs.NEURAL.csv';
select TUNEDCONF.* from TUNEDCONF where TUNEDCONF.lastcalculation > (select max(date) from EVENTS where EVENTS.eventdefid not in (503,507) and TUNEDCONF.symbol=EVENTS.symbol and TUNEDCONF.isin=EVENTS.isin);
update TUNEDCONF set TUNEDCONF.lastcalculation=(select max(date) from EVENTS where EVENTS.eventdefid not in (503,507) and TUNEDCONF.symbol=EVENTS.symbol and TUNEDCONF.isin=EVENTS.isin) where TUNEDCONF.CONFIGFILE='preTrainConfs.NEURAL.csv';
--update TUNEDCONF set TUNEDCONF.lastcalculation=(select date(max(date)) from EVENTS where EVENTS.eventdefid not in (503,507) and TUNEDCONF.symbol=EVENTS.symbol and TUNEDCONF.isin=EVENTS.isin) where TUNEDCONF.CONFIGFILE='preTrainConfs.NEURAL.csv';

select * from TUNEDCONF where LASTTUNING > LASTCALCULATION;
update TUNEDCONF set LASTTUNING='1970-01-01', LASTCALCULATION='1970-01-01' where LASTTUNING > LASTCALCULATION;

select * from EVENTS where date not like '%00:00:00';
--derby select * from EVENTS where  timestamp(date(EVENTS.date),'00.00.00') <> EVENTS.date;
--delete from EVENTS where date not like '%00:00:00';
select SHARES.lastquote, EVENTS.* from EVENTS, SHARES where SHARES.symbol=EVENTS.symbol and SHARES.isin=EVENTS.isin and EVENTS.date > DATE_ADD(SHARES.lastquote, INTERVAL 1 DAY) and EVENTS.eventdefid not in (503,507);
select SHARES.lastquote, EVENTS.* from EVENTS, SHARES where SHARES.symbol=EVENTS.symbol and SHARES.isin=EVENTS.isin and EVENTS.date >  DATE_ADD(SHARES.lastquote, INTERVAL 1 DAY);
--select distinct(SHARES.symbol) from SHARES, EVENTS where SHARES.symbol=EVENTS.symbol and SHARES.isin=EVENTS.isin and EVENTS.date >  DATE_ADD(SHARES.lastquote, INTERVAL 1 DAY) and EVENTS.eventdefid in (503,507);
--delete from EVENTS where EVENTS.eventdefid in (503,507);