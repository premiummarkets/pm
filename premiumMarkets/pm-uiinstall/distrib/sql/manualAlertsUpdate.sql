 select * from alerts where alerttype  = 'MANUAL';
 update alerts set alerttype = 'MANUALUP' where alerttype = 'MANUAL' and THRESHOLDTYPE='UP';
 update alerts set alerttype = 'MANUALDOWN'  where alerttype = 'MANUAL' and THRESHOLDTYPE='DOWN'; 