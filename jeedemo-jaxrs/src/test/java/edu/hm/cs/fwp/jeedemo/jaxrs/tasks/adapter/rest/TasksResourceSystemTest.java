/*
 * jeedemo-jaxrs:TasksResourceSystemTest.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.jaxrs.tasks.adapter.rest;

import static org.junit.Assert.*;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;
import org.junit.Before;
import org.junit.Test;

import edu.hm.cs.fwp.jeedemo.jaxrs.common.test.rest.RestEndpointUriBuilder;
import edu.hm.cs.fwp.jeedemo.jaxrs.tasks.core.entity.Task;
import edu.hm.cs.fwp.jeedemo.jaxrs.tasks.core.entity.TaskCategory;
import edu.hm.cs.fwp.jeedemo.jaxrs.tasks.core.entity.TaskLifeCycleState;
import edu.hm.cs.fwp.jeedemo.jaxrs.tasks.core.entity.TaskPriority;

/**
 * {@code System Test} on {@link TasksResource} to verify that the REST endpoint
 * works according to it's specified behaviour.
 * <p>
 * Application {@code jeedemo-jaxrs} must be up and running in a remote
 * application server before you can run this test.
 * </p>
 * 
 * @author theism
 * @version 1.1
 * @since release 2018
 */
public class TasksResourceSystemTest {

	private final Logger logger = Logger.getLogger(getClass().getName());

	private Client restClient;
	private WebTarget restTarget;

	@Before
	public void onBefore() {
		// TODO: re-integrate client side logging
		Configuration clientConfig = new ClientConfig();
		this.restClient = ClientBuilder.newClient().register(LoggingFeature.class);
		this.logger.fine("trying to get the request/response logging working!");
		// this.restClient =
		// this.restClient.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT,
		// "FINE")
		// .property(LoggingFeature.LOGGING_FEATURE_LOGGER_NAME_CLIENT,
		// this.logger.getName());
		this.restTarget = this.restClient.target(new RestEndpointUriBuilder("tasks").build());
	}

	@Test
	public void postNewTaskWithValidTaskReturns201AndLocationOfNewTask() {
		Task newTask = createTask();
		UriBuilder targetUri = new RestEndpointUriBuilder("tasks").build();
		Response response = this.restTarget.request().post(Entity.json(newTask));
		assertEquals(201, response.getStatus());
		assertNotNull("POST with new resource must return location", response.getLocation());
		assertTrue("Location of new resource must start with request URI [" + targetUri.build() + "]",
				response.getLocation().toString().startsWith(targetUri.build().toString()));
	}

	@Test
	public void getTaskWithValidTaskIdReturnsTask() {
		Task newTask = createTask();
		Response response = this.restTarget.request().post(Entity.json(newTask));
		String locationText = response.getLocation().toString();
		long newTaskId = Long.parseLong(locationText.substring(locationText.lastIndexOf('/') + 1));
		Task foundTask = this.restClient.target(response.getLocation()).request(MediaType.APPLICATION_JSON)
				.get(Task.class);
		assertNotNull(foundTask);
		assertEquals(newTaskId, foundTask.getId());
	}

	@Test
	public void getTasksWithInvalidTaskIdReturns404() {
		Response response = this.restTarget.path("999").request(MediaType.APPLICATION_JSON).get();
		assertNotNull(response);
		assertEquals(404, response.getStatus());
	}

	@Test
	public void putModifiedTaskReturns204() {
		Task newTask = createTask();
		String currentUserId = System.getProperty("user.name", "mtheis");
		Response response = this.restTarget.request().post(Entity.json(newTask));
		String locationText = response.getLocation().toString();
		long newTaskId = Long.parseLong(locationText.substring(locationText.lastIndexOf('/') + 1));
		Task existingTask = this.restTarget.path(Long.toString(newTaskId)).request().get(Task.class);
		existingTask.setLifeCycleState(TaskLifeCycleState.CLOSED_COMPLETED);
		existingTask.setCompletedByUserId(currentUserId);
		existingTask.setCompletionDate(Date.from(ZonedDateTime.now().toInstant()));
		existingTask.setCompletionRate(100);
		response = this.restTarget.path(Long.toString(existingTask.getId())).request().put(Entity.json(existingTask));
		assertEquals(204, response.getStatus());
	}

	@Test
	public void deleteExistingTaskReturns204() {
		Task newTask = createTask();
		Response response = this.restTarget.request().post(Entity.json(newTask));
		String locationText = response.getLocation().toString();
		long newTaskId = Long.parseLong(locationText.substring(locationText.lastIndexOf('/') + 1));
		Task existingTask = this.restTarget.path(Long.toString(newTaskId)).request().get(Task.class);
		response = this.restTarget.path(Long.toString(existingTask.getId())).request().delete();
		assertEquals(204, response.getStatus());
		response = this.restTarget.path(Long.toString(existingTask.getId())).request().get();
		assertEquals(404, response.getStatus());
	}

	@Test
	public void deleteNonExistingTaskReturns404() {
		Response response = this.restTarget.path(Long.toString(999)).request().delete();
		assertEquals(404, response.getStatus());
	}

	@Test
	public void getAllTasksReturns200WithListOfTasks() {
		Response response = this.restTarget.request().get();
		assertEquals(200, response.getStatus());
		List<Task> allTasks = response.readEntity(new GenericType<List<Task>>() {
		});
		assertNotNull(allTasks);
		assertFalse(allTasks.isEmpty());
	}

	private Task createTask() {
		ZonedDateTime now = ZonedDateTime.now();
		Date nowAsDate = Date.from(now.toInstant());
		String currentUserId = System.getProperty("user.name", "mtheis");
		Task result = new Task();
		result.setAffectedApplicationId("jeedemo");
		result.setAffectedModule("jeedemo-jaxrs");
		result.setAffectedProjectId("FWPSS2017");
		result.setCategory(TaskCategory.NEW_FEATURE);
		result.setDescription("description: system test " + now);
		result.setPriority(TaskPriority.MEDIUM);
		result.setResponsibleUserId(currentUserId);
		result.setSubject("subject: system test " + now);
		result.setSubmissionDate(nowAsDate);
		result.setSubmitterUserId(currentUserId);
		return result;
	}
}
