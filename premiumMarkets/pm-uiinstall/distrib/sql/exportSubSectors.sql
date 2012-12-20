CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select shares.* from shares,portfolio where shares.symbol=portfolio.symbol and shares.isin=portfolio.isin and portfolio.name=''subSectors''','/home/guil/Developpement/Quotes/tmp/SSSHARES.dat',null,null,null);
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select quotations.* from quotations,portfolio where quotations.symbol=portfolio.symbol and quotations.isin=portfolio.isin and portfolio.name=''subSectors''','/home/guil/Developpement/Quotes/tmp/SSQUOTATIONS.dat',null,null,null);

CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select portfolio.* from portfolio where portfolio.name=''subSectors''','/home/guil/Developpement/Quotes/tmp/PORTFOLIO.dat',null,null,null);

--replace portfolio name
LOAD DATA INFILE "/var/lib/mysql/premiummarkets/PORTFOLIO.dat" INTO TABLE PORTFOLIO COLUMNS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' ESCAPED BY '"' LINES TERMINATED BY '\n';

ALTER TABLE PORTFOLIO ADD CONSTRAINT FK_PORTFOLIO_TO_PORTFOLIO_NAME FOREIGN KEY (NAME) REFERENCES PORTFOLIO_NAME (NAME) ON DELETE NO ACTION ON UPDATE NO ACTION;
