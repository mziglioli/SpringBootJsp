package com.test.service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.test.model.User;
import com.test.model.form.UserForm;
import com.test.repository.UserRepository;

@Service
public class UserService extends ServiceDefault<User, UserRepository> {

	public User findByUsername(String username) {
		return getRepository().findByUsername(username);
	}

	public Long getQtdUsers() {
		return getRepository().count();
	}

	public User getUserFromUserLogado() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			Authentication auth = context.getAuthentication();
			if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
				return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			}
		}
		return null;
	}

	public UserForm convertUserToUserForm(Long id) {
		User user = getRepository().findOne(id);
		if (user != null) {
			return new UserForm(user.getId(), user.getName(), user.getUsername(), user.getStatus(), user.isUser(),
					user.isManager(), user.isAdmin());
		}
		return new UserForm();
	}
}