package org.cybertop.back.app.controllers;

import org.cybertop.back.app.model.user.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Resume RestController
 * <p/>
 * Created by owahlen on 10.04.14.
 */
@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login() {
		// This method will simply return status 200 if it is not intercepted by
		// Spring Security
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logout(HttpServletRequest request) throws ServletException {
		request.logout();
	}

	@RequestMapping("/user")
	public User user(User user) {
		return user;
	}
}
