/**
 * 
 */
package edu.hm.cs.fwp.jeedemo.filter;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * {@code Writer} für die relevanten Informationen zu einem HTTP-Request.
 * 
 * @author theism
 *
 */
public final class HttpRequestLoggingWriter {

	private static final int DEFAULT_TEXT_IDENT = 4;

	private final PrintWriter writer;

	private int textIndent;

	public HttpRequestLoggingWriter(PrintWriter writer) {
		this.writer = writer;
	}

	public void print(HttpServletRequest request) {
		writer.print("Request {");
		incrementTextIndent();
		printProperties(request);
		printHeaders(request);
		printCookies(request);
		printParameters(request);
		printAttributes(request);
		decrementTextIndent();
		println();
		writer.print("}");
	}

	private void printProperties(HttpServletRequest request) {
		int currentFieldIndex = 0;
		String actualRequestURL = null;
		StringBuffer requestURL = request.getRequestURL();
		if (requestURL != null) {
			if (request.getQueryString() != null && request.getQueryString().length() != 0) {
				requestURL.append("?").append(request.getQueryString());
			}
			actualRequestURL = requestURL.toString();
		}

		printField(currentFieldIndex, "requestURL", actualRequestURL);
		printField(currentFieldIndex, "requestURI", request.getRequestURI());
		printField(++currentFieldIndex, "method", request.getMethod());
		printField(++currentFieldIndex, "characterEncoding", request.getCharacterEncoding());
		printField(++currentFieldIndex, "contentLength", request.getContentLength());
		printField(++currentFieldIndex, "contentType", request.getContentType());
		printField(++currentFieldIndex, "localAddr", request.getLocalAddr());
		printField(++currentFieldIndex, "localName", request.getLocalName());
		printField(++currentFieldIndex, "localPort", request.getLocalPort());
		printField(++currentFieldIndex, "remoteAddr", request.getRemoteAddr());
		printField(++currentFieldIndex, "remoteHost", request.getRemoteHost());
		printField(++currentFieldIndex, "remotePort", request.getRemotePort());
		printField(++currentFieldIndex, "remoteUser", request.getRemoteUser());
	}

	private void printHeaders(HttpServletRequest request) {
		Enumeration<String> headerNames = request.getHeaderNames();
		if (headerNames.hasMoreElements()) {
			println();
			writer.print("Headers { ");
			incrementTextIndent();
			int currentHeaderIndex = 0;
			while (headerNames.hasMoreElements()) {
				if (currentHeaderIndex > 0) {
					writer.print(",");
				}
				println();
				String currentName = headerNames.nextElement();
				printValue(currentName, request.getHeader(currentName));
				currentHeaderIndex++;
			}
			decrementTextIndent();
			println();
			writer.print("}");
		}
	}

	private void printCookies(HttpServletRequest request) {
		if (request.getCookies() != null && request.getCookies().length != 0) {
			int currentCookieIndex = 0;
			for (Cookie currentCookie : request.getCookies()) {
				if (currentCookieIndex > 0) {
					writer.print(",");
				}
				println();
				print(currentCookie);
			}
		}
	}

	private void printParameters(HttpServletRequest request) {
		if (!request.getParameterMap().isEmpty()) {
			println();
			writer.print("Parameters { ");
			incrementTextIndent();
			int currentParamIndex = 0;
			for (Map.Entry<String, String[]> currentParam : request.getParameterMap().entrySet()) {
				if (currentParam.getValue().length == 1) {
					if (currentParamIndex > 0) {
						writer.print(",");
					}
					println();
					writer.print(currentParam.getKey());
					writer.print(" : \"");
					writer.print(currentParam.getValue()[0]);
					writer.print("\"");
					currentParamIndex++;
				} else {
					int currentValueIndex = 0;
					for (String currentValue : currentParam.getValue()) {
						if (currentParamIndex > 0) {
							writer.print(",");
						}
						println();
						writer.print(currentParam.getKey());
						writer.print("[");
						writer.print(currentValueIndex);
						writer.print("] : \"");
						writer.print(currentValue);
						writer.print("\"");
						currentValueIndex++;
						currentParamIndex++;
					}
				}
			}
			decrementTextIndent();
			println();
			writer.print("}");
		}

	}

	private void printAttributes(HttpServletRequest request) {
		Enumeration<String> attributeNames = request.getAttributeNames();
		if (attributeNames.hasMoreElements()) {
			println();
			writer.print("Attributes { ");
			incrementTextIndent();
			int currentAttrIndex = 0;
			while (attributeNames.hasMoreElements()) {
				if (currentAttrIndex > 0) {
					writer.print(",");
				}
				println();
				String currentName = attributeNames.nextElement();
				printValue(currentName, request.getAttribute(currentName));
				currentAttrIndex++;
			}
			decrementTextIndent();
			println();
			writer.print("}");
		}
	}

	private void print(Cookie value) {
		println();
		writer.print("Cookie {");
		incrementTextIndent();
		int currentFieldIndex = 0;
		printField(currentFieldIndex, "name", value.getName());
		printField(++currentFieldIndex, "domain", value.getDomain());
		printField(++currentFieldIndex, "path", value.getPath());
		printField(++currentFieldIndex, "maxAge", value.getMaxAge());
		printField(++currentFieldIndex, "secure", value.getSecure());
		printField(++currentFieldIndex, "value", value.getValue());
		printField(++currentFieldIndex, "version", value.getVersion());
		printField(++currentFieldIndex, "comment", value.getComment());
		decrementTextIndent();
		println();
		writer.print("}");
	}

	private void printValue(String name, String value) {
		writer.print(name);
		if (value != null) {
			writer.print(" : \"");
			writer.print(value);
			writer.print("\"");
		} else {
			writer.print("null");
		}
	}

	private void printValue(String name, Object value) {
		writer.print(name);
		writer.print(" : ");
		writer.print(value);
	}

	private void printField(int fieldIndex, String fieldName, Object fieldValue) {
		if (fieldValue != null) {
			if (fieldIndex > 0) {
				writer.print(",");
			}
			println();
			writer.print(fieldName);
			writer.print(" : ");
			if (fieldValue instanceof String) {
				writer.print("\"");
			}
			writer.print(fieldValue);
			if (fieldValue instanceof String) {
				writer.print("\"");
			}
		}
	}

	private void incrementTextIndent() {
		this.textIndent += DEFAULT_TEXT_IDENT;
	}

	private void decrementTextIndent() {
		this.textIndent -= DEFAULT_TEXT_IDENT;
	}

	private void println() {
		writer.println();
		for (int textIndentIndex = 0; textIndentIndex < textIndent; textIndentIndex++) {
			writer.print(" ");
		}
	}
}
