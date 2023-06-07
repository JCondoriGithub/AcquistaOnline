package com.acquistionline.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.acquistionline.model.Client;

@Repository
public interface InterfaceClientRepo extends CrudRepository<Client, String> {

}
