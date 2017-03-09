/*
 * jeedemo-jaxrs:HelloWorldResource.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.jaxrs.port.hello.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * Simple {@code Hello World} like REST resource.
 * @author theism
 * @version 1.0
 * @since 08.03.2017
 */
@Path("hello")
public class HelloWorldResource {

	@Context
	private UriInfo context;
	
	/**
	 * Returns the welcome message as an HTML document.
	 */
	@GET
	@Produces("text/html")
	public String getWelcomeMessage() {
		return "<html lang=\"en\"><body><h1>Hello, World!!</h1></body></html>";
	}
}
