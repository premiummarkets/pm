--install replace.class
CALL SQLJ.install_jar('#INSTALLDIR#lib/ReplaceBin.jar','APP.ReplaceBin',0);
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.database.classpath', 'APP.ReplaceBin');
create function Replace( SourceStringExpression varchar(50),  SearchStringExpression varchar(50),  ReplaceStringExpression varchar(50)) returns varchar(50) parameter style java no sql language java external name 'Replace.replace';


--update
ALTER TABLE "APP"."PORTFOLIO" DROP CONSTRAINT "FK_PORTFOLIO_TO_PORTFOLIO_NAME";
--update PORTFOLIO_NAME set name = replace (name, ':NSE', ':NSEINDIA') where name like 'NSE%';
update PORTFOLIO_NAME set name = replace (name, 'NSE', 'NSEINDIA') where name like 'NSE%';
--update PORTFOLIO set name = replace (name, ':NSE', ':NSEINDIA') where name like 'NSE%';
update PORTFOLIO set name = replace (name, 'NSE', 'NSEINDIA') where name like 'NSE%';
ALTER TABLE "APP"."PORTFOLIO" ADD CONSTRAINT "FK_PORTFOLIO_TO_PORTFOLIO_NAME" FOREIGN KEY ("NAME") REFERENCES "APP"."PORTFOLIO_NAME" ("NAME") ON DELETE NO ACTION ON UPDATE NO ACTION;
update SHARES set MARKETLISTPROVIDER = replace (MARKETLISTPROVIDER, 'NSE', 'NSEINDIA') where MARKETLISTPROVIDER like 'NSE%';