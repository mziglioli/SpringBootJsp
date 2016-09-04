package com.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.User;
import com.test.service.UserService;
import com.test.util.Catalago;

@RestController
@RequestMapping(value = Catalago.URL_USER)
public class UserController extends ControllerDefault<User, UserService> {

	public UserController() {
		super("user");
	}
}