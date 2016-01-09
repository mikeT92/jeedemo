/* DemoResource.java 
 */
package edu.hm.cs.fwp.jeetrain.ws.rest.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author theism
 */
@Path("/demo")
public class DemoResource {

	@GET
	@Path("/{id}")
	public DemoEntity getEntityById( @PathParam("id") long id) {
		return new DemoEntity();
	}
}
