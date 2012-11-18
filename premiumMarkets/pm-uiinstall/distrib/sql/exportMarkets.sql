CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from portfolio where portfolio.name = ''YAHOOINDICES,NDX:NASDAQ,NY:NYSE''','/home/guil/Developpement/Quotes/tmp/portfolio.dat',null,null,null);
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from portfolio_name where portfolio_name.name = ''YAHOOINDICES,NDX:NASDAQ,NY:NYSE''','/home/guil/Developpement/Quotes/tmp/portfolio_name.dat',null,null,null);
--CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from shares where symbol in (select symbol from portfolio where portfolio.name = ''YAHOOINDICES,NDX:NASDAQ,NY:NYSE'')','/home/guil/Developpement/Quotes/tmp/shares.dat',null,null,null);
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from shares where symbol in (''ACL'',''GENZ'',''NEE'',''STT'',''HANS'',''JNPR'',''MOS'',''FWLT'',''PPDI'',''AFL'',''MHS'',''MICC'',''LOGI'',''XTO'',''BNI'',''PCU'',''BLK'',''JBHT'',''RIG'',''PDCO'',''IACI'',''URBN'',''MRO'',''CTSH'',''STLD'',''LBTYA'',''DISH'',''HOLX'',''FPL'',''WFMI'',''CTXS'',''FLIR'',''NIHD'',''QGEN'',''CEPH'',''ERTS'',''JOYG'',''ILMN'',''VIAB'')','/home/guil/Developpement/Quotes/tmp/shares.dat',null,null,null);
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from shares where symbol in (''BRK-B'',''ABT'',''GS'',''TGT'',''PX'',''GIS'',''LMT'',''UNH'',''OXY'',''WAG'',''MON'',''DD'',''KFT'',''D'',''MMM'',''ADBE'',''LLY'',''SO'',''LOW'',''EXC'',''COP'',''WFC'',''MS'',''DUK'',''KMB'',''TRV'',''DVN'',''GLW'',''SLB'',''FDX'',''WLP'',''FCX'',''APOL'',''BIDU'')','/home/guil/Developpement/Quotes/tmp/shares2.dat',null,null,null);
--CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from quotations where symbol in (select symbol from portfolio where portfolio.name = ''YAHOOINDICES,NDX:NASDAQ,NY:NYSE'')','/home/guil/Developpement/Quotes/tmp/quotations.dat',null,null,null);
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from quotations where symbol in  (''ACL'',''GENZ'',''NEE'',''STT'',''HANS'',''JNPR'',''MOS'',''FWLT'',''PPDI'',''AFL'',''MHS'',''MICC'',''LOGI'',''XTO'',''BNI'',''PCU'',''BLK'',''JBHT'',''RIG'',''PDCO'',''IACI'',''URBN'',''MRO'',''CTSH'',''STLD'',''LBTYA'',''DISH'',''HOLX'',''FPL'',''WFMI'',''CTXS'',''FLIR'',''NIHD'',''QGEN'',''CEPH'',''ERTS'',''JOYG'',''ILMN'',''VIAB'')','/home/guil/Developpement/Quotes/tmp/quotations.dat',null,null,null);
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from quotations where symbol in  (''BRK-B'',''ABT'',''GS'',''TGT'',''PX'',''GIS'',''LMT'',''UNH'',''OXY'',''WAG'',''MON'',''DD'',''KFT'',''D'',''MMM'',''ADBE'',''LLY'',''SO'',''LOW'',''EXC'',''COP'',''WFC'',''MS'',''DUK'',''KMB'',''TRV'',''DVN'',''GLW'',''SLB'',''FDX'',''WLP'',''FCX'',''APOL'',''BIDU'')','/home/guil/Developpement/Quotes/tmp/quotations2.dat',null,null,null);

sed "s/ *\"/\"/g" quotations.dat > quotations_TRIMED.dat
sed "s/ *\"/\"/g" quotations2.dat > quotations2_TRIMED.dat

select * from shares where symbol in ('ACL','GENZ','NEE','STT','HANS','JNPR','MOS','FWLT','PPDI','AFL','MHS','MICC','LOGI','XTO','BNI','PCU','BLK','JBHT','RIG','PDCO','IACI','URBN','MRO','CTSH','STLD','LBTYA','DISH','HOLX','FPL','WFMI','CTXS','FLIR','NIHD','QGEN','CEPH','ERTS','JOYG','ILMN','VIAB');


--Import
CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(null,'SHARES','/home/guil/Developpement/Quotes/tmp/shares.dat',null,null,null,0);
CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(null,'QUOTATIONS','/home/guil/Developpement/Quotes/tmp/quotations_TRIMED.dat',null,null,null,0);
CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(null,'SHARES','/home/guil/Developpement/Quotes/tmp/shares2.dat',null,null,null,0);
CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(null,'QUOTATIONS','/home/guil/Developpement/Quotes/tmp/quotations2_TRIMED.dat',null,null,null,0);
CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(null,'PORTFOLIO_NAME','/home/guil/Developpement/Quotes/tmp/portfolio_name.dat',null,null,null,0);
CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(null,'PORTFOLIO','/home/guil/Developpement/Quotes/tmp/portfolio.dat',null,null,null,0);