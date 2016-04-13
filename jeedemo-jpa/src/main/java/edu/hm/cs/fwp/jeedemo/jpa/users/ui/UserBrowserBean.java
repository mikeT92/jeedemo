/*
 * UserBrowser.java
 * jeedemo-jpa
 */
package edu.hm.cs.fwp.jeedemo.jpa.users.ui;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import edu.hm.cs.fwp.jeedemo.jpa.users.entity.User;
import edu.hm.cs.fwp.jeedemo.jpa.users.entity.UserRepositoryBean;

/**
 * {@code Controller} für den View /user/helloJPA.
 * 
 * @author theism
 * @version 1.0
 * @since Release 2016.1
 */
@Named("userBrowser")
@SessionScoped
public class UserBrowserBean implements Serializable {

	private static final long serialVersionUID = 4612144375872798613L;

	private String userId;

	private User user;

	@Inject
	private UserRepositoryBean repository;

	/**
	 * User-ID des gesuchten Benutzers.
	 * 
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * User-ID des gesuchten Benutzers
	 */
	public String getUserId() {
		return userId;
	}

	public String search() {
		this.user = this.repository.getUserById(this.userId);
		return null;
	}
}
