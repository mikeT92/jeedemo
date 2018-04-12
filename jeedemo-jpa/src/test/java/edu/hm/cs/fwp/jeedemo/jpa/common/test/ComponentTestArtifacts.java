package edu.hm.cs.fwp.jeedemo.jpa.common.test;

import org.jboss.shrinkwrap.api.spec.WebArchive;

import edu.hm.cs.fwp.jeedemo.jpa.generic.repo.GenericRepository;

/**
 * Common component test artifacts required for any component test.
 *
 * @author Michael Theis (msg)
 * @version 1.0
 * @since release 18.2
 */
public final class ComponentTestArtifacts {

	/**
	 * Adds all common component test artifacts to the given deployment.
	 */
	public static void addToDeployment(WebArchive deployment) {
		deployment.addPackages(true, "edu.hm.cs.fwp.jeedemo.jpa.common.persistence") //
				.addClass(GenericRepository.class) //
				.addClass(ComponentTestAuthenticationFilter.class) //
				.addAsResource("arquillian-persistence.xml", "META-INF/persistence.xml") //
				.addAsWebInfResource("arquillian-web.xml", "web.xml") //
				.addAsWebInfResource("arquillian-beans.xml", "beans.xml");
	}
}
