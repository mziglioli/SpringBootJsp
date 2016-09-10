package com.test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = Catalago.URL_BASE, method = RequestMethod.POST)
	public final ModelAndView save(ModelAndView model, @Valid E entity) {
		service.save(model, entity);
		model.setViewName(entityURL + Pages.LIST);
		return model;
	}

	@RequestMapping(value = Catalago.URL_BASE, method = RequestMethod.PUT)
	public ModelAndView update(ModelAndView model, @Valid E entity) {
		service.update(model, entity);
		model.setViewName(entityURL + Pages.LIST);
		return model;
	}

	@RequestMapping(value = Catalago.URL_ID, method = RequestMethod.DELETE)
	public ModelAndView delete(ModelAndView model, @PathVariable Long id) {
		service.delete(model, id);
		model.setViewName(entityURL + Pages.LIST);
		return model;
	}

	@RequestMapping(value = Catalago.URL_ID, method = RequestMethod.GET)
	public ModelAndView findById(ModelAndView model, @PathVariable Long id) {
		model.addObject("entity", service.findById(id));
		model.setViewName(entityURL + Pages.EDIT);
		return model;
	}

	@RequestMapping(value = Catalago.URL_NEW, method = RequestMethod.GET)
	public ModelAndView newEntity(ModelAndView model) {
		model.setViewName(entityURL + Pages.ADD);
		return model;
	}

	@RequestMapping(value = Catalago.URL_BASE, method = RequestMethod.GET)
	public ModelAndView findAll(ModelAndView model) {
		model.addObject("entityList", service.findAll());
		model.setViewName(entityURL + Pages.LIST);
		return model;
	}
}