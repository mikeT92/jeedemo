/*
 * jeedemo-jaxrs:RestApplicationConfig.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.jaxrs;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * REST configuration for this application.
 * <p>
 * Mandatory for {@code JAX-RS}: This is the only way to trigger REST endpoint
 * support.
 * </p>
 * 
 * @author theism
 * @version 1.0
 * @since 09.03.2017
 */
@ApplicationPath("rest")
public class RestApplicationConfig extends Application {

}
