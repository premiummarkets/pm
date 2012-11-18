CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from shares where symbol in (''DJI'',''IXIC'',''GSPC'',''CTAS'',''RYAAY'')','/home/guil/Developpement/Quotes/tmp/SSSHARES.dat',null,null,null);

--CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from portfolio where symbol in (''DJI'',''IXIC'',''GSPC'',''CTAS'',''RYAAY'')','/home/guil/Developpement/Quotes/tmp/SSPORTFOLIO.dat',null,null,null);

CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from quotations where symbol in (''DJI'',''IXIC'',''GSPC'',''CTAS'',''RYAAY'')','/home/guil/Developpement/Quotes/tmp/SSQUOTATIONS.dat',null,null,null);

--sed "s/ *\"/\"/g" SSQUOTATIONS.dat > SSQUOTATIONS_TRIMED.dat


--Import
--'DJI','IXIC','GSPC','CTAS','RYAAY'
CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(null,'SHARES','/home/guil/Developpement/Quotes/tmp/SSSHARES.dat',null,null,null,0);
CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(null,'QUOTATIONS','/home/guil/Developpement/Quotes/tmp/SSQUOTATIONS_TRIMED.dat',null,null,null,0);