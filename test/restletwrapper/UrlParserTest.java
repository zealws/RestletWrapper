package restletwrapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;
import org.restlet.data.Protocol;

import restletwrapper.UrlParser;


public class UrlParserTest {

	@Test
	public void parsesHttpCorrectly() {
		UrlParser url = new UrlParser("http://google.com/");
		assertEquals(Protocol.HTTP, url.getProtocol());
	}

	@Test
	public void parsesHttpsCorrectly() {
		UrlParser url = new UrlParser("https://google.com/");
		assertEquals(Protocol.HTTPS, url.getProtocol());
	}

	@Test
	public void parsesDomainNameCorrectly() {
		UrlParser url = new UrlParser("https://google.com/");
		assertEquals("google.com", url.getHostname());
	}

	@Test
	public void parsesHostnameCorrectly() {
		UrlParser url = new UrlParser("https://localhost/");
		assertEquals("localhost", url.getHostname());
	}

	@Test
	public void parsesHostnameWithoutSlashCorrectly() {
		UrlParser url = new UrlParser("https://localhost");
		assertEquals("localhost", url.getHostname());
	}

	@Test
	public void parsesEmptyPath() {
		UrlParser url = new UrlParser("https://localhost");
		assertEquals("/", url.getPath());
	}

	@Test
	public void parsesPathCorrectly() {
		UrlParser url = new UrlParser("https://localhost/path");
		assertEquals("/path", url.getPath());
	}

	@Test
	public void parsesFullPathCorrectly() {
		UrlParser url = new UrlParser("https://localhost/path/full");
		assertEquals("/path/full", url.getPath());
	}

	@Test
	public void parsesQueryStringCorrectly() {
		UrlParser url = new UrlParser("https://localhost/path?xy=z;foo=bar");
		assertEquals("xy=z;foo=bar", url.getQueryString());
	}

	@Test
	public void parsesQueryParamsCorrectly() {
		UrlParser url = new UrlParser("https://localhost/path?xy=z;foo=bar");
		Map<String, String> queryParams = url.getQueryParams();
		assertTrue(queryParams.containsKey("xy"));
		assertTrue(queryParams.containsKey("foo"));
		assertEquals("z", queryParams.get("xy"));
		assertEquals("bar", queryParams.get("foo"));
	}
}
