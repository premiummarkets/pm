package com.finance.pms.datasources;

public enum TaskId {
	
	FetchLists ("Fetching stock(s) list(s)"),
	FetchQuotations ("Fetching quotations"),
	Alerts ("Refreshing alerts"),
	Clean ("Cleaning events"),
	Analysis ("Running analysis"),
	FetchRecos ("Fetching recommendations"),
	ViewRefresh ("Refreshing views");
	
	String descr;
	
	private TaskId(String descr) {
		this.descr = descr;
	}
	
	public String getDescr() {
		return descr;
	}
	
	
	}

