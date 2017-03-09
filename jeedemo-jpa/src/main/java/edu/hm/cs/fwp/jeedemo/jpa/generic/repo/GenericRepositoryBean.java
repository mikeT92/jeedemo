/*
 * GenericRepositoryBean.java
 * jeedemo-jpa
 */
package edu.hm.cs.fwp.jeedemo.jpa.generic.repo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.hm.cs.fwp.jeedemo.jpa.core.persistence.AbstractGenericRepository;

/**
 * Konkrete Implementierung eines generischen Repositories.
 * 
 * @author theism
 * @version 1.0
 * @since Release 2016.1
 */
@Stateless
public class GenericRepositoryBean extends AbstractGenericRepository {

	/**
	 * Konkreter PersistenceContext für eine bestimmte PersistenceUnit
	 */
	@PersistenceContext
	EntityManager entityManager;
	
	/** 
	 * @see edu.hm.cs.fwp.jeedemo.jpa.core.persistence.AbstractGenericRepository#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}
}
