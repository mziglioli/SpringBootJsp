package com.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = Catalago.URL_HOME, method = RequestMethod.GET)
	public ModelAndView home(ModelAndView model) {
		model.setViewName(Pages.HOME);
		return model;
	}

	@RequestMapping(value = Catalago.URL_PROFILE, method = RequestMethod.GET)
	public ModelAndView profile(ModelAndView model) {
		model.setViewName(Pages.PROFILE);
		model.addObject("user", service.getUserFromUserLogado());
		return model;
	}
}