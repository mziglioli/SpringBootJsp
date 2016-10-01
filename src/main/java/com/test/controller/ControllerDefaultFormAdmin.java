package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.service.ServiceDefault;
import com.test.util.Catalago;
import com.test.util.Pages;

import lombok.Getter;

@SuppressWarnings({ "rawtypes" })
public abstract class ControllerDefaultFormAdmin<T extends ServiceDefault> {

	@Autowired
	@Getter
	protected T service;

	private String entityURL = "";

	public ControllerDefaultFormAdmin(String entityURL) {
		this.entityURL = entityURL;
	}

	protected void addExtraModel(ModelAndView model) {

	}

	protected void addExtraModel(ModelAndView model, Long id) {

	}

	@PostMapping(value = Catalago.URL_DELETE)
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView delete(ModelAndView model, @PathVariable Long id, RedirectAttributes redir) {
		if (service.delete(model, id, redir)) {
			return new ModelAndView("redirect:/" + entityURL + "/");
		}
		buildModelList(model);
		return model;
	}

	@GetMapping(value = Catalago.URL_EDIT)
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView edit(ModelAndView model, @PathVariable Long id) {
		buildModelEdit(model, id);
		return model;
	}

	@GetMapping(value = Catalago.URL_NEW)
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView newEntity(ModelAndView model) {
		buildModelNew(model);
		return model;
	}

	@GetMapping(value = Catalago.URL_BASE)
	@PreAuthorize("hasRole('ADMIN')")
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
		addExtraModel(model, id);
	}

	private void buildModelList(ModelAndView model) {
		model.addObject("entityList", service.findAll());
		model.addObject("entityName", entityURL);
		model.addObject("deleteURL", "/" + entityURL + "/delete/");
		model.setViewName(entityURL + Pages.LIST);
		addExtraModel(model);
	}
}