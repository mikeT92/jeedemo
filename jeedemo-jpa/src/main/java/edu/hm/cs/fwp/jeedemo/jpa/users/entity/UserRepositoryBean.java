/*
 * UserRespositoryBean.java
 * jeedemo-jpa
 */
package edu.hm.cs.fwp.jeedemo.jpa.users.entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * {@code Repository} für {@link User}.
 * <p>
 * Kapselt alle Zugriffsmethoden für User-Entitäten.
 * </p>
 * 
 * @author theism
 * @version 1.0
 * @since Release 2016.1
 */
@Stateless
public class UserRepositoryBean {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Liest einen einzelnen User über dessen User-ID.
	 */
	public User getUserById(String userId) {
		TypedQuery<User> query = this.entityManager.createNamedQuery(User.QUERY_BY_ID, User.class);
		query.setParameter("userId", userId);
		return query.getSingleResult();
	}
}
