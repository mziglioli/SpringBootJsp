package com.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.Product;
import com.test.service.ProductService;
import com.test.util.Catalago;

@RestController
@RequestMapping(value = Catalago.URL_PRODUCT)
public class ProductController extends ControllerDefault<Product, ProductService> {

	public ProductController() {
		super("product");
	}
}