/*
 * DefaultServiceBean.java
 * jeedemo-cdi
 */
package edu.hm.cs.fwp.jeedemo.cdi.alternatives.impl;

import javax.inject.Named;

import edu.hm.cs.fwp.jeedemo.cdi.alternatives.AlternativeService;

/**
 * Default-Implementierung von Einfacher Service, zu dem zur Laufzeit verschiedene Alternativen existieren 
 * können.
 * 
 * @author theism
 * @version 1.0
 * @since Release 2016.1
 */
@Named
public class DefaultAlternativeServiceBean implements AlternativeService {

	/** 
	 * @see edu.hm.cs.fwp.jeedemo.cdi.alternatives.AlternativeService#doSomething(java.lang.String)
	 */
	@Override
	public String doSomething(String what) {
		return "done " + what + " by " + getClass().getSimpleName();
	}
}
