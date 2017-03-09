package edu.hm.cs.fwp.jeedemo.cdi.injection;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.hm.cs.fwp.jeedemo.cdi.injection.Consumer;
import edu.hm.cs.fwp.jeedemo.cdi.injection.Service;
import edu.hm.cs.fwp.jeedemo.cdi.injection.impl.ConsumerBean;
import edu.hm.cs.fwp.jeedemo.cdi.injection.impl.ServiceBean;

/**
 * Einfacher Komponententest zur Demonstration von Dependency Injection.
 * 
 * @author theism
 * @version 1.0
 * @since 24.04.2013 22:08:13
 */
@RunWith(Arquillian.class)
public class InjectionDemoComponentTest {
	/**
	 * Erstellt ein JAR mit allen für den Komponententest benötigten Klassen und
	 * Ressourcen.
	 */
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)//
				.addClass(Consumer.class)//
				.addClass(ConsumerBean.class)//
				.addClass(Service.class)//
				.addClass(ServiceBean.class)//
				.addAsManifestResource("arquillian-beans.xml", "beans.xml");
	}

	@Inject
	private Consumer underTest;

	/**
	 * Test method for
	 * {@link edu.hm.cs.fwp.jeetrain.business.cdi.impl.ConsumerBean#useService(java.lang.String)}
	 * .
	 */
	@Test
	public void testUseService() {
		final String expected = "done: CDI test";
		String result = this.underTest.useService("CDI test");
		assertEquals(expected, result);
	}
}
