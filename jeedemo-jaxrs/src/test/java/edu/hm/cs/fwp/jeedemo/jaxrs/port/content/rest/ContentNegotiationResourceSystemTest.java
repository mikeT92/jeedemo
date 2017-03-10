/*
 * jeedemo-jaxrs:ContentNegotiationResourceSystemTest.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.jaxrs.port.content.rest;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.Before;
import org.junit.Test;

import edu.hm.cs.fwp.jeedemo.jaxrs.test.rest.RestEndpointUriBuilder;

/**
 * {@code System Test} on {@link ContentNegotiationResource} to verify that the
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
public class ContentNegotiationResourceSystemTest {

	private Client restClient;
	private WebTarget restTarget;

	@Before
	public void onBefore() {
		Configuration clientConfig = new ClientConfig().register(LoggingFilter.class);
		this.restClient = ClientBuilder.newClient(clientConfig);
		this.restTarget = this.restClient.target(new RestEndpointUriBuilder("contentTypes").build());
	}

	@Test
	public void getAnyMediaTypeEntityReturnsFirstProducedMediaType() {
		Response response = this.restTarget.path("1234").request().get();
		assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
		System.out.println(response.readEntity(String.class));
	}

	@Test
	public void getJsonEntityReturnsJsonPayload() {
		Response response = this.restTarget.path("1234").request().accept(MediaType.APPLICATION_JSON).get();
		assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
		System.out.println(response.readEntity(String.class));
	}

	@Test
	public void getXmlEntityReturnsXmlPayload() {
		Response response = this.restTarget.path("1234").request().accept(MediaType.APPLICATION_XML).get();
		assertEquals(MediaType.APPLICATION_XML_TYPE, response.getMediaType());
		System.out.println(response.readEntity(String.class));
	}

	@Test
	public void getUnsupportedMediaTypeReturns406() {
		Response response = this.restTarget.path("1234").request().accept(MediaType.TEXT_PLAIN).get();
		assertEquals(406, response.getStatus());
		System.out.println(response.getStatus() + " " + response.getStatusInfo());
	}

}
