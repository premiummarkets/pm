package com.finance.pms.queue;

import java.util.Map;

import com.finance.pms.admin.config.Config;

public abstract class EmailMessage extends IdentifiedObjecMessage {

	protected EmailMessage(Integer messageKey, Map<String, Config> ptc) {
		super(messageKey, ptc);
	}

}
