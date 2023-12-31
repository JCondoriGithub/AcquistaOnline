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
	public ResponseEntity<Iterable<Client>> getAll() {
		Iterable<Client> clients = clientService.getAll();
		return new ResponseEntity<>(clients, HttpStatus.OK);
	}
	
	@RequestMapping("api/clients/{id}")
	public ResponseEntity<Client> getById(@PathVariable String id) {
		
		Optional<Client> foundClient = clientService.getById(id);
		
		if(foundClient.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "il cliente non è stato trovato!");
		
		return new ResponseEntity<>(foundClient.get(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/clients", method = RequestMethod.POST)
	public ResponseEntity<Client> create(@RequestBody Client client) {
		Client clientCreated = clientService.create(client);
		return new ResponseEntity<>(clientCreated, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "api/clients/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Client> update(@PathVariable String id, @RequestBody Client client) {
		
		Optional<Client> foundClient = clientService.update(id, client);
		
		if(foundClient.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "il cliente non è stato trovato!");
		
		return new ResponseEntity<>(foundClient.get(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/clients/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable String id) {
		
		boolean isDeleted = clientService.delete(id);
		
		if(!isDeleted)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "il cliente non è stato trovato!");
		
		return new ResponseEntity<>("cliente eliminato!", HttpStatus.NO_CONTENT);
	}
}
