/*
 * Task.java
 * jeedemo-ejb
 */
package hm.edu.cs.fwp.jeedemo.ejb.ecb.entity;

/**
 * TODO: dokumentieren !!!
 * @author theism
 *
 */
public class Task {
	
	private String id;
	
	private String subject;
	
	private String description;
	
	private Status status;

	public void submit(String taskId) {
		this.id = taskId;
		this.status = Status.OPEN;
	}
}
