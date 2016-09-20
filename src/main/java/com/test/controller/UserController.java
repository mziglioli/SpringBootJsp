package com.test.controller;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.model.User;
import com.test.model.enuns.Authorities;
import com.test.model.form.UserForm;
import com.test.service.UserService;
import com.test.util.Catalago;
import com.test.util.Pages;

@Controller
@RequestMapping(value = Catalago.URL_USER)
public class UserController extends ControllerDefaultAdmin<User, UserService> {

	private static final String entityURL = Pages.USER;

	public UserController() {
		super(entityURL);
	}

	@PostMapping(value = Catalago.URL_SAVE)
	@PreAuthorize("hasRole('ADMIN')")
	public final ModelAndView save(ModelAndView model, @Valid UserForm entity, BindingResult bindingResult,
			RedirectAttributes redir) {
		if (validate(model, bindingResult, entity)) {
			// service.save(model, entity, redir);
			return new ModelAndView("redirect:/" + entityURL + "/");
		} else {
			model.setViewName(entityURL + Pages.ADD);
			model.addObject("saveURL", "/" + entityURL + Catalago.URL_SAVE);
			return model;
		}
	}

	@PostMapping(value = Catalago.URL_UPDATE)
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView update(ModelAndView model, @Valid UserForm entity, BindingResult bindingResult,
			RedirectAttributes redir) {
		if (validate(model, bindingResult, entity)) {
			// service.update(model, entity, redir);
			return new ModelAndView("redirect:/" + entityURL + "/");
		} else {
			model.setViewName(entityURL + Pages.EDIT);
			model.addObject("saveURL", "/" + entityURL + Catalago.URL_UPDATE);
			return model;
		}
	}

	private boolean validate(ModelAndView model, BindingResult bindingResult, UserForm entity) {
		if (bindingResult.hasErrors()) {
			addError(model, bindingResult);
			model.addObject("entity", entity);
			model.addObject("entityName", entityURL);
			return false;
		}
		return true;
	}

	@Override
	protected void addExtraModel(ModelAndView model, Long id) {
		model.addObject("roles", Authorities.getAuthorities());
		UserForm form = service.convertUserToUserForm(id);
		model.addObject("form", form);
	}

}