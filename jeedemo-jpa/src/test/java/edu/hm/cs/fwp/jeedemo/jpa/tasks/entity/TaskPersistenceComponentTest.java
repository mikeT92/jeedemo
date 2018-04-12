/*
 * TaskPersistenceComponentTest.java
 * jeedemo-jpa
 */
package edu.hm.cs.fwp.jeedemo.jpa.tasks.entity;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.hm.cs.fwp.jeedemo.jpa.common.test.ComponentTestArtifacts;
import edu.hm.cs.fwp.jeedemo.jpa.generic.repo.GenericRepository;

/**
 * Komponententest zum Testen des JPA-Mappings des User-Models.
 * 
 * @author theism
 * @version 1.0
 * @since Release 2016.1
 */
@RunWith(Arquillian.class)
public class TaskPersistenceComponentTest {
	/**
	 * Erstellt ein WAR mit allen für den Komponententest benötigten Klassen und
	 * Ressourcen.
	 */
	@Deployment
	public static WebArchive createDeployment() {
		WebArchive result = ShrinkWrap.create(WebArchive.class)//
				.addClass(Task.class)//
				.addClass(TaskCategory.class)//
				.addClass(TaskLifeCycleState.class)//
				.addClass(TaskPriority.class);
		ComponentTestArtifacts.addToDeployment(result);
		return result;
	}

	@Inject
	private GenericRepository underTest;

	/**
	 * Mülleimer für abzuräumende User.
	 */
	private final List<Task> trashBin = new ArrayList<>();

	@Before
	public void onBefore() {
	}

	@After
	public void onAfter() {
		for (Task current : trashBin) {
			try {
				this.underTest.removeEntity(current);
			} catch (Exception ex) {
				System.err.print("Unable to remove task [" + current.getId() + "]");
			}
		}
	}

	@Test
	public void testTaskByUnknownTaskIdReturnsNull() {
		Task found = this.underTest.getEntityById(Task.class, 0L);
		assertNull(found);
	}

	@Test
	public void testAddWithValidTaskAddsNewTaskToDatastore() {
		Task newEntity = createTask();
		this.underTest.addEntity(newEntity);
		this.trashBin.add(newEntity);
		Task persistentEntity = this.underTest.getEntityById(Task.class, newEntity.getId());
		assertNotNull("Repository.getEntityById() must return newly added task", persistentEntity);
		assertEquals("entity IDs must match", newEntity.getId(), persistentEntity.getId());
	}

	private Task createTask() {
		Task result = new Task();
		result.setSubject("test subject");
		result.setDescription("test description");
		result.setSubmittedAt(LocalDateTime.now());
		result.setSubmitterUserId("mtheis");
		return result;
	}
}
