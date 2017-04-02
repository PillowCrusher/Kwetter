
package com.robvangastel.kwetter.bean;

import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Named
@SessionScoped
public class LoggedUser implements Serializable {

//	@Inject
//	private UserService userService;

	private String username;
	private User user;
	
	public LoggedUser() {
		// find who signed in and set name
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		username = request.getRemoteUser();
//		user = userService.findByUsername(username);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index";
	}
}
