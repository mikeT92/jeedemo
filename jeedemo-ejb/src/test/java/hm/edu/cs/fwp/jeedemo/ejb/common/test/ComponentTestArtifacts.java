/*
 * jeedemo-ejb:ComponentTestArtifacts.java
 * Copyright (c) Michael Theis 2017
 */
package hm.edu.cs.fwp.jeedemo.ejb.common.test;

import org.jboss.shrinkwrap.api.spec.WebArchive;

import hm.edu.cs.fwp.jeedemo.ejb.interceptors.TraceInterceptor;

/**
 * Utility that adds common component test resources to a deployment.
 * 
 * @author mikeT92
 * @version 1.0
 * @since 25.02.2018
 */
public final class ComponentTestArtifacts {

	public static void attachToDeployment(WebArchive deployment) {
		deployment.addClass(TraceInterceptor.class).addAsWebInfResource("arquillian-beans.xml", "beans.xml");
	}

	/**
	 * Private constructor to avoid unintended instantiation.
	 */
	private ComponentTestArtifacts() {
		super();
	}

}
