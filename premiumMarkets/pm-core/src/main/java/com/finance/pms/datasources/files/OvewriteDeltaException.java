package com.finance.pms.datasources.files;

public class OvewriteDeltaException extends Exception {

	private String filePath;

	public OvewriteDeltaException(String filePath) {
		this.filePath = filePath;
 	}

	public String getFilePath() {
		return filePath;
	}

	private static final long serialVersionUID = 1L;

}
