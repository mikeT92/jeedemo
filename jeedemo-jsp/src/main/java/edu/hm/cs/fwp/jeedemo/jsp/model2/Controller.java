/*
 * Controller.java
 * jeedemo-jsp
 */
package edu.hm.cs.fwp.jeedemo.jsp.model2;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller in einer Model2-Architektur
 * 
 * @author theism
 * @since release 2016.0
 */
public interface Controller {

	/**
	 * Liefert den Kontextpfad zurück, auf den dieser Controller hört.
	 */
	String getContextPath();

	/**
	 * Bereitet die Anzeige des nächsten Views vor.
	 */
	ActionResult doGet(HttpServletRequest request);

	/**
	 * Führt die im Request angegebene Aktion aus und bereitet die Anzeige des
	 * nächsten Views vor.
	 */
	ActionResult doPost(HttpServletRequest request);
}
