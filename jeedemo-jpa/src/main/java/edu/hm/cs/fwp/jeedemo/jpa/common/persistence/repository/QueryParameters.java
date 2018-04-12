package edu.hm.cs.fwp.jeedemo.jpa.common.persistence.repository;

import javax.persistence.Query;

/**
 * Represents a set of query parameters that are supposed to be passed to a
 * {@link Query}.
 *
 * @author Michael Theis (msg)
 * @version 1.0
 * @since release 18.2
 */
public interface QueryParameters {
	/**
	 * Applies all internally stored parameters to the given {@code Query}.
	 *
	 * @param query
	 *            JPA Query
	 */
	void applyParameters(Query query);
}