/*
 * TaskProcessor.java
 * jeedemo-ejb
 */
package hm.edu.cs.fwp.jeedemo.ejb.ecb.boundary;

import hm.edu.cs.fwp.jeedemo.ejb.ecb.entity.Task;

/**
 * TODO: dokumentieren !!!
 * @author theism
 *
 */
public interface TaskProcessor {

	void submitTask(String projectId, Task newTask);

}