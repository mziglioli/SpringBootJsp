package com.test.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

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
			Set<String> auths = user.getAuth();
			List<Menu> menus = repository.findAll();
			Set<Menu> set = new HashSet<>();
			try (Stream<Menu> stream = menus.stream()) {
				stream.forEach(m -> {
					auths.forEach(a -> {
						if (m.getAuthorities().contains(a)) {
							set.add(m);
						}
					});
				});
			}
			return new ArrayList<>(set);
		}
		return null;
	}
}