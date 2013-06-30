package com.finance.pms.datasources;

public class InvalidEventRefreshTask extends Exception {


	private static final long serialVersionUID = 6245991820060000490L;

	
	private TaskId taskId;

	public InvalidEventRefreshTask(TaskId lastTaskOfThisGroup) {
		this.taskId = lastTaskOfThisGroup;
	}

	public TaskId getTaskId() {
		return taskId;
	}

}
