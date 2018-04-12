/**
 * 
 */
package hm.edu.cs.fwp.jeedemo.ejb.ecb.boundary;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import hm.edu.cs.fwp.jeedemo.ejb.common.test.ComponentTestArtifacts;
import hm.edu.cs.fwp.jeedemo.ejb.ecb.control.TaskIdGeneratorBean;
import hm.edu.cs.fwp.jeedemo.ejb.ecb.entity.Status;
import hm.edu.cs.fwp.jeedemo.ejb.ecb.entity.Task;

/**
 * Komponententest für den {@link TaskProcessor}.
 * 
 * @author theism
 */
@RunWith(Arquillian.class)
public class TaskProcessorBeanComponentTest {

	@Inject
	TaskProcessor underTest;

	/**
	 * Erstellt das zu deployende EJB-Modul mit allen für den Test erforderlichen
	 * Klassen und Ressourcen.
	 */
	@Deployment
	public static WebArchive createDeployment() {
		WebArchive result = ShrinkWrap.create(WebArchive.class).addClass(TaskProcessor.class)
				.addClass(TaskProcessorBean.class).addClass(TaskIdGeneratorBean.class).addClass(Task.class)
				.addClass(Status.class);
		ComponentTestArtifacts.attachToDeployment(result);
		return result;
	}

	/**
	 * Test method for
	 * {@link hm.edu.cs.fwp.jeedemo.ejb.ecb.boundary.TaskProcessorBean#submitTask(java.lang.String, hm.edu.cs.fwp.jeedemo.ejb.ecb.entity.Task)}.
	 */
	@Test
	public void testSubmitTask() {
		final String PROJECT_ID = "FWP";
		Task newTask = new Task();
		newTask.setSubject("Test");
		newTask.setDescription("Test");
		this.underTest.submitTask(PROJECT_ID, newTask);
		assertNotNull(newTask.getId());
		assertEquals(Status.OPEN, newTask.getStatus());
	}

}
