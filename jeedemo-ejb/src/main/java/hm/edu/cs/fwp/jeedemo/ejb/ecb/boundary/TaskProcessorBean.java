/*
 * TaskProcessorBean.java
 * jeedemo-ejb
 */
package hm.edu.cs.fwp.jeedemo.ejb.ecb.boundary;

import javax.inject.Inject;

import hm.edu.cs.fwp.jeedemo.ejb.ecb.control.TaskIdGeneratorBean;
import hm.edu.cs.fwp.jeedemo.ejb.ecb.entity.Task;

/**
 * TODO: dokumentieren !!!
 * @author theism
 *
 */
public class TaskProcessorBean implements TaskProcessor {

	@Inject 
	TaskIdGeneratorBean taskIdGenerator;
	
	/** 
	 * TODO: Grund für das Überschreiben kommentieren !!!
	 *
	 * @see hm.edu.cs.fwp.jeedemo.ejb.ecb.boundary.TaskProcessor#submitTask(java.lang.String, hm.edu.cs.fwp.jeedemo.ejb.ecb.entity.Task)
	 */
	@Override
	public void submitTask(String projectId, Task newTask) {
		String taskId = this.taskIdGenerator.nextTaskId(projectId);
		newTask.submit(taskId);
	}
}
