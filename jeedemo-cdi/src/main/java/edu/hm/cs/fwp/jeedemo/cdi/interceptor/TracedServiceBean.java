/*
 * TracedServiceBean.java
 * jeedemo-cdi
 */
package edu.hm.cs.fwp.jeedemo.cdi.interceptor;

import javax.inject.Named;

/**
 * Mit einem TraceInterceptor überwachter Service, bei dem alle Methodenaufrufe 
 * protokolliert werden.
 * 
 * @author theism
 * @version 1.0
 * @since Release 2016.1
 */
@Named
@Traced
public class TracedServiceBean implements Service {
	/**
	 * @see edu.hm.cs.fwp.jeedemo.cdi.interceptor.Service#doSomething(java.lang.String)
	 */
	@Override
	public String doSomething(String what) {
		return "done: " + what;
	}

}
