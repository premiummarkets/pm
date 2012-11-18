--NX
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from shares where symbol in (''FRFPR.NX'',''FROG.NX'',''FRBM.NX'',''FRCG.NX'',''FRFIN.NX'',''FRIN.NX'',''FRCS.NX'',''FRHC.NX'',''FRUT.NX'',''FRTEL.NX'',''FRTEC.NX'')','/home/guil/Developpement/Quotes/tmp/shares.dat',null,null,null);
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from quotations where symbol in  (''FRFPR.NX'',''FROG.NX'',''FRBM.NX'',''FRCG.NX'',''FRFIN.NX'',''FRIN.NX'',''FRCS.NX'',''FRHC.NX'',''FRUT.NX'',''FRTEL.NX'',''FRTEC.NX'')','/home/guil/Developpement/Quotes/tmp/quotations.dat',null,null,null);

sed "s/ *\"/\"/g" quotations.dat > quotations_TRIMED.dat


--Import
CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(null,'SHARES','/home/guil/Developpement/Quotes/tmp/shares.dat',null,null,null,0);
CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(null,'QUOTATIONS','/home/guil/Developpement/Quotes/tmp/quotations_TRIMED.dat',null,null,null,0);

--US + GOLD
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from shares where symbol in (''XLY'',''XLK'',''XLI'',''XLB'',''XLE'',''XLP'',''XLV'',''XLU'',''XLF'',''3233S.XPAR'',''CRB.XPAR'',''1008N.XPAR'')','/home/guil/Developpement/Quotes/tmp/shares.dat',null,null,null);
CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY ('select * from quotations where symbol in  (''XLY'',''XLK'',''XLI'',''XLB'',''XLE'',''XLP'',''XLV'',''XLU'',''XLF'',''3233S.XPAR'',''CRB.XPAR'',''1008N.XPAR'')','/home/guil/Developpement/Quotes/tmp/quotations.dat',null,null,null);

sed "s/ *\"/\"/g" quotations.dat > quotations_TRIMED.dat


--Import
CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(null,'SHARES','/home/guil/Developpement/Quotes/tmp/shares.dat',null,null,null,0);
CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(null,'QUOTATIONS','/home/guil/Developpement/Quotes/tmp/quotations_TRIMED.dat',null,null,null,0);