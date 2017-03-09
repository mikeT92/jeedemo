/*
 * TaskProcessor.java
 * jeedemo-ejb
 */
package hm.edu.cs.fwp.jeedemo.ejb.ecb.boundary;

import hm.edu.cs.fwp.jeedemo.ejb.ecb.entity.Task;

/**
 * Business interfaces einer TaskProcessor {@code Boundary}
 * @author theism
 */
public interface TaskProcessor {

	/**
	 * Stellt einen neuen Task für das angegebene Projekt ein.
	 * @param projectId
	 * @param newTask
	 */
	void submitTask(String projectId, Task newTask);
}