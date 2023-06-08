package com.acquistionline.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acquistionline.model.Product;
import com.acquistionline.repository.InterfaceProductRepo;

@Service
public class ProductService implements InterfaceProductService {

	@Autowired
	private InterfaceProductRepo productRepo;
	
	public ProductService() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Iterable<Product> getAll() {
		return productRepo.findAll();
	}

	@Override
	public Optional<Product> getById(String id) {
		return productRepo.findById(id);
	}

	@Override
	public Product create(Product product) {
		return productRepo.save(product);
	}

	@Override
	public Optional<Product> update(String id, Product product) {

		Optional<Product> foundProduct = productRepo.findById(id);
		
		if(foundProduct.isEmpty())
			return Optional.empty();

		if(product.getName() != null)
			foundProduct.get().setName(product.getName());
		
		if(product.getPrice() != 0)
			foundProduct.get().setPrice(product.getPrice());
		
		if(product.getDescription() != null)
			foundProduct.get().setDescription(product.getDescription());
		
		productRepo.save(foundProduct.get());
		
		return foundProduct;
	}

	@Override
	public boolean delete(String id) {

		Optional<Product> foundProduct = productRepo.findById(id);

		if(foundProduct.isEmpty())
			return false;
		
		productRepo.delete(foundProduct.get());
		return true;
	}

}
