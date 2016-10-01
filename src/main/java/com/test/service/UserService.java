package com.test.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.model.User;
import com.test.model.UserAuthority;
import com.test.model.enuns.Authorities;
import com.test.model.enuns.Status;
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

	public User convertUserFormToUser(UserForm form) {
		return new User(form.getId(), form.getName(), form.getUsername(), null, getStatusFromUserForm(form),
				getAuthoritiesFromUserForm(form));
	}

	private String getStatusFromUserForm(UserForm form) {
		if (form.getState() != null && form.getState().equals("on")) {
			return Status.ATIVO.getDescricao();
		} else {
			return Status.DESATIVO.getDescricao();
		}
	}

	private Set<UserAuthority> getAuthoritiesFromUserForm(UserForm form) {
		Set<UserAuthority> authorities = new HashSet<>();
		if (form.isUser()) {
			authorities.add(new UserAuthority(Authorities.USER.getRole()));
		}
		if (form.isManager()) {
			authorities.add(new UserAuthority(Authorities.MANAGER.getRole()));
		}
		if (form.isAdmin()) {
			authorities.add(new UserAuthority(Authorities.ADMIN.getRole()));
		}
		return authorities;
	}

	@Override
	protected void beforeInsert(ModelAndView model, User entity, RedirectAttributes redir) {
		super.beforeInsert(model, entity, redir);
		entity.setPassword(null);
		// TODO
		// send email to set password
	}

	@Override
	protected void beforeUpdate(ModelAndView model, User entity, RedirectAttributes redir) {
		super.beforeUpdate(model, entity, redir);
		// avoid change password
		User userDB = getRepository().findOne(entity.getId());
		entity.setPassword(userDB.getPassword());
	}
}