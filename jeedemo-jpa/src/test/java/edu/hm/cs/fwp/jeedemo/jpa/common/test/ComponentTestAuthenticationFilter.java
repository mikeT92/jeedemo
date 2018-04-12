package edu.hm.cs.fwp.jeedemo.jpa.common.test;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * {@code Filter} for component tests which require an authenticated user.
 *
 * @author Michael Theis (msg)
 * @version 1.0
 * @since release 18.2
 */
// @WebFilter(urlPatterns = { "/*" })
public class ComponentTestAuthenticationFilter implements Filter {

	private static final String TEST_USER_NAME = "fwparqln";
	private static final String TEST_USER_PASSWORD = "arquillian";
	private static final String TEST_USER_REALM = "JEEDEMO_REALM";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		httpRequest.login(TEST_USER_NAME, TEST_USER_PASSWORD);

		chain.doFilter(request, response);

		httpRequest.logout();
	}

	@Override
	public void destroy() {
	}
}
