package com.acquistionline.service;

import java.util.Optional;

import com.acquistionline.model.Client;

public interface InterfaceClientService {

	public Iterable<Client> getAll();
	
	public Optional<Client> getById(String id);
	
	public Client create(Client client);
	
	public Optional<Client> update(String id, Client client);
	
	public boolean delete(String id);
}
