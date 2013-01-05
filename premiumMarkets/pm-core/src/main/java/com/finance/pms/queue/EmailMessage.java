package com.finance.pms.queue;

public abstract class EmailMessage extends IdentifiedObjecMessage {

	protected EmailMessage(Integer messageKey) {
		super(messageKey);
	}

}
