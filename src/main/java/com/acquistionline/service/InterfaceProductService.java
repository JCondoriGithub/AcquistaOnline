package com.acquistionline.service;

import java.util.Optional;

import com.acquistionline.model.Product;

public interface InterfaceProductService {

	public Iterable<Product> getAll();
	
	public Optional<Product> getById(String id);
	
	public Product create(Product product);
	
	public Optional<Product> update(String id, Product product);
	
	public boolean delete(String id);
}
