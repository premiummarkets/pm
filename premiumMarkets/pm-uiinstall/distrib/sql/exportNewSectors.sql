CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from shares where symbol in (''GLD'',''GSPC'',''FXE'',''CRUD.XPAR'',''AGG'',''DBA'')','/home/guil/Developpement/Quotes/tmp/newSectShares.dat',null,null,null);
LOAD DATA INFILE "/var/lib/mysql/premiummarkets/newSectShares.dat" IGNORE INTO TABLE SHARES COLUMNS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' ESCAPED BY '"' LINES TERMINATED BY '\n';

CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from quotations where symbol in (''GLD'',''GSPC'',''FXE'',''CRUD.XPAR'',''AGG'',''DBA'')','/home/guil/Developpement/Quotes/tmp/newSectQuotations.dat',null,null,null);
LOAD DATA INFILE "/var/lib/mysql/premiummarkets/newSectQuotations.dat" IGNORE INTO TABLE QUOTATIONS COLUMNS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' ESCAPED BY '"' LINES TERMINATED BY '\n';


--mysqlimport --local --ignore --user= --password= --host= --port=  --fields-terminated-by=',' --fields-optionally-enclosed-by='"' --fields-escaped-by='"' --lines-terminated-by='\n' premiummarkets ~/Developpement/Quotes/tmp/SHARES.txt 

insert into PORTFOLIO values ('GLD','US8633071043','SECTORINDICES', 1.00,1.00,'1970-01-01','EUR',NULL,0.00,1,0.00);
insert into PORTFOLIO values ('FXE','FXE','SECTORINDICES', 1.00,1.00,'1970-01-01','USD',NULL,0.00,1,0.00);
insert into PORTFOLIO values ('CRUD.XPAR','GB00B15KXV33','SECTORINDICES', 1.00,1.00,'1970-01-01','EUR',NULL,0.00,1,0.00);
insert into PORTFOLIO values ('AGG','AGG','SECTORINDICES', 1.00,1.00,'1970-01-01','USD',NULL,0.00,1,0.00);
insert into PORTFOLIO values ('DBA','DBA','SECTORINDICES', 1.00,1.00,'1970-01-01','USD',NULL,0.00,1,0.00);

