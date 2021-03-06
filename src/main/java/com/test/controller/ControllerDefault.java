package com.test.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.model.EntityJpaClass;
import com.test.service.ServiceDefault;
import com.test.util.Catalago;
import com.test.util.Pages;

import lombok.Getter;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class ControllerDefault<E extends EntityJpaClass, T extends ServiceDefault> {

	@Autowired
	@Getter
	protected T service;

	private String entityURL = "";

	public ControllerDefault(String entityURL) {
		this.entityURL = entityURL;
	}

	@PostMapping(value = Catalago.URL_SAVE)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ModelAndView save(ModelAndView model, @Valid E entity, BindingResult bindingResult,
			RedirectAttributes redir) {
		if (validate(model, bindingResult, entity)) {
			if (service.save(model, entity, redir)) {
				return new ModelAndView("redirect:/" + entityURL + "/");
			}
			buildModelNew(model);
			model.addObject("entity", entity);
		}
		return model;
	}

	@PostMapping(value = Catalago.URL_UPDATE)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ModelAndView update(ModelAndView model, @Valid E entity, BindingResult bindingResult,
			RedirectAttributes redir) {
		if (validate(model, bindingResult, entity)) {
			if (service.save(model, entity, redir)) {
				return new ModelAndView("redirect:/" + entityURL + "/");
			}
			buildModelEdit(model, entity.getId());
			model.addObject("entity", entity);
		}
		return model;
	}

	private boolean validate(ModelAndView model, BindingResult bindingResult, E entity) {
		myValidate(model, bindingResult, entity);
		if (bindingResult.hasErrors()) {
			addError(model, bindingResult);
			model.addObject("entity", entity);
			model.addObject("entityName", entityURL);
			return false;
		}
		return true;
	}

	protected void myValidate(ModelAndView model, BindingResult bindingResult, E entity) {

	}

	private void addError(ModelAndView model, BindingResult bindingResult) {
		List<ObjectError> errors = bindingResult.getAllErrors();
		if (errors != null && !errors.isEmpty()) {
			model.addObject("errors", errors.stream().collect(Collectors.toList()));
		}
	}

	protected void addExtraModel(ModelAndView model) {

	}

	@PostMapping(value = Catalago.URL_DELETE)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ModelAndView delete(ModelAndView model, @PathVariable Long id, RedirectAttributes redir) {
		if (service.delete(model, id, redir)) {
			return new ModelAndView("redirect:/" + entityURL + "/");
		}
		buildModelList(model);
		return model;
	}

	@GetMapping(value = Catalago.URL_EDIT)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ModelAndView edit(ModelAndView model, @PathVariable Long id) {
		buildModelEdit(model, id);
		return model;
	}

	@GetMapping(value = Catalago.URL_NEW)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ModelAndView newEntity(ModelAndView model) {
		buildModelNew(model);
		return model;
	}

	@GetMapping(value = Catalago.URL_BASE)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ModelAndView findAll(ModelAndView model) {
		buildModelList(model);
		return model;
	}

	private void buildModelNew(ModelAndView model) {
		model.addObject("action", "/" + entityURL);
		model.addObject("entityName", entityURL);
		model.setViewName(entityURL + Pages.ADD);
		addExtraModel(model);
	}

	private void buildModelEdit(ModelAndView model, Long id) {
		model.addObject("entity", service.findById(id));
		model.addObject("action", "/" + entityURL);
		model.addObject("entityName", entityURL);
		model.setViewName(entityURL + Pages.EDIT);
		addExtraModel(model);
	}

	private void buildModelList(ModelAndView model) {
		model.addObject("entityList", service.findAll());
		model.addObject("entityName", entityURL);
		model.addObject("deleteURL", "/" + entityURL + "/delete/");
		model.setViewName(entityURL + Pages.LIST);
	}
}