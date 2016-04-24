package com.finance.pms.threads;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public interface MyHttpClient<W,R> {

	R doCall(W what) throws IOException, ClientProtocolException;

}
