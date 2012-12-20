
select * from SHARES where symbol in ('FTSE','GDAXI','FCHI','IBEX','OSEAX','SSMI','NDX','BSESN','GSPC', 'AXJO','IXIC');
select * from PORTFOLIO where name='SECTORINDICES';

CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from shares where symbol in (''FTSE'',''FCHI'',''IBEX'',''OSEAX'',''SSMI'',''NDX'',''BSESN'',''AXJO'',''GDAXI'')','/home/guil/Developpement/Quotes/tmp/SSSHARES.dat',null,null,null);
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from quotations where symbol in (''FTSE'',''FCHI'',''IBEX'',''OSEAX'',''SSMI'',''NDX'',''BSESN'',''AXJO'',''GDAXI'')','/home/guil/Developpement/Quotes/tmp/SSQUOTATIONS.dat',null,null,null);

--sed "s/ *\"/\"/g" SSQUOTATIONS.dat > SSQUOTATIONS_TRIMED.dat


--Import
--CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(null,'SHARES','/home/guil/Developpement/Quotes/tmp/SSSHARES.dat',null,null,null,0);
--CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(null,'QUOTATIONS','/home/guil/Developpement/Quotes/tmp/SSQUOTATIONS_TRIMED.dat',null,null,null,0);
LOAD DATA INFILE "/var/lib/mysql/premiummarkets/SSSHARES.dat" INTO TABLE SHARES COLUMNS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' ESCAPED BY '"' LINES TERMINATED BY '\n';
LOAD DATA INFILE "/var/lib/mysql/premiummarkets/SSQUOTATIONS_TRIMED.dat" INTO TABLE QUOTATIONS COLUMNS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' ESCAPED BY '"' LINES TERMINATED BY '\n';

insert into PORTFOLIO values ('BSESN','BSESN',1.00,1.00,0.00,'SECTORINDICES',0,'1970-01-01',0.00,'INR',NULL);
insert into PORTFOLIO values ('FCHI','FCHI',1.00,1.00,0.00,'SECTORINDICES',0,'1970-01-01',0.00,'EUR',NULL);
insert into PORTFOLIO values ('FTSE','FTSE',1.00,1.00,0.00,'SECTORINDICES',0,'1970-01-01',0.00,'GBP',NULL);
insert into PORTFOLIO values ('GDAXI','GDAXI',1.00,1.00,0.00,'SECTORINDICES',0,'1970-01-01',0.00,'EUR',NULL);
--insert into PORTFOLIO values ('GSPC','GSPC',1.00,1.00,0.00,'SECTORINDICES',0,'1970-01-01',0.00,'USD',NULL);
insert into PORTFOLIO values ('IBEX','IBEX',1.00,1.00,0.00,'SECTORINDICES',0,'1970-01-01',0.00,'EUR',NULL);
insert into PORTFOLIO values ('IXIC','IXIC',1.00,1.00,0.00,'SECTORINDICES',0,'1970-01-01',0.00,'USD',NULL);
insert into PORTFOLIO values ('NDX','NDX',1.00,1.00,0.00,'SECTORINDICES',0,'1970-01-01',0.00,'USD',NULL);
insert into PORTFOLIO values ('OSEAX','OSEAX',1.00,1.00,0.00,'SECTORINDICES',0,'1970-01-01',0.00,'EUR',NULL);
insert into PORTFOLIO values ('SSMI','SSMI',1.00,1.00,0.00,'SECTORINDICES',0,'1970-01-01',0.00,'EUR',NULL);

insert into PORTFOLIO values ('AXJO','AXJO',1.00,1.00,0.00,'SECTORINDICES',0,'1970-01-01',0.00,'AUD',NULL);
