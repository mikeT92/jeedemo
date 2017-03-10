/*
 * jeedemo-jaxrs:ContentNegotiationResource.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.jaxrs.port.content.rest;

import java.time.ZonedDateTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * {@code REST Resource} demonstrating content negotiation.
 * 
 * @author theism
 * @version 1.0
 * @since 10.03.2017
 */
@Path("contentTypes")
public class ContentNegotiationResource {

	@GET
	@Path("{entityId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Entity getEntityById(@PathParam("entityId") long entityId) {
		return buildEntity(entityId);
	}

	private Entity buildEntity(long entityId) {
		ZonedDateTime now = ZonedDateTime.now();
		return new Entity(entityId, "Entity" + entityId, "system test " + now);
	}

}
