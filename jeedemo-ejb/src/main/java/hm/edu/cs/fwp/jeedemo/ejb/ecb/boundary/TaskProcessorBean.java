/*
 * TaskProcessorBean.java
 * jeedemo-ejb
 */
package hm.edu.cs.fwp.jeedemo.ejb.ecb.boundary;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import hm.edu.cs.fwp.jeedemo.ejb.ecb.control.TaskIdGeneratorBean;
import hm.edu.cs.fwp.jeedemo.ejb.ecb.entity.Task;

/**
 * Implementierung der Boundary als Stateless Session Bean.
 * <p>
 * Implementiert das Business Interface {@link TaskProcessor}, was damit automatisch
 * als Local Business Interface des Stateless Session Beans betrachtet wird.
 * </p>
 * @author theism
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class TaskProcessorBean implements TaskProcessor {

	@Inject 
	TaskIdGeneratorBean taskIdGenerator;
	
	/** 
	 * @see hm.edu.cs.fwp.jeedemo.ejb.ecb.boundary.TaskProcessor#submitTask(java.lang.String, hm.edu.cs.fwp.jeedemo.ejb.ecb.entity.Task)
	 */
	@Override
	public void submitTask(String projectId, Task newTask) {
		String taskId = this.taskIdGenerator.nextTaskId(projectId);
		newTask.submit(taskId);
	}
}
