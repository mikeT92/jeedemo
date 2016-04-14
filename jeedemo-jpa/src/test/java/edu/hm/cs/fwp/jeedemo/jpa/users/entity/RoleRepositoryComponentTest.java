/*
 * RoleRepositoryComponentTest.java
 * jeedemo-jpa
 */
package edu.hm.cs.fwp.jeedemo.jpa.users.entity;

import static org.junit.Assert.*;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Komponententest für {@link RoleRepositoryBean}.
 * @author theism
 * @version 1.0
 * @since Release 2016.1
 */
@RunWith(Arquillian.class)
public class RoleRepositoryComponentTest {
	/**
	 * Erstellt ein WAR mit allen für den Komponententest benötigten Klassen und
	 * Ressourcen.
	 */
	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "RoleRepositoryComponentTest.war")//
				.addClass(Role.class)//
				.addClass(RoleRepositoryBean.class)//
				.addAsWebInfResource("arquillian-beans.xml", "beans.xml")//
				// ACHTUNG: In WARs muss persistence.xml unter /WEB-INF/classes/META-INF stehen!!! 
				.addAsResource("arquillian-persistence.xml", "/META-INF/persistence.xml");
	}
	
	@EJB
	RoleRepositoryBean repository;
	
	@Test
	public void testGetAllRolesUsingNamedQueryReturnsAtLeastOneRole() {
		List<Role> roles = this.repository.getAllRolesUsingNamedQuery();
		assertNotNull(roles);
		assertFalse(roles.isEmpty());
	}
	
	@Test
	public void testGetAllRolesUsingDynamicQueryReturnsAtLeastOneRole() {
		List<Role> roles = this.repository.getAllRoles();
		assertNotNull(roles);
		assertFalse(roles.isEmpty());
	}
}
