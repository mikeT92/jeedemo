/*
 * jeedemo-jaxrs:RestApplicationConfig.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.jaxrs;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import edu.hm.cs.fwp.jeedemo.jaxrs.port.hello.rest.HelloWorldResource;
import edu.hm.cs.fwp.jeedemo.jaxrs.port.tasks.rest.TasksResource;

/**
 * REST configuration for this application.
 * 
 * @author theism
 * @version 1.0
 * @since 09.03.2017
 */
@ApplicationPath("rest")
public class RestApplicationConfig extends Application {

	/**
	 * Returns list of classes which are REST resources or in other words REST
	 * endpoints.
	 * 
	 * @see javax.ws.rs.core.Application#getClasses()
	 */
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> result = new HashSet<>();
		result.add(HelloWorldResource.class);
		result.add(TasksResource.class);
		return result;
	}
}
