/* HelloServlet.java @(#)%PID%
 */
package edu.hm.cs.fwp.jeedemo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Simple demo implementation of a {@code Servlet}.
 * 
 * @author theism
 * @version 1.0
 * @since release 1.0 11.04.2013 22:25:56
 */
@WebServlet(urlPatterns = { "/helloServlet" })
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 590153928180563245L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter writer = new PrintWriter(response.getOutputStream());
		writer.println("Hello out there!");
		writer.flush();
		writer.close();
	}
}
