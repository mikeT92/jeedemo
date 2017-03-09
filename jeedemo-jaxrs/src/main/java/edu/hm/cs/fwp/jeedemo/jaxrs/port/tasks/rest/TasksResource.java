/*
 * jeedemo-jaxrs:TasksResource.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.jaxrs.port.tasks.rest;

import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import edu.hm.cs.fwp.jeedemo.jaxrs.core.tasks.boundary.TaskProcessorBean;
import edu.hm.cs.fwp.jeedemo.jaxrs.core.tasks.entity.Task;

/**
 * {@code REST Resource} for task processing.
 * <p>
 * Acts as an {@code Adapter} to {@code Boundary} {@link TaskProcessorBean} that
 * maps REST requests to business method invocations to keep REST protocol logic
 * separate from business logic.
 * </p>
 * 
 * @author theism
 * @version 1.0
 * @since 08.03.2017
 */
@Stateless
@Path("tasks")
public class TasksResource {

	@Context
	private UriInfo uriInfo;

	@Inject
	private TaskProcessorBean taskProcessor;

	/**
	 * Returns the task with the specified task id.
	 * 
	 * @param taskId
	 *            task ID specified as last component of request path
	 * @return HTTP status code 200 plus Task as payload, if the task could be
	 *         found; otherwise HTTP status code 404 without any payload
	 */
	@GET
	@Path("{taskId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getTaskById(@PathParam("taskId") long taskId) {
		Response result = null;
		Task payload = this.taskProcessor.readTask(taskId);
		if (payload != null) {
			result = Response.ok(payload).build();
		} else {
			result = Response.status(Status.NOT_FOUND).build();
		}
		return result;
	}

	/**
	 * Creates a new task and returns the task ID of the newly created task.
	 * 
	 * @param newTask
	 *            new task unmarshalled from the request payload
	 * @return HTTP status code 201 plus response header {@code Location}
	 *         containing the URI of the newly created task, if the task could
	 *         be created successfully; otherwise HTTP status code 400.
	 * 
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createTask(Task newTask) {
		Response result = null;
		long newEntityId = this.taskProcessor.createTask(newTask);
		URI newEntityLocation = this.uriInfo.getAbsolutePathBuilder().path(Long.toString(newEntityId)).build();
		result = Response.created(newEntityLocation).build();
		return result;
	}

	/**
	 * Updates an existing task identified by the given task ID with the
	 * modified task data provided as payload.
	 * 
	 * @param taskId
	 *            task ID specified as last component of request path
	 * @param modifiedTask
	 *            modified task data unmarshalled from payload
	 * @return HTTP status code 204 (NO CONTENT), if the task could be found and
	 *         successfully updated; otherwise HTTP status code 400, if the task
	 *         data is not valid or HTTP status code 404, if the specified task
	 *         could not be found.
	 */
	@PUT
	@Path("{taskId}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateTask(@PathParam("taskId") long taskId, Task modifiedTask) {
		Response result = null;
		if (taskId == modifiedTask.getId()) {
			this.taskProcessor.updateTask(modifiedTask);
			result = Response.noContent().build();
		} else {
			result = Response.status(Status.BAD_REQUEST).build();
		}
		return result;
	}

	/**
	 * Removes an existing task identified by the given task ID.
	 * 
	 * @param taskId
	 *            task ID specified as last component of request path
	 * @return HTTP status code 204 (NO CONTENT), if the task could be found and
	 *         successfully deleted; otherwise HTTP status code 400, if the task
	 *         cannot be deleted or HTTP status code 404, if the specified task
	 *         could not be found.
	 */
	@DELETE
	@Path("{taskId}")
	public Response deleteTask(@PathParam("taskId") long existingTaskId) {
		Response result = null;
		Task existingTask = this.taskProcessor.readTask(existingTaskId);
		if (existingTask != null) {
			this.taskProcessor.deleteTask(existingTask);
			result = Response.noContent().build();
		} else {
			result = Response.status(Status.NOT_FOUND).build();
		}
		return result;
	}

	/**
	 * Returns all existing tasks.
	 * 
	 * @return HTTP Status code 200 plus list of tasks as payload.
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllTasks() {
		List<Task> entities = this.taskProcessor.readAllTasks();
		GenericEntity<List<Task>> genericEntities = new GenericEntity<List<Task>>(entities) {
		};
		return Response.ok(genericEntities).build();
	}
}
