package com.zealjagannatha.http;

import org.restlet.Client;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Protocol;

public class HttpClient {

	Client client = new Client(Protocol.HTTP);

	public HttpClient() {

	}

	public Response get() {
		return new Response(new Request());
	}

	public Response get(String string) {
		return new Response(new Request());
	}

}
