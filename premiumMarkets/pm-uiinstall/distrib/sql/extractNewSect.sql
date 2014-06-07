connect 'jdbc:derby:/home/guil/Developpement/Quotes/premiummarkets';
select * from shares where symbol in ('GLD','GSPC','FXE','CRUD.XPAR','AGG','DBA');