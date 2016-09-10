package com.test.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.Menu;
import com.test.model.User;
import com.test.repository.MenuRepository;

@Service
public class MenuService extends ServiceDefault<Menu, MenuRepository> {

	@Autowired
	private UserService userService;

	public Collection<Menu> getMenuByUserLoged() {
		User user = userService.getUserFromUserLogado();
		if (user != null) {
			return repository.findByAuthorities(user.getUserAuth());
		}
		return null;
	}
}