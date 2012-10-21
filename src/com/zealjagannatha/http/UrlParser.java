package com.zealjagannatha.http;

import java.util.HashMap;
import java.util.Map;

import org.restlet.data.Protocol;

class UrlParser {

	public final String rawUrl;

	public UrlParser(String raw) {
		rawUrl = raw;
	}

	public Protocol getProtocol() {
		return Protocol.valueOf(parseProtocol());
	}

	private String parseProtocol() {
		return rawUrl.substring(0, protocolEnd());
	}

	public String getHostname() {
		return rawUrl.substring(hostnameStart(), hostnameEnd());
	}

	private int protocolEnd() {
		return rawUrl.indexOf(":");
	}

	private int hostnameStart() {
		return protocolEnd() + 3;
	}

	private int hostnameEnd() {
		if (rawUrl.indexOf(":", hostnameStart()) != -1)
			return rawUrl.indexOf(":", hostnameStart());
		if (rawUrl.indexOf("/", hostnameStart()) != -1)
			return rawUrl.indexOf("/", hostnameStart());
		else
			return rawUrl.length();
	}

	public String getPath() {
		if (pathStart() >= rawUrl.length())
			return "/";
		return rawUrl.substring(pathStart(), pathEnd());
	}

	private int pathStart() {
		return hostnameEnd();
	}

	private int pathEnd() {
		int semicolon = rawUrl.indexOf(";", pathStart());
		int questionMark = rawUrl.indexOf("?", pathStart());
		int pound = rawUrl.indexOf("#", pathStart());
		semicolon = (semicolon == -1 ? rawUrl.length() : semicolon);
		pound = (pound == -1 ? rawUrl.length() : pound);
		questionMark = (questionMark == -1 ? rawUrl.length() : questionMark);
		return Math.min(semicolon, Math.min(questionMark, pound));
	}

	String getQueryString() {
		if (paramStart() >= rawUrl.length())
			return "";
		return rawUrl.substring(paramStart(), paramEnd());
	}

	private int paramStart() {
		return pathEnd() + 1;
	}

	private int paramEnd() {
		if (rawUrl.indexOf("#", paramStart()) != -1)
			return rawUrl.indexOf("#", paramStart());
		return rawUrl.length();
	}

	public Map<String, String> getQueryParams() {
		String query = getQueryString();
		Map<String, String> queryParams = new HashMap<String, String>();
		if (query.length() > 0) {
			for (String raw : query.split(";")) {
				queryParams.put(raw.substring(0, raw.indexOf('=')), raw.substring(raw.indexOf('=') + 1));
			}
		}
		return queryParams;
	}
}
