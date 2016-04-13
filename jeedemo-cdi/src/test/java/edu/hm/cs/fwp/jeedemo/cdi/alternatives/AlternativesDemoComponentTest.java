/*
 * ConsumerWithAlternativesComponentTest.java
 * jeedemo-cdi
 */
package edu.hm.cs.fwp.jeedemo.cdi.alternatives;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.hm.cs.fwp.jeedemo.cdi.alternatives.impl.DefaultAlternativeServiceBean;
import edu.hm.cs.fwp.jeedemo.cdi.alternatives.impl.MockAlternativeServiceBean;

/**
 * Komponenten-Test zur Demonstration von CDI-Alternatives.
 * 
 * @author theism
 *
 */
@RunWith(Arquillian.class)
public class AlternativesDemoComponentTest {
	/**
	 * Erstellt ein JAR mit allen für den Komponententest benötigten Klassen und
	 * Ressourcen.
	 */
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)//
				.addClass(ConsumerBean.class)//
				.addClass(AlternativeService.class)//
				.addClass(DefaultAlternativeServiceBean.class)//
				.addClass(MockAlternativeServiceBean.class)//
				//.addAsManifestResource("arquillian-beans.xml", "beans.xml");
				.addAsManifestResource("arquillian-beans-with-alternatives.xml", "beans.xml");
	}

	@Inject
	private ConsumerBean underTest;

	/**
	 * Test method for
	 * {@link edu.hm.cs.fwp.jeetrain.business.cdi.impl.ConsumerBean#useService(java.lang.String)}
	 * .
	 */
	@Test
	public void testUseService() {
		final String expected = "done CDI test by MockAlternativeServiceBean";
		String result = this.underTest.useService("CDI test");
		assertEquals(expected, result);
	}
}
