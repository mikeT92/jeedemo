package edu.hm.cs.fwp.jeedemo.cdi.interceptor;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Einfacher Komponententest zur Demonstration von CDI Interceptoren.
 * 
 * @author theism
 * @version 1.0
 * @since Release 2016.1
 */
@RunWith(Arquillian.class)
public class InterceptorDemoComponentTest {
	/**
	 * Erstellt ein WAR mit allen für den Komponententest benötigten Klassen und
	 * Ressourcen.
	 */
	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class)//
				.addClass(Service.class)//
				.addClass(TracedServiceBean.class)//
				.addClass(Traced.class)//
				.addClass(TraceInterceptor.class)//
				.addAsWebInfResource("arquillian-beans.xml", "beans.xml");
				// .addAsWebInfResource("arquillian-beans-with-interceptors.xml", "beans.xml");
	}

	@Inject
	private Service underTest;

	@Test
	public void testUseService() {
		final String expected = "done: CDI test";
		String result = this.underTest.doSomething("CDI test");
		assertEquals(expected, result);
	}
}
