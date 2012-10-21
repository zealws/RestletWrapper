package com.zealjagannatha.http.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.restlet.Response;
import org.restlet.data.Status;

import com.zealjagannatha.http.client.HttpClient;

public class HttpClientTest {

	@Test
	public void getSpecificUri() {
		HttpClient client = new HttpClient();
		Response response = client.get("http://www.google.com/");
		assertEquals(Status.SUCCESS_OK, response.getStatus());
	}

	@Test
	public void getResponseHasBody() {
		HttpClient client = new HttpClient();
		Response response = client.get("http://www.google.com/");
		assertTrue(response.getEntityAsText().length() != 0);
	}

}
