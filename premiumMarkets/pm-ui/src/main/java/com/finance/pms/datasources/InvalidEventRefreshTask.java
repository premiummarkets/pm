package com.finance.pms.datasources;

public class InvalidEventRefreshTask extends Exception {


	private static final long serialVersionUID = 6245991820060000490L;

	
	private TaskId taskId;
	private Object rootViewParam;

	public InvalidEventRefreshTask(TaskId lastTaskOfThisGroup, Object rootViewParam) {
		this.taskId = lastTaskOfThisGroup;
		this.rootViewParam = rootViewParam;
	}

	public TaskId getTaskId() {
		return taskId;
	}

	@Override
	public String toString() {
		return taskId.getDescr()+ ((rootViewParam !=null)?" for "+rootViewParam:"");
	}
	
	
}
