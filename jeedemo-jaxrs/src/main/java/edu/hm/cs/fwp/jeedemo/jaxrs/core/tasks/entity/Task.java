/*
 * jeedemo-jaxrs:Task.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.jaxrs.core.tasks.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * {@code Entity} representing a task.
 * 
 * @author theism
 * @version 1.0
 * @since 09.03.2017
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Task implements Serializable {

	private static final long serialVersionUID = -2698983700648804968L;

	/**
	 * Unique identifier of this task.
	 */
	@Id
	private long id;

	/**
	 * Single-line description or summary of what this task is about.
	 */
	@Size(max = 80)
	private String subject;

	/**
	 * Detailed description of this task.
	 */
	@Size(max = 1024)
	private String description;

	/**
	 * Groups task into specific categories like "Bug", "Enhancement".
	 */
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private TaskCategory category = TaskCategory.UNDEFINED;

	/**
	 * Priority.
	 */
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private TaskPriority priority = TaskPriority.UNDEFINED;

	/**
	 * Status of this task.
	 */
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private TaskLifeCycleState lifeCycleState = TaskLifeCycleState.UNDEFINED;

	/**
	 * Date/time when this task has been requested.
	 * <p>
	 * Expected to be set when task lifeCycleState is <code>running</code>.
	 * </p>
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date submissionDate;

	/**
	 * User-ID of participant who submitted this task.
	 * <p>
	 * Expected to be set when task lifeCycleState is <code>completed</code>.
	 * </p>
	 */
	private String submitterUserId;

	/**
	 * Date/time when this task is supposed to be completed.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date dueDate;

	/**
	 * Completion rate in percent, ranges from 0 to 100.
	 */
	private int completionRate;

	/**
	 * Date/time when this task has been completed.
	 * <p>
	 * Expected to be set when task lifeCycleState is <code>completed</code>.
	 * </p>
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date completionDate;

	/**
	 * User-ID of participant who completed this task.
	 * <p>
	 * Expected to be set when task lifeCycleState is <code>completed</code>.
	 * </p>
	 */
	private String completedByUserId;

	/**
	 * User-ID of participant who is currently responsible for the completion of
	 * this task.
	 */
	@Size(max = 16)
	private String responsibleUserId;

	/**
	 * Project-ID of the project this task is related to.
	 * <p>
	 * Equals {@link #affectedApplicationId} if this is a application
	 * maintenance task not related to a specific project.
	 * </p>
	 */
	@Size(max = 16)
	private String affectedProjectId;

	/**
	 * Application-ID of the application this task is related to.
	 */
	@Size(max = 8)
	private String affectedApplicationId;

	/**
	 * Name of the logical module this task is related to.
	 */
	@Size(max = 32)
	private String affectedModule;

	/**
	 * Application resource that this task is referring to.
	 */
	@Size(max = 256)
	private String affectedResource;

	/**
	 * Estimated effort in hours to complete this task.
	 */
	private int estimatedEffort;

	/**
	 * Actual effort in hours to complete this task.
	 */
	private int actualEffort;

	/**
	 * Current version of this instance (used for optimistic locking).
	 */
	@Version
	private int version;

	/**
	 * User ID of the user who created this entity.
	 */
	@Size(max = 16)
	private String creatorId;

	/**
	 * Date/timer when this entity was created.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	/**
	 * User ID of the user who modified this entity.
	 */
	@Size(max = 16)
	private String lastModifierId;

	/**
	 * Date/time this entity was modified.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModificationDate;

	public long getId() {
		return id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskCategory getCategory() {
		return category;
	}

	public void setCategory(TaskCategory category) {
		this.category = category;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	public TaskLifeCycleState getLifeCycleState() {
		return lifeCycleState;
	}

	public void setLifeCycleState(TaskLifeCycleState state) {
		this.lifeCycleState = state;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date startDate) {
		this.submissionDate = startDate;
	}

	public String getSubmitterUserId() {
		return submitterUserId;
	}

	public void setSubmitterUserId(String requesterUserId) {
		this.submitterUserId = requesterUserId;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public int getCompletionRate() {
		return completionRate;
	}

	public void setCompletionRate(int completionRate) {
		this.completionRate = completionRate;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public String getCompletedByUserId() {
		return completedByUserId;
	}

	public void setCompletedByUserId(String completedById) {
		this.completedByUserId = completedById;
	}

	public String getResponsibleUserId() {
		return responsibleUserId;
	}

	public void setResponsibleUserId(String responsibleId) {
		this.responsibleUserId = responsibleId;
	}

	public String getAffectedProjectId() {
		return affectedProjectId;
	}

	public void setAffectedProjectId(String affectedProjectId) {
		this.affectedProjectId = affectedProjectId;
	}

	public String getAffectedApplicationId() {
		return affectedApplicationId;
	}

	public void setAffectedApplicationId(String affectedApplicationId) {
		this.affectedApplicationId = affectedApplicationId;
	}

	public String getAffectedModule() {
		return affectedModule;
	}

	public void setAffectedModule(String affectedModule) {
		this.affectedModule = affectedModule;
	}

	public String getAffectedResource() {
		return affectedResource;
	}

	public void setAffectedResource(String affectedResource) {
		this.affectedResource = affectedResource;
	}

	public int getEstimatedEffort() {
		return estimatedEffort;
	}

	public void setEstimatedEffort(int estimatedEffort) {
		this.estimatedEffort = estimatedEffort;
	}

	public int getActualEffort() {
		return actualEffort;
	}

	public void setActualEffort(int actualEffort) {
		this.actualEffort = actualEffort;
	}

	public int getVersion() {
		return version;
	}

	public String getCreatorId() {
		return this.creatorId;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public String getLastModifierId() {
		return this.lastModifierId;
	}

	public Date getLastModificationDate() {
		return this.lastModificationDate;
	}

	public void trackModification(String lastModifierId, Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
		this.lastModifierId = lastModifierId;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + " { id : " + this.id + ", version : " + this.version + " }";
	}
}
