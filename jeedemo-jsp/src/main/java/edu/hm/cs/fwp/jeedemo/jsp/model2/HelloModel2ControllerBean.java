/*
 * HelloWorldController.java
 * jeedemo-jsp
 */
package edu.hm.cs.fwp.jeedemo.jsp.model2;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * Einfacher Controller für die Views {@code helloWorld} und {@code welcomeUser}
 * .
 * <p>
 * Bei diesem Controller handelt es sich um ein {@code CDI-mananged Bean} mit
 * {@code Application Scope}, da die gleiche Controller-Instanz für die gesamte
 * Applikation wiederverwendet werden kann.
 * </p>
 * 
 * @author theism
 * @since release 2016.0
 */
@Named
@ApplicationScoped
public class HelloModel2ControllerBean implements Controller {

	/**
	 * @see edu.hm.cs.fwp.jeedemo.jsp.model2.Controller#getContextPath()
	 */
	@Override
	public String getContextPath() {
		return "helloModel2";
	}

	/** 
	 * @see edu.hm.cs.fwp.jeedemo.jsp.model2.Controller#doGet(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionResult doGet(HttpServletRequest request) {
		ActionResult result = null;
		if (request.getRequestURI().contains("/helloWorld")) {
			result = helloWorld(request);
		}
		return result;
	}

	/**
	 * @see edu.hm.cs.fwp.jeedemo.jsp.model2.Controller#doPost(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionResult doPost(HttpServletRequest request) {
		ActionResult result = null;
		if (request.getRequestURI().contains("/helloWorld")) {
			result = enterWorld(request);
		}
		return result;
	}

	private ActionResult helloWorld(HttpServletRequest request) {
		Map<String, Object> model = new HashMap<>();
		model.put("formAction", request.getContextPath() + "/model2/" + getContextPath() + "/helloWorld");
		return new ActionResult("/helloModel2/helloWorld.jsp", model);
	}

	private ActionResult enterWorld(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		Map<String, Object> model = new HashMap<>();
		model.put("userName", userName);
		return new ActionResult("/helloModel2/welcomeUser.jsp", model);
	}
}
