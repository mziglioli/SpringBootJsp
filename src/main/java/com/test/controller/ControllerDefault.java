package com.test.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	public final ModelAndView save(ModelAndView model, @Valid E entity, BindingResult bindingResult,
			RedirectAttributes redir) {
		if (validate(model, bindingResult, entity)) {
			service.save(model, entity, redir);
			return new ModelAndView("redirect:/" + entityURL + "/");
		} else {
			model.setViewName(entityURL + Pages.ADD);
			model.addObject("saveURL", "/" + entityURL + Catalago.URL_SAVE);
			return model;
		}
	}

	@PostMapping(value = Catalago.URL_UPDATE)
	public ModelAndView update(ModelAndView model, @Valid E entity, BindingResult bindingResult,
			RedirectAttributes redir) {
		if (validate(model, bindingResult, entity)) {
			service.update(model, entity, redir);
			return new ModelAndView("redirect:/" + entityURL + "/");
		} else {
			model.setViewName(entityURL + Pages.EDIT);
			model.addObject("saveURL", "/" + entityURL + Catalago.URL_UPDATE);
			return model;
		}
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
	public ModelAndView delete(ModelAndView model, @PathVariable Long id, RedirectAttributes redir) {
		if (service.delete(model, id, redir)) {
			return new ModelAndView("redirect:/" + entityURL + "/");
		}
		buildModelList(model);
		return model;
	}

	@GetMapping(value = Catalago.URL_EDIT)
	public ModelAndView edit(ModelAndView model, @PathVariable Long id) {
		buildModelEdit(model, id);
		return model;
	}

	@GetMapping(value = Catalago.URL_NEW)
	public ModelAndView newEntity(ModelAndView model) {
		buildModelNew(model);
		return model;
	}

	@GetMapping(value = Catalago.URL_BASE)
	public ModelAndView findAll(ModelAndView model) {
		buildModelList(model);
		return model;
	}

	private void buildModelNew(ModelAndView model) {
		model.addObject("saveURL", "/" + entityURL + Catalago.URL_SAVE);
		model.addObject("entityName", entityURL);
		model.setViewName(entityURL + Pages.ADD);
		addExtraModel(model);
	}

	private void buildModelEdit(ModelAndView model, Long id) {
		model.addObject("entity", service.findById(id));
		model.addObject("updateURL", "/" + entityURL + "/update/" + id);
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