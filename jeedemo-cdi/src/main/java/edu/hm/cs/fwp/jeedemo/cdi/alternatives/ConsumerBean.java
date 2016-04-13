/*
 * ConsumerBean.java
 * jeedemo-cdi
 */
package edu.hm.cs.fwp.jeedemo.cdi.alternatives;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Konsument (Nutzer) eines Services, von dem zur Laufzeit verschiedene
 * Alternativen existieren können.
 * 
 * @author theism
 * @version 1.0
 * @since Release 2016.1
 */
@Named
public class ConsumerBean {

	@Inject
	private AlternativeService service;

	public String useService(String what) {
		return this.service.doSomething(what);
	}

}
