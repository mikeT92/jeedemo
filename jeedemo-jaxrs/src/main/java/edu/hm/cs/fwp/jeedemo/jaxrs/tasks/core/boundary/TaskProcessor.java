/*
 * jeedemo-jaxrs:TaskProcessor.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.jaxrs.tasks.core.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.hm.cs.fwp.jeedemo.jaxrs.tasks.adapter.persistence.TaskRepository;
import edu.hm.cs.fwp.jeedemo.jaxrs.tasks.core.entity.Task;

/**
 * {@code Boundary} of a task processor.
 * 
 * @author theism
 * @version 1.0
 * @since 09.03.2017
 */
@Stateless
public class TaskProcessor {

	@Inject
	private TaskRepository taskRepository;

	public long createTask(Task newTask) {
		return this.taskRepository.addTask(newTask);
	}

	public void updateTask(Task modifiedTask) {
		this.taskRepository.setTask(modifiedTask);
	}

	public void deleteTask(Task existingTask) {
		this.taskRepository.removeTask(existingTask);
	}

	public Task readTask(long taskId) {
		return this.taskRepository.getTask(taskId);
	}

	public List<Task> readAllTasks() {
		return this.taskRepository.findAllTasks();
	}
}
