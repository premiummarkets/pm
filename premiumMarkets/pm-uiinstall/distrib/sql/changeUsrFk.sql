ALTER TABLE USERS drop FOREIGN KEY  `FK_USER_TO_STOCK`;
ALTER TABLE USERS drop KEY  `FK_USER_TO_STOCK`;
alter table USERS drop column name;
alter table USERS add column `NAME` VARCHAR(255) NOT NULL;
alter table USERS change column NAME NAME  varchar(255) NOT NULL after ISIN;

update USERS join (select PORTFOLIO.name, PORTFOLIO.symbol, PORTFOLIO.isin from PORTFOLIO) as A on A.symbol = USERS.symbol and A.isin = USERS.isin set USERS.NAME = A.name;
update PORTFOLIO set  NAME='UNKNOWN' where NAME='Sample Portfolio';
update USERS set NAME='UNKNOWN' where NAME='Sample Portfolio';


ALTER TABLE  USERS  ADD CONSTRAINT `FK_USER_TO_PORTFOLIO` FOREIGN KEY (`SYMBOL`, `ISIN`, `NAME` ) REFERENCES `PORTFOLIO` (`SYMBOL`, `ISIN`, `NAME`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE  USERS ADD  PRIMARY KEY (`EMAIL`,`SYMBOL`,`ISIN`,`NAME`);