/*
 * jeedemo-jaxrs:TaskRepositoryBean.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.jaxrs.port.tasks.persistence;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.Id;

import edu.hm.cs.fwp.jeedemo.jaxrs.core.tasks.entity.Task;

/**
 * {@Repository} mock persisting Task entities in an internal map.
 * 
 * @author theism
 * @version 1.0
 * @since 09.03.2017
 */
@Named
@Singleton
public class TaskRepositoryBean {

	private final ConcurrentHashMap<Long, Task> entityStore = new ConcurrentHashMap<>();

	private final AtomicLong taskIdGenerator = new AtomicLong(1000);

	public long addTask(Task newTask) {
		setTaskId(newTask, generateTaskId(newTask));
		this.entityStore.putIfAbsent(newTask.getId(), newTask);
		return newTask.getId();
	}

	public Task getTask(long taskId) {
		return this.entityStore.get(taskId);
	}

	public void setTask(Task modifiedTask) {
		this.entityStore.replace(modifiedTask.getId(), modifiedTask);
	}

	public void removeTask(Task existingTask) {
		this.entityStore.remove(existingTask.getId());
	}

	public List<Task> findAllTasks() {
		return new ArrayList<>(this.entityStore.values());
	}

	public List<Task> findTasksByResponsible(String responsibleUserId) {
		List<Task> result = new ArrayList<>();
		for (Task current : this.entityStore.values()) {
			if (responsibleUserId.equals(current.getResponsibleUserId())) {
				result.add(current);
			}
		}
		return result;
	}

	private void setTaskId(Task task, long taskId) {
		Field[] fields = task.getClass().getDeclaredFields();
		for (Field current : fields) {
			if (current.isAnnotationPresent(Id.class)) {
				current.setAccessible(true);
				try {
					current.setLong(task, taskId);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new IllegalStateException(
							String.format("Unable to set value of @Id field [%s] of entity type [%s]: %s",
									current.getName(), current.getDeclaringClass().getName(), e.getMessage()),
							e);
				}
				break;
			}
		}
	}

	private long generateTaskId(Task task) {
		return this.taskIdGenerator.incrementAndGet();
	}
}
