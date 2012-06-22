--Done 
update portfolio_name set totalinamountever = 0 where totalinamountever is null;
update portfolio_name set totaloutamountever = 0 where totaloutamountever is null;
alter table portfolio_name alter totalinamountever default 0;
alter table portfolio_name alter totaloutamountever  default 0;
 
 
