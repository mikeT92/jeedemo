/*
 * jeedemo-jaxrs:TaskProcessorBean.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.jaxrs.core.tasks.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.hm.cs.fwp.jeedemo.jaxrs.core.tasks.entity.Task;
import edu.hm.cs.fwp.jeedemo.jaxrs.port.tasks.persistence.TaskRepositoryBean;

/**
 * {@code Boundary} of a task processor.
 * 
 * @author theism
 * @version 1.0
 * @since 09.03.2017
 */
@Stateless
public class TaskProcessorBean {

	@Inject
	private TaskRepositoryBean taskRepository;

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
