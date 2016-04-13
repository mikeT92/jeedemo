/*
 * MockAlternativeServiceBean.java
 * jeedemo-cdi
 */
package edu.hm.cs.fwp.jeedemo.cdi.alternatives.impl;

import javax.enterprise.inject.Alternative;
import javax.inject.Named;

import edu.hm.cs.fwp.jeedemo.cdi.alternatives.AlternativeService;

/**
 * Mock-Implementierung von {@link AlternativeService}. 
 * 
 * @author theism
 * @version 1.0
 * @since Release 2016.1
 */
@Named
@Alternative
public class MockAlternativeServiceBean implements AlternativeService {

	/** 
	 * @see edu.hm.cs.fwp.jeedemo.cdi.alternatives.AlternativeService#doSomething(java.lang.String)
	 */
	@Override
	public String doSomething(String what) {
		return "done " + what + " by " + getClass().getSimpleName();
	}
}
