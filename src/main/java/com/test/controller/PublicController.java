package com.test.controller;

import java.util.Collection;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.model.Menu;
import com.test.model.User;
import com.test.model.UserAuthority;
import com.test.model.enuns.Authorities;
import com.test.model.enuns.Status;
import com.test.service.MenuService;
import com.test.service.UserService;
import com.test.util.Catalago;
import com.test.util.Pages;

@Controller
@RequestMapping(value = "/public")
public class PublicController {

	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;

	@GetMapping(value = Catalago.URL_BASE)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.setViewName(Pages.INDEX);
		return model;
	}

	@GetMapping(value = Catalago.URL_LOGIN)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model.setViewName(Pages.LOGIN);
		return model;
	}

	@GetMapping(value = Catalago.URL_LOGOUT)
	public ModelAndView logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		ModelAndView model = new ModelAndView();
		model.setViewName(Pages.INDEX);
		return model;
	}

	@GetMapping(value = Catalago.URL_DENIED)
	public ModelAndView denied() {
		ModelAndView model = new ModelAndView();
		model.setViewName(Pages.DENIED);
		return model;
	}

	@GetMapping(value = Catalago.URL_ERROR)
	public ModelAndView error() {
		ModelAndView model = new ModelAndView();
		model.setViewName(Pages.ERROR);
		return model;
	}

	@GetMapping(value = Catalago.URL_NOT_FOUND)
	public ModelAndView notFound() {
		ModelAndView model = new ModelAndView();
		model.setViewName(Pages.DENIED);
		return model;
	}

	// FIXME
	// remove, just to test
	@GetMapping(value = "/start")
	public ModelAndView auth() {

		Collection<UserAuthority> userAuthorities = new HashSet<>();
		userAuthorities.add(new UserAuthority(Authorities.USER.getRole()));

		createMenuLink("label.menu.home", "/user/home", "mif-home icon", userAuthorities);
		createMenuLink("label.menu.categories", "/category/", "mif-location-city icon", userAuthorities);
		createMenuLink("label.menu.products", "/product/", "mif-libray icon", userAuthorities);

		User user = new User();
		user.setName("test");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode("test"));
		user.setStatus(Status.ATIVO.getDescricao());
		user.setUsername("test@test.com");
		Collection<UserAuthority> authorities = new HashSet<>();
		authorities.add(new UserAuthority(Authorities.USER.getRole()));
		user.setAuthorities(authorities);
		userService.save(user);

		ModelAndView model = new ModelAndView();
		model.setViewName(Pages.INDEX);
		return model;
	}

	private void createMenuLink(String nome, String link, String icon, Collection<UserAuthority> auths) {
		Menu menu = new Menu();
		menu.setIcon(icon);
		menu.setLink(link);
		menu.setName(nome);
		menu.setAuthorities(auths);
		menuService.save(menu);
	}
}