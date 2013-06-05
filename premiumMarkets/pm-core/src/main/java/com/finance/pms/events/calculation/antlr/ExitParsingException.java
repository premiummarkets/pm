package com.finance.pms.events.calculation.antlr;

import org.antlr.runtime.RecognitionException;

import com.finance.pms.admin.install.logging.MyLogger;


public class ExitParsingException extends RuntimeException {

	private static final long serialVersionUID = 7393866925465101476L;
	
	public static MyLogger LOGGER = MyLogger.getLogger(ParameterizedBuilder.class);
	
	public ExitParsingException(String msg, RecognitionException e) {
		super(msg, e);
	}
	

	public ExitParsingException() {
		super();
		LOGGER.info("Getting out");
	}
	
}
