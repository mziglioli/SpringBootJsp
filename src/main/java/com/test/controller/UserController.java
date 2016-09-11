package com.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.test.model.User;
import com.test.service.UserService;
import com.test.util.Catalago;
import com.test.util.Pages;

@RestController
@RequestMapping(value = Catalago.URL_USER)
public class UserController extends ControllerDefault<User, UserService> {

	public UserController() {
		super("user");
	}

	@GetMapping(value = Catalago.URL_HOME)
	public ModelAndView home(ModelAndView model) {
		model.setViewName(Pages.HOME);
		return model;
	}

	@GetMapping(value = Catalago.URL_PROFILE)
	public ModelAndView profile(ModelAndView model) {
		model.setViewName(Pages.PROFILE);
		model.addObject("user", service.getUserFromUserLogado());
		return model;
	}
}