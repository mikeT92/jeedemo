/*
 * Task.java
 * jeedemo-ejb
 */
package hm.edu.cs.fwp.jeedemo.ejb.ecb.entity;

/**
 * {@code Entity}, die einen Task repräsentiert.
 * @author theism
 */
public class Task {
	
	private String id;
	
	private String subject;
	
	private String description;
	
	private Status status = Status.UNDEFINED;

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

	public String getId() {
		return id;
	}

	public Status getStatus() {
		return this.status;
	}

	public void submit(String taskId) {
		this.id = taskId;
		this.status = Status.OPEN;
	}
}
