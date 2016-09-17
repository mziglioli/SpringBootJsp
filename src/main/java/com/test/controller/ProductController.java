package com.test.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.test.model.Product;
import com.test.service.ProductService;
import com.test.util.Catalago;

@RestController
@RequestMapping(value = Catalago.URL_PRODUCT)
public class ProductController extends ControllerDefault<Product, ProductService> {

	public ProductController() {
		super("product");
	}

	@Override
	protected void myValidate(ModelAndView model, BindingResult bindingResult, Product entity) {
		if (entity.getPrice() == null || entity.getPrice().isNaN() || entity.getPrice() <= 0) {
			bindingResult.addError(new ObjectError("price", "error.invalid.price"));
		}
	}
}