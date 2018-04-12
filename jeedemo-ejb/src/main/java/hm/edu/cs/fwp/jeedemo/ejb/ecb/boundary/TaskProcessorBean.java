/*
 * TaskProcessorBean.java
 * jeedemo-ejb
 */
package hm.edu.cs.fwp.jeedemo.ejb.ecb.boundary;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import hm.edu.cs.fwp.jeedemo.ejb.ecb.control.TaskIdGeneratorBean;
import hm.edu.cs.fwp.jeedemo.ejb.ecb.entity.Task;
import hm.edu.cs.fwp.jeedemo.ejb.interceptors.TraceInterceptor;

/**
 * Implementierung der Boundary als Stateless Session Bean.
 * <p>
 * Implementiert das Business Interface {@link TaskProcessor}, was damit
 * automatisch als Local Business Interface des Stateless Session Beans
 * betrachtet wird.
 * </p>
 * 
 * @author theism
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@RolesAllowed({ "JEEDEMO_USER" })
@Interceptors({ TraceInterceptor.class })
public class TaskProcessorBean implements TaskProcessor {

	@Inject
	TaskIdGeneratorBean taskIdGenerator;

	/**
	 * @see hm.edu.cs.fwp.jeedemo.ejb.ecb.boundary.TaskProcessor#submitTask(java.lang.String,
	 *      hm.edu.cs.fwp.jeedemo.ejb.ecb.entity.Task)
	 */
	@Override
	public void submitTask(String projectId, Task newTask) {
		String taskId = this.taskIdGenerator.nextTaskId(projectId);
		newTask.submit(taskId);
	}
}
