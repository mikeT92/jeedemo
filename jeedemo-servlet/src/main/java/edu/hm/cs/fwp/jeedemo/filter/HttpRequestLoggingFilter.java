/**
 * 
 */
package edu.hm.cs.fwp.jeedemo.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Loggt alle relevanten relevanten Informationen zum HTTP-Request und der
 * HTTP-Response.
 * 
 * @author theism
 *
 */
@WebFilter(urlPatterns = { "/helloServlet" })
public class HttpRequestLoggingFilter implements Filter {

	private FilterConfig config;

	/**
	 * Protokolliert die Initialisierung des Filters
	 *
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
		this.config.getServletContext().log("Initialising filter [" + this.config.getFilterName() + "]");
	}

	/**
	 * Protokolliert alle relevanten Informationen zum eingehenden Request und
	 * der ausgehenden Response.
	 *
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		traceRequestInfo((HttpServletRequest) request);
		ResponseWrapper wrappedResponse = new ResponseWrapper((HttpServletResponse) response);
		chain.doFilter(request, wrappedResponse);
		traceResponseInfo(wrappedResponse);
	}

	/**
	 * Protkolliert die Freigabe des Filters
	 *
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		this.config.getServletContext().log("Destroying filter [" + this.config.getFilterName() + "]");
	}

	private void traceRequestInfo(HttpServletRequest request) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		HttpRequestLoggingWriter loggingWriter = new HttpRequestLoggingWriter(pw);
		loggingWriter.print(request);
		pw.close();
		this.config.getServletContext().log(sw.toString());
	}

	private void traceResponseInfo(ResponseWrapper wrappedResponse) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		HttpResponseLoggingWriter loggingWriter = new HttpResponseLoggingWriter(pw);
		loggingWriter.print(wrappedResponse);
		pw.close();
		this.config.getServletContext().log(sw.toString());
	}
}
