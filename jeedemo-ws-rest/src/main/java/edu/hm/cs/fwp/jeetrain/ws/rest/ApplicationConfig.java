/* ApplicationConfig.java 
 */
package edu.hm.cs.fwp.jeetrain.ws.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import edu.hm.cs.fwp.jeetrain.ws.rest.demo.DemoResource;

/**
 * Configuration class that declares all REST endpoints exposed by the JEETrain
 * application.
 * 
 * @author theism
 * @since 1.0
 */
@ApplicationPath("/rest")
public class ApplicationConfig extends Application {

	/**
	 * Returns all REST resource classes implementing REST endpoints.
	 * 
	 * @see javax.ws.rs.core.Application#getClasses()
	 */
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> result = new HashSet<>();
		result.add(DemoResource.class);
		return result;
	}

}
