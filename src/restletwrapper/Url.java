package restletwrapper;

import java.util.Map;

import org.restlet.data.Protocol;

public class Url {

	private Protocol protocol;
	private String hostname;
	private String path;
	private Map<String, String> queryParams;

	public Url(Protocol protocol, String hostname, String path, Map<String, String> queryParams) {
		this.protocol = protocol;
		this.hostname = hostname;
		this.path = path;
		this.queryParams = queryParams;
	}

	public static Url fromString(String urlString) {
		UrlParser parser = new UrlParser(urlString);
		return new Url(parser.getProtocol(), parser.getHostname(), parser.getPath(), parser.getQueryParams());
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public String getHostname() {
		return hostname;
	}

	public String getPath() {
		return path;
	}

	public Map<String, String> getQueryParams() {
		return queryParams;
	}

	public String getUrlString() {
		if (queryParams.isEmpty())
			return String.format("%s://%s%s", protocol.getName().toLowerCase(), hostname, path);
		else
			return String.format("%s://%s%s?%s", protocol.getName().toLowerCase(), hostname, path, getQueryParametersString());
	}

	private String getQueryParametersString() {
		StringBuilder b = new StringBuilder();
		boolean first = true;
		for (String name : queryParams.keySet()) {
			if (first)
				first = false;
			else
				b.append(";");
			b.append(name);
			b.append("=");
			b.append(queryParams.get(name));
		}
		return b.toString();
	}
}
