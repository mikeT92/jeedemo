package edu.hm.cs.fwp.jeedemo.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * {@code Decorator} für eine {@code HttpServletResponse}, der Modifikationen an
 * der Response f�r das Logging aufzeichnet.
 * 
 * @author F7WFF5T
 *
 */
public class ResponseWrapper extends HttpServletResponseWrapper {

	private String statusMessage;

	private String redirectURL;

	private Map<String, String> headers = new LinkedHashMap<>();

	public ResponseWrapper(HttpServletResponse response) {
		super(response);
	}

	@Override
	public void sendRedirect(String location) throws IOException {
		super.sendRedirect(location);
		this.redirectURL = location;
	}

	@Override
	public void sendError(int sc, String msg) throws IOException {
		super.sendError(sc, msg);
		this.statusMessage = msg;
	}

	@Override
	public void addHeader(String name, String value) {
		super.addHeader(name, value);
		this.headers.put(name, value);
	}

	public String getStatusMessage() {
		return this.statusMessage;
	}

	public String getRedirectURL() {
		return this.redirectURL;
	}

	public Map<String, String> getHeaders() {
		return Collections.unmodifiableMap(this.headers);
	}
}
