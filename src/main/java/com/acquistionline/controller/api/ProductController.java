package com.acquistionline.controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Iterable<Product>> getAll() {
		Iterable<Product> products = productService.getAll();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@RequestMapping("api/products/{id}")
	public ResponseEntity<Product> getById(@PathVariable String id) {
		
		Optional<Product> foundProduct = productService.getById(id);
		
		if(foundProduct.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "il prodotto non è stato trovato!");
		
		return new ResponseEntity<>(foundProduct.get(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/products", method = RequestMethod.POST)
	public ResponseEntity<Product> create(@RequestBody Product product) {
		Product productCreated = productService.create(product);
		return new ResponseEntity<>(productCreated, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "api/products/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Product> update(@PathVariable String id, @RequestBody Product product) {
		
		Optional<Product> foundProduct = productService.update(id, product);
		
		if(foundProduct.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "il prodotto non è stato trovato!");
		
		return new ResponseEntity<>(foundProduct.get(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable String id) {
		
		boolean isDeleted = productService.delete(id);
		
		if(!isDeleted)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "il prodotto non è stato trovato!");
		
		return new ResponseEntity<>("il prodotto è stato eliminato!", HttpStatus.NO_CONTENT);
	}
}
