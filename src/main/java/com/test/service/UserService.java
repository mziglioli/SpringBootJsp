package com.test.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.test.model.User;
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
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}