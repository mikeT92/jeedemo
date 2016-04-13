/* Service.java
 */
package edu.hm.cs.fwp.jeedemo.cdi.interceptor;

/**
 * Einfacher Service, der über CDI in alle Nutzer injiziert wird.
 * 
 * @author theism
 * @version 1.0
 * @since Release 2016.1
 */
public interface Service {

	/**
	 * Simple Servicemethode.
	 *
	 * @param what Teilt der Methode mit, was zu tun ist.
	 * @return Ergebnis der Verarbeitung
	 */
	String doSomething(String what);
}