/*
 * jeedemo-jaxrs:DependencyInjectionSystemTest.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.jaxrs.port.inject.rest;

import static org.junit.Assert.*;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.junit.Test;

import edu.hm.cs.fwp.jeedemo.jaxrs.test.rest.RestEndpointUriBuilder;

/**
 * {@code System Test} on {@link DependencyInjectionResource} to verify that the
 * REST endpoint works according to it's specified behaviour.
 * <p>
 * Application {@code jeedemo-jaxrs} must be up and running in a remote
 * application server before you can run this test.
 * </p>
 * 
 * @author theism
 * @version 1.0
 * @since 10.03.2017
 */
public class DependencyInjectionSystemTest {

	@Test
	public void helloReturnsHtmlWelcomeMessage() {
		String responsePayload = ClientBuilder.newClient().target(new RestEndpointUriBuilder("injections").build())
				.request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals("successfully retrieved data from injected repository!", responsePayload);
	}

}
