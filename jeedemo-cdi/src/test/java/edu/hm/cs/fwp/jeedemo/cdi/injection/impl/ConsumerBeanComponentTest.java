package edu.hm.cs.fwp.jeedemo.cdi.injection.impl;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.hm.cs.fwp.jeedemo.cdi.injection.Consumer;

/**
 * Simple test class demonstrating tests of CDI mananged beans using
 * {@code Arquillian}.
 * 
 * @author theism
 * @version %PR% %PRT% %PO%
 * @since release 1.0 24.04.2013 22:08:13
 */
@RunWith(Arquillian.class)
public class ConsumerBeanComponentTest {
	/**
	 * Erstellt ein JAR mit allen für den Komponententest benötigten Klassen und
	 * Ressourcen.
	 */
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class).addClass(ConsumerBean.class).addClass(ServiceBean.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
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
