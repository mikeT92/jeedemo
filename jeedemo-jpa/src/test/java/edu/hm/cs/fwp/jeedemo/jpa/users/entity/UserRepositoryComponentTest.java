/*
 * UserModelComponentTest.java
 * jeedemo-jpa
 */
package edu.hm.cs.fwp.jeedemo.jpa.users.entity;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Komponententest zum Testen des JPA-Mappings des User-Models.
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
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class)//
				.addClass(Credential.class)//
				.addClass(Gender.class)//
				.addClass(Role.class)//
				.addClass(Roles.class)//
				.addClass(User.class)//
				.addClass(UserRepositoryBean.class)//
				.addAsWebInfResource("arquillian-beans.xml", "beans.xml")//
				.addAsManifestResource("arquillian-persistence.xml", "persistence.xml");
	}
	
	@Inject
	UserRepositoryBean repository;
	
	@Test
	public void testGetUserByIdWithUserNotExist() {
		User user = this.repository.getUserById("UNKNOWN");
		assertNull(user);
	}
}
