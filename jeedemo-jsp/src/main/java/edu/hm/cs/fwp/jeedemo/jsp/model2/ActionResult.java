/*
 * ActionResult.java
 * jeedemo-jsp
 */
package edu.hm.cs.fwp.jeedemo.jsp.model2;

import java.util.Collections;
import java.util.Map;

/**
 * Ergebnis einer Action aus einem {@code Controller} bestehend aus der URI des
 * nächsten anzuzeigenden Views und dem anzuzeigenden Model.
 * 
 * @author theism
 * @release 2016.0
 */
public final class ActionResult {
	
	private final String nextView;

	private final Map<String, Object> model;

	private final boolean redirect;

	public ActionResult(String nextView) {
		this(nextView, Collections.emptyMap(), false);
	}

	public ActionResult(String nextView, Map<String, Object> model) {
		this(nextView, model, false);
	}

	public ActionResult(String nextView, Map<String, Object> model, boolean redirect) {
		this.nextView = nextView;
		this.model = model;
		this.redirect = redirect;
	}

	/**
	 * URI des nächsten anzuzeigenden Views.
	 */
	public String getNextView() {
		return nextView;
	}

	/**
	 * Daten, die im angegebenen View angezeigt werden sollen.
	 */
	public Map<String, Object> getModel() {
		return this.model;
	}
	
	/**
	 * {@code true}, falls auf den nächsten View über Redirect navigiert werden soll; sonst {@code false}.
	 */
	public boolean useRedirect() {
		return this.redirect;
	}
}
