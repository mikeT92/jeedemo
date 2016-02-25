/*
 * ConsumerBeanComponentTest.java
 * jeedemo-cdi
 */
package edu.hm.cs.fwp.jeedemo.cdi.producer;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Komponenten-Test zur Demonstration von CDI-Producern.
 * 
 * @author theism
 *
 */
@RunWith(Arquillian.class)
public class ConsumerBeanComponentTest {

	/** 
	 * Erstellt ein JAR mit allen für den Komponententest benötigten Klassen und Ressourcen.
	 */
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class).addClass(ConcreteProduct.class).addClass(ConsumerBean.class)
				.addClass(ProducerBean.class).addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	/**
	 * Inijiziertes Bean, das getestet werden soll.
	 */
	@Inject
	private ConsumerBean underTest;

	@Test
	public void testUseProductReturnsExpectedResult() {
		final String expected = "done: CDI Producer test";
		String result = this.underTest.useProduct("CDI Producer test");
		assertEquals(expected, result);
	}

}
