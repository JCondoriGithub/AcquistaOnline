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

import com.acquistionline.model.Client;
import com.acquistionline.service.InterfaceClientService;

@RestController
public class ClientController {
	
	@Autowired
	private InterfaceClientService clientService;

	public ClientController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@RequestMapping("api/clients")
	public Iterable<Client> getAll() {
		return clientService.getAll();
	}
	
	@RequestMapping("api/clients/{id}")
	public Client getById(@PathVariable String id) {
		
		Optional<Client> foundClient = clientService.getById(id);
		
		if(foundClient.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "il cliente non è stato trovato!");
		
		return foundClient.get();
	}
	
	@RequestMapping(value = "api/clients", method = RequestMethod.POST)
	public Client create(@RequestBody Client client) {
		return clientService.create(client);
	}
	
	@RequestMapping(value = "api/clients/{id}", method = RequestMethod.PUT)
	public Client update(@PathVariable String id, @RequestBody Client client) {
		
		Optional<Client> foundClient = clientService.update(id, client);
		
		if(foundClient.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "il cliente non è stato trovato!");
		
		return foundClient.get();
	}
	
	@RequestMapping(value = "api/clients/{id}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable String id) {
		
		boolean isDeleted = clientService.delete(id);
		
		if(!isDeleted)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "il cliente non è stato trovato!");
		
		return isDeleted;
	}
}
