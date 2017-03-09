/* Consumer.java @(#)%PID%
 */
package edu.hm.cs.fwp.jeedemo.cdi.injection;

/**
 * Simple service consumer to demonstrate CDI-features.
 * 
 * @author theism
 * @version 1.0
 * @since release 2016.1
 */
public interface Consumer {

	public String useService(String what);
}