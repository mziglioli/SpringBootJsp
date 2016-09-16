package com.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public Product findByName(String name);

	public List<Product> findByCategory(String category);

}
