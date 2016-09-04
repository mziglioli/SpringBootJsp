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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.test.model.User;
import com.test.model.UserAuthority;
import com.test.model.enuns.Authorities;
import com.test.model.enuns.Status;
import com.test.service.UserService;
import com.test.util.Pages;

@Controller
@RequestMapping(value = "/public")
public class PublicController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.setViewName(Pages.INDEX);
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model.setViewName(Pages.LOGIN);
		return model;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		model.setViewName(Pages.HOME);
		return model;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String denied(Model model) {
		return Pages.DENIED;
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(Model model) {
		return Pages.DENIED;
	}

	// FIXME
	// remove, just to test
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public void auth() {
		User user = new User();
		user.setNome("test");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode("test"));
		user.setStatus(Status.ATIVO.getDescricao());
		user.setUsername("test@test.com");
		Collection<UserAuthority> authorities = new HashSet<>();
		authorities.add(new UserAuthority(Authorities.USER.getRole()));
		user.setAuthorities(authorities);
		userService.save(user);
	}
}