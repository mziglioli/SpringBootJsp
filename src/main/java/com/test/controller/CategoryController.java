package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.model.Category;
import com.test.service.CategoryService;
import com.test.util.Catalago;
import com.test.util.Pages;

@Controller
@RequestMapping(value = Catalago.URL_CATEGORY)
public class CategoryController extends ControllerDefault<Category, CategoryService> {

	public CategoryController() {
		super(Pages.CATEGORY);
	}
}