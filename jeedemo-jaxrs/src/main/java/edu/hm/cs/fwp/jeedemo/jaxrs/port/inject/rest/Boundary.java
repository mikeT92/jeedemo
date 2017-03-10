/*
 * jeedemo-jaxrs:ServiceBean.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.jaxrs.port.inject.rest;

/**
 * Simple {@code Boundary} to demonstrate dependency injection into JAX-RX
 * resource classes.
 * 
 * @author theism
 * @version 1.0
 * @since 10.03.2017
 */
public interface Boundary {

	/**
	 * Returns a simple value.
	 */
	String getValue();
}
