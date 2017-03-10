/*
 * jeedemo-jaxrs:BoundaryBean.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.jaxrs.port.inject.rest;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Dummy {@code Boundary} implementation to demonstrate dependency injection on
 * JAX-RS resource classes.
 * 
 * @author theism
 * @version 1.0
 * @since 10.03.2017
 */
@Named
@Singleton
public class BoundaryBean implements Boundary {

	/**
	 * @see edu.hm.cs.fwp.jeedemo.jaxrs.port.inject.rest.Boundary#getValue()
	 */
	@Override
	public String getValue() {
		return "successfully retrieved data from injected repository!";
	}

}
