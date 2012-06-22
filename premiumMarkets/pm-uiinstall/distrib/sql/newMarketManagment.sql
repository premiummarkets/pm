--DONE
alter table shares add column QUOTATIONPROVIDER char(32);
alter table shares add column MARKETLISTPROVIDER char(32);
update shares set quotationprovider='BOURSORAMA' where provider='BOURSORAMA';
update shares set quotationprovider='YAHOO' where provider='YAHOO';
update shares set MARKETLISTPROVIDER='NONE' where provider='BOURSORAMA';
update shares set MARKETLISTPROVIDER='EURONEXT' where provider='YAHOO';
update shares set symbol = isin where quotationprovider='BOURSORAMA';

