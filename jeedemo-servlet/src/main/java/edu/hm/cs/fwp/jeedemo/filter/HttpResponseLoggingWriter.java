/**
 * 
 */
package edu.hm.cs.fwp.jeedemo.filter;

import java.io.PrintWriter;
import java.util.Map;

/**
 * {@code Writer} für alle relevanten Informationen zu einer HTTP-Response.
 * 
 * @author theism
 *
 */
public final class HttpResponseLoggingWriter {

	private static final int DEFAULT_TEXT_IDENT = 4;

	private final PrintWriter writer;

	private int textIndent;

	public HttpResponseLoggingWriter(PrintWriter writer) {
		this.writer = writer;
	}

	public void print(ResponseWrapper response) {
		writer.print("Response {");
		incrementTextIndent();
		printProperties(response);
		printHeaders(response);
		decrementTextIndent();
		println();
		writer.print("}");
	}

	private void printProperties(ResponseWrapper response) {
		int currentFieldIndex = 0;
		printField(currentFieldIndex, "statusCode", response.getStatus());
		printField(++currentFieldIndex, "statusMessage", response.getStatusMessage());
		printField(++currentFieldIndex, "redirectURL", response.getRedirectURL());
	}

	private void printHeaders(ResponseWrapper response) {
		Map<String, String> headers = response.getHeaders();
		if (!headers.isEmpty()) {
			println();
			writer.print("Headers { ");
			incrementTextIndent();
			int currentHeaderIndex = 0;
			for (Map.Entry<String, String> currentHeader : headers.entrySet()) {
				if (currentHeaderIndex > 0) {
					writer.print(",");
				}
				println();
				printValue(currentHeader.getKey(), currentHeader.getValue());
				currentHeaderIndex++;
			}
			decrementTextIndent();
			println();
			writer.print("}");
		}
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
