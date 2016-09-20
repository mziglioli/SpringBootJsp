package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.model.Product;
import com.test.service.CategoryService;
import com.test.service.ProductService;
import com.test.util.Catalago;
import com.test.util.Pages;

@Controller
@RequestMapping(value = Catalago.URL_PRODUCT)
public class ProductController extends ControllerDefault<Product, ProductService> {

	@Autowired
	private CategoryService categoryService;

	public ProductController() {
		super(Pages.PRODUCT);
	}

	@Override
	protected void myValidate(ModelAndView model, BindingResult bindingResult, Product entity) {
		if (entity.getPrice() == null || entity.getPrice().isNaN() || entity.getPrice() <= 0) {
			bindingResult.addError(new ObjectError("price", "error.invalid.price"));
		}
		if (entity.getCategory() == null || entity.getCategory().getId() == null) {
			bindingResult.addError(new ObjectError("category", "error.invalid.category"));
		}
	}

	@Override
	protected void addExtraModel(ModelAndView model) {
		model.addObject("categories", categoryService.findCategoriesForProduct());
	}
}