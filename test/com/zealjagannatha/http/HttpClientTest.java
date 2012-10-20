package com.zealjagannatha.http;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.restlet.Response;
import org.restlet.data.Status;

public class HttpClientTest {

	@Test
	public void getSucceeds() {
		HttpClient client = new HttpClient();
		Response response = client.get();
		assertEquals(Status.SUCCESS_OK, response.getStatus());
	}

	@Test
	public void getSpecificUri() {
		HttpClient client = new HttpClient();
		Response response = client.get("http://google.com/");
		assertEquals(Status.SUCCESS_OK, response.getStatus());
	}
}
