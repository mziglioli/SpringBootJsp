package com.test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public final ModelAndView save(ModelAndView model, @Valid E entity, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// TODO
		} else {
			service.save(model, entity);
		}
		model.setViewName(entityURL + Pages.LIST);
		return model;
	}

	@PostMapping(value = Catalago.URL_UPDATE)
	public ModelAndView update(ModelAndView model, @Valid E entity, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// TODO
		} else {
			service.update(model, entity);
		}
		model.setViewName(entityURL + Pages.LIST);
		return model;
	}

	@DeleteMapping(value = Catalago.URL_DELETE)
	public ModelAndView delete(ModelAndView model, @PathVariable Long id) {
		service.delete(model, id);
		model.setViewName(entityURL + Pages.LIST);
		return model;
	}

	@GetMapping(value = Catalago.URL_EDIT)
	public ModelAndView edit(ModelAndView model, @PathVariable Long id) {
		model.addObject("entity", service.findById(id));
		model.addObject("updateURL", "/" + entityURL + "/update/" + id);
		model.addObject("deleteURL", "/" + entityURL + "/delete/" + id);
		model.addObject("entityName", entityURL);
		model.setViewName(entityURL + Pages.EDIT);
		return model;
	}

	@GetMapping(value = Catalago.URL_NEW)
	public ModelAndView newEntity(ModelAndView model) {
		model.addObject("saveURL", "/" + entityURL + Catalago.URL_SAVE);
		model.addObject("entityName", entityURL);
		model.setViewName(entityURL + Pages.ADD);
		return model;
	}

	@GetMapping(value = Catalago.URL_BASE)
	public ModelAndView findAll(ModelAndView model) {
		model.addObject("entityList", service.findAll());
		model.setViewName(entityURL + Pages.LIST);
		return model;
	}
}