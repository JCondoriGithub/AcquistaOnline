package com.acquistionline.controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.acquistionline.model.Product;
import com.acquistionline.service.InterfaceProductService;

@RestController
public class ProductController {

	@Autowired
	private InterfaceProductService productService;

	public ProductController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("api/products")
	public Iterable<Product> getAll() {
		return productService.getAll();
	}
	
	@RequestMapping("api/products/{id}")
	public Product getById(@PathVariable String id) {
		
		Optional<Product> foundProduct = productService.getById(id);
		
		if(foundProduct.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "il prodotto non è stato trovato!");
		
		return foundProduct.get();
	}
	
	@RequestMapping(value = "api/products", method = RequestMethod.POST)
	public Product create(@RequestBody Product product) {
		return productService.create(product);
	}
	
	@RequestMapping(value = "api/products/{id}", method = RequestMethod.PUT)
	public Product update(@PathVariable String id, @RequestBody Product product) {
		
		Optional<Product> foundProduct = productService.update(id, product);
		
		if(foundProduct.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "il prodotto non è stato trovato!");
		
		return foundProduct.get();
	}
	
	@RequestMapping(value = "api/products/{id}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable String id) {
		
		boolean isDeleted = productService.delete(id);
		
		if(!isDeleted)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "il prodotto non è stato trovato!");
		
		return isDeleted;
	}
}
