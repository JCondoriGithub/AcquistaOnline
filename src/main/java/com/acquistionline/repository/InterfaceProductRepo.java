package com.acquistionline.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.acquistionline.model.Product;

@Repository
public interface InterfaceProductRepo extends CrudRepository<Product, String> {

}
