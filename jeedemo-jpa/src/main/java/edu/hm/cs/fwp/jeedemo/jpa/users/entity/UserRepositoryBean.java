/*
 * UserRespositoryBean.java
 * jeedemo-jpa
 */
package edu.hm.cs.fwp.jeedemo.jpa.users.entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	 * Fügt den angegebenen neuen User hinzu.
	 *
	 * @param newUser
	 */
	public void addUser(User newUser) {
		this.entityManager.persist(newUser);
		this.entityManager.flush();
		this.entityManager.refresh(newUser);
	}

	/**
	 * Liest einen einzelnen User über dessen User-ID.
	 * 
	 * @param userId
	 *            User-ID des gesuchten Users
	 * @return gefundener User, falls ein User mit der angegebenen ID gefunden
	 *         werden kann; sonst {@code null}.
	 */
	public User getUserById(String userId) {
		return this.entityManager.find(User.class, userId);
	}

	/**
	 * Löscht den angegebenen Benutzer.
	 */
	public void removeUser(User user) {
		User mergedUser = this.entityManager.merge(user);
		this.entityManager.remove(mergedUser);
	}

	/**
	 * Löscht den Benutzer mit der angegebenen User-ID.
	 */
	public void removeUserById(String userId) {
		User user = this.entityManager.getReference(User.class, userId);
		this.entityManager.remove(user);
	}
}
