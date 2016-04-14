/*
 * RoleRepositoryBean.java
 * jeedemo-jpa
 */
package edu.hm.cs.fwp.jeedemo.jpa.users.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * {@code Repository} für {@code Role}s.
 * 
 * @author theism
 * @version 1.0
 * @since Release 2016.1
 */
@Stateless
public class RoleRepositoryBean {

	@PersistenceContext
	EntityManager entityManager;
	
	/**
	 * Liefert alle Rollen über eine Named Query zurück.
	 *
	 * @return alle verfügbaren Rollen
	 */
	public List<Role> getAllRolesUsingNamedQuery() {
		TypedQuery<Role> query = this.entityManager.createNamedQuery(Role.QUERY_ALL, Role.class);
		return query.getResultList();
	}
	
	/**
	 * Liefert alle Rollen über ein dynamische Query mit einem JPQL-Statement zurück.
	 *
	 * @return alle verfügbaren Rollen
	 */
	public List<Role> getAllRoles() {
		TypedQuery<Role> query = this.entityManager.createQuery("SELECT r FROM Role r", Role.class);
		return query.getResultList();
	}
}
