package com.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import com.test.model.Category;
import com.test.model.Product;
import com.test.repository.ProductRepository;

@Service
public class ProductService extends ServiceDefault<Product, ProductRepository> {

	public boolean hasProductWithCategory(Category category) {
		List<Product> products = repository.findByCategory(category);
		if (products == null || products.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	protected void addUniqueError(ModelAndView model, Product entity) {
		List<ObjectError> errors = new ArrayList<>();
		Object[] args = new Object[] { "name" };
		errors.add(new ObjectError("name", null, args, "error.duplicate.key"));
		model.addObject("errors", errors.stream().collect(Collectors.toList()));
	}

	@Override
	protected boolean isUnique(Product entity) {
		Product p = repository.findByName(entity.getName());
		if (p == null || p.getId() == entity.getId()) {
			return true;
		}
		return false;
	}
}