package com.zealjagannatha.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;
import org.restlet.data.Protocol;

public class UrlTest {

	@Test
	public void constructsUrlCorrectly() {
		Url url = Url.fromString("https://localhost/full/path?xy=z;foo=bar");
		assertEquals(Protocol.HTTPS, url.getProtocol());
		assertEquals("localhost", url.getHostname());
		assertEquals("/full/path", url.getPath());
		Map<String, String> queryParams = url.getQueryParams();
		assertTrue(queryParams.containsKey("xy"));
		assertTrue(queryParams.containsKey("foo"));
		assertEquals("z", queryParams.get("xy"));
		assertEquals("bar", queryParams.get("foo"));
	}

	@Test
	public void reconstructsUrlStringCorrectly() {
		String raw = "https://localhost/full/path?foo=bar;xy=z";
		Url url = Url.fromString(raw);
		assertEquals(raw, url.getUrlString());
	}
}
