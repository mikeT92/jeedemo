package edu.hm.cs.fwp.jeedemo.jpa.core.persistence;

import javax.persistence.Query;

/**
 * Repräsentiert eine Menge von SQL-Parameters, die an eine Query übergeben werden soll.
 * @author theism
 * @version 1.0
 * @since R2016.1 16.04.2015
 */
public interface QueryParameters {
    /**
     * Übergibt alle Paramter an die angegebene Query
     * 
     * @param query
     */
    void applyParameters(Query query);
}