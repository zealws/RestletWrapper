package restletwrapper.client;

import org.restlet.Client;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Method;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;

import restletwrapper.Url;


public class HttpClient {

	private Response handle(Method method, Url url, Representation body) {
		Client client = new Client(url.getProtocol());
		Request request = new Request(method, url.getUrlString());
		if (body != null)
			request.setEntity(body);
		return client.handle(request);
	}

	protected Representation getDefaultRepresentation(String body) {
		return new StringRepresentation(body);
	}

	// GET
	// Does not allow body.

	public Response get(String url) {
		return get(Url.fromString(url));
	}

	public Response get(Url url) {
		return handle(Method.GET, url, null);
	}

	// DELETE
	// Does not allow body

	public Response delete(String url) {
		return delete(Url.fromString(url));
	}

	public Response delete(Url url) {
		return handle(Method.DELETE, url, null);
	}

	// POST

	public Response post(String url, String body) {
		return post(Url.fromString(url), body);
	}

	public Response post(Url url, String body) {
		return post(url, getDefaultRepresentation(body));
	}

	public Response post(String url, Representation body) {
		return post(Url.fromString(url), body);
	}

	public Response post(Url url, Representation body) {
		return handle(Method.POST, url, body);
	}

	// PUT

	public Response put(String url, String body) {
		return post(Url.fromString(url), body);
	}

	public Response put(Url url, String body) {
		return post(url, getDefaultRepresentation(body));
	}

	public Response put(String url, Representation body) {
		return put(Url.fromString(url), body);
	}

	public Response put(Url url, Representation body) {
		return handle(Method.PUT, url, body);
	}

}
