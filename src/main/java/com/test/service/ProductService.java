package com.test.service;

import org.springframework.stereotype.Service;

import com.test.model.Product;
import com.test.repository.ProductRepository;

@Service
public class ProductService extends ServiceDefault<Product, ProductRepository> {

}