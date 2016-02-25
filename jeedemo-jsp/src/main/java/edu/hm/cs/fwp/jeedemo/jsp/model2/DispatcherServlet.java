/* HelloServlet.java @(#)%PID%
 */
package edu.hm.cs.fwp.jeedemo.jsp.model2;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Einfache Implementierung eines Dispatcher-Servlets innerhalb einer
 * Model2-Architektur.
 * 
 * @author theism
 * @since release 2016.0
 */
@WebServlet(urlPatterns = { "/model2/*" }, loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 590153928180563245L;

	/**
	 * Name des Session-Attributes, unter dem ein Model bei Redirects in der
	 * Session zwischengespeichert wird.
	 */
	public static final String RETAINED_MODEL_ATTRIBUTE_NAME = DispatcherServlet.class.getName() + ".retainedModel";

	/**
	 * Collector für alle definierten {@code Controller}.
	 */
	@Inject
	Instance<Controller> controllers;

	/**
	 * Delegiert die Verarbeitung des Requests an den passenden Controller.
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().log("request: " + request.getMethod() + " " + request.getRequestURI());
		Controller targetController = findTargetController(getControllerContextPath(request));
		ActionResult actionResult = targetController.doGet(request);
		propagateModelToNextView(request, actionResult);
		navigateToNextView(request, response, actionResult);
	}

	/**
	 * Delegiert die Verarbeitung des Requests an den passenden Controller.
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().log("request: " + request.getMethod() + " " + request.getRequestURI());
		Controller targetController = findTargetController(getControllerContextPath(request));
		ActionResult actionResult = targetController.doPost(request);
		propagateModelToNextView(request, actionResult);
		navigateToNextView(request, response, actionResult);
	}

	/**
	 * Ermittelt aus der Request-URI den Kontextpfad des angesprochenen
	 * Controllers.
	 *
	 * @param request
	 *            eingehender HTTP-Request
	 * @return Kontextpfad des angesprochenen Controllers; niemals {@code null}.
	 * @throws IllegalStateException
	 *             - falls aus der Request-URI kein Controller-Kontextpfad
	 *             abgeleitet werden kann.
	 */
	private String getControllerContextPath(HttpServletRequest request) {
		String result = null;
		String requestURI = request.getRequestURI();
		String[] requestURIParts = requestURI.split("/");
		if (requestURIParts.length >= 4) {
			result = requestURIParts[3];
		}
		if (result == null) {
			throw new IllegalStateException(
					"Request URI [" + requestURI + "] does not contain a controller context path!");
		}
		return result;
	}

	/**
	 * Ermittelt den zum angegebenen Controller-Kontextpfad passenden
	 * Controller.
	 *
	 * @param controllerContextPath
	 * @return passender Controller; niemals {@code null}.
	 * @throws IllegalException
	 *             - falls zum angegebenen Controller-Kontextpfad keine
	 *             Controller registriert ist.
	 */
	private Controller findTargetController(String controllerContextPath) {
		Controller result = null;
		Iterator<Controller> itr = this.controllers.iterator();
		while (itr.hasNext()) {
			Controller currentController = itr.next();
			if (controllerContextPath.equals(currentController.getContextPath())) {
				result = currentController;
				break;
			}
		}
		if (result == null) {
			throw new IllegalStateException(
					"Unable to find any controller mapped to context path [" + controllerContextPath + "]");
		}
		return result;
	}

	/**
	 * Propagiert das im Actionergebnis enthaltene Model an den nächsten
	 * anzuzeigenden View.
	 * <p>
	 * Bei einem {@code Redirect} auf den nächsten View wird das Model in der
	 * HTTP-Session zwischengespeichert, da erste der nächste GET-Request zur
	 * Weiterleitung auf den View führt.
	 * </p>
	 * <p>
	 * Bei einem {@code Forward} wird das Model als einzelne Requestattribute in
	 * den HTTP-Request übernommen.
	 * </p>
	 * 
	 * @param request
	 *            eingangener HTTP-Request
	 * @param actionResult
	 *            Ergebnis der letzten Controller-Aktion
	 */
	private void propagateModelToNextView(HttpServletRequest request, ActionResult actionResult) {
		if (actionResult.useRedirect()) {
			if (!actionResult.getModel().isEmpty()) {
				HttpSession session = request.getSession();
				session.setAttribute(RETAINED_MODEL_ATTRIBUTE_NAME, actionResult.getModel());
			}
		} else {
			for (Map.Entry<String, Object> currentModelEntry : actionResult.getModel().entrySet()) {
				request.setAttribute(currentModelEntry.getKey(), currentModelEntry.getValue());
			}
			HttpSession session = request.getSession();
			Map<String, Object> retainedModel = (Map<String, Object>) session
					.getAttribute(RETAINED_MODEL_ATTRIBUTE_NAME);
			if (retainedModel != null) {
				for (Map.Entry<String, Object> currentModelEntry : retainedModel.entrySet()) {
					request.setAttribute(currentModelEntry.getKey(), currentModelEntry.getValue());
				}
				session.removeAttribute(RETAINED_MODEL_ATTRIBUTE_NAME);
			}
		}
	}

	/**
	 * Navigiert über Redirect oder Forward auf den nächsten View, der als
	 * Ergebnis der letzten Controller-Action angezeigt werden soll.
	 *
	 * @param request
	 *            eingegangener HTTP-Request
	 * @param response
	 *            ausgehende HTTP-Response
	 * @param actionResult
	 *            Ergebnis der letzten Controller-Aktion.
	 * @throws ServletException,
	 *             falls beim Forward etwas schiefgeht
	 * @throws IOException,
	 *             falls beim Forward oder Redirect etwas schiefgeht
	 */
	private void navigateToNextView(HttpServletRequest request, HttpServletResponse response, ActionResult actionResult)
			throws ServletException, IOException {
		if (actionResult.useRedirect()) {
			String encodedURL = response.encodeRedirectURL(actionResult.getNextView());
			response.sendRedirect(encodedURL);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(actionResult.getNextView());
			dispatcher.forward(request, response);
		}
	}
}
