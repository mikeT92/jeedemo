/*
 * IReplaceableService.java
 * jeedemo-cdi
 */
package edu.hm.cs.fwp.jeedemo.cdi.alternatives;

/**
 * Einfacher Service, zu dem zur Laufzeit verschiedene Alternativen existieren 
 * können.
 * 
 * @author theism
 * @version 1.0
 * @since Release 2016.1
 */
public interface AlternativeService {

	/**
	 * Simple Servicemethode.
	 *
	 * @param what Teilt der Methode mit, was zu tun ist.
	 * @return Ergebnis der Verarbeitung
	 */
	String doSomething(String what);
}