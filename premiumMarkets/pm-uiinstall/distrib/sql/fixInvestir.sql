
-- clean
delete from quotations where symbol in (select symbol from shares where QUOTATIONPROVIDER='INVESTIR') and date > '2009-02-18';
-- then resync shares last quotes => fixLastCals.sql
-- then update from UI or export/import

--export
create view investirQ as select quotations.* from quotations join shares on shares.isin = quotations.isin and shares.symbol = quotations.symbol where shares.QUOTATIONPROVIDER='INVESTIR' and quotations.date > '2009-02-18';
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from investirQ','/home/guil/tmp/investirQ.dat',null,null,null);

-- import
-- clean (delete) then
CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(null,'QUOTATIONS','/home/guil/tmp/investirQ.dat',null,null,null,0);
-- then resync shares last quotes => fixLastCals.sql
