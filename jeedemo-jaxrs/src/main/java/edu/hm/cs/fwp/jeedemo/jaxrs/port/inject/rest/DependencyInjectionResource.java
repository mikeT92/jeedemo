/*
 * jeedemo-jaxrs:DependencyInjectionResource.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.jaxrs.port.inject.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Simple REST {@code Resource} to demonstrate {@code CDI} dependency injection
 * on JAX-RS resource classes.
 * <p>
 * By default, classes annotated with {@code @Path} are managed by the JAX-RS
 * runtime and not by CDI. So CDI dependency injection does not work
 * out-of-the-box. The only way to solve this problem is to make the JAX-RS
 * resource class either a scoped CDI-managed bean or an EJB-managed stateless
 * session bean.
 * </p>
 * 
 * @author theism
 * @version 1.0
 * @since 10.03.2017
 */
@Path("injections")
@RequestScoped
public class DependencyInjectionResource {

	@Inject
	private Boundary repository;

	@GET
	public String getValue() {
		return this.repository.getValue();
	}
}
