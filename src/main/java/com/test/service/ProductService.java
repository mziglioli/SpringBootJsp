package com.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
}