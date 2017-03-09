/*
 * UserRepositoryComponentTest.java
 * jeedemo-jpa
 */
package edu.hm.cs.fwp.jeedemo.jpa.users.entity;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Komponententest zum Testen des JPA-Mappings des User-Models.
 * 
 * @author theism
 * @version 1.0
 * @since Release 2016.1
 */
@RunWith(Arquillian.class)
public class UserRepositoryComponentTest {
	/**
	 * Erstellt ein WAR mit allen für den Komponententest benötigten Klassen und
	 * Ressourcen.
	 */
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)//
				.addClass(Credential.class)//
				.addClass(Gender.class)//
				.addClass(Role.class)//
				.addClass(RoleRepositoryBean.class)//
				.addClass(User.class)//
				.addClass(UserRepositoryBean.class)//
				.addAsManifestResource("arquillian-beans.xml", "beans.xml")//
				.addAsManifestResource("arquillian-persistence.xml", "persistence.xml");
	}

	@Inject
	UserRepositoryBean underTest;

	@Inject
	RoleRepositoryBean roleRepository;

	/**
	 * Liste der verfügbaren Rollen
	 */
	private List<Role> availableRoles;

	/**
	 * Mülleimer für abzuräumende User.
	 */
	private final List<User> trashBin = new ArrayList<>();

	@Before
	public void onBefore() {
		if (this.availableRoles == null) {
			this.availableRoles = this.roleRepository.getAllRolesUsingNamedQuery();
		}
	}

	@After
	public void onAfter() {
		for (User current : trashBin) {
			try {
				this.underTest.removeUser(current);
			} catch (Exception ex) {
				System.err.print("Unable to remove user [" + current.getId() + "]");
			}
		}
	}

	@Test
	public void testGetUserByIdWithUserNotExistReturnsNull() {
		User user = this.underTest.getUserById("UNKNOWN");
		assertNull(user);
	}

	@Test
	public void testAddUserWithValidUserAddsNewUserToDatastore() {
		User newUser = createUser();
		this.underTest.addUser(newUser);
		this.trashBin.add(newUser);
		User persistentUser = this.underTest.getUserById(newUser.getId());
		assertNotNull("UserRepositoryBean.getUserById() must return added user", persistentUser);
		assertEquals("User.id's must match", newUser.getId(), persistentUser.getId());
	}

	private User createUser() {
		User result = new User();
		result.setId(generateUserId());
		result.setFirstName("Karl");
		result.setLastName("Mustermann");
		result.setFullName("Karl Mustermann");
		result.setDateOfBirth(new GregorianCalendar(1965, 0, 13).getTime());
		result.setGender(Gender.MALE);
		result.setEmail("karl.mustermann@hm.edu");
		result.getRoles().add(this.availableRoles.get(0));
		return result;
	}

	private String generateUserId() {
		String result = "fwp" + (10000L + System.nanoTime() % 10000L);
		System.out.println(result);
		return result;
	}
}
