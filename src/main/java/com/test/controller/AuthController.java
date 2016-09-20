package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.service.UserService;
import com.test.util.Catalago;
import com.test.util.Pages;

@Controller
public class AuthController {

	@Autowired
	private UserService userService;

	@GetMapping(value = Catalago.URL_HOME_BASE)
	public ModelAndView home(ModelAndView model) {
		model.setViewName(Pages.HOME);
		return model;
	}

	@GetMapping(value = Catalago.URL_PROFILE)
	public ModelAndView profile(ModelAndView model) {
		model.setViewName(Pages.PROFILE);
		model.addObject("user", userService.getUserFromUserLogado());
		return model;
	}
}