/* HelloBean.java @(#)%PID%
 */
package edu.hm.cs.fwp.jeedemo.jsf.presentation.hello;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Size;

/**
 * {@code Controller} für die Views /hello/helloWorld und /hello/welcomeUser.
 * 
 * @author theism
 * @version 1.0
 * @since release 2016.1 11.04.2013 23:53:35
 */
@Named("hello")
@SessionScoped
public class HelloBean implements Serializable {

	private static final long serialVersionUID = 4069463169212859579L;

	@Size(max = 8)
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String name) {
		this.userName = name;
	}

	public String enterWorld() {
		return "welcomeUser?faces-redirect=true";
	}
}
