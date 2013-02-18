

--//	private String configStr; //TODO remove column
--	
--	@Column(name="lastcalculationstart") //TODO rename from lasttuning => lastcalculationstart
--	private Date lastCalculationStart;
--	@Column(name="lastcalculatedevent") //TODO  rename from lastcalculation => lastcalculatedevent
--	private Date lastCalculatedEvent;
--	@Column(name="lastcalculationend") //TODO new
--	private Date lastCalculationEnd;
--	
--//	@Column(name="staled") //TODO remove column
--//	private Boolean valid;

alter table TUNEDCONF drop column staled;
alter table TUNEDCONF drop column configStr;

--alter table TUNEDCONF add column lastcalculationstart DATE NOT NULL DEFAULT '1970-01-01';
--update TUNEDCONF set lastcalculationstart = lasttuning;
--alter table TUNEDCONF drop column lasttuning;
alter table TUNEDCONF change column LASTTUNING LASTCALCULATIONSTART  DATE NOT NULL DEFAULT '1970-01-01';
--alter table TUNEDCONF add column lastcalculatedevent DATE;
--update TUNEDCONF set lastcalculatedevent = lastcalculation;
--alter table TUNEDCONF drop column lastcalculation;
alter table TUNEDCONF change column LASTCALCULATION LASTCALCULATEDEVENT  DATE NULL;

alter table TUNEDCONF add column LASTCALCULATIONEND DATE NOT NULL DEFAULT '1970-01-01';
update TUNEDCONF set LASTCALCULATIONEND = LASTCALCULATEDEVENT;