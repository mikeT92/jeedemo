/*
 * jeedemo-jaxrs:RestEndpointUrlBuilder.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.jaxrs.test.rest;

import javax.ws.rs.core.UriBuilder;

/**
 * {@code Builder} for URLs to REST endpoints.
 * <p>
 * Allows injection of base URLs via system property
 * {@code jeedemo.jaxrs.endpointUrl}.
 * </p>
 * 
 * @author theism
 * @version 1.0
 * @since 09.03.2017
 */
public class RestEndpointUriBuilder {

	private static final String ENDPOINT_URL_PROPERTY_NAME = "jeedemo.jaxrs.endpointUrl";

	private static final String DEFAULT_ENDPOINT_URL = "http://localhost:8080/jeedemo-jaxrs/rest";

	private final String resourcePath;

	public RestEndpointUriBuilder(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public UriBuilder build() {
		return UriBuilder.fromPath(System.getProperty(ENDPOINT_URL_PROPERTY_NAME, DEFAULT_ENDPOINT_URL))
				.path(this.resourcePath);
	}
}
