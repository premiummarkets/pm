package com.finance.pms.threads;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

public class HtmlUnitClient implements SourceClient, MyHttpClient<String, Page> {
	
	private WebClient webClient;

	public HtmlUnitClient(WebClient webClient) {
		super();
		this.webClient = webClient;
	}

	@Override
	public Boolean isValid() {
		return true;
	}

	@Override
	public Page doCall(String what) throws IOException, ClientProtocolException {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		return webClient.getPage(what);
	}


}
